<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
 	.radioClass {
		margin-right: 20px;
	}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
    .activity-time {
    	margin-left: 50px;
    }
    .activity-hint {
    	color: #6B6B6B;
    }
</style>
<script type="text/javascript">
	var submitFlag = true;
	$(function(){
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 160
		});
		
		var status = $("#status").val();
		if(status == '1' || status == '2') {
    		$("#tr-rejectReason").hide();
    	}else if(status == '3') {
    		$("#tr-rejectReason").show();
    	}
		
		$.metadata.setType("attr", "validate");
		$("#form1").validate({
	        errorPlacement: function (lable, element) {  
	        	var elementType=$(element).attr("type");
	        	if('radio'==elementType) {
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").parent("span").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else {
	        		$(element).ligerTip({
						content : lable.html(),width: 100
					});
	        	}
	        	$(".l-verify-tip-corner").css("z-index", "1001");
	        	$(".l-verify-tip-content").css("z-index", "1000");
	        },
	        success: function (lable,element) {
	            lable.ligerHideTip();
	            lable.remove();
	        },
	        submitHandler: function(form) {
	        	var status = $("#status").val();
	        	if(status == '1') {
	        		$("#rejectReason").val("提供支付宝账号错误/不存在，或对方关闭了“通过手机号找到我”");
	        	}else if(status == '2') {
					$("#rejectReason").val("麻烦进行支付宝的实名认证");
				}else if(status == '4'){
					$("#rejectReason").val("您已多次提现失败，请尽快联系官方客服电话0592-3289731处理您的提现");
	        	}else if(status == '3') {
	        		if($("#rejectReason").val() == '') {
	        			commUtil.alertError("驳回备注不能为空");
	        			return;
	        		}else if($("#rejectReason").val().length > 120) {
		        		commUtil.alertError("最多填写120个字符");
		        		return;
	        		}
	        	}
	        	if(submitFlag){
	        		submitFlag = false;
	        		form.submit();
	        	}
	        }
	    }); 
		
	});
	
	function rejectReasonfun(status) {
		$("#rejectReason").val("");
		if(status == '1' || status == '2' || status == '4') {
    		$("#tr-rejectReason").hide();
    	}else if(status == '3') {
    		$("#tr-rejectReason").show();
    	}
	}
	
	
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/marketing/cashTransfer/updateStatus.shtml" >
		<input type="hidden" id="id" name="id" value="${id }" />
		<table class="gridtable">
           	<tr>
            	<td class="title" width="20%">驳回原因<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="status" name="status" onchange="rejectReasonfun(this.value);" >
						<option value="1">提供支付宝账号错误/不存在，或对方关闭了“通过手机号找到我”</option>
						<option value="2">麻烦进行支付宝的实名认证</option>
						<option value="3">其他</option>
						<option value="4">您已多次提现失败，请尽快联系官方客服电话0592-3289731处理您的提现</option>
					</select>
				</td>
           	</tr>
           	<tr id="tr-rejectReason" >
           		<td class="title" width="20%">驳回备注<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="5" cols="50" id="rejectReason" name="rejectReason" ></textarea>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>