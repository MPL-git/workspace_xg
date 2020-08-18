<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.color-1{color: #9D999D;}
.color-2{color: #FC0000;}
.color-3{color: #EFD104;}
.color-4{color: #00FC28;}
.color-5{color: #0351F7;}
.color-6{color: #DF00FB;}
</style>
<html>
<script type="text/javascript">
function submit_fun(){	
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/cancelmchtBlacklistdata.shtml?inblacklist=1",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : $("#form1").serialize(),
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
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" id="id" name="id" value="${mchtInfo.id}">
	<div style="padding:10px 10px 30px 10px;">
		<span id="remarks" name="remarks" rows="4" cols="45" class="text" >${mchtInfo.remarks}</span>
	</div>
	<div style="padding:25px 25px 25px 25px;font-size: 13px;">
		<input name="btnSubmit" type="submit" id="Button1" style="float:right;width: 100px;" class="l-button l-button-submit" value="取消黑名单" onclick="submit_fun();"/>
	</div>
	</form>
</body>
</html>
