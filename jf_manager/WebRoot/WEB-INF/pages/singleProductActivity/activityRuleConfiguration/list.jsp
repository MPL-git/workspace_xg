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
function toEdit(id){
	$.ligerDialog.open({
		height: $(window).height()*0.8,
		width: $(window).width()*0.8,
		title: "规则设置",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/singleProductActivity/activityRuleConfiguration/toEdit.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 var listConfig={
     url:"/singleProductActivity/activityRuleConfiguration/data.shtml",
     listGrid:{ columns: [
			            { display: '所属专区', name:'typeDesc',render: function (rowdata, rowindex) {
				            return rowdata.typeDesc;
						}},
			            { display: '价格规则', name:'name',width:250,render: function (rowdata, rowindex) {
			            	return rowdata.priceRulesDesc;
						}},
			            { display: '销量规则', name:'showToMchtCodes',render: function (rowdata, rowindex) {
			            	return rowdata.salesRulesDesc;
						}},
			            { display: '库存规则', name:'statusDesc',render: function (rowdata, rowindex) {
			            	return rowdata.stockRulesDesc;
						}},
			            { display: '好评率', name:'statusDesc',render: function (rowdata, rowindex) {
			            	if(rowdata.favorableRate){
			            		var num = parseInt(rowdata.favorableRate*100);
				            	return num+"%";
			            	}else{
			            		return "";
			            	}
						}},
			            { display: '销售周期和品牌报名数', name:'statusDesc',render: function (rowdata, rowindex) {
			            	return rowdata.salesCycleRulesDesc;
						}},
			            { display: '其他要求', name:'statusDesc',render: function (rowdata, rowindex) {
			            	return rowdata.otherRequirements;
						}},
			            { display: '店铺评价', name:'statusDesc',render: function (rowdata, rowindex) {
			            	return rowdata.shopComment;
						}},
			            { display: '操作',name:'op',render: function (rowdata, rowindex) {
		            		var html=[];
			            	html.push('<a href="javascript:;" onclick="toEdit('+rowdata.id+');">编辑</a>');
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