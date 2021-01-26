package com.via.mall.controller;

import com.via.mall.common.ApiRestResponse;
import com.via.mall.request.CreateOrderReq;
import com.via.mall.service.ImoocMallOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Qingqing.He
 * @date 2021/1/22 16:14
 * 订单控制器
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    ImoocMallOrderService orderService;

    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody CreateOrderReq createOrderReq){


        return ApiRestResponse.success();
    }
}
