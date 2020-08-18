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
	var linkType = '${couponCenterBottomNavigation.linkType }';
	var linkValue = '${couponCenterBottomNavigation.linkValue }';
	var showType = "16,17,18,20,22,24,26,28,30";
	 $.ajax({
			url : "${pageContext.request.contextPath}/linkType/moduleMap/getLinkTypeList.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {showType:showType},
			timeout : 30000,
			success : function(data) {
				var linkTypeLists = data.linkTypeList
				for(var i=0;i<linkTypeLists.length;i++){
					if(i==11){
						continue;
					}
					var id=linkTypeLists[i].linkType
					var name=linkTypeLists[i].linkTypeName
					if(id==${couponCenterBottomNavigation.linkType }){
						$("#linkType").append('<option value="'+id+'" selected >'+name+'</option>')
					}else{
						$("#linkType").append('<option value="'+id+'" >'+name+'</option>')
					}

					
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	 
	 $("#linkType").change( function() { 
		 var linkType = $("#linkType").val();
	 	radioItem(linkType, '');	
	 })

 	if(linkType != ''){
			radioItem(linkType, linkValue);
	}else{
			radioItem('1', ' ');
	} 

	
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

//限制上传图片
 function ajaxFileUploadLogoAstrict(){
	 var file = document.getElementById("logoPicFile").files[0];  
	 if(!/image\/(png)$/.test(file.type)) {  
	    	commUtil.alertError("请上传.png格式的图片");
	    	$("input[name='file']").val('');
	        return;
	    }
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() { 
    				var imgWidth = '50';
					var imgHeight = '50';
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

function changeTwoType(titleName){
	$("#show1-1").html(''+titleName+'<span class="required">*</span>');
	var sonIds ="${sonIds}";
	var tmpArray =sonIds.split(",");
	var array = [];
	for(var j=0;j<tmpArray.length;j++){
		array.push(parseInt(tmpArray[j]));
	}
	var html = [];
	html.push('<select name="firstProductType" id="firstProductTypeXX" style="width:120px;">');
	html.push('<option value="">请选择</option>');
	var firstProductTypeId = $("#partenId").val();
	<c:forEach items="${productTypes}" var="productType">
		var selectedStr=""
		if(firstProductTypeId == ${productType.id}){
			selectedStr="selected";
		}
		html.push('<option value="${productType.id}" '+selectedStr+'>${productType.name}</option>');
	</c:forEach>
	html.push('</select>');
	html.push('<select name="linkValue" id="linkValue"  multiple="multiple" size="5" style="margin-top:15px;margin-left:10px;width:190px;">');
	if(firstProductTypeId==null||firstProductTypeId==""){
	html.push('</select>');
	}			
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
				/* 	var secondHtml = [];
						secondHtml.push('<option value="">请选择</option>'); */
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
	}
	$("#firstProductTypeXX").live('change',function(){
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
						
						var index = $.inArray(id,array);
						if(index>=0){
							secondHtml.push('<option value="'+id+'" selected>'+name+'</option>');
						}else{
							secondHtml.push('<option value="'+id+'">'+name+'</option>');
						}
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
	linkValueHtml = html.join("");
	$("#show1-2").html(linkValueHtml);
}


function radioItem(linkType, linkValue) {
	switch (linkType) {
		case '1':
			$("#show1-1").html('会场ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '" placeholder="请输入会场ID" validate="{digits:true,maxlength:9}"/>');
			break;
		case '2':
			$("#show1-1").html('特卖ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '" placeholder="请输入活动ID" validate="{digits:true,maxlength:9}"/>');
			break;
		case '3':
			$("#show1-1").html('商品ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '" placeholder="请输入商品ID" validate="{digits:true,maxlength:12}"/>');
			break;
		case '4':
			$("#show1-1").html('自建页面ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '" placeholder="请输入自建页面ID"  validate="{digits:true,maxlength:9}"/>');
			break;
		case '6':
			$("#show1-1").html('<span>旧品牌特卖一级类目<span class="required">*</span></span>');
			linkIStr = '<select name="linkValue" id="linkValue" style="width: 160px;">';
			<c:forEach items="${productTypes}" var="productType">
			var _selected="";
			var productTypeid = ${productType.id};
			if(linkValue == productTypeid){
				_selected = "selected";
			}
			linkIStr+='<option value="${productType.id}" '+_selected+'>${productType.name}</option>';
		    </c:forEach>
			linkIStr+='</select>';
			$("#show1-2").html(linkIStr);
			break;
		case '5':
			if(linkValue == '')
				linkValue = 1;
			$("#show1-1").html('<span>栏目<span class="required">*</span></span>');
			var linkIStr = '<select id="linkValue" name="linkValue" style="width: 160px;" >';
			$.ajax({
				url : "${pageContext.request.contextPath}/linkType/moduleMap/getLinkTypeList.shtml",
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
						if(id==linkValue){
							linkIStr += '<option  value="'+id+'"   selected>'+name+'</option>'
						}else{
							linkIStr += '<option  value="'+id+'"  >'+name+'</option>'
						}
						
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
			
			linkIStr += '</select>'
			$("#show1-2").html(linkIStr);
			break;
		case '9':
			$("#show1-1").html('URL<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue"  value="' +linkValue+ '"  placeholder="请输入URL连接" validate="{maxlength:120}"/>');
		  	break;
		case '11':
			$("#show1-1").html('优惠券ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '"  placeholder="请输入优惠券ID" validate="{digits:true,maxlength:9}"/>');
		  	break;
		case '12':
			$("#show1-1").html('<span>商城一级分类<span class="required">*</span></span>');
			linkIStr = '<select name="linkValue" id="linkValue" style="width: 160px;"><option value="">请选择</option>';
			<c:forEach items="${mallCategorys}" var="mallCategory">
			var _selected="";
			var productTypeid = ${mallCategory.id};
			if(linkValue == productTypeid){
				_selected = "selected";
			}
			linkIStr+='<option value="${mallCategory.id}" style="width:" 160px" '+_selected+'>${mallCategory.categoryName}</option>';
		    </c:forEach>
			linkIStr+='</select>';
			$("#show1-2").html(linkIStr);
		  	break;
		case '13':
			$("#show1-1").html('商家序号<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue"  value="' +linkValue+ '" placeholder="请输入商家序号" validate="{maxlength:120}" />');
		  	break;
		case '14':
			$("#show1-1").html('淘宝优选关键字<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue"  value="' +linkValue+ '" placeholder="请输入淘宝优选关键字" validate="{maxlength:120}" />');
		  	break;
		case '15':
				$("#show1-1").html('<span>新品牌团<span class="required">*</span></span>');
				linkIStr = '<select name="linkValue" id="linkValue" style="width: 160px;"><option value="">请选择</option>';
				<c:forEach items="${brandteamTypes}" var="brandteamType">
				var _selected="";
				var productTypeid = ${brandteamType.id};
				if(linkValue == productTypeid){
					_selected = "selected";
				}
				linkIStr+='<option value="${brandteamType.id}" style="width:" 160px" '+_selected+'>${brandteamType.name}</option>';
			    </c:forEach>
				linkIStr+='</select>';
				$("#show1-2").html(linkIStr);
				break;
		case '16':
			var titleName="新品牌团二级分类";
			changeTwoType(titleName);
		  	break;
		case '17':
			$("#show1-1").html('淘宝优选频道<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue"  value="' +linkValue+ '" placeholder="请输入淘宝优选频道" validate="{maxlength:120}" />');
		  	break;
		case '18':
			var titleName="有好货二级分类";
			changeTwoType(titleName);
		  	break;
		case '19':
			$("#show1-1").html('有好货品牌ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '"  placeholder="请输入有好货品牌ID" validate="{maxlength:120}"/>');
		  	break;
		case '20':
			var titleName="潮鞋上新二级分类";
			changeTwoType(titleName);
		  	break;
		case '21':
			$("#show1-1").html('潮鞋上新品牌ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '"  placeholder="潮鞋上新品牌ID" validate="{maxlength:120}"/>');
		  	break;
		case '22':
			var titleName="潮流男装二级分类";
			changeTwoType(titleName);
		  	break;
		case '23':
			$("#show1-1").html('潮流男装品牌ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '"  placeholder="潮流男装品牌ID"  validate="{maxlength:120}"/>');
		  	break;
		case '24':
			var titleName="断码特惠二级分类";
			changeTwoType(titleName);
		  	break;
		case '25':
			$("#show1-1").html('断码特惠品牌ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '" placeholder="断码特惠品牌ID" validate="{maxlength:120}"/>');
		  	break;
		case '26':
			var titleName="运动鞋服二级分类";
			changeTwoType(titleName);
		  	break;
		case '27':
			$("#show1-1").html('运动鞋服品牌ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '"  placeholder="运动鞋服品牌ID" validate="{maxlength:120}"/>');
		  	break;
		case '28':
			var titleName="潮鞋美妆二级分类";
			changeTwoType(titleName);
		  	break;
		case '29':
			$("#show1-1").html('潮鞋美妆品牌ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '"  placeholder="潮鞋美妆品牌ID" validate="{maxlength:120}"/>');
		  	break;
		case '30':
			var titleName="食品超市二级分类";
			changeTwoType(titleName);
		  	break;
		case '31':
			$("#show1-1").html('食品超市品牌ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '"  placeholder="食品超市品牌ID" validate="{maxlength:120}"/>');
		  	break;
		case '33':
			$("#show1-1").html('倒计时<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' +linkValue+ '"  placeholder="倒计时ID" validate="{maxlength:120}"/>');
		  	break;
	}
}


function commitSave(){
	//判断链接类型

	var linkType = $("#linkType").val();
	var linkValue = document.getElementById("linkValue");
	var linkValue = document.getElementById("linkValue");
	var name = document.getElementById("name");
	var pic = document.getElementById("pic");
	var iosPic = document.getElementById("iosPic");
	var androidPic = document.getElementById("androidPic");
	
	var autoUpDate = document.getElementById("autoUpDate");
	var autoDownDate = document.getElementById("autoDownDate");
	
	var tabId = $("#tabId").val()

	if(tabId==2||tabId==3){
		
	if( linkType != '4' && linkType != '5'  && linkType != '10' && linkType != '14' 
		&& linkType != '15' && linkType != '16' && linkType != '17' && linkType != '18' 
		&& linkType != '19' && linkType != '20' && linkType != '21' && linkType != '22' 
		&& linkType != '23'	&& linkType != '24' && linkType != '25' && linkType != '26' 
		&& linkType != '27' && linkType != '28' && linkType != '29' && linkType != '30'
		&& linkType != '31' && linkType != '32' && linkType != '33' && linkType != '34' && linkType != '35' && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "该字段不能为空。"});
		linkValue.focus();
		return;
	}else if(linkType == '4' && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "该字段不能为空。"});
		linkValue.focus();
		return;
	}else if(linkType == '10' && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "该字段不能为空。"});
		linkValue.focus();
		return;
	}else if( linkType == '14'  && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "该字段不能为空。"});
		linkValue.focus();
		return;
	}else if( linkType == '16'  && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkValue.focus();
		return;
	}else if( linkType == '18'  && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkValue.focus();
		return;
	}else if( linkType == '20'  && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkValue.focus();
		return;
	}else if( linkType == '22'  && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkValue.focus();
		return;
	}else if( linkType == '24'  && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkValue.focus();
		return;
	}else if( linkType == '26'  && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkValue.focus();
		return;
	}else if( linkType == '28'  && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkValue.focus();
		return;
	}else if( linkType == '30'  && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkValue.focus();
		return;
	}else if( linkType == '32'  && $.trim(linkValue.value)==""){
		$("#linkValue").ligerTip({ content: "请选中详细里的二级类目，可以按住Ctrl键多选"});
		linkValue.focus();
		return;
	 }
	}
 
	if($.trim(name.value)==""){
		commUtil.alertError("Tab名称不可为空。");
		return;
	} 
	
	
	if($("#name").val().length>4){
		commUtil.alertError("Tab名字最长为4位。");
		return;
	}

	
	if(v.form()){
		var dataJson = $("#form1").serializeArray();
		
		$.ajax({
			method: 'POST',
			url: '${pageContext.request.contextPath}/resourceLocationManagement/saveChangeEditTab.shtml',
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
		<input type="hidden" id="id" name="id" value="${couponCenterBottomNavigation.id}"/>
		<input type="hidden" id="partenId" name="partenId" value="${partenId}">
		<input type="hidden" id="tabId" name="tabId" value="${tabId}">
		<table class="gridtable">
		
				<tr>
					<td colspan="1" class="title">Tab名称<span class="required">*</span></td>
					<td colspan="5" align="left" class="l-table-edit-td">
				    <input type="text" id="name" name="name" value="${couponCenterBottomNavigation.name}"/>
					</td>
				</tr>
			
			<c:if test="${couponCenterBottomNavigation.id==2 || couponCenterBottomNavigation.id==3}">
				 <tr>
					<td colspan="1" class="title">类型<span class="required">*</span></td>
					<td colspan="5" align="left" class="l-table-edit-td">
						<select style="width: 160px;" id="linkType" name="linkType" validate="{required:true}"  >
						    <option value="">请选择</option>
						</select>
					</td>
				</tr>
			</c:if>
			
			<c:if test="${couponCenterBottomNavigation.id==2 || couponCenterBottomNavigation.id==3}">
			<tr id="show1">
				<td id="show1-1" colspan="1" class="title"></td>
				<td id="show1-2" colspan="5" align="left" class="l-table-edit-td" ></td>
			</tr>
			</c:if>
			
			<tr>
               <td  class="title" width="20%">Tab图标：</td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="logoPic" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${couponCenterBottomNavigation.pic}"></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogoAstrict();" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图标</a>
	    			</div>
	    			<span><font size="2" color="gray"> 图标格式：png，尺寸：50*50px </font></span>
	    			<input id="pic" name="pic" type="hidden" value="${couponCenterBottomNavigation.pic}">
               </td>
           </tr>
           
			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
					<!-- 	<input name="btnSubmit"  id="Button1" style="float:left;" class="l-button l-button-submit" value="提交" onclick="commitSave();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" /> -->
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交" onclick="commitSave();"/>
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit"value="取消" onclick="frameElement.dialog.close()" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
