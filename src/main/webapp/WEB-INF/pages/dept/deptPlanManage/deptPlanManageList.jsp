<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分店定价</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/dept/deptPlanManage.js"></script>
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
					<table id="ProInfoDg"></table>
				</div>
			</div>
		</div>
		<div data-options="region:'center',border:false,split:true" style=" border-bottom: 1px solid #ccc;" >
			<div class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
				<div>
					<hr style="background-color:lightgrey;height:1px;"/>
				</div>	
		<!-- toolbar显示区域 -->
		<div id="tb" style="padding:2px;height:auto">
			<div style="margin-bottom:1px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-modify" plain="true" onclick="updatePlanFunc()">修改</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="deletePlanFunc()">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="checkPlanFunc()">审核</a>
			<font style="float:right">
			<input id="allCheck" type="checkbox" name="allCheck" style="width:20px" onclick="selectAll()"><label for="allCheck">全选</label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			总申请数量:<input id="sumNumber" style="border:0;width:100px" type="text" readOnly="readonly">
			总申请金额:<input id="sumCost" style="border:0;width:150px" type="text" readOnly="readonly"></font>
			</div>
		</div>
				
				<div id="searchBar" data-options="region:'north',border:false,split:true" class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;margin-top:5px;">
					<!-- <div  fit="true" style="width: 65%; height: 100%;"> -->
					<div class="l-searchbar clearfix centertoolbar-w1">
						<div class="l-panel-search-cond clearfix">
							<form id="searchForm" onsubmit="return false;">
								<div class="float-l">
									<div class="l-panel-search-title">调出库房:</div>
									<div class="l-panel-search-item">
										<input id="oh_deptPlan_exportDept" name="deptPlan_exportDept" type="text"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">申请库房:</div>
									<div class="l-panel-search-item">
										<input id="oh_deptPlan_applyDept" name="deptPlan_applyDept" type="text"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">应调数量:</div>
									<div class="l-panel-search-item">
										<input id="shouldDeliveryNum" name="shouldDeliveryNum" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">产品编码:</div>
									<div class="l-panel-search-item">
										<input id="product_code" name="product_code" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">申请数量:</div>
									<div class="l-panel-search-item">
										<input id="deptPlan_applyNum" name="deptPlan_applyNum" type="text"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">申请单价:</div>
									<div class="l-panel-search-item">
										<input id="deptPlan_applyPrice" name="deptPlan_applyPrice" type="text"/>
									</div>
								</div>
							
						</div>
				
						<div class="l-panel-search-btn">
								<input id="" class="btn-search"
									onmouseover="this.className='btn-search-over'"
									onmouseout="this.className='btn-search'"
									onmousedown="this.className='btn-search-down'" type="button"
									onclick="stockInfoQuery();" /> 
								<input id="" class="btn-empty"
									onmouseover="this.className='btn-empty-over'"
									onmouseout="this.className='btn-empty'"
									onmousedown="this.className='btn-empty-down'" type="button"
									onclick="clearsearchForm();" /> &nbsp;&nbsp;&nbsp;
									<!-- onclick="$('#searchForm').form('reset');" /> -->
								<input id="submitDeptPlan"  class="btn-save"
									onmouseover="this.className='btn-save-over'"
									onmouseout="this.className='btn-save'"
									onmousedown="this.className='btn-save-down'" type="button" type="button"
									onclick="addPlanManual();" /> 
							
						</div>
						</form>	
					</div>
				</div>
				<!-- 显示区域 -->
				<div data-options="region:'center',border:false" style="overflow: hidden;">
					<table id="StockInfoDg"></table>
				</div>
			</div>
		</div>
		
		<div data-options="region:'south',border:false,split:true" style="height:360px;overflow: hidden;">
			<div class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
				
				<!-- 显示区域 -->
				<div data-options="region:'center',border:false" style="overflow: hidden; margin-bottom: 15px;">
					<table id="PlanDg"></table>
				</div>
			</div>
		</div>
</div>
	<!-- 编辑窗口 -->
	<div id="editStockPriceWindow"></div>
	<div id="editDeptPlanWindow"></div>
	<div id="editDeptPlanWindow1"></div>
	<div id="proListWindow"></div>
	<!-- <div id="editWindow"></div> -->
