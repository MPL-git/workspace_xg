<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 <script type="text/javascript">
function toSetting(id){
	$.ligerDialog.open({
		height: $(window).height()*0.8,
		width: $(window).width()*0.8,
		title: "单品活动入口控制设置",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/singleProductActivity/singleProductActivityControl/toSetting.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 var listConfig={
     url:"/singleProductActivity/singleProductActivityControl/data.shtml",
     listGrid:{ columns: [
			            { display: '栏目', name:'typeDesc',render: function (rowdata, rowindex) {
				            return rowdata.typeDesc;
						}},
			            { display: '不可见类目', name:'name',render: function (rowdata, rowindex) {
			            	return rowdata.productTypeNames;
						}},
			            { display: '特定可见商家', name:'showToMchtCodes',render: function (rowdata, rowindex) {
			            	return rowdata.showToMchtCodes;
						}},
			            { display: '特定不可见商家', name:'statusDesc',render: function (rowdata, rowindex) {
			            	return rowdata.hideToMchtCodes;
						}},
			            { display: '操作',name:'op',render: function (rowdata, rowindex) {
		            		var html=[];
			            	html.push('<a href="javascript:;" onclick="toSetting('+rowdata.id+');">设置</a>');
	                		return html.join("");
						}}
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
	<form id="dataForm" runat="server">
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>