<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function rejectRemarks(){
		var type="${type}";
		var activityProductId=${activityProductId};
		var batchName=$("#rejectProductRemarks").val();
		$.ajax({ //ajax提交
			type:'POST',
			url:"${pageContext.request.contextPath}/activity/allActivityProductBatchReject.shtml",
			data:{activityProductId:activityProductId,batchName:batchName,type:type},
			dataType:'json',
			cache: false,
			success: function(json){
			   if(json==null || json.statusCode!=200){
			     commUtil.alertError(json.message);
			  }else{
                 $.ligerDialog.success("操作成功",function(yes) {
                	 		parent.$("#searchbtn").click();
	  						frameElement.dialog.close();
					});
			  }
			},
			error: function(e){
			 commUtil.alertError("系统异常请稍后再试");
			}
    });
	}

</script>	
<html>
<body>

	<table class="gridtable">
		<tr>
			<td style="border:none" align="center"><span style="font-size: 15px;">驳回理由：</span><input id="rejectProductRemarks" style="width: 500px; height: 20px;" type="text" value="" /></td>
		</tr>
		<tr>
			<td style="border:none" align="center">
				<input type="button" style="cursor:pointer;width: 150px;height: 30px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" onclick="rejectRemarks();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" style="cursor:pointer;width: 150px;height: 30px;background-color: rgba(255, 255, 255, 1);" value="关闭" onclick="frameElement.dialog.close();" />
			</td>
		</tr>
	</table>
</body>
</html>
