package com.jackson.ojjudgeservice.codesandbox.impl;

import com.jackson.ojjudgeservice.codesandbox.CodeSandbox;
import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeRequest;
import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeResponse;

/**
 * 第三方代码沙箱
 *
 * @author jackson
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {

    /**
     * 运行代码，获取输出结果
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        // 调用其他人实现的代码沙箱，未完待续
        return null;
    }
}
