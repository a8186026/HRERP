/**
 * 数据筛选js(筛选任意数据库数据)
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-11-10
 */

function screening(){
	
	var obj = new Object();
	
	//定义全局变量
	obj.c_id = null;//datagrid ID
	obj.data = null;//datagrid 数据
	obj.win = null;//过滤页面ID
	obj.className = null;//类名
	
	//为datagrid添加拓展事件
	$.extend($.fn.datagrid.methods, {
		    //为datagrid添加toolbar
		    addToolbarItem : function (jq, items) {
				return jq.each(function () {
					var dpanel = $(this).datagrid('getPanel');
					var toolbar = dpanel.children("div.datagrid-toolbar");
					if (!toolbar.length) {
						toolbar = $("<div class=\"datagrid-toolbar\"><table cellspacing=\"0\" cellpadding=\"0\"><tr></tr></table></div>").prependTo(dpanel);
						$(this).datagrid('resize');
					}
					var tr = toolbar.find("tr");
					for (var i = 0; i < items.length; i++) {
						var btn = items[i];
						if (btn == "-") {
							$("<td><div class=\"datagrid-btn-separator\"></div></td>").appendTo(tr);
						} else {
							var td = $("<td></td>").appendTo(tr);
							var b = $("<a href=\"javascript:void(0)\"></a>").appendTo(td);
							b[0].onclick = eval(btn.handler || function () {});
							b.linkbutton($.extend({}, btn, {
									plain : true,
								}));
						}
					}
				});
			},  
	});

	/***
	 * @param ctrl_id datagrid的ID
	 * @param window 筛选窗口ID
	 * @param className 类名
	 * @param userHabits 用户习惯对象
	 */
	obj.loadScreening = function(ctrl_id,window,className,userHabits){
		obj.c_id = ctrl_id;
		obj.win = window;
		obj.className = className;
		
		//修改用户习惯toolbar
		if(userHabits!=null)
			userHabits.toolbars.push({"text":"筛选","iconCls":"icon-modify","handler":obj.openScreening});
		//添加筛选按钮
		$('#'+obj.c_id).datagrid("addToolbarItem",[{"text":"筛选","iconCls":"icon-modify","handler":obj.openScreening}]);
	};
	
	obj.openScreening = function(){
		var fields = "";
		var fieldNames = "";
		
		var opts = $('#'+obj.c_id).datagrid('getColumnFields');
		for(var i = 0;i<opts.length;i++){
			fields = fields + opts[i];
			var pro = $('#'+obj.c_id).datagrid("getColumnOption",opts[i]);
			fieldNames = fieldNames + pro.title;
			if(i<opts.length-1)
			{
				fields = fields + ",";
				fieldNames = fieldNames + ",";
			}
		}
		//重命名窗口
		util.window(obj.win, {
			title : '数据筛选'
		});
		
		//打开筛选页面
		util.openWindow(obj.win, global_param.context_name + "/system/screening?fields="+
				fields+"&fieldNames="+fieldNames+"&className="+obj.className+"&win="+obj.win+"&ctrl_id="+obj.c_id);
	};
	return obj;
}





