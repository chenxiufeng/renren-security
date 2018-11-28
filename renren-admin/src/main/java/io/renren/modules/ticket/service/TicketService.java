package io.renren.modules.ticket.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ticket.entity.TicketEntity;

import java.util.Map;

/**
 * 
 *
 * @author llc
 * @email 787254039@gmail.com
 * @date 2018-11-23 15:41:40
 */
public interface TicketService extends IService<TicketEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

