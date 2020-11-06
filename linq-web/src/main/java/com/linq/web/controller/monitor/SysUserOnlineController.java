package com.linq.web.controller.monitor;

import cn.hutool.db.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.linq.common.constant.Constants;
import com.linq.common.core.domain.LoginUser;
import com.linq.common.core.redis.RedisService;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.common.result.code.ResponseCode;
import com.linq.common.utils.string.StringUtils;
import com.linq.system.domain.SysUserOnline;
import com.linq.system.service.SysUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 8:01 下午
 * @Description: 在线用户监控
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/monitor/online")
public class SysUserOnlineController {
    @Autowired
    private SysUserOnlineService userOnlineService;

    @Autowired
    private RedisService redisService;

    @PreAuthorize("@ss.hasPermi('monitor:online:list')")
    @GetMapping("/list")
    public Map<String, Object> list(String ipaddr, String userName) {
        // 拿出所有当前登录用户token标识
        Collection<String> keys = redisService.keys(Constants.LOGIN_TOKEN_KEY + "*");
        List<SysUserOnline> userOnlineList = new ArrayList<SysUserOnline>();
        for (String key : keys) {
            LoginUser user = redisService.getCacheObject(key);
            // 先判断传参是否为空
            if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName)) {
                // 通过登录地址/用户名称查询信息
                if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername())) {

                    userOnlineList.add(userOnlineService.findOnlineByInfo(ipaddr, userName, user));
                }
            } else if (StringUtils.isNotEmpty(ipaddr)) {
                // 通过登录地址查询信息
                if (StringUtils.equals(ipaddr, user.getIpaddr())) {
                    userOnlineList.add(userOnlineService.findOnlineByIpaddr(ipaddr, user));
                }
            } else if (StringUtils.isNotEmpty(userName) && StringUtils.isNotNull(user.getUser())) {
                // 通过用户名称查询信息
                if (StringUtils.equals(userName, user.getUsername())) {
                    userOnlineList.add(userOnlineService.findOnlineByUserName(userName, user));
                }
            } else {
                userOnlineList.add(userOnlineService.setLoginUserToUserOnline(user));
            }
        }
        Collections.reverse(userOnlineList);
        userOnlineList.removeAll(Collections.singleton(null));
        return new HashMap<String, Object>() {{
            put("total", userOnlineList.size());
            put("code", ResponseCode.OK.getCode());
            put("rows", userOnlineList);
        }};
    }

    /**
     * 强退用户
     */
    @PreAuthorize("@ss.hasPermi('monitor:online:forceLogout')")
    @Log(title = "在线用户", businessType = BusinessType.DELETE)
    @GetMapping("/{tokenId}")
    public Result<Object> forceLogout(@PathVariable String tokenId) {
        redisService.deleteObject(Constants.LOGIN_TOKEN_KEY + tokenId);
        return ResultUtils.success();
    }
}
