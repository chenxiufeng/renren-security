package io.renren.modules.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import io.renren.common.config.AlipayConfig;
import io.renren.common.utils.R;
import io.renren.modules.pay.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Author llc
 * @Description
 * @Date 2019/4/30 9:43
 */
@RestController
@RequestMapping("pay/alipay")
public class AlipayController {

    @Autowired
    AlipayConfig alipayConfig;

    @Autowired
    private AlipayService alipayService;

    @RequestMapping("test/{code}")
    public R pay(@PathVariable("code") String code){
        AlipayClient alipayClient = alipayConfig.getAlipayClient();
        //创建API对应的request类
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradePayModel model = new AlipayTradePayModel();
        request.setBizModel(model);

        model.setOutTradeNo(System.currentTimeMillis()+"");
        model.setSubject("Iphone6 16G");
        model.setTotalAmount("0.01");
        model.setAuthCode(code);//沙箱钱包中的付款码
        model.setScene("bar_code");

        AlipayTradePayResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.println(response.getBody());
            System.out.println(response.getTradeNo());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return R.ok();
    }


    //根据自定义单号查询
    @RequestMapping("tradeQuery/{tradeNo}")
    public R tradeQuery(@PathVariable("tradeNo") String tradeNo){
        return alipayService.tradeQuery(tradeNo);
    }

    //根据自定义单号查询
    @RequestMapping("qrCodePay/{money}")
    public R qrCodePay(@PathVariable("money") String money){
        BigDecimal pay=new BigDecimal(money);
        return alipayService.qrCodePay(pay);
    }

}
