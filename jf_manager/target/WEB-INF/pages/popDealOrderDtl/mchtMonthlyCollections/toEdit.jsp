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
	$("#update").bind('click',function(){
		var id = $.trim($("#id").val());
		var discount = $.trim($("#discount").val());
		var discountDesc = $.trim($("#discountDesc").val());
		$.ajax({
			url : "${pageContext.request.contextPath}/popDealOrderDtl/mchtMonthlyCollections/update.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"discount":discount,"discountDesc":discountDesc},
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
	<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/popDealOrderDtl/mchtMonthlyCollections/update.shtml">
		<input type="hidden" name="id" id="id" value="${mchtMonthlyCollections.id}">
		<table class="gridtable">
			<tr>
				<td>折让金额：</td>
				<td>
					<input type="text" id="discount" name="discount" value="${mchtMonthlyCollections.discount}"/>元 
				</td>
			</tr>
			
			<tr>
				<td>折让说明：</td>
				<td>
					<textarea rows="5" cols="40" id="discountDesc" name="discountDesc">${mchtMonthlyCollections.discountDesc}</textarea>
				</td>
			</tr>
			
			<tr>
				<td>操作</td>
				<td>
					<div id="btnDiv">
						<input id="update" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
