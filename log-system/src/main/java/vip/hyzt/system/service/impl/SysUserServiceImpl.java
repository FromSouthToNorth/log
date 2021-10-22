package vip.hyzt.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.hyzt.common.constant.UserConstants;
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
     * Return query user information based on user name
     * @param userName user name
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * 校验用户名称是否唯一
     * @param userName - 用户名称
     * @return 结果 "0" 表示唯一，"1" 表示不唯一
     */
    @Override
    public String checkUserNameUnique(String userName) {
        int count = userMapper.checkUserNameUnique(userName);
        if (count > 0) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean registerUser(SysUser user) {
        return userMapper.insertUser(user) > 0;
    }

}
