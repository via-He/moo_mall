package com.via.mall.exception;

import lombok.Data;

/**
 * @author Qingqing.He
 * @date 2020/12/29 10:01
 * 描述：统一异常
 */
@Data
public class MallException extends RuntimeException {
    private final Integer code;
    private final String message;

    public MallException(Integer code,String message){
        this.code = code;
        this.message = message;
    }
    public MallException(MallExceptionEnum exceptionEnum){
        this(exceptionEnum.getCode(),exceptionEnum.getMsg());
    }
}
