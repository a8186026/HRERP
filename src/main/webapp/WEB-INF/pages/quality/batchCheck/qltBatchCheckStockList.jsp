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
		/*准备显示数据*/
		$("#stock_dg").datagrid({
			method : 'get',
			url : global_param.context_name + "/quality/batchCheck/listStock?product_id="+$("#product_id").val(), // 数据来源
			title : '产品信息', // 标题
			sortName : 'product_code', // 排序的列
			sortOrder : 'asc', // 排序方式
			fitcolumns : true,
			singleSelect : true,//单选
			pagination : false,
			rownumbers : true,
			columns:[[{
				field : 'product_code',
				title : '产品编号',
				width : 80,
				sortable : true
			}, {
				field : 'product_name',
				title : '产品名称',
				width : 80,
				sortable : true
			}, {
				field : 'stock_intakeTicket',
				title : '入库票号',
				width : 80,
				sortable : true
			}, {
				field : 'stock_intakeSmallNumber',
				title : '入库小号',
				width : 80,
				sortable : true
			}, {
				field : 'stock_batchCode',
				title : '批号',
				width : 80,
				sortable : true
			}, {
				field : 'stock_invalidDate',
				title : '失效期',
				width : 100,
				sortable : true,
				formatter : function (value, row, index) {
					return util.formatDate(value,"yyyy-MM-dd");
				}
			}, {
				field : 'stock_produceDate',
				title : '生产日期',
				width : 100,
				sortable : true,
				formatter : function (value, row, index) {
					return util.formatDate(value,"yyyy-MM-dd");
				}
			}, {
				field : 'stock_sterilizationbnum',
				title : '灭菌批号',
				width : 80,
				sortable : true
			}, {
				field : 'stock_sterilizationtime',
				title : '灭菌日期',
				width : 100,
				sortable : true,
				formatter : function (value, row, index) {
					return util.formatDate(value,"yyyy-MM-dd");
				}
			}, {
				field : 'stock_storageNumber',
				title : '库存数量',
				width : 100,
			}, {
				field : 'stock_storage',
				title : '库房',
				width : 100,
				hidden : true,
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
		var nodes = $('#stock_dg').datagrid('getSelected');
		if(nodes!=null&&nodes!=""){
			window.parent['${callback}'](nodes);
			parent.$('#stockListWindow').window('close');
		}
	}
</script>	
</head>        
<body>

			<div>
				<input id="product_id" name="product_id" type="hidden" value="${product_id}" />
			</div>

	<!-- 显示区域 -->
	<div id="stockData" data-options="region:'center',border:false" style="overflow: hidden;width: 100%;">
		<table id="stock_dg"></table>
	</div> 
</body>
</html>