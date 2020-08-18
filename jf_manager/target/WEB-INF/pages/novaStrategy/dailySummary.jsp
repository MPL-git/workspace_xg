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
	function formatMoney(s, n) {
		n = n > 0 && n <= 20 ? n : 2;
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
		var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
		t = "";
		for (i = 0; i < l.length; i++) {
			t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
		}
		return t.split("").reverse().join("") + "." + r;
	}

	$(function() {
		$("#search").bind('click',function(){
			var createDateBegin = $("#createDateBegin").val();
			var createDateEnd = $("#createDateEnd").val();
			if(!createDateBegin || !createDateEnd){
				commUtil.alertError("日期不能为空");
				return false;
			}else{
				$("#searchbtn").click();
			}
		});

		$(".dateEditor").ligerDateEditor({
			showTime : false,
			labelAlign : 'left'
		});
	});

	var listConfig = {
		url : "/novaStrategy/dailySummaryList.shtml",
		listGrid : {
			columns : [
					{
						display : '日期',
						name : 'eachDay',
						align : 'center',
						isSort : false,
						width : 180,
						render : function(rowdata, rowindex) {
							var html = [];
							if (rowdata.eachDay != null
									&& rowdata.eachDay != '') {
								var createDate = new Date(rowdata.eachDay);
								html.push(createDate
										.format("yyyy-MM-dd"));
							}
							return html.join("");

						}
					},
					{
						display : '期初余额（元）',
						name : 'first1',
						align : 'center',
						isSort : false,
						render : function(rowdata, rowindex) {
							if (rowdata.first1) {
								return formatMoney(rowdata.first1, 2);
							} else {
								return "0.00";
							}
						}
					},
					{
						display : '订单分润（元）',
						name : 'orderDistribution',
						align : 'center',
						isSort : false,
						render : function(rowdata, rowindex) {
							if (rowdata.orderDistribution) {
								return '+'+formatMoney(rowdata.orderDistribution, 2);
							} else {
								return "0.00";
							}
						}
					},
					{
						display : '退款分润（元）',
						name : 'drawBackAmount',
						align : 'center',
						isSort : false,
						render : function(rowdata, rowindex) {
							if (rowdata.drawBackAmount) {
								return '-'+formatMoney(rowdata.drawBackAmount, 2);
							} else {
								return "0.00";
							}
						}
					},
					{
						display : '邀新奖励（元）',
						name : 'inviteNewAwards',
						align : 'center',
						isSort : false,
						render : function(rowdata, rowindex) {
							if (rowdata.inviteNewAwards) {
								return '+'+formatMoney(rowdata.inviteNewAwards, 2);
							} else {
								return "0.00";
							}
						}
					}, {
						display : '提现支出（元）',
						name : 'cashWithdrawals',
						align : 'center',
						isSort : false,
						render : function(rowdata, rowindex) {
							if (rowdata.cashWithdrawals) {
								return '-'+formatMoney(rowdata.cashWithdrawals, 2);
							} else {
								return "0.00";
							}
						}
					}, {
						display : '期末余额（元）',
						name : 'end',
						align : 'center',
						isSort : false,
						render : function(rowdata, rowindex) {
							if (rowdata.end1) {
								return formatMoney(rowdata.end1, 2);
							} else {
								return "0.00";
							}
						}
					}, ],
			showCheckbox : false, //不设置默认为 true
			showRownumber: false //不设置默认为 true
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
					<div class="search-td-label" style="float: left;width: 20%;">日期</div>
					<div class="l-panel-search-item">
						<input type="text" id="createDateBegin" name="createDateBegin"
							class="dateEditor" placeholder="请选择" style="width: 100px;"
							value="${createDateBegin}" />
					</div>
					<div class="l-panel-search-item"
						style="margin-left: 15px;margin-right: 15px;">—</div>
					<div class="l-panel-search-item">
						<input type="text" id="createDateEnd" name="createDateEnd"
							class="dateEditor" placeholder="请选择" style="width: 100px;"
							value="${createDateEnd}" />
					</div>
				</div>
				<div class="search-td-search-btn"
					>
					<div id="search" class="l-button">搜索</div>
					<input type="hidden" id="searchbtn">
				</div>
			</div>
		</div>
	</form>

	<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
