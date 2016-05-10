<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript">
$.extend($.fn.datagrid.methods, {
	//键盘上下键换行
    keyCtr : function (jq) {
        return jq.each(function () {
            var grid = $(this);
            grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function (e) {
            	var rows = grid.datagrid('getRows');
                var selected = grid.datagrid('getSelected');
                var index = grid.datagrid('getRowIndex', selected);
                switch (e.keyCode) {
                case 38: // up
                    if (index>0) {
                        grid.datagrid('selectRow', index - 1);
                    } else {
                        grid.datagrid('selectRow', rows.length - 1);
                    }
                    break;
                case 40: // down
                    if (index<rows.length - 1) {
                        grid.datagrid('selectRow', index + 1);
                    } else {
                        grid.datagrid('selectRow', 0);
                    }
                    break;
                }
            });
        });
    }
});

	$(function() {
		//原下拉菜单数据，用于转换显示列的内容
		var depts = util.getList(global_param.context_name + "/basInfo/basDepartmentInfo/getList");
		
		/*准备显示数据*/
		$("#stock_dg").datagrid({
			method : 'get',
			url : global_param.context_name + "/stock/stockInfo/lists", // 数据来源
			title : '库存信息', // 标题
			sortName : 'stock_info_id', // 排序的列
			sortOrder : 'asc', // 排序方式
			fitcolumns : true,
			singleSelect : true,//单选
			pagination : false,
			rownumbers : true,
			queryParams: {
				stock_storage: "${dept_id}",
				product_id: '${product_id}',
			},
			columns:[[{
				field : 'product_id',
				title : '产品序号',
				width : 80,
				sortable : true
			}, {
				field : 'stock_batchCode',
				title : '批号',
				width : 80,
				sortable : true
			}, {
				field : 'stock_storageNumber',
				title : '库房数量',
				width : 80,
				sortable : true,
			},{
				field : 'stock_storage',
				title : '库房',
				width : 100,
				sortable : true,
				formatter: function(value,row,index){
					for(var i=0;i<depts.length;i++){
						if(depts[i].department_id == value){
							return depts[i].department_fullname;
						}
					}
				}
			}, {
				field : 'stock_invalidDate',
				title : '失效期',
				width : 150,
				sortable : true,
				formatter: function(value,row,index){
					return util.formatDate(value);
				}
			}, {
				field : 'stock_intakeTicket',
				title : '入库票号',
				width : 150,
				sortable : true
			}, {
				field : 'stock_intakeSmallNumber',
				title : '入库小号',
				width : 80,
				sortable : true
			}]],
			toolbar : [ {
				id: 'chooseStock',
				text : '选择',
				iconCls : 'icon-add',
				handler : chooseStock
			}]
		}).datagrid("keyCtr");
		
		$("#chooseStock").focus();
	});
	function chooseStock(){
		var node = $('#stock_dg').datagrid('getSelected');
		//alert(JSON.stringify(node));
		if(node!=null&&node!=""){
			window.parent['${callback}'](node);
			parent.$('#stockBatchChoose').window('close');
		}
	}
</script>	
</head>        
<body>
	<!-- 显示区域 -->
	<div id="stockData" data-options="region:'center',border:false" style="overflow: hidden;width: 100%;">
		<table id="stock_dg"></table>
	</div>
</body>
</html>