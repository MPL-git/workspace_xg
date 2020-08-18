<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript">
	$(function(){

		$(".dateEditor").ligerDateEditor({
			showTime : false,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});

		$(".dateMonthEditor").ligerDateEditor( {
			showTime : false,
			format : "yyyy-MM",
			labelAlign : 'left',
			width : 135
		});

		// 禁止分页
		$("#maingrid").ligerGrid({
			usePager:false
		});

		$("#searchbtn2").bind('click',function(){
			spreadChannelDataTotal();
		});

		spreadChannelDataTotal();
	});

	function spreadChannelDataTotal() {
		var reportType = $("#reportType").val();
		var reportDayDateBegin = $("#reportDayDateBegin").val();
		var reportDayDateEnd = $("#reportDayDateEnd").val();
		var reportMonthDateBegin = $("#reportMonthDateBegin").val();
		var reportMonthDateEnd = $("#reportMonthDateEnd").val();
		var channel = $("#channel").val();
		//1,展示渠道;2,隐藏渠道
		var showStatus = 1;
		if($("#showStatus").is(":checked")){
			showStatus = 2;
		}
		if(reportType == 'day' && (!reportDayDateBegin || !reportDayDateEnd)) {
			commUtil.alertError("请选择报表日期")
			return;
		}
		if(reportType == 'month' && (!reportMonthDateBegin || !reportMonthDateEnd)) {
			commUtil.alertError("请选择报表月份")
			return;
		}
		if(reportType == 'day') {
			$(".l-grid-hd-cell-text").eq(0).text(reportDayDateBegin+"~"+reportDayDateEnd);
			$("#td-date").text(reportDayDateBegin+"~"+reportDayDateEnd);
		}else {
			$(".l-grid-hd-cell-text").eq(0).text(reportMonthDateBegin+"~"+reportMonthDateEnd);
			$("#td-date").text(reportMonthDateBegin+"~"+reportMonthDateEnd);
		}
		$("#entirety_new_shop").html('0.00');
		$("#actual_consumption").html('0.00');
		$("#total_pay_amount_sum").html('0.00');
		$("#co_pay_amount_sum").html('0.00');
		$("#combine_order_count").html('0');
		$("#each_member_combine_order_pirce").html('0.00');
		$("#roi").html('0.00');
		$("#searchbtn").click();
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/promotionData/spreadChannelData/spreadChannelDataTotal.shtml",
			data : {reportType : reportType, reportDayDateBegin : reportDayDateBegin, reportDayDateEnd : reportDayDateEnd,
				reportMonthDateBegin : reportMonthDateBegin, reportMonthDateEnd : reportMonthDateEnd, channel : channel,
				showStatus :showStatus},
			dataType : 'json',
			success : function(data){
				if(data.code == 200) {
					$("#entirety_new_shop").html(data.entirety_new_shop);
					$("#actual_consumption").html(data.actual_consumption);
					$("#total_pay_amount_sum").html(data.total_pay_amount_sum);
					$("#co_pay_amount_sum").html(data.co_pay_amount_sum);
					$("#combine_order_count").html(data.combine_order_count);
					$("#each_member_combine_order_pirce").html(data.each_member_combine_order_pirce);
					$("#roi").html(data.roi);
				}
			},
			error : function(e) {
				commUtil.alertError("系统异常请稍后再试！");
			}
		});
	}

	var listConfig={
		url:"/promotionData/spreadChannelData/spreadChannelDataList.shtml",
		btnItems:[],
		listGrid:{ columns: [
				{ display: '日期', width: 300, name:'channel_type'},
				{ display: '账面消耗', width:150, name:'total_consumption_sum'},
				{ display: '实际消耗', width:150, name:'actual_consumption', totalSummary:{type:'sum'}},
				{ display: '激活用户', width:150, name:'conversion_quantity_sum'},
				{ display: '单个激活成本', width:150, name:'each_conversion_quantity'},
				{ display: '新购客户', width:150, name:'new_guest_combine_order_count'},
				{ display: '新购成本', width:150, name:'new_conversion_quantity'},
				{ display: '推广销售额', width:150, name:'total_pay_amount_sum'}
			],
			showCheckbox : false,  //不设置默认为 true
			showRownumber: false, //不设置默认为 true
			showIsScroll: false,
			showGroupColumnName: 'channel',
			showGroupColumnDisplay: '推广渠道'
		},
		container:{
			toolBarName:"toptoolbar",
			searchBtnName:"searchbtn",
			fromName:"dataForm",
			listGridName:"maingrid"
		}
	};

	function updateReportType(reportType) {
		if(reportType == "day" ) {
			$("#report-day").show();
			$("#report-month").hide();
		}else {
			$("#report-day").hide();
			$("#report-month").show();
		}
	}

