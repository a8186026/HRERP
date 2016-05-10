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
		var m_chnpy = null;
		//如果输入的是拼音码，则重新加载数据
		if('${content}'!=''){
			m_chnpy = '${content}';
		}
	
		$("#t_dg").datagrid({
			url : global_param.context_name + "/basInfo/basMedicineInfo/lists", // 数据来源
			title : '药品信息列表', // 标题
			method : 'get',
			sortName : 'medicine_id', // 排序的列
			sortOrder : 'desc', // 顺序
			striped:false,
			queryParams : {
				medicine_chnpy:m_chnpy,
			},
			singleSelect:true,
			fit:true,
			pagination : true,
			columns : [ [ {
				field : 'medicine_code',
				title : '药品编码',
				width : 100,
				sortable : true
			}, { 
				field : 'medicine_name',
				title : '药品名称',
				width : 100,
				sortable : true
			}, {
				field : 'medicine_proname',
				title : '学名',
				width : 100,
				sortable : true
			},  {
				field : 'medicine_engname',
				title : '药品英文名',
				width : 100,
				sortable : true
			},  {
				field : 'medicine_chnpy',
				title : '拼音码',
				width : 100,
				sortable : true
			},  {
				field : 'medicine_effect',
				title : '功效',
				sortable : true
			},  {
				field : 'medicine_note',
				title : '注意事项',
				width : 100,
				sortable : true
			},  {
				field : 'medicine_description',
				title : '药品简介',
				width : 100,
				sortable : true
			},  {
				field : 'medicine_update_time',
				title : '药品更新时间',
				width : 100,
				sortable : true,
				formatter : function (value, row, index) {
					return util.formatDate(value);
				}
			}] ],
			onDblClickRow : selectMedName,
			toolbar : [ {
				text : '选择',
				iconCls : 'icon-add',
				handler : selectMedName
			}, {
				text : '清除',
				iconCls : 'icon-delete',
				handler : function() {
					callParent(null);
					parent.$("#medNameChooseWin").window('close');
				}
			}]
		});
		
	});
	function selectMedName() {
		var node = $('#t_dg').datagrid('getSelected');
		if (!node) {
			util.show("请选择药品");
			return;
		}
		callParent(node);
		parent.$("#medNameChooseWin").window('close');
	}
	//查询
	function query() {
		$('#t_dg').datagrid('options').queryParams = util.formParams("medNameChooseForm");
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
							<form id="medNameChooseForm" onsubmit="return false;">
								<div class="float-l">
									<div class="l-panel-search-title">药品编码:</div>
									<div class="l-panel-search-item">
										<input id="medicine_code" name="medicine_code" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">药品名称:</div>
									<div class="l-panel-search-item">
										<input id="medicine_name" name="medicine_name" type="text" />
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
								onclick="$('#medNameChooseForm').form('reset');" />
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