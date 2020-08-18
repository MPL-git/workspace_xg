<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
	.radioClass{margin-right: 20px;}
	.hidden{display:none;}
</style>

<script type="text/javascript">
var submitting;
var viewerPictures; 
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
});

function viewerPic(imgPath){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
}

function picArchive(id,archiveStatus){
	$.ajax({ 
		url : "${pageContext.request.contextPath}/mchtLicenseChg/archive.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"id":id,"archiveStatus":archiveStatus},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
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

</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" id="id" name="id" value="${mchtLicenseChg.id}">
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">经营许可证</td>
				<td  colspan="2" align="left" class="l-table-edit-td">
					<img style="width: 120px;height: 120px;" src="${pageContext.request.contextPath}/file_servelt${mchtLicenseChg.businessLicensePic}" onclick="viewerPic(this.src);">
					<div>
						<span>
							<input type="radio" name="archiveStatus" value="0" <c:if test='${empty hideRadio}'>onclick="picArchive(${mchtLicenseChg.id},0);" </c:if><c:if test="${mchtLicenseChg.archiveStatus ne '3'}">checked="checked"</c:if>>未齐
						</span>
						<span>
							<input type="radio" name="archiveStatus" value="1" <c:if test='${empty hideRadio}'>onclick="picArchive(${mchtLicenseChg.id},1);" </c:if><c:if test="${mchtLicenseChg.archiveStatus eq '3'}">checked="checked"</c:if>>已归
						</span>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
</html>
