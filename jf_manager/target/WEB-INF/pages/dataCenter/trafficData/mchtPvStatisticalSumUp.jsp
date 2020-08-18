<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 <style type="text/css">
 	.tab-1{
		width: 100%;
		height: 57px;
		background: inherit;
	    background-color: inherit;
		background-color: rgba(242, 242, 242, 1);
		box-sizing: border-box;
		border-width: 1px;
		border-style: solid;
		border-color: rgba(204, 204, 204, 1);
		border-radius: 0px;
		-moz-box-shadow: none;
		-webkit-box-shadow: none;
		box-shadow: none;
		line-height: 16px;
 	}
 	.tab-1 td:last-child{
 		width: 70%;
 	}
 	.tab-1 td:nth-child(1), .tab-1 td:nth-child(2), .tab-1 td:nth-child(3){
 		width: 10%;
 		text-align: center; 
		background: inherit;
	    background-color: inherit;
		box-sizing: border-box;
		border-width: 1px;
		border-style: solid;
		border-color: rgba(204, 204, 204, 1);
		border-radius: 0px;
		-moz-box-shadow: none;
		-webkit-box-shadow: none;
		box-shadow: none;
		font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
		font-weight: 700;
		font-style: normal;
		font-size: 20px;
		color: #0099FF;
		line-height: 16px;
		cursor: pointer;
 	}
 	#dataForm{
 		background-color: rgba(242, 242, 242, 1);
 	}
 	#searchbtn{
		height: 23px;
		overflow: hidden;
		width: 60px;
		line-height: 23px;
		cursor: pointer;
		position: relative;
		text-align: center;
		border: solid 1px #A3C0E8;
		color: #333333;
		background: #E0EDFF url(../images/controls/button-bg.gif) repeat-x center;
 	}
 	.p-title{
 		background-color: rgba(242, 242, 242, 1);
 		padding: 20px 0px 0px 20px;
 	}
 	.span1{
 		font-weight: 700;
		font-style: normal;
		font-size: 16px;
		text-align: left;
 	}
 	.span2{
 		color: rgba(0, 153, 255, 1);
 	}
 	.dataShow{
 		margin: 10px 20px; 
 		background-color: rgba(0, 153, 255, 1); 
 		height: 170px;
 	}
 	.div-tab{
 		padding-top: 20px;
 	}
 	.tab-2{
 		width: 100%;
 	}
 	.tab-2 td{
 		width: 10%;
 		text-align: center; 
		background: inherit;
	    background-color: inherit;
		box-sizing: border-box;
		-moz-box-shadow: none;
		-webkit-box-shadow: none;
		box-shadow: none;
		font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
		font-weight: 400;
		font-style: normal;
		font-size: 16px;
		color: white;
		line-height: 32px;
 	}
 	.tab-2 tr:nth-child(2) td{
 		font-size: 28px;
 	}
 	.span-title1{
 		font-size: 28px;
 		color: rgba(0, 153, 255, 1); 
 	}
 	.span-title2{
 		font-size: 16px;
 		color: rgba(0, 153, 255, 1);
 	}
 </style>
 <script type="text/javascript">
 
 	$(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135,
			cancelable : false
		});
		
		$("#searchbtn").click(function(){
			var statisticsBeginDate = $("#statisticsBeginDate").val();
			var statisticsEndDate = $("#statisticsEndDate").val();
			var comparisonBeginDate = $("#comparisonBeginDate").val();
			var comparisonEndDate = $("#comparisonEndDate").val();
			if(statisticsBeginDate == '' || statisticsEndDate == '' 
					|| comparisonBeginDate == '' || comparisonEndDate == '' ) {
				commUtil.alertError("日期不能为空！");				
			} else {
				$("#dataForm").submit();
			}
		});
		
	 });
 
 	
 	
 </script>
