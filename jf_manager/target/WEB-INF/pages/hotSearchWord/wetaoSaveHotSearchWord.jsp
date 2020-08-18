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
					if($("#tag1").attr("checked")==true &&$("#tag2").attr("checked")==true){
						commUtil.alertError("标签最多只能选1个！");
						return;
					}
					if(submitFlag){
						submitFlag = false;
						form.submit();
					}
				}
			});

		});

	</script>

</head>
<body style="margin: 10px; ">
<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/wetaoHotSearchWord/addHotSearchWord.shtml" >
	<table class="gridtable">
		<tr>
			<td class="title" width="20%">关键词<span class="required">*</span></td>
			<td align="left" class="l-table-edit-td" >
				<input style="width:160px;" type="text" id="word" name="word" value="" validate="{required:true,minlength:2,maxlength:10}" />
			</td>
		</tr>
		<tr>
			<td class="title" width="20%">标签</td>
			<td align="left" class="l-table-edit-td" >
				<input type="checkbox" id="tag1" name="tag1" value="1" >热
				<input style="margin-left: 32px;" type="checkbox" id="tag2" name="tag2" value="2">荐
			</td>
		</tr>
		<tr>
			<td class="title" width="20%">热搜状态<span class="required">*</span></td>
			<td align="left" class="l-table-edit-td" >
				<label ><input type="radio" id="status" name="status" value="0" checked />下架</label>
				<label style="margin-left: 20px;"><input type="radio" id="status" name="status" value="1" />上架</label>
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
</body>
</html>