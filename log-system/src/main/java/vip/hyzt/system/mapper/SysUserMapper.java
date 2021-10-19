package vip.hyzt.system.mapper;

import org.apache.ibatis.annotations.Param;
import vip.hyzt.system.domain.SysUser;

/**
 * User operation data layer
 * @author hy
 * @since 2021/10/18
 */
public interface SysUserMapper {

    /**
     * Query users by username
     * @param userName user name
     */
    SysUser selectUserByUserName(@Param("userName") String userName);

}
