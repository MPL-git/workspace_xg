<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>商家合作变更申请</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
      <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
<!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->
<style type="text/css">
.video_box {
    z-index: 2222;
    display: block;
}

.black_box {
    background: #000;
    opacity: 0.6;
    left: 0;
    top: 0;
    z-index: 1111;
    position: fixed;
    height: 100%;
    width: 100%;
}
.video_close {
    position: absolute;
    top: -14px;
    right: -12px;
}
</style>
</head>

<body>

  <div class="modal-dialog" role="document" style="width:1000px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">商家合作变更申请</span>
      </div>
		<div class="modal-body">
		<form id="dataFrom">
		<input type="hidden" value="${cooperationChangeApply.id}" id="id" name="id">
		<div class="table-responsive">
			<h5>申请类型</h5>
			<span>
				<c:if test="${not empty shopNameAuth}">
					<input type="checkbox" disabled="disabled" checked="checked">店铺名称变更&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${not empty mchtProductTypeAuth}">
					<input type="checkbox" disabled="disabled" checked="checked">店铺主营类目变更&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${not empty popCommissionRateAuth}">
					<input type="checkbox" disabled="disabled" checked="checked">品牌技术服务费变更&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${not empty depositAuth}">
					<input type="checkbox" disabled="disabled" checked="checked">保证金变更&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
			</span>
		</div>
		<c:if test="${not empty shopNameAuth}">
		<div class="table-responsive">
			<h5>店铺信息修改</h5>
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td" style="background-color: #9C9C9C">项目</td>
						<td class="text-center" style="width: 42%">变更前</td>
						<td class="text-center">变更后</td>
					</tr>
					<tr>
						<td class="editfield-label-td" style="background-color: #9C9C9C">店铺名</td>
						<td class="text-center">${oldShopName}</td>
						<td class="text-center"><span id="newShopName" <c:if test="${mchtInfo.shopName ne cooperationChangeApply.shopName}">style="color: red;"</c:if>>${cooperationChangeApply.shopName}</span><a href="javascript:;" onclick="editShopInfo();">[查看]</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		</c:if>
		<c:if test="${not empty mchtProductTypeAuth}">
		<div class="table-responsive">
			<h5>店铺主营类目变更</h5>
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td" style="background-color: #9C9C9C">项目</td>
						<td class="text-center" style="width: 42%">变更前</td>
						<td class="text-center">变更后</td>
					</tr>
					<tr>
						<td class="editfield-label-td" style="background-color: #9C9C9C">店铺主营类目</td>
						<td class="text-center">${oldProductTypeName}</td>
						<td class="text-center"><span <c:if test="${oldProductTypeName ne newProductTypeName}">style="color: red;"</c:if>>${newProductTypeName}</span></td>
					</tr>
				</tbody>
			</table>
		</div>
		</c:if>
		<c:if test="${not empty depositAuth}">
		<div class="table-responsive">
			<h5>保证金变更</h5>
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td" style="background-color: #9C9C9C">项目</td>
						<td class="text-center" style="width: 42%">变更前</td>
						<td class="text-center">变更后</td>
					</tr>
					<tr>
						<td class="editfield-label-td" style="background-color: #9C9C9C">保证金变更</td>
						<td class="text-center">${totalAmt}</td>
						<td class="text-center"><span <c:if test="${totalAmt ne cooperationChangeApply.deposit}">style="color: red;"</c:if>>${cooperationChangeApply.deposit}</span></td>
					</tr>
				</tbody>
			</table>
		</div>
		</c:if>
		<c:if test="${not empty popCommissionRateAuth}">
		<div class="table-responsive">
			<h5>品牌技术服务费费率变更</h5>
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td" style="background-color: #9C9C9C">项目</td>
						<td class="text-center" style="width: 42%">变更前</td>
						<td class="text-center">变更后</td>
					</tr>
					<c:forEach items="${mchtProductBrands}" var="mchtProductBrand">
					<tr>
						<td class="editfield-label-td" style="background-color: #9C9C9C">${mchtProductBrand.productBrandName}</td>
						<td class="text-center">
							<c:if test="${status eq 0}">
							<fmt:formatNumber type="number" pattern="0.00" maxFractionDigits="2" value="${mchtProductBrand.popCommissionRate}"></fmt:formatNumber>
							</c:if>
							<c:if test="${status eq 1}">
								<c:forEach items="${mchtBrandRateChanges}" var="mchtBrandRateChange">
									<c:if test="${mchtBrandRateChange.mchtProductBrandId eq mchtProductBrand.id}">
									<span>
											${mchtBrandRateChange.prePopCommissionRate}
									</span>
									</c:if>
								</c:forEach>
							</c:if>
						</td>
						<td class="text-center">
							<c:forEach items="${mchtBrandRateChanges}" var="mchtBrandRateChange">
								<c:if test="${mchtBrandRateChange.mchtProductBrandId eq mchtProductBrand.id}">
									<span <c:if test="${mchtProductBrand.popCommissionRate ne mchtBrandRateChange.popCommissionRate}">style="color: red;"</c:if>>
										${mchtBrandRateChange.popCommissionRate}
									</span>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
		</form>
		</div>
	</div>
  </div>
