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
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("#activityBeginDate").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm:ss"
	});
	$("#activityEndDate").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm:ss"
	});
	
	
});

function save(){

	var bgPic = $("#bgPic").val();
	var param = $("#violateOrderForm").serialize();

		if(!bgPic){
		commUtil.alertError("背景图不能为空，请选择上传图片");
		return;
	}
	var activityBeginDate = $("#activityBeginDate").val();
	var activityEndDate = $("#activityEndDate").val();
	if(!activityBeginDate){
		commUtil.alertError("活动开始时间不能为空");
		return;
	}
	if(!activityEndDate){
		commUtil.alertError("活动结束时间不能为空");
		return;
	}
	if(activityBeginDate>=activityEndDate){
		commUtil.alertError("活动开始时间必须小于活动结束时间");
		return;
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/marketing/indexTopStyle/save.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : param,
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				commUtil.alertSuccess("保存成功");
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

function viewerPic(imgPath){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
}

//图片格式验证
function ajaxFileUploadImg(statusImg) {
	var file = document.getElementById(statusImg).files[0];
    if(!/image\/(JPG|jpg|JPEG|jpeg)$/.test(file.type)){  
    	commUtil.alertError("图片格式不正确！");
        return;
    }
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
    		var widthStr = '1242'; 
    		var heightStr = '311';
    		var type = ${type};
    		if(type == 1){
    			heightStr = '212';
    		}
    		if(this.width != widthStr || this.height != heightStr) {
    			commUtil.alertError("图片像素不是"+widthStr+"x"+heightStr+"像素！");
        	}else{
        		ajaxFileUploadPic(statusImg);
        	}
        };
        image.src = e.target.result;
    }
    reader.readAsDataURL(file);  
}

function ajaxFileUploadPic() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml?fileType=3',
		secureuri: false,
		fileElementId: "bgPicFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#bgPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
				$("#bgPicImg").show();
				$("#bgPic").val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}
</script>
<html>
<body>
<form id="violateOrderForm" method="post" action="${pageContext.request.contextPath}/indexTopStyle/addSave.shtml">
	<input type="hidden" id="id" name="id" value="${indexTopStyle.id}">
	<input type="hidden" id="type" name="type" value="${type}">
	<table class="gridtable">
		<tr>
			<td class="title">背景图：</td>
			<td align="left" class="l-table-edit-td">
				<div><img id="bgPicImg" style="width: 124.2px;height: 31.1px;<c:if test="${empty indexTopStyle.bgPic}">display: none;</c:if>" alt="" src="${pageContext.request.contextPath}/file_servelt${indexTopStyle.bgPic}"></div>
    			<div>
    			<input style="position:absolute; opacity:0;" type="file" id="bgPicFile" name="file" onchange="ajaxFileUploadImg('bgPicFile');" />
    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
    			</div>
    			<c:if test="${type == 0}">
	    			<div style="color: grey;">图片格式为.JPG 尺寸要求：1242*311PX</div>
    			</c:if>
    			<c:if test="${type == 1}">
    				<div style="color: grey;">图片格式为.JPG 尺寸要求：1242*212PX</div>
    			</c:if>
    			<input id="bgPic" name="bgPic" type="hidden" value="${indexTopStyle.bgPic}">
			</td>
		</tr>
		<tr>
			<td  class="title"  >字体颜色</td>
			<td align="left" class="l-table-edit-td">
				<input type="radio"  name="fontType" value="0" <c:if test= "${indexTopStyle.fontType == 0}">checked="checked"</c:if> >白色
				<input type="radio"  name="fontType" value="1" <c:if test= "${indexTopStyle.fontType == 1}">checked="checked"</c:if> >黑色
<%--				<select id = "fontType" name = "fontType">--%>
<%--					<option value="0" selected = "selected">白色</option>--%>
<%--					<option value="1">黑色</option>--%>
<%--				</select>--%>
			</td>
		</tr>
		<tr>
			<td class="title">活动开始时间：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="activityBeginDate" name="activityBeginDate" value="<fmt:formatDate value="${indexTopStyle.activityBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</td>
		</tr>
		<tr>
			<td class="title">活动结束时间：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="activityEndDate" name="activityEndDate" value="<fmt:formatDate value="${indexTopStyle.activityEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</td>
		</tr>
		<tr>
			<td class="title">操作</td>
			<td>
				<input type="button" class="l-button l-button-submit" value="提交" onclick="save();"/>
			</td>
		</tr>
	</table>	
</form>		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
