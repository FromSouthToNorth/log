package vip.hyzt.common.utils.file;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import vip.hyzt.common.utils.DateUtils;
import vip.hyzt.common.utils.string.StringUtils;
import vip.hyzt.common.utils.uuid.IdUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * 文件md5工具类
 * @author hy
 */
public abstract class FileUtils {

    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'};

    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F'};

    /**
     * 获取文件md5值
     * @param inputStream 文件输入流
     * @return {@link String} 文件md5值
     */
    public static String getMd5(InputStream inputStream) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                md5.update(buffer, 0, length);
            }
            return new String(encodeHex(md5.digest()));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 编码文件名
     */
    public static String extractFilename(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        fileName = DateUtils.datePath() + "/" + IdUtils.fastUUID() + "." + extension;
        return fileName;
    }

    /**
     * 获取文件名的后缀
     * @param file 表单文件
     * @return 后缀名
     */
    public static String getExtension(MultipartFile file) {
        System.out.println(file.getContentType());
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }

    /**
     * 判断字符串中是否全是空白字符
     * @param cs 需要判断的字符串
     * @return 如果字符串序列是 null 或者全是空白，返回 true
     */
    public static boolean isBlank(CharSequence cs) {
        if (cs != null) {
            int length = cs.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static char[] encodeHex(final byte[] data) {
        return encodeHex(data, true);
    }

    public static char[] encodeHex(final byte[] data, final boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int length = data.length;
        char[] out = new char[length << 1];
        encodeHex(data, 0, data.length, toDigits, out, 0);
        return out;
    }

    private static void encodeHex(byte[] data, int dataOffset, int dataLen, char[] toDigits, char[] out, int outOffset) {
        int i = dataOffset;

        for (int var7 = outOffset; i < dataOffset + dataLen; ++i) {
            out[var7++] = toDigits[(240 & data[i]) >>> 4];
            out[var7++] = toDigits[15 & data[i]];
        }
    }
}
