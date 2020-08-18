<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>醒购商城商家后台</title>

<link href="${ctx }/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/static/css/main-style.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<style type="text/css">
.form-inline label {
	width: 90px;
}

.form-inline .form-group {
	padding: 5px 0;
}

.left-align {
	text-align: left;
}

.erroMsg {
	color: #6C6C6C;
	padding: 4px;
	background: #fef2f2;
	border: 1px solid #ffb4a8;
	font-size: 12px;
	font-weight: 400;
	width: 100%;
	margin-top: 10px;
}
</style>
</head>
<body style="background-color: #fff;">
	<div>
		<div class="header-login">
			<div class="header-main">
				<div class="header-main-left">
					<img src="${ctx}/static/images/LOGO_NEW.png" />
				</div>
				<div class="header-main-right">
					<a class="btn btn-default" href="http://shop.xgbuy.cc">商家平台</a>
					<a class="btn btn-default" href="http://zs.xgbuy.cc/">申请入驻</a>
                    <a class="btn btn-default" href="http://www.xgbuy.cc/information/informationManager" target="_blank">醒购规则</a>
					<a class="btn btn-default" href="http://www.xgbuy.cc/xgNews/xgNewsManager?catalogId=71" target="_blank">醒购动态</a>
				</div>
			</div>
		</div>

		<div id="content-div" class="content-div">
			<div class="content-container">
				<div class="content-ad">
					<img alt="" src="${ctx }/static/images/login-back.png">
				</div>
				<div class="content-login-div">
					<c:if
						test="${empty sessionScope.mchtUser||sessionScope.mchtUser.password!='e10adc3949ba59abbe56e057f20f883e'}">
						<div id="loginDiv">
							<div class="login-title" >
								<div >商家登录</div>
							</div>
							<form class="form-horizontal" id="loginForm" method="post">
								<div class="form-group">
									<div class="col-md-12">
										<input type="text" class="form-control font-px14"
											id="userCode" name="userCode" placeholder="输入用户名">
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<input type="password" class="form-control font-px14"
											id="password" name="password" placeholder="输入密码">
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-8">
										<input type="text" class="form-control font-px14"
											id="validCode" name="validCode" placeholder="验证码">
									</div>
									<div class="col-md-4">
										<span class="verifycode" title="点击图片刷新"><img
											style="width: 84px;height:32px;" id="captchaImg"
											src="${ctx}/getCaptcha" onclick="reloadCaptcha()" /></span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<button type="button" onclick="loginSubmit();"
											class="btn btn-default btn-lg submit-btn">登录</button>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12 text-center">
										<span><a class="find-password font-px14"
											href="javascript:;" id="findPassword">找回密码</a></span>
									</div>
								</div>
							</form>
						</div>
					</c:if>

					<c:if
						test="${not empty sessionScope.mchtUser && sessionScope.mchtUser.password=='e10adc3949ba59abbe56e057f20f883e'}">
						<div class="login-title">
							<div>修改密码</div>
						</div>
						<form class="form-inline" id="dataForm1" method="post">
							<input type="hidden" id="id" name="id"
								value="${sessionScope.mchtUser.id }">
							<div class="form-group">
								<label for="userCode">用户名</label> <input
									class="form-control font-px14" id="userCode" name="userCode"
									value="${mchtUser.userCode}" disabled="disabled"> <a
									href="javascript:void(0);"
									onclick="$('#userCode').attr('disabled',false)">修改</a>
							</div>
							<div class="form-group">
								<label for="oldPassword">原密码</label> <input
									class="form-control font-px14" type="password" id="oldPassword"
									name="oldPassword" value="">
							</div>
							<div class="form-group">
								<label for="newPassword">新密码</label> <input
									class="form-control font-px14" type="password" id="newPassword"
									name="newPassword" value="">
							</div>
							<div class="form-group">
								<label for="confirmPassword">再次输入密码</label> <input
									class="form-control font-px14" type="password"
									id="confirmPassword" name="confirmPassword" value="">
							</div>
							<div class="form-group">
								<label for="email">绑定邮箱</label> <input
									class="form-control font-px14" class="form-control font-px14"
									type="text" id="email" name="email" value="${mchtUser.email}">
							</div>

							<div class="form-group">
								<label for="mobile">绑定手机</label> <input
									class="form-control font-px14" type="text" id="mobile"
									name="mobile" value="${mchtUser.mobile}" disabled="disabled">
								<a href="javascript:void(0);"
									onclick="$('#mobile').attr('disabled',false)">修改</a>
							</div>

							<div class="form-group">
								<label for="mobileValidateCode">验证码</label> <input
									class="form-control font-px14" style="width:76px;" type="text"
									id="mobileValidateCode" name="mobileValidateCode" value="">
								<span id="getValidateCodeBtn" style="width:96px;"
									onclick="getMobileValideCode();" class="btn btn-default">获取验证码</span>
								<label id="msg" style="padding: 0 10px;color: red;"></label>
							</div>
						</form>

						<div class="form-group">
							<button style="margin-left:90px;" class="btn btn-info"
								onclick="commitAudit();">确定</button>
							<a style="margin-left:31px;" class="btn btn-info"
								href="${ctx}/loginOut">退出</a>
						</div>
					</c:if>
					<div id="findPasswordDiv" style="display: none;">
						<div class="login-title">
							<div>找回密码</div>
						</div>
						<form class="form-inline" id="dataForm2" method="post">
							<div class="form-group">
								<label for="userCode">用户名</label> <input
									class="form-control font-px14" id="findUserCode"
									name="findUserCode">
							</div>
							<div class="form-group">
								<label for="newPassword">新密码</label> <input
									class="form-control font-px14" type="password"
									id="findNewPassword" name="findNewPassword">
							</div>
							<div class="form-group">
								<label for="confirmPassword">再次输入密码</label> <input
									class="form-control font-px14" type="password"
									id="findConfirmPassword" name="findConfirmPassword">
							</div>

							<div class="form-group">
								<label for="mobile">绑定手机</label> <input
									class="form-control font-px14" type="text" id="findMobile"
									name="findMobile">
							</div>

							<div class="form-group">
								<label for="findMobileValidateCode">验证码</label> <input
									class="form-control font-px14"
									style="width: 110px;margin-right: 7px;" type="text"
									id="findMobileValidateCode" name="findMobileValidateCode">
								<span id="getFindValidateCodeBtn" style="width:96px;"
									onclick="getValideCode();" class="btn btn-default">获取验证码</span>
								<label id="msg" style="padding: 0 10px;color: red;"></label>
							</div>
						</form>

						<div class="form-group">
							<button style="margin-left:95px;padding: 6px 30px;"
								class="btn btn-info" onclick="commitConfirm();">确定</button>
							<button style="margin-left:31px;padding: 6px 30px;"
								class="btn btn-info" id="findPasswords">退出</button>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script type="text/javascript">
		function checkUserMobile() {
			var result = false;
			$
					.ajax({
						url : "${pageContext.request.contextPath}/mchtUser/checkUserMobile",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						data : {
							id : $("#id").val(),
							mobile : $("#mobile").val()
						},
						timeout : 30000,
						success : function(data) {
							if (data.returnCode == '0000') {
								result = true;
							} else {
								result = false;
							}
						},
						error : function() {
							swal('操作超时，请稍后再试！');
						}
					});
			return result;
		}

		var timer = 0;

		function countdown() {
			if (timer > 0) {
				timer = timer - 1;
				$("#getValidateCodeBtn").html(timer + "秒");
				setTimeout(function() {
					countdown();
				}, 1000);
			} else {
				$("#getValidateCodeBtn").removeClass("disabled");
				$("#getValidateCodeBtn").html("获取验证码");
			}
		}

		function getMobileValideCode() {

			if (timer > 0) {
				return;
			}

			if ($.trim($("#mobile").val()) == "") {
				setErroMsg("请输入手机号！");
				return;
			}

			var reg = /^1\d{10}/;
			if (!reg.test($.trim($("#mobile").val()))) {
				setErroMsg("请输入正确的手机号！");
				return;
			}
			$("#getValidateCodeBtn").addClass("disabled");
			timer = 120;
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
						setErroMsg("验证码已发至您的手机！");
					} else {
						setErroMsg("获取失败，请重新获取！");
					}

				},
				error : function() {
					setErroMsg("获取失败，请重新获取！");
				}
			});
		}

		function getValideCode() {
			if (timer > 0) {
				return;
			}
			if ($.trim($("#findUserCode").val()) == "") {
				setErroMsg("请输入用户名！");
				return;
			}
			if ($.trim($("#findNewPassword").val()) == "") {
				setErroMsg("请输入新密码！");
				return;
			}
			if ($.trim($("#findConfirmPassword").val()) == "") {
				setErroMsg("请确认新密码！");
				return;
			}
			if (($("#findNewPassword").val() != "" || $("#findConfirmPassword")
					.val() != "")
					&& $("#findNewPassword").val() != $("#findConfirmPassword")
							.val()) {
				setErroMsg('两次输入的密码不一致');
				return;
			}

			if ($("#findNewPassword").val().length < 6
					|| $("#findConfirmPassword").val().length < 6) {
				setErroMsg('密码长度不能小于6');
				return;
			}
			if ($.trim($("#findMobile").val()) == "") {
				setErroMsg("请输入手机号！");
				return;
			}
			var reg = /^1\d{10}/;
			if (!reg.test($.trim($("#findMobile").val()))) {
				setErroMsg("请输入正确的手机号！");
				return;
			}
			var result = false;
			$.ajax({
				url : "${pageContext.request.contextPath}/checkExisit",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					userCode : $.trim($("#findUserCode").val()),
					mobile : $.trim($("#findMobile").val())
				},
				timeout : 30000,
				success : function(data) {
					if (data.returnCode == '0000') {
						result = true;
					} else {
						setErroMsg(data.returnMsg);
						result = false;
					}
				},
				error : function() {
					setErroMsg('操作超时，请稍后再试！');
				}
			});

			if (!result) {
				return;
			}
			$("#getFindValidateCodeBtn").addClass("disabled");
			timer = 120;
			countdown();
			$.ajax({
				url : "${ctx}/getMobileValidateCode",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					userCode : $.trim($("#findUserCode").val()),
					mobile : $.trim($("#findMobile").val())
				},
				timeout : 30000,
				success : function(data) {
					if (data.returnCode == "0000") {
						setErroMsg("验证码已发至您的手机！");
					} else {
						setErroMsg("获取失败，请重新获取！");
					}

				},
				error : function() {
					setErroMsg("获取失败，请重新获取！");
				}
			});
		}

		function commitAudit() {

			//判断有没有空值
			var hasEmptyValue = false;
			var emptyErroMsg = "";
			if ($.trim($("#userCode").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",用户名";
			}
			if ($.trim($("#oldPassword").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",原密码";
			}
			if ($.trim($("#newPassword").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",新密码";
			}
			if ($.trim($("#confirmPassword").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",确认密码";
			}

			if ($.trim($("#mobile").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",手机号";
			}

			if ($.trim($("#mobileValidateCode").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",短信验证码";
			}

			if (hasEmptyValue) {
				emptyErroMsg = emptyErroMsg.substring(1);
				emptyErroMsg = "请输入" + emptyErroMsg;
				setErroMsg(emptyErroMsg);
				return;
			}

			if (($("#newPassword").val() != "" || $("#confirmPassword").val() != "")
					&& $("#newPassword").val() != $("#confirmPassword").val()) {
				setErroMsg('两次输入的密码不一致');
				return;
			}

			if ($("#newPassword").val().length < 6
					|| $("#confirmPassword").val().length < 6) {
				setErroMsg('密码长度不能小于6');
				return;
			}

			var result = false;
			$
					.ajax({
						url : "${pageContext.request.contextPath}/mchtUser/checkUserCode",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						data : {
							id : $("#id").val(),
							userCode : $("#userCode").val()
						},
						timeout : 30000,
						success : function(data) {
							if (data.returnCode == '0000') {
								result = true;
							} else {
								setErroMsg(data.returnMsg);
								result = false;
							}
						},
						error : function() {
							setErroMsg('操作超时，请稍后再试！');
						}
					});

			if (!result) {
				return;
			}

			// 			if (!checkUserMobile()) {
			// 				setErroMsg('手机号已经存在!');
			// 				return;
			// 			}

			$.ajax({
				url : "${ctx}/mchtUser/setUserInfoCommit",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					id : $("#id").val(),
					userCode : $("#userCode").val(),
					oldPassword : $("#oldPassword").val(),
					newPassword : $("#newPassword").val(),
					email : $("#email").val(),
					mobile : $("#mobile").val(),
					mobileValidateCode : $("#mobileValidateCode").val()
				},
				timeout : 30000,
				success : function(data) {
					if (data.returnCode == "0000") {
						location.href = "${ctx}/toLogin";
					} else {
						setErroMsg(data.returnMsg);
					}

				},
				error : function() {
					setErroMsg("提交失败");
				}
			});

		}

		function commitConfirm() {

			//判断有没有空值
			var hasEmptyValue = false;
			var emptyErroMsg = "";
			if ($.trim($("#findUserCode").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",用户名";
			}
			if ($.trim($("#findNewPassword").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",新密码";
			}
			if ($.trim($("#findConfirmPassword").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",确认密码";
			}

			if ($.trim($("#findMobile").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",手机号";
			}

			if ($.trim($("#findMobileValidateCode").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",短信验证码";
			}

			if (hasEmptyValue) {
				emptyErroMsg = emptyErroMsg.substring(1);
				emptyErroMsg = "请输入" + emptyErroMsg;
				setErroMsg(emptyErroMsg);
				return;
			}

			if (($("#findNewPassword").val() != "" || $("#findConfirmPassword")
					.val() != "")
					&& $("#findNewPassword").val() != $("#findConfirmPassword")
							.val()) {
				setErroMsg('两次输入的密码不一致');
				return;
			}

			if ($("#findNewPassword").val().length < 6
					|| $("#findConfirmPassword").val().length < 6) {
				setErroMsg('密码长度不能小于6');
				return;
			}

			var result = false;
			$.ajax({
				url : "${pageContext.request.contextPath}/checkExisit",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					userCode : $.trim($("#findUserCode").val()),
					mobile : $.trim($("#findMobile").val())
				},
				timeout : 30000,
				success : function(data) {
					if (data.returnCode == '0000') {
						result = true;
					} else {
						setErroMsg(data.returnMsg);
						result = false;
					}
				},
				error : function() {
					setErroMsg('操作超时，请稍后再试！');
				}
			});

			if (!result) {
				return;
			}

			$
					.ajax({
						url : "${ctx}/saveFindPassword",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						data : {
							userCode : $("#findUserCode").val(),
							newPassword : $("#findNewPassword").val(),
							mobile : $("#findMobile").val(),
							mobileValidateCode : $("#findMobileValidateCode")
									.val()
						},
						timeout : 30000,
						success : function(data) {
							if (data.returnCode == "0000") {
								$("#findPasswordDiv").empty();
								$("#findPasswordDiv")
										.append(
												'<div class="login-title"><div>找回密码</div></div>');
								$("#findPasswordDiv")
										.append(
												'<div class="form-group" style="margin-top: 100px;text-align: center;height:170px;">恭喜您 ，密码找回成功 快去<a href="${ctx}/toLogin">登录</a></div>');
								$("#findSuccessDiv").show();
							} else {
								setErroMsg(data.returnMsg);
							}

						},
						error : function() {
							setErroMsg("提交失败");
						}
					});

		}
	</script>





	<script>
		document.onkeydown = function(event) {
			var e = event || window.event
					|| arguments.callee.caller.arguments[0];
			if (e && e.keyCode == 13) {
				loginSubmit();
			}
			
		};
		
		//错误提示
		function setErroMsg(erroMsg) {
			$(".login-title div")
					.html(
							"<i style='color:red;margin-right:10px;' class='glyphicon glyphicon-warning-sign'></i>"
									+ erroMsg);
			$(".login-title div").addClass("erroMsg");
			$(".login-title").addClass("left-align");		
			
		}
		
		function loginSubmit() {
			//判断有没有空值
			var hasEmptyValue = false;
			var emptyErroMsg = "";
			if ($.trim($("#userCode").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",帐号";
			}

			if ($.trim($("#password").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",密码";
			}
			if ($.trim($("#validCode").val()) == "") {
				hasEmptyValue = true;
				emptyErroMsg = emptyErroMsg + ",验证码";
			}

			if (hasEmptyValue) {
				emptyErroMsg = emptyErroMsg.substring(1);
				emptyErroMsg = "请输入" + emptyErroMsg;
				setErroMsg(emptyErroMsg);
				return;
			}

			var datas = $("#loginForm").serialize();
			$.ajax({
				url : "${ctx}/login",
				type : 'POST',
				data : datas,
				dataType : 'json',
				timeout : 10000,
				error : function() {
					setErroMsg("登陆出错~，请重新登录!");
				},
				success : function(data) {
					if (data.returnCode == "0000") {
						location.href = "${ctx}/";
					} else {
						setErroMsg(data.returnMsg);	
						//清除
						window.setTimeout(function(){
						$(".login-title div").html("<i style='color:red;margin-right:10px;' class='glyphicon glyphicon-warning-sign'></i>"+"请核对相应信息后，重新登录")
						},1000);			
					}

				}
			});
			changeImage();	
		}
		//自动刷新验证码
		function changeImage() {
			var tagImg = document.getElementById('captchaImg');
			if (tagImg != "undefined") {
				tagImg.src = "getCaptcha?ram=" + Math.random();
			}
		}
		
		//手动刷新验证码
		reloadCaptcha = function(path) {
			var src = "getCaptcha?ram=" + Math.random();
			$("#captchaImg").attr("src",(typeof path != "undefined") ? (path + "/" + src): src);
		}
		
		//找回密码
		$(function() {
			$("#findPassword").on('click', function() {
				console.log(111);
				$("#loginDiv").hide();
				$("#findPasswordDiv").show();
			});

			$("#findPasswords").on('click', function() {
				$("#loginDiv").show();
				$("#findPasswordDiv").hide();
			});

		});
	</script>
</body>
</html>
