package vip.hyzt.common.enums;

/**
 * user status
 *
 * @author hy
 * @since 2021/10/19
 */
public enum UserStatus {

    OK(0, "正常"),
    DISABLE(1, "停用"),
    DELETED(1, "删除");

    private final int code;
    private final String info;

    UserStatus(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

}
