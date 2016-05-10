<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/purchase/purOrderCheck.js"></script>

<!-- <script type="text/javascript" src="/HRERP/resources/js/supply/supInfoManage.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/JsUtil/userHabits.js"></script> -->

<div class="easyui-layout">
	<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix" >
					<form id="searchForm" onsubmit="return false;">
						
						<div class="float-l">
							<div class="l-panel-search-title">选择供方：</div>
							<div class="l-panel-search-item">
								<input id="search_photos_id" name="supply_id" type="text" />
							</div>
						</div>
						
						<div class="float-l">
							<input id="" class="btn-search"
								onmouseover="this.className='btn-search-over'"
								onmouseout="this.className='btn-search'"
								onmousedown="this.className='btn-search-down'" type="button"
								onclick="getImage();" /> 
						</div>
						
						<!-- 加入了两个空格 使其可以于选择供方对齐 -->
						<br><br>
						
						<div class="l-panel-search-title">选择图片：</div>
						
						<div>
							<input type="radio" value="sup_goodticketphoto" name="option">随货票样
							<input type="radio" value="sup_sealmoldphoto" name="option">印章印模
						</div>

					</form>
						
				</div>
			</div>
	</div>
	
	<script type="text/javascript"> 

		function getImage(){
			
			//获取搜索框中输入的id与所选择的单选框
		    var searchId = document.getElementById('search_photos_id').value;
		    var userOption = $("input[name='option']:checked").val();
		    var goodticketphotoItem = "sup_goodticketphoto";
		    
		    if(searchId == ""){
		    	alert("请输入所选择的供方编码");
		    }
		    
		    //必须用原来必须用typeof方法来判断undefined的数据
		    else if(typeof(userOption) == "undefined"){
		    	alert("请选择需要显示的图片类型");
		    }
		    
		    else{
			    //使用jquery的ajax提交请求
			    $.ajax({
			    	
			    	type:"get",
			    	url:"supphotosmanagecontroller/getStampFileUrl.do",
			    	data:{
			    		searchId:searchId,
			    		userOption:userOption
			    	},
			    	success : function(data) {
			    		//成功时候的回调函数
			    		if(data!=""){
			    			
			    			//处理返回的图片路径字符串 找到本地图片
			    			var index=data.indexOf("\HRERP");
			    			var subString=data.substring(index-1);
			    			var url=subString.replace(/\\/g,"/");//将斜杠\全部替换为/
			    			document.getElementById("imgPre").style.display="block";//将隐藏图片显示
			    			$("#imgPre").attr("src",url);
 		    				
/* 			    			alert(url); 
	 		    			alert(data); */
			    		}
			    	},
			    	
			        error : function(data) {  
			        	//失败时候的回调函数
			            if(userOption == goodticketphotoItem){
		    				alert("编码为"+searchId+" 的供方不存在随货票样的图片");
		    			}
		    			else{
		    				alert("编码为"+searchId+" 的供方不存在印章印模的图片");
		    			}
			       }
			    	

			    });
		    	
		    	
		    }
		}
		
	</script> 

	<form action=""> 
		<img  id="imgPre" src="" width="1300px" height="750px" style="display: block;" alt="暂无图像" /> 
		
		
<!-- 								<div class="float-l">
							<div class="l-panel-search-title">地区药监：</div>
							<div class="l-panel-search-item">
								<input id="search_sup_drugid" name="sup_drugid" type="text" />
							</div>
						</div> -->
	</form> 
	
</div>