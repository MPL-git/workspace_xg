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
    <script type="text/javascript">
 var commUtil={
     ajaxSuccessCode:200,
     ajaxSuccessMsg:"操作成功",
     ajaxErrorMsg:"系统异常请稍后再试",
	 toDoAjaxForm: function(formName){
			       var dform=$("#"+formName);
				   $.ajax({ //ajax提交
							type:'POST',
							url: dform.attr("action"),
							data:dform.serializeArray(),
							dataType:"json",
							cache: false,
							success: function(json){
							  if(json==null || json.statusCode!=commUtil.ajaxStatusCode){
							    commUtil.alertError(json.message);
							  }else{
							    commUtil.alertSuccess(commUtil.ajaxSuccessMsg);
							  
							  }
							},
							error: function(e){
							 commUtil.alertError(commUtil.ajaxErrorMsg);
							}
				    });
	     },
	     toDoAjax: function(url,jsonParms){
			       operFlag=0;
				   $.ajax({ //ajax提交
							type:'POST',
							url:'${pageContext.request.contextPath}' +url,
							data:jsonParms,
							dataType:"json",
							cache: false,
							success: function(json){
							  if(json==null || json.statusCode!=200){
							    commUtil.alertError(json.message);
							  }else{
							    commUtil.alertSuccess(commUtil.ajaxSuccessMsg);
							    operFlag=1;
							  }
							},
							error: function(e){
							 commUtil.alertError(commUtil.ajaxErrorMsg);
							}
				    });
	     },
	     openUrlWin:function(winTile,url,params)  {  
		            $.ligerDialog.open({
		                height:580,
		                width: 800,
		                title : winTile,
		                url: "${pageContext.request.contextPath}"+url, 
		                showMax: false,
		                showToggle: true,
		                showMin: false,
		                isResize: true,
		                slide: false,
		                data:params ,
		                //自定义参数
		                myDataName: $("#txtValue").val()
		            });
           },
           alert:function(content){
             $.ligerDialog.alert(content);
           },
           alertSuccess:function(content){
            $.ligerDialog.success(content);
           },
           alertError:function(content){
            $.ligerDialog.warn(content);
           }, 
		   formToJson:function (form){
			 	var paramsArray = $("#"+form).serializeArray();
			 	return paramsArray;
		   }
    
    
    }
    
    	var treeObject = null;
    	var orgTreeData = ${ORG_TREE_JSON};
    	///var productTypeTree = ${ORG_TREE_JSON}; , children: productTypeTree
    	var treeRoot =[{id: 1, text: '根节点', isExpand: true}];
   $(function() {
    		$("#layout1").ligerLayout({leftWidth: 270});
    		$("#menuTree").ligerTree({
    			data : orgTreeData,
                checkbox: false,
                slide: false,
                nodeWidth: 120,
                attribute: ['nodename', 'url'],
                btnClickToToggleOnly: false,
                isExpand: 2,
                onSelect: function (node) {
                	$("#frm").attr("src", "${pageContext.request.contextPath}/orgMng/list_index.shtml?ORG_ID=" + node.data.id);
                }
    		});
    		
    		var height = $(".l-layout-center").height();
    		$("#treeContent").css({height: height - 34});
    		treeObject = liger.get("menuTree");
    		
    	});
    	
    </script>
  </head>
  
  <body style="padding:10px; margin:0;">
  	<div id="layout1">
  	  <div  position="left" title="【组织部门树】">
  	    <div id="toptoolbar"></div>
  	   	<div id="treeContent" style="height: 100%; overflow: scroll;">
  	    <ul id="menuTree" style="margin-top:3px;"></ul>
  	    </div>
  	  </div>
  	  <div position="center">
  	  	<iframe id="frm" frameborder="0" name="home" id="home" src="about:blank" style="width: 100%; height: 100%;"></iframe>
  	  </div>
  	</div> 
  </body>
</html>
