<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
 
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
        type="text/javascript"></script>
  
<html>
<script type="text/javascript">
	$(function(){
		if (${mchtInfo!=null && fn:length(mchtInfo) > 0}) {
			parent.mchtCheckFlag = true;
		} else {
			parent.mchtCheckFlag = false;
		}
	})
</script>
<body>
<form method="post" id="form1" name="form1">
    <c:if test="${mchtInfo!=null}">
        <table class="gridtable">
        	<tr>
                <td colspan="1" class="title" style="text-align: center;">商家序号</td>
                <td colspan="1" class="title" style="text-align: center;">公司名称</td>
                <td colspan="1" class="title" style="text-align: center;">店铺名称</td>
                <td colspan="1" class="title" style="text-align: center;">合作状态</td>
            </tr>
            <tr>
                <td colspan="1">
                	${mchtInfo.mchtCode}
               	</td>
                <td colspan="1">
                	${mchtInfo.companyName}
                </td>
                <td colspan="1">
                	${mchtInfo.shopName}
                </td>
                <td colspan="1">
                	${mchtInfo.status_desc}
                </td>
            </tr>
        </table>
    </c:if>
    <c:if test="${mchtInfo==null}">
    	<p>商家不存在或商家序号填写错误。</p>
    </c:if>
</form>
</body>
</html>
