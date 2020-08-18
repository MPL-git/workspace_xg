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
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript">
	var staffIdtext;
	$(function(){
		 function getGridOptions(checkbox){
		     var options = {
		         columns: [
		         { display: '帐号', name: 'staffCode', minWidth: 120, width: 100 },
		         { display: '姓名', name: 'staffName', minWidth: 120, width: 100 }
		         ], 
		         switchPageSizeApplyComboBox: false,
		         url: '${pageContext.request.contextPath}/service/platformContact/staffdata.shtml',
		         pageSize: 10, 
		         checkbox: checkbox
		     };
		     return options;
		 }
		 
		 staffIdtext = $("#STAFFINFO").ligerComboBox({
		     	width: 162,
		         slide: false,
		         selectBoxWidth: 300,
		         selectBoxHeight: 240,
		         valueField: 'staffId',
		         textField: 'staffName',
		         valueFieldID:'staffId',
		         grid: getGridOptions(false),
		         value: '${staffId}',
		         condition:{ fields: [{ name: 'staffName', label: '姓名',width:90,type:'text' }] }
		     });	

 		window.onload=function(){ 
		staffIdtext.setValue("${platformContactCustom.staffId}");
		staffIdtext.setText("${platformContactCustom.staffName}");
		}; 
		
	$.metadata.setType("attr", "validate");		
	var v = $("#form1").validate({
    	
		errorPlacement: function (lable, element)
		{   
        	var elementType=$(element).attr("type");

        	if($(element).hasClass("l-text-field")){
        		$(element).parent().ligerTip({
					content : lable.html(),width: 100
				});
        	}else{
        		$(element).ligerTip({
					content : lable.html(),width: 100
				});
        	}
		},
		
		success: function (lable,element)
		{
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler: function (form)
		{   
			//系统用户
			var staffidss = document.getElementById("staffId").value;
			var staffvalue = staffIdtext.getValue();
			if($.trim(staffvalue)=='' && $.trim(staffidss)==''){
				$("#staffId").val(staffvalue);
			}else if($.trim(staffvalue)=='' &&  $.trim(staffidss)!=''){
			}else{
				$("#staffId").val(staffvalue);
			}
			form.submit();
		}
	});
});
  </script>
  </head>
  
  <body>
  
  	<form method="post" id="form1" name="form1" 
  	 action="${pageContext.request.contextPath}/service/platformContact/editSave.shtml" style="margin: 10px" >
  	 <input id="id"  name="id" value="${platformContactCustom.id}" type="hidden"/>
  		<table class="gridtable">
  			<tr>
  				<td class="title">系统用户</td>
  				<td align="left" class="l-table-edit-td">
  				<div>
  					<div  style="float: left; ">
  						<input id="STAFFINFO" name="STAFFINFO" type="text" />
  					</div>
				</div>
				<input id="staffId" name="staffId" value="${platformContactCustom.staffId}" type="hidden"/>
				</td> 
  			</tr>
  			<tr>
  				<td class="title">对外姓名<span class="required">*</span></td>
  				<td align="left" class="l-table-edit-td"><div><input id="contactName" name="contactName" type="text" value="${platformContactCustom.contactName}"
					style="float:left;width: 160px;" validate="{required:true}" /></div>
				</td>
  			</tr>
  			<tr>
  				<td class="title">对接类型<span class="required">*</span></td>
  				<td align="left" class="l-table-edit-td">
  					<div>
						<select style="width:163px;" id="contactType" name="contactType" class="querysel">
							<c:forEach var="list" items="${platformType}">
								<option value="${list.statusValue}" <c:if test="${list.statusValue==platformContactCustom.contactType}">selected="selected"</c:if>>${list.statusDesc}</option>
							</c:forEach>
						</select>
					</div>
				</td>
  			</tr>
  			<tr>
  				<td class="title">手机号</td>
  				<td align="left" class="l-table-edit-td"><div><input id="mobile" name="mobile" type="text" value="${platformContactCustom.mobile}"
					style="float:left;width: 160px;"  /></div>
				</td>
  			</tr>
  			<tr>
  				<td class="title">对接人状态</td>
  				<td align="left" class="l-table-edit-td">
  					<div>
						<select style="width:163px;" id="status" name="status" class="querysel">
							<c:forEach var="list" items="${platformStatus}">
								<option value="${list.statusValue}" <c:if test="${list.statusValue==platformContactCustom.status}">selected="selected"</c:if>>${list.statusDesc}</option>
							</c:forEach>
						</select>
					</div>
				</td>
  			</tr>
  			<tr>
  				<td class="title">座机号</td>
  				<td align="left" class="l-table-edit-td"><div><input id="tel" name="tel" type="text" value="${platformContactCustom.tel}"
					style="float:left;width: 160px;"  /></div>
				</td>
  			</tr>
  			
  			<tr>
  				<td class="title">QQ号</td>
  				<td align="left" class="l-table-edit-td"><div><input id="qq" name="qq" type="text" value="${platformContactCustom.qq}"
					style="float:left;width: 160px;"  /></div>
				</td>
  			</tr>
  			<tr>
  				<td class="title">邮箱</td>
  				<td align="left" class="l-table-edit-td"><div><input id="email" name="email" type="text" value="${platformContactCustom.email}"
					style="float:left;width: 160px;"  validate = "{email:true}"/></div>
				</td>
  			</tr>
  			
  			<tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div><input name="btnSubmit"  type ="submit" id="Button1" style="float: left;" class="l-button" value="提交" /></div>
					<div><input name="btnCancle" type="button" id="Button2" style="float: left;margin-left: 40px;" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
				</td>
			</tr>
  		</table>
  	</form>
  </body>
</html>
