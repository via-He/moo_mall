package com.via.mall.exception;

/**
 * @author Qingqing.He
 * @date 2020/12/28 14:00
 * 描述：异常枚举
 */

public enum MallExceptionEnum {
    NEED_USER_NAME(10001,"用户名不能为空"),
    NEED_PASSWORD(10002,"密码不能为空"),
    PASSWORD_TOO_SHORT(10003,"密码不能低于8位"),
    NAME_EXISTED(10004,"不允许重名"),
    INSERT_FAILED(10005,"新建User失败"),
    WRONG_PASSWORD(10006,"密码错误"),
    NEED_LOGIN(10007,"用户未登录，请重新登录"),
    UPDATE_FAILED(10008,"更新失败"),
    NEED_ADMIN(10009,"无管理权限"),
    PARAM_NOT_NULL(10010,"参数不能为空"),
    CREATE_FAILED(10011,"新增失败"),
    REQUEST_PARAM_ERROR(10012,"参数错误"),
    DELETE_FAILED(10013,"删除失败"),
    MKDIR_FAILED(10014,"文件夹创建失败"),
    UPLOAD_FAILED(10015,"图片上传失败"),
    NOT_SALE(10016,"商品状态不可售"),
    NOT_STOCK(10017,"商品库存不足"),
    SYSTEM_ERROR(20000,"系统异常");
    /**
     * 异常码
     */
    Integer code;
    /**
     * 异常信息
     */
    String msg;

    MallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
