package com.hohai.component.security;

import com.alibaba.fastjson.JSON;
import com.hohai.component.common.constant.HttpStatus;
import com.hohai.component.common.core.model.AjaxResult;
import com.hohai.component.common.util.ServletUtils;
import com.hohai.component.common.util.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Lynn
 * @date 2022/3/10 16:04
 * @description 认证失败处理类 返回未授权
 **/

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        //访问失败消息渲染到客户端
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
    }
}
