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
    
    <title>招商类目一览表</title>
  <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript">
  $(function(){//不分页
	$("#maingrid").ligerGrid({
	  usePager:false
	 });
  });  
   
 
 function seeQualifications(id){
		$.ligerDialog.open({
			height: 500,
			width: 700,
			title: "查看资质要求",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/prod/info.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
 
	   var listConfig={
		     url:"/prod/zsProductType/zscataloguedata.shtml",
		     listGrid:{ columns: [
								{ display: 'APP顶级类目', name:'product_type_name'},
								{ display: '上级类目',name:'zsproduct_name'},
					            { display: '子类目名称', name:'zs_name'},
					            { display: '保证金',name:'deposit'},
				                { display: '技术服务费',name:'fee_rate'},
				                { display: 'APP子类目',name:'third_product_type_name'},
				                { display: '操作',name:"OPER",width:150,render: function (rowdata, rowindex){
				                  return "<a href=\"javascript:seeQualifications(" + rowdata.id + ");\">【查看资质要求】</a>";
				                }},
				                { display: '状态', name:'status_desc'},
				                ],
		                 showCheckbox : false,  //不设置默认为 true
		                 showRownumber: true   //不设置默认为 true
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
