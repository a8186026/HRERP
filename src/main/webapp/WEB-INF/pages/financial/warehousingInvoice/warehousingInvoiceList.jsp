<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分店定价</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/financial/warehousingInvoice.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/JsUtil/userHabits.js"></script>
<script type="text/javascript">
</script>
</head>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div data-options="region:'north',border:false,split:true" style="height:46px; overflow: hidden;">
			<div class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
				
				<!-- 显示区域 -->
				<div data-options="region:'center',border:false" style="overflow: hidden; margin-bottom: 15px;">
					<table id="supplInfoDg"></table>
				</div>
			</div>
		</div>
		<div data-options="region:'center',border:false,split:true" style=" border-bottom: 1px solid #ccc;" >
			<div class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
				<div>
					<hr style="background-color:lightgrey;height:2px;"/>
				</div>	
		<!-- toolbar显示区域 -->
		<div id="tb" style="padding:2px;height:auto">
			<div style="margin-bottom:1px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-modify" plain="true" onclick="confirmFunc()">确认</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="deleteFunc()">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveFunc()">保存</a>
			<font style="float:right">
				<!-- <label for="reject_ticket">发票票号：</label>
				<input id="reject_ticket" name="reject_ticket" readonly="readonly" disabled="disabled"  type="text" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="allCheck" type="checkbox" name="allCheck" style="width:20px" onclick="selectAll()"><label for="allCheck">全选</label> -->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				返货发票金额:<input id="sumRefundMoney" style="border:0;width:150px" type="text" readOnly="readonly">
				采购发票金额:<input id="sumStockMoney" style="border:0;width:150px" type="text" readOnly="readonly">
				应开发票金额:<input id="sumInvoiceMoney" style="border:0;width:150px" type="text" readOnly="readonly">
			</font>
			</div>
		</div>
				
				<div id="searchBar" data-options="region:'north',border:false,split:true" class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;margin-top:5px;">
					<div class="l-searchbar clearfix centertoolbar-w1" style="width:1400px">
						<div class="l-panel-search-cond clearfix">
							<form id="searchForm" onsubmit="return false;">
								<div class="float-l">
									<div class="l-panel-search-title">开始时间:</div>
									<div class="l-panel-search-item">
										<input id="begin_time" name="begin_time" type="text" class="easyui-datebox"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">结束时间:</div>
									<div class="l-panel-search-item">
										<input id="end_time" name="end_time" type="text" class="easyui-datebox"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">入库票号:</div>
									<div class="l-panel-search-item">
										<input id="stock_intakeTicket" name="stock_intakeTicket" type="text"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title"><font color='red'>*</font>供方编码:</div>
									<div class="l-panel-search-item">
										<input id="sup_code" name="sup_code" class="easyui-validatebox" 
												data-options="required:true,validType:'length[1,20]'"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">联系人:</div>
									<div class="l-panel-search-item">
										<input id="sup_ctactperson" name="sup_ctactperson" disabled="disabled" type="text"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">发票票号:</div>
									<div class="l-panel-search-item">
										<input id="rkfp_ticket" name="rkfp_ticket" disabled="disabled" type="text"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">税率:</div>
									<div class="l-panel-search-item">
										<input id="rkfp_taxrate" name="rkfp_taxrate" editable="false" type="text"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">发票号:</div>
									<div class="l-panel-search-item">
										<input id="rkfp_code" name="rkfp_code"  class="easyui-validatebox" 
										data-options="required:true,validType:['nosp']" type="text"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">发票总金额:</div>
									<div class="l-panel-search-item">
										<input id="rkfp_sumMoney" name="rkfp_sumMoney"  class="easyui-validatebox" 
										data-options="required:true,validType:['decimal','nosp']" type="text"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">折让总金额:</div>
									<div class="l-panel-search-item">
										<input id="rkfp_discount" name="rkfp_discount"  class="easyui-validatebox" 
										data-options="required:true,validType:['decimal','nosp']" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">开票单位:</div>
									<div class="l-panel-search-item">
										<input id="rkfp_supplier" name="rkfp_supplier" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">发票名头:</div>
									<div class="l-panel-search-item">
										<input id="rkfp_accepter" name="rkfp_accepter" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">摘要:</div>
									<div class="l-panel-search-item">
										<input id="rkfp_abstract" name="rkfp_abstract" editable="false" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">种类:</div>
									<div class="l-panel-search-item">
										<input id="rkfp_category" name="rkfp_category" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">备注:</div>
									<div class="l-panel-search-item">
										<input id="rkfp_remark" name="rkfp_remark" type="text" />
									</div>
								</div>
								<input id="sup_id" name="sup_id" type="hidden"/>
							</form>
						</div>
					
						<div class="l-panel-search-btn">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input id="" class="btn-search"
									onmouseover="this.className='btn-search-over'"
									onmouseout="this.className='btn-search'"
									onmousedown="this.className='btn-search-down'" type="button"
									onclick="query();" /> 
								<input id="" class="btn-empty"
									onmouseover="this.className='btn-empty-over'"
									onmouseout="this.className='btn-empty'"
									onmousedown="this.className='btn-empty-down'" type="button"
									onclick="clearsearchForm();" />
						</div>
						</div>
						
					<div>
						<hr style="background-color:lightgrey;height:1px;"/>
					</div>
				</div>
				
				<!-- 显示区域 -->
				<div data-options="region:'center',border:false" style="overflow: true;width: 100%;">
					<div data-options="region:'north',border:false,split:true" style="height:250px;overflow: true;">
							<table id="RefundInfoDg"></table>
					</div>
					<div data-options="region:'center',border:false,split:true" style="height:250px;overflow: true;">
							<table id="StockCheckedDg"></table>
					</div>
					<div data-options="region:'south',border:false,split:true" style="height:250px;overflow: true;">
							<table id="TestDg"></table>
					</div>
				</div>
				
			</div>
		</div>
		<!-- <div data-options="region:'south',border:false,split:true" style="height:238px;overflow: hidden;">
			<div class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
				<div data-options="region:'center',border:false" style="overflow: hidden;">
					<table id="TestDg"></table>
				</div>
			</div>
		</div> -->
</div>
	<!-- 编辑窗口 -->
	<div id="supListWindow"></div>
