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
/* var linkTypeStr = '';
var linkValueStr = ''; */
$(function () {
	var linkType = '${brandteamTypeadInfo.linkType }';
	var linkValue = '${brandteamTypeadInfo.linkValue }';

	 $.ajax({
			url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml?showType=13",
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
					if(id==linkType){
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
	 	radioItem(linkType, "");	
	 })

	if(linkType != '') {
		radioItem(linkType, linkValue);
	}else{		
	radioItem('1', '');
	}
	 
	
	
/* 	<c:if test="${not empty brandteamTypeadInfo.id}">
		linkTypeStr = '${brandteamTypeadInfo.linkType}';
		linkValueStr = '${brandteamTypeadInfo.linkValue}';
		if(linkTypeStr == '') {
			linkTypeStr = '1';
		}
	</c:if>
	radioItem(linkTypeStr);
	
	<c:if test="${empty brandteamTypeadInfo.id}">
	$("input[name='linkType']").eq(0).attr("checked",'true');
	$("#show1-1").html('会场ID<span class="required">*</span>');
	$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" validate="{digits:true,maxlength:9}"/>');
 	$(".radioItem").change( function() { 			
		var linkType = $("input[name='linkType']:checked").val();
		switch (linkType) {
		case '1':
			$("#show1-1").html('会场ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" validate="{digits:true,maxlength:9}"/>');
		  	break;
		case '2':
			$("#show1-1").html('活动ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" validate="{digits:true,maxlength:9}"/>');
		  	break;
		case '3':
			$("#show1-1").html('商品ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" validate="{digits:true,maxlength:9}"/>');
		  	break;
		case '4':
			$("#show1-1").html('URL<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" validate="{maxlength:120}"/>');
		  	break;
		case '5':
			$("#show1-1").html('URL');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" />');
			break;	
		case '6':
			var linkIStr = '<span class="radioClass"><input type="radio" checked name="linkValue" value="1" />新用户专区</span>'
				+'<span class="radioClass"><input type="radio" name="linkValue" value="2" />爆款专区</span>'
				+'<span class="radioClass"><input type="radio" name="linkValue" value="3" />限时抢购</span>'
				+'<span class="radioClass"><input type="radio" name="linkValue" value="4" />新人秒杀</span>'
				+'<span class="radioClass"><input type="radio" name="linkValue" value="5" />积分商城</span>'
				+'<span class="radioClass"><input type="radio" name="linkValue" value="6" />断码清仓</span>';
			$("#show1-1").html('频道<span class="required">*</span>');
			$("#show1-2").html(linkIStr);
			break;
		case '7':
			$("#show1-1").html('自建页面ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" validate="{digits:true,maxlength:9}"/>');
		  	break;
		}
	});
</c:if> */

	$("#upDate").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	$("#downDate").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	
	$.metadata.setType("attr", "validate");
	
	var v = $("#form1").validate({
	            	
		errorPlacement: function (lable, element)
		{   
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
		
		success: function (lable,element)
		{
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler: function (form)
		{   
			//判断链接类型
			//var linkType = $("input[name='linkType']:checked").val();
			var linkType = $("#linkType").val();
			var linkValue = document.getElementById("linkValue");
			var pic = document.getElementById("pic");
			var upDate = document.getElementById("upDate");
			var downDate = document.getElementById("downDate");
			if(linkType != '4' && linkType != '5' && linkType != '6' && $.trim(linkValue.value)==""){
    			$("#linkValue").ligerTip({ content: "该字段不能为空。"});
    			linkValue.focus();
    			return;
			}else if(linkType == '4' && $.trim(linkValue.value)==""){
    			$("#linkValue").ligerTip({ content: "该字段不能为空。"});
    			linkValue.focus();
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
			if($.trim(downDate.value)==""){
				commUtil.alertError("自动下架时间不能为空。");
				return;
			}
			if($.trim(upDate.value)>=$.trim(downDate.value)){
				commUtil.alertError("自动上架时间必须小于自动下架时间");
				return;
			}
			var downDate=new Date(downDate.value);
			var nowDate=new Date();
			var dateDiff=downDate.getTime()-nowDate.getTime();
			if (dateDiff<=0){
				commUtil.alertError("自动下架时间必须大于当前时间");
				return;
			}
			var brandteamTypeid = $("#brandteamTypeid").val();
			var values="";
			$("#linkValue option:selected").each(function () {
				values+=($(this).val())+","
			})
			$.ajax({
				url : "${pageContext.request.contextPath}/brandteamType/savebrandteamTypeAdInfo.shtml?values="+values,
				type : 'POST',
				data : $("#form1").serialize(),
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
	});
});

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
					/* secondHtml.push('<option value="">请选择</option>'); */
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
	
/* 	$("#selectBox option:selected").each(function () {
	     console.log($(this).val())
	}) */
}



function radioItem(linkType, linkValue) {
	switch (linkType) {
		case '1':
			$("#show1-1").html('会场ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入会场ID" value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
			break;
		case '2':
			$("#show1-1").html('活动ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入活动ID" value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
			break;
		case '3':
			$("#show1-1").html('商品ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入商品ID" value="' + linkValue + '" validate="{digits:true,maxlength:12}"/>');
			break;
		case '4':
			$("#show1-1").html('URL<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue"  placeholder="请输入URL连接"  value="' + linkValue + '" validate="{maxlength:120}"/>');
			break;
		case '5':
			$("#show1-1").html('URL');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="无连接"  value="' + linkValue + '" />');
			break;
		case '6':
			if(linkValue == '')
				linkValue = 1;
			$("#show1-1").html('<span>频道<span class="required">*</span></span>');
			var linkIStr = '<select id="linkValue" name="linkValue" style="width: 160px;">';
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
		case '7':
			$("#show1-1").html('自建页面ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入自建页面ID"  value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
			break;
		case '8':
			$("#show1-1").html('微淘关键字<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入微淘关键字"   value="' + linkValue + '"  validate="{digits:true,maxlength:9}"/>');
		  	break;
		case '9':
			$("#show1-1").html('<span>新品牌团<span class="required">*</span></span>');
			linkIStr = '<select name="linkValue" id="linkValue" style="width: 160px;"><option value="">请选择</option>';
			<c:forEach items="${brandteamTypes}" var="brandteamType">
			var _selected="";
			var brandteamTypeId = ${brandteamType.id};
			if(linkValue == brandteamTypeId){
				_selected = "selected";
			}
			linkIStr+='<option value="${brandteamType.id}"  '+_selected+'>${brandteamType.name}</option>';
			</c:forEach>
			linkIStr+='</select>';
			$("#show1-2").html(linkIStr);
			break;
		case '10':
			$("#show1-1").html('商家店铺<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入商家店铺ID"   value="' + linkValue + '" validate="{maxlength:120}" />');
		  	break;
		case '11':
			$("#show1-1").html('<span>一级分类<span class="required">*</span></span>');
			linkIStr = '<select name="linkValue" id="linkValue" style="width: 160px;"><option value="">请选择</option>';
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
		case '13':
			var titleName="新品牌团二级分类";
			changeTwoType(titleName);
		  	break;
		case '28':
			$("#show1-1").html('<span>商城一级分类<span class="required">*</span></span>');
			linkIStr = '<select name="linkValue" id="linkValue" style="width: 160px;"> <option value="">请选择</option>';
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
		case '29':
			$("#show1-1").html('优惠券ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入优惠券ID" value="' + linkValue + '"  validate="{digits:true,maxlength:11}"/>');
		  	break;
	}
}


/* function radioItem(linkType) {
	switch (linkType) {
		case '1':
			$("#show1-1").html('会场ID<span class="required">*</span>');
			if(linkType == linkTypeStr ) {
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' + linkValueStr + '" validate="{digits:true,maxlength:9}"/>');
			}else {
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="" validate="{digits:true,maxlength:9}"/>');
			}
			break;
		case '2':
			$("#show1-1").html('活动ID<span class="required">*</span>');
			if (linkType == linkTypeStr) {
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' + linkValueStr + '" validate="{digits:true,maxlength:9}"/>');
			}else {
			   $("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="" validate="{digits:true,maxlength:9}"/>');		
			}
			break;
		case '3':
			$("#show1-1").html('商品ID<span class="required">*</span>');
			if (linkType == linkTypeStr) {
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' + linkValueStr + '" validate="{digits:true,maxlength:9}"/>');
			}else {
			   $("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="" validate="{digits:true,maxlength:9}"/>');
				
			}
			break;
		case '4':
			$("#show1-1").html('URL<span class="required">*</span>');
			if (linkType == linkTypeStr) {
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' + linkValueStr + '"/>');
			}else {
			   $("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="" validate="{maxlength:120}"/>');
				
			}
			break;
		case '5':
			$("#show1-1").html('URL');
			if (linkType == linkTypeStr) {
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' + linkValueStr + '" />');
			}else {
			    $("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="" />');
				
			}
			break;
		case '6':
			if(linkValueStr == '')
				linkValueStr = 1;
			$("#show1-1").html('<span>频道<span class="required">*</span></span>');
			var linkIStr = "";
			if(linkValueStr == '1') 
				linkIStr = '<span class="radioClass"><input type="radio" checked name="linkValue" value="1" />新用户专区</span>';
			else
				linkIStr = '<span class="radioClass"><input type="radio" name="linkValue" value="1" />新用户专区</span>';
			if(linkValueStr == '2') 
				linkIStr += '<span class="radioClass"><input type="radio" checked name="linkValue" value="2" />爆款专区</span>';
			else
				linkIStr += '<span class="radioClass"><input type="radio" name="linkValue" value="2" />爆款专区</span>';
			if(linkValueStr == '3') 
				linkIStr += '<span class="radioClass"><input type="radio" checked name="linkValue" value="3" />限时抢购</span>';
			else
				linkIStr += '<span class="radioClass"><input type="radio" name="linkValue" value="3" />限时抢购</span>';
			if(linkValueStr == '4') 
				linkIStr += '<span class="radioClass"><input type="radio" checked name="linkValue" value="4" />新人秒杀</span>';
			else
				linkIStr += '<span class="radioClass"><input type="radio" name="linkValue" value="4" />新人秒杀</span>';
			if(linkValueStr == '5') 
				linkIStr += '<span class="radioClass"><input type="radio" checked name="linkValue" value="5" />积分商城</span>';
			else
				linkIStr += '<span class="radioClass"><input type="radio" name="linkValue" value="5" />积分商城</span>';
			if(linkValueStr == '6') 
				linkIStr += '<span class="radioClass"><input type="radio" checked name="linkValue" value="6" />断码清仓</span>';
			else
				linkIStr += '<span class="radioClass"><input type="radio" name="linkValue" value="6" />断码清仓</span>';
			if(linkValueStr == '7') 
				linkIStr += '<span class="radioClass"><input type="radio" checked name="linkValue" value="7" />有好货</span>';
			else
				linkIStr += '<span class="radioClass"><input type="radio" name="linkValue" value="7" />有好货</span>';
			if(linkValueStr == '8') 
			   linkIStr += '<span class="radioClass"><input type="radio" checked name="linkValue" value="8" />每日好店</span>';
			else
			   linkIStr += '<span class="radioClass"><input type="radio" name="linkValue" value="8" />每日好店</span>';	
			$("#show1-2").html(linkIStr);
			break;
		case '7':
			$("#show1-1").html('自建页面ID<span class="required">*</span>');
			if (linkType == linkTypeStr) {
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="' + linkValueStr + '" validate="{digits:true,maxlength:9}"/>');
			}else {
			    $("#show1-2").html('<input type="text" id="linkValue" name="linkValue" value="" validate="{digits:true,maxlength:9}"/>');
				
			}
			break;
	}
} */
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/brandteamType/savebrandteamTypeAdInfo.shtml">
		<input type="hidden" id="id" name="id" value="${brandteamTypeadInfo.id}"/>
		<input type="hidden" id="brandteamTypeid" name="brandteamTypeid" value="${brandteamTypeid}"/>
		<input type="hidden" id="partenId" name="partenId" value="${partenId}">
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">广告类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
				<select style="width: 160px;" id="linkType" name="linkType" validate="{required:true}"  >
						    <option value="">请选择</option>
						</select>
					<%-- <c:forEach var="typeItem" items="${linkTypeList}">
						<span class="radioClass"><input class="radioItem" onClick="radioItem(this.value)" type="radio" value="${typeItem.statusValue}" name="linkType" <c:if test="${typeItem.statusValue==brandteamTypeadInfo.linkType}">checked="checked"</c:if> >${typeItem.statusDesc}</span>
					</c:forEach> --%>
				</td>
			</tr>
			
			<tr id="show1">
				<td id="show1-1" colspan="1" class="title"></td>
				<td id="show1-2" colspan="5" align="left" class="l-table-edit-td" ></td>
			</tr>
			<tr>
               <td  class="title" width="20%">广告图片：</td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="logoPic" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${brandteamTypeadInfo.pic}"></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<input id="pic" name="pic" type="hidden" value="${brandteamTypeadInfo.pic}">
               </td>
           </tr>
			
			<tr>
				<td colspan="1" class="title">自动上架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="upDate" name="upDate" value="<fmt:formatDate value='${brandteamTypeadInfo.upDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">自动下架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="downDate" name="downDate" value="<fmt:formatDate value='${brandteamTypeadInfo.downDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
