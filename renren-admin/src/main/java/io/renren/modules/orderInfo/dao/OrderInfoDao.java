package io.renren.modules.orderInfo.dao;

import io.renren.modules.orderInfo.entity.OrderInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * erp订单信息表
 * 
 * @author llc
 * @email 787254039@qq.com
 * @date 2019-04-04 15:22:39
 */
public interface OrderInfoDao extends BaseMapper<OrderInfoEntity> {

    List<OrderInfoEntity> queryPageOrderList(@Param("map") Map<String,Object> params);

    Integer selectOrderTotal(@Param("map") Map<String,Object> params);
}
