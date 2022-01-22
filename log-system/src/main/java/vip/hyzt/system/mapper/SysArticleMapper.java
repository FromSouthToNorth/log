package vip.hyzt.system.mapper;

import vip.hyzt.system.domain.SysArticle;

import java.util.List;

/**
 * 文章数据持久层
 * @author hy
 * @since 2022/01/18
 */
public interface SysArticleMapper {

    /**
     * 查询文章列表
     * @param article 文章查询对象参数
     * @return 文章结果集
     */
    List<SysArticle> selectArticle(SysArticle article);

    /**
     * 根据文章 id 查询文章
     * @param articleId 文章 id
     * @return id 对应的文章
     */
    SysArticle selectArticleByArticleId(String articleId);

    /**
     * 新增文章
     * @param article 文章信息
     * @return 结果
     */
    int insertArticle(SysArticle article);

    /**
     * 修改文章信息
     * @param article 文章信息
     * @return 结果
     */
    int updateArticle(SysArticle article);

    /**
     * 取消文章置顶
     * @return 结果
     */
    int cancelArticleTop();

    /**
     * 根据文章ID批量删除文章
     * @param articleIds 删除的文章ID
     * @return 结果
     */
    int deleteArticleByIds(String[] articleIds);
}
