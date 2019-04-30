package io.renren.api.priceAdvice.controller;

import io.renren.common.utils.FileUtil;
import io.renren.common.utils.JsonUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.priceAdvice.entity.PriceAdviceEntity;
import io.renren.modules.priceAdvice.service.PriceAdviceService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author llc
 * @email 787254039@gmail.com
 * @date 2018-12-12 15:14:20
 */
@RestController
@RequestMapping("api/priceAdvice/priceadvice")
public class PriceAdviceControllerApi {
    @Autowired
    private PriceAdviceService priceAdviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("limit","25");
        params.put("page","1");
        PageUtils page = priceAdviceService.queryPage(params);
        return R.ok().put("page", page);
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

    //查看阶梯价详情
    @RequestMapping("/seeDetail/{code}")
    public R seeDetail(@PathVariable("code") String code){
        if(StringUtils.isBlank(code)){
            return R.error("查询信息不存在!");
        }
        return  priceAdviceService.seeDetail(code);
    }
}
