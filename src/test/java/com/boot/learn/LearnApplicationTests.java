package com.boot.learn;

import com.boot.learn.service.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnApplicationTests {

    @Resource
    private IRedisService iRedisService;
	@Test
	public void contextLoads() {
        String k3 = (String) iRedisService.get("k3");
        System.out.println(k3);

        boolean k3Exists = iRedisService.exists("k3");
        System.out.println(k3Exists);
    }
}