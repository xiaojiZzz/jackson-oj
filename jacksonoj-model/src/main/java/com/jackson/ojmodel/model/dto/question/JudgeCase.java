package com.jackson.ojmodel.model.dto.question;

import lombok.Data;

/**
 * 题目用例
 *
 * @author jackson
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
