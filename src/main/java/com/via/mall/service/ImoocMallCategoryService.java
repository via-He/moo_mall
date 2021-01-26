package com.via.mall.service;

import com.github.pagehelper.PageInfo;
import com.via.mall.domain.ImoocMallCategory;
import com.via.mall.request.AddCategoryReq;
import com.via.mall.vo.ImoocMallCategoryVO;

import java.util.List;

/**
*@author Qinging.He
*@date 2020/12/25 16:13
*/
public interface ImoocMallCategoryService{


    int deleteByPrimaryKey(Integer id);

    int insert(ImoocMallCategory record);

    int insertSelective(ImoocMallCategory record);

    ImoocMallCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImoocMallCategory record);

    int updateByPrimaryKey(ImoocMallCategory record);

    void add(AddCategoryReq addCategoryReq);

    void update(ImoocMallCategory updateCategory);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    List<ImoocMallCategoryVO> listCategoryForCustomer(Integer parentId);
}
