package com.via.mall.mapper;

import com.via.mall.domain.ImoocMallCategory;

import java.util.List;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
public interface ImoocMallCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImoocMallCategory record);

    int insertSelective(ImoocMallCategory record);

    ImoocMallCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImoocMallCategory record);

    int updateByPrimaryKey(ImoocMallCategory record);

    ImoocMallCategory selectByName(String name);

    List<ImoocMallCategory> selectList();

    List<ImoocMallCategory> selectCategoriesByParentId(Integer parentId);
}