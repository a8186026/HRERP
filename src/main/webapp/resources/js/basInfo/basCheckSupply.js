

var id="";//对应bas_check中每条审批记录的主键id
var currentIndex = 0;
var myDate = new Date();


//所有控件ID
var ctrlsID = [ "supply_number", "supply_code", "supply_name", "supply_type", "supply_person",
                "supply_telephone", "supply_permission_no", "supply_permission_name", "supply_permission_wide"];

$(function() {
	//控件权限加载
	var ctrlPermission = new pageCtrlPermission();
	ctrlPermission.bindCtrlPermission(ctrlsID,"","basCheckSupplyList");
	
	// 初始化表格
	$('#choose_supply').combobox({
		url: global_param.context_name + "/basInfo/basCheckSupply/chooseSupply",
		valueField:'sup_id',
		textField:'sup_name',
		editable:false,
		onSelect: function(node){ 
			
           $('#supply_name').val(node.sup_name);
           $('#supply_code').val(node.sup_areano);
           $('#supply_number').val(node.sup_code);
           $('#supply_type').val(node.sup_property);
           $('#supply_person').val(node.sup_ctactperson);
           $('#supply_telephone').val(node.sup_mobile);
           $('#supply_permission_no').val(node.sup_pmtno);
           $('#supply_permission_name').val(node.sup_pmtname);
           $('#supply_permission_wide').val(node.sup_pmtscope);
          /* supCode=$('#supply_number').val();
	    	 $("#"+checkNumber+"_form").attr("action",preformUrl+"/"+supCode);*/
	    	
	    	
        },
	    onChange:function(newNode,oldNode){
	    	/*document.getElementById("save_"+checkNumber).disabled=false;
	    	 supCode=$('#supply_number').val();
	    	 $("#"+checkNumber+"_form").attr("action",preformUrl+"/"+supCode);*/
	    	 $.ajax({
	    			type : "get", // 表单提交类型
	    			url : global_param.context_name + "/system/setting/get/"+$("#type").val(), //获得审批供方的用户组等信息
	    			//data :"json", // 表单数据
	    			success : function(data) {
	    				      
	    				      //  var type=data.check_type;//审批流程类型  0-流水  1-平行 
	    				        var check_setting_id = data.check_id;//审批业务类型
	    				      
					    	 $.ajax({
					    		 type : "get", // 表单提交类型
					    			url : global_param.context_name + "/basInfo/basCheckSupply/getCheckComment?id="+newNode+"&type="+check_setting_id, // 表单提交目标
					    		//	data :"json", // 表单数据
					    			success : function(result) {
					    				
					    				//var type=data.check_type;//审批流程类型  0-流水  1-平行 
					    			
					    				var user = util.get(global_param.context_name + "/usergroup");
			        					var currentgroup = user.group_id;//当前登录的用户组id
			        				
			        					var times=data.check_times;			//审批次数
			        					var titles=data.check_titles.split(",");	//审批内容的标题
			        					var groups = data.check_groups.split(",");				//审批权限组
			        					var title_id = data.check_title_ids.split(",");    //审批环节组的id
			        					var check_types = data.check_types.split(",");
			        					
			        					   
			        					//拿到当前审批已有的结果
			        					var list = result.list;				//当前已有的审批记录
			        					//var count = result.count;
			        					var content = new Array();
			        					var comment_id = new Array();
			        					var content_time = new Array();
			        					var content_person = new Array();
			        					var check_id=new Array();//对应sys_checkTitle的check_title_id,填入check_comment_id中
			        					var check_modified =new Array();
			        					
			        					for(var j=0;j<list.length;j++){
			        						
			        							check_id.push(list[j].check_id);
			        						
			        						//check_id.push(list[j].check_id);            //已有记录的id
			        						content.push(list[j].check_content);		//当前已有的审批内容
			        						comment_id.push(list[j].check_comment_id);	//当前的审批条目
			        						check_modified.push(list[j].check_modified);
			
			        					}
			        					 
			        					//var check_name = data.check_name;				
			        					
			        					//删除上一个产品加载的审批项
			        					var checkcontent = document.getElementById("checkContent");
			        				
			        					for(var j=0;j<currentIndex;j++){
			        						var form = document.getElementById(j+"_form");
			        						checkcontent.removeChild(form);
			        					}
			        					
			        					//加载当前产品的审批项
			        	
			        					
			        					
			        					
			        					for(var i = 0;i < times; i++){
			        						
			        					//	var spring_token = document.getElementById("SPRING_TOKEN");
			        						

			        						if(i == 0)
			    								$("#checkContent").append("<form id='"+i+"_form' action='"+result.formUrl+"?type="+check_setting_id+"' method='"+result.method+"' onsubmit='return false;'></form>");
			    							else{
				    							if($("#check_id_"+(i-1)).val() != ""){
				    								$("#checkContent").append("<form id='"+i+"_form' action='"+result.formUrl+"?type="+check_setting_id+"&lastId="+$("#check_id_"+(i-1)).val()+"&checkType="+check_types[i-1]+"' method='"+result.method+"' onsubmit='return false;'></form>");
				    							}else{
				    								$("#checkContent").append("<form id='"+i+"_form' action='"+result.formUrl+"?type="+check_setting_id+"' method='"+result.method+"' onsubmit='return false;'></form>");
				    							}
			    							}
			        						//$("#"+i+"_form").append("<input name='SPRING_TOKEN' type='hidden' value='"+spring_token.value+"' />");
			        					    if(check_types[i]==0){//流水审批
			        					    	$("#"+i+"_form").append("<div style='margin-top: 30px;margin-left: 10px'><div>"+titles[i]+" <font color='#0000ff'>(控制下级)</font>: </div>");
			        					    }else{
			        					    	$("#"+i+"_form").append("<div style='margin-top: 30px;margin-left: 10px'><div>"+titles[i]+": </div>");
			        					    }
			        					    
			    							$("#"+i+"_form").append("<input name = 'check_comment_id' type = 'hidden' value = '"+(newNode+"_"+title_id[i])+"' />");
			    							$("#"+i+"_form").append("<input name = 'check_type_id' type = 'hidden' value = '"+check_setting_id+"' />");
			    							
			    							var index = comment_id.indexOf(newNode+"_"+ title_id[i]);
			    							
			    							if(index>=0){
			    								$("#"+i+"_form").append("<input name = 'check_id' id='check_id_"+i+"' type = 'hidden' value = '"+check_id[index]+"' />");
			        							$("#"+i+"_form").append("<textarea id='check_content_"+i+"'  name='check_content' type='text' rows='10' cols='50'  maxlength='300' style='height:100px;width:1285px;'>"+content[index]+"</textarea>");
			        							$("#"+i+"_form").append("<div class='SelectAll' style='margin-top:7px'><div id='check"+i+"' style='margin-left: 520px'><input id='check_result_"+i+"' name='check_result' class='easyui-combobox' name='check_result' style='margin-left: 15px'>");
			        							$("#check"+i).append("<input style='margin-left: 15px' id='save_"+i+"' class='btn-save' onmouseover='this.className=\"btn-save-over\"' 'onmouseout=this.className=\"btn-save\"' onmousedown='this.className=\"btn-save-down\"' type='button' onclick='submitForm("+i+");' />");
			        							$("#check"+i).append("<label>负责人签字：</label><input id='signature_"+i+"'  readOnly='readOnly' value='"+content_person[index]+"' type='text' />&nbsp;&nbsp;<label>日期:</label><input id='date_"+i+"'  readOnly='readOnly'value=' "+content_time[index]+"'  /></div></div></div></form>");
			        						}else{
			        							$("#"+i+"_form").append("<input name = 'check_id' id='check_id_"+i+"' type = 'hidden' value = '' />");
			        							$("#"+i+"_form").append("<textarea id='check_content_"+i+"' name='check_content' type='text' rows='10' cols='50'  maxlength='300' style='height:100px;width:1285px;'></textarea>");
			        							$("#"+i+"_form").append("<div class='SelectAll' style='margin-top:7px'><div id='check"+i+"' style='margin-left: 520px'><input id='check_result_"+i+"' name='check_result' class='easyui-combobox' name='check_result' style='margin-left: 15px'>");
			        							$("#check"+i).append("<input style='margin-left: 15px' id='save_"+i+"' class='btn-save' onmouseover='this.className=\"btn-save-over\"' 'onmouseout=this.className=\"btn-save\"' onmousedown='this.className=\"btn-save-down\"' type='button' onclick='submitForm("+i+");' />");
			        							$("#check"+i).append("<label>负责人签字：</label><input id='signature_"+i+"'  readOnly='readOnly' type='text' />&nbsp;&nbsp;<label>日期:</label><input id='date_"+i+"' readOnly='readOnly'  type='text' /></div></div></div></form>");
			        						}
			    							if(i==0){
			    								$('#check_result_'+i).combobox({    
				    							    data:[{"id":1,"text":"同意"},{"id":2,"text":"不同意"}],    
				    							    valueField:"id",    
				    							    textField:"text" 
				    							});
			    							}
			    							else{
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
			    							}
			    							//设置下拉框选项,为流水第一项审批或平行审批时，没有取消审核的功能&&
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
			    								//如果本条审核不是控制下级，但它上一条是，本条有取消审批的功能
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
			    							}*/
			    							
			    							if(index>=0){
			    								//审核结果下拉框赋值
			    								$('#check_result_'+i).combobox('setValue',result.list[index].check_result);
			    							$('#signature_'+i).val(result.list[index].check_person_name);
		    								$('#date_'+i).val(util.formatDate(result.list[index].check_time));
			    							}
			    							/*if(currentgroup != groups[i]){
			    								document.getElementById("check_content_"+i).disabled = true;
			    								$('#check_result_'+i).combobox('disable',true);
			    								document.getElementById("save_"+i).disabled = true;
			    							}*/
			    							
			        					} 

			        					//初始化将所有控件置为disable
			        					for(var i = 0;i<times;i++){
			    							document.getElementById("check_content_"+i).disabled = true;
											$('#check_result_'+i).combobox('disable',true);
											document.getElementById("save_"+i).disabled = true;
			    						}
			        					
			        				
			        					//第一个单独判断，因为它不具有上级，只需判断当前用户组具有对该项的权限，就可设为enable
			        					if(currentgroup == groups[0]){
			        						
			        						document.getElementById("check_content_0").disabled = false;
											$('#check_result_0').combobox('enable',true);
											document.getElementById("save_0").disabled = false;
											
			        					}
			        					
			        					
			        					//循环每一条审批项
			        					for(var j = 0; j < times; j++){
			        						var index = comment_id.indexOf(newNode+"_"+title_id[j]);
			        						//如果为平行审批，且当前用户组具有对下一项的审批权限，则将下一项置为enable
			        						if(check_types[j] == 1 && currentgroup ==groups[j+1]){
			        							document.getElementById("check_content_"+(j+1)).disabled = false;
			    								$('#check_result_'+(j+1)).combobox('enable',true);
			    								document.getElementById("save_"+(j+1)).disabled = false;
			        						}
			        						
			        						//如果为流水审核，且当前审批项已经输入值，则将当前审批项置disable
			        						else if(check_types[j] == 0 && $('#check_id_'+j).val() != ""){
			        							
			        							document.getElementById("check_content_"+j).disabled = true;
			    								$('#check_result_'+j).combobox('disable',true);
			    								document.getElementById("save_"+j).disabled = true;
			    								
			    								//如果当前用户组具有对下一项的审批权限，，将下一项置enable
			    								if(currentgroup ==groups[j+1]&&check_modified[index]!=1){
			    								
			            							document.getElementById("check_content_"+(j+1)).disabled = false;
			        								$('#check_result_'+(j+1)).combobox('enable',true);
			        								document.getElementById("save_"+(j+1)).disabled = false;
			    								}
			        						}
			        						
			        						if(index != -1&&check_modified[index]==1){
			        							document.getElementById("check_content_"+(j)).disabled = false;
			    								$('#check_result_'+(j)).combobox('enable',true);
			    								document.getElementById("save_"+(j)).disabled = false;
			        						}
			        						
			        					}
			        					
			        					currentIndex = times;
					    			}
					    	 });
	    			}
	    	 });
	    
	    }
	});
	

});

function submitForm(i){
	var code=$('#supply_number').val();
	var result=$('#check_result_'+i).combobox('getValue');
	
	if(code==""){//未选择审批对象
		util.show("请选择一条供方记录进行审批");
	}
	else{
		if(result==""){//未选择审批意见
			util.show("请选择是否同意");
		}
		
		else{
		var data = util.submit(i+"_form");
		if (data) {
		
			if (data.result == "success"){
			      
				$('#choose_supply').combobox('reload');
				$('#choose_supply').combobox('clear');
				var sup_id = data.bascheck.check_comment_id.substring(0,data.bascheck.check_comment_id.indexOf('_'));
		
				$('#choose_supply').combobox('setValue',sup_id);
				util.show(data.message);
			
				
			} else {
				util.error(data.message);
			}
		}
		}
	}
}

