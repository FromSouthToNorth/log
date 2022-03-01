package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.hyzt.common.annotation.DataScope;
import vip.hyzt.common.utils.StringUtils;
import vip.hyzt.common.utils.uuid.IdUtils;
import vip.hyzt.system.domain.SysArticle;
import vip.hyzt.system.domain.SysArticleConnectTag;
import vip.hyzt.system.mapper.SysArticleMapper;
import vip.hyzt.system.mapper.SysArticleConnectTagMapper;
import vip.hyzt.system.service.ISysArticleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文章业务服务实现
 * @author hy
 */
@Service
public class SysArticleServiceImpl implements ISysArticleService {

    @Autowired
    private SysArticleMapper articleMapper;

    @Autowired
    private SysArticleConnectTagMapper articleTagMapper;

    /**
     * 查询文章列表
     * @param article 文章查询对象参数
     * @return 文章结果集
     */
    @Override
    @DataScope(userAlias = "u")
    public List<SysArticle> selectArticle(SysArticle article) {
        return articleMapper.selectArticle(article);
    }

    /**
     * 根据文章 id 查询文章
     * @param articleId 文章 id
     * @return id 对应的文章
     */
    @Override
    public SysArticle selectArticleByArticleId(String articleId) {
        return articleMapper.selectArticleByArticleId(articleId);
    }

    /**
     * 新增文章
     * @param article 文章信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertArticle(SysArticle article) {
        article.setArticleId(IdUtils.simpleUUID());
        if ("1".equals(article.getIsTop())) {
            articleMapper.cancelArticleTop();
        }
        int rows = articleMapper.insertArticle(article);
        insertArticleTag(article);
        return rows;
    }

    /**
     * 修改文章信息
     * @param article 文章信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateArticle(SysArticle article) {
        if ("1".equals(article.getIsTop())) {
            articleMapper.cancelArticleTop();
        }
        int rows = articleMapper.updateArticle(article);
        articleTagMapper.deleteArticleTagByArticleId(article.getArticleId());
        insertArticleTag(article);
        return rows;
    }

    /**
     * 修改文章置顶
     * @param article 文章信息
     * @return 结果
     */
    @Override
    public int changeTop(SysArticle article) {
        articleMapper.cancelArticleTop();
        return articleMapper.updateArticle(article);
    }

    /**
     * 根据文章ID批量删除文章
     * @param articleIds 删除的文章ID
     * @return 结果
     */
    @Override
    public int deleteArticleByIds(String[] articleIds) {
        articleTagMapper.deleteArticleTag(articleIds);
        return articleMapper.deleteArticleByIds(articleIds);
    }

    /**
     * 查询置顶文章
     * @return 置顶文章
     */
    @Override
    public SysArticle topArticle() {
        return articleMapper.topArticle();
    }

    /**
     * 查询文章列表
     * @return 文章列表
     */
    @Override
    public List<SysArticle> selectArticleAll() {
        return articleMapper.selectArticleAll();
    }

    /**
     * 根据文章的分类标签发现更多文章
     * @param article 文章的分类标签
     * @return 发现更多的文章
     */
    @Override
    public List<SysArticle> selectArticleDiscoverMore(SysArticle article) {
        return articleMapper.selectArticleDiscoverMore(article.getArticleId(), article.getTypeId(), article.getTagIds());
    }

    /**
     * 关键字搜索文章
     * @param keywords 关键字
     * @return 搜索结果
     */
    @Override
    public List<SysArticle> searchArticle(String keywords) {
        List<SysArticle> articles = articleMapper.searchArticle(keywords);
        for (SysArticle article : articles) {
            String newS;
            int index = article.getArticleContent().indexOf(keywords);
            if (index != -1) {
                // 获取关键词前面的文字
                int preIndex = index > 25 ? index - 25 : 0;
                String preText = article.getArticleContent().substring(preIndex, index);
                // 获取关键词到后面的文字
                int last = index + keywords.length();
                int postLength = article.getArticleContent().length() - last;
                int postIndex = postLength > 175 ? last + 175 : last + postLength;
                String postText = article.getArticleContent().substring(index, postIndex);
                newS = (preText + postText).replaceAll(keywords, "<em>" + keywords + "</em>");
                article.setArticleContent(newS);
            }
        }
        return articles;
    }

    /**
     * 查询文章归档
     * @return 返回文章的归档
     */
    @Override
    public Map<String, List<SysArticle>> articleArchive() {
        List<String> years = articleMapper.selectArticleCreateYear();
        Map<String, List<SysArticle>> articleMap = new ConcurrentHashMap<>();
        for (String year : years) {
            List<SysArticle> articles = articleMapper.selectArticleByYear(year);
            articleMap.put(year, articles);
        }
        return articleMap;
    }

    /**
     * 新增文章标签信息
     * @param article 文章信息
     */
    private void insertArticleTag(SysArticle article) {
        String[] tagIds = article.getTagIds();
        if (StringUtils.isNotNull(tagIds)) {
            List<SysArticleConnectTag> list = new ArrayList<>();
            for (String tagId : tagIds) {
                SysArticleConnectTag at = new SysArticleConnectTag();
                at.setId(IdUtils.simpleUUID());
                at.setArticleId(article.getArticleId());
                at.setTagId(tagId);
                list.add(at);
            }
            if (list.size() > 0) {
                articleTagMapper.batchArticleTag(list);
            }
        }
    }
}
