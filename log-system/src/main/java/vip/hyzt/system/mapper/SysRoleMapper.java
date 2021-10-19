package vip.hyzt.system.mapper;

import org.apache.ibatis.annotations.Param;
import vip.hyzt.system.domain.SysRole;

import java.util.List;

/**
 * Role table Data layer
 * @author hy
 * @since 2021/101/19
 */
public interface SysRoleMapper {

    /**
     * Query role based on user ID
     * @param userId user id
     * @return user role list
     */
    List<SysRole> selectRolePermissionByUserId(@Param("userId") String userId);

}
