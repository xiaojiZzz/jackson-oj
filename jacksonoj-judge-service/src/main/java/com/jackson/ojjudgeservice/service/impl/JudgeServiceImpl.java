package com.jackson.ojjudgeservice.service.impl;

import cn.hutool.json.JSONUtil;
import com.jackson.ojcommon.common.StatusCode;
import com.jackson.ojcommon.exception.BusinessException;
import com.jackson.ojjudgeservice.codesandbox.CodeSandbox;
import com.jackson.ojjudgeservice.codesandbox.factory.CodeSandboxFactory;
import com.jackson.ojjudgeservice.codesandbox.proxy.CodeSandboxProxy;
import com.jackson.ojjudgeservice.service.JudgeService;
import com.jackson.ojjudgeservice.strategy.JudgeContext;
import com.jackson.ojjudgeservice.strategy.JudgeManager;
import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeRequest;
import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeResponse;
import com.jackson.ojmodel.model.dto.codesandbox.JudgeInfo;
import com.jackson.ojmodel.model.dto.question.JudgeCase;
import com.jackson.ojmodel.model.entity.Question;
import com.jackson.ojmodel.model.entity.QuestionSubmit;
import com.jackson.ojmodel.model.enums.JudgeInfoMessageEnum;
import com.jackson.ojmodel.model.enums.QuestionSubmitStatusEnum;
import com.jackson.ojserviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题服务实现
 *
 * @author jackson
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionFeignClient questionFeignClient;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:remote}")
    private String codeSandboxType;

    /**
     * 执行判题
     *
     * @param questionSubmitId
     * @return
     */
    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {

        // 1. 传入题目的提交 id，获取到对应的题目提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(StatusCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if (question == null) {
            throw new BusinessException(StatusCode.NOT_FOUND_ERROR, "题目不存在");
        }

        // 2. 如果题目提交状态不为等待中，就不用重复执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(StatusCode.OPERATION_ERROR, "题目正在判题中");
        }

        // 3. 更改判题（题目提交）的状态为 判题中 ，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(StatusCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        // 4. 调用代码沙箱，获取执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(codeSandboxType);
        // 使用代理模式增强代码沙箱的功能
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();

        // 5. 根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);

        // 6. 修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(executeCodeResponse.getStatus() == 0 || executeCodeResponse.getStatus() == 2 ?
                QuestionSubmitStatusEnum.SUCCEED.getValue() : QuestionSubmitStatusEnum.FAILED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(StatusCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        // 7. 修改题目通过数
        if (JudgeInfoMessageEnum.ACCEPTED.getText().equals(judgeInfo.getMessage())) {
            questionFeignClient.updateQuestionAcceptedNumById(questionId);
        }

        return questionFeignClient.getQuestionSubmitById(questionId);
    }
}
