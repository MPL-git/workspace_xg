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
<script type="text/javascript">

</script>
<body>
		<table class="gridtable">
			<tr>
				 <td>
					<c:if test="${not empty video.videoUrl}">
					<video id="videos" width="450" height="260" controls="controls">
    				 	<source src="${pageContext.request.contextPath}/file_servelt${video.videoUrl}" type="video/mp4">
  					</video>
				   </c:if>	
				</td>
			</tr>
		</table>
</body>
</html>
