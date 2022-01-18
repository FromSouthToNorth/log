package vip.hyzt.system.service;

import vip.hyzt.system.domain.SysArticle;

import java.util.List;

/**
 * 文章业务服务接口
 * @author hy
 */
public interface ISysArticleService {

    /**
     * 查询文章列表
     * @param article 文章查询对象参数
     * @return 文章结果集
     */
    List<SysArticle> selectArticle(SysArticle article);

}
