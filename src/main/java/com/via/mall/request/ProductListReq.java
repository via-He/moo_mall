package com.via.mall.request;

import lombok.Data;

import java.util.Date;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
@Data
public class ProductListReq {

    private String keyword;

    private Integer categoryId;

    private String orderBy;

    private Integer pageNum;

    private Integer pageSize;
}