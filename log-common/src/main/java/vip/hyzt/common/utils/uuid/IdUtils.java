package vip.hyzt.common.utils.uuid;

/**
 * ID generator tools
 * @author hy
 * @since 2021/10/18
 */
public abstract class IdUtils {

    /**
     * 获取随机UUID
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     * @return 随机UUID
     */
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID() {
        return UUID.fastUUID().toString(true);
    }

}
