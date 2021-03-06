package com.linq.common.constant;

/**
 * 用户常量信息
 */
public class UserConstants {
    /**
     * 平台内系统用户的唯一标志
     */
    public static final String SYS_USER = "SYS_USER";
    /**
     * 平台管理员用户名
     */
    public static final String SYS_ADMIN = "admin";

    /**
     * 正常状态
     */
    public static final String NORMAL = "0";

    /**
     * 异常状态
     */
    public static final String EXCEPTION = "1";

    /**
     * 用户封禁状态
     */
    public static final String USER_DISABLE = "1";

    /**
     * 角色正常状态
     */
    public static final String ROLE_NORMAL = "0";

    /**
     * 角色封禁状态
     */
    public static final String ROLE_DISABLE = "1";

    /**
     * 部门正常状态
     */
    public static final String DEPT_NORMAL = "0";

    /**
     * 部门停用状态
     */
    public static final String DEPT_DISABLE = "1";

    /**
     * 字典正常状态
     */
    public static final String DICT_NORMAL = "0";

    /**
     * 是否为系统默认（是）
     */
    public static final String YES = "Y";

    /**
     * 是否菜单外链（是）
     */
    public static final String YES_FRAME = "0";

    /**
     * 是否菜单外链（否）
     */
    public static final String NO_FRAME = "1";

    /**
     * 菜单类型（目录）
     */
    public static final String TYPE_DIR = "M";

    /**
     * 菜单类型（菜单）
     */
    public static final String TYPE_MENU = "C";

    /**
     * 菜单类型（按钮）
     */
    public static final String TYPE_BUTTON = "F";

    /**
     * Layout组件标识
     */
    public final static String LAYOUT = "Layout";

    /**
     * 校验返回结果码
     */
    public final static String UNIQUE = "0";
    public final static String NOT_UNIQUE = "1";

    /**
     * 新闻审核
     * 0: 正在审核中
     * 1: 审核通过 | 成功
     * 2: 审核未通过 | 失败
     */
    public final static String PASSING = "0";
    public final static String PASSED = "1";
    public final static String NOT_PASS = "2";
    /**
     * 新闻属性 0.头条区新闻 1.幻灯片区新闻 2.热点区新闻
     * Headline area news
     * Slide show news
     * Hot spot news
     */
    public final static String HEADLINE_AREA_NEWS = "头条区新闻";
    public final static String SLIDE_SHOW_NEWS = "幻灯片区新闻";
    public final static String HOT_SPOT_NEWS = "热点区新闻";
    /**
     * 新闻公开: 0公开 1私有
     */
    public final static String PUBLIC = "0";
    public final static String PRIVATE = "1";


}
