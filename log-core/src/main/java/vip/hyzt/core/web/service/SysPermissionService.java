package vip.hyzt.core.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vip.hyzt.system.domain.SysUser;
import vip.hyzt.system.service.ISysPermissionMenuService;
import vip.hyzt.system.service.ISysRoleService;

import java.util.HashSet;
import java.util.Set;

/**
 * User authority handling
 * @author hy
 * @since 2021/10/18
 */
@Component
public class SysPermissionService {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPermissionMenuService permissionMenuService;

    /**
     * Get role data permissions
     * @param user - user info
     * @return user role permissions info
     */
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<>();
        if (user.isAdmin()) {
            roles.add("admin");
        }
        else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }

    /**
     * Get menu data permissions
     * @param user - user info
     * @return user permissions info
     */
    public Set<String> getPermissionMenu(SysUser user) {
        Set<String> perms = new HashSet<>();
        if (user.isAdmin()) {
            perms.add("*:*:*");
        }
        else {
            perms.addAll(permissionMenuService.selectPermissionMenuByUserId(user.getUserId()));
        }
        return perms;
    }

}
