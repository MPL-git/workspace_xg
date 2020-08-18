<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
	.radioClass{margin-right: 20px;}
	.hidden{display:none;}
</style>

<script type="text/javascript">
var onlineContract_viewer; 
$(function(){
	$("#fwConfirm").bind('click',function(){
		var renewRemarks = $("#renewRemarks").val();
		if(!renewRemarks){
			commUtil.alertError("备注不可为空");
			return;
		}
		var dataJson = $("#form1").serializeArray();
		dataJson.push({"name":"id",value:JSON.stringify($('#id').val())});
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtContract/reNewRebuildMchtContract.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data :  dataJson,
			timeout : 30000,
			success : function(data) {
				if ("999" == data.resultCode) {
					$.ligerDialog.error(data.resultMsg);
				}else{
					$.ligerDialog.alert("生成成功！", function (yes) { 
						parent.parent.location.reload();
					});		
				}			
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
});
</script>

<html>
<body>
<input type="hidden" id="id" name="id" value="${mchtContract.id}">
<form method="post" id="form1" name="form1">
		<div class="title-top">
		<table class="gridtable" style="font-family:none;">
          	<tr>
          		<td class="title">合同开始时间</td>
          			<td><input type="text" id="beginDate" name="beginDate" value="${beginDate}"/></td>
          			<script type="text/javascript">
					$(function() {
						$("#beginDate").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
					</script>
          	</tr>
          	<tr>
          		<td class="title">合同结束时间</td>
          		<td><input type="text" id="endDate" name="endDate"  value="${endDate}"/></td>
          			<script type="text/javascript">
						$(function() {
							$("#endDate").ligerDateEditor( {
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script>
          	</tr>
          	<tr>
          		<td class="title">店铺保证金</td>
          		<td>${mchtInfo.totalAmt}</td>
          	</tr>
          	<tr>
          		<td class="title">备注<span style="color:red;">*</span></td>   		
          		<td><textarea rows="5" cols="90" id="renewRemarks" name="renewRemarks" maxlength="256"></textarea></td>
          	</tr>
          	<tr>
          		<td class="title">操作</td>
          		<td>
					<input id="fwConfirm" type="button" style="float:left;" class="l-button l-button-test" value="提交"/>
				</td>
          	</tr>
        </table>
		</div>
		</form>
</body>
</html>
