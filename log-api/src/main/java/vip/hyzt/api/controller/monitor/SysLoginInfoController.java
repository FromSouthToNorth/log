package vip.hyzt.api.controller.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vip.hyzt.common.annotation.Log;
import vip.hyzt.common.core.page.TableDataInfo;
import vip.hyzt.common.enums.BusinessType;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.web.controller.BaseController;
import vip.hyzt.system.domain.SysLoginInfo;
import vip.hyzt.system.service.ISysLoginInfoService;

import java.util.List;

/**
 * 登录日志
 * @author hy
 */
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLoginInfoController extends BaseController {

    @Autowired
    private ISysLoginInfoService loginInfoService;

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLoginInfo loginInfo) {
        startPage();
        List<SysLoginInfo> list = loginInfoService.selectLoginInfoList(loginInfo);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public Result remove(@PathVariable String[] infoIds) {
        return toAjax(loginInfoService.deleteLoginInfoIds(infoIds));
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public Result clean() {
        loginInfoService.cleanLoginInfo();
        return Result.success();
    }

}
