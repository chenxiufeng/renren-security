package io.renren.api.alipay;

import io.renren.modules.pay.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author llc
 * @Description
 * @Date 2019/4/30 15:07
 */
@RestController
@RequestMapping("api/alipay")
public class AlipayControllerApi {
    @Autowired
    AlipayService alipayService;

    //支付宝回调
    @RequestMapping("alipayNotify")
    public void alipayNotify(HttpServletRequest request,HttpServletResponse response){
    Boolean flag=alipayService.dealBusiness(request);
        if (flag) {
            try {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("success");//返回success通知支付宝业务处理完成
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
