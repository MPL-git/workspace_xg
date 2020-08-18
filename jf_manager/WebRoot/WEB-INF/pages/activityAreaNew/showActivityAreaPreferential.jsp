<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
	 <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
 	.radioClass{margin: 0 10px 0 10px;}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
</style>
<script type="text/javascript">
	$(function(){
// 		$(".dateEditor").ligerDateEditor({
// 			showTime : true,
// 			format: "yyyy-MM-dd hh:mm:ss",
// 			labelAlign : 'left',
// 			width : 160
// 		});
		
		var preferentialType = '${activityArea.preferentialType }';
		var status = '${activityArea.status }';
		var couponList = '';
		<c:if test="${not empty couponList}">
			couponList = ${couponList }
		</c:if>
		preferentialCoupon(couponList, preferentialType);
		if(preferentialType == '1') { //优惠券
			$(".couponClass").show();
	 		$(".fullCutId").hide();
		 	$(".fullOfGiftsTr").hide();
		 	$(".fullDiscountTr").hide();
		}else if(preferentialType == '2') { //满减
			$(".fullCutId").show();
	 		$(".couponClass").hide();
		 	$(".fullOfGiftsTr").hide();
		 	$(".fullDiscountTr").hide();
		 	<c:if test="${not empty fullCut}">
		 		if(${fullCut.ladderFlag == 0}){
		 			$(".ladderIsNot").show();
		 			$(".ladderIs").hide();
		 		}
		 		if(${fullCut.ladderFlag == 1}){
		 			$(".ladderIsNot").hide();
		 			$(".ladderIs").show();
		 		}
		 	</c:if>
		 	<c:if test="${not empty fullCutList}">
		 		preferentialFullCut(${fullCutList });
			</c:if>
		}else if(preferentialType == '3'){ //满赠
			$(".fullOfGiftsTr").show();
		 	$(".couponClass").hide();
		 	$(".fullCutId").hide();	
			$(".fullDiscountTr").hide();
			
			//满赠
			<c:if test="${not empty fullGive}">
			 	if(${fullGive.type==1}){
			 		$(".ofGifts").show();
			 	}else{
			 		$(".ofGifts").hide();
			 	}
			 	if(${fullGive.couponFlag==1}){
			 		$("#coupon_id_group").show();
			 		$("#choice").show();
			 	}
			 	if(${fullGive.integralFlag==1}){
			 		$("#integralSamp").show();
			 	}
		 	</c:if>
		}else if(preferentialType == '4') { //多买多送
			$(".fullDiscountTr").show();
	 		$(".couponClass").hide();
		 	$(".fullCutId").hide();
		 	$(".fullOfGiftsTr").hide();
		 	//多买多送
		 	var objFullDiscount = "";
			<c:if test="${not empty fullDiscountList}">
			 	objFullDiscount = ${fullDiscountList};
			 	$(".Discount1").hide();
				$(".Discount2").hide();
				$(".Discount3").show();
				$(".Discount4").hide();
			</c:if>
			<c:if test="${not empty fullDiscount}">
			 	if(${fullDiscount.type==1}){
			 		$(".Discount1").show();
			 		$(".Discount2").hide();
					$(".Discount3").hide();
					$(".Discount4").hide();
			 	}
			 	if(${fullDiscount.type==2}){
			 		$(".Discount1").hide();
					$(".Discount2").show();
					$(".Discount3").hide();
					$(".Discount4").hide();
			 	}
			 	if(${fullDiscount.type==4}){
			 		$(".Discount1").hide();
					$(".Discount2").hide();
					$(".Discount3").hide();
					$(".Discount4").show();
			 	}
			</c:if>
			if(objFullDiscount != '') {
				var obj = objFullDiscount;
				for(var i=0;i<obj.length;i++){
					var html = '<div class="full_discount_list_div'+i+' full_discount_list_loop">'
							+ '<span class="radioClass">满&nbsp;<input border="none" style="width: 50px;" type="text" value="'+obj[i].fullGiscountThree+'" id="fullOf'+i+'" name="fullOf'+i+'">&nbsp;件&nbsp;&nbsp;</span>'
							+ '<span class="radioClass"><input border="none" style="width: 50px;" type="text" value="'+obj[i].fullGiscountThreeName+'" id="fullGifts'+i+'" name="fullGifts'+i+'">&nbsp;折</span>'
							+ '&nbsp;<input type="button" id="discountDel'+i+'" style="color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="fullDiscountDel('+i+');"  value="-">'
							+ '</div><br class="full_discount_br'+i+'" >';
					$(".full_discount_list").append(html);
					if(i != 0){
						var k = i-1;
						$("#discountDel"+k).hide();
					}else{
						$("#discountDel"+i).hide();
					}
					if(i == obj.length-1 && obj.length < 3) {
						$(".full_discount_list").append('<span id="addFullDiscount" class="radioClass"><input type="button" style="background-color: #dddddd;padding: 3px;" onclick="fullDiscountAdd('+i+');" value="再加1条"/></span>');
					}
				}
			}
		}
		
    	$("body").find("input").not("#button-close").attr("disabled", "disabled");
		
	});
	
	//优惠卷
	function preferentialCoupon(couponList, preferentialType) {
		if(couponList != '' && preferentialType == 1) {
			for(var i=0; i<couponList.length; i++) {
				var html = '<div class="couponDiv'+i+' coupon_list"><input type="hidden" id="couponId" name="couponId" value="'+couponList[i].id+'"/>'
						+ '面额&nbsp;<input type="text" id="money" name="money" style="width: 50px;" value="'+couponList[i].money+'">元&nbsp; &nbsp;'
						+ '使用条件&nbsp;<input type="text" id="minimum" name="minimum" style="width: 50px;" value="'+couponList[i].minimum+'"/>元&nbsp; &nbsp;'
						+ '发行量&nbsp;<input type="text" id="grantQuantity" name="grantQuantity" style="width: 50px;" value="'+couponList[i].grantQuantity+'"/>张&nbsp; &nbsp;'
						+ '需&nbsp;<input id="needIntegral'+i+'" name="needIntegral" type="text" style="width:50px;" value="'+couponList[i].needIntegral+'"/>积分兑换&nbsp;';
						if (couponList[i].recType=='4') {
							html += '&nbsp;<input id="recType1'+i+'" name="recType1" type="checkbox" style="width:20px;" value="'+couponList[i].recType+'" checked="checked" />限SVIP领取&nbsp;';			
						}else {
							html += '&nbsp;<input id="recType1'+i+'" name="recType1" type="checkbox" style="width:20px;" value="4" />限SVIP领取&nbsp;';
						}
					html+='&nbsp;<input type="button" id="del'+i+'" style="color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="couponDel('+i+');"  value="-">'
						+ '</div><br class="couponBr'+i+'" >';
				$(".couponList").append(html);
				if(i != 0) {
					var k = i-1;
					$("#del"+k).hide();
				}else {
					$("#del"+i).hide();
				}
				if(i == couponList.length-1 && couponList.length < 3) {
					$(".couponList").append('<input type="button" id="addCoupon" style="background-color: #dddddd;padding: 3px;" onclick="couponAdd('+i+');"  value="再加1条">');
				}
			}
		}else {	
			var i = 0;
			var html = '<div class="couponDiv'+i+' coupon_list"><input type="hidden" id="couponId" name="couponId" value=""/>'
					+ '面额&nbsp;<input type="text" id="money" name="money" style="width: 50px;" value="">元&nbsp; &nbsp;'
					+ '使用条件&nbsp;<input type="text" id="minimum" name="minimum" style="width: 50px;" value=""/>元&nbsp; &nbsp;'
					+ '发行量&nbsp;<input type="text" id="grantQuantity" name="grantQuantity" style="width: 50px;" value=""/>张&nbsp;'
					+ '需&nbsp;<input id="needIntegral'+i+'" name="needIntegral" type="text" style="width:50px;" value="0"/>积分兑换&nbsp;'	
					+ '&nbsp;<input id="recType1'+i+'" name="recType1" type="checkbox" style="width:20px;" value="4"/>限SVIP领取&nbsp;'
					+ '</div><br class="couponBr'+i+'" >'
					+ '<input type="button" id="addCoupon" style="background-color: #dddddd;padding: 3px;" onclick="couponAdd('+i+');"  value="再加1条">';
			$(".couponList").append(html);
			$("#recEach").hide();
		}
	}
	
	//添加一个div
	function couponAdd(i){
		i = i+1;
		var addCouponHtml = '<input type="button" id="addCoupon" style="background-color: #dddddd;padding: 3px;" onclick="couponAdd('+i+');"  value="再加1条">';
		if($(".couponList").find("div").length >= 2){
			addCouponHtml = '';
		}
		var html = '<div class="couponDiv'+i+' coupon_list"><input type="hidden" id="couponId" name="couponId" value=""/>'
				+ '面额&nbsp;<input type="text" id="money" name="money" style="width: 50px;" value="">元&nbsp; &nbsp;'
				+ '使用条件&nbsp;<input type="text" id="minimum" name="minimum" style="width: 50px;" value=""/>元&nbsp; &nbsp;'
				+ '发行量&nbsp;<input type="text" id="grantQuantity" name="grantQuantity" style="width: 50px;" value=""/>张&nbsp;'
				+ '需&nbsp;<input id="needIntegral'+i+'" name="needIntegral" type="text" style="width:50px;" value="0"/>积分兑换&nbsp;'	
				+ '&nbsp;<input id="recType1'+i+'" name="recType1" type="checkbox" style="width:20px;" value="4"/>限SVIP领取&nbsp;'
				+ '&nbsp;<input type="button" id="del'+i+'" style="color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="couponDel('+i+');"  value="-">'
				+ '</div><br class="couponBr'+i+'" >';
		$(".couponList").find("#addCoupon").remove();
		$(".couponList").append(html+addCouponHtml);
		var k = i-1;
		$("#del"+k).hide();
	}
	
	//删除一个div
	function couponDel(y){
		$(".couponDiv"+y).remove();
		$(".couponBr"+y).remove();
		var j = y-1;
		if(j != 0){
			$("#del"+j).show();
		}
		var addCouponHtml = '<input type="button" id="addCoupon" style="background-color: #dddddd;padding: 3px;" onclick="couponAdd('+j+');"  value="再加1条">';
		$(".couponList").find("#addCoupon").remove();
		$(".couponList").append(addCouponHtml);
	}
	
	//限领
	function recLimitTypeOnclick(p){
		$("#recLimitType").val(p);
		if(p == 1){
			if($("#reclimit").prop("checked")){
				$("#recli").attr("checked", false);
				$("#reclimi").attr("checked", false);
				$("#recEach").hide();
			}
		}else if(p == 2){
			if($("#recli").prop("checked")){
				$("#recEach").show();
				$("#reclimit").attr("checked", false);
				$("#reclimi").attr("checked", false);
			}
		}else{
			if($("#reclimi").prop("checked")){
				$("#reclimit").attr("checked", false);
				$("#recEach").hide();
				$("#recli").attr("checked", false);
			}
		}
	}
	
	//优惠方式
	function promotion(p){
		if(p==0){ //无
			$(".couponClass").hide();
			$(".fullCutId").hide();	
			$(".fullOfGiftsTr").hide();
			$(".fullDiscountTr").hide();
			$(".shoppingCoupon1").hide();

		}else if(p==1){ //优惠券
			$(".couponClass").show();
			$(".fullCutId").hide();	
			$(".fullOfGiftsTr").hide();
			$(".fullDiscountTr").hide();
			$(".shoppingCoupon1").hide();
		}else if(p==2){ //满减
			$(".fullCutId").show();
			$(".couponClass").hide();
			$(".fullOfGiftsTr").hide();
			$(".fullDiscountTr").hide();
			$(".shoppingCoupon1").hide();
		}else if(p==3){ //满赠
			$(".fullOfGiftsTr").show();
			$(".couponClass").hide();
			$(".fullCutId").hide();	
			$(".fullDiscountTr").hide();
			$(".shoppingCoupon1").hide();
		}else if (p==4) {//多买优惠
			$(".fullDiscountTr").show();
			$(".couponClass").hide();
			$(".fullCutId").hide();
			$(".fullOfGiftsTr").hide();
			$(".shoppingCoupon1").hide();

			//多买多送
		 	<c:if test="${not empty fullDiscountList}">
			 	objFullDiscount=${fullDiscountList};
			 	$(".Discount1").hide();
				$(".Discount2").hide();
				$(".Discount3").show();
			</c:if>
		 	<c:if test="${not empty fullDiscount}">
			 	if(${fullDiscount.type==1}){
			 		$(".Discount1").show();
			 		$(".Discount2").hide();
					$(".Discount3").hide();
			 	}
			 	if(${fullDiscount.type==2}){
			 		$(".Discount1").hide();
					$(".Discount2").show();
					$(".Discount3").hide();
			 	}
			</c:if>
		 	<c:if test="${empty fullDiscount}">
			 	$(".Discount1").hide();
				$(".Discount2").hide();
				$(".Discount3").hide();
			</c:if>
		}else {
			$(".shoppingCoupon").show();
			$(".shoppingCoupon1").show();
			$(".couponClass").hide();
			$(".fullCutId").hide();
			$(".fullDiscountTr").hide();
			$(".fullOfGiftsTr").hide();
			$(".shoppingCoupon2").hide();
			$("#shoppingCouponRadio").click()
			$("#shoppingCouponRadio").prop("disabled","disabled")

		}
	}
	
	//满减
	function preferentialFullCut(fullCutList) {
		if('${fullCut.ladderFlag}' == 1) {
			$(".ladderIsNot").hide();
			for(var i=0; i<fullCutList.length; i++) {
				var html = '<div class="full-class-list'+i+' full_class_list">'
						+ '满&nbsp;<input type="text" style="width: 50px;" id="full'+i+'" name="full'+i+'" value="'+fullCutList[i].fullName+'"/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input type="text" style="width: 50px;" id="reduceId'+i+'" name="reduceName'+i+'" value="'+fullCutList[i].reduceName+'"/>元&nbsp;&nbsp;'
						+ '&nbsp;<input type="button" id="fullDel'+i+'" style="color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="fullDel('+i+');"  value="-">'
						+ '</div><br class="full-br'+i+'" >';
				$(".full-class").append(html);		
				if(i != 0) {
					var k = i-1;
					$("#fullDel"+k).hide();
				}else {
					$("#fullDel"+i).hide();
				}
				if(i == fullCutList.length-1 && fullCutList.length < 3) {
					$(".full-class").append('<input type="button" id="addFullCut" style="background-color: #dddddd;padding: 3px;" onclick="fullAdd('+i+');"  value="再加1条">');
				}
			}
			$(".fullCutId").show();
			$(".ladderIs").show();
		}
	}
	
	//是否阶梯
	function ladderFlagBy(op){
		$("#ladderFlag").val(op);
		if(op==0){
			if($("#ladderNot").prop("checked")){
				$("#ladder").attr("checked", false);
				$(".ladderIsNot").show();
				$(".ladderIs").hide();
			}
		}else{
			if($("#ladder").prop("checked")){
				$("#ladderNot").attr("checked", false);
				$(".ladderIsNot").hide();
				$(".ladderIs").show();
			}
			if($(".full-class").find("div").length==0){
				var html = '<div class="full-class-list0 full_class_list">'
						+ '满&nbsp;<input type="text" style="width: 50px;" id="full0" name="fullName0" value=""/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input type="text" style="width: 50px;" id="reduceId0" name="reduceName0" value=""/>元&nbsp;&nbsp;'
						+ '</div><br class="full-br0" >'
						+ '<input type="button" id="addFullCut" style="background-color: #dddddd;padding: 3px;" onclick="fullAdd(0);"  value="再加1条">';
				$(".full-class").append(html);
			}
		}
	}
	
	//阶梯插入数据
	function fullAdd(op){
		op = op+1;
		var addFullCutHtml = '<input type="button" id="addFullCut" style="background-color: #dddddd;padding: 3px;" onclick="fullAdd('+op+');"  value="再加1条">';
		if($(".full-class").find("div").length >= 2){
			addFullCutHtml = '';
		}
		var html = '<div class="full-class-list'+op+' full_class_list">'
				+ '满&nbsp;<input type="text" style="width: 50px;" id="full'+op+'" name="full'+op+'" value=""/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input type="text" style="width: 50px;" id="reduceId'+op+'" name="reduceName'+op+'" value=""/>元&nbsp;&nbsp;'
				+ '&nbsp;<input type="button" id="fullDel'+op+'" style="color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="fullDel('+op+');"  value="-">'
				+ '</div><br class="full-br'+op+'" >';
		$(".full-class").find("#addFullCut").remove();
		$(".full-class").append(html+addFullCutHtml);
		var k = op-1;
		$("#fullDel"+k).hide();
	}
	
	//删除
	function fullDel(op){
		$(".full-class-list"+op).remove();
		$(".full-br"+op).remove();
		var j = op-1;
		if(j != 0){
			$("#fullDel"+j).show();
		}
		var addFullCutHtml = '<input type="button" id="addFullCut" style="background-color: #dddddd;padding: 3px;" onclick="fullAdd('+j+');"  value="再加1条">';
		$(".full-class").find("#addFullCut").remove();
		$(".full-class").append(addFullCutHtml);
	}
	
	//累加
	function sumFlagOnclick(){
		if($("#flagSum").prop("checked")){
			$("#sumFlagId").val("1");
		}else{
			$("#sumFlagId").val("0");
		}
	}
	
	//满赠
	function fullOfGifts(p){
		if(p==1){
			if($("#fullOf").prop("checked")){
				$("#fullGifts").attr("checked", false);
				$("#type").val(p);
				$(".ofGifts").show();
			}
		}else{
			if($("#fullGifts").prop("checked")){
				$("#fullOf").attr("checked", false);
				$("#type").val(p);
				$(".ofGifts").hide();
			}
		}
	}
	
	//满赠是否累加
	function fullGiveOnclick(){
		if($("#fullGiveSumFlag").prop("checked")){
			$("#sumFlag").val("1");
		}else{
			$("#sumFlag").val("0");
		}
	}
	
	function couponFlagOnclick(){
		if($("#couponFlagId").prop("checked")){
			$("#couponFlag").val("1");
			$("#coupon_id_group").show();
			$("#choice").show();
		}else{
			$("#couponFlag").val("0");
			$("#coupon_id_group").hide();
			$("#choice").hide();
			$("#preferentialIdGroup").val("");
			$("#coupon_id_group").val("");
		}
		
	}

	function integralFlagOnclick(){
		if($("#integral_flag").prop("checked")){
			$("#integralFlag").val("1");
			$("#integralSamp").show();
		}else{
			$("#integralFlag").val("0");
			$("#integralSamp").hide();
			$("#integral").val("");
		}
	}
	
	//选择优惠券
	function choiceOnclick(){
		var preferentialIdGroup = $("#coupon_id_group").val();
		if(preferentialIdGroup == '') {
			commUtil.alertError("优惠券ID不能为空！");
			return;
		}
		if(!/^\d+(,\d+)*$/.test(preferentialIdGroup)) {
			commUtil.alertError("请输入正确格式的优惠券ID。例：1,2,3");
			return;
		}
		$.ligerDialog.open({
			height: height=$(window).height() - 100,
			width: width=$(window).width() - 100,
			title: "优惠券信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/choiceCouponList.shtml?preferentialIdGroup="+preferentialIdGroup,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//多买类型
	function fullDiscountOnclick(p){
		$("#fullDiscount").val(p);
		if(p==1){
			if($("#fullDisM").prop("checked")){
				$("#fullDisN").attr("checked", false);
				$("#fullDisMN").attr("checked", false);
				$("#fullDisFour").attr("checked", false);
				$(".Discount1").show();
				$(".Discount2").hide();
				$(".Discount3").hide();
				$(".Discount4").hide();
			}
		}else if(p==2){
			if($("#fullDisN").prop("checked")){
				$("#fullDisM").attr("checked", false);
				$("#fullDisMN").attr("checked", false);
				$("#fullDisFour").attr("checked", false);
				$(".Discount1").hide();
				$(".Discount2").show();
				$(".Discount3").hide();
				$(".Discount4").hide();
			}
		}else if(p==3){
			if($("#fullDisMN").prop("checked")){
				$("#fullDisM").attr("checked", false);
				$("#fullDisN").attr("checked", false);
				$("#fullDisFour").attr("checked", false);
				$(".Discount1").hide();
				$(".Discount2").hide();
				$(".Discount3").show();
				$(".Discount4").hide();
				if($(".full_discount_list").find("div").length==0){
					var html = '<div class="full_discount_list_div0 full_discount_list_loop">'
							+ '<span class="radioClass">满&nbsp;<input style="width:50px" border="none" type="text" value="" id="fullOf0" name="fullOf0">&nbsp;件&nbsp;&nbsp;</span>'
							+ '<span class="radioClass"><input style="width:50px" border="none" type="text" value="" id="fullGifts0" name="fullGifts0">&nbsp;折</span>'
							+ '</div><br class="full_discount-br0" >'
							+ '<span id="addFullDiscount" class="radioClass"><input type="button" style="background-color: #dddddd;padding: 3px;" onclick="fullDiscountAdd(0);" value="再加1条"/></span>';
					$(".full_discount_list").append(html);
				}
			}
		}else{
			if($("#fullDisFour").prop("checked")){
				$("#fullDisM").attr("checked", false);
				$("#fullDisN").attr("checked", false);
				$("#fullDisMN").attr("checked", false);
				$(".Discount1").hide();
				$(".Discount2").hide();
				$(".Discount3").hide();
				$(".Discount4").show();
			}
		}
	}
	
	function fullDiscountAdd(op){
		op = op+1;
		var addFullDiscountHtml = '<span id="addFullDiscount" class="radioClass"><input type="button" style="background-color: #dddddd;padding: 3px;" onclick="fullDiscountAdd('+op+');" value="再加1条"/></span>';
		if($(".full_discount_list").find("div").length >= 2){
			addFullDiscountHtml = '';
		}
		var html = '<div class="full_discount_list_div'+op+' full_discount_list_loop">'
				+ '<span class="radioClass">满&nbsp;<input style="width:50px" border="none" type="text" value="" id="fullOf'+op+'" name="fullOf'+op+'">&nbsp;件&nbsp;&nbsp;</span>'
				+ '<span class="radioClass"><input style="width:50px" border="none" type="text" value="" id="fullGifts'+op+'" name="fullGifts'+op+'">&nbsp;折</span>'
				+ '&nbsp;<input type="button" id="discountDel'+op+'" style="color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="fullDiscountDel('+op+');"  value="-">'
				+ '</div><br class="full_discount-br'+op+'" >';
		$(".full_discount_list").find("#addFullDiscount").remove();
		$(".full_discount_list").append(html+addFullDiscountHtml);
		var k = op-1;
		$("#discountDel"+k).hide();
	}
	//删除
	function fullDiscountDel(op){
		$(".full_discount_list_div"+op).remove();
		$(".full_discount-br"+op).remove();
		var j = op-1;
		if(j != 0){
			$("#discountDel"+j).show();
		}
		var addFullDiscountHtml = '<span id="addFullDiscount" class="radioClass"><input type="button" style="background-color: #dddddd;padding: 3px;" onclick="fullDiscountAdd('+j+');" value="再加1条"/></span>';
		$(".full_discount_list").find("#addFullDiscount").remove();
		$(".full_discount_list").append(addFullDiscountHtml);
	}
	
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" method="post" id="activityAreaForm" action="">
		
		<input type="hidden" id="activityAreaId" name="activityAreaId" value="${activityArea.id}"/>
		<input type="hidden" id="fullCutId" name="fullCutId" value="${fullCut.id}"/>
		<input type="hidden" id="fullGiveId" name="fullGiveId" value="${fullGive.id}"/>
		<input type="hidden" id="fullDiscountId" name="fullDiscountId" value="${fullDiscount.id}"/>
		<!-- 	优惠券 -->
		<input type="hidden" id="jsonCoupon" name="jsonCoupon">
		<!-- 	满减 -->
		<input type="hidden" id="jsonFullCut" name="jsonFullCut">
		<!-- 	多买多送 -->
		<input type="hidden" id="jsonFullDiscount" name="jsonFullDiscount"/>
		
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">会场名称</td>
				<td align="left" class="l-table-edit-td" >${activityArea.name }</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">活动时间</td>
				<td align="left" class="l-table-edit-td table-edit-activity-time">
					<div><input type="text" class="dateEditor" id="activityBeginTime" name="activityBeginTime" value="<fmt:formatDate value="${activityArea.activityBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
					<span style="margin: 0px 10px;">到</span>
					<div><input type="text" class="dateEditor" id="activityEndTime" name="activityEndTime" value="<fmt:formatDate value="${activityArea.activityEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
					<c:if test="${activityArea.isPreSell eq 1}">
						&nbsp;&nbsp;定金开始时间&nbsp;&nbsp;
						<div><input type="text" class="dateEditor" id="preheatTime" name="preheatTime" value="<fmt:formatDate value="${activityArea.preheatTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
					</c:if>				
				</td>
           	</tr>
           	<tr  <c:if test="${activityArea.isPreSell eq 1}">style="display: none"</c:if>>
            	<td class="title" width="20%">优惠方式</td>
				<td align="left" class="l-table-edit-td" >
					<c:forEach var="preferentialType" items="${preferentialTypeList }">
						<span class="radioClass">
							<input type="radio" value="${preferentialType.statusValue }" name="preferentialType" onclick="promotion(${preferentialType.statusValue });" <c:if test="${preferentialType.statusValue == activityArea.preferentialType }">checked</c:if> >${preferentialType.statusDesc}
						</span>
					</c:forEach>
				</td>
           	</tr>
           	
           	<tr class="couponClass" <c:if test="${activityArea.preferentialType ne 1}">style="display: none"</c:if>>
				<td class="title">优惠劵：</td>
				<td>
					<div class="couponC">
						<table>
							<tr>
								<td style="border:none">
									<div class="couponList">
									
									</div>
								</td>
							</tr>
							<tr>
								<td style="border:none">
									<input type="hidden" id="recLimitType" name="recLimitType" value="${copuonMap.recLimitType}"/>
									<div>
										限领：&nbsp;&nbsp;<input class="radioItem" type="radio" id="reclimit" name="reclimit" value="1" <c:if test="${copuonMap.recLimitType eq 1}">checked="checked"</c:if>  onclick="recLimitTypeOnclick(1);">每人每天限领1张
											&nbsp;&nbsp;&nbsp;<input class="radioItem"  type="radio" id="recli" name="recli" value="2" <c:if test="${copuonMap.recLimitType eq 2}">checked="checked"</c:if> onclick="recLimitTypeOnclick(2);" >每人限领
											&nbsp;&nbsp;<input type="text" style="width: 50px;" id="recEach"  name="recEach"  value="${copuonMap.recEach}" />&nbsp;张
											&nbsp;&nbsp;&nbsp;<input class="radioItem" type="radio" id="reclimi" name="reclimi" value="3" <c:if test="${copuonMap.recLimitType eq 3}">checked="checked"</c:if> onclick="recLimitTypeOnclick(3);">不限
									</div>
									<%-- <div style="margin-top: 10px;">
										优惠承担方：&nbsp;&nbsp;<input class="radioItem" type="radio" name="belong" value="1" <c:if test="${copuonMap.belong eq 1}">checked="checked"</c:if> >平台承担
											&nbsp;&nbsp;&nbsp;<input class="radioItem"  type="radio" name="belong" value="2" <c:if test="${copuonMap.belong eq 2}">checked="checked"</c:if> >商家承担
									</div> --%>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr class="fullCutId" <c:if test="${activityArea.preferentialType ne 2}">style="display: none"</c:if>>
				<td class="title">满减额度：</td>
				<td>
					<div class="fullCut">
						<table>
							<tr>
								<td style="border:none">
									<input type="hidden" id="ladderFlag" name="ladderFlag" value="${fullCut.ladderFlag}">
									<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="ladderNot" name="ladderNot" value="0" onclick="ladderFlagBy(0);" <c:if test="${fullCut.ladderFlag eq 0}">checked="checked"</c:if> >非阶梯</span>
									<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="ladder" name="ladder" value="1" onclick="ladderFlagBy(1);" <c:if test="${fullCut.ladderFlag eq 1}">checked="checked"</c:if> >阶梯</span>
								</td>
							</tr>
							<tr class="ladderIsNot">
								<td style="border:none">
									<input type="hidden" id="sumFlagId" name="sumFlagId" value="${fullCut.sumFlag}"/>
									满&nbsp;<input type="text" id="full" name="fullName" value="${fullName}" style="width: 50px;"/>&nbsp;元&nbsp;&nbsp;减&nbsp;
									<input type="text" id="reduceId" name="reduceName" value="${reduceName}" style="width: 50px;"/>元&nbsp;&nbsp;
									<input type="checkbox" id="flagSum"  name="flagSum" value="" onclick="sumFlagOnclick();" <c:if test="${fullCut.sumFlag==1}">checked="checked"</c:if> />&nbsp;累加
								</td>
							</tr>
							<tr class="ladderIs">
								<td style="border:none">
									<div class="full-class">
									
									</div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr class="fullOfGiftsTr" <c:if test="${activityArea.preferentialType ne 3}">style="display: none"</c:if>>
				<td class="title">满赠：</td>
				<td>
					<div class="full-of-gifts">
						<table>
							<tr>
								<td style="border:none">
									<span>满赠类型：</span>
									<input type="hidden" id="type" name="type" value="${fullGive.type}">
									<span class="radioClass"><input border="none" class="radioItem" type="radio" value="1" id="fullOf" name="fullOf" onclick="fullOfGifts(1);" <c:if test="${fullGive.type eq 1}">checked="checked"</c:if>>满额赠</span>
									<span class="radioClass"><input border="none" class="radioItem" type="radio" value="2" id="fullGifts" name="fullGifts" onclick="fullOfGifts(2);" <c:if test="${fullGive.type eq 2}">checked="checked"</c:if>>买即赠</span>
								</td>
							</tr>
							<tr class="ofGifts">
								<td style="border:none">
									<input type="hidden" id="sumFlag" name="sumFlag" value="${fullGive.sumFlag}"/>
									满额：&nbsp;满&nbsp;<input type="text" id="fullGiveMinimum" name="fullGiveMinimum" style="width: 50px;" value="${fullGive.minimum}"/>&nbsp;元&nbsp;&nbsp;
									<input type="checkbox" id="fullGiveSumFlag" name="fullGiveSumFlag" value="${fullGive.sumFlag }" <c:if test="${fullGive.sumFlag eq 1}">checked="checked"</c:if>  onclick="fullGiveOnclick();"/>&nbsp;累加
								</td>
							</tr>
							<tr>
								<td style="border:none">
									<div style="float: left;">
										<input type="hidden" id="couponFlag" name="couponFlag" value="${fullGive.couponFlag}"/>
										<input type="checkbox" id="couponFlagId" value="${fullGive.couponFlag}" onclick="couponFlagOnclick();" <c:if test="${fullGive.couponFlag eq 1}">checked="checked"</c:if> />优惠券ID&nbsp;&nbsp;&nbsp;
										<input style="display: none;width: 300px;height: 20px;" id="coupon_id_group" name="preferentialIdGroup" type="text" value="${fullGive.couponIdGroup }"/>&nbsp;&nbsp;
									</div>
									<div style="float: left;">
										<input type="button" id="choice" style="background-color: rgba(255, 153, 0, 1);width: 50px;display: none;cursor:pointer;height: 24px;border-radius: 3px;" onclick="choiceOnclick();" value="检测">
									</div>    
								</td>
							</tr>
							<tr>
								<td style="border:none">
									<input type="hidden" id="integralFlag" name="integralFlag" value="${fullGive.integralFlag}" />
									<input type="checkbox" id="integral_flag" value="" onclick="integralFlagOnclick();" <c:if test="${fullGive.integralFlag eq 1}">checked="checked"</c:if> />金币&nbsp;&nbsp;&nbsp;
									<samp id="integralSamp" style="display: none" ><input type="text" id="integral" name="integral" style="width: 50px;" value="${fullGive.integral}"/>金币</samp>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr class="fullDiscountTr" <c:if test="${activityArea.preferentialType ne 4}">style="display: none"</c:if>>
				<td class="title">多买优惠：</td>
				<td>
					<div class="full_discount">
						<table>
							<tr>
								<td style="border:none">
									<span>多买类型：</span>
									<input type="hidden" id="fullDiscount" name="fullDiscount" value="${fullDiscount.type}">
									<span class="radioClass"><input border="none"  type="radio" value="1" id="fullDisM" <c:if test="${fullDiscount.type eq 1}">checked="checked"</c:if> onclick="fullDiscountOnclick(1);">满M件减N件</span>
									<span class="radioClass"><input border="none"  type="radio" value="2" id="fullDisN" <c:if test="${fullDiscount.type eq 2}">checked="checked"</c:if> onclick="fullDiscountOnclick(2);">M件N元</span>
									<span class="radioClass"><input border="none"  type="radio" value="3" id="fullDisMN" <c:if test="${fullDiscount.type eq 3}">checked="checked"</c:if> onclick="fullDiscountOnclick(3);">M件N折</span>
									<span class="radioClass"><input border="none"  type="radio" value="4" id="fullDisFour" <c:if test="${fullDiscount.type eq 4}">checked="checked"</c:if> onclick="fullDiscountOnclick(4);">第M件N折</span>
								</td>
							</tr>
							<tr class="Discount1">
								<td style="border:none">
									<span class="radioClass">满&nbsp;<input border="none" type="text" style="width: 50px;" value="${fullDiscountMap.fullOfOne}" id="fullOfOne" name="fullOfOne">&nbsp;件&nbsp;&nbsp;</span>
									<span class="radioClass">减&nbsp;<input border="none" type="text" style="width: 50px;" value="${fullDiscountMap.fullGiftsOneName}" id="fullGiftsOneName" name="fullGiftsOneName">&nbsp;件</span>
								</td>
							</tr>
							<tr class="Discount2">
								<td style="border:none">
									<span class="radioClass"><input border="none" type="text" style="width: 50px;" value="${fullDiscountMap.fullOfTwo}" id="fullOfTwo" name="fullOfTwo">&nbsp;件&nbsp;&nbsp;</span>
									<span class="radioClass"><input border="none" type="text" style="width: 50px;" value="${fullDiscountMap.fullGiftsTwoName}" id="fullGiftsTwoName" name="fullGiftsTwoName">&nbsp;元</span>
								</td>
							</tr>
							<tr class="Discount3">
								<td style="border:none">
									<div class="full_discount_list">
									
									</div>
								</td>
							</tr>
							<tr class="Discount4">
								<td style="border:none">
									<span class="radioClass">第&nbsp;<input border="none" type="text" style="width: 50px;" value="${fullDiscountMap.fullOfFour}" id="fullOfFour" name="fullOfFour">&nbsp;件&nbsp;&nbsp;</span>
									<span class="radioClass">&nbsp;<input border="none" type="text" style="width: 50px;" value="${fullDiscountMap.fullGiftsFourName}" id="fullGiftsFourName" name="fullGiftsFourName">&nbsp;折</span>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>

			<tr class="shoppingCoupon" <c:if test="${activityArea.preferentialType ne 5}">style="display: none"</c:if>>
				<td class="title">购物津贴：</td>
				<td>
					<div class="full_discount">
						<table>
							<tr>
								<td style="border:none">
									<span>津贴模式</span>
									每满<input style="width: 50px" id="fullMoney" name="fullMoney" value="${fullMoney}">元减<input style="width: 50px" id="reduceMoney" name="reduceMoney" value="${reduceMoney}">元,上不封顶
								</td>
							</tr>
							<tr>
								<td style="border:none"  align="left" class="l-table-edit-td table-edit-activity-time">
									<span>津贴使用时间</span>
									<input type="text" class="dateEditor"  id="usageBeginTime" name="usageBeginTime" value="<fmt:formatDate value="${allowanceInfo.usageBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" >
									到
									<input type="text" class="dateEditor"  id="usageEndTime"  name="usageEndTime" value="<fmt:formatDate value="${allowanceInfo.usageEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" >
								</td>
							</tr>
							<tr>
								<td style="border:none">
									<span>津贴说明</span>
									<input style="width: 500px" id="allowanceDescription" name="allowanceDescription" value="${allowanceInfo.allowanceDescription}"><span>例:满300元减30元上不封顶,12.12当天可用</span>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr class="shoppingCoupon1" <c:if test="${activityArea.preferentialType ne 5}">style="display: none"</c:if>>
				<td class="title" width="20%">优惠承担方</td>
				<td align="left" class="l-table-edit-td" >
					<span class="radioClass"><input id="shoppingCouponRadio" class="radioItem" type="radio" name="belong2" value="2" <c:if test="${allowanceInfo.belong eq 2}">checked="checked"</c:if>>商家承担</span>
				</td>
			</tr>

			
			<tr  <c:if test="${activityArea.isPreSell eq 1 || activityArea.preferentialType eq 5}">style="display: none"</c:if>>
            	<td class="title" width="20%">优惠承担方</td>
				<td align="left" class="l-table-edit-td" >
					<label><input class="radioItem" type="radio" name="belong" value="1" <c:if test="${copuonMap.belong eq 1 or fullCut.belong eq 1 or fullGive.belong eq 1 or fullDiscount.belong eq 1}">checked="checked"</c:if> >平台承担</label>
					<label><input class="radioItem"  type="radio" name="belong" value="2" <c:if test="${copuonMap.belong eq 2 or fullCut.belong eq 2 or fullGive.belong eq 2 or fullDiscount.belong eq 2}">checked="checked"</c:if> >商家承担</label>
				</td>
           	</tr>
			
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input style="margin-left: 20px;" id="button-close" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>