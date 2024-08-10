package com.jackson.ojjudgeservice.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.ojcommon.common.StatusCode;
import com.jackson.ojcommon.exception.BusinessException;
import com.jackson.ojjudgeservice.codesandbox.CodeSandbox;
import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeRequest;
import com.jackson.ojmodel.model.dto.codesandbox.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 *
 * @author jackson
 */
public class RemoteCodeSandbox implements CodeSandbox {

    /**
     * 定义鉴权请求头
     */
    private static final String AUTH_REQUEST_HEADER = "auth";

    /**
     * 定义鉴权密钥
     */
    private static final String AUTH_REQUEST_SECRET = "jackson_7788";

    /**
     * 运行代码，获取输出结果
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:8205/codesandbox/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(StatusCode.API_REQUEST_ERROR, "executeCode remoteSandbox error, message = " + responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
