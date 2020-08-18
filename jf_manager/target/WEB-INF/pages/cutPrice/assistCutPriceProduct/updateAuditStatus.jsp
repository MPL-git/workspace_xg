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
	        	var auditStatus = $("[name='auditStatus']:checked").val();
	        	if(auditStatus == undefined) {
	        		commUtil.alertError("请选择审核结果！");
	        		return;
	        	}
	        	var remarksLog = $("#remarksLog").val();
	        	if(auditStatus == '4' && remarksLog == '') {
	        		commUtil.alertError("审核驳回时，原因说明不能为空！");
	        		return;
	        	}
	        	if(auditStatus == '3') {
		        	var activeTime = $("[name='activeTime']").val();
		        	if(!/^([1-9]\d*)$/.test(activeTime)) {
		        		commUtil.alertError("活动有效时间，请输入正整数！");
		        		return;
		        	}
		        	if(new Number(activeTime) < 24) {
		        		commUtil.alertError("活动有效时间，不可小于24小时！");
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
	
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/assistCutPriceProduct/updateAuditStatus.shtml" >
		<input type="hidden" id="singleProductActivityId" name="singleProductActivityId" value="${singleProductActivityCustom.id }" />
		<input type="hidden" id="productId" name="productId" value="${singleProductActivityCustom.productId }" />
		<input type="hidden" id="activityPrice" name="activityPrice" value="${singleProductActivityCustom.activityPrice }" />
		<input type="hidden" id="fixedAmount" name="fixedAmount" value="${singleProductActivityCustom.fixedAmount }" />
		<input type="hidden" id="maxInviteTimes" name="maxInviteTimes" value="${singleProductActivityCustom.maxInviteTimes }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">商家优势说明</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="5" cols="30" id="remarks" name="remarks" >${singleProductActivityCustom.remarks } </textarea>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">活动类型<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<span style="margin-left: 10px;">
						<label>
							<input type="radio" name="type" value="10" checked="checked" />助力减价
						</label>
					</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">助力减价设置</td>
				<td align="left" class="l-table-edit-td" >
					<span>邀请一人减</span>
					<input type="text" style="width: 80px;margin-right: 5px;" disabled="disabled" value="${singleProductActivityCustom.fixedAmount }" />元
					<span style="margin-left: 10px;">最多可邀请</span>
					<input type="text" style="width: 50px;margin-right: 5px;" disabled="disabled" value="${singleProductActivityCustom.maxInviteTimes }" />人
					<span style="margin-left: 5px;">最低成交价<span id="amount" style="color: red;">${singleProductActivityCustom.activityPrice - (singleProductActivityCustom.fixedAmount * singleProductActivityCustom.maxInviteTimes) }</span>元</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">活动有效时间</td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" style="width: 80px;" name="activeTime" value='<c:if test="${empty singleProductActivityCustom.activeTime }">24</c:if><c:if test="${not empty singleProductActivityCustom.activeTime }">${singleProductActivityCustom.activeTime }</c:if>' />小时（发起后多少时间内失效，失效后不可再发起邀请）
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">审核结果<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<span style="margin-left: 10px;">
						<label>
							<input type="radio" name="auditStatus" value="3" <c:if test="${singleProductActivityCustom.auditStatus == '3' }">checked</c:if> />审核通过
						</label>
					</span>
					<span style="margin-left: 20px;">
						<label>
							<input type="radio" name="auditStatus" value="4" <c:if test="${singleProductActivityCustom.auditStatus == '4' }">checked</c:if> />审核驳回
						</label>
					</span>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">原因说明</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="5" cols="30" id="remarksLog" name="remarksLog"  >${singleProductActivityCustom.remarksLog }</textarea>
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