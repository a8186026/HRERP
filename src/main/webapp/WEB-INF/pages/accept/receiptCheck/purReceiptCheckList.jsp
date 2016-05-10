<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/accept/purReceiptCheck.js"></script>

<div class="easyui-layout" fit="true">
	
	<!-- toolbar显示区域 -->
	<div id="tb" style="padding:2px;height:auto">
		<div style="margin-bottom:1px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-modify" plain="true" onclick="checkAll()">全选</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="uncheckAllFunc()">取消全选</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-modify" plain="true" onclick="checkReceiptList()">收货确认</a>
		
		<font style="float:right">
		收货人:<input id="quality_check_person" style="border:0;width:100px" type="text" readOnly="readonly">
		收货日期:<input id="quality_check_time" style="border:0;width:150px" type="text" readOnly="readonly"></font>
		</div>
	</div>	


	<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix" >
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">票号：</div>
							<div class="l-panel-search-item">
								<input id="search_ticket_id" name="ticket_id" type="text" />
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

	
	<!-- 大单显示区域 -->
	<div data-options="region:'center',border:false" class="Dialog-Bg"
		style="overflow: hidden;height:280px;" >
		<table id="order_dg"></table>
	</div>
	<!-- 小单显示区域 -->
	<div data-options="region:'center',border:false" class="Dialog-Bg"
		style="overflow: hidden;height:500px;margin-top:300px" >
		<table id="orderList_dg"></table>
	</div>
	<div id=editReceiptWindow></div>
</div>