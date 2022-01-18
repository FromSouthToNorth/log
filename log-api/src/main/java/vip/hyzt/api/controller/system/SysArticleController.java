package vip.hyzt.api.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.hyzt.common.core.page.TableDataInfo;
import vip.hyzt.core.web.controller.BaseController;
import vip.hyzt.system.domain.SysArticle;
import vip.hyzt.system.service.ISysArticleService;

import java.util.List;

/**
 * 文章业务接口
 * @author hy
 */
@RestController
public class SysArticleController extends BaseController {

    @Autowired
    private ISysArticleService articleService;

    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/system/article/list")
    public TableDataInfo adminList(SysArticle article) {
        startPage();
        List<SysArticle> list = articleService.selectArticle(article);
        return getDataTable(list);
    }

}