<div class="video_box" style="position:fixed; width:700px; height:250px; top:20%; left:35%; display: none;" id="shopInfoDiv">
	    <div class="modal-content">
	    <div class="modal-header">
	    	<span class="modal-title" id="exampleModalLabel">店铺信息修改</span>
	    	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
	    </div>
		<div class="modal-body">
			<form id="dataFrom">
			<div class="table-responsive">
				<table class="table table-bordered ">
					<tbody>
						<tr>
							<td class="editfield-label-td">店铺类型</td>
							<td colspan="2" class="text-left">
							   <select  name="shopType" id="shopType" onchange="changeShopType();">
							   <option value="">--请选择--</option>
							   <c:forEach var="shopTypeStatus" items="${shopTypeStatusList}">
							   <option value="${shopTypeStatus.statusValue}" <c:if test="${cooperationChangeApply.shopType==shopTypeStatus.statusValue}">selected</c:if>>${shopTypeStatus.statusDesc}</option>
							   </c:forEach>
	                          </select>
	                          <span style="color:#999999;">开设旗舰店如不是自主品牌，须取得品牌商的旗舰店独家授权</span>
							</td>
						</tr>
					    <tr>
							<td class="editfield-label-td">公司字号</td>
							<td colspan="2" class="text-left">
								<input type="text" id="businessFirms" name="businessFirms" onkeyup="generateShopName();" value="${cooperationChangeApply.businessFirms}">
								<span style="color:#999999;">例：厦门聚买网络科技有限公司，字号：聚买</span>
							</td>
						</tr>
					    <tr>
							<td class="editfield-label-td">品牌</td>
							<td colspan="2" class="text-left">
								<input type="text" id="brand" name="brand" onkeyup="generateShopName();" value="${cooperationChangeApply.brand}" >
								<span style="color:#999999;">填写须与商标证书名称一致</span>
							</td>
						</tr>
					    <tr>
							<td class="editfield-label-td">品类</td>
							<td colspan="2" class="text-left">
								<input type="text" id="productType" name="productType" onkeyup="generateShopName();" value="${cooperationChangeApply.productType}" >
								<span style="color:#999999;">只能选择一种品类，且要与商标类目一致</span>
							</td>
						</tr>
						
						<tr>
							<td class="editfield-label-td">店铺名</td>
							<td colspan="2" class="text-left">
							<input type="text" id="shopName" name="shopName" value="${cooperationChangeApply.shopName}">
							</td>
						</tr>
						
						<tr>
							<td class="editfield-label-td">合作模式</td>
							<td colspan="2" class="text-left">
							<span style="padding: 0 10px;"><c:if test="${mchtInfo.mchtType == 1}">SPOP</c:if><c:if test="${mchtInfo.mchtType == 2}">POP</c:if></span>
							</td>
						</tr>
						<tr>
							<td class="editfield-label-td">简要描述</td>
							<td colspan="2" class="text-left">
							<c:if test="${mchtInfo.mchtType==1}">
							<div style="padding: 10px;">
							SPOP：<br>
							
							由商家定结算价和零售价；<br>
							
							商家开具增值税专用发票给平台；<br>
							
							订单发票由平台提供并邮寄给客户；<br>
							
							商家负责商品的所有服务，包括售前、发货、退换货等服务；<br>
							
							平台为商家提供技术服务，包括销售平台技术支持和流量支持；
							</div>
							</c:if>
							<c:if test="${mchtInfo.mchtType==2}">
							<div style="padding: 10px">
							          开放POP：<br>
	
								由商家自主定价销售，订单发票由商家提供并邮寄给客户；<br>
								
								商家负责商品的所有服务，包括售前、发货、退换货等服务；<br>
								
								平台为商家提供技术服务，包括销售平台技术支持和流量支持；<br>
							</div>
							</c:if>
							</td>
						</tr>
						<c:if test="${mchtInfo.shopNameAuditStatus == 4}">
						<tr>
							<td class="editfield-label-td" style="color: red;">驳回原因</td>
							<td colspan="2" class="text-left" style="color: red;word-break:break-all;">
								${mchtInfo.shopNameAuditRemarks}
							</td>
						</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			</form>
		</div>
		</div>
