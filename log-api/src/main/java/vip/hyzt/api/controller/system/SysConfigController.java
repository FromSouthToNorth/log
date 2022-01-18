package vip.hyzt.api.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.hyzt.common.annotation.Log;
import vip.hyzt.common.annotation.RepeatSubmit;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.core.page.TableDataInfo;
import vip.hyzt.common.enums.BusinessType;
import vip.hyzt.common.utils.uuid.IdUtils;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.web.controller.BaseController;
import vip.hyzt.system.domain.SysConfig;
import vip.hyzt.system.service.ISysConfigService;

import java.util.List;

/**
 * 参数配置 信息操作处理
 *
 * @author hy
 */
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {

    @Autowired
    private ISysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysConfig config) {
        startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        return getDataTable(list);
    }

    /**
     * 根据参数编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:config:query')")
    @GetMapping(value = "/{configId}")
    public Result getInfo(@PathVariable String configId) {
        return Result.success(configService.selectConfigById(configId));
    }

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public Result getConfigKey(@PathVariable String configKey) {
        return Result.success(configService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:add')")
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping
    @RepeatSubmit
    public Result add(@Validated @RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return Result.error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setConfigId(IdUtils.simpleUUID());
        config.setCreateBy(getUsername());
        return toAjax(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:edit')")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return Result.error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setUpdateBy(getUsername());
        return toAjax(configService.updateConfig(config));
    }

    /**
     * 删除参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:remove')")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{configIds}")
    public Result remove(@PathVariable String[] configIds) {
        configService.deleteConfigByIds(configIds);
        return success();
    }

    /**
     * 刷新参数缓存
     */
    @PreAuthorize("@ss.hasPermi('system:config:remove')")
    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public Result refreshCache() {
        configService.resetConfigCache();
        return Result.success();
    }

}
