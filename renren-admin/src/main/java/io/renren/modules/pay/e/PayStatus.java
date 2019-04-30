package io.renren.modules.pay.e;

/**
 *支付宝返回结果枚举
 */
public enum PayStatus {


    SUCC("交易成功"),

    FAILED("失败"),

    CANCEL("交易取消"),

    ERROR("交易错误"),

    READY("等待交易"),

    TIMEOUT("交易超时关闭");

    private String type;

    PayStatus(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
