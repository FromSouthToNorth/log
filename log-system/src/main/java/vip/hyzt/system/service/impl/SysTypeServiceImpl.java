package vip.hyzt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
