<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>金币数据统计</title>

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
	<div style="border:1px solid #d7d7d7; font-size:18px; font-weight:bold; background-color: #f2f2f2; padding:3px 10px;">金币基础数据</div>
	<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
		<p style="margin:5px;">金币总数：<span style="color:#f00; font-size:22px; font-weight:bold;">${integralCount}</span></p>
		<p style="margin:5px;">成长值总数：<span style="color:#f00; font-size:22px; font-weight:bold;">${growthCount}</span></p>
		<table class="gridtable marT10">
			<tr>
				<td class="title">今天新增金币数</td><td>${todayIntegralPlusCount}</td>
				<td class="title">今天消耗金币数</td><td>${todayIntegralMinusCount}</td>
				<td class="title">今天净增金币数</td><td>${todayIntegralNetPlusCount}</td>
				<td class="title">今天新增成长值</td><td>${todayGrowthCount}</td>
			</tr>
			<tr>
				<td class="title">昨天新增金币数</td><td>${yesterdayIntegralPlusCount}</td>
				<td class="title">昨天消耗金币数</td><td>${yesterdayIntegralMinusCount}</td>
				<td class="title">昨天净增金币数</td><td>${yesterdayIntegralNetPlusCount}</td>
				<td class="title">昨天新增成长值</td><td>${yesterdayGrowthCount}</td>
			</tr>
			<tr>
				<td class="title">本月新增金币数</td><td>${monthIntegralPlusCount}</td>
				<td class="title">本月消耗金币数</td><td>${monthIntegralMinusCount}</td>
				<td class="title">本月净增金币数</td><td>${monthIntegralNetPlusCount}</td>
				<td class="title">本月新增成长值</td><td>${monthGrowthCount}</td>
			</tr>
		</table>

		<p style="margin-top:10px; padding:10px;background-color: #f2f2f2;">
			新增金币数：该时段全平台新增加的金币数。<br/>
			消耗金币数：该时段全平台消耗掉的金币数。<br/>
			净增金币数：该时段全平台净增金币数（新增金币数-消耗金币数）（允许负数）。<br/>
			新增成长值：该时段全平台新增加的成长值。<br/>
		</p>
	</div>
</div>
<div style="height:5px;"></div>
<div id="tab1" style="width: 900px;overflow:hidden; border:1px solid #A3C0E8; ">
	<div id="integralPlusCountDiv" title="新增金币数" style="height:500px">
		<div style="border:1px solid #d7d7d7; background-color: #f2f2f2; padding:3px 10px;" >
			<div style="display: inline-block;">
				<input type="text" class="ligerDate" name="beginDate1" value="<fmt:formatDate value="${beginDate1}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate1" value="<fmt:formatDate value="${endDate1}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<input type="checkbox" name="compare" class="liger-checkbox" onchange="compare(this, 'compareIntegralPlusCount');" />对比&nbsp;&nbsp;
			<div class="hidden" id="compareIntegralPlusCount">
				<input type="text" class="ligerDate" name="beginDate2" value="<fmt:formatDate value="${beginDate2}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate2" value="<fmt:formatDate value="${endDate2}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<div class="liger-button" style="display: inline-block;" onclick="statChart('tabitem1')">查看</div>
		</div>
		<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
			<div id="integralPlusCountChart" style="width: 600px;height:400px;"></div>
		</div>
	</div>
	<div id="integralMinusCountDiv" title="消耗金币数" style="height:500px">
		<div style="border:1px solid #d7d7d7; background-color: #f2f2f2; padding:3px 10px;" >
			<div style="display: inline-block;">
				<input type="text" class="ligerDate" name="beginDate1" value="<fmt:formatDate value="${beginDate1}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate1" value="<fmt:formatDate value="${endDate1}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<input type="checkbox" name="compare" class="liger-checkbox" onchange="compare(this, 'compareIntegralMinusCount');" />对比&nbsp;&nbsp;
			<div class="hidden" id="compareIntegralMinusCount">
				<input type="text" class="ligerDate" name="beginDate2" value="<fmt:formatDate value="${beginDate2}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate2" value="<fmt:formatDate value="${endDate2}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<div class="liger-button" style="display: inline-block;" onclick="statChart('tabitem2')">查看</div>
		</div>
		<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
			<div id="integralMinusCountChart" style="width: 600px;height:400px;"></div>
		</div>
	</div>
	<div id="integralNetPlusCountDiv" title="净增金币数" style="height:500px">
		<div style="border:1px solid #d7d7d7; background-color: #f2f2f2; padding:3px 10px;" >
			<div style="display: inline-block;">
				<input type="text" class="ligerDate" name="beginDate1" value="<fmt:formatDate value="${beginDate1}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate1" value="<fmt:formatDate value="${endDate1}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<input type="checkbox" name="compare" class="liger-checkbox" onchange="compare(this, 'compareIntegralNetPlusCount');" />对比&nbsp;&nbsp;
			<div class="hidden" id="compareIntegralNetPlusCount">
				<input type="text" class="ligerDate" name="beginDate2" value="<fmt:formatDate value="${beginDate2}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate2" value="<fmt:formatDate value="${endDate2}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<div class="liger-button" style="display: inline-block;" onclick="statChart('tabitem3')">查看</div>
		</div>
		<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
			<div id="integralNetPlusCountChart" style="width: 600px;height:400px;"></div>
		</div>
	</div>
	<div id="growthCountDiv" title="新增成长值" style="height:500px">
		<div style="border:1px solid #d7d7d7; background-color: #f2f2f2; padding:3px 10px;" >
			<div style="display: inline-block;">
				<input type="text" class="ligerDate" name="beginDate1" value="<fmt:formatDate value="${beginDate1}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate1" value="<fmt:formatDate value="${endDate1}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<input type="checkbox" name="compare" class="liger-checkbox" onchange="compare(this, 'compareGrowthCount');" />对比&nbsp;&nbsp;
			<div class="hidden" id="compareGrowthCount">
				<input type="text" class="ligerDate" name="beginDate2" value="<fmt:formatDate value="${beginDate2}" pattern="yyyy-MM-dd"/>" />
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" class="ligerDate" name="endDate2" value="<fmt:formatDate value="${endDate2}" pattern="yyyy-MM-dd"/>"/>
				&nbsp;&nbsp;
			</div>
			<div class="liger-button" style="display: inline-block;" onclick="statChart('tabitem4')">查看</div>
		</div>
		<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
			<div id="growthCountChart" style="width: 600px;height:400px;"></div>
		</div>
	</div>
