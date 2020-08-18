<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript">
	$(function(){
		
		$.metadata.setType("attr", "validate");
		var v = $("#form1").validate({
			errorPlacement: function (lable, element) {   
	        	var elementType = $(element).attr("type");
	        	if($(element).hasClass("l-text-field")){
	        		$(element).parent().ligerTip({
						content : lable.html(),width: 100
					});
	        	}else if('radio'==elementType){
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else{
	        		$(element).ligerTip({
						content : lable.html(),width: 100
					});
	        	}
			},
			success: function (lable,element){
				lable.ligerHideTip();
				lable.remove();
			},
			submitHandler: function (form){
        		formSubmit();
			}
		}); 
		
		
		
	});

	
	function formSubmit() {
		$.ajax({
			url : "${pageContext.request.contextPath}/order/complain/updateCustomerServiceOrder.shtml",
			type : 'POST',
			dataType : 'json',
			data : $("#form1").serialize(),
			success : function(data) {
				if ("0000" == data.returnCode) {
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
	
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" >
		<input type="hidden" id="customerServiceOrderId" name="customerServiceOrderId" value="${customerServiceOrderId }"/>
		<table class="gridtable">
			<tr>
				<td class="title">快递<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" id="memberExpressCompany" name="memberExpressCompany" value="" validate="{required:true }" />
				</td>
			</tr>	
			<tr>
				<td class="title">快递单号<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" id="memberExpressNo" name="memberExpressNo" value="" validate="{required:true }" />
				</td>
			</tr>
			<tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
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
