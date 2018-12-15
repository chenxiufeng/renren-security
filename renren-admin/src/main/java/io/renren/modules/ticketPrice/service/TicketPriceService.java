package io.renren.modules.ticketPrice.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ticketPrice.entity.TicketPriceEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author llc
 * @email 787254039@gmail.com
 * @date 2018-11-23 15:22:50
 */
public interface TicketPriceService extends IService<TicketPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<TicketPriceEntity> getPriceByCode(String code);
}

