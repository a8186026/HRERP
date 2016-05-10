<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库房验收确认管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/accept/purStockCheck.js"></script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<!-- toolbar显示区域 -->
	<div id="tb" style="padding:2px;height:auto">
		<div style="margin-bottom:1px">
		<a id="intake" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="printList()">入库</a>
		<font style="float:right">
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		入库票号:<input id="accept_intakeTicket" name="accept_intakeTicket" readOnly="readonly" style="width: 100px;"/>
		&nbsp;&nbsp;
		打印格式:<input id="printType" name="printType" type="text" class="easyui-combobox" style="width: 125px;"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="perview" type="button"  value="预览打印格式" onclick="perviewPrint();" style="height: 22px;width: 90px;"/> 
		总数量:<input id="sumNumber" style="border:0;width:100px" type="text" readOnly="readonly">
		总金额:<input id="sumCost" style="border:0;width:150px" type="text" readOnly="readonly"></font>
		</div>
	</div>
	
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix">
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">库房：</div>
							<div class="l-panel-search-item">
								<input id="department_id" name="department_id" type="text"/>
							</div>
						</div>
						<div class="float-l">	
							<div class="l-panel-search-title">组号：</div>
							<div class="l-panel-search-item">
								<input id="accept_groupNo" name="accept_groupNo" type="text"/>
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
						onclick="$('#searchForm').form('reset');" />
				</div>
			</div>
		</div>
		<!-- 显示区域 -->
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="dg"></table>
		</div>
	</div>
	<!-- 编辑窗口 -->
	<div id="editWindow"></div>
</body>
</html>