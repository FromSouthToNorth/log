package vip.hyzt.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云 OSS 配置
 * @author hy
 */
@Component
@ConfigurationProperties(prefix = "upload.oss")
public class OssConfigProperties {

    /** oss域名 */
    private String url;

    /** 终点 */
    private String endpoint;

    /** 访问密钥id */
    private String accessKyeId;

    /** 访问密钥密码 */
    private String accessKeySecret;

    /** bucket名称 */
    private String bucketName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKyeId() {
        return accessKyeId;
    }

    public void setAccessKyeId(String accessKyeId) {
        this.accessKyeId = accessKyeId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
