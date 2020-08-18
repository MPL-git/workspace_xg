<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
 <head>
    <link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
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

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}
</style>
<script type="text/javascript">

	     //验证
	      $(function(){   
	           var v = $("#form1").validate({
	           submitHandler: function (form)
	                {   
	                	var isValidateOk=true;
	            		var platformExpressNo = document.getElementById("platformExpressNo");
 	            		if($.trim(platformExpressNo.value)==""){
 	            			$("#platformExpressNo").ligerTip({ content: "该字段不能为空。"});
 	            			isValidateOk=false;
 	            		}
 	            
	                	if(isValidateOk){
	                		form.submit();
	                	}
	                }
	            });           
	  }); 
	     
	    
</script>

 </head>
  <body>
 <form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/mchtContract/updatemchtexpres.shtml"  >
		<input id="id" name="id" type="hidden" value="${id}" />
		<input id="contractType" name="contractType" type="hidden" value="${contractType}" />
		<table class="gridtable">
			<tr>	
				<td class="title" >快递*</td>
				<td align="left" class="l-table-edit-td" >
					<select id="expressnameid" name="expressnameid" style="width: 200px;">
						<option value="">请选择...</option>
						 <c:forEach var="express" items="${expressList}">
						    <option value="${express.id}" <c:if test="${contact.platformExpressId eq express.id}">selected</c:if>>
									${express.name}
							</option>
						</c:forEach>
					</select>
				 </td>			
			</tr>
			
			<tr>
				<td class="title">快递单号*</td>
	    	    <td align="left" class="l-table-edit-td"><input id="platformExpressNo" 
					name="platformExpressNo" type="text" maxlength="64" value="${contact.platformExpressNo}"
					style="float:left;width: 200px;"/>
				</td>
			</tr>     
            <tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button" value="保存" /></div>
	               		<div><input name="btnCancle" type="button" id="Button2" style="float:left; margin-left: 40px" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
					</div>
				</td>
			</tr>           
		</table>
	</form>
</body>

</html>
