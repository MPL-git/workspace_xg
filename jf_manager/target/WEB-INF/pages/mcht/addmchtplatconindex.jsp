<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
    <title>My JSP 'addmchtplatconindex.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


	<style type="text/css">
		.title{height: 30px;width: 60px;}
		.l-table-edit-td{height:30px; width: 120px;}
	</style>
	
	<script type="text/javascript">
		var contactName;
		$(function(){
			 function getGridOptions(checkbox){
			     var options = {
			         columns: [
			         { display: '职务', name: 'contactTypeDesc', minWidth: 100, width: 100 },
			         { display: '姓名', name: 'contactName', minWidth: 100, width: 100 },
			         { display: '状态', name: 'statusDesc', minWidth: 80, width: 80 },
			         { display: '手机号', name: 'mobile', minWidth: 100, width: 100 }
			         ], 
			         switchPageSizeApplyComboBox: false,
			         url: '${pageContext.request.contextPath}/mcht/getplatformcontact.shtml?mchtId=${MCHTID}',
			         pageSize: 10, 
			         checkbox: checkbox
			     };
			     return options;
			 }
			 
			 contactName = $("#contactName").ligerComboBox({
			     	 width: 150,
			         slide: false,
			         selectBoxWidth: 450,
			         selectBoxHeight: 300,
			         valueField: 'id',
			         textField: 'contactName',
			         valueFieldID:'id',
			         grid: getGridOptions(false),
			         condition:{ fields: [{ name: 'contactName', label: '姓名',width:90,type:'text' },
			                              { name: 'contactType', label: '职务',newline: false, type: "select", width:90, comboboxName: "contactType",options:{
				                                valueField : 'statusValue',
				                                textField: 'statusDesc',
				                                url: '${pageContext.request.contextPath}/mcht/getcontacttype.shtml' 
			                              }
			       						} ]}
			     });	
				
		});
		
		$.validator.addMethod("contactName", function(value, element) {   
			var platformContactId = contactName.getValue();
	    	if($.trim(platformContactId)==''){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    }, "请选择人员");
		
		function sava(){
			var platformid = contactName.getValue();
			$("#platformContactId").val(platformid);
		}
		
		$(function ()  { 
			 $.metadata.setType("attr", "validate");
	          $("#form1").validate({
						errorPlacement : function(lable, element) {
							if ($(element).attr('name') == 'contactName') {
								$("#contactName").ligerTip({
									content : lable.html()
								});
							} else{
								lable.appendTo(element.parent());
							}
						},
						success : function(lable) {
							lable.ligerHideTip();
							lable.remove();
						},
						submitHandler : function(form) {
							parent.location.reload();
							form.submit();
						}
				});

		});   
		
		
	</script>
  </head>
  
  <body>
  
  	<form method="post" id="form1" name="form1"  action="${pageContext.request.contextPath}/mcht/saveplatformcontact.shtml" style="margin: 10px">
  		<input type="hidden" name="mchtId" value="${MCHTID}"/>
  		<input type="hidden" name="platformContactId" value="" class="platformContactId" id="platformContactId"/>
  		<table class="gridtable">
  			<tr>
  				<td class ="title" >人员</td>
  				<td align="left" class="l-table-edit-td"><input id="contactName"  class="contactName" type="text" name="contactName"  value=""  validate="{required:true}"/>
  				</td>
  			</tr>
  			
  			<tr>
  				<td class="title">操作</td>
  				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;margin-right:30px;" class="l-button l-button-submit" value="提交" onclick="sava();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
  				</td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>
