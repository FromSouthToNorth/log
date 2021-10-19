package vip.hyzt.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.hyzt.system.domain.SysUser;
import vip.hyzt.system.mapper.SysUserMapper;
import vip.hyzt.system.service.ISysUserService;

/**
 * User business layer processing
 * @author hy
 * @since 2021/10/19
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;

    /**
     * Query user information based on user name
     * @param userName user name
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

}
