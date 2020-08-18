<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
</script>


<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}



.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}
</style>
<script type="text/javascript">





$(function ()  {
	$.metadata.setType("attr", "validate");
	//密码输入框验证
    $.validator.addMethod("passwordRequired", function(value, element) {   
    	if(($("#password").val()!=""||$("#confirmPassword").val()!="")&&$("#password").val()!=$("#confirmPassword").val()){
    		return false;
    	}else{
    		return true;
    	}
    }, "两次输入的密码不一致");
	
	
	            var v = $("#form1").validate({
	            	
	            	rules:{
	            		"password": {
	         		        minlength: 6
	         		      },
	         		      "confirmPassword": {
	         		    	passwordRequired: true,
	         		        minlength: 6
	         		      }
	            	},
	            	
	            	messages:{
	            		password: {
	            	        minlength: "密码长度不能小于 6 个字母"
	            	      },
	            	      confirmPassword: {
	            	        minlength: "密码长度不能小于 6 个字母",
	            	      }
	            	},
	            	
	                errorPlacement: function (lable, element)
	                {   console.log(lable[0].innerText);
	                	if($(element).hasClass("l-text-field")){
	                		$(element).parent().ligerTip({
								content : lable.html(),width: 100
							});
	                	}else{
	                		$(element).ligerTip({
								content : lable.html(),width: 100
							});
	                	}
	                	
	                	
	                },
	                success: function (lable,element)
	                {
	                    lable.ligerHideTip();
	                    lable.remove();
	                },
	                submitHandler: function (form)
	                {   
	                	mchtUserChangeSubmit();
	                }
	            });
	            
	            
	            
	  });    



function mchtUserChangeSubmit(){
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtUserChangeSubmit.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : $("#form1").serialize(),
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				parent.location.reload();
				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}




</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" name="id" value="${mchtUser.id}">
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">用户名<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="userCode"
					name="userCode" type="text" value="${mchtUser.userCode}" validate="{required:true}"
					style="float:left;" /></td>
			</tr>
			<tr>
				<td  colspan="1" class="title">密码</td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="password"
					name="password" type="password" value=""
					style="float:left;" /></td>
			</tr>
			<tr>
			<td  colspan="1" class="title">确认密码</td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="confirmPassword"
					name="confirmPassword" type="password" value=""
					style="float:left;" /></td>
			</tr>
			<tr>
			
				<td  colspan="1" class="title">用户手机号 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input id="mobile" 	name="mobile" type="text" value="${mchtUser.mobile}"
					style="float:left;" validate="{required:true,mobile:true}" /></td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">用户邮箱<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="email" 
					name="email" type="text" value="${mchtUser.email}" validate="{required:true,email:true}"
					style="float:left;" /></td>
			</tr>
			
			
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<c:if test="${STAFF_ID!=1}">
							<input name="btnSubmit" type="submit" id="Button1" 
								style="float:left;" class="l-button l-button-submit" value="提交"
								 />
						</c:if>
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />

					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
