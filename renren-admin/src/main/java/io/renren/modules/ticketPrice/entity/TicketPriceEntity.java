package io.renren.modules.ticketPrice.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author llc
 * @email 787254039@gmail.com
 * @date 2018-11-23 15:22:50
 */
@TableName("ticket_price")
public class TicketPriceEntity implements Serializable {

	/**
	 * 自增Id
	 */
	@TableId
	private Integer id;
	/**
	 * 代号
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 今日开盘价
	 */
	private BigDecimal start;
	/**
	 * 昨日收盘价
	 */
	private BigDecimal end;
	/**
	 * 当前价格
	 */
	private BigDecimal current;
	/**
	 * 今日最高价
	 */
	private BigDecimal high;
	/**
	 * 今日最低价
	 */
	private BigDecimal low;
	/**
	 * 竞买价，即“买一”报价
	 */
	private BigDecimal quoteBuy1;
	/**
	 * 竞卖价，即“卖一”报价
	 */
	private BigDecimal quoteSale1;
	/**
	 * 成交的股票数,通常把该值除以一百
	 */
	private BigDecimal quantity;
	/**
	 * 成交金额,通常把该值除以一万,万元”为成交金额的单位
	 */
	private BigDecimal money;
	/**
	 * “买一”申请数量
	 */
	private BigDecimal buy1Quantity;
	/**
	 * “买一”报价
	 */
	private BigDecimal buy1Price;
	/**
	 * “买二”申请数量
	 */
	private BigDecimal buy2Quantity;
	/**
	 * “买二”报价
	 */
	private BigDecimal buy2Price;
	/**
	 * “买三”申请数量
	 */
	private BigDecimal buy3Quantity;
	/**
	 * “买三”报价
	 */
	private BigDecimal buy3Price;
	/**
	 * “买四”申请数量
	 */
	private BigDecimal buy4Quantity;
	/**
	 * “买四”报价
	 */
	private BigDecimal buy4Price;
	/**
	 * “买五”申请数量
	 */
	private BigDecimal buy5Quantity;
	/**
	 * “买五”报价
	 */
	private BigDecimal buy5Price;
	/**
	 * “卖一”申请数量
	 */
	private BigDecimal sale1Quantity;
	/**
	 * “卖一”报价
	 */
	private BigDecimal sale1Price;
	/**
	 * “卖二”申请数量
	 */
	private BigDecimal sale2Quantity;
	/**
	 * “卖二”报价
	 */
	private BigDecimal sale2Price;
	/**
	 * “卖三”申请数量
	 */
	private BigDecimal sale3Quantity;
	/**
	 * “卖三”报价
	 */
	private BigDecimal sale3Price;
	/**
	 * “卖四”申请数量
	 */
	private BigDecimal sale4Quantity;
	/**
	 * “卖四”报价
	 */
	private BigDecimal sale4Price;
	/**
	 * “卖五”申请数量
	 */
	private BigDecimal sale5Quantity;
	/**
	 * “卖五”报价
	 */
	private BigDecimal sale5Price;
	/**
	 * 时间
	 */
	private Date time;

