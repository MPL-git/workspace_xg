<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	        	var id = $("#id").val();
	        	var beginDate = $("#beginDate").val();
	        	var endDate = $("#endDate").val();
	        	var integralMin = $("#integralMin").val();
	        	var integralMax = $("#integralMax").val();
	        	if(beginDate == '' || endDate == '') {
	        		commUtil.alertError("活动时间不能为空！");
					return;
	        	}
	        	if(Number(new Date(beginDate).getTime()) > Number(new Date(endDate).getTime())) {
	        		commUtil.alertError("结束时间不能小于开始时间！");
					return;
	        	}
	        	if(!validateDate(beginDate, endDate, id)) {
	        		commUtil.alertError("此刮奖活动时间重叠！");
					return;
	        	}
	        	if(integralMin == '' || integralMax == '') {
	        		commUtil.alertError("积分范围不能为空！");
					return;
	        	}
	        	if(!/[0-9]+/.test(integralMin) || !/[0-9]+/.test(integralMax)) {
	        		commUtil.alertError("积分范围，请输入整数！");
					return;
	        	}
	        	if(Number(integralMin) < 8 || Number(integralMax) > 8888) {
	        		commUtil.alertError("积分范围只能在8-8888（含8&8888）！");
					return;
	        	}
	        	if(submitFlag){
	        		submitFlag = false;
	        		form.submit();
	        	}
	        }
	    }); 
		
	});
	
	//验证时间不能重叠
	function validateDate(beginDate, endDate, id) {
		var flag = true;
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/commentDrawSetting/validateDate.shtml",
			 data : {beginDate : beginDate, endDate : endDate, id : id},
			 dataType : 'json',
			 async : false,
			 success : function(data){
				 if(data == null || data.code != 200) {
					 commUtil.alertError(data.msg);
				 }else {
					 if(data.status == false) {
						 flag = false;
					 }
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试！");
			 }
		});
		return flag;
	}
	

</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/commentDrawSetting/addOrUpdateCommentDrawSetting.shtml" >
		<input type="hidden" id="id" name="id" value="${commentDrawSetting.id }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">
            		<span style="color:red;">*</span>活动时间
            	</td>
				<td align="left" class="l-table-edit-td" >
					<div  style="position: relative;">
						<div style="display: inline-block;position: absolute;">
							<input type="text" class="dateEditor" id="beginDate" name="beginDate" value="<fmt:formatDate value='${commentDrawSetting.beginDate }' pattern='yyyy-MM-dd HH:mm:ss' />" >
						</div>
						<span style="margin-left: 170px;position: absolute;">到</span>
						<div style="margin-left: 200px;display: inline-block;margin-bottom: 10px;">
							<input type="text" class="dateEditor" id="endDate" name="endDate" value="<fmt:formatDate value='${commentDrawSetting.endDate }' pattern='yyyy-MM-dd HH:mm:ss' />" >
						</div>
					</div>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">
            		<span style="color:red;">*</span>积分范围
            	</td>
				<td align="left" class="l-table-edit-td" >
					<div  style="position: relative;">
						<div style="display: inline-block;position: absolute;">
							<input type="text" class="" id="integralMin" name="integralMin" value="${commentDrawSetting.integralMin }" >
						</div>
						<span style="margin-left: 170px;position: absolute;">到</span>
						<div style="margin-left: 200px;display: inline-block;margin-bottom: 10px;">
							<input type="text" class="" id="integralMax" name="integralMax" value="${commentDrawSetting.integralMax }" >
						</div>
					</div>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">
            		<span style="color:red;">*</span>状态
            	</td>
            	<c:if test="${not empty commentDrawSetting.status }">
            		<td align="left" class="l-table-edit-td" >
						<span style="margin-left: 10px;margin-right: 10px;">
							<label><input type="radio" class="" name="status" value="1" <c:if test="${commentDrawSetting.status == '1' }">checked</c:if> >上架</label>
						</span>
						<span style="margin-left: 10px;margin-right: 10px;">
							<label><input type="radio" class="" name="status" value="0" <c:if test="${commentDrawSetting.status == '0' }">checked</c:if> >下架</label>
						</span>
					</td>
            	</c:if>
            	<c:if test="${empty commentDrawSetting.status }">
            		<td align="left" class="l-table-edit-td" >
						<span style="margin-left: 10px;margin-right: 10px;">
							<label><input type="radio" class="" name="status" value="1" checked>上架</label>
						</span>
						<span style="margin-left: 10px;margin-right: 10px;">
							<label><input type="radio" class="" name="status" value="0">下架</label>
						</span>
					</td>
            	</c:if>
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