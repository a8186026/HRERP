<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>选择地区药监ID</title>
<style type="text/css">
html,body {
	width: 100%;
	height: 100%;
}
</style>
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
		$("#localDrugDg").datagrid({
			url : global_param.context_name + "/basInfo/basLocalDrug/lists", // 数据来源
			title : '地区药监信息列表', // 标题
			method : 'get',
			sortName : 'ca_id', // 排序的列
			sortOrder : 'desc', // 顺序
			striped:false,
			fit:true,
			singleSelect:true,
			queryParams : {
				ca_gysmc:'${value}'//获取后台来的值，双引号单引号一样
			},
			pagination : true,
			columns : [ [ {
				title : '客户名称',
				field : 'ca_gysmc',
				width : 100,
				sortable : true
			}, {
				title : '许可证号',
				field : 'ca_xkzbh',
				width : 100,
				sortable : true
			},{
				title : '法人',
				field : 'ca_frdb',
				width : 100,
				sortable : true
			},{
				title : '企业负责人',
				field : 'ca_qyfzr',
				width : 100,
				sortable : true
			}, {
				title : '地址',
				field : 'ca_zcdz',
				width : 100,
				sortable : true
			},{
				title : '药监ID',
				field : 'ca_id',
				width : 100,
				sortable : true
			}] ],
			onDblClickRow : selectLocalDrug,
			toolbar : [ {
				id:'chooseDrug',
				text : '选择',
				iconCls : 'icon-add',
				handler : selectLocalDrug
			}, {
				text : '清除',
				iconCls : 'icon-delete',
				handler : function() {
					callParent(null);
					//parent.$("#comNameChooseWin").window('close');
					parent.$("#localDrugChooseWin").window('close');
				}
			}]
		}).datagrid("keyCtr");
		
		
		$("#chooseDrug").focus();
});
	function selectLocalDrug() {
		var node = $('#localDrugDg').datagrid('getSelected');
		if (!node) {
			util.show("请选择地区药监");
			return;
		}
		callParent(node);
		parent.$("#localDrugChooseWin").window('close');
	}
	//查询
	function query() {
		$('#localDrugDg').datagrid('options').queryParams = util.formParams("localDrugChooseForm");
		$('#localDrugDg').datagrid('reload');
	}
	
	//调用回调函数
	function callParent(node) {
		window.parent['${callback}'](node);
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<div class="easyui-layout" data-options="fit:true">			
			<div data-options="region:'center'">
				<table id="localDrugDg"></table>
			</div>
		</div>
	</div>
</body>
</html>