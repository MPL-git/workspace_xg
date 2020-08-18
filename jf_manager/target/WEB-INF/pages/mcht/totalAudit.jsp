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



.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}
</style>
<script type="text/javascript">
var dataFromValidate;
 $(function(){
		$("#agreementBeginDate").ligerDateEditor( {
			showTime : false,
			width:158,
		});
		$("#agreementEndDate").ligerDateEditor( {
			showTime : false,
			width:158,
		});
		
		
		 $.metadata.setType("attr", "validate");
		 dataFromValidate= $("#form1").validate({
			 	errorPlacement: function (lable, element) {   
		        	var elementType = $(element).attr("type");
		        	if($(element).hasClass("l-text-field")){
		        		$(element).parent().ligerTip({
							content : lable.html(),width: 80
						});
		        	}else if('radio'==elementType){
		        		var radioName=$(element).attr("name");
		        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
							content : lable.html(),width: 100
						});
		        	}else{
		        		$(element).ligerTip({
							content : lable.html(),width: 80
						});
		        	}
				},
				success: function (lable,element){
					lable.ligerHideTip();
					lable.remove();
				}
			});
		
 });



function auditSubmmit(){
	if(dataFromValidate.form()){
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/totalAuditSave.shtml",
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
}

function changeStatus(){
	var auditStatus=$("input[name='totalAuditStatus']:checked").val();
	if(auditStatus=='2'){
		$("#contractDeposit_tr").show();
		$("#depositContent_tr").show();
		$("#agreementBeginDate_tr").show();
		$("#agreementEndDate_tr").show();
		$("#auditRemarks_tr").hide();
	}else{
		$("#contractDeposit_tr").hide();
		$("#depositContent_tr").hide();
		$("#agreementBeginDate_tr").hide();
		$("#agreementEndDate_tr").hide();
		$("#auditRemarks_tr").show();
	}
}

$.validator.addMethod("auditStatus_2_required", function(value, element) {  
	var auditStatus=$("input[name='totalAuditStatus']:checked").val();
	if(auditStatus=='2'){
		if(value==""){
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
	
}, "必输项");
$.validator.addMethod("auditStatus_3_required", function(value, element) {  
	var auditStatus=$("input[name='totalAuditStatus']:checked").val();
	if(auditStatus=='3'){
		if(value==""){
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}, "必输项");


</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" name="id" value="${mchtId}">
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">公司信息</td>
				<td  colspan="3" align="left" class="l-table-edit-td">${isCompanyInfPerfect }</td>
				<td  colspan="1" class="title">税务状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td">${taxInvoiceInfoStatus }</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">店铺名</td>
				<td  colspan="3" align="left" class="l-table-edit-td">${shopNameAuditStatus }</td>
				<td  colspan="1" class="title">银行账号状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td">${mchtBankStatus }</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">类目</td>
				<td  colspan="3" align="left" class="l-table-edit-td">${mchtProductTypeCount}个通过</td>
				<td  colspan="1" class="title">SPOP资料状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td">通过</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">品牌</td>
				<td  colspan="3" align="left" class="l-table-edit-td">${mchtBrandCount}个通过</td>
				<td  colspan="1" class="title">SPOP资料状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td">通过</td>
			</tr>
			<tr>
	           	<td colspan="1" class="title">审核结果</td>
	           	<td colspan="7">
					<span class="radioClass"><input class="radioItem" type="radio" validate="{required:true}" value="2" name="totalAuditStatus" onchange="changeStatus();">审核通过</span>
					<span class="radioClass"><input class="radioItem" type="radio" value="3" name="totalAuditStatus" onchange="changeStatus();">驳回</span>
				</td>
	        </tr>
			<tr id="contractDeposit_tr" style="display:none;">
	           	<td colspan="1" class="title">合同保证金</td>
	           	<td colspan="7">
					<input type="text" name="contractDeposit" validate="{number:true,min:0,max:999999}" value="${contractDeposit}">元
				</td>
	        </tr>
	        <tr id="depositContent_tr" style="display:none;">
	           	<td colspan="1" class="title">保证金内容</td>
	           	<td colspan="7">
					<textarea validate="{auditStatus_2_required:true}" rows=3 id="depositContent" name="depositContent" cols="45" class="text" ></textarea>
				</td>
				
	        </tr>
	        <tr id="agreementBeginDate_tr" style="display:none;">
	           	<td colspan="1" class="title">合同开始日期</td>
	           	<td colspan="7">
					<input type="text" id="agreementBeginDate" name="agreementBeginDate"  value="" style="float:left;" validate="{auditStatus_2_required:true}"/>
				</td>
				
	        </tr>
	        <tr id="agreementEndDate_tr" style="display:none;">
	           	<td colspan="1" class="title">合同结束日期</td>
	           	<td colspan="7">
					<input type="text" id="agreementEndDate" name="agreementEndDate"  value="" style="float:left;" validate="{auditStatus_2_required:true}"/>
				</td>
				
	        </tr>
	        <tr id="auditRemarks_tr" style="display:none;">
	           	<td colspan="1" class="title">驳回原因</td>
	           	<td colspan="7">
					<textarea validate="{auditStatus_3_required:true}" rows=3 id="auditRemarks" name="auditRemarks" cols="45" class="text" ></textarea>
				</td>
				
	        </tr>
			<tr>
				<td colspan="1"  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
							<input name="btnSubmit" type="button" id="Button1"  onclick="auditSubmmit()"
								style="float:left;" class="l-button l-button-submit" value="提交"
								 />
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
