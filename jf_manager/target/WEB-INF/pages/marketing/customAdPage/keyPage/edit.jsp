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
			var id = $.trim($("#id").val());
			var pageType = $("#pageType").val();
			var pageName = $.trim($("#pageName").val());
			var sourceProductTypeId = $.trim($("#sourceProductTypeId").val());
			<c:if test="${pageType == 12 || pageType == 13 || pageType == 16 || pageType == 25}">
			if(!sourceProductTypeId){
				commUtil.alertError("所属类目不能为空");
				return false;
			}
			</c:if>

			if(!pageName){
				commUtil.alertError("主题名称不能为空");
				return false;
			}
			var autoUpDate = $("#autoUpDate").val();
			if(!autoUpDate){
				commUtil.alertError("上架时间不能为空");
				return false;
			}
			var autoDownDate = $("#autoDownDate").val();
			if(!autoDownDate){
				commUtil.alertError("下架时间不能为空");
				return false;
			}
			if(autoUpDate>=autoDownDate){
				commUtil.alertError("上架时间应该小于下架时间");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/customAdPage/keyPage/save.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id,pageName:pageName,autoUpDate:autoUpDate,autoDownDate:autoDownDate,pageType:pageType,sourceProductTypeId:sourceProductTypeId},
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


function testLength(){
	var length = $("#pageName").val().length;
	if(Number(length) > 50){
		$("#testLength").removeAttr("style");
	}else{
		$("#testLength").attr("style",'display:none');
	}
}
</script>
<html>
<body>
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/customAdPage/keyPage/save.shtml">
			<input type="hidden" id="id" name="id" value="${id}">
			<input type="hidden" id="pageType" name="pageType" value="${pageType}">
			<table class="gridtable">
			<c:if test="${pageType == 12 || pageType == 13 || pageType == 16 || pageType == 25}">
		       <tr>
					<td colspan="1" class="title">所属类目</td>
					<td colspan="5" align="left" class="l-table-edit-td">
						<select style="width: 160px;" id="sourceProductTypeId" name="sourceProductTypeId">
						    <option value="">请选择</option>
							<c:forEach var="sourceProductTypes" items="${sourceProductTypes}">
							   <option value="${sourceProductTypes.id}" <c:if test="${sourceProductTypes.id==customAdPage.sourceProductTypeId}">selected="selected"</c:if>>${sourceProductTypes.sourceProductTypeName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			
			<c:if test="${pageType == 14 || pageType == 15 || pageType == 17 || pageType == 18|| pageType == 19 || pageType == 22}">
				<input type="hidden" id="sourceProductTypeId" name="sourceProductTypeId" value="${sourceProductTypes.id}"/>
			</c:if>
           	<tr>
               <td class="title">主题名称</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="pageName" name="pageName" value="${customAdPage.pageName}" style="width: 200px;" onchange="testLength()"/>
               		<span id="testLength" style="display:none" class="required">最多可输入50个字！</span>
               </td>
			</tr>
	        <tr>
               <td class="title">上架时间</td>
               <td align="left" class="l-table-edit-td">
               		<input type="text" id="autoUpDate" name="autoUpDate" value="${autoUpDate}"/>
	               	<script type="text/javascript">
						$(function(){
							$("#autoUpDate").ligerDateEditor({
								format: "yyyy-MM-dd hh:mm:ss",
								showTime : true,
								labelWidth : 150,
								width:200,
								labelAlign : 'left'
							});
						});
					</script>
				</td>
	        </tr>
	        <tr>
               <td class="title">下架时间</td>
               <td align="left" class="l-table-edit-td">
               		<input type="text" id="autoDownDate" name="autoDownDate" value="${autoDownDate}"/>
	               	<script type="text/javascript">
						$(function(){
							$("#autoDownDate").ligerDateEditor({
								format: "yyyy-MM-dd hh:mm:ss",
								showTime : true,
								labelWidth : 150,
								width:200,
								labelAlign : 'left'
							});
						});
					</script>
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
