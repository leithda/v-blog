package cn.leithda.blog.common.utils;

import cn.leithda.blog.common.exception.BaseRespCode;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author leithda
 * @version 2021/7/3
 * @desc 通用返回结果封装
 */

@Data
public class R<T> {
    private Integer code = 200;
    private String message;
    private T data;

    /**
     * 成功响应
     *
     * @param data 数据
     * @param <T>  泛型
     * @return 响应
     */
    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setMessage("操作成功");
        r.setData(data);
        return r;
    }

    /**
     * 成功响应
     *
     * @param message 消息
     * @param <T>     泛型
     * @return 响应
     */
    public static <T> R<T> ok(String message) {
        R<T> r = new R<>();
        r.setMessage(message);
        return r;
    }


    /**
     * 成功响应
     *
     * @param message 消息
     * @param data    数据
     * @param <T>     泛型
     * @return 响应
     */
    public static <T> R<T> ok(String message, T data) {
        R<T> r = new R<>();
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    /**
     * 失败响应
     *
     * @param message 消息
     * @param <T>     泛型
     * @return 响应
     */
    public static <T> R<T> fail(String message) {
        R<T> r = new R<>();
        r.setCode(-1);
        r.setMessage(message);
        return r;
    }

    /**
     * 失败响应
     *
     * @param message 消息
     * @param data    数据
     * @param <T>     泛型
     * @return 响应
     */
    public static <T> R<T> fail(String message, T data) {
        R<T> r = new R<>();
        r.setCode(-1);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    /**
     * 失败响应
     *
     * @param code    响应码
     * @param message 消息
     * @param data    数据
     * @param <T>     泛型
     * @return 响应
     */
    public static <T> R<T> fail(Integer code, String message, T data) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    /**
     * 失败响应
     * @param baseRespCode 通用响应枚举
     * @param <T> 泛型
     * @return 响应
     */
    public static <T> R<T> fail(BaseRespCode baseRespCode) {
        return fail(baseRespCode.getCode(), baseRespCode.getMessage(), null);
    }


}
