<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>



<style type="text/css">
	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
	.radioClass{margin-right: 20px;}
	.hidden{display:none;}
</style>

<style type="text/css">
	.middle input {
		display: block;
		width: 30px;
		margin: 2px;
	}
	table.l-checkboxlist-table td{
		border-style: none;
	}
	table.l-listbox-table td{
		border-style: none;
	}
</style>

<script type="text/javascript">

	$(function() {

		$(".ligerDate").ligerDateEditor( {
			showTime : false,
			labelWidth : 160,
			width:160,
			labelAlign : 'left'
		});

		$.metadata.setType("attr", "validate");
		var v = $("#form1").validate({
			errorPlacement: function (lable, element){
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
			success: function (lable,element){
				lable.ligerHideTip();
				lable.remove();
			},
			submitHandler: function (form){
				submitForm();
			}
		});


	});

	function submitForm(){
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtCloseApply/requestCommit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : $("#form1").serialize(),
			timeout : 30000,
			success : function(result) {
				if (result.success) {
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

<html>
<body>
<form method="post" id="form1" name="form1">
	<input type="hidden" name="model.mchtId" value="${mchtId}">
	<table class="gridtable">
		<tr>
			<td  class="title">暂停日期</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" class="ligerDate" name="model.stopBeginDate" value="" validate="{required:true}"/>
				-
				<input type="text" class="ligerDate" name="model.stopEndDate" value="" />
			</td>
		</tr>
		<tr>
			<td  class="title">是否关店</td>
			<td align="left" class="l-table-edit-td">
				<input type="checkbox" name="model.closeFlag" class="liger-checkbox" value="1" <c:if test="${mchtInfoStatus==2}">checked="checked" readonly="readonly"</c:if> />是
			</td>
		</tr>
		<tr>
			<td  class="title">关店日期</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" class="ligerDate" name="model.closeBeginDate" value="" />
				-
				<input type="text" class="ligerDate" name="model.closeEndDate" value="" />
			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">申请理由</td>
			<td colspan="3">
				<textarea rows=3 name="model.requestRemarks" cols="45" class="text" ></textarea>
			</td>
		</tr>

		<tr>
			<td class="title" style="text-align: center;">操作</td>
			<td colspan="3" align="left" class="l-table-edit-td">
				<div id="btnDiv">
					<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="确认申请"/>
					<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
				</div>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
