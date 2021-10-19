package vip.hyzt.common.exception.user;

/**
 * Verification code error exception class
 *
 * @author hy
 * @since 2021/10/19
 */
public class CaptchaException extends UserException {

    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }

}
