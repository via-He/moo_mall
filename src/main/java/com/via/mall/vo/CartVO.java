package com.via.mall.vo;

import lombok.Data;

/**
 * @author Qingqing.He
 * @date 2021/1/13 16:40
 *给前端展示
 */
@Data
public class CartVO {
    private Integer id;

    private Integer productId;

    private Integer userId;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 是否被勾选；0：未勾选；1：勾选
     */
    private Integer selected;

    private Integer price;
    private Integer totalPrice;
    private String productName;
    private String productImage;
}
