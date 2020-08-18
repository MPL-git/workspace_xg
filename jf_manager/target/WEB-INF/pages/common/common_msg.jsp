<%@ page language="java" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<html>
	<head>
		<title>管理平台</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	</head>
	<body>
		
		<c:if test="${RESULT_CODE!=null &&RESULT_CODE!='' &&RESULT_CODE!=0}" >	
		 <p> <font color="red" >${RESULT_MESSAGE} </font></p>
		
		</c:if>
		
		<c:if test="${RESULT_CODE!=null &&RESULT_CODE!='' &&RESULT_CODE==0}" >	
		<%--  <p> <font > <c:if test="${RESULT_MESSAGE==null ||RESULT_MESSAGE=='' }"> 操作成功</c:if>${RESULT_MESSAGE} </font></p> --%>
		 <p> <font  >操作成功 </font></p>
		</c:if>
	</body>
</html>
