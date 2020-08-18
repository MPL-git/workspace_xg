<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
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
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	

<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />	
	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}



.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}
 .l-text-readonly{
 border-bottom: none !important;
 }
 td img{
 width: 60px;
 height: 40px;
 }
 td ul li{
	display: inline-block;
 }
</style>
<script type="text/javascript">
Date.prototype.format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} ;


var logoPic_viewer; 
var viewerPictures;

<c:forEach var="mchtBrandAptitudeCustom" items="${mchtBrandAptitudeCustoms}" varStatus="index">
	var aptitudePic_viewer${index.index};
</c:forEach>
$(function ()  {
	logoPic_viewer = new Viewer(document.getElementById('logoPic_viewer'), {});
	
	<c:forEach var="mchtBrandAptitudeCustom" items="${mchtBrandAptitudeCustoms}" varStatus="index">
	aptitudePic_viewer${index.index} = new Viewer(document.getElementById('aptitudePic_viewer${index.index}'), {});
	</c:forEach>
	platformAuthPic_viewer = new Viewer(document.getElementById('platformAuthPic_viewer'), {});
	mchtBrandInvoicePic_viewer = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer'), {});
	mchtBrandInspectionPic_viewer = new Viewer(document.getElementById('mchtBrandInspectionPic_viewer'), {});
	mchtBrandOtherPics_viewer = new Viewer(document.getElementById('mchtBrandOtherPics_viewer'), {});
	aptitudePic_viewer = new Viewer(document.getElementById('aptitudePic_viewer'), {});

	if('${view }' == "1") {
		$.metadata.setType("attr", "validate");
		
	    //当运营状态=正常时，但审核状态！=通过时。提时：对不起，审核状态未通过，运营状态不能为正常。
	    $.validator.addMethod("statusCheck", function(value, element) {  
	    	if(!$("input[type=radio][name='auditStatus'][value=3]").attr("checked")&&$("#status").val()=="1"){
	 			return false;
	 		}else{
	 			return true;
	 		}
	    }, "审核状态未通过，运营状态不能为正常");
	    
	    //驳回原因必填
	    $.validator.addMethod("auditRemarksRequired", function(value, element) {  
	    	if($("input[type=radio][name='auditStatus'][value=4]").attr("checked")&&$.trim($("#auditRemarks").val())==""){
	 			return false;
	 		}else{
	 			return true;
	 		}
	    }, "请注明驳回原因");
	    
	    //非未完善状态必填
	    $.validator.addMethod("perfectRequired", function(value, element) { 
	    	
	    	if(($("input[type=radio][name='auditStatus'][value=1]").attr("checked")||$("input[type=radio][name='auditStatus'][value=2]").attr("checked")||$("input[type=radio][name='auditStatus'][value=3]").attr("checked")||$("input[type=radio][name='auditStatus'][value=4]").attr("checked"))&&$(element).attr("type")=="radio"){
	  		   var radioName=$(element).attr("name");
			   var hasSelect=false;
			   $($("input[type=radio][name="+radioName+"]")).each(function(){
				    if($(this).attr("checked")){
				    	hasSelect=true;
				    }
				  });
			   return hasSelect;
	    	}
	    	
	    	if(($("input[type=radio][name='auditStatus'][value=1]").attr("checked")||$("input[type=radio][name='auditStatus'][value=2]").attr("checked")||$("input[type=radio][name='auditStatus'][value=3]").attr("checked")||$("input[type=radio][name='auditStatus'][value=4]").attr("checked"))&&value==""){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    	
	    }, "该字段不能为空");
		
	    var v = $("#form1").validate({
	    	
	        errorPlacement: function (lable, element)
	        {  
	        	var elementType=$(element).attr("type");
	
	        	if($(element).hasClass("l-text-field")){
	        		$(element).parent().ligerTip({
						content : lable.html(),width: 100
					});
	        	}
	        	else if('radio'==elementType){
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else if('hidden'==elementType){
	        		$(element).closest('td').find("div").ligerTip({
						content : lable.html(),width: 100
					});  
	        	}
	        	
	        	else{
	        		$(element).ligerTip({
						content : lable.html(),width: 100
					});
	        	}
	        	
	        	
	        },
	        success: function (lable,element)
	        {
	            lable.ligerHideTip();
	            lable.remove();
	        },
	        submitHandler: function (form)
	        {   
	        	mchtProductBrandSaveSubmit();
	        }
	    });   
	}
	
	            
});    

//图片格式验证
function ajaxFileUploadImg(_this) {
	var file = document.getElementById(_this.id).files[0]; 
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
        	ajaxFileUpload(_this);
        };
        image.src = e.target.result;
    }
    reader.readAsDataURL(file);  
}

