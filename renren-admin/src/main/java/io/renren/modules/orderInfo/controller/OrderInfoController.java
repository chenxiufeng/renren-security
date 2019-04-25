package io.renren.modules.orderInfo.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.orderInfo.dto.OrderInfoItemDto;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.orderInfo.entity.OrderInfoEntity;
import io.renren.modules.orderInfo.service.OrderInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * erp订单信息表
 *
 * @author llc
 * @email 787254039@qq.com
 * @date 2019-04-04 15:22:39
 */
@RestController
@RequestMapping("orderInfo/orderinfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @RequiresPermissions("orderInfo:orderinfo:list")
    public R list(@RequestBody Map<String, Object> params){
        return orderInfoService.queryPageOrderList(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderNo}")
   /* @RequiresPermissions("orderInfo:orderinfo:info")*/
    public R info(@PathVariable("orderNo") Long orderNo){
        OrderInfoEntity orderInfo = orderInfoService.selectById(orderNo);

        return R.ok().put("orderInfo", orderInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   /* @RequiresPermissions("orderInfo:orderinfo:save")*/
    public R save(@RequestBody OrderInfoItemDto orderInfoItemDto){
        return orderInfoService.saveOrder(orderInfoItemDto);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    /*@RequiresPermissions("orderInfo:orderinfo:update")*/
    public R update(@RequestBody OrderInfoEntity orderInfo){
        ValidatorUtils.validateEntity(orderInfo);
        orderInfoService.updateAllColumnById(orderInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    /*@RequiresPermissions("orderInfo:orderinfo:delete")*/
    public R delete(@RequestBody Long[] orderNos){
        orderInfoService.deleteBatchIds(Arrays.asList(orderNos));

        return R.ok();
    }

}
