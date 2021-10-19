package vip.hyzt.system.mapper;

import vip.hyzt.system.domain.SysOperLog;

import java.util.List;

/**
 * Operation log Data layer
 * @author hy
 * @since 2021/10/19
 */
public interface SysOperLogMapper {

    /**
     * New operation log
     */
    void insertOperLog(SysOperLog operLog);

    /**
     * Return to query system operation log collection
     */
    List<SysOperLog> selectOperLog(SysOperLog operLog);

    /**
     * Delete system operation logs in batches
     * @param ids - delete log id
     */
    int deleteOperLogByIds(String[] ids);

    /**
     * Return Query operation log details
     */
    SysOperLog selectOperLogById(Long operId);

    /**
     * Clear operation log
     */
    void cleanOperLog();

}
