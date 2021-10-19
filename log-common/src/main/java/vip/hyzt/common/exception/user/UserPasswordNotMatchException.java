package vip.hyzt.common.exception.user;

/**
 * The user password is incorrect or does not conform to the standard exception category
 *
 * @author hy
 * @since 2021/10/19
 */
public class UserPasswordNotMatchException extends UserException {

    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }

}
