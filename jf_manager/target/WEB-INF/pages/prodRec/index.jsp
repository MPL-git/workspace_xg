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
	 
	function modify() {
		if(!$tree) {
			$.ligerDialog.warn("树不存在！");
			return false;
		}
		
		var node = $tree.getSelected();
		if(!node) {
			$.ligerDialog.warn("请选择要修改的分类！");
			return false;
		}
		
		if(node.data.id == 0) {
			$.ligerDialog.warn("根对象不能修改！");
			return false;
		}
		
		$.ligerDialog.open({
			height: 200,
			width: 400,
			title: "修改",
			name: "MODIFY_WINDOW",
			url: "${pageContext.request.contextPath}/prodRec/edit.shtml?CATALOG_ID=" + node.data.id,
			showMax: false,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null,
			buttons: [
				{text: "保存",
				 cls: "l-dialog-btn-highlight",
				 onclick: function(item, dialog) {
					 var result = $("#MODIFY_WINDOW")[0].contentWindow.onSubmit();
					 if(result.RESULT_CODE != 0) {
						 $.ligerDialog.warn(result.RESULT_MESSAGE);
					 } else {
						 dialog.close();
						 $.ligerDialog.success(result.RESULT_MESSAGE);
						 loadTree();
					 }
				 }
				},
				{text: "取消",
				 onclick: function (item, dialog) {dialog.close();}
				}
			]
		});
	}
	
	function loadTree() {
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/prodRec/calalogtype.shtml",
			dataType: 'json',
			success: function(data) { 
				data = LigerTreeUtil.dataFormat(data, "id", "text"); 
				var root = [{id: 0, text: '根节点', isExpand: true, children: data}]; 
				$tree.setData(root);
			}
		});
	}
	
	
	
  	$(function() { 
		$("#layout1").ligerLayout({leftWidth: 220, minLeftWidth: 220});
		
		$("#toptoolbar").ligerToolBar({
			items: [ 
			  {text: '修改', icon: 'modify', click: modify}
			]
		});
		
		$tree = $("#menuTree").ligerTree({
			//data : root,
			//nodeDraggable: true,
			checkbox: false,
            slide: false,
            nodeWidth: 150,
            attribute: ['nodename', 'url'],
            //btnClickToToggleOnly: false,  //只能加减号展开
            isExpand: false,
            needCancel: false,  //不能取消选择
            onSelect: function(node) {
            	if(node.data.id != 0) {
            		$("#frm").attr("src", "${pageContext.request.contextPath}/prodRec/list_index.shtml?CATALOG_ID=" + node.data.id);
            	}
            }
		}); 
		loadTree();  //加载数据 
		var height = $(".l-layout-center").height();
		$("#treeContent").css({height: height - 34});
	}); 
    	
    	
    </script>
  </head>
    <body style="padding:10px; margin:0;">
    <div class="l-loading" style="display: none;" id="pageloading"></div>
    <div id="layout1">
  	  <div position="left" title="栏目配置">
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
