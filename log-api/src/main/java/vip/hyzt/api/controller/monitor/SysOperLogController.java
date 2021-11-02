package vip.hyzt.api.controller.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vip.hyzt.common.annotation.Log;
import vip.hyzt.common.core.page.TableDataInfo;
import vip.hyzt.common.enums.BusinessType;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.web.controller.BaseController;
import vip.hyzt.system.domain.SysOperLog;
import vip.hyzt.system.service.ISysOperLogService;

import java.util.List;

/**
 * 在线用户监控
 * @author hy
 * @since 2021/11/02
 */
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperLogController extends BaseController {

    @Autowired
    private ISysOperLogService operLogService;

    @PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOperLog operLog) {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLog(operLog);
        return getDataTable(list);
    }

    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping("/{operIds}")
    public Result remove(@PathVariable String[] operIds) {
        return toAjax(operLogService.deleteOperLogIds(operIds));
    }

    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping("/clean")
    public Result clean() {
        operLogService.cleanOperLog();
        return Result.success();
    }

}
