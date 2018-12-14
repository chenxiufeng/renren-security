$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'priceAdvice/priceadvice/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
            { label: '代码', name: 'code', index: 'code', width: 50},
			{ label: '名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '推荐价', name: 'advicePrice', index: 'advice_price', width: 80 }, 			
			{ label: '当前价', name: 'currentPrice', index: 'current_price', width: 80 }			
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
            //循环判断是否为加急，加急添加背景色
            var ids = $("#jqGrid").getDataIDs();
            for(var i=0;i<ids.length;i++){
                var rowData = $("#jqGrid").getRowData(ids[i]);
                if(rowData.currentPrice<=rowData.advicePrice){//如果加急状态等于1，则背景色置绿显示
                    $('#'+ids[i]).find("td").css("background-color", "pink");
                }
            }
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		updateVisible:false,
		priceAdvice: {id:'',code:'',name:'',advicePrice:'',currentPrice:''},
        formLabelWidth: '120px'
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.priceAdvice = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
            vm.getInfo(id)
            vm.updateVisible = true;
		},
		saveOrUpdate: function (event) {
			var url = vm.priceAdvice.id == null ? "priceAdvice/priceadvice/save" : "priceAdvice/priceadvice/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.priceAdvice),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
                            vm.updateVisible=false;
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
				    url: baseURL + "priceAdvice/priceadvice/delete",
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
			$.get(baseURL + "priceAdvice/priceadvice/info/"+id, function(r){
                vm.priceAdvice = r.priceAdvice;
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