<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>修改图片</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
</head>

<style type="text/css">
	.panel-table tr td:first-child {
		width: 20%;
		text-align: center;
	}
	.single_pic_picker.x1 {
         width: 400px;
         height: 200px;
         margin: 0;
     }
     .single_pic_picker.x1 div,
     .single_pic_picker.x1 img,
     .single_pic_picker.x1 input {
         width: 100% !important;
         height: 100% !important;
     }
     .single_pic_picker.x1 div {
         line-height: 200px;
     }
</style>

<body>
<!--查看品牌 -->
<div class="modal-dialog wg-xx" role="document" style="width:800px;">
<div class="modal-content">
    <div class="modal-header">
      <button type="button" id="but-close" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <span class="modal-title" id="exampleModalLabel">修改图片</span>
    </div>
	<div class="modal-body">
		<form id="dataFrom">
			<input type="hidden" name="id" value="${activity.id }" />
			<input type="hidden" name="statusSource" value="${statusSource }" />
			<div>
				<table class="panel-table">
					<tr>
	                    <td class="editfield-label-td">品牌团入口图</td>
	                    <td colspan="2" class="text-left padding-10">
	                        <div class="single_pic_picker x1">
	                            <input type="hidden" name="brandTeamPic" value="${activity.brandTeamPic}"/>
	                            <input id="brandTeamPicFile" onchange="loadImageFile(this)" type="file">
	                            <c:if test='${activity.brandTeamPic != null && activity.brandTeamPic != ""}'>
	                            <img src="${ctx}/file_servelt${activity.brandTeamPic}" id="brandTeamPic-img" picStatus="false" >
	                            </c:if>
	                            <c:if test='${activity.brandTeamPic == null || activity.brandTeamPic == ""}'>
	                            <div>+</div>
	                            </c:if>
	                        </div>
	                        <div style="color: #999;">图片要求： 格式为.JPG，尺寸为1080x355像素，大小不超过100Kb</div>
	                    </td>
	                </tr>
					<tr>
	                    <td class="editfield-label-td">入口图</td>
	                    <td colspan="2" class="text-left padding-10">
	                        <div class="single_pic_picker x1">
	                            <input type="hidden" name="entryPic" value="${activity.entryPic}"/>
	                            <input id="entryPicFile" onchange="loadImageFile(this)" type="file">
	                            <c:if test='${activity.entryPic != null && activity.entryPic != ""}'>
	                            <img src="${ctx}/file_servelt${activity.entryPic}" id="entryPic-img" picStatus="false" >
	                            </c:if>
	                            <c:if test='${activity.entryPic == null || activity.entryPic == ""}'>
	                            <div>+</div>
	                            </c:if>
	                        </div>
	                        <div style="color: #999;">图片要求： 格式为.JPG，尺寸为800x400像素，大小不超过100Kb</div>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="editfield-label-td">头部海报</td>
	                    <td colspan="2" class="text-left padding-10">
	                        <div class="single_pic_picker x1">
	                            <input type="hidden" name="posterPic" value="${activity.posterPic}"/>
	                            <input id="posterPicFile" onchange="loadImageFile(this)" type="file">
	                            <c:if test='${activity.posterPic != null && activity.posterPic != ""}'>
	                                <img src="${ctx}/file_servelt${activity.posterPic}" id="posterPic-img" picStatus="false" >
	                            </c:if>
	                            <c:if test='${activity.posterPic == null || activity.posterPic == ""}'>
	                                <div>+</div>
	                            </c:if>
	                        </div>
	                        <div style="color: #999;">图片要求： 格式为.JPG，尺寸为800x400像素，大小不超过100Kb</div>
	                    </td>
	                </tr>
		    		<tr>
		    			<td colspan="2">
		    				<div>
		    					<button type="button" class="btn btn-info" onclick="commitSave();">提交</button>
		    				</div>
		    			</td>
		    		</tr>
				</table>
			</div>
		</form>
	</div>
	<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;">
	
	</ul>
