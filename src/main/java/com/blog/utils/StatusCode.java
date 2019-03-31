package com.blog.utils;

/**
 * Created by hc on 2019/3/31.
 */
public enum StatusCode {
    Success(200,"成功"),
    Fail(-1,"失败"),
    NotFound(10010,"不存在"),
    Entity_Not_Exist(10011,"实体信息不存在"),
    Invalid_Params(10012,"请求参数不合法!");
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }
    StatusCode(int code, String message) {
        this.code = code;
        this.msg = message;
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
