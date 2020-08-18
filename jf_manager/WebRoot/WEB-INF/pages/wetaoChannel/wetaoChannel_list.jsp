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
	
  //新增
	 function addWetaoChannel(helpTagId) {
		 $.ligerDialog.open({
				height: 300,
				width: 500,
				title: "新增频道",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/wetaoChannel/toEdit.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	  //修改
	 function updateWetaoChannel(id) {
		 $.ligerDialog.open({
				height: 300,
				width: 500,
				title: "修改",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/wetaoChannel/toEdit.shtml?id="+id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
  
 
  var listConfig={
	 btnItems:[
		           { text: '新增频道', icon: 'add', dtype:'win',  click: addWetaoChannel, url: "/wetaoChannel/toEdit.shtml", seqId:"", width: 600, height: 250},
		         ], 
     url:"/wetaoChannel/data.shtml",
     listGrid:{ columns: [
						{ display: '频道Id', name:'id'},
						{ display: '频道名称',name:'channelName'},
			            { display: '备注', name:'remarks'},
			            { display: '上架中商品',name:'upNumber'},
		                { display: '下架中商品',name:'downNumber'},
		               
		                { display: '操作',name:"OPER",width:150,render: function (rowdata, rowindex){
		                  return "<a href='javascript:;' onclick='updateWetaoChannel("+rowdata.id+");'>修改</a>";
		                }},
		             
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
