package com.jackson.ojjudgeservice.codesandbox.impl;

import com.jackson.ojjudgeservice.codesandbox.CodeSandbox;
import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeRequest;
import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeResponse;

/**
 * 示例代码沙箱（仅为了跑通业务流程）
 *
 * @author jackson
 */
public class ExampleCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return new ExecuteCodeResponse();
    }
}
