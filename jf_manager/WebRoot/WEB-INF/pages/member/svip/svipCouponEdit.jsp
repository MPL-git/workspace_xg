<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
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
 	
	/* $(".dateEditor").ligerDateEditor({
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	}); */
	
	var linkTypeS= $("#linkTypeS").val();
	  
	  if (linkTypeS=='' || linkTypeS=='1' || linkTypeS=='2' || linkTypeS=='3' || linkTypeS=='4' || linkTypeS=='7') {
        if (linkTypeS=='1') {
      	 $("#typeName").html("会场ID");
		  }else if (linkTypeS=='2') {
			 $("#typeName").html("活动ID");
		 }else if (linkTypeS=='3') {
			 $("#typeName").html("商品ID");
		 }else if (linkTypeS=='4') {
			 $("#typeName").html("外面链接");
		 }else if (linkTypeS=='7') {
			 $("#typeName").html("自建页面ID");
		 }else {
			 $("#typeName").html("链接");
		 }
		  $("#linkValue").show();
		  $("#linkValue0").hide();
		  $("#linkValue1").hide();
		  $("#linkValue2").hide();
		  $("#linkValue3").hide();	  
	  }else if (linkTypeS=='6') {
		  $("#linkValue").hide();
		  $("#linkValue0").show();
		  $("#linkValue1").hide();
		  $("#linkValue2").hide();
		  $("#linkValue3").hide();
		  $("#typeName").html("栏目");
	  }else if (linkTypeS=='11') {
		  $("#linkValue").hide();
		  $("#linkValue0").hide();
		  $("#linkValue1").show();
		  $("#linkValue2").hide();
		  $("#linkValue3").hide();
		  $("#typeName").html("品牌特卖一级分类");
	  }else if (linkTypeS=='9') {
		  $("#linkValue").hide();
		  $("#linkValue0").hide();
		  $("#linkValue1").hide();
		  $("#linkValue2").show();
		  $("#linkValue3").hide();
		  $("#typeName").html("品牌团一级类目");
	 }else if (linkTypeS=='28') {
		 $("#linkValue").hide();
		  $("#linkValue0").hide();
		  $("#linkValue1").hide();
		  $("#linkValue2").hide();
		  $("#linkValue3").show();
		  $("#typeName").html("商城一级分类");
	}
	
	 var preferentialType= $("input[name='preferentialType']:checked").val();
	 if (preferentialType=='1') {
		 $("#dcm").hide();
		 $("#mmu").show();
	 }else if (preferentialType=='2') {
		 $("#dcm").show();
		 $("#mmu").hide();
	}
	 
	 
	 var conditiontype=$("select[name='conditionType']").val();
	  if (conditiontype=='1') {
		  $("#minimum1").hide();
		  $("#minicount1").hide();
	   }else if (conditiontype=='2') {
		   $("#minimum1").show();
		  $("#minicount1").hide();
	  }else if (conditiontype=='3') {
		  $("#minimum1").hide();
		  $("#minicount1").show();
	 }
	 
	
	 var couponType=$("input[name='couponType']:checked").val();
	 if (couponType=='1') {
		 $("#productType1").hide();
	 }else if (couponType=='2') {
		 $("#productType1").show();
	 }
	 
  var typeIds=$("#productTypeIds").val().replace('[','').replace(']','').replace(/\s+/g,"");
  var checkVar = document.getElementsByName("productTypeId");
	if(typeIds!=''){
		var strs= new Array(); 
		strs=typeIds.split(","); 
		for (i=0;i<strs.length;i++) {		
			for(j=0;j<checkVar.length;j++){
				if (strs[i]==checkVar[j].value){
					checkVar[j].checked =true; 
				}
			}		
		} 
	}
	
	
	$("#linkValue").bind('change',function(){
		var linkTypes=$("select[name='linkType']").val();
			var linkValue = $(this).val();
			if(linkValue){
				$.ajax({
					url : "${pageContext.request.contextPath}/marketing/checkExists.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {"linkValue":linkValue,"linkType":linkTypes},
					timeout : 30000,
					success : function(data) {
						if (data.returnCode!="0000" ) {
						   $.ligerDialog.error(data.returnMsg);
						 }		
					},
				});
			}
		});
		   
		
		$("#conditionType").bind('change',function(){
			var conditionTypes = $(this).val();
			if(conditionTypes=='2'){
				$("#minimum1").show();
				$("#minicount1").hide();
			}else if (conditionTypes=='3') {
				$("#minimum1").hide();
				$("#minicount1").show();
			}else if (conditionTypes=='1') {
				$("#minimum1").hide();
				$("#minicount1").hide();
			}
		});
		
		
		if (${couponCustom.status}!=0){
			$("#name,#money,#minimum,#expiryBeginDate,#expiryEndDate,#expiryDays,#minMemberGroup,#needIntegral,#recEach").attr("disabled","true");
			$("input[name='expiryType']").attr("disabled","true");
			$("input[name='recType']").attr("disabled","true");
			$("input[name='recLimitType']").attr("disabled","true");
		}
		
		if (${couponCustom.recType}==1){
			document.getElementById('integral').style.display = 'none';
		}
		if (${couponCustom.expiryType}==1){
		 	document.getElementById('expiryDate2').style.display = 'none';
		}else{
			document.getElementById('expiryDate1').style.display = 'none';
		}
	 	
	 	$(".radioItem1").change( 
	 			function() {				
	 				var expiryType = $("input[name='expiryType']:checked").val(); 
	 				if (expiryType == 1)
	 				{ 
	 					document.getElementById('expiryDate1').style.display = '';
	 					document.getElementById('expiryDate2').style.display = 'none';
	 				} 
	 				else
	 				{ 
	 					document.getElementById('expiryDate1').style.display = 'none';
	 					document.getElementById('expiryDate2').style.display = '';
	 				}
	 		});
	 	

	 	$(".radioItem2").change( 
			function() { 
				var recType = $("input[name='recType']:checked").val(); 
				if (recType == 2)
				{ 
					document.getElementById('integral').style.display = '';
				} 
				else
				{ 
					document.getElementById('integral').style.display = 'none';
				}
		});
		
	 	$("#recBeginDate").ligerDateEditor( {
			showTime : true,
			width: 158,
			format: "yyyy-MM-dd hh:mm"
		});
		$("#recEndDate").ligerDateEditor( {
			showTime : true,
			width: 158,
			format: "yyyy-MM-dd hh:mm"
		});
	 	if (${couponCustom.status}==0){
			$("#expiryBeginDate").ligerDateEditor( {
				showTime : false,
				width: 158,
				format: "yyyy-MM-dd hh:mm"
			});
			$("#expiryEndDate").ligerDateEditor( {
				showTime : false,
				width: 158,
				format: "yyyy-MM-dd hh:mm"
			});
	 	}
	 	
	 	$("#beginDate").ligerDateEditor( {
			showTime : true,
			width: 158,
			format: "yyyy-MM-dd hh:mm"
		});
		$("#endDate").ligerDateEditor( {
			showTime : true,
			width: 158,
			format: "yyyy-MM-dd hh:mm"
		});
	
	
	$.metadata.setType("attr", "validate");
	
	//驳回原因必填
    /* $.validator.addMethod("grantQuantityRequired", function(value, element) { 
   	if(document.getElementById("grantQuantity").value < 0){
			return false;
		}else{
			return true;
		}
   }, "发行量不能小余零"); */
	
	var v = $("#form1").validate({
		errorPlacement: function (lable, element) {   
        	var elementType = $(element).attr("type");
        	if($(element).hasClass("l-text-field")) {
        		$(element).parent().ligerTip({
					content : lable.html(),width: 100
				});
        	}else if('radio'==elementType) {
        		var radioName=$(element).attr("name");
        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
					content : lable.html(),width: 100
				});
        	}else {
        		$(element).ligerTip({
					content : lable.html(),width: 100
				});
        	}
		},
		success: function (lable,element) {
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler: function (form) {   
			var isValidateOk = true;
			
			//清除提示信息
			$("#money").ligerHideTip();
			$("#recEndDate").ligerHideTip();
			$("#expiryEndDate").ligerHideTip();
			$("#expiryDays").ligerHideTip();
			$("#needIntegral").ligerHideTip();
			$("#recEach").ligerHideTip();
			
			
			//检查面额和使用条件
			var money = document.getElementById("money");
			var minimum = document.getElementById("minimum");
			if (parseFloat(money.value)>=parseFloat(minimum.value)){
    			$("#money").ligerTip({ content: "面额必须小于使用条件。"});
    			money.focus();
    			isValidateOk=false;
			}
			
			//检查领取日期
			var recBeginDate = document.getElementById("recBeginDate");
			var recEndDate = document.getElementById("recEndDate");
			if ($.trim(recBeginDate.value) == "" || $.trim(recEndDate.value) == "") {
    			$("#recEndDate").ligerTip({ content: "领取日期不能为空。"});
    			recEndDate.focus();
    			isValidateOk = false;
			}else {
				var beginDate = new Date(recBeginDate.value);
				var endDate = new Date(recEndDate.value);
				var nowDate = new Date();
	    		//时间差的毫秒数
	    		var dateDiff = endDate.getTime() - beginDate.getTime();
	    		var dateDiff2 = endDate.getTime() - nowDate.getTime();
	    		if (dateDiff < 0) {
	    			$("#recEndDate").ligerTip({ content: "结束日期不能小于开始日期。"});
	    			recEndDate.focus();
	    			isValidateOk = false;
	    		}
	    		if (dateDiff2 < 0) {
	    			$("#recEndDate").ligerTip({ content: "结束日期不能小于当前日期。"});
	    			recEndDate.focus();
	    			isValidateOk = false;
	    		}
			}
			
			//检查有效期
			var expiryBeginDate = document.getElementById("expiryBeginDate");
			var expiryEndDate = document.getElementById("expiryEndDate");
			var expiryDays = document.getElementById("expiryDays");
			var expiryType = $("input[name='expiryType']:checked").val();
			if(expiryType=='1' && ($.trim(expiryBeginDate.value)=="" || $.trim(expiryEndDate.value)=="")){
    			$("#expiryEndDate").ligerTip({ content: "有效期不能为空。"});
    			expiryEndDate.focus();
    			isValidateOk=false;
			}else if (expiryType=='1'){
				var beginDate=new Date(expiryBeginDate.value+" 00:00:00");
				var endDate=new Date(expiryEndDate.value+" 23:59:59");
				var nowDate=new Date();
	    		//时间差的毫秒数
	    		var dateDiff=endDate.getTime()-beginDate.getTime();
	    		if (dateDiff<0){
	    			$("#expiryEndDate").ligerTip({ content: "结束日期不能小于开始日期。"});
	    			expiryEndDate.focus();
	    			isValidateOk=false;
	    		}
	    		var dateDiff2=endDate.getTime()-nowDate.getTime();
	    		if (dateDiff2<0){
	    			$("#expiryEndDate").ligerTip({ content: "结束日期不能小于当前日期。"});
	    			expiryEndDate.focus();
	    			isValidateOk=false;
	    		}
	    		//计算出相差天数
	    		var days=Math.floor(dateDiff/(24*3600*1000));
	    		if (days>=90){
	    			$("#expiryEndDate").ligerTip({ content: "有效期不能超过90天。"});
	    			expiryEndDate.focus();
	    			isValidateOk=false;
	    		}
			}else if (expiryType=='2' && $.trim(expiryDays.value)==""){
    			$("#expiryDays").ligerTip({ content: "有效期不能为空。"});
    			expiryDays.focus();
    			isValidateOk=false;
			}else if (expiryType=='2' && ($.trim(expiryDays.value)>90 || $.trim(expiryDays.value)==0)){
    			$("#expiryDays").ligerTip({ content: "有效期范围1-90天。"});
    			expiryDays.focus();
    			isValidateOk=false;
			}
			
			//检查金币数量
			var needIntegral = document.getElementById("needIntegral");
			var recType = $("input[name='recType']:checked").val();
			if(recType=='2' && $.trim(needIntegral.value)==""){
    			$("#needIntegral").ligerTip({ content: "金币数量不能为空。"});
    			needIntegral.focus();
    			isValidateOk=false;
			}
			
			//检查限领数量
			var recLimitType = $("input[name='recLimitType']:checked").val();
			var recEach = document.getElementById("recEach");
			if(recLimitType=='2' && $.trim(recEach.value)==""){
    			$("#recEach").ligerTip({ content: "限领数量不能为空。"});
    			recEach.focus();
    			isValidateOk=false;
			}
			
			
			var couponType=$("input[name='couponType']:checked").val();
			if (couponType!=null && couponType!='') {
				 var productTypeId=$("input[name='productTypeId']").val();
				if (couponType=='2' && productTypeId=='') {
					commUtil.alertError("请选择品类！");
					isValidateOk=false;
				}
			  }
			
			
			var preferentialType = $("input[name='preferentialType']:checked").val();
			var money = document.getElementById("money");
			var minimum = document.getElementById("minimum");
			var discount= document.getElementById("discount");
			var maxDiscountMoney= document.getElementById("maxDiscountMoney");
			var minicount = $("input[name='minicount']").val();
			var minimum1 = $("input[name='minimum1']").val();
			var conditiontype=$("select[name='conditionType']").val();
			var limitCouponRainScore=document.getElementById("limitCouponRainScore");
			if(preferentialType=='1' && $.trim(money.value)==""){
    			$("#money").ligerTip({ content: "满额不能为空。"});
    			money.focus();
    			isValidateOk=false;
			}
			
			var re= /^[0-9]+$/;
			 if (preferentialType=='2' && minicount!="") {
				 if (!re.test(minicount)) {
					 commUtil.alertError("满件请输入正确数字！");
					 isValidateOk=false;
				}
				 
			  } 
			
			var Vf=/^\d{1,6}(\.\d{1,2})?$/;
			if (preferentialType=='1' && $.trim(money.value)!="" && !Vf.test(money.value)) {
				$("#money").ligerTip({ content: "面额请输正确数字！"});
    			money.focus();
    			isValidateOk=false;
			 }
			
			if (preferentialType=='1' &&  $.trim(minimum.value)=="") {
				$("#minimum").ligerTip({ content: "满元不能为空。"});
				minimum.focus();
				isValidateOk=false;
			}
			
			if (preferentialType=='2' &&  $.trim(discount.value)=="") {
				$("#discount").ligerTip({ content: "折扣不能为空。"});
				discount.focus();
				isValidateOk=false;
			}
			
			/*var str=/^\d{1,6}(\.\d{1,2})?$/;
			if (preferentialType=='2' &&  $.trim(maxDiscountMoney.value)!="") {
				if (!str.test(maxDiscountMoney.value)) {
				$("#maxDiscountMoney").ligerTip({ content: "请重新输入折扣上限。"});
				maxDiscountMoney.focus();
				isValidateOk=false;			
				}
			}*/
            var str=/^\+?[1-9][0-9]*$/;
            if (preferentialType=='2' &&  $.trim(maxDiscountMoney.value)!="") {
                if (!str.test(maxDiscountMoney.value)) {
                    $("#maxDiscountMoney").ligerTip({ content: "折扣最多优惠多少元必须是 整数。"});
                    maxDiscountMoney.focus();
                    isValidateOk=false;
                }
            }
            if (preferentialType=='2' &&  $.trim(maxDiscountMoney.value)=="") {
                $("#maxDiscountMoney").ligerTip({ content: "折扣最多优惠多少元不可为空!"});
                isValidateOk=false;
            }
			
		    var reg = /^[1-9](\.\d{1,1})?$/; 
			if (preferentialType=='2' && !reg.test(discount.value)) {
				$("#discount").ligerTip({ content: "折扣必须1-9.9之间包含1,9.9"});
				discount.focus();
				isValidateOk=false;
			}
			
			if(preferentialType=='2' && conditiontype!='1' && $.trim(minicount)=="" && $.trim(minimum1)==""){
				  commUtil.alertError("满多少件或者满多少元不能为空！");
				  isValidateOk=false;
			}
			
			    var days=$("input[name='days']:checked").val();
				var beginDate = document.getElementById("beginDate");
				var endDate = document.getElementById("endDate");
				if (days=='1' && ($.trim(beginDate.value)=="" || $.trim(endDate.value)=="")){	
	    			commUtil.alertError("追加日期不能为空");
	    			isValidateOk=false;
				}
				
				var addCount=$("input[name='addCount']").val();
	            if (days=='1' && $.trim(addCount)=="") {
	            	commUtil.alertError("追加张数不能为空！");
	            	isValidateOk=false;
				 }	
	            
	            if (days=='1' && $.trim(addCount)!="" && !re.test(addCount)) {
	            	commUtil.alertError("追加张数请输入正确数字！");
	            	isValidateOk=false;
			
	           }
			
	         var ver = /^\d{1,6}(\.\d{1,2})?$/;
	   		 if (preferentialType=='2' && minimum1!="") {
	   		 if (!ver.test(minimum1)) {
	   			 commUtil.alertError("满元请输入正确数字！");
	   			 isValidateOk=false;
	   		   }
	   		 }
	            
	   		 var linkType=$("select[name='linkType']").val();
	   		 var linkValue=$("input[name='linkValue']").val();
			 var linkValue0=$("select[name='linkValue0']").val();
			 var linkValue1=$("select[name='linkValue1']").val();
			 var linkValue2=$("select[name='linkValue2']").val();
			 var linkValue3=$("select[name='linkValue3']").val();
			 
				if ((linkType=='1' || linkType=='2' || linkType=='3' || linkType=='4' || linkType=='7') && linkValue=='') {
					commUtil.alertError("ID或者下拉框选择不能为空！");
					isValidateOk=false;
				}else if ((linkType=='6' || linkType=='11' || linkType=='9' || linkType=='28') && (linkValue0=='' && linkValue1=='' && linkValue2=='' && linkValue3=='')) {
					commUtil.alertError("ID或者下拉框选择不能为空！");
					isValidateOk=false;
				}
				
				
				if (days=='1') {
		             var recEndDate1=new Date(recEndDate.value);
		             var beginDate1=new Date(beginDate.value);
		             var recBeginDate1=new Date(recBeginDate.value);
		             var endDate1=new Date(endDate.value); 
		             if (beginDate1.getTime()<=recBeginDate1.getTime() || endDate1.getTime()>=recEndDate1.getTime()) {
		            	 commUtil.alertError("追加日期范围在领取开始日期+1，领取结束日期-1之间，不可超出此日期范围！");
		             	 isValidateOk=false;
					  }
						
				}
			
			
			//发行量
			var grantQuantity = $("#grantQuantity").val();
			if(grantQuantity != '' && grantQuantity < ${couponCustom.grantQuantity } ) {
				$("#grantQuantity").ligerTip({ content: "发行量不能小于${couponCustom.grantQuantity }"});
				$("#grantQuantity").focus();
    			isValidateOk = false;
			}

			if ($.trim(limitCouponRainScore.value)!="" && !re.test(limitCouponRainScore.value)) {
				commUtil.alertError("红包雨张数请输正整数！");
				isValidateOk=false;
			}
			
	    	if(isValidateOk){
	    		form.submit();
	    	}else{
				$("html,body").animate({scrollTop:$("body").offset().top},0);
	    	}
		}
	});

    $("#maxDiscountMoney").val(Math.floor(${couponCustom.maxDiscountMoney}));


	//加载时判断是否是红包雨
	var activityType = $("#activityType").val()
	if("1"==activityType){
		$("#redEnveloped").show();
	}else{
		$("#redEnveloped").hide();
	}
});

