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
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/clipboard.min.js"></script>
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
	var clipBoardContent= "";
	$(function(){
		$('#iosActivityGroupId').val(${landingPage.iosActivityGroupId});

		$('#copy').bind('click',function () {
			if($("#iosActivityGroupId option:selected").val() != '' && $('#iosActivityName').val() == ''){
				commUtil.alertError("iOS活动名称不能为空！");
				return;
			}
			if($("#iosActivityGroupId option:selected").val() =='' && $('#androidChannel').val() == '' && $('#iosActivityName').val() == ''){
				clipBoardContent="${mUrl}"+"?id="+$('#id').val();
				new Clipboard('#copy',{text:function(trigger) { return clipBoardContent;} });
			}else{
				clipBoardContent="${mUrl}"+"?id="+$('#id').val()+"&bao="+$('#androidChannel').val()+"&zuid="+$("#iosActivityGroupId option:selected").val()+"&pname="+$('#iosActivityName').val();
				new Clipboard('#copy',{text:function(trigger) { return clipBoardContent;} });
			}
			commUtil.alertSuccess("复制成功！");
			$.ajax({
				url : "${pageContext.request.contextPath}/landingPage/viewAddressSubmit.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : $("#form1").serializeArray(),
				timeout : 30000,
				success : function(data) {
				},
				error : function() {
				}
			});
		})
	});
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" id="form1">
	   <input type="hidden" id="id"  name="id" value="${landingPage.id}">
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">安卓渠道:</td>
				<td align="left" class="l-table-edit-td" >
					<input maxLength="50" style="width:160px;" type="text" id="androidChannel" name="androidChannel" value="${landingPage.androidChannel}"/>
				</td>				
           	</tr>
			<tr>
            	<td class="title" width="20%">IOS活动组:</td>
				<td align="left" class="l-table-edit-td" >
					<select style="width:160px;" id="iosActivityGroupId" name="iosActivityGroupId" >
						<option value="">请选择</option>
						<c:forEach var="spreadActivityGroup" items="${spreadActivityGroups}">
							<option value="${spreadActivityGroup.id}">${spreadActivityGroup.activityGroup}</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr>
				<td class="title" width="20%">IOS活动名称:</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="iosActivityName" name="iosActivityName" value="${landingPage.iosActivityName}" />
				</td>
			</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" class="l-button l-button-submit" id="copy" value="复制"/>
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>