</div>    
<div class="black_box" style="display: none;" id="shopInfoBlackBox"></div>

	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<!--     <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/fileUpload.js"></script> -->
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
    <script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>	
    <script src="${pageContext.request.contextPath}/static/js/utils.js" type="text/javascript"></script>	
	
<script type="text/javascript">
function editShopInfo(){
	$("#shopInfoDiv").show();
	$("#shopInfoBlackBox").show();
}

function changeShopType(){
	dataFromValidate.resetForm();
	var shopType=$('#shopType').val();
	if(shopType=='1'||shopType=='2'){
		$('#businessFirms').attr("disabled",true);
		$('#businessFirms').val("");
	}else{
		$('#businessFirms').attr("disabled",false);
	}
	if(shopType=='4'||shopType=='5'){
		$('#brand').attr("disabled",true);
		$('#brand').val("");
	}else{
		$('#brand').attr("disabled",false);
	}
	if(shopType=='1'||shopType=='3'||shopType=='5'){
		$('#productType').attr("disabled",true);
		$('#productType').val("");
	}else{
		$('#productType').attr("disabled",false);
	}
	generateShopName();
}

function setDisableFiled(){
	var shopType=$('#shopType').val();
	if(shopType=='1'||shopType=='2'){
		$('#businessFirms').attr("disabled",true);
	}else{
		$('#businessFirms').attr("disabled",false);
	}
	if(shopType=='4'||shopType=='5'){
		$('#brand').attr("disabled",true);
	}else{
		$('#brand').attr("disabled",false);
	}
	if(shopType=='1'||shopType=='3'||shopType=='5'){
		$('#productType').attr("disabled",true);
	}else{
		$('#productType').attr("disabled",false);
	}
}

function generateShopName(){
	var shopType=$('#shopType').val();
	if(shopType=='1'){
		$('#shopName').val($('#brand').val()+"官方旗舰店");
	}
	if(shopType=='2'){
		$('#shopName').val($('#brand').val()+$('#productType').val()+"旗舰店");
	}
	if(shopType=='3'){
		$('#shopName').val($('#brand').val()+$('#businessFirms').val()+"专卖店");
	}
	if(shopType=='4'){
		$('#shopName').val($('#businessFirms').val()+$('#productType').val()+"专营店");
	}
	if(shopType=='5'){
		$('#shopName').val($('#businessFirms').val()+"官方旗舰店");
	}
}


var dataFromValidate;

$(function(){
	
	$(".video_close").on('click',function(){
		$("#shopInfoDiv").hide();
		$("#shopInfoBlackBox").hide();
	});
	
	$.metadata.setType("attr", "validate");

	dataFromValidate =$("#dataFrom").validate({
        highlight : function(element) {  
        	$(element).addClass('error');
            $(element).closest('tr').find("td").first().addClass('has-error');  
        },
        success : function(label) {  
            label.closest('tr').find("td").first().removeClass('has-error');  
        }
	});
	
	setDisableFiled();
	
});


</script>
</body>
</html>
