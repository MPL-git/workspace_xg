<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	   
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<script type="text/javascript">
	var editor1;
	KindEditor.ready(function(K) {
		 editor1 = K.create('textarea[name="designRejectReason"]', {
			cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
			uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
			fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
			}
			
		});
		prettyPrint();
	});
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" action="${pageContext.request.contextPath}/activityNew/saveAuditDesignReject.shtml" method="post" id="form1" enctype="multipart/form-data">
		<input type="hidden" name="activityId" value="${activity.id }" />
		<input type="hidden" name="designAuditStatus" value="3" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">驳回理由</td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" name="designAuditRemarks" id="designAuditRemarks" value="${activity.designAuditRemarks }"  style="width:100%; height: 30px;" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">具体原因指导说明</td>
				<td align="left" class="l-table-edit-td" >
					<textarea name="designRejectReason" id="designRejectReason" style="width:100%;height:500px;visibility:hidden;">${activity.designRejectReason }</textarea>
 				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input name="btnSubmit" type="submit" id="Button1" class="l-button l-button-submit" value="保存"  /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>