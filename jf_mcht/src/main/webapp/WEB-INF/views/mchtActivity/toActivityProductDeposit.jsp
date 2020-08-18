<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>预售设置</title>
</head>

<body>
<!--查看品牌 -->
  <div class="modal-dialog" role="document">
    <div class="modal-content">
     <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title">预售设置</span>
     </div>
     <div class="modal-body">
     	<form id="dataFrom">
			<input type="hidden" value="${activityProductDeposit.id}" name="id">
			<input type="hidden" value="${activityId}" name="activityId">
			<input type="hidden" value="${productId}" name="productId">
			<div class="table-responsive">
				<table class="table table-bordered ">
					<tbody>
						<tr>
							<td class="editfield-label-td">商品活动价</td>
							<td colspan="2" class="text-left" id="activityPrice">
							<c:if test="${salePriceMin == salePriceMax}">
								${salePriceMin}
							</c:if>
							<c:if test="${salePriceMin != salePriceMax}">
								${salePriceMin}~${salePriceMax}
							</c:if>
							</td>
						</tr>
						<tr>
							<td class="editfield-label-td">商品定金<span class="required">*</span></td>
							<td colspan="2" class="text-left">
								<input type="text" value="<fmt:formatNumber value="${activityProductDeposit.deposit}" pattern="#" type="number"></fmt:formatNumber>" name="deposit" id="deposit" style="width: 100px;">
								<div style="display: inline-block;color: #999999;">提示：商品定金不高于商品活动价格</div>
							</td>
						</tr>
						<tr>
							<td class="editfield-label-td">商品抵扣金额<span class="required">*</span></td>
							<td colspan="2" class="text-left">
								<input type="text" value="<fmt:formatNumber value="${activityProductDeposit.deductAmount}" pattern="#" type="number"></fmt:formatNumber>" name="deductAmount" id="deductAmount" style="width: 100px;">
								<div style="display: inline-block;color: #999999;">提示：未正常发货将抵扣金额退还用户，由商家承担</div>
							</td>
						</tr>
	 				</tbody>
	 			</table>	
	    	</div>
    	</form>
    	<div class="modal-footer">
			<button class="btn btn-info" onclick="commit();">提交</button>
      	</div>
    </div>
  </div>
  
<script type="text/javascript">
function commit(){
	var reg = /^[0-9]*[1-9][0-9]*$/;
	var deposit = $("#deposit").val();
	if(!deposit){
		swal("商品定金不能为空");
       	return;
	}
	if(!reg.test(deposit)){
       	swal("商品定金必须为大于0的正整数");
       	return;
	}
	var activityPrice = $("#activityPrice").text();
	var array = activityPrice.split("~");
	var minPrice = array[0];
	if(Number(deposit)>=Number(minPrice)){
		swal("商品定金必须小于活动价最低价");
       	return;
	}
	if(Number(minPrice)<50){
		swal("商品活动价不得低于50元");
       	return;
	}
	if(Number(deposit)<5){
		swal("商品定金不得低于5元");
       	return;
	}
	if(Number(deposit)%5>0){
		swal("预售商品的定金金额须为5的倍数;");
       	return;
	}
	var price1 = minPrice*0.1;
	var price2 = minPrice*0.2;
	if(Number(deposit)<Number(price1) || Number(deposit)>Number(price2)){
		swal("定金金额须在预售商品价格的10%（含）至预售商品价格的20%（含）之间;");
       	return;
	}
	var deductAmount = $("#deductAmount").val();
	if(!deductAmount){
		swal("商品抵扣金额不能为空");
       	return;
	}
	if(!reg.test(deductAmount)){
       	swal("商品抵扣金额必须为大于0的正整数");
       	return;
	}
	if(Number(deductAmount)<=Number(deposit)){
		swal("商品抵扣金额必须大于定金");
       	return;
	}
	if(Number(deductAmount)>=Number(minPrice)){
		swal("商品抵扣金额必须小于商品活动价");
       	return;
	}
	if(Number(deductAmount)>Number(minPrice*0.5)){
		swal("商品抵扣金额不可大于商家预售价的50%");
       	return;
	}
	$.ajax({
        method: 'POST',
        url: '${ctx}/mcht/activity/saveActivityProductDeposit',
        data: $("#dataFrom").serialize(),
        dataType: 'json'
    }).done(function (result) {
        if (result.returnCode =='0000') {
        	submitting = false;
           	swal("操作成功");
           	$("#viewModal").modal('hide');
           	table.ajax.reload(null, false);
        }else{
        	swal("操作失败，请稍后重试");
        	submitting = false;
        }
    });
}
</script>
</body>
</html>
