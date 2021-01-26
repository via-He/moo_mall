package com.via.mall.service.impl;

import com.via.mall.common.ApiRestResponse;
import com.via.mall.exception.MallException;
import com.via.mall.exception.MallExceptionEnum;
import com.via.mall.util.MD5Utils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.via.mall.domain.ImoocMallUser;
import com.via.mall.mapper.ImoocMallUserMapper;
import com.via.mall.service.ImoocMallUserService;

import java.security.NoSuchAlgorithmException;

/**
*@author Qinging.He
*@date 2020/12/25 16:13
*/
@Service
public class ImoocMallUserServiceImpl implements ImoocMallUserService{

    @Resource
    private ImoocMallUserMapper imoocMallUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return imoocMallUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ImoocMallUser record) {
        return imoocMallUserMapper.insert(record);
    }

    @Override
    public int insertSelective(ImoocMallUser record) {
        return imoocMallUserMapper.insertSelective(record);
    }

    @Override
    public ImoocMallUser selectByPrimaryKey(Integer id) {
        return imoocMallUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ImoocMallUser record) {
        return imoocMallUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ImoocMallUser record) {
        return imoocMallUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public void register(String userName, String password) throws MallException {
        ImoocMallUser result = imoocMallUserMapper.selectByName(userName);
        if (result != null){
            throw new MallException(MallExceptionEnum.NAME_EXISTED);

        }

        //写入数据库
        //新建user
        ImoocMallUser user = new ImoocMallUser();
        user.setUsername(userName);
        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int count = imoocMallUserMapper.insertSelective(user);
        if (count == 0){
            throw new MallException(MallExceptionEnum.INSERT_FAILED);
        }

    }

    @Override
    public ImoocMallUser login(String userName, String password) throws MallException {

        String md5Password = null;
        try {
            md5Password = MD5Utils.getMD5Str(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        ImoocMallUser user = imoocMallUserMapper.selectLogin(userName,md5Password);
        if (user == null){
            throw new MallException(MallExceptionEnum.WRONG_PASSWORD);
        }

        return user;
    }

    @Override
    public void updateInformation(ImoocMallUser user) throws MallException {
        int updateCount = imoocMallUserMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 1){
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public boolean checkAdminRole(ImoocMallUser user){
        //1普通用户 2管理员
        return user.getRole().equals(2);
    }

}
