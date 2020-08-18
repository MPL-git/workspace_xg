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
	$("#confirmBtn").bind('click',function(){
		var id = $("#mchtSettleOrderId").val();
		var platformInvoiceStatus = $("#platformInvoiceStatus").val();
		var platformInvoiceExpressNo = $("#platformInvoiceExpressNo").val();
		var expressId = $("#expressId").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtSettleOrder/platformInvoiceStatus.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"platformInvoiceStatus":platformInvoiceStatus,"platformInvoiceExpressNo":platformInvoiceExpressNo,"expressId":expressId},
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
	<input type="hidden" id="mchtSettleOrderId" value="${mchtSettleOrder.id}">
		<table class="gridtable">
          	<tr>
          		<td colspan="1">开票状态</td>
          		<td>
	          		<select id="platformInvoiceStatus" name="platformInvoiceStatus" style="width: 150px;">
						<option value="">请选择</option>
						<c:forEach var="platformInvoiceStatus" items="${platformInvoiceStatusList}">
							<option value="${platformInvoiceStatus.statusValue}" <c:if test="${mchtSettleOrder.platformInvoiceStatus eq platformInvoiceStatus.statusValue}">selected="selected"</c:if>>${platformInvoiceStatus.statusDesc}</option>
						</c:forEach>
					</select>
				</td>
          	</tr>
          	<tr>
          		<td>快递名称</td>
          		<td>
          			<select id="expressId" name="expressId" style="width: 150px;">
						<option value="">请选择</option>
						<c:forEach var="express" items="${expressList}">
							<option value="${express.id}">${express.name}</option>
						</c:forEach>
					</select>
          		</td>
          	</tr>
          	<tr>
          		<td>快递单号</td>
          		<td><input type="text" id="platformInvoiceExpressNo"></td>
          	</tr>
          	<tr>
          		<td colspan="5">
					<div style="margin-left: 40%">
						<input id="confirmBtn" type="button" style="float:left;" class="l-button l-button-test" value="确认"/>
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
