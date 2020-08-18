<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
   <style type="text/css">
        body{ font-size:12px;padding:10px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
    <script type="text/javascript">
        var message = '${message}';
        var statusCode = '${statusCode}';
        var dialog = frameElement.dialog;
        $(function () {
        if(statusCode=='200'){
         document.onkeydown=function(event){
             if(event.keyCode == 13)                   
             { 
               parent.$("#searchbtn").click();
               dialog.close();
               event.returnValue = false;               
             }
         };
        	$.ligerDialog.alert(message, '提示', 'success', function() {
				//parent.location.reload();
				parent.$("#searchbtn").click();
				dialog.close(); 
			});
        }else if(statusCode=='300'){
        	$.ligerDialog.alert(message, '提示', 'error',function() {
        		dialog.close();
			});
        }
        }); 
       
    </script>
	<html>
	<body>
	</body>
	</html>
