package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.hyzt.common.annotation.DataScope;
import vip.hyzt.system.domain.SysArticle;
import vip.hyzt.system.mapper.SysArticleMapper;
import vip.hyzt.system.service.ISysArticleService;

import java.util.List;

/**
 * 文章业务服务实现
 * @author hy
 */
@Service
public class SysArticleServiceImpl implements ISysArticleService {

    @Autowired
    private SysArticleMapper articleMapper;

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

    @Override
    public SysArticle selectArticleByArticleId(String articleId) {
        return null;
    }
}
