<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<html>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<script type="text/javascript">
$(function() {
	$(".dateEditor").ligerDateEditor({
		showTime : false,
		format : "yyyy-MM-dd",
		labelAlign : 'left',
		width : 150
	});
	
 });
var submitting; 
function saveMchtContract(){	
	if(submitting){
		return false;
	}
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	if(!beginDate){
		commUtil.alertError("合同开始日期不能为空");
		return false;
	}
	if(!endDate){
		commUtil.alertError("合同结束日期不能为空");
		return false;
	}
	submitting = true;
	$.ajax({
		url : "${pageContext.request.contextPath}/mchtContract/saveMchtContract.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : $("#form1").serialize(),
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				submitting = false;
				parent.location.reload();
				frameElement.dialog.close();
			}else{
				submitting = false;
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			submitting = false;
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}
</script>
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" id="mchtId" name="mchtId" value="${mchtId}">
	<div style="padding:10px 10px 30px 10px;">
		<div class="search-td-label" style="float: left;">合同开始日期：</div>
		<div class="l-panel-search-item" >
			<input type="text" id="beginDate" name="beginDate" class="dateEditor" value="${beginDate}"/>
		</div>
	</div>
	<div style="padding:10px 10px 30px 10px;">
		<div class="l-panel-search-item">合同结束日期：</div>
		<div class="l-panel-search-item">
			<input type="text" id="endDate" name="endDate" class="dateEditor" value="${endDate}"/>
		</div>
	</div>
	<div style="padding-top:30px;text-align: center;">
		<input name="btnSubmit" type="submit" class="l-button l-button-submit" value="提交" onclick="saveMchtContract();"/>
		<input type="button" value="取消" class="l-button l-button-test" onclick="frameElement.dialog.close();" />
	</div>
	</form>
</body>
</html>
