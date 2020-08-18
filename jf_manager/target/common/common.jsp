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
<link href="${pageContext.request.contextPath}/jui/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${pageContext.request.contextPath}/jui/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${pageContext.request.contextPath}/jui/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${pageContext.request.contextPath}/jui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>

<!-- 
<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/jquery/jquery-1.8.3.min.js"></script>  -->
<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/utils/util.js"></script>	
<script src="${pageContext.request.contextPath}/jui/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jui/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jui/js/jquery.validate.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jui/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jui/bin/dwz.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jui/js/dwz.regional.zh.js" type="text/javascript"></script>
	


<%--  jui -start 
<link href="<%=request.getContextPath()%>/jui/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=request.getContextPath()%>/jui/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=request.getContextPath()%>/jui/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=request.getContextPath()%>/jui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/> --%>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
 
<!--[if lte IE 9]>
<script src="<%=request.getContextPath()%>/jui/js/speedup.js" type="text/javascript"></script>
<![endif]-->
 <!--  
<script src="<%=request.getContextPath()%>/jui/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>  -->
 
<!-- svgͼ��  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ 
<script type="text/javascript" src="<%=request.getContextPath()%>/jui/chart/raphael.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jui/chart/g.raphael.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jui/chart/g.bar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jui/chart/g.line.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jui/chart/g.pie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jui/chart/g.dot.js"></script> -->
 <%--
<script src="<%=request.getContextPath()%>/jui/js/dwz.core.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.util.date.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.drag.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.tree.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.accordion.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.ui.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.theme.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.navTab.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.tab.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.resize.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.dialog.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.stable.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.ajax.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.pagination.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.database.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.effects.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.panel.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.history.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.combox.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.print.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jui/js/dwz.regional.zh.js" type="text/javascript"></script>
 end --%>


<!--  
<script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/pop.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer-1.8.1/layer.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tags/page.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/tags/css/tags.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonstyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commonDev.css"/>
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>tags/js/page.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/tags/css/zTree/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/tags/js/zTree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/tags/js/tree.js"></script>
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/tags/js/util.js"></script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/tags/js/ajaxSelect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/comm-css.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/formValidate.js"></script>
-->


