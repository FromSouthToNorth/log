package vip.hyzt.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.constant.Constants;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.system.domain.MetaVo;
import vip.hyzt.system.domain.RouterVo;
import vip.hyzt.system.domain.SysPermissionMenu;
import vip.hyzt.system.domain.SysUser;
import vip.hyzt.system.mapper.SysPermissionMenuMapper;
import vip.hyzt.system.service.ISysPermissionMenuService;

import java.util.*;

/**
 * Permission menu business processing
 * @author hy
 * @since 2021/10/19
 */
@Service
public class SysPermissionMenuServiceImpl implements ISysPermissionMenuService {

    @Autowired
    private SysPermissionMenuMapper permissionMenuMapper;

    /**
     * Query permissions based on user ID
     * @param userId - user id
     */
    @Override
    public Set<String> selectPermissionMenuPermsByUserId(String userId) {
        List<String> perms = permissionMenuMapper.selectPermissionMenuByUserId(userId);
        HashSet<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (ObjectUtils.isEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询菜单
     * @param userId - 用户 id
     * @return 菜单列表
     */
    @Override
    public List<SysPermissionMenu> selectPermissionMenuTreeByUserId(String userId) {
        List<SysPermissionMenu> menuList;
        if ("1".equals(userId)) {
            menuList = permissionMenuMapper.selectPermissionMenuTreeAll();
        }
        else {
            menuList = permissionMenuMapper.selectPermissionMenuTreeByUserId(userId);
        }
        return getChildPerms(menuList, UserConstants.PARENT_ID);
    }

    /**
     * 查询系统菜单列表
     * @param permissionMenu - 菜单信息
     * @param userId - 用户id
     * @return 菜单列表
     */
    @Override
    public List<SysPermissionMenu> selectPermissionMenuList(SysPermissionMenu permissionMenu, String userId) {
        List<SysPermissionMenu> menuList;
        if (SysUser.isAdmin(userId)) {
            menuList = permissionMenuMapper.selectPermissionMenuList(permissionMenu);
        }
        else {
            permissionMenu.getParams().put("userId", userId);
            menuList = permissionMenuMapper.selectPermissionMenuList(permissionMenu);
        }
        return menuList;
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menuList 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<SysPermissionMenu> menuList) {
        List<RouterVo> routers = new LinkedList<>();
        for (SysPermissionMenu menu : menuList) {
            RouterVo router = new RouterVo();
            router.setHidden(1 == menu.getVisible());
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), 1 == menu.getIsCache(), menu.getPath()));
            List<SysPermissionMenu> children = menu.getChildren();
            if (!children.isEmpty() && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(children));
            }
            else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<>();
                RouterVo childrenMenu = new RouterVo();
                childrenMenu.setPath(menu.getPath());
                childrenMenu.setComponent(menu.getComponent());
                childrenMenu.setName(StringUtils.capitalize(menu.getPath()));
                childrenMenu.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), 1 == menu.getIsCache(), menu.getPath()));
                childrenList.add(childrenMenu);
                router.setChildren(childrenList);
            }
            else if (UserConstants.PARENT_ID.equals(menu.getParentId()) && isInnerLink(menu)) {
                router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                router.setPath("/inner");
                List<RouterVo> childrenList = new ArrayList<>();
                RouterVo childrens = new RouterVo();
                String routerPath = StringUtils.replaceEach(menu.getPath(), new String[]{Constants.HTTP, Constants.HTTPS}, new String[]{"", ""});
                childrens.setPath(routerPath);
                childrens.setComponent(UserConstants.INNER_LINK);
                childrens.setName(StringUtils.capitalize(routerPath));
                childrens.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(childrens);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由名称
     */
    private String getRouteName(SysPermissionMenu menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取设置路由地址
     */
    private String getRouterPath(SysPermissionMenu menu) {
        String path = menu.getPath();
        // 内链打开外网方式
        if (!UserConstants.PARENT_ID.equals(menu.getParentId()) && isInnerLink(menu)) {
            path = StringUtils.replaceEach(path, new String[] { Constants.HTTP, Constants.HTTPS }, new String[] {"", ""});
        }
        // 非外链并且是一级目录（类型为目录）
        if (UserConstants.PARENT_ID.equals(menu.getParentId())
                && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME == menu.getIsFrame()) {
            path = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            path = "/";
        }
        return path;
    }

    /**
     * 获取组件信息
     */
    private String getComponent(SysPermissionMenu menu) {
        String component = UserConstants.LAYOUT;
        if (!ObjectUtils.isEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        }
        else if (ObjectUtils.isEmpty(menu.getComponent()) && !UserConstants.PARENT_ID.equals(menu.getParentId()) && isInnerLink(menu)) {
            component = UserConstants.INNER_LINK;
        }
        else if (ObjectUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isMenuFrame(SysPermissionMenu menu) {
        return "0".equals(menu.getParentId()) && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && UserConstants.NO_FRAME == menu.getIsFrame();
    }

    /**
     * 是否为内链组件
     */
    private boolean isInnerLink(SysPermissionMenu menu) {
        return UserConstants.NO_FRAME == menu.getIsFrame() && StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS);
    }

    /**
     * 是否为parent_view组件
     */
    private boolean isParentView(SysPermissionMenu menu) {
        return !UserConstants.PARENT_ID.equals(menu.getParentId()) && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    /**
     * 根据父节点的ID获取所有子节点
     * @param list
     * @param parentId
     * @return
     */
    private List<SysPermissionMenu> getChildPerms(List<SysPermissionMenu> list, String parentId) {
        List<SysPermissionMenu> resultList = new ArrayList<>();
        for (Iterator<SysPermissionMenu> iterator = list.iterator(); iterator.hasNext();) {
            SysPermissionMenu menu = iterator.next();
            if (menu.getParentId().equals(parentId)) {
                recursionFn(list, menu);
                resultList.add(menu);
            }
        }
        return resultList;
    }

    private void recursionFn(List<SysPermissionMenu> list, SysPermissionMenu menu) {
        List<SysPermissionMenu> childList = getChildList(list, menu);
        menu.setChildren(childList);
        for (SysPermissionMenu permissionMenu : childList) {
            if (hasChild(list, permissionMenu)) {
                recursionFn(list, permissionMenu);
            }
        }
    }

    private List<SysPermissionMenu> getChildList(List<SysPermissionMenu> list, SysPermissionMenu menu) {
        List<SysPermissionMenu> resultList = new ArrayList<>();
        Iterator<SysPermissionMenu> iterator = list.iterator();
        while (iterator.hasNext()) {
            SysPermissionMenu next = iterator.next();
            if (next.getParentId().equals(menu.getMenuId())) {
                resultList.add(next);
            }
        }
        return resultList;
    }

    private boolean hasChild(List<SysPermissionMenu> list, SysPermissionMenu menu) {
        return getChildList(list, menu).size() > 0;
    }

}
