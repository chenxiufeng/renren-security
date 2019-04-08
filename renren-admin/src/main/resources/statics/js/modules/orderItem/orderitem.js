$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'orderItem/orderitem/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单ID', name: 'orderId', index: 'order_id', width: 80 }, 			
			{ label: '货品ID', name: 'skuId', index: 'sku_id', width: 80 }, 			
			{ label: '商品图片地址', name: 'goodsImgUrl', index: 'goods_img_url', width: 80 }, 			
			{ label: '分摊后价格', name: 'price', index: 'price', width: 80 }, 			
			{ label: '明细商品购买数量(购买价格方式--数量)', name: 'quantity', index: 'quantity', width: 80 }, 			
			{ label: '商品详细信息', name: 'detailedInfo', index: 'detailed_info', width: 80 }, 			
			{ label: '商品单位', name: 'unit', index: 'unit', width: 80 }, 			
			{ label: '商品应付金额', name: 'payamount', index: 'payAmount', width: 80 }, 			
			{ label: '商品总金额', name: 'amount', index: 'amount', width: 80 }, 			
			{ label: '是否删除（true：是；false：否）', name: 'deleted', index: 'deleted', width: 80 }, 			
			{ label: '删除时间', name: 'deleteTime', index: 'delete_time', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
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
		orderItem: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.orderItem = {};
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
			var url = vm.orderItem.id == null ? "orderItem/orderitem/save" : "orderItem/orderitem/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.orderItem),
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
				    url: baseURL + "orderItem/orderitem/delete",
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
			$.get(baseURL + "orderItem/orderitem/info/"+id, function(r){
                vm.orderItem = r.orderItem;
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