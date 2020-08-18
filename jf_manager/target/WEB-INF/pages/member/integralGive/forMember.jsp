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
		<title>指定会员赠送金币</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

<script type="text/javascript">
function save() {
 	$("#tblIntegral").text("");
	 
	var re = /^[1-9]+[0-9]*]*$/;
	if (!re.test($("#integral").val()))
		{$("#tblIntegral").text("无效数字"); return false;}

	 $("#form1").submit();
	 } 
</script>
</head>
	<body>
		<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/member/integralGive/forMemberSuccess.shtml" >
			<input id="accId" name="accId" type="hidden" value="${accId}"/>
			<table class="gridtable" >
				
				<tr>
					<td class="title"  width="30%">会员ID</td>
					<td align="left" class="l-table-edit-td">${memberId}</td>
				</tr>

				<tr>
					<td class="title"  width="30%">赠送金币数</td>
					<td align="left" class="l-table-edit-td">
						<input type="text" id="integral" name="integral" style="float:left;width: 150px" />
					 	<label id="tblIntegral" style="float:left;margin:0 0 0 10px;color: #FF0000"></label> 
					</td> 
				</tr>

				<tr>
					<td class="title"  width="30%">详细说明</td>
					<td align="left" class="l-table-edit-td">
						<textarea rows=3 id="remarks" name="remarks" cols="45" class="text" ></textarea>
					</td> 
				</tr>
				<tr>
				<td class="title"  width="30%">操作</td> 
				<td align="left" class="l-table-edit-td">
					<input name="btnSubmit" type="button" id="Button1" class="l-button l-button-submit" value="赠送" onClick="save();" />
					<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
				</td>
				</tr>
			</table> 
			 
		</form>
	</body>
</html>