function ajaxFileUpload(_this) {
	var id = $(_this).attr("id");
	var data_value = $(_this).attr("data_value");
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: id,
		dataType: 'json',
		success: function(result, status) {
			debugger;
			if(result.RESULT_CODE == 0) {
				$("#mcht"+id).append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');
				$(".del").live('click',function(){
					$(this).closest("li").remove();
				});

				<c:forEach var="mchtBrandAptitudeCustom" items="${mchtBrandAptitudeCustoms}" varStatus="index">
				if(data_value == 'aptitudePic_viewer${index.index}'){
					aptitudePic_viewer${index.index}.destroy();
					aptitudePic_viewer${index.index} = new Viewer(document.getElementById('aptitudePic_viewer${index.index}'), {});
				}
				</c:forEach>
				if(data_value == 'aptitudePic_viewer'){
					aptitudePic_viewer.destroy();
					aptitudePic_viewer = new Viewer(document.getElementById('aptitudePic_viewer'), {});
				}

				//对viewer进行重新赋值
				if(id == 'PlatformAuthPictures'){
					platformAuthPic_viewer.destroy();
					platformAuthPic_viewer = new Viewer(document.getElementById('platformAuthPic_viewer'), {});
				}else if(id == 'BrandInvoicePictures'){
					mchtBrandInvoicePic_viewer.destroy();
					mchtBrandInvoicePic_viewer = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer'), {});
				}else if(id == 'BrandInspectionPictures'){
					mchtBrandInspectionPic_viewer.destroy();
					mchtBrandInspectionPic_viewer = new Viewer(document.getElementById('mchtBrandInspectionPic_viewer'), {});
				}else if(id == 'BrandOtherPictures'){
					mchtBrandOtherPics_viewer.destroy();
					mchtBrandOtherPics_viewer = new Viewer(document.getElementById('mchtBrandOtherPics_viewer'), {});
				}
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
}

function mchtProductBrandSaveSubmit(){
	var popCommissionRate = $("#popCommissionRate").val();
	if(!popCommissionRate){
		commUtil.alertError("技术服务费不能为空");
		return false;
	}
	var reg=/^0\.\d+$/;
	if(!reg.test(popCommissionRate)){
		commUtil.alertError("技术服务费只能是大于0小于1的小数");
		return false;
	}

	var zsAuditStatus = $("input[name='zsAuditStatus']:checked").val();
	if(!zsAuditStatus){
		commUtil.alertError("请选择招商确认结果");
		return false;
	}
	var mchtPlatformAuthPictures = [];
	var lis = $("#mchtPlatformAuthPictures").find("li");
	lis.each(function(index, item) {
		var path = $("img", item).attr("path");
		var def = ($(".def", item).length == 1 ? "1" : "0");
		var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
		mchtPlatformAuthPictures.push(pic);
	});
	
	var mchtBrandInvoicePictures = [];
	var lis = $("#mchtBrandInvoicePictures").find("li");
	lis.each(function(index, item) {
		var path = $("img", item).attr("path");
		var def = ($(".def", item).length == 1 ? "1" : "0");
		var pic = {"picPath": path, "isPrimary": def, "picOrder": index + 1};
		mchtBrandInvoicePictures.push(pic);
	});
	var mchtBrandInspectionPictures = [];
	var lis = $("#mchtBrandInspectionPictures").find("li");
	lis.each(function(index, item) {
		var path = $("img", item).attr("path");
		var def = ($(".def", item).length == 1 ? "1" : "0");
		var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
		mchtBrandInspectionPictures.push(pic);
	});
	
	var mchtBrandOtherPictures = [];
	var lis = $("#mchtBrandOtherPictures").find("li");
	lis.each(function(index, item) {
		var path = $("img", item).attr("path");
		var def = ($(".def", item).length == 1 ? "1" : "0");
		var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
		mchtBrandOtherPictures.push(pic);
	});
	
	//TODO 商标注册证或受理通知书
	var mchtBrandAptitudeArray = [];
	$("table[name='mchtBrandAptitudeTable']").each(function(idx){
		debugger;
		var mchtBrandAptitudeId = $(this).attr("mchtBrandAptitudeId");
		var mchtBrandAptitudePictures = [];
		$(this).find("ul").find("li").each(function(index, item) {
			var path = $("img", item).attr("path");
			var def = ($(".def", item).length == 1 ? "1" : "0");
			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
			if(path){
				mchtBrandAptitudePictures.push(pic);
			}
		});
		mchtBrandAptitudeArray.push({
			"mchtBrandAptitudeId":mchtBrandAptitudeId,
			"mchtBrandAptitudePics":JSON.stringify(mchtBrandAptitudePictures)
		});
	});
	
	var dataJson = $("#form1").serializeArray();
	dataJson.push({"name":"mchtPlatformAuthPics","value":JSON.stringify(mchtPlatformAuthPictures)});
	dataJson.push({"name":"mchtBrandInvoicePics","value":JSON.stringify(mchtBrandInvoicePictures)});
	dataJson.push({"name":"mchtBrandInspectionPics","value":JSON.stringify(mchtBrandInspectionPictures)});
	dataJson.push({"name":"mchtBrandOtherPics","value":JSON.stringify(mchtBrandOtherPictures)});
	dataJson.push({"name":"mchtBrandAptitudeJsonStr","value":JSON.stringify(mchtBrandAptitudeArray)});
	
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtProductBrandSaveSubmit.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : dataJson,
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				commUtil.alertSuccess("提交成功");
				parent.location.reload();
				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function getGridOptions(){
    var options = {
        columns: [
        { display: 'ID', name: 'id', minWidth: 100, width: 100 },
        { display: '品牌', name: 'name', minWidth: 100, width: 100 }
        ], 
        switchPageSizeApplyComboBox: false,
        url: '${pageContext.request.contextPath}/productBrand/selectDatalist.shtml',
        pageSize: 10, 
        checkbox: false
    };
    return options;
}

function aptitudePicUpload() {
	$.ajaxFileUpload({
		url : contextPath + '/service/common/ajax_upload.shtml',
		secureuri : false,
		fileElementId : "mchtBrandAptitudePic",
		dataType : 'json',
		success : function(result, status) {
			if (result.RESULT_CODE == 0) {
				uploadImageList.addImage(contextPath + "/file_servelt",
						result.FILE_PATH,"aptitudePics");
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error : function(result, status, e) {
			alert("服务异常");
		}
	});

}

function platformAuthPicUpload() {
	$.ajaxFileUpload({
		url : contextPath + '/service/common/ajax_upload.shtml',
		secureuri : false,
		fileElementId : "mchtPlatformAuthPic",
		dataType : 'json',
		success : function(result, status) {
			if (result.RESULT_CODE == 0) {
				uploadImageList.addImage(contextPath + "/file_servelt",
						result.FILE_PATH,"platformAuthPics");
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error : function(result, status, e) {
			alert("服务异常");
		}
	});

}

function toEditDate(id,type){
	$.ligerDialog.open({
		height: 400,
		width: 600,
		title: "修改有效期",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/toEditDate.shtml?id="+ id +"&type=" + type,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type=hidden type="hidden" name="id" value="${mchtProductBrand.id}">
	<input type="hidden" name="mchtId" value="${mchtProductBrand.mchtId}">
	<input type="hidden" name="view" value="${view}">
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">品牌名称 </td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<span>${mchtProductBrand.productBrandName}</span>
				</td>	
			</tr>
			
			<tr>
				<td  colspan="1" class="title">资质类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<span>${mchtProductBrand.aptitudeTypeDesc}</span>
			</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">LOGO图片</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<ul  class="docs-pictures clearfix td-pictures" id="logoPic_viewer" >
					<li>
					<c:choose>
					<c:when test="${not empty mchtProductBrand.logo || mchtProductBrand.logo!=''}">
					<img  src="${pageContext.request.contextPath}/file_servelt${mchtProductBrand.logo}">
					</c:when>
					<c:otherwise>
					<img  src="${pageContext.request.contextPath}/file_servelt${mchtProductBrand.brandLogo}">
					</c:otherwise>
					</c:choose>
					</li>
				</ul>
			</td>
			</tr>
			
			<tr>
					<td  colspan="1" class="title">商标注册证或受理通知书</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${not empty mchtBrandAptitudeCustoms}">
					<c:forEach var="mchtBrandAptitudeCustom" items="${mchtBrandAptitudeCustoms}" varStatus="index">
						<table class="gridtable" style="margin-top: 10px;" name="mchtBrandAptitudeTable" mchtBrandAptitudeId="${mchtBrandAptitudeCustom.id}">
							<tbody>
								<tr>
									<td  colspan="1" class="title" style="width:150px;">商标注册证号</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										${mchtBrandAptitudeCustom.certificateNo}
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">本商标注册证相关文件1</td>
									<td  colspan="3" align="left" class="l-table-edit-td" id="aptitudePic_viewer${index.index}">
							    		<t:imageList name="mchtBrandAptitudePicstures${index.index}" list="${mchtBrandAptitudeCustom.mchtBrandAptitudePics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
										<div style="float: left;height: 105px;margin: 10px;">
							    			<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandAptitudePicstures${index.index}" data_value="aptitudePic_viewer${index.index}" name="file" onchange="ajaxFileUploadImg(this);" /> 
											<input type="button" style="width: 70px;" value="上传图片" /> 
							    		</div>
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">商家注册证有效期</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										<fmt:formatDate value="${mchtBrandAptitudeCustom.aptitudeExpDate}" pattern="yyyy-MM-dd"/>
										<c:if test="${contactType == 7 || sessionScope.USER_SESSION.staffID==1}">
											&nbsp;&nbsp;<a href="javascript:;" onclick="toEditDate(${mchtBrandAptitudeCustom.id},0);">【修改有效期】</a>
										</c:if>
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
					</c:if>
					<c:if test="${empty mchtBrandAptitudeCustoms}">
						<table class="gridtable" style="margin-top: 10px;" name="mchtBrandAptitudeTable" mchtBrandAptitudeId="0">
							<tbody>
								<tr>
									<td  colspan="1" class="title" style="width:150px;">商标注册证号</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">本商标注册证相关文件2</td>
									<td  colspan="3" align="left" class="l-table-edit-td" id="aptitudePic_viewer">
							    		<t:imageList name="mchtBrandAptitudePicstures" list="${mchtBrandAptitudePics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
										<div style="float: left;height: 105px;margin: 10px;">
							    			<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandAptitudePicstures" data_value="aptitudePic_viewer" name="file" onchange="ajaxFileUploadImg(this);" />
											<input type="button" style="width: 70px;" value="上传图片" /> 
							    		</div>
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">商家注册证有效期</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										
									</td>
								</tr>
							</tbody>
						</table>
					</c:if>	
					</td>
				</tr>				
			
			<tr>
				<td  colspan="1" class="title">销售授权书</td>
				<td  colspan="3" align="left" class="l-table-edit-td" id="platformAuthPic_viewer">
			    	<t:imageList name="mchtPlatformAuthPictures" list="${mchtPlatformAuthPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 105px;margin: 10px;">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="PlatformAuthPictures" data_value="platformAuthPic_viewer" name="file" onchange="ajaxFileUploadImg(this);" />
						<input type="button" style="width: 70px;" value="上传图片" />
					</div>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">授权有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtProductBrand.platformAuthExpDate}" pattern="yyyy-MM-dd"/>
					<c:if test="${contactType == 7 || sessionScope.USER_SESSION.staffID==1}">
						&nbsp;&nbsp;<a href="javascript:;" onclick="toEditDate(${mchtProductBrand.id},1);">【修改有效期】</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">进货发票</td>
				<td  colspan="3" align="left" class="l-table-edit-td" id="mchtBrandInvoicePic_viewer">
			    	<t:imageList name="mchtBrandInvoicePictures" list="${mchtBrandInvoicePics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 105px;margin: 10px;">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandInvoicePictures" data_value="mchtBrandInvoicePic_viewer" name="file" onchange="ajaxFileUploadImg(this);" /> 
						<input type="button" style="width: 70px;" value="上传图片" />
					</div>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">质检报告/检疫报告</td>
				<td  colspan="3" align="left" class="l-table-edit-td" id="mchtBrandInspectionPic_viewer">
			    	<t:imageList name="mchtBrandInspectionPictures" list="${mchtBrandInspectionPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 105px;margin: 10px;">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandInspectionPictures" data_value="mchtBrandInspectionPic_viewer" name="file" onchange="ajaxFileUploadImg(this);" /> 
						<input type="button" style="width: 70px;" value="上传图片" />
					</div>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">报告有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtProductBrand.inspectionExpDate}" pattern="yyyy-MM-dd"/>
					<c:if test="${contactType == 7 || sessionScope.USER_SESSION.staffID==1}">
						&nbsp;&nbsp;<a href="javascript:;" onclick="toEditDate(${mchtProductBrand.id},2);">【修改有效期】</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">其他资质<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td" id="mchtBrandOtherPics_viewer">
			    	<t:imageList name="mchtBrandOtherPictures" list="${mchtBrandOtherPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 105px;margin: 10px;">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandOtherPictures" data_value="mchtBrandOtherPics_viewer" name="file" onchange="ajaxFileUploadImg(this);" /> 
						<input type="button" style="width: 70px;" value="上传图片" />
					</div>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">其他资质有效期</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtProductBrand.otherExpDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">品牌经营的类目</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<table class="gridtable">
						<tbody>
							<tr>
								<td class="title">一级类目</td>
								<td class="title">二级类目</td>
								<td class="title">三级类目</td>
							</tr>
							<c:forEach var="mchtBrandProductTypeCustom" items="${mchtBrandProductTypeCustoms}">
								<tr>
									<td>${mchtBrandProductTypeCustom.firstProductTypeName}</td>
									<td>${mchtBrandProductTypeCustom.secondProductTypeName}</td>
									<td>${mchtBrandProductTypeCustom.thirdProductTypeNames}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>			
			
			<tr>
				<td  colspan="1" class="title">技术服务费<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">

					<input id="popCommissionRate" validate="{required:true,number:true}"
						   name="popCommissionRate" type="text" value="${mchtProductBrand.popCommissionRate }"
						   style="float:left;"/>

				</td>
			 </tr>
			 
			<tr>
				<td  colspan="1" class="title">招商确认结果<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<c:if test="${sessionScope.USER_SESSION.staffID==1}">
					<input type="radio" name="zsAuditStatus" value="2" <c:if test="${mchtProductBrand.zsAuditStatus == 2}">checked="checked"</c:if> <c:if test="${view == 3}">disabled="disabled"</c:if>>通过
					<input type="radio" name="zsAuditStatus" value="4" <c:if test="${mchtProductBrand.zsAuditStatus == 4}">checked="checked"</c:if> <c:if test="${view == 3}">disabled="disabled"</c:if>>驳回
					<input type="radio" name="zsAuditStatus" value="5" <c:if test="${mchtProductBrand.zsAuditStatus == 5}">checked="checked"</c:if> <c:if test="${view == 3}">disabled="disabled"</c:if>>不签约
					<input type="radio" name="zsAuditStatus" value="6" <c:if test="${mchtProductBrand.zsAuditStatus == 6}">checked="checked"</c:if> <c:if test="${view == 3}">disabled="disabled"</c:if>>黑名单
				</c:if>
				<c:if test="${contactType==1}">
				<c:if test="${view!=0}">
					<input type="radio" name="zsAuditStatus" value="2" <c:if test="${mchtProductBrand.zsAuditStatus == 2}">checked="checked"</c:if> <c:if test="${view == 3}">disabled="disabled"</c:if>>通过
					<input type="radio" name="zsAuditStatus" value="4" <c:if test="${mchtProductBrand.zsAuditStatus == 4}">checked="checked"</c:if> <c:if test="${view == 3}">disabled="disabled"</c:if>>驳回
					<input type="radio" name="zsAuditStatus" value="5" <c:if test="${mchtProductBrand.zsAuditStatus == 5}">checked="checked"</c:if> <c:if test="${view == 3}">disabled="disabled"</c:if>>不签约
					<input type="radio" name="zsAuditStatus" value="6" <c:if test="${mchtProductBrand.zsAuditStatus == 6}">checked="checked"</c:if> <c:if test="${view == 3}">disabled="disabled"</c:if>>黑名单
				</c:if>
				<c:if test="${view==0}">
					<c:if test="${mchtProductBrand.zsAuditStatus == 2}">通过</c:if>
					<c:if test="${mchtProductBrand.zsAuditStatus == 4}">驳回</c:if>
					<c:if test="${mchtProductBrand.zsAuditStatus == 5}">不签约</c:if>
					<c:if test="${mchtProductBrand.zsAuditStatus == 6}">黑名单</c:if>
				</c:if>
				</c:if>
				<c:if test="${contactType != 1 && sessionScope.USER_SESSION.staffID!=1}">
					<c:if test="${mchtProductBrand.zsAuditStatus == 2}">通过</c:if>
					<c:if test="${mchtProductBrand.zsAuditStatus == 4}">驳回</c:if>
					<c:if test="${mchtProductBrand.zsAuditStatus == 5}">不签约</c:if>
					<c:if test="${mchtProductBrand.zsAuditStatus == 6}">黑名单</c:if>
				</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">备注/驳回原因</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea rows="" cols="" name="zsAuditRemarks" id="zsAuditRemarks">${mchtProductBrand.zsAuditRemarks}</textarea>
				</td>
			</tr>
			<c:if test="${contactType == 1 || contactType == 7 || not empty generalOffice}">
			<tr>
				<td colspan="1" class="title">招商内部备注</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea rows="" cols="" name="zsAuditInnerRemarks" id="zsAuditInnerRemarks">${mchtProductBrand.zsAuditInnerRemarks}</textarea>
				</td>
			</tr>
			</c:if>
			<c:if test="${not empty mchtProductBrand.auditStatus}">
			<tr>
				<td  colspan="1" class="title">法务确认结果<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${sessionScope.USER_SESSION.staffID!=1}">
					<input type="hidden" name="auditStatus" value="${mchtProductBrand.auditStatus}">
						<c:if test="${mchtProductBrand.auditStatus == 3}">
							通过
						</c:if>
						<c:if test="${mchtProductBrand.auditStatus == 4}">
							驳回
						</c:if>
						<c:if test="${mchtProductBrand.auditStatus == 5}">
							不签约
						</c:if>
						<c:if test="${mchtProductBrand.auditStatus == 6}">
							黑名单
						</c:if>
					</c:if>
					<c:if test="${sessionScope.USER_SESSION.staffID==1}">
						<input type="radio" name="auditStatus" value="3" <c:if test="${mchtProductBrand.auditStatus == 3}">checked="checked"</c:if>>通过
						<input type="radio" name="auditStatus" value="4" <c:if test="${mchtProductBrand.auditStatus == 4}">checked="checked"</c:if>>驳回
						<input type="radio" name="auditStatus" value="5" <c:if test="${mchtProductBrand.auditStatus == 5}">checked="checked"</c:if>>不签约
						<input type="radio" name="auditStatus" value="6" <c:if test="${mchtProductBrand.auditStatus == 6}">checked="checked"</c:if>>黑名单
					</c:if>
				</td>
			</tr>
			</c:if>
			<c:if test="${contactType == 1 || contactType == 7 || not empty generalOffice}">
			<tr>
				<td colspan="1" class="title">法务内部备注</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea rows="" cols="" name="auditInnerRemarks" id="auditInnerRemarks">${mchtProductBrand.auditInnerRemarks}</textarea>
				</td>
			</tr>
			</c:if>
			<c:if test="${mchtProductBrand.auditStatus == '4' }">
				<tr>
					<td  colspan="1" class="title">驳回原因</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						${mchtProductBrand.auditRemarks	}
					</td>
				</tr>
			</c:if>
			<c:if test="${sessionScope.USER_SESSION.staffID=='1'}">
			 <tr>
				<td  colspan="1" class="title">运营状态<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<select style="width: 160px;" id="status" name="status" validate="{required:true}">
				<option value="">请选择</option>
					<c:forEach var="statusItem" items="${mchtProductBrandStatus}">
						<option  <c:if test="${mchtProductBrand.status==statusItem.statusValue}">selected</c:if>
						value="${statusItem.statusValue}">${statusItem.statusDesc}
						</option>
					</c:forEach>
				</select>
				</td>
			</tr>
		 	</c:if>
			<c:if test="${view=='1' || view=='2' || sessionScope.USER_SESSION.staffID==1}">
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<c:if test="${STAFF_ID!=1}">
							<input name="btnSubmit" type="submit" id="Button1" 
								style="float:left;" class="l-button l-button-submit" value="提交" onclick="mchtProductBrandSaveSubmit();"/>
						</c:if>
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />

					</div>
				</td>
			</tr>
			</c:if>
		</table>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
