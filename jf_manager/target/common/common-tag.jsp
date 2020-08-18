<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" " http://www.w3.org/TR/html4/loose.dtd">

<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires",0); 
%>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
	
</script>
<%-- Liger  --%>
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
 <%-- Liger -validate --%>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>

<%-- JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/commUtil.js"></script>

<%-- extra define --%>
<link href="${pageContext.request.contextPath}/css/extra.css" rel="stylesheet" type="text/css" />