</div>
</div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
  <script type="text/javascript">
	  function loadImageFile(obj) {
	      if (obj.files.length === 0) {
	          return;
	      }
	      var id = $(obj).attr("id");
	      var oFile = obj.files[0];
	      if(oFile.size > 100*1024){
	          swal("图片大小不能超过100K！");
	          return;
	      }
	      var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
	      if (!rFilter.test(oFile.type)) {
	          swal("请选择图片文件！");
	          return;
	      }
	      if(!/image\/(JPG|jpg|JPEG|jpeg)$/.test(oFile.type)){  
	    	  swal("图片格式不正确！");
	          return;
	      }
	      if($(obj).parent().children("img").length <= 0){
	          $('<img>').appendTo( $(obj).parent() );;
	      }
	      var oFReader = new FileReader();
	      oFReader.onload = function(oFREvent) {
	          var img = new Image();
	          img.src = oFREvent.target.result;
	          img.onload = function(){
	        	  if(id == "brandTeamPicFile"){
	        		  if(img.width != 1080 || img.height != 335){
		                  swal("请选择尺寸为1080*335的图片！");
		                  return;
		              }else{
		                  $(obj).parent().children("img").attr("src",oFREvent.target.result);
		                  $(obj).parent().children("div").remove();
		                  $(obj).parent().children("img").attr("picStatus", "true");
		              }
	        	  }else{
		              if(img.width != 800 || img.height != 400){
		                  swal("请选择尺寸为800*400的图片！");
		                  return;
		              }else{
		                  $(obj).parent().children("img").attr("src",oFREvent.target.result);
		                  $(obj).parent().children("div").remove();
		                  $(obj).parent().children("img").attr("picStatus", "true");
		              }
	        	  }
	          }
	      };
	      oFReader.readAsDataURL(oFile);
	  }
	  
	  //上传图片
	  function uploadImg($img,$valueFiled) {
	  	var oFile = document.getElementById($img).files[0];
	  	var formData = new FormData();
	  	formData.append("file",oFile);
	  	$.ajax({ 
	  		url : "${ctx}/common/fileUpload?fileType=3", 
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
	              if (data.returnCode == "0000") {
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
	  
	  function commitSave() {
		   var entryPicStatus = $("#entryPic-img").attr("picStatus");
		   var posterPicStatus = $("#posterPic-img").attr("picStatus");
		   var brandTeamPicStatus = $("#brandTeamPic-img").attr("picStatus");
		   if(entryPicStatus == 'false' && posterPicStatus == 'false' && brandTeamPicStatus == 'false') {
			   swal("必需修改上传其中一张图片！");
			   return;
		   }
           var entryPic = $("input[name='entryPic']");
           var entryPics = entryPic.parent().children("img");
           if(entryPics.length <= 0){
               swal({
                   title: '请上传入口图',
                   type: "error",
                   confirmButtonText: "确定"
               });
               return;
           }
           if("${activity.entryPic}" == "" || entryPics.attr("src").indexOf("${activity.entryPic}") < 0){ //有修改
               uploadImg("entryPicFile", entryPic);
           }
           var posterPic = $("input[name='posterPic']");
           var posterPics = posterPic.parent().children("img");
           if(posterPics.length <= 0){
               swal({
                   title: '请上传头部海报',
                   type: "error",
                   confirmButtonText: "确定"
               });
               return;
           }
           if("${activity.posterPic}"=="" || posterPics.attr("src").indexOf("${activity.posterPic}") < 0){ //有修改
               uploadImg("posterPicFile", posterPic);
           }
           var brandTeamPic = $("input[name='brandTeamPic']");
           var brandTeamPics = brandTeamPic.parent().children("img");
           if(brandTeamPics.length <= 0){
               swal({
                   title: '请上传品牌团入口图',
                   type: "error",
                   confirmButtonText: "确定"
               });
               return;
           }
           if("${activity.brandTeamPic}"=="" || brandTeamPics.attr("src").indexOf("${activity.brandTeamPic}") < 0){ //有修改
               uploadImg("brandTeamPicFile", brandTeamPic);
           }
           $.ajax({
               url: "${ctx}/mcht/activity/saveDesignImg",
               type: 'POST',
               //ontentType : 'application/json;charset=utf-8', //设置请求头信息
               dataType: 'json',
               cache: false,
               async: false,
               data: $("#dataFrom").serialize(),
               timeout: 30000,
               success: function (data) {
                   if (data.code == '200') {
                	   $("#but-close").click();
                	   swal({
                           title: "提交成功!",
                           type: "success",
                           confirmButtonText: "确定"
                       });
                	   window.parent.subFlush();
                   } else {
                       swal({
                           title: data.returnMsg,
                           type: "error",
                           confirmButtonText: "确定"
                       });
                   }
               },
               error: function () {
                   swal({
                       title: "提交失败！",
                       type: "error",
                       confirmButtonText: "确定"
                   });
               }
           });
    }
  
  </script>
</body>
</html>
