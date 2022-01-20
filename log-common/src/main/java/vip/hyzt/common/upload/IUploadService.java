package vip.hyzt.common.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件业务服务
 * @author hy
 */
public interface IUploadService {

    /**
     * 上传文件
     * @param file 文件
     * @param path 上传路径
     * @return 文件地址
     */
    String uploadFile(MultipartFile file, String path);

}
