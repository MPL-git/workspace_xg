<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
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
function del(_this){
	console.log("${activityRuleConfiguration}")
	$(_this).closest("div").remove();
}

function modeType(_this){
	var modeType = $(_this).val();
	if(modeType == 0){
		$(_this).next().next().next().val("");
	}else{
		$(_this).prev().val("");
	}
}

$(function(){
	$("input[name='priceRuleAdd']").live('click',function(){
		var $closestDiv = $(this).closest("div");
		var index = $closestDiv.parent().find("div").length;
		var $div = $closestDiv.parent().find("div").eq(index-1).clone();
		$div.find("option:selected").attr("selected",false);
		$div.find("input[type='radio']").each(function(){
			$(this).attr("name","modeType"+index);
			$(this).attr("checked",false);
		});
		$div.find("input[type='text']").each(function(){
			$(this).val("");
		});
		if(index == 1){
			var html = '&nbsp;&nbsp;<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">';
			$div.append(html);
			$("#priceRuleTd").append($div);
		}else{
			$("#priceRuleTd").append($div);
		}
	});
	
	$("input[name='salesRuleAdd']").live('click',function(){
		var $closestDiv = $(this).closest("div");
		var index = $closestDiv.parent().find("div").length;
		var $div = $closestDiv.parent().find("div").eq(index-1).clone();
		$div.find("option:selected").attr("selected",false);
		$div.find("input[type='text']").each(function(){
			$(this).val("");
		});
		if(index == 1){
			var html = '&nbsp;&nbsp;<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">';
			$div.append(html);
			$("#salesRuleTd").append($div);
		}else{
			$("#salesRuleTd").append($div);
		}
	});
	
	$("input[name='stockRuleAdd']").live('click',function(){
		var $closestDiv = $(this).closest("div");
		var index = $closestDiv.parent().find("div").length;
		var $div = $closestDiv.parent().find("div").eq(index-1).clone();
		$div.find("option:selected").attr("selected",false);
		$div.find("input[type='text']").each(function(){
			$(this).val("");
		});
		if(index == 1){
			var html = '&nbsp;&nbsp;<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">';
			$div.append(html);
			$("#stockRuleTd").append($div);
		}else{
			$("#stockRuleTd").append($div);
		}
	});
	
	$("input[name='salesCycleRuleAdd']").live('click',function(){
		var $closestDiv = $(this).closest("div");
		var index = $closestDiv.parent().find("div").length;
		var $div = $closestDiv.parent().find("div").eq(index-1).clone();
		$div.find("option:selected").attr("selected",false);
		$div.find("input[name='count']").each(function(){
			$(this).val("");
		});
		if(index == 1){
			var html = '&nbsp;&nbsp;<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">';
			$div.append(html);
			$("#salesCycleRuleTd").append($div);
		}else{
			$("#salesCycleRuleTd").append($div);
		}
	});
	
});

function priceRuleChangeProductType(_this){
	var productTypeIdArray = [];
	$("#priceRuleTd").find("option:selected").each(function(){
		if($(this).val()){
			productTypeIdArray.push($(this).val());
		}
	});
	var productTypeId = $(_this).val();
	var idx = $.inArray(productTypeId,productTypeIdArray);
	if(idx<productTypeIdArray.length-1){
		/* commUtil.alertError("同一个类目 在同一个规则内只能被选择一次");
		$(_this).find("option:selected").remove(); */
	}
}

function salesRuleChangeProductType(_this){
	var productTypeIdArray = [];
	$("#salesRuleTd").find("option:selected").each(function(){
		if($(this).val()){
			productTypeIdArray.push($(this).val());
		}
	});
	var productTypeId = $(_this).val();
	var idx = $.inArray(productTypeId,productTypeIdArray);
	if(idx<productTypeIdArray.length-1){
		/* commUtil.alertError("同一个类目 在同一个规则内只能被选择一次");
		$(_this).find("option:selected").remove(); */
	}
}

