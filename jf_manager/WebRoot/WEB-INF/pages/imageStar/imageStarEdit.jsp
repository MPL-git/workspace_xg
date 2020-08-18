<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link
	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/common/js/uploadImageList.js"
	type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/css/upload_image_list.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass {
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

table.l-checkboxlist-table td {
	border-style: none;
}

table.l-listbox-table td {
	border-style: none;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#listbox1")
				.ligerListBox(
						{
							isShowCheckBox : false,
							isMultiSelect : true,
							height : 100,
							width : 200,
							url : "${pageContext.request.contextPath}/productType/getProductType4ListBox.shtml?productTypeIds="
									+ "${ProductBrand.productTypeGroup}"
						});

		$("#listbox2")
				.ligerListBox(
						{
							isShowCheckBox : false,
							isMultiSelect : true,
							height : 150,
							width : 200,
							url : "${pageContext.request.contextPath}/productType/getProductType4ListBox.shtml?parentId=1"
						});
	});

	//移动到左边
	function moveToLeft() {
		var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
		var selecteds = box2.getSelectedItems();
		if (!selecteds || !selecteds.length)
			return;
		box2.removeItems(selecteds);
		box1.addItems(selecteds);
	}
	//移动到右边
	function moveToRight() {
		var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
		var selecteds = box1.getSelectedItems();
		if (!selecteds || !selecteds.length)
			return;
		box1.removeItems(selecteds);
		box2.addItems(selecteds);
	}
	//全部移动到左边
	function moveAllToLeft() {
		var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
		var selecteds = box2.data;
		if (!selecteds || !selecteds.length)
			return;
		box1.addItems(selecteds);
		box2.removeItems(selecteds);
	}
	//全部移动到右边
	function moveAllToRight() {
		var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
		var selecteds = box1.data;
		if (!selecteds || !selecteds.length)
			return;
		box2.addItems(selecteds);
		box1.removeItems(selecteds);
	}
	//提交操作
	$(function() {
		$("#Button1").bind('click',function(){
			var param = $("#form1").serialize();
			$.ajax({
				url : "${pageContext.request.contextPath}/service/imageStar/editImageStarSave.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : param,
				timeout : 30000,
				success : function(data) {
					if (data.statusCode==200) {
						commUtil.alertSuccess("保存成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.message);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});		
				
	});
	
	//文件上传
	function ajaxFileUploadLogo() {
		$.ajaxFileUpload({
			url : contextPath + '/service/common/ajax_upload.shtml',
			secureuri : false,
			fileElementId : "logoPicFile",
			dataType : 'json',
			success : function(result, status) {
				if (result.RESULT_CODE == 0) {
					$("#logoPic").attr("src",
							contextPath + "/file_servelt" + result.FILE_PATH);
					$("#logo").val(result.FILE_PATH);
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error : function(result, status, e) {
				alert("服务异常");
			}
		});
	}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/service/imageStar/editImageStarSave.shtml">
		<input id="paramId" name="paramId" type="hidden"
			value="${sysParamCfg.paramId}" />
		<table class="gridtable">
			<tr>
				<td class="title">图片编号 <span class="required">*</span></td>
				<td align="left" class="l-table-edit-td"><input id="paramCode"
					disabled="disabled" name="paramCode" type="text"
					value="${sysParamCfg.paramCode}" style="float:left;width: 200px;"
					validate="{required:true,paramCodeUnique:true}" /></td>
			</tr>
			<tr>
				<td class="title">图片名称</td>
				<td align="left" class="l-table-edit-td"><input id="paramName"
					name="paramName" type="text" disabled="disabled"
					value="${sysParamCfg.paramName}" style="float:left;width: 200px;"
					validate="{paramNameUnique:true}" /></td>
			</tr>
			<tr>
				<td class="title">LOGO图片 <span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div>
						<img id="logoPic" style="width: 100px;height: 50px" alt=""
							src="${pageContext.request.contextPath}/file_servelt${sysParamCfg.paramValue}">
					</div>
					<div style="float: left;margin: 10px;">
						<input style="position:absolute; opacity:0;" type="file"
							id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();" />
						<a href="javascript:void(0);" style="width:30%;">上传图片</a>
					</div> <input id="logo" name="logo" type="hidden"
					value="${sysParamCfg.paramValue}">
				</td>
			</tr>
			<tr>
				<td class="title">图片描述</td>
				<td align="left" class="l-table-edit-td"><input id="paramDesc"
					name="paramDesc" type="text" disabled="disabled"
					value="${sysParamCfg.paramDesc}" style="float:left;width: 200px;"
					validate="{paramDescUnique:true}" /></td>
			</tr>

			<tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div>
							<input name="btnSubmit" type="text" id="Button1"
								style="float:left;" class="l-button" value="提交" />
						</div>
						<div>
							<input name="btnCancle" type="button" id="Button2"
								style="float:left; margin-left: 40px" class="l-button"
								value="取消" onclick="frameElement.dialog.close();" />
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
