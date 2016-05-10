<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品档案信息管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/accept/purSpecialVarietyCheckList.js"></script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overfolw: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix">
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">供方简码：</div>
							<div class="l-panel-search-item">
								<input id="supply_code" name="supply_code" type="text" value=""  />
							    <input id="supply_id" name="supply_id" value="" type="hidden" />
							</div>
						</div>
						</br></br>
						<div class="float-l">
							<div class="l-panel-search-title">供方全称：</div>
							<div class="l-panel-search-item">
								<input id="sup_name" name="sup_name" readonly="readonly" disabled="disabled" type="text" value=""/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">联系人：</div>
							<div class="l-panel-search-item">
								<input id="sup_ctactperson" name="sup_ctactperson" readonly="readonly" disabled="disabled" type="text" value=""  />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">电话：</div>
							<div class="l-panel-search-item">
								<input id="sup_tel" name="sup_tel" readonly="readonly" disabled="disabled" type="text" value=""  />
							</div>
						</div>
						</br></br>
						<div class="float-l">
							<div class="l-panel-search-title">产品简码：</div>
							<div class="l-panel-search-item">
								<input id="product_code" name="product_code" type="text" value="" />
								 <input id="product_id" name="product_id" value="" type="hidden" />
							</div>
						</div>
						</br></br>
						<div class="float-l">
							<div class="l-panel-search-title">产品名称：</div>
							<div class="l-panel-search-item">
								<input id="product_name" name="product_name" readonly="readonly" disabled="disabled" type="text"  value=""/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">生产厂家：</div>
							<div class="l-panel-search-item">
								<input id="product_factoryname" name="product_factoryname" readonly="readonly" disabled="disabled" type="text" value="" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">规格：</div>
							<div class="l-panel-search-item">
								<input id="product_specification" name="product_specification" readonly="readonly" disabled="disabled" type="text" value="" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">单位：</div>
							<div class="l-panel-search-item">
								<input id="product_unit" name="product_unit" readonly="readonly" disabled="disabled" type="text" value="" />
							</div>
						</div>
						</br></br></br></br>
						<div class="float-l">
							<div class="l-panel-search-title">质量状况：</div>
							<div class="l-panel-search-item">
							   <select id="accept_specialQuantityCondition">
                                    <option value="0" selected="selected">合格</option>
                                    <option value="1">不合格</option>
                                </select>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">验收结论：</div>
							<div class="l-panel-search-item">
								<select id="accept_specialCheckConclusion">
                                    <option value="0" selected="selected">符合</option>
                                    <option value="1">不符合</option>
                                </select>
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
						onclick="clearForm();" />
						<!-- onclick="$('#searchForm').form('clear');" -->
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
	<div id="supListWindow"></div>
	<div id="proListWindow"></div>
</body>
</html>