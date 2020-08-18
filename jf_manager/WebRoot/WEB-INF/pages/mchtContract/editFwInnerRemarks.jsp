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

<style type="text/css">
	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
	.radioClass{margin-right: 20px;}
	.hidden{display:none;}
</style>

<script type="text/javascript">

function submitForm(){
	var values = {};
    var t = $('#form1').serializeArray();
    $.each(t, function(i, obj) {
    	values[obj.name] = obj.value;
    });
	
    var data = {};
    data['model'] = JSON.stringify(values);
    
	$.ajax({
		url : "${pageContext.request.contextPath}/mchtContract/saveFwInnerRemarks.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : data,
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
		<input type="hidden" name="id" value="${model.id}">
		<table class="gridtable">
	        <tr>
	           	<td class="title" style="text-align: center;">内部备注</td>
	           	<td colspan="3">
					<textarea name="fwInnerRemarks" class="text" style="width:425px;height:100px;" >${model.fwInnerRemarks}</textarea>
				</td>
	        </tr>
	        <tr>
				<td class="title" style="text-align: center;">操作</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交" onclick="submitForm();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
	        </tr>
		</table>
	</form>
</body>
</html>
