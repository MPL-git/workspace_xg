<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.radioClass{margin-right: 20px;}
.hidden{display:none;}
</style>
<script type="text/javascript">
function submitForm(){
	var length = $("input[name='model.renewStatus']:checked").length;
	if(length == 0){
		commUtil.alertError("请选择是否要续签");
		return false;
	}
	var renewRemarks = $.trim($("#renewRemarks").val());
	if(!renewRemarks){
		commUtil.alertError("续签备注不能为空");
		return false;
	}
	var newContractBeginDate = $("#newContractBeginDate").val();
	var newContractEndDate = $("#newContractEndDate").val();
	if(!newContractBeginDate){
		commUtil.alertError("合同开始时间不能为空");
		return false;
	}
	if(!newContractEndDate){
		commUtil.alertError("合同结束时间不能为空");
		return false;
	}
	var oldEndDate = $("#oldEndDate").val();
	if(newContractEndDate>newContractBeginDate && newContractBeginDate>oldEndDate){
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtContract/renewCommit.shtml",
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
	}else{
		commUtil.alertError("合同结束日期 必须大于合同开始日期,合同开始日期必须大于原合同到期日期");
		return false;
	}
}

$(function() {

	$(".ligerDate").ligerDateEditor( {
		showTime : false,
		labelWidth : 160,
		width:160,
		labelAlign : 'left'
	});

	$("input[name='model.renewStatus']").change(function() {
		var renewStatus = $("input[name='model.renewStatus']:checked").val();
		if (renewStatus == 1){
			$(".newContractDate").removeClass("hidden");
		}else{
			$(".newContractDate").addClass("hidden");
		}
	});
}); 

</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" name="model.id" value="${model.id}">
	<input type="hidden" id="oldEndDate" value="${oldEndDate}">
		<table class="gridtable">
			<tr>
				<td class="title" style="text-align: center;">是否要续签<span style="color:red;">*</span></td>
				<td colspan="3">
					<span class="radioClass"><input type="radio" value="1" name="model.renewStatus" <c:if test="${model.renewStatus==0 || model.renewStatus==1}">checked="checked"</c:if> >要续签</span>
					<span class="radioClass"><input type="radio" value="2" name="model.renewStatus" <c:if test="${model.renewStatus==2}">checked="checked"</c:if> >不再续签</span>
				</td>
			</tr>
	        <tr>
	           	<td class="title" style="text-align: center;">备注<span style="color:red;">*</span></td>
	           	<td colspan="3">
					<textarea rows=3 id="renewRemarks" name="model.renewRemarks" cols="45" class="text" >${model.renewRemarks}</textarea>
				</td>
	        </tr>
			<tr class="newContractDate">
				<td  class="title">合同开始日期<span style="color:red;">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" class="ligerDate" id="newContractBeginDate" name="newContractBeginDate" value="${newContractBeginDate}"/>
				</td>
			</tr>
			<tr class="newContractDate">
				<td  class="title">合同结束日期<span style="color:red;">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" class="ligerDate" id="newContractEndDate" name="newContractEndDate" value="${newContractEndDate}"/>
				</td
				>
			</tr>
	        <tr>
				<td class="title" style="text-align: center;">操作</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input type="button" style="float:left;" class="l-button l-button-submit" value="提交" onclick="submitForm();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
	        </tr>
		</table>
	</form>
</body>
</html>
