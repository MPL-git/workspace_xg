<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>缴纳保证金</title>
</head>

<body>
<div class="modal-dialog" role="document" style="width:1000px;">
	<div class="modal-content">
		<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <span class="modal-title" id="exampleModalLabel">缴纳保证金</span>
      	</div>
      	<div class="modal-body">
      		<div class="x_panel container-fluid" style="width: 940px;">
				<div class="search-container container-fluid"
					style="padding:10px 10px;">
						<form class="form-horizontal" id="saveForm">
							<div class="form-group">
								<label for="" class="col-md-2">保证金余额：</label>
								<div class="col-md-2 search-condition">
									 <span>${mchtDeposit.payAmt}</span>元
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-md-2">需补缴保证金：</label>
								<div class="col-md-5">
									   <span>${mchtDeposit.unpayAmt}</span>元
									   <a href="javascript:;" class="btn btn-info" onclick="depositOrderIndex();">缴款记录</a>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-md-2">缴纳金额：</label>
								<div class="col-md-5">
									   <input type="text"  id="amount" name="amount" data-type="money">元
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-md-2">充值方式：</label>
							</div>
							<div class="form-group">
								<input type="radio" name="paymentType" value="1" checked="checked"><a href="javascript:;" class="btn btn-info" id="Alipay">支付宝支付</a><input type="radio" name="paymentType" value="3"><a href="javascript:;" class="btn btn-info" id="onlineBanking">网银支付</a>
							</div>
							<div class="form-group">
								<label for="" class="col-md-9">支付金额：<span style="color: red;" id="payAmount">0.00</span>元<span style="color: red;">&nbsp;&nbsp;&nbsp;(转账金额必须与支付金额一致，否则可能会付款失败。）</span></label>
							</div>
							<div id="AlipayDiv">
							<div class="form-group">
								<label for="" class="col-md-7">
									您可以手动登录 支付宝 直接转账到支付宝账号
									<c:forEach items="${aliAccountList}" var="aliAccount">
										${aliAccount.accountNo},
									</c:forEach>
									 或者直接扫描
									右方二维码进行支付，然后在下方添加充值的 交易号 即可。
								</label>
							</div>
							<div class="panel panel-default">
							    <div class="panel-body">
							    	<div class="info-row">
							    		<span style="font-size: medium;font-weight: bolder;">支付宝交易号&nbsp;：</span>
							    		<input type="text" id="paymentNo" name="paymentNo" style="width: 200px;">
							    		<img src="${ctx}/static/images/ZFB.png" style="float: right;width: 80px;height: 80px;">
							    	</div>
								    <div class="info-row">
								    	<a href="javascript:;" class="btn btn-info" name="comfirm">提交</a>
								    </div>
							    </div>
							</div>
							<div class="form-group">
								<div class="info-row">
									<a href="javascript:;">◇如何获取支付宝交易号？</a>
								</div>
							</div>
							</div>
							<div id="onlineBankingDiv" style="display: none;">
								<div class="form-group">
									<label for="" class="col-md-7">对公账户：</label>		
								</div>
								<div class="form-group">
									<c:forEach items="${publicBankAccountList}" var="publicBankAccount">
										<div class="info-row"> <span class="info-title"><input type="radio" name="accountId" value="${publicBankAccount.id}">公司名称&nbsp;:</span><span  class="info-cotent">${publicBankAccount.accountName}</span></div>
										<div class="info-row" style="margin-left: 30px;"> <span class="info-title">开户银行&nbsp;:</span><span  class="info-cotent">${publicBankAccount.paymentName}</span></div>
										<div class="info-row" style="margin-left: 30px;"> <span class="info-title">对公账号&nbsp;:</span><span  class="info-cotent">${publicBankAccount.accountNo}</span></div>
									</c:forEach>
								</div>
								
								<div class="form-group">
									<label for="" class="col-md-7">私人账户：</label>		
								</div>
								<div class="form-group">
									<c:forEach items="${privateBankAccountList}" var="privateBankAccount">
										<div class="info-row"> <span class="info-title"><input type="radio" name="accountId" value="${privateBankAccount.id}">户名&nbsp;:</span><span  class="info-cotent">${privateBankAccount.accountName}</span></div>
										<div class="info-row" style="margin-left: 30px;"> <span class="info-title">开户银行&nbsp;:</span><span  class="info-cotent">${privateBankAccount.paymentName}</span></div>
										<div class="info-row" style="margin-left: 30px;"> <span class="info-title">银行账号&nbsp;:</span><span  class="info-cotent">${privateBankAccount.accountNo}</span></div>
									</c:forEach>
								</div>
								<div class="panel panel-default">
								    <div class="panel-body">
								    	<form class="form-horizontal" role="form">
											  <div class="form-group">
											    <label for="" class="col-sm-3">支付账户户名：</label>
											    <div class="col-sm-6">
											      <input type="text"  id="accountName" name="accountName" class="form-control">
											    </div>
											  </div>
											  <div class="form-group">
											    <label for="" class="col-sm-3">支付账户开户银行：</label>
											    <div class="col-sm-8">
											      <input type="text"  id="paymentName" name="paymentName" class="form-control">
											    </div>
											  </div>
											  <div class="form-group">
											    <label for="" class="col-sm-3">支付账户银行账号：</label>
											    <div class="col-sm-8">
											      <input type="text"  id="accountNo" name="accountNo" class="form-control">
											    </div>
											  </div>
											  <div class="form-group">
											    <label for="" class="col-sm-3">打款时间：</label>
											    <div class="col-sm-3 " >
													<input class="form-control datePicker" type="text"  id="payDate" name="payDate" data-date-format="yyyy-mm-dd HH:mm:ss">
												</div>
											  </div>
											  <div class="form-group">
											    <div class="col-sm-offset-3 col-sm-10">
											      <a href="javascript:;" class="btn btn-info" name="comfirm">提交</a>
											    </div>
											  </div>
										</form>
								    </div>
								</div>
							</div>
						</form>
				</div>
			</div>
      	</div>
	</div>
</div>
	
		
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
$(document).ready(function () {
	
	$('.datePicker').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd HH:mm:ss", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
    	    }
    );
	
	$("#amount").keyup(function(){
		if($(this).val()){
			$("#payAmount").text(formatMoney($(this).val(),2));
		}else{
			$("#payAmount").text("");
		}
	});
	
	$("input[name='paymentType']").on('click',function(){
		var paymentType = $(this).val();
		if(paymentType == "1"){//支付宝
			$("#AlipayDiv").show();
			$("#onlineBankingDiv").hide();
		}else if(paymentType == "3"){//网银
			$("#onlineBankingDiv").show();
			$("#AlipayDiv").hide();
		}
	});
	var submitting;
	$("a[name='comfirm']").on('click',function(){
		if(submitting){
			return false;
		}
		var amount = $.trim($("#amount").val());
		if(amount==""){
			swal("缴纳金额不能为空");
			return false;
		}
		var paymentType = $("input[name='paymentType']:checked").val();
		if(paymentType=="1"){
			var paymentNo = $.trim($("#paymentNo").val());
			if(paymentNo == ""){
				swal("支付宝交易号不能为空");
				return false;
			}
		}else if(paymentType=="3"){
			if($("input[name='accountId']:checked").length == 0){
				swal("请选择对公账户或私人账户");
				return false;
			}
			if($("input[name='paymentType']:checked").length == 0){
				swal("请选择账户");
				return false;
			}
			var accountName = $.trim($("#accountName").val());
			if(accountName == ""){
				swal("支付账户户名不能为空");
				return false;
			}
			var paymentName = $.trim($("#paymentName").val());
			if(paymentName == ""){
				swal("支付账户开户银行不能为空");
				return false;
			}
			var accountNo = $.trim($("#accountNo").val());
			if(accountNo == ""){
				swal("支付账户银行账号不能为空");
				return false;
			}
			var payDate = $.trim($("#payDate").val());
			if(payDate == ""){
				swal("打款时间不能为空");
				return false;
			}
		}
		var param = $("#saveForm").serializeArray();
		submitting = true;
		$.ajax({
            method: 'POST',
            url: '${ctx}/mchtDeposit/saveDepositOrder',
            data: param,
            dataType: 'json'
        }).done(function (result) {
        	submitting = false;
            if (result.returnCode =='0000') {
            	depositOrderIndex();
            }
        });
	});
	
	
});

function depositOrderIndex(){
		$.ajax({
	        url: "${ctx}/mchtDeposit/depositOrderIndex", 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	            $("#viewModal").modal();
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});

}
</script>
</body>
</html>
