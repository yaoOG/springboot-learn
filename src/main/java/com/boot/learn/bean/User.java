package com.boot.learn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhuyao
 * @date 2019/08/23
 */
@Data
@AllArgsConstructor
public class User implements Serializable {

    private Integer userId;
    private String userName;
    private String password;
    private String phone;
}