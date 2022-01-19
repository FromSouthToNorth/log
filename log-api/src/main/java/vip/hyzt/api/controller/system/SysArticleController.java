package vip.hyzt.api.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vip.hyzt.common.core.page.TableDataInfo;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.web.controller.BaseController;
import vip.hyzt.system.domain.SysArticle;
import vip.hyzt.system.domain.SysTag;
import vip.hyzt.system.domain.SysType;
import vip.hyzt.system.service.ISysArticleService;
import vip.hyzt.system.service.ISysTagService;
import vip.hyzt.system.service.ISysTypeService;

import java.util.List;

/**
 * 文章业务接口
 * @author hy
 */
@RestController
public class SysArticleController extends BaseController {

    @Autowired
    private ISysArticleService articleService;

    @Autowired
    private ISysTagService tagService;

    @Autowired
    private ISysTypeService typeService;

    /**
     * 查询文章列表
     */
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/system/article/list")
    public TableDataInfo adminList(SysArticle article) {
        startPage();
        List<SysArticle> list = articleService.selectArticle(article);
        return getDataTable(list);
    }

    /**
     * 根据文章编号查询文章
     */
    @PreAuthorize("@ss.hasPermi('system:article:query')")
    @GetMapping(value = {"/system/article/{articleId}"})
    public Result getInfo(@PathVariable(value = "articleId", required = false) String articleId) {
        Result ajax = Result.success();
        List<SysTag> tags = tagService.selectTagAll();
        List<SysType> types = typeService.selectTypeAll();
        ajax.put("tags", tags);
        ajax.put("types", types);
        ajax.put(Result.DATA_TAG, articleService.selectArticleByArticleId(articleId));
        ajax.put("tagIds", tagService.selectTagListByArticleId(articleId));
        return ajax;
    }
}