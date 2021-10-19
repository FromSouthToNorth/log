package vip.hyzt.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import vip.hyzt.common.utils.spring.SpringUtils;

/**
 * Obtain i18n resource files
 * @author hy
 * @since 2021/10/19
 */
public abstract class MessageUtils {
    /**
     * Obtain the message according to the message key and parameters and delegate to spring messageSource
     */
    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
