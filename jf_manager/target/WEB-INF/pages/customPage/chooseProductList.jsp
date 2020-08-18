<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("#productType").bind('change',function(){
		var productTypeId = $(this).val();
		if(productTypeId){
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
							optionArray.push('<option value="'+id+'">'+name+'</option>');
						}
						$("#productType2").html(optionArray.join(""));
						$("#productType3").html('<option value="">不限</option>');
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			$("#productType2").html('<option value="">不限</option>');
			$("#productType3").html('<option value="">不限</option>');
		}
	});
	
	$("#productType2").bind('change',function(){
		var productTypeId = $(this).val();
		if(productTypeId){
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
							optionArray.push('<option value="'+id+'">'+name+'</option>');
						}
						$("#productType3").html(optionArray.join(""));
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
	
	$("a[name='saveDecorateModule']").live('click',function(){
		var $this = $(this);
		if($this.text()=="已添加"){
			return false;
		}
		var decorateInfoId = $("#decorateInfoId").val();
		var decorateAreaId = $("#areaId").val();
		var decorateModuleId = $("#moduleId").val();
		var showNum = $("#showNum").val();
		var seqNo = $("#seqNo").val();
		var productId = $this.attr("productId");
		$.ajax({
			url : "${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"decorateInfoId":decorateInfoId,"decorateAreaId":decorateAreaId,"moduleType":2,"productIds":productId,"showNum":showNum,"decorateModuleId":decorateModuleId,"seqNo":seqNo},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$.ligerDialog.open({
						height: 300,
						width: 400,
						title: "选择类目",
						name: "INSERT_WINDOW",
						url: "${pageContext.request.contextPath}/customPage/selectCategory.shtml?decorateModuleId="+decorateModuleId+"&productIds="+productId,
						showMax: true,
						showToggle: false,
						showMin: false,
						isResize: true,
						slide: false,
						allowClose: false,
						data: null
					});
					$this.css("cursor", "default");
					$this.attr("disabled", true);
					$this.text("已添加");
					$("#decorateModuleId").val(data.decorateModuleId);
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	
	$("#nextPage").bind('click',function(){
		if($(this).text()=="已全部加载"){
			return false;
		}
		var pageNo = $("#pageNo").val();
		$("#pageNo").val(Number(pageNo)+1);
		getProductData(0);
	});
	
	$("#addAll").bind('click',function(){
		var productIds="";
		var hasAdd=0;
		$("a[name='saveDecorateModule']").each(function(i){
			if($(this).text()=="已添加"){
				hasAdd++;
			}
			if(i!=$("a[name='saveDecorateModule']").length-1){
				productIds+=$(this).attr("productId")+",";
			}else{
				productIds+=$(this).attr("productId");
			}
		});
		if(!productIds){
			return false;
		}
		if(hasAdd == $("a[name='saveDecorateModule']").length){
			return false;
		}
		var decorateInfoId = $("#decorateInfoId").val();
		var decorateAreaId = $("#areaId").val();
		var decorateModuleId = $("#moduleId").val();
		var showNum = $("#showNum").val();
		var seqNo = $("#seqNo").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"decorateInfoId":decorateInfoId,"decorateAreaId":decorateAreaId,"moduleType":2,"productIds":productIds,"showNum":showNum,"decorateModuleId":decorateModuleId,"seqNo":seqNo},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$.ligerDialog.open({
						height: 300,
						width: 400,
						title: "选择类目",
						name: "INSERT_WINDOW",
						url: "${pageContext.request.contextPath}/customPage/selectCategory.shtml?decorateModuleId="+decorateModuleId+"&productIds="+productIds,
						showMax: true,
						showToggle: false,
						showMin: false,
						isResize: true,
						slide: false,
						allowClose: false,
						data: null
					});
					// commUtil.alertSuccess("添加成功");
					$("#decorateModuleId").val(data.decorateModuleId);
					$("a[name='saveDecorateModule']").each(function(i){
						$(this).css("cursor", "default");
						$(this).attr("disabled", true);
						$(this).text("已添加");
					});
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

function getProductData(isSearch){
	$.ajax({
		url : "${pageContext.request.contextPath}/customPage/getProductData.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : $("#dataForm").serialize(),
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				if(isSearch == 1){
					$("#productTable").empty();
				}
				var productCustoms = data.productCustoms;
				var pageNo = data.pageNo;
				var maxPage = data.maxPage;
				$("#pageNo").val(pageNo);
				var productArray = [];
				if(productCustoms){
					for(var i=0;i<productCustoms.length;i++){
						var productCustom = productCustoms[i];
						var tmpStr = '';
						tmpStr+='<tr>';
						tmpStr+='<td class="l-grid-row-cell ">';
						tmpStr+='<div class="l-grid-row-cell-inner">';
						tmpStr+='<img style="float:left;margin:10px;" src="${pageContext.request.contextPath}/file_servelt'+productCustom.pic+'" width="100" height="100" onclick="viewerPic(this.src)">';
						tmpStr+='<span style="display:block;text-align:left;margin-top:8px;">';
						tmpStr+=productCustom.name+'<br>';
						if(productCustom.salePriceMin!=productCustom.salePriceMax){
							tmpStr+='<span style="color:red;">'+productCustom.salePriceMin+'-'+productCustom.salePriceMax+'</span>元<br>';
						}else{
							tmpStr+='<span style="color:red;">'+productCustom.salePriceMin+'</span>元<br>';
						}
						tmpStr+='<a href="javascript:;" productId="'+productCustom.id+'" name="saveDecorateModule">【添加】</a>';
						tmpStr+='</span>';
						tmpStr+='</div>';
						tmpStr+='</td>';
						tmpStr+='</tr>';
						productArray.push(tmpStr);
					}
				}
				$("#productTable").append(productArray.join());
				if(maxPage>pageNo){
					$("#nextPage").text("加载下一页");
				}else{
					$("#nextPage").css("cursor", "default");
					if(productCustoms && productCustoms.length>0){
						$("#nextPage").text("已全部加载");
					}else{
						$("#nextPage").text("没有数据");
					}
				}
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function viewerPic(imgPath){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
}

</script>
</head>
<body style="padding: 0px;">
	<form id="dataForm" runat="server">
		<input type="hidden" id="decorateInfoId" name="decorateInfoId" value="${decorateInfoId}"> 
		<input type="hidden" id="areaId" name="decorateAreaId" value="${decorateAreaId}"> 
		<input type="hidden" id="moduleId" name="decorateModuleId" value="${decorateModuleId}"> 
		<input type="hidden" id="showNum" name="showNum" value="${showNum}"> 
		<input type="hidden" id="seqNo" name="seqNo" value="${seqNo}"> 
		<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}"> 
		<div class="search-pannel">
		<div class="search-tr"> 
			<div class="search-td" style="width: 484px;float: left;">
			<div class="search-td-label" style="float:left;width: 40px;">类目：</div>
			<div class="l-panel-search-item" style="margin-left: 0px;">
				<select id="productType" name="productType">
					<option value="">不限</option>
					<c:forEach var="productType" items="${productTypes}">
						<option value="${productType.id}">${productType.name}</option>
					</c:forEach>
				</select>
		 	 </div>
		 	 <div class="l-panel-search-item" >
				<select id="productType2" name="productType2">
					<option value="">不限</option>
				</select>
		 	 </div>
		 	 <div class="l-panel-search-item" >
				<select id="productType3" name="productType3">
					<option value="">不限</option>
				</select>
		 	 </div>
			 </div>
			<div class="search-td">
			<div class="search-td-label" >价格：</div>
			<div class="search-td-condition" style="display: inline-flex">
				<input type="text" id="salePriceMin" name="salePriceMin" style="width: 120px;">&nbsp;-&nbsp;<input type="text" id="salePriceMax" name="salePriceMax" style="width: 120px;">
			</div>
			</div>
		</div>
		<div class="search-tr"> 	 
			<div class="search-td">
			<div class="search-td-label" style="float:left;width: 40px;">名称：</div>
			<div class="search-td-condition">
				<input type="text" id="productName" name="productName" style="width: 780px;height: 23px;">
			</div>
			</div> 
			<div class="search-td-search-btn">
				<input type="button" class="l-button" style="width: 60px;" value="搜索" id="search" onclick="getProductData(1);">
			</div>
		</div>
		</div>
		<div class="search-tr">
			<input type="button" class="l-button" style="width: 60px;" value="添加全部" id="addAll">
		</div>
		<div style="overflow-y:auto;height: 350px;text-align: center;width: 98%;margin-left: 21px;">
			<table class="gridtable" id="productTable" style="width: 98%">
			<!-- 
				<c:forEach var="productCustom" items="${productCustoms}">
				<tr>				
					<td class="l-grid-row-cell ">
						<div class="l-grid-row-cell-inner">
							<img style="float:left;margin:10px;" src="${pageContext.request.contextPath}/file_servelt${productCustom.pic}" width="300" height="150" onclick="viewerPic(this.src)">
							<span style="display:block;text-align:left;margin-top:8px;">
								${productCustom.name}${productCustom.code}<br>
								${productCustom.salePriceMin}-${productCustom.salePriceMax}元<br>
								<a href="javascript:;">【添加】</a>
							</span>
						</div>
					</td>
				</tr>
				</c:forEach>
			-->	
			</table>
			<span><a href="javascript:;" id="nextPage"></a></span>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>