package com.via.mall.domain;

import java.util.Date;
import lombok.Data;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
@Data
public class ImoocMallOrder {
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    private Integer userId;

    /**
     * 订单总价
     */
    private Integer totalPrice;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人手机号
     */
    private String receiverMobile;

    /**
     * 收货人地址
     */
    private String receiverAddress;

    /**
     * 订单状态；0用户取消；10未付款；20已付款；30已发货；40交易完成
     */
    private Integer orderSatus;

    /**
     * 运费
     */
    private Integer postage;

    /**
     * 支付类型1在线支付
     */
    private Integer paymentType;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 交易完成时间
     */
    private Date endTime;

    /**
     * 创建
     */
    private Date createTime;

    private Date updateTime;
}