<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
    <script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
	  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
    
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
  	 action="${pageContext.request.contextPath}/platformContact/assistantdata.shtml" style="margin: 10px" >
  	 <input id="id"  name="id" value="${platformContactCustom.id}" type="hidden"/>
  		<table class="gridtable">
  			<tr>
  				<td class="title">当前协助人*</td>
  			    <td align="left" class="l-table-edit-td">				      
					<select style="width:163px;" id="assistantid" name="assistantid">
						      <c:choose>
						      <c:when test="${platformContactCustom.contactType=='1'}">
						       <option value="0">请选择...</option>
							  <c:forEach var="platformContactlist" items="${platformContactlist}">												
								<option value="${platformContactlist.id}">			                    							
								        ${platformContactlist.contactName}     										 
								 </option>								 
							  </c:forEach> 
							  </c:when>
							  <c:when test="${platformContactCustom.contactType=='2'}">
						       <option value="0">请选择...</option>
							  <c:forEach var="platformContactlist" items="${platformContactlist}">												
								<option value="${platformContactlist.id}">			                    							
								        ${platformContactlist.contactName}     										 
								 </option>								 
							  </c:forEach> 
							  </c:when>
							  <c:when test="${platformContactCustom.contactType=='3'}">
						       <option value="0">请选择...</option>
							  <c:forEach var="platformContactlist" items="${platformContactlist}">												
								<option value="${platformContactlist.id}">			                    							
								        ${platformContactlist.contactName}     										 
								 </option>								 
							  </c:forEach> 
							  </c:when>
							  <c:when test="${platformContactCustom.contactType=='4'}">
						       <option value="0">请选择...</option>
							  <c:forEach var="platformContactlist" items="${platformContactlist}">												
								<option value="${platformContactlist.id}">			                    							
								        ${platformContactlist.contactName}     										 
								 </option>								 
							  </c:forEach> 
							  </c:when>
							  <c:when test="${platformContactCustom.contactType=='5'}">
						       <option value="0">请选择...</option>
							  <c:forEach var="platformContactlist" items="${platformContactlist}">												
								<option value="${platformContactlist.id}">			                    							
								        ${platformContactlist.contactName}     										 
								 </option>								 
							  </c:forEach> 
							  </c:when>
							  <c:when test="${platformContactCustom.contactType=='6'}">
						       <option value="0">请选择...</option>
							  <c:forEach var="platformContactlist" items="${platformContactlist}">												
								<option value="${platformContactlist.id}">			                    							
								        ${platformContactlist.contactName}     										 
								 </option>								 
							  </c:forEach> 
							  </c:when>
							  <c:when test="${platformContactCustom.contactType=='7'}">
						       <option value="0">请选择...</option>
							  <c:forEach var="platformContactlist" items="${platformContactlist}">												
								<option value="${platformContactlist.id}">			                    							
								        ${platformContactlist.contactName}     										 
								 </option>								 
							  </c:forEach> 
							  </c:when>
							  <c:when test="${platformContactCustom.contactType=='8'}">
						       <option value="0">请选择...</option>
							  <c:forEach var="platformContactlist" items="${platformContactlist}">												
								<option value="${platformContactlist.id}">			                    							
								        ${platformContactlist.contactName}     										 
								 </option>								 
							  </c:forEach> 
							  </c:when>
							 </c:choose>
					</select>				   
				</td> 
  			</tr>
   			 	  			 
  			<tr>
				<td class="title">操作*</td>
				<td align="left" class="l-table-edit-td">
					<div><input name="btnSubmit"  type ="submit" id="Button1" style="float: left;" class="l-button" value="提交" /></div>
					<div><input name="btnCancle" type="button" id="Button2" style="float: left;margin-left: 40px;" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
				</td>
			</tr>
  		</table>
  	</form>
  </body>
</html>
