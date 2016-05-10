<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收货拒收处理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/accept/acceptReject.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/JsUtil/userHabits.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div id="tb" style="padding:2px;height:auto">
			<div style="margin-bottom:1px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-modify" plain="true" onclick="updateRejectFunc()">修改</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="deleteRejectFunc()">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveRejectFunc()">提交</a>
			<font style="float:right">
				<label for="reject_ticket">拒收票号：</label>
				<input id="reject_ticket" name="reject_ticket" readonly="readonly" disabled="disabled"  type="text" />
			</font>
			</div>
		</div>
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden; ">		
			<div class="l-searchbar clearfix centertoolbar-w1" style="width:1400px">
				<div class="l-panel-search-cond clearfix" >
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px"><font color='red'>*</font>产品编码:</div>
							<div class="l-panel-search-item">
								<input id="product_code" name="product_code" class="easyui-validatebox" 
										data-options="required:true,validType:'length[1,20]'"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">产品条码：</div>
							<div class="l-panel-search-item ">
								<input id="product_barcode" name="product_barcode" readonly="readonly" disabled="disabled"  type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">规格：</div>
							<div class="l-panel-search-item">
								<input id="product_specification" name="product_specification"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">剂型：</div>
							<div class="l-panel-search-item">
								<input id="product_dosagetype" name="product_dosagetype"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">产品名称：</div>
							<div class="l-panel-search-item">
								<input id="product_name" name="product_name"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">生产厂家：</div>
							<div class="l-panel-search-item">
								<input id="product_factoryname" name="product_factoryname"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">单位：</div>
							<div class="l-panel-search-item">
								<input id="product_unit" name="product_unit"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">产地：</div>
							<div class="l-panel-search-item">
								<input id="product_productarea" name="product_productarea"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">分类：</div>
							<div class="l-panel-search-item">
								<input id="product_category" name="product_category"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">包装量：</div>
							<div class="l-panel-search-item">
								<input id="product_packingamount" name="product_packingamount"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">储存条件：</div>
							<div class="l-panel-search-item">
								<input id="product_storagecondition" name="product_storagecondition"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">批准文号：</div>
							<div class="l-panel-search-item">
								<input id="product_approvalname" name="product_approvalname"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px"><font color='red'>*</font>供货单位：</div>
							<div class="l-panel-search-item">
								<input id="supply_code" name="supply_code" class="easyui-validatebox" 
										data-options="required:true,validType:'length[1,20]'"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">客户全称：</div>
							<div class="l-panel-search-item">
								<input id="sup_name" name="sup_name"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">联系人：</div>
							<div class="l-panel-search-item">
								<input id="sup_ctactperson" name="sup_ctactperson"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">电话：</div>
							<div class="l-panel-search-item">
								<input id="sup_tel" name="sup_tel"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">操作员：</div>
							<div class="l-panel-search-item">
								<input id="user_name" name="user_name"  readonly="readonly" disabled="disabled"   type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">批号：</div>
							<div class="l-panel-search-item">
								<input id="reject_batchnum" name="reject_batchnum" class="easyui-validatebox" 
										data-options="required:true,validType:['num','nosp']"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">有效期：</div>
							<div class="l-panel-search-item">
								<input id="reject_validtime" name="reject_validtime"  data-options="required:true" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">生产日期：</div>
							<div class="l-panel-search-item">
								<input id="reject_productdate" name="reject_productdate"  data-options="required:true" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">数量：</div>
							<div class="l-panel-search-item">
								<input id="reject_num" name="reject_num" class="easyui-validatebox" 
										data-options="required:true,validType:['decimal','nosp']"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">单价：</div>
							<div class="l-panel-search-item">
								<input id="reject_price" name="reject_price" class="easyui-validatebox" 
										data-options="required:true,validType:['decimal','nosp']"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">拒收理由：</div>
							<div class="l-panel-search-item">
								<input id="reject_reason" name="reject_reason" class="easyui-validatebox" 
										data-options="required:true,validType:'length[1,50]'"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">质量状况：</div>
							<div class="l-panel-search-item">
								<input id="reject_quality" name="reject_quality" class="easyui-validatebox" 
										data-options="required:true,validType:'length[1,20]'"/>
							</div>
						</div>
						<input id="sup_id" name="sup_id" type="hidden"/>
						<input id="product_id" name="product_id" type="hidden" />
					</form>
					<div class="l-panel-search-btn">
								<input id="submitDeptPlan"  class="btn-save"
									onmouseover="this.className='btn-save-over'"
									onmouseout="this.className='btn-save'"
									onmousedown="this.className='btn-save-down'" type="button"
									onclick="save();" />  <input id="" class="btn-empty"
									onmouseover="this.className='btn-empty-over'"
									onmouseout="this.className='btn-empty'"
									onmousedown="this.className='btn-empty-down'" type="button"
									onclick="$('#searchForm').form('reset');" />
							
					</div>
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
	<div id="editAcceptRejectWindow"></div>
	<div id="proListWindow"></div>
	<div id="supListWindow"></div>
</body>
</html>