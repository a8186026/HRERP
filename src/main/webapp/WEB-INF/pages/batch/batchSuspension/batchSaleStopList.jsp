<%@page contentType="text/html;charset=gb2312"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>����ͣ����Ϣ����</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/batch/batchSuspension.js"></script> 
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
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">��Ʒ��ţ�</div>
							<div class="l-panel-search-item">
								<input id="product_code" name="product_code" type="text" />
								<input id="product_id" name="product_id" type="hidden" />
							</div>
						</div>
					</form>
					<div class="float-l">
							<div class="l-panel-search-title">ͣ�����ɣ�</div>
							<div class="l-panel-search-item">
								<input id="stock_stopReason" name="stock_stopReason"  type="text" />
							</div>
					</div>
				</div>
			</div>
				<div class="l-panel-search-cond clearfix">
					<form id="displayForm" >
						<div class="float-l">
							<div class="l-panel-search-title">��Ʒ���ƣ�</div>
							<div class="l-panel-search-item">
								<input id="product_name" name="product_name"  type="text" readonly="readonly" disabled="disabled" value=" " />
							</div>
						</div>
						<div class="float-l" >
							<div class="l-panel-search-title">��Ʒ���</div>
							<div class="l-panel-search-item">
								<input id="product_specification" name="product_specification"  type="text"  readonly="readonly" disabled="disabled" value=""  />
							</div>
						</div>
						</br></br>
						<div class="float-l"> 
							<div class="l-panel-search-title">��Ʒ��λ��</div>
							<div class="l-panel-search-item">
								<input id="product_unit" name="product_unit" type="text"  readonly="readonly" disabled="disabled" value=""  />
							</div>
						</div>
						<div class="float-l"> 
							<div class="l-panel-search-title">��װ��λ��</div>
							<div class="l-panel-search-item">
								<input id="product_packingunit" name="product_packingunit" type="text"  readonly="readonly" disabled="disabled" value=""  />
							</div>
						</div>
						</br></br>
						<div class="float-l"> 
							<div class="l-panel-search-title">��Ʒ���أ�</div>
							<div class="l-panel-search-item">
								<input id="product_productarea" name="product_productarea" type="text"  readonly="readonly" disabled="disabled" value=""  />
							</div>
						</div>
					</form>
					<div class="float-l">
							<div class="l-panel-search-title">����Ա  ��</div>
							<div class="l-panel-search-item">
								<input id="stock_stopPerson" name="stock_stopPerson" disabled="disabled" type="text"    value="${user.display_name}"  />
							</div>
					</div> 
				</div>
		</div>
		<!-- ��ʾ���� -->
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="dg"></table>
		</div>
	</div>
	<!-- �༭���� -->
	<div id="batchListWindow"></div> 
</body>
</html>