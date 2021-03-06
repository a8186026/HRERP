<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员日管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/promotion/memDay.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/JsUtil/datagrid-dnd.js"></script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix">
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">会员日类型：</div>
							<div class="l-panel-search-item">
								<input id="search_mem_day_type" class="easyui-validatebox" name="mem_day_type" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">开始时间：</div>
							<div class="l-panel-search-item">
								<input id="search_mem_day_startDate" name="mem_day_startDate" type="text" class="easyui-datebox"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">结束时间：</div>
							<div class="l-panel-search-item">
								<input id="search_mem_day_endDate" name="mem_day_endDate" type="text" class="easyui-datebox"/>
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