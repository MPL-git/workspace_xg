<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.radioClass{margin-right: 20px;}
</style>
<script type="text/javascript">
$(function(){
	$("#confirm").bind('click',function(){
		var mchtProductBrandId = $("#mchtProductBrandId").val();
		var isActivity = $("input[name='isActivity']:checked").val();
		if(!mchtProductBrandId){
			commUtil.alertError("数据错误，请刷新页面");
			return false;
		}
		if(!isActivity){
			commUtil.alertError("请选择是否参与特卖");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/editIsActivity.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":mchtProductBrandId,"isActivity":isActivity},
			timeout : 30000,
			success : function(data) {
				if(data.returnCode == '0000'){
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
	
	$("#cancel").bind('click',function(){
		frameElement.dialog.close();
	});
});
</script>
<html>
<body>
	<div class="title-top">
		<input type="hidden" id="mchtProductBrandId" value="${mchtProductBrandId}">
		<table class="gridtable">
          	<tr>
          		<td class="title">是否参与特卖</td>
          		<td colspan="6">
	          		<span class="radioClass">
						<input type="radio" value="1" name="isActivity" <c:if test="${isActivity == 1}">checked="checked"</c:if>>是
					</span>
					<span class="radioClass">
						<input type="radio" value="0" name="isActivity" <c:if test="${isActivity == 0}">checked="checked"</c:if>>否
					</span>
				</td>
          	</tr>
          	<tr>
          		<td class="title">操作</td>
          		<td colspan="6" align="left" class="l-table-edit-td">
					<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					<input id="cancel" type="button" style="float:left;" class="l-button l-button-test" value="取消"/>
				</td>
          	</tr>
        </table>
	</div>
	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
