<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("#confirm").bind('click',function(){
		var activityAreaId = $("#activityAreaId").val();
		var topPic = $("#topPic").val();
		$.ajax({
			type:'POST',
			url:"${pageContext.request.contextPath}/activityArea/saveTopPic.shtml",
			data:{
				activityAreaId:activityAreaId,
				topPic:topPic
			},
			dataType:'json',
			cache: false,
			success: function(json){
			   if(json==null || json.statusCode!=200){
			     commUtil.alertError("提交失败，请稍后重试");
			  	}else{
			  		parent.location.reload();
			  		frameElement.dialog.close();
			  	}
			},
			error: function(e){
			 commUtil.alertError("系统异常请稍后再试");
			}
		});
	});
	
});

function ajaxFileUpload() {
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: "picFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#topPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
				$("#topPic").val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}

function viewerPic(imgPath){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
}
</script>

<html>
<body>
<form method="post" name="form" action="${pageContext.request.contextPath}/activityAreaNew/saveTopPic.shtml">
			<input type="hidden" id="activityAreaId" name="activityAreaId" value="${activityAreaId}">
			<div>
				<div style="float: left;height: 30px;">
					<input type="file" id="picFile" name="file" onchange="ajaxFileUpload();" style="position:absolute; opacity:0;width: 80px;height: 30px;cursor: pointer;">
					<a href="javascript:void(0);" style="width:30%;">上传图片</a>
				</div>
				<input type="button" style="float:right;width: 80px;height: 35px;cursor: pointer;" value="提交" id="confirm">
				<br>
				<br>
				<img src="${pageContext.request.contextPath}/file_servelt${topPic}" style="width: 750px;height: 500px;" id="topPicImg">
	    		<input id="topPic" name="topPic" type="hidden" value="${topPic}">
			</div>
</form>	  
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
