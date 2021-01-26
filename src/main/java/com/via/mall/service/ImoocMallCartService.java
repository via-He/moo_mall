package com.via.mall.service;

import com.via.mall.domain.ImoocMallCart;
import com.via.mall.vo.CartVO;

import java.util.List;

/**
*@author Qinging.He
*@date 2020/12/25 16:13
*/
public interface ImoocMallCartService{


    int deleteByPrimaryKey(Integer id);

    int insert(ImoocMallCart record);

    int insertSelective(ImoocMallCart record);

    ImoocMallCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImoocMallCart record);

    int updateByPrimaryKey(ImoocMallCart record);

    List<CartVO> list(Integer userId);

    List<CartVO> add(Integer userId, Integer productId, Integer count);

    List<CartVO> update(Integer userId, Integer productId, Integer count);

    List<CartVO> delete(Integer userId, Integer productId);

    List<CartVO> selectOrNot(Integer userId, Integer productId, Integer selected);

    List<CartVO> selectAllOrNot(Integer userId, Integer selected);
}
