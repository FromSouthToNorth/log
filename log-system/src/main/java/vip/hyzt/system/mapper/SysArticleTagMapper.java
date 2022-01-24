package vip.hyzt.system.mapper;

import vip.hyzt.system.domain.SysArticleTag;

import java.util.List;

/**
 * 文章标签持久层
 * @author hy
 */
public interface SysArticleTagMapper {

    /**
     * 查询文章标签列表
     * @param tag 标签查询参数
     * @return 文章标签结果集
     */
    List<SysArticleTag> selectTagList(SysArticleTag tag);

    /**
     * 查询所有文章标签
     * @return 文章标签列表
     */
    List<SysArticleTag> selectTagAll();

    /**
     * 根据文章 ID 获取标签选择框列表
     * @param articleId 文章 ID
     * @return 选中的标签 ID 列表
     */
    List<String> selectTagListByArticleId(String articleId);

    /**
     * 根据文章标签查询标签信息
     * @param typeId 标签 ID
     * @return 文章标签信息
     */
    SysArticleTag selectTagById(String typeId);

    /**
     * 新增文章标签
     * @param type 文章标签信息
     * @return 结果
     */
    int insertTag(SysArticleTag type);

    /**
     * 根据文章标签编号编辑文章标签
     * @param type 文章标签信息
     * @return 结果
     */
    int updateTagById(SysArticleTag type);

    /**
     * 根据文章标签编号批量删除文章标签
     * @param typeIds 删除的文章标签编号
     * @return 结果
     */
    int deleteTagByIds(String[] typeIds);

    /**
     * 校验文章标签名称是否唯一
     * @param typeName 文章标签名称
     * @return 文章标签信息
     */
    SysArticleTag checkTagNameUnique(String typeName);

    /**
     * 根据文章标签编号查询文章标签使用量
     * @param typeId 文章标签编号
     * @return 结果
     */
    int countTagByTagId(String typeId);
}
