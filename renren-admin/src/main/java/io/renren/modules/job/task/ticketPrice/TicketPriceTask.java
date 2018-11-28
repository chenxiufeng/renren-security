package io.renren.modules.job.task.ticketPrice;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import io.renren.common.utils.TickerAPI;
import io.renren.modules.ticket.entity.TicketEntity;
import io.renren.modules.ticket.service.TicketService;
import io.renren.modules.ticketPrice.entity.TicketPriceEntity;
import io.renren.modules.ticketPrice.service.TicketPriceService;

/**
 * 
 * @author PC
 *	获取实时价格
 */

@Component("ticketPriceTask")
public class TicketPriceTask {
	/**
     * 日志
     */
    private  static  Logger logger = LoggerFactory.getLogger(TicketPriceTask.class);
    
    @Autowired
    private TicketPriceService ticketPriceService;
    
    @Autowired
    private TicketService ticketService;
    
    
    //调用接口获取数据，然后存到数据库中
    public void getTicketPrice(){
    	List<TicketEntity>	ticketList= ticketService.selectList(new EntityWrapper<TicketEntity>().eq("del_flag",0));
    	if(ticketList.size()>0){
    		for(int i=0;i<ticketList.size();i++){
    			String code=ticketList.get(i).getCode();
    			try {
					TicketPriceEntity ticketPriceEntity = TickerAPI.getData(code);
					logger.info("请求参数:"+code);
					if(ticketPriceEntity!=null){
						ticketPriceService.insert(ticketPriceEntity);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
    		}
    	}
    	
    }
}
