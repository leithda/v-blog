package cn.leithda.blog.common.exception;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author leithda
 * @version 2021/7/5
 * @desc 通用异常枚举
 */
public enum CommErrorCode {
    OK(200,"操作成功"),
    UNAUTHORIZED(401,"权限校验失败"),
    INTERNAL_SERVER_ERROR(500, "服务器内部异常"),
    SERVICE_UNAVAILABLE(503,"服务不可用")
    ;

    private Integer code;
    private String message;
    CommErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
