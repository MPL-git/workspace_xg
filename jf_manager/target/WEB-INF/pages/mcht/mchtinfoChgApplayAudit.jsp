<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}
.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}

.td-pictures li{
display: inline-block;
}
td img{
width: 60px;
height: 40px;
}
.infoChange{
	color: red;
}
</style>
<script type="text/javascript">

var corporationNewViewer;
var corporationOldViewer;
var blPicNewViewer;
var blPicOldViewer;
var occPicNewViewer;
var occPicOldViewer;
var trcPicNewViewer;
var trcPicOldViewer;
var atqPicNewViewer;
var atqPicOldViewer;
var boaalPicNewViewer;
var boaalPicOldViewer;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	corporationNewViewer = new Viewer(document.getElementById('corporation-new'), {});
	corporationOldViewer = new Viewer(document.getElementById('corporation-old'), {});
	blPicNewViewer = new Viewer(document.getElementById('blPic-new'), {});
	blPicOldViewer = new Viewer(document.getElementById('blPic-old'), {});
	if($("#occPic-new").length>0){
		occPicNewViewer = new Viewer(document.getElementById('occPic-new'), {});
	}
	if($("#occPic-old").length>0){
		occPicOldViewer = new Viewer(document.getElementById('occPic-old'), {});
	}
	if($("#trcPic-new").length>0){
		trcPicNewViewer = new Viewer(document.getElementById('trcPic-new'), {});
	}
	if($("#trcPic-old").length>0){
		trcPicOldViewer = new Viewer(document.getElementById('trcPic-old'), {});
	}
	if($("#atqPic-new").length>0){
		atqPicNewViewer = new Viewer(document.getElementById('atqPic-new'), {});
	}
	if($("#atqPic-old").length>0){
		trcPicOldViewer = new Viewer(document.getElementById('atqPic-old'), {});
	}
	if($("#boaalPic-new").length>0){
		boaalPicNewViewer = new Viewer(document.getElementById('boaalPic-new'), {});
	}
	if($("#boaalPic-old").length>0){
		boaalPicOldViewer = new Viewer(document.getElementById('boaalPic-old'), {});
	}
});



