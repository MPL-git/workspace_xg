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
    
     
     function getAudit(id){
   	  
 	  	$.ligerDialog.confirm('确认领取？', function (yes) {
 			if(yes){
 				$.ajax({
 					url : "${pageContext.request.contextPath}/mcht/getAudit.shtml",
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
 							$.ligerDialog.success("领取成功!");
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
     
	    var listConfig={
	    		  
	    	      url:"/mcht/mchprandupdatecheck/list.shtml",
	    	      
	    	      listGrid:{ columns: [
		    			          { display: '提审日期', name: 'commitDate', width:180,render:function(rowdata,rowindex){
		    			        	  if (rowdata.commitDate!=null) {
		    			               var commitDate=new Date(rowdata.commitDate);
		    			               return commitDate.format("yyyy-MM-dd");									
									}else{
									   return "";
									}
		    			           }},   
		    			           { display: '招商对接人', name: 'zsContactName',width:180},
		    			           { display: '商家序号', name: 'mchtCode',width:180},
	    	                       { display: '公司名称', name: 'shortName',width:180 , render: function(rowdata, rowindex){
					  					var html = [];
					  					 html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">"+rowdata.companyName+"</a>"); 
					  					return html.join("");
				  					}},  
		    			           { display: '店铺名', name: 'shopName',width:180},
	    			                { display: '品牌LOGO', name: 'logo',width:180,render: function(rowdata, rowindex) {
	    			  				    return "<div style='padding:3px;'><img style='width:60px;height:40px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.logo+"'></div>";
	    			     			 } },

	    			                { display: '品牌名称', name: 'productBrandName',width:180},
	    			                { display: '品牌库ID', name: 'productBrandId',width:180},
	    			                { display: '资质类型',  name: 'aptitudeTypeDesc', width: 100}, 
	    			                { display: '授权有效期', name: 'platformAuthExpDate', width: 180 ,render:function(rowdata,rowindex){
	    			                	var platformAuthExpDate=new Date(rowdata.platformAuthExpDate);
	    			                	return platformAuthExpDate.format("yyyy-MM-dd");
	    			                }},
	    			                { display: '运营状态',  name: 'statusDesc', width: 100}, 
	    			                { display: '审核状态',   name: 'auditStatusDesc' , width: 180,render:function(rowdata,rowindex){
	    			                	var html = [];
	    			                	if((rowdata.auditStatus == "1"||rowdata.auditStatus == "2") && (rowdata.fwStaffId==${sessionScope.USER_SESSION.staffID} || rowdata.fwAssistantid==${sessionScope.USER_SESSION.staffID})){//1待审
	    			                		if(rowdata.isAuditRecommit == "1"){
	    			                			html.push("<a href=\"javascript:editMchtProductBrand(" + rowdata.id + ");\">"+rowdata.auditStatusDesc+"(重新提交)</a>&nbsp;&nbsp;");
	    			                		} else {
	    			                			html.push("<a href=\"javascript:editMchtProductBrand(" + rowdata.id + ");\">"+rowdata.auditStatusDesc+"(首次提交)</a>&nbsp;&nbsp;");
	    			                		}
	    			                	}else{
	    			                		 html.push(rowdata.auditStatusDesc);
	    			                	}
	    							    return html.join("");
	    			                }
	    			                },
    		 		                { display: "法务对接人", name: "fwContactName",width:100 , render: function(rowdata, rowindex) {
    		 		                	if((rowdata.fwContactName==null||rowdata.fwContactName=="")&&rowdata.auditStatus=="1"){
    		 		                		return "<a href=\"javascript:getAudit(" + rowdata.mchtId + ");\">领取</a>";
    		 		                	}else{
    		 		                		return rowdata.fwContactName;
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
					<div class="search-td-label" style="float:left;width: 20%;">提审日期：</div>
					<div class="l-panel-search-item">
						<input type="text" id="commit_date_begin" name="commit_date_begin" />
						<script type="text/javascript">
							$(function() {
								$("#commit_date_begin").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item">
						<input type="text" id="commit_date_end" name="commit_date_end" />
						<script type="text/javascript">
							$(function() {
								$("#commit_date_end").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>
				
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
			</div> 
				 
			<div class="search-tr"  >
			 	<div class="search-td">
				 <div class="search-td-label">资质类型:</div>
				 <div class="search-td-condition" >
					 <select  id="APTITUDETYPE" name="APTITUDETYPE"
						class="querysel">
						<option value="">请选择</option>
						<c:forEach var="list" items="${APTITUDE_TYPE}">
							<option value="${list.statusValue}">${list.statusDesc}</option>
						</c:forEach>
					</select>
				 </div>
				 </div>
			
				<div class="search-td">
				 <div class="search-td-label" >审核状态:</div>
				 <div class="search-td-condition" >
					<select id="AUDITSTATUS" name="AUDITSTATUS" class="querysel">
						<option value="">请选择</option> 
						<c:forEach var="list" items="${aptitudetypeList}">
							<option value="${list.statusValue}" <c:if test="${list.statusValue == '1' }">selected</c:if> >${list.statusDesc}</option>
						</c:forEach>
<!-- 						<option value="${Item_value}">${Item_desc}</option>  -->
<!-- 						<c:forEach var="list" items="${AUDIT_STATUS}"> -->
<!-- 							<option value="${list.statusValue}">${list.statusDesc} -->
<!-- 							</option> -->
<!-- 						</c:forEach> -->
					</select>
			 	 </div>
			 	 </div>		
			 	 
			 	 <div class="search-td">
					<div class="search-td-label">平台对接人：</div>
					<select id="suitGroup" name="platContactStaffId" class="platformContact" style="width: 150px;">
							<c:if test="${isManagement == 1}">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${staffID}" >我自己的商家</option>
							<c:forEach items="${sysStaffInfoCustomList}" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId}">${sysStaffInfoCustom.staffName}的商家</option>
							</c:forEach>
						</select>
				</div>		
				
				<div class="search-td">
					<div class="search-td-label">类目：</div>
					<select id="suitGroup" name="productTypeId" class="productType" style="width: 150px;">
								<option value="">请选择</option>
							<c:forEach  var="sysStaffProductTypeCustom" items="${Rows}">
								<option value="${sysStaffProductTypeCustom.productTypeId}">${sysStaffProductTypeCustom.staffName}</option>
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
				
				<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
				</div>
			</div> -->
				
			
		</div>
		
		<div id="maingrid" style="margin-top: 20px; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
