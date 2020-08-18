<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>设置店铺名</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
	<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
	<!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->



</head>

<body>

<div class="modal-dialog" role="document" style="width:700px;">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<span class="modal-title" id="exampleModalLabel">设置店铺名</span>
		</div>
		<div class="modal-body">
			<form id="dataFrom">
				<input type="hidden" value="${mchtInfo.id}" id="id" name="id">
				<input type="hidden" value="${mchtInfo.shopNameAuditStatus}" id="shopNameAuditStatus" name="shopNameAuditStatus">
				<input type="hidden" value="${mchtInfo.zsTotalAuditStatus}" id="zsTotalAuditStatus" name="zsTotalAuditStatus">
				<div class="table-responsive">
					<table class="table table-bordered ">
						<tbody>
						<c:if test="${mchtInfo.settledType eq 1}">
							<tr>
								<td class="editfield-label-td">店铺类型</td>
								<td colspan="2" class="text-left">
									<select  name="shopType" id="shopType" validate="{required:true}" onchange="changeShopType();" <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2 || mchtInfo.isManageSelf eq 1}">disabled="disabled"</c:if>>
										<option value="">--请选择--</option>
										<c:forEach var="shopTypeStatus"  items="${shopTypeStatusList }">
											<c:if test="${shopTypeStatus.statusValue != 6 && shopTypeStatus.statusValue != 7}">
											<option value="${shopTypeStatus.statusValue}"   <c:if test="${mchtInfo.shopType==shopTypeStatus.statusValue}">selected</c:if> >${shopTypeStatus.statusDesc}</option>
											</c:if>
										</c:forEach>
									</select>
									<span style="color:#999999;">开设旗舰店如不是自主品牌，须取得品牌商的旗舰店独家授权</span>
								</td>
							</tr>
						<c:if test="${mchtInfo.isManageSelf ne 1}">
							<tr id="notManageSelfBusinessFirms">
								<td class="editfield-label-td">公司字号</td>
								<td colspan="2" class="text-left">
									<input   type="text" id="businessFirms" name="businessFirms" onkeyup="generateShopName();" value="${mchtInfo.businessFirms}" validate="{required:true}" <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2}">disabled="disabled"</c:if>>
									<span style="color:#999999;">例：厦门聚买网络科技有限公司，字号：聚买</span>
								</td>
							</tr>
							<tr id="notManageSelfBrand">
								<td class="editfield-label-td">品牌</td>
								<td colspan="2" class="text-left">
									<input type="text" id="brand" name="brand" <c:if test="${mchtInfo.settledType eq 1}">onkeyup="generateShopName();"</c:if> value="${mchtInfo.brand}" validate="{required:true}" <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2 || mchtInfo.totalAuditStatus == 3}">disabled="disabled"</c:if>>
									<span style="color:#999999;">填写须与商标证书名称一致</span>
								</td>
							</tr>
							<tr id="notManageSelfProductType">
								<td class="editfield-label-td">品类</td>
								<td colspan="2" class="text-left">
									<input   type="text" id="productType" name="productType" onkeyup="generateShopName();" value="${mchtInfo.productType}" validate="{required:true}" <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2}">disabled="disabled"</c:if>>
									<span style="color:#999999;">只能选择一种品类，且要与商标类目一致</span>
								</td>
							</tr>
						</c:if>
							<tr>
								<td class="editfield-label-td">店铺名</td>
								<td colspan="2" class="text-left">
									<input   type="text" id="shopName" name="shopName" value="${mchtInfo.shopName}" validate="{required:true,maxlength:20}"  <c:if test="${mchtInfo.isManageSelf == 0}">readonly="readonly"</c:if>>
								</td>
							</tr>
							<tr>
								<td class="editfield-label-td">合作模式</td>
								<td colspan="2" class="text-left">
									<span style="padding: 0 10px;"><span id="cooperationMode">自营</span>${mchtInfo.mchtTypeDesc}</span>
								</td>
							</tr>


							<tr>
								<td class="editfield-label-td">店铺经营类目</td>
								<td colspan="2" class="text-left">
									<select name="productTypeId" id="productTypeId" onchange="productTypelist();" validate="{required:true}" <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2}">disabled="disabled"</c:if>>
										<c:forEach var="productType" items="${productTypes}">
											<option value="${productType.id}" <c:if test="${mchtProductType.productTypeId eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>

							<tr>
								<td class="editfield-label-td">商品主要经营类目</td>
								<td colspan="2" class="text-left">
									<select name="productType2Id" id="productType2Id" style="width:120px;" validate="{required:true}" <c:if test="${mchtInfo.zsTotalAuditStatus == 0 || mchtInfo.zsTotalAuditStatus == 2 || mchtInfo.totalAuditStatus == 0 || mchtInfo.totalAuditStatus == 1 || mchtInfo.totalAuditStatus == 2}">disabled="disabled"</c:if>>
										<option value=""></option>
										<c:forEach var="productType" items="${productTypes2}">
											<option value="${productType.id}" <c:if test="${mchtInfo.productType2Id eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
										</c:forEach>
									</select>
									此品类为您的店铺中最主要的品类
								</td>
							</tr>

							<tr>
								<td class="editfield-label-td">经营许可证</td>
								<td colspan="2" class="text-left">
									<div style="color: #999999;">
										图片最多可上传1张且图片格式为JPG。每天图片大小不超过3M
									</div>
									<c:if test='${mchtInfo.businessLicensePic!=null&&mchtInfo.businessLicensePic!=""}'>
										<div class="single_pic_picker">
											<input id="businessLicensePicFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfo.businessLicensePic}">
											<input type="hidden"  id="businessLicensePic" name="businessLicensePic" value="${mchtInfo.businessLicensePic}">
										</div>
									</c:if>
									<c:if test='${mchtInfo.businessLicensePic==null||mchtInfo.businessLicensePic==""}'>
										<div class="single_pic_picker">
											<input id="businessLicensePicFile" onchange="loadImageFile(this)" type="file"><div>+</div>
											<input type="hidden"  id="businessLicensePic" name="businessLicensePic" value="">
										</div>
									</c:if>
								</td>
							</tr>
						<c:if test="${mchtInfo.isManageSelf ne 1}">
							<tr id="simpleDescription">
								<td class="editfield-label-td">简要描述</td>
								<td colspan="2" class="text-left">
									<c:if test="${mchtInfo.mchtType==1}">
										<div style="padding: 10px;">
											开放SPOP：<br>

											由商家自主定价销售，订单发票由商家提供并邮寄给客户；<br>

											商家负责商品的所有服务，包括售前、发货、退换货等服务；<br>

											平台为商家提供技术服务，包括销售平台技术支持和流量支持；<br>

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
						</c:if>
							<c:if test="${mchtInfo.shopNameAuditStatus == 4}">
								<tr>
									<td class="editfield-label-td" style="color: red;">驳回原因</td>
									<td colspan="2" class="text-left" style="color: red;word-break:break-all;">
											${mchtInfo.shopNameAuditRemarks}
									</td>
								</tr>
							</c:if>
						</c:if>

						<c:if test="${mchtInfo.settledType eq 2}">
							<tr>
								<td class="editfield-label-td">店铺类型</td>
								<td colspan="2" class="text-left">
									<select  name="shopType" id="shopType" validate="{required:true}" onchange="changeShopType();" <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2}">disabled="disabled"</c:if>>
										<option value="6" <c:if test="${mchtInfo.shopType eq 6}">selected="selected"</c:if>>规范命名</option>
										<option value="7" <c:if test="${mchtInfo.shopType eq 7}">selected="selected"</c:if>>自主命名</option>
									</select>
									<span style="color:#999999;">开设旗舰店如不是自主品牌，须取得品牌商的旗舰店独家授权</span>
								</td>
							</tr>

							<tr id="businessFirmsTr" <c:if test="${mchtInfo.shopType eq 7}">style="display: none;"</c:if>>
								<td class="editfield-label-td">公司字号</td>
								<td colspan="2" class="text-left">
									<input   type="text" id="businessFirms" name="businessFirms" onkeyup="generateShopName();" value="${mchtInfo.businessFirms}" <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2}">disabled="disabled"</c:if>>
									<span style="color:#999999;">例：厦门聚买网络科技有限公司，字号：聚买</span>
								</td>
							</tr>
							<tr id="productTypeTr" <c:if test="${mchtInfo.shopType eq 7}">style="display: none;"</c:if>>
								<td class="editfield-label-td">品类</td>
								<td colspan="2" class="text-left">
									<input   type="text" id="productType" name="productType" onkeyup="generateShopName();" value="${mchtInfo.productType}" <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2}">disabled="disabled"</c:if>>
									<span style="color:#999999;">只能选择一种品类，且要与商标类目一致</span>
								</td>
							</tr>
							<tr id="otherTr" <c:if test="${mchtInfo.shopType eq 7}">style="display: none;"</c:if>>
								<td class="editfield-label-td">其他</td>
								<td colspan="2" class="text-left">
									<input type="text" id="other" name="other" onkeyup="generateShopName();" value="${mchtInfo.other}" <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2}">disabled="disabled"</c:if>>
								</td>
							</tr>

							<tr>
								<td class="editfield-label-td">店铺名</td>
								<td colspan="2" class="text-left">
									<input type="text" id="shopName" name="shopName" value="${mchtInfo.shopName}" validate="{required:true}" maxlength="20" <c:if test="${mchtInfo.shopType eq 6}">readonly="readonly"</c:if> <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2}">disabled="disabled"</c:if>>
								</td>
							</tr>

							<tr>
								<td class="editfield-label-td">合作模式</td>
								<td colspan="2" class="text-left">
									<span style="padding: 0 10px;">${mchtInfo.mchtTypeDesc}</span>
								</td>
							</tr>

							<tr>
								<td class="editfield-label-td">店铺经营类目</td>
								<td colspan="2" class="text-left">
									<select name="productTypeId" id="productTypeId" onchange="productTypelist();" validate="{required:true}" <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2}">disabled="disabled"</c:if>>
										<option value="">--请选择--</option>
										<c:forEach var="productType" items="${productTypes}">
											<option value="${productType.id}" <c:if test="${mchtProductType.productTypeId eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td class="editfield-label-td">商品主要经营类目</td>
								<td colspan="2" class="text-left">
									<select name="productType2Id" id="productType2Id" style="width:120px;" validate="{required:true}" <c:if test="${mchtInfo.zsTotalAuditStatus == 0 || mchtInfo.zsTotalAuditStatus == 2 || mchtInfo.totalAuditStatus == 0 || mchtInfo.totalAuditStatus == 1 || mchtInfo.totalAuditStatus == 2}">disabled="disabled"</c:if>>
										<option value=""></option>
										<c:forEach var="productType" items="${productTypes2}">
											<option value="${productType.id}" <c:if test="${mchtInfo.productType2Id eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
										</c:forEach>
									</select>
									此品类为您的店铺中最主要的品类
								</td>
							</tr>
							<tr>
								<td class="editfield-label-td">简要描述</td>
								<td colspan="2" class="text-left">
									<c:if test="${mchtInfo.mchtType==1}">
										<div style="padding: 10px;">
											开放联营：<br>

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
						</c:if>
						</tbody>
					</table>
				</div>
			</form>

			<div class="modal-footer">
				<button class="btn btn-info" onclick="commitAudit();">提交</button>
				<button class="btn btn-info" data-dismiss="modal">取消</button>
			</div>

		</div>
	</div>
