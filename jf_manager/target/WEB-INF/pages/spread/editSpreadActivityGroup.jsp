<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
   var dataFromValidate;
// 	$(function (){
// 	    $("#form1").ligerForm();
// 	});	
	
	$(function (){ 
	    $.metadata.setType("attr", "validate");    
	    
	           dataFromValidate = $("#form1").validate({
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
				submitHandler: function (form) {   			
					//集合优惠率不能小于0
	       			var discountRate=document.getElementById("discountRate");
	       			if ($.trim(discountRate.value)<=0){
	       				$("#discountRate").ligerTip({ content: "请输入非零正数!"});
	       				discountRate.focus();
	       				return;
	       			}
			    	form.submit();
				}
	            });
	  }); 
  </script>
 </head>
<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/spread/saveSpreadActivityGroup.shtml"  >
		<input id="id" name="id" type="hidden" value="${spreadActivityGroup.id}" />
		<input id="deviceType" name="deviceType" type="hidden" value="${deviceType}" />
		<table class="gridtable">
		
			<tr>
               <td class="title"  style="text-align: center;" >推广渠道*</td>  
	               <td class="statu_value" colspan="5" height="40px">
						<select style="width:163px;" id="channel" name="channel" class="querysel" validate="{required:true}">
						<option value="">--请选择--</option>
							<c:if test="${deviceType == '1'}">
								<option value="6" <c:if test="${'6'==spreadActivityGroup.channel}">selected="selected"</c:if>>IOS落地页</option>
								<c:forEach var="channelItem" items="${channelList}">
									<option value="${channelItem.statusValue}" <c:if test="${channelItem.statusValue==spreadActivityGroup.channel}">selected="selected"</c:if>>${channelItem.statusDesc}</option>
								</c:forEach>
							</c:if>
							<c:if test="${deviceType == '2'}">
								<c:forEach var="list" items="${channelList}">
									<c:if test="${list.statusDesc == '今日头条'}">
										<option value="${list.statusValue}" <c:if test="${channelItem.statusValue==spreadActivityGroup.channel}">selected="selected"</c:if> >${list.statusDesc}</option>
									</c:if>
								</c:forEach>
							</c:if>
						</select>
				   </td> 
            </tr>
            
            <tr>
				<td class="title">活动组ID*</td>
				<td align="left" class="l-table-edit-td">
				<input id="groupId" 
					name="groupId" type="text" value="${spreadActivityGroup.groupId}"
					style="float:left;width: 200px;" validate="{required:true,maxlength:30}" />
			</tr>
			
			<tr>
				<td class="title">活动组优惠率*</td>
				<td align="left" class="l-table-edit-td">
				<input id="discountRate" 
					name="discountRate" type="text" value="${spreadActivityGroup.discountRate}"
					style="float:left;width: 200px;" validate="{required:true,number:true,maxlength:10}" />
			</tr>
		
			<tr>
				<td class="title">活动组*</td>
				<td align="left" class="l-table-edit-td">
				<input id="activityGroup" 
					name="activityGroup" type="text" value="${spreadActivityGroup.activityGroup}"
					style="float:left;width: 200px;" validate="{required:true}" />
			</tr>
			
			
			<tr>
               <td class="title"  style="text-align: center;" >状态*</td>  
	               <td class="statu_value" colspan="5" height="40px">
						<select name="isEffect"  id = "isEffect" validate="{required:true}">
						<option value="">--请选择--</option>
						<option value="1" <c:if test="${1==spreadActivityGroup.isEffect}">selected="selected"</c:if>>启用</option>
						<option value="0" <c:if test="${0==spreadActivityGroup.isEffect}">selected="selected"</c:if>>禁用</option>
						</select>
				   </td> 
            </tr>
	            
            
            <tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="submit" id="Button1" onclick="commitSave();" style="float:left;" class="l-button"  value="提交"  /></div>
	               		<div><input name="btnCancle" type="button" id="Button2" style="float:left; margin-left: 40px" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
					</div>
				</td>
			</tr>
            

		</table>
	</form>
</body>
</html>
