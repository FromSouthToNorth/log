package vip.hyzt.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.hyzt.common.constant.Constants;
import vip.hyzt.core.domain.LoginBody;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.security.SecurityService;
import vip.hyzt.core.web.service.SysLoginService;
import vip.hyzt.core.web.service.SysPermissionService;
import vip.hyzt.system.domain.SysPermissionMenu;
import vip.hyzt.system.domain.SysUser;
import vip.hyzt.system.service.ISysPermissionMenuService;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * @author hy
 * @since 2021/10/20
 */
@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysPermissionMenuService permissionMenuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     * @param loginBody 登录信息
     * @return 登录是否成功
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginBody loginBody) {
        Result result = Result.success();
        String token = loginService
                .login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        result.put(Constants.TOKEN, token);
        return result;
    }

    /**
     * 获取用户信息
     * @return 登录的用户信息及用户角色权限。
     */
    @GetMapping("getInfo")
    public Result getUserInfo() {
        SysUser userInfo = SecurityService.getLoginUser().getUserInfo();
        Set<String> roles = permissionService.getRolePermission(userInfo);
        Set<String> permissions = permissionService.getPermissionMenu(userInfo);
        Result result = Result.success();
        result.put("user", userInfo);
        result.put("roles", roles);
        result.put("permissions", permissions);
        return result;
    }

    /**
     * 获取路由
     * @return 登录用户的权限路由菜单
     */
    @GetMapping("getRouters")
    public Result getRouters() {
        String userId = SecurityService.getUserId();
        List<SysPermissionMenu> menuList = permissionMenuService.selectPermissionMenuTreeByUserId(userId);
        return Result.success(permissionMenuService.buildMenus(menuList));
    }

}
