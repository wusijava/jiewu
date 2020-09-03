package com.linq.web.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 林义清
 * @Date: 2020/9/3 6:58 下午
 * @Description:
 * @Version: 1.0.0
 */
@Controller
public class PageController {
    
    @GetMapping("/doc/sql")
    public String toSqlDoc() {
        return "doc/sql";
    }

}
