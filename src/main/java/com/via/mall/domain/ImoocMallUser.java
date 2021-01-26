package com.via.mall.domain;

import java.util.Date;
import lombok.Data;


/**
 * @author Qingqing.He
 * @date 2020/12/25 16:13
 */
@Data
public class ImoocMallUser {
    /**
     * 主键id
     */
    private Integer id;

    private String username;

    private String password;

    private String personalizedSignature;

    /**
     * 1:普通用户；2:管理员
     */
    private Integer role;

    private Date createtime;

    private Date updatetime;
}