package io.renren.api.ticketPrice.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.ticketPrice.entity.TicketPriceEntity;
import io.renren.modules.ticketPrice.service.TicketPriceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author llc
 * @email 787254039@gmail.com
 * @date 2018-11-23 15:22:50
 */
@RestController
@RequestMapping("api/ticketPrice/ticketprice")
public class TicketPriceControllerApi {
    @Autowired
    private TicketPriceService ticketPriceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("limit","25");
        params.put("page","1");
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
