<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<title>添加缴纳记录</title>
<style type="text/css">
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 80px;
height: 80px;
}
.glyphicon-remove{
color:red;
margin-left: 3px;
cursor:pointer;
}
.docs-pictures{
padding: 0px;
}
.docs-pictures li{
position: relative;
margin: 3px;
}


.pic-btn-container{
	width:100%;
	position: absolute;
    top: 0px;
    background:rgba(0, 0, 0, 0.5);
    height:0px;
    z-index: 300;
    overflow: hidden;
    text-align: right;
    padding-right: 3px;
}

</style>
</head>

<body>
<div class="modal-dialog" role="document" style="width:800px;">
	<div class="modal-content">
		<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <span class="modal-title" id="exampleModalLabel">添加缴纳记录</span>
      	</div>
      	<div class="modal-body">
      		<div>
      		<form id="dataFrom" class="form-horizontal" role="form">
      			<input type="hidden" name="proofPic" id="proofPic">
					<table class="panel-table">
						<tbody>
						    <tr>
								<td style="width: 175px;">应缴保证金</td>
								<td colspan="2" class="text-left">
									${contractDeposit}元
								</td>
							</tr>
						    <tr>
								<td>缴纳金额<span class="required">*</span></td>
								<td colspan="2" class="text-left">
									<input type="text" id="amount" name="amount" validate="{required:true}" style="width: 200px;" data-type="money">
								</td>
							</tr>
							<tr>
								<td>您的支付账户户名<span class="required">*</span></td>
								<td colspan="2" class="text-left">
									<input type="text" id="accountName" name="accountName" validate="{required:true}" style="width: 200px;">
									<div style="display: inline-block;color: #999999;">例 支付账户户名：陈小灵</div>
								</td>
							</tr>
							<tr>
								<td>您的支付开户银行<span class="required">*</span></td>
								<td colspan="2" class="text-left">
									<input type="text" id="paymentName" name="paymentName" validate="{required:true}" style="width: 200px;">
									<div style="display: inline-block;color: #999999;">例 支付开户银行：兴业银行厦门分行莲花支行</div>
								</td>
							</tr>
							<tr>
								<td>您的支付开户银行账号<span class="required">*</span></td>
								<td colspan="2" class="text-left">
									<input type="text" id="accountNo" name="accountNo" validate="{required:true}" style="width: 200px;">
									<div style="display: inline-block;color: #999999;">例支付银行账号：622908123888888888</div>
									<div style="display: inline-block;color: #999999;margin-left: 205px;">支付宝转账请填写手机号码方便联系</div>
								</td>
							</tr>
							<tr>
								<td>打款时间</td>
								<td colspan="2" class="text-left">
									<input type="text" validate="{required:true}" id="payDate" name="payDate" data-date-format="yyyy-mm-dd" style="width: 200px;">
								</td>
							</tr>
							<tr>
								<td>交易流水号</td>
								<td colspan="2" class="text-left">
									<input type="text" id="paymentNo" name="paymentNo" style="width: 200px;">
									<div style="display: inline-block;color: #999999;">网银转账 请填写银行转账的交易流水号</div>
									<div style="display: inline-block;color: #999999;margin-left: 205px;">支付宝转账请填写支付宝实名认证的姓名</div>
								</td>
							</tr>
							<tr>
								<td>转账凭证</td>
								<td>
									<div id="pic-container"></div>
								</td>
							</tr>
							<tr>
								<td>备注<span class="required">*</span></td>
								<td colspan="2" class="text-left">
									<textarea rows="5" cols="" style="min-height: 100px;" id="remarks" name="remarks">${mchtCode}，合同保证金。</textarea>
									<div style="display: inline-block;color: #999999;">备注：汇款时请备注${mchtCode}合同保证金</div>
								</td>
							</tr>
							
						</tbody>
					</table>
      		</form>
			</div>
      		<div class="modal-footer">
				<button class="btn btn-info" name="comfirm">提交</button>
				<button class="btn btn-info" data-dismiss="modal" style="background: grey;">取消</button>
      		</div>
      	</div>
	</div>
</div>
	
		
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myWebUploader.js"></script>

<script>
var imageUploader;
$(document).ready(function () {
	
	imageUploader = $("#pic-container").myWebUploader({
		fileNumLimit:1,
        fileSizeLimit:1024*1000*0.5,
        fileSingleSizeLimit:1024*1000*0.5,
        preview:false
    });
	
	imageUploader.on('beforeFileQueued', function( file ) {
		if(file.size>500*1024){
			$("#pic-containerErrorMsg").text("您选择了包含大于500K的图片！");
			return false;
	    }
	});
	
	$('#payDate').datetimepicker(
			{	
    			format: 'yyyy-mm-dd',
    		    minView: 2,
    		    minuteStep:1,
    		    autoclose: true,
    		    language: 'zh-CN' //汉化
    	    }
    );
	
	var submitting;
	$("button[name='comfirm']").on('click',function(){
		if(submitting){
			return false;
		}
		var amount = $.trim($("#amount").val());
		if(amount==""){
			swal("缴纳金额不能为空");
			return false;
		}
		if(!checkMoney(amount)){
			swal("请输入正确格式的缴纳金额");
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
		if(!checkNumber(accountNo)){
			swal("请输入正确格式的支付账户银行账号");
			return false;
		}
		/* var paymentNo = $.trim($("#paymentNo").val());
		if(!paymentNo){
			swal("交易流水号不能为空");
			return false;
		} */
		var remarks = $.trim($("#remarks").val());
		if(!remarks){
			swal("备注不能为空");
			return false;
		}
		var payDate = $("#payDate").val();
		if(!payDate){
			swal("打款时间不能为空");
			return false;
		}
		submitting = true;
		imageUploader.upload();
		imageUploader.on('uploadFinished',function(){
			var imgs = $("#pic-container").getImgPaths();
			$("#proofPic").val(imgs);
			var param = $("#dataFrom").serializeArray();
			$.ajax({
	            method: 'POST',
	            url: '${ctx}/mcht/contract/saveDepositOrder',
	            data: param,
	            dataType: 'json'
	        }).done(function (result) {
	            if (result.returnCode =='0000') {
	            	swal("提交成功");
	            	location.reload();
	            }else{
	            	submitting = false;
	            	swal("提交失败，请稍后重试");
	            }
	        });
		});
	});
	
	$("#payDate").keydown(function(event){
		return false;
	});
});
</script>
</body>
</html>
