/**
 * 用户习惯js(包括用户上下键选中行,拖拽列，设置显示列以及保存用户习惯)
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-6-8
 */

function userHabits(){
	
	var obj = new Object();
	
	//定义全局变量
	obj.p_id = null;//页面ID
	obj.c_id = null;//datagrid ID
	obj.win = null;//配置页面ID
	
	
	
	//键盘上下键换行
	$.extend($.fn.datagrid.methods, {
			//键盘上下键换行
		    keyCtr : function (jq) {
		        return jq.each(function () {
		            var grid = $(this);
		            grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function (e) {
		            	var rows = grid.datagrid('getRows');
	                    var selected = grid.datagrid('getSelected');
	                    var index = grid.datagrid('getRowIndex', selected);
		                switch (e.keyCode) {
		                case 38: // up
		                    if (index>0) {
		                        grid.datagrid('selectRow', index - 1);
		                    } else {
		                        grid.datagrid('selectRow', rows.length - 1);
		                    }
		                    break;
		                case 40: // down
		                    if (index<rows.length - 1) {
		                        grid.datagrid('selectRow', index + 1);
		                    } else {
		                        grid.datagrid('selectRow', 0);
		                    }
		                    break;
		                }
		            });
		        });
		    },
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
		//拖动自定义列顺序
		columnMoving: function(jq,param){  
	        return jq.each(function(){  
	            var target = this;  
	            var cells = $(this).datagrid('getPanel').find('div.datagrid-header td[field]');  
	            cells.draggable({  
	                revert:true,  
	                cursor:'move',  //pointer
	                edge:5,  
	                proxy:function(source){  
	                    var p = $('<div class="tree-node-proxy tree-dnd-no" style="position:absolute;border:1px solid #ff0000"/>').appendTo('body');  
	                    p.html($(source).text());  
	                    p.hide();  
	                    return p;  
	                },  
	                onBeforeDrag:function(e){  
	                    e.data.startLeft = $(this).offset().left;  
	                    e.data.startTop = $(this).offset().top;  
	                },  
	                onStartDrag: function(){  
	                    $(this).draggable('proxy').css({  
	                        left:-10000,  
	                        top:-10000  
	                    });  
	                },  
	                onDrag:function(e){  
	                    $(this).draggable('proxy').show().css({  
	                        left:e.pageX+15,  
	                        top:e.pageY+15  
	                    });  
	                    return false;  
	                }  
	            }).droppable({  
	                accept:'td[field]',  
	                onDragOver:function(e,source){  
	                    $(source).draggable('proxy').removeClass('tree-dnd-no').addClass('tree-dnd-yes');  
	                    $(this).css('border-left','1px solid #ff0000');  
	                },  
	                onDragLeave:function(e,source){  
	                    $(source).draggable('proxy').removeClass('tree-dnd-yes').addClass('tree-dnd-no');  
	                    $(this).css('border-left',0);  
	                },  
	                onDrop:function(e,source){  
	                    $(this).css('border-left',0);  
	                    var fromField = $(source).attr('field');  
	                    var toField = $(this).attr('field');  
	                    setTimeout(function(){  
	                        swapField(fromField,toField);  
	                        $(target).datagrid();  
	                        $(target).datagrid('columnMoving',param);
	                        alert(obj.toolbars);
	                        $("#"+param.c_id).datagrid("addToolbarItem",obj.toolbars);
	                    },0);  
	                }  
	            });  
	              
	            // swap Field to another location  
	            function swapField(from,to){  
	                var columns = $(target).datagrid('options').columns;  
	                var cc = columns[0];  
	                _swap(from,to);  
	                function _swap(fromfiled,tofiled){  
	                    var fromtemp;  
	                    var totemp;  
	                    var fromindex = 0;  
	                    var toindex = 0;  
	                    for(var i=0; i<cc.length; i++){  
	                        if (cc[i].field == fromfiled){  
	                            fromindex = i;  
	                            fromtemp = cc[i];  
	                        }  
	                        if(cc[i].field == tofiled){  
	                            toindex = i;  
	                            totemp = cc[i];  
	                        }  
	                    }  
	                    //cc.splice(fromindex,1,totemp);  
	                    cc.splice(toindex,0,fromtemp);
	                    if(fromindex>toindex)
	                    	cc.splice(fromindex+1,1);
	                    else
	                    	cc.splice(fromindex,1);
	                    
	                }  
	            }  
	        });  
	    }  
	});

	//配置需要显示的列
	obj.configFunc = function() {
		//重命名窗口
		util.window(obj.win, {
			title : '用户习惯'
		});
		//打开配置页面
		util.openWindow(obj.win, "/HRERP/system/user/config?page_id="+obj.p_id+"&ctrl_id="+obj.c_id);
	};
	
	obj.saveConfig = function(){
		var fields = "";
		var fieldNames = "";
		var widths = "";
		var opts = $('#'+obj.c_id).datagrid('getColumnFields');//获取所有列
		for(var i=0;i<opts.length;i++){
			fields = fields + opts[i];
			var pro = $('#'+obj.c_id).datagrid("getColumnOption",opts[i]);//获取一列的所有属性
			fieldNames = fieldNames + pro.title;
			widths = widths + pro.width;
			if(i<opts.length-1)
			{
				fields = fields + ",";
				fieldNames = fieldNames + ",";
				widths = widths + ",";
			}
		}
		//提交用户习惯
		$.ajax({
			type : "get", // 表单提交类型
			url : global_param.context_name + "/system/user/saveHabits?fields="+fields+"&fieldNames="+fieldNames+"&widths="+widths+"&page_id="+obj.p_id+"&ctrl_id="+obj.c_id, // 表单提交目标
			success : function(data) {
				if(data.result=="success")
				{
					util.show(data.message);
				}else{
					util.error(data.message);
				}
			}
		});
	};
	
	obj.toolbars = new Array({"text":"配置","iconCls":"icon-modify","handler":obj.configFunc},{"text":"保存","iconCls":"icon-add","handler":obj.saveConfig});
	
	
	
	
	
	//第一个参数页面ID,第二个参数datagrid ID，第三个参数配置显示框ID,第四个参数是否启动上下键换行，第五个参数是否启动列拖拽
	obj.loadColumns = function(page_id,ctrl_id,window,keyCtr,columnMoving){
		var flag = 0;//用来加载列拖拽和添加toolbar按钮（只加载一次）
		obj.p_id = page_id;
		obj.c_id = ctrl_id;
		obj.win = window;
		var columns=new Array();
		$.ajax({
			type : "get", // 表单提交类型
			async:false,
			url : global_param.context_name + "/system/user/getHabits?page_id="+page_id+"&ctrl_id="+ctrl_id, // 表单提交目标
			success : function(data) {
				if(data.result=="success")
				{
					//封装datagrid的列及其属性
					columns=[];
					for(var i=0;i<data.sysUserHabits.length;i++)
					{
						var column={};
						column["field"]=data.sysUserHabits[i].habit_field;
						column["title"]=data.sysUserHabits[i].habit_field_name;	
						column["width"]=data.sysUserHabits[i].habit_width;
						column["sortable"]=data.sysUserHabits[i].habit_sortable;
						column["hidden"]=data.sysUserHabits[i].habit_hidden;
						//判断列名是否是性别，状态，时间等
						if(data.sysUserHabits[i].habit_field=="sex")
						{
							column["formatter"] = function(value, row, index) {return util.getDict('SEX', value);};
						}else if(data.sysUserHabits[i].habit_field=="status")
						{
							column["formatter"] = function (value, row, index) {return util.getDict('STATUS', value);};
						}else if(data.sysUserHabits[i].habit_field.indexOf("time")>=0)
						{
							column["formatter"] = function (value, row, index) {return util.formatDate(value);};
						}
						columns.push(column);
					}
					//添加配置和保存功能，自定义列
					$('#'+obj.c_id).datagrid({
						queryParams : {
							flag : 1
						},
						onBeforeLoad:function(param){
							if(flag == 1){
								if(columnMoving){
									$('#'+obj.c_id).datagrid("columnMoving",obj);
								}
								$('#'+obj.c_id).datagrid("addToolbarItem",obj.toolbars);
							}
							flag++;
							//防止此次datagrid初始化数据申请
							if(flag==1){
								return false;
							}
						}
					});
					
					//加载datagrid数据,加载列
					$('#'+obj.c_id).datagrid('options').columns = [columns];
					//添加上下键换行
					if(keyCtr){
						$('#'+obj.c_id).datagrid("keyCtr");//添加上下键选中事件
					}
				}
			}
		});

	};
	return obj;
}





