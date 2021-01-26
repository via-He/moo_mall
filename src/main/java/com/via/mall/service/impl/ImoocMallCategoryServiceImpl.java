package com.via.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.via.mall.controller.CategoryController;
import com.via.mall.exception.MallException;
import com.via.mall.exception.MallExceptionEnum;
import com.via.mall.request.AddCategoryReq;
import com.via.mall.vo.ImoocMallCategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.via.mall.mapper.ImoocMallCategoryMapper;
import com.via.mall.domain.ImoocMallCategory;
import com.via.mall.service.ImoocMallCategoryService;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
*@author Qingqing.He
*@date 2020/12/25 16:13
*/
@Service
public class ImoocMallCategoryServiceImpl implements ImoocMallCategoryService{

    @Resource
    private ImoocMallCategoryMapper imoocMallCategoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        ImoocMallCategory categoryOld = imoocMallCategoryMapper.selectByPrimaryKey(id);
        //查不到记录，无法删除
        if (categoryOld == null){
            throw new MallException(MallExceptionEnum.DELETE_FAILED);
        }
        int count = imoocMallCategoryMapper.deleteByPrimaryKey(id);
        if (count == 0){
            throw new MallException(MallExceptionEnum.DELETE_FAILED);
        }
        return count;
    }

    @Override
    public int insert(ImoocMallCategory record) {
        return imoocMallCategoryMapper.insert(record);
    }

    @Override
    public int insertSelective(ImoocMallCategory record) {
        return imoocMallCategoryMapper.insertSelective(record);
    }

    @Override
    public ImoocMallCategory selectByPrimaryKey(Integer id) {
        return imoocMallCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ImoocMallCategory record) {
        return imoocMallCategoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ImoocMallCategory record) {
        return imoocMallCategoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public void add(AddCategoryReq addCategoryReq){
        ImoocMallCategory category = new ImoocMallCategory();
        BeanUtils.copyProperties(addCategoryReq,category);
        ImoocMallCategory categoryOld = imoocMallCategoryMapper.selectByName(addCategoryReq.getName());
        if (categoryOld != null){
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }
        int count = imoocMallCategoryMapper.insertSelective(category);
        if (count == 0){
            throw new MallException(MallExceptionEnum.CREATE_FAILED);
        }

    }

    @Override
    public void update(ImoocMallCategory updateCategory){
        if (updateCategory.getName() != null){
            ImoocMallCategory categoryOld =imoocMallCategoryMapper.selectByName(updateCategory.getName());
            if (categoryOld != null && !categoryOld.getId().equals(updateCategory.getId())){
                throw new MallException(MallExceptionEnum.NAME_EXISTED);
            }
        }
        int count = imoocMallCategoryMapper.updateByPrimaryKeySelective(updateCategory);
        if (count == 0){
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        }
    }


    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize,"type,order_num");
        List<ImoocMallCategory> categoryList = imoocMallCategoryMapper.selectList();
        PageInfo<ImoocMallCategory> pageInfo = new PageInfo<>(categoryList);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "listCategoryForCustomer")
    public List<ImoocMallCategoryVO> listCategoryForCustomer(Integer parentId){
        ArrayList<ImoocMallCategoryVO> categoryVOArrayList = new ArrayList<ImoocMallCategoryVO>();
        recursivelyFindCategories(categoryVOArrayList,parentId);
        return categoryVOArrayList;
    }

    private void recursivelyFindCategories(List<ImoocMallCategoryVO> categoryVOArrayList,Integer parentId){
        //递归获取所有子类别，并组合成为一个“目录树”
        List<ImoocMallCategory> categoryList = imoocMallCategoryMapper.selectCategoriesByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)){
            /*for (int i = 0; i < categoryList.size(); i++) {
                ImoocMallCategory category = categoryList.get(i);
                ImoocMallCategoryVO categoryVO = new ImoocMallCategoryVO();
                BeanUtils.copyProperties(category,categoryVO);
                categoryVOArrayList.add(categoryVO);
                recursivelyFindCategories(categoryVO.getChildCategory(),categoryVO.getId());
            }*/

            categoryList.forEach(category ->{
                ImoocMallCategoryVO categoryVO = new ImoocMallCategoryVO();
                BeanUtils.copyProperties(category,categoryVO);
                categoryVOArrayList.add(categoryVO);
                recursivelyFindCategories(categoryVO.getChildCategory(),categoryVO.getId());

            });
        }

    }
}
