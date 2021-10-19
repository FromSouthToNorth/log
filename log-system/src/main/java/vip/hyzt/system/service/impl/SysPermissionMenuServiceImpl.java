package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.hyzt.common.utils.string.StringUtils;
import vip.hyzt.system.mapper.SysPermissionMenuMapper;
import vip.hyzt.system.service.ISysPermissionMenuService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Permission menu business processing
 * @author hy
 * @since 2021/10/19
 */
@Service
public class SysPermissionMenuServiceImpl implements ISysPermissionMenuService {

    @Autowired
    private SysPermissionMenuMapper permissionMenuMapper;

    /**
     * Query permissions based on user ID
     * @param userId - user id
     */
    @Override
    public Set<String> selectPermissionMenuByUserId(String userId) {
        List<String> perms = permissionMenuMapper.selectPermissionMenuByUserId(userId);
        HashSet<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (!StringUtils.isNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
