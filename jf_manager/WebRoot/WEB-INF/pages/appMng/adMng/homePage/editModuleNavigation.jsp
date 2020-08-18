<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
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
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />	

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
var productBrandSelect;
var linkTypeList = "";
var linkValueList = "";
var linkValueFlag = true;
$(function(){
	var showType = "16,17,18,20,21,22,24,26,28,30";
	var linkType = ${moduleNavigation.linkType};
	var linkValue = '${moduleNavigation.linkValue}';
	var firstProductTypeId;
	<c:if test="${not empty firstProductTypeId}">
	firstProductTypeId = ${firstProductTypeId};
	</c:if>
	$.ajax({
		url : "${pageContext.request.contextPath}/linkType/moduleMap/getLinkTypeList.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"showType":showType},
		timeout : 30000,
		success : function(data) {
				linkTypeList = data.linkTypeList;
				linkValueList = data.linkValueList;
				var optionArray = [];
				optionArray.push('<option value="0">请选择</option>');
				for(var i=0;i<linkTypeList.length;i++){
					var linkType = linkTypeList[i].linkType;
					var linkTypeName = linkTypeList[i].linkTypeName;
						if(linkType == ${moduleNavigation.linkType}){
							optionArray.push('<option value="'+linkType+'" selected="selected">'+linkTypeName+'</option>');
						}else{
							optionArray.push('<option value="'+linkType+'">'+linkTypeName+'</option>');
						}
					}
					
				
				$("#linkType").html(optionArray.join(""));
		},
		error : function() {
			submitting = false;
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
	if(linkType == "5"){	   
	    var html1 = [];
	    html1.push('<select name="linkValue" id="linkValue"');
		html1.push('<option value="">请选择</option>');
		for(var i=0;i<linkValueList.length;i++){
			var id = linkValueList[i].linkValue;
			var name = linkValueList[i].linkValueName;
		     if (linkValue==id) {
		    	 html1.push('<option value="'+id+'" selected>'+name+'</option>');
			}else {
				html1.push('<option value="'+id+'">'+name+'</option>');
				
			}
		}
		html1.push('</select>');
	    linkValueHtml=html1.join("");
    }else if(linkType == "6"){
        linkValueHtml = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
		<c:forEach items="${productTypes}" var="productType">
			var _selected${productType.id}="";
			if(linkValue == ${productType.id}){
				_selected${productType.id} = "selected";
			}
			linkValueHtml+='<option value="${productType.id}" '+_selected${productType.id}+'>${productType.name}</option>';
		</c:forEach>
		linkValueHtml+='</select>';
	}else if(linkType == "15"){//新品牌团
		linkValueHtml = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
		<c:forEach items="${brandteamTypes}" var="brandteamType">
			var _selected="";
			var brandteamTypeId = ${brandteamType.id};
			if(linkValue == brandteamTypeId){
				_selected = "selected";
			}
			linkValueHtml+='<option value="${brandteamType.id}" '+_selected+'>${brandteamType.name}</option>';
		</c:forEach>
		linkValueHtml+='</select>';
	}else if(linkType == "12"){//商城一级分类
		linkValueHtml = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
		<c:forEach items="${mallCategories}" var="mallCategories">
			var _selected="";
			var mallCategoriesId = ${mallCategories.id};
			if(linkValue == mallCategoriesId){
				_selected = "selected";
			}
			linkValueHtml+='<option value="${mallCategories.id}" '+_selected+'>${mallCategories.categoryName}</option>';
		</c:forEach>
		linkValueHtml+='</select>';
	 }else if (linkType == "18" || linkType == "20" || linkType == "22" || linkType == "24" 
			 || linkType == "26" || linkType == "28" || linkType == "30" || linkType == "16") {
			var tmpArray = linkValue.split(",");
			var array = [];
			for(var j=0;j<tmpArray.length;j++){
				array.push(parseInt(tmpArray[j]));
			}
			var html = [];
			html.push('<select name="firstProductType" style="width:120px;">');
			<c:forEach items="${productTypes}" var="productType">
				var selectedStr=""
				if(firstProductTypeId == ${productType.id}){
					selectedStr="selected";
				}
				html.push('<option value="${productType.id}" '+selectedStr+'>${productType.name}</option>');
			</c:forEach>
			html.push('</select>');
			if(firstProductTypeId){
				$.ajax({
					url : "${pageContext.request.contextPath}/customPage/getProductTypes.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {productTypeId:firstProductTypeId},
					timeout : 30000,
					success : function(data) {
						if ("0000" == data.returnCode) {
							var productTypeList = data.productTypes;
							html.push('<select name="linkValue" id="linkValue"  multiple="multiple" size="5" style="margin-top:15px;margin-left:10px;width:190px;">');
							html.push('<option value="">请选择</option>');
							for(var i=0;i<productTypeList.length;i++){
								var id = productTypeList[i].id;
								var name = productTypeList[i].name;
								var index = $.inArray(id,array);
								if(index>=0){
									html.push('<option value="'+id+'" selected>'+name+'</option>');
								}else{
									html.push('<option value="'+id+'">'+name+'</option>');
								}
							}
							html.push('</select>');
						}else{
							commUtil.alertError("操作超时，请稍后再试！");
							return false;
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}else{
				$("select[name='firstProductType']").bind('change',function(){
					var productTypeId = $(this).val();
					$.ajax({
						url : "${pageContext.request.contextPath}/customPage/getProductTypes.shtml",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						data : {productTypeId:productTypeId},
						timeout : 30000,
						success : function(data) {
							if ("0000" == data.returnCode) {
								var productTypeList = data.productTypes;
								html.push('<select name="linkValue" id="linkValue"  multiple="multiple" size="5" style="margin-top:15px;margin-left:10px;width:190px;">');
								html.push('<option value="">请选择</option>');
								for(var i=0;i<productTypeList.length;i++){
									var id = productTypeList[i].id;
									var name = productTypeList[i].name;
									var index = $.inArray(id,array);
									if(index>=0){
										html.push('<option value="'+id+'" selected>'+name+'</option>');
									}else{
										html.push('<option value="'+id+'">'+name+'</option>');
									}
								}
								html.push('</select>');
							}else{
								commUtil.alertError("操作超时，请稍后再试！");
								return false;
							}
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});
				});
			}
			linkValueHtml = html.join("");
	 }else if(linkType == "0"){
		linkValueHtml = '<input id="linkValue" name="linkValue" type="text" style="width:100%" value="" onchange="linkValueListVerify()">';	
	 }else{
		linkValueHtml = '<input id="linkValue" name="linkValue" type="text" style="width:100%" value="'+linkValue+'" onchange="linkValueListVerify()">';
		}
	$("#linkValueDiv").html(linkValueHtml);		
	
	
	$("select[name='firstProductType']").live('change',function(){
		var html=[];
		var productTypeId = $(this).val();
		$.ajax({
			url : "${pageContext.request.contextPath}/customPage/getProductTypes.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {productTypeId:productTypeId},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var productTypeList = data.productTypes;
					html.push('<option value="">请选择</option>');
					for(var i=0;i<productTypeList.length;i++){
						var id = productTypeList[i].id;
						var name = productTypeList[i].name;
						html.push('<option value="'+id+'">'+name+'</option>');
					}
					$("#linkValue").html(html.join(""));
				}else{
					commUtil.alertError("操作超时，请稍后再试！");
					return false;
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});

});


	

	function linkValueListVerify(){
		var linkType = $("#linkType").get(0);
		var linkValue = $("#linkValue").get(0);
		if(!linkType.value){
			commUtil.alertError("请选择类型");
			return false;
		}
		if(!linkValue.value){
			commUtil.alertError("详细不能为空");
			return false;
		}
		if(!testNumber(linkValue.value) && linkType.value!=9 && linkType.value!=13 && linkType.value!=14 ){
			linkValueFlag = false;
			commUtil.alertError("只能输入正整数！");
			return false;
		}
		var list = [1,2,4,10,11,12,17];
		if($.inArray(Number(linkType.value), list) >= 0 && Number(linkValue.value)>=2147483646){
			linkValueFlag = false;
			commUtil.alertError("请输入正确的ID！");
			return false;
		}
		if(linkType.value != 5 && linkType.value != 6 && linkType.value != 9 && linkType.value != 18 
			 && linkType.value != "16" && linkType.value != "20" && linkType.value != "22" && linkType.value != "24" 
			 && linkType.value != "26" && linkType.value != "28" && linkType.value != "30" ){//排除5.栏目和6.一级分类和9.url链接
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/checkExists.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {linkType:linkType.value,linkValue:linkValue.value},
				timeout : 30000,
				success : function(data) {
					if('4004' == data.returnCode) {
					       $.ligerDialog.error(data.returnMsg);
					       $("#linkValue").val("");
					       linkValueFlag = false;
					      //commUtil.alertError("请选择填写正确链接"); 
					     }else{
					    	linkValueFlag = true; 
					     }
					    },
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}}
	
	
	 function firstProductTypeList(){
		var productTypeId = $("select[name='firstProductType']").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/customPage/getProductTypes.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {productTypeId:productTypeId},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var productTypeList = data.productTypes;
					var secondHtml = [];
					secondHtml.push('<option value="">请选择</option>');
					for(var i=0;i<productTypeList.length;i++){
						var id = productTypeList[i].id;
						var name = productTypeList[i].name;
						secondHtml.push('<option value="'+id+'">'+name+'</option>');
					}
					$("#linkValue").empty();
					$("#linkValue").append(secondHtml.join(""));
				}else{
					commUtil.alertError("操作超时，请稍后再试！");
					return false;
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
	 
	 function commit(){
			if($("#pic").val().trim() == "" || $("#pic").val() == null){
				commUtil.alertError("请上传一张入口图");
				return false;
			}
			
			var linkType = $("#linkType").val();
			if(linkType == "0"){
				commUtil.alertError("请选择类型");
				return false;
			}
			
			var linkValue = $("#linkValue").val();
			if(linkValue==null||linkValue==""){
				commUtil.alertError("请选择填写详细");
				return false;
			}
			
			if(!linkValueFlag){
				commUtil.alertError("请输入正确的ID");
				return false;
			}

			if(linkType==16||linkType==18||linkType==20||linkType==22||linkType==24||linkType==26||linkType==28||linkType==30){
				var html="";
				$("#linkValue option:selected").each(function () {
					if(html==""){
						html=$(this).val();
					}else{
						html+=","+$(this).val();
					}
				})
				linkValue=html;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/appMng/adMng/insertModuleNavigation.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"moduleNavigationId":$("#moduleNavigationId").val(),
						"decorateModuleId":$("#decorateModuleId").val(),
						"pic":$("#pic").val(),
						"linkType":linkType,
						"linkValue":linkValue,
						"row":$("#row").val(),
						"col":$("#col").val()
					},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						parent.location.reload();		
					}else{
						//alert("该行该列已有数据,请检查后再次修改");
						commUtil.alertError("该行该列已有数据,请检查后再次修改");
						return false;
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});			
		}
		

	//下拉框选择
	function linkTypeChange(_this){
		var linkType = $(_this).val();
		var $linkValueDiv = $("#linkValueDiv");
 		$linkValueDiv.find("input").remove();
		$linkValueDiv.find("select").remove(); 
		var html;
		if(linkType == 5){
			html = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
			for(var i=0;i<linkValueList.length;i++){
				var id = linkValueList[i].linkValue;
				var name = linkValueList[i].linkValueName;			
				html+='<option value="'+id+'">'+name+'</option>';
			}
			html+='</select>';
			linkValueFlag=true;
		}else if(linkType == 6){
			html = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
			<c:forEach items="${productTypes}" var="productType">
				html+='<option value="${productType.id}">${productType.name}</option>';
			</c:forEach>
			html+='</select>';
			linkValueFlag=true;
		}else if(linkType == 15){//新品牌团
			html = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
			<c:forEach items="${brandteamTypes}" var="brandteamType">
				html+='<option value="${brandteamType.id}">${brandteamType.name}</option>';
			</c:forEach>
			html+='</select>';
			linkValueFlag=true;
		}else if(linkType == 12){//商城一级类目
			html = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
			<c:forEach items="${mallCategories}" var="mallCategories">
				html+='<option value="${mallCategories.id}">${mallCategories.categoryName}</option>';
			</c:forEach>
			html+='</select>';
			linkValueFlag=true;
		}else if (linkType == "18" || linkType == "20" || linkType == "22" || linkType == "24" 
			 || linkType == "26" || linkType == "28" || linkType == "30" || linkType == "16") {//有好货二级类目
			var h = [];
			h.push('<select name="firstProductType" style="width:120px;" onchange="firstProductTypeList()">');
			h.push('<option value="">请选择</option>');
			<c:forEach items="${productTypes}" var="productType">
				h.push('<option value="${productType.id}">${productType.name}</option>');
			</c:forEach>
			h.push('</select>');
			h.push('<select name="linkValue" id="linkValue"  multiple="multiple" size="5" style="margin-top:15px;margin-left:10px;width:190px;">');
			h.push('</select>');
			$("select[name='firstProductType']").live('change',function(){
				var productTypeId = $(this).val();
				$.ajax({
					url : "${pageContext.request.contextPath}/customPage/getProductTypes.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {productTypeId:productTypeId},
					timeout : 30000,
					success : function(data) {
						if ("0000" == data.returnCode) {
							var productTypeList = data.productTypes;
							var secondHtml = [];
							secondHtml.push('<option value="">请选择</option>');
							for(var i=0;i<productTypeList.length;i++){
								var id = productTypeList[i].id;
								var name = productTypeList[i].name;
								secondHtml.push('<option value="'+id+'">'+name+'</option>');
							}
							$("#linkValue").empty();
							$("#linkValue").append(secondHtml.join(""));
						}else{
							commUtil.alertError("操作超时，请稍后再试！");
							return false;
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			});
			html = h.join(""); 
			linkValueFlag=true;
		}else{
			html = '<input id="linkValue" name="linkValue" type="text" style="width: 100%;" value="" onchange="linkValueListVerify()">';
		}
		$("#linkValueDiv").html(html);
	}
	
	//图片格式验证
	function ajaxFileUploadImg(_this) {
		var file = document.getElementById(_this).files[0]; 
		 if(!/image\/(PNG|png|gif|GIF)$/.test(file.type)){  
	        	commUtil.alertError("图片格式需为gif或png！");
	        	$("input[name='file']").attr('type','text'); 
				$("input[name='file']").attr('type','file'); 
	            return;
	        }

	    var reader = new FileReader();  
	    reader.onload = function(e) { 
	    	var image = new Image();
	    	image.onload = function() {
	   		 if(this.width != '311' || this.height != '311'){
					commUtil.alertError("图片尺寸需为311*311px！");
					$("input[name='file']").attr('type','text'); 
					$("input[name='file']").attr('type','file'); 
					return;
				}else{
	    			ajaxFileUploadLogo(_this);
				}
	        };
	        image.src = e.target.result;
	    }
	    reader.readAsDataURL(file);  
	}
	
	function ajaxFileUploadLogo() {
		$.ajaxFileUpload({
			url: contextPath + '/service/common/ajax_upload.shtml',
			secureuri: false,
			fileElementId: "businessLicensePicFile",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					$("#businessLicensePicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
					$("#pic").val(result.FILE_PATH);
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
<form method="post" id="test_box" name="form">
		<input type="hidden" id="moduleNavigationId" value="${moduleNavigation.id}">
		<input type="hidden" id="decorateModuleId" value="${decorateModuleId}">
		<input type="hidden" id="moduleNavigationLinkType" value="${moduleNavigation.linkType}">
		<input type="hidden" id="moduleNavigationLinkValue" value="${moduleNavigation.linkValue}">
		<table class="gridtable" style="width:450px;">
			
			<tr style="height: 200px">
				<td class="title">入口图:</td>
				<td id="industryLicense_viewer">
					<div><img id="businessLicensePicImg" style="width: 155.5px;height: 155.5px" alt="" src="${pageContext.request.contextPath}/file_servelt${moduleNavigation.pic}"></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="businessLicensePicFile" name="file" data_value="industryLicense_viewer" onchange="ajaxFileUploadImg('businessLicensePicFile');" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片(图片尺寸：311*311px,格式：gif、png)</a>
	    			</div>
	    			<input id="pic" name="pic" type="hidden" value="${moduleNavigation.pic}">
				</td>
			</tr>

			 <tr>
               <td  class="title">类型:<span class="required">*</span></td>
               <td>
               		<select id="linkType" name="linkType" style="width: 135px;" onchange="linkTypeChange(this);">
							<option value='"${moduleNavigation.linkType}"' selected="selected">请选择</option>
							
					</select>
               </td>
			</tr>
			
			<tr>
               <td  class="title">详细:<span class="required">*</span></td>
               <td>
               		
	               		<div id="linkValueDiv">
	               		<input type="text" id="linkValue" name="linkValue" style="width: 100%;">
						</div>
					

               </td>
			</tr>
			
			<tr>
               <td  class="title">位置:<span class="required">*</span></td>
               <td>
               		第
               		<select id="row" name="row" style="width: 50px;" >
							<option value="1" <c:if test="${moduleNavigation.row eq 1}">selected="selected"</c:if>>1</option>
							<option value="2" <c:if test="${moduleNavigation.row eq 2}">selected="selected"</c:if>>2</option>
							<option value="3" <c:if test="${moduleNavigation.row eq 3}">selected="selected"</c:if>>3</option>
					</select>
					行, 
					第
               		<select id="col" name="col" style="width: 50px;" >
							<option value="1" <c:if test="${moduleNavigation.col eq 1}">selected="selected"</c:if>>1</option>
							<option value="2" <c:if test="${moduleNavigation.col eq 2}">selected="selected"</c:if>>2</option>
							<option value="3" <c:if test="${moduleNavigation.col eq 3}">selected="selected"</c:if>>3</option>
							<option value="4" <c:if test="${moduleNavigation.col eq 4}">selected="selected"</c:if>>4</option>
							<option value="5" <c:if test="${moduleNavigation.col eq 5}">selected="selected"</c:if>>5</option>
							<option value="6" <c:if test="${moduleNavigation.col eq 6}">selected="selected"</c:if>>6</option>
							<option value="7" <c:if test="${moduleNavigation.col eq 7}">selected="selected"</c:if>>7</option>
							<option value="8" <c:if test="${moduleNavigation.col eq 9}">selected="selected"</c:if>>8</option>
							<option value="9" <c:if test="${moduleNavigation.col eq 9}">selected="selected"</c:if>>9</option>
							<option value="10" <c:if test="${moduleNavigation.col eq 10}">selected="selected"</c:if>>10</option>
							<option value="11" <c:if test="${moduleNavigation.col eq 11}">selected="selected"</c:if>>11</option>
							<option value="12" <c:if test="${moduleNavigation.col eq 12}">selected="selected"</c:if>>12</option>
					</select>
					列
               </td>
			</tr>
			<tr>
               <td colspan="4" align="left" class="l-table-edit-td">
               		<div id="btnDiv">
						<input id="save" type="button" style="margin-left:40%;" class="l-button l-button-submit" value="保存" onclick="commit()"/>
					</div>
               </td>
			</tr>
		</table>
</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
		
</body>
</html>
