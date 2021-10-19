package vip.hyzt.system.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Permission menu data layer
 * @author hy
 * @since 2021/10/19
 */
public interface SysPermissionMenuMapper {

    /**
     * Query the permission menu table based on user id
     * @param userId - user id
     * @return user permission menu
     */
    List<String> selectPermissionMenuByUserId(@Param("userId") String userId);

}
