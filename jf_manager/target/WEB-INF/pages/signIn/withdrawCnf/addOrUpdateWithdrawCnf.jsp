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
</style>
<script type="text/javascript">
	var submitFlag = true;
	$(function(){
		if('${withdrawCnf.withdrawType }' == '2') {
			$("#form1").hide();
		}else {
			$("#form2").hide();
		}
		$("[name='withdrawType']").bind('click', function() {
			var withdrawType = $(this).val();
			if(withdrawType == '1') {
				$("#form1").show();
				$("#form2").hide();
				$("#form1 [name='withdrawType']:first").attr("checked", "checked");
			}else if(withdrawType == '2') {
				$("#form1").hide();
				$("#form2").show();
				$("#form2 [name='withdrawType']:last").attr("checked", "checked");
			}
		});
		
	});
		
	function subForm1() {
		if(submitFlag) {
			var name = $("#nameOne").val();
			if(name == '') {
				commUtil.alertError("提现名称不能为空！");
				return;
			}
        	var couponId = $("#couponId").val();
    		var couponIdFlag = $("#couponIdFlag").val();
    		if(!(/^[1-9]\d*$/).test(couponId)) {
    			commUtil.alertError("请输入一个正整数！");
    			return;
    		}
    		if(couponId != couponIdFlag) {
    			commUtil.alertError("请先检测优惠券ID！");
    			return;
    		}
    		submitFlag = false;
        	$("#form1").submit();
    	}
	}
	function subForm2() {
		if(submitFlag) {
			var name = $("#nameTwo").val();
			if(name == '') {
				commUtil.alertError("提现名称不能为空！");
				return;
			}
			var amount = $("#amountTwo").val();
    		if(!(/^[1-9]\d*$/).test(amount)) {
    			commUtil.alertError("请输入一个正整数！");
    			return;
    		}
    		submitFlag = false;
    		$("#form2").submit();
    	}
	}
	
	function choiceCouponId() {
		var couponId = $("#couponId").val();
		var couponIdFlag = $("#couponIdFlag").val();
		if(!(/^[1-9]\d*$/).test(couponId)) {
			commUtil.alertError("请输入一个正整数！");
			return;
		}
		if(couponId != couponIdFlag) {
			$.ajax({
				 type: 'post',
				 url: '${pageContext.request.contextPath }/withdrawCnf/choiceCouponId.shtml',
				 data: {couponId : couponId},
				 dataType: 'json',
				 success: function(data) {
					 if(data == null || data.code != 200){
					     commUtil.alertError(data.msg);
					 }else if(data.coupon == null){
						 commUtil.alertError("此ID优惠券不存在！");
					 }else{
						 $("#couponIdFlag").val(couponId);
						 $("#amount").val(data.coupon.money);
						 $("#couponName").val(data.coupon.name);
						 if(data.coupon.money != null) {
							 if(data.coupon.minimum == null) {
								 $("#couponMoney").val(data.coupon.money+"元");
							 }else {
								 $("#couponMoney").val(data.coupon.money+"元 / 满"+data.coupon.minimum+"元");
							 }
						 }
						 $("#couponExpiryDate").val(data.expiryDate);
					 }
				 },
				 error: function(e) {
					 commUtil.alertError("系统异常请稍后再试！");
				 }
			 });
		}
	}
	
</script>

</head>
	<body style="margin: 10px;">
	<form id="form1" class="form1" method="post" action="${pageContext.request.contextPath}/withdrawCnf/addOrUpdateWithdrawCnf.shtml" >
		<input type="hidden" id="couponIdFlag" value="${withdrawCnf.couponId }" />
		<input type="hidden" id="amount" name="amount" value="${withdrawCnf.amount }" />
		<input type="hidden" id="withdrawCnfId" name="withdrawCnfId" value="${withdrawCnf.id }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="25%">奖励类型<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<span style="margin-left: 10px;">
						<label><input type="radio" name="withdrawType" value="1" checked="checked" />抵用券</label>
					</span>
					<span style="margin-left: 30px;">
						<label><input type="radio" name="withdrawType" value="2" />微信红包</label>
					</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="25%">提现名称：<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="nameOne" name="name" value="${withdrawCnf.name }" validate="{required:true}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="25%">优惠券ID：<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="couponId" name="couponId" value="${withdrawCnf.couponId }" />
					<input type="button" id="choice" style="background-color: rgba(255, 153, 0, 1);width: 50px;cursor:pointer;height: 20px;border-radius: 3px;" onclick="choiceCouponId();" value="检测">
				</td>
           	</tr>
			<tr>
            	<td class="title" width="25%">优惠券名称：</td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="couponName" name="couponName" value="${coupon.name }" readonly="readonly" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="25%">面额/使用条件：</td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="couponMoney" name="couponMoney" value="${couponMoney }" readonly="readonly" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="25%">使用时间：</td>
				<td align="left" class="l-table-edit-td" >
					<textarea cols="30" rows="3" id="couponExpiryDate" name="couponExpiryDate" readonly="readonly" >${expiryDate }</textarea>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="25%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input class="l-button" type="button" value="提交" onclick="subForm1();" />
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table>
	</form>
	<form id="form2" class="form2" method="post" action="${pageContext.request.contextPath}/withdrawCnf/addOrUpdateWithdrawCnf.shtml" >
		<input type="hidden" id="withdrawCnfId" name="withdrawCnfId" value="${withdrawCnf.id }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="25%">奖励类型<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<span style="margin-left: 10px;">
						<label><input type="radio" name="withdrawType" value="1" />抵用券</label>
					</span>
					<span style="margin-left: 30px;">
						<label><input type="radio" name="withdrawType" value="2" checked="checked" />微信红包</label>
					</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="25%">提现名称：<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="nameTwo" name="name" value="${withdrawCnf.name }" validateTwo="{required:true}" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="25%">提现金额：<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="amountTwo" name="amount" value="${amount }" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="25%">说明：</td>
				<td align="left" class="l-table-edit-td" >
					<textarea cols="30" rows="3" type="text" name="remarks" >${withdrawCnf.remarks }</textarea>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="25%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input class="l-button" type="button" value="提交" onclick="subForm2();" />
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table>
	</form>
	</body>
</html>