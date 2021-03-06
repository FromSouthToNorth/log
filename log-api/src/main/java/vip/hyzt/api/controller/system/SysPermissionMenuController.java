package vip.hyzt.api.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.hyzt.common.annotation.Log;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.enums.BusinessType;
import vip.hyzt.common.utils.StringUtils;
import vip.hyzt.common.utils.uuid.IdUtils;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.web.controller.BaseController;
import vip.hyzt.system.domain.SysPermissionMenu;
import vip.hyzt.system.service.ISysPermissionMenuService;

import java.util.List;

/**
 * 菜单信息
 * @author hy
 * @since 2021/11/03
 */
@RestController
@RequestMapping("/system/menu")
public class SysPermissionMenuController extends BaseController {

    @Autowired
    private ISysPermissionMenuService menuService;

    /**
     * 获取菜单列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    @GetMapping("/list")
    public Result list(SysPermissionMenu menu) {
        List<SysPermissionMenu> menus = menuService.selectPermissionMenuList(menu, getUserId());
        return Result.success(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:menu:query')")
    @GetMapping(value = "/{menuId}")
    public Result getInfo(@PathVariable String menuId) {
        return Result.success(menuService.selectMenuById(menuId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public Result treeselect(SysPermissionMenu menu) {
        List<SysPermissionMenu> menus = menuService.selectPermissionMenuList(menu, getUserId());
        return Result.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public Result roleMenuTreeselect(@PathVariable("roleId") String roleId) {
        List<SysPermissionMenu> menus = menuService.selectPermissionMenuList(getUserId());
        Result ajax = Result.success();
        ajax.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        ajax.put("menus", menuService.buildMenuTreeSelect(menus));
        return ajax;
    }

    /**
     * 新增菜单
     */
    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@Validated @RequestBody SysPermissionMenu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return Result.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } 
        else if (UserConstants.YES_FRAME == menu.getIsFrame() && !StringUtils.ishttp(menu.getPath())) {
            return Result.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        menu.setMenuId(IdUtils.simpleUUID());
        menu.setCreateBy(getUsername());
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @PreAuthorize("@ss.hasPermi('system:menu:edit')")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody SysPermissionMenu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return Result.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } 
        else if (UserConstants.YES_FRAME == menu.getIsFrame() && !StringUtils.ishttp(menu.getPath())) {
            return Result.error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        else if (menu.getMenuId().equals(menu.getParentId())) {
            return Result.error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(getUsername());
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public Result remove(@PathVariable("menuId") String menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return Result.error("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId)) {
            return Result.error("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }

}
