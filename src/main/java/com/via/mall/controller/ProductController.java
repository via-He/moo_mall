package com.via.mall.controller;

import com.github.pagehelper.PageInfo;
import com.via.mall.common.ApiRestResponse;
import com.via.mall.domain.ImoocMallProduct;
import com.via.mall.request.ProductListReq;
import com.via.mall.service.ImoocMallProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Qingqing.He
 * @date 2021/1/11 16:30
 * 前台商品
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    ImoocMallProductService productService;

    @ApiOperation("商品详情")
    @GetMapping("/detail")
    public ApiRestResponse detail(@RequestParam Integer id){
        ImoocMallProduct productDetail = productService.selectByPrimaryKey(id);
        return ApiRestResponse.success(productDetail);

    }


    @ApiOperation("商品列表")
    @PostMapping("/list")
    public ApiRestResponse list(ProductListReq productListReq){
        PageInfo list = productService.list(productListReq);
        return ApiRestResponse.success(list);

    }
}
