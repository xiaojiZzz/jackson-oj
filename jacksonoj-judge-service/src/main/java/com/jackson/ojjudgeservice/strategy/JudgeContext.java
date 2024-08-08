package com.jackson.ojjudgeservice.strategy;

import com.jackson.ojmodel.model.dto.codesandbox.JudgeInfo;
import com.jackson.ojmodel.model.dto.question.JudgeCase;
import com.jackson.ojmodel.model.entity.Question;
import com.jackson.ojmodel.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 判题上下文信息（用于定义在策略中传递的参数）
 *
 * @author jackson
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    public QuestionSubmit questionSubmit;
}
