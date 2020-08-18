<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
 	.radioClass {
		margin-right: 20px;
	}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
    .activity-time {
    	margin-left: 50px;
    }
    .activity-hint {
    	color: #6B6B6B;
    }
</style>
<script type="text/javascript">
	var submitFlag = true;
	$(function(){
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 160
		});
		
		var dateArray = [];
		<c:if test="${not empty productTypeAList }" >
			var productTypeAList = ${productTypeAList };
			for(var i=0;i<productTypeAList.length;i++) {
				dateArray.push(dataBox(productTypeAList[i].name, productTypeAList[i].id));
			}
		</c:if>
		<c:if test="${not empty productTypeBList }" >
			var productTypeBList = ${productTypeBList };
			for(var i=0;i<productTypeBList.length;i++) {
				dateArray.push(dataBox(productTypeBList[i].name, productTypeBList[i].id));
			}
		</c:if>
		var productType2IdsComboBox = $("#productType2Id").ligerComboBox({ 
			isShowCheckBox: true, 
			isMultiSelect: true, 
			emptyText: false,
            data: dateArray, 
            valueFieldID: 'productType2Ids',
            selectBoxWidth: 160,
            width: 160
        }); 
		productType2IdsComboBox.selectValue('${productType2Ids }');
		productType2IdsComboBox.updateStyle();
		
		$(".moduleType").bind("change", function() {
			var moduleType = $('input[name="moduleType"]:checked').val();
			if(moduleType == '1') {
				$(".productType2Ids-tr").hide();
			}else {
				$(".productType2Ids-tr").show();
			}
		});
		
		$.metadata.setType("attr", "validate");
		$("#form1").validate({
	        errorPlacement: function (lable, element) {  
	        	var elementType=$(element).attr("type");
	        	if('radio'==elementType) {
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").parent("span").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else {
	        		$(element).ligerTip({
						content : lable.html(),width: 100
					});
	        	}
	        	$(".l-verify-tip-corner").css("z-index", "1001");
	        	$(".l-verify-tip-content").css("z-index", "1000");
	        },
	        success: function (lable,element) {
	            lable.ligerHideTip();
	            lable.remove();
	        },
	        submitHandler: function(form) {
	        	if($("input[name='moduleType']:checked").val() == '2' && $("#productType2Ids").val() == '') {
	        		commUtil.alertError("绑定类目不能为空！"); 
	        		return;
	        	}
	        	if(submitFlag){
	        		submitFlag = false;
	        		form.submit();
	        	}
	        }
	    }); 
		
	});
	
	function dataBox(text, id){ 
		 var obj = new Object();
		 obj.text = text; 
		 obj.id = id; 
		 return obj;
	}
	
	
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/mallCategoryModule/addOrUpdateMallCategoryModule.shtml" >
		<input type="hidden" id="id" name="id" value="${mallCategoryModuleCustom.id }" >
		<input type="hidden" id="mallCategoryId" name="mallCategoryId" value="${mallCategoryId }" >
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">类型<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<c:if test="${empty mallCategoryModuleCustom }">
						<label><input type="radio" class="moduleType" name="moduleType" value="1" checked="checked" >品牌推荐模块</label>
						<label style="margin-left: 20px;"><input type="radio" class="moduleType" name="moduleType" value="2" >类目模块</label>
					</c:if>
					<c:if test="${not empty mallCategoryModuleCustom }">
						<label><input type="radio" class="moduleType" name="moduleType" value="1" <c:if test="${mallCategoryModuleCustom.moduleType == '1' }">checked</c:if> >品牌推荐模块</label>
						<label style="margin-left: 20px;"><input type="radio" class="moduleType" name="moduleType" value="2" <c:if test="${mallCategoryModuleCustom.moduleType == '2' }">checked</c:if> >类目模块</label>
					</c:if>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">模块名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="categoryModuleName" name="categoryModuleName" value="${mallCategoryModuleCustom.categoryModuleName }" validate="{required:true,minlength:2,maxlength:15}" />
				</td>
           	</tr>
			<tr class="productType2Ids-tr" <c:if test="${mallCategoryModuleCustom.moduleType != '2' }">style="display: none;"</c:if> > 
            	<td class="title" width="20%">绑定类目<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="productType2Id" name="productType2Id" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	</body>
</html>