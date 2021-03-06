package vip.hyzt.common.constant;

/**
 * 用户常量信息
 * @author hy
 * @since 2021/10/20
 */
public class UserConstants {

    /**
     * 平台内系统用户的唯一标志
     */
    public static final String SYS_USER = "SYS_USER";

    /**
     * 父菜单id
     */
    public static final String  PARENT_ID = "0";

    /** 正常状态 */
    public static final String NORMAL = "";

    /** 异常状态 */
    public static final int EXCEPTION = 1;

    /** 用户封禁状态 */
    public static final int USER_DISABLE = 1;

    /** 角色封禁状态 */
    public static final int ROLE_DISABLE = 1;

    /** 字典正常状态 */
    public static final int DICT_NORMAL = 0;

    /** 是否为系统默认（是） */
    public static final String YES = "Y";

    /** 是否菜单外链（是） */
    public static final int YES_FRAME = 0;

    /** 是否菜单外链（否） */
    public static final int NO_FRAME = 1;

    /** 菜单类型（目录） */
    public static final String TYPE_DIR = "M";

    /** 菜单类型（菜单） */
    public static final String TYPE_MENU = "C";

    /** 菜单类型（按钮） */
    public static final String TYPE_BUTTON = "F";

    /** Layout组件标识 */
    public final static String LAYOUT = "Layout";

    /** ParentView组件标识 */
    public final static String PARENT_VIEW = "ParentView";

    /** InnerLink组件标识 */
    public final static String INNER_LINK = "InnerLink";

    /** 校验返回结果码 */
    public final static String UNIQUE = "0";
    public final static String NOT_UNIQUE = "1";

    /**
     * 用户名长度限制
     */
    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;

    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 16;

}
