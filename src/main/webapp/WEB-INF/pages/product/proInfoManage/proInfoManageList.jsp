<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品档案信息管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/product/proInfoManage.js"></script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div data-options="region:'west',border:false,split:false" style="width: 300px">
			<div id="menuDiv" class="easyui-accordion" border="true" fit="true"></div>
		</div>
		
		<div data-options="region:'center'" style="height:100%">
			<div id="searchBar"
				data-options="region:'north',border:false,split:false"
				class="l-panel-search clearfix centertoolbar-w"
				style="overflow: hidden;height:10%">
				<div class="l-searchbar clearfix centertoolbar-w1">
					<div class="l-panel-search-cond clearfix" >
						<form id="searchForm" onsubmit="return false;">
							<div class="float-l">
								<div class="l-panel-search-title">产品编号：</div>
								<div class="l-panel-search-item">
									<input id="search_product_code" name="product_code" type="text" />
								</div>
							</div>
							<div class="float-l">
								<div class="l-panel-search-title">审核状态：</div>
								<div class="l-panel-search-item">
									<input id="search_product_check" name="product_check" type="text" />
								</div>
							</div>
							<div class="float-l">
								<div class="l-panel-search-title">地区药监：</div>
								<div class="l-panel-search-item">
									<input id="search_product_dsurveillanceid" name="product_dsurveillanceid" type="text" />
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
				style="overflow: hidden;height:89%">
				<table id="dg"></table>
			</div>
		 </div>
	</div>
	<!-- 编辑窗口 -->
	<div id="editWindow"></div>
</body>
</html>