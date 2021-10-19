package vip.hyzt.system.service;

import vip.hyzt.system.domain.SysLoginInfo;

import java.util.List;

/**
 * System access log information service layer
 * @author hy
 * @since 2021/10/18
 */
public interface ISysLoginInfoService {

    /**
     * Added system login log
     */
    void insertLoginInfo(SysLoginInfo loginInfo);

    /**
     * Return to query system login log collection
     */
    List<SysLoginInfo> selectLoginInfoList(SysLoginInfo loginInfo);

    /**
     * Returns the number of affected rows of system login logs deleted in batches
     */
    int deleteLoginInfoIds(String[] infoIds);

    /**
     * Clear system login log
     */
    void cleanLoginInfo();

}
