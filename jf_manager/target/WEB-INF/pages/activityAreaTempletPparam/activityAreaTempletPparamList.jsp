<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
	 
	 //修改会场模板
	 function updateActivityAreaTempletPparam(id) {
		$.ligerDialog.open({
			height: 800,
			width: 900,
			title: "修改会场模板",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaTempletPparam/activityAreaTempletPparamManager.shtml?flag=2&id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	 
 	var listConfig={
	      url:"/activityAreaTempletPparam/getActivityAreaTempletPparamList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
	                        {display:'模板名称',name:'name', align:'center', isSort:false, width:'5%'},
	                        {display:'会场ID',name:'activityAreaId', align:'center', isSort:false, width:'5%'},
	                        {display:'模板后缀',name:'suffix', align:'center', isSort:false, width:'10%'},
	                        {display:'模板代码',name:'code', align:'center', isSort:false, width:'70%'},
	                        {display:'操作',name:'', align:'center', isSort:false, width:'5%', render: function(rowdata, rowindex) {
							    return "<a href=\"javascript:updateActivityAreaTempletPparam(" + rowdata.id + ");\">【修改】</a></br>";
							}}
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
// 	                 usePager: true //不分页
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
		<div id="searchbtn" style="display: none; ">搜索</div>
	</form>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
