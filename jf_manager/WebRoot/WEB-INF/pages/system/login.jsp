<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理平台</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="common/js/utils/util.js"></script>

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
	$(function(){
		var loginErrorCountFlag = '${loginErrorCountFlag }';
		var mobilePhoneFlag = '${mobilePhoneFlag }';
		if(mobilePhoneFlag == 'false') {
			commUtil.alertError("为账号安全，请系统人事部设置手机号码！");
			$(".l-dialog-content").css("line-height", "150%");
			$(".l-dialog-content").css("line-height", "150%");
			$(".l-dialog-content").css("margin-right", "15px");
			$(".l-dialog-btn-inner,.l-dialog-title").css("line-height", "200%");
		}else if(Number(loginErrorCountFlag) > 5) {
			$.ligerDialog.open({
				height: 300,
				width: 400,
				title: "设置",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/system/sendSmsManager.shtml",
				showMax: false,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
			$(".l-dialog-btn-inner,.l-dialog-title").css("line-height", "200%");
		}
		
	});
	
	function doSubmit() {
		if($.trim($("#username").val()) == "") {
			$("#error_code").html("用户名不能为空");
			return false;
		}
		if($.trim($("#password").val()) == "") {
			$("#error_code").html("密码不能为空");
			return false;
		}
		if($.trim($("#validCode").val()) == "" && $("#captchaFlag").val() != '1') {
			$("#error_code").html("验证码不能为空");
			return false;
		}
		return true;
	}
	
	function reloadCaptcha(path) {
		var src = "${pageContext.request.contextPath}/system/getCaptcha.shtml?ram=" + Math.random();
		$("#captchaImg").attr("src", (typeof path != "undefined") ? (path + "/" + src) : src);
	}
	
	function chlidFun(captchaFlag, username, password) {
		$("#captchaFlag").val(captchaFlag);
		$("#username").val(username);
		$("#password").val(password);
		$("#form1").submit();
	}
	
</script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="#"><img src="images/login/login_logo.gif" /></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="#">反馈</a></li>
						<li><a href="#" target="_blank">帮助</a></li>
					</ul>
				</div>
				<h2 class="login_title">
					<img src="images/login/login_title.png" />
				</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form id="form1" action="login.shtml" method="post" onsubmit="return doSubmit();">
					<input type="hidden" id="captchaFlag" name="captchaFlag" value="${captchaFlag }" />
					<p>
						<label>用户名：</label> <input type="text" id="username" name="username" size="20" style="width: 150px"/>
					</p>
					<p>
						<label>密码：</label> <input type="password" id="password" name="password" size="20"  style="width: 150px" />
					</p>
					<c:if test="${captchaFlag != 1 }">
						<p>
							<label>验证码：</label> <input type="text" id="validCode" name="validCode" size="20"  style="width: 67px;vertical-align: top;margin-right: 20px;" />
							<span class="verifycode" title="点击图片刷新">
								<img style="width: 60px;height:23px;" id="captchaImg" onclick="reloadCaptcha();" src="${pageContext.request.contextPath}/system/getCaptcha.shtml" />
							</span>
						</p>
					</c:if>
					<p style="text-align: center; color: red;">
						<span id="error_code" >${RESULT_MESSAGE }</span>&nbsp;
					</p>
					<c:if test="${captchaFlag == 1 }">
						<p>&nbsp;</p>
					</c:if>
					
					<div class="login_bar" style="margin-top: 25px;">
						<input class="sub" type="submit" value="" />
					</div>
				</form>
			</div>
			<div class="login_banner">
				<img src="images/login/login_banner.jpg" />
			</div>
			<div class="login_main">
				<div class="login_inner">&nbsp;</div>
			</div>
		</div>
		<div id="login_footer">Copyright &copy; 2017. All Rights Reserved.</div>
	</div>
</body>
</html>