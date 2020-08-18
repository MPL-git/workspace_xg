<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
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
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
	  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript">
</script>

<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
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
.td-pictures li img{
width: 100px;
height: 100px;
}


.table-title{
font-size: 17px;font-weight: 600;
}
.center-align{
	text-align: center;
}
.table-title-link{
	color: #1B17EE;
	font-size: 15px;
	text-decoration: none;
}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("#search").bind('click',function(){
		var refId = $("#refId").val();
		if(!refId){
			commUtil.alertError("商品ID不能为空");
			return;
		}
		alert("搜索中，请耐心等待");
		$.ajax({
			url : "${pageContext.request.contextPath}/taobaoke/getInfoByRefId.shtml?refId=" + refId,
			secureuri : false,
			dataType : 'json',
			cache : false,
			async : false,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var infoJSONArray = data.infoJSONArray;
					var jo = infoJSONArray[0];
					if(jo.user_type == 0){
						$("#sourceTd").text("淘宝");
					}else{
						$("#sourceTd").text("天猫");
					}
					$("#titleTd").text(jo.title);
					$("#reservePriceTd").text(jo.reserve_price);
					$("#zkFinalPriceTd").text(jo.zk_final_price);
					var imgHtml = [];
					imgHtml.push('<img src="'+jo.pict_url+'" onclick="viewerPic(this.src)">');
					var ja = jo.small_images.string;
					for(var i=0;i<ja.length;i++){
						imgHtml.push('<img src="'+ja[i]+'" onclick="viewerPic(this.src)">');
					}
					$("#productPicTd").html(imgHtml.join(""));
					$("#infoTable").show();
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	
	$("#save").bind('click',function(){
		var refId = $("#refId").val();
		var productTypeId = $("#productTypeId").val();
		var wetaoChannelId = $("#wetaoChannel").val();
		if(!refId){
			commUtil.alertError("商品ID不能为空");
			return;
		}
		if(!productTypeId){
			commUtil.alertError("请选择分类");
			return;
		}
		alert("保存中，请耐心等待");
		$.ajax({
			url : "${pageContext.request.contextPath}/taobaoke/saveTaobaoProduct.shtml?refId=" + refId+"&productTypeId="+productTypeId+"&wetaoChannelId="+wetaoChannelId,
			secureuri : false,
			dataType : 'json',
			cache : false,
			async : false,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("保存成功");
					location.reload();
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

function viewerPic(imgPath){
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
	imgPath=imgPath.replace('_M', '');
	imgPath=imgPath.replace('_S', '');
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	$("#viewer-pictures").show();
	viewerPictures.show();
}

function productAuditLogList(productId) {
	$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "审核记录",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/product/productAuditLogList.shtml?productId=" + productId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toEditManualWeight(productId){
	$.ligerDialog.open({
		height: $(window).height()*0.35,
		width: $(window).width()*0.35,
		title: "操作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/product/toEditManualWeight.shtml?productId=" + productId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function setProductType2Id(_this){
	$("#productTypeId").empty();
	$("#productTypeId").append("<option value=''>请选择</option>");
	$("#productType2Id").empty();
	$("#productType2Id").append("<option value=''>请选择</option>");
	var productType1Id = $(_this).val();
	if(productType1Id){
		$.ajax({
			url : "${pageContext.request.contextPath}/taobaoke/getProductTypeListByParentId.shtml?parentId=" + productType1Id,
			secureuri : false,
			dataType : 'json',
			cache : false,
			async : false,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var productTypes = data.productTypes;
					var html = [];
					for(var i=0;i<productTypes.length;i++){
						html.push('<option value="'+productTypes[i].id+'">'+productTypes[i].name+'</option>');
					}
					$("#productType2Id").append(html.join(""));
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
}

function setProductType3Id(_this){
	$("#productTypeId").empty();
	$("#productTypeId").append("<option value=''>请选择</option>");
	var productType2Id = $(_this).val();
	if(productType2Id){
		$.ajax({
			url : "${pageContext.request.contextPath}/taobaoke/getProductTypeListByParentId.shtml?parentId=" + productType2Id,
			secureuri : false,
			dataType : 'json',
			cache : false,
			async : false,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var productTypes = data.productTypes;
					var html = [];
					for(var i=0;i<productTypes.length;i++){
						html.push('<option value="'+productTypes[i].id+'">'+productTypes[i].name+'</option>');
					}
					$("#productTypeId").append(html.join(""));
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
}
</script>
<html>
<body>
		<div>
			<span>淘宝/天猫商品ID:&nbsp;&nbsp;<input type="text" id="refId" style="width: 200px"></span>
			<span><input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="搜索" id="search"></span>
			<span><input type="button" style="width: 80px;height: 25px;cursor: pointer;" value="提交保存" id="save"></span>
		</div>
		<br>
		<table class="gridtable" style="display:none;" id="infoTable">
			<tr>
				<td class="title" width="140px;">类型</td>
				<td align="left" class="l-table-edit-td" id="sourceTd">
					
				</td>
			</tr>
			<tr>
				<td class="title">
					商品名称				
				</td>
				<td align="left" class="l-table-edit-td" id="titleTd">
				</td>
			</tr>
			
			<tr>
				<td class="title">
					所属频道				
				</td>
				<td align="left" class="l-table-edit-td">
				<select id="wetaoChannel" name="wetaoChannel" style="width:150px;" >
					<option value="">请选择</option>
					<c:forEach var="wetaoChannel" items="${wetaoChannels}">
						<option value="${wetaoChannel.id}">${wetaoChannel.channelName}</option>
					</c:forEach> 
				</select>
				</td>
			</tr>
			
			<tr>
				<td class="title">分类</td>
				<td align="left" class="l-table-edit-td">
					<select id="productType1Id" name="productType1Id" style="width: 150px;" onchange="setProductType2Id(this);">
						<option value="">请选择</option>
						<c:forEach items="${productTypeList}" var="productType">
							<option value="${productType.id}">${productType.name}</option>
						</c:forEach>
					</select>
					<select id="productType2Id" name="productType2Id" style="width: 150px;" onchange="setProductType3Id(this);">
						<option value="">请选择</option>
					</select>
					<select id="productTypeId" name="productTypeId" style="width: 150px;">
						<option value="">请选择</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="title">原价</td>
				<td align="left" class="l-table-edit-td" id="reservePriceTd">
				</td>
			</tr>
			<tr>
				<td class="title">券后价</td>
				<td align="left" class="l-table-edit-td" id="zkFinalPriceTd">
				</td>
			</tr>
			<tr>
				<td class="title">商品主图</td>
				<td align="left" class="l-table-edit-td" id="productPicTd">
					
				</td>
			</tr>
		</table>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
</html>
