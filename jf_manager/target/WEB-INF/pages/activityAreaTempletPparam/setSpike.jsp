<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
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
$(function(){
		$("#save").bind('click',function(){
			$.ajax({
				url : "${pageContext.request.contextPath}/activityAreaNew/saveDecorateArea.shtml",
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
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/activityAreaNew/saveDecorateArea.shtml">
			<input type="hidden" id="decorateInfoId" name="decorateInfoId" value="${decorateInfoId}">
			<input type="hidden" id="decorateAreaId" name="decorateAreaId" value="${decorateArea.id}">
	<div style="align-content: center;height: 40px;margin-left:20px;margin-top:10px">
<%--		<select id="user_id" name="user_id">
	<c:forEach items="${users}" var="u">
		<option value="${u.id }" <c:if test="${user.user_id==u.id}"><c:out value="selected"/></c:if>>
			${u.name}
		</option>
	</c:forEach>
</select>
--%>
		数据来源:&nbsp;&nbsp;<select id="projectPorperty" name="projectPorperty">
	<option value="1">会场秒杀</option>
	<option value="0">限时秒杀</option>
</select>
	</div>
		<div style="align-content: center;height: 60px;margin-left:20px;margin-top:10px">
	背景图:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="background" type="button" style="width: 120px" value="上传背景图"/>
			<br>
	<span style="margin-left:50px;margin-top:10px">&nbsp;&nbsp;图片格式为.JPG 尺寸要求:200kb以内 宽1242 像素不限</span>
		</div>
<%--	<div style="height: 40px;margin:0 auto;width: 400px">--%>
	<div style="margin-left:80px;margin-top:10px">
	<input id="save" type="button" style="align-content: center;width: 100px" class="l-button l-button-submit" value="确认"/>
	</div>

</form>
</body>
</html>
