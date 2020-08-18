<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
/* .upload_image_list li a{display:none !important} */
</style>
<script type="text/javascript">
var viewerPictures;
var pics="${staffFeedbackContentPics}".replace('[','').replace(']','').replace(/, /g,',').split(',');

function viewerPic(imagePath){
  	$("#viewer-pictures").empty();
	viewerPictures.destroy();
 	$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+imagePath+'" src="${pageContext.request.contextPath}/file_servelt'+imagePath+'" alt=""></li>');
 	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
 	$("#viewer-pictures").show();
	viewerPictures.show();
}

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
        		if(filesize>1024*1024*10) {
        			commUtil.alertError("单张大小不超过10M");
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
		fileElementId: "staffFeedbackContentPic",
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


  $(function(){		
	$.metadata.setType("attr", "validate");
		$("#form1").validate({//表单验证
	        errorPlacement: function (lable, element) {
	         var name =  $("#feedbackContent").val();
	         if ($.trim(name)=='') {
				element.ligerTip({ content: lable.html("反馈内容不能为空!")}); 
			  }else{
	        		$(element).ligerTip({
						content : lable.html("反馈内容不能超过500个字!"),width: 100
					});    	 
			     }  
	        },
	        success: function (lable,element) {
	            lable.ligerHideTip();
	            lable.remove();
	        },
	        submitHandler: function(form) {	        	   
	        	var pictures = [];
    		    var lis = $(".upload_image_list").find("li");
    		    lis.each(function(index, item) {
    			var path = $("img", item).attr("path");
    			var def = ($(".def", item).length == 1 ? "1" : "0");
    			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
    			pictures.push(pic);
    		});
    		if(pictures.length >0 && pictures.length<=5){
    			$("#staffFeedbackContentPics").val(JSON.stringify(pictures));
    		}else if(pictures.length >5){
    			commUtil.alertError("最多上传5张图片！");
	   		    return;
    		}
    		   var organizationId =  $("#organizationId").val();        	
	           if(organizationId == '') {
	        	   commUtil.alertError("发送给谁不能为空，不能为空！");
	   		       return;
	        	}
	          form.submit();
	        }
	    }); 
		
	});
</script>
<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/staffOpinionFeedback/addstaffOpinionFeedback.shtml">
	<input id="id" name="id" type="hidden" value=""/>
	<table class="gridtable">
	       <tr>
				<td colspan="1" class="title">反馈内容<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;" rows="10" cols="100" id='feedbackContent' name="feedbackContent" placeholder="请输入1-500个字~" validate="{required:true,maxlength:500}"></textarea>
				</td>
		   </tr>			
		
		 <tr>
			<td colspan="1" class="title">反馈图片</td>
			<td colspan="5" align="left" class="l-table-edit-td">
	    		<t:imageList name="pictures" list="${staffOpinionFeedbackPicList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
	    		<div style="float: left;height: 105px;margin: 10px;">
	    		<input style="position:absolute; opacity:0;" type="file" id="staffFeedbackContentPic" name="file" onchange="ajaxFileUploadImg('staffFeedbackContentPic');" />
					<div class="l-icon l-icon-up" style="display:inline-block;"></div>
					<a href="javascript:void(0);" style="width:30%;">上传图片</a>
					<span style="color:#CC0000;">(提示：最多上传5张,单张大小不超过10M)</span>	               
	    		</div>
	    		<input id="staffFeedbackContentPics" name="staffFeedbackContentPics" type="hidden" value="${staffOpinionFeedbackPicList}">	    	   
	      </td>
		 </tr>

		    <tr>
           	    <td class="title" width="20%">发送给谁<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
              	<select id="organizationId" name="organizationId" style="width: 135px;">
						<option value=''>请选择...</option>
						<option value='1'>总经办</option>
						<option value='2'>人事部</option>
						<option value='3'>财务部</option>
						<option value='4'>法务部</option>
						<option value='5'>产品部</option>
						<option value='6'>技术部</option>
						<option value='7'>商品部</option>
						<option value='8'>招商部</option>
						<option value='9'>客服部</option>
						<option value='10'>策划部</option>
						<option value='11'>设计部</option>
						<option value='12'>文案部</option>
						<option value='13'>推广部</option>
						<option value='14'>其他</option>
						<%-- <c:forEach var="list" items="${sysOrganizationList}">
							<option value="${list.orgId}">${list.orgName}</option>
						</c:forEach> --%>					
				</select>
                </td>
           	</tr>
           	
           	<tr>
				<td class="title">匿名<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				<input type="radio" name="isanonymous" value="1">是
                <input type="radio" name="isanonymous" value="2" checked="checked">否
                </td>
			</tr>
				
			<tr>
				<td colspan="1" class="title">操作<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">					
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"/>				
						<input type="button" value="关闭" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
		</form>	
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
