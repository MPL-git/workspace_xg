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
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
   <script src="${pageContext.request.contextPath}/common/js/ligerTreeUtil.js" type="text/javascript"></script>
     
  </head>
  
  <body style="padding:10px; margin:0;">
   <div class="l-loading" style="display: none;" id="pageloading"></div>
  	<div id="layout1">
  	  <div  position="left" title="【组织部门树】">
  	    <div id="toptoolbar"></div>
  	   	<div id="treeContent" style="height: 100%; overflow: scroll;">
  	    <ul id="menuTree" style="margin-top:3px;"></ul>
  	    </div>
  	  </div>
  	</div> 
  </body>
</html>
<script type="text/javascript">
	var $tree = null;
	
	function insertNode() {
		if(!$tree) {
			$.ligerDialog.warn("树不存在！");
			return false;
		}
		
		var node = $tree.getSelected(); 
		if(!node) {
			$.ligerDialog.warn("请选择上级的部门！");
			return false;
		}
		
		$.ligerDialog.open({
			height: 400,
			width: 800,
			title: "新增部门",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/orgMng/toadd_org.shtml?IF_EDIT=0&ORG_ID="+node.data.id,
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
					 var result = $("#INSERT_WINDOW")[0].contentWindow.onSubmit();
					 if(result.RESULT_CODE != 0) {
						 $.ligerDialog.warn(result.RESULT_MESSAGE);
					 } else {
						 dialog.close();
						 addNode(result.ORG_ID ,result.ORG_NAME);
						 $.ligerDialog.success(result.RESULT_MESSAGE);
					 }
				 }
				},
				{text: "取消",
				 onclick: function (item, dialog) {dialog.close();}
				}
			]
		});
	}
	
	function deleteNode() {
		if(!$tree) {
			$.ligerDialog.warn("树不存在！");
			return false;
		}
		
		var node = $tree.getSelected();
		if(!node) {
			$.ligerDialog.warn("请选择要删除的部门！");
			return false;
		}
		
		if(node.data.id == 0) {
			$.ligerDialog.warn("根对象不能删除！");
			return false;
		}
		
		$.ligerDialog.confirm("请确认删除选中的部门？", function(YoN) {
			if(YoN) {
				$.ajax({
					type: "POST",
					url: "${pageContext.request.contextPath}/orgMng/delData_org.shtml?", 
					data: {ORG_ID: node.data.id},
					dataType: 'json',
					success: function(result) {
						if(result.RESULT_CODE != 0) {
							$.ligerDialog.warn(result.RESULT_MESSAGE);
						} else { 
                            delNode(result.ORG_ID);
							$.ligerDialog.success(result.RESULT_MESSAGE);
						}
					}
				});
			}
		});
	}
	
	function modify() {
		if(!$tree) {
			$.ligerDialog.warn("树不存在！");
			return false;
		}
		
		var node = $tree.getSelected();
		if(!node) {
			$.ligerDialog.warn("请选择要修改的部门！");
			return false;
		}
		
		if(node.data.id == 0) {
			$.ligerDialog.warn("根对象不能修改！");
			return false;
		}
		
		$.ligerDialog.open({
			height: 400,
			width: 800,
			title: "修改部门",
			name: "MODIFY_WINDOW",
			url: "${pageContext.request.contextPath}/orgMng/toadd_org.shtml?IF_EDIT=1&ORG_ID=" + node.data.id,
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
						 updateNode(result.ORG_ID ,result.ORG_NAME);
						 $.ligerDialog.success(result.RESULT_MESSAGE); 
						
					 }
				 }
				},
				{text: "取消",
				 onclick: function (item, dialog) {dialog.close();}
				}
			]
		});
	}
 	function addNode(id, text) {
		if($tree) {
			var curNode = $tree.getSelected();
			if(curNode) {
				LigerTreeUtil.addNodes( $tree, curNode, [{id:id, text:text}]);
			}
		}
	}
	function delNode(id) {
		if($tree) { 
			var curNode = $tree.getSelected();
			
			if(curNode) {
				LigerTreeUtil.removeNodes( $tree, id);
			}
		}} 	 	
	function updateNode(id,text) {
			if($tree) { 
				var curNode = $tree.getSelected(); 
				if(curNode) { 
					LigerTreeUtil.updateNodes( $tree, curNode,text);
				}
			}} 
	function loadTree() {  
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/orgMng/orgtree.shtml",
			dataType: 'json',
			success: function(data) {    
				 /* alert(JSON.stringify(data)); */
				 $tree.setData(null);
				 $tree.setData(${ORG_TREE_JSON}); 
			}
		});
	} 
	
	
  	$(function() {
		$("#layout1").ligerLayout({leftWidth: 220, minLeftWidth: 220});
		
		$("#toptoolbar").ligerToolBar({
			items: [
			  {text: '增加', icon: 'add', click: insertNode},
			  {line:true },
			  {text: '修改', icon: 'modify', click: modify},
			  {line:true },
			  {text: '删除', icon: 'delete', click: deleteNode}
			]
		});
		
		$tree = $("#menuTree").ligerTree({
			//data : root,
			//nodeDraggable: true,
			checkbox: false,
            slide: false,
            nodeWidth: 120,
            isExpand: 2,
            needCancel: false,  //不能取消选择
		});
		loadTree();  //加载数据
		
		var height = $(".l-layout-center").height();
		$("#treeContent").css({height: height - 34});
	});
  </script>