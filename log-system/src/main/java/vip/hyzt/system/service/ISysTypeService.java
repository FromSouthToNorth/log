package vip.hyzt.system.service;

import vip.hyzt.system.domain.SysType;

import java.util.List;

/**
 * 文章分类业务服务接口
 * @author hy
 */
public interface ISysTypeService {

    /**
     * 查询文章分类列表
     * @param type 查询分类参数
     * @return 文章分类结果集
     */
    List<SysType> selectTypeList(SysType type);

    /**
     * 查询所以文章分类
     * @return 文章分类结果集
     */
    List<SysType> selectTypeAll();
}
