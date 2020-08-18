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
		$("#sprType").bind("change", function(){
			var sprType = $("#sprType option:selected").val();
			if(sprType == 15 || sprType == 16 || sprType == 17 || sprType == 18 || sprType == 19 || sprType == 20 ) {
				$("#sprValue-div").show();
			}else {
				$("#sprValue-div").hide();
			}
		});
		
	});
	function funSubmit() {
		var weixinXcxSprChnlId = $("#weixinXcxSprChnlId").val();
		var sprPlanName = $("#sprPlanName").val();
		var sprType = $("#sprType").val();
		var sprValue = $("#sprValue").val();
		var pic = $("#pic").val();
		if(weixinXcxSprChnlId == '') {
			commUtil.alertError("渠道不能为空~");
			return;
		} 
		if(sprPlanName == '') {
			commUtil.alertError("投放广告位置不能为空~");
			return;
		} 
		if(sprPlanName.length > 50) {
			commUtil.alertError("投放广告位置只能1-50个字符~");
			return;
		}
		if(sprType == 15 || sprType == 16 || sprType == 17 || sprType == 18 || sprType == 19 || sprType == 20 ) {
			if(sprValue == '') {
				commUtil.alertError("页面ID不能为空~");
				return;
			}
		}
		if(pic == '') {
			commUtil.alertError("缩略图不能为空~");
			return;
		}
		if(submitFlag){
    		submitFlag = false;
    		$("#form1").submit();
    	}
	}
	
	//图片格式验证
	function ajaxFileUploadImg(statusImg) {
		var file = document.getElementById(statusImg).files[0];  
        if(!/image\/(JPG|jpg|JPEG|jpeg|PNG|png)$/.test(file.type)){  
        	commUtil.alertError("缩略图格式限制为.JPG或.PNG");
            return;
        }
        if(statusImg == 'picFile') {
	        var size = file.size;
	        if(size > 100*1024 ) {
	        	commUtil.alertError("缩略图大小不能超过100kb");
	            return;
	        }
        }
        ajaxFileUploadPicFile(statusImg);
	}
	
	//图片上传
	function ajaxFileUploadPicFile(statusImg) {
        $.ajaxFileUpload({
        	url: '${pageContext.request.contextPath}/service/common/ajax_upload.shtml?fileType=13',
			secureuri: false,
			fileElementId: statusImg,
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
            		if(statusImg == 'picFile') {
            			$("#pic").val(result.FILE_PATH);
    					$("#picImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
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
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/weixinXcxSprDtl/addWeixinXcxSprDtl.shtml" >
		<input type="hidden" id="pic" name="pic" value="" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">渠道<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="weixinXcxSprChnlId" name="weixinXcxSprChnlId" style="width: 164px;" >
						<c:forEach var="weixinXcxSprChnl" items="${weixinXcxSprChnlList }">
							<option value="${weixinXcxSprChnl.id }">${weixinXcxSprChnl.sprChnlName }</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">投放广告位置<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="sprPlanName" name="sprPlanName" />
				</td>
           	</tr>
           	
           	<tr height="100px;">
            	<td class="title" width="20%">缩略图<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 120px;height: 80px;border: 1px solid #6B6B6B;">
						<img src="" id="picImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="picFile" name="file" onchange="ajaxFileUploadImg('picFile');" />
					</div>
			 		<span style="color: #6B6B6B;">图片格式为：.JPG、.PNG，大小不超过100kb</span>
				</td>
           	</tr>
           	
           	
           	<tr>
            	<td class="title" width="20%">页面类型<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="sprType" name="sprType" style="width: 164px;" >
						<c:forEach var="sprType" items="${sprTypeList }">
							<option value="${sprType.statusValue }">${sprType.statusDesc }</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
           	<tr id="sprValue-div" style="display: none;">
            	<td class="title" width="20%">页面ID<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="sprValue" name="sprValue" />
					</br><span class="activity-hint" style="color: red;" >温馨提示：</span>
					</br><span class="activity-hint" >商品详情页：页面ID填写商品ID（长ID）</span>
					</br><span class="activity-hint" >特卖详情页：页面ID填写特卖ID</span>
					</br><span class="activity-hint" >会场详情页：页面ID填写会场ID</span>
					</br><span class="activity-hint" >商家店铺：页面ID填写商家序号</span>
					</br><span class="activity-hint" >特卖分类：页面ID填写特卖一级类目ID</span>
					</br><span class="activity-hint" >自建页面：页面ID填写自建页面ID</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" class="l-button l-button-submit" onclick="funSubmit();" style="width: 100px;" value="保存并生成链接" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>