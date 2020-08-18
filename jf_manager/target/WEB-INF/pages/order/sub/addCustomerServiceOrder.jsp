<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
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
</style>

<script type="text/javascript">
	$(function(){
		
		if('${subOrderStatus }' == '1') {
			$("#proStatusTr2").hide();
			$("#proStatusTr3").hide();
		}else {
			$("#proStatusTr1").hide();
			$("#proStatusTr2").hide();
			$("#payAmountTr").hide();
		}
		
		$.metadata.setType("attr", "validate");
		var v = $("#form1").validate({
			errorPlacement: function (lable, element) {   
	        	var elementType = $(element).attr("type");
	        	if($(element).hasClass("l-text-field")){
	        		$(element).parent().ligerTip({
						content : lable.html(),width: 100
					});
	        	}else if('radio'==elementType){
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else{
	        		$(element).ligerTip({
						content : lable.html(),width: 100
					});
	        	}
			},
			success: function (lable,element){
				lable.ligerHideTip();
				lable.remove();
			},
			submitHandler: function (form){
				// 退款理由
				var serviceType = $("[name='serviceType']:checked").val();
				var reason = '';
				if(serviceType == 'B') {
					reason = $("#reason2").val();
				}else if(serviceType == 'C') {
					reason = $("#reason3").val();
				}else {
					reason = $("#reason1").val();
				}
				if(reason == '') {
					commUtil.alertError("退款理由不能为空！");
					return;
				}
				$("#reason").val(reason);
				// 联系电话
				var reg = /\d/g;
				var contactPhone = $("#contactPhone").val();
				if(!reg.test(contactPhone)) {
					commUtil.alertError("请输入正确格式的联系电话！");
					return;
				}
				// 上传图片
            	var pictures = [];
        		var lis = $(".upload_image_list").find("li");
        		lis.each(function(index, item) {
        			var path = $("img", item).attr("path");
        			var def = ($(".def", item).length == 1 ? "1" : "0");
        			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
        			pictures.push(pic);
        		});
        		if(pictures.length > 0) {
        			$("#customerServicePic").val(JSON.stringify(pictures));
        		}
        		// 售后说明
        		if($("#remarks").val().length > 120) {
        			commUtil.alertError("售后说明不得超过120个字符！");
					return;
        		}
        		formSubmit();
			}
		}); 
		
		
		
	});

	
	function formSubmit() {
		$.ajax({
			url : "${pageContext.request.contextPath}/order/afterService/saveCustomerServiceOrder.shtml",
			type : 'POST',
			dataType : 'json',
			data : $("#form1").serialize(),
			success : function(data) {
				if ("0000" == data.returnCode) {
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
	
	function ajaxFileUploadLogo(){
		var file = document.getElementById("logoPicFile").files[0];  
        if(!/image\/\w+/.test(file.type)){  
        	commUtil.alertError("图片格式不正确！");
            return;
        }
        var lis = $(".upload_image_list").find("li");
		if(lis.length < 3) {
	        ajaxFileUpload();
		}else {
			commUtil.alertError("最多上传3张图片");
		}
	}
	
	function ajaxFileUpload() {
        $.ajaxFileUpload({
			url: contextPath + '/service/common/ajax_upload.shtml?fileType=1',
			secureuri: false,
			fileElementId: "logoPicFile",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					uploadImageList.addImage(contextPath + "/file_servelt", result.FILE_PATH);
				} else {
					commUtil.alertError(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				commUtil.alertError("服务异常");
			}
		});
	}
	
	function updateServiceType(serviceType) {
		if(serviceType == 'C') {
			$("#proStatusTr2").hide();
			$("#proStatus2 option:first").attr("selected", "selected");
			$("#proStatusTr3").show();
			$("#payAmountTr").hide();
		}
		if(serviceType == 'B') {
			$("#proStatusTr2").show();
			$("#proStatusTr3").hide();
			$("#payAmountTr").show();
			$("#proStatus3 option:first").attr("selected", "selected");
		}
	}
	
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" >
		<input type="hidden" id="subOrderStatus" name="subOrderStatus" value="${subOrderStatus }"/>
		<input type="hidden" id="customerServiceOrderId" name="customerServiceOrderId" value="${customerServiceOrderId }"/>
		<input type="hidden" id="reason" name="reason" value=""/>
		<input id="customerServicePic" name="customerServicePic" type="hidden" value="">
		<table class="gridtable">
			<c:if test="${subOrderStatus != '1' }">
				<tr class="serviceTypeTr">
					<td class="title">售后类型</td>
					<td align="left" class="l-table-edit-td">
						<c:forEach var="serviceType" items="${serviceTypeList }">
							<c:if test="${serviceType.statusValue != 'A' and serviceType.statusValue != 'D' }">
								<span class="radioClass"><input type="radio" onclick="updateServiceType(this.value);" value="${serviceType.statusValue }" <c:if test="${serviceType.statusValue == 'C' }">checked</c:if> name="serviceType">${serviceType.statusDesc}</span>
							</c:if>
						</c:forEach>
					</td>
				</tr>
			</c:if>
			<tr id="proStatusTr1">
				<td class="title">退款理由<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="reason1" style="width: 155px;" >
						<option value="" selected >请选择...</option>
						<c:forEach items="${reasonList }" var="reason">
							<c:if test="${fn:contains(reason.statusValue, 'A') }">
								<option value="${reason.statusValue }" >${reason.statusDesc }</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
			</tr>	
			<tr id="proStatusTr2">
				<td class="title">退款理由<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="reason2" style="width: 155px;" >
						<option value="" selected >请选择...</option>
						<c:forEach items="${reasonList }" var="reason">
							<c:if test="${fn:contains(reason.statusValue, 'B') }">
								<option value="${reason.statusValue }" >${reason.statusDesc }</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr id="proStatusTr3">
				<td class="title">退款理由<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="reason3" style="width: 155px;" >
						<option value="" selected >请选择...</option>
						<c:forEach items="${reasonList }" var="reason">
							<c:if test="${fn:contains(reason.statusValue, 'C') }">
								<option value="${reason.statusValue }" >${reason.statusDesc }</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr id="payAmountTr">
				<td class="title">退款金额</td>
				<td align="left" class="l-table-edit-td">
					<input type="text" disabled="disabled" value="${payAmount }" />
				</td>
			</tr>
			<tr>
				<td class="title">联系电话<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" id="contactPhone" name="contactPhone" value="" />
				</td>
			</tr>
			<tr>
               <td  class="title" width="20%">上传图片</td>
               <td align="left" class="l-table-edit-td" >
	    			<div style="margin: 10px;">
	    				<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();" />
						<a href="javascript:void(0);" style="width:30%;">上传图片</a><span style="color:#B0B0B0;">（最多上传3张图片）</span>
	    			</div>
               		<t:imageList name="pictures" list="${customerServicePic }" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
               </td>
           </tr>
           <tr>
				<td class="title">售后说明</td>
				<td align="left" class="l-table-edit-td">
					<textarea rows="3" cols="50" id="remarks" name="remarks"></textarea>
				</td>
			</tr>
			<tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
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
