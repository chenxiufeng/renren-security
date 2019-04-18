package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.product.entity.ProductEntity;
import io.renren.modules.product.service.ProductService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 商品表
 *
 * @author llc
 * @email 787254039@qq.com
 * @date 2019-04-12 17:23:45
 */
@RestController
@RequestMapping("product/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("product:product:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productService.queryPage(params);

        return R.ok().put("page", page);
    }





    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    @RequiresPermissions("product:product:info")
    public R info(@PathVariable("skuId") String skuId){
        ProductEntity product = productService.selectById(skuId);

        return R.ok().put("product", product);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:product:save")
    public R save(@RequestBody ProductEntity product){
        productService.insert(product);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:product:update")
    public R update(@RequestBody ProductEntity product){
        ValidatorUtils.validateEntity(product);
        productService.updateAllColumnById(product);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:product:delete")
    public R delete(@RequestBody String[] skuIds){
        productService.deleteBatchIds(Arrays.asList(skuIds));

        return R.ok();
    }

    /**
     * 列表
     */
    @PostMapping ("/productlist")
    public R productlist(@RequestBody Map<String, Object> params){
        return productService.productlist(params);
    }


}
