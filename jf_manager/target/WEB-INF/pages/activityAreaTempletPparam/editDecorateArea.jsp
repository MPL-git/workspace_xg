<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<script type="text/javascript">
$(function(){
		$("#save").bind('click',function(){
			$.ajax({
				url : "${pageContext.request.contextPath}/activityAreaNew/saveDecorateArea.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : $("#form").serialize(),
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});
		
});
</script>
<html>
<body>
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/activityAreaNew/saveDecorateArea.shtml">
			<input type="hidden" id="decorateInfoId" name="decorateInfoId" value="${decorateInfoId}">
			<input type="hidden" id="decorateAreaId" name="decorateAreaId" value="${decorateArea.id}">
			<table class="gridtable">
	        <tr>
               <td class="title">分区名称</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="areaName" name="areaName" value="${decorateArea.areaName}">
               </td>
	        </tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="save" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
