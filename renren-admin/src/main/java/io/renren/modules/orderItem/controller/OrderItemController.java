package io.renren.modules.orderItem.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.orderItem.entity.OrderItemEntity;
import io.renren.modules.orderItem.service.OrderItemService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 订单详情表
 *
 * @author llc
 * @email 787254039@qq.com
 * @date 2019-04-04 16:07:03
 */
@RestController
@RequestMapping("orderItem/orderitem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("orderItem:orderitem:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderItemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("orderItem:orderitem:info")
    public R info(@PathVariable("id") Long id){
        OrderItemEntity orderItem = orderItemService.selectById(id);

        return R.ok().put("orderItem", orderItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("orderItem:orderitem:save")
    public R save(@RequestBody OrderItemEntity orderItem){
        orderItemService.insert(orderItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("orderItem:orderitem:update")
    public R update(@RequestBody OrderItemEntity orderItem){
        ValidatorUtils.validateEntity(orderItem);
        orderItemService.updateAllColumnById(orderItem);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("orderItem:orderitem:delete")
    public R delete(@RequestBody Long[] ids){
        orderItemService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
