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
	        	var advertiserId = $("#advertiserId").val();
	        	if(!/^[0-9]*$/.test(advertiserId)) {
	        		commUtil.alertError("请输入正确格式的广告主ID！");
	        		return;
	        	}
	        	$.ajax({
		   			 type: 'post',
		   			 url: '${pageContext.request.contextPath }/toutiaoAdvertiser/validateAdvertiserId.shtml',
		   			 data: {advertiserId : advertiserId},
		   			 dataType: 'json',
		   			 success: function(data) {
		   				 if(data == null || data.statusCode != 200){
		   				     commUtil.alertError(data.message);
		   				 }else if(data.message >0){
		   					commUtil.alertError("广告主ID已存在！");
		   				 }else {
		   					if(submitFlag){
		   		        		submitFlag = false;
		   		        		form.submit();
		   		        	}
		   				 }
		   			 },
		   			 error: function(e) {
		   				 $.ligerDialog.closeWaitting();
		   				 commUtil.alertError("系统异常请稍后再试");
		   			 }
		   		});
	        }
	    }); 
		
	});

</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/toutiaoAdvertiser/saveToutiaoAdvertiser.shtml" >
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">广告主体<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select style="width: 135px;" id="tokenId" name="tokenId" validate="{required:true}" >
						<c:forEach items="${toutiaoTokenInfoList }" var="toutiaoTokenInfo">
							<option value="${toutiaoTokenInfo.id}" >${toutiaoTokenInfo.mainName}</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">广告主ID<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width: 230px;" type="text" id="advertiserId" name="advertiserId" value="" validate="{required:true,}" />
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