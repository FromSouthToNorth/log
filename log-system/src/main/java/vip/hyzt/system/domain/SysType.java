package vip.hyzt.system.domain;

/**
 * 文章类型实体类
 * @author hy
 */
public class SysType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String typeId;

    /** 文章标签名称 */
    private String typeName;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "SysType{" +
                "typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
