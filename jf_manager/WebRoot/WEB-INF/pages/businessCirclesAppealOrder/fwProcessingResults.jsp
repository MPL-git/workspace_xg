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

<script type="text/javascript">
$(function(){
	$("#form1").validate({
        submitHandler: function(form) {
        	var appealStatus = $('input[name="appealStatus"]:checked').val();
            if (!appealStatus){
            	commUtil.alertError("请选择处理状态");
                return ;
            }
        	var fwResult = $.trim($("#fwResult").val());
   		    if(fwResult == '') {	    
   		      commUtil.alertError("法务处理建议不能为空！");
   		       return
   		    }
   		    
        	form.submit();
        }
    }); 
	
});

</script>
</head>
	<body style="margin: 10px; margin-bottom: 200px;">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/businessCirclesAppealOrder/addfwProcessingResults.shtml" >
		<input type="hidden" id="id" name="id" value="${businessCirclesAppealOrder.id}" />
		<table class="gridtable">
		    <tr>
				<td class="title">处理结果<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				<input type="radio" name="appealStatus" value="4">未结案移交工商
				&nbsp;&nbsp;
                <input type="radio" name="appealStatus" value="3">内部结案移交工商
                &nbsp;&nbsp;
                <input type="radio" name="appealStatus" value="5">驳回跟进
                </td>
			</tr>
			<tr>
				<td class="title">法务处理建议<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea id="fwResult" name="fwResult" rows="5" cols="60" class="text" ></textarea>
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