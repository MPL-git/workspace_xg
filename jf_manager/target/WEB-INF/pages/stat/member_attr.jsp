<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>会员属性统计</title>

	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	.l-text-wrapper {display: inline-block;}
	</style>
</head>
<body style="padding:10px; height:100%;">

<div style="border:1px solid #d7d7d7; font-size:14px; background-color: #f2f2f2; padding:3px 10px;">
	<form class="form-horizontal" id="exportForm" action="${pageContext.request.contextPath}/stat/memberAreaExport.shtml" method="post">
	<input type="hidden" name="areaType" value="1"/>
	<select id="timeType" name="timeType" style="position: relative; bottom: 5px;">
		<option value="1">注册时间</option>
		<option value="2">最后登录时间</option>
	</select>
	<input type="text" class="ligerDate" name="beginDate" value="<fmt:formatDate value="${beginDate}" pattern="yyyy-MM-dd"/>" />
	&nbsp;&nbsp;至&nbsp;&nbsp;
	<input type="text" class="ligerDate" name="endDate" value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"/>
	&nbsp;&nbsp;
	<div class="liger-button" style="display: inline-block;" onclick="statChart()">查看</div>
	</form>
</div>

<div style="height:5px;"></div>

<div style="border:1px solid #d7d7d7; font-size:18px; font-weight:bold; background-color: #f2f2f2; padding:3px 10px;">会员性别分析</div>
<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
	<div id="genderChart" style="width: 900px;height:200px;"></div>
</div>

<div style="height:5px;"></div>

<div style="border:1px solid #d7d7d7; font-size:18px; font-weight:bold; background-color: #f2f2f2; padding:3px 10px;">会员地区分析</div>
<div style="border:1px solid #d7d7d7; border-top:0px; padding:3px 10px;">
	<div id="areaMapChart" style="width: 600px;height:480px; display: inline-block"></div>
	<div id="areaPieChart" style="width: 600px;height:480px; display: inline-block"></div>
</div>

<div style="height:5px;"></div>

<div id="tab1" style="overflow:hidden; border:1px solid #A3C0E8; ">
	<div title="按省份" style="padding:5px;">
		<div id="maingrid_province" style="margin: 0; padding: 0"></div>
	</div>
	<div title="按城市" style="padding:5px;">
		<div id="maingrid_city" style="margin: 0; padding: 0"></div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/echarts/echarts.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/asset/map/js/china.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/echarts/china.js"></script>--%>
