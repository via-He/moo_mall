package com.via.mall.domain;

import java.util.Date;
import lombok.Data;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
@Data
public class ImoocMallProduct {
    private Integer id;

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
    private Integer price;

    /**
     * 上架状态
     */
    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}