<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>返货审核</title>
	<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
	<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
	<script type="text/javascript"	src="/HRERP/resources/js/refund/refundCheck.js"></script>
	<script type="text/javascript"	src="/HRERP/resources/js/JsUtil/userHabits.js"></script>
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
									<div class="l-panel-search-title">供方编码：</div>
									<div class="l-panel-search-item">
										<input id="supply_code" name="supply_code" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">联系人：</div>
										<div class="l-panel-search-item">
											<input id="sup_ctactperson" name="sup_ctactperson" readonly="readonly" disabled = "disabled" type="text" value=""  />
										</div>
								</div>
									<!-- 记账票号与返货合计的text框  同时记住设置为disabled的标签放在form表单里面是不会提交的，若是不可输入，只要设置readonly属性即可  -->
									<div class="float-l">
										<div class="l-panel-search-title">记账票号：</div>
											<div class="l-panel-search-item">
											<input id="refund_ticket_id" name="refund_ticket_id" type="text" disabled = "disabled" readonly= "readonly"/>
										</div>
									</div>
							</form>
						</div>
				
						<!-- 记账票号与返货合计的text框  -->
						<div class="l-panel-search-cond clearfix" >
<!-- 							<div class="float-l">
								<div class="l-panel-search-title">记账票号：</div>
									<div class="l-panel-search-item">
									<input id="refund_ticket_id" name="refund_ticket_id" type="text" disabled = "disabled" readonly= "readonly"/>
									</div>
							</div> -->
						
						<!-- 这个应该可以不要 -->
	<!-- 						<div class="float-l">
								<div class="l-panel-search-title">返货合计：</div>
								<div class="l-panel-search-item">
									<input id="refund_count" name="refund_count" type="text"  disabled = "disabled" readonly= "readonly" />
								</div>
							</div> -->
							
							<div class="float-l">
								<div class="l-panel-search-title">操作人：</div>
								<div class="l-panel-search-item">
								
									<!-- 将controller里面传回来的model中的user对应的内容里面的 display_name取出，显示操作员-->
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
					<table id="refund_order_dg"></table>
				</div>
			</div>
		</div>
		
		<div data-options="region:'center',border:false,split:true" style="height:100%; overflow: hidden;">
			<div class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
				
				<!-- 小单显示区域 -->
				<div data-options="region:'center',border:false" style="overflow: hidden; margin-bottom: 15px;">
					<table id="refund_orderList_dg"></table>
				</div>
			</div>
		</div>


	<!-- 编辑窗口 -->
	<div id="supListWindow"></div>		
		
</div>
</body>
</html>