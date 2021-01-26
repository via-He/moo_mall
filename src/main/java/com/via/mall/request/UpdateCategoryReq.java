package com.via.mall.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Qingqing.He
 * @date 2020/12/31 10:46
 */
@Data
public class UpdateCategoryReq {

    @NotNull(message = "id 不能为null")
    private Integer id;
    @Size(min = 2, max = 5)
    private String name;
    @Max(3)
    private Integer type;
    private Integer parentId;
    private Integer orderNum;
}
