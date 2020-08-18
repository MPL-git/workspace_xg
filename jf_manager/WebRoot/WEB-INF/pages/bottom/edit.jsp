<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("BOTTOM_CONTENT") != null ? request.getParameter("BOTTOM_CONTENT") : "";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
	
    <script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    
    <style type="text/css">
        body{ font-size:12px;padding:10px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
    <script type="text/javascript">
    function submit_fun(){
    editor1.sync();
    var BOTTOM_CONTENT=$('#BOTTOM_CONTENT').val();
		var dform=$("#from1");
                    $.ajax({
						type:'POST',
			             url:dform.attr("action"),
						 data:dform.serializeArray(),
						 dataType:"json",
						 cache: false,
						 success: function(json){
						 if(json.statusCode==200){
						 $.ligerDialog.alert(json.message, '提示', 'success', function() {
						 	parent.location.reload(); 
						 });}else{
						 $.ligerDialog.alert(json.message, '提示', 'error', function() {
						 	parent.location.reload(); 
						 });
						 }
						 
						},
						error: function(e){
						 $.ligerDialog.alert('系统异常请稍后再试', '提示', 'error',function() {
						 	parent.location.reload(); 
						 });
						}
					});
		}
		
	     var editor1;
	     KindEditor.ready(function(K) {
			 editor1 = K.create('textarea[name="BOTTOM_CONTENT"]', {
				cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
				uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
				fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
				}
				
			});
			prettyPrint();
		});
		</script>
<html>
	<body>
	<form name="from1" id="from1" method="post" action="${pageContext.request.contextPath}/service/bottom/save.shtml">    

		<input name="BOTTOM_ID" type="hidden"  value="${BOTTOM_ID}" />
		<table class="gridtable" >
		   <tr>
               <td  class="title">栏目名称：</td>
               <td  colspan="2">
              
               <input name="BOTTOM_NAME"  type="text" size="41" disabled="disabled" value="${BOTTOM_NAME}" />
               </td>
           </tr>
           
           <tr>
               <td  class="title">栏目内容：</td>
               <td  colspan="2">
               <textarea name="BOTTOM_CONTENT" id="BOTTOM_CONTENT" style="width:670px;height:400px;visibility:hidden;"><%=htmlspecialchars(htmlData)%>${BOTTOM_CONTENT}</textarea>
               </td>
           </tr>
           <tr>
           <Td colspan="2" align="right"> 
		<input name="btnSubmit" type="button" id="Button1" class="l-button l-button-submit" style="float:right;" value="保存" onclick="submit_fun();" />
		</Td></tr>
		</table> 
	</form>
	</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>