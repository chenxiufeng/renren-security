package io.renren.modules.orderItem.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单详情表
 * 
 * @author llc
 * @email 787254039@qq.com
 * @date 2019-04-04 16:07:03
 */
@TableName("order_item")
public class OrderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单明细ID
	 */
	@TableId
	private Long id;
	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 货品ID
	 */
	private Long skuId;
	/**
	 * 商品图片地址
	 */
	private String goodsImgUrl;
	/**
	 * 分摊后价格
	 */
	private BigDecimal price;
	/**
	 * 明细商品购买数量(购买价格方式--数量)
	 */
	private String quantity;
	/**
	 * 商品详细信息
	 */
	private String detailedInfo;
	/**
	 * 商品单位
	 */
	private String unit;
	/**
	 * 商品应付金额
	 */
	private BigDecimal payAmount;
	/**
	 * 商品总金额
	 */
	private BigDecimal amount;
	/**
	 * 是否删除（true：是；false：否）
	 */
	private Boolean deleted;
	/**
	 * 删除时间
	 */
	private Date deleteTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 设置：订单明细ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：订单明细ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：订单ID
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单ID
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * 设置：货品ID
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	/**
	 * 获取：货品ID
	 */
	public Long getSkuId() {
		return skuId;
	}
	/**
	 * 设置：商品图片地址
	 */
	public void setGoodsImgUrl(String goodsImgUrl) {
		this.goodsImgUrl = goodsImgUrl;
	}
	/**
	 * 获取：商品图片地址
	 */
	public String getGoodsImgUrl() {
		return goodsImgUrl;
	}
	/**
	 * 设置：分摊后价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：分摊后价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：明细商品购买数量(购买价格方式--数量)
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	/**
	 * 获取：明细商品购买数量(购买价格方式--数量)
	 */
	public String getQuantity() {
		return quantity;
	}
	/**
	 * 设置：商品详细信息
	 */
	public void setDetailedInfo(String detailedInfo) {
		this.detailedInfo = detailedInfo;
	}
	/**
	 * 获取：商品详细信息
	 */
	public String getDetailedInfo() {
		return detailedInfo;
	}
	/**
	 * 设置：商品单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：商品单位
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * 设置：商品应付金额
	 */
	public void setPayAmount(BigDecimal payamount) {
		this.payAmount = payamount;
	}
	/**
	 * 获取：商品应付金额
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	/**
	 * 设置：商品总金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：商品总金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：是否删除（true：是；false：否）
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否删除（true：是；false：否）
	 */
	public Boolean getDeleted() {
		return deleted;
	}
	/**
	 * 设置：删除时间
	 */
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	/**
	 * 获取：删除时间
	 */
	public Date getDeleteTime() {
		return deleteTime;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
