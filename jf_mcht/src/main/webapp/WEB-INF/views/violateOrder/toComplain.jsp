<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>违规详细页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}
.glyphicon-remove{
color:red;
margin-left: 3px;
cursor:pointer;
}
.docs-pictures{
padding: 0px;
}
.docs-pictures li{
position: relative;
margin: 3px;
}


.pic-btn-container{
	width:100%;
	position: absolute;
    top: 0px;
    background:rgba(0, 0, 0, 0.5);
    height:0px;
    z-index: 300;
    overflow: hidden;
    text-align: right;
    padding-right: 3px;
}

</style>
</head>

<body>
<!--查看品牌 -->
<div class="modal-dialog wg-xx" role="document" style="width:1000px;">
<div class="modal-content">
    <input type="hidden" id="violateOrderId" name="violateOrderId" value="${violateOrderId}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">填写申诉资料</span>
      </div>
	<div class="modal-body">
		<div id="toComplain">
			<form class="form-horizontal" role="form">
				<table class="panel-table">
					<tr>
		    			<td>联系方式</td>
		    			<td>
		    				<div class="form-group">
		    					<label for="productBrand" class="col-md-1 control-label search-lable">联系电话：</label>
								<div class="col-md-2 search-condition" >
				    				<input type="text" class="form-control" id="phone" placeholder="请输入联系电话" data-type="number" style="width: 150px;">
								</div>
								
		    					<label for="productBrand" class="col-md-1 control-label search-lable">联系邮箱：</label>
								<div class="col-md-2 search-condition" >
				    				<input type="text" class="form-control" id="email" placeholder="请输入联系邮箱" style="width: 150px;">
								</div>
		    				</div>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>申诉内容</td>
		    			<td><textarea class="form-control" rows="3" placeholder="请输入申诉内容" style="height: 57px;" id="content"></textarea></td>
		    		</tr>
		    		<tr>
		    			<td>上传图片</td>
		    			<td><div id="pic-container"></div></td>
		    		</tr>
		    		<tr id="comfirmDiv">
		    			<td colspan="2">
		    				<div>
		    					<a href="javascript:;" class="btn btn-default" id="saveComplain">提交</a>
		    				</div>
		    			</td>
		    		</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myWebUploader.js"></script>
  <script type="text/javascript">
	var imageUploader;
	$(function(){
		imageUploader = $("#pic-container").myWebUploader({
			fileNumLimit:5,
	        fileSizeLimit:1024*1024*6,
	        fileSingleSizeLimit:1024*1024*2,
	        preview:true
	    });
				
		$("#complain").on('click',function(){
			$("#complainDiv").show();
			$("#comfirmDiv").show();
		});
		
		var submitting;
		$("#saveComplain").on('click',function(){
			if(submitting){
				return false;
			}
			var violateOrderId = $("#violateOrderId").val();
			var phone = $.trim($("#phone").val());
			if(phone == ""){
				swal("联系电话必填");
				return false;
			}
			if(!checkTelephone(phone)){
				swal("请输入正确的联系电话");
				return false;
			}
			
			var email = $.trim($("#email").val());
			if(email == ""){
				swal("联系邮箱必填");
				return false;
			}
			if(!checkEmail){
				swal("请输入正确的联系邮箱");
				return false;
			}
			var content = $.trim($("#content").val());
			if(content == ""){
				swal("申诉内容必填");
				return false;
			}
			var status = "0";
			submitting = true;
			imageUploader.upload();
			imageUploader.on('uploadFinished',function(){
				var imgs = $("#pic-container").getImgPaths();
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/violateOrder/saveComplain',
			        data: {"violateOrderId":violateOrderId,"content":content,"phone":phone,"email":email,"status":status,"imgs":imgs},
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			        	$("#toComplainModal").modal('hide');
			        	$("#viewModal").modal('hide');
			        	table.ajax.reload(null, false);
			        }else{
			        	swal("申诉失败，请稍后重试");
			        }
			        submitting = false;
			    });
			});
		});
	});
  </script>
</body>
</html>
