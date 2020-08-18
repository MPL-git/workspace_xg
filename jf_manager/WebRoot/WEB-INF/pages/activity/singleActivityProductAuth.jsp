<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.title{
	width: 300px;
}

td input,td select{
border:1px solid #AECAF0;
}
.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
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

.l-verify-tip {
	left: 230px;
	top: 120px;
}

.table-title-link{
color: #1B17EE;
font-size: 15px;
text-decoration: none;
}

.table-title{
font-size: 17px;font-weight: 600;
}
.examine{
	float: left;
}
.examine table{
	width: 318px;
	font-size: 15px;
}
.textarea{margin-left: 100px;}
</style>
<script type="text/javascript">

// 		$("#fullOfGiftsType").attr("checked", false);
function activityModifyFlagOnclick(){
	if($("#activityModifyFlag").prop("checked")){
		$("#activityModifyFlag").val("1");
	}else{
		$("#activityModifyFlag").attr("checked", false);
		$("#activityModifyFlag").val("0");
	}
}
function productTypeBrandFlagOnclick(){
	if($("#productTypeBrandFlag").prop("checked")){
		$("#productTypeBrandFlag").val("1");
	}else{
		$("#productTypeBrandFlag").attr("checked", false);
		$("#productTypeBrandFlag").val("0");
	}
}
function productNamePropFlagOnclick(){
	if($("#productNamePropFlag").prop("checked")){
		$("#productNamePropFlag").val("1");
	}else{
		$("#productNamePropFlag").attr("checked", false);
		$("#productNamePropFlag").val("0");
	}
}
function productPicFlagOnclick(){
	if($("#productPicFlag").prop("checked")){
		$("#productPicFlag").val("1");
	}else{
		$("#productPicFlag").attr("checked", false);
		$("#productPicFlag").val("0");
	}
}
function productDescPicFlagOnclick(){
	if($("#productDescPicFlag").prop("checked")){
		$("#productDescPicFlag").val("1");
	}else{
		$("#productDescPicFlag").attr("checked", false);
		$("#productDescPicFlag").val("0");
	}
}
function productOtherFlagOnclick(){
	if($("#productOtherFlag").prop("checked")){
		$("#productOtherFlag").val("1");
	}else{
		$("#productOtherFlag").attr("checked", false);
		$("#productOtherFlag").val("0");
	}
}
function productPropNumFlagOnclick(){
	if($("#productPropNumFlag").prop("checked")){
		$("#productPropNumFlag").val("1");
	}else{
		$("#productPropNumFlag").attr("checked", false);
		$("#productPropNumFlag").val("0");
	}
}
function productPropPriceFlagOnclick(){
	if($("#productPropPriceFlag").prop("checked")){
		$("#productPropPriceFlag").val("1");
	}else{
		$("#productPropPriceFlag").attr("checked", false);
		$("#productPropPriceFlag").val("0");
	}
}
function productPropStockFlagOnclick(){
	if($("#productPropStockFlag").prop("checked")){
		$("#productPropStockFlag").val("1");
	}else{
		$("#productPropStockFlag").attr("checked", false);
		$("#productPropStockFlag").val("0");
	}
}
$(function(){
	$("#productAuthAll").click(function(){   
	    if(this.checked){   
	        $("#div2 :checkbox").prop("checked", true);
	        $("#productTypeBrandFlag").val("1");
			$("#productNamePropFlag").val("1");
			$("#productPicFlag").val("1");
			$("#productDescPicFlag").val("1");
			$("#productOtherFlag").val("1");
			$("#productPropNumFlag").val("1");
			$("#productPropPriceFlag").val("1");
			$("#productPropStockFlag").val("1");
	    }else{   
		  $("#div2 :checkbox").prop("checked", false);
		  $("#productTypeBrandFlag").val("0");
			$("#productNamePropFlag").val("0");
			$("#productPicFlag").val("0");
			$("#productDescPicFlag").val("0");
			$("#productOtherFlag").val("0");
			$("#productPropNumFlag").val("0");
			$("#productPropPriceFlag").val("0");
			$("#productPropStockFlag").val("0");
	    }   
	});
});

