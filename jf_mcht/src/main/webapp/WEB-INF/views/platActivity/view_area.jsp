<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>查看会场详情</title>
</head>

<style type="text/css">
	.preCss{
		white-space: pre-wrap;
		word-wrap: break-word;
		margin: 0 0px;
		border: 0px none white;
		background-color: white;
		padding: 0 0px;
	}
</style>

<body>
<!--查看会场详情 -->
<div class="modal-dialog dd-xq" role="document" style="width:1000px;">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<span class="modal-title" id="exampleModalLabel">查看会场详情</span>
		</div>
		<div class="modal-body">
			<div class="row">
				<div class="col-md-12 datatable-container at-table">
					<table class="table table-striped table-bordered dataTable no-footer"
						   role="grid" aria-describedby="datatable_info">
						<tbody>
						<tr>
							<td style="width: 80px;">会场名称</td>
							<td style="text-align:left;">${activityArea.name}</td>
						</tr>
						<tr>
							<td>活动类型</td>
							<td style="text-align:left;"><c:if test="${activityArea.type eq '1'}">品牌</c:if><c:if test="${activityArea.type eq '2'}">单品</c:if><c:if test="${activityArea.type eq '3'}">推广会场</c:if></td>
						</tr>
						<tr>
							<td>报名时间</td>
							<td style="text-align:left;">
								<fmt:formatDate value='${activityArea.enrollBeginTime}' pattern='yyyy-MM-dd HH:mm:ss'/> 到 <fmt:formatDate value='${activityArea.enrollEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
							</td>
						</tr>
						<tr>
							<td>活动时间</td>
							<td style="text-align:left;">
								<fmt:formatDate value='${activityArea.activityBeginTime}' pattern='yyyy-MM-dd HH:mm:ss'/> 到 <fmt:formatDate value='${activityArea.activityEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
							</td>
						</tr>
						<tr>
							<td>利益点</td>
							<td style="text-align:left;">
								<c:if test="${not empty activityArea.benefitPoint}">
									${activityArea.benefitPoint}<c:if test="${activityArea.preferentialType==1}"></c:if>
								</c:if>
							</td>
						</tr>

						<%--促销方式的选择展示--%>
						<c:if test="${mchtInfo.mchtType !='1' or mchtInfo.isManageSelf == '1'}">
							<tr>
								<td>促销方式</td>
								<c:if test="${activityArea.isPreSell eq '1'}">
									<td style="text-align:left;">预付会场</td>
								</c:if>
								<c:if test="${activityArea.isPreSell ne '1'}">
									<td style="text-align:left;">${preferentialTypeDesc}
										<c:if test="${belone == '1'  }">
											<span style="color: red;">（说明：本优惠为跨店优惠，优惠金额按商品金额均分后由平台承担）</span>
										</c:if>
										<c:if test="${belone == '2'  }">
											<span style="color: red;">（说明：本优惠为跨店优惠，优惠金额按商品金额均分后由商家承担）</span>
										</c:if>
										<c:if test="${activityArea.type == '3'}">
											<span style="color: red;">（本推广会场的优惠金额按商品金额均分后由商家承担）</span>
										</c:if>
									</td>
								</c:if>
							</tr>
						</c:if>


						<c:if test="${not empty activityArea.preferentialType && activityArea.preferentialType ne '0'}">
							<tr>
								<td>促销详情</td>
								<td style="text-align: left;">
									<c:if test="${not empty couponList}">
										<c:forEach var="coupon" items="${couponList}" varStatus="vs" >
											面额${coupon.money}元：使用条件${coupon.minimum}元，发行量${coupon.grantQuantity}张;&nbsp;&nbsp;
										</c:forEach>
									</c:if>
									<c:if test="${not empty fullCut}">
										<c:if test="${fullCut.ladderFlag==0}"> 非阶梯</c:if>
										<c:if test="${fullCut.ladderFlag==1}"> 阶梯</c:if>
										<c:if test="${not empty fullCut && fullCut.ladderFlag==0}">
											满${fullCut.get("minimum")}元&nbsp;&nbsp;减${fullCut.get("money")}元<c:if test="${fullCut.sumFlag==1}">(累加)</c:if>
										</c:if>
										<c:if test="${not empty fullCut && fullCut.ladderFlag==1 &&  fullCut.get('ruleList').size()>0}">
											<c:forEach var="rule" items="${fullCut.get('ruleList')}" varStatus="vs" >
												满${rule.minimum}元&nbsp;&nbsp;减${rule.money}元&nbsp;&nbsp;
											</c:forEach>
										</c:if>
									</c:if>
									<c:if test="${not empty allowanceInfo}">
										<c:if test="${not empty allowanceInfo && allowanceInfo.ladderFlag==0}">
											每满${allowanceInfo.get("minimum")}元&nbsp;&nbsp;减${allowanceInfo.get("money")}元,上不封顶
										</c:if>
									</c:if>
									<c:if test="${not empty fullGive}">
										<c:if test="${fullGive.type==1}">
											满额赠：满 ${fullGive.minimum}元 <c:if test="${fullGive.sumFlag==1}">(累加)</c:if>&nbsp;&nbsp;
											${fullGive.get('fullGiveDesc')}
										</c:if>
										<c:if test="${fullGive.type==2}">
											买即赠：${fullGive.get('fullGiveDesc')}
										</c:if>
									</c:if>
									<c:if test="${not empty fullDiscount && fullDiscount.get('ruleList').size()>0}">
										<c:if test="${fullDiscount.type == 1}">
											<c:forEach var="rule" items="${fullDiscount.get('ruleList')}" varStatus="vs" >
												满${rule.minimum}件减${rule.money}件&nbsp;&nbsp;
											</c:forEach>
										</c:if>
										<c:if test="${fullDiscount.type == 2}">
											<c:forEach var="rule" items="${fullDiscount.get('ruleList')}" varStatus="vs" >
												${rule.money}元任选${rule.minimum}件&nbsp;&nbsp;
											</c:forEach>
										</c:if>
										<c:if test="${fullDiscount.type == 3}">
											<c:forEach var="rule" items="${fullDiscount.get('ruleList')}" varStatus="vs" >
												${rule.minimum}件${rule.money}折&nbsp;&nbsp;
											</c:forEach>
										</c:if>
										<c:if test="${fullDiscount.type == 4}">
											<c:forEach var="rule" items="${fullDiscount.get('ruleList')}" varStatus="vs" >
												第${rule.minimum}件${rule.money}折&nbsp;&nbsp;
											</c:forEach>
										</c:if>
									</c:if>

								</td>
							</tr>
						</c:if>
						<tr>
							<td>活动描述</td>
							<td style="text-align:left;" id="description">
								<pre class="preCss">${activityArea.description}</pre>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>

</body>
</html>
