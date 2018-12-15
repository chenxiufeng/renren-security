package io.renren.modules.job.task.ticketPrice;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import io.renren.modules.priceAdvice.entity.PriceAdviceEntity;
import io.renren.modules.priceAdvice.service.PriceAdviceService;
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
    @Autowired
	private PriceAdviceService priceAdviceService;
    
    //调用接口获取数据，然后存到数据库中
    public void getTicketPrice(){
    	List<TicketEntity>	ticketList= ticketService.selectList(new EntityWrapper<TicketEntity>().eq("del_flag",0));
    	//定义集合用于新增
		List<PriceAdviceEntity> addList=new ArrayList<>();
		//定义集合用于修改
		List<PriceAdviceEntity> updateList=new ArrayList<>();
    	if(ticketList.size()>0){
    		for(int i=0;i<ticketList.size();i++){
				TicketEntity ticketEntity=ticketList.get(i);
    			String code=ticketEntity.getCode();
    			try {
					TicketPriceEntity ticketPriceEntity = TickerAPI.getData(code);
					logger.info("请求参数:"+code);
					if(ticketPriceEntity!=null){
						ticketPriceService.insert(ticketPriceEntity);
						//再根据code查询price_advice看是否已经存在了，存在则更新，不存在则新增
						PriceAdviceEntity priceAdvice = priceAdviceService.selectOne(new EntityWrapper<PriceAdviceEntity>().eq("code", code));
						if(priceAdvice ==null){
							PriceAdviceEntity priceAdviceEntity=new PriceAdviceEntity();
							priceAdviceEntity.setCode(code);
							priceAdviceEntity.setName(ticketPriceEntity.getName());
							priceAdviceEntity.setAdvicePrice(ticketEntity.getAdvicePrice());
							priceAdviceEntity.setCurrentPrice(ticketPriceEntity.getCurrent());
							addList.add(priceAdviceEntity);
						}else{
							priceAdvice.setAdvicePrice(ticketEntity.getAdvicePrice());
							priceAdvice.setCurrentPrice(ticketPriceEntity.getCurrent());
							updateList.add(priceAdvice);
						}
					}
					if(addList.size()>0){
						priceAdviceService.insertBatch(addList);
					}
					if(updateList.size()>0){
						priceAdviceService.updateBatchById(updateList);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
    		}
    	}
    	
    }
}
