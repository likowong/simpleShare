package com.simple.share.context;


import com.simple.share.task.GoodsTask;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: luke
 * @time: 2020/7/1 0001 16:58
 */
@Component
@Configuration
public class SimpleShareApplication implements ApplicationContextAware {
    @Autowired
    private GoodsTask goodsTask;

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SimpleShareApplication.applicationContext = applicationContext;
        // 初始化数据
        //goodsTask.refreshGoods();
    }

    public static <T> T getBeanByType(Class clazz) throws BeansException {
        return (T) applicationContext.getBean(clazz);
    }

    public static boolean isExistBean(String beanName) throws BeansException {
        return applicationContext.containsBean(beanName);
    }
}