<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<html>
<body>
<form method="post" id="form" name="form" >
			<input type="hidden" id="refundOrderId" value="${refundOrder.id}">
			<table class="gridtable">
           	<tr>
               <td class="title">应退金额</td>
               <td colspan="2" align="left" class="l-table-edit-td">
	               <span style="color: red;">${refundOrder.refundAmount}</span>
               </td>
			</tr> 
	        <tr>
               <td class="title">退款渠道<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<select>
	               		<c:if test="${refundOrder.refundMethod eq '1'}">
	               			<c:if test="${paymentId eq '1'}">
		               			<option value="1">支付宝</option>
	               			</c:if>
	               			<c:if test="${paymentId eq '2'}">
		               			<option value="2">微信支付</option>
	               			</c:if>
	               			<c:if test="${paymentId eq '3'}">
		               			<option value="3">银联在线</option>
	               			</c:if>
	               		</c:if>	
	               		<c:if test="${refundOrder.refundMethod eq '2'}">
		               		<option value="-1">其他</option>
	               		</c:if>
               		</select>
               </td>
	        </tr>
	        <tr>
               <td class="title">退款方式<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<select>
               			<option>${refundMethodDesc}</option>
               		</select>
               </td>
	        </tr>
	        <tr>
               <td class="title">支付凭证（交易号）</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" value="${refundOrder.refundReqNo}" readonly="readonly">
               </td>
	        </tr>
	        <tr>
               <td class="title">退款编号<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" value="${refundOrder.refundCode}" readonly="readonly">
               </td>
	        </tr>
	        <tr>
               <td class="title">退款登记日期</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd" value='${refundOrder.refundRegisterDate}'/>"/>
				</td>
	        </tr>
	        <tr>
               <td class="title">备注</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<textarea rows="6" cols="50" readonly="readonly">${refundOrder.remarks}</textarea>
               </td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
