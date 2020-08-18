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
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}

.video_box {
    background: #fff;
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
.panel {
    margin-bottom: 14px;
    background-color: #fff;
    border: 1px solid transparent;
    border-radius: 4px;
    -webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.panel-default {
    border-color: #ddd;
}
.modal-header {
    height: 34px;
    line-height: 34px;
    padding: 0;
    border-bottom: 1px solid #ddd;
}
.modal-title {
    line-height: 34px;
    font-size: 12px;
    font-weight: bold;
    padding-left: 10px;
}
</style>

<script type="text/javascript">
function addBrand(){
	var productType1 = $("#productType1").val();
	if(!productType1){
		commUtil.alertError("请选择一级分类");
		return false;
	}
	$("#add1").empty();
	$("#add2").empty();
	$("#add3").empty();
	var $op1 = $("#productType1").find("option:selected");
	var op1 = '<option value="'+$op1.val()+'" selected>'+$op1.text()+'</option>';
	var op2 = [];
	$("#productType2Ids").find("option:selected").each(function(){
		op2.push('<option value="'+$(this).val()+'" selected>'+$(this).text()+'</option>');
	});
	var op3 = [];
	$("#productType3Ids").find("option:selected").each(function(){
		op3.push('<option value="'+$(this).val()+'" selected>'+$(this).text()+'</option>');
	});
	$("#add1").append(op1);
	$("#add2").append(op2.join());
	$("#add3").append(op3.join());
	$("#brandList").empty();
	$("#addBrandDiv").show();
	$(".black_box").show();
}

function searchBrand(){
	var productType1Ids = $("#add1").val();
	var productType2Ids = "";
	$("#add2").find("option:selected").each(function(index){
		if(index!=$("#add2").find("option:selected").length-1){
			productType2Ids+=$(this).val()+",";
		}else{
			productType2Ids+=$(this).val();
		}
	});
	var productType3Ids = "";
	$("#add3").find("option:selected").each(function(index){
		if(index!=$("#add3").find("option:selected").length-1){
			productType3Ids+=$(this).val()+",";
		}else{
			productType3Ids+=$(this).val();
		}
	});
	var productbrandIdArray = [];
	$("a[name='deleteProductBrand']").each(function(){
		var productbrandId = $(this).attr("productbrandId");
		productbrandIdArray.push(productbrandId);
	});
	var brandIds = productbrandIdArray.join(",");
	var searchBrandName = $("#searchBrandName").val();
	$.ajax({
		url : "${pageContext.request.contextPath}/customPage/searchBrand.shtml?productType1Ids="+productType1Ids+"&productType2Ids="+productType2Ids+"&brandIds="+brandIds+"&productType3Ids="+productType3Ids+"&searchBrandName="+searchBrandName,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				$("#brandList").empty();
				var brandList = data.brandList;
				var html=[];
				for(var i=0;i<brandList.length;i++){
					var id = brandList[i].id;
					var name = brandList[i].name;
					html.push('<span>'+name+'</span>&nbsp;<a href="javascript:;" name="add" brandId="'+id+'" brandName="'+name+'">添加</a>&nbsp;&nbsp;&nbsp;');
				}
				$("#brandList").html(html.join());
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

$(function ()  {
	
	var mallCategorySubId = $("#mallCategorySubId").val();
	if(mallCategorySubId){
		var productType2Ids = '${mallCategorySub.productType2Ids}';
		var productType2IdsArray = productType2Ids.split(",");
		for(var i=0;i<productType2IdsArray.length;i++){
			$("#productType2Ids").find("option[value='"+productType2IdsArray[i]+"']").attr("selected",true);
		}
		var productType3Ids = '${mallCategorySub.productType3Ids}';
		if(productType3Ids){
			var productType3IdsArray = productType3Ids.split(",");
			for(var i=0;i<productType3IdsArray.length;i++){
				$("#productType3Ids").find("option[value='"+productType3IdsArray[i]+"']").attr("selected",true);
			}
		}
	}
	
	
	var submitting;
	$("#confirm").bind('click',function(){
		if(submitting){
			return false;
		}
		var id = $("#mallCategorySubId").val();
		var name = $("#name").val();
		var suitSex = $("#suitSex").val();
		if(!name){
			commUtil.alertError("名称不能为空");
			return false;
		}
		var productType1 = $("#productType1").val();
		if(!productType1){
			commUtil.alertError("请选择一级分类");
			return false;
		}
		var productType2Ids = "";
		$("#productType2Ids").find("option:selected").each(function(index){
			if(index!=$("#productType2Ids").find("option:selected").length-1){
				productType2Ids+=$(this).val()+",";
			}else{
				productType2Ids+=$(this).val();
			}
		});
		var productType3Ids = "";
		$("#productType3Ids").find("option:selected").each(function(index){
			if(index!=$("#productType3Ids").find("option:selected").length-1){
				productType3Ids+=$(this).val()+",";
			}else{
				productType3Ids+=$(this).val();
			}
		});
		var productBrandIds = "";
		$("a[name='deleteProductBrand']").each(function(index){
			if(index!=$("a[name='deleteProductBrand']").length-1){
				productBrandIds+=$(this).attr("productbrandid")+",";
			}else{
				productBrandIds+=$(this).attr("productbrandid");
			}
		});
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtShopManager/mallCategorySub/save.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"name":name,"productType1":productType1,"productType2Ids":productType2Ids,"productBrandIds":productBrandIds,"productType3Ids":productType3Ids,"suitSex":suitSex},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("提交成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				submitting = false;
			},
			error : function() {
				submitting = false;
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
	});
	
	$("#productType1").bind('change',function(){
		var productTypeId = $(this).val();
		if(productTypeId){
			$("#selectBrandsDiv").empty();
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/getProductTypes.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"productTypeId":productTypeId},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var productTypes = data.productTypes;
						var optionArray = [];
						optionArray.push('<option value="">不限</option>');
						for(var i=0;i<productTypes.length;i++){
							var id = productTypes[i].id;
							var name = productTypes[i].name;
							optionArray.push('<option value="'+id+'" name="secondProductType">'+name+'</option>');
						}
						$("#productType2Ids").html(optionArray.join(""));
						$("#productType3Ids").html("");
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			$("#productType2Ids").html('<option value="">不限</option>');
			$("#productType3Ids").html('<option value="">不限</option>');
		}
	});
	
	$("option[name='secondProductType']").live('click',function(){
		var productTypeIds="";
		$("option[name='secondProductType']:selected").each(function(index){
			if(index!=$("option[name='secondProductType']:selected").length-1){
				productTypeIds+=$(this).val()+",";
			}else{
				productTypeIds+=$(this).val();
			}
		});
		if(productTypeIds){
			$("#selectBrandsDiv").empty();
			$.ajax({
				url : "${pageContext.request.contextPath}/mchtShopManager/getThirdProductTypes.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"productTypeIds":productTypeIds},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var thirdProductTypes = data.thirdProductTypes;
						var optionArray = [];
						optionArray.push('<option value="">不限</option>');
						for(var i=0;i<thirdProductTypes.length;i++){
							var id = thirdProductTypes[i].id;
							var name = thirdProductTypes[i].name;
							optionArray.push('<option value="'+id+'" name="thirdProductType">'+name+'</option>');
						}
						$("#productType3Ids").html(optionArray.join(""));
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	});	
	
	$("a[name='deleteProductBrand']").live('click',function(){
		$(this).parent().remove();
	});
	
	$(".video_close").bind('click',function(){
		$("#addBrandDiv").hide();
		$(".black_box").hide();
	});
	
	$("a[name='add']").live('click',function(){
		var $this = $(this);
		var text = $this.text();
		if(text == "已添加"){
			return false;
		}
		$this.text("已添加");
		var brandId = $this.attr("brandId");
		var brandName = $this.attr("brandName");
		var hasAdd = false;
		$("a[name='deleteProductBrand']").each(function(){
			var productbrandId = $(this).attr("productbrandId");
			if(brandId == productbrandId){
				hasAdd = true;
				return;
			}
		});
		if(!hasAdd){
			$("#selectBrandsDiv").append('<span>'+brandName+'&nbsp;<a href="javascript:;" style="color: blue;" name="deleteProductBrand" productBrandId="'+brandId+'">删</a>&nbsp;&nbsp;&nbsp;</span>');
		}
	});
});

</script>

<html>
<body>
	<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/mchtShopManager/mallCategorySub/save.shtml">
		<input type="hidden" id="mallCategorySubId" value="${mallCategorySubId}">
		<table class="gridtable">
			<tr>
               <td class="title">名称</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="name" name="name" value="${mallCategorySub.name}" maxlength="8">
               		<span style="color: gray;">字数不超过8个字</span>
               </td>
			</tr>
           	<tr>
               <td class="title">性别</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<select id="suitSex" name="suitSex">
               		<option value="">请选择</option>
               		<option value="10" <c:if test="${mallCategorySub.suitSex eq '10'}">selected="selected"</c:if>>男</option>
               		<option value="01" <c:if test="${mallCategorySub.suitSex eq '01'}">selected="selected"</c:if>>女</option>
               		<option value="11" <c:if test="${mallCategorySub.suitSex eq '11'}">selected="selected"</c:if>>男丶女</option>
               	</select>
               </td>
			</tr>
           	<tr>
               <td class="title">一级分类</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<select id="productType1" name="productType1">
               		<option value="">请选择</option>
               		<c:forEach items="${productTypes}" var="productType">
	               		<option value="${productType.id}" <c:if test="${productType.id == mallCategorySub.productType1}">selected="selected"</c:if>>${productType.name}</option>
               		</c:forEach>
               	</select>
               </td>
			</tr>
			<tr>
               <td class="title">二级分类</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<select id="productType2Ids" name="productType2Ids" multiple="multiple" style="width: 200px;height: 200px;">
               		<option value="">请选择</option>
               		<c:forEach items="${secondProductTypes}" var="secondProductType">
               			<option value="${secondProductType.id}" name="secondProductType">${secondProductType.name}</option>
               		</c:forEach>
               	</select>
               </td>
			</tr>
			<tr>
               <td class="title">三级分类</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<select id="productType3Ids" name="productType3Ids" multiple="multiple" style="width: 200px;height: 200px;">
               		<option value="">请选择</option>
               		<c:forEach items="${thirdProductTypes}" var="thirdProductType">
               			<option value="${thirdProductType.id}">${thirdProductType.name}</option>
               		</c:forEach>
               	</select>
               </td>
			</tr>
			<tr>
               <td class="title">品牌</td>
               <td colspan="2" align="left" class="l-table-edit-td" id="selectBrands">
               		<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="添加品牌" onclick="addBrand();"><br>
               		<div id="selectBrandsDiv">
               		<c:forEach items="${productBrands}" var="productBrand">
	               		<span>${productBrand.name}&nbsp;<a href="javascript:;" style="color: blue;" name="deleteProductBrand" productBrandId="${productBrand.id}">删</a>&nbsp;&nbsp;&nbsp;</span>
               		</c:forEach>
               		</div>
               </td>
			</tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
	</form>
<div class="video_box" style="position:fixed; width:750px; height:500px; top:5%; left:20%; display: none;border-radius: 2px;" id="addBrandDiv">
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/close.png"></a>
	<div class="panel panel-default" style="margin-bottom:0px;">
		<div class="modal-header">
			<h3 class="modal-title">
				添加品牌
			</h3>
		</div>
		<table class="gridtable">
	           	<tr>
	               <td class="title">一级分类</td>
	               <td colspan="2" align="left" class="l-table-edit-td">
	               	<select id="add1" name="add1">
	               		
	               	</select>
	               </td>
				</tr>
				<tr>
	               <td class="title">二级分类</td>
	               <td colspan="2" align="left" class="l-table-edit-td">
	               	<select id="add2" name="add2" multiple="multiple" style="width: 200px;height: 100px;">
	               		
	               	</select>
	               </td>
				</tr>
				<tr>
	               <td class="title">三级分类</td>
	               <td colspan="2" align="left" class="l-table-edit-td">
	               	<select id="add3" name="add3" multiple="multiple" style="width: 200px;height: 100px;">
	               		
	               	</select>
	               	<br>
	               	<br>
	               	<input type="text" name="searchBrandName" id="searchBrandName" style="height: 30px;width: 200px;">
	               	<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="搜索品牌" onclick="searchBrand();">
	               </td>
				</tr>
				<tr>
	               <td class="title">品牌</td>
	               <td colspan="2" align="left" class="l-table-edit-td" id="brandList" style="height:150px;">
	               		
	               </td>
				</tr>
		 </table>
	 </div>
</div>
<!--弹出div End-->
<div class="black_box" style="display: none;"></div>	
</body>
</html>
