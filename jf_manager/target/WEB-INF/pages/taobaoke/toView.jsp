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
</script>
<html>
<body>
		<div><span class="table-title" >查看商品</span></div>
		<table class="gridtable">
			<tr>
				<td class="title" width="140px;">类型</td>
				<td align="left" class="l-table-edit-td">
					<c:if test="${thirdPlatformProductInfoCustom.source eq 1}">
						淘宝
					</c:if>
					<c:if test="${thirdPlatformProductInfoCustom.source eq 2}">
						天猫
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="title">
					商品名称				
				</td>
				<td align="left" class="l-table-edit-td">
					${thirdPlatformProductInfoCustom.title}
				</td>
			</tr>
			<tr>
				<td class="title">分类</td>
				<td align="left" class="l-table-edit-td">
					${thirdPlatformProductInfoCustom.gproductTypeName}>${thirdPlatformProductInfoCustom.fproductTypeName}>${thirdPlatformProductInfoCustom.productTypeName}
				</td>
			</tr>
			<tr>
				<td class="title">原价</td>
				<td align="left" class="l-table-edit-td">
					${thirdPlatformProductInfoCustom.reservePrice}
				</td>
			</tr>
			<tr>
				<td class="title">券后价</td>
				<td align="left" class="l-table-edit-td">
					${thirdPlatformProductInfoCustom.zkFinalPrice}
				</td>
			</tr>
			<tr>
				<td class="title">佣金比例</td>
				<td align="left" class="l-table-edit-td">
					${thirdPlatformProductInfoCustom.commissionRate}
				</td>
			</tr>
			<tr>
				<td class="title">优惠券有效期</td>
				<td align="left" class="l-table-edit-td">
					${thirdPlatformProductInfoCustom.couponStartTime}至${thirdPlatformProductInfoCustom.couponEndTime}
				</td>
			</tr>
			<tr>
				<td class="title">商品主图</td>
				<td align="left" class="l-table-edit-td">
				 <c:forEach var="pic" items="${pics}"> 
				 	<img src="${pic.pic}" onclick='viewerPic(this.src)'>
				 </c:forEach>
				</td>
			</tr>
		</table>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
</html>
