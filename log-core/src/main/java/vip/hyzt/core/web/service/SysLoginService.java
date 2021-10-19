package vip.hyzt.core.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.constant.Constants;
import vip.hyzt.common.exception.user.CaptchaException;
import vip.hyzt.common.exception.user.UserPasswordNotMatchException;
import vip.hyzt.common.utils.MessageUtils;
import vip.hyzt.core.manager.AsyncManager;
import vip.hyzt.core.manager.factory.AsyncFactory;
import vip.hyzt.core.redis.RedisCache;
import vip.hyzt.system.service.ISysUserService;

import javax.annotation.Resource;

/**
 * Login verification method
 * @author hy
 * @since 2021/10/18
 */
@Component
public class SysLoginService {

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    /**
     * Login authentication
     */
    public String login(String username, String password, String code, String uuid) {
        validateCaptcha(username, code, uuid);
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
                throw new CaptchaException();
            }
        }
        return null;
    }

    /**
     * Verification code
     */
    private void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (ObjectUtils.isEmpty(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
        }
    }

}
