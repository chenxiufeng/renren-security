package io.renren.modules.orderItem.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.orderItem.entity.OrderItemEntity;

import java.util.Map;

/**
 * 订单详情表
 *
 * @author llc
 * @email 787254039@qq.com
 * @date 2019-04-04 16:07:03
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

