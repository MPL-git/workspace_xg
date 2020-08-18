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
</head>
	<body>
		<form method="post" id="form1" name="form1">
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
						<textarea rows=3 id="auditRemarks" name="auditRemarks" cols="45" class="text" >${integralGiveCustom.auditRemarks}</textarea>
					</td> 
				</tr>
				<tr>
					<td class="title"  width="30%">状态</td>
					<td align="left" class="l-table-edit-td">
						<c:forEach items="${auditStatusList }" var="auditStatus">
							<c:if test="${integralGiveCustom.auditStatus == auditStatus.statusValue}">${auditStatus.statusDesc }</c:if>
						</c:forEach>
					</td> 
				</tr>
				
			</table> 
		</form>
	</body>
	
<script type="text/javascript">

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

</html>
