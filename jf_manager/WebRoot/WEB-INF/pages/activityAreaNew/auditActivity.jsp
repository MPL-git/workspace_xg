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
	        	}
        		if(operateAuditStatus == '2' || cooAuditStatus == '2' ) {
	        		if(!validateActivity('${statusAudit }', $("#id").val())) {
	        			return;
	        		}
        		}
		        form.submit();
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
				} else {
					commUtil.alertError(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				commUtil.alertError("服务异常！");
			}
		});
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
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" method="post" enctype="multipart/form-data" id="form1" action="${pageContext.request.contextPath}/activityAreaNew/auditActivity.shtml" >
		<input type="hidden" id="entryPic" name="entryPic" value="${activityNewCustom.entryPic }" />
		<input type="hidden" id="posterPic" name="posterPic" value="${activityNewCustom.posterPic }" />
		<input type="hidden" id="statusAudit" name="statusAudit" value="${statusAudit }" />
		<input type="hidden" id="id" name="id" value="${activityNewCustom.id }" />
		<table class="gridtable">
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
           	<c:if test="${statusAudit == '1' }"><!-- 专员审核 -->
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
						<input type="submit" class="l-button l-button-submit" value="提交" /> 
					</c:if>
					<c:if test="${statusAudit != '2' }">
						<input type="submit" class="l-button l-button-submit" value="提交" /> 
					</c:if>
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>