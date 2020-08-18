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
     <script type="text/javascript">
	    var listConfig={
	    	      url:"/mcht/close/viewMoreRemarksDate.shtml",
	    	      listGrid:{ columns: [
				    			          { display: '时间', name: 'createDate', width:180,render:function(rowdata,rowindex){
				    			        	  	if(rowdata.createDate){
				    			                	var createDate=new Date(rowdata.createDate);
				    			                	return createDate.format("yyyy-MM-dd hh:mm:ss");
				    			        	  	}else{
				    			        	  		return "";
				    			        	  	}
				    			           }}, 
				    			          { display: '操作人', name: 'staffName',width:100 },   
						                  { display: '备注内容',  name: 'remarks', width: 450 }
    		 		                
	    			                ],   
	    	                 showCheckbox : false,  //不设置默认为 true
	    	                 showRownumber:true //不设置默认为 true
	    	      } , 							
	    	     container:{
	    	        searchBtnName:"searchbtn",
	    	        fromName:"dataForm",
	    	        listGridName:"maingrid"
	    	      }        
	    	  };
    </script>
  </head>
  
  <body>
    <div class="l-loading" style="display: block" id="pageloading"></div>
	  <form id="dataForm" runat="server">
	  	<input type="hidden" name="mchtCloseApplicationId" id="mchtCloseApplicationId" value="${mchtCloseApplicationId}">
	  	<input type="hidden" name="remarksType" id="remarksType" value="${remarksType}">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	  </form>
		<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
		
		</ul>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
