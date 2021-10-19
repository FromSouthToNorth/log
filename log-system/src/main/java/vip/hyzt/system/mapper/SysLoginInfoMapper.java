package vip.hyzt.system.mapper;

import vip.hyzt.system.domain.SysLoginInfo;

import java.util.List;

/**
 * System access log situation information Data layer
 * @author hy
 * @since 2021/10/18
 */
public interface SysLoginInfoMapper {

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
