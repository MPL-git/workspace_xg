<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String htmlData = request.getParameter("NOTICE_CONTENT") != null ? request.getParameter("NOTICE_CONTENT") : "";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
   <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


	<script type="text/javascript">
		var productTypeCombo;
	    $(function(){
	    	productTypeCombo= $("#catalogName").ligerComboBox({
	              selectBoxWidth: 200,
	              selectBoxHeight: 200,  
	              valueField: 'id',
	              textField: 'frontName',
	              valueFieldID:'catalogId',
	              treeLeafOnly:false,
	              valueField : 'id',
	              width: 160,
	              tree: { url: '${pageContext.request.contextPath}/infomation/getCatalogTree.shtml?&seeInfoInfo=${seeInfoInfo}', checkbox: false, ajaxType: 'get', idFieldName: 'id',textFieldName:'frontName',parentIDFieldName:'parentId',isExpand:2}
	          });
	    });
	</script>
  </head>
  
  <body>
  	  <form method="post" id="form1" name="form1" 
  	 	action="${pageContext.request.contextPath}/infomation/changecatalog.shtml" style="margin: 10px" >
  	 	<input type="hidden" name="id" value="${inforIds}"/>
  		<table class="gridtable">
            <tr >
               <td  class="title" width="20%">转移栏目：</td>
               <td align="left" class="l-table-edit-td" height="60px">
               		<input id="catalogName" type="text" style="width:450px;"/>
               </td>
           </tr>
           
  			<tr>            
	           <td colspan="2"> 
	            <input type="button" value="取消" style="float:right;" class="l-button l-button-submit" onclick="frameElement.dialog.close()"/>
				<input name="btnSubmit" type="submit" id="Button1" style="float:right; margin-right: 50px;" class="l-button l-button-submit" value="保存"  /> 
	       </td></tr>
  		</table>
  		</form>
  </body>
</html>
