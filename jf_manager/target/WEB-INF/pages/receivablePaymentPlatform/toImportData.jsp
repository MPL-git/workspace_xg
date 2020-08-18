<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript">
$(function ()  {
	$("#save").bind('click',function(){
		var paymentType = $.trim($("#paymentType").val());
		if(!paymentType){
			commUtil.alertError("请选择支付平台");
			return false;
		}
		var excelFilePath = $.trim($("#excelFilePath").val());
		if(!excelFilePath){
			commUtil.alertError("请先上传文件");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/receivablePaymentPlatform/save.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"paymentType":paymentType,"excelFilePath":excelFilePath},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$.ligerDialog.confirm("共"+data.total+"条，成功导入："+data.successTotal+"条,已存在："+data.exisitTotal+"条,异常："+data.errorTotal+"条", function (yes) 
					{ 
						if(yes){
							parent.location.reload();
							frameElement.dialog.close();	
						}
					});
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
});
function fileUpload() {
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: "file",
		dataType: 'json',
		success: function(result, status) {
			console.log(result);
			if(result.RESULT_CODE == 0) {
				$("#excelFilePath").val(result.FILE_PATH);
				$("#fileInfoDesc").text("已上传文件："+result.FILE_PATH.substring(result.FILE_PATH.lastIndexOf("/")+1));
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}
</script>

<html>
<body>
	<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/receivablePaymentPlatform/save.shtml">
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">表格前三列</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					日期、交易号、金额
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">支付平台</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					<select style="width: 120px;" id="paymentType" name="paymentType">
						<option value="">请选择</option>
						<option value="1">微信(APP、H5)</option>
						<option value="2">微信(公众号)</option>
						<option value="3">支付宝(APP、H5)</option>
					</select>
	    		</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">上传文件</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div style="float: left;height: 45px;margin: 10px;">
	    				<input style="position:absolute; opacity:0;" type="file" id="file" name="file" onchange="fileUpload();" />
						<a href="javascript:void(0);" style="width:30%;">上传文件</a>
						<br>
						<span id="fileInfoDesc"></span>
	    			</div>
	    			<input id="excelFilePath" name="excelFilePath" type="hidden">
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="save" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
