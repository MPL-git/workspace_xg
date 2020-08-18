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
		var productTypeId = $.trim($("#productTypeId").val());
		var isSmallware = $("#isSmallware").val();
		if(isSmallware==null || isSmallware==""){
			commUtil.alertError("请选择商品类型");
			return;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/service/prod/product_type/editSmallware.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":productTypeId,isSmallware:isSmallware},
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
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/service/prod/product_type/editReturn7days.shtml">
			<input type="hidden" id="productTypeId" value="${productType.id}">
			<table class="gridtable">
           	<tr>
               <td class="title">商品类型</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<select id="isSmallware" name="isSmallware" style="width: 135px;" >
								<option value="0" <c:if test="${productType.isSmallware eq 0}">selected="selected"</c:if>>有品牌</option>
								<option value="2" <c:if test="${productType.isSmallware eq 2}">selected="selected"</c:if>>无品牌</option>
								<option value="1" <c:if test="${productType.isSmallware eq 1}">selected="selected"</c:if>>小商品</option>
					</select>
               </td>
			</tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="确认"/>
						<input type="button" value="取消" style="margin-left: 10px;" class="l-button l-button-submit" onclick="frameElement.dialog.close()"/>
					</div>
				</td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
