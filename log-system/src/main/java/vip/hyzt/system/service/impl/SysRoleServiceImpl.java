package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.exception.ServiceException;
import vip.hyzt.common.utils.StringUtils;
import vip.hyzt.common.utils.spring.SpringUtils;
import vip.hyzt.common.utils.uuid.IdUtils;
import vip.hyzt.system.domain.SysRole;
import vip.hyzt.system.domain.SysRolePermission;
import vip.hyzt.system.domain.SysUser;
import vip.hyzt.system.domain.SysUserRole;
import vip.hyzt.system.mapper.SysRoleMapper;
import vip.hyzt.system.mapper.SysRolePermissionMapper;
import vip.hyzt.system.mapper.SysUserRoleMapper;
import vip.hyzt.system.service.ISysRoleService;

import java.util.*;

/**
 * Role business processing
 * @author hy
 * @since 2021/10/19
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRolePermissionMapper rolePermissionMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    /**
     * Query role permissions based on user ID
     * @param userId - user id
     */
    @Override
    public Set<String> selectRolePermissionByUserId(String userId) {
        List<SysRole> perms = roleMapper.selectRolePermissionByUserId(userId);
        HashSet<String> permSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (!ObjectUtils.isEmpty(perm)) {
                permSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permSet;
    }

    /**
     * 根据条件分页查询角色数据
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 根据用户ID查询角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<SysRole> selectRolesByUserId(String userId) {
        List<SysRole> roles = roleMapper.selectRolePermissionByUserId(userId);
        List<SysRole> roleAll = selectRoleAll();
        for (SysRole role : roleAll) {
            for (SysRole sysRole : roles) {
                if (role.getRoleId().equals(sysRole.getRoleId())) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roleAll;
    }

    /**
     * 查询所有角色
     * @return 角色列表
     */
    @Override
    public List<SysRole> selectRoleAll() {
        return roleMapper.selectRoleAll();
    }

    /**
     * 根据用户ID获取角色选择框列表
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    @Override
    public List<String> selectRoleListByUserId(String userId) {
        return roleMapper.selectRoleListByUserId(userId);
    }

    /**
     * 通过角色ID查询角色
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public SysRole selectRoleById(String roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 校验角色名称是否唯一
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(SysRole role) {
        String roleId = ObjectUtils.isEmpty(role) ? "-1" : role.getRoleId();
        SysRole info = roleMapper.checkRoleNameUnique(role.getRoleName());
        System.out.println("校验角色名称是否唯一 --> " + info);
        if (!ObjectUtils.isEmpty(info) && !info.getRoleId().equals(roleId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(SysRole role) {
        String roleId = ObjectUtils.isEmpty(role) ? "-1" : role.getRoleId();
        SysRole info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (!ObjectUtils.isEmpty(info) && !info.getRoleId().equals(roleId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验角色是否允许操作
     *
     * @param role 角色信息
     */
    @Override
    public void checkRoleAllowed(SysRole role) {
        if (!ObjectUtils.isEmpty(role.getRoleId()) && role.isAdmin()) {
            throw new ServiceException("不允许操作超级管理员角色");
        }
    }

    /**
     * 校验角色是否有数据权限
     * @param roleId 角色id
     * @param userId 用户id
     */
    @Override
    public void checkRoleDataScope(String roleId, String userId) {
        if (!SysUser.isAdmin(userId)) {
            SysRole role = new SysRole();
            role.setRoleId(roleId);
            List<SysRole> roles = SpringUtils.getAopProxy(this).selectRoleList(role);
            if (StringUtils.isEmpty(roles)) {
                throw new ServiceException("没有权限访问角色数据！");
            }
        }
    }

    /**
     * 通过角色ID查询角色使用数量
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(String roleId) {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }

    /**
     * 新增保存角色信息
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertRole(SysRole role) {
        roleMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    private int insertRoleMenu(SysRole role) {
        int rows = 1;
        List<SysRolePermission> list = new ArrayList<>();
        for (String menuId : role.getMenuIds()) {
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setId(IdUtils.fastSimpleUUID());
            rolePermission.setRoleId(role.getRoleId());
            rolePermission.setPermissionId(menuId);
            list.add(rolePermission);
        }
        if (!ObjectUtils.isEmpty(list)) {
            rows = rolePermissionMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * 修改保存角色信息
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateRole(SysRole role) {
        roleMapper.updateRole(role);
        rolePermissionMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    /**
     * 修改角色状态
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRoleStatus(SysRole role) {
        return roleMapper.updateRole(role);
    }

    /**
     * 修改数据权限信息
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int authDataScope(SysRole role) {
        return roleMapper.updateRole(role);
    }

    /**
     * 通过角色ID删除角色
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteRoleById(String roleId) {
        rolePermissionMapper.deleteRoleMenuByRoleId(roleId);
        return roleMapper.deleteRoleById(roleId);
    }

    /**
     * 批量删除角色信息
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
    @Override
    public int deleteRoleByIds(String[] roleIds) {
        for (String roleId : roleIds) {
            checkRoleAllowed(new SysRole(roleId));
            SysRole sysRole = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", sysRole.getRoleName()));
            }
        }
        rolePermissionMapper.deleteRoleMenu(roleIds);
        return roleMapper.deleteRoleByIds(roleIds);
    }

    /**
     * 取消授权用户角色
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    @Override
    public int deleteAuthUser(SysUserRole userRole) {
        return userRoleMapper.deleteUserRoleInfo(userRole);
    }

    /**
     * 批量取消授权用户角色
     * @param roleId 角色ID
     * @param userIds 需要取消授权的用户数据ID
     * @return 结果
     */
    @Override
    public int deleteAuthUsers(String roleId, String[] userIds) {
        return userRoleMapper.deleteUserRoleInfos(roleId, userIds);
    }

    /**
     * 批量选择授权用户角色
     * @param roleId 角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    @Override
    public int insertAuthUsers(String roleId, String[] userIds) {
        // 新增用户与角色管理
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        for (String userId : userIds)
        {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        return userRoleMapper.batchUserRole(list);
    }

}
