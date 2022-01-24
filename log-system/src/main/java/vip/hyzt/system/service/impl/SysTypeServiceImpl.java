package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.exception.ServiceException;
import vip.hyzt.system.domain.SysType;
import vip.hyzt.system.mapper.SysTypeMapper;
import vip.hyzt.system.service.ISysTypeService;

import java.util.List;

/**
 * 文章分类业务服务实现
 * @author hy
 */
@Service
public class SysTypeServiceImpl implements ISysTypeService {

    @Autowired
    private SysTypeMapper typeMapper;

    /**
     * 查询文章分类列表
     * @param type 查询分类参数
     * @return 文章分类结果集
     */
    @Override
    public List<SysType> selectTypeList(SysType type) {
        return typeMapper.selectTypeList(type);
    }

    /**
     * 查询所以文章分类
     * @return 文章分类结果集
     */
    @Override
    public List<SysType> selectTypeAll() {
        return typeMapper.selectTypeAll();
    }

    /**
     * 根据文章类型查询类型信息
     * @param typeId 类型 ID
     * @return 文章类型信息
     */
    @Override
    public SysType selectTypeById(String typeId) {
        return typeMapper.selectTypeById(typeId);
    }

    /**
     * 新增文章类型
     * @param type 文章类型信息
     * @return 结果
     */
    @Override
    public int insertType(SysType type) {
        return typeMapper.insertType(type);
    }

    /**
     * 根据文章类型编号编辑文章类型
     * @param type 文章类型信息
     * @return 结果
     */
    @Override
    public int updateTypeById(SysType type) {
        return typeMapper.updateTypeById(type);
    }

    /**
     * 根据文章类型编号批量删除文章类型
     * @param typeIds 删除的文章类型编号
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteTypeByIds(String[] typeIds) {
        for (String typeId : typeIds) {
            SysType type = selectTypeById(typeId);
            if (countTypeByTypeId(typeId) > 0) {
                throw new ServiceException(String.format("%1$s 类型已分配,不能删除", type.getTypeName()));
            }
        }
        return typeMapper.deleteTypeByIds(typeIds);
    }

    /**
     * 校验文章类型名称是否唯一
     * @param type 文章类型信息
     * @return 文章类型信息
     */
    @Override
    public String checkTypeNameUnique(SysType type) {
        String typeId = ObjectUtils.isEmpty(type) ? "-1" : type.getTypeId();
        SysType typeInfo = typeMapper.checkTypeNameUnique(type.getTypeName());
        if (!ObjectUtils.isEmpty(typeInfo) && typeInfo.getTypeId().equals(typeId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int countTypeByTypeId(String typeId) {
        return typeMapper.countTypeByTypeId(typeId);
    }
}
