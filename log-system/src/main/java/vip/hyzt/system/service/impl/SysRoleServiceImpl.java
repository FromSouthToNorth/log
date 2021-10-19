package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vip.hyzt.system.domain.SysRole;
import vip.hyzt.system.mapper.SysRoleMapper;
import vip.hyzt.system.service.ISysRoleService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Role business processing
 * @author hy
 * @since 2021/10/19
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * Query role permissions based on user ID
     * @param userId - user id
     */
    @Override
    public Set<String> selectRolePermissionByUserId(String userId) {
        List<SysRole> perms = roleMapper.selectRolePermissionByUserId(userId);
        HashSet<String> permSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (!ObjectUtils.isEmpty(perm)) {
                permSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permSet;
    }

}
