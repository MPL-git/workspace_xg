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
</style>
<script type="text/javascript">
function checkMoney(money){
	var re = /^(0|[1-9]\d*)(\.\d{1,2})?$/;
	return re.test(money);
}
$(function(){
	var submitting;
	$("#confirmStatus").bind('click',function(){
		if(submitting){
			return false;
		}
		var id = $("#mchtSettleOrderId").val();
		var depositAmount = $("#depositAmount").val();
		var unpayAmt = $("#unpayAmt").text();
		var settleAmount = $("#settleAmount").text();
		if(!checkMoney(depositAmount)){
			commUtil.alertError("请输入正确格式的保证金金额");
			return false;
		}
		if(parseFloat(depositAmount)<0){
			commUtil.alertError("对不起，只能填写零和正数。");
			return false;
		}
		if(parseFloat(unpayAmt)>0){
			if(parseFloat(depositAmount)>parseFloat(unpayAmt)){
				commUtil.alertError("划到保证金金额不能大于保证金未缴金额");
				return false;
			}
		}else{
			if(parseFloat(depositAmount)>0){
				commUtil.alertError("保证金已缴足，无需再划到保证金");
				$("#depositAmount").val(0);
				return false;
			}
		}
		if(parseFloat(depositAmount)>parseFloat(settleAmount)){
			commUtil.alertError("划到保证金金额不能大于本次结算单金额");
			return false;
		}
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtSettleOrder/confirmStatus.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"depositAmount":depositAmount},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("操作成功");
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
</script>
<html>
<body>
	<div class="title-top">
	<input type="hidden" id="mchtSettleOrderId" value="${mchtSettleOrder.id}">
		<table class="gridtable">
          	<tr>
          		<td colspan="1">供应商序号</td>
          		<td>${mchtInfo.mchtCode}</td>
          	</tr>
          	<tr>
          		<td>公司名称</td>
          		<td>${mchtInfo.companyName}</td>
          	</tr>
          	<tr>
          		<td colspan="2">保证金应缴总额：${mchtDeposit.totalAmt}元，&nbsp;已缴总额：${mchtDeposit.payAmt}元，&nbsp;未缴：<span id="unpayAmt">${mchtDeposit.unpayAmt}</span>元</td>
          	</tr>
          	<tr>
          		<td>本次结算单金额</td>
          		<td><span id="settleAmount">${mchtSettleOrder.settleAmount}</span>元</td>
          	</tr>
          	<tr>
          		<td>划到保证金金额</td>
          		<td><input type="text" id="depositAmount">元</td>
          	</tr>
          	<tr>
          		<td colspan="5">
					<div style="margin-left: 40%">
						<input id="confirmStatus" type="button" style="float:left;" class="l-button l-button-test" value="确认"/>
					</div>
					<div style="margin-left: 50%">
						<input type="button" value="取消" class="l-button l-button-test" onclick="frameElement.dialog.close();"/>
					</div>
				</td>
          	</tr>
        </table>
	</div>
	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
