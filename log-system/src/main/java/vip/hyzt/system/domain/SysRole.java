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

    private int roleSort;

    /** Role status (0 normal, 1 disabled) */
    private int status;

    private String roleKey;

    /** 1=All data permissions, 2=Custom data permissions, 3=Only personal data permissions */
    private int dataScope;

    /** 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示） */
    private boolean menuCheckStrictly;

    /** 部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ） */
    private boolean deptCheckStrictly;

    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;

    private String[] menuIds;

    public SysRole() {

    }

    public SysRole(String roleId) {
        this.roleId = roleId;
    }

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

    public int getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(int roleSort) {
        this.roleSort = roleSort;
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

    public boolean isMenuCheckStrictly() {
        return menuCheckStrictly;
    }

    public void setMenuCheckStrictly(boolean menuCheckStrictly) {
        this.menuCheckStrictly = menuCheckStrictly;
    }

    public boolean isDeptCheckStrictly() {
        return deptCheckStrictly;
    }

    public void setDeptCheckStrictly(boolean deptCheckStrictly) {
        this.deptCheckStrictly = deptCheckStrictly;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isAdmin() {
        return isAdmin(this.roleId);
    }

    public static boolean isAdmin(String roleId) {
        return "1".equals(roleId);
    }

    public String[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String[] menuIds) {
        this.menuIds = menuIds;
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
