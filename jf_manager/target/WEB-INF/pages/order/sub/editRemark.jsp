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
function flagSet(seqNo){
	var flag="color-"+seqNo;
	$("#flagSet").removeClass();
	$("#flagSet").attr("class","glyphicon glyphicon-flag "+flag);
	document.getElementById("remarksColor").value=seqNo;
}

function submit_fun(){	
	$.ajax({
		url : "${pageContext.request.contextPath}/order/sub/remarkSubmit.shtml",
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
	<input type="hidden" id="subOrderId" name="subOrderId" value="${subOrderId}">
	<input type="hidden" id="remarksColor" name="remarksColor" value="1">
	<input type="hidden" id="mchtRemark" name="mchtRemark" value="${mchtRemark}">
	<div style="padding:10px 0px 0px 10px;">
		<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-1' aria-hidden='true' onclick="flagSet(1);"></span>
		<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-2' aria-hidden='true' onclick="flagSet(2);"></span>
		<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-3' aria-hidden='true' onclick="flagSet(3);"></span>
		<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-4' aria-hidden='true' onclick="flagSet(4);"></span>
		<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-5' aria-hidden='true' onclick="flagSet(5);"></span>
		<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-6' aria-hidden='true' onclick="flagSet(6);"></span>
	</div>
	<div style="padding:10px 0px 0px 10px;">
		<textarea id="remarks" name="remarks" rows="4" cols="45" class="text" >${remarks}</textarea>
	</div>
	<div style="padding:20px 20px 5px 10px;font-size: 13px;">
		<c:if test="${not empty mchtRemark}">
			旗帜设置为：<span id="flagSet" style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-${remarksColor}' aria-hidden='true'></span>
		</c:if>
		<c:if test="${empty mchtRemark}">
			旗帜设置为：<span id="flagSet" style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-1' aria-hidden='true'></span>
		</c:if>
		<input name="btnSubmit" type="submit" id="Button1" style="float:right;" class="l-button l-button-submit" value="提交备注" onclick="submit_fun();"/>
	</div>
	</form>
</body>
</html>