</script>

<style type="text/css">
	.l-grid-grouprow-cell{
		font-size: 12px;
	}
	.title-top{
		padding-top: 10px;
		padding-bottom: 10px;
		overflow: auto;
	}
	.marT10{
		margin-top: 10px;
	}
	table.gridtable {
		font-family: verdana,arial,sans-serif;
		border-width: 1px;
		border-color: #DDDDDD;
		border-collapse: collapse;
		color:#333333;
		font-size: 12px;
		width: 1360px;
	}
	table.gridtable td {
		border-width: 1px;
		padding: 7px;
		border-style: solid;
		border-color: #DDDDDD;
		background-color: #ffffff;
		text-align: center;
	}
	table.gridtable td.title {
		border-top: 1px solid #A3C0E8;
		height: 22px;
		background: #E2F0FF url(${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/images/grid/header-bg.gif) repeat-x left bottom;
		overflow: hidden;
		width: 100%;
	}

</style>

</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<%--<div id="toptoolbar"></div>--%>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >报表类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="reportType" name="reportType" style="width: 135px;" onchange="updateReportType(this.value);">
							<option value="day">日报表</option>
							<option value="month">月报表</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="width: 40%;" id="report-day" >
					<div class="search-td-label" style="float: left;width: 20%;">
						<span style="color: #FF0000;font-weight: bold;">*</span>报表日期：
					</div>
					<div class="l-panel-search-item" >
						<input type="text" id="reportDayDateBegin" name="reportDayDateBegin" class="dateEditor" value="${reportDayDate}" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="reportDayDateEnd" name="reportDayDateEnd" class="dateEditor" value="${reportDayDate}" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;display: none;" id="report-month" >
					<div class="search-td-label" style="float: left;width: 20%;">
						<span style="color: #FF0000;font-weight: bold;">*</span>报表月份：
					</div>
					<div class="l-panel-search-item" >
						<input type="text" id="reportMonthDateBegin" name="reportMonthDateBegin" value="${reportMonthDate}" class="dateMonthEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="reportMonthDateEnd" name="reportMonthDateEnd" value="${reportMonthDate}" class="dateMonthEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" style="margin-left: -200px">
					<div class="search-td-label">
						推广渠道：
					</div>
					<div class="search-td-condition">
						<select id="channel" name="channel" style="width: 135px;" >
							<option value="">请选择</option>
							<c:forEach var="channel" items="${channelList}">
								<option value="${channel.statusValue},${channel.statusDesc}">${channel.statusDesc}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td" style="margin-left: -100px">
					<div class="search-td-label" >
						<input type="checkbox" id = "showStatus" name="showStatus" value="2" checked>
					</div>
					<div class="search-td-condition" >
						隐藏实际消耗为零的推广渠道
					</div>
				</div>
				<div class="search-td-search-btn" >
					<input type="button" class="l-button" ligeruiid="searchbtn" style="width: 60px;" value="查询" id="searchbtn2">
					<input type="hidden" id="searchbtn">
				</div>
			</div>
		</div>
		<div class="title-top">
			<table class="gridtable marT10">
				<tr>
					<td class="title" style="width: 318px;">日期</td>
					<td class="title" style="width: 150px;">总消耗</td>
					<td class="title" style="width: 150px;">推广销售额</td>
					<td class="title" style="width: 150px;">总销售额</td>
					<td class="title" style="width: 150px;">总订单</td>
					<td class="title" style="width: 150px;">新购成本</td>
					<td class="title" style="width: 150px;">客单价</td>
					<td class="title" style="width: 150px;">ROI</td>

				</tr>
				<tr>
					<td id="td-date">${reportDayDate}</td>
					<td id="actual_consumption">0.00</td>
					<td id="total_pay_amount_sum">0.00</td>
					<td id="co_pay_amount_sum">0.00</td>
					<td id="combine_order_count">0</td>
					<td id="entirety_new_shop">0.00</td>
					<td id="each_member_combine_order_pirce">0.00</td>
					<td id="roi">0.00</td>
				</tr>
			</table>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>