package vip.hyzt.system.service;

import vip.hyzt.system.domain.SysUser;

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
     * 注册用户信息
     * @param user - 用户信息
     * @return 注册结果
     */
    boolean registerUser(SysUser user);

}
