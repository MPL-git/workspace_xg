<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
</head>

<body>

  <div class="modal-dialog" role="document" style="width:500px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">修改密码</span>
      </div>
			<div class="modal-body">
				<form id="dataFrom" class="form-horizontal">
					<div class="form-group">
						<div class="col-md-12" style="text-align: center;color: red;">
							<span id="msg">&nbsp;</span>
						</div>
					</div>
				<div id="step1_div">
					<div class="form-group">
						<label for="oldPassword" class="col-md-3 control-label">输入原始密码</label>
						<div class="col-md-9">
							<input type="password" name="oldPassword"  id="oldPassword" class="form-control">
						</div>
					</div>
				  <div class="modal-footer">
				      <button type="button" class="btn btn-info" onclick="toStep2();" style="margin-right: 17px;">下一步</button>
				      <button class="btn btn-info" data-dismiss="modal">取消</button>
				  </div>
				</div>
				
				<div id="step2_div" style="display:none;">
					<div class="form-group">
						<label for="mobile" class="col-md-3 control-label">绑定的手机号</label>
						<div class="col-md-9">
							<input type="text" name="mobile"  id="mobile" class="form-control" value="${userInfo.mobile }" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="validateCode" class="col-md-3 control-label">输入验证码</label>
						<div class="col-md-6">
							<input type="text" name="validateCode"  id="validateCode" class="form-control">
						</div>
						<div class="col-md-3">
							<span id="getValidateCodeBtn" style="width:96px;"  onclick="getMobileValideCode();"  class="btn btn-default">获取验证码</span>
						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-info" onclick="toStep3();" style="margin-right: 17px;">下一步</button>
						<button class="btn btn-info" data-dismiss="modal">取消</button>
					</div>
				</div>
				
				<div id="step3_div" style="display:none;">
					<div class="form-group">
						<label for="newPassword" class="col-md-3 control-label">输入新密码</label>
						<div class="col-md-9">
							<input type="password" name="newPassword"  id="newPassword" class="form-control" >
						</div>
					</div>
					<div class="form-group">
						<label for="confirmPassword" class="col-md-3 control-label">再次输入密码</label>
						<div class="col-md-9">
							<input type="password" name="confirmPassword"  id="confirmPassword" class="form-control" >
						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-info" onclick="commit();" style="margin-right: 17px;">确认</button>
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

function toStep2(){

	var oldPassword = $("#oldPassword").val();
	$.ajax({
		url : "${ctx}/mchtUser/checkPassword",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {
			password : oldPassword
		},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode == "0000") {
				//验证手机号
				$("#msg").html("");
				$("#step1_div").hide();
				$("#step2_div").show();
			} else {
				$("#msg").html(data.returnMsg);
			}

		},
		error : function() {
			$("#msg").html("请重新输入密码");
		}
	});
	}
	
function toStep3(){

	var mobile = $("#mobile").val();
	var validateCode = $("#validateCode").val();
	$.ajax({
		url : "${ctx}/mchtUser/checkMobileValidateCode",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {
			mobile : mobile,
			validateCode:validateCode
		},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode == "0000") {
				//验证手机号
				$("#msg").html("");
				$("#step2_div").hide();
				$("#step3_div").show();
			} else {
				$("#msg").html(data.returnMsg);
			}

		},
		error : function() {
			$("#msg").html("请重新获取验证码");
		}
	});
	}


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
		var newPassword=$.trim($("#newPassword").val());
		var confirmPassword=$.trim($("#confirmPassword").val());
		if (newPassword == "") {
			$("#msg").html("请输入新密码！");
			return;
		}
		
		
		if (confirmPassword == "") {
			$("#msg").html("请输入确认密码！");
			return;
		}
		
		
		if (confirmPassword!=newPassword) {
			$("#msg").html("两次输入的密码不一致!");
			return;
		}
		
			var dataJson = $("#dataFrom").serializeArray();
			$.ajax({
				url : "${ctx}/mchtUser/changePasswordConfirm",
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
							title : "修改成功!",
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
