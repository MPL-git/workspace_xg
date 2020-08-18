<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>行业经营许可证管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
            <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <input type="hidden" id="mchtId" name="mchtId" value="${mchtId}">
    <input type="hidden" id="id" name="id" value="${mchtLicenseChg.id}">
    <input type="hidden" id="source" name="source" value="${source}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" >更新经营许可证</span>
      </div>
     <div class="modal-body">
     	<table class="panel-table">
     		<c:if test="${mchtLicenseChg.auditStatus eq 2}">
     		<tr>
     			<td class="editfield-label-td" style="color: red;">驳回原因：</td>
     			<td>
     				${mchtLicenseChg.licenseRejectReason}
     			</td>
     		</tr>
     		</c:if>
 			<tr>
				<td class="editfield-label-td">经营许可证</td>
				<td colspan="2" class="text-left">
					<div style="color: #999999;">
		    			图片最多可上传1张且图片格式为JPG。每天图片大小不超过3M
		    		</div>
					<div class="single_pic_picker">
						<input id="businessLicensePicFile" onchange="loadImageFile(this)" type="file"><div>+</div>
						<input type="hidden"  id="businessLicensePic" name="businessLicensePic" value="">
					</div>
				</td>
			</tr>
 			<tr>
 				<td>操作</td>
 				<td colspan="2">
					<button class="btn btn-info" data-dismiss="modal">取消</button>
 					<button class="btn btn-info" id="commit">提交</button>
 				</td>
 			</tr>
	 	</table>
     </div>
    </div>
  </div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<!--     <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/fileUpload.js"></script> -->
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
    <script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>	
  <script type="text/javascript">
  $(function(){
	  	var submitting;
	  	$("#commit").on('click',function(){
	  		if(submitting){
	  			return false;	
	  		}
	  		var id = $("#id").val();
	  		var mchtId = $("#mchtId").val();
	  		var $businessLicensePic=$("#businessLicensePic").parent().children("img");
			if($businessLicensePic.length <= 0){
				swal({
					  title: '请上传经营许可证',
					  type: "error",
					  confirmButtonText: "确定"
					});
				return;
			}
			if($businessLicensePic.length > 0 && $businessLicensePic.attr("src") != "" && $businessLicensePic.attr("src").indexOf("data:image") == 0){//有修改
				uploadImage("businessLicensePicFile",$("#businessLicensePic"));
			}
	  		var businessLicensePic = $("#businessLicensePic").val();
	  		submitting = true;
	  		$.ajax({
	            method: 'POST',
	            url: '${ctx}/mchtUser/saveMchtLicenseChg',
	            data: {"id":id,"mchtId":mchtId,"businessLicensePic":businessLicensePic,"source":$("#source").val()},
	            dataType: 'json'
	        }).done(function (result) {
	            if (result.returnCode =='0000') {
	            	if(result.returnData.source){
						swal("提交成功，可在栏目【商家管理】中的【用户信息】中查看审核情况");
		            	$("#viewModal").modal('hide');
	            	}else{
		            	$("#viewModal").modal('hide');
		            	var url = "${ctx}/mchtUser/mchtUserIndex";
		            	getContent(url);
		            	$(".modal-backdrop").hide();
	            	}
	            }else{
	            	swal("备注失败，请重试");
	            }
	            submitting = false;
	        });
	  	});
	  
	  
  });
	
  function loadImageFile(obj) {
		if (obj.files.length === 0) {
			return;
		}
		var oFile = obj.files[0];
		var rFilter = /^(?:image\/jpeg|image\/jpg)$/i;
		if (!rFilter.test(oFile.type)) {
			swal("图片格式不正确！");
			return;
		}
		var reader = new FileReader();  
	    reader.onload = function(e) { 
	    	var image = new Image();
	    	image.onload = function() {
	    		if($(obj).parent().children("img").length<=0){
	    	    	$('<img>').appendTo( $(obj).parent() );;
	    	    }
	   			$(obj).parent().children("img").attr("src",e.target.result);
	   			$(obj).parent().children("div").remove();
	        };
	        image.src = e.target.result;
	    }
	    reader.readAsDataURL(oFile); 
	}

	//上传图片
	function uploadImage(fileElementId,$valueFiled) {
		var oFile = document.getElementById(fileElementId).files[0];
		var formData = new FormData();
		formData.append("file",oFile);
		$.ajax({ 
			url : "${ctx}/common/fileUpload", 
			type : 'POST', 
			data : formData, 
			async: false,
			// 告诉jQuery不要去处理发送的数据
			processData : false, 
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			beforeSend:function(){
				console.log("图片片上传中，请稍候");
			},
			success : function(data) {
	            if (data.returnCode=="0000") {
	                $valueFiled.val(data.returnData);
	            } else {
	                swal({
	                    title: "图片上传失败！",
	                    type: "error",
	                    confirmButtonText: "确定"
	                });
	            }
			}, 
			error : function(responseStr) { 
				swal("图片上传失败");
			} 
			});


	}	
	
	
	
  </script>
  
  
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->

</body>
</html>
