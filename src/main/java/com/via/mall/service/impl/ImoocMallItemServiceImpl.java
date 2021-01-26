package com.via.mall.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.via.mall.mapper.ImoocMallItemMapper;
import com.via.mall.domain.ImoocMallItem;
import com.via.mall.service.ImoocMallItemService;

/**
*@author Qinging.He
*@date 2020/12/25 16:13
*/
@Service
public class ImoocMallItemServiceImpl implements ImoocMallItemService{

    @Resource
    private ImoocMallItemMapper imoocMallItemMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return imoocMallItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ImoocMallItem record) {
        return imoocMallItemMapper.insert(record);
    }

    @Override
    public int insertSelective(ImoocMallItem record) {
        return imoocMallItemMapper.insertSelective(record);
    }

    @Override
    public ImoocMallItem selectByPrimaryKey(Integer id) {
        return imoocMallItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ImoocMallItem record) {
        return imoocMallItemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ImoocMallItem record) {
        return imoocMallItemMapper.updateByPrimaryKey(record);
    }

}
