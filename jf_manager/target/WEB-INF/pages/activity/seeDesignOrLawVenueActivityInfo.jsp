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
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}
.textarea{margin-left: 80px;}
</style>
<script type="text/javascript">
$(function(){
	statud();
});
function statud(){
	var designAuditStatus=$("#designAuditStatus").val();
	var lawAuditStatus=$("#lawAuditStatus").val();
	if(designAuditStatus==3||lawAuditStatus==3){
		$("#text").show();
	}else{
		$("#text").hide();
	}
}

//设计
function adoptDesignStatus(){
	$("#designAuditStatus").val("2");
	$("#text").hide();
	$("#rejectDesignStatus").attr('checked',false);
}

function rejectDesignStatus(){
	$("#designAuditStatus").val("3");
	$("#text").show();
	$("#adoptDesignStatus").attr('checked',false);
}
//法务
function adoptLawStatus(){
	$("#lawAuditStatus").val("2");
	$("#text").hide();
	$("#rejectLawStatus").attr('checked',false);
}

function rejectLawStatus(){
	$("#lawAuditStatus").val("3");
	$("#text").show();
	$("#adoptLawStatus").attr('checked',false);
}

//提交按钮
function submitButton(){
	var status="";
	var statusRemarks=$("#statusRemarks").val();
	var type="${type}";
	if(type==3){
		status=$("#designAuditStatus").val();
	}
	if(type==4){
		status=$("#lawAuditStatus").val();
	}
	
	if(status==null||status==""||status==undefined||status==1){
		commUtil.alertError("请选择状态");
		return;
	}
	
	if(status==3){
		if(statusRemarks==null||statusRemarks==""||statusRemarks==undefined){
			commUtil.alertError("请填写驳回原因");
			return;
		}
	}
	$.ajax({ //ajax提交
		type:'POST',
		url:'${pageContext.request.contextPath}' +"/activityArea/venueActivityAudit.shtml",
		data:{
				activityId:"${activityCustom.id}",
				statusRemarks:statusRemarks,
				status:status,
				type:type
		},
		dataType:"json",
		cache: false,
		success: function(json){
		   if(json==null || json.statusCode!=200){
		     commUtil.alertError(json.message);
		  }else{
             $.ligerDialog.success("操作成功",function() {
            	 	parent.$("#searchbtn").click();
            	 	frameElement.dialog.close();
				});
		  }
		},
		error: function(e){
		 commUtil.alertError("系统异常请稍后再试");
		}
		 });
}

/* 查看活动商品 */
function seeDesignOrLawActivityProductList(activityId) {
		
		$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width()-10,
		title: "官方会场活动商品审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allActivityProductList.shtml?activityId=" + activityId+"&type=${type}",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}


</script>
<style type="text/css">
.ss{
	width:100%;
	background-color: rgba(215, 215, 215, 1);
}
.samptime{font-size: 15px;}

