<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
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

</script>
<html>
<body>
<form id="form" method="post">
	<c:if test="${not empty map.weight_update_date}">
		<div><font size="3" color="#a9a9a9">${map.weight_update_date} ~ 至今</font></div>
	</c:if>
	<table class="gridtable">
	
		<thead>
			<td colspan="1" class="title">权重因素</td>
				<td colspan="3" align="center" class="title">
					<div>权重值</div>
				</td>
			    <td colspan="3" align="center" class="title">
					<div>备注</div>
				</td>
		</thead>

		<tr>
			<td class="title">销量</td>
			<td colspan="3" align="center" class="l-table-edit-td">
				<c:if test="${empty map.sales_volume}">
					<div>0</div>
				</c:if>
				<c:if test="${not empty map.sales_volume}">
					<div>${map.sales_volume}</div>
				</c:if>
			</td>
			<td colspan="3" align="center" class="l-table-edit-td">
				<c:if test="${empty map.sales_volume}">
					<div>0件</div>
				</c:if>
				<c:if test="${not empty map.sales_volume}">
					<div>${map.sales_volume}件</div>
				</c:if>
			</td>
		</tr>

		<tr>
			<td class="title">销售额</td>
			<td colspan="3" align="center" class="l-table-edit-td">
				<c:if test="${empty map.sales_degree_weight}">
					<div>0</div>
				</c:if>
				<c:if test="${not empty map.sales_degree_weight}">
					<div>${map.sales_degree_weight}</div>
				</c:if>
			</td>
			<td colspan="3" align="center" class="l-table-edit-td">
				<c:if test="${empty map.sales_degree}">
					<div>0.00元</div>
				</c:if>
				<c:if test="${not empty map.sales_degree}">
					<div>${map.sales_degree}元</div>
				</c:if>
			</td>
		</tr>

		<tr>
			<td class="title">合计</td>
			<td colspan="3" align="center" class="l-table-edit-td">
				<c:if test="${empty map.weight}">
					<div>0</div>
				</c:if>
				<c:if test="${not empty map.weight}">
					<div>${map.weight}</div>
				</c:if>
			</td>
			<td colspan="3" align="left" class="l-table-edit-td">
				<div></div>
			</td>
		</tr>
	</table>
	<br>
	<div><font size="1" color="#a9a9a9">注:销量和销售额仅统计商品权重计算周期内的数据(每30天清空数据,重新进行数据的统计)</font></div>
</form>		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
