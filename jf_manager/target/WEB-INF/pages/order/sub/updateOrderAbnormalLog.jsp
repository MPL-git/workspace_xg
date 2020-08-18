<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.color-1{color: #9D999D;}
.color-2{color: #FC0000;}
.color-3{color: #EFD104;}
.color-4{color: #00FC28;}
.color-5{color: #0351F7;}
.color-6{color: #DF00FB;}
</style>
<html>
<script type="text/javascript">
function flagSet(seqNo){
	var flag="color-"+seqNo;
	$("#flagSet").removeClass();
	$("#flagSet").attr("class","glyphicon glyphicon-flag "+flag);
	document.getElementById("remarksColor").value=seqNo;
}

$(function(){
	$("#confirm").bind('click',function(){
		var followStatus = $("#followStatus").val();
		if(!followStatus){
			commUtil.alertError("请选择跟单状态");
			return false;
		}
		var remarks = $("#remarks").val();
		if(!remarks){
			commUtil.alertError("备注不能为空");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/order/sub/updateOrderAbnormalLog.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : $("#form1").serialize(),
			timeout : 30000,
			success : function(data) {
				if ("200" == data.returnCode) {
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
	});
});

</script>
<body>
	<form method="post" id="form1" name="form1" >
			<input type="hidden" id="orderAbnormalLogId" name="orderAbnormalLogId" value="${orderAbnormalLogId }">
			<input type="hidden" id="subOrderId" name="subOrderId" value="${subOrderId }">
			<input type="hidden" id="remarksColor" name="remarksColor" value="1">
			<table class="gridtable">
           	<tr>
               <td class="title">跟单状态*</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<select id="followStatus" name="followStatus">
						<option value="">请选择</option>
						<c:forEach items="${followStatusList }" var="followStatus">
							<option value="${followStatus.statusValue }">${followStatus.statusDesc }</option>
						</c:forEach>
					</select>
               </td>
			</tr>
           	<tr>
               <td class="title">旗帜*</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-1' aria-hidden='true' onclick="flagSet(1);"></span>
					<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-2' aria-hidden='true' onclick="flagSet(2);"></span>
					<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-3' aria-hidden='true' onclick="flagSet(3);"></span>
					<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-4' aria-hidden='true' onclick="flagSet(4);"></span>
					<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-5' aria-hidden='true' onclick="flagSet(5);"></span>
					<span style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-6' aria-hidden='true' onclick="flagSet(6);"></span>
               </td>
			</tr>
           	<tr>
               <td class="title">备注*</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<textarea id="remarks" name="remarks" rows="4" cols="45" class="text" ></textarea>
               </td>
			</tr>
           	<tr>
               <td class="title">旗帜设置为：</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<span id="flagSet" style='padding:0 10px;font-size:20px;' class='glyphicon glyphicon-flag color-1' aria-hidden='true'></span>
               </td>
			</tr>
						
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
	</form>
</body>
</html>
