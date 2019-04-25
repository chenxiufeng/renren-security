package io.renren.modules.orderInfo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * erp订单信息表
 * 
 * @author llc
 * @email 787254039@qq.com
 * @date 2019-04-04 15:22:39
 */
@TableName("order_info")
public class OrderInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单号
	 */
	@TableId(type = IdType.INPUT)
	private Long orderNo;
	/**
	 * 会员ID
	 */
	private Integer memberId;
	/**
	 * 会员名
	 */
	private String memberName;
	/**
	 * 付款状态:0.未支付;1.已支付;
	 */
	private Integer payStatus;
	/**
	 * 商品总值
	 */
	private BigDecimal totalAmount;
	/**
	 * 调价
	 */
	private BigDecimal discount;
	/**
	 * 商品促销优惠
	 */
	private BigDecimal pmtGoods;
	/**
	 * 订单应付金额
	 */
	private BigDecimal payableAmount;
	/**
	 * 订单已支付金额
	 */
	private BigDecimal payed;
	/**
	 * 下单时间
	 */
	private Date orderCreatetime;
	/**
	 * 收货时间
	 */
	private Date receivedTime;
	/**
	 * 支付时间
	 */
	private Date paymentTime;
	/**
	 * 支付方式
	 */
	private String payment;
	/**
	 * 支付单号
	 */
	private String paymentId;
	/**
	 * 伪删除标记（0正常 1删除）
	 */
	private Integer del;

	/**
	 * 设置：订单号
	 */
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单号
	 */
	public Long getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：会员ID
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：会员ID
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * 设置：会员名
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	/**
	 * 获取：会员名
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * 设置：付款状态:0.未支付;1.已支付;
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * 获取：付款状态:0.未支付;1.已支付;
	 */
	public Integer getPayStatus() {
		return payStatus;
	}
	/**
	 * 设置：商品总值
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 获取：商品总值
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 设置：调价
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	/**
	 * 获取：调价
	 */
	public BigDecimal getDiscount() {
		return discount;
	}
	/**
	 * 设置：商品促销优惠
	 */
	public void setPmtGoods(BigDecimal pmtGoods) {
		this.pmtGoods = pmtGoods;
	}
	/**
	 * 获取：商品促销优惠
	 */
	public BigDecimal getPmtGoods() {
		return pmtGoods;
	}
	/**
	 * 设置：订单应付金额
	 */
	public void setPayableAmount(BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}
	/**
	 * 获取：订单应付金额
	 */
	public BigDecimal getPayableAmount() {
		return payableAmount;
	}
	/**
	 * 设置：订单已支付金额
	 */
	public void setPayed(BigDecimal payed) {
		this.payed = payed;
	}
	/**
	 * 获取：订单已支付金额
	 */
	public BigDecimal getPayed() {
		return payed;
	}
	/**
	 * 设置：下单时间
	 */
	public void setOrderCreatetime(Date orderCreatetime) {
		this.orderCreatetime = orderCreatetime;
	}
	/**
	 * 获取：下单时间
	 */
	public Date getOrderCreatetime() {
		return orderCreatetime;
	}
	/**
	 * 设置：收货时间
	 */
	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}
	/**
	 * 获取：收货时间
	 */
	public Date getReceivedTime() {
		return receivedTime;
	}
	/**
	 * 设置：支付时间
	 */
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	/**
	 * 获取：支付时间
	 */
	public Date getPaymentTime() {
		return paymentTime;
	}
	/**
	 * 设置：支付方式
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}
	/**
	 * 获取：支付方式
	 */
	public String getPayment() {
		return payment;
	}
	/**
	 * 设置：支付单号
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	/**
	 * 获取：支付单号
	 */
	public String getPaymentId() {
		return paymentId;
	}
	/**
	 * 设置：伪删除标记（0正常 1删除）
	 */
	public void setDel(Integer del) {
		this.del = del;
	}
	/**
	 * 获取：伪删除标记（0正常 1删除）
	 */
	public Integer getDel() {
		return del;
	}
}
