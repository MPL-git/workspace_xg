<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
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
</style>
<script type="text/javascript">
function submit_fun(){
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtDepositContSubmit.shtml",
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
	<input type="hidden" name="mchtId" value="${mchtId}">
		<table class="gridtable">
		    <tr>
               <td class="title" style="text-align: center;" >&nbsp;</td>  
               <td class="title" style="text-align: center;" colspan="5">&nbsp;</td>
			</tr> 
	        <tr>
	           	<td style="text-align: center;">保证内容</td> 
	           	<td colspan="5"><textarea name="depositCont" id="depositCont" rows="16" style="width: 100%;">${depositCont}</textarea></td>
	        </tr>
	        <tr>
				<td style="text-align: center;">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交" onclick="submit_fun();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
	        </tr>
		</table>
	</form>
</body>
</html>
