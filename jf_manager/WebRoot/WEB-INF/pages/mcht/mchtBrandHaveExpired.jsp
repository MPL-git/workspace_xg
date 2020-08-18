<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    
    <link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	
     <script type="text/javascript">
    
     
     function immediatelySuspend(id){
   	  
 	  	$.ligerDialog.confirm('确认后，商品牌的运营状态将变更为：暂停', function (yes) {
 			if(yes){
 				$.ajax({
 					url : "${pageContext.request.contextPath}/mcht/immediatelySuspend.shtml",
 					type : 'POST',
 					dataType : 'json',
 					cache : false,
 					async : false,
 					data : {
 						id:id
 					},
 					timeout : 30000,
 					success : function(data) {
 						if ("0000" == data.returnCode) {
 							$.ligerDialog.success("操作成功!");
 							$("#searchbtn").click();
 						}else{
 							$.ligerDialog.error(data.returnMsg);
 						}
 						
 					},
 					error : function() {
 						$.ligerDialog.error('操作超时，请稍后再试！');
 					}
 				});
 				
 				
 			}
 			
 		});
   }
     
     
     var viewerPictures;
    $(function(){
     	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
    });

     function editMchtProductBrand(mchtBrandChgId) {
    		$.ligerDialog.open({
    			height: $(window).height() - 100,
    			width: $(window).width() - 200,
	    		title: "商家授权品牌更新审核",
	    		name: "INSERT_WINDOW",
	    		url: "${pageContext.request.contextPath}/mcht/editmchtprandapti.shtml?mchtBrandChgId=" + mchtBrandChgId,
	    		showMax: true,
	    		showToggle: false,
	    		showMin: false,
	    		isResize: true,
	    		slide: false,
	    		data: null
    		});
    	}
     
		function editMchtBaseInfo(id) {
			$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "商家基础资料",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
		} 
		
	     function viewMchtProductBrandPics(mchtProductBrandId){
	    	 $.ligerDialog.open({
	 			height: $(window).height() - 100,
	 			width: $(window).width() - 200,
		    		title: "品牌资料归档情况",
		    		name: "INSERT_WINDOW",
		    		url: "${pageContext.request.contextPath}/mcht/viewMchtProductBrandPics.shtml?hideRadio=1&id=" + mchtProductBrandId,
		    		showMax: true,
		    		showToggle: false,
		    		showMin: false,
		    		isResize: true,
		    		slide: false,
		    		data: null
	 		}); 
	     }
	     
	     function viewMchtProductBrand(mchtProductBrandId){
	    	 $.ligerDialog.open({
	 			height: $(window).height() - 100,
	 			width: $(window).width() - 200,
		    		title: "品牌信息",
		    		name: "INSERT_WINDOW",
		    		url: "${pageContext.request.contextPath}/mcht/viewMchtProductBrand.shtml?mchtProductBrandId=" + mchtProductBrandId,
		    		showMax: true,
		    		showToggle: false,
		    		showMin: false,
		    		isResize: true,
		    		slide: false,
		    		data: null
	 		}); 
	     }
     
     function viewerMchtPic(mchtProductBrandId,picType){
    		
    		var url;
    		if(picType==1){
    			url="${pageContext.request.contextPath}/mcht/getMchtBrandPicChg.shtml";
    		}
    		if(picType==2){
    			url="${pageContext.request.contextPath}/mcht/getPlatfromAuthPicChg.shtml";
    		}
    		
    		$("#viewer-pictures").empty();
    		viewerPictures.destroy();
    		$.ajax({
    			url : url,
    			type : 'POST',
    			dataType : 'json',
    			cache : false,
    			async : false,
    			data : {mchtProductBrandId:mchtProductBrandId},
    			timeout : 30000,
    			success : function(data) {
    				console.log(data);
    				if(data&&data.length>0){
    					for (var i=0;i<data.length;i++)
    					{
    						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
    					}
    					viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    						$("#viewer-pictures").hide();
    					}});
    					$("#viewer-pictures").show();
    					viewerPictures.show();
    				}
    				
    				
    			},
    			error : function() {
    				$.ligerDialog.error('操作超时，请稍后再试！');
    			}
    		});
    		
    	}
     
     	var date = new Date();
	    var listConfig={
	    		  
	    	      url:"/mcht/mchtBrandHaveExpiredList.shtml",
	    	      
	    	      listGrid:{ columns: [
		    			          { display: '品牌开通日期', name: 'createDate', width:180,render:function(rowdata,rowindex){
		    			        	  if (rowdata.createDate!=null) {
		    			               var createDate=new Date(rowdata.createDate);
		    			               return createDate.format("yyyy-MM-dd");									
									}else{
									   return "";
									}
		    			           }},   
		    			           { display: '招商对接人', name: 'zsContactName',width:100},
		    			           { display: '商家序号', name: 'mchtCode',width:180},
		    			           { display: '品牌名称', name: 'productBrandName',width:180},
	    	                       { display: '公司名称', name: 'companyName',width:180},  
		    			           { display: '店铺名称', name: 'shopName',width:180},
	    			                { display: '公司/经营信息', name: 'productBrandName',width:120 , render: function(rowdata, rowindex){
					  					var html = [];
					  					 html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">查看</a>"); 
					  					return html.join("");
				  					}},
	    			                { display: '品牌授权到期日期',  name: 'platformAuthExpDate', width: 150,render:function(rowdata,rowindex){
			    			        	  if (rowdata.platformAuthExpDate!=null) {
			    			                	if(date.getTime()>Number(rowdata.platformAuthExpDate)){
			    			                		var platformAuthExpDate=new Date(rowdata.platformAuthExpDate);   
			    			                		return platformAuthExpDate.format("yyyy-MM-dd");			
					    			               }else{
					    			            	   return ""; 
					    			               }
	    			                		}else{
											   return "";
											}
				    			           }},  
	    			                { display: '其它许可证有效期', name: 'otherExpDate', width: 150 ,render:function(rowdata,rowindex){
	    			                	if (rowdata.otherExpDate!=null) {
		    			                	if(date.getTime()>Number(rowdata.otherExpDate)){
		    			                		var otherExpDate=new Date(rowdata.otherExpDate);   
		    			                		return otherExpDate.format("yyyy-MM-dd");			
				    			               }else{
				    			            	   return ""; 
				    			               }
    			                		}else{
										   return "";
										}
			    			           }}, 
	    			                { display: '品牌状态',  name: 'statusDesc', width: 100}, 
	    			                { display: '运营对接人', name: 'yyContactName',width:100},
    		 		                { display: '法务对接人', name: 'fwContactName',width:100},
    		 		                { display: '操作', name: "operation",width:100 , render: function(rowdata, rowindex) {
    		 		                	if(rowdata.status!=2){
	    		 		                	if(${staffID} == rowdata.yyStaffId || ${staffID} == 1){
	    		 		                		return "<a href=\"javascript:viewMchtProductBrand(" + rowdata.id + ");\">查看</a><br><a href=\"javascript:immediatelySuspend(" + rowdata.id + ");\">立即暂停</a>";
	    		 		                	}else{
	    		 		                		return "<a href=\"javascript:viewMchtProductBrand(" + rowdata.id + ");\">查看</a>";
	    		 		                	}
    		 		                	}else{
    		 		                		return "<a href=\"javascript:viewMchtProductBrand(" + rowdata.id + ");\">查看</a>";
    		 		                	}
    				              	}}
	    			                
	    			                ],   
	    	                 showCheckbox : false,  //不设置默认为 true
	    	                 showRownumber:true //不设置默认为 true
	    	      } , 							
	    	     container:{
	    	        toolBarName:"toptoolbar",
	    	        searchBtnName:"searchbtn",
	    	        fromName:"dataForm",
	    	        listGridName:"maingrid"
	    	      }        
	    	  };
    </script>
  </head>
  
  <body style="padding: 0px; overflow: hidden;">
    <div class="l-loading" style="display: block" id="pageloading"></div>
	  <div id="toptoolbar"></div>
  
	  <form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
				<div class="search-td-label">商家序号:</div>
				<div class="search-td-condition" >
					<input type="text" id="mchtCode" name="mchtCode" >
				</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label">公司名称:</div>
				<div class="search-td-condition" >
					<input type="text" id = "companyName" name="companyName" >
				</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label">店铺名称:</div>
				<div class="search-td-condition" >
					<input type="text" id = "shopName" name="shopName" >
				</div>
				</div>
											
				<div class="search-td">
					<div class="search-td-label">类目：</div>
					<select id="suitGroup" name="productType" class="productType" style="width: 150px;">
								<option value="">请选择</option>
							<c:forEach  var="sysStaffProductTypeCustom" items="${Rows}">
								<option value="${sysStaffProductTypeCustom.productTypeId}">${sysStaffProductTypeCustom.staffName}</option>
							</c:forEach>
						</select>
				</div>	
			</div> 
				 
			<div class="search-tr"  >

			 	 
			 	 <div class="search-td">
					<div class="search-td-label">平台对接人：</div>
					<select id="suitGroup" name="platformContact" class="platformContact" style="width: 150px;">
							<c:if test="${isManagement == 1}">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${staffID}" >我自己的商家</option>
							<c:forEach items="${sysStaffInfoCustomList}" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId}">${sysStaffInfoCustom.staffName}的商家</option>
							</c:forEach>
						</select>
				</div>		

				<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
				</div>
			</div>
			
			<!-- <div class="search-tr"  > 
				<div class="search-td">
			 	<div class="search-td-label" >只看本人领取</div>
			 	<div class="search-td-condition" style="text-align: left;width: 5%;">
					<input type="checkbox" value="1" name="is_my_audit">
				</div>
				</div>

			</div> -->
				
			
		</div>
		
		<div id="maingrid" style="margin-top: 20px; padding: 0"></div>
	</form>
	
		<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
