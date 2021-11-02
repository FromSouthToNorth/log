package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.hyzt.system.domain.SysOperLog;
import vip.hyzt.system.mapper.SysOperLogMapper;
import vip.hyzt.system.service.ISysOperLogService;

import java.util.List;

/**
 * Operation log Service layer processing
 * @author hy
 * @since 2021/10/19
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {

    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * New operation log
     */
    @Override
    public void insertOperLog(SysOperLog operLog) {
        operLogMapper.insertOperLog(operLog);
    }

    /**
     * Query operation log list
     */
    @Override
    public List<SysOperLog> selectOperLog(SysOperLog operLog) {
        return operLogMapper.selectOperLog(operLog);
    }

    /**
     * Batch delete operation log
     * @param ids - delete log id
     */
    @Override
    public int deleteOperLogIds(String[] ids) {
        return operLogMapper.deleteOperLogByIds(ids);
    }

    /**
     * Query operation log details
     */
    @Override
    public SysOperLog selectOperLogById(Long operId) {
        return operLogMapper.selectOperLogById(operId);
    }

    /**
     * Clear operation log
     */
    @Override
    public void cleanOperLog() {
        operLogMapper.cleanOperLog();
    }

}