function types(value){
	if (value=='1') {
		document.getElementById("dcm").style.display = 'none';
		document.getElementById("mmu").style.display = '';
		$("#discount").val("");
		$("#conditionType").val("");
		$("#minimum1").val("");
		$("#minicount").val("");
	}else if (value=='2') {
		document.getElementById("dcm").style.display = '';
		document.getElementById("mmu").style.display = 'none';
		$("#money").val("");
		$("#minimum").val("");
		
	}
}


function couponTypeS(value){
	if (value=='1') {
		document.getElementById("productType1").style.display = 'none';
	}else if (value=='2') {
		document.getElementById("productType1").style.display = '';
	}
}


function LinkType(){
	var linkTypes= $("select[name='linkType']").val();
	if (linkTypes==null || linkTypes=='') {
		document.getElementById("linkValue").style.display = '';
		document.getElementById("linkValue0").style.display = 'none';
		document.getElementById("linkValue1").style.display = 'none';
		document.getElementById("linkValue2").style.display = 'none';
		document.getElementById("linkValue3").style.display = 'none';
		$("#typeName").html("链接");
	 }
	if (linkTypes!=null && linkTypes!='') {
		if (linkTypes=='11') {
			document.getElementById("linkValue").style.display = 'none';
			document.getElementById("linkValue0").style.display = 'none';
			document.getElementById("linkValue1").style.display = '';
			document.getElementById("linkValue2").style.display = 'none';
			document.getElementById("linkValue3").style.display = 'none';
			$("#linkValue").val("");
			$("#linkValue0").val("");
			$("#linkValue2").val("");
			$("#linkValue3").val("");
			$("#typeName").html("品牌特卖一级分类");
		}else if (linkTypes=='28') {
			document.getElementById("linkValue").style.display = 'none';
			document.getElementById("linkValue0").style.display = 'none';
			document.getElementById("linkValue1").style.display = 'none';
			document.getElementById("linkValue2").style.display = 'none';
			document.getElementById("linkValue3").style.display = '';
			$("#linkValue").val("");
			$("#linkValue0").val("");
			$("#linkValue2").val("");
			$("#linkValue1").val("");
			$("#typeName").html("商城一级分类");
		}else if (linkTypes=='9') {
			document.getElementById("linkValue").style.display = 'none';
			document.getElementById("linkValue1").style.display = 'none';
			document.getElementById("linkValue2").style.display = '';
			document.getElementById("linkValue3").style.display = 'none';
			document.getElementById("linkValue0").style.display = 'none';
			$("#linkValue").val("");
			$("#linkValue0").val("");
			$("#linkValue3").val("");
			$("#linkValue1").val("");
			$("#typeName").html("品牌团一级类目");
		}else if (linkTypes=='6') {
			document.getElementById("linkValue").style.display = 'none';
			document.getElementById("linkValue0").style.display = '';
			document.getElementById("linkValue1").style.display = 'none';
			document.getElementById("linkValue2").style.display = 'none';
			document.getElementById("linkValue3").style.display = 'none';
			$("#linkValue").val("");
			$("#linkValue2").val("");
			$("#linkValue3").val("");
			$("#linkValue1").val("");
			$("#typeName").html("栏目");
		}else if (linkTypes=='1' || linkTypes=='2' || linkTypes=='3' || linkTypes=='4' || linkTypes=='7') {
			if (linkTypes=='1') {
	        	 $("#typeName").html("会场ID");
			 }else if (linkTypes=='2') {
				 $("#typeName").html("活动ID");
			 }else if (linkTypes=='3') {
				 $("#typeName").html("商品ID");
			 }else if (linkTypes=='4') {
				 $("#typeName").html("外面链接");
			 }else if (linkTypes=='7') {
				 $("#typeName").html("自建页面ID");
			 }
			document.getElementById("linkValue").style.display = '';
			document.getElementById("linkValue0").style.display = 'none';
			document.getElementById("linkValue1").style.display = 'none';
			document.getElementById("linkValue2").style.display = 'none';
			document.getElementById("linkValue3").style.display = 'none';
			$("#linkValue0").val("");
			$("#linkValue2").val("");
			$("#linkValue3").val("");
			$("#linkValue1").val("");
	
		}
	
	} 
}

