<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
</script>


<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}

.radioClass{
	margin-right: 20px;
}



.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}
</style>
<script type="text/javascript">





$(function ()  {
	$.metadata.setType("attr", "validate");
    //驳回原因必填
    $.validator.addMethod("auditRemarksRequired", function(value, element) {  
    	if($("input[type=radio][name='isCompanyInfPerfect'][value=4]").attr("checked")&&$.trim($("#companyInfAuditRemarks").val())==""){
 			return false;
 		}else{
 			return true;
 		}
    }, "请注明驳回原因");
	
	            var v = $("#form1").validate({
	            	
	                errorPlacement: function (lable, element)
	                {   console.log(lable[0].innerText);
	                	if($(element).hasClass("l-text-field")){
	                		$(element).parent().ligerTip({
								content : lable.html(),width: 100
							});
	                	}else{
	                		$(element).ligerTip({
								content : lable.html(),width: 100
							});
	                	}
	                	
	                	
	                },
	                success: function (lable,element)
	                {
	                    lable.ligerHideTip();
	                    lable.remove();
	                },
	                submitHandler: function (form)
	                {   
	                	isCompanyInfPerfectChangeSubmit();
	                }
	            });
	            
	            
	            
	  });    



function isCompanyInfPerfectChangeSubmit(){
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/isCompanyInfPerfectChangeSubmit.shtml",
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

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" name="id" value="${mchtInfo.id}">
		<table class="gridtable">
					<tr>
				<td class="title">审核状态<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				<c:forEach var="pefectStatusItem" items="${isCompanyInfPerfectStatus }">
					<span class="radioClass"><input type="radio" value="${pefectStatusItem.statusValue }" name="isCompanyInfPerfect" <c:if test="${pefectStatusItem.statusValue==mchtInfo.isCompanyInfPerfect}">checked="checked"</c:if> >${pefectStatusItem.statusDesc}</span>
				</c:forEach>	
				</td>
			</tr>
			<tr>
				<td class="title">驳回原因<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td"><textarea rows=5
						id="companyInfAuditRemarks" name="companyInfAuditRemarks" cols="50" class="text" validate="{auditRemarksRequired:true}"  >${mchtInfo.companyInfAuditRemarks}</textarea>
				</td>
			</tr>
			
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<c:if test="${STAFF_ID!=1}">
							<input name="btnSubmit" type="submit" id="Button1" 
								style="float:left;" class="l-button l-button-submit" value="提交"
								 />
						</c:if>
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />

					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
