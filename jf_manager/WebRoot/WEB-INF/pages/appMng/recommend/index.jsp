<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	function loadTree() {
		var root = [{id: 10000, text: '推荐设置', isExpand: true, children: [{id: 1, text: '首页推荐'},{id: 2, text: '运动推荐'},{id: 3, text: '鞋包推荐'},
		                                                            {id: 4, text: '服饰推荐'},{id: 5, text: '生活推荐'},{id: 11, text: '钟表推荐'},{id: 12, text: '数码推荐'},
		                                                            {id: 13, text: '美妆推荐'},{id: 14, text: '母婴推荐'},{id: 15, text: '食品推荐'}]},
		            {id: 10001, text: '推荐统计', isExpand: true, children: [{id: 101, text: '首页统计'},{id: 102, text: '运动统计'},{id: 103, text: '鞋包统计'},
																	{id: 104, text: '服饰统计'},{id: 105, text: '生活统计'},{id: 111, text: '钟表统计'},{id: 112, text: '数码统计'},
																	{id: 113, text: '美妆统计'},{id: 114, text: '母婴统计'},{id: 115, text: '食品统计'}]}];
		$tree.setData(root);
	}
	
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
            		if(node.data.id == 1 || node.data.id == 2 || node.data.id == 3 || node.data.id == 4 || node.data.id == 5 
            				|| node.data.id == 11 || node.data.id == 12 || node.data.id == 13 || node.data.id == 14 || node.data.id == 15 ){
	            		$("#frm").attr("src", "${pageContext.request.contextPath}/appMng/recommend/list.shtml?catalog=" + node.data.id);
            		}else{
            			var catalog = parseInt(node.data.id)-100;
	            		$("#frm").attr("src", "${pageContext.request.contextPath}/appMng/recommend/countList.shtml?catalog=" + catalog);
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
  	  <div position="left" title="推荐类型">
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
