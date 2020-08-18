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
 	.radioClass {
		margin-right: 20px;
	}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
    .activity-time {
    	margin-left: 50px;
    }
    .activity-hint {
    	margin-left: 20px;
    	color: #6B6B6B;
    }
</style>
<script type="text/javascript">
	$(function(){
		$("input[name='purchaseLimits']").bind('click',function(){
			var purchaseLimits = $(this).val();
			if(purchaseLimits == 0){
				$("#purchaseLimitsQuantity").val("");
			}
		});
		
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 160
		});
		
		if('${activityArea}' != '' && '${activityArea.productTypeGroup}' != '') {
			var productTypeGroups = '${activityArea.productTypeGroup}'.split(",");
			for(var i=0;i<productTypeGroups.length;i++){    
				$("#productTypeGroup"+productTypeGroups[i]).attr("checked", "checked");       
			}
		}
		
	
		$.metadata.setType("attr", "validate");
		$("#form1").validate({
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
	        submitHandler: function(form) {
                var isPreSell = $("input[name='isPreSell']:checked").val();
                var activityPriceLimit=$("#activityPriceLimit").val();
                var depositLimit=$("#depositLimit").val();
                var minRate=$("#minRate").val();
                var maxRate=$("#maxRate").val();
                var offsetMultiple=$("#offsetMultiple").val();
                if(isPreSell == 1){
                    if( isEmpty(activityPriceLimit) ||  isEmpty(depositLimit) || isEmpty(minRate) || isEmpty(maxRate) || isEmpty(offsetMultiple)){
                        commUtil.alertError("请设置预售规则！");
                        return;
                    }
				}

                var shareIntegralType=$("input[name='shareIntegralType']:checked").val();
                var minShareIntegral=$("#minShareIntegral").val();
                var maxShareIntegral=$("#maxShareIntegral").val();
                if(shareIntegralType == 1 || shareIntegralType == 2){
                    if( isEmpty(minShareIntegral) ||  isEmpty(maxShareIntegral)){
                        commUtil.alertError("选中首次得积分和首次每日分享得积分，积分范围必填！");
                        return;
                    }
                    if (!(/(^[1-9]\d*$)/.test(minShareIntegral)) || !(/(^[1-9]\d*$)/.test(maxShareIntegral))) {
                        commUtil.alertError("积分只能为正整数！");
                        return;
                    }
                    if(Number(minShareIntegral) >  Number(maxShareIntegral)){
                        commUtil.alertError("最大积分需大于等于最小积分! ");
                        return;
                    }
                }

	        	var mchtIdGroupCode = $("#mchtIdGroupCode").val();
	   		    var mchtIdGroupCodeStr = $("#mchtIdGroupCodeStr").val();
	   		    if(mchtIdGroupCode == '') {
	   		    	$("#mchtIdGroup").val("");
	   		    }else if(mchtIdGroupCode != mchtIdGroupCodeStr){
	   		    	commUtil.alertError("追加指定商家序号，必须检测！");
	   		    	return;
	   		    }
        		var entryPic = $("#entryPic").val();
        		var pic = $("#pic").val();
        		var activitySharePic = $("#activitySharePic").val();
        		var pushMchtType = $("input:radio[name='pushMchtType']:checked").val();
        		var mchtIdGroup = $("#mchtIdGroup").val();
        		var limitMchtNum = $("#limitMchtNum").val();
        		var limitMchtNum = $("#limitMchtNum").val();
        		var limitMchtNum = $("#limitMchtNum").val();
        		if(!enrollTime()) 
	        		return;
        		if(entryPic == '') {
	        		commUtil.alertError("APP入口图不能为空！");
	        		return;
	        	}
	        	if(pic == '') {
	        		commUtil.alertError("报名图不能为空！");
	        		return;
	        	}
	        	if(activitySharePic == '') {
	        		commUtil.alertError("分享展示图不能为空！");
	        		return;
	        	}
	        	if(!/(^\d$)|(^[1-9]\d*$)/.test(limitMchtNum)) {
	        		commUtil.alertError("请输入正确的整数格式！");
        			return;
	        	}
	        	if(pushMchtType == undefined && mchtIdGroup == '') {
        			commUtil.alertError("推送商家类型、追加指定商家序号，不能同时为空！");
        			$(".l-dialog-content").css("margin-right", "10px");
	        		return;
        		}
	        	if('${activityAreaType}' == '3') {
	        		var productTypeGroup = $(".productTypeGroup:checked").val();
	        		if(productTypeGroup == undefined) {
	        			commUtil.alertError("类目限制，不能为空！");
	        			return;
	        		}else{
	        			var productTypeGroup = '';
	        			$(".productTypeGroup:checked").each(function(){
	        				if(productTypeGroup == '') {
	        					productTypeGroup = $(this).val();
	        				}else {
	        					productTypeGroup += ',' + $(this).val();
	        				}
						});
	        			$("#productTypeGroup").val(productTypeGroup);
	        		}
	        	}
	        	var purchaseLimits = $("input[name='purchaseLimits']:checked").val();
	        	if(purchaseLimits == 1){
	        		var purchaseLimitsQuantity = $("#purchaseLimitsQuantity").val();
	        		if(!/^[1-9]\d*$/.test(purchaseLimitsQuantity)){
	        			commUtil.alertError("选择限购必填限购件数，必须大于0的正整数");
	        			return;
	        		}
	        	}
	        	form.submit();
	        }
	    }); 
		
	});

	//图片格式验证
	function ajaxFileUploadImg(statusImg) {
		var file = document.getElementById(statusImg).files[0];
		var reg = /image\/(JPG|jpg|JPEG|jpeg)$/;
		if(statusImg == 'areaLabelPicFile'){
			reg = /image\/(JPG|jpg|JPEG|jpeg|PNG|png)$/;
		}
        if(!reg.test(file.type)){
        	commUtil.alertError("图片格式不正确！");
            return;
        }
        if(statusImg == 'entryPicFile') {
	        var size = file.size;
	        if(size > 100*1024 ) {
	        	commUtil.alertError("图片过大，请重新上传！");
	            return;
	        }
        }
        if(statusImg == 'activitySharePicFile' || statusImg == 'areaLabelPicFile') {
	        var size = file.size;
	        if(size > 128*1024 ) {
	        	commUtil.alertError("图片过大，请重新上传！");
	            return;
	        }
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() {
        		var widthStr = '200'; 
        		var heightStr = '200'; 
        		if(statusImg == 'entryPicFile') {
        			widthStr = '800';
        			heightStr = '400';
        		}
        		if(statusImg == 'areaLabelPicFile'){
					ajaxFileUploadPicFile(statusImg);
				}else if(this.width != widthStr || this.height != heightStr) {
        			commUtil.alertError("图片像素不是"+widthStr+"x"+heightStr+"像素！");
            	}else{
            		ajaxFileUploadPicFile(statusImg);
            	}
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);  
	}
	
	//图片上传
	function ajaxFileUploadPicFile(statusImg) {
        $.ajaxFileUpload({
        	url: '${pageContext.request.contextPath}/service/common/ajax_upload.shtml?fileType=3',
			secureuri: false,
			fileElementId: statusImg,
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					if(statusImg == 'entryPicFile') {
						$("#entryPic").val(result.FILE_PATH);
						$("#entryPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
						$("#entryPicA").attr("href",'${contextPathStr }'+result.FILE_PATH);
            		}
            		if(statusImg == 'picFile') {
            			$("#pic").val(result.FILE_PATH);
    					$("#picImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
    					$("#picA").attr("href",'${contextPathStr }'+result.FILE_PATH);
            		}
            		if(statusImg == 'activitySharePicFile') {
            			$("#activitySharePic").val(result.FILE_PATH);
    					$("#activitySharePicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
    					$("#activitySharePicA").attr("href",'${contextPathStr }'+result.FILE_PATH);
            		}
            		if(statusImg == 'areaLabelPicFile') {
            			$("#areaLabelPic").val(result.FILE_PATH);
    					$("#areaLabelPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
    					$("#areaLabelPicA").attr("href",'${contextPathStr }'+result.FILE_PATH);
            		}
				} else {
					commUtil.alertError(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				commUtil.alertError("服务异常！");
			}
		});
	}
	
	//报名时间验证
	function enrollTime() {
		var flag = true;
		var id = $("#id").val();
		var enrollBeginTimeStr = $("#enrollBeginTimeStr").val();
		var enrollEndTimeStr = $("#enrollEndTimeStr").val();
		if(enrollBeginTimeStr == '') {
			commUtil.alertError("报名开始时间不能为空！");
			flag = false;
		}else if(enrollEndTimeStr == '') {
			commUtil.alertError("报名结束时间不能为空！");
			flag = false;
		}else if(enrollBeginTimeStr >= enrollEndTimeStr) {
			commUtil.alertError("报名开始时间必须小于报名结束时间！");
			flag = false;
		}else if(id != '') {
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath }/activityAreaNew/validateEnrollEndTime.shtml',
				dataType: 'json',
				data: {activityAreaId : id, enrollEndTime : enrollEndTimeStr},
				async: false,
				success: function(data) {
					if(data != null && data.code != '200') {
						flag = false;
						commUtil.alertError(data.msg);
					}
				},
				error: function(e) {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
		return flag;
	}
	
	//选择商家
	function choiceMemberList(){
		var mchtIdGroupCode = $.trim($("#mchtIdGroupCode").val());
		if(mchtIdGroupCode == '') {
			commUtil.alertError("追加指定商家序号不能为空！");
			return;
		}
		if(!/^[a-zA-Z0-9]+(,[a-zA-Z_0-9]+)*$/.test(mchtIdGroupCode)) {
			commUtil.alertError("请输入正确格式的追加指定商家序号。例：P1,P2,P3");
			return;
		}
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 200,
			title: "商家列表",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/activityMemberInfoList.shtml?mchtIdGroupCode="+mchtIdGroupCode,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
		$(".l-dialog-win").css("z-index", "90000");
	}
	function mchtInfoListId(str,strCode){
		 $("#mchtIdGroup").val(str);
		 $("#mchtIdGroupCode").val(strCode);
		 $("#mchtIdGroupCodeStr").val(strCode);
	}

    //判断字符是否为空
    function isEmpty(obj){
        return (typeof obj === 'undefined' || obj === null || obj === "");
    }

	//设置预售规则
	function setAreaActivityPreSellRule(type){
        var activityPriceLimit=$("#activityPriceLimit").val();
        var depositLimit=$("#depositLimit").val();
        var minRate=$("#minRate").val();
        var maxRate=$("#maxRate").val();
        var offsetMultiple=$("#offsetMultiple").val();
        var html = [];
        //type为1时可修改,0只读
        if(!isEmpty(type)){
            if(html.length==0){
                html.push('?type='+type);
			}else{
                html.push('&type='+type);
			}
        }if(!isEmpty(activityPriceLimit)){
            if(html.length==0){
                html.push('?activityPriceLimit='+activityPriceLimit);
			}else{
                html.push('&activityPriceLimit='+activityPriceLimit);
			}
        }
        if(!isEmpty(depositLimit)){
            if(html.length==0){
                html.push('?depositLimit='+depositLimit);
			}else{
                html.push('&depositLimit='+depositLimit);
			}
        }
        if(!isEmpty(minRate)){
            if(html.length==0){
                html.push('?minRate='+minRate);
			}else{
                html.push('&minRate='+minRate);
			}
        }
        if(!isEmpty(maxRate)){
            if(html.length==0){
                html.push('?maxRate='+maxRate);
			}else{
                html.push('&maxRate='+maxRate);
			}
        }
        if(!isEmpty(offsetMultiple)){
            if(html.length==0){
                html.push('?offsetMultiple='+offsetMultiple);
			}else{
                html.push('&offsetMultiple='+offsetMultiple);
			}
        }
		$.ligerDialog.open({
			height: $(window).height()*0.4,
			width: $(window).width()*0.5,
			title: "预售规则",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/setAreaActivityPreSellRule.shtml"+html.join(""),
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

</script>

</head>
	<body style="margin: 10px; margin-bottom: 200px;">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/activityAreaNew/addOrUpdateActivityArea.shtml" >
		<input type="hidden" id="id" name="id" value="${activityArea.id }" />
		<input type="hidden" id="entryPic" name="entryPic" value="${activityArea.entryPic }" />
		<input type="hidden" id="pic" name="pic" value="${activityArea.pic }" />
		<input type="hidden" id="areaLabelPic" name="areaLabelPic" value="${activityArea.areaLabelPic }" />
		<input type="hidden" id="activitySharePic" name="activitySharePic" value="${activityArea.activitySharePic }" />
		<input type="hidden" id="mchtIdGroup" name="mchtIdGroup" value="${activityArea.mchtIdGroup }"/>
		<input type="hidden" id="preferentialType" name="preferentialType" value="${activityArea.preferentialType }"/>
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">会场名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:260px;" id="name" name="name" type="text" value="${activityArea.name }" validate="{required:true,maxlength:20}" />
					<span class="activity-hint" >注意：会场名称不能超过20个字符</span>
				</td>
           	</tr>
           	<c:if test="${activityAreaType == '3' }">
           		<tr>
	            	<td class="title" width="20%">类型<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td">
						<span class="radioClass"><input type="radio" name="type" value="3" checked="checked" />推广会场</span>
					</td>
	           	</tr>
           		<tr>
           			<input type="hidden" id="productTypeGroup" name="productTypeGroup" value="" />
	            	<td class="title" width="20%">类目限制<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td">
						<c:forEach items="${productTypeList }" var="productType">
							<span class="radioClass"><input type="checkbox" id="productTypeGroup${productType.id }" class="productTypeGroup" value="${productType.id }" />${productType.name }</span>
						</c:forEach>
					</td>
	           	</tr>
           	</c:if>
           	<c:if test="${activityAreaType != '3' }">
           		<c:if test="${not empty activityArea.id }">
		           	<tr>
		            	<td class="title" width="20%">类型<span class="required">*</span></td>
						<td align="left" class="l-table-edit-td">
							<span class="radioClass"><input type="radio" name="type" value="2" disabled="disabled" <c:if test="${activityArea.type == '2' }">checked</c:if> />单品会场</span>
							<span class="radioClass"><input type="radio" name="type" value="1" disabled="disabled" <c:if test="${activityArea.type == '1' }">checked</c:if> />品牌会场</span>
						</td>
		           	</tr>
	           	</c:if>
	           	<c:if test="${empty activityArea.id }">
		           	<tr>
		            	<td class="title" width="20%">类型<span class="required">*</span></td>
						<td align="left" class="l-table-edit-td">
							<span class="radioClass"><input type="radio" name="type" value="2" <c:if test="${activityArea.type == '2' }">checked</c:if> validate="{required:true}" />单品会场</span>
							<span class="radioClass"><input type="radio" name="type" value="1" <c:if test="${activityArea.type == '1' }">checked</c:if> validate="{required:true}" />品牌会场</span>
						</td>
		           	</tr>
	           	</c:if>
           	</c:if>
           	<tr>
	           	<td class="title" width="20%">是否预付会场<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass"><input type="radio" name="isPreSell" value="0" <c:if test="${not empty activityArea.id }">disabled="disabled"</c:if> <c:if test="${empty activityArea || activityArea.isPreSell == '0' }">checked</c:if> validate="{required:true}" />否</span>
					<span class="radioClass"><input type="radio" name="isPreSell" value="1" <c:if test="${not empty activityArea.id }">disabled="disabled"</c:if> <c:if test="${activityArea.isPreSell == '1' }">checked</c:if> validate="{required:true}" />是</span>
					<a id="setPreSellRule" onclick="setAreaActivityPreSellRule(1);" style="color:blue;">【设置预售规则】</a>
					<input type="hidden" id="activityPriceLimit" name="activityPriceLimit" value="${activityAreaPreSellRule.activityPriceLimit }"/>
					<input type="hidden" id="depositLimit" name="depositLimit" value="${activityAreaPreSellRule.depositLimit }"/>
					<input type="hidden" id="minRate" name="minRate" value="${activityAreaPreSellRule.minRate }"/>
					<input type="hidden" id="maxRate" name="maxRate" value="${activityAreaPreSellRule.maxRate }"/>
					<input type="hidden" id="offsetMultiple" name="offsetMultiple" value="${activityAreaPreSellRule.offsetMultiple }"/>
				</td>
		    </tr>
			<tr>
	           	<td class="title" width="20%">分享得积分<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass"><input type="radio" name="shareIntegralType" value="0" <c:if test="${empty activityArea || activityArea.shareIntegralType == '0' }">checked</c:if> validate="{required:true}" />无积分</span>
					<span class="radioClass"><input type="radio" name="shareIntegralType" value="1" <c:if test="${activityArea.shareIntegralType == '1' }">checked</c:if> validate="{required:true}" />首次得积分</span>
					<span class="radioClass"><input type="radio" name="shareIntegralType" value="2" <c:if test="${activityArea.shareIntegralType == '2' }">checked</c:if> validate="{required:true}" />每日首次分享得积分</span>
					<input type="text" style="width: 50px;" id="minShareIntegral" name="minShareIntegral" <c:if test="${activityArea.minShareIntegral gt 0}">value="${activityArea.minShareIntegral}"</c:if>>~
					<input type="text" style="width: 50px;" id="maxShareIntegral" name="maxShareIntegral" <c:if test="${activityArea.maxShareIntegral gt 0}">value="${activityArea.maxShareIntegral}"</c:if>>
				</td>
		    </tr>
			<tr height="100px;">
				<td class="title" width="20%">分享展示图<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 75px;height: 75px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${activityArea.activitySharePic}" id="activitySharePicImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="activitySharePicFile" name="file" onchange="ajaxFileUploadImg('activitySharePicFile');" />
					</div>
					<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：200x200像素 大小不超过128K</span>
					<span style="margin-left: 10px;">
			 			<a href="${contextPathStr }${activityArea.activitySharePic}" id="activitySharePicA" target="_blank" >【查看图片】</a>
			 		</span>
				</td>
			</tr>
			<tr>
				<td class="title">分享描述<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea rows="5" cols="100" maxlength="256" id="activityShareDesc" name="activityShareDesc" validate="{required:true}" >${activityArea.activityShareDesc }</textarea>
				</td>
			</tr>
			<tr>
	           	<td class="title" width="20%">会场限购<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass"><input type="radio" name="purchaseLimits" value="0" <c:if test="${empty activityArea || activityArea.purchaseLimits == '0' }">checked</c:if> validate="{required:true}" />不限购</span>
					<span class="radioClass"><input type="radio" name="purchaseLimits" value="1" <c:if test="${activityArea.purchaseLimits == '1' }">checked</c:if> validate="{required:true}" />限购<input type="text" style="width: 50px;" id="purchaseLimitsQuantity" name="purchaseLimitsQuantity" <c:if test="${activityArea.purchaseLimitsQuantity gt 0}">value="${activityArea.purchaseLimitsQuantity}"</c:if>>件</span>
				</td>
		    </tr>
           	<tr>
            	<td class="title" width="20%">会场标签<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:260px;" id="areaLabel" name="areaLabel" type="text" value="${activityArea.areaLabel }" validate="{required:true,rangelength:[2,6]}" />
					<span class="activity-hint" >注意：2-6个字符之间</span>
					<br>
					<br>
					<div style="position: relative;width: 75px;height: 75px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${activityArea.areaLabelPic}" id="areaLabelPicImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="areaLabelPicFile" name="file" onchange="ajaxFileUploadImg('areaLabelPicFile');" />
					</div>
					<span style="color: #6B6B6B;">图片的格式为：.JPG.PNG，尺寸要求：大小不超过128K</span>
					<span style="margin-left: 10px;">
			 			<a href="${contextPathStr }${activityArea.areaLabelPic}" id="areaLabelPicA" target="_blank" >【查看图片】</a>
			 		</span>
				</td>
           	</tr>
           	<tr>
				<td class="title">活动描述和报名要求<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea rows="5" cols="100" id="description" name="description" validate="{required:true}" >${activityArea.description }</textarea>
				</td>
			</tr>
			<tr>
            	<td class="title" width="20%">利益点<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:260px;" type="text" id="benefitPoint" name="benefitPoint" value="${activityArea.benefitPoint }" validate="{required:true}" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">报名时间<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td table-edit-activity-time">
					<div><input type="text" class="dateEditor" id="enrollBeginTimeStr" name="enrollBeginTimeStr" value="<fmt:formatDate value="${activityArea.enrollBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
					<span>到</span>
					<div><input type="text" class="dateEditor" id="enrollEndTimeStr" name="enrollEndTimeStr" value="<fmt:formatDate value="${activityArea.enrollEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
					<span class="activity-hint" >注意：报名开始时间必须小于报名结束时间，报名结束时间必须小于活动结束时间</span>
				</td>
           	</tr>
           	<tr height="100px;">
            	<td class="title" width="20%">报名图<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 75px;height: 75px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${activityArea.pic}" id="picImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="picFile" name="file" onchange="ajaxFileUploadImg('picFile');" />
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：200x200像素</span>
			 		<span style="margin-left: 10px;">
			 			<a href="${contextPathStr }${activityArea.pic}" id="picA" target="_blank" >【查看图片】</a>
			 		</span>
				</td>
           	</tr>
           	<tr height="100px;">
            	<td class="title" width="20%">APP入口图<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 150px;height: 75px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${activityArea.entryPic}" id="entryPicImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="entryPicFile" name="file" onchange="ajaxFileUploadImg('entryPicFile');" />
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：800x400像素，大小不超过100Kb</span>
			 		<span style="margin-left: 10px;">
			 			<a href="${contextPathStr }${activityArea.entryPic}" id="entryPicA" target="_blank" >【查看图片】</a>
			 		</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">限商家报名额<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="limitMchtNum" name="limitMchtNum" value="${activityArea.limitMchtNum }" validate="{required:true}" />
				</td>
           	</tr>
           	<tr>
				<td class="title">推送商家</td>
				<td align="left" class="l-table-edit-td">
					<c:forEach items="${pushMchtTypeList }" var="pushMchtType" >
						<span style="margin-right: 20px;">
							<input type="radio" id="pushMchtType" <c:if test="${activityAreaType == '3' || activityArea.status eq '1'}">disabled</c:if> name="pushMchtType" value="${pushMchtType.statusValue }" <c:if test="${activityArea.pushMchtType == pushMchtType.statusValue }">checked</c:if> />${pushMchtType.statusDesc }
						</span>
					</c:forEach>
					<span style="color: #6B6B6B;">注意：推送商家类型、追加指定商家序号，不能同时为空</span>
				</td>
			</tr>
			<tr>
				<td class="title">追加指定商家序号</td>
				<td align="left" class="l-table-edit-td">
					<input type="hidden" id="mchtIdGroupCodeStr" value="${mchtIdGroupCode }" />
					<textarea id="mchtIdGroupCode" rows="5" class="textarea" cols="80">${mchtIdGroupCode }</textarea>
					<input type="button" id="choice" style="background-color: rgba(255, 153, 0, 1);width: 50px;cursor:pointer;height: 24px;border-radius: 3px;" onclick="choiceMemberList();" value="检测">
					<span style="color: #6B6B6B;">注意：商家序号用英文逗号隔开</span>
				</td>
			</tr>
			<tr>
				<td class="title">会员限制<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<select style="width: 135px;" id="minMemberGroup" name="minMemberGroup" validate="{required:true}" >
						<c:forEach items="${memberGroupList }" var="group">
							<option value="${group.id}" <c:if test="${activityArea.minMemberGroup eq group.id }">selected</c:if> >${group.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<c:if test="${activityAreaType != '3' }">
				<tr>
					<td class="title">分组<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td">
						<select id="activityGroupId" name="activityGroupId" style="width: 135px;" validate="{required:true}" >
							<option value="">请选择...</option>
							<c:forEach var="activityGroup" items="${activityGroupList }">
								<option value="${activityGroup.id }" <c:if test="${activityArea.activityGroupId eq activityGroup.id }">selected</c:if> >
									${activityGroup.id }.${activityGroup.groupName }
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>