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
	        	var couponId = $("#couponId").val();
	        	if(!/^\d+(,\d+)*$/.test(couponId)) {
					commUtil.alertError("请输入正确格式的优惠券ID。例：1,2,3");
					return;
				}
	        	if(submitFlag){
	        	   submitFlag = false;
	        	   formSubmit();
	        	}
	        }
	    }); 
		
	});
	
	
	function formSubmit() {
		$.ajax({
			url : "${pageContext.request.contextPath}/couPon/edItCouponCombinereclimit.shtml",
			type : 'POST',
			dataType : 'json',
			data : $("#form1").serialize(),
			success : function(data) {
				if ("0000" == data.returnCode) {
					parent.location.reload();
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				submitFlag = true;
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}

</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="" >
	   <input type="hidden" id="id"  name="id" value="${combineRecLimitCustom.id}"> 
		<table class="gridtable">
			<tr>
            	<td class="title" width="30%">优惠券组名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:300px;" type="text" id="name" name="name" value="${combineRecLimitCustom.name}" validate="{required:true}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="30%">关联优惠券ID<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:300px;" type="text" id="couponId" name="couponId" value="${combineRecLimitCustom.couponIdgroup}" validate="{required:true}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="30%">操作<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>