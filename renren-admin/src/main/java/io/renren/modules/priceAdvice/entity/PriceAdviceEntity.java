package io.renren.modules.priceAdvice.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * @date 2018-12-12 15:14:20
 */
@TableName("price_advice")
public class PriceAdviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增id
	 */
	@TableId

	private Integer id;
	/**
	 * 名称
	 */
	@Excel(name = "名称", orderNum = "1")
	private String name;

	/**
	 * 代码
	 */
	@Excel(name = "代码", orderNum = "2")
	private String code;
	/**
	 * 推荐价
	 */
	@Excel(name = "推荐价", orderNum = "3")
	private BigDecimal advicePrice;
	/**
	 * 当前价
	 */
	@Excel(name = "当前价", orderNum = "4")
	private BigDecimal currentPrice;

	/**
	 * 设置：自增id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：自增id
	 */
	public Integer getId() {
		return id;
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
	 * 设置：推荐价
	 */
	public void setAdvicePrice(BigDecimal advicePrice) {
		this.advicePrice = advicePrice;
	}
	/**
	 * 获取：推荐价
	 */
	public BigDecimal getAdvicePrice() {
		return advicePrice;
	}
	/**
	 * 设置：当前价
	 */
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	/**
	 * 获取：当前价
	 */
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
