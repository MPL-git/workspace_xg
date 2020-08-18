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
			<div class="search-tr" id="decorateModuleDiv">
			<c:forEach items="${decorateAreaCustoms}" var="decorateAreaCustom">
				<c:forEach items="${decorateAreaCustom.decorateModuleCustoms}" var="decorateModuleCustom">
					<c:if test="${decorateModuleCustom.moduleType == 1 || decorateModuleCustom.moduleType == 9}">
						<div style="position: relative;width: 750px;">
							<img src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.pic}" style="width: 750px;">
						</div>
						<br>
					</c:if>
				</c:forEach>
			</c:forEach>
			</div>
		</div>
	</form>
</body>