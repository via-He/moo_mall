package com.via.mall.domain;

import java.util.Date;
import lombok.Data;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
@Data
public class ImoocMallItem {
    private Integer id;

    /**
     * 归属订单
     */
    private String orderNo;

    private Integer productId;

    private String productName;

    private String productImg;

    private Integer unitPrice;

    private Integer quanlity;

    private Integer totalPrice;

    private Date createTime;

    private Date updateTime;
}