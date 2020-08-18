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
		$("#form1").validate({//表单验证
	        errorPlacement: function (lable, element) {
	         var name =  $("#name").val();
	         if (name=='') {
				element.ligerTip({ content: lable.html("品牌团名称未填写，请填写后重试!")}); 
			  }else{
	        	var elementType=$(element).attr("type");
	        	if('radio'==elementType) {
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").parent("span").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else {
	        		$(element).ligerTip({
						content : lable.html("品牌团名称不能超过20个中文，请重新输入!"),width: 200
					});
	        	} 
			  }  
	        },
	        success: function (lable,element) {
	            lable.ligerHideTip();
	            lable.remove();
	        },
	        submitHandler: function(form) {
	        	var entryPic =  $("#entryPic").val();
	        	var posterPic =  $("#posterPic").val();
	        	var productTypeId=$("#productTypeId").val();
	        	var position=$("#position").val();
	        	if(entryPic == '') {
	        		commUtil.alertError("入口背景图，不能为空！");
	   		    	return;
	        	}
	        	if(posterPic == '') {
	        		commUtil.alertError("海报图，不能为空！");
	   		    	return;
	        	}
				if (productTypeId=='') {
					commUtil.alertError("分类栏目，不能为空！");
					return;
				}
	        	if (position=='') {
					commUtil.alertError("显示位置，不能为空！");
					return;
				}
				if (!/^[1-9]\d*$/.test(position)) {
					commUtil.alertError("显示位置,请输入正整数！");
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
        		var widthStr = '800'; 
        		var heightStr = '522'; 
        		if(statusImg == 'posterPicFile') {
        			widthStr = '1080';
        			heightStr = '420';
        		}
        		if(this.width != widthStr || this.height != heightStr) {
        			commUtil.alertError("图片像素不是"+widthStr+"x"+heightStr+"像素！");
            	}else {
        			ajaxFileUploadPicFile(statusImg);
        		}
            };
            image.src = e.target.result;
        },
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
            		}
            		if(statusImg == 'posterPicFile') {
            			$("#posterPic").val(result.FILE_PATH);
            			$("#posterPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
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
	
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
});

function viewerPic(imgPath){//点击查看大图	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	$("#viewer-pictures").show();
	viewerPictures.show();	
}
</script>

</head>
	<body style="margin: 10px; margin-bottom: 200px;">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/activitybrandGroupadd/activitybrandGroupadds.shtml" >
		<input type="hidden" id="id" name="id" value="${activitybrandGroup.id}" />
		<input type="hidden" id="entryPic" name="entryPic" value="${activitybrandGroup.entryPic }" />
		<input type="hidden" id="posterPic" name="posterPic" value="${activitybrandGroup.posterPic }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:260px;" id="name" name="name" type="text" value="${activitybrandGroup.name }" validate="{required:true,maxlength:20}" />
					<span class="activity-hint" >注意：品牌团名称不能超过20个中文</span>
				</td>
           	</tr>
			
           	<tr height="100px;">
            	<td class="title" width="20%">入口背景图<span class="required">*</span></td>
				<%-- <td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 150px;height: 80px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${activitybrandGroup.entryPic}" id="entryPicImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="entryPicFile" name="file" onchange="ajaxFileUploadImg('entryPicFile');" />
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：800x522像素</span>
				</td> --%>
				<td>
				  <div style="width: 160px;height: 120px;">
	    			<div style="position: relative;width: 150px;height: 80px;border: 1px solid #6B6B6B;"><img id="entryPicImg" style="width: 100%;height: 100%;display: block;" alt="" src="${pageContext.request.contextPath}/file_servelt${activitybrandGroup.entryPic}" onclick="viewerPic(this.src)"></div>
	    			<div style="float: left;margin: 10px;">
	    				<input style="position:absolute; opacity:0;width: 60px;" type="file" id="entryPicFile" name="file" onchange="ajaxFileUploadImg('entryPicFile');"/>
						<a href="javascript:void(0);">上传图片</a>
	    			</div>
	    			<input id="entryPicFile" name="entryPicFile" type="hidden" value="${activityGroup.entryPic}">
	    		  </div>
	    		  <span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：800x522像素</span>
	    	    </td>  		
           	</tr>
           	<tr height="100px;">
            	<td class="title" width="20%">海报图<span class="required">*</span></td>
				<%-- <td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 150px;height: 80px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${activitybrandGroup.posterPic}" id="posterPicImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="posterPicFile" name="file" onchange="ajaxFileUploadImg('posterPicFile');" />
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：800x400像素</span>
				</td> --%>
				<td>
				<div style="width: 160px;height: 120px;">
	    			<div style="position: relative;width: 150px;height: 80px;border: 1px solid #6B6B6B;"><img id="posterPicImg" style="width: 100%;height: 100%;display: block;" alt="" src="${pageContext.request.contextPath}/file_servelt${activitybrandGroup.posterPic}" onclick="viewerPic(this.src)"></div>
	    			<div style="float: left;margin: 10px;">
	    				<input style="position:absolute; opacity:0;width: 60px;" type="file" id="posterPicFile" name="file" onchange="ajaxFileUploadImg('posterPicFile');"/>
						<a href="javascript:void(0);">上传图片</a>
	    			</div>
	    			<input id="posterPicFile" name="posterPicFile" type="hidden" value="${aactivitybrandGroup.posterPic}">
	    		</div>
	    		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：1080x420像素</span>
	    	   </td>		
           	</tr>
           	<tr>
           	    <td class="title" width="20%">分类栏目<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
              	<select id="productTypeId" name="productTypeId" style="width: 135px;">
						<option value=''>请选择...</option>
						<c:forEach var="productType" items="${producttypeList}">
							<option value="${productType.id}" <c:if test="${productType.id==activitybrandGroup.appCatalog}">selected</c:if>>${productType.name}</option>
						</c:forEach>
						<option value='0' <c:if test="${activitybrandGroup.appCatalog eq '0'}">selected</c:if>>预告</option>
				</select>
               	<span class="activity-hint" >提示：选择栏目后，品牌团将在该分类栏目下显示</span>
            	</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">展示位置<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:135px;" id="position" name="position" type="text" value="${activitybrandGroup.position}"/>
					<span class="activity-hint" >提示：只能输入正整数；若输入3，则显示在第3个特卖活动后面</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="保存" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="取消" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	</body>
</html>