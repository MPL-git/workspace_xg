<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>

<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript">
$(function ()  {
	$.metadata.setType("attr", "validate");
	
	var v = $("#form1").validate({
	            	
		errorPlacement: function (lable, element)
		{   
        	var elementType=$(element).attr("type");

        	if($(element).hasClass("l-text-field")){
        		$(element).parent().ligerTip({
					content : lable.html(),width: 100
				});
        	}
        	else if('radio'==elementType){
        		var radioName=$(element).attr("name");
        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
					content : lable.html(),width: 100
				});
        	}
        	else{
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
			if(!$("#email").val()){
				$.ligerDialog.error("邮箱不能为空!");
				return;
			}
			if(!$("#dutyParagraph").val()){
				$.ligerDialog.error("税号不能为空!");
				return;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/order/saveCombineOrderInvoice.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : $("#form1").serialize(),
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$.ligerDialog.success('操作成功!');
 						parent.location.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	});
});
</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
		<input id="id" name="id" type="hidden" value="${combineOrderInvoice.id }"/>
		<input id="combineOrderId" name="combineOrderId" type="hidden" value="${combineOrderId }"/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">邮箱<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input style="width: 200px" type="text" id="email" name="email" value="${combineOrderInvoice.email }"/>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">税号<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input style="width: 200px" type="text" id="dutyParagraph" name="dutyParagraph" value="${combineOrderInvoice.dutyParagraph }"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">公司名称/抬头名称</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input style="width: 200px" type="text" id="company" name="company" value="${combineOrderInvoice.company }"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">发票类型</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<select style="width: 200px;" id="type" name="type">
						<option value="">请选择</option>
						<option value="1" <c:if test="${combineOrderInvoice.type eq '1' }">selected</c:if>>专票</option>
						<option value="2" <c:if test="${combineOrderInvoice.type eq '2' }">selected</c:if>>电子发票</option>
						<option value="3" <c:if test="${combineOrderInvoice.type eq '3' }">selected</c:if>>纸质普票</option>
					</select>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">单位地址</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input style="width: 200px" type="text" id="address" name="address" value="${combineOrderInvoice.address }"/>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">单位电话</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input style="width: 200px" type="text" id="phone" name="phone" value="${combineOrderInvoice.phone }"/>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">开户银行</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input style="width: 200px" type="text" id="bank" name="bank" value="${combineOrderInvoice.bank }"/>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">银行账号</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input style="width: 200px" type="text" id="bankAccount" name="bankAccount" value="${combineOrderInvoice.bankAccount }"/>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
