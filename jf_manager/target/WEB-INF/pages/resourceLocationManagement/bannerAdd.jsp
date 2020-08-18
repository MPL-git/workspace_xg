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
</style>
<script type="text/javascript">
var v;
$(function ()  {
	
	$.ajax({
		url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml?showType="+14,
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
	
	
	
	if('${isCwOrgStatus }' == '1') {
		$("#show1-1").html('活动ID<span class="required">*</span>');
		$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入活动ID"  validate="{digits:true,maxlength:9}"/>');
	}else {
		$("#show1-1").html('会场ID<span class="required">*</span>');
		$("#show1-2").html('<input type="text" id="linkValue" name="linkValue"  placeholder="请输入会场ID"  validate="{digits:true,maxlength:9}"/>');
	}
 	$("#linkType").change( function() { 			
		var linkType = $("#linkType").val();
		switch (linkType) {
			case '1':
				$("#show1-1").html('会场ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入会场ID"  validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '2':
				$("#show1-1").html('活动ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入活动ID"  validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '3':
				$("#show1-1").html('商品ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入商品ID" validate="{digits:true,maxlength:12}"/>');
			  	break;
			case '4':
				$("#show1-1").html('URL<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入URL连接" validate="{maxlength:120}"/>');
			  	break;
			case '5':
				$("#show1-1").html('无连接');
				$("#show1-2").html('<input type="text" id="linkValue"   placeholder="无连接不需要填写"/>');
				break;	
			case '6':
				var linkIStr ='<select id="linkValue" name="linkValue" style="width: 160px;">'
				
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
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入自建页面ID" validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '8':
				$("#show1-1").html('优惠券ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入优惠券ID" validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '9':
				var brandteamType='<select id="linkValue" name="linkValue" style="width: 160px;">'
				+'<c:forEach var="brandteamType" items="${brandteamTypes}">'
				+'<option value="${brandteamType.id}">${brandteamType.name}</option>'
				+'</c:forEach>'
				+'</select>'
				
				$("#show1-1").html('新品牌团<span class="required">*</span>');
				$("#show1-2").html(brandteamType);
			/* 	$("#show1-2").html('<input type="text" id="linkId" name="linkId" validate="{digits:true,maxlength:9}"/>'); */
			  	break;
			case '10':
				$("#show1-1").html('商家店铺<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入商家序号" />');
			  	break;
			case '11':
				var productTypes='<select id="linkValue" name="linkValue" style="width: 160px;">'
					+'<c:forEach var="productType" items="${productTypes}">'
					+'<option value="${productType.id}">${productType.name}</option>'
					+'</c:forEach>'
					+'</select>'
					
				$("#show1-1").html('一级分类<span class="required">*</span>');
				$("#show1-2").html(productTypes);
				/* $("#show1-2").html('<input type="text" id="linkId" name="linkId" validate="{digits:true,maxlength:9}"/>'); */
			  	break;
			case '14':
				$("#show1-1").html('有好货二级分类<span class="required">*</span>');
				var h = [];
				h.push('<select name="firstProductType" id="firstProductType" style="width:120px;">');
				h.push('<option value="">请选择</option>');
				<c:forEach items="${productTypes}" var="productType">
					h.push('<option value="${productType.id}">${productType.name}</option>');
				</c:forEach>
				h.push('</select>');
				h.push('<select name="linkValue" id="linkValue"  multiple="multiple" size="5" style="margin-top:15px;margin-left:10px;width:190px;">');
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
				$("#show1-2").html(h.join(""));
			  	break;
			case '15':
				$("#show1-1").html('有好货品牌ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入有好货品牌ID" validate="{maxlength:120}"/>');
			  	break;
			case '28':
				var productTypes='<select id="linkValue" name="linkValue" style="width: 160px;">'
					+'<c:forEach var="mallCategory" items="${mallCategorys}">'
					+'<option value="${mallCategory.id}" style="width:" 160px">${mallCategory.categoryName}</option>'
					+'</c:forEach>'
					+'</select>'
					
				$("#show1-1").html('商城一级分类<span class="required">*</span>');
				$("#show1-2").html(productTypes);
			  	break;
			case '29':
				$("#show1-1").html('优惠券ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue"  placeholder="请输入优惠券ID" validate="{maxlength:120}"/>');
			  	break;
		}
	});
	$("#upDate").ligerDateEditor( {
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
		/* 	submitHandler: function (form) {   			
			//判断链接类型
			var linkType = $("#linkType").val();
			var linkValue = document.getElementById("linkValue");
			var pic = document.getElementById("pic");
			var upDate = document.getElementById("upDate");
			if(linkType != '5' && linkType != '14' && $.trim(linkValue.value)==""){
    			$("#linkValue").ligerTip({ content: "该字段不能为空。"});
    			linkId.focus();
    			return;
			}else if(linkType == '14' && $.trim(linkUrl.value)==""){
    			$("#linkUrl").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
    			linkUrl.focus();
    			return;
			}
			
			if($.trim(pic.value)==""){
				commUtil.alertError("请上传图片。");
				return;
			}
			
			if($.trim(upDate.value)==""){
				commUtil.alertError("自动上架时间不能为空。");
				return;
			}
			var upDate=new Date(upDate.value);
			var nowDate=new Date();
			var dateDiff=upDate.getTime()-nowDate.getTime();
			if (dateDiff<=0){
				commUtil.alertError("自动上架时间必须大于当前时间");
				return;
			}
	    	form.submit(); 
		}*/
	});
});


//限制上传图片
function ajaxFileUploadLogoAstrict(){
	 var file = document.getElementById("logoPicFile").files[0];  
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() { 
    				 var imgWidth = '1242';
					var imgHeight = '911'; 
					
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
	var linkValue = document.getElementById("linkValue");
	var pic = document.getElementById("pic");
	var upDate = document.getElementById("upDate");
	if(linkType != '5' && linkType != '14' && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "该字段不能为空。"});
		linkValue.focus();
		return;
	}else if(linkType == '14' && $.trim(linkUrl.value)==""){
		$("#linkUrl").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkUrl.focus();
		return;
	}
	
	if($.trim(pic.value)==""){
		commUtil.alertError("请上传图片。");
		return;
	}
	
	if($.trim(upDate.value)==""){
		commUtil.alertError("自动上架时间不能为空。");
		return;
	}
	var upDate=new Date(upDate.value);
	var nowDate=new Date();
	var dateDiff=upDate.getTime()-nowDate.getTime();
	if (dateDiff<=0){
		commUtil.alertError("自动上架时间必须大于当前时间");
		return;
	}
	
	if(v.form()){
		var dataJson = $("#form1").serializeArray();
		
		$.ajax({
			method: 'POST',
			url: '${pageContext.request.contextPath}/resourceLocationManagement/bannerSave.shtml',
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
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/resourceLocationManagement/bannerSave.shtml">
	<input type="hidden" id="sourceProductTypeId" name="sourceProductTypeId" value="${sourceProductTypeId}"/>
		<input type="hidden" id="id" name="id" value="${adInfoCustom.id}"/>
		<input type="hidden" id="partenId" name="partenId" value="${partenId}">
		<table class="gridtable">
		
		       <tr>
					<td colspan="1" class="title">banner类型<span class="required">*</span></td>
					<td colspan="5" align="left" class="l-table-edit-td">
						<select style="width: 160px;" id="linkType" name="linkType" validate="{required:true}"  >
						    <option value="">请选择</option>
						</select>
					</td>
				</tr>

			<tr id="show1">
				<td id="show1-1" colspan="1" class="title"></td>
				<td id="show1-2" colspan="5" align="left" class="l-table-edit-td" ></td>
			</tr>

			<tr>
               <td  class="title" width="20%">广告图片：</td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="logoPic" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${adInfoCustom.pic}"></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogoAstrict();" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<c:if test="${mngPos == 1002 || mngPos == 1004 || mngPos == 1007}">
	    			(<span><font size="2" color="gray">上传图片尺寸建议设定为1080*400PX</font></span>)
	    			</c:if>
	    			<c:if test="${pagetype==1003}">
	    			(<span><font size="2" color="gray">上传图片尺寸1242*911PX</font></span>)
	    			</c:if>
	    			<input id="pic" name="pic" type="hidden" value="${adInfoCustom.pic}">
               </td>
           </tr>
     
			
			<tr>
				<td colspan="1" class="title">上架时间<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="upDate" name="upDate" value="<fmt:formatDate value='${adInfoCustom.autoUpDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
				</td>
			</tr>
			

			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit"  id="Button1" style="float:left;" class="l-button l-button-submit" value="提交" onclick="commitSave();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
