<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link
	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/common/js/uploadImageList.js"
	type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/css/upload_image_list.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass {
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}

table.l-checkboxlist-table td {
	border-style: none;
}

table.l-listbox-table td {
	border-style: none;
}
</style>
<script type="text/javascript">
	//提交操作
	$(function() {
		$("#Button1").bind('click',function(){
			var param = $("#form1").serialize();
			$.ajax({
				url : "${pageContext.request.contextPath}/service/navigation/savenavigation.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : param,
				timeout : 30000,
				success : function(data) {
					if (data.statusCode==200) {
						commUtil.alertSuccess("保存成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.message);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});		

	});
	//序号随状态改变而改变
	var paramValue = document.getElementById("paramValue").value;
	document.getElementById("paramOrder").value = paramValue;
	function test (defaultVal) {
	document.getElementById("paramOrder").value = defaultVal;
	}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/service/navigation/savenavigation.shtml">
		<input id="paramId" name="paramId" type="hidden"
			value="${sysParamCfg.paramId}" />
		<table class="gridtable">
			<tr>
				<td class="title">APP首页导航方案编号 <span class="required">*</span></td>
				<td align="left" class="l-table-edit-td"><input id="paramCode"
					disabled="disabled" name="paramCode" type="text"
					value="${sysParamCfg.paramCode}" style="float:left;width: 200px;"
					validate="{required:true,paramCodeUnique:true}" /></td>
			</tr>
			<tr>
				<td class="title">APP首页导航方案名称</td>
				<td align="left" class="l-table-edit-td"><input id="paramName"
					name="paramName" type="text" disabled="disabled"
					value="${sysParamCfg.paramName}" style="float:left;width: 200px;"
					validate="{paramNameUnique:true}" /></td>
			</tr>

			<tr>
				<td class="title">APP首页导航方案状态：</td>
				<td class="search-td-condition"><select id="paramValue"
					name="paramValue"  onchange="test(this.value);" style="float:left;width: 150px;">
						<option value="">请选择</option>
						<option value="1"
							<c:if test="${sysParamCfg.paramValue eq '1'}">selected="selected"</c:if>>悬浮</option>
						<option value="2"
							<c:if test="${sysParamCfg.paramValue eq '2'}">selected="selected"</c:if>>固定</option>
						<option value="3"
							<c:if test="${sysParamCfg.paramValue eq '3'}">selected="selected"</c:if>>不显示</option>
				</select></td>
			</tr>
			<tr>
				<td class="title">APP首页导航方案序号</td>
				<td align="left" class="l-table-edit-td"><input id="paramOrder"
					name="paramOrder" type="text"  value="" readonly="readonly"  style="float:left;width: 200px;"
					validate="{paramOrdercUnique:true}" /></td>
			</tr>

			<tr>
				<td class="title">APP首页导航方案描述</td>
				<td align="left" class="l-table-edit-td"><input id="paramDesc"
					name="paramDesc" type="text" disabled="disabled"
					value="${sysParamCfg.paramDesc}" style="float:left;width: 200px;"
					validate="{paramDesccUnique:true}" /></td>
			</tr>

			<tr>
				<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td"><input
					name="btnSubmit" type="text" id="Button1" style="float:left;"
					class="l-button" value="提交" /> <input style="margin-left: 20px;"
					class="l-button" type="button" value="关闭"
					onclick="frameElement.dialog.close()" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
