<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
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
var viewerPictures;
$(function(){
		$("#save").bind('click',function(){
			var pageType = $("#pageType").val();
			var pageName = $("#pageName").val();
			if(!pageType){
				commUtil.alertError("请选择类型");
				return false;
			}
			if(!pageName){
				commUtil.alertError("请输入名称");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/saveCustomPage.shtml",
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
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/customPage/saveCustomPage.shtml">
			<input type="hidden" id="id" name="id" value="${customPage.id}">
			<table class="gridtable">
           	<tr>
               <td class="title">类型<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
	               <select id="pageType" name="pageType">
               			<option value="">请选择</option>
						<c:forEach var="pageType" items="${pageTypeList}">
							<option value="${pageType.statusValue}" <c:if test="${customPage.pageType eq pageType.statusValue}">selected="selected"</c:if>>${pageType.statusDesc}</option>
						</c:forEach>
               	   </select>
               </td>
			</tr> 
	        <tr>
               <td class="title">名称<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="pageName" name="pageName" value="${customPage.pageName}">
               </td>
	        </tr>
	        
	        <tr>
               <td class="title">备注</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<textarea rows="6" cols="50" id="remarks" name="remarks">${customPage.remarks}</textarea>
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
