package vip.hyzt.common.utils;

/**
 * Process and record log files
 *
 * @author hy
 * @since 2021/10/18
 */
public class LogUtils {

    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }

}
