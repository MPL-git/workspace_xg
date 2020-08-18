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
	.l-text-wrapper{ display:inline-block; }
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
		$("#form1").validate({
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
			url : "${pageContext.request.contextPath}/mchtCloseApply/auditCommit.shtml",
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
	<input type="hidden" name="model.id" value="${model.id}">
	<table class="gridtable">
		<tr>
			<td  class="title">暂停日期</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" class="ligerDate" name="model.stopBeginDate" value="<fmt:formatDate value='${model.stopBeginDate}' pattern='yyyy-MM-dd'/>" validate="{required:true}"/>
				<%-- ${model.get("stopBeginStatusStr")} --%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至
				<input type="text" class="ligerDate" style="float:left;" name="model.stopEndDate" value="<fmt:formatDate value='${model.stopEndDate}' pattern='yyyy-MM-dd'/>" />
				<%-- ${model.get("stopEndStatusStr")} --%>
			</td>
		</tr>
		<tr>
			<td  class="title">是否关店</td>
			<td align="left" class="l-table-edit-td">
				<input type="checkbox" class="liger-checkbox" value="1" <c:if test="${model.closeFlag==1}">checked="checked"</c:if> />是
			</td>
		</tr>
		<tr>
			<td  class="title">关店日期</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" class="ligerDate" name="model.closeBeginDate" value="<fmt:formatDate value='${model.closeBeginDate}' pattern='yyyy-MM-dd'/>" />
				<%-- ${model.get("closeBeginStatusStr")} --%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至
				<input type="text" class="ligerDate" name="model.closeEndDate" value="<fmt:formatDate value='${model.closeEndDate}' pattern='yyyy-MM-dd'/>" />
				<%-- ${model.get("closeEndStatusStr")} --%>
			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">申请理由</td>
			<td colspan="3">
				<textarea rows=3 name="model.requestRemarks" cols="45" class="text" readonly="readonly" >${model.requestRemarks}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">招商意见</td>
			<td colspan="3">
				<c:if test="${isMyMerchants}">
					<span class="radioClass"><input type="radio" value="1" name="model.merchantsAuditStatus" <c:if test="${model.merchantsAuditStatus==1}">checked="checked"</c:if> >同意</span>
					<span class="radioClass"><input type="radio" value="2" name="model.merchantsAuditStatus" <c:if test="${model.merchantsAuditStatus==2}">checked="checked"</c:if> >不同意</span>
				</c:if>
				<c:if test="${!isMyMerchants}">
					<%-- ${model.merchantsAuditStatusStr} --%>
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">招商说明</td>
			<td colspan="3">
				<textarea rows=3 name="model.merchantsAuditRemarks" cols="45" class="text" <c:if test="${!isMyMerchants}">readonly="readonly"</c:if> >${model.merchantsAuditRemarks}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">运营意见</td>
			<td colspan="3">
				<c:if test="${isMyOperate}">
					<span class="radioClass"><input type="radio" value="1" name="model.operateAuditStatus" <c:if test="${model.operateAuditStatus==1}">checked="checked"</c:if> >同意</span>
					<span class="radioClass"><input type="radio" value="2" name="model.operateAuditStatus" <c:if test="${model.operateAuditStatus==2}">checked="checked"</c:if> >不同意</span>
				</c:if>
				<c:if test="${!isMyOperate}">
					<%-- ${model.operateAuditStatusStr} --%>
				</c:if>

			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">运营说明</td>
			<td colspan="3">
				<textarea rows=3 name="model.operateAuditRemarks" cols="45" class="text" <c:if test="${!isMyOperate}">readonly="readonly"</c:if> >${model.operateAuditRemarks}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title" style="text-align: center;">操作</td>
			<td colspan="3" align="left" class="l-table-edit-td">
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
