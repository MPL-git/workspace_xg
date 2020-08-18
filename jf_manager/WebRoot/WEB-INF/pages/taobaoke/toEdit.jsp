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
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
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
	
	$("#productType1Id").bind('change',function(){
		var productType1Id = $(this).val();
		if(productType1Id){
			$("#productType2Id").empty();
			$("#productTypeId").empty();
			$.ajax({
				url : "${pageContext.request.contextPath}/taobaoke/getProductTypesByParentId.shtml?parentId="+productType1Id,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var productTypeList = data.productTypeList;
						var html=[];
						html.push('<option value="">请选择</option>');
						for(var i=0;i<productTypeList.length;i++){
							html.push('<option value="'+productTypeList[i].id+'">'+productTypeList[i].name+'</option>');
						}
						$("#productType2Id").append(html.join(""));
						$("#productTypeId").append('<option value="">请选择</option>');
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			$("#productType2Id").empty();
			$("#productType2Id").append('<option value="">请选择</option>');
			$("#productTypeId").empty();
			$("#productTypeId").append('<option value="">请选择</option>');
		}
	});
	
	$("#productType2Id").bind('change',function(){
		var productType2Id = $(this).val();
		if(productType2Id){
			$("#productTypeId").empty();
			$.ajax({
				url : "${pageContext.request.contextPath}/taobaoke/getProductTypesByParentId.shtml?parentId="+productType2Id,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var productTypeList = data.productTypeList;
						var html=[];
						html.push('<option value="">请选择</option>');
						for(var i=0;i<productTypeList.length;i++){
							html.push('<option value="'+productTypeList[i].id+'">'+productTypeList[i].name+'</option>');
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
		}else{
			$("#productTypeId").empty();
			$("#productTypeId").append('<option value="">请选择</option>');
		}
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

function setDefault(_this){
	var src1 = $(_this).prev().attr("src");
	var src2 = $("#defaultPic").attr("src");
	$(_this).prev().attr("src",src2);
	$("#defaultPic").attr("src",src1);
	if(src2.indexOf("/file_servelt/")>=0){
		$(_this).next().val(src2.substring(src2.indexOf("file_servelt")+12));
	}else{
		$(_this).next().val(src2);
	}
	if(src1.indexOf("/file_servelt/")>=0){
		$("#isDefault").val(src1.substring(src1.indexOf("file_servelt")+12));
	}else{
		$("#isDefault").val(src1);
	}
}

function ajaxFileUploadLogo() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml',
		secureuri: false,
		fileElementId: "logoPicFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#defaultPic").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
				$("#isDefault").val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}

function save(){
	var id = ${thirdPlatformProductInfoCustom.id};
	var title = $("#title").val();
	var productType1Id = $("#productType1Id").val();
	var productType2Id = $("#productType2Id").val();
	var productTypeId = $("#productTypeId").val();
	var autoUpDate = $("#autoUpDate").val();
	var autoDownDate = $("#autoDownDate").val();
	var seqNo = $("#seqNo").val();
	var imgs = [];
	var isDefault = $("#isDefault").val();
	imgs.push(isDefault);
	$("input[name='otherPic']").each(function(){
		imgs.push($(this).val());
	});
	if(!title){
		commUtil.alertError("请输入名称");
		return;
	}
	if(!productType1Id || !productType2Id || !productTypeId){
		commUtil.alertError("请选择分类");
		return;
	}
	if(seqNo && !testNumber(seqNo)){
		commUtil.alertError("排序值有误");
		return;		
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/taobaoke/saveThirdPlatformProductInfo.shtml",
		type : 'POST',
		data : {id:id,title:title,
				productType1Id:productType1Id,
				productType2Id:productType2Id,
				productTypeId:productTypeId,
				autoUpDate:autoUpDate,
				autoDownDate:autoDownDate,
				seqNo:seqNo,
				imgs:JSON.stringify(imgs)
				},
		dataType : 'json',
		cache : false,
		async : false,
		success : function(data) {
			if ("0000" == data.returnCode) {
				commUtil.alertSuccess("保存成功");
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
}
</script>
<html>
<body>
		<div><span class="table-title" >编辑商品</span></div>
		<table class="gridtable">
			<tr>
				<td class="title">
					名称				
				</td>
				<td align="left" class="l-table-edit-td">
					<input type="text" id="title" name="title" style="width: 400px;" value="${thirdPlatformProductInfoCustom.title}">
				</td>
			</tr>
			<tr>
				<td class="title">分类</td>
				<td align="left" class="l-table-edit-td">
					<select id="productType1Id" name="productType1Id" style="width: 150px;">
						<option value="">请选择</option>
						<c:forEach items="${productTypes}" var="productType">
							<option value="${productType.id}" <c:if test="${thirdPlatformProductInfoCustom.productType1Id eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
						</c:forEach>
					</select>
					<select id="productType2Id" name="productType2Id" style="width: 150px;">
						<option value="">请选择</option>
						<c:forEach items="${productTypeList}" var="productType">
							<option value="${productType.id}" <c:if test="${thirdPlatformProductInfoCustom.productType2Id eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
						</c:forEach>
					</select>
					<select id="productTypeId" name="productTypeId" style="width: 150px;">
						<option value="">请选择</option>
						<c:forEach items="${pts}" var="productType">
							<option value="${productType.id}" <c:if test="${thirdPlatformProductInfoCustom.productType3Id eq productType.id}">selected="selected"</c:if>>${productType.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="title">上下架时间</td>
				<td align="left" class="l-table-edit-td">
					<div style="display: inline-flex;">
						<input id="autoUpDate" name="autoUpDate" validate="{required:true}" type="text" style="width:400px;" value='<fmt:formatDate value="${thirdPlatformProductInfoCustom.autoUpDate}" pattern="yyyy-MM-dd"/>'/>
						<script type="text/javascript">
							$(function() {
								$("#autoUpDate").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width:150,
									labelAlign : 'left'
								});
							});
						</script>
						&nbsp;&nbsp;&nbsp;&nbsp;<input id="autoDownDate" name="autoDownDate" validate="{required:true}" type="text" style="width:400px;" value='<fmt:formatDate value="${thirdPlatformProductInfoCustom.autoDownDate}" pattern="yyyy-MM-dd"/>'/>
						<script type="text/javascript">
							$(function() {
								$("#autoDownDate").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width:150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>	
				</td>
			</tr>
			<tr>
				<td class="title">商品佣金</td>
				<td align="left" class="l-table-edit-td">
					${thirdPlatformProductInfoCustom.commissionRate/100}%
				</td>
			</tr>
			<tr>
				<td class="title">归属品牌</td>
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
				<td class="title">优惠券金额</td>
				<td align="left" class="l-table-edit-td">
					${thirdPlatformProductInfoCustom.couponAmount}
				</td>
			</tr>
			<tr>
				<td class="title">排序</td>
				<td align="left" class="l-table-edit-td">
					<input type="text" id="seqNo" name="seqNo" value="${thirdPlatformProductInfoCustom.seqNo}">
				</td>
			</tr>
			<tr>
				<td class="title">主图<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td">
					<c:if test="${isDefault.indexOf('http')>=0}">
						<div><img id="defaultPic" onclick='viewerPic(this.src)' style="max-width: 100px;max-height: 100px;" src="${isDefault}"></div>
					</c:if>
					<c:if test="${isDefault.indexOf('http')<0}">
						<div><img id="defaultPic" onclick='viewerPic(this.src)' style="max-width: 100px;max-height: 100px;" src="${pageContext.request.contextPath}/file_servelt${isDefault}"></div>
					</c:if>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<input id="isDefault" name="isDefault" type="hidden" value="${isDefault}">
				</td>
			</tr>
			<tr>
				<td class="title">详情图片</td>
				<td align="left" class="l-table-edit-td">
				 <c:forEach var="pic" items="${pics}">
				 	<input type="hidden" > 
				 	<c:if test="${pic.isDefault ne 1}">
					 	<c:if test="${pic.pic.indexOf('http')>=0}">
						 	<img src="${pic.pic}" onclick='viewerPic(this.src)' style="max-width: 100px;max-height: 100px;">
					 	</c:if>
					 	<c:if test="${pic.pic.indexOf('http')<0}">
					 		<img src="${pageContext.request.contextPath}/file_servelt${pic.pic}" onclick='viewerPic(this.src)' style="max-width: 100px;max-height: 100px;">
					 	</c:if>
					 	<a href="javascript:;" onclick="setDefault(this);">设为主图</a>
					 	<input type="hidden" id="otherPic${pic.id}" name="otherPic" value="${pic.pic}">
				 	</c:if>
				 </c:forEach>
				</td>
			</tr>
			<tr>
				<td class="title">操作</td>
				<td>
					<input type="button" class="l-button l-button-submit" value="提交保存" onclick="save();"/>&nbsp;&nbsp;
					<input type="button" value="取消" class="l-button l-button-test" onclick="frameElement.dialog.close();" />
				</td>
			</tr>
		</table>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
</html>