<script type="text/javascript">

	$(function (){
//		$("#timeType").ligerComboBox({
//			data: [{ text: '注册时间', id: '1' },{ text: '最后登录时间', id: '2' }],
//			value: '1'
//		});

		$("#tab1").ligerTab({
			onBeforeSelectTabItem: function (tabid){
				var selTabId = liger.get("tab1").getSelectedTabItemID();
				if(selTabId != tabid){
//					if(tabid == 'tabitem1'){
//						listArea(1);
//					}
					if(tabid == 'tabitem2'){
						listArea(2);
					}

				}
			}
		});

		$(".ligerDate").ligerDateEditor( {
			width: 158,
			format: "yyyy-MM-dd"
		});

		statGenderChart();
		statAreaMapChart();
		statAreaPieChart();

		listArea(1);
	});

	function statChart(){
		statGenderChart();
		statAreaMapChart();
		statAreaPieChart();
		listArea(1);
	}

	// 性别分析图表
	var genderChart = echarts.init(document.getElementById("genderChart"));
	function statGenderChart(){
		// 基于准备好的dom，初始化echarts实例
		var url = "${pageContext.request.contextPath}/stat/memberGenderStat.shtml";
		var data = [];
		data.push({"name": "timeType", "value": $("select[name='timeType']").attr("value")});
		data.push({"name": "beginDate", "value": $("input[name='beginDate']").attr("value")});
		data.push({"name": "endDate", "value": $("input[name='endDate']").attr("value")});
		genderChart.showLoading();
		$.getJSON(url, data, function (res) {
			//console.log(JSON.stringify(res.data));
			var manCount = res.data.manCount;
			var womanCount = res.data.womanCount;
			var otherCount = res.data.otherCount;
			var allCount = manCount + womanCount + otherCount;
			genderChart.hideLoading();
			genderChart.setOption({
				tooltip : {
					trigger: 'axis',
					axisPointer : {            // 坐标轴指示器，坐标轴触发有效
						type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend: {
					data: ["男",'女','未知']
				},
				xAxis : {
					type: 'value'
				},
				yAxis: {
					type: 'category',
					data: ['性别']
				},
				series: [{
					"type": "bar",
					"name": "男",
					"stack": '总量',
					"label": {
						normal: {
							show: true,
							position: 'inside',
							offset:[0, -7],
							formatter: '男（{c}）' + (manCount / allCount * 100).toFixed(2)  + '%'
						}
					},
					"data": [manCount]
				},
				{
					"type": "bar",
					"name": "女",
					"stack": '总量',
					"label": {
						normal: {
							show: true,
							position: 'inside',
							offset:[0, -7],
							formatter: '女（{c}）' + (womanCount / allCount * 100).toFixed(2)  + '%'
						}
					},
					"data": [womanCount]
				},
				{
					"type": "bar",
					"name": "未知",
					"stack": '总量',
					"label": {
						normal: {
							show: true,
							position: 'inside',
							offset:[0, -7],
							formatter: '未知（{c}）' + (otherCount / allCount * 100).toFixed(2)  + '%'
						}
					},
					"data": [otherCount]
				}],
				color: ['#2e91da','#6cbf3d', '#f5ad46']
			})

		});
	}

	// 地区分析地图
	var areaMapChart = echarts.init(document.getElementById("areaMapChart"));
	function statAreaMapChart(){
		// 基于准备好的dom，初始化echarts实例
		var url = "${pageContext.request.contextPath}/stat/memberAreaStat.shtml";
		var data = [];
		data.push({"name": "timeType", "value": $("select[name='timeType']").attr("value")});
		data.push({"name": "beginDate", "value": $("input[name='beginDate']").attr("value")});
		data.push({"name": "endDate", "value": $("input[name='endDate']").attr("value")});
		areaMapChart.showLoading();
		$.getJSON(url, data, function (res) {
			areaMapChart.hideLoading();
			areaMapChart.setOption({
				tooltip: {
					trigger: 'item',
					formatter: '{b}<br/>{c}'
				},
				visualMap: {
					orient:'horizontal',
					left: 'center',
					top: 'top',
					text: ['高','低'],           // 文本，默认为数值文本
					inRange: {
						color: ['lightskyblue','yellow', 'orangered']
					}
				},
				series: [
					{
						name: 'iphone',
						type: 'map',
						mapType: 'china',
						roam: false,
						label: {
							normal: {
								show: true
							},
							emphasis: {
								show: true
							}
						},
						data:res.serieDataList
					}
				]
			});
		})

	}


	// 地区分析饼图
	var areaPieChart = echarts.init(document.getElementById("areaPieChart"));
	function statAreaPieChart(){
		// 基于准备好的dom，初始化echarts实例
		var url = "${pageContext.request.contextPath}/stat/memberAreaStat.shtml";
		var data = [];
		data.push({"name": "timeType", "value": $("select[name='timeType']").attr("value")});
		data.push({"name": "beginDate", "value": $("input[name='beginDate']").attr("value")});
		data.push({"name": "endDate", "value": $("input[name='endDate']").attr("value")});
		areaPieChart.showLoading();
		$.getJSON(url, data, function (res) {
			areaPieChart.hideLoading();
			areaPieChart.setOption({
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				series : [
					{
						name: '地区比例',
						type: 'pie',
						radius : '55%',
						center: ['50%', '60%'],
						data:res.serieDataList,
						itemStyle: {
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						}
					}
				]
			});
		})

	}

	function listArea(areaType){
		var maingrid;
		if(areaType == 1){
			maingrid = "maingrid_province";
		}else{
			maingrid = "maingrid_city";
		}
		var data = [];
		data.push({"name": "areaType", "value": areaType});
		data.push({"name": "timeType", "value": $("select[name='timeType']").attr("value")});
		data.push({"name": "beginDate", "value": $("input[name='beginDate']").attr("value")});
		data.push({"name": "endDate", "value": $("input[name='endDate']").attr("value")});
		$("#" + maingrid).ligerGrid({
			columns: [
				{ display: '地区', name: 'areaName', width: 250, align: 'center' },
				{ display: '会员数量', name: 'count', width: 250, type: 'int', align: 'center' },
				{ display: '总数占比', name: 'percent', align: 'center' }
			],
			toolbar: { items: [
				{ text: '导出表格', click: exportMemberArea, areaType:areaType, img: '${pageContext.request.contextPath}/liger/lib/ligerUI/skins/icons/database.gif' },
				{ line:true }
			]},
			width: '1000',
			url: '${pageContext.request.contextPath}/stat/memberAreaList.shtml',
			parms:data,
			dataAction: 'server', //服务器排序
			usePager: false,       //服务器分页
			alternatingRow: false
		});

	}

	function exportMemberArea(item){
		$("input[name='areaType']").val(item.areaType);
		$("#exportForm").submit();
	}

</script>
</body>
</html>
