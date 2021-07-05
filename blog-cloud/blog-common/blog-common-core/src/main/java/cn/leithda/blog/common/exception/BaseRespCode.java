package cn.leithda.blog.common.exception;


/**
 * Created with IntelliJ IDEA.
 *
 * @author leithda
 * @version 2021/7/5
 * @desc 通用响应枚举
 */
public enum BaseRespCode {
    OK(200,"操作成功"),
    UNAUTHORIZED(401,"权限校验失败"),
    INTERNAL_SERVER_ERROR(500, "服务器内部异常"),
    SERVICE_UNAVAILABLE(503,"服务不可用")
    ;

    /**
     * 响应码
     */
    private final Integer code;
    /**
     * 响应消息
     */
    private final String message;

    BaseRespCode(int code, String message) {
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
