<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>门店返货</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/storeTransCargo/transCargo.js"></script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div data-options="region:'north',border:false,split:true" style="height:440px; border-bottom: 1px solid #ccc;" >
			<div class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
				<div id="searchBar" data-options="region:'north',border:false,split:true" class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
					<div class="l-searchbar clearfix centertoolbar-w1">
						
						<div class="l-panel-search-cond clearfix">
							<form id="searchForm" onsubmit="return false;">
								<div class="float-l">
									<div class="l-panel-search-title">产品编号：</div>
									<div class="l-panel-search-item">
										<input id="supply_code" name="supply_code" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">批号：</div>
									<div class="l-panel-search-item">
										<input id="supply_code" name="supply_code" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">效期：</div>
									<div class="l-panel-search-item">
										<input id="supply_code" name="supply_code" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">可调数量：</div>
									<div class="l-panel-search-item">
										<input id="supply_code" name="supply_code" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">件数：</div>
									<div class="l-panel-search-item">
										<input id="supply_code" name="supply_code" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">数量：</div>
									<div class="l-panel-search-item">
										<input id="supply_code" name="supply_code" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">单价：</div>
									<div class="l-panel-search-item">
										<input id="supply_code" name="supply_code" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">金额：</div>
									<div class="l-panel-search-item">
										<input id="supply_code" name="supply_code" type="text" />
									</div>
								</div>
							</form>
						</div>
				
						<!-- 记账票号与返货合计的text框  -->
						<div class="l-panel-search-cond clearfix" >
							<div class="float-l">
								<div class="l-panel-search-title">操作人：</div>
								<div class="l-panel-search-item">
									<input id="operator" name="operator" type="text"  disabled = "disabled" readonly= "readonly" value="${user.display_name}" />
								</div>
							</div>
						</div>
				
				
						<div class="l-panel-search-btn">
							<input id="" class="btn-search"
								onmouseover="this.className='btn-search-over'"
								onmouseout="this.className='btn-search'"
								onmousedown="this.className='btn-search-down'" type="button"
								onclick="query();" /> 
							<input id="" class="btn-empty"
								onmouseover="this.className='btn-empty-over'"
								onmouseout="this.className='btn-empty'"
								onmousedown="this.className='btn-empty-down'" type="button"
								onclick="clearForm();" />
						</div>
					</div>
				</div>
				<!-- 大单显示区域 -->
					<div data-options="region:'center',border:false" style="overflow: hidden;">
					<table id="dg"></table>
				</div>
			</div>
		</div>
		
		<div data-options="region:'center',border:false,split:true" style="height:100%; overflow: hidden;">
			<div class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
				
				<!-- 小单显示区域 -->
				<div data-options="region:'center',border:false" style="overflow: hidden; margin-bottom: 15px;">
					<table id="check_dg"></table>
				</div>
			</div>
		</div>


	<!-- 编辑窗口 -->
	<div id="supListWindow"></div>		
		
</div>
</body>
</html>