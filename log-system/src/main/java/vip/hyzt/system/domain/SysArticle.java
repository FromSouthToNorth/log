package vip.hyzt.system.domain;

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
    private int isTop;

    /** 文章类型 1原创 2转载 */
    private int type;

    /** 状态值 1公开 2私密 */
    private int status;

    /** 数据范围（1：全部数据权限 2: 自定义数据权限 3：仅自己数据权限） */
    private int dataScope;

    /** 删除标志（0 代表存在 1 代表删除） */
    private int delFlag;

    /** 用户 (文章作者) */
    private SysUser user;

    /** 文章类型 */
    private SysType articleType;

    /** 文章标签集 */
    private List<SysTag> tags;

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

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDataScope() {
        return dataScope;
    }

    public void setDataScope(int dataScope) {
        this.dataScope = dataScope;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysType getArticleType() {
        return articleType;
    }

    public void setArticleType(SysType articleType) {
        this.articleType = articleType;
    }

    public List<SysTag> getTags() {
        return tags;
    }

    public void setTags(List<SysTag> tags) {
        this.tags = tags;
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
                ", isTop=" + isTop +
                ", type=" + type +
                ", status=" + status +
                ", dataScope=" + dataScope +
                ", delFlag=" + delFlag +
                ", user=" + user +
                ", articleType=" + articleType +
                ", tags=" + tags +
                '}';
    }
}
