<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- Liger  --%>
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 

<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
  <style type="text/css">
        body{ font-size:12px;padding:10px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
  </style>
<body >
<script type="text/javascript">
 
 var commTree ={
   treeObj:null,
   initTree:function(){
            $("#menuTree").ligerTree({
   			   data : ${TREE_DATA_JSON},
               checkbox: false,
               slide: false,
               checkbox:false,
               attribute: ['nodename', 'url'],
               btnClickToToggleOnly: false ,
               isExpand:1
            });
            commTree.treeObj = $("#menuTree").ligerGetTreeManager();
   
   
   },
 
 };
 
$(function() {
   commTree.initTree(); 
 	
}); 

</script>
	<form id="postForm" method="post" action="" >
		  <ul id="menuTree" style="margin-top:3px;margin-left:100px;"></ul>
	</form>
</body>
</html>
