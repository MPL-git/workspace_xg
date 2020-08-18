<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 
    <script src="${pageContext.request.contextPath}/liger/lib/json2.js"></script>
    <!-- 主页独有样式 -->
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" /> 
   <script type="text/javascript">
 function keydown() {
    if (event.keyCode == 8) {
     event.returnValue = false; 
     }
    }
  document.onkeydown = keydown;
 </script>
    <script type="text/javascript">
    	var tab = null;
    	var menuTree = null;
    	var menuData = ${MENU_TREE_JSON};  
    	$(function() {
    		$("#layoutMain").ligerLayout({leftWidth: 190, height: '100%', space: 4});
    		
    		var height = $(".l-layout-center").height();
    		
    		$("#accordion1").css({height: height - 24});
    		
    		$("#menuTree").ligerTree({
    			data : menuData,
                checkbox: false,
                slide: false,
                 isExpand: 1,
                nodeWidth: 120,
                attribute: ['nodename', 'url'],
                btnClickToToggleOnly: false,
                onSelect: function (node) {
                	var url = node.data.url;
                	
                	if (!url) return;
                	
                	//获取tabid
                	var tabid = $(node.target).attr("tabid");
                	if (!tabid) {
                		tabid = new Date().getTime();
                        $(node.target).attr("tabid", tabid);
                	}
                	
                	
                	url = "${pageContext.request.contextPath}" + url;
                	tab.addTabItem({
                		tabid: tabid,
                		text: node.data.text,
                		url: url,
                        callback: function () {}
                	});
                	//alert(tabid);
                	
                }
    		});
    		
    		$("#framecenter").ligerTab({
    			height: height,
                showSwitchInTab : true,
                showSwitch: true,
                onAfterAddTabItem: function(tabid) {},
                onAfterRemoveTabItem: function(tabid) {}
    		});
    		
    		tab = liger.get("framecenter");
    		menuTree = liger.get("menuTree");
    		
    		
    	});
    	
    	function addTab(tabid, title, url) {
    		tab.addTabItem({
        		tabid: tabid,
        		text: title,
        		url: "${pageContext.request.contextPath}" + url,
                callback: function () {}
        	});
    	}
    	
    </script>
  </head>
  <body style="padding: 0px; background: #eaeef5;">
    <div id="pageloading"></div>
    <div id="topmenu" class="l-topmenu" style="height:56px;">
      <div style="height:56px;float:left;margin:0 0 0 20px;">	
      <img src="${pageContext.request.contextPath}/images/login/logo.png" />
      </div>
      <div  style="height:56px;float:left;margin:0 0 0 20px;">
      <label style="font:18px/56px '微软雅黑';color:#0a5899;">后端管理平台</label>
      </div>
      <div  style="height:56px;float:right;margin:0 20px 0 0;">
     <label  style="font:18px/56px '微软雅黑';color:#0a5899;">欢迎：${sessionScope.USER_SESSION.staffName}     </label>  
     <a href="${pageContext.request.contextPath}/logOut.shtml" style="font:18px/56px '微软雅黑';color:#0a5899;">退出</a>  
       </div>
      </div>
    <div id="layoutMain" style="width:100%; margin:0 auto;">
    	<div position="left"  title="主要菜单" id="accordion1" style="overflow: scroll;">
    		<ul id="menuTree" style="margin-top:0px;"></ul>
    	</div>
    	<div position="center" id="framecenter">
    		<div tabid="home" title="我的主页" style="height:300px">
    			<iframe frameborder="0" name="home" id="home" src="welcome.shtml"></iframe>
    		</div> 
    	</div>
    </div>
  </body>
</html>
