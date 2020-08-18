<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}
</style>
<script type="text/javascript">
function changeShopType(){
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


$(function(){
	setDisableFiled();
	
	$("#confirm").bind('click',function(){
		var shopName = $("#shopName").val();
		if(!shopName){
			commUtil.alertError("店铺名称不能为空");
			return false;
		}
		if(shopName.length>20){
			commUtil.alertError("店铺名设置过长。");
			return false;
		}
		var productTypeId = $("#productTypeId").val();
		if(!productTypeId){
			commUtil.alertError("请选择店铺经营类目");
			return false;
		}
		var businessFirms = $("#businessFirms").val();
		if(businessFirms){
			var companyName = "${mchtInfo.companyName}";
			if(companyName.indexOf(businessFirms)<0){
				commUtil.alertError("公司字号必须是填写公司名称中的一部分，否则不能提交");
				return;
			}
		}
		var shopType=$('#shopType').val();
		if(shopType == '6'){
			if(!businessFirms){
				commUtil.alertError("公司字号必填");
				return;
			}
			var productType=$('#productType').val();
			if(!productType){
				commUtil.alertError("品类必填");
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
			commUtil.alertError("当前店铺名称含有"+errorWord+"(敏感词)该词不能作为店铺名使用。");
			return;
		}
		</c:if>
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/editShopName.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {
					mchtId:$("#mchtId").val(),
					shopType:$("#shopType").val(),
					businessFirms:$("#businessFirms").val(),
					brand:$("#brand").val(),
					productType:$("#productType").val(),
					shopName:$("#shopName").val(),
					productTypeId:$("#productTypeId").val(),
					other:$("#other").val()
					},
			timeout : 30000,
			success : function(data) {
				if(data.returnCode == '0000'){
					commUtil.alertSuccess("修改成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
});
</script>
<html>
<body>
	<div class="title-top">
	<input type="hidden" id="mchtId" value="${mchtId}">
		<table class="gridtable">
			<c:if test="${mchtInfo.settledType eq 1}">
			<tr>
				<td class="title">店铺类型</td>
				<td colspan="2" >
				   <select  name="shopType" id="shopType" onchange="changeShopType();">
				   	   <option value="">--请选择--</option>
					   <c:forEach var="shopTypeStatus" items="${shopTypeStatusList}">
					   		<option value="${shopTypeStatus.statusValue}"   <c:if test="${mchtInfo.shopType==shopTypeStatus.statusValue}">selected</c:if>>${shopTypeStatus.statusDesc}</option>
					   </c:forEach>
                   </select>
                   <span style="color:#999999;">开设旗舰店如不是自主品牌，须取得品牌商的旗舰店独家授权</span>
				</td>
			</tr>
					
		    <tr>
				<td class="title">公司字号</td>
				<td colspan="2" >
					<input type="text" id="businessFirms" name="businessFirms" onkeyup="generateShopName();" value="${mchtInfo.businessFirms}" >
					<span style="color:#999999;">例：厦门聚买网络科技有限公司，字号：聚买</span>
				</td>
			</tr>
		    <tr>
				<td class="title">品牌</td>
				<td colspan="2">
					<input type="text" id="brand" name="brand" onkeyup="generateShopName();" value="${mchtInfo.brand}" >
					<span style="color:#999999;">填写须与商标证书名称一致</span>
				</td>
			</tr>
		    <tr>
				<td class="title">品类</td>
				<td colspan="2" class="text-left">
					<input type="text" id="productType" name="productType" onkeyup="generateShopName();" value="${mchtInfo.productType}" >
					<span style="color:#999999;">只能选择一种品类，且要与商标类目一致</span>
				</td>
			</tr>
			
			<tr>
				<td class="title">店铺名</td>
				<td colspan="2" class="text-left">
					<input type="text" id="shopName" name="shopName" value="${mchtInfo.shopName}">
				</td>
			</tr>
			
			
			
			<tr>
				<td class="title">合作模式</td>
				<td colspan="2">
					<span style="padding: 0 10px;">${mchtInfo.mchtTypeDesc}</span> 
				</td>
			</tr>
			
			<tr>
				<td class="title">店铺经营类目</td>
				<td colspan="2" >
					<select name="productTypeId" id="productTypeId" <c:if test="${not empty mchtProductType.productTypeId}">disabled="disabled"</c:if>>
					   <option value="">--请选择--</option>
					   <c:forEach var="productType" items="${productTypes}">
					   		<option value="${productType.id}" <c:if test="${mchtProductType.productTypeId eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
					   </c:forEach>
                    </select>
				</td>
			</tr>
			
			<tr>
				<td class="title">简要描述</td>
				<td colspan="2" >
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
			</c:if>
			
			<c:if test="${mchtInfo.settledType eq 2}">
			<tr>
				<td class="editfield-label-td">店铺类型</td>
				<td colspan="2" class="text-left">
				   <select  name="shopType" id="shopType" validate="{required:true}" onchange="changeShopType();">
				   	   <option value="6" <c:if test="${mchtInfo.shopType eq 6}">selected="selected"</c:if>>规范命名</option>
				   	   <option value="7" <c:if test="${mchtInfo.shopType eq 7}">selected="selected"</c:if>>自主命名</option>
                        </select>
                        <span style="color:#999999;">开设旗舰店如不是自主品牌，须取得品牌商的旗舰店独家授权</span>
				</td>
			</tr>
			
		    <tr id="businessFirmsTr">
				<td class="editfield-label-td">公司字号</td>
				<td colspan="2" class="text-left">
					<input   type="text" id="businessFirms" name="businessFirms" onkeyup="generateShopName();" value="${mchtInfo.businessFirms}">
					<span style="color:#999999;">例：厦门聚买网络科技有限公司，字号：聚买</span>
				</td>
			</tr>
		    <tr id="productTypeTr">
				<td class="editfield-label-td">品类</td>
				<td colspan="2" class="text-left">
					<input   type="text" id="productType" name="productType" onkeyup="generateShopName();" value="${mchtInfo.productType}">
					<span style="color:#999999;">只能选择一种品类，且要与商标类目一致</span>
				</td>
			</tr>
			
		    <tr id="otherTr">
				<td class="editfield-label-td">其他</td>
				<td colspan="2" class="text-left">
					<input type="text" id="other" name="other" onkeyup="generateShopName();" value="${mchtInfo.other}">
				</td>
			</tr>
			
			<tr>
				<td class="editfield-label-td">店铺名</td>
				<td colspan="2" class="text-left">
					<input type="text" id="shopName" name="shopName" value="${mchtInfo.shopName}" validate="{required:true}" maxlength="20" <c:if test="${mchtInfo.shopType eq 6}">readonly="readonly"</c:if>>
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
					<select name="productTypeId" id="productTypeId" validate="{required:true}" <c:if test="${not empty mchtProductType.productTypeId}">disabled="disabled"</c:if>>
					   <option value="">--请选择--</option>
					   <c:forEach var="productType" items="${productTypes}">
					   		<option value="${productType.id}" <c:if test="${mchtProductType.productTypeId eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
					   </c:forEach>
                        	</select>
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
			</c:if>
			
          	<tr>
          		<td class="title">操作</td>
          		<td>
					<input id="confirm" type="button" style="float:left;" class="l-button l-button-test" value="确定"/>
				</td>
          	</tr>
        </table>
	</div>
	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
