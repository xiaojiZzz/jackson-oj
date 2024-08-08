package com.jackson.ojjudgeservice.service;

import com.jackson.ojmodel.model.entity.QuestionSubmit;

/**
 * 判题服务
 *
 * @author jackson
 */
public interface JudgeService {

    /**
     * 执行判题
     *
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
