<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<html>
<body>
	<div class="title-top">
		<input type="hidden" id="appealOrderId" value="${appealOrder.id}">
		<div class="table-title">
				<span class="marR10">
					大类：
				</span>
		</div>
		<table class="gridtable baseInfo" style="width: 20%;text-align: center;">
			<tr><td>类型</td></tr>
			<c:forEach items="${txnTypeList}" var="txnType">
	          	<tr>
	          		<td>${txnType.statusDesc}</td>
	          	</tr>
			</c:forEach>
        </table>
	</div>
	
		<div class="title-top">
			<div class="table-title">
				<span class="marR10">
					子类：
				</span>
			</div>
			<table class="gridtable baseInfo" style="width: 20%;text-align: center;">
	          	<tr>
	          		<td>类型</td>
	          		<td>子类</td>
	          	</tr>
	          	<c:forEach items="${AList}" var="typeSub">
		          	<tr>
		          		<td>${typeSub.statusValue}</td>
		          		<td colspan="4" style="text-align: left;">${typeSub.statusDesc}</td>
	          		</tr>
	          	</c:forEach>
	          	<c:forEach items="${BList}" var="typeSub">
		          	<tr>
		          		<td>${typeSub.statusValue}</td>
		          		<td colspan="4" style="text-align: left;">${typeSub.statusDesc}</td>
	          		</tr>
	          	</c:forEach>
	          	<c:forEach items="${CList}" var="typeSub">
		          	<tr>
		          		<td>${typeSub.statusValue}</td>
		          		<td colspan="4" style="text-align: left;">${typeSub.statusDesc}</td>
	          		</tr>
	          	</c:forEach>
	          	<c:forEach items="${DList}" var="typeSub">
		          	<tr>
		          		<td>${typeSub.statusValue}</td>
		          		<td colspan="4" style="text-align: left;">${typeSub.statusDesc}</td>
	          		</tr>
	          	</c:forEach>
	          	<c:forEach items="${EList}" var="typeSub">
		          	<tr>
		          		<td>${typeSub.statusValue}</td>
		          		<td colspan="4" style="text-align: left;">${typeSub.statusDesc}</td>
	          		</tr>
	          	</c:forEach>
	          	<c:forEach items="${FList}" var="typeSub">
		          	<tr>
		          		<td>${typeSub.statusValue}</td>
		          		<td colspan="4" style="text-align: left;">${typeSub.statusDesc}</td>
	          		</tr>
	          	</c:forEach>
	          	
	        </table>
	</div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
