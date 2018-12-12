package io.renren.modules.ticket.controller;

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

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import io.renren.modules.ticket.entity.TicketEntity;
import io.renren.modules.ticket.service.TicketService;
import io.renren.common.e.DeleteEnum;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author llc
 * @email 787254039@gmail.com
 * @date 2018-11-23 15:41:40
 */
@RestController
@RequestMapping("ticket/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ticket:ticket:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ticketService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ticket:ticket:info")
    public R info(@PathVariable("id") Integer id){
        TicketEntity ticket = ticketService.selectById(id);

        return R.ok().put("ticket", ticket);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ticket:ticket:save")
    public R save(@RequestBody TicketEntity ticket){
    	//先判断是否已经存在
    	String code = ticket.getCode();
    	int selectCount = ticketService.selectCount(new EntityWrapper<TicketEntity>().eq("code", code).eq("del_flag", DeleteEnum.NOT_DELETE.toInt()));
    	if(selectCount!=0){
    		return R.error("已存在！");
    	}
    	ticketService.insert(ticket);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ticket:ticket:update")
    public R update(@RequestBody TicketEntity ticket){
        ValidatorUtils.validateEntity(ticket);
        ticketService.updateAllColumnById(ticket);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ticket:ticket:delete")
    public R delete(@RequestBody Integer[] ids){
        ticketService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
