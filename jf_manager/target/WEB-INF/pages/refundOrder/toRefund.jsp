<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
		$("#refund").bind('click',function(){
			var refundOrderId = $.trim($("#refundOrderId").val());
			var refundMethod = $("#refundMethod").val();
			var refundRegisterDate = $("#refundRegisterDate").val();
			var remarksOne = $("#remarks-one").val();
			var remarksTwo = $("#remarks-two").val();
			var refundCode = $("#refundCode").val();
			var remarks = "";
			if(refundMethod == '1') {
				remarks = remarksOne;
			}else if(refundMethod == '2') {
				remarks = remarksTwo;
			}
			if(!refundCode){
				commUtil.alertSuccess("退款编号不能为空");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/refundOrder/refund.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":refundOrderId,refundMethod:refundMethod,refundRegisterDate:refundRegisterDate,remarks:remarks,refundCode:refundCode},
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
		});
		$("#refundPaymentId").bind('change',function(){
			if($(this).val() == "-1"){
				$("#refundMethod").find("option[value='2']").attr("selected","selected");
			}else if($(this).val() != ""){
				$("#refundMethod").find("option[value='1']").attr("selected","selected");
			}
			remarksFun();
		});
		$("#refundMethod").bind('change',function(){
			if($(this).val() == "1"){
				$("#refundPaymentId").find("option[value='-1']").prev().attr("selected","selected");
			}else{
				$("#refundPaymentId").find("option[value='-1']").attr("selected","selected");
			}
			remarksFun();
		});
		remarksFun();
		
});

function remarksFun(){
	if($("#refundMethod").val() == '1') {
		$(".remarks-td-one").show();
		$(".remarks-td-two").hide();
	}else if($("#refundMethod").val() == '2') {
		$(".remarks-td-one").hide();
		$(".remarks-td-two").show();
	}
}

</script>
<html>
<body>
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/refundOrder/register.shtml">
			<input type="hidden" id="refundOrderId" value="${refundOrder.id}">
			<table class="gridtable">
           	<tr>
               <td class="title">应退金额</td>
               <td colspan="2" align="left" class="l-table-edit-td">
	               <span style="color: red;">${refundOrder.refundAmount}</span>&nbsp;&nbsp;&nbsp;&nbsp;该明细ID：${orderDtlId}，有${count}笔退款任务
	               <c:if test="${count gt 1}">
		               <span style="color: red;">【异常】</span>
	               </c:if>
               </td>
			</tr> 
	        <tr>
               <td class="title">退款渠道<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<select id="refundPaymentId" name="refundPaymentId">
               			<c:if test="${paymentId eq '1'}">
	               			<option value="1" selected="selected">支付宝</option>
               			</c:if>
               			<c:if test="${paymentId eq '2'}">
	               			<option value="2" selected="selected">微信支付</option>
               			</c:if>
               			<c:if test="${paymentId eq '3'}">
	               			<option value="3" selected="selected">银联在线</option>
               			</c:if>
               			<option value="-1">其他</option>
               		</select>
               </td>
	        </tr>
	        <tr>
               <td class="title">退款方式<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<select id="refundMethod" name="refundMethod">
               			<c:forEach var="refundMethod" items="${refundMethods}">
               				<option value="${refundMethod.statusValue}" <c:if test="${refundMethod.statusValue eq '1'}">selected="selected"</c:if>>${refundMethod.statusDesc}</option>
               			</c:forEach>
               		</select>
               </td>
	        </tr>
	        <tr>
               <td class="title">退款编号<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" value="${refundOrder.refundCode}" id="refundCode" name="refundCode">
               </td>
	        </tr>
	        <tr>
               <td class="title">退款登记日期</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="refundRegisterDate" name="refundRegisterDate" value="${nowDate }" readonly="readonly"/>
	               	<script type="text/javascript">
						/* $(function(){
							$("#refundRegisterDate").ligerDateEditor({
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						}); */
					</script>
				</td>
	        </tr>
	        <tr>
               <td class="title">备注</td>
               <td colspan="2" align="left" class="l-table-edit-td remarks-td-one" >
               		<textarea rows="6" cols="50" id="remarks-one" name="remarks-one">退款操作完成，金额将原路返回到客户支付账号上，实际到账时间以在线支付工具和银行结算时间为准。请耐心等待。同时售后完成。</textarea>
               </td>
               <td colspan="2" align="left" class="l-table-edit-td remarks-td-two" >
               		<textarea rows="6" cols="50" id="remarks-two" name="remarks-two">退款操作完成，实际到账时间以银行结算时间为准。请耐心等待。同时售后完成。</textarea>
               </td>
	        </tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="refund" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
