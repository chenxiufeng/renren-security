$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'ticketPrice/ticketprice/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '代号', name: 'code', index: 'code', width: 80 }, 			
			{ label: '名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '今日开盘价', name: 'start', index: 'start', width: 80 }, 			
			{ label: '昨日收盘价', name: 'end', index: 'end', width: 80 }, 			
			{ label: '当前价格', name: 'current', index: 'current', width: 80 }, 			
			{ label: '今日最高价', name: 'high', index: 'high', width: 80 }, 			
			{ label: '今日最低价', name: 'low', index: 'low', width: 80 }, 			
			{ label: '竞买价，即“买一”报价', name: 'quoteBuy1', index: 'quote_buy1', width: 80 }, 			
			{ label: '竞卖价，即“卖一”报价', name: 'quoteSale1', index: 'quote_sale1', width: 80 }, 			
			{ label: '成交的股票数,通常把该值除以一百', name: 'quantity', index: 'quantity', width: 80 }, 			
			{ label: '成交金额,通常把该值除以一万,万元”为成交金额的单位', name: 'money', index: 'money', width: 80 }, 			
			{ label: '“买一”申请数量', name: 'buy1Quantity', index: 'buy1_quantity', width: 80 }, 			
			{ label: '“买一”报价', name: 'buy1Price', index: 'buy1_price', width: 80 }, 			
			{ label: '“买二”申请数量', name: 'buy2Quantity', index: 'buy2_quantity', width: 80 }, 			
			{ label: '“买二”报价', name: 'buy2Price', index: 'buy2_price', width: 80 }, 			
			{ label: '“买三”申请数量', name: 'buy3Quantity', index: 'buy3_quantity', width: 80 }, 			
			{ label: '“买三”报价', name: 'buy3Price', index: 'buy3_price', width: 80 }, 			
			{ label: '“买四”申请数量', name: 'buy4Quantity', index: 'buy4_quantity', width: 80 }, 			
			{ label: '“买四”报价', name: 'buy4Price', index: 'buy4_price', width: 80 }, 			
			{ label: '“买五”申请数量', name: 'buy5Quantity', index: 'buy5_quantity', width: 80 }, 			
			{ label: '“买五”报价', name: 'buy5Price', index: 'buy5_price', width: 80 }, 			
			{ label: '“卖一”申请数量', name: 'sale1Quantity', index: 'sale1_quantity', width: 80 }, 			
			{ label: '“卖一”报价', name: 'sale1Price', index: 'sale1_price', width: 80 }, 			
			{ label: '“卖二”申请数量', name: 'sale2Quantity', index: 'sale2_quantity', width: 80 }, 			
			{ label: '“卖二”报价', name: 'sale2Price', index: 'sale2_price', width: 80 }, 			
			{ label: '“卖三”申请数量', name: 'sale3Quantity', index: 'sale3_quantity', width: 80 }, 			
			{ label: '“卖三”报价', name: 'sale3Price', index: 'sale3_price', width: 80 }, 			
			{ label: '“卖四”申请数量', name: 'sale4Quantity', index: 'sale4_quantity', width: 80 }, 			
			{ label: '“卖四”报价', name: 'sale4Price', index: 'sale4_price', width: 80 }, 			
			{ label: '“卖五”申请数量', name: 'sale5Quantity', index: 'sale5_quantity', width: 80 }, 			
			{ label: '“卖五”报价', name: 'sale5Price', index: 'sale5_price', width: 80 }, 			
			{ label: '时间', name: 'time', index: 'time', width: 80 }			
        ],
		viewrecords: true,
        height: window.innerHeight - 120,
        rowNum: 25,
        rowList : [25,50,75],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		ticketPrice: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.ticketPrice = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.ticketPrice.id == null ? "ticketPrice/ticketprice/save" : "ticketPrice/ticketprice/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.ticketPrice),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "ticketPrice/ticketprice/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "ticketPrice/ticketprice/info/"+id, function(r){
                vm.ticketPrice = r.ticketPrice;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});