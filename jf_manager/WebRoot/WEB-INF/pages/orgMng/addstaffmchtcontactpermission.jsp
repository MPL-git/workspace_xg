<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass{
	margin-right: 20px;
}

.l-table-edit-td {
	padding: 4px;
}

.table-title{
font-size: 17px;font-weight: 600;
}
.center-align{
	text-align: center;
}
table.gridtable td.title{
	text-align: left;
}
table.gridtable .title{
border-style: none;
}
</style>
<html>
<script type="text/javascript">
$(function(){
   var mchtContactTypes=$("#mchtContactTypes").val().replace('[','').replace(']','').replace(/\s+/g,"");
	if(mchtContactTypes!=''){
		var strs= new Array(); 
		strs=mchtContactTypes.split(","); 
		for (i=0;i<strs.length;i++) {
			var j=strs[i]-1;
			$("input[name='mchtContactType']")[j].checked =true; 		
		} 
	}
});

</script>
<body>
<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/orgMng/addStaffmchtcontactpermission.shtml" style="margin: 10px">
		<input type="hidden" id="staffId" name="staffId" value="${staffid}"/>
		<input type="hidden" id="mchtContactTypes" name="mchtContactTypes" value="${mchtContactTypeList}"/>
		<table class="gridtable">																		 
			<tr>
			   <td align="left" class="l-table-edit-td">
			    <input name="mchtContactType" type="checkbox" value="1" style="width: 30px;"/>电商总负责人
				<input name="mchtContactType" type="checkbox" value="2" style="width: 30px;"/>运营对接人
				<input name="mchtContactType" type="checkbox" value="3" style="width: 30px;"/>订单对接人
			  </td>
			</tr>
			<tr>
			   <td align="left" class="l-table-edit-td">
				<input name="mchtContactType" type="checkbox" value="4" style="width: 30px;"/>售后对接人
				<input name="mchtContactType" type="checkbox" value="5" style="width: 30px;"/>财务对接人
				<input name="mchtContactType" type="checkbox" value="6" style="width: 30px;"/>客服对接人
			  </td>
			</tr>
			<tr>
	          <td align="center" class="l-table-edit-td">
	           <input type="submit" id="Button1" style="margin-right: 30px;" class="l-button l-button-submit" value="提交"/> 
	           <input type="button" value="取消" class="l-button l-button-submit" onclick="frameElement.dialog.close()"/>
	          </td>
	       </tr>
		</table>
</form>
</body>
</html>
