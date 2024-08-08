package com.jackson.ojjudgeservice.codesandbox.factory;

import com.jackson.ojjudgeservice.codesandbox.CodeSandbox;
import com.jackson.ojjudgeservice.codesandbox.impl.ExampleCodeSandbox;
import com.jackson.ojjudgeservice.codesandbox.impl.RemoteCodeSandbox;
import com.jackson.ojjudgeservice.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * 代码沙箱工厂（根据字符串参数创建指定的代码沙箱实例）
 *
 * @author jackson
 */
public class CodeSandboxFactory {

    /**
     * 使用静态工厂方法创建代码沙箱实例
     *
     * @param type 沙箱类型
     * @return
     */
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
