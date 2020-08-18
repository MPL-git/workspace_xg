<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
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
	$("#recBeginDate").ligerDateEditor({
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	$("#recEndDate").ligerDateEditor({
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});

});

function ajaxPicFileUploadImg(statusImg) {
	var file = document.getElementById(statusImg).files[0];
	if(!/image\/(GIF|gif)$/.test(file.type)){
    	commUtil.alertError("图片格式不正确！");
        return;
    }
    var size = file.size;
    if(size > 1024*1024 ) {
    	commUtil.alertError("图片不能大于1024Kb，请重新上传！");
        return;
    }
    var reader = new FileReader();  
    reader.onload = function(e) {
    	var image = new Image();
    	image.onload = function() {
    		if(this.width != '660' && this.height != '660') {
    			commUtil.alertError("图片像素不是660x660像素！");
        	}else{
        		ajaxFileUploadLogo(statusImg);
        	}
        };
        image.src = e.target.result;
    };
    reader.readAsDataURL(file);  
} 

function ajaxFileUploadLogo() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml',
		secureuri: false,
		fileElementId: "logoPicFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#logoPic").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
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


function commitSave(){
	    var pic = document.getElementById("pic");
		var title = document.getElementById("title");
		var limitRecCount=document.getElementById("limitRecCount");
		var includeIntegralPercent=document.getElementById("includeIntegralPercent");
		var includeProductCouponPercent=document.getElementById("includeProductCouponPercent");
		var includeProductCoupon = $("input:radio[name='includeProductCoupon']:checked").val();
		var includeIntegral = $("input:radio[name='includeIntegral']:checked").val();
		var includeIntegralMin=document.getElementById("includeIntegralMin");
		var includeIntegralMax=document.getElementById("includeIntegralMax");
		var includeSale = $("input:radio[name='includeSale']:checked").val();
		var includeSalePercent=document.getElementById("includeSalePercent");
		var includePlatformCoupon = $("input:radio[name='includePlatformCoupon']:checked").val();
		var includePlatformCouponPercent=document.getElementById("includePlatformCouponPercent");
		var recBeginDate=document.getElementById("recBeginDate");
		var recEndDate=document.getElementById("recEndDate");
		var reg = /^[0-9]+\.?[0-9]*$/;
		
		if ($.trim(title.value)=="") {
			commUtil.alertError("红包雨名称不能为空！");
			return;
		}
		
		if(includeIntegral==null){
			commUtil.alertError("请选择是否支持积分。");
			return;
		}
		
		if(includeIntegral=="1" && ($.trim(includeIntegralPercent.value)=="" || $.trim(includeIntegralMin.value)=="" || $.trim(includeIntegralMax.value)=="")){
			commUtil.alertError("请填写积分百分比和积分范围。");
			return;
		}
		
		if(Number(includeIntegralMin.value)> Number(includeIntegralMax.value)){
			commUtil.alertError("起始积分不能大于结束积分。");
			return;
		}
		
		if(includeIntegral=="1" && (!/^[1-9]\d*$/.test($.trim(includeIntegralMin.value)) || !/^[1-9]\d*$/.test($.trim(includeIntegralMax.value)))){
			commUtil.alertError("积分范围请填写正整数。");
			return;
		}
		if (includeIntegral=="1" && !reg.test($.trim(includeIntegralPercent.value))) {
			commUtil.alertError("积分百分比请填写正确数值。");
			return;
		}
		if (includeIntegral=="1" && Number(includeIntegralPercent.value)>100) {
			commUtil.alertError("积分百分比例不能大于100%！");
			return;
		}
		
		if(includeProductCoupon==null){
			commUtil.alertError("请选择是否支持商品劵。");
			return;
		}
		if (includeProductCoupon=="1" && $.trim(includeProductCouponPercent.value)=="") {
			commUtil.alertError("请填写商品劵百分比！");
			return;
		}
		if (includeProductCoupon=="1" && !reg.test(includeProductCouponPercent.value)) {
			commUtil.alertError("商品劵百分比请输正確數值！");
			return;
		}
		
		if (includeProductCoupon=="1" && Number(includeProductCouponPercent.value)>100) {
			commUtil.alertError("商品劵百分比例不能大于100%！");
			return;
		}
		if (includePlatformCoupon==null) {
			commUtil.alertError("请选择是否支持平台劵。");
			return;
		}	
		if (includePlatformCoupon=="1" && $.trim(includePlatformCouponPercent.value)=="") {
			commUtil.alertError("请填写平台劵百分比！");
			return;
		}
		if (includePlatformCoupon=="1" && Number(includePlatformCouponPercent.value)>100) {
			commUtil.alertError("平台券百分比例不能大于100%！");
			return;
		}
		if(includeSale==null){
			commUtil.alertError("请选择是否支持特卖劵。");
			return;
		}
		
		if (includeSale=="1" && $.trim(includeSalePercent.value)=="") {
			commUtil.alertError("请填写特卖劵百分比！");
			return;
		}
		
		if (includeSale=="1" && !reg.test($.trim(includeSalePercent.value))) {
			commUtil.alertError("特卖百分比请填写正确数值。");
			return;
		}
		
		if (includeSale=="1" && Number(includeSalePercent.value)>100) {
			commUtil.alertError("特卖百分比例不能大于100%！");
			return;
		}
		
		/* if (Number(includeProductCouponPercent.value)+Number(includeIntegralPercent.value)+Number(includeSalePercent.value)>100) {
			commUtil.alertError("商品劵和积分劵及特卖劵百分比总和不能大于100%！");
			return;
		} */
		
		if ($.trim(pic.value)=="") {
			commUtil.alertError("弹窗图片不能为空！");
			return;
		}

		if($.trim(limitRecCount.value)==""){
			commUtil.alertError("红包雨上限个数不能为空。");
			return;
		}
		
		if($.trim(limitRecCount.value)!="" && !/^[1-9]\d*$/.test($.trim(limitRecCount.value))){
			commUtil.alertError("红包雨上限个请输入整数。");
			return;
		}
		
		if($.trim(recBeginDate.value)==""){
			commUtil.alertError("有效开始时间不能为空。");
			return;
		}
		if($.trim(recEndDate.value)==""){
			commUtil.alertError("有效结束时间不能为空。");
			return;
		}
		if($.trim(recBeginDate.value)>=$.trim(recEndDate.value)){
			commUtil.alertError("有效开始时间必须小于有效结束时间");
			return;
		}
		
		var dataJson = $("#form1").serializeArray();
		$.ajax({
			method: 'POST',
			url: '${pageContext.request.contextPath}/couponRain/addcouponRainSetup.shtml',
			data: dataJson,
			dataType: 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success:function(data){
				if(data.returnCode == '0000'){
					commUtil.alertSuccess("保存成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					commUtil.alertError(data.returnMsg); 
				}
			},
			error:function(){
				commUtil.alertError("请求失败"); 
			}
		});	
};

</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/couponRain/addcouponRainSetup.shtml">
		<input type="hidden" id="id" name="id" value="${couponRainSetup.id}"/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">红包雨名称 <span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="title" name="title" type="text" style="width:160px;" value="${couponRainSetup.title}" maxlength="30" <c:if test="${not empty couponRainSetup.id}">disabled="disabled"</c:if>/>
				</td>
			</tr>
	
			<tr>
				<td colspan="1" class="title">是否支持积分<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
						<input type="radio" id="includeIntegral" name="includeIntegral" value="0" <c:if test="${couponRainSetup.includeIntegral==0}">checked="checked"</c:if>>不支持&nbsp;
						<input type="radio" id="includeIntegral" name="includeIntegral" value="1" <c:if test="${couponRainSetup.includeIntegral==1}">checked="checked"</c:if>>支持&nbsp;
						<input type="text"  id="includeIntegralPercent" name="includeIntegralPercent"  value="${includeIntegralPercent}" style="width: 66px;" maxlength="4"/>&nbsp;%
						&nbsp;&nbsp;&nbsp;<input type="text"  id="includeIntegralMin" name="includeIntegralMin"  value="${couponRainSetup.includeIntegralMin}" style="width: 66px;"/>&nbsp;~
						<input type="text"  id="includeIntegralMax" name="includeIntegralMax"  value="${couponRainSetup.includeIntegralMax}" style="width: 66px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">是否支持商品劵<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
						<input type="radio" id="includeProductCoupon" name="includeProductCoupon" value="0"  <c:if test="${couponRainSetup.includeProductCoupon==0}">checked="checked"</c:if>/>不支持&nbsp;
						<input type="radio" id="includeProductCoupon" name="includeProductCoupon" value="1"  <c:if test="${couponRainSetup.includeProductCoupon==1}">checked="checked"</c:if>/>支持&nbsp;
						<input type="text"  id="includeProductCouponPercent" name="includeProductCouponPercent" value="${includeProductCouponPercent}" style="width: 66px;" maxlength="4"/>&nbsp;%
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">是否支持平台劵<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
						<input type="radio" id="includePlatformCoupon" name="includePlatformCoupon" value="0"  <c:if test="${couponRainSetup.includePlatformCoupon==0}">checked="checked"</c:if>/>不支持&nbsp;
						<input type="radio" id="includePlatformCoupon" name="includePlatformCoupon" value="1"  <c:if test="${couponRainSetup.includePlatformCoupon==1}">checked="checked"</c:if>/>支持&nbsp;
						<input type="text"  id="includePlatformCouponPercent" name="includePlatformCouponPercent" value="${includePlatformCouponPercent}" style="width: 66px;" maxlength="4"/>&nbsp;%
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">是否支持特卖劵<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
						<input type="radio" id="includeSale" name="includeSale" value="0"  <c:if test="${couponRainSetup.includeSale==0}">checked="checked"</c:if>/>不支持&nbsp;
						<input type="radio" id="includeSale" name="includeSale" value="1"  <c:if test="${couponRainSetup.includeSale==1}">checked="checked"</c:if>/>支持&nbsp;
						<input type="text"  id="includeSalePercent" name="includeSalePercent" value="${includeSalePercent}" style="width: 66px;" maxlength="4"/>&nbsp;%
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">红包上限个数<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="limitRecCount" name="limitRecCount" value="${couponRainSetup.limitRecCount}"/>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">红包类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="radio" id="redEnvelopeType" name="redEnvelopeType" value="P" <c:if test="${couponRainSetup.redEnvelopeType=='P'}">checked="checked"</c:if>checked/>普通红包&nbsp;
					<input type="radio" id="redEnvelopeType" name="redEnvelopeType" value="S" <c:if test="${couponRainSetup.redEnvelopeType=='S'}">checked="checked"</c:if>/>圣诞红包&nbsp;						
				</td>
			</tr>
			<tr>
               <td  class="title" width="20%">弹窗图片<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="logoPic" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${couponRainSetup.pic}"></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxPicFileUploadImg('logoPicFile');" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    				<span style="color: gray;">(图片尺寸不超过660*660px，大小不超过1024kb)</span>
	    			</div>
	    			<input id="pic" name="pic" type="hidden" value="${couponRainSetup.pic}">
               </td>
           </tr>
			<tr>
				<td colspan="1" class="title">有效期开始时间<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="recBeginDate" name="recBeginDate" value="<fmt:formatDate value="${couponRainSetup.recBeginDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">有效期结束时间<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="recEndDate" name="recEndDate" value="<fmt:formatDate value="${couponRainSetup.recEndDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit"  id="Button1" style="float:left;" class="l-button l-button-submit" value="提交" onclick="commitSave();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
