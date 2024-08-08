package com.jackson.ojmodel.model.dto.codesandbox;

import lombok.Data;

/**
 * 判题信息
 *
 * @author jackson
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗内存
     */
    private Long memory;

    /**
     * 消耗时间
     */
    private Long time;
}
