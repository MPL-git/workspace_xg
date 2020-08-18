<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>设置密码保护</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
</head>

<body>

  <div class="modal-dialog" role="document" style="width:500px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">绑定手机</span>
      </div>
			<div class="modal-body">
				<form id="dataFrom" class="form-horizontal">
					<div class="form-group">
						<div class="col-md-12" style="text-align: center;color: red;">
							<span id="msg">&nbsp;</span>
						</div>
					</div>
					<div class="form-group">
						<label for="mobile" class="col-md-3 control-label">请输入手机号</label>
						<div class="col-md-9">
						<input type="text" class="form-control" readonly="readonly"  name="mobile"  id="mobile"value="${userInfo.mobile}">
						</div>
					</div>
					<div class="form-group">
						<label for="validateCode" class="col-md-3 control-label">验证码</label>
						<div class="col-md-6">
							<input type="text" name="validateCode"  id="validateCode" class="form-control">
						</div>
						<div class="col-md-3">
							<span id="getValidateCodeBtn" style="width:96px;"  onclick="getMobileValideCode();"  class="btn btn-default">获取验证码</span>
						</div>
						
					</div>
					<div class="form-group">
						<label for="mobile" class="col-md-3 control-label">登录保护</label>
						<div class="col-md-9">
						<span class="form-control" style="border: none;-webkit-box-shadow:none;box-shadow:none;">
							<input type="checkbox" name="isLoginValidate" value="1" <c:if test='${userInfo.isLoginValidate=="1"}'>checked="checked"</c:if>   id="isLoginValidate" ><span style="padding:0 10px;">需要手机验证</span>
						</span>
						</div>
					</div>
				  <div class="form-group">
				    <div class="col-md-offset-3 col-md-9">
				      <button type="button" class="btn btn-info" onclick="commit();">提交</button>
				      <button class="btn btn-info" data-dismiss="modal">取消</button>
				    </div>
				  </div>
				</form>
			</div>
	</div>
  </div>




	
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<!--     <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/fileUpload.js"></script> -->
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
	
<script type="text/javascript">
var timer = 0;

	function countdown() {
       if (timer > 0) {
			timer = timer - 1;
			$("#getValidateCodeBtn").html(timer+"秒");
			setTimeout(function() {
				countdown();
         }, 1000);
		} else {
			$("#getValidateCodeBtn").removeClass("disabled");
			$("#getValidateCodeBtn").html("获取验证码");
		}
	}

	function getMobileValideCode() {
		if(timer>0){
			return;
		}else{
		
		if ($.trim($("#mobile").val()) == "") {
			$("#msg").html("请输入手机号！");
			return;
		}
		
		var reg=/^1\d{10}/;
		if (!reg.test($.trim($("#mobile").val()))) {
			$("#msg").html("请输入正确的手机号！");
			return;
		}
		
		  
		
		$("#getValidateCodeBtn").addClass("disabled");
		timer=120;
		countdown();
		var mobile = $("#mobile").val();
		$.ajax({
			url : "${ctx}/common/getMobileValidateCode",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {
				mobile : mobile
			},
			timeout : 30000,
			success : function(data) {
				if (data.returnCode == "0000") {
					$("#msg").html("验证码已发至您的手机！");
				} else {
					$("#msg").html("获取失败，请重新获取！");
				}

			},
			error : function() {
				$("#msg").html("获取失败，请重新获取！");
			}
		});
		}
	}
	function commit() {
		
		if ($.trim($("#mobile").val()) == "") {
			$("#msg").html("请输入手机号！");
			return;
		}
		
		var reg=/^1\d{10}/;
		if (!reg.test($.trim($("#mobile").val()))) {
			$("#msg").html("请输入正确的手机号！");
			return;
		}
		
		if ($.trim($("#validateCode").val()) == "") {
			$("#msg").html("请输入手机验证码！");
			return;
		}
		
			var dataJson = $("#dataFrom").serializeArray();
			$.ajax({
				url : "${ctx}/mchtUser/setPropectCommit",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : dataJson,
				timeout : 30000,
				success : function(data) {
					if (data.returnCode == "0000") {
						isReload = true;
						$("#viewModal").modal('hide');
						swal({
							title : "设置成功!",
							type : "success",
							confirmButtonText : "确定"

						});
					} else {
						swal({
							title : data.returnMsg,
							type : "error",
							confirmButtonText : "确定"
						});
					}

				},
				error : function() {
					swal({
						title : "提交失败！",
						type : "error",
						confirmButtonText : "确定"
					});
				}
			});

	}
</script>
</body>
</html>
