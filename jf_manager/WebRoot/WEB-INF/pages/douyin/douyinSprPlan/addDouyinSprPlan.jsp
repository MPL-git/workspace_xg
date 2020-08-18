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
	function funSubmit() {
		var sprPlanSite = $("#sprPlanSite").val();
		var convertId = $("#convertId").val();
		var convertId2 = $("#convertId2").val();
		var trackingId = $("#trackingId").val();
		var sprPlanType = $("#sprPlanType").val();
		var linkValue = $("#linkValue").val();
		if(sprPlanSite.length > 50 || sprPlanSite.length == 0) {
			commUtil.alertError("投放广告位置只能1-50个字符~");
			return;
		}
		if(convertId.length == 0) {
			commUtil.alertError("抖音转化ID(提交订单)不能为空");
			return;
		}
		if(convertId2.length == 0) {
			commUtil.alertError("抖音转化ID(支付成功)不能为空");
			return;
		}
		if(trackingId.length == 0) {
			commUtil.alertError("抖音跟踪ID不能为空");
			return;
		}
		if(sprPlanType == '7' || sprPlanType == '8' || sprPlanType == '9' ) {
			if(linkValue == '') {
				commUtil.alertError("页面ID不能为空");
				return;
			}
			$.ajax({
				type: 'POST',
				url: '${pageContext.request.contextPath}/douyinSprPlan/validateLinkValue.shtml',
				data: {sprPlanType : sprPlanType, linkValue : linkValue},
				dataType: 'json',
				sync: false,
				success: function(data){
					if(data == null || data.code != 200 ) {
				     	commUtil.alertError(data.msg);
				     	return;
				  	}else{
					 	if(data.linkId == '' ) {
					 		commUtil.alertError('ID不存在');
					 	}else {
					 		$("#linkId").val(data.linkId);
					 		if(submitFlag){
					    		submitFlag = false;
					    		$("#form1").submit();
					    	}
					 	}
				  	}
				},
				error: function(e){
				 	commUtil.alertError('系统异常请稍后再试');
				}
			});
		}else {
			if(submitFlag){
	    		submitFlag = false;
	    		$("#form1").submit();
	    	}
		}
	}
	
	function linkValueFun(sprPlanType) {
		if(sprPlanType == '7' || sprPlanType == '8' || sprPlanType == '9' ) {
			$("#linkValue-tr").show();
		}else {
			$("#linkValue").val("");
			$("#linkValue-tr").hide();
		}
	}
	
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/douyinSprPlan/addDouyinSprPlan.shtml" >
		<input type="hidden" id="linkId" name="linkId" value="" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">渠道<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="sprChnlId" name="sprChnlId" style="width: 164px;" >
						<c:forEach var="douyinSprChnl" items="${douyinSprChnlList }">
							<option value="${douyinSprChnl.id }">${douyinSprChnl.sprChnlName }</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">投放广告位置<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="sprPlanSite" name="sprPlanSite" value="" />
					</br><span class="activity-hint" >注意：投放广告位置只能1-50个字符</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">抖音转化ID(提交订单)<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="convertId" name="convertId" value="" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">抖音转化ID(支付成功)<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="convertId2" name="convertId2" value="" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">抖音跟踪ID<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="trackingId" name="trackingId" value="" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">页面类型<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="sprPlanType" name="sprPlanType" style="width: 164px;" onchange="linkValueFun(this.value);" >
						<c:forEach var="sprPlanType" items="${sprPlanTypeList }">
							<option value="${sprPlanType.statusValue }">${sprPlanType.statusDesc }</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr id="linkValue-tr" style="display: none;" >
            	<td class="title" width="20%">页面ID<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="linkValue" name="linkValue" value="" />
					</br>
					<div>
						<p style="color:red;">温馨提示：</p>
						<span class="activity-hint" >商品详情页：页面ID填写商品ID（长ID）
							</br>特卖详情页：页面ID填写特卖ID
							</br>会场详情页：页面ID填写会场ID
						</span>
					</div>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" class="l-button l-button-submit" onclick="funSubmit();" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>