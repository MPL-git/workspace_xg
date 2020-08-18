<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>公司信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
      <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">修改密码</div>
		</div>
		
		<div class="x_content container-fluid sp-gl">
			<div class="row">
				<div class="col-md-12 datatable-container at-table">
					<form id="dataFrom">
						<input type="hidden" value="${mchtUser.id}" name="id" id="id">

						<table
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<tbody>
								<tr>
									<td class="editfield-label-td">用户名</td>
									<td colspan="2" class="text-left"><input type="text"
										id="userCode" name="userCode" value="${mchtUser.userCode}"
										validate="{required:true,userCodeUnique:true}"
										disabled="disabled"> <a href="javascript:void(0);"
										onclick="$('#userCode').attr('disabled',false)">修改</a></td>
								</tr>
								<tr>
									<td class="editfield-label-td">密码</td>
									<td colspan="2" class="text-left"><input type="password"
										id="oldPassword" name="oldPassword" value=""
										validate="{required:true}"></td>
								</tr>
								<tr>
									<td class="editfield-label-td">新密码</td>
									<td colspan="2" class="text-left"><input type="password"
										id="newPassword" name="newPassword" value=""></td>
								</tr>

								<tr>
									<td class="editfield-label-td">再次输入密码</td>
									<td colspan="2" class="text-left"><input type="password"
										id="confirmPassword" name="confirmPassword" value=""
										validate="{passwordRequired:true}"></td>
								</tr>
								<tr>
									<td class="editfield-label-td">绑定邮箱</td>
									<td colspan="2" class="text-left"><input type="text"
										id="email" name="email" value="${mchtUser.email}"
										validate="{email:true}"></td>
								</tr>
								<tr>
									<td class="editfield-label-td">绑定手机</td>
									<td colspan="2" class="text-left"><input type="text"
										id="mobile" name="mobile" value="${mchtUser.mobile}"
										validate="{required:true,mobileUnique:true}"
										disabled="disabled"> <a href="javascript:void(0);"
										onclick="$('#mobile').attr('disabled',false)">修改</a></td>
								</tr>
								<tr>
									<td class="editfield-label-td">短信验证码</td>
									<td colspan="2" class="text-left"><input type="text"
										id="mobileValidateCode" name="mobileValidateCode" value=""
										validate="{required:true}"> <span
										id="getValidateCodeBtn" style="width:96px;"
										onclick="getMobileValideCode();" class="btn btn-default">获取验证码</span>
										<label id="msg" style="padding: 0 10px;color: red;"></label></td>
								</tr>



							</tbody>
						</table>
					</form>
					<div class="modal-footer">

						<button class="btn btn-info" onclick="commitAudit();">提交</button>
						<button class="btn btn-info" data-dismiss="modal">取消</button>
					</div>

				</div>
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
    <script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>	
	
<script type="text/javascript">




var dataFromValidate;

$(function(){
	
	$.metadata.setType("attr", "validate");
	//密码输入框验证
    $.validator.addMethod("passwordRequired", function(value, element) {   
    	if(($("#newPassword").val()!=""||$("#confirmPassword").val()!="")&&$("#newPassword").val()!=$("#confirmPassword").val()){
    		return false;
    	}else{
    		return true;
    	}
    }, "两次输入的密码不一致");
	
    $.validator.addMethod("userCodeUnique", function(value, element) {
    	var result=false;
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtUser/checkUserCode",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {
				id : $("#id").val(),
				userCode:$("#userCode").val()
			},
			timeout : 30000,
			success : function(data) {
				if(data.returnCode=='0000'){
					result=true;
				}else{
					result=false;
				}
			},
			error : function() {
				swal('操作超时，请稍后再试！');
			}
		});
		return result;
    }, "用户名已经存在");
    
    
    
    $.validator.addMethod("mobileUnique", function(value, element) {
    	return checkUserMobile();
    }, "手机号已经存在");
    
	
	
	dataFromValidate =$("#dataFrom").validate({
        highlight : function(element) {  
        	$(element).addClass('error');
            $(element).closest('tr').find("td").first().addClass('has-error');  
        },
        errorPlacement: function(error, element) {  
        	error.appendTo($(element).closest('td'));
        },
        success : function(label) {  
            label.closest('tr').find("td").first().removeClass('has-error');  
        }
	});
});







function checkUserMobile(){
	var result=false;
	
	$.ajax({
		url : "${pageContext.request.contextPath}/mchtUser/checkUserMobile",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {
			id : $("#id").val(),
			mobile:$("#mobile").val()
		},
		timeout : 30000,
		success : function(data) {
			if(data.returnCode=='0000'){
				result=true;
			}else{
				result=false;
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
	}
	

	
	if ($.trim($("#mobile").val()) == "") {
		$("#msg").html("请输入手机号！");
		return;
	}
	
	var reg=/^1\d{10}/;
	if (!reg.test($.trim($("#mobile").val()))) {
		$("#msg").html("请输入正确的手机号！");
		return;
	}
	if(!dataFromValidate.element($("#mobile"))){
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



function commitAudit(){
	if(dataFromValidate.form()){
			
			$.ajax({
				url : "${ctx}/mchtUser/setUserInfoCommit",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					id:$("#id").val(),
					userCode:$("#userCode").val(),
					oldPassword:$("#oldPassword").val(),
					newPassword:$("#newPassword").val(),
					email:$("#email").val(),
					mobile:$("#mobile").val(),
					mobileValidateCode:$("#mobileValidateCode").val()
				},
				timeout : 30000,
				success : function(data) {
					if (data.returnCode=="0000") {
						isReload=true;
						$("#viewModal").modal('hide');
						swal({
							  title: "提交成功!",
							  type: "success",
							  confirmButtonText: "确定"
							  
							});
					} else {
						swal({
							  title: data.returnMsg,
							  type: "error",
							  confirmButtonText: "确定"
							});
					}
					
				},
				error : function() {
					swal({
						  title: "提交失败！",
						  type: "error",
						  confirmButtonText: "确定"
						});
				}
			});
		
		

	}
}

</script>
</body>
</html>
