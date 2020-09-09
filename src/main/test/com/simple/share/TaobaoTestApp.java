package com.simple.share;

import com.spring.simple.development.core.annotation.config.*;
import com.spring.simple.development.core.annotation.config.SpringSimpleApplication;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import org.junit.Before;
import org.junit.Test;

/**
 * @author liko.wang
 * @Date 2019/12/24/024 14:20
 * @Description 程序启动
 **/
@EnableWebMvc
@EnableMybatis
@EnableRedis
@EnableSwagger
@SpringSimpleApplication
public class TaobaoTestApp {

    @Before
    public void simpleTestBefore() {
        //SimpleApplication.runTest(TestApp.class);
    }

    @Test
    public void test() {

    }
}