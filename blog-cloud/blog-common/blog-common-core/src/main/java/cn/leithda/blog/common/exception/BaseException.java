package cn.leithda.blog.common.exception;

import cn.leithda.blog.common.utils.I18nUtils;
import cn.leithda.blog.common.utils.StringUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author leithda
 * @version 2021/7/5
 * @desc 业务异常
 */
@Data
public class BaseException extends RuntimeException {

    @Autowired
    I18nUtils i18nUtils;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException(String module, Integer code, Object[] args, String defaultMessage)
    {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, Integer code, Object[] args)
    {
        this(module, code, args, null);
    }

    public BaseException(String module, String defaultMessage)
    {
        this(module, null, null, defaultMessage);
    }

    public BaseException(Integer code, Object[] args)
    {
        this(null, code, args, null);
    }

    public BaseException(String defaultMessage)
    {
        this(null, null, null, defaultMessage);
    }

    @Override
    public String getMessage() {
        String message = null;
        if (Objects.nonNull(code))
        {
            message = i18nUtils.get(String.valueOf(code), args);
        }
        if (message == null)
        {
            message = defaultMessage;
        }
        return message;
    }
}
