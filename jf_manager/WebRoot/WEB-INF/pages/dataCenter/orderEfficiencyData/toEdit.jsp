<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}

	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }

</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
	<c:if test="${not empty deliveryOvertimeCnf}">
		var timeType = ${deliveryOvertimeCnf.timeType};
		if(timeType == 0){
			$("#overtime-tr").show();		
			$("#deliveryDate-tr").hide();		
		}else{
			$("#deliveryDate-tr").show();		
			$("#overtime-tr").hide();		
		}
	</c:if>
	
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
		$(".dateEditor").ligerDateEditor({
			format: "yyyy-MM-dd hh:mm:ss",
			showTime : true,
			labelWidth : 200,
			width:200,
			labelAlign : 'left'
		});
	
		$("#save").bind('click',function(){
			var begin_date = $("#begin_date").val();
			var end_date = $("#end_date").val();
			var timeType = $("#timeType").val();
			var overtime = $("#overtime").val();
			var deliveryDate = $("#deliveryDate").val();
			if(!begin_date){
				commUtil.alertError("请选择开始时间");
				return false;
			}
			if(!end_date){
				commUtil.alertError("请选择结束时间");
				return false;
			}
			if(timeType == '') {
				commUtil.alertError("请选择时间类型");
				return false;
			}else if(timeType == '0') {
				if(!overtime){
					commUtil.alertError("请输入发货承诺时间");
					return false;
				}else if(!testNumber(overtime)) {
					commUtil.alertError("发货承诺时间只能是正整数");
					return false;
				}
			}else if(timeType == '1') {
				if(!deliveryDate){
					commUtil.alertError("请选择发货承诺时间");
					return false;
				}else if(new Date(end_date) >= new Date(deliveryDate)) {
					commUtil.alertError("发货承诺时间大于结束时间");
					return false;
				}
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/orderEfficiencyData/deliveryOvertimeCnf/save.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : $("#form").serialize(),
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
						$(".l-dialog-content").attr("style", "padding-left: 64px;padding-bottom: 30px;margin-right: 5px;");
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});
		
});

function updateTimeType(timeType) {
	if(timeType == '') {
		$("#overtime-tr").attr("style", "display: none");
		$("#deliveryDate-tr").attr("style", "display: none");
	}else if(timeType == '0') {
		$("#overtime-tr").attr("style", "");
		$("#deliveryDate-tr").attr("style", "display: none");
	}else if(timeType == '1') {
		$("#overtime-tr").attr("style", "display: none");
		$("#deliveryDate-tr").attr("style", "");
	}
	
}

//图片格式验证
function ajaxFileUploadImg(statusImg) {
	var file = document.getElementById(statusImg).files[0];  
    if(!/image\/(PNG|png|JPG|jpg|JPEG|jpeg|gif|GIF)$/.test(file.type)){  
    	commUtil.alertError("图片格式不正确！");
        return;
    }
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
    		var widthStr = '750';
    		if(this.width != widthStr) {
    			commUtil.alertError("图片宽度不是"+widthStr+"像素！");
        	}else{
        		ajaxFileUpload();
        	}
        };
        image.src = e.target.result;
    }
    reader.readAsDataURL(file);  
}

function ajaxFileUpload() {
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: "picFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#pic").attr("src","${pageContext.request.contextPath}/file_servelt"+result.FILE_PATH);
				$("#pic").show();
				$("#productNoticePic").val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
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
	$("#viewer-pictures").show();
	viewerPictures.show();
}
</script>
<html>
<body>
	<form method="post" id="form" name="form" action="">
		<input type="hidden" name="id" value="${deliveryOvertimeCnf.id}">
		<table class="gridtable">
			<tr>
	              <td class="title">备注名称</td>
	              <td colspan="2" align="left" class="l-table-edit-td">
	              		<input type="text" id="remarks" name="remarks" style="width: 200px;" value="${deliveryOvertimeCnf.remarks}"/>
	              </td>
	        </tr>
			<tr>
	              <td class="title">开始时间<span style="color: red;">*</span></td>
	              <td colspan="2" align="left" class="l-table-edit-td">
	              		<input type="text" class="dateEditor" id="begin_date" name="begin_date" style="width: 200px;" value="<fmt:formatDate value="${deliveryOvertimeCnf.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
	              </td>
	        </tr>
			<tr>
	              <td class="title">结束时间<span style="color: red;">*</span></td>
	              <td colspan="2" align="left" class="l-table-edit-td">
	              		<input type="text" class="dateEditor" id="end_date" name="end_date" style="width: 200px;" value="<fmt:formatDate value="${deliveryOvertimeCnf.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
	              </td>
	        </tr>
	        <tr>
	              <td class="title">时间类型<span style="color: red;">*</span></td>
	              <td colspan="2" align="left" class="l-table-edit-td">
						<select id="timeType" name="timeType" style="width: 200px;" onchange="updateTimeType(this.value);" >
							<option value="">请选择...</option>
							<option value="0" <c:if test="${deliveryOvertimeCnf.timeType == 0}">selected="selected"</c:if>>相对值</option>
							<option value="1" <c:if test="${deliveryOvertimeCnf.timeType == 1}">selected="selected"</c:if>>绝对值</option>
						</select>
	              </td>
	        </tr>
	        <tr id="overtime-tr" style="display: none;">
	        	  <td class="title">发货承诺时间<span style="color: red;">*</span></td>
	        	  <td colspan="2" align="left" class="l-table-edit-td">
	        	  		<input type="text" id="overtime" name="overtime" style="width: 200px;" value="${deliveryOvertimeCnf.overtime}"/>
	        	  		<span>小时</span>
	        	  </td>
	        </tr>
	        <tr id="deliveryDate-tr" style="display: none;">
	        	  <td class="title">发货承诺时间<span style="color: red;">*</span></td>
	              <td colspan="2" align="left" class="l-table-edit-td table-edit-activity-time">
	              		<div><input type="text" class="dateEditor" id="deliveryDate" name="deliveryDate" style="width: 200px;" value="<fmt:formatDate value="${deliveryOvertimeCnf.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></div>
	              		<span>前发货</span>
	              </td>
	        </tr>
	        <tr style="height: 160px;">
	        	  <td class="title">商品公告</td>
	              <td colspan="2" align="left" class="l-table-edit-td table-edit-activity-time">
	              		<div style="float: left;margin-top: -20px;">
							<input type="file" id="picFile" name="file" onchange="ajaxFileUploadImg('picFile');" style="position:absolute; opacity:0;width: 80px;height: 30px;cursor: pointer;">
							<button>上传图片</button>
							<span>尺寸：宽度分辨率750，高度不限</span>
						</div>
						<div style="margin-top: 20px;margin-left: -247px;">
							<img src="${pageContext.request.contextPath}/file_servelt${deliveryOvertimeCnf.productNoticePic}" style="width: 75px;height:75px;<c:if test="${empty deliveryOvertimeCnf.productNoticePic}">display: none;</c:if>" id="pic" onclick='viewerPic(this.src)'>
						</div>
						<input id="productNoticePic" name="productNoticePic" type="hidden" value="${deliveryOvertimeCnf.productNoticePic}">
	              </td>
	        </tr>
	        <tr>
	              <td class="title">操作</td>
	              <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="save" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
        </table>
	</form>	     
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>   
</body>
</html>
