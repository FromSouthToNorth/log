package vip.hyzt.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.annotation.DataScope;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.exception.ServiceException;
import vip.hyzt.common.utils.uuid.IdUtils;
import vip.hyzt.system.domain.SysRole;
import vip.hyzt.system.domain.SysUser;
import vip.hyzt.system.domain.SysUserRole;
import vip.hyzt.system.mapper.SysRoleMapper;
import vip.hyzt.system.mapper.SysUserMapper;
import vip.hyzt.system.mapper.SysUserRoleMapper;
import vip.hyzt.system.service.ISysConfigService;
import vip.hyzt.system.service.ISysUserService;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * User business layer processing
 * @author hy
 * @since 2021/10/19
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private ISysConfigService configService;

    /**
     * Return query user information based on user name
     * @param userName user name
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * 校验用户名称是否唯一
     * @param userName - 用户名称
     * @return 结果 "0" 表示唯一，"1" 表示不唯一
     */
    @Override
    public String checkUserNameUnique(String userName) {
        int count = userMapper.checkUserNameUnique(userName);
        if (count > 0) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 根据条件分页查询用户列表
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(userAlias = "u")
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }

    /**
     * 根据条件分页查询已分配用户角色列表
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(userAlias = "u")
    public List<SysUser> selectAllocatedList(SysUser user) {
        return userMapper.selectAllocatedList(user);
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(userAlias = "u")
    public List<SysUser> selectUnallocatedList(SysUser user) {
        return userMapper.selectUnallocatedList(user);
    }

    /**
     * 通过用户ID查询用户
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(String userId) {
        return userMapper.selectUserById(userId);
    }

    /**
     * 查询用户所属角色组
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userName) {
        List<SysRole> sysRoles = roleMapper.selectRolesByUserName(userName);
        StringBuilder roleNameStr = new StringBuilder();
        for (SysRole role : sysRoles) {
            roleNameStr.append(role.getRoleName()).append(",");
        }
        if (ObjectUtils.isEmpty(roleNameStr.toString())) {
            return roleNameStr.substring(0, roleNameStr.length() - 1);
        }
        return roleNameStr.toString();
    }

    /**
     * 校验用户名称是否唯一
     * @param user 用户信息
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        String userId = ObjectUtils.isEmpty(user.getUserId()) ? "-1" : user.getUserId();
        SysUser info = userMapper.checkPhoneUnique(userId);
        if (!ObjectUtils.isEmpty(info) && info.getUserId().equals(userId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     * @param user 用户信息
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        String userId = ObjectUtils.isEmpty(user.getUserId()) ? "-1" : user.getUserId();
        SysUser info = userMapper.checkEmailUnique(userId);
        if (!ObjectUtils.isEmpty(info) && info.getUserId().equals(userId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验用户是否允许操作
     * @param user 用户信息
     */
    @Override
    public void checkUserAllowed(SysUser user) {
        if (!ObjectUtils.isEmpty(user.getUserId()) && user.isAdmin())
        {
            throw new ServiceException("不允许操作超级管理员用户");
        }
    }


    /**
     * 新增保存用户信息
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(SysUser user) {
        userMapper.insertUser(user);

        return 0;
    }

    /**
     * 新增用户角色信息
     * @param user 用户对象
     */
    private void insertUserRole(SysUser user) {
        String[] roleIds = user.getRoleIds();
        if (!ObjectUtils.isEmpty(roleIds)) {
            List<SysUserRole> userRoles = new ArrayList<>();
            for (String roleId : roleIds) {
                SysUserRole userRole = new SysUserRole();
                userRole.setId(IdUtils.simpleUUID());
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(roleId);
                userRoles.add(userRole);
            }
            if (!ObjectUtils.isEmpty(userRoles)) {
                userRoleMapper.batchUserRole(userRoles);
            }
        }
    }

    /**
     * 注册用户信息
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean registerUser(SysUser user) {
        return userMapper.insertUser(user) > 0;
    }

    /**
     * 修改保存用户信息
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(SysUser user) {
        String userId = user.getUserId();
        userRoleMapper.deleteUserRoleByUserId(userId);
        insertUserRole(user);
        return userMapper.updateUser(user);
    }

    /**
     * 用户授权角色
     * @param userId 用户ID
     * @param roleIds 角色组
     */
    @Override
    public void insertUserAuth(String userId, String[] roleIds) {
        userRoleMapper.deleteUserRoleByUserId(userId);
        insertUserRole(userId, roleIds);
    }

    /**
     * 新增用户角色信息
     * @param userId 用户ID
     * @param roleIds 角色组
     */
    private void insertUserRole(String userId, String[] roleIds) {
        if (!ObjectUtils.isEmpty(roleIds)) {
            List<SysUserRole> userRoles = new ArrayList<>();
            for (String roleId : roleIds) {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRoles.add(userRole);
            }
            if (!ObjectUtils.isEmpty(userRoles)) {
                userRoleMapper.batchUserRole(userRoles);
            }
        }
    }

    @Override
    public int updateUserStatus(SysUser user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserProfile(SysUser user) {
        return userMapper.updateUser(user);
    }

    @Override
    public boolean updateUserAvatar(String userName, String avatar) {
        return userMapper.updateUserAvatar(userName, avatar) > 0;
    }

    @Override
    public int resetPwd(SysUser user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int resetUserPwd(String userName, String password) {
        return userMapper.resetUserPwd(userName, password);
    }

    @Override
    @Transactional
    public int deleteUserById(String userId) {
        userRoleMapper.deleteUserRoleByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    @Override
    @Transactional
    public int deleteUserByIds(String[] userIds) {
        for (String userId : userIds) {
            checkUserAllowed(new SysUser(userId));
        }
        userRoleMapper.deleteUserRole(userIds);
        return userMapper.deleteUserByIds(userIds);
    }

}
