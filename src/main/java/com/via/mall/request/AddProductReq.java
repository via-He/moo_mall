package com.via.mall.request;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
@Data
public class AddProductReq {

    @NotNull(message = "商品名不能为空")
    private String name;

    @NotNull(message = "商品图片不能为空")
    private String image;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 价格，单位-分
     */
    @NotNull(message = "目录Id不能为空")
    private Integer categoryId;

    /**
     * 库存shuliang
     */
    @NotNull(message = "商品价格不能为空")
    @Min(value = 1,message = "价格不能低于1")
    private Integer price;

    /**
     * 上架状态
     */
    @NotNull(message = "库存不能为空")
    @Max(value=10000,message = "库存不能多于10000")
    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}