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
		
	});
	
	function customValidate() {
		var flag = true;
		$(".custom-validate").remove();
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
				submitFlag = false;
				$("#form1").submit();
			}
		}
	}
	
	//开始计算
	function showResult() {
		var validateFlag = customValidate();
		if(validateFlag && submitFlag) {
			if(submitCustomValidate()) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/cutPriceProduct/showResult.shtml",
					 data : $("#form1").serialize(),
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.statusCode != 200)
							 commUtil.alertError(data.message);
						 else{
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
	
	//方案计算金额
	function cutPriceCnfCountPrice(obj) {
		var singleProductActivityId = $("[name='singleProductActivityId']").val();
		if(singleProductActivityId != '' ) {
			var price = $(obj).val();
			if(price != '' ) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/cutPriceProduct/cutPriceCnfCountPrice.shtml",
					 data : {singleProductActivityId : singleProductActivityId, price : price},
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
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/cutPriceProduct/addOrUpdateCutPriceCnf.shtml" >
		<input type="hidden" name="singleProductActivityId" value="${singleProductActivityId }" />
		<input type="hidden" name="cutPriceCnfId" value="${cutPriceCnf.id }" />
		<input type="hidden" name="oneCutPriceCnfDtlId" value="${oneCutPriceCnfDtl.id }" />
		<input type="hidden" name="twoCutPriceCnfDtlId" value="${twoCutPriceCnfDtl.id }" />
		<input type="hidden" name="threeCutPriceCnfDtlId" value="${threeCutPriceCnfDtl.id }" />
		<input type="hidden" name="fourCutPriceCnfDtlId" value="${fourCutPriceCnfDtl.id }" />
		<input type="hidden" name="fiveCutPriceCnfDtlId" value="${fiveCutPriceCnfDtl.id }" />
		<input type="hidden" name="sixCutPriceCnfDtlId" value="${sixCutPriceCnfDtl.id }" />
		<input type="hidden" name="sevenCutPriceCnfDtlId" value="${sevenCutPriceCnfDtl.id }" />
		<input type="hidden" name="scCutPriceCnfDtlId" value="${scCutPriceCnfDtl.id }" />
		<input type="hidden" name="jcCutPriceCnfDtlId" value="${jcCutPriceCnfDtl.id }" />
		
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">用户首次分享砍价比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="scBeginRate" class="validate-tipRate" name="scBeginRate" value="${scCutPriceCnfDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
					~
					<input id="scEndRate" class="validate-tipRate" name="scEndRate" value="${scCutPriceCnfDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="oneBeginAmount" name="oneBeginAmount" type="hidden" value="${oneCutPriceCnfDtl.beginAmount }" />
					<input id="oneEndAmount" class="validate-tipRate" name="oneEndAmount" value="${oneCutPriceCnfDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="oneBeginRate" class="validate-tipRate" name="oneBeginRate" value="${oneCutPriceCnfDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
					~
					<input id="oneEndRate" class="validate-tipRate" name="oneEndRate" value="${oneCutPriceCnfDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="twoEndAmount" class="validate-tipRate" name="twoEndAmount" value="${twoCutPriceCnfDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="twoBeginRate" class="validate-tipRate" name="twoBeginRate" value="${twoCutPriceCnfDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
					~
					<input id="twoEndRate" class="validate-tipRate" name="twoEndRate" value="${twoCutPriceCnfDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="threeEndAmount" class="validate-tipRate" name="threeEndAmount" value="${threeCutPriceCnfDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="threeBeginRate" class="validate-tipRate" name="threeBeginRate" value="${threeCutPriceCnfDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
					~
					<input id="threeEndRate" class="validate-tipRate" name="threeEndRate" value="${threeCutPriceCnfDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="fourEndAmount" class="validate-tipRate" name="fourEndAmount" value="${fourCutPriceCnfDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="fourBeginRate" class="validate-tipRate" name="fourBeginRate" value="${fourCutPriceCnfDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
					~
					<input id="fourEndRate" class="validate-tipRate" name="fourEndRate" value="${fourCutPriceCnfDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="fiveEndAmount" class="validate-tipRate" name="fiveEndAmount" value="${fiveCutPriceCnfDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="fiveBeginRate" class="validate-tipRate" name="fiveBeginRate" value="${fiveCutPriceCnfDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
					~
					<input id="fiveEndRate" class="validate-tipRate" name="fiveEndRate" value="${fiveCutPriceCnfDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="sixEndAmount" class="validate-tipRate" name="sixEndAmount" value="${sixCutPriceCnfDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="sixBeginRate" class="validate-tipRate" name="sixBeginRate" value="${sixCutPriceCnfDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
					~
					<input id="sixEndRate" class="validate-tipRate" name="sixEndRate" value="${sixCutPriceCnfDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">砍价累计金额小于等于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="sevenEndAmount" class="validate-tipRate" name="sevenEndAmount" value="${sevenCutPriceCnfDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">砍价金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="sevenBeginRate" class="validate-tipRate" name="sevenBeginRate" value="${sevenCutPriceCnfDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
					~
					<input id="sevenEndRate" class="validate-tipRate" name="sevenEndRate" value="${sevenCutPriceCnfDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">其他情况签到获得金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="jcBeginRate" class="validate-tipRate" name="jcBeginRate" value="${jcCutPriceCnfDtl.beginRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
					~
					<input id="jcEndRate" class="validate-tipRate" name="jcEndRate" value="${jcCutPriceCnfDtl.endRate }" type="text" style="width:80px;" onchange="cutPriceCnfCountPrice(this);" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input class="l-button" type="button" value="开始计算" onclick="showResult();" />
					<input style="margin-left: 20px;" type="button" class="l-button" value="提交" onclick="submitFun();" /> 
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>