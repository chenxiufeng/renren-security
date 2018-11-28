package io.renren.modules.ticketPrice.controller;

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

import io.renren.modules.ticketPrice.entity.TicketPriceEntity;
import io.renren.modules.ticketPrice.service.TicketPriceService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author llc
 * @email 787254039@gmail.com
 * @date 2018-11-23 15:22:50
 */
@RestController
@RequestMapping("ticketPrice/ticketprice")
public class TicketPriceController {
    @Autowired
    private TicketPriceService ticketPriceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ticketPrice:ticketprice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ticketPriceService.queryPage(params);
 
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ticketPrice:ticketprice:info")
    public R info(@PathVariable("id") Integer id){
        TicketPriceEntity ticketPrice = ticketPriceService.selectById(id);

        return R.ok().put("ticketPrice", ticketPrice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ticketPrice:ticketprice:save")
    public R save(@RequestBody TicketPriceEntity ticketPrice){
        ticketPriceService.insert(ticketPrice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ticketPrice:ticketprice:update")
    public R update(@RequestBody TicketPriceEntity ticketPrice){
        ValidatorUtils.validateEntity(ticketPrice);
        ticketPriceService.updateAllColumnById(ticketPrice);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ticketPrice:ticketprice:delete")
    public R delete(@RequestBody Integer[] ids){
        ticketPriceService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
