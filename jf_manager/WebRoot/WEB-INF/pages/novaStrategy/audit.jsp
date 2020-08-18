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
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {});
	 
	$("body").click(function(event){
		if($('#auditNewStartPics').find('li').length<5){
			$('#buttons').show();
		}
	});
	
	if(${picLength}>=5){	
		$('#buttons').hide();
	}
	
	$("#confirm").bind('click',function(){
		var ids = $("#withdrawOrderIds").val();
		var status =$("input[name='status']:checked").val();
		var yyRejectReason = $("#yyRejectReason").val();
		var yy = $("#yy").val();
		//审核结果==审核驳回时，原因说明必填未填写点击提交，弹窗提示“原因说明不可为空！”
		if(status == '0' && !yyRejectReason){
			commUtil.alertError("驳回理由不可为空");
			return;
		}
		var dataJson = $("#form1").serializeArray();
		var withdrawOrderPics = [];
		var lis = $("#auditNewStartPics").find("li");
		lis.each(function(index, item) {
			var path = $("img", item).attr("path");
			withdrawOrderPics.push(path);
		});
		dataJson.push({"name":"withdrawOrderPic","value":JSON.stringify(withdrawOrderPics)});
		dataJson.push({"name":"withdrawOrderIds",value:JSON.stringify(ids)});
		dataJson.push({"name":"yy",value:JSON.stringify(yy)});
		$.ajax({
			url : "${pageContext.request.contextPath}/newStart/auditSubmit.shtml",
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
    	commUtil.alertError("请上传格式为.jpg或.png的图片！");
        return;
    }
    var size = file.size;
    if(size > 200*1024 ) {
    	commUtil.alertError("图片大小不能超过200k!");
      	return;
    }
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
        	ajaxFileUpload(_this);
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
				$("#audit"+id).append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');
				$(".del").live('click',function(){
					$(this).closest("li").remove();
					if($('#auditNewStartPics').find('li').length<5){
						$('#auditNewStartPics').next().show();
					}
				});
				//当上传图片张数>5时，隐藏【上传图片】按钮
				var lis =  $('#auditNewStartPics').find('li');
				if(lis.length>=5){
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
	<input type="hidden" id="withdrawOrderIds" value="${ids}">
	<input type="hidden" id="yy" value="${yy}">
		<table class="gridtable">
		    <tr>
          		<td class="title">选中记录</td>
          		<td>${total}</td>
          	</tr>
          	<tr>
          		<td class="title">审核结果<span style="color:red;">*</span></td>
          		<td><input type="radio" name="status" value="1" <c:if test="${type=='1'}">checked=checked"</c:if>>
          		<c:choose>
          			<c:when test="${yy=='0'}">
          			审核通过
          			</c:when>
          			<c:when test="${yy=='1'}">
          			财审通过
          			</c:when>
          		</c:choose>
          		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          		<input type="radio" name="status" value="0" <c:if test="${type=='2'}">checked="checked"</c:if>>
          		<c:choose>
          			<c:when test="${yy=='0'}">
          			审核驳回
          			</c:when>
          			<c:when test="${yy=='1'}">
          			财审驳回
          			</c:when>
          		</c:choose>
          		</td>
          	</tr>
          	<tr>
          		<td class="title">驳回理由<span style="color:red;">*</span></td>
          		<td><textarea rows="5" cols="30" id="yyRejectReason" name="yyRejectReason" maxlength="256"><c:if test="${yy=='0'}">${withdrawOrder.yyRejectReason}</c:if><c:if test="${yy=='1'}">${withdrawOrder.cwRejectReason}</c:if></textarea>
          		<span style="position: relative;display: inline-block;
    vertical-align: top;"><div style="display: inline-block;
    vertical-align: top;width:170px;">温馨提示：驳回理由将会展示给用户查看</div><br><br><br><div id="wordPrompt" style="color:red;">最多可输入256字！</div>
          		</span></td>
          	</tr>
          	<tr>
          		<td class="title">内部备注</td>
          		<td><textarea rows="5" cols="30" id="yyInnerRemarks" name="yyInnerRemarks" maxlength="256"><c:if test="${yy=='0'}">${withdrawOrder.yyInnerRemarks}</c:if><c:if test="${yy=='1'}">${withdrawOrder.cwInnerRemarks}</c:if></textarea>
          		<span style="position: relative;display: inline-block;
    vertical-align: top;">
          		<div style="display: inline-block;
    vertical-align: top;width:170px;">温馨提示：内部备注与图片，仅供后台查看，不展示给用户</div><br><br><br><div id="wordPrompt" style="color:red;">最多可输入256字！</div>
          		</span>
          		</td>
          	</tr>
          	<tr>
				<td  colspan="1" class="title">图片</td>
				<td  colspan="3" align="left" class="l-table-edit-td" style="position:relative;padding-bottom:25px" id="viewer-pictures">
			    	<t:imageList name="auditNewStartPics" list="${withdrawOrderPicList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 105px;margin: 10px;" id="buttons">
						<input class="td-input" style="position:absolute; bottom: 10px;left: 18px;opacity:0;width: 110px;z-index:9"" type="file" id="NewStartPics" name="file" onchange="ajaxFileUploadImg(this);" /> 
						<input type="button" style="width: 70px;position: absolute;bottom: 10px;left: 18px;"value="上传图片" /> 
						<span style="width:295px;position:absolute;bottom:10px;left:90px;">(大小限制为200k,格式：jpg,png,最多可上传5张)</span>
					</div>
				</td>
			</tr>
          	<tr>
          		<td class="title">操作</td>
          		<td>
					<input id="confirm" type="button" style="float:left;" class="l-button l-button-test" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="关闭" class="l-button l-button-test" onclick="frameElement.dialog.close();"/>
				</td>
          	</tr>
        </table>
	</div>
	</form>
</body>
</html>
