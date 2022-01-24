package vip.hyzt.api.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.hyzt.common.annotation.Log;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.core.page.TableDataInfo;
import vip.hyzt.common.enums.BusinessType;
import vip.hyzt.common.utils.uuid.IdUtils;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.web.controller.BaseController;
import vip.hyzt.system.domain.SysArticleTag;
import vip.hyzt.system.service.ISysArticleTagService;

import java.util.List;

/**
 * 标签业务接口
 * @author hy
 * @since 2022/01/24
 */
@RestController
public class SysArticleTagController extends BaseController {

    @Autowired
    private ISysArticleTagService tagService;

    @PreAuthorize("@ss.hasPermi('system:tag:query')")
    @GetMapping("/system/tag/list")
    public TableDataInfo adminTagList(SysArticleTag tag) {
        startPage();
        List<SysArticleTag> list = tagService.selectTagList(tag);
        return getDataTable(list);
    }

    /**
     * 根据文章标签编号查询标签信息
     */
    @PreAuthorize("@ss.hasPermi('system:tag:query')")
    @GetMapping("/system/tag/{tagId}")
    public Result getInfo(@PathVariable String tagId) {
        return Result.success(tagService.selectTagById(tagId));
    }

    /**
     * 新增标签
     */
    @PreAuthorize("@ss.hasPermi('system:tag:add')")
    @Log(title = "文章标签管理", businessType = BusinessType.INSERT)
    @PostMapping("/system/tag")
    public Result add(@Validated @RequestBody SysArticleTag tag) {
        if (UserConstants.NOT_UNIQUE.equals(tagService.checkTagNameUnique(tag))) {
            return Result.error("新增文章标签'" + tag.getTagName() + "'失败, 标签名称已存在" );
        }
        tag.setCreateBy(getUsername());
        tag.setTagId(IdUtils.fastSimpleUUID());
        return toAjax(tagService.insertTag(tag));
    }

    /**
     * 编辑标签
     */
    @PreAuthorize("@ss.hasPermi('system:tag:edit')")
    @Log(title = "文章标签管理", businessType = BusinessType.UPDATE)
    @PutMapping("/system/tag")
    public Result edit(@Validated @RequestBody SysArticleTag tag) {
        if (UserConstants.NOT_UNIQUE.equals(tagService.checkTagNameUnique(tag))) {
            return Result.error("编辑文章标签'" + tag.getTagName() + "'失败, 标签名称已存在" );
        }
        tag.setUpdateBy(getUsername());
        return toAjax(tagService.updateTagById(tag));
    }

    /**
     * 删除标签
     */
    @PreAuthorize("@ss.hasPermi('system:tag:remove')")
    @Log(title = "文章标签管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/system/tag/{tagIds}")
    public Result remove(@PathVariable String[] tagIds) {
        return toAjax(tagService.deleteTagByIds(tagIds));
    }
}
