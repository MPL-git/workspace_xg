<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
	 <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
 	.radioClass{margin: 0 10px 0 10px;}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
</style>
<script type="text/javascript">
		
	//追加发行量
	function addGrantQuantity(couponId) {
		var grantQuantity = $("#grantQuantity").val();
		if(grantQuantity == '') {
			commUtil.alertError("追加发行量，不能为空！");
			return;
		}
		if(!/^[1-9]\d*$/.test(grantQuantity)) {
			commUtil.alertError("请输入正整数！");
			return;
		}
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/coupon/updateGrantQuantity.shtml',
			data: {couponId : couponId, grantQuantity : grantQuantity},
			dataType: 'json',
			success: function(data) {
				if(data.statusCode == 200) {
					parent.updateGrantQuantity(couponId, data.message);
					frameElement.dialog.close();
				}else {
					commUtil.alertError(data.message);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	}
		
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" method="post" action="">
		<table class="gridtable">
			<tr align="center">
				<td class="title">面额</td>
				<td class="title">发行量</td>
				<td class="title">生成游离码数</td>
				<td class="title">未生成数</td>
				<td class="title">追加发行量</td>
			</tr>
			<tr align="center">
				<td>${couponCustom.money }元（满${couponCustom.minimum }）</td>
				<td>${couponCustom.grantQuantity }</td>
				<td>${couponCustom.sumCouponExchange }</td>
				<td>${couponCustom.grantQuantity - couponCustom.sumCouponExchange }</td>
				<td>
					<input name="grantQuantity" id="grantQuantity" value="">
				</td>
			</tr>
			<tr align="center">
				<td colspan="5" style="border: none;height: 50px;">
					<input type="button" onclick="addGrantQuantity(${couponCustom.id });" class="l-button l-button-submit" value="提交" /> 
				</td>
			</tr>
		</table>
	</form>
	</body>
</html>