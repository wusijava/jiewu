package com.linq.common.core.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 林义清
 * @Date: 2020/8/23 6:05 下午
 * @Description: 登录信息
 * @Version: 1.0.0
 */
@Data
public class LoginBody {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";

}
