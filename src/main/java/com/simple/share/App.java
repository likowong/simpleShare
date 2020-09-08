package com.simple.share;

import com.spring.simple.development.core.annotation.config.*;
import com.spring.simple.development.core.annotation.config.SpringSimpleApplication;
import com.spring.simple.development.core.baseconfig.tomcat.SimpleApplication;

/**
 * @author liko.wang
 * @Date 2019/12/24/ 14:20
 * @Description 程序启动
 **/
@EnableWebMvc
@EnableMybatis
@EnableRedis
@EnableSwagger
@SpringSimpleApplication
public class App {
    public static void main(String[] args) {
        SimpleApplication.run(App.class);
    }
}