package com.linq.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.linq.common.constant.Constants;
import com.linq.common.core.domain.TreeSelect;
import com.linq.common.core.domain.entity.SysDept;
import com.linq.common.core.redis.RedisService;
import com.linq.common.utils.string.StringUtils;
import com.linq.framework.web.domain.Server;
import com.linq.system.service.SysDeptService;
import com.linq.system.service.SysRoleService;
import com.linq.system.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/25 4:55 下午
 * @Description:
 * @Version: 1.0.0
 */
@SpringBootTest
public class TestDemos {
    @Autowired
    private RedisService redisService;
    @Autowired
    private SysDeptService deptService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;

    @Test
    public void testSet() {
        Object cacheObject = redisService.getCacheObject(Constants.LOGIN_TOKEN_KEY + "6c637b64-a130-40dd-ab67-75d7ab0840ab");
        System.out.println(cacheObject);
    }

    @Test
    public void testString() {
        System.out.println(StringUtils.capitalize("asaaadasd"));
    }

    @Test
    public void testToDeptTree() {
        List<TreeSelect> treeSelects = deptService.buildDeptTreeSelect(deptService.findDeptList(new SysDept()));
        System.err.println(JSON.toJSON(treeSelects));
    }

    @Test
    public void testCheckUsernameUnique() {
        System.err.println(userService.checkUsernameUnique("ry"));
    }

    @Test
    public void testfindRoleAll() {
        System.err.println(roleService.findRoleAll());
    }
    @Test
    public void testfindServer() {
        Server server = new Server();
        try {
            server.copyTo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(server);
    }
}
