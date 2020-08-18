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
	$("input[name='rewardType']").bind('click',function(){
		var rewardType = $(this).val();
		if(rewardType == 1){
			$("#integralTr").hide();
			$("#couponTr").hide();
			$("#rewardName").val("现金红包");
		}else if(rewardType == 2){
			$("#integralTr").show();
			$("#couponTr").hide();
			$("#rewardName").val("积分");
			$("#integral").val("");
		}else if(rewardType == 3){
			$("#couponTr").show();
			$("#integralTr").hide();
			$("#rewardName").val("优惠券");
		}
	});
	
	$("#integral").bind('input',function(){
		var integral = $(this).val();
		if(integral){
			if(testNumber(integral)){
				$("#rewardName").val(integral+"积分");
			}else{
				return;
			}
		}else{
			$("#rewardName").val("积分");
		}
	});
	
	$("#save").bind('click',function(){
		var id = $("#id").val();
		var rewardName = $("#rewardName").val();
		var rewardType = $("input[name='rewardType']:checked").val();
		var rewardGift;
		if(rewardType == 2){
			var rewardGift = $("#integral").val();
			if(!rewardGift){
				commUtil.alertError("赠送积分不能为空");
				return false;
			}else{
				if(!testNumber(rewardGift)){
					commUtil.alertError("赠送积分请输入正整数");
					return false;
				}
			}
		}else if(rewardType == 3){
			var rewardGift = $("#couponIds").val();
			if(!rewardGift){
				commUtil.alertError("优惠券ID不能为空");
				return false;
			}else{
				var array = rewardGift.split(",");
				if(array.length>3){
					commUtil.alertError("最多维护3个优惠券ID");
					return false;
				}
			}
		}
		if(!rewardName){
			commUtil.alertError("奖励名称不能为空");
			return false;
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath}/marketing/signInSetting/save.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"rewardName":rewardName,"rewardType":rewardType,"rewardGift":rewardGift},
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
</script>
<html>
<body>
<form method="post" id="form" name="form" action="">
	<input type="hidden" id="id" name="id" value="${signInSetting.id}">
	<table class="gridtable">
         	<tr>
             <td class="title">时间</td>
             <td colspan="2" align="left" class="l-table-edit-td">
             		<fmt:formatDate value="${signInSetting.signInDate}" pattern="yyyy-MM-dd"/>
             </td>
	</tr>
       <tr>
             <td class="title">奖励名称</td>
             <td colspan="2" align="left" class="l-table-edit-td">
             		<input type="text" id="rewardName" name="rewardName" maxlength="32" value="${signInSetting.rewardName}">
             </td>
       </tr>
       <tr>
             <td class="title">奖励类型</td>
             <td colspan="2" align="left" class="l-table-edit-td">
             		<input type="radio" name="rewardType" value="1" <c:if test="${signInSetting.rewardType == 1}">checked="checked"</c:if>>现金红包
             		<input type="radio" name="rewardType" value="2" <c:if test="${signInSetting.rewardType == 2}">checked="checked"</c:if>>积分
             		<input type="radio" name="rewardType" value="3" <c:if test="${signInSetting.rewardType == 3}">checked="checked"</c:if>>优惠券
             </td>
       </tr>
       <tr id="integralTr" <c:if test="${signInSetting.rewardType ne 2}">style="display: none;"</c:if>>
             <td class="title">赠送积分</td>
             <td colspan="2" align="left" class="l-table-edit-td">
             		<input type="text" id="integral" name="rewardGift" <c:if test="${signInSetting.rewardType == 2}">value="${signInSetting.rewardGift}"</c:if>>
             </td>
       </tr>
       <tr id="couponTr" <c:if test="${signInSetting.rewardType ne 3}">style="display: none;"</c:if>>
             <td class="title">优惠券ID</td>
             <td colspan="2" align="left" class="l-table-edit-td">
             		<input type="text" id="couponIds" name="rewardGift" <c:if test="${signInSetting.rewardType == 3}">value="${signInSetting.rewardGift}"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="检测" onclick="checkCouponIds();">(最多维护3个,使用逗号隔开)
             </td>
       </tr>
       <c:if test="${canEdit}">
       <tr>
            <td class="title">操作</td>
            <td colspan="2" align="left" class="l-table-edit-td">
				<div id="btnDiv">
					<input id="save" type="button" style="float:left;" class="l-button l-button-submit" value="保存"/>
				</div>
			</td>
       </tr>
       </c:if>
       </table>
</form>	        
</body>
</html>