</style>
<html>
<body>

	<table class="ss">
		<tr>
			<td align="center" width="200px;" style="border:none"><img src='${pageContext.request.contextPath}/file_servelt${activityCustom.activityAreaPic}' width='100px;' height='100px;' style="margin-top: 20px;margin-bottom: 20px;"/></td>
			<td style="border:none">
				<samp style="color: red;font-size: 20px;">${activityCustom.activityAreaTypeDesc}-${activityCustom.activityAreaName }</samp>
				<br>
				<br>
				<samp class="samptime">报名时间：<fmt:formatDate value="${activityCustom.enrollBeginTime}" pattern="yyyy-MM-dd hh:mm:ss"/>&nbsp;-&nbsp;<fmt:formatDate value="${activityCustom.enrollEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></samp>
				<br>
				<samp class="samptime">活动时间：<fmt:formatDate value="${activityCustom.activityBeginTime}" pattern="yyyy-MM-dd hh:mm:ss"/>&nbsp;-&nbsp;<fmt:formatDate value="${activityCustom.activityEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></samp>
			</td>
		</tr>
	</table>
	<br/>
	<table class="gridtable">
		<tr>
			<td style="border:none">商家名称：&nbsp;&nbsp;${activityCustom.shortName}</td>
		</tr>
		<tr>
			<td style="border:none">商家序号：&nbsp;&nbsp;${activityCustom.mchtCode}</td>
		</tr>
		<c:if test="${activityCustom.type==1}">
		<tr>
			<td style="border:none">入口图：</td>
		</tr>
		<tr>
			<td style="border:none">
				<div style="margin-left: 80px;"><img id="logoPic" alt="" src="${pageContext.request.contextPath}/file_servelt${activityCustom.entryPic}"></div>
			</td>
		</tr>
		<tr>
			<td style="border:none">头部海报：</td>
		</tr>
		<tr>
			<td style="border:none">
				<div style="margin-left: 80px;"><img id="logoPic" alt="" src="${pageContext.request.contextPath}/file_servelt${activityCustom.posterPic}"></div>
			</td>
		</tr>
		</c:if>
	</table>
	<table class="gridtable">
		<tr>
			<td style="border:none">
				是否通过：&nbsp;&nbsp;&nbsp;
				<c:if test="${type eq 3 }">
					<input type="hidden" id="designAuditStatus" name="designAuditStatus" value="${activityCustom.designAuditStatus}"/>
					<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="adoptDesignStatus" name="adoptDesignStatus" onclick="adoptDesignStatus();" value="${activityCustom.designAuditStatus}" <c:if test="${activityCustom.designAuditStatus==2}">checked="checked"</c:if> >通过审核</span>&nbsp;&nbsp;&nbsp;
					<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="rejectDesignStatus" name="rejectDesignStatus" onclick="rejectDesignStatus();" value="${activityCustom.designAuditStatus}" <c:if test="${activityCustom.designAuditStatus==3}">checked="checked"</c:if> >已驳回</span>
				</c:if>
				<c:if test="${type eq 4 }">
					<input type="hidden" id="lawAuditStatus" name="lawAuditStatus" value="${activityCustom.lawAuditStatus}"/>
					<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="adoptLawStatus" name="adoptLawStatus" onclick="adoptLawStatus();" value="${activityCustom.lawAuditStatus}" <c:if test="${activityCustom.lawAuditStatus==2}">checked="checked"</c:if> >通过审核</span>&nbsp;&nbsp;&nbsp;
					<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="rejectLawStatus" name="rejectLawStatus" onclick="rejectLawStatus();" value="${activityCustom.lawAuditStatus}" <c:if test="${activityCustom.lawAuditStatus==3}">checked="checked"</c:if> >已驳回</span>
				</c:if>
			</td>
		</tr>
		<tr id="text">
			<td style="border:none">
				<textarea id="statusRemarks" class="textarea" rows="3" cols="80"><c:if test="${type eq 3 }">${activityCustom.designAuditRemarks}</c:if><c:if test="${type eq 4 }">${activityCustom.lawAuditRemarks}</c:if></textarea>
			</td>
		</tr>
	</table>	
	<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
	<br/>
	<table class="gridtable">
		<tr>
			<td style="border:none">
				<c:if test="${type eq 3 && myself eq 1}">
					<c:if test="${activityCustom.designAuditStatus eq 1}">
						<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" onclick="submitButton();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
				</c:if>
				<c:if test="${type eq 4 && myself eq 1}">
					<c:if test="${activityCustom.lawAuditStatus eq 1}">
						<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" onclick="submitButton();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
				</c:if>
				<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(255, 255, 255, 1);" value="查看活动商品" onclick="seeDesignOrLawActivityProductList(${activityCustom.id});" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(255, 255, 255, 1);" value="预览活动商品" />
			</td>
		</tr>
	</table>
</body>
</html>
