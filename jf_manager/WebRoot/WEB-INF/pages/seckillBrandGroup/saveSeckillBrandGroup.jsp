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
    	margin-left: 20px;
    	color: #6B6B6B;
    }
</style>
<script type="text/javascript">
	$(function(){
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format: "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
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
						content : lable.html(),width: 200
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
	        	var entryPic =  $("#entryPic").val();
	        	var posterPic =  $("#posterPic").val();
	        	var beginDate = $("#beginDate").val();
	        	var beginTm = $("#beginTm").val();
	        	if(entryPic == '') {
	        		commUtil.alertError("入口背景图，不能为空！");
	   		    	return;
	        	}
	        	if(posterPic == '') {
	        		commUtil.alertError("海报图，不能为空！");
	   		    	return;
	        	}
	        	if(beginDate == '' || beginTm == '') {
	        		commUtil.alertError("请选择活动时间点！");
	   		    	return;
	        	}
	        	form.submit();
	        }
	    }); 
		
	});

	//图片格式验证
	function ajaxFileUploadImg(statusImg) {
		var file = document.getElementById(statusImg).files[0];
        if(!/image\/(JPG|jpg|JPEG|jpeg)$/.test(file.type)) {  
        	commUtil.alertError("图片格式不正确！");
            return;
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() {
        		var widthStr = '710'; 
        		var heightStr = '400'; 
        		if(statusImg == 'posterPicFile') {
        			widthStr = '800';
        			heightStr = '400';
        		}
        		if(this.width != widthStr || this.height != heightStr) {
        			commUtil.alertError("图片像素不是"+widthStr+"x"+heightStr+"像素！");
            	}else {
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
        	url: '${pageContext.request.contextPath}/service/common/ajax_upload.shtml?fileType=3',
			secureuri: false,
			fileElementId: statusImg,
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					if(statusImg == 'entryPicFile') {
						$("#entryPic").val(result.FILE_PATH);
						$("#entryPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
						$("#entryPicImgA").attr("href",'${contextPathStr }'+result.FILE_PATH);
            		}
            		if(statusImg == 'posterPicFile') {
            			$("#posterPic").val(result.FILE_PATH);
            			$("#posterPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
            			$("#posterPicImgA").attr("href",'${contextPathStr }'+result.FILE_PATH);
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
	<body style="margin: 10px; margin-bottom: 200px;">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/seckillBrandGroup/saveSeckillBrandGroup.shtml" >
		<input type="hidden" id="id" name="id" value="${seckillBrandGroup.id }" />
		<input type="hidden" id="entryPic" name="entryPic" value="${seckillBrandGroup.entryPic }" />
		<input type="hidden" id="posterPic" name="posterPic" value="${seckillBrandGroup.posterPic }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:260px;" id="name" name="name" type="text" value="${seckillBrandGroup.name }" validate="{required:true,maxlength:20}" />
					<span class="activity-hint" >注意：名称不能超过20个字符</span>
				</td>
           	</tr>
			
           	<tr height="100px;">
            	<td class="title" width="20%">入口背景图<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 142px;height: 80px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${seckillBrandGroup.entryPic}" id="entryPicImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="entryPicFile" name="file" onchange="ajaxFileUploadImg('entryPicFile');" />
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：710x400像素</span>
			 		<span style="margin-left: 10px;">
			 			<a href="${contextPathStr }${seckillBrandGroup.entryPic}" id="entryPicImgA" target="_blank" >【查看图片】</a>
			 		</span>
				</td>
           	</tr>
           	<tr height="100px;">
            	<td class="title" width="20%">海报图<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 150px;height: 75px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${seckillBrandGroup.posterPic}" id="posterPicImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="posterPicFile" name="file" onchange="ajaxFileUploadImg('posterPicFile');" />
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：800x400像素</span>
			 		<span style="margin-left: 10px;">
			 			<a href="${contextPathStr }${seckillBrandGroup.posterPic}" id="posterPicImgA" target="_blank" >【查看图片】</a>
			 		</span>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">活动时间点</td>
				<td align="left" class="l-table-edit-td" >
					<div>
						<input type="text" style="width: 135px;" <c:if test="${empty seckillBrandGroup.id }">class="dateEditor"</c:if>  id="beginDate" name="beginDate" value="${beginDate }" <c:if test="${not empty seckillBrandGroup.id }">disabled</c:if> >
					</div>
					<div style="margin-left: 150px;">
						<div class="search-td" style="position:relative;">
							<div class="search-td-combobox-condition" style="position:absolute; top:-22px;">
								<select id="beginTm" name="beginTm" style="height: 23px;width: 80px;" <c:if test="${not empty seckillBrandGroup.id }">disabled</c:if> >
									<option value="">请选择...</option>
									<c:forEach var="seckillTime" items="${seckillTimeList }">
										<option value="${seckillTime.startHours }:${seckillTime.startMin }" 
											<c:if test="${startHours == seckillTime.startHours and startMin == seckillTime.startMin }">selected</c:if>> 
											${seckillTime.startHours }:${seckillTime.startMin }
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
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