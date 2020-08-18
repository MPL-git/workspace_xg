<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}
.l-table-edit-td {
	padding: 4px;
}

</style>
<html>
<script type="text/javascript">
	function submit_fun() {
		var subordinateStaffIds = $("#subordinateStaffIds").val();
		if(subordinateStaffIds != '' ) {
			if(!/(^(([1-9]\d*)+\,)+([1-9]\d*)+$)|(^[1-9]\d*$)/.test(subordinateStaffIds)) {
				$("#errorShow").attr("style", "color: red;");
				$("#errorShow").html("请输入正确的格式！");
				return;
			}else {
				var returnStatus = false;
				$.ajax({
					type: 'post',
					url: '${pageContext.request.contextPath}/orgMng/getSubordinateStaffIds.shtml',
					data: {subordinateStaffIds : subordinateStaffIds},
					dataType: 'json',
					async: false,
					success: function(data) {
						if(data.code != null && data.code == 200) {
							if(data.status == 1) {
								$("#errorShow").attr("style", "color: red;");
								$("#errorShow").html(data.staffIds+"下级人员ID不存在！");
								returnStatus = true;
							}
						}else {
							commUtil.alertError(data.msg);
							returnStatus = true;
						}
					},
					error: function(e) {
						commUtil.alertError("系统异常请稍后再试");
						returnStatus = true;
					}
				});
				if(returnStatus) {
					return;
				}
			}
		}
		$("#form1").submit();
	}
</script>
<body>
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/orgMng/updateStaff.shtml" style="margin: 10px">
		<input type="hidden" id="staffId" name="staffId" value="${sysStaffInfo.staffId}" />
		<table class="gridtable">
			<tr>
				<td align="left" class="l-table-edit-td">
					<span>是否为管理层：</span>
					<select id="isManagement" name="isManagement" style="width: 60px;" >
						<option value="0" <c:if test="${sysStaffInfo.isManagement == '0' }">selected</c:if> >否</option>
						<option value="1" <c:if test="${sysStaffInfo.isManagement == '1' }">selected</c:if> >是</option>
					</select>
	        	</td>
			</tr>
			<tr>
				<td align="left" class="l-table-edit-td">
					<span>是否可看资质：</span>
					<select id="canViewQualification" name="canViewQualification" style="width: 60px;" >
						<option value="0" <c:if test="${sysStaffInfo.canViewQualification == '0' }">selected</c:if> >否</option>
						<option value="1" <c:if test="${sysStaffInfo.canViewQualification == '1' }">selected</c:if> >是</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="left" class="l-table-edit-td">
					<span>我的下级人员（可查看他们的商家）：</span>
					<textarea rows="8" cols="45" style="margin: 5px 0px;" id="subordinateStaffIds" name="subordinateStaffIds" >${sysStaffInfo.subordinateStaffIds }</textarea>
	        		<span id="errorShow" style="color: gray;">多个请使用英文逗号隔开！</span>
	        	</td>
			</tr>
			<tr>
				<td align="center" class="">
					<input type="button" id="Button1" style="margin-right: 30px;" class="l-button l-button-submit" value="提交" onclick="submit_fun();" /> 
					<input type="button" value="取消" class="l-button l-button-submit" onclick="frameElement.dialog.close()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
