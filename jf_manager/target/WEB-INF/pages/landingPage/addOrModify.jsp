<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.4.4.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	

<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
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
table.gridtable td:nth-child(5) {
	position: relative;
    padding-bottom: 25px;
}
.l-table-edit-td {
	padding-bottom: 50px;
}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
	if(${picLength}>=6){
		$('#buttons').hide();
	}
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {});

	$("body").click(function(event){
		if($('#auditNewStartPics').find('li').length<6){
			$('#buttons').show();
		}
	});
	
	$("#confirm").bind('click',function(){
		var id = $("#id").val();
		var name = $("#name").val();
		var editorRecommend = $("#editorRecommend").val();
		var applicationInformation = $("#applicationInformation").val();
		var remarks = $("#remarks").val();
		if(name == null || name == ''){
			commUtil.alertError("落地页名称不能为空！");
			return;
		}
		if(editorRecommend == null || editorRecommend == ''){
			commUtil.alertError("小编推荐不能为空！");
			return;
		}
		if(applicationInformation == null || applicationInformation == ''){
			commUtil.alertError("应用信息不能为空！");
			return;
		}

		var dataJson = $("#form1").serializeArray();
		var landingPagePics = [];
		var lis = $("#auditNewStartPics").find("li");
		if(lis.length == 0){
			commUtil.alertError("应用截图不能为空！");
			return;
		}
		lis.each(function(index, item) {
			var path = $("img", item).attr("path");
			landingPagePics.push(path);
		});
		dataJson.push({"name":"landingPagePics","value":JSON.stringify(landingPagePics)});
		$.ajax({
			url : "${pageContext.request.contextPath}/landingPage/submit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : dataJson,
			timeout : 30000,
			success : function(data) {
				if ("200" == data.statusCode) {
					$.ligerDialog.alert('提交成功！', function (yes) { 
					$(window.parent.document).find("#searchbtn").click();
					frameElement.dialog.close();});		
				}else{
					$.ligerDialog.error(data.message);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
});

//图片格式验证
function ajaxFileUploadImg(_this) {
	var file = document.getElementById(_this.id).files[0];
	if(!/image\/(PNG|png|JPG|jpg|JPEG|jpeg)$/.test(file.type)){  
    	commUtil.alertError("图片格式错误，仅支持.jpg .png");
        return;
    }
    var size = file.size;
    if(size > 200*1024 ) {
		commUtil.alertError("图片大小不能超过200K");
		return;
    }
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
			if(this.width != '750' || this.height != '1334') {
				commUtil.alertError("请上传750×1334的图片");
			}else{
				ajaxFileUpload(_this);
			}
        };
        image.src = e.target.result;
    }
    reader.readAsDataURL(file);  
}

function ajaxFileUpload(_this) {
	var id = $(_this).attr("id");
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: id,
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#auditNewStartPics").append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');
				$(".del").live('click',function(){
					$(this).closest("li").remove();
					if($('#auditNewStartPics').find('li').length<6){
						$('#auditNewStartPics').next().show();
					}
				});
				//当上传图片张数>6时，隐藏【上传图片】按钮
				var lis =  $('#auditNewStartPics').find('li');
				if(lis.length>=6){
					$('#auditNewStartPics').next().hide();
				}
				//对viewer进行重新赋值
				viewerPictures.destroy();
				viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {});
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
	<form method="post" id="form1" name="form1">
	<div class="title-top">
	<input type="hidden" id="id" name="id" value="${landingPage.id}">
		<table class="gridtable">
          	<tr>
          		<td class="title"><span style="color:red;">*</span>落地页名称</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:230px;" type="text" id="name" name="name" maxlength="200" value="${landingPage.name}" />
				</td>
          	</tr>
			<tr>
				<td class="title"><span style="color:red;">*</span>小编推荐</td>
				<td><textarea rows="6" cols="60" id="editorRecommend" name="editorRecommend" maxlength="200" style="resize: none;">${landingPage.editorRecommend}</textarea>
			</tr>
			<tr>
				<td class="title"><span style="color:red;">*</span>应用信息</td>
				<td><textarea rows="10" cols="60" id="applicationInformation" name="applicationInformation" maxlength="500" style="resize: none;">${landingPage.applicationInformation}</textarea>
			</tr>
          	<tr>
				<td class="title"><span style="color:red;">*</span>应用截图</td>
				<td align="left" class="l-table-edit-td" id="viewer-pictures" style="height: 153px;width: 890px;">
					<t:imageList name="auditNewStartPics" list="${landingPagePicsList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 120px;margin: 10px;" id="buttons">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="NewStartPics" name="file" onchange="ajaxFileUploadImg(this);" />
						<input type="button" style="width: 70px;" value="上传图片" />
					</div>
					<div>
						<span style="width:450px;position:absolute;bottom:27%;left:17%;">图片的格式为：.JPG，PNG  尺寸要求：宽高为750×1334像素，大小不超过200K</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">备注</td>
				<td><textarea rows="6" cols="60" id="remarks" name="remarks" maxlength="500" style="resize: none;">${landingPage.remarks}</textarea>
			</tr>
          	<tr>
          		<td class="title">操作</td>
          		<td>
					<input id="confirm" type="button" style="float:left;" class="l-button l-button-test" value="保存"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="关闭" class="l-button l-button-test" onclick="frameElement.dialog.close();"/>
				</td>
          	</tr>
        </table>
	</div>
	</form>
</body>
</html>
