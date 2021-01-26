package com.via.mall.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.via.mall.domain.ImoocMallOrder;
import com.via.mall.mapper.ImoocMallOrderMapper;
import com.via.mall.service.ImoocMallOrderService;

/**
*@author Qingqing.He
*@date 2020/12/25 16:13
*/
@Service
public class ImoocMallOrderServiceImpl implements ImoocMallOrderService{

    @Resource
    private ImoocMallOrderMapper imoocMallOrderMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return imoocMallOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ImoocMallOrder record) {
        return imoocMallOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(ImoocMallOrder record) {
        return imoocMallOrderMapper.insertSelective(record);
    }

    @Override
    public ImoocMallOrder selectByPrimaryKey(Integer id) {
        return imoocMallOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ImoocMallOrder record) {
        return imoocMallOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ImoocMallOrder record) {
        return imoocMallOrderMapper.updateByPrimaryKey(record);
    }

}
