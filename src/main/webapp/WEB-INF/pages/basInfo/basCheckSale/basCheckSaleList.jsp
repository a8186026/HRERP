<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销方审批管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/basInfo/basCheckSale.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageCtrlPermission.js"></script>
</head>
<body>
	<div id="searchBar"	class="l-panel-search clearfix centertoolbar-w"	style="overflow: hidden;">
		<div class="l-searchbar clearfix ">
			<div class="l-panel-search-cond clearfix">
				<div class="float-l">
					<div class="l-panel-search-title">选择销方：</div>
					<div class="l-panel-search-item">
						<input id="choose_sale" name="choose_sale" class="easyui-combobox" type="text" />
					</div>
				</div>
			</div>
		</div>
		<div class="l-panel-search-cond clearfix"
			style="width: 1285px; height: 170px; margin-left: 25px; margin-top: 10px; border: 2px solid #F0F0F0;">
			销方基本信息：
			<div class="l-panel-search-cond clearfix">
				<div id="div_product_id" class="float-l">
					<div class="l-panel-search-title">销方序号：</div>
					<div class="l-panel-search-item">
						<input id="sal_id" name="sal_id" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_code" class="float-l">
					<div class="l-panel-search-title">销方编码：</div>
					<div class="l-panel-search-item">
						<input id="sal_code" name="sal_code" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_commonname" class="float-l">
					<div class="l-panel-search-title">客户全称：</div>
					<div class="l-panel-search-item">
						<input id="sal_customername" name="sal_customername" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_proname" class="float-l">
					<div class="l-panel-search-title">类别：</div>
					<div class="l-panel-search-item">
						<input id="sal_type" name="sal_type" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title">联系人：</div>
					<div class="l-panel-search-item">
						<input id="sal_contactperson" name="sal_contactperson" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title">联系电话：</div>
					<div class="l-panel-search-item">
						<input id="sal_phone" name="sal_phone" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title">联系地址：</div>
					<div class="l-panel-search-item">
						<input id="sal_address" name="sal_address"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title">建档日期：</div>
					<div class="l-panel-search-item">
						<input id="sal_fillingdate" name="sal_fillingdate"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_unit" class="float-l">
					<div class="l-panel-search-title">许可证号：</div>
					<div class="l-panel-search-item">
						<input id="sal_permitno" name="sal_permitno" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_productarea" class="float-l">
					<div class="l-panel-search-title">许可证名称：</div>
					<div class="l-panel-search-item">
						<input id="sal_permitname" name="sal_permitname"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_filingtime" class="float-l">
					<div class="l-panel-search-title">许可范围：</div>
					<div class="l-panel-search-item">
						<input id="sal_permitscope" name="sal_permitscope"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title">主要产品：</div>
					<div class="l-panel-search-item">
						<input id="sal_majorproduct" name="sal_majorproduct"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title" style="width: 100px">许可证负责人：</div>
					<div class="l-panel-search-item">
						<input id="sal_permitchief" name="sal_permitchief"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title" style="width: 100px">许可证发证机关：</div>
					<div class="l-panel-search-item">
						<input id="sal_permitissuingauthority" name="sal_permitissuingauthority"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title" style="width: 100px">许可证有效期：</div>
					<div class="l-panel-search-item">
						<input id="sal_permitvailddate" name="sal_permitvailddate"
							type="text" readOnly="readonly"/>
					</div>
				</div>		
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title" style="width: 100px">质量认证书编号：</div>
					<div class="l-panel-search-item">
						<input id="sal_qltycateno" name="sal_qltycateno"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title" style="width: 100px">质量认证书名：</div>
					<div class="l-panel-search-item">
						<input id="sal_qltycatename" name="sal_qltycatename"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title" style="width: 100px">质量证书有效期：</div>
					<div class="l-panel-search-item">
						<input id="sal_qltycatevailddate" name="sal_qltycatevailddate"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title" style="width: 100px">质量证书发证机关：</div>
					<div class="l-panel-search-item">
						<input id="sal_qltycateissuingauthority" name="sal_qltycateissuingauthority"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				
						
			</div>
		</div>
	</div>
		

	<div style="margin-top: 15px; margin-left: 30px; width: 100%; height: 100%;">
		<div id="checkContent">
			<input id="SPRING_TOKEN" type="hidden"
				value="${sessionScope.SPRING_TOKEN}" /> 
			<input id="type" type="hidden" value="${type}">
		</div>
	</div>	
</body>
</html>