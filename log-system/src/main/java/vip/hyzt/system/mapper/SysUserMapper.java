package vip.hyzt.system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
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

    /**
     * 校验用户名称是否唯一
     * @param userName - 用户名称
     * @return 结果
     */
    int checkUserNameUnique(@Param("userName") String userName);

    /**
     * 新增用户
     * @param user - 用户信息
     * @return - 新增结果:受影响行数
     */
    int insertUser(SysUser user);

}
