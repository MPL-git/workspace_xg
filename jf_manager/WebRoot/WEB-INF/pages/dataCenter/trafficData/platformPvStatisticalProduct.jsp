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
 	#dataForm, #dataForm1{
 		background-color: rgba(242, 242, 242, 1);
 	}
 	#searchbtn1, #searchbtn{
		height: 23px;
		overflow: hidden;
		width: 60px;
		line-height: 23px;
		cursor: pointer;
		position: relative;
		text-align: center;
		border: solid 1px #A3C0E8;
		color: #333333;
		background: #E0EDFF;
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
 		height: 260px;
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
		
		/*$(".tab-1 td:nth-child(3)").css("background-color", "rgba(255, 255, 255, 1)");
		
		$(".tab-1 td:nth-child(1)").click(function(){
			window.location.href = "${pageContext.request.contextPath}/platformPvStatistical/platformPvStatisticalSumUp.shtml";
		});
		
		$(".tab-1 td:nth-child(2)").click(function(){
			window.location.href = "${pageContext.request.contextPath}/platformPvStatistical/platformPvStatisticalMSA.shtml";
		});*/
		
		$("#searchbtn1").click(function(){
			var statisticsBeginDate = $("#statisticsBeginDate").val();
			var statisticsEndDate = $("#statisticsEndDate").val();
			var comparisonBeginDate = $("#comparisonBeginDate").val();
			var comparisonEndDate = $("#comparisonEndDate").val();
			if(statisticsBeginDate == '' || statisticsEndDate == '' 
					|| comparisonBeginDate == '' || comparisonEndDate == '' ) {
				commUtil.alertError("日期不能为空！");				
			} else {
				$("#dataForm1").submit();
			}
		});
		
	 });
 
 	 var listConfig={ 
		      url: "/platformPvStatistical/platformPvStatisticalProductList.shtml",
		      btnItems: [],
		      listGrid: { columns: [
						{display:'商品名称',name:'', align:'center', isSort:false, width:300, render:function(rowdata, rowindex) {
							var html = [];
							html.push("<div style='overflow:hidden;'><img style='float:left;margin: 5px;' src='${pageContext.request.contextPath}/file_servelt/"+rowdata.product_pic+"' width='80' height='80' >");
							html.push("<p style='padding-top: 5px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;width: calc(100%-100px);' >"+rowdata.product_name+"</p>");
							html.push("<p>商品ID:"+rowdata.product_code+"</p>");
							html.push("<div style='margin-top: 15px;'><p style='color: #21a5fa;'>类目："+rowdata.product_type_name+"</p></div></div>");
							return html.join("");
                        }},      
						{display:'商品访客数(会员)',name:'total_visitor_count', align:'center', isSort:false, width:120},
						{display:'商品访客数(非会员)',name:'total_visitor_count_tourist', align:'center', isSort:false, width:120},
						{display:'商品浏览量(会员)',name:'total_pv_count', align:'center', isSort:false, width:120},
						{display:'商品浏览量(非会员)',name:'total_pv_count_tourist', align:'center', isSort:false, width:120},
						{display:'商品收藏人数',name:'member_remind_count', align:'center', isSort:false, width:120},
						{display:'加购件数',name:'shopping_cart_quantity_sum', align:'center', isSort:false, width:120},
						{display:'提交订单件数',name:'order_dtl_quantity_sum', align:'center', isSort:false, width:120},
						{display:'支付件数',name:'pay_product_sum', align:'center', isSort:false, width:120},
						{display:'支付金额',name:'pay_amount_sum', align:'center', isSort:false, width:120}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
		      }, 							
		      container: {
		        toolBarName: "toptoolbar",
		        searchBtnName: "searchbtn",
		        fromName: "dataForm",
		        listGridName: "maingrid"
		      },
		      pageSize: 10
		  };
 	
 	
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
			商品信息总况
		</span>
	</p>
	<form id="dataForm1" action="${pageContext.request.contextPath}/platformPvStatistical/platformPvStatisticalProduct.shtml" method="post" >
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
					<div id="searchbtn1" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="dataShow" >
		<div class="div-tab">
			<table class="tab-2">
				<tr>
					<td style="font-size: 28px;" rowspan="3" >流量相关</td>
					<td>商品访客数(会员)</td>
					<td>商品访客数(非会员)</td>
					<td>商品浏览量(会员)</td>
					<td>商品浏览量(非会员)</td>
					<td>被访问商品数</td>
				</tr>
				<tr>
					<td>${flowProductPvMap.total_visitor_count_a }</td>
					<td>${flowProductPvMap.total_visitor_count_tourist_a }</td>
					<td>${flowProductPvMap.total_pv_count_a }</td>
					<td>${flowProductPvMap.total_pv_count_tourist_a }</td>
					<td>${flowProductPvMap.total_product_count_a }</td>
				</tr>
				<tr>
					<td>
						较对比日
						<c:if test="${flowProductPvMap.total_visitor_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${flowProductPvMap.total_visitor_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${flowProductPvMap.total_visitor_count_rate }%</span>
					</td>
					<td>
						较对比日
						<c:if test="${flowProductPvMap.total_visitor_count_tourist_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${flowProductPvMap.total_visitor_count_tourist_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${flowProductPvMap.total_visitor_count_tourist_rate }%</span>
					</td>
					<td>
						较对比日
						<c:if test="${flowProductPvMap.total_pv_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${flowProductPvMap.total_pv_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${flowProductPvMap.total_pv_count_rate }%</span>
					</td>
					<td>
						较对比日
						<c:if test="${flowProductPvMap.total_pv_count_tourist_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${flowProductPvMap.total_pv_count_tourist_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${flowProductPvMap.total_pv_count_tourist_rate }%</span>
					</td>
					<td>
						较对比日
						<c:if test="${flowProductPvMap.total_product_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${flowProductPvMap.total_product_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${flowProductPvMap.total_product_count_rate }%</span>
					</td>
				</tr>
			</table>
		</div>
		<div class="div-tab">
			<table class="tab-2">
				<tr>
					<td style="font-size: 28px;" rowspan="3" >商品相关</td>
					<td>商品收藏人数</td>
					<td>加购件数</td>
					<td>提交订单件数</td>
					<td>支付件数</td>
					<td></td>
				</tr>
				<tr>
					<td>${productPvListMap.member_remind_count_a }</td>
					<td>${productPvListMap.shopping_cart_quantity_sum_a }</td>
					<td>${productPvListMap.order_dtl_quantity_sum_a }</td>
					<td>${productPvListMap.pay_product_sum_a }</td>
					<td></td>
				</tr>
				<tr>
					<td>
						较对比日
						<c:if test="${productPvListMap.member_remind_count_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${productPvListMap.member_remind_count_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${productPvListMap.member_remind_count_rate }%</span>
					</td>
					<td>
						较对比日
						<c:if test="${productPvListMap.shopping_cart_quantity_sum_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${productPvListMap.shopping_cart_quantity_sum_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${productPvListMap.shopping_cart_quantity_sum_rate }%</span>
					</td>
					<td>
						较对比日
						<c:if test="${productPvListMap.order_dtl_quantity_sum_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${productPvListMap.order_dtl_quantity_sum_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${productPvListMap.order_dtl_quantity_sum_rate }%</span>
					</td>
					<td>
						较对比日
						<c:if test="${productPvListMap.pay_product_sum_rate < 0 }">
							<span style="margin-left: 10px; color: rgb(51, 204, 204);">
						</c:if>
						<c:if test="${productPvListMap.pay_product_sum_rate >= 0 }">
							<span style="margin-left: 10px; ">
						</c:if>
						${productPvListMap.pay_product_sum_rate }%</span>
					</td>
					<td></td>
				</tr>
			</table>
		</div>
	</div>
	<p class="p-title">
		<span class="span1" >
			<span class="span2" >|</span>
			商品效果明细
		</span>
	</p>
	<form id="dataForm" runat="server" >
		<div class="search-pannel" >
			<input type="hidden" name="clientSource" value="${clientSource }" />
			<input type="hidden" name="statisticsBeginDate" value="${statisticsBeginDate }" />
			<input type="hidden" name="statisticsEndDate" value="${statisticsEndDate }" />
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >
						销售类型：
					</div>
					<div class="search-td-combobox-condition" >
						<select id="saleType" name="saleType" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1" >品牌款</option>
							<option value="2" >单品款</option>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >
						商品名称：
					</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productName" name="productName" >
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >
						商品ID：
					</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productCode" name="productCode" >
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >
						商品类目：
					</div>
					<div class="search-td-combobox-condition" >
						<select id="productTypeId" name="productTypeId" style="width: 135px;" >
							<option value="">全部类目</option>
							<c:forEach var="productType" items="${productTypeList }">
								<option value="${productType.id }">
									${productType.name }
								</option>
							</c:forEach>
						</select>
				 	</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	<div id="maingrid" style="margin: 0; padding: 0"></div>
	
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
