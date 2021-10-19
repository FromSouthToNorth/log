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

}
