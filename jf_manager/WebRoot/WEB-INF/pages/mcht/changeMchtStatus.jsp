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
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />

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
function statusChange(_this){
	var status = $(_this).val();
	if(status == 3){
		$("#closeReasonTr").hide();
		$("#closeTr").show();
		$("#remarksSpan").hide();
		$("#lastCloseRemarksSpan").show();
	}else if(status == 2){
		$("#closeReasonTr").show();
		$("#closeTr").hide();
		$("#lastCloseRemarksSpan").hide();
		$("#remarksSpan").show();
	}else{
		$("#closeReasonTr").hide();
		$("#closeTr").hide();
		$("#lastCloseRemarksSpan").hide();
		$("#remarksSpan").show();
	}
}

function mchtStatusChangeSubmit(){
	var status = $("#status").val();
	var remarks=$("#remarks").val();
	var lastCloseRemarks=$("#lastCloseRemarks").val();
	var closeDate=$("#closeDate").val();
	var closeReason = $("#closeReason").val();
	if(status == 2){
		if(!closeReason){
			commUtil.alertError("请选择关店原因");
			return;
		}
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtStatusChangeSubmit.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {
			mchtId : ${mchtId},
			status:status,
			remarks:remarks,
			lastCloseRemarks:lastCloseRemarks,
			closeDate:closeDate,
			closeReason:closeReason
		},
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
		<table class="gridtable">
			<tr>
			<td  colspan="1" class="title">合作状态<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					
				<select style="width: 160px;" id="status" name="status" onchange="statusChange(this);">
					<c:forEach var="statusItem" items="${mchtInfoStatus}">
						<c:if test="${status eq 0}">
							<option	<c:if test="${status==statusItem.statusValue}">selected</c:if> 
								value="${statusItem.statusValue}">${statusItem.statusDesc}
							</option>
						</c:if>
						<c:if test="${status ne 0}">
							<c:if test="${statusItem.statusValue ne 5}">
								<option	<c:if test="${status==statusItem.statusValue}">selected</c:if> 
									value="${statusItem.statusValue}">${statusItem.statusDesc}
								</option>
							</c:if>
						</c:if>
						
					</c:forEach>
				</select>
			</td>
			</tr>
			
			<tr id="closeReasonTr" <c:if test="${status ne 2}">style="display:none;"</c:if>>
				<td  colspan="1" class="title">关店原因<span style="color: red;">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<select style="width: 160px;" id="closeReason" name="closeReason" >
						<option value="">请选择</option>
						<c:forEach items="${closeReasonList}" var="item">
							<option value="${item.statusValue}" <c:if test="${closeReason eq item.statusValue}">selected="selected"</c:if>>${item.statusDesc}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr id="closeTr" <c:if test="${status ne 3}">style="display:none;"</c:if>>
				<td  colspan="1" class="title">店铺关闭日期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<input type="text" id="closeDate" name="closeDate" value="${lastCloseDate}"/>
	               	<script type="text/javascript">
						$(function(){
							$("#closeDate").ligerDateEditor({
								showTime : false,
								labelWidth : 150,
								width:160,
								labelAlign : 'left'
							});
						});
					</script>
				</td>
			</tr>
			
			<tr>
			<td  colspan="1" class="title">备注</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				<span <c:if test="${status eq 3}"> style="display:none;"</c:if> id="remarksSpan">
					<textarea rows="3" style="width:80%;" name="remarks" id="remarks">${remarks}</textarea>
				</span>
				<span <c:if test="${status ne 3}"> style="display:none;"</c:if> id="lastCloseRemarksSpan">
					<textarea rows="3" style="width:80%;" name="lastCloseRemarks" id="lastCloseRemarks">${lastCloseRemarks}</textarea>
				</span>
			</td>
			</tr>
			
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<c:if test="${STAFF_ID!=1}">
							<input name="btnSubmit" type="button" id="Button1" onclick="mchtStatusChangeSubmit();"
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
