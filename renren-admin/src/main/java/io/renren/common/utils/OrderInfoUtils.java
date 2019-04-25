package io.renren.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author llc
 * @Description
 * @Date 2019/4/24 14:13
 */
public class OrderInfoUtils {

    private OrderInfoUtils(){}



    public   static String getOrderNo(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date())+(int)(Math.random() * 1000);
    }
}
