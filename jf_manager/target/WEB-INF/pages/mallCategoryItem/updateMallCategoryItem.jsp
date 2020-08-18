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
<style type="text/css">
 	.radioClass {
		margin-right: 20px;
	}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
    .activity-time {
    	margin-left: 50px;
    }
    .activity-hint {
    	color: #6B6B6B;
    }
</style>
<script type="text/javascript">
	var submitFlag = true;
	$(function(){
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 160
		});
		
		$.metadata.setType("attr", "validate");
		$("#form1").validate({
	        errorPlacement: function (lable, element) {  
	        	var elementType=$(element).attr("type");
	        	if('radio'==elementType) {
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").parent("span").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else {
	        		$(element).ligerTip({
						content : lable.html(),width: 100
					});
	        	}
	        	$(".l-verify-tip-corner").css("z-index", "1001");
	        	$(".l-verify-tip-content").css("z-index", "1000");
	        },
	        success: function (lable,element) {
	            lable.ligerHideTip();
	            lable.remove();
	        },
	        submitHandler: function(form) {
	        	if($("#itemPic").val() == '') {
	        		if('${mallCategoryItem.itemLinkType }' == '1') {
		        		commUtil.alertError("品牌图片不能为空！"); 
	        		}else if('${mallCategoryItem.itemLinkType }' == '2') {
	        			commUtil.alertError("三级分类图片不能为空！"); 
	        		}
	        		return;
	        	}
	        	if(submitFlag){
	        		submitFlag = false;
	        		form.submit();
	        	}
	        }
	    }); 
		
	});
	
	//图片格式验证
	function ajaxFileUploadImg(statusImg) {
		var file = document.getElementById(statusImg).files[0];  
        /* if(!/image\/(JPG|jpg|JPEG|jpeg)$/.test(file.type)){  
        	commUtil.alertError("图片格式不正确！");
            return;
        } */
        if(statusImg == 'itemPicFile') {
	        var size = file.size;
	        if(size > 200*1024 ) {
	        	commUtil.alertError("图片过大，请重新上传！");
	            return;
	        }
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() {
        		var widthStr = '200'; 
        		var heightStr = '200'; 
        		if(statusImg == 'itemPicFile') {
        			widthStr = '328';
        			heightStr = '328';
        		}
        		if(this.width != widthStr || this.height != heightStr) {
        			commUtil.alertError("图片像素不是"+widthStr+"*"+heightStr+"像素！");
            	}else{
            		ajaxFileUploadPicFile(statusImg);
            	}
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);  
	}
	
	//图片上传
	function ajaxFileUploadPicFile(statusImg) {
        $.ajaxFileUpload({
        	url: '${pageContext.request.contextPath}/service/common/ajax_upload.shtml?fileType=9',
			secureuri: false,
			fileElementId: statusImg,
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					if(statusImg == 'itemPicFile') {
						$("#itemPic").val(result.FILE_PATH);
						$("#itemPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
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

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/mallCategoryItem/updateMallCategoryItemBrand.shtml" >
		<input type="hidden" id="mallCategoryItemId" name="mallCategoryItemId" value="${mallCategoryItemId }" >
		<input type="hidden" id="itemPic" name="itemPic" value="${mallCategoryItem.itemPic }" />
		<table class="gridtable">
			<!-- 1 品牌 -->
           	<c:if test="${mallCategoryItem.itemLinkType  == '1'}">
           		<tr>
	            	<td class="title" width="20%">品牌名称</td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:160px;" type="text" id="itemName" name="itemName" value="${mallCategoryItem.itemName }" readonly="readonly" />
					</td>
	           	</tr>
				<tr height="100px;">
	            	<td class="title" width="20%">品牌图片<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td">
						<div style="position: relative;width: 75px;height: 75px;border: 1px solid #6B6B6B;">
							<img src="${pageContext.request.contextPath}/file_servelt${mallCategoryItem.itemPic}" id="itemPicImg" alt="" style="width: 100%;height: 100%;display: block;">
							<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="itemPicFile" name="file" onchange="ajaxFileUploadImg('itemPicFile');" />
						</div>
				 		<span style="color: #6B6B6B;">图片的格式为：尺寸要求：328*328像素，大小不超过200kb</span>
					</td>
	           	</tr>
           	</c:if>
           	<!-- 2 类目 -->
           	<c:if test="${mallCategoryItem.itemLinkType  == '2'}">
           		<tr>
	            	<td class="title" width="20%">三级分类名称<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:160px;" type="text" id="itemName" name="itemName" value="${mallCategoryItem.itemName }" validate="{required:true,rangelength:[2,15]}" />
					</td>
	           	</tr>
				<tr height="100px;">
	            	<td class="title" width="20%">三级分类图片<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td">
						<div style="position: relative;width: 75px;height: 75px;border: 1px solid #6B6B6B;">
							<img src="${pageContext.request.contextPath}/file_servelt${mallCategoryItem.itemPic}" id="itemPicImg" alt="" style="width: 100%;height: 100%;display: block;">
							<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="itemPicFile" name="file" onchange="ajaxFileUploadImg('itemPicFile');" />
						</div>
				 		<span style="color: #6B6B6B;">图片的格式为：尺寸要求：328*328像素，大小不超过200kb</span>
					</td>
	           	</tr>
				<tr>
	            	<td class="title" width="20%">绑定类目<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td">
						<select style="width: 160px;" id="itemLinkValue" name="itemLinkValue" disabled="disabled" >
							<c:forEach var="productType" items="${productTypeList }" >
								<option value="${productType.id }" <c:if test="${mallCategoryItem.itemLinkValue == productType.id }">selected</c:if> >${productType.name }</option>
							</c:forEach>
						</select>
					</td>
	           	</tr>
           	</c:if>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	</body>
</html>