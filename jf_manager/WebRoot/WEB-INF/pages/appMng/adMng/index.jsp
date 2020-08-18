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
		var root = [{id: 10000, text: '广告设置', isExpand: true, children: [{id: 1, text: '首页头部广告'},{id: 2, text: '品类广告'},{id: 3, text: '首页专区广告'},{id: 4, text: '首页品牌推荐'},{id: 5, text: '频道广告'},{id: 6, text: '首页主题馆装修'},{id: 7, text: '日常营销入口装修'},{id: 8, text: '商品主题馆装修'},{id: 9, text: 'SVIP会场广告'},{id: 10, text: '启动广告'},{id: 11, text: '个人中心'}]},
		            {id: 10001, text: '广告统计', isExpand: true, children: [{id: 101, text: '首页统计'},{id: 102, text: '品类统计'}]}];
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
            		if(node.data.id == 1 || node.data.id == 2 || node.data.id == 9 || node.data.id == 10 || node.data.id == 11){
	            		$("#frm").attr("src", "${pageContext.request.contextPath}/appMng/adMng/list.shtml?mngPos=" + node.data.id);
            		}else if(node.data.id == 101 || node.data.id == 102){
            			$("#frm").attr("src", "${pageContext.request.contextPath}/appMng/adMng/countList.shtml?mngPos=" + node.data.id);
            		}else if(node.data.id == 3 || node.data.id == 4 || node.data.id == 5 || node.data.id == 6 || node.data.id == 7 || node.data.id == 8){
            			var flag = node.data.id;
            			$("#frm").attr("src", "${pageContext.request.contextPath}/appMng/homePageManager.shtml?flag=" + flag);
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
  	  <div position="left" title="广告类型">
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
