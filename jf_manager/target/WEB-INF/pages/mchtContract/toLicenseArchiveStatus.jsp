<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>

<style type="text/css">
	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
	.radioClass{margin-right: 20px;}
	.hidden{display:none;}
</style>

<script type="text/javascript">
function picArchive(mchtId,licenseArchiveStatus){
	$.ajax({ 
		url : "${pageContext.request.contextPath}/mchtContract/licenseArchiveStatus.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"mchtId":mchtId,"licenseArchiveStatus":licenseArchiveStatus},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				parent.location.reload();
				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" id="mchtId" name="mchtId" value="${mchtId}">
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">经营许可证</td>
				<td  colspan="2" align="left" class="l-table-edit-td">
					<img  src="${pageContext.request.contextPath}/file_servelt${businessLicensePic}">
					<div>
						<span>
							<input type="radio" name="licenseArchiveStatus" value="0" onclick="picArchive(${mchtId},0);" <c:if test="${licenseArchiveStatus == '0'}">checked="checked"</c:if>>未齐
						</span>
						<span>
							<input type="radio" name="licenseArchiveStatus" value="1" onclick="picArchive(${mchtId},1);" <c:if test="${licenseArchiveStatus == '1'}">checked="checked"</c:if>>已归
						</span>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
