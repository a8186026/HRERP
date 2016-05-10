/**
 * 系统键盘操作工具类
 * 
 * @author RiverYao
 * @version v1.2
 * @since 2015-06-17
 */
function pageKeyPress(){
	/**类对象*/
	var obj = new Object();  
	
	/**需要绑定按键的控件ID数组*/
	obj.ctrlsID = null;
	/** 该页面最后的确认键ID*/
	obj.sumbitButtonID = null;
	/**当前的按键序列*/
	obj.ctrlNumber = 0;
	/** 选项卡组ID*/
	obj.tabID = null;
	
	obj.tabChangID = null;
	/** 是否启动enter跳转功能,默认处于开启状态*/
	obj.flag = true;
	
	/**
	 * 设置需要进行tab选项卡切换的当前页面末尾的ID组
	 * @param param_tabID 选项卡组ID 
	 * @param param_tabChangID 需要进行tab选项卡切换的当前页面末尾的ID组
	 * */
	obj.setTabChangeID = function(param_tabID, param_tabChangID){
		obj.tabID = param_tabID;
		obj.tabChangID = param_tabChangID;
	};
	
	/**
	 * 按照ctrlsID的顺序绑定控件回车按键动作
	 * @param ctrlsID 需要绑定按键的控件ID数组
	 * @param sumbitButtonID 该页面最后的确认键ID
	 * */
	obj.bindKeyPressToCtrl = function(param_ctrlsID,param_sumbitButtonID){
		//获得参数
		obj.ctrlsID = param_ctrlsID;
		obj.sumbitButtonID = param_sumbitButtonID;
		//根据当前传入控件ID组以及该控件的类别为其绑定focus动作
		bindfocus(obj.ctrlsID);
		// 绑定按键动作，回车后开始按序列在控件中进行回车跳转
		var e = (typeof event != 'undefined') ? window.event : e;
		// 绑定键盘动作
		document.onkeydown = function(e) {
			if(obj.flag){
				var actElement = document.activeElement;
				//只按下回车,无Ctrl,焦点不是button,焦点不是确认键,焦点不是文本输入框
				if (e.keyCode == 13 && !e.ctrlKey && actElement.type != "button" && actElement.id != obj.sumbitButtonID && actElement.className.indexOf("easyui-validatebox textareaW1")<=-1) {
					//选项卡切换
					if(obj.tabID!=null && obj.tabChangID!=null){
						for(var i = 0 ; i < obj.tabChangID.length ; i++){
							if(actElement.id == obj.tabChangID[i]){
								$('#'+obj.tabID).tabs('select',i+1);
								break;
							}
						}
					}
					//当所有控件全部跳完的时候
					if(obj.ctrlNumber < obj.ctrlsID.length){
						obj.ctrlNumber = focusKeyPress(obj.ctrlsID, obj.ctrlNumber);
					} else if (obj.ctrlNumber>=obj.ctrlsID.length){//全部跳完
						if(obj.sumbitButtonID!=null)
							focusSumbitButton(obj.sumbitButtonID);
					}
				}
				//按下Ctrl+Enter,焦点是文本输入框
				else if(e.keyCode == 13 && e.ctrlKey&& actElement.className.indexOf("easyui-validatebox textareaW1")>-1){ //如果该焦点为textarea 激活组合键
					//选项卡切换
					if(obj.tabID!=null && obj.tabChangID!=null){
						for(var i = 0 ; i < obj.tabChangID.length ; i++){
							if(actElement.id == obj.tabChangID[i]){
								$('#'+obj.tabID).tabs('select',i+1);
								break;
							}
						}
					}
					// 按下组合键后聚焦到下一个失焦
					if (obj.ctrlNumber < obj.ctrlsID.length) {
						obj.ctrlNumber = focusKeyPress(obj.ctrlsID, obj.ctrlNumber);
					} else if (obj.ctrlNumber >= obj.ctrlsID.length) {// 全部跳完
						if(obj.sumbitButtonID!=null)
							focusSumbitButton(obj.sumbitButtonID);
					}
				}
			};
		}
	};
	
	/**
	 * 根据当前传入控件ID组以及该控件的类别为其绑定focus动作
	 * @param ctrlsID 需要绑定按键的控件ID数组
	 * */
	var bindfocus = function(param_ctrlsID){
		//遍历所有的控件数组
		for(var i = 0 ; i < param_ctrlsID.length ; i++){
			//根据不同的控件类型，对控件进行Focus绑定
			//下拉菜单combobox
			if(document.getElementById(param_ctrlsID[i]).className.indexOf("combobox")>-1){	
				//动作绑定，约定所有Panel在聚焦时，直接展开
				$('#'+param_ctrlsID[i]).combobox('textbox').bind('focus', function() {
					//执行当前循环的序列，千万别用I
					$('#'+param_ctrlsID[obj.ctrlNumber]).combo("showPanel");	
				});
				//动作绑定，约定所有Panel在关闭时，修改ctrlNumber
				$('#'+param_ctrlsID[i]).combobox({
					onHidePanel: function(){
						//ComboBox选项卡切换
						if(obj.tabID!=null && obj.tabChangID!=null){
							for(var i = 0 ; i < obj.tabChangID.length ; i++){
								if(this.id == obj.tabChangID[i]){
									$('#'+obj.tabID).tabs('select',i+1);
									break;
								}
							}
						}
						//该控件ID为 this.id
						for(var j = 0 ;  j<param_ctrlsID.length ; j++){
							if(this.id == param_ctrlsID[j]){
								obj.ctrlNumber = j;
								obj.ctrlNumber++; 	
							}
						}
						obj.ctrlNumber = focusKeyPress(obj.ctrlsID, obj.ctrlNumber);
					}
				});
			}
			//树形下拉菜单combotree
			if(document.getElementById(param_ctrlsID[i]).className.indexOf("combotree")>-1){	
				//动作绑定，约定所有Panel在聚焦时，直接展开
				$('#'+param_ctrlsID[i]).combotree('textbox').bind('focus', function() {
					//执行当前循环的序列，千万别用I
					$('#'+param_ctrlsID[obj.ctrlNumber]).combo("showPanel");	
				});
				//动作绑定，约定所有Panel在关闭时，修改ctrlNumber
				$('#'+param_ctrlsID[i]).combotree({
					onHidePanel: function(){
						//ComboTree选项卡切换
						if(obj.tabID!=null && obj.tabChangID!=null){
							for(var i = 0 ; i < obj.tabChangID.length ; i++){
								if(this.id == obj.tabChangID[i]){
									$('#'+obj.tabID).tabs('select',i+1);
									break;
								}
							}
						}
						//该控件ID为 this.id
						for(var j = 0 ;  j<param_ctrlsID.length ; j++){
							if(this.id == param_ctrlsID[j]){
								obj.ctrlNumber = j;
								obj.ctrlNumber++; 	
							}
						}
						obj.ctrlNumber = focusKeyPress(obj.ctrlsID, obj.ctrlNumber);
					}
				});
			}
			//日期框
			if(document.getElementById(param_ctrlsID[i]).className.indexOf("easyui-datebox")>-1){	
				//动作绑定，约定所有Panel在聚焦时，直接展开
				$('#'+param_ctrlsID[i]).datebox().next('span').find('input').on('focus',function() {
					$('#'+param_ctrlsID[obj.ctrlNumber]).combo("showPanel");
				});
				//动作绑定，约定所有Panel在关闭时，修改ctrlNumber
				$('#'+param_ctrlsID[i]).datebox({
					onSelect: function(){
						//Datebox选项卡切换
						if(obj.tabID!=null && obj.tabChangID!=null){
							for(var i = 0 ; i < obj.tabChangID.length ; i++){
								if(this.id == obj.tabChangID[i]){
									$('#'+obj.tabID).tabs('select',i+1);
									break;
								}
							}
						}
						//该控件ID为 this.id
						for(var j = 0 ;  j<param_ctrlsID.length ; j++){
							if(this.id == param_ctrlsID[j]){
								obj.ctrlNumber = j;
								obj.ctrlNumber++; 	
							}
						}
						obj.ctrlNumber = focusKeyPress(obj.ctrlsID, obj.ctrlNumber);
					}
				});
			}
			
			//输入框validatebox
			if(document.getElementById(param_ctrlsID[i]).className.indexOf("easyui-validatebox")>-1){
				$('#'+param_ctrlsID[i]).bind('focus', function() {
					//选中文本
					this.select();
					//Focus时，当前控件为活动控件
					for(var j = 0 ; j < param_ctrlsID.length ;j++){
						if(this.id == param_ctrlsID[j]){
							obj.ctrlNumber = j;
							obj.ctrlNumber++; 	//指向下一个位置，用于平衡回车次数
						}
					}
				});
			}
		}
	};
	
	/**
	 * 取消所有焦点的聚焦动作
	 * @param ctrlsID 需要绑定按键的控件ID数组
	 * */
	var unbindfocus = function(param_ctrlsID){
		//遍历所有的控件数组
		for(var i = 0 ; i < param_ctrlsID.length ; i++){
			//根据不同的控件类型，对控件进行事件解绑
			//下拉菜单combobox
			if(document.getElementById(param_ctrlsID[i]).className.indexOf("combobox")>-1){	
				//解除下拉框事件绑定
				$('#'+param_ctrlsID[i]).combobox('textbox').unbind();
				//$('#'+param_ctrlsID[obj.ctrlNumber]).combo("hidePanel");
			}
			//树形下拉菜单combotree
			if(document.getElementById(param_ctrlsID[i]).className.indexOf("combotree")>-1){	
				//解除树形下拉菜单框事件绑定
				$('#'+param_ctrlsID[i]).combotree('textbox').unbind();
				//$('#'+param_ctrlsID[obj.ctrlNumber]).combo("hidePanel");
			}
			//日期框
			if(document.getElementById(param_ctrlsID[i]).className.indexOf("easyui-datebox")>-1){	
				//解除日期框事件绑定
				$('#'+param_ctrlsID[i]).datebox().next('span').find('input').off();
				//$('#'+param_ctrlsID[obj.ctrlNumber]).combo("hidePanel");
			}
			//输入框validatebox
			if(document.getElementById(param_ctrlsID[i]).className.indexOf("easyui-validatebox")>-1){
				$('#'+param_ctrlsID[i]).unbind();
			}
		}
	};
	
	/**
	 * 根据当前传入控件ID组以及序号Focus到当前的控件
	 * @param ctrlsID 需要绑定按键的控件ID数组
	 * @param ctrlNumber 按键顺序
	 * */
	var focusKeyPress = function(param_ctrlsID,param_ctrlNumber){
		
		var currentCtrlId = param_ctrlsID[param_ctrlNumber];
		var currentCtrl = document.getElementById(currentCtrlId);
		//判断控件类型-由于时真假判断，用平行判断
		if(currentCtrl.className.indexOf("easyui-validatebox validatebox-text")>-1){	//输入框validatebox
			currentCtrl.focus();
			param_ctrlNumber++;
		}
		if(currentCtrl.className.indexOf("combobox")>-1){	//下拉菜单combobox
			$("#"+currentCtrlId).combobox().next('span').find('input').focus();
			param_ctrlNumber++;
		}
		if(currentCtrl.className.indexOf("combotree")>-1){	//树形下拉菜单combotree
			$("#"+currentCtrlId).combotree().next('span').find('input').focus();
			param_ctrlNumber++;
		}
		if(currentCtrl.className.indexOf("easyui-datebox")>-1){	//日期选择
			$("#"+currentCtrlId).datebox().next('span').find('input').focus();
			param_ctrlNumber++;
		}
		if(currentCtrl.className.indexOf("easyui-validatebox textareaW1")>-1){	//文本输入框textarea
			//文本输入框Focus后会自动执行回车键从而使原有内容消除，故在此做强行以Alert恢复
			alert("该控件内回车键用于换行，切换下一个输入框以Ctrl+Enter进行");
			currentCtrl.focus();
			param_ctrlNumber++;
		}
		if(currentCtrl.type == "checkbox"){	//checkBox
			currentCtrl.focus();
			param_ctrlNumber++;
		}
		return param_ctrlNumber;
	};
	
	/**
	 * 将焦点聚焦到确认键
	 * @param param_sumbitButtonID 确认键ID
	 * */
	var focusSumbitButton = function (param_sumbitButtonID){
		document.getElementById(param_sumbitButtonID).focus();
	};
	
	/**
	 * 按照参数修改ctrlNumber序号
	 * @param param_ctrlNumber 要改为的序号
	 * */
	obj.changeCtrlNumber = function(param_ctrlNumber){
		obj.ctrlNumber = param_ctrlNumber;
	};
	/**
	 * 停止当前焦点聚焦动作
	 * @param flag 是否停止键盘监控
	 * @param ctrlsID 需要绑定按键的控件ID数组
	 * */
	obj.stopFocus = function(flag,param_ctrlsID){
		//alert("停止聚焦操作");
		//设置键盘监控开关
		obj.flag = flag;
		//解除所有控件的绑定事件
		unbindfocus(param_ctrlsID);
		//获取当前焦点控件，当前控件和实际相差2，但失焦当前节点会+1，通过-1来隐藏panel，暂时解决问题
		var currentCtrl = null;
		if(obj.ctrlNumber-1>=0)
			currentCtrl = document.getElementById(obj.ctrlsID[obj.ctrlNumber-1]);
		if(currentCtrl!=null&&(currentCtrl.className.indexOf("combobox")>-1||currentCtrl.className.indexOf("combotree")>-1||currentCtrl.className.indexOf("easyui-datebox")>-1))
			$("#"+obj.ctrlsID[obj.ctrlNumber-1]).combo("hidePanel");
	};
	
	return obj;
};