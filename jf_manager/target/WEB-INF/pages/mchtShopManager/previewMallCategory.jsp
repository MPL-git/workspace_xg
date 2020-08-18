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
			<c:forEach items="${decorateAreaCustoms}" var="decorateAreaCustom">
				<c:forEach items="${decorateAreaCustom.decorateModuleCustoms}" var="decorateModuleCustom">
					<c:if test="${decorateModuleCustom.moduleType == 1}">
						<div style="position: relative;width: 750px;">
							<img src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.pic}" style="width: 750px;">
						</div>
						<br>
					</c:if>
					<c:if test="${decorateModuleCustom.moduleType == 4}">
						<table class="gridtable" style="border: solid;">
							<c:forEach var="productCustom" items="${decorateModuleCustom.productCustoms}" varStatus="status">
							<c:if test="${(status.index+1)%2==1}">
								<tr>				
							</c:if>
							<td class="l-grid-row-cell">
								<div class="l-grid-row-cell-inner">
									<span style="display:block;text-align:left;margin-top:8px;">
										<img style="margin:10px;" src="${pageContext.request.contextPath}/file_servelt${productCustom.pic}" width="100" height="100" onclick="viewerPic(this.src)"><br>
										${productCustom.name}<br>
										价格：<c:if test="${productCustom.salePriceMin != productCustom.salePriceMax}">
												${productCustom.salePriceMin}-${productCustom.salePriceMax}
											</c:if>
											<c:if test="${productCustom.salePriceMin == productCustom.salePriceMax}">
												${productCustom.salePriceMin}
											</c:if>
										元<br>
										折扣：<c:if test="${productCustom.discountMin != productCustom.discountMax}">
												${productCustom.discountMin}-${productCustom.discountMax}
											</c:if>
											<c:if test="${productCustom.discountMin == productCustom.discountMax}">
												${productCustom.discountMax}
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
				</c:forEach>
			</c:forEach>
			</div>
			<div class="search-tr">
			<table class="gridtable" style="border: solid;">
				<c:forEach var="productCustom" items="${productCustoms}" varStatus="status">
					<c:if test="${(status.index+1)%2==1}">
						<tr>				
					</c:if>
						<td class="l-grid-row-cell">
							<div class="l-grid-row-cell-inner">
								<span style="display:block;text-align:left;margin-top:8px;">
									<img style="margin:10px;" src="${pageContext.request.contextPath}/file_servelt${productCustom.pic}" width="100" height="100" onclick="viewerPic(this.src)"><br>
									${productCustom.name}<br>
									价格：<c:if test="${productCustom.salePriceMin != productCustom.salePriceMax}">
											${productCustom.salePriceMin}-${productCustom.salePriceMax}
										</c:if>
										<c:if test="${productCustom.salePriceMin == productCustom.salePriceMax}">
											${productCustom.salePriceMin}
										</c:if>
									元<br>
									折扣：<c:if test="${productCustom.discountMin != productCustom.discountMax}">
											${productCustom.discountMin}-${productCustom.discountMax}
										</c:if>
										<c:if test="${productCustom.discountMin == productCustom.discountMax}">
											${productCustom.discountMax}
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
			</div>			
		</div>
	</form>
</body>