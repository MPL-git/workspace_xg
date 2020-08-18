<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/ligerTreeUtil.js" type="text/javascript"></script>
<script type="text/javascript"> 
	var $tree = null;
	<c:if test="${myApproval}">
	function loadTree() {
		var root = [{id: 1, text: '待我审批'},
		            {id: 2, text: '抄送我的'},
		            {id: 3, text: '全部审批流程'},
		           ];
		$tree.setData(root);
	}
	</c:if>
	
  	$(function() { 
		$("#layout1").ligerLayout({leftWidth: 200, minLeftWidth: 200});
		$tree = $("#menuTree").ligerTree({
			checkbox: false,
            slide: false,
            nodeWidth: 150,
            attribute: ['nodename', 'url'],
            isExpand: false,
            needCancel: false,  //不能取消选择
            onSelect: function(node) {
            	if(node.data.id != 0) {
            		if(node.data.id == 1 ){
            			$("#frm").attr("src", "${pageContext.request.contextPath}/approvalProcessManagement/examinedApprovedToMeList.shtml");
            		}else if (node.data.id == 2) {
            			$("#frm").attr("src", "${pageContext.request.contextPath}/approvalProcessManagement/copyApprovedToMe.shtml");
					}else if (node.data.id == 3) {
						$("#frm").attr("src", "${pageContext.request.contextPath}/approvalProcessManagement/allApprovedProcess.shtml");
					}
            	}
            }
		}); 
		loadTree();  //加载数据 
		var height = $(".l-layout-center").height();
		$("#treeContent").css({height: height});
	}); 
    	
    	
</script>
  </head>
    <body style="padding:10px; margin:0;">
    <div class="l-loading" style="display: none;" id="pageloading"></div>
    <div id="layout1">
  	  <div position="left" title="栏目树">
  	    <div id="toptoolbar"></div>
  	   	<div id="treeContent" style="height: 100%; overflow: scroll;">
  	    <ul id="menuTree" style="margin-top:3px;"></ul>
  	    </div>
  	  </div>
  	  <div position="center">
  	  	<iframe id="frm" frameborder="0" name="home" id="home" src="about:blank" style="width: 100%; height: 100%;" scrolling="no"></iframe>
  	  </div>
  	</div> 
  </body>
</html>
