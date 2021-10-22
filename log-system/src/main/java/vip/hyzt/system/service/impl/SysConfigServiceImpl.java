package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.constant.Constants;
import vip.hyzt.common.utils.redis.RedisCache;
import vip.hyzt.system.domain.SysConfig;
import vip.hyzt.system.mapper.SysConfigMapper;
import vip.hyzt.system.service.ISysConfigService;

import javax.annotation.PostConstruct;
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

    /**
     * 根据键名查询参数配置信息
     * @param configKey 参数键名
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        Object cacheObject = redisCache.getCacheObject(getCacheKey(configKey));
        if (ObjectUtils.isEmpty(cacheObject)) {
            return null;
        }
        if (cacheObject instanceof String) {
            return (String) cacheObject;
        }
        return configKey;
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