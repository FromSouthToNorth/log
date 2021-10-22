package vip.hyzt.system.mapper;

import vip.hyzt.system.domain.SysConfig;

import java.util.List;

/**
 * 参数配置 数据层
 * @author hy
 * @since 2021/10/22
 */
public interface SysConfigMapper {

    /**
     * 查询参数配置列表
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    List<SysConfig> selectConfigList(SysConfig config);

}
