<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}



.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}
</style>
<script type="text/javascript">
$(function(){
	$(".dateEditor").ligerDateEditor({
		showTime : true,
		format: "yyyy-MM-dd hh:mm:ss",
		labelAlign : 'left',
		width : 160
	});
	
});
function saveRecDate(){
	var couponIds = $("#couponIds").val();
	var recBeginDate = new Date($("#recBeginDate").val());
	var recEndDate = new Date($("#recEndDate").val());
	var activityBeginTime = '${activityBeginTime}';
	var activityEndTime = '${activityEndTime}';
	if(recBeginDate>=activityEndTime){
		commUtil.alertError("开始领取时间必须小于活动结束时间");
		return;
	}
	if(recBeginDate>recEndDate){
		commUtil.alertError("开始领取时间不可大于结束领取时间");
		return;
	}
	if(recEndDate<activityBeginTime){
		commUtil.alertError("结束领取时间不可小于活动开始时间");
		return;
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/activityNew/saveRecDate.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {
			couponIds : couponIds,
			recBeginDate : recBeginDate.format("yyyy-MM-dd hh:mm:ss"),
			recEndDate:recEndDate.format("yyyy-MM-dd hh:mm:ss")
		},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				commUtil.alertSuccess("保存成功");
				parent.location.reload();
				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
		<input type="hidden" id="couponIds" name="couponIds" value="${couponIds}">
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">开始领取时间</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<input type="text" class="dateEditor" id="recBeginDate" name="recBeginDate" value='<fmt:formatDate value="${recBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">结束领取时间</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<input type="text" class="dateEditor" id="recEndDate" name="recEndDate" value='<fmt:formatDate value="${recEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
				</td>
			</tr>
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="button" id="Button1" onclick="saveRecDate();"
								style="float:left;" class="l-button l-button-submit" value="提交修改"
								 />
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />

					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
