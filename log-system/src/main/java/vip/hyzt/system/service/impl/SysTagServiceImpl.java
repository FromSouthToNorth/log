package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.hyzt.system.domain.SysTag;
import vip.hyzt.system.mapper.SysTagMapper;
import vip.hyzt.system.service.ISysTagService;

import java.util.List;

/**
 * 文章标签业务服务实现
 * @author hy
 */
@Service
public class SysTagServiceImpl implements ISysTagService {

    @Autowired
    private SysTagMapper tagMapper;

    /**
     * 查询文章标签列表
     * @param tag 标签查询参数
     * @return 文章标签结果集
     */
    @Override
    public List<SysTag> selectTagList(SysTag tag) {
        return tagMapper.selectTagList(tag);
    }

    /**
     * 查询所有文章标签
     * @return 文章标签列表
     */
    @Override
    public List<SysTag> selectTagAll() {
        return tagMapper.selectTagAll();
    }

    /**
     * 根据文章 ID 获取标签选择框列表
     * @param articleId 文章 ID
     * @return 选中的标签 ID 列表
     */
    @Override
    public List<String> selectTagListByArticleId(String articleId) {
        return tagMapper.selectTagListByArticleId(articleId);
    }
}
