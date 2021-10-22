package vip.hyzt.core.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.constant.Constants;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.exception.user.CaptchaException;
import vip.hyzt.common.exception.user.CaptchaExpireException;
import vip.hyzt.common.utils.MessageUtils;
import vip.hyzt.common.utils.uuid.IdUtils;
import vip.hyzt.core.domain.RegisterBody;
import vip.hyzt.core.manager.AsyncManager;
import vip.hyzt.core.manager.factory.AsyncFactory;
import vip.hyzt.common.utils.redis.RedisCache;
import vip.hyzt.core.security.SecurityService;
import vip.hyzt.system.domain.SysUser;
import vip.hyzt.system.service.ISysUserService;

/**
 * Registration verification method
 * @author hy
 * @since 2021/10/18
 */
@Component
public class SysRegisterService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody) {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        validateCaptcha(registerBody.getCode(), registerBody.getUuid());
        if (ObjectUtils.isEmpty(username)) {
            msg = "用户名不能为空";
        }
        else if (ObjectUtils.isEmpty(password)) {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在2到16个字符之间";
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(username))) {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else {
            SysUser sysUser = new SysUser();
            sysUser.setUserId(IdUtils.fastUUID());
            sysUser.setStatus(UserConstants.NORMAL);
            sysUser.setDelFlag(UserConstants.NORMAL);
            sysUser.setUserName(username);
            sysUser.setRealName(username);
            sysUser.setPassword(SecurityService.encryptPassword(registerBody.getPassword()));
            boolean regFlag = userService.registerUser(sysUser);
            if (regFlag) {
                AsyncManager.me().
                        execute(AsyncFactory.recordLoginInfo(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
            else {
                msg = "注册失败,请联系系统管理人员";
            }
        }
        return msg;
    }

    public void validateCaptcha(String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (ObjectUtils.isEmpty(captcha)) {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException();
        }
    }

}
