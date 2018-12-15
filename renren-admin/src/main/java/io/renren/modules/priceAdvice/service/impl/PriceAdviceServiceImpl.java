package io.renren.modules.priceAdvice.service.impl;

import io.renren.common.utils.R;
import io.renren.modules.ticketPrice.entity.TicketPriceEntity;
import io.renren.modules.ticketPrice.service.TicketPriceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.priceAdvice.dao.PriceAdviceDao;
import io.renren.modules.priceAdvice.entity.PriceAdviceEntity;
import io.renren.modules.priceAdvice.service.PriceAdviceService;


@Service("priceAdviceService")
public class PriceAdviceServiceImpl extends ServiceImpl<PriceAdviceDao, PriceAdviceEntity> implements PriceAdviceService {
    @Autowired
    private TicketPriceService ticketPriceService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PriceAdviceEntity> page = this.selectPage(
                new Query<PriceAdviceEntity>(params).getPage(),
                new EntityWrapper<PriceAdviceEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<PriceAdviceEntity> exportExcel(Map<String, Object> params) {
        String code=params.get("code").toString();
        String name=params.get("name").toString();
        return this.baseMapper.selectList(new EntityWrapper<PriceAdviceEntity>().eq(StringUtils.isNotBlank(code),"code",code).like(StringUtils.isNotBlank(name),"name",name));
    }

    @Override
    public R seeDetail(String code) {
        //先根据code查询出所有价格
       List<TicketPriceEntity> ticketPrices= ticketPriceService.getPriceByCode(code);
        //再根据code找到均价，后台生成均价集合
        PriceAdviceEntity priceAdviceEntity = this.selectOne(new EntityWrapper<PriceAdviceEntity>().eq("code", code));
        //推荐价集合
        List<BigDecimal> adviceList=new ArrayList<>();
        //当前价集合
        List<BigDecimal> ticketPriceList=new ArrayList<>();
        //日期集合
        List<Date> dateList=new ArrayList<>();
        for(int i=0;i<ticketPrices.size();i++){
            TicketPriceEntity ticketPriceEntity = ticketPrices.get(i);
            ticketPriceList.add(ticketPriceEntity.getCurrent());
            adviceList.add(priceAdviceEntity.getAdvicePrice());
            dateList.add(ticketPriceEntity.getTime());
        }
        return R.ok().put("adviceList",adviceList).put("ticketPriceList",ticketPriceList).put("dateList",dateList);
    }

}
