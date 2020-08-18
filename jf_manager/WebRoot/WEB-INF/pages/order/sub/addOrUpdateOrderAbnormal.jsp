<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
	 <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<style type="text/css">
 	.radioClass{margin: 0 10px 0 10px;}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
</style>
<script type="text/javascript">
	
	function subForm() {
		var checkboxNum = $("input[type=checkbox]:checked").length;
		if(checkboxNum == 0) {
			commUtil.alertError("请勾选异常标记！");
		}else {
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/order/afterService/addOrUpdateOrderAbnormal.shtml",
				data: $("#form1").serialize(),
				dataType: "json",
				cache: false,
				success: function(data){
				   if(data == null || data.returnCode != 200){
				     commUtil.alertError(data.returnMsg);
				  }else{
					 parent.location.reload();
					 frameElement.dialog.close();
				  }
				},
				error: function(e){
				 	commUtil.alertError("系统异常请稍后再试");
			    }
	    	});
		}
	}
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" id="form1" class="form1" method="post" action="${pageContext.request.contextPath}/">
		<input type="hidden" id="subOrderId" name="subOrderId" value="${subOrderId }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">异常标记<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<p>
						<input type="checkbox" id="deliveryOvertime" name="deliveryOvertime" value="1" 
							<c:forEach var="orderAbnormalLog" items="${orderAbnormalLogList }">
								<c:if test="${orderAbnormalLog.abnormalType == '3' }">disabled</c:if>
							</c:forEach>
							style="margin: 10px 5px;" />超时发货
					</p>
					<p>
						<input type="checkbox" id="shamDelivery" name="shamDelivery" value="2" 
							<c:forEach var="orderAbnormalLog" items="${orderAbnormalLogList }">
								<c:if test="${orderAbnormalLog.abnormalType == '4' }">disabled</c:if>
							</c:forEach>
							style="margin: 10px 5px;" />虚假发货
					</p>
					<p>
						<input type="checkbox" id="stockout" name="stockout" value="3" 
							<c:forEach var="orderAbnormalLog" items="${orderAbnormalLogList }">
								<c:if test="${orderAbnormalLog.abnormalType == '1' }">disabled</c:if>
							</c:forEach>
							style="margin: 10px 5px;" />缺货
					</p>
					<p>
						<input type="checkbox" id="otherOne" name="otherOne" value="4"
							<c:forEach var="orderAbnormalLog" items="${orderAbnormalLogList }">
								<c:if test="${orderAbnormalLog.abnormalType == '2' }">disabled</c:if>
							</c:forEach>
							style="margin: 10px 5px;" />其他：
						<input type="text" id="abnormalDesc" name="abnormalDesc" style="width: 300px;" />
					</p>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" class="l-button" value="提交" onclick="subForm();" /> 
					<input style="margin-left: 20px;" id="button-close" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>