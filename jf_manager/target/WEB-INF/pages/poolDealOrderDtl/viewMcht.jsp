<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>
<html>
<body>
	<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/payToMchtLog/updateConfirmStatus.shtml">
		<input type="hidden" name="ids" id="ids" value="${ids}">
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">类型</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					自营SPOP
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">经营品类</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					${productTypeNameDesc}
	    		</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">授权品牌</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					${productBrandNameDesc}
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">该品牌定价方式</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					${priceModelDesc}
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">详细说明</td>
				<td colspan="3" align="left" class="l-table-edit-td" style="height: 100px;">
					
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
