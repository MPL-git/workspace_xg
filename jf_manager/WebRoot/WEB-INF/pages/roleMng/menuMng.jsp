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
 var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
 var alert = function (content)
 {
     $.ligerDialog.alert(content);
 };
 function closeDialog()
 {
     dialog.close();//关闭dialog 
 }
 
 var commTree ={
   treeObj:null,
   initTree:function(){
            $("#menuTree").ligerTree({
   			   data : ${TREE_DATA_JSON},
               checkbox: false,
               slide: false,
               checkbox:true,
               attribute: ['nodename', 'url'],
               btnClickToToggleOnly: false ,
               isExpand:1
            });
            commTree.treeObj = $("#menuTree").ligerGetTreeManager();
   
   
   },
   getSelected:function() {
            var notes = commTree.treeObj.getChecked(); 
            var nodeIds = "";
            for (var i = 0; i < notes.length; i++)
            {   var nodeId=notes[i].data.id;
                if(i==0){nodeIds +=nodeId;
                }else{
                 nodeIds +=","+  nodeId;}
            }
            $("#CHECK_MENUS").val(nodeIds);
        }
 
 }
 
$(function() {
   commTree.initTree(); 
 	
}); 
function validateForm(){
  commTree.getSelected();
   var dform=$("#postForm");
	$("#btnDiv").hide();
   //提交
   $.ajax({
		type:'POST',
		url:dform.attr("action"),
		data:dform.serializeArray(),
		dataType:"json",
		cache: false,
		success: function(json){
		  if(json==null || json.statusCode!=200){
		    alert(json.message);
		  }else{

			   $.ligerDialog.success('操作成功', '提示',  function() {
		   frameElement.dialog.close();
								});
			  $("#btnDiv").show();
		}
		  },
		error: function(e){
		   alert("系统异常请稍后再试");
		}
	}) ; 
}   
</script>
	<form id="postForm" method="post" action="${pageContext.request.contextPath}/roleMng/saveMenus.shtml" >
	 
		 <h2 style="margin-left:50px;">&nbsp; <span> 当前选中角色：${CHECK_ROLE_NAME }    &nbsp;&nbsp;&nbsp;
		 (<font color="red"><b>&nbsp;√ </b> </font>表示权限已分配)</span>
		  &nbsp;&nbsp;&nbsp;<font color="red"><c:if test="${SYS_FLAG==1}">${SYS_FLAG_MSG}</c:if> </font>
		 </h2>
		  <ul id="menuTree" style="margin-top:3px;margin-left:100px;"></ul>
		  <input type="hidden" name="ROLE_ID" value="${CHECK_ROLE_ID }">
		  <input type="hidden" name="CHECK_MENUS" id="CHECK_MENUS" value="">
		 </br>
		<div id="btnDiv"  style="margin-left:100px;">
		    <c:if test="${SYS_FLAG!=1}" >
			<input type="button" value="保存" id="subBtn" class="l-button l-button-submit" onclick="validateForm();" /> 
		    </c:if>
			<input type="button" value="取消" class="l-button l-button-test" onclick="closeDialog()"/>
		</div> 
	</form>
</body>
</html>
