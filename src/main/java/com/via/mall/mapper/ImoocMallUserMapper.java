package com.via.mall.mapper;

import com.via.mall.domain.ImoocMallUser;
import org.apache.ibatis.annotations.Param;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
public interface ImoocMallUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImoocMallUser record);

    int insertSelective(ImoocMallUser record);

    ImoocMallUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImoocMallUser record);

    int updateByPrimaryKey(ImoocMallUser record);

    ImoocMallUser selectByName(String userName);

    ImoocMallUser selectLogin(@Param("userName") String userName, @Param("password") String password);
}