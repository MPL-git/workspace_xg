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
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	
    <script type="text/javascript">
    
//     var viewerPictures;
//     $(function(){
//      	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
//     		$("#viewer-pictures").hide();
//     	}});
//     });
    
//     function changeMchtCompanyInfo(mchtId) {
//     	$.ligerDialog.open({
//     	height: 470,
//     	width: 1000,
//     	title: "商家资质公司信息修改与审核",
//     	name: "INSERT_WINDOW",
//     	url: "${pageContext.request.contextPath}/mcht/changeMchtCompanyInfo.shtml?mchtId=" + mchtId,
//     	showMax: true,
//     	showToggle: false,
//     	showMin: false,
//     	isResize: true,
//     	slide: false,
//     	data: null
//     });
//     }
    
    
//     function viewerMchtPic(mchtId){
// 		var url="${pageContext.request.contextPath}/mcht/getMchtBlPic.shtml";
// 		$("#viewer-pictures").empty();
// 		viewerPictures.destroy();
// 		$.ajax({
// 			url : url,
// 			type : 'POST',
// 			dataType : 'json',
// 			cache : false,
// 			async : false,
// 			data : {mchtId:mchtId},
// 			timeout : 30000,
// 			success : function(data) {
// 				console.log(data);
// 				if(data&&data.length>0){
// 					for (var i=0;i<data.length;i++)
// 					{
// 						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
// 					}
// 					viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
// 						$("#viewer-pictures").hide();
// 					}});
// 					$("#viewer-pictures").show();
// 					viewerPictures.show();
// 				}
// 			},
// 			error : function() {
// 				$.ligerDialog.error('操作超时，请稍后再试！');
// 			}
// 		});
// 	}


    	// 公司/ 经营信息
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
    	
    	// 财务信息
    	function editMchtFinanceInfo(id) {
			$.ligerDialog.open({
				height: $(window).height(),
				width: $(window).width() - 200,
				title: "商家财务信息",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/mchtFinanceInfoEdit.shtml?mchtId=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
    	
    	// 联系人信息
    	function mchtContact(id){
			$.ligerDialog.open({
				height: $(window).height(),
				width: $(window).width(),
				title: "商家联系人",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/mchtContact.shtml?mchtId=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
    	
    	// 对接人
    	function mchtPlatformContact(id){
			$.ligerDialog.open({
				height: $(window).height(),
				width: $(window).width(),
				title: "平台分配对接人",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/mchtplatformcontact.shtml?mchtId=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		 }
    
    	function getAuditCw(id){
      	  
    	  	$.ligerDialog.confirm('确认领取？', function (yes) {
    			if(yes){
    				$.ajax({
    					url : "${pageContext.request.contextPath}/mcht/getAuditCw.shtml",
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
    	
	    var listConfig={
	    	      url:"/mcht/msgcheckdata.shtml",
	    	      pageSize:100,
	    	      listGrid:{ columns: [  
									{ display: '合作状态日期', name: 'status_date', width:100 ,render: function(rowdata, rowindex) {
									   	if(rowdata.status_date != null && rowdata.status_date != ""){
									    	var statusDate = new Date(rowdata.status_date);
									     	return statusDate.format("yyyy-MM-dd");	
									    }
									   	return;
									 }}, 
									 { display: '招商对接人', name: 'zs_contact_name',width:100 },
									 { display: '模式', name: 'mcht_type',width:60 },
									 { display: '商家序号', name: 'mcht_code',width:100 },
									 { display: '公司名称', name: 'company_name', width:200 ,render: function(rowdata, rowindex) {
										 var html = [];
										 if(rowdata.company_name != null && rowdata.company_name != "")
											 html.push(rowdata.company_name);
										 if(rowdata.is_company_inf_perfect != null && rowdata.is_company_inf_perfect != "")
										 	 html.push("<span style='color:red;'>[" + rowdata.is_company_inf_perfect + "]</span>"); 
										 return html.join("");
									 }},
									 { display: '店铺名', name: 'shop_name', width:200 ,render: function(rowdata, rowindex) {
										 var html = [];
										 if(rowdata.shop_name != null && rowdata.shop_name != "")
											 html.push(rowdata.company_name);
										 if(rowdata.shop_name_audit_status != null && rowdata.shop_name_audit_status != "")
										 	 html.push("<span style='color:red;'>[" + rowdata.shop_name_audit_status + "]</span>"); 
										 return html.join("");
									 }},
									 { display: '税务状态', name: 'auditStatus', width:80 ,render: function(rowdata, rowindex) {
										 if(rowdata.auditStatus != null && rowdata.auditStatus != "")
										     return "<span style='color:red;'>" + rowdata.auditStatus + "</span>";
										 return;
									 }},
									 { display: '银行状态', name: 'accountStatus', width:80 ,render: function(rowdata, rowindex) {
										 if(rowdata.accountStatus != null && rowdata.accountStatus != "")
											 return "<span style='color:red;'>" + rowdata.accountStatus + "</span>";
										 return;
									 }},
									 { display: '公司/ 经营信息', name: 'OPER1', width:100 ,render: function(rowdata, rowindex) {
										 var html = [];
									     html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.id + ");\">查看</a>"); 
									     return html.join("");
									 }},
									 { display: '财务信息', name: 'OPER2', width:100 ,render: function(rowdata, rowindex) {
										 var html = [];
									     html.push("<a href=\"javascript:editMchtFinanceInfo(" + rowdata.id + ");\">查看</a>"); 
									     return html.join("");
									 }},
									 { display: '联系人信息', name: 'OPER3', width:100 ,render: function(rowdata, rowindex) {
										 var html = [];
									     html.push("<a href=\"javascript:mchtContact(" + rowdata.id + ");\">查看</a>"); 
									     return html.join("");
									 }},
									 { display: '对接人', name: 'OPER4', width:100 ,render: function(rowdata, rowindex) {
										 var html = [];
									     html.push("<a href=\"javascript:mchtPlatformContact(" + rowdata.id + ");\">查看</a>"); 
									     return html.join("");
									 }},
									 { display: '合作状态', name: 'status',width:100 },
									 { display: '商管运营', name: 'yy_contact_name',width:100 },
									 { display: '法务对接人', name: 'fw_contact_name', width:100 ,render: function(rowdata, rowindex) {
										 if(rowdata.fw_contact_name){
											 return rowdata.fw_contact_name;
										 }else{
											 if(rowdata.isFw){
												 return '<a href="javascript:;" onclick="getAudit('+rowdata.id+');">领取</a>';
											 }else{
												 return "";
											 }
										 }
									 }},
									 { display: '财务对接人', name: 'cw_contact_name',width:100 ,render: function(rowdata, rowindex) {
										 if(rowdata.cw_contact_name){
											 return rowdata.cw_contact_name;
										 }else{
											 if(rowdata.isCw){
												 return '<a href="javascript:;" onclick="getAuditCw('+rowdata.id+');">领取</a>';
											 }else{
												 return "";
											 }
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
  
  <body>
  	  <div class="l-loading" style="display: block" id="pageloading"></div>
	  <div id="toptoolbar"></div>
	  <form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label">序号:</div>
					<div class="search-td-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
				 	<div class="search-td-label">模式:</div>
				 	<div class="search-td-condition" >
						 <select id="mchtType" name="mchtType" class="querysel">
							<option value="">请选择...</option>
							<c:forEach var="list" items="${MCHT_TYPE}">
								<option value="${list.statusValue}">${list.statusDesc}</option>
							</c:forEach>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">名称:</div>
					<div class="search-td-condition" >
						<input type="text" id = "companyName" name="companyName" >
					</div>
				</div>
				<div class="search-td">
				 	<div class="search-td-label">合作状态:</div>
				 	<div class="search-td-condition" >
						<select id="status" name="status" class="querysel">
							<option value="">请选择...</option>
							<c:forEach var="list" items="${STATUS}">
								<c:if test="${list.statusValue == '0' or list.statusValue == '1' }">
									<option value="${list.statusValue}">${list.statusDesc}</option>
								</c:if>
							</c:forEach>
						</select>
				 	</div>
				</div>
			</div>
				
			<div class="search-tr">
				<div class="search-td">
				 	<div class="search-td-label" >公司状态:</div>
				 	<div class="search-td-condition" >
						<select id="isCompanyInfPerfect" name="isCompanyInfPerfect" class="querysel">
							<option value="">请选择...</option> 
							<c:forEach var="list" items="${IS_COMPANY_INF_PERFECT}">
								<option value="${list.statusValue}">${list.statusDesc}
								</option>
							</c:forEach>
						</select>
			 	 	</div>
			 	</div>
				<div class="search-td">
				 	<div class="search-td-label" >店铺状态:</div>
				 	<div class="search-td-condition" >
						<select id="shopNameAuditStatus" name="shopNameAuditStatus" class="querysel">
							<option value="">请选择...</option> 
							<c:forEach var="list" items="${SHOP_NAME_AUDIT_STATUS}">
								<option value="${list.statusValue}">${list.statusDesc}
								</option>
							</c:forEach>
						</select>
			 	 	</div>
			 	</div>
				<div class="search-td">
				 	<div class="search-td-label" >税务状态:</div>
				 	<div class="search-td-condition" >
						<select id="auditStatus" name="auditStatus" class="querysel">
							<option value="">请选择...</option> 
							<c:forEach var="list" items="${AUDIT_STATUS}">
								<option value="${list.statusValue}">${list.statusDesc}
								</option>
							</c:forEach>
						</select>
			 	 	</div>
			 	</div>
				<div class="search-td">
				 	<div class="search-td-label" >银行状态:</div>
				 	<div class="search-td-condition" >
						<select id="accountStatus" name="accountStatus" class="querysel">
							<option value="">请选择...</option> 
							<c:forEach var="list" items="${ACCOUNT_STATUS}">
								<option value="${list.statusValue}">${list.statusDesc}
								</option>
							</c:forEach>
						</select>
			 	 	</div>
			 	</div>
			</div>	
				
			<div class="search-tr">
				<div class="search-td">
				 	<div class="search-td-label" >本人对接:</div>
				 	<div class="search-td-condition" >
						<select id="myContactType" name="myContactType" class="querysel">
							<option value="">请选择...</option>
							<option value="7">法务对接</option>
							<option value="1">招商对接</option>
							<option value="2">运营对接</option>
							<option value="5">财务对接</option>
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
		
<!-- 		<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul> -->
  
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
