package cn.leithda.blog.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 黎士达
 * @version 2021/7/5
 * @desc 启动类
 */
@SpringBootApplication
public class CommonCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonCoreApplication.class,args);
    }

}
