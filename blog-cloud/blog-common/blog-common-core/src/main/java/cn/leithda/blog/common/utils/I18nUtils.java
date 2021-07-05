package cn.leithda.blog.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author leithda
 * @version 2021/7/5
 * @desc 国际化工具类
 */
@Component
public class I18nUtils {

    @Autowired
    private MessageSource messageSource;

    /**
     * 获取国际化翻译值
     * @param msgKey 消息key
     * @param msgArgs 消息参数
     * @return 翻译内容
     */
    public String get(String msgKey, Object[] msgArgs) {
        try {
            return messageSource.getMessage(msgKey, msgArgs, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgKey;
        }
    }
}
