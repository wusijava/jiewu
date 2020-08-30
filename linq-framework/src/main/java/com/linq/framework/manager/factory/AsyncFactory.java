package com.linq.framework.manager.factory;


import com.linq.common.constant.Constants;
import com.linq.common.utils.LogUtils;
import com.linq.common.utils.ServletUtils;
import com.linq.common.utils.spring.SpringUtils;
import com.linq.common.utils.string.StringUtils;
import com.linq.system.domain.SysLogininfo;
import com.linq.system.domain.SysOperLog;
import com.linq.system.service.SysLogininfoService;
import com.linq.system.service.SysOperLogService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 */
@Slf4j
public class AsyncFactory {
    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     *
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setOperLocation(StringUtils.getHttpCityInfo(operLog.getOperIp()));
                SpringUtils.getBean(SysOperLogService.class).save(operLog);
            }
        };
    }


    /**
     * 记录登陆信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     *
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message,
                                             final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = StringUtils.getIp(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = StringUtils.getHttpCityInfo(ip);
                // 打印信息到日志
                String s = LogUtils.getBlock(ip) +
                        address +
                        LogUtils.getBlock(username) +
                        LogUtils.getBlock(status) +
                        LogUtils.getBlock(message);
                log.info(s, args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLogininfo logininfo = new SysLogininfo();
                logininfo.setUsername(username);
                logininfo.setIpaddr(ip);
                logininfo.setLoginLocation(address);
                logininfo.setBrowser(browser);
                logininfo.setOs(os);
                logininfo.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
                    logininfo.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    logininfo.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(SysLogininfoService.class).save(logininfo);
            }
        };
    }


}
