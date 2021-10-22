package vip.hyzt.common.utils.string;

/**
 * String processing tool
 * @author hy
 * @since 2021/10/18
 */
public abstract class StringUtils {

    /** null string */
    private static final String NULL_STR = "";

    /**
     * Checks if the string is empty
     * @return null || "" = true, !null || !"" = false
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || NULL_STR.equals(str.trim());
    }

    /**
     * Checks if the string is null
     * @return null = true, !null = false
     */
    public static boolean isNull(String str) {
        return str == null;
    }

}
