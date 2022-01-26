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
import java.util.List;

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
