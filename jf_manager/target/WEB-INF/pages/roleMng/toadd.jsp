<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
    <style type="text/css">
        body{ font-size:12px;padding:10px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
     <script type="text/javascript">
       function save() { 
    	   var i=0;
	      $("#labelROLE_NAME").text("");  
	       $("#labelROLE_TYPE").text("");  
			if (($("#ROLE_NAME").val())=='')
				 {$("#labelROLE_NAME").text("请填写信息");i=1 ;}  
				if (($("#ROLE_TYPE").val())=='')
				 {$("#labelROLE_TYPE").text("请填写信息");i=1 ;} 
				if(i==1)
			 {return;}
	  $("#form1").submit();
	 } 
    </script>
<html>
	<body>
<form method="post" id="form1" name="form1"			
			action="${pageContext.request.contextPath}/roleMng/save.shtml" >
	
		
		<table  class="gridtable" >
		  <c:if test="${ROLE_TYPE!=null && ROLE_TYPE==1}" >
		    <tr>
               <td align="left" class="l-table-edit-td" colspan="3">
               <font color="red">温馨提醒：该角色为系统角色，信息不能修改</font>
               </td>              
           </tr>
           </c:if>
           <tr>
               <td   class="title">角色名称：</td>
               <td align="left" class="l-table-edit-td">
					<input id="ROLE_ID" name="ROLE_ID" type="hidden"  value="${ROLE_ID }" /> 
					<input id="ROLE_NAME"name="ROLE_NAME" type="text"  value="${ROLE_NAME }" style="float:left;width: 200px;"  />
                <label id="labelROLE_NAME" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
					</td> 
           </tr>
             <tr>
               <td   class="title">角色类型：</td>
               <td align="left" class="l-table-edit-td"> 
				<select style="float:left;width: 200px;" id="ROLE_TYPE" name="ROLE_TYPE"  >
						<option value="">请选择...</option>
						<c:forEach var="list" items="${ROLETYPElIST}">
							<option <c:if test="${ROLE_TYPE==list.STATUS_VALUE}">selected</c:if>
								value="${list.STATUS_VALUE}">
								${list.STATUS_DESC}
							</option>
						</c:forEach>
					</select>
					<label id="labelROLE_TYPE" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
					
				</td> 
           </tr>
           <tr>
               <td  class="title">备注说明：</td>
               <td align="left" class="l-table-edit-td">
                  <textarea name="ROLE_DESC"   rows="5" cols="50"   >${ROLE_DESC }</textarea>
               </td> 
           </tr>
           <tr>
               <td class="title">上级角色：</td>
               <td align="left" class="l-table-edit-td"> 
					<select style="float:left;width: 200px;" id="PARENT_ID" name="PARENT_ID"  >
						<option value="">请选择...</option>
						<c:forEach var="parentRole" items="${parentRoleList}">
							<c:if test="${parentRole.ROLE_ID != ROLE_ID }">
								<option <c:if test="${parentRole.ROLE_ID == PARENT_ID}">selected</c:if> value="${parentRole.ROLE_ID }">
									${parentRole.ROLE_NAME }
								</option>
							</c:if>
						</c:forEach>
					</select>
				</td> 
           </tr>
		<tr> 
		<td colspan="2">
		<input type="button" value="取消" class="l-button l-button-test" style="float:right;" onclick="frameElement.dialog.close();" /> 
		
		<c:if test="${ROLE_TYPE==null || ROLE_TYPE==0}" >
		<input name="btnSubmit" type="button" id="Button1" style="float:right;" class="l-button l-button-submit" value="保存" onClick="save();" />
		</c:if>
		 
		</td></tr></table>
	</form>
	</body>
</html>
