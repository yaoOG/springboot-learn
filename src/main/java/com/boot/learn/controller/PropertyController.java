package com.boot.learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.learn.bean.PropertyExistBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "show")
    public String show() {
        Map<String, String> result = new HashMap<>(4);
        result.put("propertyExistBean", propertyExistBean == null ? "propertyExistBean is null" :  propertyExistBean.getName());
        return JSONObject.toJSONString(result);
    }
}