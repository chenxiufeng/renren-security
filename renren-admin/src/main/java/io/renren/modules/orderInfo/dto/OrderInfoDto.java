package io.renren.modules.orderInfo.dto;

import io.renren.modules.orderItem.entity.OrderItemEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author llc
 * @Description
 * @Date 2019/4/4 16:23
 */
@Getter
@Setter
public class OrderInfoDto {
    //订单号
    private String orderNo;
    //会员名
    private String memberName;

    //总金额
    private BigDecimal totalAmount;
    //优惠金额
    private BigDecimal pmtGoods;
    //已付金额
    private BigDecimal payed;
    //订单创建时间
    private Date orderCreatetime;
    //支付状态
    private Integer payStatus;
    //应付金额
    private BigDecimal payableAmount;
    //支付方式
    private String payment;

    private List<OrderItemEntity> orderItemList;
}
