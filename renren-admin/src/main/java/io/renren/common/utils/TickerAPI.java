package io.renren.common.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.renren.modules.ticketPrice.entity.TicketPriceEntity;

public class TickerAPI {
		
	public static final  String TICKETAPI="http://hq.sinajs.cn/list=";
	
	
	public  static TicketPriceEntity  getData(String code) throws ParseException{
		String url=TickerAPI.TICKETAPI+code;
		String result = HttpRequest.sendGet(url, null);
		result=result.substring(result.indexOf("\"")+1);
		result=result.replaceAll("\"","");
		String[] priceArr = result.split(",");
		if(priceArr.length<30){
			return null;
		}
		TicketPriceEntity t=new TicketPriceEntity();
		t.setCode(code);	
		t.setName(priceArr[0]);
		t.setStart(new BigDecimal(priceArr[1]));
		t.setEnd(new BigDecimal(priceArr[2]));
		t.setCurrent(new BigDecimal(priceArr[3]));
		t.setHigh(new BigDecimal(priceArr[4]));
		t.setLow(new BigDecimal(priceArr[5]));
		t.setQuoteBuy1(new BigDecimal(priceArr[6]));
		t.setQuoteSale1(new BigDecimal(priceArr[7]));
		t.setQuantity(new BigDecimal(priceArr[8]));
		t.setMoney(new BigDecimal(priceArr[9]));
		t.setBuy1Quantity(new BigDecimal(priceArr[10]));
		t.setBuy2Quantity(new BigDecimal(priceArr[12]));
		t.setBuy3Quantity(new BigDecimal(priceArr[14]));
		t.setBuy4Quantity(new BigDecimal(priceArr[16]));
		t.setBuy5Quantity(new BigDecimal(priceArr[18]));
		t.setBuy1Price(new BigDecimal(priceArr[11]));
		t.setBuy2Price(new BigDecimal(priceArr[13]));
		t.setBuy3Price(new BigDecimal(priceArr[15]));
		t.setBuy4Price(new BigDecimal(priceArr[17]));
		t.setBuy5Price(new BigDecimal(priceArr[19]));
		t.setSale1Quantity(new BigDecimal(priceArr[20]));
		t.setSale2Quantity(new BigDecimal(priceArr[22]));
		t.setSale3Quantity(new BigDecimal(priceArr[24]));
		t.setSale4Quantity(new BigDecimal(priceArr[26]));
		t.setSale5Quantity(new BigDecimal(priceArr[28]));
		t.setSale1Price(new BigDecimal(priceArr[21]));
		t.setSale2Price(new BigDecimal(priceArr[23]));
		t.setSale3Price(new BigDecimal(priceArr[25]));
		t.setSale4Price(new BigDecimal(priceArr[27]));
		t.setSale5Price(new BigDecimal(priceArr[29]));
		String date=priceArr[30]+" "+priceArr[31];
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = sdf.parse(date);
		t.setTime(parse);
		return t;
	}
}
