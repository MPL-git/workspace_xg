<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>店铺信息修改</title>
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
        <button type="button" style="-webkit-appearance: none;padding: 0;cursor: pointer;background: 0 0;border: 0;" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">设置店铺名</span>
      </div>
			<div class="modal-body">
		<form id="dataFrom">
		<input type="hidden" value="${cooperationChangeApply.id}" id="id" name="id">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">店铺类型</td>
						<td colspan="2" class="text-left">
						   <select  name="shopType" id="shopType" validate="{required:true}" onchange="changeShopType();">
						   <option value="">--请选择--</option>
						   <c:forEach var="shopTypeStatus" items="${shopTypeStatusList }">
						   <option value="${shopTypeStatus.statusValue}"   <c:if test="${cooperationChangeApply.shopType==shopTypeStatus.statusValue}">selected</c:if>>${shopTypeStatus.statusDesc}</option>
						   </c:forEach>
                          </select>
                          <span style="color:#999999;">开设旗舰店如不是自主品牌，须取得品牌商的旗舰店独家授权</span>
						</td>
					</tr>
					
					
					
				    <tr>
						<td class="editfield-label-td">公司字号</td>
						<td colspan="2" class="text-left">
							<input type="text" id="businessFirms" name="businessFirms" onkeyup="generateShopName();" value="${cooperationChangeApply.businessFirms}" validate="{required:true}">
							<span style="color:#999999;">例：厦门聚买网络科技有限公司，字号：聚买</span>
						</td>
					</tr>
				    <tr>
						<td class="editfield-label-td">品牌</td>
						<td colspan="2" class="text-left">
							<input type="text" id="brand" name="brand" onkeyup="generateShopName();" value="${cooperationChangeApply.brand}" validate="{required:true}">
							<span style="color:#999999;">填写须与商标证书名称一致</span>
						</td>
					</tr>
				    <tr>
						<td class="editfield-label-td">品类</td>
						<td colspan="2" class="text-left">
							<input type="text" id="productType" name="productType" onkeyup="generateShopName();" value="${cooperationChangeApply.productType}" validate="{required:true}">
							<span style="color:#999999;">只能选择一种品类，且要与商标类目一致</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">店铺名</td>
						<td colspan="2" class="text-left">
						<input type="text" id="shopName" name="shopName" value="${cooperationChangeApply.shopName}" validate="{required:true}" readonly="readonly">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">合作模式</td>
						<td colspan="2" class="text-left">
						<span style="padding: 0 10px;">${mchtInfo.mchtTypeDesc}</span> 
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
		
	<div class="modal-footer">
			<button class="btn btn-info" onclick="commitAudit();" <c:if test="${mchtInfo.shopNameAuditStatus == 2 || mchtInfo.shopNameAuditStatus == 3 || mchtInfo.zsTotalAuditStatus == 2}">disabled="disabled"</c:if>>提交</button>
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


function commitAudit(){

	if(dataFromValidate.form()){
		var productTypeId = $("#productTypeId").val();
		if(!productTypeId){
			swal("请选择店铺经营类目");
			return;
		}
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
					productTypeId:$("#productTypeId").val()
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

</script>
</body>
</html>
