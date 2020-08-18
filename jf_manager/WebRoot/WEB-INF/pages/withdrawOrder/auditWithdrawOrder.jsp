<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
 	.radioClass {
		margin-right: 20px;
	}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
    .activity-time {
    	margin-left: 50px;
    }
    .activity-hint {
    	color: #6B6B6B;
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
		
		$("#commit").bind('click',function(){
			var status = $("input[name='status']:checked").val();
			var remarks = $("#remarks").val();
			var ids = $("#ids").val();
			$.ajax({
				url : "${pageContext.request.contextPath}/withdrawOrder/auditWithdrawOrder.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {ids:ids,status:status,remarks:remarks},
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

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/withdrawOrder/auditWithdrawOrder.shtml" >
		<input type="hidden" id="ids" name="ids" value="${ids }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">选中订单数</td>
				<td align="left" class="l-table-edit-td" >${idsLength }</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">审核结果<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<span style="margin-left: 10px;">
						<label>
							<input type="radio" name="status" value="2" checked="checked" />审核通过
						</label>
					</span>
					<span style="margin-left: 30px;">
						<label>
							<input type="radio" name="status" value="5" />审核驳回
						</label>
					</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">原因说明</td>
				<td align="left" class="l-table-edit-td" >
					<textarea cols="30" rows="5" type="text" id="remarks" name="remarks" validate="{maxlength:125}" ></textarea>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" class="l-button l-button-submit" value="提交" id="commit"/> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>