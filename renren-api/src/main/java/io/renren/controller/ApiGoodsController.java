package io.renren.controller;

import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.form.LoginForm;
import io.renren.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author llc
 * @Description 小程序商品接口
 * @Date 2019/3/26 13:32
 */
@RestController
@RequestMapping("/api")
@Api(tags="商品接口")
public class ApiGoodsController {
    @Autowired
    private GoodsService goodsService;


    @PostMapping("goodsList")
    @ApiOperation("商品列表")
    public R goodsList(@RequestBody Map<String,Object> map){
       return goodsService.goodsList(map);
    }
}
