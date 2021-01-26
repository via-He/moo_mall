package com.via.mall.mapper;

import com.via.mall.domain.ImoocMallItem;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
public interface ImoocMallItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImoocMallItem record);

    int insertSelective(ImoocMallItem record);

    ImoocMallItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImoocMallItem record);

    int updateByPrimaryKey(ImoocMallItem record);
}