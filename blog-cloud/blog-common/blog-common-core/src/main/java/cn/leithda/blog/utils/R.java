package cn.leithda.blog.utils;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 黎士达
 * @version 2021/7/3
 * @desc 通用返回结果封装
 */

@Data
public class R<T> {
    private String code;
    private String message;
    private T data;


}
