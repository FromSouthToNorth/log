package vip.hyzt.core.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.enums.UserStatus;
import vip.hyzt.common.exception.ServiceException;
import vip.hyzt.core.domain.LoginUser;
import vip.hyzt.system.domain.SysUser;
import vip.hyzt.system.service.ISysUserService;

import java.util.Objects;

/**
 * User authentication processing
 * @author hy
 * @since 2021/10/18
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (ObjectUtils.isEmpty(user)) {
            logger.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        else if (Objects.equals(UserStatus.DELETED.getCode(), user.getDelFlag())) {
            logger.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (Objects.equals(UserStatus.DISABLE.getCode(), user.getStatus())) {
            logger.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        return createLoginUser(user);
    }

    private UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user.getUserId(), user, permissionService.getPermissionMenu(user));
    }
}
