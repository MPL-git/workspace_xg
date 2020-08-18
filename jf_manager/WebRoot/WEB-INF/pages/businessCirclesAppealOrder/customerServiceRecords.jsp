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

$(function() {
	$(".dateEditor").ligerDateEditor({
		showTime : true,
		format : "yyyy-MM-dd",
		labelAlign : 'left',
		width : 200
	});
	
 });
 

//附件
function ajaxFileUpload(obj) {
    var file = obj.files[0];
    var filesize = file.size;
	var fileName = file.name;
	var rFilter = ["jpg","bmp","png","gif","JPG","BMP","PNG","GIF","mp3","wav","wma","ogg","ape","acc","MP3","WAV","WMA","OGG","APE","ACC","avi","mp4","mov","rm","wma","mkv","rmvb","AVI","MP4","MOV","RM","WMA","MKV","RMVB","doc","docx","xls","xlsx","ppt","pptx","pdf","rar","zip","DOC","DOCX","XLS","XLSX","PPT","PPTX","PDF","RAR","ZIP"];
 	var suffix = file.name.substring(fileName.lastIndexOf(".")+1);
	if($.inArray(suffix,rFilter)==-1 || filesize>1024*1024*100){
		commUtil.alertError("文件格式不正确或附件总大小超过100M!！");
		return;
	}
	$("#filePathDesc").text(fileName);
    var reader = new FileReader();  
    reader.onload = function(e) {
    	$.ajaxFileUpload({
    		url: '${pageContext.request.contextPath}/service/common/fileUpload.shtml?fileType=8',
    		secureuri: false,
    		fileElementId: "uploadFile",
    		dataType: 'json',
    		success: function(result, status) {
    			if(result.RESULT_CODE == 0){
    				var filePath = result.FILE_PATH;
    				$("#filePath").val(filePath);	
    				commUtil.alertSuccess("文件上传成功");
   			  	}else{
   			  		alert(result.RESULT_MESSAGE);
   			  	}
    		},
    		error: function(e) {
    			alert("服务异常");
    		}
    	});
    };
    reader.readAsDataURL(file);  
}
 
 
$(function(){
	$("#form1").validate({
        submitHandler: function(form) {  
    		var mchtDealDate = $.trim($("#mchtDealDate").val());
    		var mchtComplain = $.trim($("#mchtComplain").val());
    		var mchtProcessingProgress =$.trim($("#mchtProcessingProgress").val());
    		var platformDealDate =$.trim($("#platformDealDate").val());
    		var platformProcessingProgress =$.trim($("#platformProcessingProgress").val());
    		var filePath =$.trim($("#filePath").val());
    		if(mchtDealDate == '') {
        		commUtil.alertError("商家首次处理时间不能为空！");
        		return;
        	}
        	if(mchtComplain == '') {
        		commUtil.alertError("商家方案或诉求不能为空！");
        		return;
        	}
        	if(mchtProcessingProgress=='') {
        	    commUtil.alertError("商家处理进度不能为空！");
    			return;
        	}
        	if(platformDealDate=='') {
        	    commUtil.alertError("平台首次处理时间不能为空！");
    			return;
        	}
        	if(platformProcessingProgress=='') {
        	    commUtil.alertError("平台处理进度不能为空！");
    			return;
        	}
        	if(filePath=='') {
        	    commUtil.alertError("上传附件不能为空！");
    			return;
        	}
        	form.submit();
        }
    }); 
	
}); 

$(function(){
	$("a[name='downLoadcustomerServiceRecordsAttachment']").bind('click',function(){
		var customerServiceRecordsFilesId = $(this).attr("customerServiceRecordsFilesId");
		var filePath = $(this).attr("filePath");
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/businessCirclesAppealOrder/checkFileExists.shtml',
			data: {"customerServiceRecordsFilesId":customerServiceRecordsFilesId},
			dataType: 'json',
			success: function(data){
				if(data == null || data.code != 200){
			    	commUtil.alertError(data.msg);
			  	}else{
			  		location.href="${pageContext.request.contextPath}/customerServiceRecordsFilesId/downLoadcustomerServiceRecordsAttachment.shtml?filePath="+filePath;
			  	}
			},
			error: function(e){
			 	commUtil.alertError("系统异常请稍后再试！");
			}
		});
	});
	
});


</script>

</head>
	<body style="margin: 10px; margin-bottom: 200px;">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/businessCirclesAppealOrder/addcustomerServiceRecords.shtml" >
		<input type="hidden" id="businessCirclesAppealOrderid" name="businessCirclesAppealOrderid" value="${businessCirclesAppealOrder.id}" />
		<input type="hidden" id="id" name="id" value="${customerServiceRecords2.id}" />
		<input type="hidden" id="filePath" name="filePath" value=""/>
		<h2>商家处理</h2>
		<br>
		<table class="gridtable">
			<tr>
				<td class="title">商家首次处理时间<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					 <input type="text" class="dateEditor" id="mchtDealDate" name="mchtDealDate" value="<fmt:formatDate value="${customerServiceRecords2.mchtDealDate}" pattern="yyyy-MM-dd"/>">
				</td>
			</tr>
			<tr>
				<td class="title">商家方案或诉求<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea id="mchtComplain" name="mchtComplain" rows="5" cols="50" class="text" >${customerServiceRecords2.mchtComplain}</textarea>
				</td>
			</tr>
			<tr>
				<td class="title">商家处理进度<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea id="mchtProcessingProgress" name="mchtProcessingProgress" rows="5" cols="50" class="text" >${customerServiceRecords2.mchtProcessingProgress}</textarea>
				</td>
			</tr>
		</table>
		<br>
		<br>
		<h2>平台处理</h2>
		<br>
		<table class="gridtable">
			<tr>
				<td class="title">平台首次处理时间<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					 <input type="text" class="dateEditor" id="platformDealDate" name="platformDealDate" value="<fmt:formatDate value="${customerServiceRecords2.platformDealDate}" pattern="yyyy-MM-dd"/>">
				</td>
			</tr>
			<tr>
				<td class="title">平台处理进度<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea id="platformProcessingProgress" name="platformProcessingProgress" rows="5" cols="50" class="text" >${customerServiceRecords2.platformProcessingProgress}</textarea>
				</td>
			</tr>
			<c:if test="${staString eq '1'}">
			<tr>
				<td class="title">上传附件<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input style="position:absolute; opacity:0;" type="file" id="uploadFile" name="uploadFile" onchange="ajaxFileUpload(this);" />
					<input type="button" style="width: 45px;" value="上传">
					<span id="filePathDesc"></span>
					<span style="color:#CC0000;">(每个附件大小不超过100M)</span>
				</td>
			</tr>
			</c:if>
			<c:if test="${staString eq '0'}">
           	<tr>
               <td class="title">上传附件<span class="required">*</span></td>
               <td>
			<c:forEach var="customerServiceRecordsFiles" items="${customerServiceRecordsFiles}"> 
               <a href="javascript:;" name="downLoadcustomerServiceRecordsAttachment" customerServiceRecordsFilesId="${customerServiceRecordsFiles.id}" filePath="${customerServiceRecordsFiles.filePath}">${customerServiceRecordsFiles.filePath}</a>
			</c:forEach>
			   </td>	
			</tr>
			</c:if>
			<c:if test="${staString eq '1'}">
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
           	</c:if>
		</table>  
	</form>
	</body>
</html>