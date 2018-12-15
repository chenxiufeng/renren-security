package io.renren.modules.ticketPrice.dao;

import io.renren.modules.ticketPrice.entity.TicketPriceEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * 
 * @author llc
 * @email 787254039@gmail.com
 * @date 2018-11-23 15:22:50
 */
public interface TicketPriceDao extends BaseMapper<TicketPriceEntity> {

    List<TicketPriceEntity> getPriceByCode(@Param("code") String code);
}