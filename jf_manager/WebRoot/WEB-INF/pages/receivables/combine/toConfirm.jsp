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
<script type="text/javascript">
var viewerPictures;
$(function(){
		$("#confirm").bind('click',function(){
			var saveFinancialStatus = $("input[name='saveFinancialStatus']:checked").val();
			if(!saveFinancialStatus){
				commUtil.alertError("请选择状态");
				return false;
			}
			$("#saveFinancialStatus").val(saveFinancialStatus);
			$.ajax({
				url : "${pageContext.request.contextPath}/receivables/combine/confirm.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : $("#form").serialize(),
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("确认成功");
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
});
</script>
<html>
<body>
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/receivables/combine/confirm.shtml">
			<input type="hidden" id="combineOrderId" name="id" value="${id}">
			<input type="hidden" id="paymentId" name="paymentId" value="${paymentId}">
			<input type="hidden" id="financialStatus" name="financialStatus" value="${financialStatus}">
			<input type="hidden" id="combineOrderCode" name="combineOrderCode" value="${combineOrderCode}">
			<input type="hidden" id="paymentNo" name="paymentNo" value="${paymentNo}">
			<input type="hidden" id="financialNo" name="financialNo" value="${financialNo}">
			<input type="hidden" id="create_date_begin" name="create_date_begin" value="${create_date_begin}">
			<input type="hidden" id="create_date_end" name="create_date_end" value="${create_date_end}">
			<input type="hidden" id="pay_date_begin" name="pay_date_begin" value="${pay_date_begin}">
			<input type="hidden" id="pay_date_end" name="pay_date_end" value="${pay_date_end}">
			<input type="hidden" id="register_date_begin" name="register_date_begin" value="${register_date_begin}">
			<input type="hidden" id="register_date_end" name="register_date_end" value="${register_date_end}">
			<input type="hidden" id="saveFinancialStatus" name="saveFinancialStatus" value="">
			<table class="gridtable">
           	<tr>
               <td class="title">状态</td>
               <td colspan="2" align="left" class="l-table-edit-td"><input type="radio" name="saveFinancialStatus" value="0">未处理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="saveFinancialStatus" value="1"/>登记&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="saveFinancialStatus" value="2"/>确认</td>
			</tr>
			<c:if test="${batch eq 0}"> 
	        <tr>
               <td class="title">收款编号</td>
               <td colspan="2" align="left" class="l-table-edit-td"><input type="text" readonly="readonly" value="${combineOrder.financialNo}"></td>
	        </tr>
	        </c:if>
	        <tr>
               <td class="title">收款登记日期</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="collectionRegisterDate" name="collectionRegisterDate"/>
	               	<script type="text/javascript">
						$(function(){
							$("#collectionRegisterDate").ligerDateEditor({
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script>
				</td>
	        </tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