/*  function sedays(value){
	  if (value=='1') {
		var recBeginDate=document.getElementById("recBeginDate");
		var recEndDate=document.getElementById("recEndDate");
	  if (recBeginDate.value !='' && recEndDate.value!='') {
		var recBeginDate1=new Date(recBeginDate.value);
		var recEndDate1=new Date(recEndDate.value);
		
		if (recBeginDate1!='' && recEndDate1 !='') {
			var day = 60 * 60 * 24 * 1000;
			var beginDates = new Date(recBeginDate1.getTime() + day);
			var endDates= new Date(recEndDate1.getTime()-day);
			document.getElementById('beginDate').value=beginDates.format("yyyy-MM-dd hh:mm");
			document.getElementById('endDate').value=endDates.format("yyyy-MM-dd hh:mm");
		}
	 }
			
  }		
} */
 function sedays(value){
	  if (value=='1') {
		var recBeginDate=document.getElementById("recBeginDate");
		var recEndDate=document.getElementById("recEndDate");
	  if (recBeginDate.value !='' && recEndDate.value!='') {
		var recBeginDate1=new Date(recBeginDate.value);
		var recEndDate1=new Date(recEndDate.value);
		
		if (recBeginDate1!='' && recEndDate1 !='') {
			var day = 60 * 60 * 24 * 1000;
			var beginDates = new Date(recBeginDate1);
			var year=beginDates.getFullYear(); 
			var month=beginDates.getMonth()+1;
			var strDate = beginDates.getDate();
			var endDates= new Date(recEndDate1.getTime()-day);
			/* document.getElementById('beginDate').value=beginDates.format("yyyy-MM-dd hh:mm"); */
			document.getElementById('endDate').value=endDates.format("yyyy-MM-dd hh:mm");
			$("[name='beginDate']").ligerDateEditor().setValue(year+"-"+month+"-"+strDate+" 23:00");
		}
	 }
			
  }
		
}

