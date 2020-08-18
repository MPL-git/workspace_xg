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
$(function(){
	<c:if test="${empty setType}">
	$("#upDate").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd",
		onChangeDate:function(){
			var nowDate = new Date();
			var test = this.getFormatDate(this.usedDate)
			var endDate = new Date(test);
			if(endDate<nowDate){
				$.ligerDialog.error('上线时间需大于当前时间！');
			}
		}
	});
	</c:if>
	<c:if test="${not empty setType}">
	$("#upDate").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm:ss",
		onChangeDate:function(){
			var nowDate = new Date();
			var test = this.getFormatDate(this.usedDate)
			var endDate = new Date(test);
			if(endDate<nowDate){
				$.ligerDialog.error('上线时间需大于当前时间！');
			}
		}
	});
	</c:if>
});

function save(){
	var id = $("#id").val();
	var upDate = $("#upDate").val();
	debugger;
	if(!upDate){
		commUtil.alertError("上线时间不能为空");
		return;
	}
	if(new Date(upDate) < new Date()){
		commUtil.alertError("不能选择当前时间以前的时间");
		return;
	}
	<c:if test="${empty setType}">
	upDate = upDate + ' 10:00:00';
	</c:if>
	$.ajax({
		url : "${pageContext.request.contextPath}/resourceLocationManagement/saveEditUpdate.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {id:id,upDate:upDate},
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
<form id="violateOrderForm" method="post" action="${pageContext.request.contextPath}/resourceLocationManagement/saveEditUpdate.shtml">
	<input type="hidden" id="id" name="id" value="${SourceNiche.id}">
	<table class="gridtable">

		<c:if test="${empty setType}">
		<tr>
			<td colspan="1" class="title">自动上架</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="upDate" name="upDate" value="<fmt:formatDate value='${SourceNiche.upDate}' pattern='yyyy-MM-dd'/>"/>
				</td>
		</tr>
		</c:if>

		<c:if test="${not empty setType}">
		<tr>
			<td colspan="1" class="title">自动上架</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="upDate" name="upDate" value="<fmt:formatDate value='${SourceNiche.upDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
				</td>
		</tr>
		</c:if>

		<tr>
			<td class="title">操作</td>
			<td>
				<input type="button" class="l-button l-button-submit" value="确认" onclick="save();"/>
			</td>
		</tr>
	</table>	
</form>		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