</div>





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

<script type="text/javascript">

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
		if(shopType == '6'){
			$("#shopName").attr("readonly",true);
			$("#businessFirmsTr").show();
			$("#productTypeTr").show();
			$("#otherTr").show();
		}
		if(shopType == '7'){
			$("#shopName").attr("readonly",false);
			$("#businessFirmsTr").hide();
			$("#productTypeTr").hide();
			$("#otherTr").hide();
			$("#businessFirms").val("");
			$("#productType").val("");
			$("#other").val("");
		}
		if(shopType == '8'){
			$("#shopName").attr("readonly",false);
			$("#shopName").val("");
			$("#cooperationMode").show();
			$("#notManageSelfBusinessFirms").hide();
			$('#businessFirms').attr("disabled",true);
			$("#notManageSelfBrand").hide();
			$('#brand').attr("disabled",true);
			$("#notManageSelfProductType").hide();
			$('#productType').attr("disabled",true);
			$("#simpleDescription").hide();
		}else {
			$("#shopName").attr("readonly",true);
			$("#notManageSelfBusinessFirms").show();
			$('#businessFirms').attr("disabled",false);
			$("#notManageSelfBusinessFirms").show();
			$("#notManageSelfBrand").show();
			$('#brand').attr("disabled",false);
			$("#notManageSelfProductType").show();
			$('#productType').attr("disabled",false);
			$("#simpleDescription").show();
			$("#cooperationMode").hide();
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
			if(${mchtInfo.shopNameAuditStatus== 2} || ${mchtInfo.shopNameAuditStatus== 3} || ${mchtInfo.zsTotalAuditStatus== 2} || ${mchtInfo.totalAuditStatus== 3} ){
				$('#brand').attr("disabled",true);
			}
		}
		if(shopType=='1'||shopType=='3'||shopType=='5'){
			$('#productType').attr("disabled",true);
		}else{
			$('#productType').attr("disabled",false);
		}
	}

	function generateShopName(){
		var settledType = "${mchtInfo.settledType}";
		var shopType=$('#shopType').val();
		if(settledType == 1){
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
		}else if(settledType == 2){
			if(shopType == '6'){
				$('#shopName').val($('#businessFirms').val()+$('#productType').val()+$('#other').val());
			}else if(shopType == '7'){
				$('#shopName').val("");
			}
		}
	}


	var dataFromValidate;

	$(function(){
		$.metadata.setType("attr", "validate");

		if(${mchtInfo.zsTotalAuditStatus== 3} || ${mchtInfo.totalAuditStatus== 3}){
			$("#productType2Id").attr("disabled", false);
		}
		if(${mchtInfo.zsTotalAuditStatus == 3} || ${mchtInfo.totalAuditStatus == 3}){
			$("#productTypeId").attr("disabled", false);
		}

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

	//获取二级类目
	function productTypelist() {
		var productTypeId = $("#productTypeId").val();
		if (productTypeId == '') {
			var option = [];
			$("#productType2Id").html(option.join(''));
			$("#productType2Id").attr("disabled", "disabled");
		} else {
			$.ajax({
				type: 'post',
				url: '${ctx}/mcht/getClassTwo',
				dataType: 'json',
				data: {productTypeId:productTypeId},
				success: function (data) {
					if (data.code == 200) {
						var option = [];
						for (var i = 0; i < data.productTypeList.length; i++) {
							option.push('<option value="' + data.productTypeList[i].id + '">' + data.productTypeList[i].name + '</option>');
						}
						$("#productType2Id").html(option.join(''));
					} else {
						commUtil.alertError(data.msg);
					}
				},
				error: function (e) {
					commUtil.alertError('操作超时，请稍后再试！');
				}
			});
		}
	}

	function commitAudit(){
		if(dataFromValidate.form()){
			var productTypeId = $("#productTypeId").val();
			if(!productTypeId){
				swal("请选择店铺经营类目");
				return;
			}
			var businessFirms = $("#businessFirms").val();
			if(businessFirms){
				var companyName = "${mchtInfo.companyName}";
				if(companyName.indexOf(businessFirms)<0){
					swal("公司字号必须是填写公司名称中的一部分，否则不能提交");
					return;
				}
			}
			var shopType=$('#shopType').val();
			if(shopType == '6'){
				if(!businessFirms){
					swal("公司字号必填");
					return;
				}
				var productType=$('#productType').val();
				if(!productType){
					swal("品类必填");
					return;
				}
			}
			var sensitiveWord="旗舰、专卖、专营、官方、直营、官字、官方认证、官方授权 、醒购特许、醒购授权、醒购、醒购网";
			<c:if test="${mchtInfo.settledType == 2}">
			var sensitiveWordArray = sensitiveWord.split("、");
			var shopName = $("#shopName").val();
			var error = false;
			var errorWord="";
			for(var i=0;i<sensitiveWordArray.length;i++){
				if(shopName.indexOf(sensitiveWordArray[i])>=0){
					errorWord = sensitiveWordArray[i];
					error = true;
					break;
				}
			}
			if(error){
				swal("当前店铺名称含有"+errorWord+"(敏感词)该词不能作为店铺名使用。");
				return;
			}
			</c:if>
			var shopNameAuditStatus = $("#shopNameAuditStatus").val();
			var zsTotalAuditStatus = $("#zsTotalAuditStatus").val();
			var $businessLicensePic=$("#businessLicensePic").parent().children("img");
			if($businessLicensePic.length > 0 && $businessLicensePic.attr("src") != "" && $businessLicensePic.attr("src").indexOf("data:image") == 0){//有修改
				uploadImage("businessLicensePicFile",$("#businessLicensePic"));
			}
			var aaa = $("#shopName").val();
			console.log(aaa);
			$.ajax({
				url : "${ctx}/mchtUser/changeShopInfoCommit",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					id:$("#id").val(),
					shopType:$("#shopType").val(),
					businessFirms:$("#businessFirms").val(),
					brand:$("#brand").val(),
					productType:$("#productType").val(),
					shopName:$("#shopName").val(),
					productTypeId:$("#productTypeId").val(),
					productType2Id:$("#productType2Id").val(),
					other:$("#other").val(),
					shopNameAuditStatus:shopNameAuditStatus,
					zsTotalAuditStatus:zsTotalAuditStatus,
					businessLicensePic:$("#businessLicensePic").val()
				},
				timeout : 30000,
				success : function(data) {
					if (data.returnCode=="0000") {
						isReload=true;
						$("#viewModal").modal('hide');
						swal({
							title: "提交成功!",
							type: "success",
							confirmButtonText: "确定"

						});
					} else {
						swal({
							title: data.returnMsg,
							type: "error",
							confirmButtonText: "确定"
						});
					}

				},
				error : function() {
					swal({
						title: "提交失败！",
						type: "error",
						confirmButtonText: "确定"
					});
				}
			});



		}
	}

	function loadImageFile(obj) {
		if (obj.files.length === 0) {
			return;
		}
		var oFile = obj.files[0];
		var rFilter = /^(?:image\/jpeg|image\/jpg)$/i;
		if (!rFilter.test(oFile.type)) {
			swal("图片格式不正确！");
			return;
		}
		var reader = new FileReader();
		reader.onload = function(e) {
			var image = new Image();
			image.onload = function() {
				if($(obj).parent().children("img").length<=0){
					$('<img>').appendTo( $(obj).parent() );;
				}
				$(obj).parent().children("img").attr("src",e.target.result);
				$(obj).parent().children("div").remove();
			};
			image.src = e.target.result;
		}
		reader.readAsDataURL(oFile);
	}

	//上传图片
	function uploadImage(fileElementId,$valueFiled) {
		var oFile = document.getElementById(fileElementId).files[0];
		var formData = new FormData();
		formData.append("file",oFile);
		$.ajax({
			url : "${ctx}/common/fileUpload",
			type : 'POST',
			data : formData,
			async: false,
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			beforeSend:function(){
				console.log("图片片上传中，请稍候");
			},
			success : function(data) {
				if (data.returnCode=="0000") {
					$valueFiled.val(data.returnData);
				} else {
					swal({
						title: "图片上传失败！",
						type: "error",
						confirmButtonText: "确定"
					});
				}
			},
			error : function(responseStr) {
				swal("图片上传失败");
			}
		});


	}
</script>
</body>
</html>