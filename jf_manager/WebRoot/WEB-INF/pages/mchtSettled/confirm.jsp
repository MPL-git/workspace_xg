<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select {
	border: 1px solid #AECAF0;
}

.radioClass {
	margin: 0 10px 0 10px;
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-text-wrapper {
	display: inline-block;
}

.wb150{
	width: 150px;
	display: inline-block;
}

table.gridtable td.title {
	background-color: #dedede;
	font-weight: bold;
	text-align: center;
	width: 150px;
}
</style>

<script type="text/javascript">
	var submitFlag = false;
	$(function ()  {
		$.metadata.setType("attr", "validate");
		var v = $("#form1").validate({
			errorPlacement: function (lable, element) {  
	        	var elementType=$(element).attr("type");
	        	if('radio'==elementType) {
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").parent("span").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else {
	        		$(element).ligerTip({
						content : lable.html(),width: 200
					});
	        	}
	        	$(".l-verify-tip-corner").css("z-index", "1001");
	        	$(".l-verify-tip-content").css("z-index", "1000");
	        },
			success: function (lable,element) {
				lable.ligerHideTip();
				lable.remove();
			},
			submitHandler: function (form) {  
				if(submitFlag) {
					return;
				}
				submitFlag = true;
	        	form.submit();
			}
		});
	});
</script>
</head>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/mchtSettled/submit.shtml">
		<input id="settledApplyId" name="settledApplyId" type="hidden" value="${settledApplyId }"/>
		<input id="platformContactId" name="platformContactId" type="hidden" value="${platformContactId }"/>
		<input id="mchtType" name="mchtType" type="hidden" value="${mchtSettledApply.mchtType}"/>
		<input id="depositType" name="depositType" type="hidden" value="${mchtSettledApply.depositType}"/>
		<input id="contractDeposit" name="contractDeposit" type="hidden" value="${mchtSettledApply.contractDeposit}"/>
		<input id="brandDeposit" name="brandDeposit" type="hidden" value="${mchtSettledApply.brandDeposit}"/>
		<input id="selectContractDeposit" name="selectContractDeposit" type="hidden" value="${mchtSettledApply.selectContractDeposit}"/>
		<input id="selectBrandDeposit" name="selectBrandDeposit" type="hidden" value="${mchtSettledApply.selectBrandDeposit}"/>
		<input id="isManageSelf" name="isManageSelf" type="hidden" value="${mchtSettledApply.isManageSelf}"/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title" >公司名称<span class="required">*</span></td>
				<td colspan="2" align="left" class="l-table-edit-td">
					<input id="companyName" name="companyName" value="${mchtSettledApply.companyName}" validate="{required:true}" type="text" style="width:200px;"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">合作模式<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<c:if test="${mchtSettledApply.mchtType eq '2'}">开放POP</c:if>
					<c:if test="${mchtSettledApply.mchtType eq '1'}">SPOP</c:if>
				</td>
			</tr>

			<c:if test="${mchtSettledApply.mchtType eq '1'}">
			<tr>
				<td colspan="1" class="title ">是否自营<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<c:if test="${mchtSettledApply.isManageSelf eq '0'}">非自营</c:if>
					<c:if test="${mchtSettledApply.isManageSelf eq '1'}">自营</c:if>
				</td>
			</tr>
			</c:if>
			
			<tr>
				<td colspan="1" class="title ">保证金缴费类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<c:if test="${mchtSettledApply.depositType eq '1'}">可货款抵扣</c:if>
					<c:if test="${mchtSettledApply.depositType eq '2'}">不可货款抵扣</c:if>
				</td>
			</tr>		
			
			<tr>
				<td colspan="1" class="title ">保证金类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="checkbox" disabled="disabled" <c:if test="${mchtSettledApply.selectContractDeposit == '1'}">checked="checked"</c:if>>店铺保证金<fmt:formatNumber type="number" value="${mchtSettledApply.contractDeposit}" pattern="0.00" maxFractionDigits="2"/>
					<input type="checkbox" disabled="disabled" <c:if test="${mchtSettledApply.selectBrandDeposit == '1'}">checked="checked"</c:if>>品牌保证金（每个）<fmt:formatNumber type="number" value="${mchtSettledApply.brandDeposit}" pattern="0.00" maxFractionDigits="2"/>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title ">技术服务费率预定<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<fmt:formatNumber type="number" value="${mchtSettledApply.feeRate}" pattern="0.00" maxFractionDigits="2"/>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title ">商家手机号<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="mobile" name="mobile" value="${mchtSettledApply.phone}" validate="{required:true,mobile:true}" type="text" style="width:120px;"/>
				</td>
			</tr>
			<%-- <tr>
				<td colspan="1" class="title">我的备注<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="remarks" name="remarks" type="text" style="width:120px;"/>
				</td>
			</tr> --%>
			<tr>
				<td colspan="1" class="title ">招商备注</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<c:if test="${empty mchtSettledApply.remarks}">
						<textarea id="remarks" name="remarks" rows="6" cols="60" class="text" >
1）主营类目：
2）佣金比例：
3）合作品牌：
4）每个品牌保证金：
5）总应收保证金：
6）各品牌的主要商品有哪些？或提供其他平台的店铺网址。
						</textarea>
					</c:if>
					<c:if test="${not empty mchtSettledApply.remarks}">
						<textarea id="remarks" name="remarks" rows="6" cols="60" class="text" >${mchtSettledApply.remarks }</textarea>
					</c:if>
				</td>
			</tr>
		<c:if test="${isView == '0'}">	
			<tr>
				<td colspan="1" class="title ">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交入驻"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>
		</c:if>
		</table>
	</form>
</body>
</html>
