$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'priceAdvice/priceadvice/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
            { label: '代码', name: 'code', index: 'code', width: 50},
			{ label: '名称', name: 'name', index: 'name', width: 80,
                formatter: function (value, options, row) {
                    return '<a href="javascript:;" onclick="return vm.seeDetail(\''+ row.code +'\',\''+ row.name+'\');">'+ row.name +'</a></li>';
                }
			},
			{ label: '推荐价', name: 'advicePrice', index: 'advice_price', width: 80 }, 			
			{ label: '当前价', name: 'currentPrice', index: 'current_price', width: 80 },
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
        detailVisible:false,
		priceAdvice: {id:'',code:'',name:'',advicePrice:'',currentPrice:''},
        formLabelWidth: '120px',
		q:{
			name:'',
			code:'',
		},
		//推荐价集合
        adviceList:[],
        ticketPriceList:[],
        dateList:[],
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
		reset:function(){
			vm.q.name='';
			vm.q.code='';
			vm.reload();
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
                page:page,
                postData:{
                    'name': vm.q.name,
					'code':vm.q.code,
                }
            }).trigger("reloadGrid");
		},

        downLoad:function () {
            //导出excel
            const data = encodeURIComponent(JSON.stringify(vm.q));
            window.location.href = baseURL + "priceAdvice/priceadvice/downLoad?postData=" + data;
        },
		//查看详情
        seeDetail:function (code,name) {
            $.get(baseURL + "priceAdvice/priceadvice/seeDetail/"+code, function(r){
            	if(r.code==0){
                    vm.adviceList=r.adviceList,
					vm.ticketPriceList=r.ticketPriceList,
					vm.dateList=r.dateList;
                     vm.detailVisible=true;
                    Vue.nextTick(function(){
                        vm.initEcharts(code,name);
                    })
				}else{
            		alert(r.msg);
				}
            });
        },
		//初始化echarts实例
        initEcharts:function (code,name) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            var option = {
                title: {
                    text: name,
                    subtext: code
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:['历史价','推荐价']
                },
                toolbox: {
                    show: true,
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis:  {
                    type: 'category',
                    boundaryGap: false,
                    data: vm.dateList,
                },
                yAxis: {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} 元'
                    }
                },
                series: [
                    {
                        name:'历史价',
                        type:'line',
                        data:vm.ticketPriceList,
                    },
                    {
                        name:'推荐价',
                        type:'line',
                        data:vm.adviceList,
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
	}
});