</div>

<div style="display:none">

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/echarts/echarts.js"></script>
<script type="text/javascript">

	$(function (){
		$("#tab1").ligerTab({
			onBeforeSelectTabItem: function (tabid){
				var selTabId = liger.get("tab1").getSelectedTabItemID();
				if(selTabId != tabid){
					if(tabid == 'tabitem1' && !chart1IsLoaded){
						statChart(tabid);
					}else if(tabid == 'tabitem2' && !chart2IsLoaded){
						statChart(tabid);
					}else if(tabid == 'tabitem3' && !chart3IsLoaded){
						statChart(tabid);
					}else if(tabid == 'tabitem4' && !chart4IsLoaded){
						statChart(tabid);
					}

				}
			}
		});

		$(".ligerDate").ligerDateEditor( {
			width: 158,
			format: "yyyy-MM-dd"
		});

		statChart("tabitem1");
	});


	var option = {
		tooltip : {
			trigger: 'axis',
			axisPointer: {
				type: 'cross',
				label: {
					backgroundColor: '#6a7985'
				}
			}
		},
		xAxis : {
			type : 'time',
		},
		yAxis : {
			type : 'value'
		},
		series : []
	}

	var chart1 = echarts.init(document.getElementById("integralPlusCountChart"));
	var chart1IsLoaded = false;
	var chart2 = echarts.init(document.getElementById("integralMinusCountChart"));
	var chart2IsLoaded = false;
	var chart3 = echarts.init(document.getElementById("integralNetPlusCountChart"));
	var chart3IsLoaded = false;
	var chart4 = echarts.init(document.getElementById("growthCountChart"));
	var chart4IsLoaded = false;
	chart1.setOption(option);
	chart2.setOption(option);
	chart3.setOption(option);
	chart4.setOption(option);

	function statChart(tabId){
		// 基于准备好的dom，初始化echarts实例
		var chartDiv;
		var type;
		if(tabId == 'tabitem1'){
			type = 1;
			chartDiv = "integralPlusCountDiv";
			chart1IsLoaded = true;
		}else if(tabId == 'tabitem2'){
			type = 2;
			chartDiv = "integralMinusCountDiv";
			chart2IsLoaded = true;
		}else if(tabId == 'tabitem3'){
			type = 3;
			chartDiv = "integralNetPlusCountDiv";
			chart3IsLoaded = true;
		}else if(tabId == 'tabitem4'){
			type = 4;
			chartDiv = "growthCountDiv";
			chart4IsLoaded = true;
		}
		var url = "${pageContext.request.contextPath}/stat/integralStat.shtml";
		var data = [];
		data.push({"name": "type", "value": type});
		data.push({"name": "beginDate1", "value": $("#" + chartDiv + " input[name='beginDate1']").attr("value")});
		data.push({"name": "endDate1", "value": $("#" + chartDiv + " input[name='endDate1']").attr("value")});
		if($("#" + chartDiv + " input[name='compare']").is(':checked')){
			data.push({"name": "beginDate2", "value": $("#" + chartDiv + " input[name='beginDate2']").attr("value")});
			data.push({"name": "endDate2", "value": $("#" + chartDiv + " input[name='endDate2']").attr("value")});
		}
		//console.log(JSON.stringify(data));
		if(tabId == 'tabitem1'){
			chart1.showLoading();
		}else if(tabId == 'tabitem2'){
			chart2.showLoading();
		}else if(tabId == 'tabitem3'){
			chart3.showLoading();
		}else if(tabId == 'tabitem4'){
			chart4.showLoading();
		}
		$.getJSON(url, data, function (res) {
			//console.log(JSON.stringify(res.data.serieList));
			// 填入数据
			if(tabId == 'tabitem1'){
				chart1.hideLoading();
				var option = chart1.getOption();
				option.series = res.data.serieList;
				chart1.setOption(option, true);
			}else if(tabId == 'tabitem2'){
				chart2.hideLoading();
				var option = chart2.getOption();
				option.series = res.data.serieList;
				chart2.setOption(option, true);
			}else if(tabId == 'tabitem3'){
				chart3.hideLoading();
				var option = chart3.getOption();
				option.series = res.data.serieList;
				chart3.setOption(option, true);
			}else if(tabId == 'tabitem4'){
				chart4.hideLoading();
				var option = chart4.getOption();
				option.series = res.data.serieList;
				chart4.setOption(option, true);
			}

		});
	}

	function compare(obj, compareId){
		if(obj.checked){
			$("#" + compareId).css("display", "inline-block");
		}else{
			$("#" + compareId).css("display", "none");
		}
	}

</script>
</body>
</html>
