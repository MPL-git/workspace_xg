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
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript">

var v ;
$(function ()  {

	$.ajax({
		url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml?showType=${showType}",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : "",
		timeout : 30000,
		success : function(data) {
			var linkTypeLists = data.linkTypeList
			for(var i=0;i<linkTypeLists.length;i++){
				var id=linkTypeLists[i].linkType
				var name=linkTypeLists[i].linkTypeName
				$("#linkType").append('<option value="'+id+'">'+name+'</option>')
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});

	$("input[name='linkType']").eq(0).attr("checked",'true');
	if('${isCwOrgStatus }' == '1') {
		$("#show1-1").html('活动ID<span class="required">*</span>');
		$("#show1-2").html('<input type="text" id="linkId" name="linkId" placeholder="请输入活动ID"  validate="{digits:true,maxlength:9}"/>');
	}else {
		$("#show1-1").html('会场ID<span class="required">*</span>');
		$("#show1-2").html('<input type="text" id="linkId" name="linkId"  placeholder="请输入会场ID"  validate="{digits:true,maxlength:9}"/>');
	}
 	$("#linkType").change( function() { 			
		var linkType = $("#linkType").val();
		switch (linkType) {
			case '1':
				$("#show1-1").html('会场ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkId" name="linkId" placeholder="请输入会场ID"  validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '2':
				$("#show1-1").html('活动ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkId" name="linkId" placeholder="请输入活动ID"  validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '3':
				$("#show1-1").html('商品ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkUrl" name="linkUrl" placeholder="请输入商品ID" validate="{digits:true,maxlength:12}"/>');
			  	break;
			case '4':
				$("#show1-1").html('URL<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkUrl" name="linkUrl" placeholder="请输入URL连接" validate="{maxlength:120}"/>');
			  	break;
			case '5':
				$("#show1-1").html('URL');
				$("#show1-2").html('<input type="text" id="linkUrl" placeholder="无连接"  name="linkUrl" />');
				break;	
			case '6':
				var linkIStr ='<select id="linkId" name="linkId" style="width: 160px;">'	
					$.ajax({
						url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml?showType="+14,
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						data : "",
						timeout : 30000,
						success : function(data) {
							var linkValueList = data.linkValueList
							for(var i=0;i<linkValueList.length;i++){
								var id=linkValueList[i].linkValue
								var name=linkValueList[i].linkValueName
								linkIStr += '<option  value="'+id+'" >'+name+'</option>'
							}
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});
					
					linkIStr += '</select>'
					$("#show1-1").html('频道<span class="required">*</span>');
					$("#show1-2").html(linkIStr);
				break;
			case '7':
				$("#show1-1").html('自建页面ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkId" name="linkId" placeholder="请输入自建页面ID" validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '8':
				$("#show1-1").html('淘宝优选关键字<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkId" name="linkId" placeholder="请输入淘宝优惠关键字" validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '9':
				var brandteamType='<select id="linkId" name="linkId" style="width: 160px;">'
				+'<c:forEach var="brandteamType" items="${brandteamTypes}">'
				+'<option value="${brandteamType.id}" style="width: 160px;" >${brandteamType.name}</option>'
				+'</c:forEach>'
				+'</select>'
				
				$("#show1-1").html('新品牌团<span class="required">*</span>');
				$("#show1-2").html(brandteamType);
			/* 	$("#show1-2").html('<input type="text" id="linkId" name="linkId" validate="{digits:true,maxlength:9}"/>'); */
			  	break;
			case '10':
				$("#show1-1").html('商家店铺<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkUrl" name="linkUrl" placeholder="请输入商家序号" />');
			  	break;
			case '11':
				var productTypes='<select id="linkId" name="linkId" style="width: 160px;">'
					+'<c:forEach var="productType" items="${productTypes}">'
					+'<option value="${productType.id}" style="width: 160px;">${productType.name}</option>'
					+'</c:forEach>'
					+'</select>'
					
				$("#show1-1").html('一级分类<span class="required">*</span>');
				$("#show1-2").html(productTypes);
				/* $("#show1-2").html('<input type="text" id="linkId" name="linkId" validate="{digits:true,maxlength:9}"/>'); */
			  	break;
			case '14':
				var titleName="有好货二级分类";
				changeTwoType(titleName);
			  	break;
			case '15':
				$("#show1-1").html('有好货品牌ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkUrl" name="linkUrl" placeholder="请输入有好货品牌ID" validate="{maxlength:120}"/>');
			  	break;
			case '16':
				var titleName="潮鞋上新二级分类";
				changeTwoType(titleName);
			  	break;
			case '17':
				$("#show1-1").html('潮鞋上新品牌ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkUrl" name="linkUrl" placeholder="潮鞋上新品牌ID" validate="{maxlength:120}"/>');
			  	break;
			case '18':
				var titleName="潮流男装二级分类";
				changeTwoType(titleName);
			  	break;
			case '19':
				$("#show1-1").html('潮流男装品牌ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkUrl" name="linkUrl" placeholder="潮流男装品牌ID" validate="{maxlength:120}"/>');
			  	break;
			case '20':
				var titleName="断码特惠二级分类";
				changeTwoType(titleName);
			  	break;
			case '21':
				$("#show1-1").html('断码特惠品牌ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkUrl" name="linkUrl" placeholder="断码特惠品牌ID" validate="{maxlength:120}"/>');
			  	break;
			case '22':
				var titleName="运动鞋服二级分类";
				changeTwoType(titleName);
			  	break;
			case '23':
				$("#show1-1").html('运动鞋服品牌ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkUrl" name="linkUrl" placeholder="运动鞋服品牌ID" validate="{maxlength:120}"/>');
			  	break;
			case '24':
				var titleName="潮鞋美妆二级分类";
				changeTwoType(titleName);
			  	break;
			case '25':
				$("#show1-1").html('潮鞋美妆品牌ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkUrl" name="linkUrl" placeholder="潮鞋美妆品牌ID" validate="{maxlength:120}"/>');
			  	break;
			case '26':
				var titleName="食品超市二级分类";
				changeTwoType(titleName);
			  	break;
			case '27':
				$("#show1-1").html('食品超市品牌ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkUrl" name="linkUrl" placeholder="食品超市品牌ID" validate="{maxlength:120}"/>');
			  	break;
			case '28':
				var productTypes='<select id="linkId" name="linkId" style="width: 160px;">'
					+'<c:forEach var="mallCategory" items="${mallCategorys}">'
					+'<option value="${mallCategory.id}" style="width:" 160px">${mallCategory.categoryName}</option>'
					+'</c:forEach>'
					+'</select>'
					
				$("#show1-1").html('商城一级分类<span class="required">*</span>');
				$("#show1-2").html(productTypes);
			  	break;
			case '29':
				$("#show1-1").html('优惠券ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkId" name="linkId"  placeholder="请输入优惠券ID" validate="{maxlength:120}"/>');
			  	break;
		}
	});
	
	$("#autoUpDate").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	$("#autoDownDate").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	
	$.metadata.setType("attr", "validate");
	
		 v = $("#form1").validate({
		errorPlacement: function (lable, element) {   
        	var elementType=$(element).attr("type");
        	if($(element).hasClass("l-text-field")){
        		$(element).parent().ligerTip({
					content : lable.html(),width: 100
				});
        	}
        	else if('radio'==elementType){
        		var radioName=$(element).attr("name");
        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
					content : lable.html(),width: 100
				});
        	}
        	else{
        		$(element).ligerTip({
					content : lable.html(),width: 100
				});
        	}
		},
		success: function (lable,element) {
			lable.ligerHideTip();
			lable.remove();
		}

	});
});

//改变二级分类
function changeTwoType(titleName){
	$("#show1-1").html(''+titleName+'<span class="required">*</span>');
	var h = [];
	h.push('<select name="firstProductType" id="firstProductType" style="width:120px;">');
	h.push('<option value="">请选择</option>');
	<c:forEach items="${productTypes}" var="productType">
		h.push('<option value="${productType.id}">${productType.name}</option>');
	</c:forEach>
	h.push('</select>');
	h.push('<select name="linkUrl" id="linkUrl"  multiple="multiple" size="5" style="margin-top:15px;margin-left:10px;width:190px;">');
	h.push('</select>');
	$("#firstProductType").live('change',function(){
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
					$("#linkUrl").empty();
					$("#linkUrl").append(secondHtml.join(""));
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
	$("#show1-2").html(h.join(""));
}


//限制上传图片
function ajaxFileUploadLogoAstrict(type){
	 var file = document.getElementById("logoPicFile").files[0];  
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
				if(type == 1){
					var imgWidth = '690';
					var imgHeight = '138';
				}else{
					var imgWidth = '1080';
					var imgHeight = '400';
				}
				if(this.width != imgWidth || this.height != imgHeight ) {
					$("#pic").val("");
					commUtil.alertError("图片像素不是"+imgWidth+"*"+imgHeight+"px");
				}else{

					ajaxFileUploadLogo();

				}
        };
        image.src = e.target.result;
    },
    reader.readAsDataURL(file);
    }

function ajaxFileUploadLogo() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml',
		secureuri: false,
		fileElementId: "logoPicFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#logoPic").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
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

//图片格式验证
function ajaxFileUploadImg(statusImg,img,id) {
	var file = document.getElementById(statusImg).files[0];
    if(!/image\/(JPG|jpg|JPEG|jpeg|PNG|png)$/.test(file.type)) {  
    	commUtil.alertError("请上传.png或.jpg格式的图片");
    	$("input[name='file']").val('');
        return;
    }
    var size = file.size;
    if(size > 600*1024 ) {
    	commUtil.alertError("图片大小不能超过600KB");
    	$("input[name='file']").val('');
      	return;
    }
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
    		var widthStr = '1080'; 
    		var heightStr = '1920'; 
    		if(statusImg == 'iosPicFile') {
    			widthStr = '1125';
    			heightStr = '2436';
    		}
    		if(this.width != widthStr || this.height != heightStr) {
    			commUtil.alertError("请上传"+widthStr+"x"+heightStr+"PX的图片");
    			$("input[name='file']").val('');
        	}else {
    			ajaxFileUploadPicFile(img,id);
    		}
        };
        image.src = e.target.result;
    },
    reader.readAsDataURL(file);
}

function ajaxFileUploadPicFile(img,id) {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml',
		secureuri: false,
		fileElementId: id+"File",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#"+img).attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
				$("#"+id).val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}


function commitSave(){
	//判断链接类型
	var linkType = $("#linkType").val();
	var linkId = document.getElementById("linkId");
	var linkUrl = document.getElementById("linkUrl");
	
	var pic = document.getElementById("pic");
	
	var iosPic = document.getElementById("iosPic");
	var androidPic = document.getElementById("androidPic");
	
	var autoUpDate = document.getElementById("autoUpDate");
	var autoDownDate = document.getElementById("autoDownDate");

	if(linkType != '3' && linkType != '4' && linkType != '5'  && linkType != '10' && linkType != '14' 
		&& linkType != '15' && linkType != '16' && linkType != '17' && linkType != '18' 
		&& linkType != '19' && linkType != '20' && linkType != '21' && linkType != '22' 
		&& linkType != '23'	&& linkType != '24' && linkType != '25' && linkType != '26' && linkType != '27' && $.trim(linkId.value)==""){
		$("#linkId").ligerTip({ content: "该字段不能为空。"});
		linkId.focus();
		return;
	}else if(linkType == '4' && $.trim(linkUrl.value)==""){
		$("#linkUrl").ligerTip({ content: "该字段不能为空。"});
		linkUrl.focus();
		return;
	}else if(linkType == '3' && $.trim(linkUrl.value)==""){
		$("#linkUrl").ligerTip({ content: "该字段不能为空。"});
		linkUrl.focus();
		return;
	}else if(linkType == '10' && $.trim(linkUrl.value)==""){
		$("#linkUrl").ligerTip({ content: "该字段不能为空。"});
		linkUrl.focus();
		return;
	}else if( linkType == '14'  && $.trim(linkUrl.value)==""){
		$("#linkUrl").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkUrl.focus();
		return;
	}else if( linkType == '16'  && $.trim(linkUrl.value)==""){
		$("#linkUrl").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkUrl.focus();
		return;
	}else if( linkType == '18'  && $.trim(linkUrl.value)==""){
		$("#linkUrl").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkUrl.focus();
		return;
	}else if( linkType == '20'  && $.trim(linkUrl.value)==""){
		$("#linkUrl").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkUrl.focus();
		return;
	}else if( linkType == '22'  && $.trim(linkUrl.value)==""){
		$("#linkUrl").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkUrl.focus();
		return;
	}else if( linkType == '24'  && $.trim(linkUrl.value)==""){
		$("#linkUrl").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkUrl.focus();
		return;
	}else if( linkType == '26'  && $.trim(linkUrl.value)==""){
		$("#linkUrl").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkUrl.focus();
		return;
	}
	/*mngPos==1002 || mngPos==1004|| mngPos==1007|| mngPos==1012|| mngPos==1022|| mngPos==1032|| mngPos==1042|| mngPos==1052|| mngPos==1062|| mngPos==1082  */
/* 	<c:if test="${mngPos == 1 || mngPos == 2 || mngPos == 9 }">
	if($.trim(pic.value)==""){
		commUtil.alertError("请上传图片。");
		return;
	}
	</c:if> */
	<c:if test="${mngPos == 1 }">
	var checkShowChannel = [];
	$("input:checkbox[name='checkShowChannel']:checked").each(function(i){
		checkShowChannel.push($(this).val());
	});
	if(checkShowChannel.length <= 0){
		commUtil.alertError("请选择客户端。");
		return;
	}
	$("#showChannel").val(checkShowChannel.toString());
	</c:if>

	<c:if test="${mngPos != 10 }">
	if($.trim(pic.value)==""){
		commUtil.alertError("请上传图片。");
		return;
	}
	</c:if>
	
	<c:if test="${mngPos == 10}">
	if($.trim(iosPic.value)==""){
		commUtil.alertError("请上传ios广告图片。");
		return;
	}
	if($.trim(androidPic.value)==""){
		commUtil.alertError("请上传安卓广告图片。");
		return;
	}
	</c:if> 
	if($.trim(autoUpDate.value)==""){
		commUtil.alertError("自动上架时间不能为空。");
		return;
	}
	if($.trim(autoDownDate.value)==""){
		commUtil.alertError("自动下架时间不能为空。");
		return;
	}
	if($.trim(autoUpDate.value)>=$.trim(autoDownDate.value)){
		commUtil.alertError("自动上架时间必须小于自动下架时间");
		return;
	}
	var autoDownDate=new Date(autoDownDate.value);
	var nowDate=new Date();
	var dateDiff=autoDownDate.getTime()-nowDate.getTime();
	if (dateDiff<=0){
		commUtil.alertError("自动下架时间必须大于当前时间");
		return;
	}
	
	if(v.form()){
		var dataJson = $("#form1").serializeArray();
		
		$.ajax({
			method: 'POST',
			url: '${pageContext.request.contextPath}/appMng/adMng/saveSubmit.shtml',
			data: dataJson,
			dataType: 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success:function(data){
				if(data.returnCode == '0000'){
					commUtil.alertSuccess("保存成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					commUtil.alertError(data.returnMsg); 
				}
			},
			error:function(){
				commUtil.alertError("请求失败"); 
			}
		});	
	
	}
	
	
};


</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/appMng/adMng/saveSubmit.shtml">
		<input type="hidden" id="type" name="type" value="1"/>
		<input type="hidden" id="showType" name="showType" value="${showType}"/>
		<table class="gridtable">
		<c:if test="${mngPos == 1002 || mngPos == 1004 || mngPos == 1007 || mngPos == 1032 || mngPos == 1102 }">
		       <tr>
					<td colspan="1" class="title">所属类目<span class="required">*</span></td>
					<td colspan="5" align="left" class="l-table-edit-td">
						<select style="width: 160px;" id="sourceProductTypeId" name="sourceProductTypeId" validate="{required:true}" >
						    <option value="">请选择</option>
							<c:forEach var="sourceProductTypes" items="${sourceProductTypes}">
							   <option value="${sourceProductTypes.id}">${sourceProductTypes.sourceProductTypeName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			<c:if test="${mngPos == 1012 || mngPos == 1022 ||  mngPos == 1042 || mngPos == 1052 || mngPos == 1062 || mngPos == 1082 }">
				<input type="hidden" id="sourceProductTypeId" name="sourceProductTypeId" value="${sourceProductTypes.id}"/>
			</c:if>
<%-- 			<tr>
				<td colspan="1" class="title">广告类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<select name="linkType" id="linkType" style="width: 160px;"   ><!-- class="radioClass"-->
					<c:forEach var="typeItem" items="${linkTypes }">
						<c:if test="${mngPos == 2 and typeItem.statusValue != 6 }">
						<option class="radioItem" type="radio" value="${typeItem.statusValue }">${typeItem.statusDesc}</option>
							<label><span class="radioClass"><input class="radioItem" type="radio" value="${typeItem.statusValue }" name="linkType">${typeItem.statusDesc}</span></label>
						</c:if>
						<c:if test="${mngPos == 1 || mngPos == 9 || mngPos == 1002 || mngPos == 1004 || mngPos == 1007 || mngPos == 10}">
						<option class="radioItem" type="radio" value="${typeItem.statusValue }" name="linkType">${typeItem.statusDesc}</option>
							<label><span class="radioClass"><input class="radioItem" type="radio" value="${typeItem.statusValue }" name="linkType">${typeItem.statusDesc}</span></label>
						</c:if>
					</c:forEach>
					</select>
				</td>
			</tr> --%>
			
			 <tr>
					<td colspan="1" class="title">广告类型<span class="required">*</span></td>
					<td colspan="5" align="left" class="l-table-edit-td">
						<select style="width: 160px;" id="linkType" name="linkType" validate="{required:true}"  >
						    <option value="">请选择</option>
						</select>
					</td>
				</tr>
			
			<c:if test="${mngPos == 1}">
				<input type="hidden" id="catalog" name="catalog" value="1"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>
			
			<c:if test="${mngPos == 9}">
				<input type="hidden" id="catalog" name="catalog" value="16"/>
			</c:if>

			<c:if test="${mngPos == 11}">
				<input type="hidden" id="catalog" name="catalog" value="30"/>
				<input type="hidden" id="position" name="position" value="2"/>
			</c:if>
			
			<c:if test="${mngPos == 1002}">
				<input type="hidden" id="catalog" name="catalog" value="17"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>
			
			<c:if test="${mngPos == 1004}">
				<input type="hidden" id="catalog" name="catalog" value="18"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>
			
			<c:if test="${mngPos == 1007}">
				<input type="hidden" id="catalog" name="catalog" value="20"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>
			
				<c:if test="${mngPos == 1012}">
				<input type="hidden" id="catalog" name="catalog" value="21"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>
			
				<c:if test="${mngPos == 1022}">
				<input type="hidden" id="catalog" name="catalog" value="22"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>
			
				<c:if test="${mngPos == 1032}">
				<input type="hidden" id="catalog" name="catalog" value="23"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>
			
				<c:if test="${mngPos == 1042}">
				<input type="hidden" id="catalog" name="catalog" value="24"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>
			
				<c:if test="${mngPos == 1052}">
				<input type="hidden" id="catalog" name="catalog" value="25"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>
			
				<c:if test="${mngPos == 1062}">
				<input type="hidden" id="catalog" name="catalog" value="26"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>
				
				<c:if test="${mngPos == 1082}">
				<input type="hidden" id="catalog" name="catalog" value="27"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>

			<c:if test="${mngPos == 1095}">
				<input type="hidden" id="catalog" name="catalog" value="28"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>

			<c:if test="${mngPos == 1102}">
				<input type="hidden" id="catalog" name="catalog" value="29"/>
				<input type="hidden" id="position" name="position" value="1"/>
			</c:if>

			<c:if test="${mngPos == 2 }">
				<tr>
					<td colspan="1" class="title">所属类目<span class="required">*</span></td>
					<td colspan="5" align="left" class="l-table-edit-td">
						<select style="width: 160px;" id="catalog" name="catalog">
							<c:forEach var="catalogItem" items="${catalogs}">
								<c:if test="${catalogItem.statusValue==2 || catalogItem.statusValue==3 || catalogItem.statusValue==4|| catalogItem.statusValue==5 || catalogItem.statusValue==11
								|| catalogItem.statusValue==12 || catalogItem.statusValue==13|| catalogItem.statusValue==14 || catalogItem.statusValue==15}">
									<option value="${catalogItem.statusValue}">${catalogItem.statusDesc}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="1" class="title">所属广告位<span class="required">*</span></td>
					<td colspan="5" align="left" class="l-table-edit-td">
						<select style="width: 160px;" id="position" name="position">
						<c:forEach var="positionItem" items="${positions}">
							<c:if test="${positionItem.statusValue==1 || positionItem.statusValue==2 || positionItem.statusValue==3}">
							<option value="${positionItem.statusValue}">${positionItem.statusDesc}</option>
							</c:if>
						</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			
			<tr id="show1">
				<td id="show1-1" colspan="1" class="title"></td>
				<td id="show1-2" colspan="5" align="left" class="l-table-edit-td" ></td>
			</tr>
			<c:if test="${mngPos ne 10}">
			<tr>
               <td  class="title" width="20%">广告图片：</td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="logoPic" style="width: 300px;height: 150px" alt="" src=""></div>
				   <c:if test="${mngPos == 11}">
					   <div style="float: left;height: 30px;">
						   <input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogoAstrict('1');" />
						   <a href="javascript:void(0);" style="width:30%;">上传图片</a>
					   </div>
					   (<span><font size="2" color="gray">上传图片尺寸为690*138</font></span>)
				   </c:if>
               		<c:if test="${mngPos == 1002 || mngPos == 1004 || mngPos == 1007  || mngPos == 1012 || mngPos == 1022 || mngPos == 1032 || mngPos == 1042 || mngPos == 1052 || mngPos == 1062 || mngPos == 1102}">
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogoAstrict('2');" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			(<span><font size="2" color="gray">上传图片尺寸为1080*400PX</font></span>)
	    			</c:if>
	    			<c:if test="${mngPos ne 11 && mngPos ne 1002 && mngPos ne 1004 && mngPos ne 1007 && mngPos ne 1012 && mngPos ne 1022 && mngPos ne 1032 && mngPos ne 1042 && mngPos ne 1052 && mngPos ne 1062 && mngPos ne 1102 }">
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			</c:if>
	    			<input id="pic" name="pic" type="hidden" value="">
               </td>
            </tr>
		    </c:if>
		   <c:if test="${mngPos == 10}">
		   	<input type="hidden" id="catalog" name="catalog" value="19"/>
		   	<input type="hidden" id="position" name="position" value="1"/>
		   	<tr>
               <td  class="title" width="20%">ios广告图片：</td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="iosPicImg" style="width: 300px;height: 150px" alt="" src=""></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="iosPicFile" name="file" onchange="ajaxFileUploadImg('iosPicFile','iosPicImg','iosPic');" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<br>
	    			<span>提示：尺寸为1125*2436PX;大小不超过600KB；支持.png.jpg</span>
	    			<input id="iosPic" name="iosPic" type="hidden" value="">
               </td>
            </tr>
		   	<tr>
               <td  class="title" width="20%">安卓广告图片：</td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="androidPicImg" style="width: 300px;height: 150px" alt="" src=""></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="androidPicFile" name="file" onchange="ajaxFileUploadImg('androidPicFile','androidPicImg','androidPic');" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<br>
	    			<span>提示：尺寸为1080*1920PX;大小不超过600KB；支持.png.jpg</span>
	    			<input id="androidPic" name="androidPic" type="hidden" value="">
               </td>
            </tr>
		   </c:if>
			<c:if test="${mngPos == 1}">
				<tr>
					<td colspan="1" class="title">选择客户端<span class="required">*</span></td>
					<td colspan="5" align="left" class="l-table-edit-td">
						<label><input type="checkbox" name="checkShowChannel" value="1" checked/>APP端</label>&nbsp;&nbsp;
						<label><input type="checkbox" name="checkShowChannel" value="2" checked/>小程序</label>&nbsp;&nbsp;
						<label><input type="checkbox" name="checkShowChannel" value="3" checked/>H5&其他</label>
						<input type="hidden" id="showChannel" name="showChannel" value="">
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="1" class="title">自动上架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="autoUpDate" name="autoUpDate"/>
					<script type="text/javascript">
						$(function(){
							$("#autoUpDate").ligerDateEditor({
								format: "yyyy-MM-dd hh:mm:ss",
								showTime : true,
								labelWidth : 150,
								width:200,
								labelAlign : 'left'
							});
						});
					</script>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">自动下架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="autoDownDate" name="autoDownDate"/>
					<script type="text/javascript">
						$(function(){
							$("#autoDownDate").ligerDateEditor({
								format: "yyyy-MM-dd hh:mm:ss",
								showTime : true,
								labelWidth : 150,
								width:200,
								labelAlign : 'left'
							});
						});
					</script>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" class="l-button l-button-submit" id="Button1" style="float:left;"  value="提交" onclick="commitSave();"/>
						<input value="取消" class="l-button l-button-submit" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
