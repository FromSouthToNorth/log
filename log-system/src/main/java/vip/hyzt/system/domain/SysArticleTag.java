package vip.hyzt.system.domain;

/**
 * 文章标签实体类
 * @author hy
 */
public class SysArticleTag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String tagId;

    /** 文章标签名称 */
    private String tagName;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "SysTag{" +
                "tagId='" + tagId + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
