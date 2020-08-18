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
		$.ligerDialog.open({
			height: 300,
			width: 800,
			title: "追加发行量",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/coupon/couponManager.shtml?couponId="+couponId+"&statusPage=2",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//修改发行量
	function updateGrantQuantity(couponId, grantQuantity) {
		$("#"+couponId).find("td").eq(1).html(grantQuantity);
		$("#"+couponId).find("td").eq(3).html(Number(grantQuantity)-Number($("#"+couponId).find("td").eq(2).html()));
	}
	
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" method="post" action="">
		<input type="hidden" name="activityAreaId" value="${activityAreaId }" />
		<table class="gridtable">
			<tr align="center">
				<td class="title">面额</td>
				<td class="title">发行量</td>
				<td class="title">生成游离码数</td>
				<td class="title">未生成数</td>
				<td class="title">兑换优惠券数</td>
				<td class="title">优惠券使用数</td>
				<td class="title">操作</td>
			</tr>
			<c:forEach items="${couponCustomList }" var="couponCustom">
				<tr align="center" id="${couponCustom.id }" >
					<td>${couponCustom.money }元（满${couponCustom.minimum }）</td>
					<td>${couponCustom.grantQuantity }</td>
					<td>${couponCustom.sumCouponExchange }</td>
					<td>${couponCustom.grantQuantity - couponCustom.sumCouponExchange }</td>
					<td>${couponCustom.sumCouponIsExchange }</td>
					<td>${couponCustom.useQuantity }</td>
					<td><a href="javascript:addGrantQuantity(${couponCustom.id });">【追加发行量】</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	</body>
</html>