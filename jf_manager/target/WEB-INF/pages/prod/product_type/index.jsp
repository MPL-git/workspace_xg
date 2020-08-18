<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>商品类型</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/common/js/ligerTreeUtil.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/liger/indexdata.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/common/js/ligerTreeUtil.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var productTypeTree = ${productTypeList};
    	 var treeObject = null;
    	$(function() {
    		$("#layout1").ligerLayout({leftWidth: 200});
    		
    		treeObject = $("#menuTree").ligerTree({
    			data : productTypeTree,
                checkbox: false,
                slide: false,
                nodeWidth: 120,
                attribute: ['nodename', 'url'],
                btnClickToToggleOnly: true,
                needCancel: false,  //不能取消选择
                isExpand: 2,
                onSelect: function (node) {
                	$("#frm").attr("src", "${pageContext.request.contextPath}/service/prod/product_type/list.shtml?id=" + node.data.id);
                 
                }
    		});
    		
    		var height = $(".l-layout-center").height();
    		$("#treeContent").css({height: height - 34});
    	});
    	
    	function addNode(id, text) {
    		if(treeObject) {
    			var curNode = treeObject.getSelected();
    			if(curNode) {
    				var ids = id.split(",");
    				var names = text.split(",");
    				for(var i=0;i<ids.length;i++) {
    					LigerTreeUtil.addNodes(treeObject, curNode, [{id:ids[i], text:names[i]}]);
    				}
    			}
    		}
    	}
    	function delNode(id) {
    		if(treeObject) {
    			var curNode = treeObject.getSelected();
    			if(curNode) {
    				LigerTreeUtil.removeNodes(treeObject, id);
    			}
    		}}
    	function updateNode(id,text) {
			if(treeObject) { 
				var curNode = treeObject.getSelected(); 
				if(curNode) { 
					LigerTreeUtil.updateNodes(treeObject, curNode,text);
				}
			}} 
    </script>
  </head>
  
  <body style="padding:10px; margin:0;">
  	<div id="layout1">
  	  <div  position="left" title="商品类型树">
  	  <div id="toptoolbar"></div>
  	   	<div id="treeContent" style="height: 100%; overflow: scroll;">
  	    <ul id="menuTree" style="margin-top:3px;"></ul>
  	    </div>
  	  </div>
  	  <div position="center">
  	  	<iframe id="frm" frameborder="0" name="home" id="home" src="" style="width: 100%; height: 100%;"></iframe>
  	  </div>
  	</div> 
  </body>
</html>
