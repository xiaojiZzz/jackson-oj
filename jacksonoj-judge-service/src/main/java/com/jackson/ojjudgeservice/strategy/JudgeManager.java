package com.jackson.ojjudgeservice.strategy;

import com.jackson.ojjudgeservice.strategy.impl.CppJudgeStrategy;
import com.jackson.ojjudgeservice.strategy.impl.DefaultJudgeStrategy;
import com.jackson.ojjudgeservice.strategy.impl.JavaJudgeStrategy;
import com.jackson.ojmodel.model.dto.codesandbox.JudgeInfo;
import com.jackson.ojmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Component;

/**
 * 根据不同的语言选择不同的判题策略
 *
 * @author jackson
 */
@Component
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy;
        switch (language) {
            case "java":
                judgeStrategy = new JavaJudgeStrategy();
                break;
            case "cpp":
                judgeStrategy = new CppJudgeStrategy();
                break;
            default:
                judgeStrategy = new DefaultJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
