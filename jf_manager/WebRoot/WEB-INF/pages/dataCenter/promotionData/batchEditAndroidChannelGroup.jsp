<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script type="text/javascript">
</script>
 </head>
<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/promotionData/androidChannelGroup/batchSave.shtml"  >
		<input id="androidChannelGroupIds" name="androidChannelGroupIds" type="hidden" value="${androidChannelGroupIds}" />
		<table class="gridtable">
			
			<tr>
               <td class="title"  style="text-align: center;" >启用状态*</td>  
	               <td class="statu_value" colspan="5" height="40px">
						<select required name="status"  id ="status" >
						<option value="">--请选择--</option>
						<option value="1">启用</option>
						<option value="0">禁用</option>
						</select>
				   </td> 
            </tr>
 
            <tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button"  value="提交"  /></div>
	               		<div><input name="btnCancle" type="button" id="Button2" style="float:left; margin-left: 40px" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
					</div>
				</td>
			</tr>
            

		</table>
	</form>
</body>
</html>
