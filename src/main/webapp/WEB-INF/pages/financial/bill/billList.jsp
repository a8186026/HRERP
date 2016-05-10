<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分店定价</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/financial/bill.js"></script>
<script src="/HRERP/resources/js/report/CreateControl.js" type="text/javascript"></script>
<script type="text/javascript">
CreateReport("Report");
alert("creat report");
</script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1">
			 <input id="loginUserID" type="hidden" value="${loginUserID}"/>
            <!-- 绝对路径 -->
               <input id="realPath" type="hidden" value="${realPath}"/>
				<div class="l-panel-search-cond clearfix" >
				
						        <div class="float-l">
									<div class="l-panel-search-title">票据种类:</div>
									<div class="l-panel-search-item">
										<input id="bill_type" name="bill_type" type="text" class="easyui-combobox"/>
									</div>
								</div>
					       <form id="searchForm" onsubmit="return false;">
					            <div class="float-l">
									<div class="l-panel-search-title">票号:</div>
									<div class="l-panel-search-item">
										<input id="end_time" name="end_time" type="text" class=""/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">打印格式:</div>
									<div class="l-panel-search-item">
										<input id="print_type" name="print_type" class="easyui-combobox"/>
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">页码:</div>
									<div class="l-panel-search-item">
										<input id="sup_code" name="sup_code" class="" />
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
						<input id="" type="button" value="编辑" style="width:80px;height:25px"
						onclick="perviewPrint();"/>
						<input id="" type="button" value="预览" style="width:80px;height:25px"
						onclick="billPrint();"/>
						<input id="" type="button" value="打印" style="width:80px;height:25px"
						onclick="billRightPrint();"/>
				</div>
			</div>
		</div>
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
		<div id="tab-dg" class="easyui-tabs" style="height:750px">   
			<div title="订单列表" id="tab-dg_1" style="height:100%;overflow-y:auto"> 
				<div data-options="region:'center',border:false" style="height:100%;width:100%;margin-top: 2px" fit="true">
					<table id="OrderDg"></table>
				</div>
			</div>
			<div title="入库结算列表" id="tab-dg_2" > 
			     <table id="StockDg"></table>
			</div>
			<div title="返货结算" id="tab-dg_3" > 
			     <table id="RetailDg"></table>
			</div>
			<div title="销售小票" id="tab-dg_4" > 
			     <table id="SaleDg"></table>
			</div>
		</div>
		</div>
		<!-- 显示区域 -->
		<!-- <div data-options="region:'center',border:false"
			style="overflow: hidden;">
				    <table id="OrderDg"></table>
					<table id="RetailDg"></table>
					<table id="SaleDg"></table>
					
		</div> -->
	</div>
	<!-- 编辑窗口 -->
	<div id="editWindow"></div>
</body>