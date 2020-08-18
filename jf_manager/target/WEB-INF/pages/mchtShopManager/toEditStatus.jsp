<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
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
	
		$("#confirm").bind('click',function(){
			var status = $("#status").val();
			if(!status){
				commUtil.alertSuccess("请选择合作状态");
				return false;
			}
			var param = $("#form").serialize();
			$.ajax({
				url : "${pageContext.request.contextPath}/mchtShopManager/editStatus.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : param,
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
});


</script>
<html>
<body>
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/mchtShopManager/editStatus.shtml">
	<input type="hidden" id="mchtSupplierUserId" name="mchtSupplierUserId" value="${mchtSupplierUser.id}">
	<table class="gridtable">
       <tr>
             <td class="title" style="width: 25%;">合作状态</td>
             <td colspan="2" align="left" class="l-table-edit-td">
             		<select id="status" name="status">
             			<option value="">请选择</option>
             			<option value="0" <c:if test="${mchtSupplierUser.status eq 0}">selected="selected"</c:if>>未合作</option>
             			<option value="1" <c:if test="${mchtSupplierUser.status eq 1}">selected="selected"</c:if>>合作中</option>
             			<option value="2" <c:if test="${mchtSupplierUser.status eq 2}">selected="selected"</c:if>>终止合作</option>
             			<option value="3" <c:if test="${mchtSupplierUser.status eq 3}">selected="selected"</c:if>>取消</option>
             		</select>
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
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>   
</body>
</html>
