package io.renren.modules.priceAdvice.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.priceAdvice.entity.PriceAdviceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author llc
 * @email 787254039@gmail.com
 * @date 2018-12-12 15:14:20
 */
public interface PriceAdviceService extends IService<PriceAdviceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PriceAdviceEntity> exportExcel(Map<String,Object> params);

    R seeDetail(String code);
}

