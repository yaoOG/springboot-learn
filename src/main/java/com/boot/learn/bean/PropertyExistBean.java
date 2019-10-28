package com.boot.learn.bean;

/**
 * @author zhuyao
 * @date 2019/10/27
 */
public class PropertyExistBean {
    private String name;

    public PropertyExistBean(String name) {
        this.name = name;
    }

    public String getName() {
        return "property : " + name;
    }
}
