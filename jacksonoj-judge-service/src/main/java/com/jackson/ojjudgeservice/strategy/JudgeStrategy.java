package com.jackson.ojjudgeservice.strategy;

import com.jackson.ojmodel.model.dto.codesandbox.JudgeInfo;

/**
 * 判题策略
 *
 * @author jackson
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
