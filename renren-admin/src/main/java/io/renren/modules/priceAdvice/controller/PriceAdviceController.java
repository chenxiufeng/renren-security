package io.renren.modules.priceAdvice.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.support.json.JSONUtils;
import io.renren.common.utils.FileUtil;
import io.renren.common.utils.JsonUtils;
import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.priceAdvice.entity.PriceAdviceEntity;
import io.renren.modules.priceAdvice.service.PriceAdviceService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author llc
 * @email 787254039@gmail.com
 * @date 2018-12-12 15:14:20
 */
@RestController
@RequestMapping("priceAdvice/priceadvice")
public class PriceAdviceController {
    @Autowired
    private PriceAdviceService priceAdviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("priceAdvice:priceadvice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = priceAdviceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("priceAdvice:priceadvice:info")
    public R info(@PathVariable("id") Integer id){
        PriceAdviceEntity priceAdvice = priceAdviceService.selectById(id);

        return R.ok().put("priceAdvice", priceAdvice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("priceAdvice:priceadvice:save")
    public R save(@RequestBody PriceAdviceEntity priceAdvice){
            priceAdviceService.insert(priceAdvice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("priceAdvice:priceadvice:update")
    public R update(@RequestBody PriceAdviceEntity priceAdvice){
        ValidatorUtils.validateEntity(priceAdvice);
        priceAdviceService.updateAllColumnById(priceAdvice);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("priceAdvice:priceadvice:delete")
    public R delete(@RequestBody Integer[] ids){
        priceAdviceService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * excel 导出
     */
    @RequestMapping("/downLoad")
    public void downLoad(HttpServletRequest request, HttpServletResponse response) {
        try {
        String data = request.getParameter("postData");
        Map<String, Object> params =new HashMap<>();
        params = JsonUtils.json2map(data);
        // 查询列表数据
        List<PriceAdviceEntity> list = priceAdviceService.exportExcel(params);
        FileUtil.exportExcel(list, "价格明细表", "价格明细表", PriceAdviceEntity.class, "价格.xls", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
