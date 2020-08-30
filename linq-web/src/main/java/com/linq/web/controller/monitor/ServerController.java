package com.linq.web.controller.monitor;

import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.framework.web.domain.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 7:11 下午
 * @Description: 服务器监控
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController {
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public Result<Object> getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return ResultUtils.success(server);
    }
}
