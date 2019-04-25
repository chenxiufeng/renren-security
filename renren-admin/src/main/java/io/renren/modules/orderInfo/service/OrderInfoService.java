package io.renren.modules.orderInfo.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.orderInfo.dto.OrderInfoItemDto;
import io.renren.modules.orderInfo.entity.OrderInfoEntity;

import java.util.Map;

/**
 * erp订单信息表
 *
 * @author llc
 * @email 787254039@qq.com
 * @date 2019-04-04 15:22:39
 */
public interface OrderInfoService extends IService<OrderInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R queryPageOrderList(Map<String,Object> params);

    R saveOrder(OrderInfoItemDto orderInfoItemDto);
}

