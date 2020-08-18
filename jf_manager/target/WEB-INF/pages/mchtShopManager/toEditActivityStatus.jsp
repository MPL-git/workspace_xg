<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript">
	function confirmOpen() {
		var mchtId = $("#mchtId").val();
		var status = $("#activityAuthStatus").val();
		var activityAuthStatus = status == 1 ? 0: 1;
		if(!mchtId){
			commUtil.alertError("数据错误，请刷新页面");
			return false;
		}
		var speciallyApprovedStatus = $("input[name='speciallyApprovedStatus']:checked").val();
		var speciallyApprovedRemarks = $("#speciallyApprovedRemarks").val();
		if (speciallyApprovedStatus == null || speciallyApprovedStatus == '') {
			commUtil.alertError("请选择是否特批");
			return false;
		}
		if (speciallyApprovedStatus == '1' && speciallyApprovedRemarks == '') {
			commUtil.alertError("请填写特批备注");
			return false;
		}
		if (speciallyApprovedStatus == '0' && activityAuthStatus == '1') {
			var flag = true;
			var enterprise_activities_DSR = 0;
			<c:if test="${not empty productType && not empty productType.enterpriseActivitiesDsr}">
				enterprise_activities_DSR = ${productType.enterpriseActivitiesDsr};
			</c:if>
			var enterprise_turnover = 0;
			<c:if test="${not empty productType && not empty productType.enterpriseTurnover}">
				enterprise_turnover = ${productType.enterpriseTurnover};
			</c:if>
			var enterprise_activity_sales = 0;
			<c:if test="${not empty productType && not empty productType.enterpriseActivitySales}">
				enterprise_activity_sales = ${productType.enterpriseActivitySales};
			</c:if>
			// 商家DSR没数据视为5分
			var mcht_DSR = 5;
			var avg_product_score = 0;
			var avg_serve_score = 0;
			<c:if test="${not empty map.avg_product_score}">
				avg_product_score = ${map.avg_product_score};
			</c:if>
			<c:if test="${not empty map.avg_serve_score}">
				avg_serve_score = ${map.avg_serve_score};
			</c:if>
			if((avg_product_score+avg_serve_score)/3>0){
				mcht_DSR = (avg_product_score+avg_serve_score)/3;
			}
			var allPayAmount = 0;
			<c:if test="${not empty map.allPayAmount}">
				allPayAmount = ${map.allPayAmount};
			</c:if>
			var allQuantity = 0;
			<c:if test="${not empty map.allQuantity}">
				allQuantity = ${map.allQuantity};
			</c:if>
			if (mcht_DSR < enterprise_activities_DSR) {
				flag = false;
			}
			if (allPayAmount < enterprise_turnover) {
				flag = false;
			}
			if (allQuantity < enterprise_activity_sales) {
				flag = false;
			}
			if (!flag) {
				commUtil.alertError("商家不符合，开通失败");
				return false;
			}
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtShopManager/changeActivityStatus.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"mchtId": mchtId,
				"speciallyApprovedStatus": speciallyApprovedStatus,
				"speciallyApprovedRemarks": speciallyApprovedRemarks,
				"activityAuthStatus": activityAuthStatus
				},
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
				submitting = false;
			},
			error : function() {
				submitting = false;
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
</script>

<html>
<body>
	<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/payToMchtLog/updateConfirmStatus.shtml">
		<input type="hidden" name="mchtId" id="mchtId" value="${mchtInfo.id}">
		<input type="hidden" name="activityAuthStatus" id="activityAuthStatus" value="${mchtInfo.activityAuthStatus}">
		<table class="gridtable">
			<tr>
				<td colspan="3" class="title">商家主营类目：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					${productType.name}
	    		</td>
			</tr>
			
			<tr>
				<td colspan="3" class="title">开通条件：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					DSR≥<c:if test="${empty productType.enterpriseActivitiesDsr}">0</c:if><c:if test="${not empty productType.enterpriseActivitiesDsr}">${productType.enterpriseActivitiesDsr}</c:if>,
					总营业额≥<c:if test="${empty productType.enterpriseTurnover}">0</c:if><c:if test="${not empty productType.enterpriseTurnover}">${productType.enterpriseTurnover}</c:if>,
					总销量≥<c:if test="${empty productType.enterpriseActivitySales}">0</c:if><c:if test="${not empty productType.enterpriseActivitySales}">${productType.enterpriseActivitySales}</c:if>
	    		</td>
			</tr>
			
			<tr>
				<td colspan="3" class="title">商家情况：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
				DSR=<fmt:formatNumber type="number" value="${(map.avg_product_score+map.avg_serve_score)/3}" pattern="0.00"/>,
				总营业额=${map.allPayAmount},
				总销量=${map.allQuantity}
	    		</td>
			</tr>
			
			<tr>
				<td colspan="3" class="title">是否特批开通：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="radio" value="0" name="speciallyApprovedStatus" checked <c:if test="${isClose == 1}">onclick="return false"</c:if>/>否
					<input type="radio" value="1" name="speciallyApprovedStatus" <c:if test="${mchtInfo.speciallyApprovedStatus == 1}">checked</c:if> <c:if test="${isClose == 1}">onclick="return false"</c:if>/>是
	    		</td>
			</tr>
			
			<tr>
				<td colspan="3" class="title">特批备注：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
				<textarea rows="3" id="speciallyApprovedRemarks" name="speciallyApprovedRemarks" cols="100"
			  	<c:if test="${isClose == 1}">readonly</c:if>
                          class="text">${mchtInfo.speciallyApprovedRemarks}</textarea>
                </td>
			</tr>

			<tr>
				<td colspan="3" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input type="button" onclick="confirmOpen()" style="float:left;" class="l-button l-button-submit" 
						value=<c:if test="${mchtInfo.activityAuthStatus == null || mchtInfo.activityAuthStatus == '' || mchtInfo.activityAuthStatus == 0}">
							"开通"
						</c:if>
						<c:if test="${mchtInfo.activityAuthStatus == 1}">
							"关闭"
						</c:if>/>
						
						<button style="margin-left: 20px;" class="l-button" type="button" onclick="frameElement.dialog.close()">取消</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
