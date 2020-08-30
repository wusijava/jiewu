package com.linq.web.controller.common;

import com.linq.common.constant.Constants;
import com.linq.common.core.redis.RedisService;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.common.utils.uuid.IdUtils;
import com.linq.framework.security.config.bean.LoginProperties;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 林义清
 * @Date: 2020/8/25 10:58 上午
 * @Description: 验证码操作处理
 * @Version: 1.0.0
 */
@Api(tags = "验证码接口")
@RestController
public class CaptchaController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private LoginProperties loginProperties;

    @ApiOperation(value = "生成验证码", notes = "生成验证码详情")
    @GetMapping("/code/img")
    public Result<HashMap<String, String>> getCodeImg() throws IOException {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        System.err.println("获取的code为: " + captcha.text() + "  uuid为: " + uuid);
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        // 保存到redis
        redisService.setCacheObject(verifyKey, captcha.text(), loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        return ResultUtils.success(new HashMap<String, String>() {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }});
    }
}
