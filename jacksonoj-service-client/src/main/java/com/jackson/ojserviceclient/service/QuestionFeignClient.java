package com.jackson.ojserviceclient.service;

import com.jackson.ojmodel.model.entity.Question;
import com.jackson.ojmodel.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 问题服务
 *
 * @author jackson
 */
@FeignClient(name = "jacksonoj-question-service", path = "/jacksonoj/question/inner")
public interface QuestionFeignClient {

    /**
     * 根据 id 获取题目
     *
     * @param questionId
     * @return
     */
    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") long questionId);

    /**
     * 根据 id 获取题目提交
     *
     * @param questionSubmitId
     * @return
     */
    @GetMapping("/question_submit/get/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId);

    /**
     * 根据 id 更新题目提交状态等
     *
     * @param questionSubmit
     * @return
     */
    @PostMapping("/question_submit/update")
    boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit);

    /**
     * 根据 id 更新题目正确提交数
     *
     * @param questionId
     * @return
     */
    @PostMapping("/question/update")
    boolean updateQuestionAcceptedNumById(@RequestParam("questionId") long questionId);
}
