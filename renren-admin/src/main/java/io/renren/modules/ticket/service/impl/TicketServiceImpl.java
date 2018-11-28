package io.renren.modules.ticket.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.ticket.dao.TicketDao;
import io.renren.modules.ticket.entity.TicketEntity;
import io.renren.modules.ticket.service.TicketService;


@Service("ticketService")
public class TicketServiceImpl extends ServiceImpl<TicketDao, TicketEntity> implements TicketService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TicketEntity> page = this.selectPage(
                new Query<TicketEntity>(params).getPage(),
                new EntityWrapper<TicketEntity>()
        );

        return new PageUtils(page);
    }

}
