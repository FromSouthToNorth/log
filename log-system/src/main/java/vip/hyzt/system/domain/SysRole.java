package vip.hyzt.system.domain;

/**
 * Role table sys_role
 * @author hy
 * @since 2021/10/13
 */
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String roleId;

    private String roleName;

    /** Role status (0 normal, 1 disabled) */
    private int status;

    private String roleKey;

    /** 1=All data permissions, 2=Custom data permissions, 3=Only personal data permissions */
    private int dataScope;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public int getDataScope() {
        return dataScope;
    }

    public void setDataScope(int dataScope) {
        this.dataScope = dataScope;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", roleKey='" + roleKey + '\'' +
                ", dataScope='" + dataScope + '\'' +
                '}';
    }
}
