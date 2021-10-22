package vip.hyzt.system.service;

/**
 * 参数配置 服务层
 * @author hy
 * @since 2021/10/18
 */
public interface ISysConfigService {

    /**
     * 加载参数缓存数据
     */
    void loadingConfigCache();

    /**
     * 获取系统配置的验证码开关
     */
    boolean selectCaptchaOnOff();

    /**
     * 根据键名查询参数配置信息
     * @param configKey 参数键名
     * @return 参数键值
     */
    String selectConfigByKey(String configKey);

}
