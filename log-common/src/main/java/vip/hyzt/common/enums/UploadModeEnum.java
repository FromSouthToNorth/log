package vip.hyzt.common.enums;

/**
 * 上传模式枚举
 * @author hy
 */
public enum UploadModeEnum {

    /**
     * oss
     */
    OSS("oss", "ossUploadServiceImpl"),
    /**
     * 本地
     */
    LOCAL("local", "localUploadServiceImpl");

    /**
     * 模式
     */
    private final String mode;

    /**
     * 策略
     */
    private final String strategy;

    UploadModeEnum(String mode, String strategy) {
        this.mode = mode;
        this.strategy = strategy;
    }

    public String getMode() {
        return mode;
    }

    public String getStrategy() {
        return strategy;
    }

    /**
     * 获取策略
     * @param mode 模式
     * @return {@link String} 搜索策略
     */
    public static String getStrategy(String mode) {
        for (UploadModeEnum value : UploadModeEnum.values()) {
            if (value.getMode().equals(mode)) {
                return value.getStrategy();
            }
        }
        return null;
    }
}
