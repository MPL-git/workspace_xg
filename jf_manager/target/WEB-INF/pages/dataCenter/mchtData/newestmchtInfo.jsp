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
    
   <title>最新商家数量</title>
   <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript">
	 var listConfig={
		     url:"/mchtData/newestmchtInfodata.shtml",
		     listGrid:{ columns: [
                                { display: '类目', name:'productname'},
								{ display: '资质总审通', name:'generalqualification'},
								{ display: '待开通', name:'opentoopen'},
								{ display: '已开通',name:'alreadyopened'},
					            { display: '已开资质未齐(待开发)', name:''},
					            { display: '合同已归档',name:'contractfiling'},
				                { display: '寄回资质未齐',name:'sendback'},
				                { display: '店铺关闭',name:'shop'},
				                { display: '入驻中且未提交',name:'unsubmitted'},
				                { display: '入驻中且已提交',name:'submission'},
				                { display: '取消入驻',name:'cancellation'}
				                ],
		                 showCheckbox : false,  //不设置默认为 true
		                 showRownumber: true //不设置默认为 true
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
		 <div id="maingrid" style="margin: 0; padding: 0"></div>
   </form>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
  </body>
</html>
