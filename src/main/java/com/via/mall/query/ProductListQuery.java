package com.via.mall.query;

import lombok.Data;

import java.util.List;

/**
 * @author Qingqing.He
 * @date 2021/1/12 10:30
 */
@Data
public class ProductListQuery {

    private String keyword;

    private List<Integer> categoryIds;
}
