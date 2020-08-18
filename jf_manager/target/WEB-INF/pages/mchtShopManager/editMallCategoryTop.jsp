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
	var submitting;
	$("#confirm").bind('click',function(){
		if(submitting){
			return false;
		}
		var id = $("#mallCategoryTopId").val();
		var productTypeId = $("#productTypeId").val();
		var remarks = $("#remarks").val();
		var upDate = $("#upDate").val();
		var downDate = $("#downDate").val();
		if(!upDate){
			commUtil.alertError("上架时间不能为空");
			return false;
		}
		if(!downDate){
			commUtil.alertError("下架时间不能为空");
			return false;
		}
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtShopManager/mallCategoryTop/save.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"productTypeId":productTypeId,"remarks":remarks,"upDate":upDate,"downDate":downDate},
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
				submitting = false;
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
	<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/mchtShopManager/mallCategoryTop/save.shtml">
		<input type="hidden" id="mallCategoryTopId" value="${mallCategoryTopId}">
		<table class="gridtable">
			<tr id="mchtCodeTr">
				<td colspan="1" class="title">类型：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<select id="productTypeId" name="productTypeId" class="querysel">
						<option value="1" <c:if test="${mallCategoryTop.productTypeId eq 1}">selected="selected"</c:if>>商城【首页】</option>
						<c:forEach var="list" items="${productTypes}">
							<option value="${list.id}" <c:if test="${mallCategoryTop.productTypeId eq list.id}">selected="selected"</c:if>>商城【${list.name}馆】</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">备注：</td>
				<td colspan="5" align="left" class="l-table-edit-td" >
					<input id="remarks" name="remarks" value="${mallCategoryTop.remarks}" style="width: 350px;">
	    		</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">上架时间：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="upDate" name="upDate" value="<fmt:formatDate value='${mallCategoryTop.upDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
					<script type="text/javascript">
						$(function() {
							$("#upDate").ligerDateEditor({
								format: "yyyy-MM-dd hh:mm:ss",
								showTime : true,
								labelWidth : 200,
								width:200,
								labelAlign : 'left'
							});
						});
					</script>
	    		</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">下架时间：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="downDate" name="downDate" value="<fmt:formatDate value='${mallCategoryTop.downDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
					<script type="text/javascript">
						$(function() {
							$("#downDate").ligerDateEditor({
								format: "yyyy-MM-dd hh:mm:ss",
								showTime : true,
								labelWidth : 200,
								width:200,
								labelAlign : 'left'
							});
						});
					</script>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input type="button" id="confirm" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