function submitAudit(){
	
	$("#singleActivityAuthForm").validate({
		success: function (lable,element)
		{
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler: function (form)
		{   
			form.submit();
		}
		
	});
}

</script>
<style type="text/css">
.ss{
	width:100%;
	background-color: rgba(215, 215, 215, 1);
}
.samptime{font-size: 15px;}

</style>
<html>
<body>
<form id="singleActivityAuthForm" method="post" action="${pageContext.request.contextPath}/activity/singleActivityAuth.shtml">
	<input type="hidden" id="id" name="id" value="${activityProductCustom.id}"/>
	<input type="hidden" id="activityId" name="activityId" value="${activityProductCustom.activityId}"/>
	<table class="gridtable">
		<tr>
			<td class="title">商家名称</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.shortName}</td>
			<td class="title">商家序号</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.mchtCode}</td>
		</tr>
	</table>
	
	<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
	<br/>
	<table class="gridtable">
		<tr align="center">
			<td class="title">商品图</td>
			<td class="title">商品信息</td>
			<td class="title">活动价/历史最低价/原价</td>
			<td class="title">类目/品牌</td>
			<td class="title">本次销量/历史总销量</td>
			<td class="title">库存</td>
			<td class="title">参加单品活动次数</td>
		</tr>
		<tr align="center">
			<td>
				<img src='${pageContext.request.contextPath}/file_servelt${activityProductCustom.productPic}' width='100px;' height='100px;' style="margin-top: 20px;margin-bottom: 20px;"/>
			</td>
			<td>
				<samp>${activityProductCustom.productName}</samp>
				<br>
				<br>
				<samp>商品ID：${activityProductCustom.productCode}</samp>&nbsp;&nbsp;&nbsp;&nbsp;<samp>货号：${activityProductCustom.productArtNo}</samp>
			</td>
			<td>
				<samp>${activityProductCustom.activitySalePrice}</samp><br>
				<samp>${activityProductCustom.minimumPrice}</samp><br>
				<samp>${activityProductCustom.tagPrice}</samp>
				
			</td>
			<td>
				<samp>${activityProductCustom.productTypeName}</samp>
				<br>
				<br>
				<samp>${activityProductCustom.productBrandName}</samp>
				</td>
			<td>
				<samp>${activityProductCustom.thisSalesNum}</samp><br>
				<samp>${activityProductCustom.totalSalesNum}</samp>
			</td>
			<td>${activityProductCustom.productStock}</td>
			<td>${activityProductCustom.signleTypeTwoNum}</td>
		</tr>
	
	</table>
	<br/>
	<!-- <br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV> -->
	<div style="height:35px;width:100%;background-color: #FFFB94;padding:15px;">温馨提示：<br>
		每个属性前的复选框表示权限，勾选表示将此权限开放给商家，所有权限都是针对商家，若开放某个商品权限，则表示商家本次活动报名的商品都有权限修改。
	</div>
	<br/>
	<table class="gridtable">
		<tr>
			<td style="border:none">
				<input type="checkbox" id="activityModifyFlag" name="activityModifyFlag" onclick="activityModifyFlagOnclick();" value="${activityAuth.activityModifyFlag}" <c:if test="${activityAuth.activityModifyFlag==1}">checked="checked"</c:if>/>&nbsp;&nbsp;修改活动商品权限（添加 / 退出活动商品）
			</td>
		</tr>
	</table>
	<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
	<br/>
	<div id="div2">
		<table class="gridtable">
			<tr>
				<td style="border:none">
					<input type="button" disabled="disabled" style="width: 150px;height: 40px;background-color: rgba(253, 225, 202, 1);border: none;color: #333333;"value="商品权限控制"/>
				</td>
			</tr>
			<tr>
				<td style="border:none">
					商品权限管理：&nbsp;&nbsp;
					<input type="checkbox" id="productAuthAll" name="productAuthAll" value="" <c:if test="${productAuthAll==1}">checked="checked"</c:if>/>&nbsp;开放全部权限&nbsp;&nbsp;
					<input type="checkbox" id="productTypeBrandFlag" name="productTypeBrandFlag" onclick="productTypeBrandFlagOnclick();" value="${activityAuth.productTypeBrandFlag}" <c:if test="${activityAuth.productTypeBrandFlag==1}">checked="checked"</c:if>/>&nbsp;品类品牌&nbsp;&nbsp;
					<input type="checkbox" id="productNamePropFlag" name="productNamePropFlag" onclick="productNamePropFlagOnclick();" value="${activityAuth.productNamePropFlag}" <c:if test="${activityAuth.productNamePropFlag==1}">checked="checked"</c:if>/>&nbsp;商品名称属性参数&nbsp;&nbsp;
					<input type="checkbox" id="productPicFlag" name="productPicFlag" onclick="productPicFlagOnclick();" value="${activityAuth.productPicFlag}" <c:if test="${activityAuth.productPicFlag==1}">checked="checked"</c:if>/>&nbsp;商品主图&nbsp;&nbsp;
					<input type="checkbox" id="productDescPicFlag" name="productDescPicFlag" onclick="productDescPicFlagOnclick();" value="${activityAuth.productDescPicFlag}" <c:if test="${activityAuth.productDescPicFlag==1}">checked="checked"</c:if>/>&nbsp;商品详情图&nbsp;&nbsp;
					<input type="checkbox" id="productOtherFlag" name="productOtherFlag" onclick="productOtherFlagOnclick();" value="${activityAuth.productOtherFlag}" <c:if test="${activityAuth.productOtherFlag==1}">checked="checked"</c:if>/>&nbsp;其它信息&nbsp;&nbsp;
					<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" id="productPropNumFlag" name="productPropNumFlag" onclick="productPropNumFlagOnclick();" value="${activityAuth.productPropNumFlag}" <c:if test="${activityAuth.productPropNumFlag==1}">checked="checked"</c:if>/>&nbsp;商品规格加减&nbsp;&nbsp;
					<input type="checkbox" id="productPropPriceFlag" name="productPropPriceFlag" onclick="productPropPriceFlagOnclick();" value="${activityAuth.productPropPriceFlag}" <c:if test="${activityAuth.productPropPriceFlag==1}">checked="checked"</c:if>/>&nbsp;商品规格-价格&nbsp;&nbsp;
					<input type="checkbox" id="productPropStockFlag" name="productPropStockFlag" onclick="productPropStockFlagOnclick();" value="${activityAuth.productPropStockFlag}" <c:if test="${activityAuth.productPropStockFlag==1}">checked="checked"</c:if>/>&nbsp;商品规格-库存&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center" style="border:none">
					<input type="submit" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" onclick="submitAudit();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</div>
</form>
</body>
</html>
