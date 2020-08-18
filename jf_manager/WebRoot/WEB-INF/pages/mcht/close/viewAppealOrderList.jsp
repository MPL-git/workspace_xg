<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>

<script type="text/javascript">

	function viewAppealOrder(id) {
		$.ligerDialog.open({
			height : $(window).height() - 50,
			width : $(window).width() - 100,
			title : "投诉详情页",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/appealOrder/view.shtml?id="
					+ id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function viewSubDetail(id) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
			title: "子订单详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	var listConfig = {
		url : "/mcht/close/viewAppealOrderData.shtml",
		listGrid : {
			columns : [{ display: '投诉单编号', width: 160, render: function(rowdata, rowindex) {
						var html = [];
						html.push('<a href="javascript:;" onclick="viewAppealOrder('+rowdata.id+');">'+rowdata.orderCode+'</a>'); 
						if(rowdata.memberInfoStatus == 'P') {
							html.push("</br><span style='color:red;'>异常</span>");
						}
						return html.join("");
					}},
					{display : '订单号',width : 160,name : 'subOrderCode',render: function(rowdata, rowindex) {
						var html = [];
						html.push('<a href="javascript:;" onclick="viewSubDetail('+rowdata.subOrderId+');">'+rowdata.subOrderCode+'</a>'); 
						return html.join("");
					}},
					{
						display : '状态',
						name : 'statusDesc'
					},
					{
						display : '客服状态',
						render : function(rowdata, rowindex) {
							if (rowdata.serviceStatus == "0") {
								return "<span id='serviceStatus-"+rowdata.id+"' style='color:red;'>待介入</span>";
							} else if (rowdata.serviceStatus == "1") {
								return "<span id='serviceStatus-"+rowdata.id+"' style='color:green;'>处理中</span>";
							} else if (rowdata.serviceStatus == "2") {
								return "<span id='serviceStatus-"+rowdata.id+"'>已结束</span>";
							}else {
								return "<span id='serviceStatus-"+rowdata.id+"'></span>";
							}
						}
					},
					{
						display : '处理人',
						render : function(rowdata, rowindex) {
							if (rowdata.staffName) {
								return rowdata.staffName;
							} else {
								return "";
							}
						}
					},
					{
						display : '投诉时间',
						width : 150,
						render : function(rowdata, rowindex) {
							var createDate = new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
						}
					},
					{
						display : '最后更新时间',
						width : 150,
						render : function(rowdata, rowindex) {
							if (rowdata.updateDate) {
								var updateDate = new Date(rowdata.updateDate);
								return updateDate.format("yyyy-MM-dd hh:mm:ss");
							} else {
								return "";
							}
						}
					} ],
			showCheckbox : true, //不设置默认为 true
			showRownumber : true
		//不设置默认为 true
		},
		container : {
			toolBarName : "toptoolbar",
			searchBtnName : "searchbtn",
			fromName : "dataForm",
			listGridName : "maingrid"
		}
	};
</script>
<style type="text/css">
	.l-box-select .l-box-select-table td {
		font-size: 12px;
		line-height: 18px;
	}
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
	<input type="hidden" name="mchtId" value="${mchtId}">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">投诉单编号：</div>
					<div class="search-td-condition">
						<input type="text" id="orderCode" name="orderCode">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">状态：</div>
					<div class="l-panel-search-item">
						<select id="status" name="status" style="width: 100px;">
							<option value="">请选择</option>
							<option value="1">待客户回复</option>
							<option value="2">待商家回复</option>
						</select>
					</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures"
		style="display: none;">

	</ul>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>