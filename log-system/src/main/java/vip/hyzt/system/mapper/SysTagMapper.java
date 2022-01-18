package vip.hyzt.system.mapper;

import vip.hyzt.system.domain.SysTag;

import java.util.List;

/**
 * 文章标签持久层
 * @author hy
 */
public interface SysTagMapper {

    /**
     * 查询文章标签列表
     * @param tag 标签查询参数
     * @return 文章标签结果集
     */
    List<SysTag> selectTagList(SysTag tag);

}
