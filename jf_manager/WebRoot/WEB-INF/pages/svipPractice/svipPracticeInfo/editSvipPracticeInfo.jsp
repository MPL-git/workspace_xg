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
				var startTimeStr = $("#startTimeStr").val();
				var endTimeStr = $("#endTimeStr").val();
				if(startTimeStr == '' || endTimeStr == '' ) {
					commUtil.alertError("活动时间不能为空！");
					return;
				}
				if(new Date(startTimeStr) > new Date(endTimeStr) ) {
					commUtil.alertError("活动开始时间必须小于活动结束时间！");
					return;
				}
				if(new Date() >= new Date(endTimeStr) ) {
					commUtil.alertError("活动结束时间不能小于当前时间！");
					return;
				}
				var practiceTimeType = $("input[name='practiceTimeType']:checked").val();
				if(practiceTimeType == '1'){
					var practiceStartTimeStr = $("#practiceStartTimeStr").val();
					var practiceEndTimeStr = $("#practiceEndTimeStr").val();
					if(practiceStartTimeStr == '' || practiceEndTimeStr == '' ) {
						commUtil.alertError("体验时间不能为空！");
						return;
					}
					if(new Date(practiceStartTimeStr) <= new Date(startTimeStr) ) {
						commUtil.alertError("体验开始时间不能小于活动开始时间！");
						return;
					}
					if(new Date(practiceStartTimeStr) > new Date(practiceEndTimeStr) ) {
						commUtil.alertError("体验开始时间必须小于体验结束时间！");
						return;
					}
					var practiceEndTimeStr = new Date(practiceEndTimeStr);
					practiceEndTimeStr.setDate(practiceEndTimeStr.getDate()-30);
					if(new Date(practiceStartTimeStr) < practiceEndTimeStr) {
						commUtil.alertError("体验时间不能超过30天！");
						return;
					}
				}else {
					var practiceHours = $("#practiceHours").val();
					if(!/^[1-9]\d*$/.test(practiceHours)){
						commUtil.alertError("选择相对时间必填体验时间，必须大于0的正整数！");
						return;
					}
					if(new Number(practiceHours) > new Number(30*24)) {
						commUtil.alertError("体验时间不能超过30天！");
						return;
					}
				}
	        	if(submitFlag){
	        		submitFlag = false;
	        		form.submit();
	        	}
	        }
	    }); 

		$("input[name='practiceTimeType']").click(function(){
			if($("input[name='practiceTimeType']:checked").val() == '1' ) {
				$(".practice-time-td").show();
				$(".practice-hours-td").hide();
			}else {
				$(".practice-time-td").hide();
				$(".practice-hours-td").show();
			}
		});

	});

</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/svipPracticeInfo/editSvipPracticeInfo.shtml" >
		<input type="hidden" id="id" name="id" value="${svipPracticeInfo.id }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">主题名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="title" name="title" value="${svipPracticeInfo.title }" validate="{required:true,maxlength:20}" />
					</br><span class="activity-hint" >注意：标签名称不能超过20个字符</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">活动时间<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td table-edit-activity-time">
					<div><input type="text" class="dateEditor" id="startTimeStr" name="startTimeStr" value="<fmt:formatDate value="${svipPracticeInfo.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
					<span>至</span>
					<div><input type="text" class="dateEditor" id="endTimeStr" name="endTimeStr" value="<fmt:formatDate value="${svipPracticeInfo.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
					<span class="activity-hint" >注意：活动开始时间必须小于活动结束时间，活动结束时间不能小于当前时间</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">体验时间类型<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<label>
						<input type="radio" name="practiceTimeType" value="1" <c:if test="${empty svipPracticeInfo.practiceTimeType or svipPracticeInfo.practiceTimeType == '1'}">checked="checked"</c:if> >绝对时间
					</label>
					<label style="margin-left:20px;">
						<input type="radio" name="practiceTimeType" value="2" <c:if test="${svipPracticeInfo.practiceTimeType == '2'}">checked="checked"</c:if> >相对时间
					</label>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">体验时间<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td table-edit-activity-time practice-time-td" <c:if test="${svipPracticeInfo.practiceTimeType == '2'}">style="display: none;"</c:if> >
					<div><input type="text" class="dateEditor" id="practiceStartTimeStr" name="practiceStartTimeStr" value="<fmt:formatDate value="${svipPracticeInfo.practiceStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
					<span>至</span>
					<div><input type="text" class="dateEditor" id="practiceEndTimeStr" name="practiceEndTimeStr" value="<fmt:formatDate value="${svipPracticeInfo.practiceEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
					<span class="activity-hint" >注意：体验开始时间必须小于体验结束时间，体验开始时间不能小于活动开始时间</span>
				</td>
				<td align="left" class="l-table-edit-td practice-hours-td" <c:if test="${empty svipPracticeInfo.practiceTimeType or svipPracticeInfo.practiceTimeType == '1'}">style="display: none;"</c:if> >
					<input style="width:100px;" type="text" id="practiceHours" name="practiceHours" value="${svipPracticeInfo.practiceHours }" />小时
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