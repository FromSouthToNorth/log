package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.exception.ServiceException;
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

    /**
     * 根据标签标签查询标签信息
     * @param tagId 标签 ID
     * @return 标签标签信息
     */
    @Override
    public SysTag selectTagById(String tagId) {
        return tagMapper.selectTagById(tagId);
    }

    /**
     * 新增标签标签
     * @param tag 标签标签信息
     * @return 结果
     */
    @Override
    public int insertTag(SysTag tag) {
        return tagMapper.insertTag(tag);
    }

    /**
     * 根据标签标签编号编辑标签标签
     * @param tag 标签标签信息
     * @return 结果
     */
    @Override
    public int updateTagById(SysTag tag) {
        return tagMapper.updateTagById(tag);
    }

    /**
     * 根据标签标签编号批量删除标签标签
     * @param tagIds 删除的标签标签编号
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTagByIds(String[] tagIds) {
        for (String tagId : tagIds) {
            SysTag tag = selectTagById(tagId);
            if (countTagByTagId(tagId) > 0) {
                throw new ServiceException(String.format("%1$s 标签已分配,不能删除", tag.getTagName()));
            }
        }
        return tagMapper.deleteTagByIds(tagIds);
    }

    /**
     * 校验标签标签名称是否唯一
     * @param tag 标签标签信息
     * @return 结果
     */
    @Override
    public String checkTagNameUnique(SysTag tag) {
        String tagId = ObjectUtils.isEmpty(tag) ? "-1" : tag.getTagId();
        SysTag tagInfo = tagMapper.checkTagNameUnique(tag.getTagName());
        if (!ObjectUtils.isEmpty(tagInfo) && tagInfo.getTagId().equals(tagId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 根据标签标签编号查询标签标签使用量
     * @param tagId 标签标签编号
     * @return 结果
     */
    @Override
    public int countTagByTagId(String tagId) {
        return tagMapper.countTagByTagId(tagId);
    }
}
