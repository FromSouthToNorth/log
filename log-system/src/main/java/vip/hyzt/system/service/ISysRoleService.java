package vip.hyzt.system.service;

import java.util.Set;

/**
 * Role business layer
 * @author hy
 * @since 2021/10/19
 */
public interface ISysRoleService {

    /**
     * Query role permissions based on user ID
     * @param userId - user id
     * @return user role
     */
    Set<String> selectRolePermissionByUserId(String userId);

}
