<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<link href="${pageContext.request.contextPath}/css/search_form.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	//编辑
	function editorder(paramId) {
		$.ligerDialog
				.open({
					height : $(window).height() - 40,
					width : $(window).width() - 40,
					title : "APP首页导航方案修改",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/service/navigation/navigationEdit.shtml?paramId="
							+ paramId,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}
	var listConfig = {
		url : "/service/navigation/navigationData.shtml",
		listGrid : {
			columns : [
					{
						display : 'ID',
						name : 'paramId',
						width : 60
					},
					{
						display : '编码',
						name : 'paramCode',
						width : 180
					},
					{
						display : '名称',
						name : 'paramName',
						width : 180
					},
					{
						display : '状态',
						name : 'paramValue',
						width : 180
					},
					{
						display : '序号',
						name : 'paramOrder',
						width : 180
					},
					{
						display : '描述',
						name : 'paramDesc',
						width : 180
					},
					{
						display : '操作',
						name : '',
						width : 150,
						align : 'center',
						render : function(rowdata, rowindex) {
							var html = [];
							html.push("<a href=\"javascript:editorder("
									+ rowdata.paramId
									+ ");\">【修改】</a>&nbsp;&nbsp;");
							return html.join("");

						}
					}, ],
			showCheckbox : false, //不设置默认为 true
			showRownumber : true
		//不设置默认为 true
		},
		container : {
			toolBarName : "toptoolbar",
			searchBtnName : "searchbtn",
			fromName : "dataForm",
			listGridName : "maingrid"
		}

	}
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
