package vip.hyzt.api.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.hyzt.common.annotation.Log;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.enums.BusinessType;
import vip.hyzt.common.utils.StringUtils;
import vip.hyzt.core.domain.LoginUser;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.security.SecurityService;
import vip.hyzt.core.web.controller.BaseController;
import vip.hyzt.core.web.service.TokenService;
import vip.hyzt.system.domain.SysUser;
import vip.hyzt.system.service.ISysUserService;

/**
 * 个人信息 业务处理
 * @author hy
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 个人信息
     */
    @GetMapping
    public Result profile() {
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUserInfo();
        Result ajax = Result.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result updateProfile(@RequestBody SysUser user) {
        if (StringUtils.isNotEmpty(user.getPhone())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return Result.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return Result.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        LoginUser loginUser = getLoginUser();
        SysUser sysUser = loginUser.getUserInfo();
        user.setUserId(sysUser.getUserId());
        user.setPassword(null);
        if (userService.updateUserProfile(user) > 0) {
            // 更新缓存用户信息
            sysUser.setRealName(user.getRealName());
            sysUser.setPhone(user.getPhone());
            sysUser.setEmail(user.getEmail());
            sysUser.setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            return Result.success();
        }
        return Result.error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public Result updatePwd(String oldPassword, String newPassword) {
        LoginUser loginUser = getLoginUser();
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityService.matchesPassword(oldPassword, password)) {
            return Result.error("修改密码失败，旧密码错误");
        }
        if (SecurityService.matchesPassword(newPassword, password)) {
            return Result.error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(userName, SecurityService.encryptPassword(newPassword)) > 0) {
            // 更新缓存用户密码
            loginUser.getUserInfo().setPassword(SecurityService.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return Result.success();
        }
        return Result.error("修改密码异常，请联系管理员");
    }
}
