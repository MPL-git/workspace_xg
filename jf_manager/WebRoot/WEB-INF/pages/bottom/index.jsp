<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>底部指南导航</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/ligerTreeUtil.js" type="text/javascript"></script>
	
    <script type="text/javascript">
    var productTypeTree = ${bottom};
    	var treeRoot = [{id: 0, text: '根节点', isExpand: true, level: 0, children: productTypeTree}];
    	var treeObject = null;
    	
    	$(function() {
    	$("#layout1").ligerLayout({leftWidth: 200});
    	
    		treeObject = $("#menuTree").ligerTree({
    			data : treeRoot,
                checkbox: false,
                slide: false,
                nodeWidth: 120,
                attribute: ['nodename', 'url'],
                btnClickToToggleOnly: true,
                needCancel: false,  //不能取消选择
                isExpand: false,
                onSelect: function (node) {
                
                	if(node.data.id != 0 && node.data.pid != 0){
                	$("#frm").attr("src", "${pageContext.request.contextPath}/service/bottom/edit.shtml?BOTTOM_ID=" + node.data.id);
                 }
                 
                }
    		});
    		
    		var height = $(".l-layout-center").height();
    		$("#treeContent").css({height: height - 34});
    	});
    	
		
		
    </script>
  </head>
  
  <body style="padding:10px; margin:0;">
    <div class="l-loading" style="display: none;" id="pageloading"></div>
    <div id="layout1">
  	  <div position="left" title="底部指南导航">
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
