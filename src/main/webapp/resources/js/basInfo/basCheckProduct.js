var currentIndex = 0;		//全局变量，存储上一次审批的次数

//所有控件ID
var ctrlsID = [ "product_id", "product_code", "product_commonname", "product_proname", "product_specification",
                "product_unit", "product_productarea", "product_filingtime", "product_dealprice", "product_tradeprice",
                "product_retailprice", "product_approvalnum", "product_quanlitystandard", "product_cartonsize",
                "product_licensevaliddate", "product_GMPcertify", "product_certificationtime", "product_storagecondition"];

$(function() {
	
	//控件权限加载
	var ctrlPermission = new pageCtrlPermission();
	ctrlPermission.bindCtrlPermission(ctrlsID,"","basCheckProductList");
		
	// 初始化表格
	$('#choose_product').combobox({
		url: global_param.context_name + "/basInfo/basCheckProduct/chooseProduct",
		valueField:'product_id',
		textField:'product_name',
		editable:false,
		onSelect: function(node){ 
			//表格信息赋值
           $('#product_id').val(node.product_id);
           $('#product_code').val(node.product_code);
           $('#product_commonname').val(node.product_commonname);
           $('#product_proname').val(node.product_proname);
           $('#product_specification').val(node.product_specification);
           $('#product_unit').val(node.product_unit);
           $('#supply_permission_no').val(node.supply_permission_no);
           $('#product_productarea').val(node.product_productarea);
           $('#product_filingtime').val(node.product_filingtime);
           $('#product_dealprice').val(node.product_dealprice);
           $('#product_tradeprice').val(node.product_tradeprice);
           $('#product_retailprice').val(node.product_retailprice);
           $('#product_approvalnum').val(node.product_approvalnum);
           $('#product_quanlitystandard').val(node.product_quanlitystandard);
           $('#product_cartonsize').val(node.product_cartonsize);
           $('#product_licensevaliddate').val(node.product_licensevaliddate);
           $('#product_GMPcertify').val(node.product_GMPcertify);
           $('#product_certificationtime').val(node.product_certificationtime);
           $('#product_storagecondition').val(node.product_storagecondition);
        },
        onChange:function(n,o){
        	$.ajax({
        		type : "get", // 表单提交类型
        		url : global_param.context_name + "/system/setting/get/"+$("#type").val(), // 获得产品审批次数的设置，得到审批次数和内容
        		//data :"json", // 表单数据
        		success : function(data) {
        			// var check_type = data.check_type;//审批流程类型  0-流水  1-平行 
        			 var check_setting_id = data.check_id;//审批业务类型
        			$.ajax({
        				type:"get",
        				url:global_param.context_name + "/basInfo/basCheckProduct/lists?id="+n+"&type="+$("#type").val(), // 获得之前产品ID为n的审批结果，type表示审批类型(产品审批、供方审批、销方审批)，check_type为审批方式(平行审批和流水审批)
        				success : function(result) {
        					var product_id = n; 	//当前选中的product
        					
        					//获取当前登录的用户所属的用户组信息
        					var user = util.get(global_param.context_name + "/usergroup");
        					var currentgroup = user.group_id;
        					
        					//拿到产品审批的具体设置
        					var times=data.check_times;			//审批次数
        					var titles=data.check_titles.split(",");	//审批内容的标题
        					var groups = data.check_groups.split(",");				//审批权限组
        					var title_ids = data.check_title_ids.split(",");
        					var check_types = data.check_types.split(",");
        					
        					
        					//拿到当前审批已有的结果
        					var list = result.list;				//当前已有的审批记录
        					var check_id = new Array();
        					var content = new Array();
        					var comment_id = new Array();
        					var check_modified =new Array();
        					for(var j=0;j<list.length;j++){
        						check_id.push(list[j].check_id);
        						content.push(list[j].check_content);		//当前已有的审批内容
        						comment_id.push(list[j].check_comment_id);	//当前的审批条目
        						check_modified.push(list[j].check_modified);
        						
        					}
        					/*
        					for(var p=0;p<times;p++){
        						var s= comment_id.indexOf(product_id+"_"+title_ids[p]);
            					if(s>=0){
            						check_id.push(list[p].check_id);
            					}else{
            						check_id.push(null);
            					}
            					alert(p+"::"+check_id[p]);
            		    	}
        					*/
        					
        					var check_name = data.check_name;
        					
        					//删除上一个产品加载的审批项
        					var checkcontent = document.getElementById("checkContent");
        					for(var j=0;j<currentIndex;j++){
        						var form = document.getElementById(j+"_form");
        						checkcontent.removeChild(form);
        					}
        					
        					//动态加载当前产品的审批项
        					for(var i = 0;i < times; i++){
    							
        						if(i == 0)
    								$("#checkContent").append("<form id='"+i+"_form' action='"+result.formUrl+"?type="+check_setting_id+"' method='"+result.method+"' onsubmit='return false;'></form>");
    							else{
	    							if($("#check_id_"+(i-1)).val() != ""){
	    								$("#checkContent").append("<form id='"+i+"_form' action='"+result.formUrl+"?type="+check_setting_id+"&lastId="+$("#check_id_"+(i-1)).val()+"&checkType="+check_types[i-1]+"' method='"+result.method+"' onsubmit='return false;'></form>");
	    							}else{
	    								$("#checkContent").append("<form id='"+i+"_form' action='"+result.formUrl+"?type="+check_setting_id+"' method='"+result.method+"' onsubmit='return false;'></form>");
	    							}
    							}
     /*   						if(check_id[i-1]==""||check_id[i-1]==null){
	         					    $("#checkContent").append("<form id='"+i+"_form' action='"+result.formUrl+"?type="+check_setting_id+"' method='"+result.method+"' onsubmit='return false;'></form>");
	        					}else{
	        					    $("#checkContent").append("<form id='"+i+"_form' action='"+result.formUrl+"?type="+check_setting_id+"&lastId="+check_id[i-1]+"&checkType="+check_types[i]+"' method='"+result.method+"' onsubmit='return false;'></form>");
	   
	        					}*/
    							
        						if(check_types[i]==0){//流水审批
        							$("#"+i+"_form").append("<div style='margin-top: 30px;margin-left: 10px'><div>"+titles[i]+" <font color='#0000ff'>(控制下级)</font>: </div>");
        					    }else{
        					    	$("#"+i+"_form").append("<div style='margin-top: 30px;margin-left: 10px'><div>"+titles[i]+": </div>");
        					    }
    							$("#"+i+"_form").append("<input id= 'check_comment_id_"+i+"' name = 'check_comment_id' type = 'hidden' value = '"+(product_id+"_"+title_ids[i])+"' />");
    							$("#"+i+"_form").append("<input id='check_type_id_"+i+"' name='check_type_id' type='hidden' value = '"+$("#type").val()+"'/>");
    							$("#"+i+"_form").append("<input id='check_type_"+i+"' name='check_type' type='hidden' value = '"+check_name+"'/>");

    							
    							//查询当前项在comment_id中的index值，若无，则返回-1
    							var index = comment_id.indexOf(product_id+"_"+title_ids[i]);
    							if(index>=0){
    								$("#"+i+"_form").append("<input id='check_id_"+i+"' name='check_id' type='hidden' value = '"+check_id[index]+"'/>");
        							$("#"+i+"_form").append("<textarea id='check_content_"+i+"'  name='check_content' type='text' rows='10' cols='50'  maxlength='300' style='height:100px;width:1285px;'>"+content[index]+"</textarea>");
        							$("#"+i+"_form").append("<div class='SelectAll' style='margin-top:7px'><div id='check"+i+"' style='margin-left: 450px'><input id='check_result_"+i+"' name='check_result' class='easyui-combobox' name='check_result' style='margin-left: 15px'>");
        							$("#check"+i).append("<input style='margin-left: 15px' id='save_"+i+"' class='btn-save' onmouseover='this.className=\"btn-save-over\"' 'onmouseout=this.className=\"btn-save\"' onmousedown='this.className=\"btn-save-down\"' type='button' onclick='submitForm("+i+");' />");
        							$("#check"+i).append("<input id = 'cancel_"+i+"' style='width:70px'  type = 'button' value='取消审批' onclick  = 'cancel("+i+");' />");
        							$("#check"+i).append("<label>负责人签字：</label><input id='signature_"+i+"'  readOnly='readOnly' type='text' />&nbsp;&nbsp;<label>日期:</label><input id='date_"+i+"' readOnly='readOnly' type='text' /></div></div></div></form>");
        						}else{
        							$("#"+i+"_form").append("<input id='check_id_"+i+"' name='check_id' type='hidden' />");
        							$("#"+i+"_form").append("<textarea id='check_content_"+i+"' name='check_content' type='text' rows='10' cols='50'  maxlength='300' style='height:100px;width:1285px;'></textarea>");
        							$("#"+i+"_form").append("<div class='SelectAll' style='margin-top:7px'><div id='check"+i+"' style='margin-left: 450px'><input id='check_result_"+i+"' name='check_result' class='easyui-combobox' name='check_result' style='margin-left: 15px'>");
        							$("#check"+i).append("<input style='margin-left: 15px' id='save_"+i+"' class='btn-save' onmouseover='this.className=\"btn-save-over\"' 'onmouseout=this.className=\"btn-save\"' onmousedown='this.className=\"btn-save-down\"' type='button' onclick='submitForm("+i+");' />");
        							$("#check"+i).append("<input id = 'cancel_"+i+"' style='width:70px'  type = 'button' value='取消审批' onclick  = 'cancel("+i+");' />");
        							$("#check"+i).append("<label>负责人签字：</label><input id='signature_"+i+"'  readOnly='readOnly' type='text' />&nbsp;&nbsp;<label>日期:</label><input id='date_"+i+"' readOnly='readOnly' type='text' /></div></div></div></form>");
        						}
    							
    							
    							
    							//设置下拉框选项

								$('#check_result_'+i).combobox({    
    							    data:[{"id":1,"text":"同意"},{"id":2,"text":"不同意"}],    
    							    valueField:"id",    
    							    textField:"text" 
    							});

    							
    							/*if(i>0&&check_types[i]==0){
    								if(check_types[i-1]==0){
    									$('#check_result_'+i).combobox({    
		    							    data:[{"id":1,"text":"同意"},{"id":2,"text":"不同意"},{"id":3,"text":"取消审核"}],    
		    							    valueField:"id",    
		    							    textField:"text" 
	    								});
    								}else{
    									$('#check_result_'+i).combobox({    
		    							    data:[{"id":1,"text":"同意"},{"id":2,"text":"不同意"}],    
		    							    valueField:"id",    
		    							    textField:"text" 
		    							});
    								}
    								
    							
    							}else{
    								$('#check_result_'+i).combobox({    
	    							    data:[{"id":1,"text":"同意"},{"id":2,"text":"不同意"}],    
	    							    valueField:"id",    
	    							    textField:"text" 
	    							});
    							}*/
    							if(index>=0){
    								//审核结果下拉框赋值
    								$('#check_result_'+i).combobox('setValue',result.list[index].check_result);
    								$('#signature_'+i).val(result.list[index].check_person_name);
    								$('#date_'+i).val(util.formatDate(result.list[index].check_time));
    							}
        					}
        					
        					//初始化将所有控件置为disable
        					for(var i = 0;i<times;i++){
        					
    							document.getElementById("check_content_"+i).disabled = true;
								$('#check_result_'+i).combobox('disable',true);
								document.getElementById("save_"+i).disabled = true;
								document.getElementById("cancel_"+i).disabled = true;
    						}
        					
        					//第一个单独判断，因为它不具有上级，只需判断当前用户组具有对该项的权限，就可设为enable
        					if(currentgroup == groups[0]){
        						document.getElementById("check_content_0").disabled = false;
								$('#check_result_0').combobox('enable',true);
								document.getElementById("save_0").disabled = false;
        					}
        					
        					//循环每一条审批项
        					for(var j = 0; j < times; j++){        						
        						//如果为平行审批，且当前用户组具有对下一项的审批权限，则将下一项置为enable
        						if(check_types[j] == 1 && currentgroup ==groups[j+1]){
        							//alert(j+",不控制下级");
        							document.getElementById("check_content_"+(j+1)).disabled = false;
    								$('#check_result_'+(j+1)).combobox('enable',true);
    								document.getElementById("save_"+(j+1)).disabled = false;
        						}

        						
        						//如果为流水审核，且当前审批项已经输入值，则将当前审批项置disable
 /*       						else if(check_types[j] == 0 && $('#check_id_'+j).val() != ""){
        							document.getElementById("check_content_"+j).disabled = true;
    								$('#check_result_'+j).combobox('disable',true);
    								document.getElementById("save_"+j).disabled = true;
    								
    								//如果当前用户组具有对下一项的审批权限，，将下一项置enable
    								if(currentgroup ==groups[j+1]&&check_modified[index]!=1){
            							document.getElementById("check_content_"+(j+1)).disabled = false;
        								$('#check_result_'+(j+1)).combobox('enable',true);
        								document.getElementById("save_"+(j+1)).disabled = false;
    								}
        						}*/
        						//流水审批，且当前项已有值
        						else if($('#check_id_'+(j)).val()!=""&&$('#check_id_'+(j)).val()!=null){
        							//alert(j+"，当前项有值");
        							
        							if(currentgroup ==groups[j+1]){
        								//alert(j+"，下一项具有权限，将下一项置为enable");
        								document.getElementById("check_content_"+(j+1)).disabled = false;
        								$('#check_result_'+(j+1)).combobox('enable',true);
        								document.getElementById("save_"+(j+1)).disabled = false;
        								//document.getElementById("cancel_"+(j+1)).disabled = false;
        							}
    								
        							if(($('#check_id_'+(j+1)).val() == ""||$('#check_id_'+(j+1)).val() == null) && currentgroup ==groups[j]){
        								//alert(j+",下一项没值，将当前项置为enable");
            							document.getElementById("check_content_"+j).disabled = false;
        								$('#check_result_'+j).combobox('enable',true);
        								document.getElementById("save_"+j).disabled = false;
        								document.getElementById("cancel_"+j).disabled = false;
        							}
        							else{
        								//alert(j+",下一项有值,将当前项设为disable");
            							document.getElementById("check_content_"+(j)).disabled = true;
        								$('#check_result_'+(j)).combobox('disable',true);
        								document.getElementById("save_"+(j)).disabled = true;
        								document.getElementById("cancel_"+j).disabled = true;
        							}
        						
        						}else{
        							//alert(j+"当前项没值");
        							if($('#check_id_'+(j-1)).val()!=""&&$('#check_id_'+(j-1)).val()!=null&&currentgroup==groups[j]){
        								//alert(j+",上一项有值，将当前项设为enable");
        								document.getElementById("check_content_"+(j)).disabled = false;
        								$('#check_result_'+(j)).combobox('enable',true);
        								document.getElementById("save_"+(j)).disabled = false;
        							}
        							
        						}
        							
    							/*	
    								if(currentgroup ==groups[j+1]&&$('#check_id_'+(j)).val()!= ""&&$('#check_id_'+(j)).val()!= null){
    									alert(j+",当前项有值，且下一项具有审批权限");
    									document.getElementById("check_content_"+(j+1)).disabled = false;
        								$('#check_result_'+(j+1)).combobox('enable',true);
        								document.getElementById("save_"+(j+1)).disabled = false;
    								}*/
        					}
        					/*	if(index != -1&&check_modified[index]==1){
        							document.getElementById("check_content_"+j).disabled = false;
    								$('#check_result_'+j).combobox('enable',true);
    								document.getElementById("save_"+j).disabled = false;
        						}
        						*/
        					//记录当前的审批次数，作为下一次初始化时删除遗留数据之用
        					currentIndex = times;
        				}
        			});		
        		}
        	});
        },

	});
		
});

function submitForm(i){
	var result=$('#check_result_'+i).combobox('getValue');
	if(result==""){//未选择审批意见
		util.show("请选择是否同意");
	}
	else{
		var data = util.submit(+i+"_form");
		if (data) {
			if (data.result == "success"){
				
				
				$('#choose_product').combobox('reload');
				$('#choose_product').combobox('clear');
				var product_id = data.bascheck.check_comment_id.substring(0,data.bascheck.check_comment_id.indexOf('_'));
				$('#choose_product').combobox('setValue',product_id);
				util.show(data.message);
			} else {
				util.error(data.message);
			}
		}
	}
};

function cancel(i){
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/basCheckProduct/" + $("#check_id_"+i).val());
		if(data.result == "success"){
			$('#choose_product').combobox('reload');
			$('#choose_product').combobox('clear');
			var product_id = $("#check_comment_id_"+i).val().substring(0,$("#check_comment_id_"+i).val().indexOf('_'));
			$('#choose_product').combobox('setValue',product_id);
		}
		util.show(data.message);
	});
}