</head>
<body style="padding: 0px; overflow: hidden;" >
	<p class="p-title">
		<span class="span-title1" >历史概况</span>
		<span class="span-title2" >（${mchtInfoCustom.shopName }-${mchtInfoCustom.mchtCode }）</span>
	</p>
	<form id="dataForm" action="${pageContext.request.contextPath}/mchtPvStatistical/mchtPvStatisticalSumUp.shtml" method="post" >
		<div class="search-pannel" >
			<input type="hidden" name="mchtId" value="${mchtInfoCustom.id }" />
			<div class="search-tr"  > 
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">统计日：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="statisticsBeginDate" name="statisticsBeginDate" value="${statisticsBeginDate }" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="statisticsEndDate" name="statisticsEndDate" value="${statisticsEndDate }" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">对比日：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="comparisonBeginDate" name="comparisonBeginDate" value="${comparisonBeginDate }" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="comparisonEndDate" name="comparisonEndDate" value="${comparisonEndDate }" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	<p class="p-title">
		<span class="span1" >
			<span class="span2" >|</span>
			历史总览
		</span>
	</p>
	<div class="dataShow" >
		<div class="div-tab">
			<table class="tab-2">
				<tr>
					<td>访客数(会员)</td>
					<td>访客数(非会员)</td>
					<td>浏览量(会员)</td>
					<td>浏览量(非会员)</td>
					<td>支付金额</td>
					<td>支付母订单数</td>
					<td>支付商品销售</td>
					<td>支付买家数</td>
				</tr>
				<tr>
					<td>${mchtPvStatisticalSumUpMap.total_visitor_count_sum_a }</td>
					<td>${mchtPvStatisticalSumUpMap.total_visitor_count_tourist_sum_a }</td>
					<td>
						${mchtPvStatisticalSumUpMap.total_pv_count_sum_a }
						<span style="font-size: 14px;">（PV：${mchtPvStatisticalSumUpMap.pv }）</span>
					</td>
					<td>
						${mchtPvStatisticalSumUpMap.total_pv_count_tourist_sum_a }
						<span style="font-size: 14px;">（PV：${mchtPvStatisticalSumUpMap.pv_tourist }）</span>
					</td>
					<td>${mchtPvStatisticalSumUpMap.pay_amount_sum_a }</td>
					<td>${mchtPvStatisticalSumUpMap.pay_combine_order_count_sum_a }</td>
					<td>${mchtPvStatisticalSumUpMap.pay_product_count_sum_a }</td>
					<td>
						${mchtPvStatisticalSumUpMap.pay_member_count_sum_a }
						<span style="font-size: 14px;">（CR：${mchtPvStatisticalSumUpMap.cr }%）</span>
					</td>
				</tr>
				<tr>
					<td>
						增长率
						<c:if test="${mchtPvStatisticalSumUpMap.total_visitor_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${mchtPvStatisticalSumUpMap.total_visitor_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${mchtPvStatisticalSumUpMap.total_visitor_count_rate }%</span>
					</td>
					<td>
						增长率
						<c:if test="${mchtPvStatisticalSumUpMap.total_visitor_count_rate_tourist < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${mchtPvStatisticalSumUpMap.total_visitor_count_rate_tourist >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${mchtPvStatisticalSumUpMap.total_visitor_count_rate_tourist }%</span>
					</td>
					<td>
						增长率
						<c:if test="${mchtPvStatisticalSumUpMap.total_pv_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${mchtPvStatisticalSumUpMap.total_pv_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${mchtPvStatisticalSumUpMap.total_pv_count_rate }%</span>
					</td>
					<td>
						增长率
						<c:if test="${mchtPvStatisticalSumUpMap.total_pv_count_rate_tourist < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${mchtPvStatisticalSumUpMap.total_pv_count_rate_tourist >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${mchtPvStatisticalSumUpMap.total_pv_count_rate_tourist }%</span>
					</td>
					<td>
						增长率
						<c:if test="${mchtPvStatisticalSumUpMap.pay_amount_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${mchtPvStatisticalSumUpMap.pay_amount_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${mchtPvStatisticalSumUpMap.pay_amount_rate }%</span>
					</td>
					<td>
						增长率
						<c:if test="${mchtPvStatisticalSumUpMap.pay_combine_order_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${mchtPvStatisticalSumUpMap.pay_combine_order_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${mchtPvStatisticalSumUpMap.pay_combine_order_count_rate }%</span>
					</td>
					<td>
						增长率
						<c:if test="${mchtPvStatisticalSumUpMap.pay_product_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${mchtPvStatisticalSumUpMap.pay_product_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${mchtPvStatisticalSumUpMap.pay_product_count_rate }%</span>
					</td>
					<td>
						增长率
						<c:if test="${mchtPvStatisticalSumUpMap.pay_member_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${mchtPvStatisticalSumUpMap.pay_member_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${mchtPvStatisticalSumUpMap.pay_member_count_rate }%</span>
					</td>
				</tr>
				<tr>
					<td>
						对比日
						<span style="margin-left: 10px;">${mchtPvStatisticalSumUpMap.total_visitor_count_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${mchtPvStatisticalSumUpMap.total_visitor_count_tourist_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${mchtPvStatisticalSumUpMap.total_pv_count_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${mchtPvStatisticalSumUpMap.total_pv_count_tourist_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${mchtPvStatisticalSumUpMap.pay_amount_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${mchtPvStatisticalSumUpMap.pay_combine_order_count_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${mchtPvStatisticalSumUpMap.pay_product_count_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${mchtPvStatisticalSumUpMap.pay_member_count_sum_b }</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<p class="p-title">
		<span class="span1" >
			<span class="span2" >|</span>
			<span>主营类目：${mchtInfoCustom.productTypeName }</span>
			<span style="font-size: 14px; margin-left: 10px; color: #666666;">以下根据全店数据排名</span>
		</span>
	</p>
	<div class="dataShow" style="height: 130px;" >
		<div class="div-tab">
			<table class="tab-2">
				<tr>
					<td>支付金额店铺排名</td>
					<td>访客数店铺排名</td>
					<td>支付买家数店铺排名</td>
				</tr>
				<tr>
					<td style="color: #ff6666;" >
						<c:if test="${empty mchtPvPayAmountRankMap.rank }">0</c:if>
						<c:if test="${mchtPvPayAmountRankMap.rank > 100 }">100+</c:if>
						<c:if test="${mchtPvPayAmountRankMap.rank <= 100 }">
							<fmt:formatNumber value="${mchtPvPayAmountRankMap.rank }" pattern="#" maxFractionDigits="0"/> 
						</c:if>
					</td>
					<td style="color: #ff9966;" >
						<c:if test="${empty mchtPvTotalVisitorCountRankMap.rank }">0</c:if>
						<c:if test="${mchtPvTotalVisitorCountRankMap.rank > 100 }">100+</c:if>
						<c:if test="${mchtPvTotalVisitorCountRankMap.rank <= 100 }">
							<fmt:formatNumber value="${mchtPvTotalVisitorCountRankMap.rank }" pattern="#" maxFractionDigits="0"/>
						</c:if>
					</td>
					<td style="color: #66cc66;" >
						<c:if test="${empty mchtPvPayMemberCountRankMap.rank }">0</c:if>
						<c:if test="${mchtPvPayMemberCountRankMap.rank > 100 }">100+</c:if>
						<c:if test="${mchtPvPayMemberCountRankMap.rank <= 100 }">
							<fmt:formatNumber value="${mchtPvPayMemberCountRankMap.rank }" pattern="#" maxFractionDigits="0"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>
						<span>行业TOP100平均</span>
						<span style="margin-left: 10px; color: #ff6666;" >${mchtPvPayAmountAvgMap.pay_amount_avg }元</span>
					</td>
					<td>
						<span>行业TOP100平均</span>
						<span style="margin-left: 10px; color: #ff9966;" >${mchtPvTotalVisitorCountAvgMap.total_visitor_count_avg }人</span>
					</td>
					<td>
						<span>行业TOP100平均</span>
						<span style="margin-left: 10px; color: #66cc66;" >${mchtPvPayMemberCountAvgMap.pay_member_count_avg }人</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
