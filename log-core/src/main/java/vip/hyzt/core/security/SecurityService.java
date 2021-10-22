package vip.hyzt.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vip.hyzt.common.constant.HttpStatus;
import vip.hyzt.common.exception.ServiceException;
import vip.hyzt.core.domain.LoginUser;

/**
 * Security service tools
 * @author hy
 * @since 2021/10/18
 */
public abstract class SecurityService {

    /**
     * Get user id
     */
    public static String getUserId() {
        try {
            return getLoginUser().getUserId();
        }
        catch (Exception e) {
            throw new ServiceException("Obtaining user ID exception!", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Get login user account
     */
    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            throw new ServiceException("Obtaining user information is abnormal!", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Get Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * Generate BCryptPasswordEncoder password
     * @param password - password
     * @return Encrypted password
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    /**
     * Determine whether the passwords are the same
     * @param rawPassword Real password
     * @param encodedPassword Characters after encryption
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * Is an administrator
     */
    public static boolean isAdmin(String userId) {
        return "1".equals(userId);
    }

}
