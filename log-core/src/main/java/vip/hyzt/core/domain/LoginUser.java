package vip.hyzt.core.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vip.hyzt.system.domain.SysUser;

import java.util.Collection;
import java.util.Set;

/**
 * Login user identity permissions
 * @author hy
 * @since 2021/10/18
 */
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    /** User id */
    private String userId;

    /** User token */
    private String token;

    /** Login time */
    private Long loginTime;

    /** Expire time */
    private Long expireTime;

    /** Login ip address  */
    private String loginIpAddress;

    /** Login location */
    private String loginLocation;

    /** Browser type */
    private String browserType;

    /** Operating system */
    private String operatingSystem;

    /** Permissions list */
    private Set<String> permissionsList;

    /** User info */
    private SysUser userInfo;

    /**
     * Default constructor
     */
    public LoginUser() {
    }

    /**
     * Constructor for passing user information and permission list
     * @param userInfo - login user info
     * @param permissionsList - login user permissions list
     */
    public LoginUser(SysUser userInfo, Set<String> permissionsList) {
        this.userInfo = userInfo;
        this.permissionsList = permissionsList;
    }

    /**
     * Constructor to pass the login user id,
     * user information and user permission list
     * @param userId - login user id
     * @param userInfo - login user info
     * @param permissionsList - login user permission list
     */
    public LoginUser(String userId, SysUser userInfo, Set<String> permissionsList) {
        this.userId = userId;
        this.userInfo = userInfo;
        this.permissionsList = permissionsList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SysUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(SysUser userInfo) {
        this.userInfo = userInfo;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getLoginIpAddress() {
        return loginIpAddress;
    }

    public void setLoginIpAddress(String loginIpAddress) {
        this.loginIpAddress = loginIpAddress;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Set<String> getPermissionsList() {
        return permissionsList;
    }

    public void setPermissionsList(Set<String> permissionsList) {
        this.permissionsList = permissionsList;
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUserName();
    }

    /**
     * Whether the account has not expired,
     * it cannot be verified after expiration
     * @return - true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicate whether the user's credentials (password) have expired,
     * expired credentials prevent authentication
     * @return - true
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicate whether the user's credentials (password) have expired,
     * expired credentials prevent authentication
     * @return - true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Is it available,
     * disabled users cannot be authenticated
     * @return - true
     */
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", loginTime=" + loginTime +
                ", expireTime=" + expireTime +
                ", loginIpAddress='" + loginIpAddress + '\'' +
                ", loginLocation='" + loginLocation + '\'' +
                ", browserType='" + browserType + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", permissionsList=" + permissionsList +
                ", userInfo=" + userInfo +
                '}';
    }
}
