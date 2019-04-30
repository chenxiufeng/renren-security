var vm = new Vue({
	el:'#rrapp',
	data:{
        fileList:[],
        code:'',
        tradeNo:'',
        money:10,
	},
    methods: {
        //支付
        zhifu:function () {
            $.get(baseURL + "pay/alipay/test/" +vm.code, function (r) {
                if(r.code==0){
                }else{
                    self.$message.error(r.msg);
                }
            });
        },
        
        //查询交易结果
        queryPayResult:function () {
            var self=this;
            $.get(baseURL + "pay/alipay/tradeQuery/" +vm.tradeNo, function (r) {
                if(r.code==0){
                    alert(r.result);
                }else{
                    self.$message.error(r.msg);
                }
            });
        },

        qrCodePay:function () {
            $.get(baseURL + "pay/alipay/qrCodePay/" +vm.money, function (r) {
                if(r.code==0){
                    jQuery('#qrcode').qrcode({
                        width:120,
                        height:120,
                        text: r.qrcode,
                    });
                }else{
                    self.$message.error(r.msg);
                }
            });
        }
    }
});