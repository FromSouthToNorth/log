package vip.hyzt.system.service;

import vip.hyzt.system.domain.SysUser;

import java.util.List;

/**
 * User business layer
 * @author hy
 * @since 2021/10/18
 */
public interface ISysUserService {

    /**
     * Query users by username
     * @param userName user name
     */
    SysUser selectUserByUserName(String userName);

    /**
     * 校验用户名称是否唯一
     * @param userName - 用户名称
     * @return 结果
     */
    String checkUserNameUnique(String userName);

    /**
     * 根据条件分页查询用户列表
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUserList(SysUser user);


    /**
     * 根据条件分页查询已分配用户角色列表
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 通过用户ID查询用户
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysUser selectUserById(String userId);

    /**
     * 根据用户ID查询用户所属角色组
     * @param userName 用户名
     * @return 结果
     */
    String selectUserRoleGroup(String userName);

    /**
     * 校验手机号码是否唯一
     * @param user 用户信息
     * @return 结果
     */
    String checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     * @param user 用户信息
     * @return 结果
     */
    String checkEmailUnique(SysUser user);

    /**
     * 校验用户是否允许操作
     * @param user 用户信息
     */
    void checkUserAllowed(SysUser user);


    /**
     * 新增用户信息
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 注册用户信息
     * @param user 用户信息
     * @return 结果
     */
    boolean registerUser(SysUser user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 用户授权角色
     * @param userId 用户ID
     * @param roleIds 角色组
     */
    void insertUserAuth(String userId, String[] roleIds);

    /**
     * 修改用户状态
     * @param user 用户信息
     * @return 结果
     */
    int updateUserStatus(SysUser user);

    /**
     * 修改用户基本信息
     * @param user 用户信息
     * @return 结果
     */
    int updateUserProfile(SysUser user);

    /**
     * 修改用户头像
     * @param userName 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    boolean updateUserAvatar(String userName, String avatar);

    /**
     * 重置用户密码
     * @param user 用户信息
     * @return 结果
     */
    int resetPwd(SysUser user);

    /**
     * 重置用户密码
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    int resetUserPwd(String userName, String password);

    /**
     * 通过用户ID删除用户
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(String userId);

    /**
     * 批量删除用户信息
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    int deleteUserByIds(String[] userIds);
}
