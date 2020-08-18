<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>入驻及合同管理</title>
	<style type="text/css">
		body{
			font-size: 14px;
		}
		.row-body{
			padding:10px 20px;
		}
	</style>
</head>

<body>
<div class="x_panel container-fluid">
	<div class="row content-title">
		<div class="col-md-12">入驻及合同管理</div>
	</div>

	<div class="x_content container-fluid">
		<div class="row row-body" style="padding-top:20px;">
			<div class="col-md-12">
				公司名称：${mchtInfo.companyName}
			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-12">
				店铺名称：${mchtInfo.shopName}
			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-12">
				合作状态：${mchtInfo.statusDesc}
		</div>
		</div>
		<div class="row row-body">
			<div class="col-md-12">
				合作模式：${mchtInfo.mchtTypeDesc}
			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-12">
				合作开始日期：<fmt:formatDate value='${mchtInfo.cooperateBeginDate}' pattern='yyyy-MM-dd'/>
			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-12">
				最新合同开始：<fmt:formatDate value='${mchtInfo.agreementBeginDate}' pattern='yyyy-MM-dd'/>
			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-12">
				最新合同到期：<fmt:formatDate value='${mchtInfo.agreementEndDate}' pattern='yyyy-MM-dd'/>
			</div>
		</div>
	</div>
</div>


<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
	 data-backdrop="static">
</div>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script>

	$(document).ready(function () {

	});

</script>
</body>
</html>
