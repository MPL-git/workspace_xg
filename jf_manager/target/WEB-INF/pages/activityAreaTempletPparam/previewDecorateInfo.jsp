<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
</head>
<body style="padding: 0px;">
	<form id="dataForm" runat="server">
		<input type="hidden" id="decorateAreaId" name="decorateAreaId" value="${decorateAreaId}">
		<div class="search-pannel" style="overflow-y: auto;">
			<div class="search-tr">
			<c:if test="${not empty topPic}">
				<div style="position: relative;width: 750px;height: 500px;">
					<img src="${pageContext.request.contextPath}/file_servelt${topPic}" style="width: 750px;">
				</div>
				<br>
			</c:if>
			<c:forEach items="${decorateAreaCustoms}" var="decorateAreaCustom">
				<c:forEach items="${decorateAreaCustom.decorateModuleCustoms}" var="decorateModuleCustom">
					<c:if test="${decorateModuleCustom.moduleType == 1}">
						<div style="position: relative;width: 750px;">
							<img src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.pic}" style="width: 750px;">
						</div>
						<br>
					</c:if>
					<c:if test="${decorateModuleCustom.moduleType == 2}">
						<table class="gridtable" style="border: solid;">
							<c:forEach var="moduleItemCustom" items="${decorateModuleCustom.moduleItemCustoms}" varStatus="status" begin="0" end="${decorateModuleCustom.showNum-1}">
							<c:if test="${(status.index+1)%2==1}">
								<tr>				
							</c:if>
							<td class="l-grid-row-cell ">
								<div class="l-grid-row-cell-inner">
									<span style="display:block;text-align:left;margin-top:8px;">
										<img style="margin:10px;" src="${pageContext.request.contextPath}/file_servelt${moduleItemCustom.pic}" width="100" height="100" onclick="viewerPic(this.src)"><br>
										${moduleItemCustom.productName}<br>
										价格：<c:if test="${moduleItemCustom.salePriceMin != moduleItemCustom.salePriceMax}">
												${moduleItemCustom.salePriceMin}-${moduleItemCustom.salePriceMax}
											</c:if>
											<c:if test="${moduleItemCustom.salePriceMin == moduleItemCustom.salePriceMax}">
												${moduleItemCustom.salePriceMin}
											</c:if>
										元<br>
										折扣：<c:if test="${moduleItemCustom.discountMin != moduleItemCustom.discountMax}">
												${moduleItemCustom.discountMin}-${moduleItemCustom.discountMax}
											</c:if>
											<c:if test="${moduleItemCustom.discountMin == moduleItemCustom.discountMax}">
												${moduleItemCustom.discountMax}
											</c:if>
										折<br>
									</span>
								</div>
							</td>
							<c:if test="${(status.index+1)%2==0}">
								</tr>
							</c:if>
							</c:forEach>
						</table>	
						<br>
					</c:if>
					<c:if test="${decorateModuleCustom.moduleType == 3}">
						<div style="position: relative;width: 750px;">
							<c:forEach items="${decorateModuleCustom.activityCustoms}" var="activityCustom" begin="0" end="${decorateModuleCustom.showNum-1}">
								<img src="${pageContext.request.contextPath}/file_servelt${activityCustom.entryPic}" style="width: 750px;">
								<br>
								<span>${activityCustom.name}</span>
								<br>
								<span>${activityCustom.benefitPoint}</span>
								<br>
							</c:forEach>
						</div>
						<br>
					</c:if>
				</c:forEach>
			</c:forEach>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>