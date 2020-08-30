package com.linq.framework.security.service;


import com.linq.common.constant.Constants;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.common.core.redis.RedisService;
import com.linq.common.exception.CustomException;
import com.linq.common.exception.user.CaptchaException;
import com.linq.common.exception.user.CaptchaExpireException;
import com.linq.common.exception.user.UserPasswordNotMatchException;
import com.linq.common.utils.MessageUtils;
import com.linq.common.utils.spring.SpringUtils;
import com.linq.common.utils.string.StringUtils;
import com.linq.framework.manager.AsyncManager;
import com.linq.framework.manager.factory.AsyncFactory;
import com.linq.common.core.domain.LoginUser;
import com.linq.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisService redisService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     *
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        // 查询验证码
        String captcha = redisService.getCacheObject(verifyKey);
        // 清除验证码
        redisService.deleteObject(verifyKey);
        if (StringUtils.isBlank(captcha)) {
            // 记录登录日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        // 验证码错误
        if (!code.equalsIgnoreCase(captcha)) {
            // 记录登录日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {  // 用户或密码错误,登录失败
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        // 记录登录日志
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
