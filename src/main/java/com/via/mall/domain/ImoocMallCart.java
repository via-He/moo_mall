package com.via.mall.domain;

import java.util.Date;
import lombok.Data;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
@Data
public class ImoocMallCart {
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

    private Date createTime;

    private Date updateTime;
}