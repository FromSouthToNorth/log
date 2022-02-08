package vip.hyzt.api.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.hyzt.common.annotation.Log;
import vip.hyzt.common.core.page.TableDataInfo;
import vip.hyzt.common.enums.BusinessType;
import vip.hyzt.common.enums.FilePathEnum;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.web.controller.BaseController;
import vip.hyzt.system.domain.SysArticle;
import vip.hyzt.system.domain.SysArticleTag;
import vip.hyzt.system.domain.SysArticleType;
import vip.hyzt.system.service.ISysArticleService;
import vip.hyzt.system.service.ISysArticleTagService;
import vip.hyzt.system.service.ISysArticleTypeService;
import vip.hyzt.system.service.impl.SysUploadService;

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
    private ISysArticleTagService tagService;

    @Autowired
    private ISysArticleTypeService typeService;

    @Autowired
    private SysUploadService uploadService;

    /**
     * 查询文章列表
     */
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/system/article/list")
    public TableDataInfo articleList(SysArticle article) {
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
        List<SysArticleTag> tags = tagService.selectTagAll();
        List<SysArticleType> types = typeService.selectTypeAll();
        ajax.put("tags", tags);
        ajax.put("types", types);
        ajax.put(Result.DATA_TAG, articleService.selectArticleByArticleId(articleId));
        ajax.put("tagIds", tagService.selectTagListByArticleId(articleId));
        return ajax;
    }

    /**
     * 上传文章封面
     */
    @PreAuthorize("@ss.hasPermi('system:article:upload')")
    @PostMapping("/system/article/image")
    public Result uploadArticleImage(@RequestParam("articlefile") MultipartFile file) {
        if (!file.isEmpty()) {
            String url = uploadService.executeUpload(file, FilePathEnum.ARTICLE.getPath());
            Result ajax = Result.success();
            ajax.put("imgUrl", url);
            return ajax;
        }
        return Result.error("上传图片异常，请联系管理员");
    }

    /**
     * 新增文章
     */
    @PreAuthorize("@ss.hasPermi('system:article:add')")
    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @PostMapping("/system/article")
    public Result add(@Validated @RequestBody SysArticle article) {
        article.setUserId(getUserId());
        article.setCreateBy(getUsername());
        return toAjax(articleService.insertArticle(article));
    }

    /**
     * 修改文章
     */
    @PreAuthorize("@ss.hasPermi('system:article:edit')")
    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @PutMapping("/system/article")
    public Result edit(@Validated @RequestBody SysArticle article) {
        article.setUpdateBy(getUsername());
        return toAjax(articleService.updateArticle(article));
    }

    /**
     * 修改文章置顶
     */
    @PreAuthorize("@ss.hasPermi('system:article:edit')")
    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @PutMapping("/system/article/changeTop")
    public Result changeTop(@RequestBody SysArticle article) {
        article.setUpdateBy(getUsername());
        return toAjax(articleService.changeTop(article));
    }

    /**
     * 删除文章
     */
    @PreAuthorize("@ss.hasPermi('system:article:remove')")
    @Log(title = "文章管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/system/article/{articleIds}")
    public Result remove(@PathVariable String[] articleIds) {
        return toAjax(articleService.deleteArticleByIds(articleIds));
    }

    /**
     * 查询置顶文章
     */
    @GetMapping("/home/article/top")
    public Result topArticle() {
        Result ajax = Result.success();
        ajax.put(Result.DATA_TAG, articleService.topArticle());
        return ajax;
    }

    /**
     * 查询文章列表
     */
    @GetMapping("/home/article")
    public Result list() {
        Result ajax = Result.success();
        ajax.put(Result.DATA_TAG, articleService.selectArticleAll());
        return ajax;
    }

    /**
     * 根据文章编号查询文章
     */
    @GetMapping(value = {"/home/article/{articleId}"})
    public Result getArticleInfo(@PathVariable(value = "articleId", required = false) String articleId) {
        Result ajax = Result.success();
        List<SysArticleTag> tags = tagService.selectTagAll();
        ajax.put("tags", tags);
        ajax.put(Result.DATA_TAG, articleService.selectArticleByArticleId(articleId));
        ajax.put("tagIds", tagService.selectTagListByArticleId(articleId));
        return ajax;
    }
}
