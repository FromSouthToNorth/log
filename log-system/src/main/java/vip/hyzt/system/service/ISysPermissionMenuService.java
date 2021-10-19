package vip.hyzt.system.service;

import java.util.Set;

/**
 * Menu business layer
 * @author hy
 * @since 2021/10/18
 */
public interface ISysPermissionMenuService {

    /**
     * Query permissions based on user ID
     * @param userId - user id
     * @return user permissions
     */
    Set<String> selectPermissionMenuByUserId(String userId);

}
