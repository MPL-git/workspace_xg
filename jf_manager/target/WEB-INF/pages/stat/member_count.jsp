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
	.hidden{display:none;}
	</style>
</head>
<body style="padding:10px;">
<div>
	<div style="border:1px solid #d7d7d7; font-size:18px; font-weight:bold; background-color: #f2f2f2; padding:3px 10px;">会员基础数据</div>
	<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
		<p style="margin:5px;">会员总数：<span style="color:#f00; font-size:22px; font-weight:bold;">${allCount}</span></p>
		<table class="gridtable marT10">
			<tr>
				<td class="title">今天新增会员数</td><td>${todayIncreaseCount}</td>
				<td class="title">今天活跃会员数</td><td>${todayLoginCount}</td>
				<td class="title">今天消费会员数</td><td>${todayOrderPaidCount}</td>
				<td class="title">今天复购会员数</td><td>${todayOrderRepeatCount}</td>
			</tr>
			<tr>
				<td class="title">昨天新增会员数</td><td>${yesterdayIncreaseCount}</td>
				<td class="title">昨天活跃会员数</td><td>${yesterdayLoginCount}</td>
				<td class="title">昨天消费会员数</td><td>${yesterdayOrderPaidCount}</td>
				<td class="title">昨天复购会员数</td><td>${yesterdayOrderRepeatCount}</td>
			</tr>
			<tr>
				<td class="title">本月新增会员数</td><td>${monthIncreaseCount}</td>
				<td class="title">本月活跃会员数</td><td>${monthLoginCount}</td>
				<td class="title">本月消费会员数</td><td>${monthOrderPaidCount}</td>
				<td class="title">本月复购会员数</td><td>${monthOrderRepeatCount}</td>
			</tr>
		</table>

		<p style="margin-top:10px; padding:10px;background-color: #f2f2f2;">
			新增会员数：该时段新增加的会员数。<br/>
			活跃会员数：该时段有打开app的会员数。<br/>
			消费会员数：该时段有下单并付款成功的会员数（以付款时间为准）。<br/>
			复购会员数：该时段有下单，并且不是第一次下单的会员数（以付款时间为准）。<br/>
		</p>
	</div>
</div>
<%-- <div style="height:5px;"></div>
<div id="tab1" style="width: 900px;overflow:hidden; border:1px solid #A3C0E8; ">
	<div id="increaseCountDiv" title="新增会员数" style="height:500px">
		<div style="border:1px solid #d7d7d7; background-color: #f2f2f2; padding:3px 10px;" >
			<div style="display: inline-block;">
				<input type="text" class="ligerDate" name="beginDate1" value="<fmt:formatDate value="${beginDate1}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate1" value="<fmt:formatDate value="${endDate1}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<input type="checkbox" name="compare" class="liger-checkbox" onchange="compare(this, 'compareIncreaseCount');" />对比&nbsp;&nbsp;
			<div class="hidden" id="compareIncreaseCount">
				<input type="text" class="ligerDate" name="beginDate2" value="<fmt:formatDate value="${beginDate2}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate2" value="<fmt:formatDate value="${endDate2}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<div class="liger-button" style="display: inline-block;" onclick="statChart('tabitem1')">查看</div>
		</div>
		<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
			<div id="increaseCountChart" style="width: 600px;height:400px;"></div>
		</div>
	</div>
	<div id="loginCountDiv" title="活跃会员数" style="height:500px">
		<div style="border:1px solid #d7d7d7; background-color: #f2f2f2; padding:3px 10px;" >
			<div style="display: inline-block;">
				<input type="text" class="ligerDate" name="beginDate1" value="<fmt:formatDate value="${beginDate1}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate1" value="<fmt:formatDate value="${endDate1}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<input type="checkbox" name="compare" class="liger-checkbox" onchange="compare(this, 'compareLoginCount');" />对比&nbsp;&nbsp;
			<div class="hidden" id="compareLoginCount">
				<input type="text" class="ligerDate" name="beginDate2" value="<fmt:formatDate value="${beginDate2}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate2" value="<fmt:formatDate value="${endDate2}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<div class="liger-button" style="display: inline-block;" onclick="statChart('tabitem2')">查看</div>
		</div>
		<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
			<div id="loginCountChart" style="width: 600px;height:400px;"></div>
		</div>
	</div>
	<div id="orderPaidCountDiv" title="消费会员数" style="height:500px">
		<div style="border:1px solid #d7d7d7; background-color: #f2f2f2; padding:3px 10px;" >
			<div style="display: inline-block;">
				<input type="text" class="ligerDate" name="beginDate1" value="<fmt:formatDate value="${beginDate1}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate1" value="<fmt:formatDate value="${endDate1}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<input type="checkbox" name="compare" class="liger-checkbox" onchange="compare(this, 'compareOrderPaidCount');" />对比&nbsp;&nbsp;
			<div class="hidden" id="compareOrderPaidCount">
				<input type="text" class="ligerDate" name="beginDate2" value="<fmt:formatDate value="${beginDate2}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate2" value="<fmt:formatDate value="${endDate2}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<div class="liger-button" style="display: inline-block;" onclick="statChart('tabitem3')">查看</div>
		</div>
		<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
			<div id="orderPaidCountChart" style="width: 600px;height:400px;"></div>
		</div>
	</div>
</div>

<div style="display:none">

</div> --%>
</body>
</html>
