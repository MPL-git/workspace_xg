<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
	$(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : false,
			labelAlign : 'left'
		});
	});

	var listConfig = {
		url : "/theoryStatistics/laNewRecordList.shtml?id=${id}",
		listGrid : {
			columns : [
					{
						display : '拉新用户ID',
						name : 'id',
						align : 'center',
						isSort : false,
						width : 180
					},
					{
						display : '拉新用户昵称',
						name : 'nick',
						align : 'center',
						isSort : false,
						width : 180
					},
					{
						display : '注册时间',
						name : 'createDate',
						align : 'center',
						isSort : false,
						width : 180,
						render : function(rowdata, rowindex) {
							var html = [];
							if (rowdata.createDate != null
									&& rowdata.createDate != '') {
								var createDate = new Date(rowdata.createDate);
								html.push(createDate
										.format("yyyy-MM-dd hh:mm:ss"));
							}
							return html.join("");

						}
					}, ],
			showCheckbox : false, //不设置默认为 true
			showRownumber : true
		//不设置默认为 true
		},
		container : {
			searchBtnName : "searchbtn",
			fromName : "dataForm",
			listGridName : "maingrid"
		}
	};
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">注册日期</div>
					<div class="l-panel-search-item">
						<input type="text" id="createDateBegin" name="createDateBegin"
							class="dateEditor" placeholder="请选择" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item"
						style="margin-left: 15px;margin-right: 15px;">—</div>
					<div class="l-panel-search-item">
						<input type="text" id="createDateEnd" name="createDateEnd"
							class="dateEditor" placeholder="请选择" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;margin-right: 680px;">
					<div id="searchbtn" class="l-button">搜索</div>
				</div>
			</div>
		</div>
	</form>

	<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
