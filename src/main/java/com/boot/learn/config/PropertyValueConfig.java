package com.boot.learn.config;

import com.boot.learn.bean.PropertyExistBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author zhuyao
 * @date 2019/10/27
 */
@Configuration
public class PropertyValueConfig {

    /**
     * 配置存在时才会加载这个bean
     *
     * @return
     */
    @Bean()
    @ConditionalOnProperty(value="conditional.property.switch",havingValue = "true")
    public PropertyExistBean propertyExistBean() {
        return new PropertyExistBean("propertyExistBean");
    }

}
