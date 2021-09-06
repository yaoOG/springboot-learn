package com.boot.learn.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class PersonFactoryBean implements FactoryBean<Person> {

    /**
     * 初始化Str
     */
    private String initStr;

    @Override
    public Person getObject() throws Exception {
        //这里我需要获取对应参数
        String[] split = initStr.split(",");
        Person p=new Person();
        p.setAge(Integer.parseInt(split[0]));
        p.setName(split[1]);
        //这里可能需要还要有其他复杂事情需要处理
        return p;
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    public String getInitStr() {
        return initStr;
    }

    public void setInitStr(String initStr) {
        this.initStr = initStr;
    }
}
