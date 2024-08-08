package com.jackson.ojjudgeservice.codesandbox.proxy;

import com.jackson.ojjudgeservice.codesandbox.CodeSandbox;
import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeRequest;
import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用静态代理模式，增强代码沙箱运行代码的功能
 *
 * @author jackson
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {

    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息：" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息：" + executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
