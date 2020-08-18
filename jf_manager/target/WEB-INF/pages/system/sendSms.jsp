<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

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
<style type="text/css">
	.login-table{
		font-size: 12px;
		width: 100%;
		
	}
	.login-title{
		width: 30%;
	}
	.login-table tr{
		height: 40px;
		line-height: 40px;
	}
	.login-button{
		height: 28px;
		overflow: hidden;
		width: 80px;
		line-height: 28px;
		cursor: pointer;
		position: relative;
		text-align: center;
		border: solid 1px #A3C0E8;
		color: #333333;
		background: #E0EDFF;
		border-radius:2px;
	}
	.send-button{
		height: 22px;
		overflow: hidden;
		width: 50px;
		line-height: 22px;
		cursor: pointer;
		position: relative;
		text-align: center;
		border: solid 1px #A3C0E8;
		color: #333333;
		background: #fcfcfc;
		border-radius:3px;
	}
	.send-span{
		margin-left: 10px;
	}
	.time-span{
		width: 50px;
		height: 22px;
		line-height: 22px;
		border: solid 1px #A3C0E8;
		color: #333333;
		background: #ccccff;
		border-radius:3px;
		display: inline-block;
		text-align: center;
	}
</style>
<script type="text/javascript">
	$(function(){
		$(".time-span").hide();
		var validCodeMsg = '${validCodeMsg }';
		if(validCodeMsg != '') {
			commUtil.alertError(validCodeMsg);
		}
		
		$(".send-button").bind('click', function(){
			$(this).hide();
			$(this).val("重发");
			var time = 120;
			$(".time-span").html(time);
			$(".time-span").show();
			sendSms();
			var interval = setInterval(function(){
				if(time == 1) {
					clearInterval(interval);
					$(".send-button").show();
					$(".time-span").hide();
					clearValidCode();
				}else {
					--time;
					$(".time-span").html(time);
				}
			}, 1000);
		});
		
		$(".sub-form1").bind('click', function(){
			var validCode = $("#validCode").val();
			if($.trim(validCode) == '') {
				commUtil.alertError("短信验证码不能为空！");
			}else {
				$.ajax({
					 type: 'post',
					 url: '${pageContext.request.contextPath }/system/mobilePhoneVerify.shtml',
					 data: {validCode : validCode},
					 dataType: 'json',
					 success: function(data) {
						 if(data == null || data.code != 200){
						     commUtil.alertError(data.msg);
						 }else {
							 if(data.validCodeMsg == null) {
								 parent.chlidFun('1', data.username, data.password);
							 }else {
								 commUtil.alertError(data.validCodeMsg);
							 }
						 }
					 },
					 error: function(e) {
						 commUtil.alertError("系统异常请稍后再试！");
					 }
				});
			}
		});
		
	});
	
	function sendSms() {
		$.ajax({
			 type: 'post',
			 url: '${pageContext.request.contextPath }/system/sendSms.shtml',
			 data: {},
			 dataType: 'json',
			 success: function(data) {
				 if(data == null || data.code != 200){
				     commUtil.alertError(data.msg);
				 }
			 },
			 error: function(e) {
				 commUtil.alertError("系统异常请稍后再试！");
			 }
		 });
	}
	
	function clearValidCode() {
		$.ajax({
			 type: 'post',
			 url: '${pageContext.request.contextPath }/system/clearValidCode.shtml',
			 data: {},
			 dataType: 'json',
			 success: function(data) {
				 if(data == null || data.code != 200){
				     commUtil.alertError(data.msg);
				 }
			 },
			 error: function(e) {
				 commUtil.alertError("系统异常请稍后再试！");
			 }
		 });
	}
	
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="" >
		<input type="hidden" id="password" name="password"  />
		<input type="hidden" id="username" name="username"  />
		<table class="login-table" >
			<tr>
            	<td colspan="2">
            		<span style="margin-left: 30px;">为账号安全，请输入手机短信验证码：</span>
            	</td>
           	</tr>
			<tr>
            	<td class="login-title">
            		<span style="margin-left: 30px;">手机号码</span>
            	</td>
				<td>
					${mobilePhone }
				</td>
           	</tr>
			<tr>
            	<td class="login-title">
            		<span style="margin-left: 30px;">短信验证码</span>
            	</td>
				<td>
					<input type="text" id="validCode" name="validCode" value="${validCode }" />
					<span class="send-span">
						<input type="button" class="send-button" value="发送" /> 
						<span class="time-span" ></span>
					</span>
				</td>
           	</tr>
			<tr style="height: 60px; line-height: 60px;">
            	<td style="text-align: center;" colspan="2">
            		<input type="button" class="login-button sub-form1" value="下一步" /> 
            	</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>