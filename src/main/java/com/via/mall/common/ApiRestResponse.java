package com.via.mall.common;

import com.via.mall.exception.MallExceptionEnum;
import lombok.Data;

/**
 * @author Qingqing.He
 * @date 2020/12/28 13:42
 * 描述：通用返回对象
 */
@Data
public class ApiRestResponse<T> {
    private Integer status;
    private String msg;
    private T data;
    private static final int OK_CODE=10000;
    private static final String OK_MSG = "SUCCESS";

    public ApiRestResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiRestResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    //无参 表示请求成功

    public ApiRestResponse() {
        this(OK_CODE,OK_MSG);
    }

    public static <T> ApiRestResponse<T> success(){
        return new ApiRestResponse<>();
    }

    public static <T> ApiRestResponse<T> success(T result){
        ApiRestResponse<T> response = new ApiRestResponse<>();
        response.setData(result);
        return response;
    }

    public static <T> ApiRestResponse<T> error(Integer code,String msg){
        return new ApiRestResponse<>(code,msg);
    }

    public static <T> ApiRestResponse<T> error(MallExceptionEnum ex){
        return new ApiRestResponse<>(ex.getCode(),ex.getMsg());
    }

}
