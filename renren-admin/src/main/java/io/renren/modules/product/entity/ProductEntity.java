package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品表
 * 
 * @author llc
 * @email 787254039@qq.com
 * @date 2019-04-12 17:23:45
 */
@TableName("product")
public class ProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品条码
	 */
	@TableId
	private String skuId;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品图片url
	 */
	private String goodsImgUrl;
	/**
	 * 商品详请
	 */
	private String detailedinfo;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 0表示未删除，1删除
	 */
	private Boolean del;

	/**
	 * 设置：商品条码
	 */
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	/**
	 * 获取：商品条码
	 */
	public String getSkuId() {
		return skuId;
	}
	/**
	 * 设置：商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：商品名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：商品图片url
	 */
	public void setGoodsImgUrl(String goodsImgUrl) {
		this.goodsImgUrl = goodsImgUrl;
	}
	/**
	 * 获取：商品图片url
	 */
	public String getGoodsImgUrl() {
		return goodsImgUrl;
	}
	/**
	 * 设置：商品详请
	 */
	public void setDetailedinfo(String detailedinfo) {
		this.detailedinfo = detailedinfo;
	}
	/**
	 * 获取：商品详请
	 */
	public String getDetailedinfo() {
		return detailedinfo;
	}
	/**
	 * 设置：单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：0表示未删除，1删除
	 */
	public void setDel(Boolean del) {
		this.del = del;
	}
	/**
	 * 获取：0表示未删除，1删除
	 */
	public Boolean getDel() {
		return del;
	}
}
