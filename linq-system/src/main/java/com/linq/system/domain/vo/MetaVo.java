package com.linq.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 路由显示信息
 *
 * @author ruoyi
 */
@Data
@AllArgsConstructor
public class MetaVo {
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/icons/svg
     */
    private String icon;


}
