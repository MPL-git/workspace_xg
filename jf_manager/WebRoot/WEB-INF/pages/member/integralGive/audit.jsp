<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<style type="text/css">
body{ font-size:12px;padding:10px;}
.l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin-right: 20px;}
</style>
<html>
	<head>
		<title>赠送会员金币审核</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

<script type="text/javascript">
function submit_fun(ausitStatus){
	document.getElementById("auditStatus").value=ausitStatus;
	$.ajax({
		url : "${pageContext.request.contextPath}/member/integralGive/auditSubmit.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : $("#form1").serialize(),
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				parent.$("#searchbtn").click();
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

$(document).ready(function() {

	var type=${integralGiveCustom.type};
	if (type==2)
	{
		document.getElementById('group1').style.display = '';
		document.getElementById('group2').style.display = 'none';
	}else if (type==3)
	{
		document.getElementById('group1').style.display = 'none';
		document.getElementById('group2').style.display = '';
	}else{
		document.getElementById('group1').style.display = 'none';
		document.getElementById('group2').style.display = 'none';
	}
});
</script>
</head>
	<body>
		<form method="post" id="form1" name="form1">
			<input id="id" name="id" type="hidden" value="${integralGiveCustom.id}"/>
			<input id="auditStatus" name="auditStatus" type="hidden"/>
			<table class="gridtable" >
				<tr>
					<td class="title"  width="30%">赠送范围</td>
					<td align="left" class="l-table-edit-td">${integralGiveCustom.typeDesc}</td> 
				</tr>
				
				<tr id="group1">
					<td class="title"  width="30%">会员组</td>
					<td align="left" class="l-table-edit-td">${integralGiveCustom.groupName }</td> 
				</tr>
				
				<tr id="group2">
					<td class="title"  width="30%">会员ID</td>
					<td align="left" class="l-table-edit-td">
						<textarea rows=3 cols="45" class="text" disabled="disabled">${integralGiveCustom.groupId}</textarea>
					</td> 
				</tr>

				<tr>
					<td class="title"  width="30%">赠送金币数</td>
					<td align="left" class="l-table-edit-td">${integralGiveCustom.integral}</td> 
				</tr>

				<tr>
					<td class="title"  width="30%">详细说明</td>
					<td align="left" class="l-table-edit-td">
						<textarea rows=3 cols="45" class="text" disabled="disabled">${integralGiveCustom.remarks}</textarea>
					</td> 
				</tr>
				
				<tr>
					<td class="title"  width="30%">驳回原因</td>
					<td align="left" class="l-table-edit-td">
						<textarea rows=3 id="auditRemarks" name="auditRemarks" cols="45" class="text" ></textarea>
					</td> 
				</tr>
				
				<tr>
				<td class="title"  width="30%">操作</td> 
				<td align="left" class="l-table-edit-td">
					<input name="btnSubmit" type="submit" style="float:left;" class="l-button l-button-submit" value="审核通过" onclick="submit_fun(1);"/>
					<input name="btnSubmit" type="submit" style="float:left;" class="l-button l-button-submit" value="驳回" onclick="submit_fun(2);"/>
				</td>
				</tr>
			</table> 
			 
		</form>
	</body>
</html>
