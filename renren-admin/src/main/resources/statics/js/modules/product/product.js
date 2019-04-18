$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'product/product/list',
        datatype: "json",
        colModel: [			
			{ label: 'skuId', name: 'skuId', index: 'sku_id', width: 50, key: true },
			{ label: '商品名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '商品图片url', name: 'goodsImgUrl', index: 'goods_img_url', width: 80 }, 			
			{ label: '商品详请', name: 'detailedinfo', index: 'detailedInfo', width: 80 }, 			
			{ label: '单价', name: 'price', index: 'price', width: 80 }, 			
			{ label: '0表示未删除，1删除', name: 'del', index: 'del', width: 80 }			
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
		product: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.product = {};
		},
		update: function (event) {
			var skuId = getSelectedRow();
			if(skuId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(skuId)
		},
		saveOrUpdate: function (event) {
			var url = vm.product.skuId == null ? "product/product/save" : "product/product/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.product),
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
			var skuIds = getSelectedRows();
			if(skuIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "product/product/delete",
                    contentType: "application/json",
				    data: JSON.stringify(skuIds),
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
		getInfo: function(skuId){
			$.get(baseURL + "product/product/info/"+skuId, function(r){
                vm.product = r.product;
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