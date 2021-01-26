package com.via.mall.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Qingqing.He
 * @date 2020/12/30 14:36
 * 描述：添加目录的请求类
 */
@Data
public class AddCategoryReq {
    @Size(min = 2,max = 5)
    @NotNull(message = "name 不能为null")
    private String name;
    @Max(3)
    @NotNull(message = "type 不能为null")
    private Integer type;
    @NotNull(message = "parentId 不能为null")
    private Integer parentId;
    @NotNull(message = "orderNum 不能为null")
    private Integer orderNum;
}
