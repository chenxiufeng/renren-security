package io.renren.modules.orderInfo.dto;

import io.renren.modules.orderInfo.entity.OrderInfoEntity;
import io.renren.modules.orderItem.entity.OrderItemEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author llc
 * @Description 订单信息以及商品信息（下单）
 * @Date 2019/4/24 14:02
 */
@Getter
@Setter
public class OrderInfoItemDto {

    private OrderInfoEntity orderInfo;

    private List<OrderItemEntity> orderItemData;

}
