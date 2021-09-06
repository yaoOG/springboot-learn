package com.boot.learn.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonFactoryBeanTest {

    @Test
    public void test() {
        //这个是BeanFactory
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("application.xml");
        //获取对应的对象化
        Object demo = beanFactory.getBean("demo");
        System.out.println(demo instanceof Person);
        System.out.println(demo);
        //获取从工厂Bean中获取对象
        Person demoFromFactory = beanFactory.getBean("demoFromFactory", Person.class);
        System.out.println(demoFromFactory);
        //获取对应的personFactory
        Object bean = beanFactory.getBean("&demoFromFactory");
        System.out.println(bean instanceof PersonFactoryBean);
        PersonFactoryBean factoryBean=(PersonFactoryBean) bean;
        System.out.println("初始化参数为："+factoryBean.getInitStr());
    }

}