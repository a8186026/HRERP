<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>价签申请管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript"	src="/HRERP/resources/js/product/proPriceTagPrint.js"></script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix"  style="width:600px;">
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">开始时间：</div>
							<div class="l-panel-search-item">
								<input id="search_start_time" name="" class="easyui-datebox" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">门店：</div>
							<div class="l-panel-search-item">
								<input id="search_department_id" name=""
									class="easyui-validatebox" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">结束时间：</div>
							<div class="l-panel-search-item">
								<input id="search_end_time" name=""
									class="easyui-datebox" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">医保：</div>
							<div class="l-panel-search-item">
								<input id="search_medinsuvariety" name=""
									class="easyui-validatebox" />
							</div>
						</div>
					</form>
				</div>
				<div  style="margin-left: 20px;margin-top: 10px">
					<input id="" class="" type="button" value="库存品种"  style="width:80px;margin-left: 5px;"
						onclick="queryStockProduct();" /> 
					<input id="" class="" type="button" value="调价品种"  style="width:80px;margin-left: 5px;"
						onclick="queryStockPriceProduct();" />
					<input id="" class="" type="button" value="指定品种"  style="width:80px;margin-left: 5px;"
						onclick="querySpecifyProduct();" />
					<input id="" class="" type="button" value="打印"  style="width:80px;margin-left: 5px;"
						onclick="" />
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