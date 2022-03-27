package vip.hyzt.api.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.web.controller.BaseController;
import vip.hyzt.system.service.ISysConfigService;

/**
 * 网页配置设置接口
 * @author hy
 */
@RestController
@RequestMapping("/home/config")
public class SysHomeConfigController extends BaseController {

    @Autowired
    private ISysConfigService configService;

    /**
     * 查询网页是否启用黑白模式
     */
    @GetMapping("/blackAndWhite")
    public Result blackAndWhiteConfig() {
        return Result.success(configService.blackAndWhiteConfig());
    }

}
