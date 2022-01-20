package vip.hyzt.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * System access record table sys_logininfo
 * @author hy
 * @since 2021/10/18
 */
public class SysLoginInfo extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** ID */
    private String infoId;

    /** 用户账号 */
    private String userName;

    /** 登录状态 0成功 1失败 */
    private String status;

    /** 登录IP地址 */
    private String ipaddr;

    /** 登录地点 */
    private String loginLocation;

    /** 浏览器类型 */
    private String browser;

    /** 操作系统 */
    private String os;

    /** 提示消息 */
    private String msg;

    /** 访问时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "SysLoginInfo{" +
                "infoId='" + infoId + '\'' +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                ", ipaddr='" + ipaddr + '\'' +
                ", loginLocation='" + loginLocation + '\'' +
                ", browser='" + browser + '\'' +
                ", os='" + os + '\'' +
                ", msg='" + msg + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }
}
