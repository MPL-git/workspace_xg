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
    .tr-alert-div .edit-td {
		position: relative;
	}
	#alert-div {
		position: absolute;
		bottom: calc(100% - 8px);
		left: 8px;
		z-index: 1;
		width: 800px;
		background: #fff;
		border:1px dotted black; 
	}
	#alert-div {
		display: none;
	}
	#alert-div table {
		width: 100%;
	}
	#alert-div tr td {
		border: none;
		width: 50%;
		color: #4700FF;
		font-weight: bolder;
		cursor: pointer;
	}
	#alert-div tr:nth-child(1) td:nth-child(1) {
		color: black;
		cursor: Auto;
	}
	#alert-div tr:nth-child(1) td:nth-child(2) {
		text-align: right;
		cursor: pointer;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#toEditRecDate").bind('click',function(){
			var couponIds = '${couponIds}';
			var recBeginDate = $("#recBeginDate").val();
			var recEndDate = $("#recEndDate").val();
			var id = $("#id").val();
			$.ligerDialog.open({
				height: 400,
				width: 400,
				title: "修改领取时间",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/activityNew/toEditRecDate.shtml?couponIds=" + couponIds+"&recBeginDate="+recBeginDate+"&recEndDate="+recEndDate+"&id="+id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		});
		
		
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 160
		});
		
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
						content : lable.html(),width: 220
					});
	        	}
	        },
	        success: function (lable,element) {
	            lable.ligerHideTip();
	            lable.remove();
	        },
	        submitHandler: function(form) {
	        	var operateAuditStatus = $("input:radio[name='operateAuditStatus']:checked").val();
	        	var cooAuditStatus = $("input:radio[name='cooAuditStatus']:checked").val();
	        	var designAuditStatus = $("input:radio[name='designAuditStatus']:checked").val();
	        	if(operateAuditStatus == '2' || cooAuditStatus == '2' || designAuditStatus == '2') {
	        		var entryPic = $("#entryPic").val();
	        		if(entryPic == '') {
		        		commUtil.alertError("入口图不能为空！");
		        		return;
		        	}
		        	var posterPic = $("#posterPic").val();
		        	if(posterPic == '') {
		        		commUtil.alertError("头部海报图不能为空！");
		        		return;
		        	}
		        	var brandteamPic = $("#brandteamPic").val();
		        	if(brandteamPic == '') {
		        		commUtil.alertError("品牌团入口图不能为空！");
		        		return;
		        	}
		        	
	        	}
	        	if('${statusAudit}' == '2' || '${statusAudit}' == '1') { //专员审核，排期审核 BUG #10061
	        		if(operateAuditStatus == '2' || cooAuditStatus == '2' ) {
			        	if(!activityTime()) {
			        		return;
			        	}
		        		if(!validateActivity('${statusAudit }', $("#id").val())) {
		        			return;
		        		}
	        		}
	        	}
	        						
	        	  formSubmit();
	        }
	    }); 
		
		$("[name='auditRemarks']").bind("focus", function(){
			$("#alert-div").show();
		});
		$("#alert-div tr:nth-child(1) td:nth-child(2)").bind("click", function(){
			$("#alert-div").hide();			
		});
		$("#alert-div tr:gt(0) td:nth-child(1)").bind("click", function(){
			$("[name='auditRemarks']").val($(this).html());
			$("#alert-div").hide();
		});
		$("[name='auditRemarks']").bind("click", function(e){
			e.stopPropagation();
		});
		$("#alert-div tr td").bind("click", function(e){
			e.stopPropagation();
		});
		$("body").bind("click", function(){
			$("#alert-div").hide();
		});
		
		
	});

	//图片格式验证
	function ajaxFileUploadImg(statusImg) {
		var file = document.getElementById(statusImg).files[0]; 
        if(!/image\/(JPG|jpg|JPEG|jpeg)$/.test(file.type)){  
        	commUtil.alertError("图片格式不正确！");
            return;
        }
        var size = file.size;
        if(size > 100*1024 ) {
        	commUtil.alertError("图片过大，请重新上传！");
            return;
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() {
        		if(this.width != '800' || this.height != '400') {
        			commUtil.alertError("图片像素不是800x400像素！");
            	}else{
            		ajaxFileUploadPicFile(statusImg);
            	}
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);  
	}
	
	
	//图片格式验证
	function ajaxbrandteamPicFileUploadImg(statusImg) {
		var file = document.getElementById(statusImg).files[0]; 
        if(!/image\/(JPG|jpg|JPEG|jpeg)$/.test(file.type)){  
        	commUtil.alertError("图片格式不正确！");
            return;
        }
        var size = file.size;
        if(size > 100*1024 ) {
        	commUtil.alertError("图片过大，请重新上传！");
            return;
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() {
        		if(this.width != '1080' || this.height != '335') {
        			commUtil.alertError("图片像素不是1080x335像素！");
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
            		if(statusImg == 'posterPicFile') {
            			$("#posterPic").val(result.FILE_PATH);
    					$("#posterPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
    					$("#posterPicA").attr("href",'${contextPathStr }'+result.FILE_PATH);
            		}
            		if(statusImg == 'brandteamPicFile') {
            			$("#brandteamPic").val(result.FILE_PATH);
    					$("#brandteamPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
    					$("#brandteamPicA").attr("href",'${contextPathStr }'+result.FILE_PATH);
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
	
	//排期时间验证
	function activityTime() {
		var flag = true;		
		var preheatTime = $("#preheatTime").val();
		var activityBeginTime = $("#activityBeginTime").val();
		var activityEndTime = $("#activityEndTime").val();
		if(preheatTime == '') {
			commUtil.alertError("预热时间不能为空！");
			flag = false;
			return flag;
		}else if(activityBeginTime == '') {
			commUtil.alertError("活动开始时间不能为空！");
			flag = false;
			return flag;
		}else if(activityEndTime == '') {
			commUtil.alertError("活动结束时间不能为空！");
			flag = false;
			return flag;
		}else if(preheatTime >= activityBeginTime) {
			commUtil.alertError("预热时间必须小于活动开始时间！");
			flag = false;
			return flag;
		}else if(activityBeginTime >= activityEndTime) {
			commUtil.alertError("活动开始时间必须小于活动结束时间！");
			flag = false;
			return flag;
		}else if(new Date(activityEndTime) <= new Date) {
			commUtil.alertError("活动结束时间必须大于现在时间！");
			flag = false;
			return flag;
		}else{
			return flag;
		}
	}
	
	//验证品牌活动下是否有商品
	function validateActivity(statusAudit, activityId) {
		var flag = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/activityNew/validateActivity.shtml",
			type : 'POST',
			dataType : 'json',
			async : false,
			data : {statusAudit : statusAudit, activityId : activityId},
			success : function(data) {
				if(data.code != '200') {
					flag = false;
					commUtil.alertError(data.msg);
					$(".l-dialog-content").css("margin-right","10px");
				}
			},
			error : function(e) {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		return flag;
	}
	
	//审核流水表
	function activityAuditLogList(activityId) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "审核进度",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAuditLog/activityAuditLogList.shtml?activityId=" + activityId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//1级类目修改
	function updateProductTypeId(productTypeId) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/activityNew/updateProductTypeId.shtml',
			data: {productTypeId : productTypeId},
			dataType: 'json',
			success: function(data) {
				if(data.code != '200') {
					commUtil.alertError(data.msg);
				}else {
					if(data.msg != '') {
						var str = '<option value="">请选择...</option>';
						$.each(data.productTypeSecondList, function(index, item) {
							str += '<option value="'+ item.id +'">'+ item.name +'</option>';
						});
						$("#productTypeSecondId").html(str);
					}
				}
			},
			error: function(e) {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
	
	//合成图片 水印
	function updateActivityGroup(activityId, isSign) {
		if(isSign == '0') {
			isSign = '1';
		}else {
			isSign = '0';
		}
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/activityNew/updateActivityGroupId.shtml',
			data: {activityId : activityId, isSign : isSign},
			dataType: 'json',
			success: function(data) {
				if(data.code != '200') {
					commUtil.alertError(data.msg);
				}else {
					var strGroupName = '';
					var strHref = '';
					if(data.isSignStr == '0') {   
						strGroupName = '【加标】';
						strHref = 'javascript:updateActivityGroup('+activityId+', 0);';
					}else {
						strGroupName = '【还原】';
						strHref = 'javascript:updateActivityGroup('+activityId+', 1);';
					}
					$("#entryPicGroupName").html(strGroupName);
					$("#entryPicGroupName").attr("href", strHref);
					
					$("#entryPicImg").attr("src",contextPath + "/file_servelt"+data.entryPicStr);
					$("#entryPicA").attr("href",'${contextPathStr }'+data.entryPicStr);
				}
			},
			error: function(e) {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
	
	function viewProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 80,
			title: "商品信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/viewProduct.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//修改优惠券
	function cuPon(_this) {
		 var name =_this.name;
		 var value=_this.value;
		 var id=_this.id;
		var ver = /^\d{1,6}(\.\d{1,2})?$/;
		if (!ver.test(value)) {
			commUtil.alertError("请输入正确数字");
		 }else{
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/activityNew/updateCuPon.shtml",
				 data : {id : id, Name : name, Value : value},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.statusCode != 200){
						 
						 commUtil.alertError(json.message);
					 }
					 else{
						 commUtil.alertSuccess("更新成功");
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
			 
		 }   
	}	
	
	//修改满减优惠
	function fullCut(_this) {
		var fullCutList = [];
		var fullName = '';
		var reduceName = '';
		var id=_this;
		var flagFull = true;
		/* var ver = /^[1-9]\d*$/; */
		var ver=/^[0-9]+([.]{1}[0-9]+){0,1}$/;
		$(".full_class_list").each(function(){
			fullName = $(this).find('input').eq(0).val(); 
			reduceName = $(this).find('input').eq(1).val(); 
			if(!ver.test(fullName)){
				flagFull=false;
				commUtil.alertError("满元请输入整数！");
				return;
			}
			if(!ver.test(reduceName)){
				flagFull=false;
				commUtil.alertError("减元请输入整数！");
				return;
			}
			if (Number(fullName)<Number(reduceName)) {
				flagFull=false;
				commUtil.alertError("减元不能大于满元！");
				return;
			}
		
			if(!flagFull){
				return false;
			}
			
			var object = new Object();
			object.fullName = fullName;
			object.reduceName = reduceName;
			object.sumFlag = 0;
			fullCutList.push(object);
	
		});
	
		
		var jsonFullCut = JSON.stringify(fullCutList);
		$("#jsonFullCut").val(jsonFullCut);
		
	if (flagFull==true) {
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/activityNew/updateFullCut.shtml",
			 data : {id : id, jsonFullCut : jsonFullCut},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.statusCode != 200){
					 
					 commUtil.alertError(json.message);
				 }
				 else{
					 commUtil.alertSuccess("更新成功");
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	 }
		
	}
	

		function formSubmit() {
			$.ajax({
				url : "${pageContext.request.contextPath}/activityNew/auditActivity.shtml",
				type : 'POST',
				dataType : 'json',
				data : $("#form1").serialize(),
				success : function(data) {
					if ("0000"==data.returnCode) {
						  if (data.operateAuditStatus!=null && data.operateAuditStatus!='') {						  
						     $.ligerDialog.success("操作成功",function() {
							 window.parent.examineStatus(data.operateAuditStatus,data.id);//子页面调用父页面方法;
							 frameElement.dialog.close();
						   });		
						}else if (data.cooAuditStatus !=null && data.cooAuditStatus!='') {							
							 $.ligerDialog.success("操作成功",function() {
								 window.parent.schedulingStatus(data.cooAuditStatus,data.id);
								 frameElement.dialog.close();
							   });	
						}
			
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

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" method="post" enctype="multipart/form-data" id="form1" action="" >
		<input type="hidden" id="entryPic" name="entryPic" value="${activityNewCustom.entryPic}" />
		<input type="hidden" id="posterPic" name="posterPic" value="${activityNewCustom.posterPic}" />
		<input type="hidden" id="brandteamPic" name="brandteamPic" value="${activityNewCustom.brandteamPic}" />
		<input type="hidden" id="statusAudit" name="statusAudit" value="${statusAudit }" />
		<input type="hidden" id="id" name="id" value="${activityNewCustom.id }" />
		<table class="gridtable">
			<c:if test="${statusAudit != '3' }">
				<tr>
	            	<td class="title" width="20%">活动名称<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:260px;" id="name" name="name" type="text" value="${activityNewCustom.name }" validate="{required:true,maxlength:100}" />
					</td>
	           	</tr>
				<tr>
	            	<td class="title" width="20%">类目<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="productTypeId" name="productTypeId" validate="{required:true}" onchange="updateProductTypeId(this.value);" >
							<option value="">请选择...</option>
							<c:forEach var="productType" items="${productTypeList }">
								<option value="${productType.id }" <c:if test="${productType.id == activityNewCustom.productTypeId }">selected</c:if>  > 
									${productType.name }
								</option>
							</c:forEach>
						</select>
						<span style="margin-left: 20px;" >
							<select style="width: 135px;" id="productTypeSecondId" name="productTypeSecondId" >
								<option value="">请选择...</option>
								<c:forEach var="productTypeSecond" items="${productTypeSecondList }">
									<option value="${productTypeSecond.id }" <c:if test="${productTypeSecond.id == activityNewCustom.productTypeSecondId }">selected</c:if>  > 
										${productTypeSecond.name }
									</option>
								</c:forEach>
							</select>
						</span>
					</td>
	           	</tr>
           	</c:if>
           	<c:if test="${statusAudit == '3' }"><!-- 设计审核 -->
           		<tr>
	            	<td class="title" width="20%">活动名称</td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:260px;" id="name" name="name" type="text" value="${activityNewCustom.name }" disabled="disabled" />
					</td>
	           	</tr>
				<tr>
	            	<td class="title" width="20%">类目</td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="productTypeId" name="productTypeId" disabled="disabled" >
							<option value="">请选择...</option>
							<c:forEach var="productType" items="${productTypeList }">
								<option value="${productType.id }" <c:if test="${productType.id == activityNewCustom.productTypeId }">selected</c:if>  > 
									${productType.name }
								</option>
							</c:forEach>
						</select>
						<span style="margin-left: 20px;" >
							<select style="width: 135px;" id="productTypeSecondId" name="productTypeSecondId" disabled="disabled" >
								<option value="">请选择...</option>
								<c:forEach var="productTypeSecond" items="${productTypeSecondList }">
									<option value="${productTypeSecond.id }" <c:if test="${productTypeSecond.id == activityNewCustom.productTypeSecondId }">selected</c:if>  > 
										${productTypeSecond.name }
									</option>
								</c:forEach>
							</select>
						</span>
					</td>
	           	</tr>
           	</c:if>
			<tr>
            	<td class="title" width="20%">品牌</td>
				<td align="left" class="l-table-edit-td" >
					<select style="width: 135px;" disabled="disabled"  >
						<option value=""></option>
						<c:forEach var="productBrand" items="${productBrandList }">
							<option value="${productBrand.id }" <c:if test="${productBrand.id == activityNewCustom.productBrandId }">selected</c:if>  > 
								${productBrand.name }
							</option>
						</c:forEach>
					</select>
					<span style="margin-left: 20px;">
						品牌限制：
						<span class="radioClass"><input type="radio" disabled="disabled" name="brandLimitType" value="1" <c:if test="${activityNewCustom.brandLimitType == '1' }">checked</c:if> />品牌专场</span>
						<span class="radioClass"><input type="radio" disabled="disabled" name="brandLimitType" value="2" <c:if test="${activityNewCustom.brandLimitType == '2' }">checked</c:if> />多品牌联合</span>
					</span>
				</td>
           	</tr>
           	<c:if test="${statusAudit != '3' }">
	           	<tr>
	            	<td class="title" width="20%">利益点<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:260px;" type="text" id="benefitPoint" name="benefitPoint" value="${activityNewCustom.benefitPoint }" validate="{required:true}" />
					</td>
	           	</tr>
           	</c:if>
           	<c:if test="${statusAudit == '3' }"><!-- 设计审核 -->
           		<tr>
	            	<td class="title" width="20%">利益点</td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:260px;" type="text" id="benefitPoint" name="benefitPoint" value="${activityNewCustom.benefitPoint }" disabled="disabled" />
					</td>
	           	</tr>
           	</c:if>
           	<tr>
            	<td class="title" width="20%">期望活动时间</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:135px;" type="text" value='<fmt:formatDate value="${activityNewCustom.expectedStartTime }" pattern="yyyy-MM-dd"/>' disabled="disabled" >
				</td>
           	</tr>
           	<tr height="220px;">
            	<td class="title" width="20%">品牌团入口图<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 200px;height: 100px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${activityNewCustom.brandteamPic}" id="brandteamPicImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="brandteamPicFile" name="file" onchange="ajaxbrandteamPicFileUploadImg('brandteamPicFile');" />
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：1080x335像素，大小不超过：100Kb</span>
			 		<span style="margin-left: 10px;">
			 			<a href="${contextPathStr}${activityNewCustom.brandteamPic}" id="brandteamPicA" target="_blank" >【查看图片】</a>
			 		</span>
				</td>
           	</tr>
           	<tr height="220px;">
            	<td class="title" width="20%">入口图<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 200px;height: 100px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${activityNewCustom.entryPic}" id="entryPicImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="entryPicFile" name="file" onchange="ajaxFileUploadImg('entryPicFile');" />
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：800x400像素，大小不超过：100Kb</span>
			 		<span style="margin-left: 10px;">
			 			<a href="${contextPathStr }${activityNewCustom.entryPic}" id="entryPicA" target="_blank" >【查看图片】</a>
			 			<c:if test="${statusAudit == '3' }">
			 				<c:if test="${not empty activityNewCustom.activityGroupId  }">
		 						<a href="javascript:updateActivityGroup(${activityNewCustom.id }, ${activityNewCustom.isSign });" id="entryPicGroupName"  >
		 							<c:if test="${activityNewCustom.isSign == '0' }">【加标】</c:if>
		 							<c:if test="${activityNewCustom.isSign != '0' }">【还原】</c:if>
		 						</a>
			 				</c:if>
			 			</c:if>
			 		</span>
				</td>
           	</tr>
           	<tr height="220px;">
            	<td class="title" width="20%">头部海报图<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div style="position: relative;width: 200px;height: 100px;border: 1px solid #6B6B6B;">
						<img src="${pageContext.request.contextPath}/file_servelt${activityNewCustom.posterPic}" id="posterPicImg" alt="" style="width: 100%;height: 100%;display: block;">
						<input style="position: absolute;top: 0;left: 0;width: 100%;height: 100%;z-index: 1; opacity: 0;" type="file" id="posterPicFile" name="file" onchange="ajaxFileUploadImg('posterPicFile');" />
					</div>
					<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：800x400像素，大小不超过：100Kb</span>
					<span style="margin-left: 10px;">
			 			<a href="${contextPathStr }${activityNewCustom.posterPic}" id="posterPicA" target="_blank" >【查看图片】</a>
			 		</span>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">促销方式</td>
				<td align="left" class="l-table-edit-td">
					<table>
						<!-- 优惠券 -->
						<c:if test="${activityNewCustom.preferentialType == 1 }">
						    <c:if test="${activityNewCustom.mchtType == 1}">
							<c:forEach items="${cuponList }" var="cupon" varStatus="ind">
								<c:if test="${ind.index == 0 }">
									<tr>
										<td style="border:none">${activityNewCustom.preferentialTypeDesc }：</td>
										<td style="border:none">
											面额&nbsp;<input type="text" id="${cupon.id}" name="money" style="width: 50px;" value="${cupon.money}" onchange="cuPon(this)" <c:if test="${statusAudit == '1'}">disabled="disabled"</c:if>>元&nbsp; &nbsp;使用条件&nbsp;<input type="text" id="${cupon.id}" name="minimum" style="width: 50px;" value="${cupon.minimum}" onchange="cuPon(this)" <c:if test="${statusAudit == '1'}">disabled="disabled"</c:if>/>元&nbsp; &nbsp;发行量&nbsp;<input type="text" id="${cupon.id}" name="grantQuantity" style="width: 50px;" value="${cupon.grantQuantity}" onchange="cuPon(this)" <c:if test="${statusAudit == '1'}">disabled="disabled"</c:if>/>张&nbsp;（ID：${cupon.id}）
										</td>
									</tr>
								</c:if>
								<c:if test="${ind.index != 0 }">
									<tr>
										<td style="border:none"></td>
										<td style="border:none">
											面额&nbsp;<input type="text" id="${cupon.id}" name="money" style="width: 50px;" value="${cupon.money}" onchange="cuPon(this)" <c:if test="${statusAudit == '1'}">disabled="disabled"</c:if>>元&nbsp; &nbsp;使用条件&nbsp;<input type="text" id="${cupon.id}" name="minimum" style="width: 50px;" value="${cupon.minimum}" onchange="cuPon(this)" <c:if test="${statusAudit == '1'}">disabled="disabled"</c:if>/>元&nbsp; &nbsp;发行量&nbsp;<input type="text" id="${cupon.id}" name="grantQuantity" style="width: 50px;" value="${cupon.grantQuantity}" onchange="cuPon(this)" <c:if test="${statusAudit == '1'}">disabled="disabled"</c:if>/>张&nbsp;（ID：${cupon.id}）
										</td>
									</tr>
								</c:if>
							</c:forEach>
							</c:if>
							<c:if test="${activityNewCustom.mchtType == 2}">
							<c:forEach items="${cuponList }" var="cupon" varStatus="ind">
								<c:if test="${ind.index == 0 }">
									<tr>
										<td style="border:none">${activityNewCustom.preferentialTypeDesc }：</td>
										<td style="border:none">
											面额&nbsp;<input type="text" style="width: 50px;" value="${cupon.money}" disabled="disabled">元&nbsp; &nbsp;使用条件&nbsp;<input type="text" style="width: 50px;" value="${cupon.minimum}" disabled="disabled"/>元&nbsp; &nbsp;发行量&nbsp;<input type="text" style="width: 50px;" value="${cupon.grantQuantity}" disabled="disabled"/>张&nbsp;（ID：${cupon.id}）
										</td>
									</tr>
								</c:if>
								 <c:if test="${ind.index != 0 }">
									<tr>
										<td style="border:none"></td>
										<td style="border:none">
											面额&nbsp;<input type="text" style="width: 50px;" value="${cupon.money}" disabled="disabled">元&nbsp; &nbsp;使用条件&nbsp;<input type="text" style="width: 50px;" value="${cupon.minimum}" disabled="disabled"/>元&nbsp; &nbsp;发行量&nbsp;<input type="text" style="width: 50px;" value="${cupon.grantQuantity}" disabled="disabled"/>张&nbsp;（ID：${cupon.id}）
										</td>
									</tr>
								</c:if>
							</c:forEach>
							</c:if>
							
							<c:if test="${statusAudit == 2}">
							<tr>
								<td style="border:none">领取时间：</td>
								<td style="border:none" align="left" class="l-table-edit-td table-edit-activity-time">
									<span class="activity-time"></span>
									<div><input type="text" readonly="readonly" id="recBeginDate" name="recBeginDate" value="<fmt:formatDate value="${recBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
									<span>到</span>
									<div><input type="text" readonly="readonly" id="recEndDate" name="recEndDate" value="<fmt:formatDate value="${recEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
									<span><a href="javascript:;" id="toEditRecDate">【修改时间】</a></span>
									<span class="activity-hint" >注意：排期时间修改后领取时间会被更新</span>
								</td>
							</tr>
							</c:if>
						</c:if>
						
						<!-- 满减 -->
						<c:if test="${activityNewCustom.preferentialType == 2 }">
							<tr>
								<td style="border:none">${activityNewCustom.preferentialTypeDesc }：</td>
								<td style="border:none">
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullCutCustom.ladderFlag == 0}">checked</c:if> >非阶梯</span>
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullCutCustom.ladderFlag == 1}">checked</c:if> >阶梯</span>
								</td>
							</tr>
						 <c:if test="${activityNewCustom.mchtType == 1}">
							<c:if test="${fullCutCustom.ladderFlag == 0 }"><!-- 非阶梯 -->
								<tr>
									<td style="border:none"></td>
									<td style="border:none" class="full_class_list">
										满&nbsp;<input style="width: 50px;" type="text"  id="${fullCutCustom.id}" value="${fullCutCustom.full}" onchange="fullCut(this.id)" <c:if test="${statusAudit == '1'}">disabled="disabled"</c:if>/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input style="width: 50px;" type="text" id="${fullCutCustom.id}" value="${fullCutCustom.cut}" onchange="fullCut(this.id)" <c:if test="${statusAudit == '1'}">disabled="disabled"</c:if>/>元&nbsp;&nbsp;<input type="checkbox"  <c:if test="${fullCutCustom.sumFlag == 1}">checked</c:if> />累加
									</td>
								</tr>
							</c:if>
							<c:if test="${fullCutCustom.ladderFlag == 1 }"><!-- 阶梯 -->
								<c:forEach items="${fullCutList }" var="fullCut" varStatus="ind">
									<td style="border:none"></td>
									<td style="border:none" class="full_class_list">
										满&nbsp;<input style="width: 50px;" type="text" id="${fullCutCustom.id}" value="${fullCut.full}" onchange="fullCut(this.id)" <c:if test="${statusAudit == '1'}">disabled="disabled"</c:if>/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input style="width: 50px;" type="text" id="${fullCutCustom.id}" value="${fullCut.cut}" onchange="fullCut(this.id)" <c:if test="${statusAudit == '1'}">disabled="disabled"</c:if>/>元
									</td>
								</c:forEach>
							</c:if>
						</c:if>
						   
						   <c:if test="${activityNewCustom.mchtType == 2}">
							<c:if test="${fullCutCustom.ladderFlag == 0 }"><!-- 非阶梯 -->
								<tr>
									<td style="border:none"></td>
									<td style="border:none">
										满&nbsp;<input style="width: 50px;" type="text"  value="${fullCutCustom.full}" disabled="disabled"/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input style="width: 50px;" type="text" value="${fullCutCustom.cut}" disabled="disabled"/>元&nbsp;&nbsp;<input type="checkbox"  <c:if test="${fullCutCustom.sumFlag == 1}">checked</c:if> />累加
									</td>
								</tr>
							</c:if>
							<c:if test="${fullCutCustom.ladderFlag == 1 }"><!-- 阶梯 -->
								<c:forEach items="${fullCutList }" var="fullCut" varStatus="ind">
									<td style="border:none"></td>
									<td style="border:none" class="full_class_list">
										满&nbsp;<input style="width: 50px;" type="text" value="${fullCut.full}" disabled="disabled"/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input style="width: 50px;" type="text" value="${fullCut.cut}" disabled="disabled"/>元
									</td>
								</c:forEach>
							</c:if>
						</c:if>
						</c:if>
						
						<!-- 满赠 -->
						<c:if test="${activityNewCustom.preferentialType == 3 }">
							<tr>
								<td style="border:none">${activityNewCustom.preferentialTypeDesc }：</td>
								<td style="border:none">
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullGive.type == 1}">checked</c:if> >满额赠</span>
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullGive.type == 2}">checked</c:if> >买即赠</span>
								</td>
							</tr>
							<c:if test="${fullGive.type == 1}">
								<tr>
									<td style="border:none"></td>
									<td style="border:none">
										满额：&nbsp;满&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${fullGive.minimum}"/>&nbsp;元&nbsp;&nbsp;<input type="checkbox" disabled="disabled" value="" <c:if test="${fullGive.sumFlag == 1}">checked</c:if> />累加
									</td>
								</tr>
							</c:if>
							<tr>
								<td style="border:none"></td>
								<td style="border:none">
									赠品ID:<input type="text" style="width: 50px;" disabled="disabled" value="${fullGive.productId}"/><a href="javascript:;" onclick="viewProduct(${fullGive.productId});">【${product.name}】</a>
								</td>
							</tr>
							<tr>
								<td style="border:none"></td>
								<td style="border:none">
									数量:<input type="text" style="width: 50px;" disabled="disabled" value="${fullGive.productNum}"/>
								</td>
							</tr>
						</c:if>
						
						<!-- 多买优惠 -->
						<c:if test="${activityNewCustom.preferentialType == 4 }">
							<tr>
								<td style="border:none">${activityNewCustom.preferentialTypeDesc }：</td>
								<td style="border:none">
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullDiscountType == 1}">checked</c:if> >满M件减N件</span>
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullDiscountType == 2}">checked</c:if> >M件N元</span>
									<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullDiscountType == 3}">checked</c:if> >M件N折</span>
								</td>
							</tr>
							<c:if test="${fullDiscountType == 1 }">
								<td style="border:none"></td>
								<tr>
									<td style="border:none">
										&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${fullDiscountMap.fullOfOne}"/>&nbsp;件&nbsp;&nbsp;减&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${fullDiscountMap.fullGiftsOneName}"/>件
									</td>
								</tr>
							</c:if>
							<c:if test="${fullDiscountType == 2 }">
								<td style="border:none"></td>
								<tr>
									<td style="border:none">
										&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${fullDiscountMap.fullOfTwo}"/>&nbsp;件&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${fullDiscountMap.fullGiftsTwoName}"/>元
									</td>
								</tr>
							</c:if>	
							<c:if test="${fullDiscountType == 3 }">
								<c:forEach items="${fullDiscountList}" var="list">
									<tr>
										<td style="border:none"></td>
										<td style="border:none">
											&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${list.fullGiscountThree}"/>&nbsp;件&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${list.fullGiscountThreeName}"/>折
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</c:if>
					</table>
				</td>
           	</tr>
           	<c:if test="${statusAudit == '1' }"><!-- 专员审核 -->
	           	<tr>
	            	<td class="title" width="20%">排期</td>
					<td align="left" class="l-table-edit-td table-edit-activity-time">
						<span>预热：</span>
						<div><input type="text" class="dateEditor" id="preheatTime" name="preheatTime" value="<fmt:formatDate value="${activityNewCustom.preheatTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
						<span class="activity-time">活动时间：</span>
						<div><input type="text" class="dateEditor" id="activityBeginTime" name="activityBeginTime" value="<fmt:formatDate value="${activityNewCustom.activityBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
						<span>到</span>
						<div><input type="text" class="dateEditor" id="activityEndTime" name="activityEndTime" value="<fmt:formatDate value="${activityNewCustom.activityEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
						<span class="activity-hint" >注意：预热时间必须小于活动开始时间，活动开始时间必须小于活动结束时间</span>
					</td>
	           	</tr>
	           	<tr>
	           		<td class="title" width="20%">特卖分组</td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="activityGroupId" name="activityGroupId" >
							<option value="">请选择...</option>
							<c:forEach var="activityGroup" items="${activityGroupList }">
								<option value="${activityGroup.id }" <c:if test="${activityGroup.id == activityNewCustom.activityGroupId }">selected</c:if>  > 
									${activityGroup.id }.${activityGroup.groupName }
								</option>
							</c:forEach>
						</select>
					</td>
	           	</tr>
	           	<c:if test="${not empty activityNewCustom.preSellAuditStatus && activityNewCustom.preSellAuditStatus ne 0}">
	           	<tr>
	           		<td class="title" width="20%">申请预售审核结果<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<span class="radioClass"><input type="radio" name="preSellAuditStatus" value="2" <c:if test="${activityNewCustom.preSellAuditStatus == '2' }">checked</c:if> validate="{required:true}" />通过</span>
						<span class="radioClass"><input type="radio" name="preSellAuditStatus" value="3" <c:if test="${activityNewCustom.preSellAuditStatus == '3' }">checked</c:if> validate="{required:true}" />驳回</span>
					</td>
	           	</tr>
	           	<tr>
	            	<td class="title" width="20%">申请预售驳回理由</td>
					<td align="left" class="l-table-edit-td">
						<input name="preSellAuditRemarks" style="width: 798px;" />
					</td>
	           	</tr>
	           	</c:if>
	           	<tr>
	            	<td class="title" width="20%">审核结果<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td">
						<span class="radioClass"><input type="radio" name="operateAuditStatus" value="2" <c:if test="${activityNewCustom.operateAuditStatus == '2' }">checked</c:if> validate="{required:true}" />通过</span>
						<span class="radioClass"><input type="radio" name="operateAuditStatus" value="3" <c:if test="${activityNewCustom.operateAuditStatus == '3' }">checked</c:if> validate="{required:true}" />驳回</span>
						<span style="margin-left: 10px;">
							<a href="javascript:activityAuditLogList(${activityNewCustom.id})">【查看审核日志】</a>
						</span>
					</td>
	           	</tr>
           	</c:if>
           	<c:if test="${statusAudit == '2' }"><!-- 排期审核 -->
	           	<tr>
	            	<td class="title" width="20%">排期</td>
					<td align="left" class="l-table-edit-td table-edit-activity-time">
						<span>预热：</span>
						<c:if test="${activityStatus == '13' }">
							<div><input type="text" readonly="readonly" id="preheatTime" name="preheatTime" value="<fmt:formatDate value="${activityNewCustom.preheatTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
						</c:if>
						<c:if test="${activityStatus != '13' }">
							<div><input type="text" class="dateEditor" id="preheatTime" name="preheatTime" value="<fmt:formatDate value="${activityNewCustom.preheatTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
						</c:if>
						<span class="activity-time">活动时间：</span>
						<c:if test="${activityStatus == '13' }">
							<div><input type="text" readonly="readonly" id="activityBeginTime" name="activityBeginTime" value="<fmt:formatDate value="${activityNewCustom.activityBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
						</c:if>
						<c:if test="${activityStatus != '13' }">
							<div><input type="text" class="dateEditor" id="activityBeginTime" name="activityBeginTime" value="<fmt:formatDate value="${activityNewCustom.activityBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
						</c:if>
						<span>到</span>
						<div><input type="text" class="dateEditor" id="activityEndTime" name="activityEndTime" value="<fmt:formatDate value="${activityNewCustom.activityEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
						<span class="activity-hint" >注意：预热时间必须小于活动开始时间，活动开始时间必须小于活动结束时间</span>
					</td>
	           	</tr>
	           	<tr>
	           		<td class="title" width="20%">特卖分组</td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="activityGroupId" name="activityGroupId" >
							<option value="">请选择...</option>
							<c:forEach var="activityGroup" items="${activityGroupList }">
								<option value="${activityGroup.id }" <c:if test="${activityGroup.id == activityNewCustom.activityGroupId }">selected</c:if>  > 
									${activityGroup.id }.${activityGroup.groupName }
								</option>
							</c:forEach>
						</select>
					</td>
	           	</tr>
	           	<c:if test="${not empty activityNewCustom.preSellAuditStatus && activityNewCustom.preSellAuditStatus ne 0}">
	           	<tr>
	           		<td class="title" width="20%">申请预售审核结果</td>
					<td align="left" class="l-table-edit-td" >
						<input type="radio" name="preSellAuditStatus" value="2" <c:if test="${activityNewCustom.preSellAuditStatus == 2}">checked="checked"</c:if>>通过
						<input type="radio" name="preSellAuditStatus" value="3" <c:if test="${activityNewCustom.preSellAuditStatus == 3}">checked="checked"</c:if>>驳回
					</td>
	           	</tr>
	           	<tr>
	            	<td class="title" width="20%">申请预售驳回理由</td>
					<td align="left" class="l-table-edit-td">
						<input name="preSellAuditRemarks" style="width: 798px;" value="${activityNewCustom.preSellAuditRemarks}"/>
					</td>
	           	</tr>
	           	</c:if>
	           	<tr>
	            	<td class="title" width="20%">审核结果<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td">
						<span class="radioClass"><input type="radio" name="cooAuditStatus" value="2" <c:if test="${activityNewCustom.cooAuditStatus == '2' }">checked</c:if> validate="{required:true}" />通过</span>
						<c:if test="${activityStatus != '13' }"><!-- activityStatus == 13  表示活动中状态 -->
							<span class="radioClass"><input type="radio" name="cooAuditStatus" value="3" <c:if test="${activityNewCustom.cooAuditStatus == '3' }">checked</c:if> validate="{required:true}" />驳回</span>
						</c:if>
						<span style="margin-left: 10px;">
							<a href="javascript:activityAuditLogList(${activityNewCustom.id})">【查看审核日志】</a>
						</span>
					</td>
	           	</tr>
           	</c:if>
           	<c:if test="${statusAudit == '3' }"><!-- 设计审核 -->
	           	<tr>
	            	<td class="title" width="20%">排期</td>
					<td align="left" class="l-table-edit-td">
						<fmt:formatDate value="${activityNewCustom.activityBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
						<c:if test="${not empty activityNewCustom.activityBeginTime or not empty activityNewCustom.activityEndTime }">到</c:if>
						<fmt:formatDate value="${activityNewCustom.activityEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
	           	</tr>
	           	<tr>
	           		<td class="title" width="20%">特卖分组</td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="activityGroupId" name="activityGroupId" disabled="disabled" >
							<option value="">请选择...</option>
							<c:forEach var="activityGroup" items="${activityGroupList }">
								<option value="${activityGroup.id }" <c:if test="${activityGroup.id == activityNewCustom.activityAreaActivityGroupId }">selected</c:if>  > 
									${activityGroup.id }.${activityGroup.groupName }
								</option>
							</c:forEach>
						</select>
					</td>
	           	</tr>
	           	<c:if test="${not empty activityNewCustom.preSellAuditStatus && activityNewCustom.preSellAuditStatus ne 0}">
	           	<tr>
	           		<td class="title" width="20%">申请预售审核结果</td>
					<td align="left" class="l-table-edit-td" >
						<input type="radio" name="preSellAuditStatus" value="2" <c:if test="${activityNewCustom.preSellAuditStatus == 2}">checked="checked"</c:if>>通过
						<input type="radio" name="preSellAuditStatus" value="3" <c:if test="${activityNewCustom.preSellAuditStatus == 3}">checked="checked"</c:if>>驳回
					</td>
	           	</tr>
	           	<tr>
	            	<td class="title" width="20%">申请预售驳回理由</td>
					<td align="left" class="l-table-edit-td">
						<input name="preSellAuditRemarks" style="width: 798px;" value="${activityNewCustom.preSellAuditRemarks}"/>
					</td>
	           	</tr>
	           	</c:if>
	           	<tr>
	            	<td class="title" width="20%">审核结果<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td">
						<span class="radioClass"><input type="radio" name="designAuditStatus" value="2" <c:if test="${activityNewCustom.designAuditStatus == '2' }">checked</c:if> validate="{required:true}" />通过</span>
						<span class="radioClass"><input type="radio" name="designAuditStatus" value="3" <c:if test="${activityNewCustom.designAuditStatus == '3' }">checked</c:if> validate="{required:true}" />驳回</span>
						<span style="margin-left: 10px;">
							<a href="javascript:activityAuditLogList(${activityNewCustom.id})">【查看审核日志】</a>
						</span>
					</td>
	           	</tr>
           	</c:if>
           	<c:if test="${activityStatus == '13' }">
           		<tr>
	            	<td class="title" width="20%">驳回理由</td>
					<td align="left" class="l-table-edit-td">
						<input name="auditRemarks" readonly="readonly" style="width: 798px;" value='<c:if test="${statusAudit == '1' }">${activityNewCustom.operateAuditRemarks }</c:if><c:if test="${statusAudit == '2' }">${activityNewCustom.cooAuditRemarks }</c:if>' />
					</td>
	           	</tr>
           	</c:if>
           	<c:if test="${activityStatus != '13' }">
	           	<tr class="tr-alert-div">
	            	<td class="title" width="20%">驳回理由</td>
					<td align="left" class="l-table-edit-td edit-td">
						<input name="auditRemarks" style="width: 798px;" value='<c:if test="${statusAudit == '1' }">${activityNewCustom.operateAuditRemarks }</c:if><c:if test="${statusAudit == '2' }">${activityNewCustom.cooAuditRemarks }</c:if>' />
						<div id="alert-div">
							<table>
								<tr>
									<td>常用文本：</td>
									<td>【取消】</td>
								</tr>
								<tr>
									<td colspan="2">价格没有优势，请重新调整。</td>
								</tr>
								<tr>
									<td colspan="2">排期已满，请等待数日后再提报。</td>
								</tr>
								<tr>
									<td colspan="2">入口图、海报图设计效果不佳，请重新设计。</td>
								</tr>
								<tr>
									<td colspan="2">促销方式设置不合理，请重新设置。</td>
								</tr>
							</table>
						</div>
					</td>
	           	</tr>
           	</c:if>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<c:if test="${statusAudit == '2' and activityNewCustom.status != '4' }"><!-- 排期审核 -->
						<input type="submit" class="l-button l-button-submit" value="提交"/> 
					</c:if>
					<c:if test="${statusAudit != '2' }">
						<input type="submit" class="l-button l-button-submit" value="提交"/> 
					</c:if>
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>