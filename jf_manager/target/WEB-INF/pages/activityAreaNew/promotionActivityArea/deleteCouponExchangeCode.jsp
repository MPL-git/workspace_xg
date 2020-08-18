<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
	 <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
 	.radioClass{margin: 0 10px 0 10px;}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
</style>
<script type="text/javascript">
		
	$(function(){
		$("#status").bind('change', function(){
			var status = $("#status").val();
			if(status == '1') {
				$("#code-td").show();
				$("#batchNum-td").hide();
				$("#codeFile-td").hide();
			}else if(status == '2') {
				$("#code-td").hide();
				$("#batchNum-td").show();
				$("#codeFile-td").hide();
			}else if(status == '3') {
				$("#code-td").hide();
				$("#batchNum-td").hide();
				$("#codeFile-td").show();
			}
		});
	});
	
	function subFunction() {
		var status = $("#status").val();
		if(status == '1') {
			if($.trim($("#code").val()) == '') {
				commUtil.alertError("号码不能为空！");
				return;
			}
		}else if(status == '2') {
			if($.trim($("#batchNum").val()) == '') {
				commUtil.alertError("生成批次不能为空！");
				return;
			}
		}else if(status == '3') {
			if($.trim($("#codeFile").val()) == '') {
				commUtil.alertError("请选择txt格式文件！");
				return;
			}else {
				var file = $("#codeFile").val();
				var exec = (/[.]/.exec(file)) ? /[^.]+$/.exec(file.toLowerCase()) : '';  
		        if (exec != "txt") {  
		        	commUtil.alertError("文件格式不对，请上传txt文件!");  
		            return;  
		        }  
			}
		}
		$("[name='form1']").submit();
	}
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" method="post" action="${pageContext.request.contextPath}/couponExchangeCode/updateDelCouponExchangeCode.shtml" enctype="multipart/form-data" >
		<input type="hidden" id="couponId" name="couponId" value="${couponId }" />
		<table class="gridtable">
			<tr>
				<td colspan="2" style="border: none;height: 35px;">
					<span>注意：只对未兑换的游离码进行作废，已兑换的直接跳过。文件要求：TXT格式、一行一个值。</span>
				</td>
			</tr>
			<tr align="center">
				<td>
					<select id="status" name="status" style="width: 135px;" >
						<option value="1" selected="selected" >号码</option>
						<option value="2">生成批次</option>
						<option value="3">文件</option>
					</select>
				</td>
				<td id="code-td">
					<input type="text" id="code" name="code" value="" /> 
				</td>
				<td id="batchNum-td" style="display: none;">
					<input type="text" id="batchNum" name="batchNum" value="" /> 
				</td>
				<td id="codeFile-td" style="display: none;">
					<input type="file" name="file" id="codeFile" /> 
				</td>
			</tr>
			<tr align="center">
				<td colspan="2" style="border: none;height: 50px;">
					<input type="button" onclick="subFunction();" class="l-button l-button-submit" value="提交" /> 
				</td>
			</tr>
		</table>
	</form>
	</body>
</html>