<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}
.l-table-edit-td {
	padding: 4px;
}

</style>
<html>
<script type="text/javascript">
	
</script>
<body>
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/mcht/editRemarks.shtml" style="margin: 10px">
		<input type="hidden" id="mchtId" name="mchtId" value="${mchtInfoCustom.id}" />
		<input type="hidden" id="remarksType" name="remarksType" value="${remarksType}" />
		<table class="gridtable">
			<tr>
				<td align="left" class="l-table-edit-td">
					<c:if test="${remarksType == 'yy' }">
						<textarea rows="12" cols="45" id="operateRemarks" name="operateRemarks" >${mchtInfoCustom.mchtOptimizeOperateRemarks }</textarea>
					</c:if>
					<c:if test="${remarksType == 'fw' }">
						<textarea rows="12" cols="45" id="companyInfAuditInnerRemarks" name="companyInfAuditInnerRemarks" >${mchtInfoCustom.companyInfAuditInnerRemarks }</textarea>
					</c:if>
					
					<c:if test="${remarksType == 'bz' }">
						<textarea rows="12" cols="45" id="mchtOptimizeDepositRemarks" name="mchtOptimizeDepositRemarks" >${mchtInfoCustom.mchtOptimizeDepositRemarks }</textarea>
					</c:if>
					<c:if test="${remarksType == 'ml' }">
						<textarea rows="12" cols="45" id="mchtOptimizeGrossProfitRateRemarks" name="mchtOptimizeGrossProfitRateRemarks" >${mchtInfoCustom.mchtOptimizeGrossProfitRateRemarks }</textarea>
					</c:if>
					<c:if test="${remarksType == 'sp' }">
						<textarea rows="12" cols="45" id="mchtOptimizeProductRemarks" name="mchtOptimizeProductRemarks" >${mchtInfoCustom.mchtOptimizeProductRemarks }</textarea>
					</c:if>
					<c:if test="${remarksType == 'ff' }">
						<textarea rows="12" cols="45" id="mchtOptimizeServiceRemarks" name="mchtOptimizeServiceRemarks" >${mchtInfoCustom.mchtOptimizeServiceRemarks }</textarea>
					</c:if>
					<c:if test="${remarksType == 'wl' }">
						<textarea rows="12" cols="45" id="mchtOptimizeWuliuRemarks" name="mchtOptimizeWuliuRemarks" >${mchtInfoCustom.mchtOptimizeWuliuRemarks }</textarea>
					</c:if>
					<c:if test="${remarksType == 'tg' }">
						<textarea rows="12" cols="45" id="mchtOptimizeSpreadRemarks" name="mchtOptimizeSpreadRemarks" >${mchtInfoCustom.mchtOptimizeSpreadRemarks }</textarea>
					</c:if>
	        	</td>
			</tr>
			<tr>
				<td align="center" class="">
					<c:if test="${status == '1' }">
						<input type="submit" id="Button1" style="margin-right: 30px;" class="l-button l-button-submit" value="提交" /> 
					</c:if>
					<input type="button" value="取消" class="l-button l-button-submit" onclick="frameElement.dialog.close()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
