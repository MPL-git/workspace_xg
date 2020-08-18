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
		var root = [{id: 10000, text: '重点页面', isExpand: true, children: [{id: 2, text: '首页栏目装修'},{id: 20, text: '首页H5装修'},{id: 3, text: '商品主题馆'}]},
		            {id: 10001, text: '营销页面', isExpand: true, children: [{id: 6, text: '购物车'},{id: 7, text: '消息'},{id: 8, text: '商品未上架'},{id: 4, text: '现金签到'},{id: 5, text: '砍价免费拿'},{id: 23, text: 'SVIP'},{id: 9, text: '邀请享免单'},{id: 21, text: '醒购店长权益介绍'}]},
					{id: 10002, text: '小程序页面', isExpand: true, children: [{id: 10, text: '首页栏目装修'}]},
					{id: 10003, text: 'H5页面装修', isExpand: true, children: [{id: 11, text: '首页栏目装修'}]},
					{id: 10004, text: 'APP风格管理', isExpand: true, children: [{id: 12, text: '首页顶部风格'},{id: 14, text: '其他页面顶部风格'},{id: 15, text: '个人中心主题背景'},{id: 13, text: '首页顶部tab'}]}
		           ];
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
	       			var pageType = node.data.id;
	        		if(node.data.id == 1 || node.data.id == 2 || node.data.id == 3 || node.data.id == 10 || node.data.id == 11 || node.data.id == 20 ){
	        			var status='0';
	        			$("#frm").attr("src", "${pageContext.request.contextPath}/customAdPage/keyPageManager.shtml?pageType=" + pageType+"&status="+status);
	        		}else if(node.data.id == 12 || node.data.id == 14){
	        			var type = 0;
	        			if(node.data.id == 14){
	        				type = 1;
	        			}
	        			$("#frm").attr("src", "${pageContext.request.contextPath}/marketing/indexTopStyle/list.shtml?type="+type);
	        		}else if(node.data.id == 15){
						$("#frm").attr("src", "${pageContext.request.contextPath}/marketing/personalCenterBackground/list.shtml");
					}else if(node.data.id == 13){
	        			$("#frm").attr("src", "${pageContext.request.contextPath}/marketing/indexTopTab/list.shtml");
	        		}else{
	        			$("#frm").attr("src", "${pageContext.request.contextPath}/customAdPage/marketingPageManager.shtml?pageType=" + pageType);
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
  	  <div position="left" title="页面类型">
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
