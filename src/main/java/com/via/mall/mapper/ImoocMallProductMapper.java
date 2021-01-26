package com.via.mall.mapper;

import com.via.mall.domain.ImoocMallProduct;
import com.via.mall.query.ProductListQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
public interface ImoocMallProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImoocMallProduct record);

    int insertSelective(ImoocMallProduct record);

    ImoocMallProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImoocMallProduct record);

    int updateByPrimaryKey(ImoocMallProduct record);

    ImoocMallProduct selectByName(String name);

    int batchUpdateSellStatus(@Param("ids") Integer[] ids, @Param("sellStatus") Integer sellStatus);

    List<ImoocMallProduct> selectListForAdmin();

    List<ImoocMallProduct> selectList(@Param("query") ProductListQuery query);
}