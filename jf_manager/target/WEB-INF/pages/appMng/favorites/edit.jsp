<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
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
$(function ()  {
	getCheckSub();
 	$("#firstProductTypeId").change( function() {
 		var selected=$(this).children('option:selected').val();
 		getSubList(selected);
 		getCheckSub();
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
			var isValidateOk=true;
			//检查图片
			var pic = document.getElementById("pic").value;
			if ($.trim(pic)=="" || pic==null){
				$.ligerDialog.error('请上传图片');
    			isValidateOk=false;
			}
			//获取选择中的二级类目
			var secondTypes = "";
		    $("[name='secondProductTypeIds']").each(function () {
		    	if (this.checked){
		    		secondTypes += this.value +",";
		        }
		    });
			if (secondTypes==""){
				$.ligerDialog.error('请选择关联的二级品类');
    			isValidateOk=false;
			}else{
				document.getElementById("secondType").value=secondTypes.replace(/(\,*$)/g, "");
			} 
		    
	    	if(isValidateOk){
	    		form.submit();
	    	}else{
				$("html,body").animate({scrollTop:$("body").offset().top},0);
	    	}
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

function getCheckSub()
{
	var secondProductTypeIdGroup="${favoritesCustom.secondProductTypeIdGroup}";
	if (secondProductTypeIdGroup!=""){
		var strs= new Array(); //定义一数组 
		strs=secondProductTypeIdGroup.split(","); //字符分割 
		$("input[name='secondProductTypeIds']").each(function(){
			if ($.inArray(this.value,strs)!=-1){
				this.checked=true;
			}
		});
	}
}

function getSubList(parentId)
{
	if(typeof(parentId)!="undefined"){
		var path = '${pageContext.request.contextPath}/service/prod/product_type/getProductTypeSub.shtml';
		jQuery.ajax( {
	  		url : path,
	  		type : 'POST',
	  		dataType : 'json',
	  		cache : false,
	  		async : false,
	  		data : { parentId : parentId},
	  		timeout : 30000,
	  		success : function(json) 
	  		{  
	  			var sel = $("#productType");
				sel.empty();
				$.each(json, function(index, item) {
					sel.append("<input name='secondProductTypeIds' type='checkbox' value='" + item.id + "'style='width: 30px;' />" + item.name);
				}); 
	  		},
			error : function() 
			{
				$.ligerDialog.warn('操作超时，请稍后再试！');
			}
	  	});
	}
}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/appMng/favorites/saveSubmit.shtml">
		<input type="hidden" id="id" name="id" value="${favoritesCustom.id}"/>
		<input id="secondType" name="secondType" type="hidden" value=""/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">名称<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="name" name="name" value="${favoritesCustom.name}" validate="{required:true}"/>
				</td>
			</tr>
				
			<tr>
               <td  class="title" width="20%">上传图片：<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="logoPic" style="width: 150px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${favoritesCustom.pic}"></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();"/>
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<input id="pic" name="pic" type="hidden" value="${favoritesCustom.pic}">
               </td>
           </tr>
           
           	<tr>
				<td colspan="1" class="title">关联类目<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<span style="margin-right:10px;">一级类目</span>
					<select style="width: 160px;" id="firstProductTypeId" name="firstProductTypeId" validate="{required:true}">
					<option value="">请选择</option>
					<c:forEach var="type1Item" items="${productTypes}">
						<option value="${type1Item.id}" <c:if test="${type1Item.id==favoritesCustom.firstProductTypeId}">selected="selected"</c:if>>${type1Item.name}
						</option>
					</c:forEach>
					</select>
					<div style="height:1px;background-color:#333333;overflow:hidden;margin-top: 10px;margin-bottom: 10px;"></div>
					<div id="productType">
					<c:forEach var="type2Item" items="${productType2s}">
					<input name="secondProductTypeIds" type="checkbox" value="${type2Item.id}" style="width: 30px;"/>${type2Item.name}
					</c:forEach>
					</div>
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