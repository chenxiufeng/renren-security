package io.renren.modules.product.dao;

import io.renren.common.utils.R;
import io.renren.modules.product.entity.ProductEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品表
 * 
 * @author llc
 * @email 787254039@qq.com
 * @date 2019-04-12 17:23:45
 */
public interface ProductDao extends BaseMapper<ProductEntity> {

    List<ProductEntity> productlist(@Param("map") Map<String,Object> params);
}
