package com.via.mall.service;

import com.via.mall.domain.ImoocMallUser;
import com.via.mall.exception.MallException;

/**
*@author Qinging.He
*@date 2020/12/25 16:13
*/
public interface ImoocMallUserService{


    int deleteByPrimaryKey(Integer id);

    int insert(ImoocMallUser record);

    int insertSelective(ImoocMallUser record);

    ImoocMallUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImoocMallUser record);

    int updateByPrimaryKey(ImoocMallUser record);

    void register(String userName,String password) throws MallException;

    ImoocMallUser login(String userName, String password) throws MallException;

    void updateInformation(ImoocMallUser user) throws MallException;

    boolean checkAdminRole(ImoocMallUser user);
}
