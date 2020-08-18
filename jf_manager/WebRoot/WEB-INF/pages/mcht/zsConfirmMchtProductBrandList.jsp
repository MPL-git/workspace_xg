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

     function editMchtProductBrand(mchtProductBrandId,view) {
    		$.ligerDialog.open({
    			height: $(window).height() - 100,
    			width: $(window).width() - 200,
	    		title: "品牌信息",
	    		name: "INSERT_WINDOW",
	    		url: "${pageContext.request.contextPath}/mcht/editMchtProductBrand.shtml?mchtProductBrandId=" + mchtProductBrandId+"&view="+view,
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
	    		  
	    	      url:"/mcht/zsConfirmMchtProductBrandData.shtml",
	    	      
	    	      listGrid:{ columns: [
		    			          { display: '提审时间', name: 'zsCommitAuditDate', width:180,render:function(rowdata,rowindex){
		    			        	  	if(rowdata.zsCommitAuditDate){
		    			                	var zsCommitAuditDate=new Date(rowdata.zsCommitAuditDate);
		    			                	return zsCommitAuditDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			                
		    			          { display: '招商对接人', name: 'zsContactName',width:100 },   
		    			          { display: '商家序号', name: 'mchtCode',width:100 }, 
				  					
					                { display: '公司名称',  name: 'companyName', width: 150 , render: function(rowdata, rowindex){
					  					var html = [];
					  					 html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">"+rowdata.companyName+"</a>"); 
					  					return html.join("");
				  					}}, 
	    			                { display: '品牌名称', name: 'productBrandName',width:180 },
	    			                { display: '授权有效期', name: 'platformAuthExpDate', width: 180 ,render:function(rowdata,rowindex){
	    			                	var platformAuthExpDate=new Date(rowdata.platformAuthExpDate);
	    			                	return platformAuthExpDate.format("yyyy-MM-dd");
	    			                }},
	    			                { display: '招商审核状态',   name: 'zsAuditStatusDesc' , width: 180,render:function(rowdata,rowindex){
	    							    if(!rowdata.zsAuditStatus || rowdata.zsAuditStatus == 0){
	    							    	return "未提交";
	    							    }else if(rowdata.zsAuditStatus == 1){
	    							    	if(!rowdata.isZsAuditRecommit || rowdata.isZsAuditRecommit == 0){
		    							    	return "待审(首次提审)";
	    							    	}else{
	    							    		return "待审(重新提审)";
	    							    	}
	    							    }else if(rowdata.zsAuditStatus == 2){
	    							    	return "通过";
	    							    }else if(rowdata.zsAuditStatus == 4){
	    							    	return "驳回";
	    							    }else if(rowdata.zsAuditStatus == 5){
	    							    	return "不签约";
	    							    }else if(rowdata.zsAuditStatus == 6){
	    							    	return "黑名单";
	    							    }
	    			                }
	    			                },
    		 		                { display: "法务对接人", name: "fwContactName",width:100 , render: function(rowdata, rowindex) {
    		 		                	if((rowdata.fwContactName==null||rowdata.fwContactName=="")&&rowdata.auditStatus=="1"&&${isCanGet}){
    		 		                		return "";
    		 		                	}else{
    		 		                		return rowdata.fwContactName;
    		 		                	}
    				              	}},
    				              	{ display: "操作", name: "op",width:100 , render: function(rowdata, rowindex) {
    		 		                	if(rowdata.zsAuditStatus == 2){
    		 		                		return "<a href=\"javascript:editMchtProductBrand(" + rowdata.id + ",0);\">【查看】</a>";
    		 		                	}else if(rowdata.zsStaffId==${sessionScope.USER_SESSION.staffID} || rowdata.assistantContact=='1'){
    		 		                		return "<a href=\"javascript:editMchtProductBrand(" + rowdata.id + ",1);\">【审核】</a>";
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
					<div class="search-td-label" style="float:left;width: 20%;">提审日期：</div>
					<div class="l-panel-search-item">
						<input type="text" id="zs_commit_audit_date_begin" name="zs_commit_audit_date_begin" />
						<script type="text/javascript">
							$(function() {
								$("#zs_commit_audit_date_begin").ligerDateEditor({
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
						<input type="text" id="zs_commit_audit_date_end" name="zs_commit_audit_date_end" />
						<script type="text/javascript">
							$(function() {
								$("#zs_commit_audit_date_end").ligerDateEditor({
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
				 <div class="search-td-label" >招商审核状态:</div>
				 <div class="search-td-condition" >
					<select id="zsAuditStatus" name="zsAuditStatus"	class="querysel">
						<option value="">请选择</option>
						<option value="0">未提交</option>
						<option value="1">待审</option>
						<option value="2">通过</option>
						<option value="4">驳回</option>
						<option value="5">不签约</option>
						<option value="6">黑名单</option>
					</select>
			 	 </div>
			 	 </div>
				 	
				 <div class="search-td">
				 <div class="search-td-label">商标类型:</div>
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
				<div class="search-td-label">公司名称:</div>
				<div class="search-td-condition" >
					<input type="text" id = "companyName" name="companyName" >
				</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label">运营状态:</div>
				<div class="search-td-condition" >
					<select  id="status" name="status"
						class="querysel">
						<option value="">请选择</option>
						<c:forEach var="list" items="${statusDescList}">
							<option value="${list.statusValue}">${list.statusDesc}</option>
						</c:forEach>
					</select>
				</div>
				</div>
				
				<div class="search-td">
				  <div class="search-td-label">对接人：</div>
				    <div class="search-td-condition" >
					<select id="platformContactId" name="platformContactId" >
					<c:if test="${isAssistant}">
						<c:if test="${isManagement==1}">
							<option value="" selected="selected">全部商家</option>
							<option value="${myContactId}">我对接的商家</option>
							<c:forEach items="${platformContacts}" var="platformContact">
								<option value="${platformContact.id}">${platformContact.contactName}的商家</option>
							</c:forEach>
						</c:if>
						<c:if test="${isManagement!=1}">
							<option value="${myContactId}" selected="selected">我对接的商家</option>
							<c:forEach items="${platformContacts}" var="platformContact">
								<option value="${platformContact.id}">${platformContact.contactName}的商家</option>
							</c:forEach>
						</c:if>
					</c:if>
					<c:if test="${!isAssistant}">
						<option value="" selected="selected">全部商家</option>
						<c:forEach items="${zsPlatformContactCustomList}" var="zsPlatformContactCustom">
							<option value="${zsPlatformContactCustom.id}">${zsPlatformContactCustom.contactName}的商家</option>
						</c:forEach>
					</c:if>
					</select>
					</div>
				</div>
				
				</div>
				<div class="search-tr"  > 
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
