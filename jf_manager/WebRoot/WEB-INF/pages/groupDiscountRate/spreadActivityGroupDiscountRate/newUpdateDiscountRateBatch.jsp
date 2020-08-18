<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>

	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
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
		.middle input {
			display: block;
			width: 30px;
			margin: 2px;
		}
		.l-table-edit-td {
			padding: 4px;
		}
		table.l-checkboxlist-table td{
			border-style: none;
		}
		table.l-listbox-table td{
			border-style: none;
			word-wrap: break-word;
			white-space: normal;
			word-break: break-all;
		}
	</style>
	<script type="text/javascript">
		var submitFlag = true;
		$(function(){
			$(".dateEditor").ligerDateEditor({
				showTime : true,
				format: "yyyy-MM-dd",
				labelAlign : 'left',
				width : 135
			});

			$("#listbox1").ligerListBox({
				isShowCheckBox: false,
				isMultiSelect: true,
				height: 250,
				width: 280,
			});

			$("#listbox2").ligerListBox({
				isShowCheckBox: false,
				isMultiSelect: true,
				height: 300,
				width: 280,
			});
			liger.get("listbox2").setData(${spreadActivityGroupJson});

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
					var groupNameId = liger.get("listbox1").data;
					if(groupNameId != null && groupNameId.length > 0 ) {
						var groupNameIds = "";
						for (var i=0;i<groupNameId.length;i++) {
							groupNameIds = groupNameIds+","+groupNameId[i].id;
						}
						$("#groupNameId").val(groupNameIds.substring(1));
					}else {
						commUtil.alertError("请选择渠道集合！");
						return;
					}
					var discountRateDateBegin = $("#discountRateDateBegin").val();
					var discountRateDateEnd = $("#discountRateDateEnd").val();
					if(discountRateDateBegin == '' || discountRateDateEnd == '' ) {
						commUtil.alertError("修改日期不能为空！");
						return;
					}
					var discountRate = $("#discountRate").val();
					if(discountRate == '' ) {
						commUtil.alertError("请填写优惠率！");
						return;
					}
					var flag = discountRate.match(/^(([1-9]{1}\d{0,6})|(0{1}))(\.\d{1,4})?$/);
					if(Number(discountRate) == Number(0) ) {
						commUtil.alertError("优惠率，请输入正数！");
						return;
					}else if(flag == null && discountRate.match(/^(([1-9]{1}\d*)|(0{1}))(\.\d+)?$/) != null ) {
						if(Number(999999.9999) < Number(discountRate) ) {
							commUtil.alertError("优惠率数值过大！");
							return;
						}else {
							commUtil.alertError("优惠率最多四位小数！");
							return;
						}
					}else if(flag == null ) {
						commUtil.alertError("优惠率，请输入正数！");
						return;
					}
					if(submitFlag){
						submitFlag = false;
						form.submit();
					}
				}
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

	</script>

</head>
<body style="margin: 10px; ">
<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/spreadActivityGroupDiscountRate/updateDiscountRateBatch.shtml" >
	<table class="gridtable">
		<tr>
			<td class="title">活动组名称<span class="required">*</span></td>
			<td align="left" class="l-table-edit-td">
				<input type="hidden" id="groupNameId" name="groupNameId" value="">
				<div style="margin:4px;float:left;">
					<div style="margin-bottom: 13px;">选中：</div>
					<div id="listbox1" class="l-listbox"></div>
				</div>
				<div style="margin:4px;float:left;height: 300px;" class="middle">
					<input type="button" style="margin-top: 80px;width: 25px;height: 25px" onclick="moveToLeft()" value="<">
					<input type="button" style="margin-top: 10px;width: 25px;height: 25px" onclick="moveToRight()" value=">">
					<input type="button" style="margin-top: 10px;width: 25px;height: 25px" onclick="moveAllToLeft()" value="<<">
					<input type="button" style="margin-top: 10px;width: 25px;height: 25px" onclick="moveAllToRight()" value=">>">
				</div>
				<div style="margin:4px;float:left;" class="test">
					<div id="listbox2" class="l-listbox" ></div>
				</div>
			</td>
		</tr>
		<tr>
			<td class="title" width="20%"> 修改日期<span class="required">*</span></td>
			<td align="left" class="l-table-edit-td" >
				<div  style="position: relative;">
					<div style="display: inline-block;position: absolute;">
						<input type="text" class="dateEditor" id="discountRateDateBegin" name="discountRateDateBegin" value="">
					</div>
					<span style="margin-left: 160px;position: absolute;">至</span>
					<div style="margin-left: 200px;display: inline-block;margin-bottom: 10px;">
						<input type="text" class="dateEditor" id="discountRateDateEnd" name="discountRateDateEnd" value="">
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td class="title" width="20%">优惠率<span class="required">*</span></td>
			<td align="left" class="l-table-edit-td" >
				<input type="text" id="discountRate" name="discountRate" value="" />
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