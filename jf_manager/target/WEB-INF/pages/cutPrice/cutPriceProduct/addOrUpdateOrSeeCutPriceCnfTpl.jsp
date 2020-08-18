<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

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
 	
</style>
<script type="text/javascript">
	var submitFlag = true;
	$(function(){
		$("input[class^='validate-']").bind('change', function(){
			if($(".custom-validate").html() != null) {
				if(customValidate()) {
					submitCustomValidate();
				}
			}
		});
		$("#beginPrice, #endPrice").bind('change', function(){
			$(".count-price").html('');
		});
		
		
	});
	
	function customValidate() {
		var flag = true;
		$(".custom-validate").remove();
		$(".validate-tipName").each(function(){
			var tipName = $(this).val();
			if(tipName == '') {
				$(this).parent().append('<span class="custom-validate" style="color: red;">该字段不能为空</span>');
				flag = false;
			}
		});
		$(".validate-tipPrice").each(function(){
			var tipPrice = $(this).val();
			if(tipPrice == '') {
				$(this).parent().append('<span class="custom-validate" style="color: red;">该字段不能为空</span>');
				flag = false;
			}else if(!(/^(([1-9]\d*)|0)(\.[0-9]{0,2})?$/).test(tipPrice)) {
				$(this).parent().append('<span class="custom-validate" style="color: red;">请输入一个数字最多保留两位小数</span>');
				flag = false;
			}
		});
		$(".validate-tipRate").each(function(){
			var tipRate = $(this).val();
			if($(this).parent().find(".custom-validate").html() == null) {
				if(tipRate == '') {
					$(this).parent().append('<span class="custom-validate" style="color: red;">该字段不能为空</span>');
					flag = false;
				}else if(!(/(^[1-9]\d*(\.[0-9]{0,2})?$)|(^0\.[0-9]?([1-9]{1})$)|(^0\.[1-9]{1}([0-9]?)$)/).test(tipRate)) {
					$(this).parent().append('<span class="custom-validate" style="color: red;">请输入一个大于0最多保留两位小数</span>');
					flag = false;
				}else if(Number(tipRate) > Number(9999)) {
					$(this).parent().append('<span class="custom-validate" style="color: red;">请输入一个最大为 9999 的值</span>');
					flag = false;
				}
			}
		});
		$(".validate-tipInviteLimit").each(function(){
			var tipAmount = $(this).val();
			if(tipAmount == '') {
				$(this).parent().append('<span class="custom-validate" style="color: red;">该字段不能为空</span>');
				flag = false;
			}else if(!(/^[1-9]\d*$/).test(tipAmount)) {
				$(this).parent().append('<span class="custom-validate" style="color: red;">请输入一个正整数</span>');
				flag = false;
			}
		});
		return flag;
	}
	
	function submitCustomValidate() {
		$(".custom-validate").remove();
		var flag = true;
		var oneEndAmount = $("#oneEndAmount").val();
		var twoEndAmount = $("#twoEndAmount").val();
		var threeEndAmount = $("#threeEndAmount").val();
		var fourEndAmount = $("#fourEndAmount").val();
		var fiveEndAmount = $("#fiveEndAmount").val();
		var sixEndAmount = $("#sixEndAmount").val();
		var sevenEndAmount = $("#sevenEndAmount").val();
		$(".validate-tipPrice").each(function(){
			if($(this).parent().find(".custom-validate").html() == null) {
				var beginPrice = $(this).parent().find(".validate-tipPrice:first").val();
				var endinPrice = $(this).parent().find(".validate-tipPrice:last").val();
				if(Number(beginPrice) > Number(endinPrice)) {
					$(this).parent().append('<span class="custom-validate" style="color: red;">结束价格不得小于起始价格</span>');
					flag = false;
				}
			}
		});
		if(Number(oneEndAmount) >= Number(twoEndAmount)) {
			$("#twoEndAmount").parent().append('<span class="custom-validate" style="color: red;">请输入一个大于'+oneEndAmount+'的正整数</span>');
			flag = false;
		}
		if(Number(twoEndAmount) >= Number(threeEndAmount)) {
			$("#threeEndAmount").parent().append('<span class="custom-validate" style="color: red;">请输入一个大于'+twoEndAmount+'的正整数</span>');
			flag = false;
		}
		if(Number(threeEndAmount) >= Number(fourEndAmount)) {
			$("#fourEndAmount").parent().append('<span class="custom-validate" style="color: red;">请输入一个大于'+threeEndAmount+'的正整数</span>');
			flag = false;
		}
		if(Number(fourEndAmount) >= Number(fiveEndAmount)) {
			$("#fiveEndAmount").parent().append('<span class="custom-validate" style="color: red;">请输入一个大于'+fourEndAmount+'的正整数</span>');
			flag = false;
		}
		if(Number(fiveEndAmount) >= Number(sixEndAmount)) {
			$("#sixEndAmount").parent().append('<span class="custom-validate" style="color: red;">请输入一个大于'+fiveEndAmount+'的正整数</span>');
			flag = false;
		}
		if(Number(sixEndAmount) >= Number(sevenEndAmount)) {
			$("#sevenEndAmount").parent().append('<span class="custom-validate" style="color: red;">请输入一个大于'+sixEndAmount+'的正整数</span>');
			flag = false;
		}
		$(".validate-tipRate").each(function(){
			if($(this).parent().find(".custom-validate").html() == null) {
				var beginRate = $(this).parent().find(".validate-tipRate:first").val();
				var endinRate = $(this).parent().find(".validate-tipRate:last").val();
				if(Number(beginRate) > Number(endinRate)) {
					$(this).parent().append('<span class="custom-validate" style="color: red;">结束比例不得小于起始比例</span>');
					flag = false;
				}
			}
		});
		return flag;
	}
	
	function submitFun(){
		var validateFlag = customValidate();
		if(validateFlag && submitFlag) {
			if(submitCustomValidate()) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/cutPriceProduct/validatePrice.shtml",
					 data : $("#form1").serialize(),
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.statusCode != 200) {
							 commUtil.alertError(data.message);
						 }else if(data.flag){
							 commUtil.alertError("模版商品价格区间，不能重叠！");							 
						 }else {
							 submitFlag = false;
						 	 $("#form1").submit(); 
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}
		}
	}
	
	//开始计算
	function showCutPriceCnfTplResult() {
		var validateFlag = customValidate();
		if(validateFlag && submitFlag) {
			if(submitCustomValidate()) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/cutPriceProduct/showCutPriceCnfTplResult.shtml",
					 data : $("#form1").serialize(),
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.statusCode != 200) {
							 commUtil.alertError(data.message);
						 }else{
							 $("#oneBeginRate").parent().append('<span class="custom-validate" style="color: red;">本阶段预计砍价'+ data.mapMin.oneNum + '-' + data.mapMax.oneNum +'人</span>');
							 $("#twoBeginRate").parent().append('<span class="custom-validate" style="color: red;">本阶段预计砍价'+ data.mapMin.twoNum + '-' + data.mapMax.twoNum +'人</span>');
							 $("#threeBeginRate").parent().append('<span class="custom-validate" style="color: red;">本阶段预计砍价'+ data.mapMin.threeNum + '-' + data.mapMax.threeNum +'人</span>');
							 $("#fourBeginRate").parent().append('<span class="custom-validate" style="color: red;">本阶段预计砍价'+ data.mapMin.fourNum + '-' + data.mapMax.fourNum +'人</span>');
							 $("#fiveBeginRate").parent().append('<span class="custom-validate" style="color: red;">本阶段预计砍价'+ data.mapMin.fiveNum + '-' + data.mapMax.fiveNum +'人</span>');
							 $("#sixBeginRate").parent().append('<span class="custom-validate" style="color: red;">本阶段预计砍价'+ data.mapMin.sixNum + '-' + data.mapMax.sixNum +'人</span>');
							 $("#sevenBeginRate").parent().append('<span class="custom-validate" style="color: red;">本阶段预计砍价'+ data.mapMin.sevenNum + '-' + data.mapMax.sevenNum +'人</span>');
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}
		}
	}
	
	//模版计算金额
	function cutPriceCnfTplCountPrice(obj) {
		var beginPrice = $("#beginPrice").val();
		var endPrice = $("#endPrice").val();
		if(beginPrice != '' && endPrice != '' ) {
			var price = $(obj).val();
			if(price != '' ) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/cutPriceProduct/cutPriceCnfTplCountPrice.shtml",
					 data : {beginPrice : beginPrice, endPrice : endPrice, price : price},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.statusCode != 200) {
							 commUtil.alertError(data.message);
						 }else{
							 if($(obj).next(".count-price").html() != null) {
								 $(obj).next(".count-price").html('('+ data.price +')');
							 }else {
								 $(obj).after('<span class="count-price" style="color: red;">('+ data.price +')</span>');
							 }
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}else {
				if($(obj).next(".count-price").html() != null) {
					$(obj).next(".count-price").html('');
				}
			}
		}
	}
	
	
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/cutPriceProduct/addOrUpdateCutPriceCnfTpl.shtml" >
		<input type="hidden" name="cutPriceCnfTplId" value="${cutPriceCnfTpl.id }" />
		<input type="hidden" name="oneCutPriceCnfTplDtlId" value="${oneCutPriceCnfTplDtl.id }" />
		<input type="hidden" name="twoCutPriceCnfTplDtlId" value="${twoCutPriceCnfTplDtl.id }" />
		<input type="hidden" name="threeCutPriceCnfTplDtlId" value="${threeCutPriceCnfTplDtl.id }" />
		<input type="hidden" name="fourCutPriceCnfTplDtlId" value="${fourCutPriceCnfTplDtl.id }" />
		<input type="hidden" name="fiveCutPriceCnfTplDtlId" value="${fiveCutPriceCnfTplDtl.id }" />
		<input type="hidden" name="sixCutPriceCnfTplDtlId" value="${sixCutPriceCnfTplDtl.id }" />
		<input type="hidden" name="sevenCutPriceCnfTplDtlId" value="${sevenCutPriceCnfTplDtl.id }" />
		<input type="hidden" name="scCutPriceCnfTplDtlId" value="${scCutPriceCnfTplDtl.id }" />
		<input type="hidden" name="jcCutPriceCnfTplDtlId" value="${jcCutPriceCnfTplDtl.id }" />
		
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">方案名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="name" class="validate-tipName" name="name" value="${cutPriceCnfTpl.name }" type="text" style="width:180px;" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">商品价格区间<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="beginPrice" class="validate-tipPrice" name="beginPrice" value="${beginPrice }" type="text" style="width:120px;" />
					~
					<input id="endPrice" class="validate-tipPrice" name="endPrice" value="${endPrice }" type="text" style="width:120px;" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">用户首次分享砍价比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="scBeginRate" class="validate-tipRate" name="scBeginRate" value="${scCutPriceCnfTplDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
					~
					<input id="scEndRate" class="validate-tipRate" name="scEndRate" value="${scCutPriceCnfTplDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="oneBeginAmount" name="oneBeginAmount" type="hidden" value="${oneCutPriceCnfTplDtl.beginAmount }" />
					<input id="oneEndAmount" class="validate-tipRate" name="oneEndAmount" value="${oneCutPriceCnfTplDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="oneBeginRate" class="validate-tipRate" name="oneBeginRate" value="${oneCutPriceCnfTplDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
					~
					<input id="oneEndRate" class="validate-tipRate" name="oneEndRate" value="${oneCutPriceCnfTplDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="twoEndAmount" class="validate-tipRate" name="twoEndAmount" value="${twoCutPriceCnfTplDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="twoBeginRate" class="validate-tipRate" name="twoBeginRate" value="${twoCutPriceCnfTplDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
					~
					<input id="twoEndRate" class="validate-tipRate" name="twoEndRate" value="${twoCutPriceCnfTplDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="threeEndAmount" class="validate-tipRate" name="threeEndAmount" value="${threeCutPriceCnfTplDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="threeBeginRate" class="validate-tipRate" name="threeBeginRate" value="${threeCutPriceCnfTplDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
					~
					<input id="threeEndRate" class="validate-tipRate" name="threeEndRate" value="${threeCutPriceCnfTplDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="fourEndAmount" class="validate-tipRate" name="fourEndAmount" value="${fourCutPriceCnfTplDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="fourBeginRate" class="validate-tipRate" name="fourBeginRate" value="${fourCutPriceCnfTplDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
					~
					<input id="fourEndRate" class="validate-tipRate" name="fourEndRate" value="${fourCutPriceCnfTplDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="fiveEndAmount" class="validate-tipRate" name="fiveEndAmount" value="${fiveCutPriceCnfTplDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="fiveBeginRate" class="validate-tipRate" name="fiveBeginRate" value="${fiveCutPriceCnfTplDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
					~
					<input id="fiveEndRate" class="validate-tipRate" name="fiveEndRate" value="${fiveCutPriceCnfTplDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="sixEndAmount" class="validate-tipRate" name="sixEndAmount" value="${sixCutPriceCnfTplDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="sixBeginRate" class="validate-tipRate" name="sixBeginRate" value="${sixCutPriceCnfTplDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
					~
					<input id="sixEndRate" class="validate-tipRate" name="sixEndRate" value="${sixCutPriceCnfTplDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="sevenEndAmount" class="validate-tipRate" name="sevenEndAmount" value="${sevenCutPriceCnfTplDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="sevenBeginRate" class="validate-tipRate" name="sevenBeginRate" value="${sevenCutPriceCnfTplDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
					~
					<input id="sevenEndRate" class="validate-tipRate" name="sevenEndRate" value="${sevenCutPriceCnfTplDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">其他情况签到获得金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="jcBeginRate" class="validate-tipRate" name="jcBeginRate" value="${jcCutPriceCnfTplDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
					~
					<input id="jcEndRate" class="validate-tipRate" name="jcEndRate" value="${jcCutPriceCnfTplDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfTplCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input class="l-button" type="button" value="开始计算" onclick="showCutPriceCnfTplResult();" />
					<c:if test="${empty flag }">
						<input style="margin-left: 20px;" type="button" class="l-button" value="提交" onclick="submitFun();" /> 
					</c:if>
					<c:if test="${not empty flag and flag eq '1'}">
						<input style="margin-left: 20px;" type="button" class="l-button" value="提交" onclick="submitFun();" /> 
					</c:if>
					<c:if test="${not empty flag and flag eq '2'}">
						<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
					</c:if>
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>