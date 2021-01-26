package com.via.mall.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
@Data
public class UpdateProductReq {

    @NotNull(message = "商品Id不能为空")
    private Integer Id;

    private String name;

    private String image;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 价格，单位-分
     */
    private Integer categoryId;

    /**
     * 库存shuliang
     */
    @Min(value = 1,message = "价格不能低于1")
    private Integer price;

    /**
     * 上架状态
     */
    @Max(value=10000,message = "库存不能多于10000")
    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}