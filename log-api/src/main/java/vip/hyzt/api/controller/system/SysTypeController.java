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
import vip.hyzt.system.domain.SysType;
import vip.hyzt.system.service.ISysTypeService;

import java.util.List;

/**
 * 文章分类业务接口
 * @author hy
 * @since 2022/01/24
 */
@RestController
public class SysTypeController extends BaseController {

    @Autowired
    private ISysTypeService typeService;

    /**
     * 查询文章类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping("/system/type/list")
    public TableDataInfo adminTypeList(SysType type) {
        startPage();
        List<SysType> list = typeService.selectTypeList(type);
        return getDataTable(list);
    }

    /**
     * 根据文章类型编号查询类型信息
     */
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping("/system/type/{typeId}")
    public Result getInfo(@PathVariable String typeId) {
        return Result.success(typeService.selectTypeById(typeId));
    }

    /**
     * 新增类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @Log(title = "文章类型管理", businessType = BusinessType.INSERT)
    @PostMapping("/system/type")
    public Result add(@Validated @RequestBody SysType type) {
        if (UserConstants.NOT_UNIQUE.equals(typeService.checkTypeNameUnique(type))) {
            return Result.error("新增文章类型'" + type.getTypeName() + "'失败, 类型名称已存在" );
        }
        type.setCreateBy(getUsername());
        type.setTypeId(IdUtils.fastSimpleUUID());
        return toAjax(typeService.insertType(type));
    }

    /**
     * 编辑类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @Log(title = "文章类型管理", businessType = BusinessType.UPDATE)
    @PutMapping("/system/type")
    public Result edit(@Validated @RequestBody SysType type) {
        if (UserConstants.NOT_UNIQUE.equals(typeService.checkTypeNameUnique(type))) {
            return Result.error("编辑文章类型'" + type.getTypeName() + "'失败, 类型名称已存在" );
        }
        type.setUpdateBy(getUsername());
        return toAjax(typeService.updateTypeById(type));
    }

    /**
     * 删除类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @Log(title = "文章类型管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/system/type/{typeIds}")
    public Result remove(@PathVariable String[] typeIds) {
        return toAjax(typeService.deleteTypeByIds(typeIds));
    }
}
