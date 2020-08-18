<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>
<script type="text/javascript">  
     //验证
      $(function(){   
           var v = $("#form1").validate({
           submitHandler: function (form)
                {   
                	var isValidateOk=true;
                	var Expression=/^[1-9]\d*$/;
            		var busId = document.getElementById("busId");
            		var mchtName = document.getElementById("mchtName");
            		var code=document.getElementById("code");
	            		if($.trim(busId.value)=="" || $.trim(busId.value).length>10 ){
	            			$("#busId").ligerTip({ content: "该字段不能为空并且长度只能限于十位。"});
	            			isValidateOk=false;
	            		}
	            		
	            		if (!Expression.test($.trim(busId.value)) ) {
	            			$("#busId").ligerTip({ content: "该字段只能是数字。"});
	            			isValidateOk=false;
						}
	            		
	            		if($.trim(mchtName.value)==""){
	            			$("#mchtName").ligerTip({ content: "该字段不能为空。"});
	            			isValidateOk=false;
	            		}
	            		
	            		if ($.trim(code.value)=="") {
	            			$("#code").ligerTip({ content: "该字段不能为空。"});
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

<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/xiaonengcustomerservice/editsales.shtml"  >
		<input id="id" name="id" type="hidden" value="" />
		<table class="gridtable">
			<tr>
				<td class="title">企业ID*</td>
				<td align="left" class="l-table-edit-td"><input id="busId" 
					name="busId" type="text" value=""
					style="float:left;width: 200px;" validate="{required:true,nameUnique:true}" />
				</td>
			</tr>
			<tr>
			<td class="title">商家名称*</td>
				<td align="left" class="l-table-edit-td"><input id="mchtName" 
					name="mchtName" type="text" value=""
					style="float:left;width: 200px;" validate="{required:true,nameUnique:true}" />
				</td>
			</tr>
			<tr>
			<td class="title">客服代码*</td>
				<td align="left" class="l-table-edit-td"><input id="code" 
					name="code" type="text" value=""
					style="float:left;width: 200px;" validate="{required:true,nameUnique:true}" />
				</td>
			</tr>
			<tr>
				<td class="title">状态*</td>
				<td align="left" class="l-table-edit-td">
				<input type="radio" name="status" value="0" >停用
                <input type="radio" name="status" value="1" checked="checked">启用
                </td>
			</tr>
			     
            <tr>
				<td class="title">操作*</td>
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