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
    
    var viewerPictures;
    $(function(){
     	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
    });
    
    function mchtInfoChgApplyAudit(id) {
    	$.ligerDialog.open({
    	height: $(window).height() - 40,
    	width: 1200,
    	title: "公司信息更新审核",
    	name: "INSERT_WINDOW",
    	url: "${pageContext.request.contextPath}/mcht/mchtInfoChgApplyAudit.shtml?id=" + id,
    	showMax: true,
    	showToggle: false,
    	showMin: false,
    	isResize: true,
    	slide: false,
    	data: null
    });
    }
    
    
    function viewerMchtPic(mchtInfoChangeId){
		
		var url="${pageContext.request.contextPath}/mcht/getMchtBlPicChg.shtml";
		
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : url,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {mchtInfoChgId:mchtInfoChangeId},
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
	    		  
	    	      url:"/mcht/mchtCompanyInfoChangeApplyData.shtml",
	    	      
	    	      listGrid:{ columns: [  
	 	    			            { display: '创建日期',   name: 'commitDate' , width: 180, render: function(rowdata, rowindex) {
		 	    			              	   var createDate=new Date(rowdata.createDate);
		 	    			            	   return createDate.format("yyyy-MM-dd");
		 	    			              		}}, 
		 	    			        { display: '招商对接人', name: 'zsContactName',width:100 },       		
	    			                { display: '模式', name: 'mchtTypeDesc',width:180},
	    	                        { display: '商家序号', name: 'mchtCode',width:180 },
	    	                        { display: '入驻类型',width: 80, render: function(rowdata, rowindex) {
	    				            	var html = [];
	    				            	if(rowdata.settledType == "1"){
	    				            		html.push("企业公司");
	    				            	}else if(rowdata.settledType == "2"){
	    				            		html.push("个体商户");
	    				            	}
	    				            	return html.join("");
	    				         	}},
	    	                        //{ display: '商家简称',  name: 'mchtShortName', width: 150 }, 
	    			                { display: '公司名称',  name: 'companyName', width: 180}, 
	    			                { display: '店铺名称',  name: 'shopName', width: 180}, 
	    			              	{ display: "公司/经营信息", name: "OPER1", width: 100, align: "center", render: function(rowdata, rowindex) {
	    								var html = [];
	    							    html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;"); 
	    							    return html.join("");
	    			              	}},
	    			              	{ display: "财务信息", name: "OPER2", width: 100, align: "center", render: function(rowdata, rowindex) {
	    								var html = [];
	    							    html.push("<a href=\"javascript:editMchtFinanceInfo(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;"); 
	    							    return html.join("");
	    			              	}},
	    			              	{ display: "商家联系人", name: "OPER3", width: 100, align: "center", render: function(rowdata, rowindex) {
	    								var html = [];
	    							    html.push("<a href=\"javascript:mchtContact(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;"); 
	    							    return html.join("");
	    			              	}},
	    			                { display: '提审日期',   name: 'commitDate' , width: 180, render: function(rowdata, rowindex) {
	    			              	   if (rowdata.commitDate!=null) {
	    			              	      var commitDate=new Date(rowdata.commitDate);
	    			            	      return commitDate.format("yyyy-MM-dd");
										
									    }else if (rowdata.commitDate==null) {
									      return "";
										}
	    			              		}},
	    			                { display: '审核状态',   name: 'statusDesc' , width: 180, render: function(rowdata, rowindex) {
	    			                	var html = [];
	    			                	if((rowdata.status=='1'||rowdata.status=='2')&&rowdata.fwStaffId==${sessionScope.USER_SESSION.staffID}){
	    							    html.push("<a href=\"javascript:mchtInfoChgApplyAudit(" + rowdata.id + ");\">"+rowdata.statusDesc+"</a>"); 
	    			                	}else{
	    			                		html.push(rowdata.statusDesc); 
	    			                	}
	    							    return html.join("");
	    			              		}},
    		 		                { display: "法务对接人", name: "fwContactName",width:100 , render: function(rowdata, rowindex) {
    		 		                	if((rowdata.fwContactName==null||rowdata.fwContactName=="")&&rowdata.total_audit_status=="1"){
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
	    
	  //查看商家联系人
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
		 
		 //平台对接人
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
    </script>
  </head>
  
  <body>
  	  <div class="l-loading" style="display: block" id="pageloading"></div>
	  <div id="toptoolbar"></div>
	  <form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
			
				 <div class="search-td">
				 <div class="search-td-label" >审核状态:</div>
				 <div class="search-td-condition" >
					<select id="status" name="status" class="querysel">
						<option value="">请选择</option> 
						<c:forEach var="list" items="${statusDesc}">
							<option value="${list.statusValue}" <c:if test="${list.statusValue == '1' }"> selected</c:if> >${list.statusDesc}</option>
						</c:forEach>
					</select>
			 	 </div>
			 	 </div>
				 	
				 <div class="search-td">
				 <div class="search-td-label">商家类型:</div>
				 <div class="search-td-condition" >
					 <select id="mchtType" name="mchtType"
						class="querysel">
						<option value="">请选择</option>
						<c:forEach var="list" items="${mchtTypeStatusDesc}">
							<option value="${list.statusValue}">${list.statusDesc}</option>
						</c:forEach>
					</select>
				 </div>
				 </div>
				 
				<div class="search-td">
				<div class="search-td-label">商家序号:</div>
				<div class="search-td-condition" >
					<input type="text" id = "mchtCode" name="mchtCode" >
				</div>
				</div>
				
				
				<div class="search-td">
				<div class="search-td-label">商家名称:</div>
				<div class="search-td-condition" >
					<input type="text" id = "companyName" name="companyName" >
				</div>
				</div>
				 
<!-- 				  <div class="search-td"> -->
<!-- 				 <div class="search-td-label">是否自营:</div> -->
<!-- 				 <div class="search-td-condition" > -->
<!-- 					 <select  id="mchtIsManageSelf" name="mchtIsManageSelf" -->
<!-- 						class="querysel"> -->
<!-- 						<option value="">请选择</option> -->
<!-- 						<c:forEach var="list" items="${isManageStatusDesc}"> -->
<!-- 							<option value="${list.statusValue}">${list.statusDesc}</option> -->
<!-- 						</c:forEach> -->
<!-- 					</select> -->
<!-- 				 </div> -->
<!-- 				 </div> -->
				 
<!-- 				 <div class="search-td"> -->
<!-- 				 <div class="search-td-label">合作状态:</div> -->
<!-- 				 <div class="search-td-condition" > -->
<!-- 					 <select id="cooperateStatus" name="cooperateStatus" -->
<!-- 						class="querysel"> -->
<!-- 						<option value="">请选择</option> -->
<!-- 						<c:forEach var="list" items="${cooperateStatus}"> -->
<!-- 							<option value="${list.statusValue}">${list.statusDesc}</option> -->
<!-- 						</c:forEach> -->
<!-- 					</select> -->
<!-- 				 </div> -->
<!-- 				 </div> -->
				 </div>
				
				 
			<div class="search-tr"  >
				
				<div class="search-td">
				<div class="search-td-label" style="float:left;">入驻类型：</div>
				<div class="search-td-condition" >
					<select id="settledType" name="settledType" style="width: 150px;">
						<option value="">请选择</option>
						<option value="1">企业公司</option>
						<option value="2">个体商户</option>
					</select>
			 	 </div>
			 	</div>
				
				<div class="search-td">
			 	<div class="search-td-label" >只看本人领取</div>
			 	<div class="search-td-condition" style="text-align: left;width: 5%;">
					<input type="checkbox" value="1" name="is_my_audit">
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
