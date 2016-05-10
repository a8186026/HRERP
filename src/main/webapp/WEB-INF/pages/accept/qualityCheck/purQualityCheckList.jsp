<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/accept/purQualityCheck.js"></script>

<div class="easyui-layout" fit="true">

	
	<!-- toolbar显示区域 -->
	<div id="tb" style="padding:2px;height:auto">
		<div style="margin-bottom:1px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="updatePurOrderList()">审核</a>
		<a id="intake" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="printList()">入库</a>
		<font style="float:right">
		
		<input id="allCheck" type="checkbox" name="allCheck" style="width:20px" onclick="selectAll()"><label for="allCheck">全选</label>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		入库票号:<input id="accept_intakeTicket" name="accept_intakeTicket" readOnly="readonly" style="width: 100px;"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		打印格式:<input id="printType" name="printType" type="text" class="easyui-combobox" style="width: 130px;"/>
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
				<div class="l-panel-search-cond clearfix" >
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">票号：</div>
							<div class="l-panel-search-item">
								<input id="search_ticket_id" name="ticket_id" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">产品编码：</div>
							<div class="l-panel-search-item">
								<input id="search_product_code" name="product_code" type="text"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">供方：</div>
							<div class="l-panel-search-item">
								<input id="search_supply_code" name="supply_code" type="text"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">质检状态：</div>
							<div class="l-panel-search-item">
								<input id="search_accept_quantityStatus" name="accept_quantityStatus" type="text"/>
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
						onclick="$('#searchForm').form('clear');" />
				</div>
			</div>
	</div>

	<!-- 小单显示区域 -->
	<div data-options="region:'center',border:false" class="Dialog-Bg"
		style="overflow: hidden" >
		<table id="orderList_dg"></table>
	</div>
	<div id="editWindow"></div>
</div>