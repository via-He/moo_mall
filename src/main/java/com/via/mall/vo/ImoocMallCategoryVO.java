package com.via.mall.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Qinging.He
 * @date 2020/12/25 16:13
 */
@Data
public class ImoocMallCategoryVO implements Serializable {
    private Integer id;

    private String name;

    /**
     * 分类目录名称
     */
    private Integer type;

    /**
     * 分类目录级别
     */
    private Integer parentId;

    /**
     * 父id
     */
    private Integer orderNum;

    /**
     * 目录展示时的排序
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;

    private List<ImoocMallCategoryVO> childCategory = new ArrayList<>();
}
