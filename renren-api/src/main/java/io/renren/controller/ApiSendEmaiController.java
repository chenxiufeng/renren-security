package io.renren.controller;

import io.renren.common.utils.R;
import io.renren.util.SendEmailCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author llc
 * @Description 小程序发送邮件接口
 * @Date 2019/3/22 11:18
 */
@RestController
@RequestMapping("/api")
@Api(tags="发送邮件接口")
public class ApiSendEmaiController {

    @GetMapping("sendEmai/{email}")
    @ApiOperation("发送邮件")
    public R sendEmai(@PathVariable String email){
        // 您要发送给谁，标题，内容
        SendEmailCode.send(email, "入职通知", "入职通知", "smtp", "smtp.163.com", "18086417780@163.com", "25", "18086417780", "llc123456");
        return R.ok();
    }

}
