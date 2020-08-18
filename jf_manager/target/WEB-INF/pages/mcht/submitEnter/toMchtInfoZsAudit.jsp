<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />	
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
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

.table-title-link{
color: #1B17EE;
font-size: 15px;
text-decoration: none;
}

.table-title{
font-size: 17px;font-weight: 600;
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
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("img").bind('click',function(){
		viewerPic($(this).attr("src"));
	});
	
	var submitting;
	$("#confirm").bind('click',function(){
		if(submitting){
			return;
		}
		var contractDeposit = $("#contractDeposit").val();
		var feeRate = $("#feeRate").val();
		var shopNameAuditStatus = $("input[name='shopNameAuditStatus']:checked").val();
		var shopNameAuditRemarks=$("#shopNameAuditRemarks").val();
		var shopNameAuditInnerRemarks=$("#shopNameAuditInnerRemarks").val();
		var zsTotalAuditRemarks=$("#zsTotalAuditRemarks").val();
		if(!contractDeposit){
			commUtil.alertError("店铺保证金不能为空");
			return;
		}
		if(!feeRate){
			commUtil.alertError("技术服务费率预定不能为空");
			return;
		}
		if(!shopNameAuditStatus){
			commUtil.alertError("对不起，商家的店铺名未审核。");
			return;
		}
		if(shopNameAuditStatus == 4){
			if(!shopNameAuditRemarks){
				commUtil.alertError("店铺信息审核驳回原因必填");
				return;
			}
		}
		var zsTotalAuditStatus = $("input[name='zsTotalAuditStatus']:checked").val();
		if(!zsTotalAuditStatus){
			commUtil.alertError("招商总确认的确认结果未审核");
			return;
		}
		if(zsTotalAuditStatus == 2){//通过
			if(shopNameAuditStatus == 4){
				commUtil.alertError("店铺信息审核结果驳回，招商总确认无法通过");
				return;
			}
			var mchtProductTypeStatus = ${mchtProductTypeStatus};
			if(mchtProductTypeStatus!=1){
				commUtil.alertError("主营品类状态不正常，招商总确认无法通过");
				return;
			}
			var i=0;
			$("table[name='mchtProductBrandTable']").each(function(){
				var mchtProductBrandZsAuditStatus = $(this).find("input[type='radio']:checked").val();
				if(mchtProductBrandZsAuditStatus && mchtProductBrandZsAuditStatus == 2){
					return;
				}else{
					i++;
				}
			});
			<c:if test="${mchtInfo.settledType eq 1}">
			if(i == $("table[name='mchtProductBrandTable']").length && $("table[name='mchtProductBrandTable']").length>0){
				/* commUtil.alertError("至少需要一个品牌审核通过，否则招商总确认无法通过");
				return; */
			}
			</c:if>
		}
		if(zsTotalAuditStatus == 3){//驳回
			if(!zsTotalAuditRemarks){
				commUtil.alertError("招商总确认的驳回原因必填");
				return;
			}
		}
		var mchtProductBrandArray = [];
		var mchtType = "${mchtInfo.mchtType}";
		var mchtProductBrandChecked = true;
		var mchtProductBrandCheckedMsg = "";
		$("input[name='productBrandName']").each(function(index){
			var mchtProductBrandObj = {};
			var mchtProductBrandId = $(this).attr("mchtProductBrandId");
			mchtProductBrandObj.id = mchtProductBrandId;
			var productBrandName = $(this).val();
			if(!productBrandName){
				mchtProductBrandChecked = false;
				mchtProductBrandCheckedMsg = "申请品牌名称不能为空";
				return false;
			}
			mchtProductBrandObj.productBrandName = productBrandName;
			
			var zsAuditStatus = $(this).closest("table").find("input[name='zsAuditStatus"+index+"']:checked").val();
			if(!zsAuditStatus){
				mchtProductBrandChecked = false;
				mchtProductBrandCheckedMsg = "品牌的招商确认结果未审核";
				return false;
			}
			mchtProductBrandObj.zsAuditStatus = zsAuditStatus;
			
			/*if(mchtType == "2"){*/
				var popCommissionRate = $(this).closest("table").find("input[name='popCommissionRate']").val();
				if(!popCommissionRate && zsAuditStatus == 2){
					mchtProductBrandChecked = false;
					mchtProductBrandCheckedMsg = "品牌的技术服务费率不能为空";
					return false;
				}
				if(parseInt(popCommissionRate)<0){
					mchtProductBrandChecked = false;
					mchtProductBrandCheckedMsg = "品牌的技术服务费率不能小于0";
					return false;
				}
				if(parseInt(popCommissionRate)>=1){
					mchtProductBrandChecked = false;
					mchtProductBrandCheckedMsg = "品牌的技术服务费率不能大于等于1";
					return false;
				}
				mchtProductBrandObj.popCommissionRate = popCommissionRate;
			/*}else{//TODO SPOP
				var priceModel = $(this).closest("table").find("select[name='priceModel']").val();
				if(!priceModel && zsAuditStatus == 2){
					mchtProductBrandChecked = false;
					mchtProductBrandCheckedMsg = "品牌的SPOP定价方式 不能为空";
					return false;
				}
				mchtProductBrandObj.priceModel = priceModel;
			}*/
			
			//TODO 备注处理
			var $lastTr = $(this).closest("table").find("tr").last();
			var zsAuditRemarks = $lastTr.prev().find("textarea").eq(0).val();
			var zsAuditInnerRemarks = $lastTr.find("textarea").eq(0).val();
			if(zsAuditStatus == 4){
				if(!zsAuditRemarks){
					mchtProductBrandChecked = false;
					mchtProductBrandCheckedMsg = "品牌审核驳回时，驳回原因不能为空。";
					return false;
				}
			}
			mchtProductBrandObj.zsAuditRemarks=zsAuditRemarks;
			mchtProductBrandObj.zsAuditInnerRemarks=zsAuditInnerRemarks;
			
			var mchtPlatformAuthPictures = [];
			var lis = $("#mchtPlatformAuthPictures"+index).find("li");
			lis.each(function(index, item) {
				var path = $("img", item).attr("path");
				var def = ($(".def", item).length == 1 ? "1" : "0");
				var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
				mchtPlatformAuthPictures.push(pic);
			});
			mchtProductBrandObj.mchtPlatformAuthPics = JSON.stringify(mchtPlatformAuthPictures);
			
			var mchtBrandInvoicePictures = [];
			var lis = $("#mchtBrandInvoicePictures"+index).find("li");
			lis.each(function(index, item) {
				var path = $("img", item).attr("path");
				var def = ($(".def", item).length == 1 ? "1" : "0");
				var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
				mchtBrandInvoicePictures.push(pic);
			});
			mchtProductBrandObj.mchtBrandInvoicePics = JSON.stringify(mchtBrandInvoicePictures);
			
			var mchtBrandInspectionPictures = [];
			var lis = $("#mchtBrandInspectionPictures"+index).find("li");
			lis.each(function(index, item) {
				var path = $("img", item).attr("path");
				var def = ($(".def", item).length == 1 ? "1" : "0");
				var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
				mchtBrandInspectionPictures.push(pic);
			});
			mchtProductBrandObj.mchtBrandInspectionPics = JSON.stringify(mchtBrandInspectionPictures);
			
			var mchtBrandOtherPictures = [];
			var lis = $("#mchtBrandOtherPictures"+index).find("li");
			lis.each(function(index, item) {
				var path = $("img", item).attr("path");
				var def = ($(".def", item).length == 1 ? "1" : "0");
				var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
				mchtBrandOtherPictures.push(pic);
			});
			mchtProductBrandObj.mchtBrandOtherPics = JSON.stringify(mchtBrandOtherPictures);
			//TODO 商标注册证或受理通知书
			var mchtBrandAptitudeArray = [];
			$(this).closest("tr").next().next().next().find("table").each(function(idx){
				var mchtBrandAptitudeId = $(this).attr("mchtBrandAptitudeId");
				var mchtBrandAptitudePictures = [];
				$(this).find("ul").find("li").each(function(index, item) {
					var path = $("img", item).attr("path");
					var def = ($(".def", item).length == 1 ? "1" : "0");
					var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
					mchtBrandAptitudePictures.push(pic);
				});
				mchtBrandAptitudeArray.push({
					"mchtBrandAptitudeId":mchtBrandAptitudeId,
					"mchtBrandAptitudePics":JSON.stringify(mchtBrandAptitudePictures)
				});
			});
			mchtProductBrandObj.mchtBrandAptitudes = JSON.stringify(mchtBrandAptitudeArray);
			mchtProductBrandArray.push(mchtProductBrandObj);
		});
		var mchtProductBrandJsonStr = JSON.stringify(mchtProductBrandArray);
		if(!mchtProductBrandChecked){
			commUtil.alertError(mchtProductBrandCheckedMsg);
			return;
		}
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/mchtInfoZsAudit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"mchtId":$("#mchtId").val(),
					"shopNameAuditStatus":shopNameAuditStatus,
					"shopNameAuditRemarks":shopNameAuditRemarks,
					"shopNameAuditInnerRemarks":shopNameAuditInnerRemarks,
					"contractDeposit":contractDeposit,
					"feeRate":feeRate,
					"zsTotalAuditStatus":zsTotalAuditStatus,
					"zsTotalAuditRemarks":zsTotalAuditRemarks,
					"mchtProductBrandJsonStr":mchtProductBrandJsonStr
					},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					submitting = false;
					commUtil.alertSuccess("操作成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					submitting = false;
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				submitting = false;
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
		
	});
});
	
