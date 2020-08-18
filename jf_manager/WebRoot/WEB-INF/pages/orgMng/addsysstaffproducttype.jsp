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
   var productTypeIds=$("#productTypeIds").val().replace('[','').replace(']','').replace(/\s+/g,"");
   var checkVar = document.getElementsByName("productTypeId");
	if(productTypeIds!=''){
		var strs= new Array(); 
		strs=productTypeIds.split(","); 
		for (i=0;i<strs.length;i++) {		
			for(j=0;j<checkVar.length;j++){
				if (strs[i]==checkVar[j].value){
					checkVar[j].checked =true; 
				}
			}		
		} 
	}
});

</script>
<body>
<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/orgMng/addsysstaffProductTypes.shtml" style="margin: 10px">
		<input type="hidden" id="staffId" name="staffId" value="${staffid}"/>
		<input type="hidden" id="productTypeIds" name="productTypeIds" value="${producttypeId}"/>
		<table class="gridtable">																		 
			<tr>
			   <td align="left" class="l-table-edit-td">
			    <c:forEach var="producttypeList" items="${producttypeList}">
				<input name="productTypeId" type="checkbox" value="${producttypeList.id}" style="width: 30px;"/>${producttypeList.name}
		        </c:forEach>
			  </td>
			</tr>
			<tr>
	          <td align="center" class="" >
	           <input type="submit" id="Button1" style="margin-right: 30px;" class="l-button l-button-submit" value="提交"/> 
	           <input type="button" value="取消" class="l-button l-button-submit" onclick="frameElement.dialog.close()"/>
	          </td>
	       </tr>
		</table>
</form>
</body>
</html>
