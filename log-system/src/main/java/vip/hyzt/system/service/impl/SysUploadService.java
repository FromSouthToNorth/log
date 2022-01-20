package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vip.hyzt.common.upload.IUploadService;
import java.util.Map;

import static vip.hyzt.common.enums.UploadModeEnum.getStrategy;

/**
 * 上传业务服务
 * @author hy
 */
@Service
public class SysUploadService {

    @Value("${upload.mode}")
    private String uploadMode;

    @Autowired
    private Map<String, IUploadService> uploadServiceMap;

    /**
     * 上传文件
     * @param file 文件
     * @param path 路径
     * @return {@link String} 文件地址
     */
    public String executeUpload(MultipartFile file, String path) {
        return uploadServiceMap.get(getStrategy(uploadMode)).uploadFile(file, path);
    }
}
