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
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
	  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
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
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
.title{width: 200px;}
</style>
<script type="text/javascript">
$(function(){
	 var objstr ="";
	 var objFull="";
	 var objFullDiscount="";
	 var preferentialType="${activityArea.preferentialType}";
	 <c:if test="${empty activityArea}">
	 	$(".text").hide();
	 	$(".couponClass").hide();
	 	$(".fullCutId").hide();	
		$(".fullOfGiftsTr").hide();
		$(".fullDiscountTr").hide();
		$(".Discount1").hide();
		$(".Discount2").hide();
		$(".Discount3").hide();
	 </c:if>
	 <c:if test="${not empty activityArea}">
	 	if(${activityArea.mchtIdGroup!=null} && ${activityArea.mchtIdGroup!=""} && ${activityArea.pushMchtType!=1}){
	 		$(".text").show();
	 	}else{
	 		$(".text").hide();
	 	}
	 	
	 	if(${activityArea.preferentialType == 1}){
	 		$(".couponClass").show();
	 		$(".fullCutId").hide();
		 	$(".fullOfGiftsTr").hide();
		 	$(".fullDiscountTr").hide();
	 	}
	 	if(${activityArea.preferentialType == 2}){
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
	 	}
	 	
	 	if(${activityArea.preferentialType == 3}){
	 		$(".fullOfGiftsTr").show();
		 	$(".couponClass").hide();
		 	$(".fullCutId").hide();	
			$(".fullDiscountTr").hide();
			//满赠
			<c:if test="${not empty fullGiveInfo}">
			 	if(${fullGiveInfo.type==1}){
			 		$(".ofGifts").show();
			 	}else{
			 		$(".ofGifts").hide();
			 	}
			 	if(${fullGiveInfo.couponFlag==1}){
			 		$("#coupon_id_group").show();
			 		$("#choice").css("display","block");
			 	}
			 	if(${fullGiveInfo.integralFlag==1}){
			 		$("#integralSamp").show();
			 	}
		 	</c:if>
	 	}
	 	
	 	if(${activityArea.preferentialType == 4}){
	 		$(".fullDiscountTr").show();
	 		$(".couponClass").hide();
		 	$(".fullCutId").hide();
		 	$(".fullOfGiftsTr").hide();
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
	 	}
	 </c:if>
	 //优惠券
	 <c:if test="${not empty couponList}">
	 	objstr=${couponList};
	 </c:if>
	 //满减
	 <c:if test="${not empty fullCutList}">
	 	objFull=${fullCutList};
	 </c:if>
	 //多买多送
	 if(objFullDiscount!='' && preferentialType==4){
		 	var obj=objFullDiscount;
			for(var i=0;i<obj.length;i++){
				var html='<div class="full_discount_list_div'+i+' full_discount_list_loop">'
				+'<span class="radioClass">满&nbsp;<input border="none" type="text" value="'+obj[i].fullGiscountThree+'" id="fullOf'+i+'" name="fullOf'+i+'">&nbsp;件&nbsp;&nbsp;&nbsp;&nbsp;</span>'
				+'<span class="radioClass"><input border="none" type="text" value="'+obj[i].fullGiscountThreeName+'" id="fullGifts'+i+'" name="fullGifts'+i+'">&nbsp;折</span>'
				+'&nbsp;&nbsp;<input type="button" id="discountAdd'+i+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullDiscountAdd('+i+');"  value="[ + ]">'
				+'<input type="button" id="discountDel'+i+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullDiscountDel('+i+');"  value="[ - ]">'
				+'</div><br>';
				$(".full_discount_list").append(html);
				if(i!=0){
					var k=i-1;
					$("#discountAdd"+k).hide();
					$("#discountDel"+k).hide();
				}else{
					$("#discountDel"+i).hide();
				}
			}
		}
	 
	// 	优惠券
	if(objstr!='' && preferentialType==1){
		var obj=objstr;
		for(var i=0;i<obj.length;i++){
			var rectypeHtml="";
			var rectype1="";
			var rectype2="";
			if(obj[i].recType==2){
				rectype1='<span class="radioClass"><input class="radioItem" type="radio" value="1" id="recType'+i+'" name="recType'+i+'" onclick="recType('+i+');" >免费领取</span>';
				rectype2='<span class="radioClass"><input class="radioItem" type="radio" value="2" id="rec'+i+'" name="rec'+i+'" checked="checked" onclick="rec('+i+');" >金币兑换</span>';
				rectypeHtml='<span id="integral'+i+'"><input id="needIntegral'+i+'" name="needIntegral" type="text" style="width:60px;" value="'+obj[i].needIntegral+'"/>金币换1张优惠券</span>';
			}else{
				rectype1='<span class="radioClass"><input class="radioItem" type="radio" value="1" id="recType'+i+'" name="recType'+i+'" checked="checked" onclick="recType('+i+');" >免费领取</span>';
				rectype2='<span class="radioClass"><input class="radioItem" type="radio" value="2" id="rec'+i+'" name="rec'+i+'" onclick="rec('+i+');" >金币兑换</span>';
				rectypeHtml='<span id="integral'+i+'" style="display:none;"><input id="needIntegral'+i+'" name="needIntegral" type="text" style="width:60px;" value="'+obj[i].needIntegral+'"/>金币换1张优惠券</span>';
			}
			
			var html='<div class="couponDiv'+i+' coupon_list"><input type="hidden" id="couponId" name="couponId" value="'+obj[i].couponId+'"/>'
			+'面额&nbsp;<input type="text" id="money" name="money" style="width: 50px;" value="'+obj[i].money+'">元&nbsp; &nbsp;'
			+'使用条件&nbsp;<input type="text" id="minimum" name="minimum" style="width: 50px;" value="'+obj[i].minimum+'"/>元&nbsp; &nbsp;'
			+'发行量&nbsp;<input type="text" id="grantQuantity" name="grantQuantity" style="width: 50px;" value="'+obj[i].grantQuantity+'"/>张&nbsp;'
			+rectype1+rectype2+rectypeHtml
			+'&nbsp;<input type="button" id="add'+i+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="coupon('+i+');"  value="[ + ]">'
			+'&nbsp;<input type="button" id="del'+i+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="couponDel('+i+');"  value="[ - ]"></div>'
			+'</div><br>';
			$(".couponList").append(html);
			if(i!=0){
				var k=i-1;
				$("#add"+k).hide();
				$("#del"+k).hide();
			}else{
				$("#del"+i).hide();
			}
		}
	}else{
		var i=0;
		var html='<div class="couponDiv'+i+' coupon_list"><input type="hidden" id="couponId" name="couponId" value=""/>'
				+'面额&nbsp;<input type="text" id="money" name="money" style="width: 50px;" value="">元&nbsp; &nbsp;'
				+'使用条件&nbsp;<input type="text" id="minimum" name="minimum" style="width: 50px;" value=""/>元&nbsp; &nbsp;'
				+'发行量&nbsp;<input type="text" id="grantQuantity" name="grantQuantity" style="width: 50px;" value=""/>张&nbsp;'
				+'<span class="radioClass"><input class="radioItem" type="radio" value="" id="recType'+i+'" name="recType'+i+'" onclick="recType('+i+');" >免费领取'
				+'</span><span class="radioClass"><input class="radioItem" type="radio" value="" id="rec'+i+'" name="rec'+i+'" onclick="rec('+i+');" >金币兑换'
				+'</span><span id="integral'+i+'"><input id="needIntegral'+i+'" name="needIntegral" type="text" style="width:60px;" value=""/>金币换1张优惠券</span>'
				+'&nbsp;<input type="button" id="add'+i+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="coupon('+i+');"  value="[ + ]"></div>'
			+'</div><br>';
		$(".couponList").append(html);
		$("#integral"+i).hide();
		$("#recEach").hide();
	}
	
	//满减
	if(objFull!='' && preferentialType==2){
		$(".ladderIsNot").hide();
		$(".couponClass").hide();
		if('${fullCut.ladderFlag}'==1){
			var obj = objFull;
			for(var i=0;i<obj.length;i++){
				var html='<div class="full-class-list'+i+' full_class_list">'
				+'满&nbsp;<input type="text" id="full'+i+'" name="full'+i+'" value="'+obj[i].fullName+'"/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input type="text" id="reduceId'+i+'" name="reduceName'+i+'" value="'+obj[i].reduceName+'"/>元&nbsp;&nbsp;'
				+'<input type="button" id="fullAdd'+i+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullAdd('+i+');"  value="[ + ]">'
				+'<input type="button" id="fullDel'+i+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullDel('+i+');"  value="[ - ]">'
				+'</div><br>';
				$(".full-class").append(html);
				if(i!=0){
					var k=i-1;
					$("#fullAdd"+k).hide();
					$("#fullDel"+k).hide();
				}else{
					$("#fullDel"+i).hide();
				}
			}
			$(".fullCutId").show();
			$(".ladderIs").show();
		}
		
	}
	
	
	
	//活动报名时间
	$("#enrollBeginTime").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	$("#enrollEndTime").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	
	//活动开始时间
	
	$("#activityBeginTime").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	$("#activityEndTime").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	
	<c:if test="${not empty activityArea}">
	 if (${activityArea.status}==1){
		var form = document.forms[0];
    	for ( var i = 0; i < form.length; i++) {
    		var element = form.elements[i];
    		element.disabled = "true";
    	}
	 }
	</c:if>
});
//添加一个div
function coupon(i){
	i=i+1;
	if($(".couponList").find("div").length>=3){
		commUtil.alertError("最多只能添加三行");
		return;
	}
	var html='<div class="couponDiv'+i+' coupon_list"><input type="hidden" id="couponId" name="couponId" value=""/>'
		+'面额&nbsp;<input type="text" id="money" name="money" style="width: 50px;" value="">元&nbsp; &nbsp;'
		+'使用条件&nbsp;<input type="text" id="minimum" name="minimum" style="width: 50px;" value=""/>元&nbsp; &nbsp;'
		+'发行量&nbsp;<input type="text" id="grantQuantity" name="grantQuantity" style="width: 50px;" value=""/>张&nbsp;'
		+'<span class="radioClass"><input class="radioItem" type="radio" value="" id="recType'+i+'" name="recType'+i+'" onclick="recType('+i+');" >免费领取</span>'
		+'<span class="radioClass"><input class="radioItem" type="radio" value="" id="rec'+i+'" name="rec'+i+'" onclick="rec('+i+');">金币兑换</span>'
		+'<span id="integral'+i+'"><input id="needIntegral'+i+'" name="needIntegral" type="text" style="width:60px;" value=""/>金币换1张优惠券</span>'
		+'&nbsp;<input type="button" id="add'+i+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="coupon('+i+');"  value="[ + ]">'
		+'&nbsp;<input type="button" id="del'+i+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="couponDel('+i+');"  value="[ - ]">'
	+'</div><br>';
	$(".couponList").append(html);
	$("#integral"+i).hide();
	var k=i-1;
	$("#add"+k).hide();
	$("#del"+k).hide();
}
//免费领取
function recType(p){
	if($("#recType"+p).prop("checked")){
		$("#needIntegral"+p).val("");
		$("#recType"+p).val("1");
		$("#rec"+p).attr("checked", false);
		$("#rec"+p).val("");
		$("#integral"+p).hide();
	}
}
//金币兑换
function rec(p){
	if($("#rec"+p).prop("checked")){
		$("#needIntegral"+p).val("");
		$("#rec"+p).val("2");
		$("#recType"+p).attr("checked", false);
		$("#recType"+p).val("");
		$("#integral"+p).show();
	}
}
//限领
function recLimitTypeOnclick(p){
	$("#recLimitType").val(p);
	if(p==1){
		if($("#reclimit").prop("checked")){
			$("#recli").attr("checked", false);
			$("#reclimi").attr("checked", false);
			$("#recEach").hide();
		}
	}else if(p==2){
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

//删除一个div
function couponDel(y){
	$(".couponDiv"+y).remove();
	var j=y-1;
	$("#add"+j).show();
	if(j!=0){
		$("#del"+j).show();
	}
}


//推送商家
function platForm(p){
	$("#pushMchtType").val(p);
	if(p==1){
		if($("#platform").prop("checked")){
			$("#pop").attr("checked", false);
			$("#pool").attr("checked", false);
			$("#appoint").attr("checked", false);
			$(".text").hide();
		}else{
			$("#pushMchtType").val("");
		}
	}else if(p==2){
		if($("#pop").prop("checked")){
			$("#platform").attr("checked", false);
			$("#pool").attr("checked", false);
		}else{
			$("#pushMchtType").val("");
		}
	}else if(p==3){
		if($("#pool").prop("checked")){
			$("#platform").attr("checked", false);
			$("#pop").attr("checked", false);
		}else{
			$("#pushMchtType").val("");
		}
	}
}
//指定商家
function appointMember(){
	if($("#appoint").prop("checked")){
		$("#platform").attr("checked", false);
	    $(".text").show();
	    $("#thisAppoint").val("1");
	}else{
		$(".text").hide();
		$("#thisAppoint").val("0");
	}
}


//选择商家
function choiceMemberList(){
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "商家信息",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activityArea/mchtInfoList.shtml",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
function mchtInfoListId(str,strName){
	 $("#mchtIdGroup").val(str);
	 $("#mchtIdGroups").val(strName);
}

//优惠券
function promotion(p){
	$("#preferentialType").val(p);
	if(p==0){//无
		if($("#nothingType").prop("checked")){
			$("#couponType").attr("checked", false);
			$("#fullCutType").attr("checked", false);
			$("#fullOfGiftsType").attr("checked", false);
			$("#buyDiscountType").attr("checked", false);
			$(".couponClass").hide();
			$(".fullCutId").hide();	
			$(".fullOfGiftsTr").hide();
			$(".fullDiscountTr").hide();
		}
	}else if(p==1){
		if($("#couponType").prop("checked")){
			$("#nothingType").attr("checked", false);
			$("#fullCutType").attr("checked", false);
			$("#fullOfGiftsType").attr("checked", false);
			$("#buyDiscountType").attr("checked", false);
// 			console.log($(".couponClass"));
			$(".couponClass").show();
			$(".fullCutId").hide();	
			$(".fullOfGiftsTr").hide();
			$(".fullDiscountTr").hide();
		}
	}else if(p==2){//满减
		if($("#fullCutType").prop("checked")){
			$("#nothingType").attr("checked", false);
			$("#couponType").attr("checked", false);
			$("#fullOfGiftsType").attr("checked", false);
			$("#buyDiscountType").attr("checked", false);
			$(".fullCutId").show();
			$(".couponClass").hide();
			$(".fullOfGiftsTr").hide();
			$(".fullDiscountTr").hide();
		}
	}else if(p==3){//满赠
		if($("#fullOfGiftsType").prop("checked")){
			$(".fullOfGiftsTr").show();
			$(".couponClass").hide();
			$(".fullCutId").hide();	
			$(".fullDiscountTr").hide();
			$("#nothingType").attr("checked", false);
			$("#couponType").attr("checked", false);
			$("#fullCutType").attr("checked", false);
			$("#buyDiscountType").attr("checked", false);
		}
	}else{//多买优惠
		if($("#buyDiscountType").prop("checked")){
			$("#nothingType").attr("checked", false);
			$("#couponType").attr("checked", false);
			$("#fullCutType").attr("checked", false);
			$("#fullOfGiftsType").attr("checked", false);
			$(".fullDiscountTr").show();
			$(".couponClass").hide();
			$(".fullCutId").hide();
			$(".fullOfGiftsTr").hide();
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
		}
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
			var html='<div class="full-class-list0 full_class_list">'
				+'满&nbsp;<input type="text" id="full0" name="fullName0" value=""/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input type="text" id="reduceId0" name="reduceName0" value=""/>元&nbsp;&nbsp;<input type="button" id="fullAdd0" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullAdd(0);"  value="[ + ]">'
				+'</div><br>';
			$(".full-class").append(html);
		}
	}
}
function sumFlagOnclick(){
	if($("#flagSum").prop("checked")){
		$("#sumFlagId").val("1");
	}else{
		$("#sumFlagId").val("0");
	}
}
//阶梯插入数据
function fullAdd(op){
	op=op+1;
	if($(".full-class").find("div").length>=3){
		commUtil.alertError("最多只能添加三行");
		return;
	}
	var html='<div class="full-class-list'+op+' full_class_list">'
		+'满&nbsp;<input type="text" id="full'+op+'" name="full'+op+'" value=""/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input type="text" id="reduceId'+op+'" name="reduceName'+op+'" value=""/>元&nbsp;&nbsp;'
		+'<input type="button" id="fullAdd'+op+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullAdd('+op+');"  value="[ + ]">'
		+'<input type="button" id="fullDel'+op+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullDel('+op+');"  value="[ - ]">'
		+'</div><br>';
	$(".full-class").append(html);
	var k=op-1;
	$("#fullAdd"+k).hide();
	$("#fullDel"+k).hide();
}
//删除
function fullDel(op){
	$(".full-class-list"+op).remove();
	var j=op-1;
	$("#fullAdd"+j).show();
	if(j!=0){
		$("#fullDel"+j).show();
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
/* 			$("#fullGiveMinimum").val("");
			$("#fullGiveSumFlag").attr("checked",false);
			$("#fullGiveSumFlag").val("0"); */
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

//多买类型
function fullDiscountOnclick(p){
	$("#fullDiscount").val(p);
	if(p==1){
		if($("#fullDisM").prop("checked")){
			$("#fullDisN").attr("checked", false);
			$("#fullDisMN").attr("checked", false);
			$(".Discount1").show();
			$(".Discount2").hide();
			$(".Discount3").hide();
		}
	}else if(p==2){
		if($("#fullDisN").prop("checked")){
			$("#fullDisM").attr("checked", false);
			$("#fullDisMN").attr("checked", false);
			$(".Discount1").hide();
			$(".Discount2").show();
			$(".Discount3").hide();
		}
	}else{
		if($("#fullDisMN").prop("checked")){
			$("#fullDisM").attr("checked", false);
			$("#fullDisN").attr("checked", false);
			$(".Discount1").hide();
			$(".Discount2").hide();
			$(".Discount3").show();
			if($(".full_discount_list").find("div").length==0){
				var html='<div class="full_discount_list_div0 full_discount_list_loop">'
				+'<span class="radioClass">满&nbsp;<input style="width:50px" border="none" type="text" value="" id="fullOf0" name="fullOf0">&nbsp;件&nbsp;&nbsp;</span>'
				+'<span class="radioClass"><input style="width:50px" border="none" type="text" value="" id="fullGifts0" name="fullGifts0">&nbsp;折</span>'
				+'&nbsp;&nbsp;<input type="button" id="discountAdd0" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullDiscountAdd(0);"  value="[ + ]">'
				+'</div><br>';
				$(".full_discount_list").append(html);
			}
		}
	}
}


function fullDiscountAdd(op){
	op=op+1;
	if($(".full_discount_list").find("div").length>=3){
		commUtil.alertError("最多只能添加三行");
		return;
	}
	var html='<div class="full_discount_list_div'+op+' full_discount_list_loop">'
		+'<span class="radioClass">满&nbsp;<input style="width:50px" border="none" type="text" value="" id="fullOf'+op+'" name="fullOf'+op+'">&nbsp;件&nbsp;&nbsp;</span>'
		+'<span class="radioClass"><input style="width:50px" border="none" type="text" value="" id="fullGifts'+op+'" name="fullGifts'+op+'">&nbsp;折</span>'
		+'&nbsp;&nbsp;<input type="button" id="discountAdd'+op+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullDiscountAdd('+op+');"  value="[ + ]">'
		+'<input type="button" id="discountDel'+op+'" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullDiscountDel('+op+');"  value="[ - ]">'
		+'</div><br>';
	$(".full_discount_list").append(html);
	var k=op-1;
	$("#discountAdd"+k).hide();
	$("#discountDel"+k).hide();
}
//删除
function fullDiscountDel(op){
	$(".full_discount_list_div"+op).remove();
	var j=op-1;
	$("#discountAdd"+j).show();
	if(j!=0){
		$("#discountDel"+j).show();
	}
}

//是否启用
function statusOnclick(p){
	$("#status").val(p);
	if(p==0){
		if($("#statusWeishiy").prop("checked")){
			$("#statusEnable").attr("checked", false);
		}
	}else if(p==1){
		if($("#statusEnable").prop("checked")){
			$("#statusWeishiy").attr("checked", false);
		}
	}
	
}

//选择优惠券
function choiceOnclick(){
	$.ligerDialog.open({
		height: height=$(window).height() - 100,
		width: width=$(window).width() - 400,
		title: "商家信息",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activityArea/choiceCouponList.shtml",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
function couponListId(couId,couName){
	$("#preferentialIdGroup").val(couId);
	$("#coupon_id_group").val(couName);
}

//入口图
function ajaxFileUploadEntryPic() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml?fileType=3',
		secureuri: false,
		fileElementId: "entryPicFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#entryPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
				$("#entryPic").val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}
//专区活动图片
function ajaxFileUploadPic() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml?fileType=3',
		secureuri: false,
		fileElementId: "areaPicFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#areaPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
				$("#pic").val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}

function typeOnclick(p){
	$("#areaType").val(p);
	if(p==1){
		if($("#brand").prop("checked")){
			$("#single").attr("checked", false);
		}
	}else if(p==2){
		if($("#single").prop("checked")){
			$("#brand").attr("checked", false);
		}
	}
	
}

//判断活动地址后缀的规则
function suffixIsHased() {
	var suffix = $("#urlSuffix").val(); 
	var oldSuffix = $("#oldUrlSuffix").val(); 
	if (suffix==""){
		commUtil.alertError("请填写地址后续");
	}else if (suffix!=oldSuffix){
		$.ajax({
			url : "${pageContext.request.contextPath}/activityArea/urlSuffix/checkSuffix.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {name : suffix},
			timeout : 30000,
			success : function(data) {
				if ("1" == data.hased) {
					commUtil.alertError("系统已存在");
				}else{
					alert("可使用");
				}
			},
			error : function() {
				alert('操作超时，请稍后再试！');
			}
		});
	}else{
		alert("没修改，不进行检测");
	}
}



//提交
function createActivityArea(){
	
	if($("#name").val().length==0){//活动名称
		commUtil.alertError("活动名称不能为空");
		return;
	}
	if($("#areaType").val().length==0){//活动类型
		commUtil.alertError("请选择活动类型");
		return;	
	}
	if($("#entryPic").val().length==0){
		commUtil.alertError("入口图不能为空，请选择上传图片");
		return;
	}
	if($("#pic").val().length==0){
		commUtil.alertError("活动图不能为空，请选择上传图片");
		return;
	}
	if($("#enrollBeginTime").val().length==0){
		commUtil.alertError("活动报名开始时间不能为空");
		return;
	}
	if($("#enrollEndTime").val().length==0){
		commUtil.alertError("活动报名结束时间不能为空");
		return;
	}
	if($("#activityBeginTime").val().length==0){
		commUtil.alertError("活动开始时间不能为空");
		return;
	}
	if($("#activityEndTime").val().length==0){
		commUtil.alertError("活动结束时间不能为空");
		return;
	}
	if($("#enrollBeginTime").val()>=$("#enrollEndTime").val()){
		commUtil.alertError("报名开始时间必须小于报名结束时间");
		return;
	}

	if($("#activityBeginTime").val()>=$("#activityEndTime").val()){
		commUtil.alertError("活动开始时间必须小于活动结束时间");
		return;
	}
	
	if($("#enrollEndTime").val()>=$("#activityEndTime").val()){
		commUtil.alertError("报名结束时间必须小于活动结束时间");
		return;
	}
	
	if($("#description").val().length==0){
		commUtil.alertError("活动描述不能为空");
		return;
	}
	if($("#limitMchtNum").val().length==0){
		commUtil.alertError("限商家名额不能为空");
		return;
	}
	if($("#benefitPoint").val().length==0){
		commUtil.alertError("利益点不能为空");
		return;
	}
	
	if($("#pushMchtType").val().length==0){//指定商家
		if($("#thisAppoint").val()==1){//指定商家
			if($("#mchtIdGroup").val().length==0){
				commUtil.alertError("请选择指定商家");
				return;
			}
		}
	}else{
		if($("#pushMchtType").val()!='1' && $("#pushMchtType").val()!='2' && $("#pushMchtType").val()!='3'){
			commUtil.alertError("请选择推送商家类型");
			return;
		}
	}
	
	if($("#thisAppoint").val()==1){//指定商家
		if($("#mchtIdGroup").val().length==0){
			commUtil.alertError("请选择指定商家");
			return;
		}
	}
	var pushMchtTypeLength = $("input[name='pushMchtType']:checked").length;
	var appointLength = $("input[name='appoint']:checked").length;
	if(pushMchtTypeLength == 0 && appointLength == 0){
		commUtil.alertError("请选择推送商家");
		return;
	}
	//专场特惠
	var preferentialType=$("#preferentialType").val();
	if(preferentialType==null||preferentialType==""||preferentialType==undefined){
		commUtil.alertError("请选择专场特惠");
		return;
	}
	//优惠券
	if(preferentialType==1){
		var couponList=[];
		if($(".couponList").html().indexOf("div")>-1){
			var flag = true;
			$(".coupon_list").each(function(){
				var couponId = $(this).find('input').eq(0).val(); 
				var money = $(this).find('input').eq(1).val(); 
				var minimum = $(this).find('input').eq(2).val(); 
				var grantQuantity = $(this).find('input').eq(3).val(); 
				var recTypeNow = $(this).find('input').eq(4); 
				var rec = $(this).find('input').eq(5); 
				var needIntegral = $(this).find('input').eq(6).val(); 
				var recType=null;
				if(money.length==0){
					flag=false;
					commUtil.alertError("面额不能为空");
					return;
				}				
				if(minimum.length==0){
					flag=false;
					commUtil.alertError("使用条件不能为空");
					return;
				}
				if(eval(money)>=eval(minimum)){
					flag=false;
					commUtil.alertError("面额必须小于使用条件");
					return;
				}
				if(grantQuantity.length==0){
					flag=false;
					commUtil.alertError("发行量不能为空");
					return;
				}				
				if(!recTypeNow.prop('checked')&& !rec.prop('checked')){
					flag=false;
					commUtil.alertError("请选择免费领取或者金币兑换");
					return;
				}else{
					if(recTypeNow.prop('checked')){
						recType=1;
					}else if(rec.prop('checked')){
						recType=2;
						if(needIntegral==""){
							flag=false;
							commUtil.alertError("请选择免金币兑换数量");
							return;
						}
					}
				}
				var object = new Object();
				if(couponId!=null){
	        		object.couponId = couponId;
				}else{
					object.couponId = 0;
				}
        		object.money = money;
        		object.minimum = minimum;
        		object.grantQuantity = grantQuantity;
        		object.needIntegral = needIntegral;
        		object.recType = recType;
        		couponList.push(object);
			});
			if(!flag){
    			return false;
    		}
			var jsonCoupon = JSON.stringify(couponList);
			$("#jsonCoupon").val(jsonCoupon);
		}	
		
		var recLimitType=$("#recLimitType").val();
		if(recLimitType.length==0){
			commUtil.alertError("请选择限领类型");
			return;
		}else{
			if(recLimitType==2){
				if($("#recEach").val().length==0){
					commUtil.alertError("请选择限领张数");
					return;
				}
			}
		}
	}
	
	//满减
	if(preferentialType==2){
		var fullCutList=[];
		var fullName=null;
		var reduceName=null;
		var sumFlag=0;
		var ladderFlag=$("#ladderFlag").val();
		if(ladderFlag.length==0){
			commUtil.alertError("请选择是否阶梯");
			return;
		}
		if(ladderFlag==0){//非阶梯
			if($("#full").val().length==0){
				commUtil.alertError("请输入满价");
				return;
			}
			if($("#reduceId").val().length==0){
				commUtil.alertError("请输入折扣价");
				return;
			}
			fullName=$("#full").val();
			reduceName=$("#reduceId").val();
			if($("#flagSum").prop("checked")){
				sumFlag=1;
			}
			var object = new Object();
			object.fullName=fullName;
			object.reduceName=reduceName;
			object.sumFlag=sumFlag;
			fullCutList.push(object);
		}
		if(ladderFlag==1){
			if($(".full-class").html().indexOf("div")>-1){
				var flagFull = true;
				$(".full_class_list").each(function(){
					fullName = $(this).find('input').eq(0).val(); 
					reduceName = $(this).find('input').eq(1).val(); 
					if(fullName.length==0){
						flagFull=false;
						commUtil.alertError("请输入满价");
						return;
					}
					if(reduceName.length==0){
						flagFull=false;
						commUtil.alertError("请输入折扣价");
						return;
					}
					
					var object = new Object();
					object.fullName=fullName;
					object.reduceName=reduceName;
					object.sumFlag=0;
					fullCutList.push(object);
				});
				if(!flagFull){
	    			return false;
	    		}
			}
		}
		var jsonFullCut = JSON.stringify(fullCutList);
		$("#jsonFullCut").val(jsonFullCut);
	}
	
	//满赠
	if(preferentialType==3){
		if($("#type").val().length==0){
			commUtil.alertError("请选择满赠类型");
			return;
		}
		if($("#type").val()==1){
			if($("#fullGiveMinimum").val().length==0){
				commUtil.alertError("请输入最低价格");
				return;
			}
		}
		if($("#couponFlag").val()==1){
			if($("#preferentialIdGroup").val().length==0){
				commUtil.alertError("请选择优惠券");
				return;
			}
		}
		if($("#integralFlag").val()==1){
			if($("#integral").val().length==0){
				commUtil.alertError("请输入金币数量");
				return;
			}
		}
	}
	
	//多买优惠
	if(preferentialType==4){
		var fullDiscount=$("#fullDiscount").val();
		if(fullDiscount.length==0){
			commUtil.alertError("请选择多买类型");
			return;
		}
		if(fullDiscount==1){
			if($("#fullOfOne").val().length==0){
				commUtil.alertError("请输入多买规则：最低买多少件");
				return;
			}
			if($("#fullGiftsOneName").val().length==0){
				commUtil.alertError("请输入多买规则：最低高减少多少件");
				return;
			}
			if(eval($("#fullGiftsOneName").val())>=eval($("#fullOfOne").val())){
				commUtil.alertError("优惠件数必须小于购买件数");
				return;
			}
		}
		if(fullDiscount==2){
			if($("#fullOfTwo").val().length==0){
				commUtil.alertError("请输入多买规则：最低消费多少元");
				return;
			}
			if($("#fullGiftsTwoName").val().length==0){
				commUtil.alertError("请输入多买规则：任选多少件");
				return;
			}
		}
		if(fullDiscount==3){
			var fullDiscountList=[];
			if($(".full_discount_list").html().indexOf("div")>-1){
				var flagFullDiscount = true;
				$(".full_discount_list_loop").each(function(){
					fullPieces = $(this).find('input').eq(0).val(); 
					discountName = $(this).find('input').eq(1).val(); 
					if(fullPieces.length==0){
						flagFullDiscount=false;
						commUtil.alertError("请输入最低满件数量");
						return;
					}
					if(discountName.length==0){
						flagFullDiscount=false;
						commUtil.alertError("请输入最高折扣数");
						return;
					}
					
					var object = new Object();
					object.fullPieces=fullPieces;
					object.discountName=discountName;
					fullDiscountList.push(object);
				});
				if(!flagFullDiscount){
	    			return false;
	    		}
				var jsonFullDiscount = JSON.stringify(fullDiscountList);
				$("#jsonFullDiscount").val(jsonFullDiscount);
			}
			
		}
	}
	$("#activityAreaForm").submit();
		
}
</script>
<html>
<body>
<form id="activityAreaForm" method="post" action="${pageContext.request.contextPath}/activityArea/addActivityArea.shtml">
	<input type="hidden" id="id" name="id" value="${activityArea.id}"/>
	<input type="hidden" id="fullCutId" name="fullCutId" value="${fullCut.id}"/>
	<input type="hidden" id="fullGiveId" name="fullGiveId" value="${fullGiveInfo.id}"/>
	<input type="hidden" id="fullDiscountId" name="fullDiscountId" value="${fullDiscount.id}"/>
<!-- 	优惠券 -->
	<input type="hidden" id="jsonCoupon" name="jsonCoupon">
<!-- 	满减 -->
	<input type="hidden" id="jsonFullCut" name="jsonFullCut">
<!-- 	多买多送 -->
	<input type="hidden" id="jsonFullDiscount" name="jsonFullDiscount"/>
	
	
	<table class="gridtable">
		<tr>
			<td class="title">活动名称：</td>
			<td align="left" class="l-table-edit-td"><input type="text" id="name" name="name" value="${activityArea.name}" style="width: 300px;height: 18px;" /></td>
		</tr>
		<tr>
			<td class="title">活动类型：</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input type="hidden" id="areaType" name="areaType" value="${activityArea.type}" />
				<input type="radio" id="brand" name="brand" value="${activityArea.type}" onclick="typeOnclick(1);" <c:if test="${activityArea.type eq 1}">checked="checked"</c:if> />品牌活动&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="single" name="single" value="${activityArea.type}" onclick="typeOnclick(2);" <c:if test="${activityArea.type eq 2}">checked="checked"</c:if>/>单品活动
			</td>
		</tr>
		<tr>
			<td  class="title">入口图：</td>
			<td align="left" class="l-table-edit-td">
				<div><img id="entryPicImg" style="width: 400px;height: 200px"  alt="" src="${pageContext.request.contextPath}/file_servelt${activityArea.entryPic}"></div>
    			<div style="float: left;margin: 10px;">
    			<input style="position:absolute; opacity:0;" type="file" id="entryPicFile" name="file" onchange="ajaxFileUploadEntryPic();" />
    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
    			</div>
    			<input id="entryPic" name="entryPic" type="hidden" value="${activityArea.entryPic}">
			</td>
		</tr>
		<tr>
			<td class="title">活动报名图：</td>
			<td align="left" class="l-table-edit-td">
				<div><img id="areaPicImg" style="width: 200px;height: 200px" alt="" src="${pageContext.request.contextPath}/file_servelt${activityArea.pic}"></div>
    			<div style="float: left;margin: 10px;">
    			<input style="position:absolute; opacity:0;" type="file" id="areaPicFile" name="file" onchange="ajaxFileUploadPic();" />
    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
    			</div>
    			<input id="pic" name="pic" type="hidden" value="${activityArea.pic}">
			</td>
		</tr>
		<tr>
		
			<td class="title">活动报名时间：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="enrollBeginTime" name="enrollBeginTime" value="<fmt:formatDate value="${activityArea.enrollBeginTime}" pattern="yyyy-MM-dd HH:mm"/>"/>&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" id="enrollEndTime" name="enrollEndTime" value="<fmt:formatDate value="${activityArea.enrollEndTime}" pattern="yyyy-MM-dd HH:mm"/>"/>
			</td>
		</tr>
		<tr>
		
			<td class="title">活动时间：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="activityBeginTime" name="activityBeginTime" value="<fmt:formatDate value="${activityArea.activityBeginTime}" pattern="yyyy-MM-dd HH:mm"/>"/>
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input type="text" id="activityEndTime" name="activityEndTime" value="<fmt:formatDate value="${activityArea.activityEndTime}" pattern="yyyy-MM-dd HH:mm"/>"/>
			</td>
		</tr>
		<tr>
			<td class="title">活动描述：</td>
			<td align="left" class="l-table-edit-td">
				<textarea rows="5" cols="100" id="description" name="description">${activityArea.description}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title">限商家名额：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="limitMchtNum" name="limitMchtNum" value="${activityArea.limitMchtNum}" />
			</td>
		</tr>
		<tr>
			<td class="title">利益点：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="benefitPoint" name="benefitPoint" value="${activityArea.benefitPoint}" />
			</td>
		</tr>
		<tr>
			<td class="title">面向对象：</td>
			<td align="left" class="l-table-edit-td">
				<select style="width: 120px;" id="minMemberGroup" name="minMemberGroup">
					<c:forEach items="${memberGroupList}" var="group">
						<option value="${group.id}" <c:if test="${memberGroup.id eq group.id }">selected='selected'</c:if> >${group.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="title">推送商家：</td>
			<td align="left" class="l-table-edit-td">
				<input type="hidden" id="pushMchtType" name="pushMchtType" value="${activityArea.pushMchtType}"/>
				<input type="hidden" id="thisAppoint" name="thisAppoint" value="${activityArea.mchtIdGroup}"/>
				<input type="checkbox" id="platform" name="pushMchtType" value="${activityArea.pushMchtType}" onclick="platForm(1);" <c:if test="${activityArea.pushMchtType eq 1}">checked="checked"</c:if> />&nbsp;全平台&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="pop" name="pushMchtType" value="${activityArea.pushMchtType}" onclick="platForm(2);" <c:if test="${activityArea.pushMchtType eq 2}">checked="checked"</c:if> />&nbsp;POP商家&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="pool" name="pushMchtType" value="${activityArea.pushMchtType}" onclick="platForm(3);" <c:if test="${activityArea.pushMchtType eq 3}">checked="checked"</c:if> />&nbsp;SPOP商家&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="appoint" name="appoint" value="" onclick="appointMember();" <c:if test="${not empty activityArea.mchtIdGroup}">checked="checked"</c:if> />&nbsp;指定商家
			</td>
		</tr>

		<tr class="text">
			<td class="title">指定商家：</td>
			<td align="left" class="l-table-edit-td">
				<input type="hidden" id="mchtIdGroup" name="mchtIdGroup" value="${activityArea.mchtIdGroup}"/>
				<textarea id="mchtIdGroups" rows="5" disabled="disabled" class="textarea" cols="100">${mchtInfoGroup}</textarea>
				<input type="button" style="cursor:pointer;width: 100px;height: 30px;" value="选择商家" onclick="choiceMemberList();"/>
			</td>
		</tr>

		<tr>
			<td class="title">专场特惠：</td>
			<td align="left" class="l-table-edit-td">
				<input type="hidden" id="preferentialType" name="preferentialType" value="${activityArea.preferentialType}"/>
				<input type="radio" id="nothingType" name="nothingType" value="0" onclick="promotion(0);" <c:if test="${activityArea.preferentialType eq 0}">checked="checked"</c:if> />&nbsp;无&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="couponType" name="couponType" value="1" onclick="promotion(1);" <c:if test="${activityArea.preferentialType eq 1}">checked="checked"</c:if> />&nbsp;优惠券&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="fullCutType" name="fullCutType" value="2" onclick="promotion(2);" <c:if test="${activityArea.preferentialType eq 2}">checked="checked"</c:if> />&nbsp;满减&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="fullOfGiftsType" name="fullOfGiftsType" value="3" onclick="promotion(3);" <c:if test="${activityArea.preferentialType eq 3}">checked="checked"</c:if> />&nbsp;满赠&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="buyDiscountType" name="buyDiscountType" value="4" onclick="promotion(4);" <c:if test="${activityArea.preferentialType eq 4}">checked="checked"</c:if> />&nbsp;多买优惠
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
								限领：&nbsp;&nbsp;<input class="radioItem" type="radio" id="reclimit" name="reclimit" value="1" <c:if test="${copuonMap.recLimitType eq 1}">checked="checked"</c:if>  onclick="recLimitTypeOnclick(1);">每人每天限领1张
								&nbsp;&nbsp;&nbsp;<input class="radioItem"  type="radio" id="recli" name="recli" value="2" <c:if test="${copuonMap.recLimitType eq 2}">checked="checked"</c:if> onclick="recLimitTypeOnclick(2);" >每人限领
									&nbsp;&nbsp;<input type="text" style="width: 50px;" id="recEach"  name="recEach"  value="${copuonMap.recEach}" />&nbsp;张
								&nbsp;&nbsp;&nbsp;<input class="radioItem" type="radio" id="reclimi" name="reclimi" value="3" <c:if test="${copuonMap.recLimitType eq 3}">checked="checked"</c:if> onclick="recLimitTypeOnclick(3);">不限
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
								满&nbsp;<input type="text" id="full" name="fullName" value="${fullName}" style="width: 50px;"/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input type="text" id="reduceId" name="reduceName" value="${reduceName}" style="width: 50px;"/>元&nbsp;&nbsp;<input type="checkbox" id="flagSum"  name="flagSum" value="" validate="{radioRequired:true}" onclick="sumFlagOnclick();" <c:if test="${fullCut.sumFlag==1}">checked="checked"</c:if> />&nbsp;累加
							</td>
						</tr>
						<tr class="ladderIs">
							<td style="border:none">
								<div class="full-class">
									<!-- <div class="full-class-list0 full_class_list">
										满&nbsp;<input type="text" id="full0" name="fullName0" value=""/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input type="text" id="reduceId0" name="reduceName0" value=""/>元&nbsp;&nbsp;<input type="button" id="fullAdd0" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullAdd(0);"  value="[ + ]">
									</div><br> -->
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
								<input type="hidden" id="type" name="type" value="${fullGiveInfo.type}">
								<span class="radioClass"><input border="none" class="radioItem" type="radio" value="1" id="fullOf" name="fullOf" validate="{radioRequired:true}" onclick="fullOfGifts(1);" <c:if test="${fullGiveInfo.type eq 1}">checked="checked"</c:if>>满额赠</span>
								<span class="radioClass"><input border="none" class="radioItem" type="radio" value="2" id="fullGifts" name="fullGifts" validate="{radioRequired:true}" onclick="fullOfGifts(2);" <c:if test="${fullGiveInfo.type eq 2}">checked="checked"</c:if>>买即赠</span>
							</td>
						</tr>
						<tr class="ofGifts">
							<td style="border:none">
								<input type="hidden" id="sumFlag" name="sumFlag" value="${fullGiveInfo.sumFlag}"/>
								满额：&nbsp;满&nbsp;<input type="text" id="fullGiveMinimum" name="fullGiveMinimum" style="width: 50px;" value="${fullGiveInfo.minimum}"/>&nbsp;元&nbsp;&nbsp;<input type="checkbox" id="fullGiveSumFlag" name="fullGiveSumFlag" value="${fullGiveInfo.sumFlag }" <c:if test="${fullGiveInfo.sumFlag eq 1}">checked="checked"</c:if>  onclick="fullGiveOnclick();"/>&nbsp;累加
							</td>
						</tr>
						<tr>
							<td style="border:none">
								<div style="float: left;">
									<input type="hidden" id="couponFlag" name="couponFlag" value="${fullGiveInfo.couponFlag}"/>
									<input type="hidden" id="preferentialIdGroup" name="preferentialIdGroup" value="${fullGiveInfo.couponIdGroup}"/>
									<input type="checkbox" id="couponFlagId" value="${fullGiveInfo.couponFlag}" onclick="couponFlagOnclick();" <c:if test="${fullGiveInfo.couponFlag eq 1}">checked="checked"</c:if> />优惠券&nbsp;&nbsp;&nbsp;
									<input style="display: none;width: 300px;height: 20px;" id="coupon_id_group" disabled="disabled" type="text" value="${couponName}"/>&nbsp;&nbsp;
								</div>
								<div style="float: left;">
									<input type="button" id="choice" style="background-color: rgba(255, 153, 0, 1);width: 100px;display: none;cursor:pointer;" onclick="choiceOnclick();" value="选择优惠券">
								</div>
							</td>
						</tr>
						<tr>
							<td style="border:none">
								<input type="hidden" id="integralFlag" name="integralFlag" value="${fullGiveInfo.integralFlag}" />
								<input type="checkbox" id="integral_flag" value="" onclick="integralFlagOnclick();" <c:if test="${fullGiveInfo.integralFlag eq 1}">checked="checked"</c:if> />金币&nbsp;&nbsp;&nbsp;
								<samp id="integralSamp" style="display: none"><input type="text" id="integral" name="integral" style="width: 50px;" value="${fullGiveInfo.integral}"/>金币</samp>
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
								<span class="radioClass"><input border="none"  type="radio" value="1" id="fullDisM"  validate="{radioRequired:true}" <c:if test="${fullDiscount.type eq 1}">checked="checked"</c:if> onclick="fullDiscountOnclick(1);">满M件减N件</span>
								<span class="radioClass"><input border="none"  type="radio" value="2" id="fullDisN" validate="{radioRequired:true}" <c:if test="${fullDiscount.type eq 2}">checked="checked"</c:if> onclick="fullDiscountOnclick(2);">M件N元</span>
								<span class="radioClass"><input border="none"  type="radio" value="3" id="fullDisMN" validate="{radioRequired:true}" <c:if test="${fullDiscount.type eq 3}">checked="checked"</c:if> onclick="fullDiscountOnclick(3);">M件N折</span>
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
									<!-- <div class="full_discount_list_div0 full_discount_list_loop">
										<span class="radioClass">满&nbsp;<input border="none" type="text" value="" id="fullOf0" name="fullOf0">&nbsp;件&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<span class="radioClass"><input border="none" type="text" value="" id="fullGifts0" name="fullGifts0">&nbsp;折</span>
										&nbsp;&nbsp;<input type="button" id="discountAdd0" style="color: red;background-color: #FFFFFF;border: none;" onclick="fullDiscountAdd(0);"  value="[ + ]">
									</div><br> -->
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td class="title">活动地址：</td>
			<td align="left" class="l-table-edit-td">
				<samp style="font-size: 15px;"><c:if test="${not empty activityArea.templetSuffix}">http://m.xgbuy.cc${activityArea.templetSuffix}</c:if></samp>
<%-- 				<input type="hidden" id="oldUrlSuffix" name="oldUrlSuffix" value="${activityArea.urlSuffix}" />
				<input style="width: 250px; height: 20px;" type="text" id="urlSuffix" name="urlSuffix" value="${activityArea.urlSuffix}" />
				<input type="button" style="background-color: rgba(0, 207, 226, 1);border: none;width: 150px;height: 30px;" value="重复检测" onclick="suffixIsHased();"/> --%>
			</td>
		</tr>
		<tr>
			<td class="title">创建人：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" disabled="disabled" value="${activityArea.createStaffName}"/>
			</td>
		</tr>
		<c:if test="${not empty activityArea.id}">
		<tr>
			<td class="title">状态：</td>
			<td align="left" class="l-table-edit-td">
				<input type="hidden" id="status" name="status" value="${activityArea.status}">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="statusEnable" onclick="statusOnclick(1);" <c:if test="${activityArea.status eq 1}">checked="checked"</c:if>/>&nbsp;启用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="statusWeishiy" onclick="statusOnclick(0);" <c:if test="${activityArea.status eq 0}">checked="checked"</c:if>/>&nbsp;暂不启用
			</td>
		</tr>
		</c:if>
	</table>
	<c:if test="${activityArea.status!=1}">
	<br/>
	<table class="gridtable">
		<tr>
			<td style="border:none">
				<input type="button" style="width: 150px;height: 50px;background-color: rgba(22, 155, 213, 1);border: none;" value="<c:if test="${activityArea.id==null}">创建会场</c:if><c:if test="${activityArea.id!=null}">保存会场</c:if>" onclick="createActivityArea();"/>
			</td>
		</tr>
	</table>
	</c:if>
</form>
</body>
</html>
