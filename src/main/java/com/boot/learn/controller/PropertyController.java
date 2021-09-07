package com.boot.learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.learn.bean.PropertyExistBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zhuyao
 * @date 2019/10/27
 */
@RestController
@RequestMapping(path = "property")
public class PropertyController {

    @Autowired(required = false)
    private PropertyExistBean propertyExistBean;
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @GetMapping(path = "show")
    public String show() {
        Map<String, String> result = new HashMap<>(4);
        result.put("propertyExistBean", propertyExistBean == null ? "propertyExistBean is null" :  propertyExistBean.getName());
        return JSONObject.toJSONString(result);
    }

    @GetMapping(path = "threadPool")
    public String threadPool() throws Exception{
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(10000);
            taskExecutor.submit(() -> {
                System.out.println(Thread.currentThread());
            });
        }
        return null;
    }
}