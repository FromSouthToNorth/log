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
}
