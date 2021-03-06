package vip.hyzt.common.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vip.hyzt.common.exception.ServiceException;
import vip.hyzt.common.utils.file.FileUtils;

import static vip.hyzt.common.utils.file.FileUtils.extractFilename;
import static vip.hyzt.common.utils.file.FileUtils.getExtension;

import java.io.IOException;
import java.io.InputStream;

/**
 * 抽象上传模板
 * @author hy
 */
@Service
public abstract class AbstractUploadService implements IUploadService {

    @Override
    public String uploadFile(MultipartFile file, String path) {
        try {
            String fileName = extractFilename(file);
            if (!exists(path + fileName)) {
                upload(path, fileName, file.getInputStream());
            }
            return getFileAccessUrl(path + fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("对不起，上传文件失败");
        }
    }

    /**
     * 判断文件是否存在
     * @param filePath 文件路径
     * @return {@link Boolean}
     */
    public abstract Boolean exists(String filePath);

    /**
     * 上传
     * @param path        路径
     * @param fileName    文件名
     * @param inputStream 输入流
     * @throws IOException io异常
     */
    public abstract void upload(String path, String fileName, InputStream inputStream) throws IOException;

    /**
     * 获取文件访问url
     * @param filePath 文件路径
     * @return {@link String}
     */
    public abstract String getFileAccessUrl(String filePath);
}