	/**
	 * 设置：自增Id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：自增Id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：代号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：代号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：今日开盘价
	 */
	public void setStart(BigDecimal start) {
		this.start = start;
	}
	/**
	 * 获取：今日开盘价
	 */
	public BigDecimal getStart() {
		return start;
	}
	/**
	 * 设置：昨日收盘价
	 */
	public void setEnd(BigDecimal end) {
		this.end = end;
	}
	/**
	 * 获取：昨日收盘价
	 */
	public BigDecimal getEnd() {
		return end;
	}
	/**
	 * 设置：当前价格
	 */
	public void setCurrent(BigDecimal current) {
		this.current = current;
	}
	/**
	 * 获取：当前价格
	 */
	public BigDecimal getCurrent() {
		return current;
	}
	/**
	 * 设置：今日最高价
	 */
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	/**
	 * 获取：今日最高价
	 */
	public BigDecimal getHigh() {
		return high;
	}
	/**
	 * 设置：今日最低价
	 */
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	/**
	 * 获取：今日最低价
	 */
	public BigDecimal getLow() {
		return low;
	}
	/**
	 * 设置：竞买价，即“买一”报价
	 */
	public void setQuoteBuy1(BigDecimal quoteBuy1) {
		this.quoteBuy1 = quoteBuy1;
	}
	/**
	 * 获取：竞买价，即“买一”报价
	 */
	public BigDecimal getQuoteBuy1() {
		return quoteBuy1;
	}
	/**
	 * 设置：竞卖价，即“卖一”报价
	 */
	public void setQuoteSale1(BigDecimal quoteSale1) {
		this.quoteSale1 = quoteSale1;
	}
	/**
	 * 获取：竞卖价，即“卖一”报价
	 */
	public BigDecimal getQuoteSale1() {
		return quoteSale1;
	}
	/**
	 * 设置：成交的股票数,通常把该值除以一百
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	/**
	 * 获取：成交的股票数,通常把该值除以一百
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}
	/**
	 * 设置：成交金额,通常把该值除以一万,万元”为成交金额的单位
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：成交金额,通常把该值除以一万,万元”为成交金额的单位
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置：“买一”申请数量
	 */
	public void setBuy1Quantity(BigDecimal buy1Quantity) {
		this.buy1Quantity = buy1Quantity;
	}
	/**
	 * 获取：“买一”申请数量
	 */
	public BigDecimal getBuy1Quantity() {
		return buy1Quantity;
	}
	/**
	 * 设置：“买一”报价
	 */
	public void setBuy1Price(BigDecimal buy1Price) {
		this.buy1Price = buy1Price;
	}
	/**
	 * 获取：“买一”报价
	 */
	public BigDecimal getBuy1Price() {
		return buy1Price;
	}
	/**
	 * 设置：“买二”申请数量
	 */
	public void setBuy2Quantity(BigDecimal buy2Quantity) {
		this.buy2Quantity = buy2Quantity;
	}
	/**
	 * 获取：“买二”申请数量
	 */
	public BigDecimal getBuy2Quantity() {
		return buy2Quantity;
	}
	/**
	 * 设置：“买二”报价
	 */
	public void setBuy2Price(BigDecimal buy2Price) {
		this.buy2Price = buy2Price;
	}
	/**
	 * 获取：“买二”报价
	 */
	public BigDecimal getBuy2Price() {
		return buy2Price;
	}
	/**
	 * 设置：“买三”申请数量
	 */
	public void setBuy3Quantity(BigDecimal buy3Quantity) {
		this.buy3Quantity = buy3Quantity;
	}
	/**
	 * 获取：“买三”申请数量
	 */
	public BigDecimal getBuy3Quantity() {
		return buy3Quantity;
	}
	/**
	 * 设置：“买三”报价
	 */
	public void setBuy3Price(BigDecimal buy3Price) {
		this.buy3Price = buy3Price;
	}
	/**
	 * 获取：“买三”报价
	 */
	public BigDecimal getBuy3Price() {
		return buy3Price;
	}
	/**
	 * 设置：“买四”申请数量
	 */
	public void setBuy4Quantity(BigDecimal buy4Quantity) {
		this.buy4Quantity = buy4Quantity;
	}
	/**
	 * 获取：“买四”申请数量
	 */
	public BigDecimal getBuy4Quantity() {
		return buy4Quantity;
	}
	/**
	 * 设置：“买四”报价
	 */
	public void setBuy4Price(BigDecimal buy4Price) {
		this.buy4Price = buy4Price;
	}
	/**
	 * 获取：“买四”报价
	 */
	public BigDecimal getBuy4Price() {
		return buy4Price;
	}
	/**
	 * 设置：“买五”申请数量
	 */
	public void setBuy5Quantity(BigDecimal buy5Quantity) {
		this.buy5Quantity = buy5Quantity;
	}
	/**
	 * 获取：“买五”申请数量
	 */
	public BigDecimal getBuy5Quantity() {
		return buy5Quantity;
	}
	/**
	 * 设置：“买五”报价
	 */
	public void setBuy5Price(BigDecimal buy5Price) {
		this.buy5Price = buy5Price;
	}
	/**
	 * 获取：“买五”报价
	 */
	public BigDecimal getBuy5Price() {
		return buy5Price;
	}
	/**
	 * 设置：“卖一”申请数量
	 */
	public void setSale1Quantity(BigDecimal sale1Quantity) {
		this.sale1Quantity = sale1Quantity;
	}
	/**
	 * 获取：“卖一”申请数量
	 */
	public BigDecimal getSale1Quantity() {
		return sale1Quantity;
	}
	/**
	 * 设置：“卖一”报价
	 */
	public void setSale1Price(BigDecimal sale1Price) {
		this.sale1Price = sale1Price;
	}
	/**
	 * 获取：“卖一”报价
	 */
	public BigDecimal getSale1Price() {
		return sale1Price;
	}
	/**
	 * 设置：“卖二”申请数量
	 */
	public void setSale2Quantity(BigDecimal sale2Quantity) {
		this.sale2Quantity = sale2Quantity;
	}
	/**
	 * 获取：“卖二”申请数量
	 */
	public BigDecimal getSale2Quantity() {
		return sale2Quantity;
	}
	/**
	 * 设置：“卖二”报价
	 */
	public void setSale2Price(BigDecimal sale2Price) {
		this.sale2Price = sale2Price;
	}
	/**
	 * 获取：“卖二”报价
	 */
	public BigDecimal getSale2Price() {
		return sale2Price;
	}
	/**
	 * 设置：“卖三”申请数量
	 */
	public void setSale3Quantity(BigDecimal sale3Quantity) {
		this.sale3Quantity = sale3Quantity;
	}
	/**
	 * 获取：“卖三”申请数量
	 */
	public BigDecimal getSale3Quantity() {
		return sale3Quantity;
	}
	/**
	 * 设置：“卖三”报价
	 */
	public void setSale3Price(BigDecimal sale3Price) {
		this.sale3Price = sale3Price;
	}
	/**
	 * 获取：“卖三”报价
	 */
	public BigDecimal getSale3Price() {
		return sale3Price;
	}
	/**
	 * 设置：“卖四”申请数量
	 */
	public void setSale4Quantity(BigDecimal sale4Quantity) {
		this.sale4Quantity = sale4Quantity;
	}
	/**
	 * 获取：“卖四”申请数量
	 */
	public BigDecimal getSale4Quantity() {
		return sale4Quantity;
	}
	/**
	 * 设置：“卖四”报价
	 */
	public void setSale4Price(BigDecimal sale4Price) {
		this.sale4Price = sale4Price;
	}
	/**
	 * 获取：“卖四”报价
	 */
	public BigDecimal getSale4Price() {
		return sale4Price;
	}
	/**
	 * 设置：“卖五”申请数量
	 */
	public void setSale5Quantity(BigDecimal sale5Quantity) {
		this.sale5Quantity = sale5Quantity;
	}
	/**
	 * 获取：“卖五”申请数量
	 */
	public BigDecimal getSale5Quantity() {
		return sale5Quantity;
	}
	/**
	 * 设置：“卖五”报价
	 */
	public void setSale5Price(BigDecimal sale5Price) {
		this.sale5Price = sale5Price;
	}
	/**
	 * 获取：“卖五”报价
	 */
	public BigDecimal getSale5Price() {
		return sale5Price;
	}
	/**
	 * 设置：时间
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	/**
	 * 获取：时间
	 */
	public Date getTime() {
		return time;
	}
}
