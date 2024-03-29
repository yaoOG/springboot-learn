package com.boot.learn.bean.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class CloseHandler implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        Map<String, ThreadPoolExecutor> beans = applicationContext.getBeansOfType(ThreadPoolExecutor.class);
        beans.forEach((beanName,threadPoolExecutor)->{
            log.info("关闭了" + beanName);
        });


    }
}
