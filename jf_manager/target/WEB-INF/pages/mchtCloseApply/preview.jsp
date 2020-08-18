<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>



<style type="text/css">
	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
	.radioClass{margin-right: 20px;}
	.hidden{display:none;}
	.l-text-wrapper{ display:inline-block; }
</style>

<script type="text/javascript">

	$(function() {

		$(".ligerDate").ligerDateEditor( {
			showTime : false,
			labelWidth : 160,
			width:160,
			labelAlign : 'left'
		});

	});

</script>

<html>
<body>
<form method="post" id="form1" name="form1">
	<input type="hidden" name="model.id" value="${model.id}">
	<table class="gridtable">
		<tr>
			<td  class="title">暂停日期</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" class="ligerDate" name="model.stopBeginDate" value="<fmt:formatDate value='${model.stopBeginDate}' pattern='yyyy-MM-dd'/>" validate="{required:true}"/>
				${model.get("stopBeginStatusStr")}
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至
				<input type="text" class="ligerDate" style="float:left;" name="model.stopEndDate" value="<fmt:formatDate value='${model.stopEndDate}' pattern='yyyy-MM-dd'/>" />
				${model.get("stopEndStatusStr")}
			</td>
		</tr>
		<tr>
			<td  class="title">是否关店</td>
			<td align="left" class="l-table-edit-td">
				<input type="checkbox" class="liger-checkbox" value="1" readonly="readonly" <c:if test="${model.closeFlag==1}">checked="checked"</c:if> />是
			</td>
		</tr>
		<tr>
			<td  class="title">关店日期</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" class="ligerDate" name="model.closeBeginDate" value="<fmt:formatDate value='${model.closeBeginDate}' pattern='yyyy-MM-dd'/>" />
				${model.get("closeBeginStatusStr")}
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至
				<input type="text" class="ligerDate" name="model.closeEndDate" value="<fmt:formatDate value='${model.closeEndDate}' pattern='yyyy-MM-dd'/>" />
				${model.get("closeEndStatusStr")}
			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">申请理由</td>
			<td colspan="3">
				<textarea rows=3 name="model.requestRemarks" cols="45" class="text" readonly="readonly" >${model.requestRemarks}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">招商意见</td>
			<td colspan="3">
				${model.get("merchantsAuditStatusStr")}
			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">招商说明</td>
			<td colspan="3">
				<textarea rows=3 name="model.merchantsAuditRemarks" cols="45" class="text" readonly="readonly">${model.merchantsAuditRemarks}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">运营意见</td>
			<td colspan="3">
				${model.get("operateAuditStatusStr")}
			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">运营说明</td>
			<td colspan="3">
				<textarea rows=3 name="model.operateAuditRemarks" cols="45" class="text" readonly="readonly">${model.operateAuditRemarks}</textarea>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
