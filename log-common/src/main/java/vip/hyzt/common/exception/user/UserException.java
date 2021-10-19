package vip.hyzt.common.exception.user;

import vip.hyzt.common.exception.base.BaseException;

/**
 * User information exception class
 *
 * @author hy
 * @since 2021/10/19
 */
public class UserException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }

}
