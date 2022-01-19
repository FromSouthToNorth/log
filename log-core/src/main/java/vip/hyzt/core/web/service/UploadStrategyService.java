package vip.hyzt.core.web.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 上传文件业务服务
 * @author hy
 */
@Service
public class UploadStrategyService {

    @Value("${upload.mode}")
    private String uploadMode;

}
