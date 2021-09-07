package com.boot.learn.bean;

import lombok.Data;

/**
 * @author choo
 */
@Data
public class ProductOrder {
    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 订单类型(订单创建、订单付款、订单完成）
     */
    private String type;
}
