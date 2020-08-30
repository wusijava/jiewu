package com.linq.framework.security.handler;

import com.alibaba.fastjson.JSON;
import com.linq.common.constant.Constants;
import com.linq.common.result.ResultUtils;
import com.linq.common.utils.ServletUtils;
import com.linq.common.utils.string.StringUtils;
import com.linq.framework.manager.AsyncManager;
import com.linq.framework.manager.factory.AsyncFactory;
import com.linq.common.core.domain.LoginUser;
import com.linq.framework.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 林义清
 * @Date: 2020/8/25 10:25 上午
 * @Description: 自定义退出处理类 返回成功 登录认证失败处理器
 * @Version: 1.0.0
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String username = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(ResultUtils.success("退出成功")));
    }
}
