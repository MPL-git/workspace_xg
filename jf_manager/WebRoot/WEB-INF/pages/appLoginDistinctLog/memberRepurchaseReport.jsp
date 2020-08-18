<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script src="${pageContext.request.contextPath}/highcharts-7.2.1/code/highcharts.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
<style type="text/css">
	table.gridtable {
		border: 1px solid #f9f9fa;
		border-top-color: #4ad9ab;
	}
	table.gridtable td {
		border: none;
	}
	table.gridtable td.title {
		background-color: #f9f9fa;
		font-weight: bold;
		text-align: center;
	}
	.table-title{
		font-size: 17px;font-weight: 600;
	}
</style>
<script type="text/javascript">

	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});

		var dataXList = new Array();
		<c:forEach items="${dataXList}" var="dateX" varStatus="index" >
			dataXList[${index.index}] = '${dateX}';
		</c:forEach>
		var memberList = new Array();
		<c:forEach items="${memberList}" var="members" varStatus="index" >
		 	var memberCountList = new Array();
			<c:forEach items="${members}" var="memberCount" varStatus="ind" >
		 		memberCountList[${ind.index}] = ${memberCount};
		 	</c:forEach>
			var memberCountJson = {};
			<c:if test="${status == 'week'}">
		 		memberCountJson["name"] = "第${index.index+1}周";
		 	</c:if>
		 	<c:if test="${status == 'month'}">
		 		memberCountJson["name"] = "第${index.index+1}月";
		 	</c:if>
		 	memberCountJson["data"] = memberCountList;
		 	memberList[${index.index}] = memberCountJson;
		</c:forEach>

		var rateList = new Array();
		<c:forEach items="${rateList}" var="rates" varStatus="index" >
		 	var rateCountList = new Array();
			<c:forEach items="${rates}" var="rateCount" varStatus="ind" >
		 		rateCountList[${ind.index}] = ${rateCount};
		 	</c:forEach>
			var rateCountJson = {};
			<c:if test="${status == 'week'}">
		 		rateCountJson["name"] = "第${index.index+1}周";
			</c:if>
			<c:if test="${status == 'month'}">
		 		rateCountJson["name"] = "第${index.index+1}月";
			</c:if>
		 	rateCountJson["data"] = rateCountList;
		 	rateList[${index.index}] = rateCountJson;
		</c:forEach>

		// 留存人数图表配置
		var memberOptions = {
			title: {
				text: '复购人数情况'
			},
			xAxis: {
				categories: dataXList
			},
			yAxis: {
				labels: {
					format: '{value} 人'
				},
				title: {
					text: '复购人数（人）'
				}
			},
			tooltip: {
				valueSuffix: ' 人',
				shared: true
			},
			series: memberList,
			credits: {
				enabled: false
			}
		};
		// 留存人数图表初始化函数
		var memberContainer = Highcharts.chart('member-container', memberOptions);

		// 留存百分比图表配置
		var rateOptions = {
			title: {
				text: '复购百分比情况'
			},
			xAxis: {
				categories: dataXList
			},
			yAxis: {
				labels: {
					format: '{value} %'
				},
				title: {
					text: '复购百分比（%）'
				}
			},
			tooltip: {
				valueSuffix: ' %',
				shared: true
			},
			series: rateList,
			credits: {
				enabled: false
			}
		};
		// 留存百分比图表初始化函数
		var rateContainer = Highcharts.chart('rate-container', rateOptions);

	 });

	 function selectMemberList(payDate, sexType, status, resultNum) {
		 $.ligerDialog.open({
			 height: $(window).height()-100,
			 width: $(window).width()-200,
			 title: "用户复购明细",
			 name: "INSERT_WINDOW",
			 url: "${pageContext.request.contextPath}/appLoginDistinctLog/selectMemberRepurchaseListManager.shtml?"
				 +"payDate="+payDate+"&sexType="+sexType+"&status="+status+"&resultNum="+resultNum,
			 showMax: true,
			 showToggle: false,
			 showMin: false,
			 isResize: true,
			 slide: false,
			 data: null
		 });
	 }
