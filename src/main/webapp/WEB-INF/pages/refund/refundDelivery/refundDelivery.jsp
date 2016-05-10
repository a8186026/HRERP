<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>返货付货登记</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script> 
<script src="/HRERP/resources/js/refund/refundDelivery.js" type="text/javascript"></script> 

</head>
<body>
<!--  readonly="readonly" disabled="disabled" -->
<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			>
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix">
					<form id="searchForm" onsubmit="refund false;">
					   <div class="float-l">
							<div class="l-panel-search-title">返货票号：</div>
							<div class="l-panel-search-item">
								<input id="refund_ticketId" name="refund_ticketId" type="text" value=""  />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">客户全称：</div>
							<div class="l-panel-search-item">
								<input id="sup_name" name="sup_name"  type="text" readonly="readonly" disabled="disabled" value="" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">联系人：</div>
							<div class="l-panel-search-item">
								<input id="sup_ctactperson" name="sup_ctactperson"  type="text"  readonly="readonly" disabled="disabled" value=""  />
							</div>
						</div>
				
						<div class="float-l">
							<div class="l-panel-search-title">电话：</div>
							<div class="l-panel-search-item">
								<input id="sup_tel" name="sup_tel" type="text"  readonly="readonly" disabled="disabled" value=""  />
							</div>
						</div>
					</form>
					<div class="float-l">
							<div class="l-panel-search-title">操作员：</div>
							<div class="l-panel-search-item">
								<input id="refund_operator" name="refund_operator"   type="text"  value="${user.display_name}" 
								readonly="readonly" disabled="disabled" /> 
								<input id="refund_operator_id" name="refund_operator_id"     value="${user.user_id}" 
								readonly="readonly" disabled="disabled"  type="hidden" /> 
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
								 onclick="$('#searchForm').form('clear');"/>
								<!-- onclick="clearForm();"/> -->
								
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