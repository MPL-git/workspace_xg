<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<script type="text/javascript">

</script>
<html>
<body>
	<div class="title-top">
		<table class="gridtable marT10">
		<tr>
              <td class="title">来源</td> 
              <td class="title">访客数</td>
              <td class="title">访客数占比</td>
              <td class="title">访问次数</td>
              <td class="title">访问次数占比</td>
		</tr>
		<c:forEach var="sourceMap" items="${sourceList}"> 
          	<tr>
              <td>${sourceMap.page_classify_desc}</td>
              <td>${sourceMap.total_visitor_count}</td>
              <td>${sourceMap.total_visitor_rate}</td>
              <td>${sourceMap.total_pv_count}</td>
              <td>${sourceMap.total_pv_rate}</td>
			</tr>
		</c:forEach>
        </table>	        	  
	</div>
</body>
</html>