</script>
</head>
<body>
	<div style="padding: 0px;margin: 0px;position: fixed;width: 100%;background-color: white;top: 0px;">
		<form id="dataForm" method="post" action="${pageContext.request.contextPath}/appLoginDistinctLog/memberRepurchaseReport.shtml" >
			<div class="search-pannel">
				<div class="search-tr"  >
					<div class="search-td" style="width: 80%;">
						<div class="l-panel-search-item" >
							<select id="dateStatus" name="dateStatus" style="width: 135px;height: 22px;">
								<option value="begin" <c:if test="${dateStatus == 'begin'}">selected</c:if> >开始日期</option>
								<option value="end" <c:if test="${dateStatus == 'end'}">selected</c:if> >结束日期</option>
							</select>
						</div>
						<div class="l-panel-search-item">
							<input type="text" id="payDate" name="payDate" class="dateEditor" style="width: 135px;"  value="${payDate}" placeholder="请选择" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 80px;height: 22px;" >
							<select id="sexType" name="sexType" style="width: 135px;">
								<option value="" <c:if test="${sexType == ''}">selected</c:if> >全部用户</option>
								<option value="1" <c:if test="${sexType == '1'}">selected</c:if> >男性用户</option>
								<option value="0" <c:if test="${sexType == '0'}">selected</c:if> >女性用户</option>
							</select>
						</div>
						<div class="l-panel-search-item" style="margin-left: 80px;height: 22px;" >
							<select id="keepStatus" name="keepStatus" style="width: 135px;">
								<option value="8|week" <c:if test="${keepStatus == '8|week'}">selected</c:if>>8周复购</option>
								<option value="12|week" <c:if test="${keepStatus == '12|week'}">selected</c:if> >12周复购</option>
								<option value="6|month" <c:if test="${keepStatus == '6|month'}">selected</c:if> >6个月复购</option>
								<option value="12|month" <c:if test="${keepStatus == '12|month'}">selected</c:if> >12个月复购</option>
							</select>
						</div>
						<div class="l-panel-search-item" >

						</div>
					</div>
					<div class="search-td-search-btn" >
						<input class="l-button" type="submit" value="查询">
					</div>
				</div>
			</div>
		</form>
	</div>
	<div style="padding: 0px; margin-top: 50px;">
		<div style="margin: 20px 0px 15px 0px;">
			<span class="table-title" >
				<c:if test="${empty sexType or sexType == ''}">
					全部用户
				</c:if>
				<c:if test="${sexType == '1'}">
					男性用户
				</c:if>
				<c:if test="${sexType == '0'}">
					女性用户
				</c:if>
				<c:if test="${empty keepStatus or keepStatus == '8|week'}">
					8周复购
				</c:if>
				<c:if test="${keepStatus == '12|week'}">
					12周复购
				</c:if>
				<c:if test="${keepStatus == '6|month'}">
					6个月复购
				</c:if>
				<c:if test="${keepStatus == '12|month'}">
					12个月复购
				</c:if>
				 分析
			</span>
		</div>
		<div>
			<table class="gridtable">
				<tr align="center">
					<td class="title">日期</td>
					<td class="title">总人数</td>
					<c:forEach items="${resultNumList}" var="resultNum" >
						<td class="title">
							第${resultNum}<c:if test="${status == 'month'}" >月</c:if><c:if test="${status == 'week'}" >周</c:if>
						</td>
					</c:forEach>
				</tr>
				<c:forEach items="${list}" var="map" >
					<tr align="center">
						<td>${map.pay_date}</td>
						<td style="font-weight: bold;">
							<c:if test="${map.base_count != 0}" >
								<span style="text-decoration: underline;font-weight: bold;">
									<a href="javascript:selectMemberList('${map.pay_date_param}','${sexType}','${status}','');">${map.base_count}人</a>
								</span>
							</c:if>
							<c:if test="${map.base_count == 0}" >
								<span style="text-decoration: underline;font-weight: bold;">${map.base_count}人</span>
							</c:if>
						</td>
						<c:forEach items="${resultNumList}" var="resultNum" >
							<c:set value="wm_count_${resultNum}" var="wm_count" />
							<c:set value="wm_count_rate_${resultNum}" var="wm_count_rate" />
							<td <c:if test="${not empty map[wm_count]}" >style="background: #4ad9ab;"</c:if> >
								<c:if test="${map[wm_count] == 0}" >
									<span style="text-decoration: underline;font-weight: bold;">${map[wm_count]}人</span>
								</c:if>
								<c:if test="${not empty map[wm_count] and map[wm_count] != 0}" >
									<span style="text-decoration: underline;font-weight: bold;">
										<a href="javascript:selectMemberList('${map.pay_date_param}','${sexType}','${status}','${resultNum}');">${map[wm_count]}人</a>
									</span>
								</c:if>
								<br>
								<c:if test="${not empty map[wm_count_rate]}" >
									${map[wm_count_rate]}%
								</c:if>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div style="margin: 50px 0px 15px 0px;">
			<span class="table-title" >
				<c:if test="${empty sexType or sexType == ''}">
					全部用户
				</c:if>
				<c:if test="${sexType == '1'}">
					男性用户
				</c:if>
				<c:if test="${sexType == '0'}">
					女性用户
				</c:if>
				<c:if test="${empty keepStatus or keepStatus == '8|week'}">
					8周复购
				</c:if>
				<c:if test="${keepStatus == '12|week'}">
					12周复购
				</c:if>
				<c:if test="${keepStatus == '6|month'}">
					6个月复购
				</c:if>
				<c:if test="${keepStatus == '12|month'}">
					12个月复购
				</c:if>
				 趋势（复购人数）
			</span>
		</div>
		<div>
			<div id="member-container" style="width: 90%;height:500px;"></div>
		</div>

		<div style="margin: 50px 0px 15px 0px;">
			<span class="table-title" >
				<c:if test="${empty sexType or sexType == ''}">
					全部用户
				</c:if>
				<c:if test="${sexType == '1'}">
					男性用户
				</c:if>
				<c:if test="${sexType == '0'}">
					女性用户
				</c:if>
				<c:if test="${empty keepStatus or keepStatus == '8|week'}">
					8周复购
				</c:if>
				<c:if test="${keepStatus == '12|week'}">
					12周复购
				</c:if>
				<c:if test="${keepStatus == '6|month'}">
					6个月复购
				</c:if>
				<c:if test="${keepStatus == '12|month'}">
					12个月复购
				</c:if>
				 趋势（复购百分比）
			</span>
		</div>
		<div>
			<div id="rate-container" style="width: 90%;height:500px;"></div>
		</div>
	</div>


</body>

</html>
