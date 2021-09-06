package com.boot.learn.bean.threadpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

public class CloseHandler implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        Map<String, ThreadPoolExecutor> beans = applicationContext.getBeansOfType(ThreadPoolExecutor.class);
        beans.forEach((beanName,threadPoolExecutor)->{
            System.out.println(beanName);
        });


    }
}
