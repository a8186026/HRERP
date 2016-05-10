<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>养护品种信息管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/quality/qltMaintainInfoCheck.js"></script> 
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<!-- toolbar显示区域 -->
	<div id="tb" style="padding:2px;height:auto">
		<div style="margin-bottom:1px">
		<a id="maintain" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="maintain()">确认养护</a>
		<a id="selectAll" href="#" class="easyui-linkbutton" iconCls="icon-modify" plain="true" onclick="selectAll()">全选</a>
		<a id="unSelectAll" href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="unSelectAll()">清除全选</a>
		<font style="float:right">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		养护票号:&nbsp;&nbsp;<input id="maintain_ticketNo" name="maintain_ticketNo" readOnly="readonly" style="width: 100px;"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
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
								<input id="stock_storage" name="stock_storage" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">组号：</div>
							<div class="l-panel-search-item">
								<input id="pro_group_no" name="pro_group_no" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">重点品种：</div>
							<div class="l-panel-search-item">
								<input id="search_product_immaintain" name="product_immaintain" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">重点天数：</div>
							<div class="l-panel-search-item">
								<input id="immaintain_days" type="text" value="30" 
								onfocus="if(this.value=='30'){this.value=''}"
								onblur="if(this.value==''){this.value='30'}" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">普通天数：</div>
							<div class="l-panel-search-item">
								<input id="ptmaintain_days" type="text" value="90" 
								onfocus="if(this.value=='90'){this.value=''}"
								onblur="if(this.value==''){this.value='90'}" />
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