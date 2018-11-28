package io.renren.modules.ticketPrice.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.ticketPrice.dao.TicketPriceDao;
import io.renren.modules.ticketPrice.entity.TicketPriceEntity;
import io.renren.modules.ticketPrice.service.TicketPriceService;


@Service("ticketPriceService")
public class TicketPriceServiceImpl extends ServiceImpl<TicketPriceDao, TicketPriceEntity> implements TicketPriceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TicketPriceEntity> page = this.selectPage(
                new Query<TicketPriceEntity>(params).getPage(),
                new EntityWrapper<TicketPriceEntity>()
        );

        return new PageUtils(page);
    }

}
