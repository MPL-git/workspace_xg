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
 	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.table-title{font-size: 14px;color: #333333;font-weight: 600;}
	.title-top{padding-top:10px;padding-bottom:10px;}
	.marR10{margin-right:10px;}
	.marT10{margin-top:10px;}
	.marB05{margin-bottom:5px;}
	.title-width{width: 20%}
	.marL20{margin-left:20px;}
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
    	margin-left: 20px;
    	color: #6B6B6B;
    }
</style>
<script type="text/javascript">
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
						content : lable.html(),width: 200
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
	        	var platformMsgStatus = $("[name='platformMsgStatus']:checked").val();
	        	var xiaonengPwd = $("#xiaonengPwd").val();
	        	if(platformMsgStatus == '1' && xiaonengPwd == '') {
	        		commUtil.alertError('发送站内信时，初始密码不能为空！');
	        		return;
	        	}
	        	form.submit();
	        }
	    });
		
		$("[name='flagStatus']").bind("click",function(){
			var flagStatus = $(this).val();
			if(flagStatus == '0') {
				window.location.href = "${pageContext.request.contextPath}/xiaonengCustomerService/openXiaoneng.shtml?mchtId=${mchtId }";
			}else {
				window.location.href = "${pageContext.request.contextPath}/xiaonengCustomerService/openXiaoneng.shtml?mchtId=${mchtId }&flagStatus="+flagStatus;

			}
		});
		
	});
	
	function selectMcht() {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 200,
			title : "选择客服序号",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/xiaonengCustomerService/xiaonengCustomerServiceManager.shtml?statusPage=2",
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	function childFun(id, busId, mchtName, code) {
		$("[name='id']").val(id);
		$("#busId").val(busId);
		$("#mchtName").val(mchtName);
		$("#code").val(code);
		$("#idSpan").html(code);
	}
	
	function updateStatus(val) {
		if(val == '0') {
			$("#xiaonengPwd").val("");
			$("#xiaonengPwd").attr("readonly", "readonly");
			$("[name='platformMsgStatus']:last").attr("checked", "checked");
			$("[name='platformMsgStatus']").attr("disabled", "disabled");
		}else if(val == '1') {
			$("#xiaonengPwd").val("");
			$("#xiaonengPwd").attr("readonly", "");
			$("[name='platformMsgStatus']:first").attr("checked", "checked");
			$("[name='platformMsgStatus']").attr("disabled", "");
		}
	}

</script>

</head>
	<body style="margin: 10px; margin-bottom: 200px;">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/xiaonengCustomerService/addOrUpdateXiaonengCustomerService.shtml" >
		<input type="hidden" name="mchtId" value="${mchtId }" >
		<input type="hidden" name="id" value="" >
		<div class="title-top">
			<div class="marT10" >
				<span class="marL20"><input type="radio" name="flagStatus" value="0" <c:if test="${flagStatus != '1' }">checked</c:if> >新增</span>
				<span class="marL20"><input type="radio" name="flagStatus" value="1" <c:if test="${flagStatus == '1' }">checked</c:if> >添加到旧有客服序号</span>
			</div>
			<table class="gridtable marT10 status-table" >
				<c:if test="${flagStatus == '1' }">
		           	<tr>
		               <td class="title title-width">
		               		<span>旧有客服序号</span>
		               </td>
		               <td>
		               		<span id="idSpan"></span>
		               		<input type="button" id="choice" style="background-color: rgba(255, 153, 0, 1);width: 50px;cursor:pointer;height: 24px;border-radius: 3px;" onclick="selectMcht();" value="选择">
		               </td>
					</tr>
				</c:if>
	           	<tr>
	               <td class="title title-width">
	               		<span>企业ID<span class="required">*</span></span>
	               </td>
	               <td>
	               		<input type="text" id="busId" name="busId" style="width: 30%;" <c:if test="${flagStatus == '1' }">readonly</c:if> validate="{required:true,	digits:true}" >
	               </td>
				</tr>
	           	<tr>
	               <td class="title title-width">
	               		<span>商家名称<span class="required">*</span></span>
	               </td>
	               <td>
	               		<input type="text" id="mchtName" name="mchtName" value="${mchtInfo.companyName }" style="width: 30%;" validate="{required:true}" >
	               </td>
				</tr>
	           	<tr>
	               <td class="title title-width">
	               		<span>客服代码<span class="required">*</span></span>
	               </td>
	               <td>
	               		<input type="text" id="code" name="code" style="width: 30%;" <c:if test="${flagStatus == '1' }">readonly</c:if> validate="{required:true}" >
	               </td>
				</tr>
	           	<tr>
	               <td class="title title-width">
	               		<span>状态<span class="required">*</span></span>
	               </td>
	               <td>
	               		<span class="marL20"><input type="radio" name="status" value="0" onclick="updateStatus(this.value);" >停用</span>
						<span class="marL20"><input type="radio" name="status" value="1" onclick="updateStatus(this.value);" checked="checked" >启用</span>
	               </td>
				</tr>
	           	<tr>
	               <td class="title title-width">
	               		<span>初始密码</span>
	               </td>
	               <td>
	               		<input type="text" id="xiaonengPwd" style="width: 30%;" name="xiaonengPwd" >
	               </td>
				</tr>
				<tr>
	               <td class="title title-width">
	               		<span>是否发送站内信</span>
	               </td>
	               <td>
	               		<span class="marL20"><input type="radio" name="platformMsgStatus" value="1" <c:if test="${flagStatus != '1' }">checked</c:if> >是</span>
						<span class="marL20"><input type="radio" name="platformMsgStatus" value="0" >否</span>
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
		</div>
	</form>
	</body>
</html>