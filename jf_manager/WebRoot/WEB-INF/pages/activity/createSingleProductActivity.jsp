<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
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
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
	  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	
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
.title{width: 200px;}
.textarea{margin-left: 210px;}
</style>
<script type="text/javascript">

$(function(){
	//活动报名时间
	$("#enrollBeginTime").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	$("#enrollEndTime").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	
	//活动开始时间
	
	$("#activityBeginTime").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	$("#activityEndTime").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	
	<c:if test="${not empty activityArea}">
	 if (${activityArea.status}==1){
		var form = document.forms[0];
    	for ( var i = 0; i < form.length; i++) {
    		var element = form.elements[i];
    		element.disabled = "true";
    	}
	}
	</c:if>
});

function typeOnclick(p){
	$("#type").val(p);
	if(p==6){
		if($("#brand").prop("checked")){
			$("#single").attr("checked", false);
		}
	}else if(p==7){
		if($("#single").prop("checked")){
			$("#brand").attr("checked", false);
		}
	}
	
}

//专区活动图片
function ajaxFileUploadPic() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml?fileType=3',
		secureuri: false,
		fileElementId: "areaPicFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#areaPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
				$("#pic").val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}

//是否启用
function statusOnclick(p){
	$("#status").val(p);
	if(p==0){
		if($("#statusWeishiy").prop("checked")){
			$("#statusEnable").attr("checked", false);
		}
	}else if(p==1){
		if($("#statusEnable").prop("checked")){
			$("#statusWeishiy").attr("checked", false);
		}
	}
	
}


//提交
function createActivityArea(){
	
	if($("#name").val().length==0){//活动名称
		commUtil.alertError("活动名称不能为空");
		return;
	}
	if($("#type").val().length==0){//活动类型
		commUtil.alertError("请选择活动类型");
		return;	
	}
	
	if($("#pic").val().length==0){
		commUtil.alertError("活动图不能为空，请选择上传图片");
		return;
	}
	if($("#enrollBeginTime").val().length==0){
		commUtil.alertError("活动报名开始时间不能为空");
		return;
	}
	if($("#enrollEndTime").val().length==0){
		commUtil.alertError("活动报名结束时间不能为空");
		return;
	}
	if($("#activityBeginTime").val().length==0){
		commUtil.alertError("活动开始时间不能为空");
		return;
	}
	if($("#activityEndTime").val().length==0){
		commUtil.alertError("活动结束时间不能为空");
		return;
	}
	if($("#description").val().length==0){
		commUtil.alertError("活动描述不能为空");
		return;
	}
	if($("#enrollBeginTime").val()>=$("#enrollEndTime").val()){
		commUtil.alertError("报名开始时间必须小于报名结束时间");
		return;
	}

	if($("#activityBeginTime").val()>=$("#activityEndTime").val()){
		commUtil.alertError("活动开始时间必须小于活动结束时间");
		return;
	}
	
	if($("#enrollEndTime").val()>=$("#activityEndTime").val()){
		commUtil.alertError("报名结束时间必须小于活动结束时间");
		return;
	}
	
	if($("#thisAppoint").val()==1){//指定商家
		if($("#mchtIdGroup").val().length==0){
			commUtil.alertError("请选择指定商家");
			return;
		}
	}
	
	$("#createSingleProductActivityForm").submit();
}
</script>
<html>
<body>
<form id="createSingleProductActivityForm" method="post" action="${pageContext.request.contextPath}/activityArea/addSingleProductActivityArea.shtml">
	<input type="hidden" id="id" name="id" value="${activityArea.id}"/>
	<table class="gridtable">
		<tr>
			<td class="title">活动名称：</td>
			<td align="left" class="l-table-edit-td"><input type="text" id="name" name="name" value="${activityArea.name}"/></td>
		</tr>
		<tr>
			<td class="title">活动类型：</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input type="hidden" id="type" name="type" value="${activityArea.type}" />
				<input type="radio" id="brand" name="brand" value="${activityArea.type}" onclick="typeOnclick(6);" <c:if test="${activityArea.type eq 6}">checked="checked"</c:if> />单品爆款&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="single" name="single" value="${activityArea.type}" onclick="typeOnclick(7);" <c:if test="${activityArea.type eq 7}">checked="checked"</c:if>/>新用户专享
			</td>
		</tr>
		<tr>
			<td class="title">活动报名图：</td>
			<td align="left" class="l-table-edit-td">
				<div><img id="areaPicImg" style="width: 200px;height: 200px" alt="" src="${pageContext.request.contextPath}/file_servelt${activityArea.pic}"></div>
    			<div style="float: left;margin: 10px;">
    			<input style="position:absolute; opacity:0;" type="file" id="areaPicFile" name="file" onchange="ajaxFileUploadPic();" />
    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
    			</div>
    			<input id="pic" name="pic" type="hidden" value="${activityArea.pic}">
			</td>
		</tr>
		<tr>
		
			<td class="title">活动报名时间：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="enrollBeginTime" name="enrollBeginTime" value="<fmt:formatDate value="${activityArea.enrollBeginTime}" pattern="yyyy-MM-dd HH:mm"/>"/>&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" id="enrollEndTime" name="enrollEndTime" value="<fmt:formatDate value="${activityArea.enrollEndTime}" pattern="yyyy-MM-dd HH:mm"/>"/>
			</td>
		</tr>
		<tr>
		
			<td class="title">活动时间：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="activityBeginTime" name="activityBeginTime" value="<fmt:formatDate value="${activityArea.activityBeginTime}" pattern="yyyy-MM-dd HH:mm"/>"/>
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" id="activityEndTime" name="activityEndTime" value="<fmt:formatDate value="${activityArea.activityEndTime}" pattern="yyyy-MM-dd HH:mm"/>"/>
			</td>
		</tr>
		<tr>
			<td class="title">活动描述：</td>
			<td align="left" class="l-table-edit-td">
				<textarea rows="5" cols="80" id="description" name="description">${activityArea.description}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title">限商家名额：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="limitMchtNum" name="limitMchtNum" value="${activityArea.limitMchtNum}" />
			</td>
		</tr>
		<c:if test="${not empty activityArea.id}">
		<tr>
			<td class="title">状态：</td>
			<td align="left" class="l-table-edit-td">
				<input type="hidden" id="status" name="status" value="${activityArea.status}">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="statusEnable" onclick="statusOnclick(1);" <c:if test="${activityArea.status eq 1}">checked="checked"</c:if>/>&nbsp;启用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="statusWeishiy" onclick="statusOnclick(0);" <c:if test="${activityArea.status eq 0}">checked="checked"</c:if>/>&nbsp;暂不启用
			</td>
		</tr>
		</c:if>
	</table>
	<c:if test="${activityArea.status!=1}">
	<br/>
	<table class="gridtable">
		<tr>
			<td style="border:none">
				<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(22, 155, 213, 1);border: none;" value="保存" onclick="createActivityArea();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(241, 153, 73, 1);border: none;" value="放弃" onclick="frameElement.dialog.close();"/>
			</td>
		</tr>
	</table>
	</c:if>
</form>
</body>
</html>
