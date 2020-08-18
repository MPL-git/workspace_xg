<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}



.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}
</style>
<script type="text/javascript">
function changeShopNameAuditStatusCommit(){
	var shopNameAuditStatus = $("input[name='shopNameAuditStatus']:checked").val();
	var shopNameAuditRemarks=$("#shopNameAuditRemarks").val();
	var contractDeposit = $("#contractDeposit").val();
	var feeRate = $("#feeRate").val();
	if(!shopNameAuditStatus){
		commUtil.alertError("请选择审核结果");
		return;
	}
	if(!contractDeposit){
		commUtil.alertError("店铺保证金不能为空");
		return;
	}
	if(!feeRate){
		commUtil.alertError("技术服务费率预定不能为空");
		return;
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/changeShopNameAuditStatusCommit.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {
			mchtId : ${mchtInfo.id},
			shopNameAuditStatus:shopNameAuditStatus,
			shopNameAuditRemarks:shopNameAuditRemarks,
			contractDeposit:contractDeposit,
			feeRate:feeRate
		},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				parent.location.reload();
				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
		<table class="gridtable">
					<tr>
			<td  colspan="1" class="title">店铺类型<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					
					<select  name="shopType" id="shopType" disabled="disabled">
						   <c:forEach var="shopTypeStatus" items="${shopTypeStatusList }">
						   <option value="${shopTypeStatus.statusValue}"   <c:if test="${mchtInfo.shopType==shopTypeStatus.statusValue}">selected</c:if>>${shopTypeStatus.statusDesc}</option>
						   </c:forEach>
                     </select>
			</td>
			</tr>
					<tr>
			<td  colspan="1" class="title">公司字号</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.businessFirms}
			</td>
			</tr>
					<tr>
			<td  colspan="1" class="title">品牌</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.brand}
			</td>
			</tr>
					<tr>
			<td  colspan="1" class="title">品类</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.productType}
			</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">店铺名</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.shopName}
			</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">合作模式</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.mchtTypeDesc}
			</td>
			</tr>
			<tr>
			<tr>
				<td  colspan="1" class="title">保证金缴费方式</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${mchtInfo.depositType == 1}">
						可货款抵扣
					</c:if>
					<c:if test="${mchtInfo.depositType == 2}">
						不可货款抵扣
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">保证金类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					店铺保证金<input type="text" value="${mchtInfo.contractDeposit}" id="contractDeposit" name="contractDeposit">
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">技术服务费率预定</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${empty mchtInfo.feeRate}">
						<input type="text" name="feeRate" value="${feeRate}" id="feeRate">
					</c:if>
					<c:if test="${not empty mchtInfo.feeRate}">
						<input type="text" name="feeRate" value="${mchtInfo.feeRate}" id="feeRate">
					</c:if>
				</td>	
			</tr>
			<tr>
			<td colspan="1" class="title">审核结果</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<input class="radioItem" type="radio" value="3" name="shopNameAuditStatus" <c:if test="${mchtInfo.shopNameAuditStatus==3}">checked="checked"</c:if> >通过
					<input class="radioItem" type="radio" value="4" name="shopNameAuditStatus" <c:if test="${mchtInfo.shopNameAuditStatus==4}">checked="checked"</c:if> >驳回
				</td>
			</tr>
			<tr>
			<td colspan="1" class="title">驳回原因</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<textarea rows=3 id="shopNameAuditRemarks" name="shopNameAuditRemarks" cols="45" class="text" >${mchtInfo.shopNameAuditRemarks}</textarea>
			</td>
			</tr>
			<tr>
			<td colspan="1" class="title">内部备注</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<textarea rows=3 id="shopNameAuditInnerRemarks" name="shopNameAuditInnerRemarks" cols="45" class="text" >${mchtInfo.shopNameAuditInnerRemarks}</textarea>
			</td>
			</tr>
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<c:if test="${STAFF_ID!=1}">
							<input name="btnSubmit" type="button" id="Button1" onclick="changeShopNameAuditStatusCommit();"
								style="float:left;" class="l-button l-button-submit" value="提交"
								 />
						</c:if>
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />

					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
