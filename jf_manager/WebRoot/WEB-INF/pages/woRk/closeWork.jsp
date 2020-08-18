<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
    
<script type="text/javascript">
/* var editor1;
KindEditor.ready(function(K) {
	 editor1 = K.create('textarea[name="recordContent"]', {
		cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
		uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
		fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
		allowFileManager : true,
		afterCreate : function() {
		}
		
	});
	prettyPrint();
});
 */
$(function ()  { 
	 $.metadata.setType("attr", "validate");
    $("#form1").validate({
		errorPlacement : function(lable, element) {
       	$(element).ligerTip({recordContent : lable.html()});
		},
		success : function(lable) {
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler : function(form) {
			editor1.sync();
			var recordContent=document.getElementById("recordContent").value;
			var closeReason=$("#closeReason").val();
			if (closeReason=='') {
				$.ligerDialog.alert("请选择关闭原因"); 
				return;
			}
			if($.trim(recordContent)=='' || recordContent == null){
				$.ligerDialog.alert("备注不能为空"); 
				return;
			}
			form.submit();
		}
	});
}); 


</script>

</head>
	<body style="margin: 10px;">
	<form method="post" id="form1" name="form1"  action="${pageContext.request.contextPath}/work/clsoework.shtml" >
		<input type="hidden" id="id" name="id" value="${workHistory.id}"/>
		<input type="hidden" id="workid" name="workid" value="${woRk.id}"/>
		<table class="gridtable">
				<tr>
	            	<td class="title" width="20%">关闭原因<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="closeReason" name="closeReason" >
							<option value="">请选择...</option>
								<option value="1" >已完成</option>
								<option value="2" >终止</option>
								<option value="3" >作废</option>									
						</select>
					</td>
	           	</tr>
           	
	         <tr>
				<!-- <td class="title">备注<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<textarea name="recordContent" id="recordContent" style="width:150px;height:300px;visibility:hidden;"></textarea>
              	</td> --> 
              	<td colspan="1" class="title">备注内容<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;" rows="10" cols="100" id='recordContent' name="recordContent" ></textarea>
				</td> 
            </tr>	           	
         
			 <tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>