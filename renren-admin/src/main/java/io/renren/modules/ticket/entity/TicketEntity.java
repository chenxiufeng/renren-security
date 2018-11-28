package io.renren.modules.ticket.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author llc
 * @email 787254039@gmail.com
 * @date 2018-11-23 15:41:40
 */
@TableName("ticket")
public class TicketEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增Id
	 */
	@TableId
	private Integer id;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 0：正常，1：删除
	 */
	private Boolean delFlag;

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
	 * 设置：编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：编号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：0：正常，1：删除
	 */
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：0：正常，1：删除
	 */
	public Boolean getDelFlag() {
		return delFlag;
	}
}
