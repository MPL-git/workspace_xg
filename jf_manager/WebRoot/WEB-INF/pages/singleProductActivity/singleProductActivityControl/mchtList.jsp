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

 var listConfig={
     url:"/singleProductActivity/singleProductActivityControl/mchtData.shtml",
     listGrid:{ columns: [
			            { display: '商家序号', name:'mchtCode',render: function (rowdata, rowindex) {
				            return rowdata.mchtCode;
						}},
			            { display: '公司名称', name:'companyName',render: function (rowdata, rowindex) {
			            	return rowdata.companyName;
						}},
			            { display: '店铺名称', name:'shopName',render: function (rowdata, rowindex) {
			            	return rowdata.shopName;
						}},
			            { display: '合作状态', name:'statusDesc',render: function (rowdata, rowindex) {
			            	if(rowdata.status == 0){
			            		return "入驻中";
			            	}else if(rowdata.status == 1){
			            		return "已开通";
			            	}else if(rowdata.status == 2){
			            		return "店铺暂停";
			            	}else if(rowdata.status == 3){
			            		return "店铺关闭";
			            	}else if(rowdata.status == 5){
			            		return "不入驻";
			            	}
			            	return rowdata.status;
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
		<input type="hidden" name="mchtIds" value="${mchtIds}">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>