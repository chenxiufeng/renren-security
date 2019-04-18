package io.renren.modules.product.service.impl;

import io.renren.common.utils.R;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductDao;
import io.renren.modules.product.entity.ProductEntity;
import io.renren.modules.product.service.ProductService;


@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProductEntity> implements ProductService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductEntity> page = this.selectPage(
                new Query<ProductEntity>(params).getPage(),
                new EntityWrapper<ProductEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R productlist(Map<String, Object> params) {
        List<ProductEntity>  list=this.baseMapper.productlist(params);
        return R.ok().put("goodsData",list) ;
    }
}
