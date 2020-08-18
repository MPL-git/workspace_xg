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
		background: #E0EDFF repeat-x center;
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
 	#dataShow{
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
 		width: 6%;
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
		
		/*$(".tab-1 td:nth-child(1)").css("background-color", "rgba(255, 255, 255, 1)");
		
		$(".tab-1 td:nth-child(2)").click(function(){
			window.location.href = "${pageContext.request.contextPath}/platformPvStatistical/platformPvStatisticalMSA.shtml";
		});
		
		$(".tab-1 td:nth-child(3)").click(function(){
			window.location.href = "${pageContext.request.contextPath}/platformPvStatistical/platformPvStatisticalProduct.shtml";
		});*/
		
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
	<%--<table class="tab-1">
		<tr>
			<td>历史概况</td>
			<td>历史页面分析</td>
			<td>历史商品概括</td>
			<td></td>
		</tr>
	</table>--%>
	<p class="p-title">
		<span class="span1" >
			<span class="span2" >|</span>
			历史总览
		</span>
	</p>
	<form id="dataForm" action="${pageContext.request.contextPath}/platformPvStatistical/platformPvStatisticalSumUp.shtml" method="post" >
		<div class="search-pannel" >
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >
						终端：
					</div>
					<div class="search-td-combobox-condition" >
						<select id="clientSource" name="clientSource" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="clientSourceObj" items="${clientSourceList }" >
								<option value="${clientSourceObj.statusValue }" <c:if test="${clientSourceObj.statusValue == clientSource }">selected="selected"</c:if> >
									${clientSourceObj.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
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
			</div>
			<div class="search-tr"  >
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
	
	<div id="dataShow" >
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
					<td>${platformPvStatisticalMap.total_visitor_count_sum_a }</td>
					<td>${platformPvStatisticalMap.total_visitor_count_tourist_sum_a }</td>
					<td>
						${platformPvStatisticalMap.total_pv_count_sum_a }
						<span style="font-size: 14px;">（PV：${platformPvStatisticalMap.pv }）</span>
					</td>
					<td>
						${platformPvStatisticalMap.total_pv_count_tourist_sum_a }
						<span style="font-size: 14px;">（PV：${platformPvStatisticalMap.pv_tourist }）</span>
					</td>
					<td>${platformPvStatisticalMap.pay_amount_sum_a }</td>
					<td>${platformPvStatisticalMap.pay_combine_order_count_sum_a }</td>
					<td>${platformPvStatisticalMap.pay_product_count_sum_a }</td>
					<td>
						${platformPvStatisticalMap.pay_member_count_sum_a }
						<span style="font-size: 14px;">（CR：${platformPvStatisticalMap.cr }%）</span>
					</td>
				</tr>
				<tr>
					<td>
						增长率
						<c:if test="${platformPvStatisticalMap.total_visitor_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${platformPvStatisticalMap.total_visitor_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${platformPvStatisticalMap.total_visitor_count_rate }%</span>
					</td>
					<td>
						增长率
						<c:if test="${platformPvStatisticalMap.total_visitor_count_rate_tourist < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${platformPvStatisticalMap.total_visitor_count_rate_tourist >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${platformPvStatisticalMap.total_visitor_count_rate_tourist }%</span>
					</td>
					<td>
						增长率
						<c:if test="${platformPvStatisticalMap.total_pv_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${platformPvStatisticalMap.total_pv_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${platformPvStatisticalMap.total_pv_count_rate }%</span>
					</td>
					<td>
						增长率
						<c:if test="${platformPvStatisticalMap.total_pv_count_rate_tourist < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${platformPvStatisticalMap.total_pv_count_rate_tourist >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${platformPvStatisticalMap.total_pv_count_rate_tourist }%</span>
					</td>
					<td>
						增长率
						<c:if test="${platformPvStatisticalMap.pay_amount_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${platformPvStatisticalMap.pay_amount_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${platformPvStatisticalMap.pay_amount_rate }%</span>
					</td>
					<td>
						增长率
						<c:if test="${platformPvStatisticalMap.pay_combine_order_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${platformPvStatisticalMap.pay_combine_order_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${platformPvStatisticalMap.pay_combine_order_count_rate }%</span>
					</td>
					<td>
						增长率
						<c:if test="${platformPvStatisticalMap.pay_product_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${platformPvStatisticalMap.pay_product_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${platformPvStatisticalMap.pay_product_count_rate }%</span>
					</td>
					<td>
						增长率
						<c:if test="${platformPvStatisticalMap.pay_member_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${platformPvStatisticalMap.pay_member_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${platformPvStatisticalMap.pay_member_count_rate }%</span>
					</td>
				</tr>
				<tr>
					<td>
						对比日
						<span style="margin-left: 10px;">${platformPvStatisticalMap.total_visitor_count_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${platformPvStatisticalMap.total_visitor_count_tourist_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${platformPvStatisticalMap.total_pv_count_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${platformPvStatisticalMap.total_pv_count_tourist_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${platformPvStatisticalMap.pay_amount_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${platformPvStatisticalMap.pay_combine_order_count_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${platformPvStatisticalMap.pay_product_count_sum_b }</span>
					</td>
					<td>
						对比日
						<span style="margin-left: 10px;">${platformPvStatisticalMap.pay_member_count_sum_b }</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
