package io.renren.common.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 支付宝基础配置类 ClassName: AlipayConfig
 * 
 * @Description: TODO
 * @author llc
 * @date
 */
@Getter
@Setter
@Configuration
@PropertySource("classpath:pay/pay_config.properties")
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {

	// 接收通知的接口名
	private String service;

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	private String appId;

	private String partner;

	private String sellerId;

	// 签名方式
	private String signType;

	// 字符编码格式 目前支持 gbk 或 utf-8
	private String inputCharset;

	// 商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	private String privateKey;

	// 支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	private String alipayPublicKey;

	@Bean
	public AlipayClient getAlipayClient() {
		AlipayClient alipayClient = new DefaultAlipayClient(this.service, this.appId, this.privateKey, "json", "utf-8",
				this.alipayPublicKey, this.signType);
		return alipayClient;
	}

}
