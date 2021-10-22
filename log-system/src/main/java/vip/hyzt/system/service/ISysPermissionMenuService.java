package vip.hyzt.system.service;

import vip.hyzt.system.domain.RouterVo;
import vip.hyzt.system.domain.SysPermissionMenu;

import java.util.List;
import java.util.Set;

/**
 * Menu business layer
 * @author hy
 * @since 2021/10/18
 */
public interface ISysPermissionMenuService {

    /**
     * Query permissions based on user ID
     * @param userId - user id
     * @return user permissions
     */
    Set<String> selectPermissionMenuPermsByUserId(String userId);

    /**
     * 根据用户ID查询菜单
     * @param userId - 用户 id
     * @return 菜单列表
     */
    List<SysPermissionMenu> selectPermissionMenuTreeByUserId(String userId);

    /**
     * 根据用户查询系统菜单列表
     * @param permissionMenu - 菜单信息
     * @param userId - 用户 id
     * @return 菜单列表
     */
    List<SysPermissionMenu> selectPermissionMenuList(SysPermissionMenu permissionMenu, String userId);

    /**
     * 构建前端路由所需要的菜单
     * @param menuList - 菜单列表
     * @return - 路由列表
     */
    List<RouterVo> buildMenus(List<SysPermissionMenu> menuList);

}
