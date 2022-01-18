package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.constant.Constants;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.core.text.Convert;
import vip.hyzt.common.exception.ServiceException;
import vip.hyzt.common.utils.StringUtils;
import vip.hyzt.common.utils.redis.RedisCache;
import vip.hyzt.system.domain.SysConfig;
import vip.hyzt.system.mapper.SysConfigMapper;
import vip.hyzt.system.service.ISysConfigService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

/**
 * 参数配置 服务层实现
 * @author hy
 * @since 2021/10/22
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {

    @Autowired
    private SysConfigMapper configMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init() {
        loadingConfigCache();
    }

    /**
     * 加载参数缓存数据
     */
    @Override
    public void loadingConfigCache() {
        List<SysConfig> configList = configMapper.selectConfigList(new SysConfig());
        for (SysConfig config : configList) {
            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
    }

    @Override
    public void clearConfigCache() {
        Collection<String> keys = redisCache.keys(Constants.SYS_CONFIG_KEY + "*");
        redisCache.deleteObject(keys);
    }

    @Override
    public void resetConfigCache() {
        clearConfigCache();
        loadingConfigCache();
    }

    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        String configId = ObjectUtils.isEmpty(config.getConfigId()) ? "-1" : config.getConfigId();
        SysConfig info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (!ObjectUtils.isEmpty(info) && info.getConfigId().equals(configId))
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 获取系统配置的验证码开关
     */
    @Override
    public boolean selectCaptchaOnOff() {
        String captchaOnOff = selectConfigByKey("sys.account.captchaOnOff");
        if (ObjectUtils.isEmpty(captchaOnOff)) {
            return true;
        }
        return "0".equals(captchaOnOff);
    }

    @Override
    public List<SysConfig> selectConfigList(SysConfig config) {
        return configMapper.selectConfigList(config);
    }

    @Override
    public int insertConfig(SysConfig config) {
        int row = configMapper.insertConfig(config);
        if (row > 0) {
            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    @Override
    public int updateConfig(SysConfig config) {
        int row = configMapper.updateConfig(config);
        if (row > 0)
        {
            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    @Override
    public void deleteConfigByIds(String[] configIds) {
        for (String configId : configIds)
        {
            SysConfig config = selectConfigById(configId);
            if (UserConstants.YES == config.getConfigType())
            {
                throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
            }
            configMapper.deleteConfigById(configId);
            redisCache.deleteObject(getCacheKey(config.getConfigKey()));
        }
    }

    @Override
    public SysConfig selectConfigById(String configId) {
        SysConfig config = new SysConfig();
        config.setConfigId(configId);
        return configMapper.selectConfig(config);
    }

    /**
     * 根据键名查询参数配置信息
     * @param configKey 参数键名
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        String cacheObject = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));
        if (!ObjectUtils.isEmpty(cacheObject)) {
            return cacheObject;
        }
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = configMapper.selectConfig(config);
        if (!ObjectUtils.isEmpty(retConfig))
        {
            redisCache.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 设置cache key
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return Constants.SYS_CONFIG_KEY + configKey;
    }

}