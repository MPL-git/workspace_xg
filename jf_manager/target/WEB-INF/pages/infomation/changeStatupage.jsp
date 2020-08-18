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
		
	</script>
  </head>
  
  <body>
  	  <form method="post" id="form1" name="form1" 
  	 	action="${pageContext.request.contextPath}/infomation/changeStatu.shtml" style="margin: 10px" >
  	 	<input type="hidden" name="id" value="${inforIds}"/>
  		<table class="gridtable">
             <tr >
               <td  class="title" width="20%">状态：</td>
               <td align="left" class="l-table-edit-td" height="60px">
               		<select id="status" name="status"
						class="querysel" style="width: 160px;">
						<c:forEach var="list" items="${statu}">
							<option value="${list.statusValue}">${list.statusDesc}
							</option>
						</c:forEach>
					</select>
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
