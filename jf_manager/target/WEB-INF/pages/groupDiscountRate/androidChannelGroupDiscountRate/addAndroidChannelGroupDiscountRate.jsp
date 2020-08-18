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
			format: "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
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
	        	var groupNameId = $("#groupNameId").val();
	        	if(groupNameId == '' ) {
					commUtil.alertError("渠道集合名称不能为空！");
					return;
				}
	        	var discountRateDateBegin = $("#discountRateDateBegin").val();
	        	var discountRateDateEnd = $("#discountRateDateEnd").val();
				if(discountRateDateBegin == '' || discountRateDateEnd == '' ) {
					commUtil.alertError("创建日期不能为空！");
					return;
				}
				var date = new Date();
				discountRateDateBegin = new Date(discountRateDateBegin);
				discountRateDateEnd = new Date(discountRateDateEnd);
				if(discountRateDateEnd > new Date(date.format("yyyy-MM-dd hh:mm:ss")) ) {
					commUtil.alertError("创建结束日期不能大于当前日期！");
					return;
				}
				discountRateDateEnd = discountRateDateEnd.setDate(discountRateDateEnd.getDate()-10);
				if(discountRateDateBegin < discountRateDateEnd ) {
					commUtil.alertError("创建日期跨度不能超过10天！");
					return;
				}
	        	var discountRate = $("#discountRate").val();
				if(discountRate == '' ) {
					commUtil.alertError("优惠率不能为空！");
					return;
				}
				var flag = discountRate.match(/^(([1-9]{1}\d{0,6})|(0{1}))(\.\d{1,4})?$/);
				if(Number(discountRate) == Number(0) ) {
					commUtil.alertError("优惠率，请输入正数！");
					return;
				}else if(flag == null && discountRate.match(/^(([1-9]{1}\d*)|(0{1}))(\.\d+)?$/) != null ) {
					if(Number(999999.9999) < Number(discountRate) ) {
						commUtil.alertError("优惠率数值过大！");
						return;
					}else {
						commUtil.alertError("优惠率最多四位小数！");
						return;
					}
				}else if(flag == null ) {
					commUtil.alertError("优惠率，请输入正数！");
					return;
				}
	        	if(submitFlag){
	        		submitFlag = false;
	        		form.submit();
	        	}
	        }
	    }); 
		
	});

	function updateDiscountRate() {
		var rate = $("#groupNameId option:selected").attr("rate");
		$("#discountRate").val(rate);
	}

</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/androidChannelGroupDiscountRate/addAndroidChannelGroupDiscountRate.shtml" >
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">渠道集合名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="groupNameId" name="groupNameId" style="width: 160px;" onchange="updateDiscountRate();" >
						<option value="">请选择...</option>
						<c:forEach var="androidChannelGroup" items="${androidChannelGroupList }">
							<option value="${androidChannelGroup.id }" rate="${androidChannelGroup.discountRate}" >${androidChannelGroup.groupName }</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%"> 创建日期<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<div  style="position: relative;">
						<div style="display: inline-block;position: absolute;">
							<input type="text" class="dateEditor" id="discountRateDateBegin" name="discountRateDateBegin" value="">
						</div>
						<span style="margin-left: 160px;position: absolute;">至</span>
						<div style="margin-left: 200px;display: inline-block;margin-bottom: 10px;">
							<input type="text" class="dateEditor" id="discountRateDateEnd" name="discountRateDateEnd" value="">
						</div>
					</div>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">优惠率<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="discountRate" name="discountRate" value="" />
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