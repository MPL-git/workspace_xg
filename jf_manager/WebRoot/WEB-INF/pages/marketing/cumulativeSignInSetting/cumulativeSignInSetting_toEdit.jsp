<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
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
	$("#save").bind('click',function(){
		var id = $("#id").val();
		var rewardName = $("#rewardName").val();
		if(!rewardName){
			commUtil.alertError("奖励名称不能为空");
			return false;
		}
		var integral = $("#integral").val()||"";
		<c:if test="${type == 1}">
		if(!integral){
			commUtil.alertError("赠送积分不能为空");
			return false;
		}else{
			if(!testNumber(integral)){
				commUtil.alertError("赠送积分请输入正整数");
				return false;
			}
		}
		</c:if>
		var couponIds = $("#couponIds").val()||"";
		<c:if test="${type == 2}">
		if(!couponIds){
			commUtil.alertError("优惠券ID不能为空");
			return false;
		}else{
			var array = couponIds.split(",");
			if(array.length>3){
				commUtil.alertError("最多维护3个优惠券ID");
				return false;
			}
		}
		</c:if>
		var productCode = $("#productCode").val()||"";
		var stock = $("#stock").val();
		<c:if test="${type == 3}">
		if(!productCode){
			commUtil.alertError("商品ID不能为空");
			return false;
		}
		if(stock<=0){
			commUtil.alertError("商品ID有误，库存必须大于0");
			return false;
		}
		</c:if>
		<c:if test="${type != 3}">
			stock="";
		</c:if>
		$.ajax({
			url : "${pageContext.request.contextPath}/marketing/cumulativeSignInSetting/save.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"rewardName":rewardName,"integral":integral,"couponIds":couponIds,"productCode":productCode,"stock":stock},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("保存成功");
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

function checkCouponIds(){
	var couponIds = $("#couponIds").val();
	var array = couponIds.split(",");
	if(array.length>3){
		commUtil.alertError("最多维护3个优惠券ID");
		return false;
	}
	if(couponIds){
		$.ajax({
			url : "${pageContext.request.contextPath}/marketing/signInSetting/checkCouponIds.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"couponIds":couponIds},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("优惠券ID可用");
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}else{
		commUtil.alertError("请输入优惠券ID");
		return ;
	}
}

function checkProductCode(){
	var productCode = $("#productCode").val();
	if(productCode){
		$.ajax({
			url : "${pageContext.request.contextPath}/marketing/cumulativeSignInSetting/checkProductCode.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"productCode":productCode},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("商品ID可用");
					$("#stock").val(data.stock);
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}else{
		commUtil.alertError("请输入商品ID");
		return ;
	}
}

function clearRewardName() {
	$.ligerDialog.confirm('是否清空本次设置？', function(operType){
		if(operType) {
			$.ajax({
				url : "${pageContext.request.contextPath}/marketing/cumulativeSignInSetting/clearRewardName.shtml",
				type : 'POST',
				dataType : 'json',
				data : {id : ${cumulativeSignInSetting.id }, type : ${type }},
				success : function(data) {
					if("0000" == data.returnCode ) {
						parent.location.reload();
						frameElement.dialog.close();
					}else {
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	});
}

</script>
<html>
<body>
<form method="post" id="form" name="form" action="">
	<input type="hidden" id="id" name="id" value="${cumulativeSignInSetting.id}">
	<table class="gridtable">
		<tr>
             <td class="title">累计签到次数</td>
             <td colspan="2" align="left" class="l-table-edit-td">
             	${cumulativeSignInSetting.cumulativeSignInCount}
             </td>
		</tr>
		<tr>
		      <td class="title">奖励名称</td>
		      <td colspan="2" align="left" class="l-table-edit-td">
		      		<input type="text" id="rewardName" name="rewardName" maxlength="32" value="${cumulativeSignInSetting.rewardName}">
		      </td>
		</tr>
		<tr>
		      <td class="title">奖励类型</td>
		      <td colspan="2" align="left" class="l-table-edit-td">
		      	<c:if test="${type == 1}">
		      		<input type="radio" name="rewardType"  readonly="readonly" checked="checked">积分
		      	</c:if>
		      	<c:if test="${type == 2}">
		      		<input type="radio" name="rewardType"  readonly="readonly" checked="checked">优惠券
		      	</c:if>
		      	<c:if test="${type == 3}">
		      		<input type="radio" name="rewardType"  readonly="readonly" checked="checked">实物
		      	</c:if>	
		      </td>
		</tr>
		<c:if test="${type == 1}">
		<tr id="integralTr">
		      <td class="title">赠送积分</td>
		      <td colspan="2" align="left" class="l-table-edit-td">
		          <input type="text" id="integral" name="integral" value="${cumulativeSignInSetting.integral}">
		      </td>
		</tr>
		</c:if>
		<c:if test="${type == 2}">
		<tr id="couponTr">
			<td class="title">优惠券ID</td>
			<td colspan="2" align="left" class="l-table-edit-td">
				<input type="text" id="couponIds" name="couponIds" value="${cumulativeSignInSetting.couponIds}">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="检测" onclick="checkCouponIds();">(最多维护3个,使用逗号隔开)
			</td>
		</tr>
		</c:if>
		<c:if test="${type == 3}">
		<tr id="productTr">
			<td class="title">商品ID</td>
			<td colspan="2" align="left" class="l-table-edit-td">
				<input type="text" id="productCode" name="productCode" value="${cumulativeSignInSetting.productCode}">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="检测" onclick="checkProductCode();">
				<span>库存：<input type="text" id="stock" name="stock" value="${cumulativeSignInSetting.stock}" readonly="readonly"></span>
			</td>
		</tr>
		</c:if>
		<tr>
			<td class="title">操作</td>
			<td colspan="2" align="left" class="l-table-edit-td">
			<div id="btnDiv">
				<input id="save" type="button" style="float:left;" class="l-button l-button-submit" value="保存"/>
				<input style="margin-left: 20px;" type="button" class="l-button l-button-submit" value="清除" onclick="clearRewardName();" />
			</div>
			</td>
		</tr>
       </table>
</form>	        
</body>
</html>
