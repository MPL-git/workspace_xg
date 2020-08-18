<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select {
	border: 1px solid #AECAF0;
}


</style>
<script type="text/javascript">
	var submitStatus = true;
	$(function() {
		$.metadata.setType("attr", "validate");
		var v = $("#form1").validate({
			errorPlacement : function(lable, element) {
				console.log(lable[0].innerText);
				if ($(element).hasClass("l-text-field")) {
					$(element).parent().ligerTip({
						content : lable.html(),
						width : 100
					});
				} else {
					$(element).ligerTip({
						content : lable.html(),
						width : 100
					});
				}

			},
			success : function(lable, element) {
				lable.ligerHideTip();
				lable.remove();
			},
			submitHandler : function(form) {
				if(submitStatus) {
					submitStatus = false;
					mchtLicenseStatusSubmit();
				}
			}
		});

	});

	function mchtLicenseStatusSubmit() {
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/mchtLicenseStatus.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : $("#form1").serialize(),
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					parent.location.reload();
					frameElement.dialog.close();
				} else {
					$.ligerDialog.error(data.returnMsg);
				}

			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
	
	//图片格式验证
	function ajaxFileUploadImg(statusImg) {
		var file = document.getElementById(statusImg).files[0];  
        if(!/image\/(JPG|jpg|JPEG|jpeg)$/.test(file.type)){  
        	commUtil.alertError("图片格式不正确！");
            return;
        }
        if(statusImg == 'businessLicensePicFile') {
	        var size = file.size;
	        if(size > 3*1024*1024 ) {
	        	commUtil.alertError("图片过大，请重新上传！");
	            return;
	        }
        }
        ajaxFileUploadPicFile(statusImg);
	}
	
	//图片上传
	function ajaxFileUploadPicFile(statusImg) {
        $.ajaxFileUpload({
        	url: '${pageContext.request.contextPath}/service/common/ajax_upload.shtml?fileType=12',
			secureuri: false,
			fileElementId: statusImg,
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					if(statusImg == 'businessLicensePicFile') {
						$("#businessLicensePic").val(result.FILE_PATH);
						$("#businessLicensePicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
            		}
				} else {
					commUtil.alertError(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				commUtil.alertError("服务异常！");
			}
		});
	}
	
</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
		<input type="hidden" id="id" name="id" value="${mchtInfo.id }"> 
		<input type="hidden" id="businessLicensePic" name="businessLicensePic" value="${mchtInfo.businessLicensePic }"> 
		<table class="gridtable">
			<tr height="100px;">
            	<td class="title" width="20%">行业经营许可证</td>
				<td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 120px;height: 80px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.businessLicensePic }" id="businessLicensePicImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="businessLicensePicFile" name="file" onchange="ajaxFileUploadImg('businessLicensePicFile');" />
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，3M以内</span>
				</td>
           	</tr>
			<tr>
				<td class="title">状态<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<select style="width: 160px;" id="licenseStatus" name="licenseStatus" validate="{selectRequired:true}" >
						<c:forEach var="licenseStatus" items="${licenseStatusList }">
							<option <c:if test="${mchtInfo.licenseStatus == licenseStatus.statusValue }">selected</c:if> value="${licenseStatus.statusValue }" >
								${licenseStatus.statusDesc }
							</option>
						</c:forEach>
					</select>
					<span style="margin-left: 20px;">
						<label>
							<input type="checkbox" id="licenseIsMust" name="licenseIsMust" value="1" <c:if test="${mchtInfo.licenseIsMust == '1' }">checked</c:if> >必需上传行业执照
						</label>
					</span>
				</td>
			</tr>
			<tr>
				<td class="title">驳回原因</td>
				<td align="left" class="l-table-edit-td">
					<textarea rows="5" cols="50" id="licenseRejectReason" name="licenseRejectReason" >${mchtInfo.licenseRejectReason }</textarea>
				</td>
			</tr>
			<tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
