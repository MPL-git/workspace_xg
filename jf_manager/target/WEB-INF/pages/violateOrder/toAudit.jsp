<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
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
	
	$("input[name='auditStatus']").bind('click',function(){
		var auditStatus = $(this).val();
		if(auditStatus == "1"){
			$("#auditRemarksTr").hide();
		}else{
			$("#auditRemarksTr").show();
		}
	});
	
		$("#confirm").bind('click',function(){
			var violateOrderId = $("#violateOrderId").val();
			var auditStatus = $("input[name='auditStatus']:checked").val();
			if(!auditStatus){
				commUtil.alertError("请选择审核结果");
				return false;
			}
			var auditRemarks = $("#auditRemarks").val();
			if(auditStatus == "2"){
				if(!auditRemarks){
					commUtil.alertError("驳回原因必填");
					return false;
				}
			}
			var param = $("#form").serialize();
			$.ajax({
				url : "${pageContext.request.contextPath}/violateOrder/audit.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : param,
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
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/violateOrder/audit.shtml">
			<input type="hidden" id="violateOrderId" name="violateOrderId" value="${violateOrderId}">
			<table class="gridtable">
           	<tr>
               <td class="title" style="width: 25%;">审核结果</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<span style="margin-left: 10PX;">
	               		<label><input type="radio" name="auditStatus" value="1" />通过</label>
               		</span>
               		<span style="margin-left: 20PX;">
	               		<label><input type="radio" name="auditStatus" value="2" checked="checked"/>驳回</label>
               		</span>
               </td>
			</tr>
			<tr id="auditRemarksTr">
            	<td class="title">驳回原因</td>
				<td align="left" class="l-table-edit-td" >
					<textarea name="auditRemarks" id="auditRemarks" rows="8" cols="30"></textarea>
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
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>   
</body>
</html>
