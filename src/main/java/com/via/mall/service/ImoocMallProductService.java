package com.via.mall.service;

import com.github.pagehelper.PageInfo;
import com.via.mall.domain.ImoocMallProduct;
import com.via.mall.request.AddProductReq;
import com.via.mall.request.ProductListReq;
import com.via.mall.vo.ImoocMallCategoryVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

/**
*@author Qinging.He
*@date 2020/12/25 16:13
*/
public interface ImoocMallProductService{


    int deleteByPrimaryKey(Integer id);

    int insert(ImoocMallProduct record);

    int insertSelective(ImoocMallProduct record);

    ImoocMallProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImoocMallProduct record);

    int updateByPrimaryKey(ImoocMallProduct record);

    void add(AddProductReq addProductReq);

    void update(ImoocMallProduct product);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    PageInfo list(ProductListReq productListReq);
}
