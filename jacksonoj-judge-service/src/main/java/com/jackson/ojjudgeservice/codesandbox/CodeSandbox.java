package com.jackson.ojjudgeservice.codesandbox;

import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeRequest;
import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 *
 * @author jackson
 */
public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
