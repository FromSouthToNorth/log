package vip.hyzt.system.domain;

import java.util.Date;

/**
 * User object sys_user
 * @author hy
 * @since 2021/10/10
 */
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private String nickName;

    private String email;

    private String phoneNumber;

    /** 0=男,1=女,2=未知 */
    private String sex;

    /** 帐号状态（0正常 1停用） */
    private String status;

    /** 最后登录IP */
    private String loginIp;

    /** 最后登录时间 */
    private Date loginDate;

}
