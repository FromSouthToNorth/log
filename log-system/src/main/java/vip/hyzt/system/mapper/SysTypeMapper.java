package vip.hyzt.system.mapper;

import vip.hyzt.system.domain.SysType;

import java.util.List;

/**
 * 文章分类持久层
 * @author hy
 */
public interface SysTypeMapper {

    /**
     * 查询文章分类列表
     * @param type 查询分类参数
     * @return 文章分类结果集
     */
    List<SysType> selectTypeList(SysType type);

}
