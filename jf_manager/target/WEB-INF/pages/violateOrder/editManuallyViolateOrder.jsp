<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
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
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	<c:if test="${not empty violateOrder && violateOrder.id gt 0}">
		var violateOrder = ${violateOrder};
		var violateType = violateOrder.violateType;
		$("#violateType").find("option[value='"+violateType+"']").attr("selected","selected");
	</c:if>
	
	$("#violateType").bind('change',function(){
		var violateType = $(this).val();
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/getViolateActions.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"violateType":violateType},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var violateActions = data.violateActions;
					var optionArray = [];
					optionArray.push('<option value="">请选择</option>');
					for(var i=0;i<violateActions.length;i++){
						var statusValue = violateActions[i].statusValue;
						var statusDesc = violateActions[i].statusDesc;
						optionArray.push('<option value="'+statusValue+'">'+statusDesc+'</option>');
					}
					$("#violateAction").html(optionArray.join(""));
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	
	$("#violateAction").bind('change',function(){
		var violateType = $("#violateType").val();
		var violateAction = $(this).val();
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/getViolatePunishStandards.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"violateType":violateType,"violateAction":violateAction},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var violatePunishStandards = data.violatePunishStandards;
					var optionArray = [];
					optionArray.push('<option value="">请选择</option>');
					for(var i=0;i<violatePunishStandards.length;i++){
						var content = violatePunishStandards[i].content;
						if(content.length>30){
							shortContent=content.slice(0,30)+"...";
							optionArray.push('<option content="'+content+'"  integralCompensateRate="'+violatePunishStandards[i].integralCompensateRate+'" integralCompensateModel="'+violatePunishStandards[i].integralCompensateModel+'" minDeductionQuota="'+violatePunishStandards[i].minDeductionQuota+'" paymentBreachModel="'+violatePunishStandards[i].paymentBreachModel+'" punishStandard="'+violatePunishStandards[i].punishStandard+'" breachDeductionQuota="'+violatePunishStandards[i].breachDeductionQuota+'" jifenIntegralDesc="'+violatePunishStandards[i].jifenIntegralDesc+'" jifenIntegral="'+violatePunishStandards[i].jifenIntegral+'" punishOther="'+violatePunishStandards[i].punishOther+'" complainFlag="'+violatePunishStandards[i].complainFlag+'" enableHours="'+violatePunishStandards[i].enableHours+'">'+shortContent+'</option>');
						}else{
						optionArray.push('<option content="'+content+'" integralCompensateRate="'+violatePunishStandards[i].integralCompensateRate+'" integralCompensateModel="'+violatePunishStandards[i].integralCompensateModel+'" minDeductionQuota="'+violatePunishStandards[i].minDeductionQuota+'" paymentBreachModel="'+violatePunishStandards[i].paymentBreachModel+'" punishStandard="'+violatePunishStandards[i].punishStandard+'" breachDeductionQuota="'+violatePunishStandards[i].breachDeductionQuota+'" jifenIntegralDesc="'+violatePunishStandards[i].jifenIntegralDesc+'" jifenIntegral="'+violatePunishStandards[i].jifenIntegral+'" punishOther="'+violatePunishStandards[i].punishOther+'" complainFlag="'+violatePunishStandards[i].complainFlag+'" enableHours="'+violatePunishStandards[i].enableHours+'"><div style="width:300px">'+content+'<div></option>');
						}
					}
					$("#punishStandardContent").html(optionArray.join(""));
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	
	$("#punishStandardContent").bind('change',function(){
		
		var option = $(this).find("option:selected")[0];
		var content = $(option).attr("content");
		var punishStandard = $(option).attr("punishStandard");
		var jifenIntegralDesc = $(option).attr("jifenIntegralDesc");
		var jifenIntegral = $(option).attr("jifenIntegral");
		var punishOther = $(option).attr("punishOther");
		var complainFlag = $(option).attr("complainFlag");
		var enableHours = $(option).attr("enableHours");
		var breachDeductionQuota = $(option).attr("breachDeductionQuota");
		var paymentBreachModel = $(option).attr("paymentBreachModel");
		var minDeductionQuota = $(option).attr("minDeductionQuota");
		if(minDeductionQuota==null || minDeductionQuota=="null"){
			minDeductionQuota=0;
		}
		var integralCompensateModel = $(option).attr("integralCompensateModel");
		var integralCompensateRate = $(option).attr("integralCompensateRate");
		$("#paymentBreachModel").val(paymentBreachModel);
		$("#breachDeductionQuota").val(breachDeductionQuota);
		$("#minDeductionQuota").val(minDeductionQuota);
		$("#integralCompensateModel").val(integralCompensateModel);
		$("#integralCompensateRate").val(integralCompensateRate);
		$("#content").val(content);
		$("#jifenIntegralDescTd").html(jifenIntegralDesc);
		if(paymentBreachModel=="0"){
			$("#money").val(breachDeductionQuota);
			$("#money").removeAttr("readonly");
		}
		var payAmount = $("#payAmount").val();
		if(paymentBreachModel=="1"){
			var payMoney= parseFloat(payAmount*breachDeductionQuota).toFixed(2);
			if(payMoney<minDeductionQuota){
				$("#money").val(minDeductionQuota);
			}else{
				$("#money").val(payMoney);
			}
			
			$("#money").attr("readonly","readonly");
			
		} 
		$("#punishStandardTd").text(punishStandard);
		$("#jifenIntegralDesc").val(jifenIntegralDesc);

		if(integralCompensateModel=="0"){
			$("#jifenIntegralShow").text(jifenIntegral);
			$("#jifenIntegral").val(jifenIntegral);
		}
		if(integralCompensateModel=="1"){
			var jifenIntegralRatio = parseFloat(payAmount * integralCompensateRate * 100).toFixed(0);
			$("#jifenIntegralShow").text(jifenIntegralRatio);
			$("#jifenIntegral").val(jifenIntegralRatio);
		}
		if(integralCompensateModel=="2"){
			$("#jifenIntegralShow").text(0);
			$("#jifenIntegral").val(0);
		}
		
		$("#punishMeans").val(punishOther);
		if(complainFlag == "2"){
			$("input[name='status']").eq(0).attr("checked","checked");
			$("#enableHours").find("option").eq(0).attr("selected","selected");
		}else{
			$("input[name='status']").eq(1).attr("checked","checked");
			$("#enableHours").find("option").each(function(){
				if($(this).val()==enableHours){
					$(this).attr("selected","selected");
				}
			});
		}
		
	});
	$("#subOrderCode").bind('blur',function(){
		var subOrderCode = $("#subOrderCode").val();
		if(!subOrderCode){
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/getMchtInfo.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"subOrderCode":subOrderCode},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var mchtInfos = data.mchtInfos;
					if(mchtInfos && mchtInfos.length>0){
						$("#subOrderId").val(mchtInfos[0].subOrderId);
						$("#mchtCode").val(mchtInfos[0].mchtCode);
						$("#mchtInfo").text("公司名称："+mchtInfos[0].companyName+"，店铺名："+mchtInfos[0].shopName);
						$("#payAmount").val(mchtInfos[0].payAmount);
					}else{
						$("#subOrderId").val("");
						commUtil.alertError("子订单号有误。");
					}
				}else{
					$.ligerDialog.error(data.returnMsg);
				}

			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	$("#mchtCode").bind('blur',function(){
		var mchtCode = $("#mchtCode").val();
		if(!mchtCode){
			commUtil.alertError("商家序号不能为空");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/getMchtInfoByMchtCode.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"mchtCode":mchtCode},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var mchtInfos = data.mchtInfos;
					if(mchtInfos.length>0){
						$("#mchtId").val(mchtInfos[0].id);
						$("#mchtInfo").text("公司名称："+mchtInfos[0].companyName+"，店铺名："+mchtInfos[0].shopName);
					}else{
						$("#mchtId").val("");
						commUtil.alertError("商家序号有误。");
					}
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	
	$("#saveViolateOrder").bind('click',function(){
		var mchtCode = $("#mchtCode").val();
		if(mchtCode == ""){
			commUtil.alertError("商家序号不能为空");
			return false;
		}
		var violateType = $("#violateType").val();
		var violateAction = $("#violateAction").val();
		var content = $.trim($("#content").val());
		var status = $("input[name='status']:checked").val();
		var enableHours = $("#enableHours").val();
		if(!violateType || !violateAction){
			commUtil.alertError("请选择违规行为");
			return false;
		}
		if(!content){
			commUtil.alertError("违规详情不能为空");
			return false;
		}
		if(!status){
			commUtil.alertError("请选择是否可申诉");
			return false;
		}
		if(status == "1"){
			if(!enableHours){
				commUtil.alertError("请选择可申诉时长");
				return false;
			}
		}
		var money = $("#money").val();
		var punishMeans = $("#punishMeans").val();
		if(!money && !punishMeans){
			commUtil.alertError("处罚金额、其它处罚方式 不可同时为空。");
			return false;
		}
		var subOrderCode = $("#subOrderCode").val();
		var jifenIntegral = $("#jifenIntegral").val();
		var jifenIntegralDesc = $("#jifenIntegralDesc").val();
		if(jifenIntegral){
			if(jifenIntegral<0){
				commUtil.alertError("积分请输入正数");
				return false;
			}
		}
		if(!subOrderCode && Number(jifenIntegral)>0){
			commUtil.alertError("子订单为空，不能补偿积分。");
			return false;
		}
		if(subOrderCode){
			var subOrderCodeError = true;
			$.ajax({
				url : "${pageContext.request.contextPath}/violateOrder/getMchtInfo.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"subOrderCode":subOrderCode},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var mchtInfos = data.mchtInfos;
						if(mchtInfos && mchtInfos.length>0){
							$("#subOrderId").val(mchtInfos[0].subOrderId);
							$("#mchtCode").val(mchtInfos[0].mchtCode);
							$("#mchtInfo").text("公司名称："+mchtInfos[0].companyName+"，店铺名："+mchtInfos[0].shopName);
							subOrderCodeError = false;
						}else{
							$("#subOrderId").val("");
							subOrderCodeError = true;
						}
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
			if(subOrderCodeError){
				commUtil.alertError("子订单号有误。");
				return false;
			}
		}
		if(mchtCode){
			var mchtCodeError = true;
			$.ajax({
				url : "${pageContext.request.contextPath}/violateOrder/getMchtInfoByMchtCode.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"mchtCode":mchtCode},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var mchtInfos = data.mchtInfos;
						if(mchtInfos.length>0){
							$("#mchtId").val(mchtInfos[0].id);
							$("#mchtInfo").text("公司名称："+mchtInfos[0].companyName+"，店铺名："+mchtInfos[0].shopName);
							mchtCodeError = false;
						}else{
							$("#mchtId").val("");
							mchtCodeError = true;
						}
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
			if(mchtCodeError){
				commUtil.alertError("商家序号有误。");
				return false;
			}
		}
		
		var pictures = [];
		var lis = $(".upload_image_list").find("li");
		lis.each(function(index, item) {
			var path = $("img", item).attr("path");
			var def = ($(".def", item).length == 1 ? "1" : "0");
			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
			pictures.push(pic);
		});
		if(pictures.length > 0){
			$("#remarkPics").val(JSON.stringify(pictures));
		}

        //当选择挂起时挂起原因必填
        var suspendedStatus = $("input[name='suspendedStatus']:checked").val();
        var suspendedReason = $("#suspendedReason").val();
        if(suspendedStatus == "2"){
            if(!suspendedReason){
                commUtil.alertError("请填写挂起原因");
                return false;
            }
        }

		var param = $("#violateOrderForm").serialize();
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/saveManuallyViolateOrder.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : param,
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
                    commUtil.alertSuccess("操作成功");
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

	});

	//当默认进入“添加人工发起违规”页面，挂起原因为隐藏状态
    $("input[name='suspendedStatus']").bind('click',function () {
        var suspendedStatus = $(this).val();
        if (suspendedStatus == "1"){
            $("#suspendedReasonTr").hide();
        }else {
            $("#suspendedReasonTr").show();
		}
    });
	
});

function viewerPic(imgPath){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
}

function ajaxFileUpload() {
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: "remarkPic",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				uploadImageList.addImage("${pageContext.request.contextPath}/file_servelt", result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}

function fileUpload() {
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: "file",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#enclosure").val(result.FILE_PATH);
				$("#fileInfoDesc").text("已上传文件："+result.FILE_PATH.substring(result.FILE_PATH.lastIndexOf("/")+1));
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});

}
</script>
<html>
<body>
<form id="violateOrderForm" method="post" action="${pageContext.request.contextPath}/violateOrder/addSave.shtml">
	<input type="hidden" id="id" name="id" value="${violateOrder.id}">
	<input type="hidden" id="subOrderId" name="subOrderId" value="${violateOrder.subOrderId}">
	<input type="hidden" id="mchtId" name="mchtId" value="${violateOrder.mchtId}">
	<input type="hidden" id="auditStatus" name="auditStatus" value="${auditStatus}">
	<input type="hidden" id="jifenIntegralDesc" name="jifenIntegralDesc" value="${violateOrder.jifenIntegralDesc}">
	<input type="hidden" id="payAmount" name="payAmount" value="${violateOrder.payAmount}">
	<input type="hidden" id="paymentBreachModel" name="paymentBreachModel" value="${violatePunishStandard.paymentBreachModel}">
	<input type="hidden" id="breachDeductionQuota" name="breachDeductionQuota" value="${violatePunishStandard.breachDeductionQuota}">
	<input type="hidden" id="minDeductionQuota" name="minDeductionQuota" value="${violatePunishStandard.minDeductionQuota}">
	<input type="hidden" id="integralCompensateModel" name="integralCompensateModel" value="${violatePunishStandard.integralCompensateModel}">
	<input type="hidden" id="integralCompensateRate" name="integralCompensateRate" value="${violatePunishStandard.integralCompensateRate}">
	
	<table class="gridtable">
		<tr>
			<td class="title">子订单号</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="subOrderCode" name="subOrderCode" value="${subOrderCode}" style="width: 300px;height: 18px;" />
			</td>
		</tr>
		<tr>
			<td class="title">商家序号 <span style="color: red;">*</span></td>
			<td align="left" class="l-table-edit-td"><input type="text" id="mchtCode" name="mchtCode" value="${mchtCode}" style="width: 300px;height: 18px;" /><span id="mchtInfo"></span></td>
		</tr>
		<tr>
			<td class="title">违规行为<span style="color: red;">*</span></td>
			<td align="left" class="l-table-edit-td">
				<select style="width: 120px;" id="violateType" name="violateType">
					<option value="">请选择</option>
					<c:forEach var="violateType" items="${violateTypeList}">
						<option value="${violateType.statusValue}" <c:if test="${violateType.statusValue eq defaultViolateType}">selected="selected"</c:if>>${violateType.statusDesc}</option>
					</c:forEach>
				</select>
				<select style="width: 120px;" id="violateAction" name="violateAction">
					<option value="">请选择</option>
					<c:if test="${not empty violateActions}">
	               		<c:forEach var="violateAction" items="${violateActions}">
	               			<option value="${violateAction.statusValue}" <c:if test="${violateAction.statusValue eq defaultViolateAction}">selected="selected"</c:if> <c:if test="${violateOrder.violateAction eq violateAction.statusValue}">selected="selected"</c:if>>${violateAction.statusDesc}</option>
	               		</c:forEach>
					</c:if>
				</select>
				<select style="width: 120px;" id="punishStandardContent" name="punishStandardContent">
					<option value="">请选择</option>
					<c:if test="${not empty violatePunishStandards}">
						<c:forEach var="violatePunishStandard" items="${violatePunishStandards}">
	               			<option punishStandard="${violatePunishStandard.punishStandard}" <c:if test="${defaultContent eq violatePunishStandard.content}">selected="selected"</c:if> jifenIntegral="${violatePunishStandard.jifenIntegral}" punishOther="${violatePunishStandard.punishOther}" complainFlag="${violatePunishStandard.complainFlag}" enableHours="${violatePunishStandard.enableHours}">${violatePunishStandard.content}</option>
	               		</c:forEach>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td class="title">违规详情<span style="color: red;">*</span></td>
			<td align="left" class="l-table-edit-td">
				<textarea id="content" name="content" rows="5" class="textarea" cols="100">${violateOrder.content}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title">支付违约金标准<span style="color: red;">*</span></td>
			<td align="left" class="l-table-edit-td" id="punishStandardTd">

			</td>
		</tr>
		<tr>
			<td class="title">违约金金额<span style="color: red;">*</span></td>
			<td align="left" class="l-table-edit-td"><input type="text" id="money" name="money" value="${violateOrder.money}"/>元</td>
		</tr>
		<tr>
			<td class="title">补偿用户积分说明</td>
			<td align="left" class="l-table-edit-td" id="jifenIntegralDescTd">
				
			</td>
		</tr>
		<tr>
			<td class="title">补贴积分<span style="color: red;">*</span></td>
			<td align="left" class="l-table-edit-td">
				<div id="jifenIntegralShow" name="jifenIntegralShow" value="${violateOrder.jifenIntegral}"></div>
				<input  type="hidden" id="jifenIntegral" name="jifenIntegral" value="${violateOrder.jifenIntegral}"/>
			</td>
		</tr>
		<tr>
			<td class="title">其他处理方式</td>
			<td align="left" class="l-table-edit-td">
				<textarea id="punishMeans" name="punishMeans" rows="5" class="textarea" cols="100">${violateOrder.punishMeans}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title">是否可申诉<span style="color: red;">*</span> </td>
			<td align="left" class="l-table-edit-td">
				<input type="radio" name="status" value="2" <c:if test="${violateOrder.status eq '2'}">checked="checked"</c:if>/>不可申诉
				<input type="radio" name="status" value="1" <c:if test="${violateOrder.status eq '1'}">checked="checked"</c:if>/>可申诉
				可申诉时长:
				<select id="enableHours" name="enableHours">
               		<option value="">请选择</option>
               		<option value="12小时" <c:if test="${violateOrder.enableHours eq '12小时'}">selected="selected"</c:if>>12小时</option>
               		<option value="24小时" <c:if test="${violateOrder.enableHours eq '24小时'}">selected="selected"</c:if>>24小时</option>
               		<option value="48小时" <c:if test="${violateOrder.enableHours eq '48小时'}">selected="selected"</c:if>>48小时</option>
               		<option value="72小时" <c:if test="${violateOrder.enableHours eq '72小时'}">selected="selected"</c:if>>72小时</option>
               	</select>
			</td>
		</tr>
		<tr>
			<td class="title">附件（给商家）</td>
			<td colspan="2" align="left" class="l-table-edit-td">
				<div style="float: left;height: 45px;margin: 10px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="file" name="file" onchange="fileUpload();" />
					<a href="javascript:void(0);" style="width:30%;">上传文件</a>
					<br>
					<span id="fileInfoDesc"></span>
	    		</div>
	    		<input id="enclosure" name="enclosure" type="hidden" value="${enclosure}">
             </td>
		</tr>
		<tr>
			<td class="title">平台内部备注</td>
			<td align="left" class="l-table-edit-td">
				<textarea id="platformRemarks" name="platformRemarks" rows="5" class="textarea" cols="100">${violateOrder.platformRemarks}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title">平台内部凭证</td>
			<td colspan="3">
				<t:imageList name="pictures" list="${remarkPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
				<div style="float: left;height: 105px;margin: 10px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="remarkPic" name="file" onchange="ajaxFileUpload();" />
					<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    		</div>
	    		<input id="remarkPics" name="remarkPics" type="hidden" value="${remarkPics}">
			</td>
		</tr>

		<tr>
			<td class="title">是否挂起</td>
			<td align="left" class="l-table-edit-td">
				<span style="margin-left: 10PX;">
					<label><input type="radio" name="suspendedStatus" value="1" checked="checked"/>否</label>
				</span>
				<span style="margin-left: 20PX;">
					<label><input type="radio" name="suspendedStatus" value="2" />是</label>
				</span>
			</td>
		</tr>

		<tr id="suspendedReasonTr" style="display: none">
			<td class="title">挂起原因<span style="color: red;">*</span></td>
			<td align="left" class="l-table-edit-td">
				<textarea id="suspendedReason" name="suspendedReason" rows="5" class="textarea" cols="100"></textarea>
			</td>
		</tr>

	</table>	
	<div class="table-title" id="" style="margin-top: 15px;">
		<div style="padding-top: 10px;"><input type="button" style="cursor: pointer;width: 120px;height: 35px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" id="saveViolateOrder"/></div>
	</div>
</form>		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
