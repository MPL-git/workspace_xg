<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
.radioClass{margin: 10px 0 10px 10px;display:block;font-size: 15px;}
.l-button-submit,.l-button-test {width: 80px; margin-left: 10px;padding-bottom: 2px;}
</style>
<script type="text/javascript">
function save() {
	if(!$(':radio[name=platformContactId]:checked').length)
	{
		$.ligerDialog.warn("请选择对接人");
		return false;
	}else{
		$("#form1").submit();
	}
}
</script>
<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/mchtSettled/allot/submit.shtml">
		<input id="ids" name="ids" type="hidden" value="${ids }"/>
		<div style="min-height:370px;">
		<c:forEach var="contactItem" items="${platformContacts }">
			<span class="radioClass"><input type="radio" value="${contactItem.id }" name="platformContactId">${contactItem.contactName}</span>
		</c:forEach>
		</div>

		<div align="center">
			<input type="button" class="l-button l-button-submit" value="提交" onClick="save();"/>
			<input type="button" value="取消" class="l-button l-button-test"  onclick="frameElement.dialog.close();" />
		</div>
	</form>
</body>
</html>
