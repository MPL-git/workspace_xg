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
	$("#confirm").bind('click',function(){
		if(submitting){
			return;
		}
		var id = ${mchtLicenseChg.id};
		var businessLicensePic = $("#businessLicensePic").val();
		var auditStatus = $("input[name='auditStatus']:checked").val();
		var licenseRejectReason = $("#licenseRejectReason").val();
		if(!auditStatus){
			commUtil.alertError("请选择审核状态");
			return;
		}
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtLicenseChg/audit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"businessLicensePic":businessLicensePic,"auditStatus":auditStatus,"licenseRejectReason":licenseRejectReason},
			timeout : 30000,
			success : function(data) {
				if(data.returnCode == '0000'){
					submitting = false;
					commUtil.alertSuccess("提交成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					submitting = false;
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
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
</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
		<table class="gridtable" style="width:1200px;">
			<tbody>
			<tr>
				<td class="title">行业执照</td>
				<td>
					<div>
						<img id="businessLicensePicImg" style="width: 120px;height: 120px" onclick="viewerPic(this.src);" src="${pageContext.request.contextPath}/file_servelt${mchtLicenseChg.businessLicensePic}">
					</div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="businessLicensePicFile" name="file" onchange="ajaxFileUploadLogo();" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<input id="businessLicensePic" name="businessLicensePic" type="hidden" value="${mchtLicenseChg.businessLicensePic}">
				</td>
			</tr>
			<tr>
				<td class="title">审核状态</td>
				<td>
					<input type="radio" name="auditStatus" value="1" >通过
					<input type="radio" name="auditStatus" value="2" >驳回
				</td>
			</tr>
			<tr>
				<td class="title">备注及驳回原因</td>
				<td>
					<textarea rows="7" cols="70" id="licenseRejectReason" name="licenseRejectReason">
					</textarea>
				</td>
			</tr>
			<tr>
	          	<td class="title">操作</td>
	          	<td>
					<input id="confirm" type="button" style="float:left;width: 70px;" class="l-button l-button-test" value="提交"/>
					<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 10px;" onclick="frameElement.dialog.close();" />
				</td>
	        </tr>
			</tbody>
		</table>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
</html>
