package vip.hyzt.common.exception.user;

/**
 * 验证码失效异常类
 *
 * @author hy
 * @since 2021/10/20
 */
public class CaptchaExpireException extends UserException {

    private static final long serialVersionUID = 1L;

    public CaptchaExpireException() {
        super("user.jcaptcha.expire", null);
    }

}
