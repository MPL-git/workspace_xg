<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}
</style>
<script type="text/javascript">
$(function(){
	<c:if test="${not empty singleProductActivityControlCustom.productTypeIds}">
		var productTypeIds = '${singleProductActivityControlCustom.productTypeIds}';
		var productTypeIdArray = productTypeIds.split(",");
		$("input[name='productTypeId']").each(function(){
			var productTypeId = $(this).val();
			var index = $.inArray(productTypeId,productTypeIdArray);
			if(index>=0){
				$(this).attr("checked",true);
			}
		});
	</c:if>
});
function save(){
	var id = $("#id").val();
	var productTypeIds="";
	$("input[name='productTypeId']:checked").each(function(){
		if(!productTypeIds){
			productTypeIds = $(this).val();
		}else{
			productTypeIds += ","+$(this).val();
		}
	});
	var showToMchtCodes = $("#showToMchtCodes").val().trim();
	var hideToMchtCodes = $("#hideToMchtCodes").val().trim();
	$.ajax({
		url : "${pageContext.request.contextPath}/singleProductActivity/singleProductActivityControl/save.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {id:id,productTypeIds:productTypeIds,showToMchtCodes:showToMchtCodes,hideToMchtCodes:hideToMchtCodes},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				commUtil.alertSuccess("保存成功");
				
				parent.location.reload();
				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function checkShowMcht(){
	var showToMchtCodes = $("#showToMchtCodes").val();
	if(!showToMchtCodes){
		commUtil.alertError("请输入特定可见商家");
		return;
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/singleProductActivity/singleProductActivityControl/checkMcht.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {mchtCodes:showToMchtCodes},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				if(data.mchtIds){
					$.ligerDialog.open({
						height: $(window).height()*0.8,
						width: $(window).width()*0.8,
						title: "商家信息",
						name: "INSERT_WINDOW",
						url: "${pageContext.request.contextPath}/singleProductActivity/singleProductActivityControl/mchtList.shtml?mchtIds=" + data.mchtIds,
						showMax: true,
						showToggle: false,
						showMin: false,
						isResize: true,
						slide: false,
						data: null
					});
				}else{
					commUtil.alertError("商家不存在或商家序号填写错误");
					return;
				}
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function checkHideMcht(){
	var hideToMchtCodes = $("#hideToMchtCodes").val();
	if(!hideToMchtCodes){
		commUtil.alertError("请输入特定不可见商家");
		return;
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/singleProductActivity/singleProductActivityControl/checkMcht.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {mchtCodes:hideToMchtCodes},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				if(data.mchtIds){
					$.ligerDialog.open({
						height: $(window).height()*0.8,
						width: $(window).width()*0.8,
						title: "商家信息",
						name: "INSERT_WINDOW",
						url: "${pageContext.request.contextPath}/singleProductActivity/singleProductActivityControl/mchtList.shtml?mchtIds=" + data.mchtIds,
						showMax: true,
						showToggle: false,
						showMin: false,
						isResize: true,
						slide: false,
						data: null
					});
				}else{
					commUtil.alertError("商家不存在或商家序号填写错误");
					return;
				}
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
<form id="form" method="post" action="${pageContext.request.contextPath}/singleProductActivity/singleProductActivityControl/save.shtml">
	<input type="hidden" id="id" name="id" value="${singleProductActivityControlCustom.id}">
	<table class="gridtable">
		<tr>
			<td class="title">不可见类目</td>
			<td align="left" class="l-table-edit-td">
				<c:forEach items="${productTypeList}" var="productType">
					<input type="checkbox" name="productTypeId" value="${productType.id}">${productType.name}&nbsp;&nbsp;
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td class="title">特定可见商家</td>
			<td align="left" class="l-table-edit-td">
				<input type="hidden" id="showToMchtIds" name="showToMchtIds" value="${singleProductActivityControlCustom.showToMchtIds}" />
				<textarea id="showToMchtCodes" rows="5" class="textarea" cols="80">${singleProductActivityControlCustom.showToMchtCodes}</textarea>
				<input type="button" style="background-color: rgba(255, 153, 0, 1);width: 50px;cursor:pointer;height: 24px;border-radius: 3px;" onclick="checkShowMcht();" value="检测">
				<span style="color: #6B6B6B;">注意：商家序号用英文逗号隔开</span>
			</td>
		</tr>
		<tr>
			<td class="title">特定不可见商家</td>
			<td align="left" class="l-table-edit-td">
				<input type="hidden" id="hideToMchtIds" name="hideToMchtIds" value="${singleProductActivityControlCustom.hideToMchtIds}" />
				<textarea id="hideToMchtCodes" rows="5" class="textarea" cols="80">${singleProductActivityControlCustom.hideToMchtCodes}</textarea>
				<input type="button" style="background-color: rgba(255, 153, 0, 1);width: 50px;cursor:pointer;height: 24px;border-radius: 3px;" onclick="checkHideMcht();" value="检测">
				<span style="color: #6B6B6B;">注意：商家序号用英文逗号隔开</span>
			</td>
		</tr>
		<tr>
			<td class="title">操作</td>
			<td>
				<input type="button" class="l-button l-button-submit" value="提交" onclick="save();"/>&nbsp;&nbsp;
				<input type="button" value="取消" class="l-button l-button-test" onclick="frameElement.dialog.close();" />
			</td>
		</tr>
	</table>	
</form>		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
