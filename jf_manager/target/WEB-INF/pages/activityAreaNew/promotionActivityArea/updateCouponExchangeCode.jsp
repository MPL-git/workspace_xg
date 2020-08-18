<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
	 <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<style type="text/css">
 	.radioClass{margin: 0 10px 0 10px;}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
</style>
<script type="text/javascript">
	var subFlag = true;
	$(function(){
// 		$(".dateEditor").ligerDateEditor({
// 			showTime : true,
// 			format: "yyyy-MM-dd hh:mm:ss",
// 			labelAlign : 'left',
// 			width : 160
// 		});
		
		$.metadata.setType("attr", "validate");
		$("#form1").validate({
	        errorPlacement: function (lable, element) {  
	        	var elementType=$(element).attr("type");
	        	if('radio'==elementType) {
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").parent("label").parent("span").ligerTip({
						content : lable.html(),width: 100,target: $("[name='exchangeLimit']")
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
	            if(lable.attr("for") == 'exchangeLimit') {
		            $("#"+$("[name='exchangeLimit']").attr("ligertipid")).remove();
	            }
	        },
	        submitHandler: function(form) {  
	        	var createNum = $("#createNum").val();
	        	if(!/(^[1-9]$)|(^[1-9]\d*$)/.test(createNum)) {
	        		commUtil.alertError("请输入正确的整数格式！");
        			return;
	        	}
	        	if(Number(createNum) > Number('${notNum }')) {
	        		commUtil.alertError("生成数量必须小于等于未生成数！");
        			return;
	        	}
	        	if(subFlag) {
	        		subFlag = false;
		        	form.submit();
	        	}
	        }
	    }); 
		
		
		
	});
	
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" id="form1" class="form1" method="post" action="${pageContext.request.contextPath}/couponExchangeCode/updateAddCouponExchangeCode.shtml">
		<input type="hidden" id="couponId" name="couponId" value="${couponId }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">未生成数</td>
				<td align="left" class="l-table-edit-td" >${notNum }</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">生成数量<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="createNum" name="createNum" style="width: 80px;" value="" validate="{required:true}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">渠道<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="channel" name="channel" style="width: 135px;" validate="{required:true}" >
						<option value="">请选择...</option>
						<c:forEach var="channel" items="${channelList }">
							<option value="${channel.statusValue }">
								${channel.statusDesc }
							</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">兑换条件<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<c:forEach items="${exchangeLimitList }" var="exchangeLimit" >
						<span style="margin-left: 15px;">
							<label><input type="radio" name="exchangeLimit" value="${exchangeLimit.statusValue }" validate="{required:true}" >${exchangeLimit.statusDesc }</label>
						</span>
					</c:forEach>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">备注<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="remarks" name="remarks" style="width:260px;" value="" validate="{required:true,maxlength:20}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button" value="提交" /> 
					<input style="margin-left: 20px;" id="button-close" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>