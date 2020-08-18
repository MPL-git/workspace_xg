<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
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
<script type="text/javascript">
$(function() {
	$(".dateEditor").ligerDateEditor({
		showTime : true,
		format : "yyyy-MM-dd hh:mm:ss",
		labelAlign : 'left',
		width : 200
	});
	
 });
 
 
//图片格式验证
function ajaxFileUploadImg(statusImg) {
	var file = document.getElementById(statusImg).files[0];
    if(!/image\/(JPG|jpg|JPEG|jpeg|png)$/.test(file.type)) {  
    	commUtil.alertError("图片格式不正确！");
        return;
    }
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
    	var filesize = file.size;
    		if(filesize>1024*1024*5) {
    			commUtil.alertError("图片大小不超过5M");
        	}else {
    			ajaxFileUpload(statusImg);
    		}
        };
        image.src = e.target.result;
    },
    reader.readAsDataURL(file);
}

function ajaxFileUpload(statusImg) {
$.ajaxFileUpload({
	url: contextPath + '/service/common/ajax_upload.shtml',
	secureuri: false,
	fileElementId: "pic",
	dataType: 'json',
	success: function(result, status) {
		if(result.RESULT_CODE == 0) {
			uploadImageList.addImage(contextPath + "/file_servelt", result.FILE_PATH);
		} else {
			alert(result.RESULT_MESSAGE);
		}
	},
	error: function(result, status, e) {
		alert("服务异常");
	}
});	
}



$(function (){ 
  $.metadata.setType("attr", "validate");
  $("#form1").validate({
		errorPlacement : function(lable, element) {
     	$(element).ligerTip({recordContent : lable.html()});
		},
		success : function(lable) {
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler : function(form) {
		var consumerAppealDate = $("#consumerAppealDate").val();
		var registrationNumber = $("#registrationNumber").val();
		var appealName = $("#appealName").val();
   		var appealMobile = $("#appealMobile").val();
   		var appealAddress = $("#appealAddress").val();
   		var appealOrderType=$("#appealOrderType").val();
   		var typesOfComplaints=$("#typesOfComplaints").val();
   		var consumerAppealContent=$("#consumerAppealContent").val();
   		var pic = document.getElementById("pic");
   		if($.trim(consumerAppealDate)== '') {
    		   $.ligerDialog.alert("消费者投诉时间不能为空！"); 
     		   return;
     		}
   		if($.trim(registrationNumber)== '') {
   		   $.ligerDialog.alert("登记编号不能为空！"); 
    		   return;
    		}
   		if($.trim(appealName) == '') {
   			$.ligerDialog.alert("投诉人姓名不能为空！");
       		return;
       	}
       	if($.trim(appealMobile) == '') {
       		$.ligerDialog.alert("投诉人联系电话不能为空！");
       		return;
       	}
       	if($.trim(appealAddress)=='') {
       		$.ligerDialog.alert("投诉人地址不能为空！");
   			return;
       	}
       	if ($.trim(appealOrderType)=='') {
       		$.ligerDialog.alert("投诉单类型不能为空！");
       		return;
			}
       	if ($.trim(typesOfComplaints)=='') {
       		$.ligerDialog.alert("被投诉的类型不能为空！");
       		return;
		}
       	if ($.trim(consumerAppealContent)=='') {
       		$.ligerDialog.alert("消费者投诉具体内容不能为空！");
       		return;
		}
       	var pictures = [];
	    var lis = $(".upload_image_list").find("li");
	    lis.each(function(index, item) {
		var path = $("img", item).attr("path");
		var def = ($(".def", item).length == 1 ? "1" : "0");
		var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
		pictures.push(pic);
	});
	if(pictures.length >=1 ){
		$("#pics").val(JSON.stringify(pictures));
	}else if(pictures.length <1){
		$.ligerDialog.alert("请上传图片！");
		return;
	}   
			form.submit();
		}
	});
})

</script>

</head>
	<body style="margin: 10px;">
	<form method="post" id="form1" name="form1" class="form1" action="${pageContext.request.contextPath}/businessCirclesAppealOrder/editbusinessCirclesAppealOrder.shtml" >
		<input type="hidden" id="id" name="id" value="" />
		<table class="gridtable">
				<tr>
	            	<td class="title" width="20%">消费者投诉时间<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
				        <input type="text" class="dateEditor" id="consumerAppealDate" name="consumerAppealDate" >
					</td>
	           	</tr>
	           	<tr>
	            	<td class="title" width="20%" >登记编号<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:360px;" type="text" id="registrationNumber" name="registrationNumber" value=""  />
					</td>	
	           	</tr>
	           	
	           	<tr>	      
					<td class="title" width="20%" style="margin-left: 20px;">投诉人姓名<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:360px;" type="text" id="appealName" name="appealName" value=""  />
					</td>
	           	</tr>
	           	
	           	<tr>
	            	<td class="title" width="20%">投诉人联系电话<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:360px;" type="text" id="appealMobile" name="appealMobile" value=""  />
					</td>
	           	</tr>
	           	
	           	<tr>	       
					<td class="title" width="20%" style="margin-left: 20px;">投诉人地址<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:360px;" type="text" id="appealAddress" name="appealAddress" value="" />
					</td>
	           	</tr>
	           	
	            <tr>
	            	<td class="title" width="20%">投诉单类型<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
					<select id="appealOrderType" name="appealOrderType" style="width: 135px;">
						<option value=''>请选择...</option>
						<option value='1'>投诉单</option>
						<option value='2'>举报单</option>
					 </select>	
					</td>
	           	</tr>
	           	
	         <tr>
				<td class="title">被投诉的类型<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:360px;" type="text" id="typesOfComplaints" name="typesOfComplaints" value="" />
              	</td>  
            </tr>
	           	
	         <tr>
				<td class="title">消费者投诉具体内容<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea rows="10" cols="100" id='consumerAppealContent' name="consumerAppealContent"></textarea>
				</td>
			</tr>       
            <tr>
			<td colspan="1" class="title">工商投诉原件<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
	    		<t:imageList name="pictures" list="${picList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
	    		<div style="float: left;height: 105px;margin: 10px;">
	    		<input style="position:absolute; opacity:0;" type="file" id="pic" name="file" onchange="ajaxFileUploadImg('pic');" />
					<div class="l-icon l-icon-up" style="display:inline-block;"></div>
					<a href="javascript:void(0);" style="width:30%;">上传投诉原件</a>
					<span style="color:#CC0000;">(提示：图片大小不超过5M)</span>	               
	    		</div>
	    		<input id="pics" name="pics" type="hidden" value="">	    	   
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