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
		var submitting;
		$("#confirm").bind('click',function(){
			if(submitting){
				return false;
			}
			var productId = $.trim($("#productId").val());
			var manualWeight = $("#manualWeight").val();
			if(!manualWeight){
				commUtil.alertSuccess("人工分值不能为空");
				return false;
			}
			var reg = /^(0|[1-9][0-9]*|-[1-9][0-9]*)$/;
			if(!reg.test(manualWeight)){
				commUtil.alertSuccess("人工分值只能输入正负整数");
				return false;
			}
			submitting = true;
			$.ajax({
				url : "${pageContext.request.contextPath}/product/saveProductWeight.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"productId":productId,manualWeight:manualWeight},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},300);
					}else{
						submitting = false;
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					submitting = false;
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});
});
</script>
<html>
<body>
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/product/saveProductWeight.shtml">
			<input type="hidden" id="productId" value="${productId}">
			<table class="gridtable">
	        <tr>
               <td class="title">人工分值</td>
               <td colspan="2" align="left" class="l-table-edit-td"><input type="text" id="manualWeight" name="manualWeight" value="${productWeight.manualWeight}"></td>
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
