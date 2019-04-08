var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		orderInfo: {},
        //查询参数
		q: {
			orderNo: '',
			memberName: '',
			payment:'',
            startTime: '',
            endTime: '',
            createTime:null,
		},
		//订单信息
        orderData:[],
		//分页参数
        page: {currentPage: 0, pageSizes: [20, 50, 100], pageSize: 20, total: 0, ascending: [], descending: []},
	},
	//页面渲染完成后执行
    mounted: function () {
        this.query();
    },
	methods: {
		query: function () {
            //查询参数
            var queryParam = this.q;
            // 录入时间
            var createTime = queryParam.createTime;
            if (!isBlank(createTime)){
                queryParam.startTime = createTime[0] + ' 00:00:00';
                queryParam.endTime = createTime[1] + ' 23:59:59';
            }
            // 分页参数
            queryParam.page = this.page.currentPage;
            queryParam.limit = this.page.pageSize;
            //ajax 请求
            var self= this;
            $.ajax({
                type: "POST",
                url: baseURL + "orderInfo/orderinfo/list",
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(queryParam),
                success: function (r) {
                    if (r.code == 0) {
                        self.page.total = r.page.totalCount;
                        self.orderData = r.page.list;
                        self.totalCount=r.totalCount;
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        reset:function(){
		    vm.page={currentPage: 0, pageSizes: [20, 50, 100], pageSize: 20, total: 0, ascending: [], descending: []};
		    vm.q={orderNo: '',
                memberName: '',
                payment:'',
                startTime: '',
                endTime: '',
                createTime:null};
		    this.query();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.orderInfo = {};
		},
		update: function (event) {
			var orderNo = getSelectedRow();
			if(orderNo == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(orderNo)
		},
		saveOrUpdate: function (event) {
			var url = vm.orderInfo.orderNo == null ? "orderInfo/orderinfo/save" : "orderInfo/orderinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.orderInfo),
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
			var orderNos = getSelectedRows();
			if(orderNos == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "orderInfo/orderinfo/delete",
                    contentType: "application/json",
				    data: JSON.stringify(orderNos),
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
		getInfo: function(orderNo){
			$.get(baseURL + "orderInfo/orderinfo/info/"+orderNo, function(r){
                vm.orderInfo = r.orderInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
        // 页数变化
        handleSizeChange: function (val) {
            this.page.pageSize = val;
            this.reload();
        },
        //当前页变化
        handleCurrentChange: function (val) {
            this.page.currentPage = val;
            this.reload();
        },
		//筛选订单支付状态
        filterPayStatus:function (value, row) {
            return row.payStatus === value;
        }
	}
});