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

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.title{
	width: 100px;
	text-align: left!important;
	padding-left:20px!important;
}
.authList{left: 0px;top: 0px;width: 180px;height: 32px;border:1px solid #707070;font-weight:bold;background-color: rgba(253, 225, 202, 1);color:#000000;}

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
// var checkBox=div1.getElementsByTagName('input');

$(function(){
	$("#selectAll").click(function(){   
	    if(this.checked){   
	        $("#div1 :checkbox").prop("checked", true);
	    }else{   
			$("#div1 :checkbox").prop("checked", false);
	    }   
	});
	$("#productAuthAll").click(function(){   
	    if(this.checked){   
	        $("#div2 :checkbox").prop("checked", true);
	    }else{   
		  $("#div2 :checkbox").prop("checked", false);
	    }   
	});
	
});
//提交
function submitAuth(){
	
	if(document.getElementById("selectAll").checked==true){
		$("#selectAll").val("1");
	}else{$("#selectAll").val("0");}
	
	if(document.getElementById("productAuthAll").checked==true){
		$("#productAuthAll").val("1");
	}else{$("#productAuthAll").val("0");}
	
	if(document.getElementById("activityNameFlag").checked==true){
		$("#activityNameFlag").val("1");
	}else{$("#activityNameFlag").val("0");}
	
	if(document.getElementById("activityTypeFlag").checked==true){
		$("#activityTypeFlag").val("1");
	}else{$("#activityTypeFlag").val("0");}
	
	if(document.getElementById("activityBrandFlag").checked==true){
		$("#activityBrandFlag").val("1");
	}else{$("#activityBrandFlag").val("0");}
	
	if(document.getElementById("activityBenefitFlag").checked==true){
		$("#activityBenefitFlag").val("1");
	}else{$("#activityBenefitFlag").val("0");}
	
	if(document.getElementById("activityEntryFlag").checked==true){
		$("#activityEntryFlag").val("1");
	}else{$("#activityEntryFlag").val("0");}
	
	if(document.getElementById("activityPosterFlag").checked==true){
		$("#activityPosterFlag").val("1");
	}else{$("#activityPosterFlag").val("0");}
	
	if(document.getElementById("activityPreferentialFlag").checked==true){
		$("#activityPreferentialFlag").val("1");
	}else{$("#activityPreferentialFlag").val("0");}
	
	if(document.getElementById("activityModifyFlag").checked==true){
		$("#activityModifyFlag").val("1");
	}else{	$("#activityModifyFlag").val("0");}
	
	
	if(document.getElementById("productTypeBrandFlag").checked==true){
		$("#productTypeBrandFlag").val("1");
	}else{$("#productTypeBrandFlag").val("0");}
	
	if(document.getElementById("productNamePropFlag").checked==true){
		$("#productNamePropFlag").val("1");
	}else{$("#productNamePropFlag").val("0");}
	
	if(document.getElementById("productPicFlag").checked==true){
		$("#productPicFlag").val("1");
	}else{$("#productPicFlag").val("0");}
	
	if(document.getElementById("productDescPicFlag").checked==true){
		$("#productDescPicFlag").val("1");
	}else{$("#productDescPicFlag").val("0");}
	
	if(document.getElementById("productOtherFlag").checked==true){
		$("#productOtherFlag").val("1");
	}else{$("#productOtherFlag").val("0");}
	
	if(document.getElementById("productPropNumFlag").checked==true){
		$("#productPropNumFlag").val("1");
	}else{$("#productPropNumFlag").val("0");}
	if(document.getElementById("productPropPriceFlag").checked==true){
		$("#productPropPriceFlag").val("1");
	}else{$("#productPropPriceFlag").val("0");}
	
	if(document.getElementById("productPropStockFlag").checked==true){
		$("#productPropStockFlag").val("1");
	}else{$("#productPropStockFlag").val("0");}
	$("#activityAuthForm").validate({
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
<html>
<body>
<form id="activityAuthForm" method="post" action="${pageContext.request.contextPath}/activity/addActivityAuth.shtml">
	<input type="hidden" id="type" name="type" value="${type}"/>
	<input type="hidden" id="activityId" name="activityId" value="${activityId}"/>
	<input type="hidden" id="id" name="id" value="${activityAuth.id}"/>
	<div id="div1">
		<div style="height:35px;width:100%;background-color: #FFFB94;padding:15px;">温馨提示：<br>
		<c:if test="${activityCustom.source==1}">每个属性前的复选框表示权限，勾选表示将此权限开放给商家，已驳回给商家去修改，再次提报。</c:if>
		<c:if test="${activityCustom.source==2}">每个属性前的复选框表示权限，勾选表示将此权限开放给商家。</c:if>
		</div>
		<table class="gridtable">
			<tr>
				<td colspan="2" style="border:none"><input type="button" disabled="disabled" value="活动权限控制" class="authList"/></td>
			</tr>
			<tr>
				<td class="title"><input type="checkbox" id="activityNameFlag" name="activityNameFlag" value="${activityAuth.activityNameFlag}" <c:if test="${activityAuth.activityNameFlag==1}">checked="checked"</c:if>/>&nbsp;&nbsp;活动名称：</td>
				<td>${activityCustom.name}</td>
			</tr>
			<tr>
				<td class="title"><input type="checkbox" id="activityTypeFlag" name="activityTypeFlag" value="${activityAuth.activityTypeFlag}" <c:if test="${activityAuth.activityTypeFlag==1}">checked="checked"</c:if>/>&nbsp;&nbsp;类目：</td>
				<td>
					${activityCustom.productTypeName}
				</td>
			</tr>
			<tr>
				<td class="title"><input type="checkbox" id="activityBrandFlag" name="activityBrandFlag" value="${activityAuth.activityBrandFlag}" <c:if test="${activityAuth.activityBrandFlag==1}">checked="checked"</c:if>/>&nbsp;&nbsp;品牌：</td>
				<td>${activityCustom.productBrandName}</td>
			</tr>
			<c:if test="${activityCustom.source==2}">
			<tr>
				<td class="title"><input type="checkbox" id="activityBenefitFlag" name="activityBenefitFlag" value="${activityAuth.activityBenefitFlag}" <c:if test="${activityAuth.activityBenefitFlag==1}">checked="checked"</c:if>/>&nbsp;&nbsp;利益点：</td>
				<td>${activityCustom.benefitPoint}</td>
			</tr>
			</c:if>
			<tr height="150px;">
				<td class="title"><input type="checkbox" id="activityEntryFlag" name="activityEntryFlag" value="${activityAuth.activityEntryFlag}" <c:if test="${activityAuth.activityEntryFlag==1}">checked="checked"</c:if>/>&nbsp;&nbsp;入口图：</td>
				<td align="left">
					<img id="logoPic" style="width: 200px;height: 100px" alt="" src="${pageContext.request.contextPath}/file_servelt${activityCustom.entryPic}">
				</td>
			</tr>
			<tr height="150px;">
				<td class="title"><input type="checkbox" id="activityPosterFlag" name="activityPosterFlag" value="${activityAuth.activityPosterFlag}" <c:if test="${activityAuth.activityPosterFlag==1}">checked="checked"</c:if>/>&nbsp;&nbsp;头部海报：</td>
				<td align="left">
					<img id="logoPic" style="width: 200px;height: 100px" alt="" src="${pageContext.request.contextPath}/file_servelt${activityCustom.posterPic}">
				</td>
			</tr>
			<c:if test="${activityCustom.source==2}">
			<tr>
				<td class="title"><input type="checkbox" id="activityPreferentialFlag" name="activityPreferentialFlag" value="${activityAuth.activityPreferentialFlag}" <c:if test="${activityAuth.activityPreferentialFlag==1}">checked="checked"</c:if>/>&nbsp;&nbsp;促销方式：</td>
				<td>
					<span class="radioClass"><input class="radioItem" disabled="disabled" type="radio" value="1" name="status" validate="{radioRequired:true}" checked="checked" <c:if test="${activityCustom.preferentialType==0}">checked="checked"</c:if> >无</span>
					<span class="radioClass"><input class="radioItem" disabled="disabled" type="radio" value="1" name="status" validate="{radioRequired:true}" <c:if test="${activityCustom.preferentialType==1}">checked="checked"</c:if> >优惠券</span>
					<span class="radioClass"><input class="radioItem" disabled="disabled" type="radio" value="1" name="status" validate="{radioRequired:true}" <c:if test="${activityCustom.preferentialType==2}">checked="checked"</c:if> >满减</span>
					<span class="radioClass"><input class="radioItem" disabled="disabled" type="radio" value="1" name="status" validate="{radioRequired:true}" <c:if test="${activityCustom.preferentialType==3}">checked="checked"</c:if> >满赠</span>
				</td>
			</tr>
			</c:if>
			<tr>
				<td colspan="2" style="padding-left:20px;">
					<input type="checkbox" id="activityModifyFlag" name="activityModifyFlag" value="${activityAuth.activityModifyFlag}" <c:if test="${activityAuth.activityModifyFlag==1}">checked="checked"</c:if>/>&nbsp;&nbsp;修改活动商品权限（添加 / 退出活动商品
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding-left:20px;">
					<input type="checkbox" id="selectAll" name="selectAll" value="" <c:if test="${selectAll==1}">checked="checked"</c:if>/>&nbsp;&nbsp;全选，开放全部权限。
				</td>
			</tr>
		</table>
	</div>
	<div id="div2">
		<table class="gridtable">
			<tr>
				<td colspan="2" style="border:none">
					<input type="button" disabled="disabled" value="商品权限控制" class="authList"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					商品权限管理：&nbsp;&nbsp;
					<input type="checkbox" id="productAuthAll" name="productAuthAll" value="" <c:if test="${productAuthAll==1}">checked="checked"</c:if>/>&nbsp;开放全部权限&nbsp;&nbsp;
					<input type="checkbox" id="productTypeBrandFlag" name="productTypeBrandFlag" value="${activityAuth.productTypeBrandFlag}" <c:if test="${activityAuth.productTypeBrandFlag==1}">checked="checked"</c:if>/>&nbsp;品类品牌&nbsp;&nbsp;
					<input type="checkbox" id="productNamePropFlag" name="productNamePropFlag" value="${activityAuth.productNamePropFlag}" <c:if test="${activityAuth.productNamePropFlag==1}">checked="checked"</c:if>/>&nbsp;商品名称属性参数&nbsp;&nbsp;
					<input type="checkbox" id="productPicFlag" name="productPicFlag" value="${activityAuth.productPicFlag}" <c:if test="${activityAuth.productPicFlag==1}">checked="checked"</c:if>/>&nbsp;商品主图&nbsp;&nbsp;
					<input type="checkbox" id="productDescPicFlag" name="productDescPicFlag" value="${activityAuth.productDescPicFlag}" <c:if test="${activityAuth.productDescPicFlag==1}">checked="checked"</c:if>/>&nbsp;商品详情图&nbsp;&nbsp;
					<input type="checkbox" id="productPropNumFlag" name="productPropNumFlag" value="${activityAuth.productPropNumFlag}" <c:if test="${activityAuth.productPropNumFlag==1}">checked="checked"</c:if>/>&nbsp;商品规格加减&nbsp;&nbsp;
					<input type="checkbox" id="productPropPriceFlag" name="productPropPriceFlag" value="${activityAuth.productPropPriceFlag}" <c:if test="${activityAuth.productPropPriceFlag==1}">checked="checked"</c:if>/>&nbsp;商品规格-价格&nbsp;&nbsp;
					<input type="checkbox" id="productPropStockFlag" name="productPropStockFlag" value="${activityAuth.productPropStockFlag}" <c:if test="${activityAuth.productPropStockFlag==1}">checked="checked"</c:if>/>&nbsp;商品规格-库存&nbsp;&nbsp;
					<input type="checkbox" id="productOtherFlag" name="productOtherFlag" value="${activityAuth.productOtherFlag}" <c:if test="${activityAuth.productOtherFlag==1}">checked="checked"</c:if>/>&nbsp;其它信息&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" onclick="submitAuth();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</div>
</form>
</body>
</html>
