package com.via.mall.service;

import com.via.mall.domain.ImoocMallOrder;
    
/**
*@author Qinging.He
*@date 2020/12/25 16:13
*/
public interface ImoocMallOrderService{


    int deleteByPrimaryKey(Integer id);

    int insert(ImoocMallOrder record);

    int insertSelective(ImoocMallOrder record);

    ImoocMallOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImoocMallOrder record);

    int updateByPrimaryKey(ImoocMallOrder record);

}
