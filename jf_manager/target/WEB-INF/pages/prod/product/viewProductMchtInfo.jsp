<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}
.table-title{
font-size: 17px;font-weight: 600;
}
</style>
<html>
<body>
		<table class="gridtable">
			<tr>

				<td class="title" width="150px;">类型</td>
				<td align="left" class="l-table-edit-td">${mchtInfoCustom.mchtTypeDesc}</td>
			</tr>
			
			<tr>
				<td class="title">经营品类</td>
				<td align="left" class="l-table-edit-td">${productTypeStr}</td>
			</tr>
			
			<tr>
				<td class="title">授权品牌</td>
				<td align="left" class="l-table-edit-td">${productBrandStr}</td>
			</tr>
			
			<c:if test="${mchtInfoCustom.mchtType==1}">
			<%-- <tr>
				<td class="title">${productCustom.productBrandName}定价方式</td>
				<td align="left" class="l-table-edit-td">${productCustom.priceModel}</td>
			</tr> --%>
			
			<tr>
				<td class="title">详细说明</td>
				<td align="left" class="l-table-edit-td">
				  <textarea style="width: 60%;height:110px;" readonly="readonly">${productCustom.priceModelDesc}</textarea>
				</td>
			</tr>
			</c:if>
			
			<c:if test="${mchtInfoCustom.mchtType==2}">
			<tr>
				<td class="title">${productCustom.productBrandName}佣金</td>
				<td align="left" class="l-table-edit-td"><fmt:formatNumber value="${productCustom.popCommissionRate}" pattern="#.####"/></td>
			</tr>
			</c:if>
		</table>	
</body>
</html>
