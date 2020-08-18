<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
	 <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
 	.radioClass{margin: 0 10px 0 10px;}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
</style>
<script type="text/javascript">
		
	//模版管理
	function activityAreaTemplet(templetType) {
		var title = '';
		if(templetType == '1') {
			if('${type}' == '1') {
				title = '默认品牌模板';
			}else if('${type}' == '2') {
				title = '默认单品模板';
			}
		}else if(templetType == '2') {
			title = '代码模板';
		}else if(templetType == '3') {
			title = '自定义模板';
		}
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.9,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/activityAreaTemplet.shtml?activityAreaId=${activityAreaId}&type=${type}&isPreSell=${isPreSell}&templetType="+templetType,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
		
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" method="post" action="${pageContext.request.contextPath}/activityAreaNew/updateTempletType.shtml">
		<input type="hidden" name="activityAreaId" value="${activityAreaId }" />
		<input type="hidden" name="type" value="${type }" />
		<input type="hidden" name="isPreSell" value="${isPreSell }" />
		<table class="gridtable">
			<tr align="center">
				<td class="title">选择</td>
				<td class="title">模板名称</td>
				<td class="title">操作</td>
			</tr>
			<tr align="center">
				<td>
					<input type="radio" style="width: 50px;" name=templetType value="1" <c:if test="${templetType != '2' and templetType != '3' }">checked</c:if> />
				</td>
				<td>
					<c:if test="${type == '1' }">默认品牌模板</c:if>
					<c:if test="${type == '2' }">默认单品模板</c:if>
					<c:if test="${type == '3' }">自定义模板</c:if>
				</td>
				<td>
					<input type="button" style="width: 50px;" onclick="activityAreaTemplet(1);" value="管理" /> 
				</td>
			</tr>
			<tr align="center">
				<td>
					<input type="radio" style="width: 50px;" name=templetType value="2" <c:if test="${templetType == '2' }">checked</c:if> />
				</td>
				<td>代码模板</td>
				<td>
					<input type="button" style="width: 50px;" onclick="activityAreaTemplet(2);" value="管理" /> 
				</td>
			</tr>
			<tr align="center">
				<td>
					<input type="radio" style="width: 50px;" name=templetType value="3" <c:if test="${templetType == '3' }">checked</c:if> />
				</td>
				<td>自定义模板</td>
				<td>
					<input type="button" style="width: 50px;" onclick="activityAreaTemplet(3);" value="管理" /> 
				</td>
			</tr>
			<tr align="center">
				<td colspan="3" style="border: none;height: 50px;">
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
				</td>
			</tr>
		</table>
	</form>
	</body>
</html>