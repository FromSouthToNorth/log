package vip.hyzt.system.mapper;

import org.apache.ibatis.annotations.Param;
import vip.hyzt.system.domain.SysPermissionMenu;

import java.util.List;

/**
 * Permission menu data layer
 * @author hy
 * @since 2021/10/19
 */
public interface SysPermissionMenuMapper {

    /**
     * Query the permission menu table based on user id
     * @param userId - user id
     * @return user permission menu
     */
    List<String> selectPermissionMenuByUserId(@Param("userId") String userId);

    /**
     * 查询权限菜单
     * @param permissionMenu 菜单信息
     * @return 权限菜单
     */
    List<SysPermissionMenu> selectPermissionMenuList(SysPermissionMenu permissionMenu);

    /**
     * 查询所以权限菜单
     * @return 菜单列表
     */
    List<SysPermissionMenu> selectPermissionMenuTreeAll();

    /**
     * 更具用户 id 查询权限菜单
     * @param userId - 用户 id
     * @return 用户所拥有的权限菜单
     */
    List<SysPermissionMenu> selectPermissionMenuTreeByUserId(String userId);

}
