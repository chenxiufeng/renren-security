package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.product.entity.ProductEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品表
 *
 * @author llc
 * @email 787254039@qq.com
 * @date 2019-04-12 17:23:45
 */
public interface ProductService extends IService<ProductEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R productlist(Map<String,Object> params);
}