$(function ()  {
	$.metadata.setType("attr", "validate");
    //驳回原因必填
    $.validator.addMethod("auditRemarksRequired", function(value, element) {  
    	if($("input[type=radio][name='status'][value=4]").attr("checked")&&$.trim($("#auditRemarks").val())==""){
 			return false;
 		}else{
 			return true;
 		}
    }, "请注明驳回原因");
	            var v = $("#form1").validate({
	            	
	                errorPlacement: function (lable, element)
	                {   console.log(lable[0].innerText);
	                	if($(element).hasClass("l-text-field")){
	                		$(element).parent().ligerTip({
								content : lable.html(),width: 100
							});
	                	}else{
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
//	                	form.submit();
 	                	auditSubmit();
	                }
	            });
	            
	  });    


function auditSubmit(){
	
	//身份证
	var corporationIdcardImgPicstures = "";
	var mchtCorporationIdcardImgPicstures = [];
	var lis = $("#mchtCorporationIdcardImgPicstures").find("li");
	if(lis.length!=2 && $("input[type=radio][name='status'][value=3]").attr("checked")){
		$.ligerDialog.warn('身份证图片信息不完整');
		return;
	}
	lis.each(function(index, item) {
		var path = $("img", item).attr("path");
		mchtCorporationIdcardImgPicstures.push(path);
	});
	corporationIdcardImgPicstures = JSON.stringify(mchtCorporationIdcardImgPicstures);
	
	//营业执照副本
	var clPicPicsPicstures = "";
	var mchtClPicPicsPicstures = {};
	if($("#mchtCorporationIdcardImgPicstures").find("li").length>0){
		mchtClPicPicsPicstures.clPicPicsPicstures = $("#mchtBlPicPicsPicstures img").eq(0).attr("path");
	}
	//银行开户许可证
	if($("#mchtBoaalPicPicstures").find("li").length>0){
		mchtClPicPicsPicstures.boaalPicPicstures = $("#mchtBoaalPicPicstures img").eq(0).attr("path");
	}
	clPicPicsPicstures = JSON.stringify(mchtClPicPicsPicstures);
	var dataJson = $("#form1").serializeArray();
	dataJson.push({"name":"corporationIdcardImgPicstures","value":corporationIdcardImgPicstures});
	dataJson.push({"name":"clPicPicsPicstures","value":clPicPicsPicstures});
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtInfoChgApplyAuditSave.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : dataJson,
		timeout : 30000,
		success : function(data) {
			if ("200" == data.statusCode) {
			      $.ligerDialog.alert(data.message, '提示', 'success', function() {
			    	  parent.location.reload();
					  frameElement.dialog.close();
				 });
			}else{
		        $.ligerDialog.alert(data.message, '提示', 'error',function() {
		        	parent.location.reload();
					  frameElement.dialog.close();
					 });
			}		
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function toEditChgCompanyType(id){
	$.ligerDialog.open({
		height : 250,
		width : 400,
		title : "修改公司类型",
		name : "INSERT_WINDOW",
		url : "${pageContext.request.contextPath}/mcht/mchtInfoChg/toEditChgCompanyType.shtml?id="+id,
		showMax : true,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data : null
	});
}

function toEditYearlyInspectionDate(id){
	$.ligerDialog.open({
		height : 350,
		width : 400,
		title : "修改营业执照有效期",
		name : "INSERT_WINDOW",
		url : "${pageContext.request.contextPath}/mcht/mchtInfoChg/toEditYearlyInspectionDate.shtml?id="+id,
		showMax : true,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data : null
	});
}

function toEditScopeOfBusiness(id){
	$.ligerDialog.open({
		height : 500,
		width : 800,
		title : "修改经营范围",
		name : "INSERT_WINDOW",
		url : "${pageContext.request.contextPath}/mcht/mchtInfoChg/toEditScopeOfBusiness.shtml?id="+id,
		showMax : true,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data : null
	});
}

//公司住所
function toEditChgCompanyAddress(id,name){
	$.ligerDialog.open({
		height : 280,
		width : 800,
		title : name,
		name : "INSERT_WINDOW",
		url : "${pageContext.request.contextPath}/mcht/mchtInfoChg/toEditChgCompanyAddress.shtml?id="+id,
		showMax : true,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data : null
	});
}

//法人身份证有效期
function toEditChgCorporationIdcardDate(id,name){
	$.ligerDialog.open({
		height : 210,
		width : 800,
		title : name,
		name : "INSERT_WINDOW",
		url : "${pageContext.request.contextPath}/mcht/mchtInfoChg/toEditChgCorporationIdcardDate.shtml?id="+id,
		showMax : true,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data : null
	});
}

//主要经营品牌类型
function toEditChgBrandType(id){
	$.ligerDialog.open({
		height : 280,
		width : 800,
		title : "修改主要经营品牌类型",
		name : "INSERT_WINDOW",
		url : "${pageContext.request.contextPath}/mcht/mchtInfoChg/toEditChgBrandType.shtml?id="+id,
		showMax : true,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data : null
	});
}

//修改发照时间
function toEditChgFoundedDate(id,name){
	$.ligerDialog.open({
		height : 350,
		width : 400,
		title : name,
		name : "INSERT_WINDOW",
		url : "${pageContext.request.contextPath}/mcht/mchtInfoChg/toEditChgFoundedDate.shtml?id="+id,
		showMax : true,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data : null
	});
}

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
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: id,
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				//对viewer进行重新赋值
				if(id == 'CorporationIdcardImgPicstures'){
					if($("#mcht"+id).children('li').length<2){
						$("#mcht"+id).append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');
					}else{
						$("#mcht"+id).children('li').eq(1).remove();
						$("#mcht"+id).append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');
					}
					corporationNewViewer.destroy();
					corporationNewViewer = new Viewer(document.getElementById('corporation-new'), {});
				}else if(id == 'BlPicPicsPicstures'){
					$("#mcht"+id).html('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');
					blPicNewViewer.destroy();
					blPicNewViewer = new Viewer(document.getElementById('blPic-new'), {});
				}else if(id == 'BoaalPicPicstures'){
					$("#mcht"+id).html('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');
					boaalPicNewViewer.destroy();
					boaalPicNewViewer = new Viewer(document.getElementById('boaalPic-new'), {});
				}
				$(".del").live('click',function(){
					$(this).closest("li").remove();
				});
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
}
</script>
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" value="${mchtInfoChg.id }" name="id">
		<c:if test="${mchtInfo.settledType == 1}">
		<table class="gridtable">
			<tr>
			<td  colspan="1" class="title"></td>
			<td  colspan="1" class="title">新内容</td>
			<td  colspan="1" class="title">旧内容</td>
			</tr>
		
			<tr>
				<td  colspan="1" class="title">入驻类型</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>
						<c:if test="${mchtInfo.settledType == 1}">公司企业</c:if>
						<c:if test="${mchtInfo.settledType == 2}">个体商户</c:if>
					</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>
						<c:if test="${mchtInfo.settledType == 1}">公司企业</c:if>
						<c:if test="${mchtInfo.settledType == 2}">个体商户</c:if>
					</span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">公司名称</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.companyName!=mchtInfo.companyName}">
				 class="infoChange"
				</c:if>
				 >${mchtInfoChg.companyName}</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.companyName}</span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">公司类型</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span <c:if test="${mchtInfoChg.companyType!=mchtInfo.companyType}">class="infoChange"</c:if>>
						${mchtInfoChg.companyType}<a href="javascript:;" onclick="toEditChgCompanyType(${mchtInfoChg.id});">【修改】</a>
					</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfo.companyType}</span>	
				</td>
		
			</tr>

			<tr>
				<td  colspan="1" class="title">营业执照注册号</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.uscc!=mchtInfo.uscc}">
				 class="infoChange"
				</c:if>
				>${mchtInfoChg.uscc }</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.uscc}</span>	
				</td>

			</tr>
			<tr>
				<td  colspan="1" class="title">注册资本</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.regCapital!=mchtInfo.regCapital||mchtInfoChg.regFeeType!=mchtInfo.regFeeType}">
				 class="infoChange"
				</c:if>
				>${mchtInfoChg.regCapital }（${mchtInfoChg.regFeeTypeDesc }）</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.regCapital}（${mchtInfo.regFeeTypeDesc }）</span>	
				</td>

			</tr>
				
			<tr>
				<td  colspan="1" class="title">公司住所</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.companyAddress!=mchtInfo.companyAddress}">
				 class="infoChange"
				</c:if>
				>${mchtInfoChg.companyAddress}<c:if test="${role43or1 eq true || role43or1 eq true}"><a href="javascript:;" onclick="toEditChgCompanyAddress(${mchtInfoChg.id},'修改住所地址');">【修改】</a></c:if></span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.companyAddress }</span>	
				</td>

			</tr>
			<tr>
				<td  colspan="1" class="title">成立日期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.foundedDate!=mchtInfo.foundedDate}">
				 class="infoChange"
				</c:if>
				><fmt:formatDate value="${mchtInfoChg.foundedDate }" pattern="yyyy-MM-dd"/><a href="javascript:;" onclick="toEditChgFoundedDate(${mchtInfoChg.id},'修改成立时间');">【修改】</a>
				</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span><fmt:formatDate value="${mchtInfo.foundedDate }" pattern="yyyy-MM-dd"/></span>	
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">营业执照有效期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span <c:if test="${mchtInfoChg.yearlyInspectionDate!=mchtInfo.yearlyInspectionDate}"> class="infoChange"</c:if>>
						<fmt:formatDate value="${mchtInfoChg.yearlyInspectionDate}" pattern="yyyy-MM-dd"/>
						<c:if test="${empty mchtInfoChg.yearlyInspectionDate}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfoChg.id});">【添加有效期】</a></c:if>
						<c:if test="${not empty mchtInfoChg.yearlyInspectionDate}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfoChg.id});">【修改有效期】</a></c:if>
					</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span><fmt:formatDate value="${mchtInfo.yearlyInspectionDate}" pattern="yyyy-MM-dd"/></span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">经营范围</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span <c:if test="${mchtInfoChg.scopeOfBusiness!=mchtInfo.scopeOfBusiness}"> class="infoChange"</c:if>>
						<c:if test="${empty mchtInfoChg.scopeOfBusiness}"><a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfoChg.id});">【添加经营范围】</a></c:if>
						<c:if test="${not empty mchtInfoChg.scopeOfBusiness}">${mchtInfoChg.scopeOfBusiness}<a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfoChg.id});">【修改经营范围】</a></c:if>
					</span>
					<input type="hidden" name="scopeOfBusiness" value="${mchtInfoChg.scopeOfBusiness}">
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfo.scopeOfBusiness }</span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">法人姓名</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.corporationName!=mchtInfo.corporationName}">
				 class="infoChange"
				</c:if>
				
				>${mchtInfoChg.corporationName }</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.corporationName }</span>	
				</td>

			</tr>
			
						<tr>
			<td  colspan="1" class="title">法人身份证号</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.corporationIdcardNo!=mchtInfo.corporationIdcardNo}">
				 class="infoChange"
				</c:if>
				
				>${mchtInfoChg.corporationIdcardNo }</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.corporationIdcardNo }</span>	
				</td>

			</tr>
			<tr>
			<td  colspan="1" class="title">法人身份证</td>
				<td  colspan="1" align="left" class="l-table-edit-td" id="corporation-new">
					<t:imageList name="mchtCorporationIdcardImgPicstures" list="${corporationIdcardImgList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<c:if test="${role43or1 eq true || role43or1 eq true}">
						<div style="float: left;height: 105px;margin: 10px;">
							 <input style="position:absolute; opacity:0;width: 110px;" type="file" id="CorporationIdcardImgPicstures" name="file" onchange="ajaxFileUploadImg(this);" /> 
							 <input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
						</c:if>
				</td>	
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="corporation-old">
						<li><img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg1}" alt=""></li>
						<li><img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg2}" alt=""></li>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">法人身份证有效期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.corporationIdcardDate!=mchtInfo.corporationIdcardDate}">
				 class="infoChange"
				</c:if>
				><fmt:formatDate value="${mchtInfoChg.corporationIdcardDate }" pattern="yyyy-MM-dd"/><c:if test="${role43or1 eq true or role43or1 eq true}"><a href="javascript:;" onclick="toEditChgCorporationIdcardDate(${mchtInfoChg.id},'修改法人身份证有效期');">【修改】</a></c:if></span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span><fmt:formatDate value="${mchtInfo.corporationIdcardDate }" pattern="yyyy-MM-dd"/></span>	
				</td>

			</tr>
			<tr>
				<td  colspan="1" class="title">法人手机</td>
								<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				
				<c:if test="${mchtInfoChg.corporationMobile!=mchtInfo.corporationMobile}">
				 class="infoChange"
				</c:if>
				>${mchtInfoChg.corporationMobile }</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.corporationMobile }</span>	
				</td>

			</tr>
			
			<tr>
				<td  colspan="1" class="title">公司总机</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.companyTel!=mchtInfo.companyTel}">
				 class="infoChange"
				</c:if>
				
				>${mchtInfoChg.companyTel }</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.companyTel }</span>	
				</td>

			</tr>
			
				<tr>
				<td  colspan="1" class="title">公司通讯地址</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.contactProvinceName!=mchtInfo.contactProvinceName||mchtInfoChg.contactCityName!=mchtInfo.contactCityName||mchtInfoChg.contactCountyName!=mchtInfo.contactCountyName||mchtInfoChg.contactAddress!=mchtInfo.contactAddress}">
				 class="infoChange"
				</c:if>
				>
				${mchtInfoChg.contactProvinceName}${mchtInfoChg.contactCityName}${mchtInfoChg.contactCountyName}${mchtInfoChg.contactAddress}
					</span></td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>
				
				${mchtInfo.contactProvinceName}${mchtInfo.contactCityName}${mchtInfo.contactCountyName}${mchtInfo.contactAddress}
				</span>	
				</td>

			</tr>
			
			<tr>
			<td  colspan="1" class="title">营业执照副本</td>
				<td  colspan="1" align="left" class="l-table-edit-td" id="blPic-new">
					<t:imageList name="mchtBlPicPicsPicstures" list="${mchtBlPicPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<c:if test="${role43or1 eq true || role43or1 eq true}">
						<div style="float: left;height: 105px;margin: 10px;">
							 <input style="position:absolute; opacity:0;width: 110px;" type="file" id="BlPicPicsPicstures" name="file" onchange="ajaxFileUploadImg(this);" /> 
							 <input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
						</c:if>
				</td>	
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="blPic-old">
						<li><img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.blPic}" alt=""></li>
					</ul>
				</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">组织机构代码证</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="occPic-new">
						<li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.occPic}" alt=""></li>
					</ul>
				</td>	
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="occPic-old">
						<li><img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.occPic}" alt=""></li>
					</ul>
				</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">税务登记证</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="trcPic-new">
						<li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.trcPic}" alt=""></li>
					</ul>
				</td>	
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="trcPic-old">
						<li><img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.trcPic}" alt=""></li>
					</ul>
				</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">一般纳税人资格证</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="atqPic-new">
						<li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.atqPic}" alt=""></li>
					</ul>
				</td>	
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="atqPic-old">
						<li><img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.atqPic}" alt=""></li>
					</ul>
				</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">银行开户许可证</td>
				<td  colspan="1" align="left" class="l-table-edit-td" id="boaalPic-new">
					<t:imageList name="mchtBoaalPicPicstures" list="${mchtBoaalPicPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<c:if test="${role43or1 eq true || role43or1 eq true}">
						<div style="float: left;height: 105px;margin: 10px;">
							 <input style="position:absolute; opacity:0;width: 110px;" type="file" id="BoaalPicPicstures" name="file" onchange="ajaxFileUploadImg(this);" /> 
							 <input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
						</c:if>
				</td>	
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="boaalPic-old">
						<li><img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.boaalPic}" alt=""></li>
					</ul>
				</td>
			</tr>		
			<tr>
				<td colspan="1" class="title">主要经营品牌类型<span class="required">*</span></td>
				<td colspan="1" class="l-table-edit-td">
                    <select style="width:210px;" name="brandType" id="brandType" validate="{required:true}" disabled>
                    <option value="">请选择</option>
                    <option value="0"<c:if test="${mchtInfoChg.brandType eq 0}">selected</c:if>>品牌官方</option>
                    <option value="1"<c:if test="${mchtInfoChg.brandType eq 1}">selected</c:if>>品牌总代</option>
                    <option value="2"<c:if test="${mchtInfoChg.brandType eq 2}">selected</c:if>>品牌代理商</option>
                    </select>
                    <c:if test="${role43or1 eq true or role43or1 eq true}"><a href="javascript:toEditChgBrandType('${mchtInfoChg.id}')">【修改】</a></c:if>
                    <br><br>
                    <textarea class="form-control" disabled rows="5" style="width: 100%;resize: vertical;" id="brandTypeDesc" name="brandTypeDesc" maxlength="256" validate="{required:true}">${mchtInfoChg.brandTypeDesc}</textarea>
				</td>
				<td colspan="1" class="l-table-edit-td">
                    <select style="width:210px;" name="brandType" id="brandType" validate="{required:true}" disabled>
                    <option value="">请选择</option>
                    <option value="0">品牌官方</option>
                    <option value="1">品牌总代</option>
                    <option value="2">品牌代理商</option>
                    </select>
                    <br><br>
                    <textarea class="form-control"  disabled rows="5" style="width: 100%;resize: vertical;" id="brandTypeDesc" name="brandTypeDesc" maxlength="256" validate="{required:true}">${mchtInfo.brandTypeDesc}</textarea>
				</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">审核状态<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
				<c:forEach var="statusItem" items="${statusList }">
					<span class="radioClass"><input type="radio" value="${statusItem.statusValue }" name="status" <c:if test="${statusItem.statusValue==mchtInfoChg.status}">checked="checked"</c:if> >${statusItem.statusDesc}</span>
				</c:forEach>	
				</td>
					
			</tr>
					<tr>
			<td  colspan="1" class="title">驳回原因<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
					<textarea rows=5
						id="auditRemarks" name="auditRemarks" cols="50" class="text" validate="{auditRemarksRequired:true}"  >${mchtInfoChg.auditRemarks}</textarea>
			
				</td>
					
			</tr>
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
							<input name="btnSubmit" type="submit" id="Button1"
								style="float:left;" class="l-button l-button-submit" value="提交"
								 />
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>
		</table>
		</c:if>
		
		<c:if test="${mchtInfo.settledType == 2}">
		<table class="gridtable">
			<tr>
			<td  colspan="1" class="title"></td>
			<td  colspan="1" class="title">新内容</td>
			<td  colspan="1" class="title">旧内容</td>
			</tr>
		
			<tr>
				<td  colspan="1" class="title">入驻类型</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>
						<c:if test="${mchtInfo.settledType == 1}">公司企业</c:if>
						<c:if test="${mchtInfo.settledType == 2}">个体商户</c:if>
					</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>
						<c:if test="${mchtInfo.settledType == 1}">公司企业</c:if>
						<c:if test="${mchtInfo.settledType == 2}">个体商户</c:if>
					</span>	
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">名称</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.companyName!=mchtInfo.companyName}">
				 class="infoChange"
				</c:if>
				 >${mchtInfoChg.companyName}</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.companyName}</span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">组织形式</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span <c:if test="${mchtInfoChg.companyType!=mchtInfo.companyType}">class="infoChange"</c:if>>
						${mchtInfoChg.companyType} <a href="javascript:;" onclick="toEditChgCompanyType(${mchtInfoChg.id});">【修改】</a>
					</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfo.companyType}</span>	
				</td>
		
			</tr>

			<tr>
				<td  colspan="1" class="title">营业执照注册号</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.uscc!=mchtInfo.uscc}">
				 class="infoChange"
				</c:if>
				>${mchtInfoChg.uscc }</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.uscc}</span>	
				</td>

			</tr>
			<tr>
				<td  colspan="1" class="title">经营场所</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span <c:if test="${mchtInfoChg.companyAddress!=mchtInfo.companyAddress}">class="infoChange"</c:if>>
				${mchtInfoChg.companyAddress}<a href="javascript:;" onclick="toEditChgCompanyAddress(${mchtInfoChg.id},'修改经营场所');">【修改】</a>
				</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.companyAddress }</span>	
				</td>

			</tr>
			<tr>
				<td  colspan="1" class="title">发照时间</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span <c:if test="${mchtInfoChg.foundedDate!=mchtInfo.foundedDate}">class="infoChange"</c:if>>
				<fmt:formatDate value="${mchtInfoChg.foundedDate}" pattern="yyyy-MM-dd"/><a href="javascript:;" onclick="toEditChgFoundedDate(${mchtInfoChg.id},'修改发照时间');">【修改】</a>
				</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span><fmt:formatDate value="${mchtInfo.foundedDate }" pattern="yyyy-MM-dd"/></span>	
				</td>

			</tr>
			<tr>
				<td  colspan="1" class="title">营业执照有效期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span <c:if test="${mchtInfoChg.yearlyInspectionDate!=mchtInfo.yearlyInspectionDate}"> class="infoChange"</c:if>>
						<fmt:formatDate value="${mchtInfoChg.yearlyInspectionDate}" pattern="yyyy-MM-dd"/>
						<c:if test="${empty mchtInfoChg.yearlyInspectionDate}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfoChg.id});">【添加有效期】</a></c:if>
						<c:if test="${not empty mchtInfoChg.yearlyInspectionDate}"><a href="javascript:;" onclick="toEditYearlyInspectionDate(${mchtInfoChg.id});">【修改有效期】</a></c:if>
					</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span><fmt:formatDate value="${mchtInfo.yearlyInspectionDate}" pattern="yyyy-MM-dd"/></span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">经营范围</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span <c:if test="${mchtInfoChg.scopeOfBusiness!=mchtInfo.scopeOfBusiness}"> class="infoChange"</c:if>>
						<c:if test="${empty mchtInfoChg.scopeOfBusiness}"><a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfoChg.id});">【添加经营范围】</a></c:if>
						<c:if test="${not empty mchtInfoChg.scopeOfBusiness}">${mchtInfoChg.scopeOfBusiness}<a href="javascript:;" onclick="toEditScopeOfBusiness(${mchtInfoChg.id});">【修改经营范围】</a></c:if>
					</span>
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfo.scopeOfBusiness }</span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">经营者姓名</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.corporationName!=mchtInfo.corporationName}">
				 class="infoChange"
				</c:if>
				
				>${mchtInfoChg.corporationName }</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.corporationName }</span>	
				</td>

			</tr>
			
						<tr>
			<td  colspan="1" class="title">经营者身份证号</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.corporationIdcardNo!=mchtInfo.corporationIdcardNo}">
				 class="infoChange"
				</c:if>
				
				>${mchtInfoChg.corporationIdcardNo }</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.corporationIdcardNo }</span>	
				</td>

			</tr>
			<tr>
			<td  colspan="1" class="title">经营者身份证</td>
				<td  colspan="1" align="left" class="l-table-edit-td" id="corporation-new">
					<t:imageList name="mchtCorporationIdcardImgPicstures" list="${corporationIdcardImgList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<c:if test="${role43or1 eq true || role43or1 eq true}">
						<div style="float: left;height: 105px;margin: 10px;">
							 <input style="position:absolute; opacity:0;width: 110px;" type="file" id="CorporationIdcardImgPicstures" name="file" onchange="ajaxFileUploadImg(this);" /> 
							 <input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
						</c:if>
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="corporation-old">
						<li><img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg1}" alt=""></li>
						<li><img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg2}" alt=""></li>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">经营者身份证有效期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.corporationIdcardDate!=mchtInfo.corporationIdcardDate}">
				 class="infoChange"
				</c:if>
				><fmt:formatDate value="${mchtInfoChg.corporationIdcardDate }" pattern="yyyy-MM-dd"/><c:if test="${role43or1 eq true or role43or1 eq true}"><a href="javascript:;" onclick="toEditChgCorporationIdcardDate(${mchtInfoChg.id},'修改身份证有效期');">【修改】</a></c:if></span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span><fmt:formatDate value="${mchtInfo.corporationIdcardDate }" pattern="yyyy-MM-dd"/></span>	
				</td>

			</tr>
			<tr>
				<td  colspan="1" class="title">经营者手机</td>
								<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				
				<c:if test="${mchtInfoChg.corporationMobile!=mchtInfo.corporationMobile}">
				 class="infoChange"
				</c:if>
				>${mchtInfoChg.corporationMobile }</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfo.corporationMobile }</span>	
				</td>

			</tr>
			
				<tr>
				<td  colspan="1" class="title">通讯地址</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtInfoChg.contactProvinceName!=mchtInfo.contactProvinceName||mchtInfoChg.contactCityName!=mchtInfo.contactCityName||mchtInfoChg.contactCountyName!=mchtInfo.contactCountyName||mchtInfoChg.contactAddress!=mchtInfo.contactAddress}">
				 class="infoChange"
				</c:if>
				>
				${mchtInfoChg.contactProvinceName}${mchtInfoChg.contactCityName}${mchtInfoChg.contactCountyName}${mchtInfoChg.contactAddress}
					</span></td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>
				
				${mchtInfo.contactProvinceName}${mchtInfo.contactCityName}${mchtInfo.contactCountyName}${mchtInfo.contactAddress}
				</span>	
				</td>

			</tr>
			
			<tr>
			<td  colspan="1" class="title">营业执照副本</td>
				<td  colspan="1" align="left" class="l-table-edit-td" id="blPic-new">
					<t:imageList name="mchtBlPicPicsPicstures" list="${mchtBlPicPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<c:if test="${role43or1 eq true || role43or1 eq true}">
						<div style="float: left;height: 105px;margin: 10px;">
							 <input style="position:absolute; opacity:0;width: 110px;" type="file" id="BlPicPicsPicstures" name="file" onchange="ajaxFileUploadImg(this);" /> 
							 <input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
						</c:if>
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="blPic-old">
						<li><img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.blPic}" alt=""></li>
					</ul>
				</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">经营者银行卡信息</td>
				<td  colspan="1" align="left" class="l-table-edit-td" id="boaalPic-new">
					<t:imageList name="mchtBoaalPicPicstures" list="${mchtBoaalPicPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<c:if test="${role43or1 eq true || role43or1 eq true}">
						<div style="float: left;height: 105px;margin: 10px;">
							 <input style="position:absolute; opacity:0;width: 110px;" type="file" id="BoaalPicPicstures" name="file" onchange="ajaxFileUploadImg(this);" /> 
							 <input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
						</c:if>
				</td>	
				<td  colspan="1" align="left" class="l-table-edit-td">
					<ul class="docs-pictures clearfix td-pictures" id="boaalPic-old">
						<li><img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.boaalPic}" alt=""></li>
					</ul>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">主要经营品牌类型<span class="required">*</span></td>
				<td colspan="1" class="l-table-edit-td">
                    <select style="width:210px;" name="brandType" id="brandType" validate="{required:true}" disabled>
                    <option value="">请选择</option>
                    <option value="0"<c:if test="${mchtInfoChg.brandType eq 0}">selected</c:if>>品牌官方</option>
                    <option value="1"<c:if test="${mchtInfoChg.brandType eq 1}">selected</c:if>>品牌总代</option>
                    <option value="2"<c:if test="${mchtInfoChg.brandType eq 2}">selected</c:if>>品牌代理商</option>
                    </select>
                    <c:if test="${role43or1 eq true or role43or1 eq true}"><a href="javascript:toEditChgBrandType('${mchtInfoChg.id}')">【修改】</a></c:if>
                    <br><br>
                    <textarea class="form-control" disabled rows="5" style="width: 100%;resize: vertical;" id="brandTypeDesc" name="brandTypeDesc" maxlength="256" validate="{required:true}">${mchtInfoChg.brandTypeDesc}</textarea>
				</td>
				<td colspan="1" class="l-table-edit-td">
                    <select style="width:210px;" name="brandType" id="brandType" validate="{required:true}" disabled>
                    <option value="">请选择</option>
                    <option value="0">品牌官方</option>
                    <option value="1">品牌总代</option>
                    <option value="2">品牌代理商</option>
                    </select>
                    <br><br>
                    <textarea class="form-control"  disabled rows="5" style="width: 100%;resize: vertical;" id="brandTypeDesc" name="brandTypeDesc" maxlength="256" validate="{required:true}">${mchtInfo.brandTypeDesc}</textarea>
				</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">审核状态<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
				<c:forEach var="statusItem" items="${statusList }" varStatus="index">
					<c:if test="${statusItem.statusValue ne 1}">
						<span class="radioClass"><input type="radio" value="${statusItem.statusValue }" name="status" <c:if test="${statusItem.statusValue==mchtInfoChg.status || index.index == 0}">checked="checked"</c:if> >${statusItem.statusDesc}</span>			
					</c:if>
				</c:forEach>	
				</td>
					
			</tr>
					<tr>
			<td  colspan="1" class="title">驳回原因<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
					<textarea rows=5
						id="auditRemarks" name="auditRemarks" cols="50" class="text" validate="{auditRemarksRequired:true}"  >${mchtInfoChg.auditRemarks}</textarea>
			
				</td>
					
			</tr>
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
							<input name="btnSubmit" type="submit" id="Button1"
								style="float:left;" class="l-button l-button-submit" value="提交"
								 />
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>
		</table>
		</c:if>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
</html>
