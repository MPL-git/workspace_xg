<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
.l-verify-tip-corner{left:20px!important;}
.l-verify-tip-content{left:27px!important;}
</style>

<script type="text/javascript">
var submitFlag = true;
var endUnpay = '${endUnpay }';
$(function ()  {
 	
	$(".dateEditor").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	
	$.metadata.setType("attr", "validate");
	var v = $("#form1").validate({
		errorPlacement: function (lable, element) {   
        	var elementType = $(element).attr("type");
        	if($(element).hasClass("l-text-field")) {
        		$(element).parent().ligerTip({
					content : lable.html(),width: 100
				});
        	}else if('radio'==elementType) {
        		var radioName=$(element).attr("name");
        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
					content : lable.html(),width: 100
				});
        	}else {
        		$(element).ligerTip({
					content : lable.html(),width: 100
				});
        	}
		},
		success: function (lable,element) {
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler: function (form) {   
			var isValidateOk = true;
			
			var discount = $("#discount").val();
			if(!/^([\+ \-]?([1-9]\d*)|(0))([.]\d{0,2})?$/.test(discount)) {
				commUtil.alertError("请输入正负数，最多两位小数！");
				isValidateOk = false;
			}else {
				$("#endUnpay").val((new Number(discount) + new Number(endUnpay)).toFixed(2));				
			}
	    	if(isValidateOk && submitFlag){
	    		submitFlag = false;
	    		form.submit();
	    	}else{
				$("html,body").animate({scrollTop:$("body").offset().top},0);
	    	}
		}
	});
	
});

function updateEndUnpay(discount) {
	if(!/^([\+ \-]?([1-9]\d*)|(0))([.]\d{0,2})?$/.test($("#discount").val())) {
		commUtil.alertError("请输入正负数，最多两位小数！");
	}else {
		$("#span-endUnpay").html((new Number(discount) + new Number(endUnpay)).toFixed(2));
	}
}

</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/mchtMonthTrade/updateDiscount.shtml">
		<input id="mchtMonthTradeId" name="mchtMonthTradeId" type="hidden" value="${mchtMonthTradeId }"/>
		<input id="endUnpay" name="endUnpay" type="hidden" value="${endUnpay }"/>
		<input id="discountOld" name="discountOld" type="hidden" value="${discount }"/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">折扣调差：<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="discount" validate="{required:true}" name="discount" type="text" style="width:120px;" value="" onchange="updateEndUnpay(this.value);" />
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">调后结欠：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<span id="span-endUnpay">${endUnpay }</span>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
