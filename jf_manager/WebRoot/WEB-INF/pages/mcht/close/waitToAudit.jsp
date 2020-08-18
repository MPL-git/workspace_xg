<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
	.radioClass{margin-right: 20px;}
	.hidden{display:none;}
</style>

<script type="text/javascript">
var onlineContract_viewer; 
$(function(){
	if($("#onlineContract_viewer").length>0){
		onlineContract_viewer = new Viewer(document.getElementById('onlineContract_viewer'), {});
	}
	$("input[type='radio']").bind('click',function(){
		var auditStatus = $(this).val();
		if(auditStatus == 2){
			$("#picTr").show();
			$("#reason").hide();
			onlineContract_viewer = new Viewer(document.getElementById('onlineContract_viewer'), {});
		}else{
			$("#picTr").hide();
			$("#reason").show();
		}
	});
});


function submitForm(){
	var auditStatus = $("input[name='contractAuditStatus']:checked").val();
	if(!auditStatus){
		commUtil.alertError("请选择审核结果");
		return;
	}
	debugger;
	if(auditStatus == 3){
		var remarks = $("#contractAuditRemarks").val();
		if(remarks==''){
			commUtil.alertError("驳回原因不能为空");
			return;
		}
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/close/waitToAuditCommit.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : $("#form1").serialize(),
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				commUtil.alertSuccess("操作成功");
				parent.location.reload();
				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(result.message);
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
	<input type="hidden" name="mchtCloseId" value="${mchtCloseId}">
		<table class="gridtable">
			<tr>
				<td class="title" style="text-align: center;">商家合作模式</td>
				<td colspan="3">
					<span class="radioClass">${mchtTag}</span>
				</td>
			</tr>

			<tr>
				<td class="title" style="text-align: center;">审核结果</td>
				<td colspan="3">
					<span class="radioClass"><input type="radio" value="2" name="contractAuditStatus"  >审核通过</span>
					<span class="radioClass"><input type="radio" value="3" name="contractAuditStatus"  checked >驳回</span>
				</td>
			</tr>
			
		
	        <tr id="picTr" style="display: none;" >
	           	<td class="title" style="text-align: center;">终止合同</td>
				<c:if test="${isNeedUpload eq 1}">
				<td colspan="3" id="onlineContract_viewer">
	           		<c:forEach items="${mchtContractPics}" var="mchtContractPic">
	           			<img style="width: 120px;height: 120px" src="${pageContext.request.contextPath}/file_servelt${mchtContractPic.pic}">
	           		</c:forEach>
				</td>
				</c:if>
				<c:if test="${isNeedUpload eq 0}">
					<td colspan="3" id="onlineContract_viewer">
						<span class="radioClass">该商家为自营商家且选择了无需线上审核合同</span>
					</td>
				</c:if>
	        </tr>
	   
	        
	        <tr id="reason" >
	           	<td class="title" style="text-align: center;">驳回原因</td>
	           	<td colspan="3">
					<textarea rows=3 id ="contractAuditRemarks" name="contractAuditRemarks" cols="45" class="text"  maxlength="64" value=""></textarea>
				</td>
	        </tr>
	        
	         <tr>
	           	<td class="title" style="text-align: center;">内部备注</td>
	           	<td colspan="3">
					<textarea rows=3 name="innerRemarks" cols="45" class="text" id="remarks" maxlength="64"></textarea>
				</td>
	        </tr>
	        
	        <tr>
				<td class="title" style="text-align: center;">操作</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交" onclick="submitForm();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
	        </tr>
		</table>
	</form>
</body>
</html>
