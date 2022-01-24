package vip.hyzt.system.mapper;

import vip.hyzt.system.domain.SysArticleConnectTag;

import java.util.List;

/**
 * 文章标签关联数据接口
 * @author hy
 */
public interface SysArticleConnectTagMapper {

    /**
     * 通过文章ID删除文章和标签关联
     * @param articleId 文章ID
     * @return 结果
     */
    int deleteArticleTagByArticleId(String articleId);

    /**
     * 根据文章 ID 批量删除文章和标签关联
     * @param articleIds 需要删除的数据文章 ID
     * @return 结果
     */
    int deleteArticleTag(String[] articleIds);

    /**
     * 批量新增文章和标签信息
     * @param articleTagList 文章博客列表
     * @return 结果
     */
    int batchArticleTag(List<SysArticleConnectTag> articleTagList);

}
