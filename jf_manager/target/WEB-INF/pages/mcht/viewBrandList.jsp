<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	
<script type="text/javascript">
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
	 var listConfig={
	      url:"/mcht/viewBrandListData.shtml",
	      listGrid:{ columns: [  { display: '商家序号', name: 'mchtCode'}, 
			                { display: '招商对接人', name: 'zsContactName'},
			                { display: '公司名称', render: function (rowdata, rowindex) {
			                	return '<a href="javascript:;" onclick="editMchtBaseInfo('+rowdata.mchtId+');">'+rowdata.companyName+'</a>';
			                }},
			                { display: '店铺名称', name: 'shopName'},
			                { display: '联系人信息', render: function (rowdata, rowindex) {
			                	return '<a href="javascript:;" onclick="mchtContact('+rowdata.mchtId+');">【查看】</a>';
			                }},
			                { display: '品牌名称', name: 'brandName'},
			                { display: '授权有效期', render: function (rowdata, rowindex) {
			                	if(rowdata.platformAuthExpDate){
			                		var platformAuthExpDate = new Date(rowdata.platformAuthExpDate);
				                	return platformAuthExpDate.format("yyyy-MM-dd");
			                	}
			                }},
			                { display: '资质类型', name: 'aptitudeTypeDesc'},
			                { display: '法务对接人',  name: 'fwContactName'}
			                ],   
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      } , 							
	     container:{
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	       
	  };
</script>
	
  </head>
  
  <body>
	<form id="dataForm" runat="server">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
		<input type="hidden" name="yearMonth" value="${yearMonth}">
	</form>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
