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
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}

</style>
<script type="text/javascript">


function save(){
	var auditRemarks = $("#auditRemarks").val();
	var ids = $("#ids").val();
	var canApply = $("input[name='canApply']:checked").val()


	if(auditRemarks==""){
		commUtil.alertError("请输入驳回原因");
		return;
	}
	if(auditRemarks.length>150){
		commUtil.alertError("字数请控制在150字以内");
		return;
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/resourceLocationManagement/toShopBatchRejection.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {ids:ids,auditRemarks:auditRemarks,canApply:canApply},
		timeout : 30000,
		success : function(data) {
			if ( data.returnCode == "0000" ) {
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
<form id="violateOrderForm" method="post" action="${pageContext.request.contextPath}/resourceLocationManagement/toShopBatchRejection.shtml">
	<input type="hidden" id="ids" name="ids" value="${ids}">
		<div  align="center" style="margin-bottom:20px;">
		<input name="canApply" checked  type="radio" value="0" > 七天内不可报名
		<input name="canApply" type="radio" value="1" > 可立刻报名
		<input name="canApply" type="radio" value="2" > 不可再次报名
		</div>
		
		<div  align="center" style="margin-bottom:20px;">
		<textarea  id="auditRemarks"  name ="auditRemarks"  placeholder=" 请填写驳回理由（不可为空）"  rows="10" cols="65"  style="resize:none;"></textarea>
		</div>
		
		<div  align="right" >
		<input  class="l-button l-button-submit" value="确认" onclick="save();"/>
		<input style="margin-left: 20px;" class="l-button" type="button" value="取消" onclick="frameElement.dialog.close()" />
		</div>
		
		
</form>		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
