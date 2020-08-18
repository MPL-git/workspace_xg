<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}

.video_box {
    background: #fff;
    z-index: 2222;
    display: block;
}

.black_box {
    background: #000;
    opacity: 0.6;
    left: 0;
    top: 0;
    z-index: 1111;
    position: fixed;
    height: 100%;
    width: 100%;
}
.video_close {
    position: absolute;
    top: -14px;
    right: -12px;
}
</style>
<script type="text/javascript">
$(function(){
	$(".video_close").bind('click',function(){
		$("#returnBackDiv").hide();
		$(".black_box").hide();
	});
	
	$("#businessInformationStatus").bind('click',function(){
		var checked = $(this).attr("checked");
		if(checked){
			$("#mchtContractStatus").attr("checked",true);
			$("#mchtArchiveStatus").attr("checked",true);
		}
	});
	
	$("#activityStatus").bind('click',function(){
		var checked = $(this).attr("checked");
		var singleProductActivityCount = $("#singleProductActivityCount").val();
		var activityCount = $("#activityCount").val();
		if(checked){
			if(singleProductActivityCount == 0 && activityCount == 0){
				
			}else{
				commUtil.alertError("只有单品活动数量、品牌特卖数量 都为零时，才能勾选");
				$(this).attr("checked",false);
			}
		}
	});
	
	$("#commodityStatus").bind('click',function(){
		var checked = $(this).attr("checked");
		var productCount = $("#productCount").val();
		if(checked){
			if(productCount == 0){
				
			}else{
				commUtil.alertError("只有全部商品已下架才能勾选");
				$(this).attr("checked",false);
			}
		}
		
	});
	
	$("#orderConfirmStatus").bind('click',function(){
		var checked = $(this).attr("checked");
		var subOrderCount = $("#subOrderCount").val();
		if(checked){
			if(subOrderCount == 0){
				
			}else{
				commUtil.alertError("只有订单为零时 可勾选");
				$(this).attr("checked",false);
			}
		}
		
	});
	
	$("#serviceOrderConfirmStatus").bind('click',function(){
		var checked = $(this).attr("checked");
		var customerServiceOrderCount = $("#customerServiceOrderCount").val();
		if(checked){
			if(customerServiceOrderCount == 0){
				
			}else{
				commUtil.alertError("只有售后为零时 可勾选");
				$(this).attr("checked",false);
			}
		}
		
	});
	
	$("#appealOrderConfirmStatus").bind('click',function(){
		var checked = $(this).attr("checked");
		var appealOrderCount = $("#appealOrderCount").val();
		if(checked){
			if(appealOrderCount == 0){
				
			}else{
				commUtil.alertError("投诉单为零时，可勾选");
				$(this).attr("checked",false);
			}
		}
		
	});
	
	$("#interventionOrderConfirmStatus").bind('click',function(){
		var checked = $(this).attr("checked");
		var interventionOrderCount = $("#interventionOrderCount").val();
		if(checked){
			if(interventionOrderCount == 0){
				
			}else{
				commUtil.alertError("介入单为零时，可勾选");
				$(this).attr("checked",false);
			}
		}
		
	});
	
	
	
	$("#depositDate").ligerDateEditor({
		showTime : false,
		labelWidth : 150,
		width : 150,
		labelAlign : 'left'
	});
	
	$("#depositReturnDate").ligerDateEditor({
		showTime : false,
		labelWidth : 150,
		width : 150,
		labelAlign : 'left'
	});
	
	$("input[name='zsConfirmStatus']").bind('click',function(){
		var zsConfirmStatus = $(this).val();
		if(zsConfirmStatus == 1){
			$("#zsRejectReasonTr").hide();
			$("#closeReasonTr").show();
		}else{
			$("#zsRejectReasonTr").show();
			$("#closeReasonTr").hide();
		}
	});
	$("input[name='fwCloseHangUpStatus']").bind('click',function(){
		var fwCloseHangUpStatus = $(this).val();
		if(fwCloseHangUpStatus == 1){
			$("#fwHangUpReasonTr").show();
			$("#mchtContractStatusTr").hide();
			$("#mchtArchiveStatusTr").hide();
			$("#businessInformationStatusTr").hide();
			$("#businessInformationRemarksTr").hide();
		}else{
			$("#fwHangUpReasonTr").hide();
			$("#mchtContractStatusTr").show();
			$("#mchtArchiveStatusTr").show();
			$("#businessInformationStatusTr").show();
			$("#businessInformationRemarksTr").show();
		}
	});
	$("input[name='kfCloseHangUpStatus']").bind('click',function(){
		var kfCloseHangUpStatus = $(this).val();
		if(kfCloseHangUpStatus == 1){
			$("#kfHangUpReasonTr").show();
			$("#orderConfirmStatusTr").hide();
			$("#serviceOrderConfirmStatusTr").hide();
			$("#appealOrderConfirmStatusTr").hide();
			$("#interventionOrderConfirmStatusTr").hide();
			$("#subOrderCodeTr").hide();
			$("#deliveryDateTr").hide();
			$("#threePackagePeriodTr").hide();
		}else{
			$("#kfHangUpReasonTr").hide();
			$("#orderConfirmStatusTr").show();
			$("#serviceOrderConfirmStatusTr").show();
			$("#appealOrderConfirmStatusTr").show();
			$("#interventionOrderConfirmStatusTr").show();
			$("#subOrderCodeTr").show();
			$("#deliveryDateTr").show();
			$("#threePackagePeriodTr").show();
		}
	});
	
	$("#confirm").bind('click',function(){
		var id = $("#id").val();
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 0}">
		var zsConfirmStatus = $("input[name='zsConfirmStatus']:checked").val();
		if(zsConfirmStatus == 2){
			var zsRejectReason = $("#zsRejectReason").val().trim();
			if(!zsRejectReason){
				commUtil.alertError("驳回理由不能为空");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/returnBack.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id,"zsRejectReason":zsRejectReason},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode == '0000'){
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			var closeReason = $("#closeReason").val();
			if(!closeReason){
				commUtil.alertError("请选择关店原因");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/confirm.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id,"closeReason":closeReason},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode == '0000'){
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 1}">
		var activityStatusChecked = $("#activityStatus").attr("checked");
		var commodityStatusChecked = $("#commodityStatus").attr("checked");
		var marketingStatusChecked = $("#marketingStatus").attr("checked");
		if(activityStatusChecked && commodityStatusChecked && marketingStatusChecked){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/confirm.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode == '0000'){
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			commUtil.alertError("只有 活动结算、取消营销安排、商品全部下架 都勾选后，才可以提交");
			return false;
		}
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 2}">
			var fwCloseHangUpStatus = $("input[name='fwCloseHangUpStatus']:checked").val();
			if(fwCloseHangUpStatus == 0){
				var businessInformationStatusChecked = $("#businessInformationStatus").attr("checked");
				var mchtContractStatusChecked = $("#mchtContractStatus").attr("checked");
				var mchtArchiveStatusChecked = $("#mchtArchiveStatus").attr("checked");
				var businessInformationRemarks = $("#businessInformationRemarks").val();
				var canSubmit = false;
				var businessInformationStatus = "0";
				var mchtContractStatus = "0";
				var mchtArchiveStatus = "0";
				if(mchtContractStatusChecked){
					mchtContractStatus = "1";
				}
				if(mchtArchiveStatusChecked){
					mchtArchiveStatus = "1";
				}
				if(businessInformationStatusChecked){
					businessInformationStatus = "1";
					canSubmit = true;					
				}else{
					if(mchtContractStatusChecked && mchtArchiveStatusChecked){
						canSubmit = true;
					}else{
						canSubmit = false;
						commUtil.alertError("只有 已归档、已齐全 都勾选后，才可以提交");
						return false;
					}
				}
				if(canSubmit){
					$.ajax({
						url : "${pageContext.request.contextPath}/mcht/close/confirm.shtml",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						data : {"id":id,businessInformationStatus:businessInformationStatus,mchtContractStatus:mchtContractStatus,mchtArchiveStatus:mchtArchiveStatus,businessInformationRemarks:businessInformationRemarks},
						timeout : 30000,
						success : function(data) {
							if(data.returnCode == '0000'){
								commUtil.alertSuccess("提交成功");
								setTimeout(function(){
									parent.location.reload();
									frameElement.dialog.close();
								},1000);
							}else{
								$.ligerDialog.error(data.returnMsg);
							}
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});
				}else{
					commUtil.alertError("只有 已归档、已齐全 都勾选后，才可以提交");
					return false;
				}
			}else{
				var hangUpReason = $("#fwHangUpReason").val();
				if(!hangUpReason){
					commUtil.alertError("请选择挂起原因");
					return false;
				}
				$.ajax({
					url : "${pageContext.request.contextPath}/mcht/close/hangUp.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {"id":id,"hangUpReason":hangUpReason},
					timeout : 30000,
					success : function(data) {
						if(data.returnCode == '0000'){
							commUtil.alertSuccess("提交成功");
							setTimeout(function(){
								parent.location.reload();
								frameElement.dialog.close();
							},1000);
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 3}">
		var kfCloseHangUpStatus = $("input[name='kfCloseHangUpStatus']:checked").val();
		if(kfCloseHangUpStatus == 0){
			var orderConfirmStatusChecked = $("#orderConfirmStatus").attr("checked");
			var serviceOrderConfirmStatusChecked = $("#serviceOrderConfirmStatus").attr("checked");
			var appealOrderConfirmStatusChecked = $("#appealOrderConfirmStatus").attr("checked");
			var interventionOrderConfirmStatusChecked = $("#interventionOrderConfirmStatus").attr("checked");
			if(orderConfirmStatusChecked && serviceOrderConfirmStatusChecked && appealOrderConfirmStatusChecked && interventionOrderConfirmStatusChecked){
				var threePackagePeriod = $("#threePackagePeriod").val();
				if(!threePackagePeriod){
					commUtil.alertError("三包期不能为空");
					return false;
				}
				$.ajax({
					url : "${pageContext.request.contextPath}/mcht/close/confirm.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {"id":id,"threePackagePeriod":threePackagePeriod},
					timeout : 30000,
					success : function(data) {
						if(data.returnCode == '0000'){
							commUtil.alertSuccess("提交成功");
							setTimeout(function(){
								parent.location.reload();
								frameElement.dialog.close();
							},1000);
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}else{
				commUtil.alertError("只有订单、售后、投诉、介入单全部完成 都勾选后，才可以提交");
				return false;
			}
		}else{
			var hangUpReason = $("#kfHangUpReason").val();
			if(!hangUpReason){
				commUtil.alertError("请选择挂起原因");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/hangUp.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id,"hangUpReason":hangUpReason},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode == '0000'){
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							window.parent.confirmkfHangUpReason(id,hangUpReason);
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 4}">
		var paymentOfGoodsConfirmChecked = $("#paymentOfGoodsConfirm").attr("checked");
		var returnedDepositAmount = $("#returnedDepositAmount").val();
		if(returnedDepositAmount == '' ) {
			commUtil.alertError("请填写当前需退还的保证金！");
			return;
		}
		if(paymentOfGoodsConfirmChecked){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/confirm.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id,"returnedDepositAmount":returnedDepositAmount},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode == '0000'){
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			commUtil.alertError("只有 货款结清确认勾选后，才可以提交");
			return false;
		}
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 5}">
		var endCooperationAgreementChecked = $("#endCooperationAgreement").attr("checked");
		if(endCooperationAgreementChecked){
			var depositDate = $("#depositDate").val();
			if(!depositDate){
				commUtil.alertError("预计保证金退还日期不能为空");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/confirm.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id,"depositDate":depositDate},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode == '0000'){
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			commUtil.alertError("只有 签署终止合作协议勾选后，才可以提交");
			return false;
		}
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 6}">
		var directorConfirmStatusChecked = $("#directorConfirmStatus").attr("checked");
		if(directorConfirmStatusChecked){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/confirm.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode == '0000'){
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			commUtil.alertError("只有确认关店勾选后，才可以提交");
			return false;
		}
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 8}">
		var settlementAuditStatusChecked = $("#settlementAuditStatus").attr("checked");
        var payAmt = $("#payAmt").text();
		if(settlementAuditStatusChecked){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/confirm.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id ,"payAmt":payAmt},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode == '0000'){
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			commUtil.alertError("只有结算审核勾选后，才可以提交");
			return false;
		}
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 9}">
		var depositReturnStatusChecked = $("#depositReturnStatus").attr("checked");
		var needPayStatusChecked = $("#needPayStatus").attr("checked");
		if(depositReturnStatusChecked && needPayStatusChecked){
			var depositReturnDate = $("#depositReturnDate").val();
			if(!depositReturnDate){
				commUtil.alertError("退还保证金日期不能为空");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/close/confirm.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode == '0000'){
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			commUtil.alertError("只有当前需退还的保证金、还需支付的货款 勾选后，才可以提交");
			return false;
		}
		</c:if>
	});
	
	$("#returnBack").bind('click',function(){
		var progressStatus = ${mchtCloseApplicationCustom.progressStatus};
		var title="";
		if(progressStatus == 1){
			title="回退至招商";
		}else if(progressStatus == 2){
			title="回退至商品部";
		}else if(progressStatus == 3){
			title="回退至资料确认";
		}else if(progressStatus == 4){
			title="回退至客服部";
		}else if(progressStatus == 5){
			title="回退至财务部";
		}
		$("#returnBackTitle").text(title);
		$("#remarks").text("");
		$("#returnBackDiv").show();
		$(".black_box").show();
	});
	
	var submitting;
	$("#saveRemarks").bind('click',function(){
		if(submitting){
			return;
		}
		var id = $("#id").val();
		var remarks = $("#remarks").val().trim();
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/close/returnBack.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"remarks":remarks},
			timeout : 30000,
			success : function(data) {
				if(data.returnCode == '0000'){
					submitting = false;
					commUtil.alertSuccess("提交成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					submitting = false;
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				submitting = false;
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
});
function mchtInfoPause(id){
	$.ligerDialog.confirm('您确定将该商家的店铺（合作）更改为暂停吗？', function (yes) {
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/mcht/close/mchtInfoPause.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {"id":id},
					timeout : 30000,
					success : function(data) {
						if(data.returnCode == '0000'){
							commUtil.alertSuccess("提交成功");
							setTimeout(function(){
								location.reload();
							},1000);
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		});
}
function mchtSettleOrderList(mchtCode,needPayAmount){
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: "查看结算金额",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettleOrder/list.shtml?mchtCode=" + mchtCode+"&needPayAmount="+needPayAmount+"&pay_status_list=1&confirmStatus=-1&toHide=1",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
function confirmClose(id){
	  	$.ligerDialog.confirm('您确定将该商家的店铺（合作）更改为关闭吗？', function (yes) {
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/mcht/close/confirm.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {
						id:id
					},
					timeout : 30000,
					success : function(data) {
						if ("0000" == data.returnCode) {
							commUtil.alertSuccess("提交成功");
							setTimeout(function(){
								parent.location.reload();
								frameElement.dialog.close();
							},1000);
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
						
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		});
}

//关闭单品活动
function singleProductActivityClose(mchtId) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "商家单品活动",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/singleProductActivityCloseManager.shtml?mchtId="+mchtId+"&productBrandId=0",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

//关闭品牌活动
function activityClose(mchtId) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "商家品牌特卖活动",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/activityCloseManager.shtml?mchtId="+mchtId+"&productBrandId=0",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

//商品全部下架
function productClose(mchtId) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.5,
		title: "查看商品",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/productCloseManager.shtml?mchtId="+mchtId+"&productBrandId=0",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewSubOrderList(mchtId) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "未完成订单",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/close/viewSubOrderList.shtml?mchtId="+mchtId+"&subOrderStatus="+1,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewCustomerServiceOrderList(mchtId) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "未完成售后单",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/close/viewCustomerServiceOrderList.shtml?mchtId="+mchtId+"&status=0",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewAppealOrderList(mchtId) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "未完成投诉单",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/close/viewAppealOrderList.shtml?mchtId="+mchtId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewInterventionOrderList(mchtId) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "未结案介入单",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/close/viewInterventionOrderList.shtml?mchtId="+mchtId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function mchtInfoArchive(mchtId){
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 100,
		title: "资质归档情况",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtContract/mchtInfoArchive.shtml?mchtId="+mchtId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function newAddMchtProductBrandPics(mchtId){
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 100,
		title: "新增品牌资料归档情况",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/close/newAddMchtProductBrandPics.shtml?mchtId="+mchtId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function downLoad(mchtCloseApplicationId){
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/close/downLoadPDF2.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {
			mchtCloseApplicationId:mchtCloseApplicationId
		},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				var filePath = data.filePath;
				console.log(filePath);
				location.href="${pageContext.request.contextPath}/file_servelt/"+filePath;
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
//	location.href="${pageContext.request.contextPath}/mcht/close/downLoadPDF.shtml?mchtCloseApplicationId="+mchtCloseApplicationId;
}

function closeDiv(){
	$("#returnBackDiv").hide();
	$(".black_box").hide();
}
</script>
<html>
<body>
	<div class="title-top">
	<input type="hidden" id="id" value="${id}">
	<input type="hidden" id="singleProductActivityCount" value="${singleProductActivityCount}">
	<input type="hidden" id="activityCount" value="${activityCount}">
	<input type="hidden" id="productCount" value="${productCount}">
	<input type="hidden" id="subOrderCount" value="${subOrderCount}">
	<input type="hidden" id="customerServiceOrderCount" value="${customerServiceOrderCount}">
	<input type="hidden" id="appealOrderCount" value="${appealOrderCount}">
	<input type="hidden" id="interventionOrderCount" value="${interventionOrderCount}">
		<div>
			<span class="table-title">申请信息</span>
			<span class="table-title" style="float: right;"><a href="javascript:;" onclick="downLoad(${mchtCloseApplicationCustom.id});">下载PDF文档</a></span>
		</div>
		<table class="gridtable">
          	<tr>
          		<td class="title">商家序号</td>
          		<td>${mchtCloseApplicationCustom.mchtCode}</td>
          		<td class="title">公司名称</td>
          		<td>${mchtCloseApplicationCustom.companyName}</td>
          	</tr>
          	<tr>
          		<td class="title">主营类目</td>
          		<td>${mchtCloseApplicationCustom.productTypeName}</td>
          		<td class="title">店铺名称</td>
          		<td>${mchtCloseApplicationCustom.shopName}</td>
          	</tr>
          	<tr>
          		<td class="title">合同开始日期</td>
          		<td><fmt:formatDate value="${mchtCloseApplicationCustom.agreementBeginDate}" pattern="yyyy-MM-dd"/></td>
          		<td class="title">合同结束日期</td>
          		<td><fmt:formatDate value="${mchtCloseApplicationCustom.agreementEndDate}" pattern="yyyy-MM-dd"/></td>
          	</tr>
          	<tr>
          		<td class="title">合同归档状态</td>
          		<td>
          			<c:if test="${mchtCloseApplicationCustom.archiveStatus == 0}">
          				未处理 
          			</c:if>
          			<c:if test="${mchtCloseApplicationCustom.archiveStatus == 1}">
          				通过已归档 
          			</c:if>
          			<c:if test="${mchtCloseApplicationCustom.archiveStatus == 2}">
          				不通过驳回
          			</c:if>
          			<c:if test="${mchtCloseApplicationCustom.archiveStatus == 4}">
          				不签约
          			</c:if>
          		</td>
          		<td class="title">合同归档日期</td>
          		<td><fmt:formatDate value="${mchtCloseApplicationCustom.archiveDate}" pattern="yyyy-MM-dd"/></td>
          	</tr>
          	<tr>
          		<td class="title">经营品牌</td>
          		<td colspan="3">
          			<c:forEach items="${mchtProductBrands}" var="mchtProductBrand">
          				<span style="color: red;">[<c:if test="${mchtProductBrand.status == 0}">申请中</c:if><c:if test="${mchtProductBrand.status == 1}">正常</c:if><c:if test="${mchtProductBrand.status == 2}">暂停</c:if><c:if test="${mchtProductBrand.status == 3}">关闭</c:if><c:if test="${mchtProductBrand.status == 4}">驳回申请</c:if>]</span>${mchtProductBrand.productBrandName}<span style="color: red;">[<fmt:formatNumber type="percent" value="${mchtProductBrand.popCommissionRate}"/>]</span><br>
          			</c:forEach>
          		</td>
          	</tr>
          	<tr>
          		<td class="title">申请类型</td>
          		<td colspan="3">
          			<c:if test="${mchtCloseApplicationCustom.applySource == 1}">
          				商家申请
          			</c:if>
          			<c:if test="${mchtCloseApplicationCustom.applySource == 2}">
          				平台介入关店
          			</c:if>
          		</td>
          	</tr>
          	<tr>
          		<td class="title">申请理由</td>
          		<td colspan="3" style="word-break: break-all;">
          			${mchtCloseApplicationCustom.applyReason}
          		</td>
          	</tr>
        </table>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 0}">
		<br>
        <div>
			<span class="table-title">招商确认</span>
		</div>
		<table class="gridtable">
			<tr>
          		<td colspan="1">招商确认结果</td>
          		<td>
          			<input type="radio" name="zsConfirmStatus" value="1">同意关店
          			<input type="radio" name="zsConfirmStatus" value="2">不同意关店
          		</td>
          	</tr>
          	<tr id="closeReasonTr" style="display: none;">
          		<td colspan="1">关店原因<span style="color: red;">*</span></td>
          		<td>
          			<select id="closeReason" name="closeReason">
						<option value="">请选择</option>
						<c:forEach items="${closeReasonList}" var="closeReason">
							<option value="${closeReason.statusValue}">${closeReason.statusDesc}</option>
						</c:forEach>
					</select>
          		</td>
          	</tr>
          	<tr id="zsRejectReasonTr" style="display: none;">
          		<td colspan="1">驳回理由<span style="color: red;">*</span></td>
          		<td>
          			<textarea rows="10" cols="80" id="zsRejectReason" name="zsRejectReason" maxlength="256"></textarea>
          		</td>
          	</tr>
          	<tr>
	          	<td colspan="1">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
	     	</tr>
		</table>
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 1}">
		<br>
		<div>
			<span class="table-title">招商确认(${mchtCloseApplicationCustom.zsStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
		</div>
		<table class="gridtable">
			<tr>
          		<td>招商确认结果</td>
          		<td>
          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 1}">
          				同意申请
          			</c:if>
          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 2}">
          				不同意关店
          			</c:if>
          		</td>
          	</tr>
          	<tr>
          		<td colspan="1">关店原因</td>
          		<td>
          			<select disabled="disabled">
						<option value="">请选择</option>
						<c:forEach items="${closeReasonList}" var="closeReason">
							<option value="${closeReason.statusValue}" <c:if test="${mchtCloseApplicationCustom.closeReason eq closeReason.statusValue}">selected="selected"</c:if>>${closeReason.statusDesc}</option>
						</c:forEach>
					</select>
          		</td>
          	</tr>
		</table>
		<br>
		<div>
			<span class="table-title">商品部确认</span>
		</div>
		<table class="gridtable">
			<c:if test="${empty mchtCloseApplicationCustom.mchtInfoStatus || mchtCloseApplicationCustom.mchtInfoStatus == 0}">
			<tr>
          		<td>店铺暂停<span style="color: red;">*</span></td>
          		<td>
          			<a href="javascript:;" onclick="mchtInfoPause(${mchtCloseApplicationCustom.id});">[暂停确认]</a>
          		</td>
          	</tr>
			</c:if>
			<c:if test="${mchtCloseApplicationCustom.mchtInfoStatus == 1}">
			<tr>
          		<td>店铺暂停<span style="color: red;">*</span></td>
          		<td>
          			[店铺已暂停]
          		</td>
          	</tr>
          	<tr>
          		<td>活动结束<span style="color: red;">*</span></td>
          		<td>
          			<input type="checkbox" id="activityStatus">已结束
          			<a href="javascript:;" onclick="singleProductActivityClose(${mchtCloseApplicationCustom.mchtId});">【查看单品活动（${singleProductActivityCount}）】</a>
          			<a href="javascript:;" onclick="activityClose(${mchtCloseApplicationCustom.mchtId});">【查看品牌特卖活动（${activityCount}）】</a>
          		</td>
          	</tr>
          	<tr>
          		<td>商品全部下架<span style="color: red;">*</span></td>
          		<td>
          			<input type="checkbox" id="commodityStatus">已下架
          			<a href="javascript:;" onclick="productClose(${mchtCloseApplicationCustom.mchtId});">【查看商品（${productCount}）】</a>
          		</td>
          	</tr>
          	<tr>
          		<td>取消营销安排<span style="color: red;">*</span></td>
          		<td>
          			<input type="checkbox" id="marketingStatus">已全部取消
          		</td>
          	</tr>
			</c:if>
          	<tr>
	          	<td>操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="returnBack" type="button" value="回退至招商" class="l-button l-button-test" style="float:left;width: 80px;"/>
						<input id="confirm" type="button" style="float:left;margin-left: 20px;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	     	</tr>
		</table>
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 2}">
			<br>
			<div>
				<span class="table-title">招商确认(${mchtCloseApplicationCustom.zsStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>招商确认结果</td>
	          		<td>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 1}">
	          				同意申请
	          			</c:if>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 2}">
	          				不同意关店
	          			</c:if>
	          		</td>
	          	</tr>
	          	<tr>
	          		<td colspan="1">关店原因</td>
	          		<td>
	          			<select disabled="disabled">
							<option value="">请选择</option>
							<c:forEach items="${closeReasonList}" var="closeReason">
								<option value="${closeReason.statusValue}" <c:if test="${mchtCloseApplicationCustom.closeReason eq closeReason.statusValue}">selected="selected"</c:if>>${closeReason.statusDesc}</option>
							</c:forEach>
						</select>
	          		</td>
          		</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商品部确认(${mchtCloseApplicationCustom.commodityStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>店铺暂停</td>
	          		<td>
	          			店铺已暂停
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>活动结束<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结束
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>商品全部下架<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已下架
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>取消营销安排<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已全部取消
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商家资料确认</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="fwCloseHangUpStatus" value="1" <c:if test="${mchtCloseApplicationCustom.fwCloseHangUpStatus == 1}">checked="checked"</c:if>>是
						<input type="radio" name="fwCloseHangUpStatus" value="0" <c:if test="${empty mchtCloseApplicationCustom.fwCloseHangUpStatus || mchtCloseApplicationCustom.fwCloseHangUpStatus == 0}">checked="checked"</c:if>>否
					</td>
				</tr>
				<tr id="mchtContractStatusTr" <c:if test="${mchtCloseApplicationCustom.fwCloseHangUpStatus == 1}">style="display: none;"</c:if>>
					<td>商家合同归档<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" id="mchtContractStatus">已归档&nbsp;&nbsp;&nbsp;&nbsp;
						商家合同归档情况：
						<span style="color: red;"><c:if test="${mchtCloseApplicationCustom.archiveStatus == 0}">未处理</c:if></span>
						<span style="color: red;"><c:if test="${mchtCloseApplicationCustom.archiveStatus == 1}">已归档</c:if></span>
						<span style="color: red;"><c:if test="${mchtCloseApplicationCustom.archiveStatus == 2}">驳回</c:if></span>
						<span style="color: red;"><c:if test="${mchtCloseApplicationCustom.archiveStatus == 4}">不签约</c:if></span>
					</td>
				</tr>
				<tr id="mchtArchiveStatusTr" <c:if test="${mchtCloseApplicationCustom.fwCloseHangUpStatus == 1}">style="display: none;"</c:if>>
					<td>商家资料齐全情况<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" id="mchtArchiveStatus">已齐全&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="mchtInfoArchive(${mchtCloseApplicationCustom.mchtId});">【查看入驻资料归档情况】</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="newAddMchtProductBrandPics(${mchtCloseApplicationCustom.mchtId});">【查看新增品牌资料归档情况】</a>
					</td>
				</tr>
				<tr id="businessInformationStatusTr" <c:if test="${mchtCloseApplicationCustom.fwCloseHangUpStatus == 1}">style="display: none;"</c:if>>
					<td>商家全部资料</td>
					<td>
						<input type="checkbox" id="businessInformationStatus" name="businessInformationStatus">已审核
					</td>
				</tr>
				<tr id="businessInformationRemarksTr" <c:if test="${mchtCloseApplicationCustom.fwCloseHangUpStatus == 1}">style="display: none;"</c:if>>
					<td>商家全部资料备注</td>
					<td>
						<textarea rows="15" cols="50" id="businessInformationRemarks" name="businessInformationRemarks"></textarea>
					</td>
				</tr>
				<tr id="fwHangUpReasonTr" <c:if test="${empty mchtCloseApplicationCustom.fwCloseHangUpStatus || mchtCloseApplicationCustom.fwCloseHangUpStatus == 0}">style="display: none;"</c:if>>
					<td>挂起原因<span style="color: red;">*</span></td>
					<td>
						<select id="fwHangUpReason" name="fwHangUpReason" >
							<option value="">请选择</option>
							<option value="1" <c:if test="${mchtCloseApplicationCustom.fwHangUpReason == 1}">selected="selected"</c:if>>您寄回的资质尚未完善，请补齐。未齐全的资质事宜请联系你的招商对接人</option>
						</select>
					</td>
				</tr>
				<tr>
		          	<td>操作</td>
					<td align="left" class="l-table-edit-td">
						<div id="btnDiv">
							<input id="returnBack" type="button" value="回退至商品部" class="l-button l-button-test" style="float:left;width: 80px;"/>
							<input id="confirm" type="button" style="float:left;margin-left: 20px;" class="l-button l-button-submit" value="提交"/>
						</div>
					</td>
		     	</tr>
			</table>
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 3}">
			<br>
			<div>
				<span class="table-title">招商确认(${mchtCloseApplicationCustom.zsStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>招商确认结果</td>
	          		<td>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 1}">
	          				同意申请
	          			</c:if>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 2}">
	          				不同意关店
	          			</c:if>
	          		</td>
	          	</tr>
	          	<tr>
	          		<td colspan="1">关店原因</td>
	          		<td>
	          			<select disabled="disabled">
							<option value="">请选择</option>
							<c:forEach items="${closeReasonList}" var="closeReason">
								<option value="${closeReason.statusValue}" <c:if test="${mchtCloseApplicationCustom.closeReason eq closeReason.statusValue}">selected="selected"</c:if>>${closeReason.statusDesc}</option>
							</c:forEach>
						</select>
	          		</td>
          		</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商品部确认(${mchtCloseApplicationCustom.commodityStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>店铺暂停</td>
	          		<td>
	          			店铺已暂停
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>活动结束<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结束
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>商品全部下架<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已下架
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>取消营销安排<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已全部取消
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商家资料确认(${mchtCloseApplicationCustom.mchtArchiveStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="fwCloseHangUpStatus" value="0" checked="checked">否
					</td>
				</tr>
				<tr>
					<td>商家合同归档<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已归档
					</td>
				</tr>
				<tr>
					<td>商家资料齐全情况<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已齐全&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="mchtInfoArchive(${mchtCloseApplicationCustom.mchtId});">【查看入驻资料归档情况】</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="newAddMchtProductBrandPics(${mchtCloseApplicationCustom.mchtId});">【查看新增品牌资料归档情况】</a>
					</td>
				</tr>
				<tr>
					<td>商家全部资料</td>
					<td>
						<input type="checkbox" disabled="disabled" <c:if test="${mchtCloseApplicationCustom.businessInformationStatus eq 1}">checked="checked"</c:if>>已审核
					</td>
				</tr>
				<tr>
					<td>商家全部资料备注</td>
					<td>
						<textarea rows="15" cols="50" disabled="disabled">${mchtCloseApplicationCustom.businessInformationRemarks}</textarea>
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">客服部确认</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="kfCloseHangUpStatus" value="1" <c:if test="${mchtCloseApplicationCustom.kfCloseHangUpStatus == 1}">checked="checked"</c:if>>是
						<input type="radio" name="kfCloseHangUpStatus" value="0" <c:if test="${empty mchtCloseApplicationCustom.kfCloseHangUpStatus || mchtCloseApplicationCustom.kfCloseHangUpStatus == 0}">checked="checked"</c:if>>否
					</td>
				</tr>
				<tr id="orderConfirmStatusTr" <c:if test="${mchtCloseApplicationCustom.kfCloseHangUpStatus == 1}">style="display: none;"</c:if>>
					<td>订单完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" id="orderConfirmStatus" >已完成
						<a href="javascript:;" onclick="viewSubOrderList(${mchtCloseApplicationCustom.mchtId});">【未完成订单（${subOrderCount}）】</a>
					</td>
				</tr>
				<tr id="serviceOrderConfirmStatusTr" <c:if test="${mchtCloseApplicationCustom.kfCloseHangUpStatus == 1}">style="display: none;"</c:if>>
					<td>售后全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" id="serviceOrderConfirmStatus" >已完成
						<a href="javascript:;" onclick="viewCustomerServiceOrderList(${mchtCloseApplicationCustom.mchtId});">【未完成售后（${customerServiceOrderCount}）】</a>
					</td>
				</tr>
				<tr id="appealOrderConfirmStatusTr" <c:if test="${mchtCloseApplicationCustom.kfCloseHangUpStatus == 1}">style="display: none;"</c:if>>
					<td>投诉全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" id="appealOrderConfirmStatus" >已完成
						<a href="javascript:;" onclick="viewAppealOrderList(${mchtCloseApplicationCustom.mchtId});">【未完成投诉单（${appealOrderCount}）】</a>
					</td>
				</tr>
				<tr id="interventionOrderConfirmStatusTr" <c:if test="${mchtCloseApplicationCustom.kfCloseHangUpStatus == 1}">style="display: none;"</c:if>>
					<td>介入单全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" id="interventionOrderConfirmStatus" >已完成
						<a href="javascript:;" onclick="viewInterventionOrderList(${mchtCloseApplicationCustom.mchtId});">【未结案介入单（${interventionOrderCount}）】</a>
					</td>
				</tr>
				<tr id="subOrderCodeTr" <c:if test="${mchtCloseApplicationCustom.kfCloseHangUpStatus == 1}">style="display: none;"</c:if>>
					<td>最后一个订单<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.subOrderCode}
					</td>
				</tr>
				<tr id="deliveryDateTr" <c:if test="${mchtCloseApplicationCustom.kfCloseHangUpStatus == 1}">style="display: none;"</c:if>>
					<td>日期<span style="color: red;">*</span></td>
					<td>
						发货日期：<fmt:formatDate value="${mchtCloseApplicationCustom.deliveryDate}" pattern="yyyy-MM-dd"/>完成日期：<fmt:formatDate value="${mchtCloseApplicationCustom.completeDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr id="threePackagePeriodTr" <c:if test="${mchtCloseApplicationCustom.kfCloseHangUpStatus == 1}">style="display: none;"</c:if>>
					<td>三包期<span style="color: red;">*</span></td>
					<td>
						<input name="threePackagePeriod" id="threePackagePeriod" >
					</td>
				</tr>
				<tr id="kfHangUpReasonTr" <c:if test="${empty mchtCloseApplicationCustom.kfCloseHangUpStatus || mchtCloseApplicationCustom.kfCloseHangUpStatus == 0}">style="display: none;"</c:if>>
					<td>挂起原因<span style="color: red;">*</span></td>
					<td>
						<select id="kfHangUpReason" name="kfHangUpReason">
							<option value="">请选择</option>
							<option value="1" <c:if test="${mchtCloseApplicationCustom.kfHangUpReason == 1}">selected="selected"</c:if>>当前您还有订单未完成，审核暂停</option>
							<option value="2" <c:if test="${mchtCloseApplicationCustom.kfHangUpReason == 2}">selected="selected"</c:if>>当前店铺还有介入单未完成，审核暂停</option>
							<option value="3" <c:if test="${mchtCloseApplicationCustom.kfHangUpReason == 3}">selected="selected"</c:if>>当前存在订单三包期未过，审核暂停</option>
						</select>
					</td>
				</tr>
				<tr>
		          	<td>操作</td>
					<td align="left" class="l-table-edit-td">
						<div id="btnDiv">
							<input id="returnBack" type="button" value="回退至资料确认" class="l-button l-button-test" style="float:left;width: 90px;"/>
							<input id="confirm" type="button" style="float:left;margin-left: 20px;" class="l-button l-button-submit" value="提交"/>
						</div>
					</td>
		     	</tr>
			</table>
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 4}">
			<br>
			<div>
				<span class="table-title">招商确认(${mchtCloseApplicationCustom.zsStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>招商确认结果</td>
	          		<td>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 1}">
	          				同意申请
	          			</c:if>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 2}">
	          				不同意关店
	          			</c:if>
	          		</td>
	          	</tr>
	          	<tr>
	          		<td colspan="1">关店原因</td>
	          		<td>
	          			<select disabled="disabled">
							<option value="">请选择</option>
							<c:forEach items="${closeReasonList}" var="closeReason">
								<option value="${closeReason.statusValue}" <c:if test="${mchtCloseApplicationCustom.closeReason eq closeReason.statusValue}">selected="selected"</c:if>>${closeReason.statusDesc}</option>
							</c:forEach>
						</select>
	          		</td>
          		</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商品部确认(${mchtCloseApplicationCustom.commodityStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>店铺暂停</td>
	          		<td>
	          			店铺已暂停
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>活动结束<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结束
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>商品全部下架<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已下架
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>取消营销安排<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已全部取消
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商家资料确认(${mchtCloseApplicationCustom.mchtArchiveStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="fwCloseHangUpStatus" value="0" checked="checked">否
					</td>
				</tr>
				<tr>
					<td>商家合同归档<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已归档
					</td>
				</tr>
				<tr>
					<td>商家资料齐全情况<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已齐全&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="mchtInfoArchive(${mchtCloseApplicationCustom.mchtId});">【查看入驻资料归档情况】</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="newAddMchtProductBrandPics(${mchtCloseApplicationCustom.mchtId});">【查看新增品牌资料归档情况】</a>
					</td>
				</tr>
				<tr>
					<td>商家全部资料</td>
					<td>
						<input type="checkbox" disabled="disabled" <c:if test="${mchtCloseApplicationCustom.businessInformationStatus eq 1}">checked="checked"</c:if>>已审核
					</td>
				</tr>
				<tr>
					<td>商家全部资料备注</td>
					<td>
						<textarea rows="15" cols="50" disabled="disabled">${mchtCloseApplicationCustom.businessInformationRemarks}</textarea>
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">客服部确认(${mchtCloseApplicationCustom.kfStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.kfConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="kfCloseHangUpStatus" value="0" checked="checked">否
					</td>
				</tr>
				<tr>
					<td>订单完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>售后全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>投诉全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>介入单全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>最后一个订单<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.subOrderCode}
					</td>
				</tr>
				<tr>
					<td>日期<span style="color: red;">*</span></td>
					<td>
						发货日期：<fmt:formatDate value="${mchtCloseApplicationCustom.deliveryDate}" pattern="yyyy-MM-dd"/>完成日期：<fmt:formatDate value="${mchtCloseApplicationCustom.completeDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>三包期<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.threePackagePeriod}
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">财务部货款结清确认</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>货款结清确认<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" id="paymentOfGoodsConfirm"  <c:if test="${total==0}">  checked="checked" </c:if> >${totol}已结清&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="mchtSettleOrderList('${mchtCloseApplicationCustom.mchtCode}',-1);">【查看结算金额】</a>
	          		</td>
	          	</tr>
				<tr>
	          		<td>商家保证金情况</td>
	          		<td>
	          			保证金应缴总额：${mchtCloseApplicationCustom.totalAmt}元 已缴总额：${mchtCloseApplicationCustom.payAmt}元 还需补缴：${mchtCloseApplicationCustom.unpayAmt}元
	          		</td>
	          	</tr>

				<tr>
					<td>当前需退还的保证金</td>
					<td>
						<input id="returnedDepositAmount"  name="returnedDepositAmount" value="${mchtCloseApplicationCustom.payAmt}" type="text" style="float:left;width: 200px;">
					</td>
				</tr>

	          	<tr>
		          	<td>操作</td>
					<td align="left" class="l-table-edit-td">
						<div id="btnDiv">
							<input id="returnBack" type="button" value="回退至客服部" class="l-button l-button-test" style="float:left;width: 80px;"/>
							<input id="confirm" type="button" style="float:left;margin-left: 20px;" class="l-button l-button-submit" value="提交"/>
						</div>
					</td>
		     	</tr>
			</table>
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 5}">
			<br>
			<div>
				<span class="table-title">招商确认(${mchtCloseApplicationCustom.zsStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>招商确认结果</td>
	          		<td>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 1}">
	          				同意申请
	          			</c:if>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 2}">
	          				不同意关店
	          			</c:if>
	          		</td>
	          	</tr>
	          	<tr>
          		<td colspan="1">关店原因</td>
	          		<td>
	          			<select disabled="disabled">
							<option value="">请选择</option>
							<c:forEach items="${closeReasonList}" var="closeReason">
								<option value="${closeReason.statusValue}" <c:if test="${mchtCloseApplicationCustom.closeReason eq closeReason.statusValue}">selected="selected"</c:if>>${closeReason.statusDesc}</option>
							</c:forEach>
						</select>
	          		</td>
          		</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商品部确认(${mchtCloseApplicationCustom.commodityStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>店铺暂停</td>
	          		<td>
	          			店铺已暂停
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>活动结束<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结束
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>商品全部下架<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已下架
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>取消营销安排<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已全部取消
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商家资料确认(${mchtCloseApplicationCustom.mchtArchiveStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="fwCloseHangUpStatus" value="0" checked="checked">否
					</td>
				</tr>
				<tr>
					<td>商家合同归档<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已归档
					</td>
				</tr>
				<tr>
					<td>商家资料齐全情况<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已齐全&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="mchtInfoArchive(${mchtCloseApplicationCustom.mchtId});">【查看入驻资料归档情况】</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="newAddMchtProductBrandPics(${mchtCloseApplicationCustom.mchtId});">【查看新增品牌资料归档情况】</a>
					</td>
				</tr>
				<tr>
					<td>商家全部资料</td>
					<td>
						<input type="checkbox" disabled="disabled" <c:if test="${mchtCloseApplicationCustom.businessInformationStatus eq 1}">checked="checked"</c:if>>已审核
					</td>
				</tr>
				<tr>
					<td>商家全部资料备注</td>
					<td>
						<textarea rows="15" cols="50" disabled="disabled">${mchtCloseApplicationCustom.businessInformationRemarks}</textarea>
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">客服部确认(${mchtCloseApplicationCustom.kfStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.kfConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="kfCloseHangUpStatus" value="0" checked="checked" disabled="disabled">否
					</td>
				</tr>
				<tr>
					<td>订单完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>售后全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>投诉全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>介入单全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>最后一个订单<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.subOrderCode}
					</td>
				</tr>
				<tr>
					<td>日期<span style="color: red;">*</span></td>
					<td>
						发货日期：<fmt:formatDate value="${mchtCloseApplicationCustom.deliveryDate}" pattern="yyyy-MM-dd"/>完成日期：<fmt:formatDate value="${mchtCloseApplicationCustom.completeDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>三包期<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.threePackagePeriod}
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">财务部货款结清确认(${mchtCloseApplicationCustom.cwStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.cwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>货款结清确认<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结清&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="mchtSettleOrderList('${mchtCloseApplicationCustom.mchtCode}',-1);">【查看结算金额】</a>
	          		</td>
	          	</tr>
				<tr>
	          		<td>商家保证金情况</td>
	          		<td>
	          			保证金应缴总额：${mchtCloseApplicationCustom.totalAmt}元 已缴总额：${mchtCloseApplicationCustom.payAmt}元 还需补缴：${mchtCloseApplicationCustom.unpayAmt}元
	          		</td>
	          	</tr>
				<tr>
					<td>
						当前需退还的保证金：
					</td>
					<td>
						${mchtCloseApplicationCustom.payAmt}元
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">法务部确认<c:if test="${not empty mchtCloseApplicationCustom.filePath}"><a href="${pageContext.request.contextPath}/file_servelt${mchtCloseApplicationCustom.filePath}" target="_blank">【下载合同】</a></c:if></span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>签署终止合作协议<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" id="endCooperationAgreement">已签署
	          		</td>
	          	</tr>
				<tr>
	          		<td>协议出具日期</td>
	          		<td>
	          			<fmt:formatDate value="${mchtCloseApplicationCustom.agreementIssueDate}" pattern="yyyy-MM-dd"/>
	          		</td>
	          	</tr>
				<tr>
	          		<td>预计保证金退还日期<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="text" id="depositDate" name="depositDate" />
	          		</td>
	          	</tr>
	          	<tr>
		          	<td>操作</td>
					<td align="left" class="l-table-edit-td">
						<div id="btnDiv">
							<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
							<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
						</div>
					</td>
		     	</tr>
			</table>
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 6}">
			<br>
			<div>
				<span class="table-title">招商确认(${mchtCloseApplicationCustom.zsStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>招商确认结果</td>
	          		<td>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 1}">
	          				同意申请
	          			</c:if>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 2}">
	          				不同意关店
	          			</c:if>
	          		</td>
	          	</tr>
	          	<tr>
          		<td colspan="1">关店原因</td>
	          		<td>
	          			<select disabled="disabled">
							<option value="">请选择</option>
							<c:forEach items="${closeReasonList}" var="closeReason">
								<option value="${closeReason.statusValue}" <c:if test="${mchtCloseApplicationCustom.closeReason eq closeReason.statusValue}">selected="selected"</c:if>>${closeReason.statusDesc}</option>
							</c:forEach>
						</select>
	          		</td>
          		</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商品部确认(${mchtCloseApplicationCustom.commodityStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>店铺暂停</td>
	          		<td>
	          			店铺已暂停
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>活动结束<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结束
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>商品全部下架<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已下架
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>取消营销安排<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已全部取消
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商家资料确认(${mchtCloseApplicationCustom.mchtArchiveStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="fwCloseHangUpStatus" value="0" checked="checked" disabled="disabled">否
					</td>
				</tr>
				<tr>
					<td>商家合同归档<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已归档
					</td>
				</tr>
				<tr>
					<td>商家资料齐全情况<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已齐全&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="mchtInfoArchive(${mchtCloseApplicationCustom.mchtId});">【查看入驻资料归档情况】</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="newAddMchtProductBrandPics(${mchtCloseApplicationCustom.mchtId});">【查看新增品牌资料归档情况】</a>
					</td>
				</tr>
				<tr>
					<td>商家全部资料</td>
					<td>
						<input type="checkbox" disabled="disabled" <c:if test="${mchtCloseApplicationCustom.businessInformationStatus eq 1}">checked="checked"</c:if>>已审核
					</td>
				</tr>
				<tr>
					<td>商家全部资料备注</td>
					<td>
						<textarea rows="15" cols="50" disabled="disabled">${mchtCloseApplicationCustom.businessInformationRemarks}</textarea>
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">客服部确认(${mchtCloseApplicationCustom.kfStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.kfConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="kfCloseHangUpStatus" value="0" checked="checked" disabled="disabled">否
					</td>
				</tr>
				<tr>
					<td>订单完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>售后全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>投诉全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>介入单全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>最后一个订单<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.subOrderCode}
					</td>
				</tr>
				<tr>
					<td>日期<span style="color: red;">*</span></td>
					<td>
						发货日期：<fmt:formatDate value="${mchtCloseApplicationCustom.deliveryDate}" pattern="yyyy-MM-dd"/>完成日期：<fmt:formatDate value="${mchtCloseApplicationCustom.completeDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>三包期<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.threePackagePeriod}
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">财务部货款结清确认(${mchtCloseApplicationCustom.cwStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.cwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>货款结清确认<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结清&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="mchtSettleOrderList('${mchtCloseApplicationCustom.mchtCode}',-1);">【查看结算金额】</a>
	          		</td>
	          	</tr>
				<tr>
	          		<td>商家保证金情况</td>
	          		<td>
	          			保证金应缴总额：${mchtCloseApplicationCustom.totalAmt}元 已缴总额：${mchtCloseApplicationCustom.payAmt}元 还需补缴：${mchtCloseApplicationCustom.unpayAmt}元
	          		</td>
	          	</tr>
				<tr>
					<td>
						当前需退还的保证金：
					</td>
					<td>
							${mchtCloseApplicationCustom.payAmt}元
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">法务部确认(${mchtCloseApplicationCustom.fwStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.fwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)<c:if test="${not empty mchtCloseApplicationCustom.filePath}"><a href="${pageContext.request.contextPath}/file_servelt${mchtCloseApplicationCustom.filePath}" target="_blank">【下载合同】</a></c:if></span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>签署终止合作协议<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已签署
	          		</td>
	          	</tr>
				<tr>
	          		<td>协议出具日期</td>
	          		<td>
	          			<fmt:formatDate value="${mchtCloseApplicationCustom.agreementIssueDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	          		</td>
	          	</tr>
				<tr>
	          		<td>预计保证金退还日期</td>
	          		<td>
	          			<fmt:formatDate value="${mchtCloseApplicationCustom.depositDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">关店审核</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>确认关店<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" id="directorConfirmStatus">确认关店
	          		</td>
	          	</tr>
				<tr>
		          	<td>操作</td>
					<td align="left" class="l-table-edit-td">
						<div id="btnDiv">
							<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
							<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
						</div>
					</td>
			    </tr>
			</table>
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 7}">
			<br>
			<div>
				<span class="table-title">招商确认(${mchtCloseApplicationCustom.zsStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>招商确认结果</td>
	          		<td>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 1}">
	          				同意申请
	          			</c:if>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 2}">
	          				不同意关店
	          			</c:if>
	          		</td>
	          	</tr>
	          	<tr>
	          		<td colspan="1">关店原因</td>
	          		<td>
	          			<select disabled="disabled">
							<option value="">请选择</option>
							<c:forEach items="${closeReasonList}" var="closeReason">
								<option value="${closeReason.statusValue}" <c:if test="${mchtCloseApplicationCustom.closeReason eq closeReason.statusValue}">selected="selected"</c:if>>${closeReason.statusDesc}</option>
							</c:forEach>
						</select>
	          		</td>
          		</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商品部确认(${mchtCloseApplicationCustom.commodityStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>店铺暂停</td>
	          		<td>
	          			店铺已暂停
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>活动结束<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结束
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>商品全部下架<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已下架
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>取消营销安排<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已全部取消
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商家资料确认(${mchtCloseApplicationCustom.mchtArchiveStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="fwCloseHangUpStatus" value="0" checked="checked" disabled="disabled">否
					</td>
				</tr>
				<tr>
					<td>商家合同归档<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已归档
					</td>
				</tr>
				<tr>
					<td>商家资料齐全情况<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已齐全&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="mchtInfoArchive(${mchtCloseApplicationCustom.mchtId});">【查看入驻资料归档情况】</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="newAddMchtProductBrandPics(${mchtCloseApplicationCustom.mchtId});">【查看新增品牌资料归档情况】</a>
					</td>
				</tr>
				<tr>
					<td>商家全部资料</td>
					<td>
						<input type="checkbox" disabled="disabled" <c:if test="${mchtCloseApplicationCustom.businessInformationStatus eq 1}">checked="checked"</c:if>>已审核
					</td>
				</tr>
				<tr>
					<td>商家全部资料备注</td>
					<td>
						<textarea rows="15" cols="50" disabled="disabled">${mchtCloseApplicationCustom.businessInformationRemarks}</textarea>
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">客服部确认(${mchtCloseApplicationCustom.kfStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.kfConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="kfCloseHangUpStatus" value="0" checked="checked" disabled="disabled">否
					</td>
				</tr>
				<tr>
					<td>订单完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>售后全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>投诉全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>介入单全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>最后一个订单<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.subOrderCode}
					</td>
				</tr>
				<tr>
					<td>日期<span style="color: red;">*</span></td>
					<td>
						发货日期：<fmt:formatDate value="${mchtCloseApplicationCustom.deliveryDate}" pattern="yyyy-MM-dd"/>完成日期：<fmt:formatDate value="${mchtCloseApplicationCustom.completeDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>三包期<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.threePackagePeriod}
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">财务部货款结清确认(${mchtCloseApplicationCustom.cwStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.cwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>货款结清确认<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结清&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="mchtSettleOrderList('${mchtCloseApplicationCustom.mchtCode}',-1);">【查看结算金额】</a>
	          		</td>
	          	</tr>
				<tr>
	          		<td>商家保证金情况</td>
	          		<td>
	          			保证金应缴总额：${mchtCloseApplicationCustom.totalAmt}元 已缴总额：${mchtCloseApplicationCustom.payAmt}元 还需补缴：${mchtCloseApplicationCustom.unpayAmt}元
	          		</td>
	          	</tr>
				<tr>
					<td>
						当前需退还的保证金：
					</td>
					<td>
							${mchtCloseApplicationCustom.payAmt}元
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">法务部确认(${mchtCloseApplicationCustom.fwStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.fwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)<c:if test="${not empty mchtCloseApplicationCustom.filePath}"><a href="${pageContext.request.contextPath}/file_servelt${mchtCloseApplicationCustom.filePath}" target="_blank">【下载合同】</a></c:if></span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>签署终止合作协议<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已签署
	          		</td>
	          	</tr>
				<tr>
	          		<td>协议出具日期</td>
	          		<td>
	          			<fmt:formatDate value="${mchtCloseApplicationCustom.agreementIssueDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	          		</td>
	          	</tr>
				<tr>
	          		<td>预计保证金退还日期</td>
	          		<td>
	          			<fmt:formatDate value="${mchtCloseApplicationCustom.depositDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">关店审核(${mchtCloseApplicationCustom.directorStaffName}<fmt:formatDate value="${mchtCloseApplicationCustom.directorConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>确认关店<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">确认关店
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">产品部关店</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>确认关店<span style="color: red;">*</span></td>
	          		<td>
	          			<a href="javascript:;" onclick="confirmClose(${mchtCloseApplicationCustom.id});">【确认关店】</a>
	          		</td>
	          	</tr>
			</table>
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 8}">
			<br>
			<div>
				<span class="table-title">招商确认(${mchtCloseApplicationCustom.zsStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>招商确认结果</td>
	          		<td>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 1}">
	          				同意申请
	          			</c:if>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 2}">
	          				不同意关店
	          			</c:if>
	          		</td>
	          	</tr>
	          	<tr>
	          		<td colspan="1">关店原因</td>
	          		<td>
	          			<select disabled="disabled">
							<option value="">请选择</option>
							<c:forEach items="${closeReasonList}" var="closeReason">
								<option value="${closeReason.statusValue}" <c:if test="${mchtCloseApplicationCustom.closeReason eq closeReason.statusValue}">selected="selected"</c:if>>${closeReason.statusDesc}</option>
							</c:forEach>
						</select>
	          		</td>
          		</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商品部确认(${mchtCloseApplicationCustom.commodityStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>店铺暂停</td>
	          		<td>
	          			店铺已暂停
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>活动结束<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" readonly="readonly" disabled="disabled">已结束
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>商品全部下架<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled" disabled="disabled">已下架
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>取消营销安排<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已全部取消
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商家资料确认(${mchtCloseApplicationCustom.mchtArchiveStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="fwCloseHangUpStatus" value="0" checked="checked" disabled="disabled">否
					</td>
				</tr>
				<tr>
					<td>商家合同归档<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已归档
					</td>
				</tr>
				<tr>
					<td>商家资料齐全情况<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已齐全&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="mchtInfoArchive(${mchtCloseApplicationCustom.mchtId});">【查看入驻资料归档情况】</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="newAddMchtProductBrandPics(${mchtCloseApplicationCustom.mchtId});">【查看新增品牌资料归档情况】</a>
					</td>
				</tr>
				<tr>
					<td>商家全部资料</td>
					<td>
						<input type="checkbox" disabled="disabled" <c:if test="${mchtCloseApplicationCustom.businessInformationStatus eq 1}">checked="checked"</c:if>>已审核
					</td>
				</tr>
				<tr>
					<td>商家全部资料备注</td>
					<td>
						<textarea rows="15" cols="50" disabled="disabled">${mchtCloseApplicationCustom.businessInformationRemarks}</textarea>
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">客服部确认(${mchtCloseApplicationCustom.kfStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.kfConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="kfCloseHangUpStatus" value="0" checked="checked" disabled="disabled">否
					</td>
				</tr>
				<tr>
					<td>订单完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>售后全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>投诉全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>介入单全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>最后一个订单<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.subOrderCode}
					</td>
				</tr>
				<tr>
					<td>日期<span style="color: red;">*</span></td>
					<td>
						发货日期：<fmt:formatDate value="${mchtCloseApplicationCustom.deliveryDate}" pattern="yyyy-MM-dd"/>完成日期：<fmt:formatDate value="${mchtCloseApplicationCustom.completeDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>三包期<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.threePackagePeriod}
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">财务部货款结清确认(${mchtCloseApplicationCustom.cwStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.cwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>货款结清确认<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结清&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="mchtSettleOrderList('${mchtCloseApplicationCustom.mchtCode}',-1);">【查看结算金额】</a>
	          		</td>
	          	</tr>
				<tr>
	          		<td>商家保证金情况</td>
	          		<td>
	          			保证金应缴总额：${mchtCloseApplicationCustom.totalAmt}元 已缴总额：${mchtCloseApplicationCustom.payAmt}元 还需补缴：${mchtCloseApplicationCustom.unpayAmt}元
	          		</td>
	          	</tr>
				<tr>
					<td>
						当前需退还的保证金：
					</td>
					<td>
							${mchtCloseApplicationCustom.payAmt}元
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">法务部确认(${mchtCloseApplicationCustom.fwStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.fwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)<c:if test="${not empty mchtCloseApplicationCustom.filePath}"><a href="${pageContext.request.contextPath}/file_servelt${mchtCloseApplicationCustom.filePath}" target="_blank">【下载合同】</a></c:if></span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>签署终止合作协议<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已签署
	          		</td>
	          	</tr>
				<tr>
	          		<td>协议出具日期</td>
	          		<td>
	          			<fmt:formatDate value="${mchtCloseApplicationCustom.agreementIssueDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	          		</td>
	          	</tr>
				<tr>
	          		<td>预计保证金退还日期</td>
	          		<td>
	          			<fmt:formatDate value="${mchtCloseApplicationCustom.depositDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">关店审核(${mchtCloseApplicationCustom.directorStaffName}<fmt:formatDate value="${mchtCloseApplicationCustom.directorConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>确认关店<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">确认关店
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">产品部关店(${mchtCloseApplicationCustom.productStaffName}<fmt:formatDate value="${mchtCloseApplicationCustom.productConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>确认关店</td>
	          		<td>店铺已关闭</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">结算审核</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>当前需退还的保证金</td>
					<td><span id="payAmt">${mchtCloseApplicationCustom.payAmt}</span></td>
	          	</tr>
				<tr>
	          		<td>还需支付的货款</td>
	          		<td>${total}</td>
	          	</tr>
				<tr>
	          		<td>结算审核</td>
	          		<td><input type="checkbox" id="settlementAuditStatus">可结算</td>
	          	</tr>
	          	<tr>
		          	<td>操作</td>
					<td align="left" class="l-table-edit-td">
						<div id="btnDiv">
							<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
							<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
						</div>
					</td>
			    </tr>
			</table>
		</c:if>
		<c:if test="${mchtCloseApplicationCustom.progressStatus == 9}">
			<br>
			<div>
				<span class="table-title">招商确认(${mchtCloseApplicationCustom.zsStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.zsConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>招商确认结果</td>
	          		<td>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 1}">
	          				同意申请
	          			</c:if>
	          			<c:if test="${mchtCloseApplicationCustom.zsConfirmStatus == 2}">
	          				不同意关店
	          			</c:if>
	          		</td>
	          	</tr>
	          	<tr>
	          		<td colspan="1">关店原因</td>
	          		<td>
	          			<select disabled="disabled">
							<option value="">请选择</option>
							<c:forEach items="${closeReasonList}" var="closeReason">
								<option value="${closeReason.statusValue}" <c:if test="${mchtCloseApplicationCustom.closeReason eq closeReason.statusValue}">selected="selected"</c:if>>${closeReason.statusDesc}</option>
							</c:forEach>
						</select>
	          		</td>
          		</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商品部确认(${mchtCloseApplicationCustom.commodityStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.commodityConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>店铺暂停</td>
	          		<td>
	          			店铺已暂停
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>活动结束<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结束
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>商品全部下架<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已下架
	          		</td>
	          	</tr>
	          	<tr>
	          		<td>取消营销安排<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已全部取消
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">商家资料确认(${mchtCloseApplicationCustom.mchtArchiveStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.mchtArchiveConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="fwCloseHangUpStatus" value="0" checked="checked" disabled="disabled">否
					</td>
				</tr>
				<tr>
					<td>商家合同归档<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已归档
					</td>
				</tr>
				<tr>
					<td>商家资料齐全情况<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox"  checked="checked" disabled="disabled">已齐全&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="mchtInfoArchive(${mchtCloseApplicationCustom.mchtId});">【查看入驻资料归档情况】</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" onclick="newAddMchtProductBrandPics(${mchtCloseApplicationCustom.mchtId});">【查看新增品牌资料归档情况】</a>
					</td>
				</tr>
				<tr>
					<td>商家全部资料</td>
					<td>
						<input type="checkbox" disabled="disabled" <c:if test="${mchtCloseApplicationCustom.businessInformationStatus eq 1}">checked="checked"</c:if>>已审核
					</td>
				</tr>
				<tr>
					<td>商家全部资料备注</td>
					<td>
						<textarea rows="15" cols="50" disabled="disabled">${mchtCloseApplicationCustom.businessInformationRemarks}</textarea>
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">客服部确认(${mchtCloseApplicationCustom.kfStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.kfConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
					<td>店铺关闭是否挂起</td>
					<td>
						<input type="radio" name="kfCloseHangUpStatus" value="0" checked="checked" disabled="disabled">否
					</td>
				</tr>
				<tr>
					<td>订单完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>售后全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>投诉全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>介入单全部完成确认<span style="color: red;">*</span></td>
					<td>
						<input type="checkbox" checked="checked" disabled="disabled">已完成
					</td>
				</tr>
				<tr>
					<td>最后一个订单<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.subOrderCode}
					</td>
				</tr>
				<tr>
					<td>日期<span style="color: red;">*</span></td>
					<td>
						发货日期：<fmt:formatDate value="${mchtCloseApplicationCustom.deliveryDate}" pattern="yyyy-MM-dd"/>完成日期：<fmt:formatDate value="${mchtCloseApplicationCustom.completeDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td>三包期<span style="color: red;">*</span></td>
					<td>
						${mchtCloseApplicationCustom.threePackagePeriod}
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">财务部货款结清确认(${mchtCloseApplicationCustom.cwStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.cwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>货款结清确认<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已结清&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="mchtSettleOrderList('${mchtCloseApplicationCustom.mchtCode}',-1);">【查看结算金额】</a>
	          		</td>
	          	</tr>
				<tr>
	          		<td>商家保证金情况</td>
	          		<td>
	          			保证金应缴总额：${mchtCloseApplicationCustom.totalAmt}元 已缴总额：${mchtCloseApplicationCustom.payAmt}元 还需补缴：${mchtCloseApplicationCustom.unpayAmt}元
	          		</td>
	          	</tr>
				<tr>
					<td>
						当前需退还的保证金：
					</td>
					<td>
							${mchtCloseApplicationCustom.payAmt}元
					</td>
				</tr>
			</table>
			<br>
			<div>
				<span class="table-title">法务部确认(${mchtCloseApplicationCustom.fwStaffName} <fmt:formatDate value="${mchtCloseApplicationCustom.fwConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)<c:if test="${not empty mchtCloseApplicationCustom.filePath}"><a href="${pageContext.request.contextPath}/file_servelt${mchtCloseApplicationCustom.filePath}" target="_blank">【下载合同】</a></c:if></span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>签署终止合作协议<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">已签署
	          		</td>
	          	</tr>
				<tr>
	          		<td>协议出具日期</td>
	          		<td>
	          			<fmt:formatDate value="${mchtCloseApplicationCustom.agreementIssueDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	          		</td>
	          	</tr>
				<tr>
	          		<td>预计保证金退还日期</td>
	          		<td>
	          			<fmt:formatDate value="${mchtCloseApplicationCustom.depositDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">关店审核(${mchtCloseApplicationCustom.directorStaffName}<fmt:formatDate value="${mchtCloseApplicationCustom.directorConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>确认关店<span style="color: red;">*</span></td>
	          		<td>
	          			<input type="checkbox" checked="checked" disabled="disabled">确认关店
	          		</td>
	          	</tr>
			</table>
			<br>
			<div>
				<span class="table-title">产品部关店(${mchtCloseApplicationCustom.productStaffName}<fmt:formatDate value="${mchtCloseApplicationCustom.productConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>确认关店</td>
	          		<td>店铺已关闭</td>
	          	</tr>
			</table>
			<br>
		<%--	<div>
				<span class="table-title">结算审核(${mchtCloseApplicationCustom.settlementStaffName}<fmt:formatDate value="${mchtCloseApplicationCustom.settlementConfirmDate}" pattern="yyyy-MM-dd HH:mm"/>)</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>当前需退还的保证金</td>
					<td>${mchtCloseApplicationCustom.payAmt}</td>
	          	</tr>
				<tr>
	          		<td>还需支付的货款</td>
	          		<td>${total}</td>
	          	</tr>
				<tr>
	          		<td>结算审核</td>
	          		<td><input type="checkbox" checked="checked" disabled="disabled">可结算</td>
	          	</tr>
			</table>
			<br>--%>
			<div>
				<span class="table-title">操作结算</span>
			</div>
			<table class="gridtable">
				<tr>
	          		<td>当前需退还的保证金</td>
	          		<td><input type="checkbox" id="depositReturnStatus" >已退还</td>
	          	</tr>
				<tr>
	          		<td>退还保证金日期</td>
	          		<td><input type="text" id="depositReturnDate" name="depositReturnDate"></td>
	          	</tr>
				<tr>
	          		<td>还需支付的货款</td>
	          		<td><input type="checkbox" id="needPayStatus" >已结清</td>
	          	</tr>
				<tr>
		          	<td>操作</td>
					<td align="left" class="l-table-edit-td">
						<div id="btnDiv">
							<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
							<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
						</div>
					</td>
				</tr>
			</table>
		</c:if>
		
	</div>
	
	
<div class="video_box" style="position:fixed; width:750px; height:500px; top:5%; left:20%; display: none;border-radius: 2px;" id="returnBackDiv">
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/close.png"></a>
	<div class="panel panel-default" style="margin-bottom:0px;">
		<div class="modal-header">
			<h3 class="modal-title" id="returnBackTitle">
				
			</h3>
		</div>
		<table class="gridtable">
          	<tr>
          		<td class="title">备注</td>
          		<td>
          			<textarea rows="15" cols="50" id="remarks" name="remarks"></textarea>
          		</td>
          	</tr>
	        <tr>
	          	<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="saveRemarks" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="closeDiv();" />
					</div>
				</td>
	        </tr>
        </table>
	 </div>
</div>
<!--弹出div End-->
<div class="black_box" style="display: none;"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