function save(){
	var id= $("#id").val();
	var priceRules="";
	var priceRuleProductTypeIdArray= [];
	var error = false;
	$("#priceRuleTd").find("div[name='priceRuleDiv']").each(function(){
		if(error){
			return;
		}
		var $_this = $(this);
		var productTypeId = $_this.find("option:selected").val();
		if(productTypeId){
			var idx = $.inArray(productTypeId,priceRuleProductTypeIdArray);
			if(idx>=0){
				error = true;
				commUtil.alertError("同一个类目 在同一个规则内只能被选择一次");
				return;
			}else{
				priceRuleProductTypeIdArray.push(productTypeId);
			}
			var modeType = $_this.find("input[type='radio']:checked").val();
			if(!modeType){
				error = true;
				commUtil.alertError("请选择折扣模式或底价模式");
				return;
			}
			var modeValue = $_this.find("input[type='radio']:checked").next().val();
			if(!modeValue){
				error = true;
				commUtil.alertError("折扣或金额不能为空");
				return;
			}else{
				var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
				if (!reg.test(modeValue)){
					error = true;
					commUtil.alertError("只能输入数字或者小数,当输入小数时，小数点后最多保留2位数字");
					return;
				}
			}
			if(modeType == 0){
				if(parseFloat(modeValue)>10 || parseFloat(modeValue)<0){
					error = true;
					commUtil.alertError("折扣值必须大于0且小于10");
					return;
				}
			}
			if(!priceRules){
				priceRules = productTypeId+"_"+modeType+"_"+parseFloat(modeValue).toFixed(2);
			}else{
				priceRules+=";"+productTypeId+"_"+modeType+"_"+parseFloat(modeValue).toFixed(2);
			}
		}
	});
	var salesRules="";
	var salesRuleProductTypeIdArray= [];
	$("#salesRuleTd").find("div[name='salesRuleDiv']").each(function(){
		if(error){
			return;
		}
		var $_this = $(this);
		var productTypeId = $_this.find("option:selected").val();
		if(productTypeId){
			var idx = $.inArray(productTypeId,salesRuleProductTypeIdArray);
			if(idx>=0){
				error = true;
				commUtil.alertError("同一个类目 在同一个规则内只能被选择一次");
				return;
			}else{
				salesRuleProductTypeIdArray.push(productTypeId);
			}
			var salesVolume = $_this.find("input[name='salesVolume']").val();
			if(!salesVolume){
				error = true;
				commUtil.alertError("商品总销量不能为空");
				return;
			}else{
				var reg = /^[0-9]\d*$/;
				if(!reg.test(salesVolume)){
					error = true;
					commUtil.alertError("商品总销量只能是正整数");
					return;
				}
			}
			if(!salesRules){
				salesRules = productTypeId+"_"+salesVolume;
			}else{
				salesRules+=";"+productTypeId+"_"+salesVolume;
			}
		}
	});
	var stockRules="";
	var stockRuleProductTypeIdArray= [];
	$("#stockRuleTd").find("div[name='stockRuleDiv']").each(function(){
		if(error){
			return;
		}
		var $_this = $(this);
		var productTypeId = $_this.find("option:selected").val();
		if(productTypeId){
			var idx = $.inArray(productTypeId,stockRuleProductTypeIdArray);
			if(idx>=0){
				error = true;
				commUtil.alertError("同一个类目 在同一个规则内只能被选择一次");
				return;
			}else{
				stockRuleProductTypeIdArray.push(productTypeId);
			}
			var stock = $_this.find("input[name='stock']").val();
			if(!stock){
				error = true;
				commUtil.alertError("商品总库存不能为空");
				return;
			}else{
				var reg = /^[0-9]\d*$/;
				if(!reg.test(stock)){
					error = true;
					commUtil.alertError("商品总库存只能是正整数");
					return;
				}
			}
			if(!stockRules){
				stockRules = productTypeId+"_"+stock;
			}else{
				stockRules+=";"+productTypeId+"_"+stock;
			}
		}
	});
	var favorableRate = $("#favorableRate").val();
	if(favorableRate){
		var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		if (!reg.test(favorableRate) || parseFloat(favorableRate)>1){
			error = true;
			commUtil.alertError("好评率为不大于1的两位小数");
			return;
		}
	}
	var shopComment = $("#shopComment").val();
	if(shopComment){
		var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		if(!reg.test(shopComment)){
			commUtil.alertError("店铺评价只能输入数字或者小数,当输入小数时，小数点后最多保留2位数字");
			return;
		}
		if(parseFloat(shopComment)>5){
			commUtil.alertError("店铺评价不能大于5");
			return;
		}
	}
	var salesCycleRules="";
	var salesCycleRuleProductTypeIdArray= [];
	$("#salesCycleRuleTd").find("div[name='salesCycleRuleDiv']").each(function(){
		if(error){
			return;
		}
		var $_this = $(this);
		var productTypeId = $_this.find("option:selected").val();
		if(productTypeId){
			var idx = $.inArray(productTypeId,salesCycleRuleProductTypeIdArray);
			if(idx>=0){
				error = true;
				commUtil.alertError("同一个类目 在同一个规则内只能被选择一次");
				return;
			}else{
				salesCycleRuleProductTypeIdArray.push(productTypeId);
			}
			var day = $_this.find("input[name='day']").val();
			var type = '${activityRuleConfiguration.type}';
			if(type == 3){//限时秒杀
				day = 0;
			}else{
				if(!day){
					error = true;
					commUtil.alertError("商品销售周期不能为空");
					return;
				}else{
					var reg = /^[0-9]\d*$/;
					if(!reg.test(day)){
						error = true;
						commUtil.alertError("商品销售周期只能是数字");
						return;
					}
				}
			}
			var count = $_this.find("input[name='count']").val();
			if(!count){
				error = true;
				commUtil.alertError("品牌报名数不能为空");
				return;
			}else{
				var reg = /^[0-9]\d*$/;
				if(!reg.test(count)){
					error = true;
					commUtil.alertError("品牌报名数只能是数字");
					return;
				}
			}
			if(!salesCycleRules){
				salesCycleRules = productTypeId+"_"+day+"_"+count;
			}else{
				salesCycleRules+=";"+productTypeId+"_"+day+"_"+count;
			}
		}
	});
	var otherRequirements = $("#otherRequirements").val();
	if(error){
		return;
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/singleProductActivity/activityRuleConfiguration/save.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {id:id,priceRules:priceRules,salesRules:salesRules,stockRules:stockRules,favorableRate:favorableRate,shopComment:shopComment,salesCycleRules:salesCycleRules,otherRequirements:otherRequirements},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				commUtil.alertSuccess("保存成功");
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
<form id="violateOrderForm" method="post" action="${pageContext.request.contextPath}/singleProductActivity/activityRuleConfiguration/save.shtml">
	<input type="hidden" id="id" name="id" value="${activityRuleConfiguration.id}">
	<table class="gridtable">
		<tr>
			<td class="title">价格规则</td>
			<td align="left" class="l-table-edit-td" id="priceRuleTd">
			<c:forEach items="${priceRuleList}" var="priceRule" varStatus="idx">
			<div name="priceRuleDiv">
				<select name="productTypeId" onchange="priceRuleChangeProductType(this)">
					<option value="">请选择</option>
	               	<c:forEach items="${productTypeList}" var="productType">
		               	<option value="${productType.id}" <c:if test="${productType.id eq priceRule.productTypeId}">selected="selected"</c:if>>${productType.name}</option>
	               	</c:forEach>
               	</select>
				<input type="radio" name="modeType${idx.index}" value="0" <c:if test="${priceRule.modeType eq 0}">checked="checked"</c:if> onclick="modeType(this);"/>折扣模式&nbsp;&nbsp;
				低于吊牌价
				<input type="text" name="modeValue" <c:if test="${priceRule.modeType eq 0}">value="${priceRule.modeValue}"</c:if>/>折&nbsp;&nbsp;
				<input type="radio" name="modeType${idx.index}" value="1" <c:if test="${priceRule.modeType eq 1}">checked="checked"</c:if> onclick="modeType(this);"/>底价模式&nbsp;&nbsp;
				不高于
				<input type="text" name="modeValue" <c:if test="${priceRule.modeType eq 1}">value="${priceRule.modeValue}"</c:if> />元
				<c:if test="${idx.index == 0}">
					<input type="button" class="l-button l-button-test" name="priceRuleAdd" value="新增" >
				</c:if>
				<c:if test="${idx.index gt 0}">
					<input type="button" class="l-button l-button-test" name="priceRuleAdd" value="新增" >&nbsp;&nbsp;
					<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">
				</c:if>
			</div>
			</c:forEach>
			<c:if test="${empty priceRuleList}">
			<div name="priceRuleDiv">
				<select name="productTypeId" onchange="priceRuleChangeProductType(this)">
					<option value="">请选择</option>
	               	<c:forEach items="${productTypeList}" var="productType">
		               	<option value="${productType.id}">${productType.name}</option>
	               	</c:forEach>
               	</select>
				<input type="radio" name="modeType${idx.index}" value="0" onclick="modeType(this);"/>折扣模式&nbsp;&nbsp;
				低于吊牌价
				<input type="text" name="modeValue" />折&nbsp;&nbsp;
				<input type="radio" name="modeType${idx.index}" value="1" onclick="modeType(this);"/>底价模式&nbsp;&nbsp;
				不高于
				<input type="text" name="modeValue" />元
				<input type="button" class="l-button l-button-test" name="priceRuleAdd" value="新增" >
			</div>
			</c:if>
			</td>
		</tr>
		<tr>
			<td class="title">销量规则</td>
			<td align="left" class="l-table-edit-td" id="salesRuleTd">
			<c:forEach items="${salesRuleList}" var="salesRule" varStatus="idx">
			<div name="salesRuleDiv">	
				<select name="productTypeId" onchange="salesRuleChangeProductType(this)">
					<option value="">请选择</option>
	               	<c:forEach items="${productTypeList}" var="productType">
		               	<option value="${productType.id}" <c:if test="${productType.id eq salesRule.productTypeId}">selected="selected"</c:if>>${productType.name}</option>
	               	</c:forEach>
               	</select>&nbsp;&nbsp;
               	商品总销量高于<input type="text" name="salesVolume" value="${salesRule.salesVolume}">
               	<c:if test="${idx.index == 0}">
					<input type="button" class="l-button l-button-test" name="salesRuleAdd" value="新增" >
				</c:if>
				<c:if test="${idx.index gt 0}">
					<input type="button" class="l-button l-button-test" name="salesRuleAdd" value="新增" >&nbsp;&nbsp;
					<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">
				</c:if>
            </div>
            </c:forEach>
            <c:if test="${empty salesRuleList}">
            <div name="salesRuleDiv">	
				<select name="productTypeId">
					<option value="">请选择</option>
	               	<c:forEach items="${productTypeList}" var="productType">
		               	<option value="${productType.id}" >${productType.name}</option>
	               	</c:forEach>
               	</select>&nbsp;&nbsp;
               	商品总销量高于<input type="text" name="salesVolume" value="${salesRule.salesVolume}">
				<input type="button" class="l-button l-button-test" name="salesRuleAdd" value="新增" >
            </div>
            </c:if>   	
			</td>
		</tr>
		<tr>
			<td class="title">库存规则</td>
			<td align="left" class="l-table-edit-td" id="stockRuleTd">
			<c:forEach items="${stockRuleList}" var="stockRule" varStatus="idx">
			<div name="stockRuleDiv">
				<select id="productTypeId" name="productTypeId">
					<option value="">请选择</option>
	               	<c:forEach items="${productTypeList}" var="productType">
		               	<option value="${productType.id}" <c:if test="${productType.id eq stockRule.productTypeId}">selected="selected"</c:if>>${productType.name}</option>
	               	</c:forEach>
               	</select>&nbsp;&nbsp;
               	商品总库存高于<input type="text" name="stock" value="${stockRule.stock}">
               	<c:if test="${idx.index == 0}">
					<input type="button" class="l-button l-button-test" name="stockRuleAdd" value="新增" >
				</c:if>
				<c:if test="${idx.index gt 0}">
					<input type="button" class="l-button l-button-test" name="stockRuleAdd" value="新增" >&nbsp;&nbsp;
					<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">
				</c:if>
            </div>
            </c:forEach>
            <c:if test="${empty stockRuleList}">
            <div name="stockRuleDiv">
				<select id="productTypeId" name="productTypeId">
					<option value="">请选择</option>
	               	<c:forEach items="${productTypeList}" var="productType">
		               	<option value="${productType.id}" >${productType.name}</option>
	               	</c:forEach>
               	</select>&nbsp;&nbsp;
               	商品总库存高于<input type="text" name="stock" value="${stockRule.stock}">
				<input type="button" class="l-button l-button-test" name="stockRuleAdd" value="新增" >
            </div>
            </c:if>   	
			</td>
		</tr>
		<tr>
			<td class="title">好评率</td>
			<td align="left" class="l-table-edit-td">
				不低于<input type="text" id="favorableRate" name="favorableRate" value="${activityRuleConfiguration.favorableRate}" />&nbsp;&nbsp;不大于1的两位小数
			</td>
		</tr>
		<tr>
			<td class="title">店铺评价</td>
			<td align="left" class="l-table-edit-td">
				不低于<input type="text" id="shopComment" name="shopComment" value="${activityRuleConfiguration.shopComment}" />&nbsp;&nbsp;不大于5数值，保留两位小数
			</td>
		</tr>
		
		<c:if test="${activityRuleConfiguration.type ne 11 && activityRuleConfiguration.type ne 12 && activityRuleConfiguration.type ne 13 && activityRuleConfiguration.type ne 14 && activityRuleConfiguration.type ne 15 && activityRuleConfiguration.type ne 16 && activityRuleConfiguration.type ne 17 }">
		<tr>
			<c:if test="${activityRuleConfiguration.type ne 3}">
			<td class="title">销售周期及品牌报名数</td>
			<td align="left" class="l-table-edit-td" id="salesCycleRuleTd">
				<c:forEach items="${salesCycleRuleList}" var="salesCycleRule" varStatus="idx">
				<div name="salesCycleRuleDiv">
					<select id="productTypeId" name="productTypeId">
						<option value="">请选择</option>
		               	<c:forEach items="${productTypeList}" var="productType">
			               	<option value="${productType.id}" <c:if test="${productType.id eq salesCycleRule.productTypeId}">selected="selected"</c:if>>${productType.name}</option>
		               	</c:forEach>
	               	</select>&nbsp;&nbsp;
	               	商品销售周期<input type="text" name="day" value="${salesCycleRule.day}">天&nbsp;&nbsp;品牌报名数<input type="text" name="count" value="${salesCycleRule.count}">
	           		<c:if test="${idx.index == 0}">
						<input type="button" class="l-button l-button-test" name="salesCycleRuleAdd" value="新增" >
					</c:if>
					<c:if test="${idx.index gt 0}">
						<input type="button" class="l-button l-button-test" name="salesCycleRuleAdd" value="新增" >&nbsp;&nbsp;
						<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">
					</c:if>
	           	</div>
	            </c:forEach>
	            <c:if test="${empty salesCycleRuleList}">
	            <div name="salesCycleRuleDiv">
					<select id="productTypeId" name="productTypeId">
						<option value="">请选择</option>
		               	<c:forEach items="${productTypeList}" var="productType">
			               	<option value="${productType.id}" >${productType.name}</option>
		               	</c:forEach>
	               	</select>&nbsp;&nbsp;
	               	商品销售周期<input type="text" name="day" value="">天&nbsp;&nbsp;品牌报名数<input type="text" name="count" value="">
					<input type="button" class="l-button l-button-test" name="salesCycleRuleAdd" value="新增" >
	           	</div>
	            </c:if>
			</td>
            </c:if>
            <c:if test="${activityRuleConfiguration.type eq 3}">
            <td class="title">销售周期及品牌报名数</td>
            <td align="left" class="l-table-edit-td" id="salesCycleRuleTd">
            	<c:forEach items="${salesCycleRuleList}" var="salesCycleRule" varStatus="idx">
				<div name="salesCycleRuleDiv">
					<select id="productTypeId" name="productTypeId">
						<option value="">请选择</option>
		               	<c:forEach items="${productTypeList}" var="productType">
			               	<option value="${productType.id}" <c:if test="${productType.id eq salesCycleRule.productTypeId}">selected="selected"</c:if>>${productType.name}</option>
		               	</c:forEach>
	               	</select>&nbsp;&nbsp;
	               	商品销售周期：<input type="text" readonly="readonly" value="与秒杀间隔相同">&nbsp;&nbsp;品牌报名数<input type="text" name="count" value="${salesCycleRule.count}">
	           		<c:if test="${idx.index == 0}">
						<input type="button" class="l-button l-button-test" name="salesCycleRuleAdd" value="新增" >
					</c:if>
					<c:if test="${idx.index gt 0}">
						<input type="button" class="l-button l-button-test" name="salesCycleRuleAdd" value="新增" >&nbsp;&nbsp;
						<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">
					</c:if>
	           	</div>
	            </c:forEach>
	            <c:if test="${empty salesCycleRuleList}">
	            <div name="salesCycleRuleDiv">
					<select id="productTypeId" name="productTypeId">
						<option value="">请选择</option>
		               	<c:forEach items="${productTypeList}" var="productType">
			               	<option value="${productType.id}" >${productType.name}</option>
		               	</c:forEach>
	               	</select>&nbsp;&nbsp;
	               	商品销售周期：<input type="text" readonly="readonly" value="与秒杀间隔相同">&nbsp;&nbsp;品牌报名数<input type="text" name="count" value="">
					<input type="button" class="l-button l-button-test" name="salesCycleRuleAdd" value="新增" >
	           	</div>
	            </c:if>
            </td>
            </c:if>   	
		</tr>
		</c:if> 
		
		<tr>
			<td class="title">其他要求</td>
			<td align="left" class="l-table-edit-td">
				<textarea id="otherRequirements" name="otherRequirements" rows="4" cols="30" class="text" maxlength="256">${activityRuleConfiguration.otherRequirements}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title">操作</td>
			<td>
				<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />&nbsp;&nbsp;
				<input type="button" class="l-button l-button-submit" value="提交" onclick="save();"/>
			</td>
		</tr>
	</table>	
</form>		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
