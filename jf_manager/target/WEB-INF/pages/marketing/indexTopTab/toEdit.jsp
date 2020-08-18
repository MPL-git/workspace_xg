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
	
	$("#type").bind('change',function(){
		var type = $(this).val();
		if(type == 1){
			$("#productTypeTr").show();
			$("#keywordTr").hide();
		}else if(type == 2){
			$("#productTypeTr").hide();
			$("#keywordTr").show();
		}else if(type == 3){
			$("#productTypeTr").hide();
			$("#keywordTr").hide();
		}
	});	
	
});

function save(){
	var id = $("#id").val();
	var type = $("#type").val();
	if(!type){
		commUtil.alertError("请选择类型");
		return;
	}
	var productTypeId = $("#productTypeId").val();
	var keyword = $("#keyword").val().trim();
	if(type == 1){
		if(!productTypeId){
			commUtil.alertError("请选择特卖一级分类");
			return;
		}
	}
	if(type == 2){
		if(!keyword){
			commUtil.alertError("关键词不能为空");
			return;
		}
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/marketing/indexTopTab/save.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {id:id,type:type,productTypeId:productTypeId,keyword:keyword},
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
	<input type="hidden" id="id" name="id" value="${indexTopTab.id}">
	<table class="gridtable">
		<tr>
			<td class="title">类型</td>
			<td align="left" class="l-table-edit-td">
				<select id="type" name="type">
	               	<option value="">请选择</option>
	               	<option value="1" <c:if test="${1 eq indexTopTab.type}">selected="selected"</c:if>>特卖</option>
	               	<option value="2" <c:if test="${2 eq indexTopTab.type}">selected="selected"</c:if>>商城</option>
	               	<option value="3" <c:if test="${3 eq indexTopTab.type}">selected="selected"</c:if>>特卖预告</option>
               	</select>
			</td>
		</tr>
		<tr id="productTypeTr" <c:if test="${empty indexTopTab || indexTopTab.type ne 1}">style="display:none;"</c:if>>
			<td class="title">特卖一级分类</td>
			<td align="left" class="l-table-edit-td">
				<select id="productTypeId" name="productTypeId">
					<c:forEach items="${productTypes}" var="productType">
	               		<option value="${productType.id}" <c:if test="${productType.id eq indexTopTab.productTypeId}">selected="selected"</c:if>>${productType.name}</option>
					</c:forEach>
               	</select>
			</td>
		</tr>
		<tr id="keywordTr" <c:if test="${empty indexTopTab || indexTopTab.type ne 2}">style="display:none;"</c:if>>
			<td class="title">关键词</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="keyword" name="keyword" value="${indexTopTab.keyword}"/>
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
