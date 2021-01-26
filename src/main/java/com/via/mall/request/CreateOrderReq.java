package com.via.mall.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
@Data
public class CreateOrderReq {

    /**
     * 收货人姓名
     */
    @NotNull(message = "收货人姓名不能为空")
    private String receiverName;

    /**
     * 收货人手机号
     */
    @NotNull(message = "收货人手机号不能为空")
    private String receiverMobile;

    /**
     * 收货人地址
     */
    @NotNull(message = "收货人地址不能为空")
    private String receiverAddress;


    /**
     * 运费:包邮模式
     */
    private Integer postage = 0;

    /**
     * 支付类型 1：在线支付
     */
    private Integer paymentType = 1;

}