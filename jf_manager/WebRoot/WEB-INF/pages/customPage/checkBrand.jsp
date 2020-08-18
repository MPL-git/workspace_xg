<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<html>
<body>
<form method="post" id="form" name="form" action="">
			<table class="gridtable">
			<tr>
               <td class="title">正确</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	${rightNames}
               </td>
			</tr>
			<tr>
               <td class="title">错误</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	${errorIds}
               </td>
			</tr>
	        </table>
</form>
</body>
</html>