function viewerPic(imgPath){
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	$("#viewer-pictures").show();
	viewerPictures.show();
	
}

function viewerMchtPic(mchtProductBrandId,picType){
	
	var url;
	if(picType==1){
		url="${pageContext.request.contextPath}/mcht/getMchtBrandPic.shtml";
	}
	if(picType==2){
		url="${pageContext.request.contextPath}/mcht/getPlatfromAuthPic.shtml";
	}
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {mchtProductBrandId:mchtProductBrandId},
		timeout : 30000,
		success : function(data) {
			console.log(data);
			if(data&&data.length>0){
				for (var i=0;i<data.length;i++)
				{
					$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
				}
				viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
					$("#viewer-pictures").hide();
				}});
				$("#viewer-pictures").show();
				viewerPictures.show();
			}
			
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
	
}

function toZsConfirm(id) {
	$.ligerDialog.open({
		height: $(window).height()*0.8,
		width: $(window).width()*0.5,
		title: "招商确认",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/toZsConfirm.shtml?mchtId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
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
				$("#mcht"+id).append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'" onclick="viewerPic(this.src)"></p><a href="javascript:void(0);" class="del">删除</a></li>');
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

<html>
<body>
<form id="picForm" method="post" action="${pageContext.request.contextPath}/mchtContract/contractPicUpload.shtml">
		<input type="hidden" id="mchtId" value="${mchtId}">
		<!-- 店铺信息审核  -->
		<br>
		<br>
		<div><span class="table-title" >店铺信息审核</span></div>
		<br>
		<table class="gridtable" style="width:800px;">
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">入驻类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${mchtInfo.settledType eq 1}">
						公司企业
					</c:if>
					<c:if test="${mchtInfo.settledType eq 2}">
						个体商户
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title" style="width: 200px;">店铺类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.shopTypeDesc}
				</td>
			</tr>
			<tr>
			
				<td  colspan="1" class="title">公司字号</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.businessFirms}
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">品牌</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.brand}
				</td>
				
			</tr>
			<tr>
				<td  colspan="1" class="title">品类</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.productType}
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">其他</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.other}
				</td>
			</tr>
		
			<tr>
			<td  colspan="1" class="title">店铺名</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.shopName}				
				</td>
			</tr>
		
			<tr>
		
			<td  colspan="1" class="title">合作模式</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					${mchtInfo.mchtTypeDesc}				
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">保证金缴费方式</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<c:if test="${mchtInfo.depositType == 1}">
						可货款抵扣
					</c:if>
					<c:if test="${mchtInfo.depositType == 2}">
						不可货款抵扣
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">保证金类型</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					店铺保证金<input type="text" value="${mchtInfo.contractDeposit}" id="contractDeposit" name="contractDeposit">
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">技术服务费率预定</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<input type="text" name="feeRate" value="${feeRate}" id="feeRate">
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">审核结果</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<input type="radio" name="shopNameAuditStatus" value="3" <c:if test="${mchtInfo.shopNameAuditStatus == 3}">checked="checked"</c:if>>通过 
					<input type="radio" name="shopNameAuditStatus" value="4" <c:if test="${mchtInfo.shopNameAuditStatus == 4}">checked="checked"</c:if>>驳回
				</td>	
				
			</tr>
			<tr>
				<td  colspan="1" class="title">通过备注/驳回原因*</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea rows="7" cols="70" id="shopNameAuditRemarks">${mchtInfo.shopNameAuditRemarks }</textarea>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">内部备注</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<textarea rows="7" cols="70" name="shopNameAuditInnerRemarks" id="shopNameAuditInnerRemarks">${mchtInfo.shopNameAuditInnerRemarks }</textarea>
				</td>
			</tr>
		</table>
		
		<!-- 类目审核 -->
		<div><span class="table-title" >类目审核 </span>
		</div>
		<br>
		<table class="gridtable" style="width:400px;">
			<tbody>
				<tr>
					<td class="title" style="width: 200px;">主营品类</td>
					<td class="title">商品主要品类</td>
					<td class="title">状态</td>
				</tr>
				<tr>
					<td  colspan="1">${productTypeName}</td>
					<td  colspan="1">${productType2Name}</td>
					<td  colspan="3" align="left" class="l-table-edit-td" id="mchtProductTypeDesc">
						<c:if test="${mchtProductTypeStatus == '0'}">
							申请中
						</c:if>
						<c:if test="${mchtProductTypeStatus == '1'}">
							正常
						</c:if>
						<c:if test="${mchtProductTypeStatus == '2'}">
							暂停
						</c:if>
						<c:if test="${mchtProductTypeStatus == '3'}">
							关闭
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<br>
		<div><span class="table-title" >品牌黑名单</span></div>
		<br>
		<table class="gridtable" style="width:1200px;">
			<tbody>
			<tr>
				<td class="title">商家提审时间</td>
				<td class="title">审核时间</td>
				<td class="title">品牌名称</td>
				<td class="title">品牌状态</td>
				<td class="title">内部备注</td>
			</tr>
			<c:forEach var="mchtProductBrand" items="${blackMchtProductBrands}">
			<tr>
				<td  colspan="1">
					<fmt:formatDate value="${mchtProductBrand.zsCommitAuditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtProductBrand.zsAuditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					${mchtProductBrand.productBrandName}
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<c:if test="${mchtProductBrand.zsAuditStatus == '5'}">
						不签约
					</c:if>
					<c:if test="${mchtProductBrand.zsAuditStatus == '6'}">
						黑名单
					</c:if>
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					${mchtProductBrand.zsAuditInnerRemarks }
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		<br>
		<br>
		<div><span class="table-title" >品牌审核</span></div>
		<br>
		<c:forEach var="mchtProductBrandCustom" items="${mchtProductBrandCustoms}" varStatus="index">
		<table class="gridtable" name="mchtProductBrandTable">
			<tbody>
				<tr>
					<td  colspan="1" class="title" style="width:200px;">申请品牌名称*</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<input name="productBrandName" mchtProductBrandId="${mchtProductBrandCustom.id}" value="${mchtProductBrandCustom.productBrandName}">
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">资质类型</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<c:if test="${mchtProductBrandCustom.aptitudeType == '1'}">
							自有商标
						</c:if>
						<c:if test="${mchtProductBrandCustom.aptitudeType == '2'}">
							品牌商授权
						</c:if>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">LOGO图片</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<img alt="" src="${pageContext.request.contextPath}/file_servelt${mchtProductBrandCustom.logo}">
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">商标注册证或受理通知书</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<c:forEach var="mchtBrandAptitudeCustom" items="${mchtProductBrandCustom.mchtBrandAptitudeCustoms}" varStatus="idx">
						<table class="gridtable" style="margin-top: 10px;" mchtBrandAptitudeId="${mchtBrandAptitudeCustom.id}">
							<tbody>
								<tr>
									<td  colspan="1" class="title" style="width:150px;">商标注册证号</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										${mchtBrandAptitudeCustom.certificateNo}
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">本商标注册证相关文件</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										<t:imageList name="mchtBrandAptitudePicstures${index.index}${idx.index}" list="${mchtBrandAptitudeCustom.mchtBrandAptitudePics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
										<div style="float: left;height: 105px;margin: 10px;">
							    			<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandAptitudePicstures${index.index}${idx.index}" name="file" onchange="ajaxFileUploadImg(this);" /> 
											<input type="button" style="width: 70px;" value="上传图片" /> 
							    		</div>
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">商家注册证有效期</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										<fmt:formatDate value="${mchtBrandAptitudeCustom.aptitudeExpDate}" pattern="yyyy-MM-dd"/>
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>	
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">销售授权书</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<t:imageList name="mchtPlatformAuthPictures${index.index}" list="${mchtProductBrandCustom.mchtPlatformAuthPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<div style="float: left;height: 105px;margin: 10px;">
							<input style="position:absolute; opacity:0;width: 110px;" type="file" id="PlatformAuthPictures${index.index}" name="file" onchange="ajaxFileUploadImg(this);" /> 
							<input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">授权期限</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<fmt:formatDate value="${mchtProductBrandCustom.platformAuthExpDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">进货发票</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<t:imageList name="mchtBrandInvoicePictures${index.index}" list="${mchtProductBrandCustom.mchtBrandInvoicePics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<div style="float: left;height: 105px;margin: 10px;">
							<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandInvoicePictures${index.index}" name="file" onchange="ajaxFileUploadImg(this);" /> 
							<input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">质检报告/检疫报告</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<t:imageList name="mchtBrandInspectionPictures${index.index}" list="${mchtProductBrandCustom.mchtBrandInspectionPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<div style="float: left;height: 105px;margin: 10px;">
							<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandInspectionPictures${index.index}" name="file" onchange="ajaxFileUploadImg(this);" /> 
							<input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">质检报告/检疫报告有效期</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<fmt:formatDate value="${mchtProductBrandCustom.inspectionExpDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">其他资质</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<t:imageList name="mchtBrandOtherPictures${index.index}" list="${mchtProductBrandCustom.mchtBrandOtherPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
						<div style="float: left;height: 105px;margin: 10px;">
							<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandOtherPictures${index.index}" name="file" onchange="ajaxFileUploadImg(this);" /> 
							<input type="button" style="width: 70px;" value="上传图片" /> 
						</div>
					</td>
				</tr>
				<tr>
					<td  colspan="1" class="title">其他资质有效期</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<fmt:formatDate value="${mchtProductBrandCustom.otherExpDate}" pattern="yyyy-MM-dd"/>
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
								<c:forEach var="mchtBrandProductTypeCustom" items="${mchtProductBrandCustom.mchtBrandProductTypeCustoms}">
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
					<td  colspan="1" class="title">技术服务费率</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<input name="popCommissionRate" value="${feeRate}">
					</td>
				</tr>
					<%--<c:if test="${mchtInfo.mchtType == 1}">
                    <tr>
                        <td  colspan="1" class="title">SPOP定价方式 *</td>
                        <td  colspan="3" align="left" class="l-table-edit-td">
                            <select name="priceModel">
                                <option value="">选择定价方式</option>
                                <option value="1" <c:if test="${mchtProductBrandCustom.priceModel == '1'}">selected="selected"</c:if>>吊牌价*比例</option>
                                <option value="2" <c:if test="${mchtProductBrandCustom.priceModel == '2'}">selected="selected"</c:if>>代理价</option>
                                <option value="3" <c:if test="${mchtProductBrandCustom.priceModel == '3'}">selected="selected"</c:if>>售价*比例</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td  colspan="1" class="title">定价说明</td>
                        <td  colspan="3" align="left" class="l-table-edit-td">
                            <textarea rows="7" cols="70"></textarea>
                        </td>
                    </tr>
                    </c:if>--%>
				<tr>
					<td  colspan="1" class="title">招商确认结果 *</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<input type="radio" name="zsAuditStatus${index.index}" value="2" <c:if test="${mchtProductBrandCustom.zsAuditStatus == 2}">checked="checked"</c:if>>通过
						<input type="radio" name="zsAuditStatus${index.index}" value="4" <c:if test="${mchtProductBrandCustom.zsAuditStatus == 4}">checked="checked"</c:if>>驳回
						<input type="radio" name="zsAuditStatus${index.index}" value="5" <c:if test="${mchtProductBrandCustom.zsAuditStatus == 5}">checked="checked"</c:if>>不签约
						<input type="radio" name="zsAuditStatus${index.index}" value="6" <c:if test="${mchtProductBrandCustom.zsAuditStatus == 6}">checked="checked"</c:if>>黑名单
					</td>
				</tr>
				<tr>
					<td colspan="1" class="title">备注/驳回原因</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<textarea rows="7" cols="70">${mchtProductBrandCustom.zsAuditRemarks }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="title">内部备注</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
						<textarea rows="7" cols="70">${mchtProductBrandCustom.zsAuditInnerRemarks}</textarea>
					</td>
				</tr>
			</tbody>
		</table>
		</c:forEach>
	
	<br>
	<br>
	<div><span class="table-title">招商总确认</span></div>
	<br>
	<table class="gridtable">
		<tr>
			<td  colspan="1" class="title" style="width:200px;">招商确认结果</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				<input name="zsTotalAuditStatus" type="radio" value="2" <c:if test="${mchtInfo.zsTotalAuditStatus =='2'}">checked="checked"</c:if>>通过
				<input name="zsTotalAuditStatus" type="radio" value="3" <c:if test="${mchtInfo.zsTotalAuditStatus =='3'}">checked="checked"</c:if>>驳回
				<input name="zsTotalAuditStatus" type="radio" value="5" <c:if test="${mchtInfo.zsTotalAuditStatus =='5'}">checked="checked"</c:if>>不签约
				<input name="zsTotalAuditStatus" type="radio" value="6" <c:if test="${mchtInfo.zsTotalAuditStatus =='6'}">checked="checked"</c:if>>黑名单
			</td>
		</tr>
		<tr>
			<td  colspan="1" class="title">备注/驳回内容</td>
			<td  colspan="3" align="left" class="l-table-edit-td">
				<textarea rows="7" cols="70" id="zsTotalAuditRemarks" name="zsTotalAuditRemarks">${mchtInfo.zsTotalAuditRemarks }</textarea>
			</td>
		</tr>
		<tr>
	        <td class="title">操作</td>
	        <td>
	        	<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
					</div>
			</td>
	    </tr>
	</table>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</form>		
</body>
</html>
