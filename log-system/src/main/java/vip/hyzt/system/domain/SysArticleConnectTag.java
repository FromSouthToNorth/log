package vip.hyzt.system.domain;

/**
 * 文章标签关联信息
 * @author hy
 */
public class SysArticleConnectTag {

    /** 主键id */
    private String id;

    /** 标签主键 */
    private String tagId;

    /** 文章主键 */
    private String articleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
