<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass{
	margin-right: 20px;
}

.l-table-edit-td {
	padding: 4px;
}

.table-title{
font-size: 17px;font-weight: 600;
}
.center-align{
	text-align: center;
}
table.gridtable td.title{
	text-align: left;
}
table.gridtable .title{
border-style: none;
}
</style>
<html>
<body>
<form name="form1" class="form1" method="post" id="form1">
		<table class="gridtable">
			<tr>
				<td colspan="5" style="border-style: none;">
					<p>新增会员数：统计日期内注册的会员总数(去重)</p>
					<br>
					<p>新增会员消费人数：统计日期内注册会员在注册当天有付款操作的会员总数(去重)</p>
					<br>
					<p>活跃会员数：统计日期内有登入的会员总数(去重)</p>
					<br>
					<p>消费会员数：统计日期内有消费的会员总数(去重)</p>
					<br>
					<p>新增SVIP会员数：统计日期内有付款SVIP订单的会员总数(去重)</p>
				</td>
			</tr>
		</table>
</form>
</body>
</html>
