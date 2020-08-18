<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>问题指引</title>
</head>

<body>
<!--问题指引弹框 -->
<div class="modal-dialog" role="document" style="width:800px;">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<span class="modal-title" id="exampleModalLabel">问题指引</span>
		</div>
		<div class="modal-body">
			<c:forEach var="problemGuideline" items="${problemGuidelineList}">
				<p style="margin: 5px 20px;">${problemGuideline.problem}</p>
				<p style="margin: 5px 20px 15px 30px;color: #999;">${problemGuideline.answer}</p>
			</c:forEach>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<!-- Bootstrap -->

</body>
</html>
