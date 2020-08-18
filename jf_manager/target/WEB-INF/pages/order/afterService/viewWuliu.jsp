<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
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
			var refundOrderId = $.trim($("#refundOrderId").val());
			var status = "4";
			var refundRegisterDate = $("#refundRegisterDate").val();
			$.ajax({
				url : "${pageContext.request.contextPath}/refundOrder/confirm.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":refundOrderId,status:status,refundRegisterDate:refundRegisterDate},
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
<div class="wlxx">
	<h4>物流信息</h4>
	<c:if test="${hasWuliu}">
		<c:set var="startIndex" value="${fn:length(wuliuInfos)-1 }"></c:set>  
	    <c:forEach var="wuliuInfo" items="${wuliuInfos}" varStatus="status">
	    <div class="panel-body">
		     <p <c:if test="${status.index == 0}">style="color: green;"</c:if>>  
		         ${wuliuInfos[startIndex - status.index].AcceptTime}
		         <br>
		         ${wuliuInfos[startIndex - status.index].AcceptStation}
		     </p>  
		</div>  
		</c:forEach>
	</c:if>
	<c:if test="${!hasWuliu}">
		<div class="panel-body">
		     <p>无</p>  
		</div>
	</c:if>
</div>	        
</body>
</html>
