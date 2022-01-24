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

    /**
     * 查询所以文章分类
     * @return 文章分类结果集
     */
    List<SysType> selectTypeAll();

    /**
     * 根据文章类型查询类型信息
     * @param typeId 类型 ID
     * @return 文章类型信息
     */
    SysType selectTypeById(String typeId);

    /**
     * 新增文章类型
     * @param type 文章类型信息
     * @return 结果
     */
    int insertType(SysType type);

    /**
     * 根据文章类型编号编辑文章类型
     * @param type 文章类型信息
     * @return 结果
     */
    int updateTypeById(SysType type);

    /**
     * 根据文章类型编号批量删除文章类型
     * @param typeIds 删除的文章类型编号
     * @return 结果
     */
    int deleteTypeByIds(String[] typeIds);

    /**
     * 校验文章类型名称是否唯一
     * @param typeName 文章类型名称
     * @return 文章类型信息
     */
    SysType checkTypeNameUnique(String typeName);

    /**
     * 根据文章类型编号查询文章类型使用量
     * @param typeId 文章类型编号
     * @return 结果
     */
    int countTypeByTypeId(String typeId);
}
