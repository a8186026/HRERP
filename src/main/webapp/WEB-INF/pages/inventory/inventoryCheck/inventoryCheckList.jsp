<%@page contentType="text/html;charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>盘点损益审核</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/inventory/inventoryCheck.js"></script> 
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1"  style="width:1000px">
				<div class="l-panel-search-cond clearfix">
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">选择票号：</div>
							<div class="l-panel-search-item">
								<input id="inventory_ticket_id" name="inventory_ticket_id" type="text"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">负责人：</div>
							<div class="l-panel-search-item">
								<input id="user_name" name="user_name"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">损益金额：</div>
							<div class="l-panel-search-item">
								<input id="inventory_money" name="inventory_money"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
					</form>
				</div>
				<div class="l-panel-search-btn centertoolbar-w">
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
</body>
</html>