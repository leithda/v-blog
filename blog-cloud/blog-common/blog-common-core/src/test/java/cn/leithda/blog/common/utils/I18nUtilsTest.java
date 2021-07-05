package cn.leithda.blog.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 黎士达
 * @version 2021/7/5
 * @desc
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class I18nUtilsTest {

    @Autowired
    I18nUtils i18nUtils;

    @Test
    public void get() {
        String message = i18nUtils.get("welcome", new Object[]{"i18n"});
        System.out.println(message);
    }
}