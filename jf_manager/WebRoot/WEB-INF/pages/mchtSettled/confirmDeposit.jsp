<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<style type="text/css">
body {
	font-size: 13px;
	padding: 10px;
}

td input,td select {
	border: 1px solid #AECAF0;
}
</style>
<script type="text/javascript">
$(function(){
	$("input[name='depositConfirmStatus']").bind('click',function(){
		var depositConfirmStatus = $(this).val();
		if(depositConfirmStatus == "1"){
			$("#mchtTypeTr").show();
			$("#depositTypeTr").show();
			$("#contractDepositTr").show();
			$("#feeRateTr").show();
			//如果选合作初始化为spop需要展示是否自营
			var mchtType=$("input[name='mchtType']:checked").val();
			if(mchtType == '1'){
				$("#isManageSelf").show();
			}
		}else if(depositConfirmStatus == "2"){//不合作
			$("#mchtTypeTr").hide();
			$("#depositTypeTr").hide();
			$("#contractDepositTr").hide();
			$("#feeRateTr").hide();
			$("#isManageSelf").hide();
		}
	});	
	
	$("#confirm").bind('click',function(){
		var id = $("#mchtSettledApplyId").val();
		var mchtType = $("input[name='mchtType']:checked").val();
		var depositConfirmStatus = $("input[name='depositConfirmStatus']:checked").val();
		var depositType = $("input[name='depositType']:checked").val();
		var contractDeposit = $("#contractDeposit").val().trim();
		var brandDeposit = $("#brandDeposit").val().trim();
		var feeRate = $("#feeRate").val().trim();
		var selectContractDeposit;
		var selectBrandDeposit;
		var checked = $("#selectContractDeposit").attr("checked");

		<%--console.log(${mchtSettledApplyCustom.mchtType eq '2' ||  (mchtSettledApplyCustom.mchtType eq '1'   &&  mchtSettledApplyCustom.isManageSelf eq '0' ) })--%>
		if(checked){
			selectContractDeposit = "1";
		}else{
			selectContractDeposit = "0";
		}
		var brandChecked = $("#selectBrandDeposit").attr("checked");
		if(brandChecked){
			selectBrandDeposit = "1";
		}else{
			selectBrandDeposit = "0";
		}
		if(depositConfirmStatus == "1"){
			if(selectContractDeposit == '0' && selectBrandDeposit == '0') {
				commUtil.alertError("保证金不能为空");
				return false;
			}else {
				if(selectContractDeposit == '1') {
					if(!contractDeposit){
						commUtil.alertError("店铺保证金不能为空");
						return false;
					}
					if(!/^(0|[1-9][0-9]*)(\.\d+)?$/.test(contractDeposit)){
						commUtil.alertError("请输入正确格式的店铺保证金");
						return false;
					}
					if(contractDeposit.length > 9){
						commUtil.alertError("店铺保证金不能超过9位数字");
						return false;
					}
				}
				if(selectBrandDeposit == '1') {
					if(!brandDeposit){
						commUtil.alertError("品牌保证金不能为空");
						return false;
					}
					if(!/^(0|[1-9][0-9]*)(\.\d+)?$/.test(brandDeposit)){
						commUtil.alertError("请输入正确格式的品牌保证金");
						return false;
					}
					if(brandDeposit && brandDeposit.length > 9){
						commUtil.alertError("品牌保证金不能超过9位数字");
						return false;
					}
				}
			}
			if(!feeRate){
				commUtil.alertError("技术服务费率不能为空");
				return false;
			}else{
				if(feeRate>=1){
					commUtil.alertError("技术服务费率不能大于等于1");
					return false;
				}
			}
		}
		var mchtType = $("input[name='mchtType']:checked").val();
		var isManageSelf=$("input[name='isManageSelf']:checked").val();
		if (mchtType == '1' && !isManageSelf){
			commUtil.alertError("合作模式为SPOP时,请选择是否自营");
			return false;
		}

		$.ajax({
			url : "${pageContext.request.contextPath}/mchtSettled/confirmDepositStatus.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"mchtType":mchtType,"depositConfirmStatus":depositConfirmStatus,"depositType":depositType,"contractDeposit":contractDeposit,"brandDeposit":brandDeposit,"feeRate":feeRate,"selectContractDeposit":selectContractDeposit,"selectBrandDeposit":selectBrandDeposit,"isManageSelf":isManageSelf},
			timeout : 30000,
			success : function(data) {
				 if(data==null || data.statusCode!=200){
  				     commUtil.alertError(data.message);
  				  }else{
  	                 $.ligerDialog.success("操作成功",function() {
                     	javascript:parent.location.reload();
					 });
  				  }
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});

	showManageSelf();

});
function showManageSelf(){

	var mchtType=$("input[name='mchtType']:checked").val();
	if(mchtType == '1'){
		$("#isManageSelf").show();
	}
	if (mchtType=='2') {
		$("#isManageSelf").hide();
	}
}
</script>
<html>
<body>
	<div class="title-top">
		<input type="hidden" id="mchtSettledApplyId" value="${mchtSettledApplyCustom.id }">
		<table class="gridtable">
          	<tr>
          		<td class="title">是否合作<span class="required">*</span></td>
          		<td>
          			<label><input type="radio" name="depositConfirmStatus" value="1" checked="checked">合作</label>
          			&nbsp;&nbsp;&nbsp;&nbsp;
          			<label><input type="radio" name="depositConfirmStatus" value="2">不合作</label>
          		</td>
          	</tr>
          	<tr id="mchtTypeTr">
          		<td class="title">合作模式</td>
				<td>
					<label><input type="radio" name="mchtType" value="2" <c:if test="${mchtSettledApplyCustom.mchtType eq '2'}">checked="checked"</c:if> <c:if test="${TAG ne true}">disabled</c:if> onclick="showManageSelf()">开放POP</label>
					<label><input type="radio" name="mchtType" value="1" <c:if test="${mchtSettledApplyCustom.mchtType eq '1'}">checked="checked"</c:if> <c:if test="${TAG ne true}">disabled</c:if> onclick="showManageSelf()">开放SPOP</label>
				</td>
          	</tr>

			<tr id="isManageSelf"  style="display:none;">
				<td class="title">是否自营</td>
				<td>
					<label><input type="radio" name="isManageSelf" value="0" <c:if test="${mchtSettledApplyCustom.isManageSelf eq '0'}">checked="checked"</c:if>>非自营</label>
					<label><input type="radio" name="isManageSelf" value="1" <c:if test="${mchtSettledApplyCustom.isManageSelf eq '1'}">checked="checked"</c:if>>自营</label>
				</td>
			</tr>



          	<tr id="depositTypeTr">
          		<td class="title">保证金缴费方式<span class="required">*</span></td>
          		<td>
          			<label><input type="radio" name="depositType" value="1">可货款抵扣</label>
          			&nbsp;&nbsp;&nbsp;&nbsp;
          			<label><input type="radio" name="depositType" value="2" checked="checked">不可货款抵扣</label>
          		</td>
          	</tr>
          	<tr id="contractDepositTr">
          		<td class="title">保证金类型<span class="required">*</span></td>
          		<td>
          			<label><input type="checkbox" name="selectContractDeposit" id="selectContractDeposit" checked >店铺保证金</label>
          			<input type="text" name="contractDeposit" id="contractDeposit" style="width: 100px;" value="${mchtSettledApplyCustom.settledType=='1'?mchtSettledApplyCustom.productTypeDeposit:mchtSettledApplyCustom.productTypeIndividualDeposit }" >
          			<label><input type="checkbox" name="selectBrandDeposit" id="selectBrandDeposit">品牌保证金（每个）</label>
          			<input type="text" name="brandDeposit" id="brandDeposit" style="width: 100px;" >
          		</td>
          	</tr>
          	<tr id="feeRateTr">
          		<td class="title">技术服务费率预定<span class="required">*</span></td>
          		<td><input type="text" name="feeRate" id="feeRate" style="width: 100px;" value="${mchtSettledApplyCustom.settledType=='1'?mchtSettledApplyCustom.productTypeFeeRate:mchtSettledApplyCustom.productTypeIndividualFeeRate }" >例：0.15</td>
          	</tr>
          	<tr>
          		<td class="title">操作</td>
          		<td>
					<input id="confirm" type="button" style="float:left;" class="l-button l-button-test" value="确认"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="取消" class="l-button l-button-test" onclick="frameElement.dialog.close();"/>
				</td>
          	</tr>
        </table>
	</div>
	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
