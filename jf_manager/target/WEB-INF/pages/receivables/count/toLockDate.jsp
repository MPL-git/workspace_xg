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
		$("#lockDateBtn").bind('click',function(){
			var lockDate = $.trim($("#lockDate").val());
			if(lockDate == ""){
				commUtil.alertError("收款登记日期不能为空");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/receivables/count/lockDate.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"lockDate":lockDate},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("确认成功");
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
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/receivables/count/lockDate.shtml">
			<table class="gridtable">
           	<tr>
               <td class="title">状态更改为</td>
               <td align="left" class="l-table-edit-td">已锁住</td>
			</tr>
	        <tr>
               <td class="title">收款登记日期</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="lockDate" name="lockDate" value="${lockDate}" readonly="readonly"/>（锁住后，其他收款不能用此日期）
				</td>
	        </tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="lockDateBtn" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
