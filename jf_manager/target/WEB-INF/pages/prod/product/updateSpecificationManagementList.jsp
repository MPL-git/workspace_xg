<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
 <head>
    <title>批量修改排序值</title>
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
	            		var Alias = document.getElementById("alias");
	            		var SeqNo = document.getElementById("seqNo");
 	            		if($.trim(Alias.value)==""){
 	            			$("#alias").ligerTip({ content: "该字段不能为空。"});
 	            			isValidateOk=false;
 	            		}
 	            		
 	            		if($.trim(SeqNo.value)==""){
 	            			$("#seqNo").ligerTip({ content: "该字段不能为空。"});
	            			isValidateOk=false;
	            		}
	            				
 	            		if(!/^[1-9]\d*$/.test(SeqNo.value)){
 	            			$("#seqNo").ligerTip({ content: "请输入正整数。"});
 	            			isValidateOk=false;
	            		}
 	            		
	                	if(isValidateOk){
	                		form.submit();
	                	}
	                }
	            });           
	  }); 
	     
	    $(function (){
		    $("#form1").ligerForm();
		});	
	    
</script>

 </head>
  <body>
 <form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/product/updateSpecificationManagemendata.shtml"  >
		<input id="id" name="id" type="hidden" value="${seqnoId}" />
		<table class="gridtable">
			<tr>
				<td class="title">标准名称 *</td>
				<td align="left" class="l-table-edit-td"><input id="alias" 
					name="alias" type="text" value=""
					style="float:left;width: 200px;" />
			</tr>
			<tr>
				<td class="title">排序值 *</td>
	    	    <td align="left" class="l-table-edit-td"><input id="seqNo" 
					name="seqNo" type="text" value=""
					style="float:left;width: 200px;"/>
			</tr>     
            <tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button" value="提交" /></div>
	               		<div><input name="btnCancle" type="button" id="Button2" style="float:left; margin-left: 40px" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
					</div>
				</td>
			</tr>           
		</table>
	</form>
</body>

</html>
