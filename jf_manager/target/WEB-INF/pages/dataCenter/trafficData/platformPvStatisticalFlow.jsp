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
		<div class="table-title">
			<span>来源</span>
		</div>
		<table class="gridtable marT10">
		<tr>
              <td class="title">来源</td> 
              <td class="title">访客数(会员)</td>
              <td class="title">访客数占比(会员)</td>
			  <td class="title">访客数(非会员)</td>
              <td class="title">访客数占比(非会员)</td>
              <td class="title">访问次数(会员)</td>
              <td class="title">访问次数占比(会员)</td>
			  <td class="title">访问次数(非会员)</td>
              <td class="title">访问次数占比(非会员)</td>
		</tr>
		<c:forEach var="upstreamMap" items="${upstreamList}"> 
          	<tr>
              <td>${upstreamMap.page_classify_desc}</td>
              <td>${upstreamMap.total_visitor_count}</td>
              <td>${upstreamMap.total_visitor_rate}</td>
			  <td>${upstreamMap.total_visitor_count_tourist}</td>
              <td>${upstreamMap.total_visitor_rate_tourist}</td>
              <td>${upstreamMap.total_pv_count}</td>
              <td>${upstreamMap.total_pv_rate}</td>
				<td>${upstreamMap.total_pv_count_tourist}</td>
              <td>${upstreamMap.total_pv_rate_tourist}</td>
			</tr>
		</c:forEach>
        </table>	        	  
	</div>
	<div class="title-top">
		<div class="table-title">
			<span>去向</span>
		</div>
		<table class="gridtable marT10">
		<tr>
              <td class="title">去向</td> 
              <td class="title">访客数(会员)</td>
              <td class="title">访客数占比(会员)</td>
			  <td class="title">访客数(非会员)</td>
              <td class="title">访客数占比(非会员)</td>
              <td class="title">访问次数(会员)</td>
              <td class="title">访问次数占比(会员)</td>
			  <td class="title">访问次数(非会员)</td>
              <td class="title">访问次数占比(非会员)</td>
		</tr>
		<c:forEach var="downstreamMap" items="${downstreamList}"> 
          	<tr>
              <td>${downstreamMap.page_classify_desc}</td>
              <td>${downstreamMap.total_visitor_count}</td>
              <td>${downstreamMap.total_visitor_rate}</td>
			  <td>${downstreamMap.total_visitor_count_tourist}</td>
              <td>${downstreamMap.total_visitor_rate_tourist}</td>
              <td>${downstreamMap.total_pv_count}</td>
              <td>${downstreamMap.total_pv_rate}</td>
			  <td>${downstreamMap.total_pv_count_tourist}</td>
              <td>${downstreamMap.total_pv_rate_tourist}</td>
			</tr>
		</c:forEach>
        </table>	        	  
	</div>
</body>
</html>
