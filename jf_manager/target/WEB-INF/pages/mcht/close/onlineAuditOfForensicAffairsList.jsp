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

<style type="text/css">
	.lineFeed {
  width: 180px;
  word-wrap: break-word;
}
</style>

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	
     <script type="text/javascript">
   //上传合同图片页面
     function toUploadPic(mchtCloseId) {
     	var role107 = ${role107};
     	if(role107){
     		$.ligerDialog.open({
     			height: $(window).height() - 100,
     			width: $(window).width() - 250,
     			title: "未上传",
     			name: "INSERT_WINDOW",
     			url: "${pageContext.request.contextPath}/mcht/close/toUploadPic.shtml?id=" + mchtCloseId,
     			showMax: true,
     			showToggle: false,
     			showMin: false,
     			isResize: true,
     			slide: false,
     			data: null
     		});
     	}else{
     		$.ajax({
     			url : "${pageContext.request.contextPath}/mcht/close/checkFwPlatformContact.shtml",
     			type : 'POST',
     			dataType : 'json',
     			cache : false,
     			async : false,
     			data : {
     				mchtCloseId:mchtCloseId
     			},
     			timeout : 30000,
     			success : function(data) {
     				if ("0000" == data.returnCode) {
     					$.ligerDialog.open({
     						height: $(window).height() - 100,
     						width: $(window).width() - 250,
     						title: "未上传",
     						name: "INSERT_WINDOW",
     						url: "${pageContext.request.contextPath}/mcht/close/toUploadPic.shtml?id=" + mchtCloseId,
     						showMax: true,
     						showToggle: false,
     						showMin: false,
     						isResize: true,
     						slide: false,
     						data: null
     					});
     				}else{
     					$.ligerDialog.error(data.returnMsg);
     				}
     			},
     			error : function() {
     				$.ligerDialog.error('操作超时，请稍后再试！');
     			}
     		});
     	}
     }
     
   function waitToAudit(mchtCloseId){
		// 合同审核
			var role107 = ${role107};
			if(role107){
				$.ligerDialog.open({
					height: $(window).height()*0.8,
					width: $(window).width()*0.8,
					title: "处理审核",
					name: "INSERT_WINDOW",
					url: "${pageContext.request.contextPath}/mcht/close/waitToAudit.shtml?id=" + mchtCloseId,
					showMax: true,
					showToggle: false,
					showMin: false,
					isResize: true,
					slide: false,
					data: null
				});
			}else{
				$.ajax({
					url : "${pageContext.request.contextPath}/mcht/close/checkFwPlatformContact.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {
						mchtCloseId:mchtCloseId
					},
					timeout : 30000,
					success : function(data) {
						if ("0000" == data.returnCode) {
							$.ligerDialog.open({
								height: $(window).height()*0.8,
								width: $(window).width()*0.8,
								title: "处理审核",
								name: "INSERT_WINDOW",
								url: "${pageContext.request.contextPath}/mcht/close/waitToAudit.shtml?id=" + mchtCloseId,
								showMax: true,
								showToggle: false,
								showMin: false,
								isResize: true,
								slide: false,
								data: null
							});
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
   }
   
   function toFixRemarks(mchtCloseId){
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/close/checkFwPlatformContact.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {
				mchtCloseId:mchtCloseId
			},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$.ligerDialog.open({
						height:350, 
						width: 650,
						title: "修改内部备注",
						name: "INSERT_WINDOW",
						url: "${pageContext.request.contextPath}/mcht/close/toFixRemarks.shtml?mchtCloseId=" + mchtCloseId,
						showMax: true,
						showToggle: false,
						showMin: false,
						isResize: true,
						slide: false,
						data: null
					});
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
   }
   
   
   
	    var listConfig={
	    		  
	    	      url:"/mcht/close/onlineAuditOfForensicAffairsListData.shtml?progressStatus=5",
	    	      
	    	      listGrid:{ columns: [
	    	                           
 		    			          { display: '提交日期', name: 'contractUploadDate', width:100,render:function(rowdata,rowindex){
		    			        	  	if(rowdata.contractUploadDate){
		    			                	var contractUploadDate=new Date(rowdata.contractUploadDate);
		    			                	return contractUploadDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }},
		    			           
		    			            { display: '招商对接人', name: 'platformMerchantsContact',width:180 },
		    			            
		    			            { display: '商家序号',   width: 150 , render: function(rowdata, rowindex){
											var html = [];
											html.push(rowdata.mchtCode);
											html.push("<br>");
											if(rowdata.mchtType == "2"){
												html.push("POP");
											}else if (rowdata.mchtType == "1" && rowdata.isManageSelf == "1"){
												html.push("自营SPOP");
											}else {
												html.push("SPOP");
											}
											return html.join("");
				  					}},
		    			           
					                { display: '公司名称',  name: 'companyInfo', width: 150 , render: function(rowdata, rowindex){
					  					var html = [];
					  					html.push(rowdata.companyName+"<br>");
					  					return html.join("");
				  					}}, 
				  					
				  				   { display: '店铺名称',  name: 'companyInfo', width: 150 , render: function(rowdata, rowindex){
					  					var html = [];
					  					html.push(rowdata.shopName);
					  					return html.join("");
				  					}},
				  					
	    			              /*  { display: '终止合同线上审核', name: 'mchtProductBrands',width:180 }, */
	    			               
	    			                { display: '终止合同线上审核', name: 'contractAuditStatus', width:350, render:function(rowdata,rowindex){
	    			                	var closeAuditStatus = rowdata.contractAuditStatus
	    			                	var html = [];
	    			                	if(closeAuditStatus == 0){
	    			                		html.push("<a href=\"javascript:toUploadPic(" + rowdata.id + ");\">未上传【上传】</a>&nbsp;&nbsp;");
	    			                	}else if(closeAuditStatus == 2){
	    			                		html.push("通过")
	    			                	}else if(closeAuditStatus == 3){ 
	    			                		html.push("驳回")
	    			                	}else if(closeAuditStatus == 1){
	    			                		html.push("<a href=\"javascript:waitToAudit(" + rowdata.id + ");\">已上传待审</a>&nbsp;&nbsp;");
	    			                	}
	    			                	return html.join('');

		    			           }},
		    			           { display: '内部备注', name: 'innerRemarks',width:190 ,render:function(rowdata,rowindex){
		    			        		var html = [];
		    			        		if(rowdata.innerRemarks!=null && rowdata.innerRemarks!=''){
		    			        		html.push("<div class='lineFeed'>"+rowdata.innerRemarks+"</div>")
		    			        		html.push("<a href=\"javascript:toFixRemarks(" + rowdata.id + ");\">【修改】</a>&nbsp;&nbsp;");
		    			        		}else{
		    			        			html.push("")
		    			        		}
		    			        		return html.join('');
		    			           }},
		    			           
		    			           { display: '驳回原因', name: 'contractAuditRemarks',width:180 },
	    	
		    			/*            { display: '查看详情', name: 'detail', width: 80 ,render:function(rowdata,rowindex){
		    			        	   var html = [];
			   						   html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">店铺主信息</a><br>"); 
			   						   html.push("<a href=\"javascript:editMchtFinanceInfo(" + rowdata.mchtId + ");\">财务信息</a><br>"); 
			   						   html.push("<a href=\"javascript:mchtContact(" + rowdata.mchtId + ");\">商家对接人</a><br>"); 
			   						   return html.join("");
	    			                }}, */
	    			  
	    			                { display: '法务对接人', name: 'platformFawuContact',width:180 }
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
  
  <body>
    <div class="l-loading" style="display: block" id="pageloading"></div>
	  <div id="toptoolbar"></div>
  
	  <form id="dataForm" runat="server">
	  	<input type="hidden" name="delApply" id="delApply" value="${delApply}">
	  <%-- 	<input type="hidden" name="progressStatus" id="progressStatus" value="${progressStatus}"> --%>
		<div class="search-pannel">
				<div class="search-tr"  >
				
					<div class="search-td">
						<div class="search-td-label">主营类目:</div>
						<div class="search-td-condition" >
							<select  id="productTypeId" name="productTypeId" class="querysel">
								<option value="">请选择</option>
								<c:forEach var="productType" items="${productTypeListByStaffId}">
									<option value="${productType.id}">${productType.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>

				
				 	<div class="search-td">
						<div class="search-td-label">公司名称:</div>
						<div class="search-td-condition" >
							<input type="text" id="mchtName" name="mchtName" >
						</div>
					</div>
					
					<div class="search-td">
						<div class="search-td-label">商家序号:</div>
						<div class="search-td-condition" >
							<input type="text" id="mchtCode" name="mchtCode" >
						</div>
					</div>
					
					<div class="search-td">
					<div class="search-td-label" >对接人：</div>
					<div class="search-td-condition">
						<select id="platContactStaffId" name="platContactStaffId">
							<c:if test="${isManagement == 1}">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${staffID}" selected="selected" >我自己的商家</option>
							<c:forEach items="${sysStaffInfoCustomList}" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId}">${sysStaffInfoCustom.staffName}的商家</option>
							</c:forEach>
						</select>
					</div>
				</div>
					
					
				</div>
				
				<div class="search-tr"  >

					
					<div class="search-td">
						<div class="search-td-label">合同审核状态:</div>
						<div class="search-td-condition" >
							<select  id="contractAuditStatus" name="contractAuditStatus" class="querysel">
								<!-- <option value="0" selected="selected">待确认</option> -->	
								<option value="">请选择</option>
								<option value="1">已上传待审核</option>
								<option value="2">通过</option>
								<option value="3">驳回</option>
							</select>
						</div>
					</div>

					<div class="search-td">
						<div class="search-td-label" style="float:left;">是否自营：</div>
						<div class="search-td-condition" >
							<select id="isManageSelf" name="isManageSelf" style="width: 135px;">
								<option value="">请选择</option>
								<option value="0">非自营</option>
								<option value="1">自营</option>
							</select>
						</div>
					</div>

					
					<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
					</div>
				</div>
				
			
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	
		<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