//当选择红包雨的时候,展示红包雨的条件
function showRedEnveloped(){
	var activityType = $("#activityType").val();
	if("1" == activityType){
		$("#redEnveloped").show();
	}else {
		$("#redEnveloped").hide();
	}

}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/coupon/svipCouponEdit.shtml">
		<input id="id" name="id" type="hidden" value="${couponCustom.id }"/>
		<input id="rang" name="rang" type="hidden" value="${couponCustom.rang }"/>
		<input id="belong" name="belong" type="hidden" value="${couponCustom.belong }"/>
		<input type="hidden" id="productTypeIds" name="productTypeIds" value="${couponCustom.typeIds}"/>
		<input type="hidden" id="linkTypeS" name="linkTypeS" value="${couponCustom.linkType}"/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">优惠券名称 <span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="name" validate="{required:true}" name="name" type="text" style="width:200px;" value="${couponCustom.name }" disabled="disabled" />
					定义前缀<input id="definitionPrefix" name="definitionPrefix" type="text" style="width:100px;" value="${couponCustom.definitionPrefix}" maxlength="12"/>
				</td>
			</tr>
			<%-- <tr>
				<td colspan="1" class="title">面额<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="money" validate="{required:true,number:true,maxlength:8}" name="money" type="text" style="width:60px;" maxlength="6" value="${couponCustom.money }" disabled="disabled" />元
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">使用条件<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					满<input id="minimum" validate="{required:true,number:true,maxlength:8}" name="minimum" type="text" style="width:60px;" value="${couponCustom.minimum }" disabled="disabled" />元使用
				</td>
			</tr> --%>
			<tr>
				<td colspan="1" class="title">优惠券类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="radio"  name="preferentialType" value="1" onchange="types(1);" <c:if test="${couponCustom.preferentialType eq '1'}">checked="checked"</c:if>/>满减&nbsp;
					<input type="radio"  name="preferentialType" value="2" onchange="types(2);" <c:if test="${couponCustom.preferentialType eq '2'}">checked="checked"</c:if>/>折扣&nbsp;
				</td>
			</tr>
			
			<tr>
			 <td colspan="1" class="title">折扣<span class="required">*</span></td>
				<td id="mmu" colspan="5" align="left" class="l-table-edit-td">
					面额&nbsp;<input id="money"  name="money" type="text" style="width:60px;" maxlength="6" value="${couponCustom.money}"/>元
					&nbsp;&nbsp;
					满&nbsp;<input id="minimum" validate="{maxlength:8}" name="minimum" type="text" style="width:60px;"  value="${couponCustom.minimum}"/>元
				</td>
				<td id="dcm" colspan="5" align="left" class="l-table-edit-td">
					<input id="discount" validate="{maxlength:4}" name="discount" type="text" style="width:60px;"value="${Discount}"/>折
					&nbsp;&nbsp;
					<select style="width: 120px;" id="conditionType" name="conditionType">	
						<c:forEach var="conditionTypeS" items="${conditionTypeS}">
							<option value="${conditionTypeS.statusValue}" <c:if test="${conditionTypeS.statusValue==couponCustom.conditionType}">selected="selected"</c:if>>${conditionTypeS.statusDesc}</option>
						</c:forEach>
					 </select>	
					&nbsp;&nbsp;
					 <label id="minimum1">满<input id="" name="minimum1" type="text" style="width:60px;" value="${couponCustom.minimum}"/>元</label>
					&nbsp;&nbsp;
					 <label id="minicount1">满<input id="minicount" name="minicount" type="text" style="width:60px;" value="${couponCustom.minicount}" />件</label>
					&nbsp;&nbsp;
					最多优惠<input id="maxDiscountMoney" validate="{maxlength:8}" name="maxDiscountMoney" type="text" style="width:60px;" value="${couponCustom.maxDiscountMoney}"/>元
				</td>
			</tr>
			
			<%-- <tr>
				<td colspan="1" class="title">品类范围<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<select style="width: 120px;" id="typeIds" name="typeIds" disabled="disabled" >
						<option value="">全部品类</option>
						<c:forEach var="productType" items="${productTypes}">
							<option value="${productType.id}" <c:if test="${couponCustom.typeIds eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
						</c:forEach>
					</select>
					<c:if test="${empty couponCustom.typeIds}">
						是否叠加使用品类券
						<input type="radio" name="canSuperpose" value="1" disabled="disabled" <c:if test="${couponCustom.canSuperpose == 1}">checked="checked"</c:if>>是
						<input type="radio" name="canSuperpose" value="0" disabled="disabled" <c:if test="${couponCustom.canSuperpose == 0}">checked="checked"</c:if>>否
					</c:if>
				</td>
			</tr> --%>
			<tr>
				<td colspan="1" class="title">品类范围<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="radio"  name="couponType" value="1" onchange="couponTypeS(1);" <c:if test="${couponCustom.couponType=='1'}">checked="checked"</c:if>/>平台劵&nbsp;
					<input type="radio"  name="couponType" value="2" onchange="couponTypeS(2);" <c:if test="${couponCustom.couponType=='2'}">checked="checked"</c:if>/>品类劵&nbsp;
				</td>
			</tr>
			<tr id="productType1">
				<td colspan="1" class="title">指定品类<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
				   <c:forEach var="productTypes" items="${productTypes}">
					  <input name="productTypeId" type="checkbox" value="${productTypes.id}" style="width: 20px;"/>${productTypes.name}
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">领取时间<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="recBeginDate" name="recBeginDate" value="<fmt:formatDate value='${couponCustom.recBeginDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
					&nbsp;&nbsp;至&nbsp;&nbsp;
					<input type="text" id="recEndDate" name="recEndDate" value="<fmt:formatDate value='${couponCustom.recEndDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
				</td>
			</tr>
			<%-- <tr>
				<td colspan="1" class="title">有效期<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td" id="expiryDate2">
					<input type="text" id="expiryDays" name="expiryDays" style="width:100px;" value="${couponCustom.expiryDays}" validate="{digits:true}" disabled="disabled" />
					<font color="#CC0000">&nbsp;&nbsp;（相对时间）</font>
				</td>
			</tr> --%>
			<tr>
				<td colspan="1" class="title">有效期类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<c:forEach var="typeItem" items="${expiryTypes }">
						<span class="radioClass"><input class="radioItem1" type="radio" value="${typeItem.statusValue }" name="expiryType" <c:if test="${typeItem.statusValue==couponCustom.expiryType}">checked="checked"</c:if>validate="{digits:true}">${typeItem.statusDesc}</span>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">有效期<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td" id="expiryDate1">
					<input type="date" id="expiryBeginDate" name="expiryBeginDate" value="<fmt:formatDate value='${couponCustom.expiryBeginDate}' pattern='yyyy-MM-dd'/>"/>
					&nbsp;&nbsp;至&nbsp;&nbsp;
					<input type="date" id="expiryEndDate" name="expiryEndDate" value="<fmt:formatDate value='${couponCustom.expiryEndDate}' pattern='yyyy-MM-dd'/>"/>
					<font color="#CC0000">&nbsp;&nbsp;（不超过90天。）</font>
				</td>
				<td colspan="5" align="left" class="l-table-edit-td" id="expiryDate2">
					<input type="text" id="expiryDays" name="expiryDays" value="${couponCustom.expiryDays}" validate="{digits:true}"/>
					<font color="#CC0000">&nbsp;&nbsp;（不超过90天。）</font>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">发行量<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="grantQuantity" validate="{required:true,digits:true}" name="grantQuantity" type="text" style="width:100px;" maxlength="6" value="${couponCustom.grantQuantity}"/>张
					<font color="#CC0000">&nbsp;&nbsp;（发行量不能超过100万张。）</font>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">追加日期</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input name="days" type="checkbox" value="1" style="width: 20px;" onchange="sedays(1)" <c:if test="${couponAddtaskConfigs.couponId==couponCustom.id}">checked="checked"</c:if>/>是否每日23:45追加&nbsp;&nbsp;<input id="addCount" name="addCount" type="text" style="width:30px;" maxlength="6" value="${couponAddtaskConfigs.addCount}"/>张
					&nbsp;&nbsp;
					<input type="text" id="beginDate" name="beginDate" value="<fmt:formatDate value='${couponAddtaskConfigs.beginDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
					&nbsp;至&nbsp;
					<input type="text" id="endDate" name="endDate" value="<fmt:formatDate value='${couponAddtaskConfigs.endDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
				</td>
	        </tr>
			<tr>
				<td colspan="1" class="title">使用/领取</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					${couponCustom.useQuantity } / ${couponCustom.recQuantity }
				</td>
			</tr>
			<%-- <tr>
				<td colspan="1" class="title">限领<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<span class="radioClass"><input type="radio" value="4" name="recLimitType" checked="checked" disabled="disabled" >每人每月限领1张</span>
				</td>
			</tr> --%>
			<tr>
				<td colspan="1" class="title">领取对象<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<select style="width: 160px;" id="minMemberGroup" name="minMemberGroup">
					<c:forEach var="groupItem" items="${memberGroups}">
						<option value="${groupItem.id}" <c:if test="${groupItem.id==couponCustom.minMemberGroup}">selected="selected"</c:if>>${groupItem.name}及以上
						</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">领取方式<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					限SVIP用户领取
				</td>
			</tr>
			<%-- <tr>
				<td colspan="1" class="title">领取方式<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<c:forEach var="typeItem" items="${recTypes }">
						<span class="radioClass"><input class="radioItem2" type="radio" value="${typeItem.statusValue }" name="recType" validate="{radioRequired:true}" <c:if test="${typeItem.statusValue==couponCustom.recType}">checked="checked"</c:if> >${typeItem.statusDesc}</span>
					</c:forEach>
					<span id="integral"><input id="needIntegral" name="needIntegral" type="text" style="width:60px;" value="${couponCustom.needIntegral }" validate="{digits:true}"/>金币换1张优惠券</span>
				</td>
			</tr> --%>
		<tr>
			<td colspan="1" class="title">限领</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<span class="radioClass"><input class="radioItem" type="radio" value="1" name="recLimitType" <c:if test="${couponCustom.recLimitType==1}">checked="checked"</c:if>/>每人每天限领1张</span>
				<span class="radioClass"><input class="radioItem" type="radio" value="2" name="recLimitType" <c:if test="${couponCustom.recLimitType==2}">checked="checked"</c:if> />
					每人限领 <input type="text" id="recEach" name="recEach" maxlength="3" validate="{digits:true}" style="width:60px;" value="${couponCustom.recEach}"/>张
				</span>
				<span class="radioClass"><input class="radioItem" type="radio" value="3" name="recLimitType" <c:if test="${couponCustom.recLimitType==3}">checked="checked"</c:if> />不限</span>
			</td>
		</tr>
		  <tr>
				<td colspan="1" class="title">跳转页</td>
				<td colspan="5" align="left" class="l-table-edit-td">
				类型&nbsp;&nbsp;
				<select id="linkType" name="linkType" style="width: 150px;" onchange="LinkType()">
               		<option value="">请选择</option> 
               		 <c:forEach var="linkTypeList" items="${linkTypeList}">
               		       <option value="${linkTypeList.linkType}" <c:if test="${linkTypeList.linkType==couponCustom.linkType}">selected="selected"</c:if>>${linkTypeList.linkTypeName}</option>
               		 </c:forEach>    			     
               	</select>
               	&nbsp;&nbsp;
               	<label id="typeName">链接&nbsp;&nbsp;</label><input type="text" id="linkValue" name="linkValue" value="${couponCustom.linkValue}"/>
               	               	         	
               	<select id="linkValue0" name="linkValue0" style="width: 150px;" >
               	     <option value="">请选择</option>
               		 <c:forEach var="linkValueList" items="${linkValueList}">          
					  <option value="${linkValueList.linkValue}" <c:if test="${linkValueList.linkValue==couponCustom.linkValue}">selected="selected"</c:if>>${linkValueList.linkValueName}</option>
					</c:forEach>			     
               	</select> 
                   	      
               	<select id="linkValue1" name="linkValue1" style="width: 150px;">
               	       <option value="">请选择</option>
               		 <c:forEach var="productTypes" items="${productTypes}">              		
					  <option value="${productTypes.id}"  <c:if test="${productTypes.id==couponCustom.linkValue}">selected="selected"</c:if>>${productTypes.name}</option>
					</c:forEach>			     
               	</select> 
               	
               	<select id="linkValue2" name="linkValue2" style="width: 150px;">
               	    <option value="">请选择</option>
               		 <c:forEach var="brandteamTypes" items="${brandteamTypes}">         
					  <option value="${brandteamTypes.id}" <c:if test="${brandteamTypes.id==couponCustom.linkValue}">selected="selected"</c:if>>${brandteamTypes.name}</option>
					</c:forEach>			     
               	</select> 
               	  
               	 <select id="linkValue3" name="linkValue3" style="width: 150px;">
               	     <option value="">请选择</option>
               		<c:forEach var="mallCategories" items="${mallCategories}">            		
					  <option value="${mallCategories.id}" <c:if test="${mallCategories.id==couponCustom.linkValue}">selected="selected"</c:if>>${mallCategories.categoryName}</option>
					</c:forEach>			     
               	</select>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">创建人</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" style="width:60px;" value="${couponCustom.staffName }" disabled="true"/>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">活动类型</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<%--	<span class="radioClass"><input class="radioItem" type="radio" value="1" name="isSupportCouponRain" <c:if test="${couponCustom.isSupportCouponRain eq 1}">checked="checked"</c:if>>是否参与红包雨</span>
                        获取条件 <input id="limitCouponRainScore" name="limitCouponRainScore" type="text" style="width:60px;" maxlength="3" value="${couponCustom.limitCouponRainScore}" >张 &nbsp;（用户参与红包雨游戏领取数量达到多少及以上才可获得）
                        --%>
					<select  id="activityType" name="activityType" onchange="showRedEnveloped()">
						<option value="0">请选择</option>
						<option value="1" <c:if test="${couponCustom.activityType==1}">selected</c:if>>红包雨</option>
						<option value="2" <c:if test="${couponCustom.activityType==2}">selected</c:if>>领券秒杀</option>
					</select>
					<span id="redEnveloped" style="display: none;">获取条件 <input id="limitCouponRainScore" name="limitCouponRainScore" type="text" style="width:60px;" maxlength="3" value="${couponCustom.limitCouponRainScore}" />张 &nbsp;（用户参与红包雨游戏领取数量达到多少及以上才可获得）</span>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">状态<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<label>
						<span class="radioClass"><input class="radioItem" type="radio" value="1" name="status" validate="{radioRequired:true}" <c:if test="${couponCustom.status==1}">checked="checked"</c:if> >启用</span>
					</label>
					<c:if test="${couponCustom.status == 0 }">
						<label>
							<span class="radioClass status_span"><input class="radioItem" type="radio" value="0" name="status" validate="{radioRequired:true}" checked="checked" >未启用</span>
						</label>
					</c:if>
					<c:if test="${couponCustom.status != 0 }">
						<label>
							<span class="radioClass status_span"><input class="radioItem" type="radio" value="2" name="status" validate="{radioRequired:true}" <c:if test="${couponCustom.status==2}">checked="checked"</c:if> >停用</span>
						</label>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
