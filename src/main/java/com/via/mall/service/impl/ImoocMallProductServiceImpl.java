package com.via.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.via.mall.common.Constant;
import com.via.mall.exception.MallException;
import com.via.mall.exception.MallExceptionEnum;
import com.via.mall.query.ProductListQuery;
import com.via.mall.request.AddProductReq;
import com.via.mall.request.ProductListReq;
import com.via.mall.request.UpdateProductReq;
import com.via.mall.service.ImoocMallCategoryService;
import com.via.mall.vo.ImoocMallCategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.via.mall.mapper.ImoocMallProductMapper;
import com.via.mall.domain.ImoocMallProduct;
import com.via.mall.service.ImoocMallProductService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
*@author Qinging.He
*@date 2020/12/25 16:13
*/
@Service
public class ImoocMallProductServiceImpl implements ImoocMallProductService {

    @Resource
    private ImoocMallProductMapper imoocMallProductMapper;

    @Resource
    private ImoocMallCategoryService categoryService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        ImoocMallProduct product = imoocMallProductMapper.selectByPrimaryKey(id);
        if (product == null) {
            throw new MallException(MallExceptionEnum.DELETE_FAILED);
        }
        int count = imoocMallProductMapper.deleteByPrimaryKey(id);
        if (count == 0){
            throw new MallException(MallExceptionEnum.DELETE_FAILED);
        }
        return count;
    }

    @Override
    public int insert(ImoocMallProduct record) {
        return imoocMallProductMapper.insert(record);
    }

    @Override
    public int insertSelective(ImoocMallProduct record) {
        return imoocMallProductMapper.insertSelective(record);
    }

    @Override
    public ImoocMallProduct selectByPrimaryKey(Integer id) {
        return imoocMallProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ImoocMallProduct record) {
        return imoocMallProductMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ImoocMallProduct record) {
        return imoocMallProductMapper.updateByPrimaryKey(record);
    }

    @Override
    public void add(AddProductReq addProductReq) {
        ImoocMallProduct product = new ImoocMallProduct();
        BeanUtils.copyProperties(addProductReq, product);
        ImoocMallProduct productOld = imoocMallProductMapper.selectByName(addProductReq.getName());
        if (productOld != null) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);

        }
        int count = imoocMallProductMapper.insertSelective(product);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.CREATE_FAILED);
        }

    }

    @Override
    public void update(ImoocMallProduct product){
        if (product.getName() != null) {
            ImoocMallProduct productOld = imoocMallProductMapper.selectByName(product.getName());
            if (productOld != null && !productOld.getId().equals(product.getId())) {
                throw new MallException(MallExceptionEnum.NAME_EXISTED);
            }
        }
        int count = imoocMallProductMapper.updateByPrimaryKeySelective(product);
        if (count == 0){
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        }

    }

    @Override
    public void batchUpdateSellStatus(Integer[] ids,Integer sellStatus){
        imoocMallProductMapper.batchUpdateSellStatus(ids,sellStatus);
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ImoocMallProduct> products = imoocMallProductMapper.selectListForAdmin();

        PageInfo<ImoocMallProduct> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }

    @Override
    public PageInfo list(ProductListReq productListReq){
        ProductListQuery productListQuery = new ProductListQuery();

        //搜索处理：判空、拼接
        if (!StringUtils.isEmpty(productListReq.getKeyword())) {
            String keyword = new StringBuilder().append("%")
                .append(productListReq.getKeyword()).append("%").toString();
            productListQuery.setKeyword(keyword);

        }
        //目录处理：如果查目录下得商品，需要将该目录下得所有子目录查询，需要拿到一个目录得List
        if (productListReq.getCategoryId() != null){
            List<ImoocMallCategoryVO> categoryVOS = categoryService.
                listCategoryForCustomer(productListReq.getCategoryId());
            ArrayList<Integer> categoryIds = new ArrayList<>();
            categoryIds.add(productListReq.getCategoryId());
            getCategoryIds(categoryVOS,categoryIds);
            productListQuery.setCategoryIds(categoryIds);
        }
        //排序处理
        String orderBy = productListReq.getOrderBy();
        if (Constant.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
            PageHelper.startPage(productListReq.getPageNum(),productListReq.getPageSize(),orderBy);
        }else {
            PageHelper.startPage(productListReq.getPageNum(),productListReq.getPageSize());
        }


        List<ImoocMallProduct> productList = imoocMallProductMapper.selectList(productListQuery);
        PageInfo pageInfo = new PageInfo(productList);
        return pageInfo;
    }

    private void getCategoryIds(List<ImoocMallCategoryVO> categoryVOList,ArrayList<Integer> categoryIds){
        for (int i = 0; i < categoryVOList.size(); i++) {
            ImoocMallCategoryVO categoryVO = categoryVOList.get(i);
            if (categoryVO != null) {
                categoryIds.add(categoryVO.getId());
                getCategoryIds(categoryVO.getChildCategory(),categoryIds);
            }
        }
    }
}
