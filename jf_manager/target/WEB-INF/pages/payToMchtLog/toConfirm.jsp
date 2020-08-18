<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript">
$(function ()  {
	$("#updateConfirmStatus").bind('click',function(){
		var ids = $.trim($("#ids").val());
		$.ajax({
			url : "${pageContext.request.contextPath}/payToMchtLog/updateConfirmStatus.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"ids":ids},
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
	<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/payToMchtLog/updateConfirmStatus.shtml">
		<input type="hidden" name="ids" id="ids" value="${ids}">
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">结算总额：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" style="width:400px;" value="${totalSettleAmount}"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">商家保证金抵缴：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" style="width:400px;" value="${totalDepositAmount}"/>
	    		</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">实付总金额：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" style="width:100px;" value="${totalPayAmount}"/>&nbsp;元&nbsp;
				</td>
			</tr>

			<tr>
				<td colspan="6" class="title">共${totalCount}笔</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="updateConfirmStatus" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
