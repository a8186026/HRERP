<%@page contentType="text/html;charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>盘点损益申请</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/inventory/inventoryApply.js"></script> 

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
					<form id="searchForm" action="${formUrl}" method="${method}">
						<div class="float-l">
							<div class="l-panel-search-title">库房编号：</div>
							<div class="l-panel-search-item">
								<input id="department_id" name="department_id" type="text" />
							</div>
						</div>
						<!--  <div class="float-l">
							<div class="l-panel-search-title">库房名称：</div>
							<div class="l-panel-search-item">
								<input id="department_fullname" name="department_fullname" type="text" readonly="readonly" style="background-color:#D3D3D3""/>
							</div>
						</div> -->
						</br></br>
						<div class="float-l">
							<div class="l-panel-search-title">产品编号：</div>
							<div class="l-panel-search-item">
								<input id="product_code" name="product_code"  type="text" />
								<input id="stock_info_id" name="stock_info_id"
									 type="hidden" class="easyui-validatebox" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">产品批号：</div>
							<div class="l-panel-search-item">
								<input id="stock_batchCode" name="stock_batchCode"  type="text" />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">库房数量：</div>
							<div class="l-panel-search-item">
								<input id="stock_storageNumber" name="stock_storageNumber" type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">产品序号：</div>
							<div class="l-panel-search-item">
								<input id="product_id" name="product_id"  type="text" readonly="readonly" style="background-color:#D3D3D3" value=" " />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">产品名称：</div>
							<div class="l-panel-search-item">
								<input id="product_name" name="product_name"  type="text" readonly="readonly" style="background-color:#D3D3D3" value=" " />
							</div>
						</div>
						<div class="float-l" >
							<div class="l-panel-search-title">产品规格：</div>
							<div class="l-panel-search-item">
								<input id="product_specification" name="product_specification"  type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div>
						</br></br>
						<div class="float-l"> 
							<div class= "l-panel-search-title">产品单位：</div>
							<div class="l-panel-search-item">
								<input id="product_unit" name="product_unit" type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">包装单位：</div>
							<div class="l-panel-search-item">
								<input id="product_packingunit" name="product_packingunit" type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">零售价：</div>
							<div class="l-panel-search-item">
								<input id="product_retailprice" name="product_retailprice" type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div>	
						</br></br>
						<div class="float-l"> 
							<div class="l-panel-search-title">产     地：</div>
							<div class="l-panel-search-item">
								<input id="product_productarea" name="product_productarea" type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">失效期  ：</div>
							<div class="l-panel-search-item">
								<input id="stock_invalidDate" name="stock_invalidDate" type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">进      价：</div>
							<div class="l-panel-search-item">
								<input id="stock_purchasePrice" name="stock_purchasePrice" type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div>
						</br></br>
						<div class="float-l"> 
							<div class="l-panel-search-title">入库小号：</div>
							<div class="l-panel-search-item">
								<input id="stock_intakeSmallNumber" name="stock_intakeSmallNumber" type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">入库票号：</div>
							<div class="l-panel-search-item">
								<input id="stock_intakeTicket" name="stock_intakeTicket" type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">供    方：</div>
							<div class="l-panel-search-item">
								<input id="sup_id" name="sup_id" type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div>
						</br></br>
						<div class="float-l"> 
							<div class="l-panel-search-title">盘点数量：</div>
							<div class="l-panel-search-item">
								<input id="inventory_countedNumber" name="inventory_countedNumber" type="text" value="" class="easyui-validatebox" />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">损益数量：</div>
							<div class="l-panel-search-item">
								<input id="inventory_profitLossNumber" name="inventory_profitLossNumber" type="text" readonly="readonly" value="" class="easyui-validatebox" />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">损益总额：</div>
							<div class="l-panel-search-item">
								<input id="inventory_profitLossMoney" name="inventory_profitLossMoney" type="text"  readonly="readonly"  value=""  />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">损益原因：</div>
							<div class="l-panel-search-item">
								<input id="inventory_reason" name="inventory_reason" type="text"    value="" class="easyui-validatebox"  />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">批准文号：</div>
							<div class="l-panel-search-item">
								<input id="product_approvalnum" name="product_approvalnum" type="text"  readonly="readonly" style="background-color:#D3D3D3" value=""  />
							</div>
						</div> 
					</form>
				</div>
					<div class="l-panel-search-btn">
							<input id="" class="btn-save" 
								onmouseover="this.className='btn-save-over'"
								onmouseout="this.className='btn-save'"
								onmousedown="this.className='btn-save-down'" type="button"
								onclick="apply();" /> 
							<input id="" class="btn-empty"
								onmouseover="this.className='btn-empty-over'"
								onmouseout="this.className='btn-empty'"
								onmousedown="this.className='btn-empty-down'" type="button"
								 onclick="$('#searchForm').form('clear');"/>
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
 	<div id="proListWindow"></div>
 	<div id="editWindow"></div>
</body>
</html>