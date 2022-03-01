package vip.hyzt.system.mapper;

import org.apache.ibatis.annotations.Param;
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

    /**
     * 查询置顶文章
     * @return 置顶文章
     */
    SysArticle topArticle();

    /**
     * 查询文章列表
     * @return 文章列表
     */
    List<SysArticle> selectArticleAll();

    /**
     * 根据文章分类标签发现更多文章
     * @param typeId 分类 ID
     * @param tagIds 标签 ID
     * @return 发现更多的文章
     */
    List<SysArticle> selectArticleDiscoverMore(@Param("articleId") String articleId , @Param("typeId") String typeId, @Param("tagIds") String[] tagIds);

    /**
     * 关键字搜索文章
     * @param keywords 关键字
     * @return 搜索结果
     */
    List<SysArticle> searchArticle(String keywords);

    /**
     * 查询文章发表的年份
     * @return 文章发表的年份列表
     */
    List<String> selectArticleCreateYear();

    /**
     * 根据发布年份查询文章
     * @param year 年份
     * @return 当前查询年份的发布文章
     */
    List<SysArticle> selectArticleByYear(String year);
}
