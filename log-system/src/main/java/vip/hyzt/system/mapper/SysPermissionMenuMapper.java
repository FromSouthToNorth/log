package vip.hyzt.system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
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
     * 根据用户查询系统菜单列表
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysPermissionMenu> selectPermissionMenuListByUserId(SysPermissionMenu menu);

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

    /**
     * 根据角色ID查询菜单树信息
     * @param roleId 角色ID
     * @param menuCheckStrictly 菜单树选择项是否关联显示
     * @return 选中菜单列表
     */
    List<String> selectMenuListByRoleId(@Param("roleId") String roleId, @Param("menuCheckStrictly") boolean menuCheckStrictly);

    /**
     * 根据菜单ID查询信息
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public SysPermissionMenu selectMenuById(String menuId);

    /**
     * 是否存在菜单子节点
     * @param menuId 菜单ID
     * @return 结果
     */
    public int hasChildByMenuId(String menuId);

    /**
     * 新增菜单信息
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(SysPermissionMenu menu);

    /**
     * 修改菜单信息
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(SysPermissionMenu menu);

    /**
     * 删除菜单管理信息
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(String menuId);


    /**
     * 校验菜单名称是否唯一
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    public SysPermissionMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") String parentId);

}
