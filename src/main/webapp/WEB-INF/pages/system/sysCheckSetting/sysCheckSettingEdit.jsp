<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	
	var code = $("#check_code").val();//获取编码,以便提交前进行审核验证

	var currentIndex = 0;//当前审核名称和审核组的对数
	var ctrNumber = 0;//控件组数量
	
	//审核类型初始化
	util.select({
		id : 'check_type',
		required : true,
		param : {
			typeCode : 'CHECK_TYPE'
		}
	});
	
	//如果是查看详细页面,则对控件进行赋值
	if($('#check_id').val()!==""&&$('#check_titles').val()!==""){
		var titles = $('#check_titles').val().split(",");
		var groups = $('#check_groups').val().split(",");
		var ids = $('#check_title_ids').val().split(",");
		var cks = $('#check_types').val().split(",");
		
		for(var i=0;i<titles.length ;i++){
			//循环添加控件组并且赋值数据
			$("#_form").append("<div id='title_"+i+"' name='title' class='float-l'><div class='Dialog-form-title'><label for='check_titles_"+i
					+"' class='field'>审核标题：</label></div><div class='Dialog-form-item'><input style='width:110px;' id='check_titles_"+i
					+"' name='check_title' class='easyui-validatebox' value="+titles[i]+" /><input style='margin-left:8px;width:15px;' id='checkbox_"+i
					+"' name='checkbox' type='checkbox'>控制下级<input id='title_ids_"+i+"' name='title_ids' type='hidden' value='"+ids[i]+"' /></div></div>");
			
			if(cks[i]=="0"){
				$("#checkbox_"+i).attr("checked",true);
			}
			
			
			$("#_form").append("<div id='group_"+i+"' name='group' class='float-l'><div class='Dialog-check-title'><label for='check_group_"+i
					+"' class='field'>审核组：</label></div><div class='Dialog-check-item'><input id='check_group_"+i
					+"' name='check_group' class='easyui-combobox' style='width: 110px' data-options='required:true' value="+groups[i]+" /><input style='width:15px;margin-left:3px' onclick='deleteCtrl("+i
							+")' type='button' class='del-element' /><input style='width:15px;margin-left:3px' onclick='addCtrl("+i+")' type='button' class='add-element' /></div></div>");
		
			//控件设置必填
			$("#check_titles_"+i).validatebox({    
			    required: true,    
			});
			$("#"+"check_group_"+i).combobox({
				url : global_param.context_name + "/system/permission/groups",
				method : 'get',
				editable:false,
				valueField : 'group_id',
				textField : 'group_name',
			});
		}
		currentIndex = titles.length;//重置索引
		ctrNumber = titles.length;//重置控件组个数
	}
	
	function initList(){
		var str = /^[1-9]\d*$/;
		var num = $('#check_times').val();
		if(str.test(num)){
			if(num>10){
				$('#check_times').val(ctrNumber);
				util.show("整数不应该超过10");
			}else if(num==ctrNumber){
				return;
			}else{
				//如果数量大于现有数量就添加控件
				if(num>ctrNumber){
					//循环从最后添加控件
					for(var i= ctrNumber;i<num;i++)
						addCtrl();
				}else{
					//从最后开始减少控件，知道和对应的数量相同为止
					for(var i= ctrNumber-1;i>=num;i--)
						deleteCtrl();
				}
			}	
		}else{
			$('#check_times').val(ctrNumber);
			util.show("请输入正整数");
		}
	}

	function submitForm() {
		
		if($("#check_id").val()!=null&&$("#check_id").val()!=""){
			var result = util.get(global_param.context_name + "/system/setting/getCheckNumber?code=" +code);
			//如果有正在审核的条目，则无法更改
			if(!result){
				util.show("存在正在审核的条目,无法更改!");
				return;
			}
		}
		
		var index = 0;
		var title="",group="",id="",ck="";
		var titles = document.getElementsByName("check_title");
		var groups = document.getElementsByName("check_group");
		var ids = document.getElementsByName("title_ids");
		var checkboxs = document.getElementsByName("checkbox");
		//获取每一行数据并且封装成字符串
		for(var i=0;i<titles.length;i++){
			title = title + titles[i].value;
			group = group + groups[i].value;
			id = id + ids[i].value;
			ck = ck + checkboxs[i].checked;
			if(i<titles.length-1){
				title = title + ",";
				group = group + ",";
				id = id + ",";
				ck = ck + ",";
			}
			index++;
		}

		//给审核名称和审核组赋值
		$("#check_titles").val(title);
		$("#check_groups").val(group);
		$("#check_title_ids").val(id);
		$("#check_types").val(ck);//是否控制下级
		
		var data = util.submit('_form');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#dg').datagrid('reload');
				util.closeWindow('editWindow');
			} else {
				util.error(data.message);
			}
		}
	}
	
	function deleteCtrl(index){
		
		var id,title,group,check_title;
		//如果没有传值,则从尾部开始删除
		if(index==null){
			//获取最后一组的div
			var t = document.getElementsByName("title");//title所处的div的name
			var g = document.getElementsByName("group");//group所处的div的name
			var i = document.getElementsByName("title_ids");//title所处div中隐藏控件name
			var ct = document.getElementsByName("check_title");//审核标题name,如果是删除数据库已存在的条目，则需要重置内容，
			
			//从最后一组控件往上找，如果发现是隐藏控件，接着循环往上找，直到找到非隐藏控件为止
			var k = t.length;
			do{
				k--;
				title = t[k];
				group = g[k];
				id = i[k];
				check_title = ct[k];
			}while(k>0&&t[k].style.display=="none");

		}else{
			//获取主键id,如果有值，则使用隐藏，如果没值，直接删除空件
			id = document.getElementById("title_ids_"+index);//title所处div中隐藏控件id
			title = document.getElementById("title_"+index);//title所处的div的id
			group = document.getElementById("group_"+index);//group所处的div的id
			check_title = document.getElementById("check_titles_"+index);//获取索引index处的审核标题id
			$("#check_times").val(Number($("#check_times").val())-1);//点击-减少控件需要重置次数，如果是改变次数来减少的则不需要重置
		}
		//如果是数据库存在的条目，则隐藏控件组
		if(id.value!=null&&id.value!=""&&id.value!="undefined"){
			check_title.value = "#";//删除一个审核流程，将title置空，后面便于判断删除
			title.style.display = "none";
			group.style.display = "none";
			ctrNumber--;//控件对数-1
			return;
		}
		//直接移除控件
		var parent = document.getElementById("_form");
		parent.removeChild(title);
		parent.removeChild(group);
		ctrNumber--;//控件对数-1
		
	}
	
	function addCtrl(index){
		//如果当前控件组数大于9则不让添加
		if(ctrNumber>9){
			util.show("审批条数不能超过10条");
			return;
		}
		//如果没有索引，则在最后插入
		if(index==null){
			//添加一组控件
			$("#_form").append("<div id='title_"+currentIndex+"' name='title' class='float-l'><div class='Dialog-form-title'><label for='check_titles_"+currentIndex
					+"' class='field'>审核标题：</label></div><div class='Dialog-form-item'><input style='width:110px;' id='check_titles_"+currentIndex
					+"' name='check_title' class='easyui-validatebox' /><input name='checkbox' style='margin-left:8px;width:15px;' id='checkbox_"+currentIndex
					+"' type='checkbox'>控制下级<input id='title_ids_"+currentIndex+"' name='title_ids' type='hidden' /></div></div>");
			
			$("#_form").append("<div id='group_"+currentIndex+"' name='group' class='float-l'><div class='Dialog-check-title'><label for='check_group_"+currentIndex
					+"' class='field'>审核组：</label></div><div class='Dialog-check-item'><input id='check_group_"+currentIndex
					+"' name='check_group' class='easyui-combobox' style='width: 110px' data-options='required:true' /><input style='width:15px;margin-left:3px' onclick='deleteCtrl("+currentIndex
							+")' type='button' class='del-element' /><input style='width:15px;margin-left:3px' onclick='addCtrl("+currentIndex+")' type='button' class='add-element' /></div></div>");
			
			//为添加的行设置必填
			$("#check_titles_"+currentIndex).validatebox({    
			    required: true,    
			});
			$("#"+"check_group_"+currentIndex).combobox({
				url : global_param.context_name + "/system/permission/groups",
				method : 'get',
				editable:false,
				valueField : 'group_id',
				textField : 'group_name',
			});
		}else{
			//如果索引index存在，则在索引后面添加一组控件
			$("#group_"+index).after("<div id='title_"+currentIndex+"' name='title' class='float-l'><div class='Dialog-form-title'><label for='check_titles_"+currentIndex
					+"' class='field'>审核标题：</label></div><div class='Dialog-form-item'><input style='width:110px;' id='check_titles_"+currentIndex
					+"' name='check_title' class='easyui-validatebox' /><input name='checkbox' style='margin-left:8px;width:15px;' id='checkbox_"+i
					+"' type='checkbox'>控制下级<input id='title_ids_"+currentIndex+"' name='title_ids' type='hidden' /></div></div>");
			
			$("#title_"+currentIndex).after("<div id='group_"+currentIndex+"' name='group' class='float-l'><div class='Dialog-check-title'><label for='check_group_"+currentIndex
					+"' class='field'>审核组：</label></div><div class='Dialog-check-item'><input id='check_group_"+currentIndex
					+"' name='check_group' class='easyui-combobox' style='width: 110px' data-options='required:true' /><input style='width:15px;margin-left:3px' onclick='deleteCtrl("+currentIndex
							+")' type='button' class='del-element' /><input style='width:15px;margin-left:3px' onclick='addCtrl("+currentIndex+")' type='button' class='add-element' /></div></div>");
			
			//为添加的行设置必填
			$("#check_titles_"+currentIndex).validatebox({    
			    required: true,    
			});
			$("#"+"check_group_"+currentIndex).combobox({
				url : global_param.context_name + "/system/permission/groups",
				method : 'get',
				editable:false,
				valueField : 'group_id',
				textField : 'group_name',
			});
			//审核次数加1
			$("#check_times").val(Number($("#check_times").val())+1);
		}
		//当前索引加1，控件组数加1
		currentIndex++;//索引+1
		ctrNumber++;//控件对数+1
	}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}">
				<input id="check_id" name="check_id" type="hidden" value="${sysCheckVO.check_id}" />
				<input id="check_titles" name="check_titles" type="hidden" value="${sysCheckVO.check_titles}"/>
				<input id="check_groups" name="check_groups" type="hidden" value="${sysCheckVO.check_groups}"/>
				<input id="check_title_ids" name="check_title_ids" type="hidden" value="${sysCheckVO.check_title_ids}"/>
				<input id="check_types" name="check_types" type="hidden" value="${sysCheckVO.check_types}"/>
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="check_name" class="field">审核名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="check_name" name="check_name"
							class="easyui-validatebox"
							data-options="required:true,validType:['zh_en','nosp','length[0,50]']"
							value="${sysCheckVO.check_name}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="check_code" class="field">审核编码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="check_code" name="check_code" type="text"
							class="easyui-validatebox"
							data-options="required:true,validType:['zh_en','nosp','length[0,50]']"
							value="${sysCheckVO.check_code}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="check_times" class="field">审核次数：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="check_times" name="check_times" type="text"
							class="easyui-validatebox"
							data-options="required:true,validType:['num','length[0,10]']"
							value="${sysCheckVO.check_times}" 
							onblur="initList()"/>
					</div>
				</div>
				<br/><br/><br/><br/>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>