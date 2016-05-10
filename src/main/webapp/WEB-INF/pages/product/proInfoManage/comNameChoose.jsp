<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>选择药品</title>
<style type="text/css">
html,body {
	width: 100%;
	height: 100%;
}
</style>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
		util.table("t_dg",{
			url : global_param.context_name + "/basInfo/basDrugInfo/lists", // 数据来源
			title : '药品信息列表', // 标题
			method : 'get',
			sortName : 'eth_Id', // 排序的列
			sortOrder : 'desc', // 顺序
			striped:false,
			queryParams : {},
			pagination : true,
			columns : [ [ {
				title : '通用名',
				field : 'eth_tym',
				width : 20,
				sortable : true
			}, {
				title : '商品名',
				field : 'eth_spm',
				width : 20,
				sortable : true
			},{
				title : '规格',
				field : 'eth_gg',
				width : 20,
				sortable : true
			},{
				title : '单位',
				field : 'eth_dw',
				width : 20,
				sortable : true
			}, {
				title : '产地',
				field : 'eth_sccj',
				width : 60,
				sortable : true
			}, {
				title : '批准文号',
				field : 'eth_pzwh',
				width : 40,
				sortable : true
			},{
				title : '药品序号',
				field : 'eth_Sort',
				width : 20,
				sortable : true
			}, {
				title : '药监ID',
				field : 'eth_Id',
				width : 20,
				sortable : true
			}, {
				title : '药监编号',
				field : 'eth_UnitCode',
				width : 20,
				sortable : true
			}] ],
			onDblClickRow : selectComName,
			toolbar : [ {
				text : '选择',
				iconCls : 'icon-add',
				handler : selectComName
			}, {
				text : '清除',
				iconCls : 'icon-delete',
				handler : function() {
					callParent(null);
					parent.$("#comNameChooseWin").window('close');
				}
			}]
		});
});
	function selectComName() {
		var node = $('#t_dg').datagrid('getSelected');
		if (!node) {
			util.show("请选择药品");
			return;
		}
		callParent(node);
		parent.$("#comNameChooseWin").window('close');
	}
	//查询
	function query() {
		$('#t_dg').datagrid('options').queryParams = util.formParams("comNameChooseForm");
		$('#t_dg').datagrid('reload');
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
			<div data-options="region:'north'" style="height: 65px">
				<div id="searchBar"
					data-options="region:'north',border:true,split:false"
					class="l-panel-search clearfix centertoolbar-w"
					style="overflow: hidden; background-color: #FFFFFF">
					<div class="l-searchbar clearfix centertoolbar-w1">
						<div class="l-panel-search-cond clearfix">
							<form id="comNameChooseForm" onsubmit="return false;">
								<input id="auto" type="hidden" onclick="initData();" />
								<div class="float-l">
									<div class="l-panel-search-title">通用名:</div>
									<div class="l-panel-search-item">
										<input id="eth_tym" name="eth_tym" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">批准文号:</div>
									<div class="l-panel-search-item">
										<input id="eth_pzwh" name="eth_pzwh" type="text" />
									</div>
								</div>
							</form>
						</div>
						<div class="l-panel-search-btn">
							<input id="" class="btn-search"
								onmouseover="this.className='btn-search-over'"
								onmouseout="this.className='btn-search'"
								onmousedown="this.className='btn-search-down'" type="button"
								onclick="query();" /> <input id="" class="btn-empty"
								onmouseover="this.className='btn-empty-over'"
								onmouseout="this.className='btn-empty'"
								onmousedown="this.className='btn-empty-down'" type="button"
								onclick="$('#comNameChooseForm').form('reset');" />
						</div>
					</div>
				</div>
			</div>
			<div data-options="region:'center'">
				<table id="t_dg"></table>
			</div>
		</div>
	</div>
</body>
</html>