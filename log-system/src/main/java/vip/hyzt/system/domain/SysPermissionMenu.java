package vip.hyzt.system.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Permission menu table
 * @author hy
 * @since 2021/10/16
 */
public class SysPermissionMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String menuId;

    private String menuName;

    private String parentId;

    private int orderNum;

    private String path;

    private String component;

    /** Whether it is an external link (0 is 1 no) */
    private int isFrame;

    /** Whether to cache (0 cache 1 no cache) */
    private int isCache;

    /** Menu type (M directory C menu F button) */
    private String menuType;

    /** Menu status (0 display 1 hide) */
    private int visible;

    /** Menu status (0 normal and 1 disabled) */
    private int status;

    private String perms;

    private String icon;

    /** Submenu */
    private List<SysPermissionMenu> children = new ArrayList<>();

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(int isFrame) {
        this.isFrame = isFrame;
    }

    public int getIsCache() {
        return isCache;
    }

    public void setIsCache(int isCache) {
        this.isCache = isCache;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SysPermissionMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysPermissionMenu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SysPermissionMenu{" +
                "menuId='" + menuId + '\'' +
                ", menuName='" + menuName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", orderNum=" + orderNum +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", isFrame=" + isFrame +
                ", isCache=" + isCache +
                ", menuType='" + menuType + '\'' +
                ", visible=" + visible +
                ", status=" + status +
                ", perms='" + perms + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
    }
}
