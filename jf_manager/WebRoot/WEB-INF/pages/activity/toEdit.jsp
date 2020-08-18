<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
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
var viewerPictures;
$(function(){
		$("#confirm").bind('click',function(){
			var activityAreaId = $("#activityAreaId").val();
			var isDefault = $("input[name='isDefault']:checked").val();
			var templetSuffix = $("#templetSuffix").val();
			if(isDefault == 0){
				if(!templetSuffix){
					commUtil.alertError("自定义页不能为空");
					return false;
				}
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/activityArea/edit.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":activityAreaId,"isDefault":isDefault,"templetSuffix":templetSuffix},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("修改成功");
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
		
		$("input[name='isDefault']").bind('click',function(){
			var isDefault = $(this).val();
			if(isDefault==0){
				$("#define").show();
			}else{
				$("#define").hide();
			}
		});
});
</script>
<html>
<body>
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/activityArea/edit.shtml">
			<input type="hidden" id="activityAreaId" value="${activityAreaId}">
			<table class="gridtable">
           	<tr>
               <td class="title">选择类型</td>
               <td colspan="2" align="left" class="l-table-edit-td">
	               	<input type="radio" name="isDefault" value="1" <c:if test="${isDefault == 1}">checked="checked"</c:if>/>默认模板
	               	<input type="radio" name="isDefault" value="0" <c:if test="${isDefault == 0}">checked="checked"</c:if>/>自定义页面
               </td>
			</tr>
	        <tr id="define" <c:if test="${isDefault == 1}">style="display: none;"</c:if>>
               <td class="title">自定义页</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="templetSuffix" name="templetSuffix" <c:if test="${isDefault == 0}">value="${templetSuffix}"</c:if> style="width: 400px;height: 25px;">
               </td>
	        </tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
