package vip.hyzt.system.domain;

import java.util.Arrays;
import java.util.List;

/**
 * 文章实体类
 * @author hy
 */
public class SysArticle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String articleId;

    /** 文章标题 */
    private String articleTitle;

    /** 作者id */
    private String userId;

    /** 内容 */
    private String articleContent;

    /** 文章封面图 */
    private String articleCover;

    /** 文章分类 */
    private String typeId;

    /** 是否置顶 0否 1是 */
    private String isTop;

    /** 文章类型 1原创 2转载 */
    private String type;

    /** 状态值 1公开 2私密 */
    private String status;

    /** 删除标志（0 代表存在 1 代表删除） */
    private String delFlag;

    /** 用户 (文章作者) */
    private SysUser user;

    /** 文章类型 */
    private SysArticleType articleType;

    /** 文章标签集 */
    private List<SysArticleTag> tags;

    private String[] tagIds;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(SysArticleType articleType) {
        this.articleType = articleType;
    }

    public List<SysArticleTag> getTags() {
        return tags;
    }

    public void setTags(List<SysArticleTag> tags) {
        this.tags = tags;
    }

    public String[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(String[] tagIds) {
        this.tagIds = tagIds;
    }

    @Override
    public String toString() {
        return "SysArticle{" +
                "articleId='" + articleId + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", userId='" + userId + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articleCover='" + articleCover + '\'' +
                ", typeId='" + typeId + '\'' +
                ", isTop='" + isTop + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", user=" + user +
                ", articleType=" + articleType +
                ", tags=" + tags +
                ", tagIds=" + Arrays.toString(tagIds) +
                '}';
    }
}
