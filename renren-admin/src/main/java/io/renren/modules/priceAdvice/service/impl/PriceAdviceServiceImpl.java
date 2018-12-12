package io.renren.modules.priceAdvice.service.impl;

import org.springframework.stereotype.Service;
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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PriceAdviceEntity> page = this.selectPage(
                new Query<PriceAdviceEntity>(params).getPage(),
                new EntityWrapper<PriceAdviceEntity>()
        );

        return new PageUtils(page);
    }

}
