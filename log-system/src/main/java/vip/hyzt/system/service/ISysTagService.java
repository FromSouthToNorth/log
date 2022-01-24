package vip.hyzt.system.service;

import vip.hyzt.system.domain.SysTag;

import java.util.List;

/**
 * 文章标签业务服务接口
 * @author hy
 */
public interface ISysTagService {

    /**
     * 查询文章标签列表
     * @param tag 标签查询参数
     * @return 文章标签结果集
     */
    List<SysTag> selectTagList(SysTag tag);

    /**
     * 查询所有文章标签
     * @return 文章标签列表
     */
    List<SysTag> selectTagAll();

    /**
     * 根据文章 ID 获取标签选择框列表
     * @param articleId 文章 ID
     * @return 选中的标签 ID 列表
     */
    List<String> selectTagListByArticleId(String articleId);


    /**
     * 根据标签标签查询标签信息
     * @param tagId 标签 ID
     * @return 标签标签信息
     */
    SysTag selectTagById(String tagId);

    /**
     * 新增标签标签
     * @param tag 标签标签信息
     * @return 结果
     */
    int insertTag(SysTag tag);

    /**
     * 根据标签标签编号编辑标签标签
     * @param tag 标签标签信息
     * @return 结果
     */
    int updateTagById(SysTag tag);

    /**
     * 根据标签标签编号批量删除标签标签
     * @param tagIds 删除的标签标签编号
     * @return 结果
     */
    int deleteTagByIds(String[] tagIds);

    /**
     * 校验标签标签名称是否唯一
     * @param tag 标签标签信息
     * @return 结果
     */
    String checkTagNameUnique(SysTag tag);

    /**
     * 根据标签标签编号查询标签标签使用量
     * @param tagId 标签标签编号
     * @return 结果
     */
    int countTagByTagId(String tagId);
}
