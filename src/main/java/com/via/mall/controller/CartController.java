package com.via.mall.controller;

import com.via.mall.common.ApiRestResponse;
import com.via.mall.filter.UserFilter;
import com.via.mall.service.ImoocMallCartService;
import com.via.mall.vo.CartVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Qingqing.He
 * @date 2021/1/13 11:03
 * 购物车
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    ImoocMallCartService cartService;

    @GetMapping("/list")
    public ApiRestResponse list(){

        //内部 获取userId，防止横向越权
        Integer id = UserFilter.currentUser.getId();
        List<CartVO> cartVOList = cartService.list(id);
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    public ApiRestResponse add(@RequestParam Integer productId,@RequestParam Integer count){

        List<CartVO> cartVOList = cartService.add(UserFilter.currentUser.getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }
    @ApiOperation("更新购物车")
    @PostMapping("/update")
    public ApiRestResponse update(@RequestParam Integer productId,@RequestParam Integer count){

        List<CartVO> cartVOList = cartService.update(UserFilter.currentUser.getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("删除保护购物车")
    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam Integer productId){

        List<CartVO> cartVOList = cartService.delete(UserFilter.currentUser.getId(), productId);
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("选中购物车的商品")
    @PostMapping("/select")
    public ApiRestResponse select(@RequestParam Integer productId,@RequestParam Integer selected){

        List<CartVO> cartVOList = cartService.selectOrNot(UserFilter.currentUser.getId(), productId,selected);
        return ApiRestResponse.success(cartVOList);
    }
    @ApiOperation("全选中/全不选中购物车的商品")
    @PostMapping("/selectAll")
    public ApiRestResponse selectAll(@RequestParam Integer selected){

        List<CartVO> cartVOList = cartService.selectAllOrNot(UserFilter.currentUser.getId(), selected);
        return ApiRestResponse.success(cartVOList);
    }
}
