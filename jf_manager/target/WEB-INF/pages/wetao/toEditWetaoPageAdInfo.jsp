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
$(function () {
	var linkType="";
	<c:if test="${not empty wetaoPageAdInfo }">
		linkType = ${wetaoPageAdInfo.linkType};
	</c:if>
	var linkIStr = "";
	var showType = 12;//12.显示淘宝优选频道和淘宝优选关键字
	$.ajax({
		url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml?showType="+showType,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		success : function(data) {
				var linkTypeList = data.linkTypeList;
				linkIStr = '<select name="linkType" id="linkType" style="width: 160px;" onClick="radioItem(this.value, '+""+')">';
				for(var i=0;i<linkTypeList.length;i++){
					var id = linkTypeList[i].linkType;
					var name = linkTypeList[i].linkTypeName;
					<c:if test="${empty wetaoPageAdInfo}">
						linkIStr+='<option  value="'+id+'" <c:if test="${id == 1}">selected</c:if>>'+name+'</option>';
					</c:if>
					<c:if test="${not empty wetaoPageAdInfo}">
						if (linkType==id) {
							linkIStr+='<option value="'+id+'" selected>'+name+'</option>';
						}else{
						   linkIStr+='<option value="'+id+'">'+name+'</option>';
						}
                 	</c:if>
				}
				linkIStr+='</select>';				
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
	$("#linkTypeTd").html(linkIStr);
	
	<c:if test="${not empty wetaoPageAdInfo.id}">
		var linkType = '${wetaoPageAdInfo.linkType}';
		var linkValue = '${wetaoPageAdInfo.linkValue}';
		if(linkType != '') {
			radioItem(linkType, linkValue);
		}else{
			if('${isCwOrgStatus }' == '1') {
				radioItem('2', '');
			}else {
				radioItem('1', '');
			}
		}
	</c:if>
	<c:if test="${empty wetaoPageAdInfo.id}">
		$("input[name='linkType']").eq(0).attr("checked",'true');
		$("#show1-1").html('会场ID<span class="required">*</span>');
		$("#show1-2").html('<input type="text" id="linkValue" placeholder="请输入会场ID"  name="linkValue" validate="{maxlength:120}"/>');
	 	$(".radioItem").change( function() { 			
			var linkType = $("input[name='linkType']:checked").val();
			switch (linkType) {
			case '1':
				$("#show1-1").html('会场ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入会场ID" validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '2':
				$("#show1-1").html('特卖ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入特卖ID" validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '3':
				$("#show1-1").html('商品ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入商品ID" validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '4':
				$("#show1-1").html('外部链接<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入URL连接" validate="{maxlength:120}"/>');
			  	break;
			case '5':
				$("#show1-1").html('无连接');
				$("#show1-2").html('<input type="text" id="linkValue" placeholder="无连接" name="linkValue" />');
				break;	
			case '6':
					$.ajax({
						url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						success : function(data) {
								var linkValueList = data.linkValueList;
								linkIStr = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
								for(var i=0;i<linkValueList.length;i++){
									var id = linkValueList[i].linkValue;
									var name = linkValueList[i].linkValueName;
									if (linkValue==id) {
										linkIStr+='<option value="'+id+'" selected>'+name+'</option>';
									}else{
									   linkIStr+='<option value="'+id+'">'+name+'</option>';
										
									}
										
								}
								linkIStr+='</select>';				
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});	
				$("#show1-1").html('频道<span class="required">*</span>');
				$("#show1-2").html(linkIStr);
				break;
			case '7':
				$("#show1-1").html('自建页面ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入自建页面ID" validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '8':
				$("#show1-1").html('淘宝优选关键字<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入淘宝优选关键字" value="' + linkValue + '" validate="{maxlength:120}"/>');
				break;
			case '12':
				$("#show1-1").html('微淘优选频道<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入微淘优频道" value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
				break;
			case '29':
				$("#show1-1").html('优惠券ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入优惠券ID" value="' + linkValue + '" validate="{maxlength:120}"/>');
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
			case '9':
				$("#show1-1").html('新品牌团<span class="required">*</span>');
				linkIStr = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
				<c:forEach items="${brandteamTypes}" var="brandteamType">
				var _selected="";
				var brandteamTypeId = ${brandteamType.id};
				if(linkValue == brandteamTypeId){
					_selected = "selected";
				}
				linkIStr+='<option value="${brandteamType.id}" '+_selected+'>${brandteamType.name}</option>';
				</c:forEach>
				linkIStr+='</select>';
				$("#show1-2").html(linkIStr);
				break; 
			case '10':
				$("#show1-1").html('商家店铺<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入商家店铺ID" value="' + linkValue + '" validate="{maxlength:120}"/>');
				break;	
			case '11':
				$("#show1-1").html('旧品牌特卖一级类目<span class="required">*</span>');
				linkIStr = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
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
			}
		});
	</c:if>
	
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
			var wetaoPageId = $("#wetaoPageId").val();
			$.ajax({
				url : "${pageContext.request.contextPath}/wetaoPage/saveWetaoPageAdInfo.shtml",
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
function radioItem(linkType, linkValue) {
	if(!linkValue){
		linkValue = "";
	}
	switch (linkType) {
		case '1':
			$("#show1-1").html('会场ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入会场ID" value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
			break;
		case '2':
			$("#show1-1").html('特卖ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入特卖ID" value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
			break;
		case '3':
			$("#show1-1").html('商品ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入商品ID" value="' + linkValue + '" validate="{maxlength:20}"/>');
			break;
		case '4':
			$("#show1-1").html('外部链接<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue"  placeholder="请输入URL连接" value="' + linkValue + '" validate="{maxlength:120}"/>');
			break;
		case '5':
			$("#show1-1").html('无链接');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="无连接" value="' + linkValue + '" />');
			break;
		case '6':
			if(linkValue == '')
			   linkValue = 1;
			$("#show1-1").html('<span>频道<span class="required">*</span></span>');
			var linkIStr = "";
			$.ajax({
				url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
						var linkValueList = data.linkValueList;
						linkIStr = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
						for(var i=0;i<linkValueList.length;i++){
							var id = linkValueList[i].linkValue;
							var name = linkValueList[i].linkValueName;
							if (linkValue==id) {
								linkIStr+='<option value="'+id+'" selected>'+name+'</option>';
							}else{
							   linkIStr+='<option value="'+id+'">'+name+'</option>';
								
							}
								
						}
						linkIStr+='</select>';				
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
			$("#show1-2").html(linkIStr);
			break;
		case '7':
			$("#show1-1").html('自建页面ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入自建页面ID" value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
			break;
		case '8':
			$("#show1-1").html('淘宝优选关键字<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入淘宝优选关键字" value="' + linkValue + '" validate="{maxlength:120}"/>');
			break;
		case '12':
			$("#show1-1").html('微淘优选频道<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入微淘优选频道" value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
			break;
		case '29':
			$("#show1-1").html('优惠券ID<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入优惠券ID" value="' + linkValue + '" validate="{maxlength:120}"/>');
			break;
		case '28':
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
		case '9':
			$("#show1-1").html('新品牌团<span class="required">*</span>');
			linkIStr = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
			<c:forEach items="${brandteamTypes}" var="brandteamType">
			var _selected="";
			var brandteamTypeId = ${brandteamType.id};
			if(linkValue == brandteamTypeId){
				_selected = "selected";
			}
			linkIStr+='<option value="${brandteamType.id}" '+_selected+'>${brandteamType.name}</option>';
			</c:forEach>
			linkIStr+='</select>';
			$("#show1-2").html(linkIStr);
			break;
		case '10':
			$("#show1-1").html('商家店铺<span class="required">*</span>');
			$("#show1-2").html('<input type="text" id="linkValue" name="linkValue" placeholder="请输入商家店铺ID" value="' + linkValue + '" validate="{maxlength:16}"/>');
			break;	
		case '11':
			$("#show1-1").html('旧品牌特卖一级类目<span class="required">*</span>');
			linkIStr = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
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
	}
}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/wetaoPage/saveWetaoPageAdInfo.shtml">
		<input type="hidden" id="id" name="id" value="${wetaoPageAdInfo.id}"/>
		<input type="hidden" id="wetaoPageId" name="wetaoPageId" value="${wetaoPageId}"/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">广告类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td" id="linkTypeTd">
				 
				</td>
			</tr>
			<tr id="show1">
				<td id="show1-1" colspan="1" class="title"></td>
				<td id="show1-2" colspan="5" align="left" class="l-table-edit-td" ></td>
			</tr>
			<tr>
               <td  class="title" width="20%">广告图片：</td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="logoPic" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${wetaoPageAdInfo.pic}"></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<input id="pic" name="pic" type="hidden" value="${wetaoPageAdInfo.pic}">
               </td>
           </tr>
			
			<tr>
				<td colspan="1" class="title">自动上架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="upDate" name="upDate" value="<fmt:formatDate value='${wetaoPageAdInfo.upDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">自动下架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="downDate" name="downDate" value="<fmt:formatDate value='${wetaoPageAdInfo.downDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
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
