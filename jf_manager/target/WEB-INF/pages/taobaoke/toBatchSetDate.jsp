<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/dateUtil.js" type="text/javascript"></script>
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
	var ids = $("#ids").val();
	var autoUpDate = $("#autoUpDate").val();
	var autoDownDate = $("#autoDownDate").val();
	if(!autoUpDate){
		commUtil.alertError("自动上架时间不能为空");
		return;
	}
	if(!autoDownDate){
		commUtil.alertError("自动下架时间不能为空");
		return;
	}
	var nowDate = new Date();
	var now = nowDate.format("yyyy-MM-dd hh:mm:ss");
	if(autoUpDate<now){
		commUtil.alertError("自动上架时间不可小于当前时间");
		return;
	}
	if(autoDownDate<=autoUpDate){
		commUtil.alertError("自动下架时间不可小于自动上架时间");
		return;
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/taobaoke/batchSetDate.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {ids:ids,autoUpDate:autoUpDate,autoDownDate:autoDownDate},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				commUtil.alertSuccess("操作成功");
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
<form id="form" method="post" action="${pageContext.request.contextPath}/taobaoke/batchSetDate.shtml">
	<input type="hidden" id="ids" name="ids" value="${ids}">
	<table class="gridtable">
		<tr>
			<td class="title">自动上架时间</td>
			<td align="left" class="l-table-edit-td">
				<input id="autoUpDate" name="autoUpDate" validate="{required:true}" type="text" style="width:400px;"/>
				<script type="text/javascript">
					$(function() {
						$("#autoUpDate").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</td>
		</tr>
		<tr>
			<td class="title">自动下架时间</td>
			<td align="left" class="l-table-edit-td">
				<input id="autoDownDate" name="autoDownDate" validate="{required:true}" type="text" style="width:400px;"/>
				<script type="text/javascript">
					$(function() {
						$("#autoDownDate").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</td>
		</tr>
		<tr>
			<td class="title">操作</td>
			<td>
				<input type="button" class="l-button l-button-submit" value="提交" onclick="save();"/>&nbsp;&nbsp;
				<input type="button" value="取消" class="l-button l-button-test" onclick="frameElement.dialog.close();" />
			</td>
		</tr>
	</table>	
</form>		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
