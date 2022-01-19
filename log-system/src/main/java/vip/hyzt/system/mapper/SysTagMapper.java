package vip.hyzt.system.mapper;

import vip.hyzt.system.domain.SysTag;

import java.util.List;

/**
 * 文章标签持久层
 * @author hy
 */
public interface SysTagMapper {

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

}
