package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.hyzt.common.constant.UserConstants;
import vip.hyzt.common.exception.ServiceException;
import vip.hyzt.common.utils.StringUtils;
import vip.hyzt.common.utils.uuid.IdUtils;
import vip.hyzt.system.domain.SysDictData;
import vip.hyzt.system.domain.SysDictType;
import vip.hyzt.system.mapper.SysDictDataMapper;
import vip.hyzt.system.mapper.SysDictTypeMapper;
import vip.hyzt.system.service.ISysDictTypeService;
import vip.hyzt.system.util.DictUtils;

import java.util.List;

/**
 * 字典 业务层处理
 *
 * @author hy
 * @since 2021/11/02
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        List<SysDictData> dictList = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotEmpty(dictList)) {
            return dictList;
        }
        dictList = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictList)) {
            DictUtils.setDictCache(dictType, dictList);
            return dictList;
        }
        return null;
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(String dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    @Override
    public SysDictType selectDictTypeByType(String dictType) {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    @Override
    public void deleteDictTypeByIds(String[] dictIds) {
        for (String dictId : dictIds) {
            SysDictType dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
            dictTypeMapper.deleteDictTypeById(dictId);
            DictUtils.removeDictCache(dictType.getDictType());
        }
    }

    /**
     * 加载字典缓存数据
     */
    @Override
    public void loadingDictCache() {
        List<SysDictType> dictTypeList = dictTypeMapper.selectDictTypeAll();
        for (SysDictType dictType : dictTypeList) {
            List<SysDictData> dictList = dictDataMapper.selectDictDataByType(dictType.getDictType());
            DictUtils.setDictCache(dictType.getDictType(), dictList);
        }
    }

    /**
     * 清空字典缓存数据
     */
    @Override
    public void clearDictCache() {
        DictUtils.clearDictCache();
    }

    /**
     * 重置字典缓存数据
     */
    @Override
    public void resetDictCache() {
        clearDictCache();
        loadingDictCache();
    }

    /**
     * 新增保存字典类型信息
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dictType) {
        dictType.setDictId(IdUtils.simpleUUID());
        int row = dictTypeMapper.insertDictType(dictType);
        if (row > 0) {
            DictUtils.setDictCache(dictType.getDictType(), null);
        }
        return row;
    }

    /**
     * 修改保存字典类型信息
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDictType(SysDictType dictType) {
        SysDictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        int row = dictTypeMapper.updateDictType(dictType);
        if (row > 0) {
            List<SysDictData> dictDataList = dictDataMapper.selectDictDataByType(dictType.getDictType());
            DictUtils.setDictCache(dictType.getDictType(), dictDataList);
        }
        return row;
    }

    /**
     * 校验字典类型称是否唯一
     * @param dictType 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dictType) {
        String dictId = StringUtils.isNull(dictType.getDictId()) ? "" : dictType.getDictId();
        SysDictType dict = dictTypeMapper.checkDictTypeUnique(dictType.getDictType());
        if (StringUtils.isNotNull(dict) && dict.getDictId().equals(dictId))
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

}
