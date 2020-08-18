<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
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
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
.l-verify-tip-corner{left:20px!important;}
.l-verify-tip-content{left:27px!important;}
</style>
<script type="text/javascript">

 $(function (){
	 
	$("#signDateBegin").ligerDateEditor({
			showTime : false,
			width: 158,
			format: "yyyy-MM-dd hh:mm"
	});
	$("#signDateEnd").ligerDateEditor({
			showTime : false,
			width: 158,
			format: "yyyy-MM-dd hh:mm"
	});
	 
	$("#lastLoginDateBegin").ligerDateEditor({
		showTime : false,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
    });
   $("#lastLoginDateEnd").ligerDateEditor({
		showTime : false,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
    });
   
   $("#lastExpenseDateBegin").ligerDateEditor({
		showTime : false,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
   });
   
  $("#lastExpenseDateEnd").ligerDateEditor({
		showTime : false,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
   });
  
  $("#loginDateBegin").ligerDateEditor({
		showTime : false,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
   });
  $("#loginDateEnd").ligerDateEditor({
		showTime : false,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
   });
  
  $("#payDateBegin").ligerDateEditor({
		showTime : false,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
  });
  $("#payDateEnd").ligerDateEditor({
		showTime : false,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
  });
  
  
  $("#labelTypeId").bind('change',function(){
		var memberLabelTypeid = $(this).val();
		$.ajax({
			url : "${pageContext.request.contextPath}/memberLabel/memberLabelType.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"memberLabelTypeid":memberLabelTypeid},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var memberLabels = data.memberLabels;
					var optionArray = [];
					optionArray.push('<option value="">请选择</option>');
					for(var i=0;i<memberLabels.length;i++){
						var memberLabelid = memberLabels[i].id;
						var labelName = memberLabels[i].labelName;
						optionArray.push('<option value="'+memberLabelid+'">'+labelName+'</option>');
					}
					$("#labelId").html(optionArray.join(""));
				}else {
				
					var memberLabels = data.memberLabels;
					var optionArray = [];
					optionArray.push('<option value="">请选择</option>');
					for(var i=0;i<memberLabels.length;i++){
						var memberLabelid = memberLabels[i].id;
						var labelName = memberLabels[i].labelName;
						optionArray.push('<option value="'+memberLabelid+'">'+labelName+'</option>');
					}
					$("#labelId").html(optionArray.join(""));
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	
  
   $.metadata.setType("attr", "validate");
   $("#form1").validate({
 		errorPlacement : function(lable, element) {
 			var elementType=$(element).attr("type");

         	if($(element).hasClass("l-text-field")){
         		$(element).parent().ligerTip({
 					content : lable.html(),width: 100
 				});
         	}
         	else if('radio'==elementType){
         		var radioName=$(element).attr("expiryType");
         		$("input[type=radio][name="+radioName+"]:last").ligerTip({
 					content : lable.html(),width: 100
 				});
         	}
         	else{
         		$(element).ligerTip({
 					content : lable.html(),width: 100
 				});
         	}
 		},
 		success : function(lable) {
 			lable.ligerHideTip();
 			lable.remove();
 		},
 		submitHandler : function(form) {
 			var signDateBegin= document.getElementById("signDateBegin");
 			var lastLoginDateBegin= document.getElementById("lastLoginDateBegin");
 			var lastExpenseDateBegin= document.getElementById("lastExpenseDateBegin");
 			var loginDateBegin= document.getElementById("loginDateBegin");
 			var payDateBegin= document.getElementById("payDateBegin");
 			var payCountBegin= document.getElementById("payCountBegin");
 			var payAmountBegin= document.getElementById("payAmountBegin");
			var payLogCountBegin=document.getElementById("payLogCountBegin");
			var payLogAmountBegin=document.getElementById("payLogAmountBegin");
			var sportTypeCountBegin=document.getElementById("sportTypeCountBegin");
			var costumeTypeCountBegin=document.getElementById("costumeTypeCountBegin");
			var shoeTypeCountBegin=document.getElementById("shoeTypeCountBegin");
			var jewelTypeCountBegin=document.getElementById("jewelTypeCountBegin");
			var liveTypeCountBegin=document.getElementById("liveTypeCountBegin");
			var digitalTypeCountBegin=document.getElementById("digitalTypeCountBegin");
			var makeupTypeCountBegin=document.getElementById("makeupTypeCountBegin");
			var childTypeCountBegin=document.getElementById("childTypeCountBegin");
			var foodTypeCountBegin=document.getElementById("foodTypeCountBegin");
			var returnGoodsRateMin=document.getElementById("returnGoodsRateMin");
			var returnGoodsRateMax=document.getElementById("returnGoodsRateMax");
			var refundRateMin=document.getElementById("refundRateMin");
			var refundRateMax=document.getElementById("refundRateMax");
			var svipYear=$("option:selected") .val();
			if ($.trim(signDateBegin.value) == "" && $.trim(lastLoginDateBegin.value) == "" && $.trim(lastExpenseDateBegin.value) == "" && $.trim(loginDateBegin.value) == "" && $.trim(payDateBegin.value) == "" && $.trim(payDateBegin.value) == "" && $.trim(payCountBegin.value) == "" && $.trim(payAmountBegin.value) == "" && $.trim(payLogCountBegin.value) == "" && $.trim(payLogAmountBegin.value) == "" && $.trim(sportTypeCountBegin.value) == "" && $.trim(costumeTypeCountBegin.value) == "" && $.trim(shoeTypeCountBegin.value) == "" && $.trim(jewelTypeCountBegin.value) == "" && $.trim(liveTypeCountBegin.value)=="" && $.trim(digitalTypeCountBegin.value)=="" && $.trim(makeupTypeCountBegin.value)=="" && $.trim(childTypeCountBegin.value)=="" && $.trim(foodTypeCountBegin.value)=="" && $.trim(returnGoodsRateMin.value)=="" && $.trim(returnGoodsRateMax.value)=="" && $.trim(refundRateMin.value)=="" && $.trim(refundRateMax.value)=="" && svipYear == "") {
				commUtil.alertError("请填写规则条件！");
				return;
			}
 		
			var Verification=true;
			$(".l-table-edit-td").each(function(index, element){
				if (($(this).find("[name='signDateBegin']").val()!="" && $(this).find("[name='signDateEnd']").val()=="") || ($(this).find("[name='signDateBegin']").val()=="" && $(this).find("[name='signDateEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
				if (($(this).find("[name='lastLoginDateBegin']").val()!="" && $(this).find("[name='lastLoginDateEnd']").val()=="") || ($(this).find("[name='lastLoginDateBegin']").val()=="" && $(this).find("[name='lastLoginDateEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				} 
				if (($(this).find("[name='lastExpenseDateBegin']").val()!="" && $(this).find("[name='lastExpenseDateEnd']").val()=="") || ($(this).find("[name='lastExpenseDateBegin']").val()=="" && $(this).find("[name='lastExpenseDateEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='loginDateBegin']").val()!="" && $(this).find("[name='loginDateEnd']").val()=="") || ($(this).find("[name='loginDateBegin']").val()=="" && $(this).find("[name='loginDateEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='payDateBegin']").val()!="" && $(this).find("[name='payDateEnd']").val()=="") || ($(this).find("[name='payDateBegin']").val()=="" && $(this).find("[name='payDateEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='payAmountBegin']").val()!="" && $(this).find("[name='payAmountEnd']").val()=="") || ($(this).find("[name='payAmountBegin']").val()=="" && $(this).find("[name='payAmountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='payLogCountBegin']").val()!="" && $(this).find("[name='payLogCountEnd']").val()=="") || ($(this).find("[name='payLogCountBegin']").val()=="" && $(this).find("[name='payLogCountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='payLogAmountBegin']").val()!="" && $(this).find("[name='payLogAmountEnd']").val()=="") || ($(this).find("[name='payLogAmountBegin']").val()=="" && $(this).find("[name='payLogAmountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='sportTypeCountBegin']").val()!="" && $(this).find("[name='sportTypeCountEnd']").val()=="") || ($(this).find("[name='sportTypeCountBegin']").val()=="" && $(this).find("[name='sportTypeCountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='costumeTypeCountBegin']").val()!="" && $(this).find("[name='costumeTypeCountEnd']").val()=="") || ($(this).find("[name='costumeTypeCountBegin']").val()=="" && $(this).find("[name='costumeTypeCountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='shoeTypeCountBegin']").val()!="" && $(this).find("[name='shoeTypeCountEnd']").val()=="") || ($(this).find("[name='shoeTypeCountBegin']").val()=="" && $(this).find("[name='shoeTypeCountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='jewelTypeCountBegin']").val()!="" && $(this).find("[name='jewelTypeCountEnd']").val()=="") || ($(this).find("[name='jewelTypeCountBegin']").val()=="" && $(this).find("[name='jewelTypeCountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='liveTypeCountBegin']").val()!="" && $(this).find("[name='liveTypeCountEnd']").val()=="") || ($(this).find("[name='liveTypeCountBegin']").val()=="" && $(this).find("[name='liveTypeCountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='digitalTypeCountBegin']").val()!="" && $(this).find("[name='digitalTypeCountEnd']").val()=="") || ($(this).find("[name='digitalTypeCountBegin']").val()=="" && $(this).find("[name='digitalTypeCountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='makeupTypeCountBegin']").val()!="" && $(this).find("[name='makeupTypeCountEnd']").val()=="") || ($(this).find("[name='makeupTypeCountBegin']").val()=="" && $(this).find("[name='makeupTypeCountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='childTypeCountBegin']").val()!="" && $(this).find("[name='childTypeCountEnd']").val()=="") || ($(this).find("[name='childTypeCountBegin']").val()=="" && $(this).find("[name='childTypeCountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='foodTypeCountBegin']").val()!="" && $(this).find("[name='foodTypeCountEnd']").val()=="") || ($(this).find("[name='foodTypeCountBegin']").val()=="" && $(this).find("[name='foodTypeCountEnd']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='returnGoodsRateMin']").val()!="" && $(this).find("[name='returnGoodsRateMax']").val()=="") || ($(this).find("[name='returnGoodsRateMin']").val()=="" && $(this).find("[name='returnGoodsRateMax']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
                if (($(this).find("[name='refundRateMin']").val()!="" && $(this).find("[name='refundRateMax']").val()=="") || ($(this).find("[name='refundRateMin']").val()=="" && $(this).find("[name='refundRateMax']").val()!="")) {
					commUtil.alertError("请填写完整规则条件！");
 					return Verification=false;
				}
    			
    		});
 	           
			  if (Verification==true) {
     	          formSubmit();
				
			 }

 		}
 	}); 
    
}); 




function formSubmit() {
	$.ajax({
		url : "${pageContext.request.contextPath}/memberLabel/addmemberLabelRule.shtml",
		type : 'POST',
		dataType : 'json',
		data : $("#form1").serialize(),
		success : function(data) {
			if ("0000" == data.returnCode) {
				$.ligerDialog.success(data.returnMsg);
				parent.location.reload();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}



 //1.去掉多余的小数点
 //2.保证只能输入小数点或数字
 function onlyNonNegative(obj) {
	 var inputChar = event.keyCode;
	 //alert(event.keyCode);

	 //1.判断是否有多于一个小数点
	 if(inputChar==190 ) {//输入的是否为.
		 var index1 = obj.value.indexOf(".") + 1;//取第一次出现.的后一个位置
		 var index2 = obj.value.indexOf(".",index1);
		 while(index2!=-1) {
			 //alert("有多个.");

			 obj.value = obj.value.substring(0,index2);
			 index2 = obj.value.indexOf(".",index1);
		 }
	 }
	 //2.如果输入的不是.或者不是数字，替换 g:全局替换
	 obj.value = obj.value.replace(/[^(\d|.)]/g,"");
 }

</script>

</head>
	<body style="margin: 10px;">
	<form method="post" id="form1" name="form1" class="form1" action="" >
		<input type="hidden" id="id" name="id" value="${memberLabelRule.id}"/>
		<table class="gridtable"> 
			<tr>
				<td colspan="1" class="title">规则条件<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
				    <p style="color: red;">温馨提示:以下条件可多选，多个条件之间是交集关系</p><br>
					注册时间&nbsp;&nbsp;在&nbsp;<input type="text" id="signDateBegin" name="signDateBegin" value="<fmt:formatDate value="${memberLabelRule.signDateBegin}" pattern="yyyy-MM-dd"/>"/>&nbsp;至&nbsp;<input type="text" id="signDateEnd" name="signDateEnd" value="<fmt:formatDate value="${memberLabelRule.signDateEnd}" pattern="yyyy-MM-dd"/>"/>&nbsp;之间
					<br><br>
					  最后登入时间&nbsp;&nbsp;在&nbsp;<input type="text" id="lastLoginDateBegin" name="lastLoginDateBegin" value="<fmt:formatDate value="${memberLabelRule.lastLoginDateBegin}" pattern="yyyy-MM-dd"/>"/>&nbsp;至&nbsp;<input type="text" id="lastLoginDateEnd"  name="lastLoginDateEnd" value="<fmt:formatDate value="${memberLabelRule.lastLoginDateEnd}" pattern="yyyy-MM-dd"/>"/>&nbsp;之间
					<br><br>
					  最后消费时间&nbsp;&nbsp;在&nbsp;<input type="text" id="lastExpenseDateBegin" name="lastExpenseDateBegin" value="<fmt:formatDate value="${memberLabelRule.lastExpenseDateBegin}" pattern="yyyy-MM-dd"/>"/>&nbsp;至&nbsp;<input type="text" id="lastExpenseDateEnd"  name="lastExpenseDateEnd" value="<fmt:formatDate value="${memberLabelRule.lastExpenseDateEnd}" pattern="yyyy-MM-dd"/>"/>&nbsp;之间
					<br><br>
					  在&nbsp;&nbsp;<input type="text" id="loginDateBegin" name="loginDateBegin" value="<fmt:formatDate value="${memberLabelRule.loginDateBegin}" pattern="yyyy-MM-dd"/>"/>&nbsp;至&nbsp;<input type="text" id="loginDateEnd" name="loginDateEnd" value="<fmt:formatDate value="${memberLabelRule.loginDateEnd}" pattern="yyyy-MM-dd"/>"/>&nbsp;之间有登入
				    <br><br>
					  在&nbsp;&nbsp;<input type="text" id="payDateBegin" name="payDateBegin" value="<fmt:formatDate value="${memberLabelRule.payDateBegin}" pattern="yyyy-MM-dd"/>"/>&nbsp;至&nbsp;<input type="text" id="payDateEnd" name="payDateEnd" value="<fmt:formatDate value="${memberLabelRule.payDateEnd}" pattern="yyyy-MM-dd"/>"/>&nbsp;之间有付款
					<br><br>
					 付款次数&nbsp;&nbsp;在&nbsp;<input type="text" id="payCountBegin" name="payCountBegin" value="${memberLabelRule.payCountBegin}" validate="{digits:true}"/>&nbsp;-&nbsp;<input type="text" id="payCountEnd" name="payCountEnd" value="${memberLabelRule.payCountEnd}" validate="{digits:true}"/>&nbsp;次之间
					<br><br>
					 付款金额&nbsp;&nbsp;在&nbsp;<input type="text" id="payAmountBegin" name="payAmountBegin" value="${memberLabelRule.payAmountBegin}" validate="{number:true}"/>&nbsp;-&nbsp;<input type="text" id="payAmountEnd" name="payAmountEnd" value="${memberLabelRule.payAmountEnd}" validate="{number:true}"/>&nbsp;元之间
					<br><br>
					历史付款次数&nbsp;&nbsp;在&nbsp;<input type="text" id="payLogCountBegin" name="payLogCountBegin" value="${memberLabelRule.payLogCountBegin}" validate="{digits:true}"/>&nbsp;-&nbsp;<input type="text" id="payLogCountEnd" name="payLogCountEnd" value="${memberLabelRule.payLogCountEnd}" validate="{digits:true}" />&nbsp;次之间
					<br><br>
					历史付款金额&nbsp;&nbsp;在&nbsp;<input type="text" id="payLogAmountBegin" name="payLogAmountBegin" value="${memberLabelRule.payLogAmountBegin}" validate="{number:true}"/>&nbsp;-&nbsp;<input type="text" id="payLogAmountEnd" name="payLogAmountEnd" value="${memberLabelRule.payLogAmountEnd}" validate="{number:true}"/>&nbsp;元之间
					<br><br>
					退货率&nbsp;&nbsp;在&nbsp;<input type="text" id="returnGoodsRateMin" name="returnGoodsRateMin" value="${memberLabelRule.returnGoodsRateMin}" validate="{number:true}" onkeyup="onlyNonNegative(this)"/>&nbsp;-&nbsp;<input type="text" id="returnGoodsRateMax" name="returnGoodsRateMax" value="${memberLabelRule.returnGoodsRateMax}" validate="{number:true}" onkeyup="onlyNonNegative(this)"/>&nbsp;%之间
					<br><br>
					退款率&nbsp;&nbsp;在&nbsp;<input type="text" id="refundRateMin" name="refundRateMin" value="${memberLabelRule.refundRateMin}" validate="{number:true}" onkeyup="onlyNonNegative(this)"/>&nbsp;-&nbsp;<input type="text" id="refundRateMax" name="refundRateMax" value="${memberLabelRule.refundRateMax}" validate="{number:true}" onkeyup="onlyNonNegative(this)"/>&nbsp;%之间
					<br><br>
					运动户外类目付款次数&nbsp;&nbsp;在&nbsp;<input type="text" id="sportTypeCountBegin" name="sportTypeCountBegin" value="${memberLabelRule.sportTypeCountBegin}" validate="{digits:true}"/>&nbsp;-&nbsp;<input type="text" id="sportTypeCountEnd" name="sportTypeCountEnd" value="${memberLabelRule.sportTypeCountEnd}" validate="{digits:true}"/>&nbsp;次之间
					<br><br>
					服装配饰类目付款次数&nbsp;&nbsp;在&nbsp;<input type="text" id="costumeTypeCountBegin" name="costumeTypeCountBegin" value="${memberLabelRule.costumeTypeCountBegin}" validate="{digits:true}"/>&nbsp;-&nbsp;<input type="text" id="costumeTypeCountEnd" name="costumeTypeCountEnd" value="${memberLabelRule.costumeTypeCountEnd}" validate="{digits:true}"/>&nbsp;次之间
					<br><br>
					鞋靴箱包类目付款次数&nbsp;&nbsp;在&nbsp;<input type="text" id="shoeTypeCountBegin" name="shoeTypeCountBegin" value="${memberLabelRule.shoeTypeCountBegin}" validate="{digits:true}"/>&nbsp;-&nbsp;<input type="text" id="shoeTypeCountEnd" name="shoeTypeCountEnd" value="${memberLabelRule.shoeTypeCountEnd}" validate="{digits:true}"/>&nbsp;次之间
					<br><br>
					钟表珠宝类目付款次数&nbsp;&nbsp;在&nbsp;<input type="text" id="jewelTypeCountBegin" name="jewelTypeCountBegin" value="${memberLabelRule.jewelTypeCountBegin}" validate="{digits:true}"/>&nbsp;-&nbsp;<input type="text" id="jewelTypeCountEnd" name="jewelTypeCountEnd" value="${memberLabelRule.jewelTypeCountEnd}" validate="{digits:true}"/>&nbsp;次之间
					<br><br>
					生活家居类目付款次数&nbsp;&nbsp;在&nbsp;<input type="text" id="liveTypeCountBegin" name="liveTypeCountBegin" value="${memberLabelRule.liveTypeCountBegin}" validate="{digits:true}"/>&nbsp;-&nbsp;<input type="text" id="liveTypeCountEnd" name="liveTypeCountEnd" value="${memberLabelRule.liveTypeCountEnd}" validate="{digits:true}"/>&nbsp;次之间
					<br><br>
					数码家电类目付款次数&nbsp;&nbsp;在&nbsp;<input type="text" id="digitalTypeCountBegin" name="digitalTypeCountBegin" value="${memberLabelRule.digitalTypeCountBegin}" validate="{digits:true}"/>&nbsp;-&nbsp;<input type="text" id="digitalTypeCountEnd" name="digitalTypeCountEnd" value="${memberLabelRule.digitalTypeCountEnd}" validate="{digits:true}"/>&nbsp;次之间
					<br><br>
					美妆个护类目付款次数&nbsp;&nbsp;在&nbsp;<input type="text" id="makeupTypeCountBegin" name="makeupTypeCountBegin" value="${memberLabelRule.makeupTypeCountBegin}" validate="{digits:true}"/>&nbsp;-&nbsp;<input type="text" id="makeupTypeCountEnd" name="makeupTypeCountEnd" value="${memberLabelRule.makeupTypeCountEnd}" validate="{digits:true}"/>&nbsp;次之间
					<br><br>
					母婴童装类目付款次数&nbsp;&nbsp;在&nbsp;<input type="text" id="childTypeCountBegin" name="childTypeCountBegin" value="${memberLabelRule.childTypeCountBegin}" validate="{digits:true}"/>&nbsp;-&nbsp;<input type="text" id="childTypeCountEnd" name="childTypeCountEnd" value="${memberLabelRule.childTypeCountEnd}" validate="{digits:true}"/>&nbsp;次之间
					<br><br>
					食品超市类目付款次数&nbsp;&nbsp;在&nbsp;<input type="text" id="foodTypeCountBegin" name="foodTypeCountBegin" value="${memberLabelRule.foodTypeCountBegin}" validate="{digits:true}"/>&nbsp;-&nbsp;<input type="text" id="foodTypeCountEnd" name="foodTypeCountEnd" value="${memberLabelRule.foodTypeCountEnd}" validate="{digits:true}"/>&nbsp;次之间
					<br><br>
					SVIP付费年限&nbsp;&nbsp;为&nbsp;
			        <select id="svipYear" name="svipYear" style="width:100px;">
				           <option value="">请选择</option>
				           <option value="1" <c:if test="${memberLabelRule.svipYear eq '1'}">selected</c:if>>1</option>
				           <option value="2" <c:if test="${memberLabelRule.svipYear eq '2'}">selected</c:if>>2</option>
				           <option value="3" <c:if test="${memberLabelRule.svipYear eq '3'}">selected</c:if>>3</option>
			        </select>
				</td>
			</tr> 
			<tr>
				<td colspan="1" class="title">标签类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
				<select id="labelTypeId" name="labelTypeId" style="width: 150px;" validate="{required:true}" >
               		<option value="">请选择</option> 
               		 <c:forEach var="memberLabelTypeList" items="${memberLabelTypeList}">
               		       <option value="${memberLabelTypeList.id}" <c:if test="${memberLabelRule.labelTypeId==memberLabelTypeList.id}">selected</c:if>>${memberLabelTypeList.labelTypeName}</option>
               		 </c:forEach>    			     
               	</select>
		    </tr>
		    <tr>
               <td class="title">标签选项<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<select id="labelId" name="labelId" style="width: 150px;" validate="{required:true}">
               			<option value="">请选择</option>
               			<c:if test="${not empty memberLabelRule}">
               			  <%-- <option value="${memberLabel.id}" selected="selected">${memberLabel.labelName}</option> --%>
               			  <c:forEach var="memberLabelList" items="${memberLabelList}">
               		         <option value="${memberLabelList.id}" <c:if test="${memberLabelRule.labelId==memberLabelList.id}">selected</c:if>>${memberLabelList.labelName}</option>
               		      </c:forEach>    
               			</c:if>
               		</select>
               </td>
	        </tr>      
			 <tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="保存" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>