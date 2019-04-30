package io.renren.modules.pay.service;

import io.renren.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public interface AlipayService {
    R tradeQuery(String tradeNo);

    R qrCodePay(BigDecimal pay);

    Boolean dealBusiness(HttpServletRequest request);
}
