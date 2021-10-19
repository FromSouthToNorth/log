package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.hyzt.system.domain.SysLoginInfo;
import vip.hyzt.system.mapper.SysLoginInfoMapper;
import vip.hyzt.system.service.ISysLoginInfoService;

import java.util.List;

/**
 * System access log situation information Service layer processing
 * @author hy
 * @since 2021/10/19
 */
@Service
public class SysLoginInfoServiceImpl implements ISysLoginInfoService {

    @Autowired
    private SysLoginInfoMapper loginInfoMapper;

    /**
     * Added system login log
     */
    @Override
    public void insertLoginInfo(SysLoginInfo loginInfo) {
        loginInfoMapper.insertLoginInfo(loginInfo);
    }

    /**
     * Query system login log collection
     */
    @Override
    public List<SysLoginInfo> selectLoginInfoList(SysLoginInfo loginInfo) {
        return loginInfoMapper.selectLoginInfoList(loginInfo);
    }

    /**
     * Delete system login logs in batches
     */
    @Override
    public int deleteLoginInfoIds(String[] infoIds) {
        return loginInfoMapper.deleteLoginInfoIds(infoIds);
    }

    /**
     * Clear system login log
     */
    @Override
    public void cleanLoginInfo() {
        loginInfoMapper.cleanLoginInfo();
    }
}
