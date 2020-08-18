<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>会员数据统计</title>

	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	.title{font-size: 14px; background-color: #fad8cb!important; font-weight: 600;}
	.l-text-wrapper {display: inline-block;}
	</style>
</head>
<body style="padding:10px;">
<div style="border:1px solid #d7d7d7; background-color: #f2f2f2; padding:3px 10px;" >
	<form class="form-horizontal">
		<span style="display: inline-block; position: relative; top: -6px;">统计日期&nbsp;&nbsp;</span>
		<input type="text" class="ligerDate" id="statDate" name="statDate" value="${statDate}"/>
		<span style="display: inline-block; position: relative; top: -6px;">&nbsp;-&nbsp;</span>
		<input type="text" class="ligerDate" id="endDate" name="endDate" value="${endDate}"/>
		&nbsp;&nbsp;&nbsp;&nbsp;<div class="liger-button" style="display: inline-block;" onclick="submitForm()">搜索</div>
	</form>
</div>
<c:if test="${not empty statDate}">
<div style="height:5px;"></div>
<div>
	<div style="border:1px solid #d7d7d7; font-size:18px; font-weight:bold; background-color: #f2f2f2; padding:3px 10px;">
		注册用户(统计日期)在统计日期内的消费数据
	</div>
	<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
		<table class="gridtable marT10">
			<tr>
				<td class="title">消费金额（元）<100</td><td>${regMemberCountOfPayAmountLessThen100}人</td>
				<td class="title">消费次数（次）=1</td><td>${regMemberCountOfOrderCountEquals1}人</td>
			</tr>
			<tr>
				<td class="title">100≤消费金额（元）<200</td><td>${regMemberCountOfPayAmountMoreThen100AndLessThen200}人</td>
				<td class="title">消费次数（次）=2</td><td>${regMemberCountOfOrderCountEquals2}人</td>
			</tr>
			<tr>
				<td class="title">200≤消费金额（元）<300</td><td>${regMemberCountOfPayAmountMoreThen200AndLessThen300}人</td>
				<td class="title">消费次数（次）=3</td><td>${regMemberCountOfOrderCountEquals3}人</td>
			</tr>
			<tr>
				<td class="title">300≤消费金额（元）<400</td><td>${regMemberCountOfPayAmountMoreThen300AndLessThen400}人</td>
				<td class="title">消费次数（次）≥4</td><td>${regMemberCountOfOrderCountMoreOrEquals4}人</td>
			</tr>
			<tr>
				<td class="title">400≤消费金额（元）<800</td><td>${regMemberCountOfPayAmountMoreThen400AndLessThen800}人</td>
				<td class="title"></td><td></td>
			</tr>
			<tr>
				<td class="title">800≤消费金额（元）<1600</td><td>${regMemberCountOfPayAmountMoreThen800AndLessThen1600}人</td>
				<td class="title"></td><td></td>
			</tr>
			<tr>
				<td class="title">消费金额（元）≥1600</td><td>${regMemberCountOfPayAmountMoreOrEquals1600}人</td>
				<td class="title"></td><td></td>
			</tr>
		</table>
	</div>
</div>
<div style="height:5px;"></div>
<div>
	<div style="border:1px solid #d7d7d7; font-size:18px; font-weight:bold; background-color: #f2f2f2; padding:3px 10px;">
		历史用户在统计日期内的消费数据
	</div>
	<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
		<table class="gridtable marT10">
			<tr>
				<td class="title">消费金额（元）<100</td><td>${hisMemberCountOfPayAmountLessThen100}人</td>
				<td class="title">消费次数（次）=1</td><td>${hisMemberCountOfOrderCountEquals1}人</td>
			</tr>
			<tr>
				<td class="title">100≤消费金额（元）<200</td><td>${hisMemberCountOfPayAmountMoreThen100AndLessThen200}人</td>
				<td class="title">消费次数（次）=2</td><td>${hisMemberCountOfOrderCountEquals2}人</td>
			</tr>
			<tr>
				<td class="title">200≤消费金额（元）<300</td><td>${hisMemberCountOfPayAmountMoreThen200AndLessThen300}人</td>
				<td class="title">消费次数（次）=3</td><td>${hisMemberCountOfOrderCountEquals3}人</td>
			</tr>
			<tr>
				<td class="title">300≤消费金额（元）<400</td><td>${hisMemberCountOfPayAmountMoreThen300AndLessThen400}人</td>
				<td class="title">消费次数（次）≥4</td><td>${hisMemberCountOfOrderCountMoreOrEquals4}人</td>
			</tr>
			<tr>
				<td class="title">400≤消费金额（元）<800</td><td>${hisMemberCountOfPayAmountMoreThen400AndLessThen800}人</td>
				<td class="title"></td><td></td>
			</tr>
			<tr>
				<td class="title">800≤消费金额（元）<1600</td><td>${hisMemberCountOfPayAmountMoreThen800AndLessThen1600}人</td>
				<td class="title"></td><td></td>
			</tr>
			<tr>
				<td class="title">消费金额（元）≥1600</td><td>${hisMemberCountOfPayAmountMoreOrEquals1600}人</td>
				<td class="title"></td><td></td>
			</tr>
		</table>
	</div>
</div>
<div style="height:5px;"></div>
<div>
	<div style="border:1px solid #d7d7d7; font-size:18px; font-weight:bold; background-color: #f2f2f2; padding:3px 10px;">
		SVIP会员在统计日期内的消费数据
	</div>
	<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
		<table class="gridtable marT10">
			<tr>
				<td class="title">消费金额（元）<100</td><td>${svipMemberCountOfPayAmountLessThen100}人</td>
				<td class="title">消费次数（次）=1</td><td>${svipMemberCountOfOrderCountEquals1}人</td>
			</tr>
			<tr>
				<td class="title">100≤消费金额（元）<200</td><td>${svipMemberCountOfPayAmountMoreThen100AndLessThen200}人</td>
				<td class="title">消费次数（次）=2</td><td>${svipMemberCountOfOrderCountEquals2}人</td>
			</tr>
			<tr>
				<td class="title">200≤消费金额（元）<300</td><td>${svipMemberCountOfPayAmountMoreThen200AndLessThen300}人</td>
				<td class="title">消费次数（次）=3</td><td>${svipMemberCountOfOrderCountEquals3}人</td>
			</tr>
			<tr>
				<td class="title">300≤消费金额（元）<400</td><td>${svipMemberCountOfPayAmountMoreThen300AndLessThen400}人</td>
				<td class="title">消费次数（次）≥4</td><td>${svipMemberCountOfOrderCountMoreOrEquals4}人</td>
			</tr>
			<tr>
				<td class="title">400≤消费金额（元）<800</td><td>${svipMemberCountOfPayAmountMoreThen400AndLessThen800}人</td>
				<td class="title"></td><td></td>
			</tr>
			<tr>
				<td class="title">800≤消费金额（元）<1600</td><td>${svipMemberCountOfPayAmountMoreThen800AndLessThen1600}人</td>
				<td class="title"></td><td></td>
			</tr>
			<tr>
				<td class="title">消费金额（元）≥1600</td><td>${svipMemberCountOfPayAmountMoreOrEquals1600}人</td>
				<td class="title"></td><td></td>
			</tr>
		</table>
	</div>
</div>
</c:if>
<script type="text/javascript">

	$(function (){
		$(".ligerDate").ligerDateEditor( {
			width: 158,
			format: "yyyy-MM-dd"
		});

	});

	function submitForm(){
		var statDate =$("#statDate").attr("value");
		var endDate =$("#endDate").attr("value");
        if(!statDate || !endDate){
            commUtil.alertError("请选择统计日期！");
            return;
        }else{
            var days = new Date(endDate).getTime() - new Date(statDate).getTime();
            var day = parseInt(days / (1000 * 60 * 60 * 24));
            if(day>92){
                commUtil.alertError("日期不能超过3个月！");
                return;
            }
            if(day<0){
                commUtil.alertError("日期格式错误！");
                return;
			}
		}
		window.location.href="${pageContext.request.contextPath}/stat/memberPayData.shtml?statDate=" + statDate + "&endDate=" + endDate;
	}

</script>
</body>
</html>
