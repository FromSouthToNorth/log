package vip.hyzt.system.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * User object sys_user
 * @author hy
 * @since 2021/10/10
 */
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String userName;

    private String realName;

    /** User type (0 system users) */
    private int userType;
    
    private String password;

    private String avatar;
    
    private Date birthday;

    /** Gender (0-default unknown, 1-male, 2-female) */
    private int sex;

    private String email;

    private String phone;

    /** User status (0 normal 1 disabled) */
    private int status;

    /** Delete flag (0 means existence and 1 means deletion) */
    private int delFlag;

    /** Role object */
    private List<SysRole> roles;

    /** Role group */
    private String[] roleIds;

    /** Role ID */
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(String userId) {
        return "1".equals(userId);
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", userType=" + userType +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", roles=" + roles +
                ", roleIds=" + Arrays.toString(roleIds) +
                ", roleId='" + roleId + '\'' +
                '}';
    }

}
