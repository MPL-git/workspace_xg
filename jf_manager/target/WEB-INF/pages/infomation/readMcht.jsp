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
		$(function() {

		});


		var listConfig={
			url:"/information/getReadMchtList.shtml?id=${id}",
			btnItems:[],
			listGrid:{ columns: [
					{display:'序号',name:'rowindex', align:'center', isSort:false, width:140, render: function(rowdata, rowindex) {
								return rowindex+1;
						}},
					{display:'已读时间',name:'productCode', align:'center', isSort:false, width:220, render: function(rowdata, rowindex) {
							var html = [];
							if(rowdata.createDate){
								var date=new Date(rowdata.createDate);
								return date.getFullYear()+"-"+("0" + (date.getMonth() + 1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2)+" "+("0"+date.getHours()).slice(-2)+":"+("0"+date.getMinutes()).slice(-2)+":"+("0"+date.getSeconds()).slice(-2);							}
						}},
					{display:'商家信息',name:'productName', align:'center', isSort:false, width:440, render: function(rowdata, rowindex) {
							var html = [];
							if(rowdata.mchtCode != "" && rowdata.companyName != null) {
								html.push(rowdata.mchtCode+"</br>");
								html.push(rowdata.companyName+"</br>");
							}
							return html.join("");
						}},
				],
				showCheckbox :false,  //不设置默认为 true
				showRownumber:false //不设置默认为 true
			} ,
			container:{
				// toolBarName:"toptoolbar",
				searchBtnName:"searchbtn",
				fromName:"dataForm",
				listGridName:"maingrid"
			},
			pageSize:"100"
		};

	</script>
</head>
<body style="padding: 0px; overflow: hidden;">
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
	<div class="search-pannel">
		<div class="search-tr">
			<div class="search-td">
				<div class="search-td-label" >商家序号：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id="mchtCode" name="mchtCode" />
				</div>
			</div> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="search-td">
				<div class="search-td-label">商家名称：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id="companyName" name="companyName" />
				</div>
			</div>

			<div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn"  style="width: 30px; height: 20px">搜索</div>
			</div>
		</div>
	</div>
	</div>
	</div>
</form>

<div id="maingrid" style="margin: 0;padding: 0;"></div>
<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
