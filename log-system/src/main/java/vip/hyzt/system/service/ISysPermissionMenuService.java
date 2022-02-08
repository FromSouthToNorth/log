package vip.hyzt.system.service;

import vip.hyzt.system.domain.RouterVo;
import vip.hyzt.system.domain.SysPermissionMenu;
import vip.hyzt.system.domain.TreeSelect;

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
     * @param userId - 用户 id
     * @return 菜单列表
     */
    List<SysPermissionMenu> selectPermissionMenuList(String userId);

    /**
     * 根据用户查询系统菜单列表
     * @param permissionMenu - 菜单信息
     * @param userId - 用户 id
     * @return 菜单列表
     */
    List<SysPermissionMenu> selectPermissionMenuList(SysPermissionMenu permissionMenu, String userId);

    /**
     * 根据角色ID查询菜单树信息
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    List<String> selectMenuListByRoleId(String roleId);

    /**
     * 构建前端路由所需要的菜单
     * @param menuList - 菜单列表
     * @return - 路由列表
     */
    List<RouterVo> buildMenus(List<SysPermissionMenu> menuList);

    /**
     * 构建前端所需要树结构
     * @param menus 菜单列表
     * @return 树结构列表
     */
    List<SysPermissionMenu> buildMenuTree(List<SysPermissionMenu> menus);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildMenuTreeSelect(List<SysPermissionMenu> menus);


    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public SysPermissionMenu selectMenuById(String menuId);

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean hasChildByMenuId(String menuId);

    /**
     * 查询菜单是否存在角色
     *
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkMenuExistRole(String menuId);

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(SysPermissionMenu menu);

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(SysPermissionMenu menu);

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(String menuId);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public String checkMenuNameUnique(SysPermissionMenu menu);

}
