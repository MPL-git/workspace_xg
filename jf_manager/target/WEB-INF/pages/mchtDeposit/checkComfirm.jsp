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
$(function(){
	$("input[name='checkResult']").bind('click',function(){
		var status = $(this).attr("status");
		var depositOrderId = $("#depositOrderId").val();
		var receiveDate = $("#receive_date").val();
		if(!receiveDate){
			commUtil.alertError("到款日期不能为空");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtDeposit/checkResult.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":depositOrderId,"status":status,"receiveDate":receiveDate},
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
});
</script>
<html>
<body>
	<div class="title-top">
	<input type="hidden" id="depositOrderId" value="${depositOrder.id}">
		<table class="gridtable baseInfo">
          	<tr>
          		<td>${paymentTypeDesc}</td>
          		<td>
          			<c:if test="${depositOrder.paymentType ne '3'}">
          				${paymentTypeDesc}
          			</c:if>
          			<c:if test="${depositOrder.paymentType eq '3'}">
          				${platformCapitalAccount.paymentName}
          			</c:if>
          		</td>
          		
          	</tr>
          	<tr>
          		<td>金额：</td>
          		<td>${depositOrder.amount}</td>
          	</tr>
          	<c:if test="${depositOrder.paymentType eq '3'}">
          	<tr>
          		<td>账户名：</td>
          		<td>${depositOrder.accountName}</td>
          	</tr>
          	<tr>
          		<td>开户银行：</td>
          		<td>${depositOrder.paymentName}</td>
          	</tr>
          	<tr>
          		<td>银行账号：</td>
          		<td>${depositOrder.accountNo}</td>
          	</tr>
          	<tr>
          		<td>打款时间:</td>
          		<td><fmt:formatDate value="${depositOrder.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          	</tr>
          	</c:if>
          	<c:if test="${depositOrder.paymentType ne '3'}">
          		<td>交易号：</td>
          		<td>${depositOrder.paymentNo}</td>
          	</c:if>
          	<tr>
          		<td>到款日期：</td>
          		<td>
	          		<input type="text" id="receive_date" name="receive_date" />
					<script type="text/javascript">
						$(function() {
							$("#receive_date").ligerDateEditor({
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
          		<td colspan="1" >操作</td>
          		<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="checkResult" status="1" type="button" style="float:left;" class="l-button l-button-submit" value="成功"/>
						<input name="checkResult" status="2" type="button" value="失败" class="l-button l-button-test" style="float:left;" />
					</div>
				</td>
          	</tr>
        </table>
	</div>
	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
