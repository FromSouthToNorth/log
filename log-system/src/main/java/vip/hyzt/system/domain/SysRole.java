package vip.hyzt.system.domain;

/**
 * Role table sys_role
 * @author hy
 * @since 2021/10/13
 */
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private String roleName;

    private String roleKey;

    private String roleSort;

    /** 1=所有数据权限,2=自定义数据权限,3=仅本人数据权限 */
    private String dataScope;

}
