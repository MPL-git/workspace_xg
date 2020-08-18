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
	var submitFlag = true;
	function funSubmit() {
		var sprChnlName = $("#sprChnlName").val();
		var remarks = $("#remarks").val();
		if(sprChnlName == '') {
			commUtil.alertError("渠道名称不能为空");
			return;
		} 
		if(sprChnlName.length > 50) {
			commUtil.alertError("渠道名称只能1-50个字符~");
			return;
		}
		if(!validateSprChnlName(sprChnlName)) {
			commUtil.alertError("渠道名称不能重复~");
			return;
		}
		if(remarks.length > 100) {
			commUtil.alertError("备注只能0-100个字符~");
			return;
		}
		if(submitFlag){
    		submitFlag = false;
    		$("#form1").submit();
    	}
	}
	
	function validateSprChnlName(sprChnlName) {
		var returnFlag = true;
		var weixinXcxSprChnlId = $("#weixinXcxSprChnlId").val();
		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/weixinXcxSprChnl/validateSprChnlName.shtml',
			data : {sprChnlName : sprChnlName, weixinXcxSprChnlId : weixinXcxSprChnlId},
			dataType : 'json',
			async : false,
			success : function(data) {
				if(data.code == 200 && !data.flag) {
					returnFlag = false;
				}
				if(data.code != 200) {
					commUtil.alertError(data.msg);
				}
			},
			error : function(e) {
				commUtil.alertError("系统异常请稍后再试！");
			}
		});
		return returnFlag;
	}

</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/weixinXcxSprChnl/editWeixinXcxSprChnl.shtml" >
		<input type="hidden" id="weixinXcxSprChnlId" name="weixinXcxSprChnlId" value="${weixinXcxSprChnl.id }" />
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">渠道名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:160px;" type="text" id="sprChnlName" name="sprChnlName" value="${weixinXcxSprChnl.sprChnlName }" />
					</br><span class="activity-hint" >注意：标签名称不能超过50个字符</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">备注</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="13" cols="40" id="remarks" name="remarks" >${weixinXcxSprChnl.remarks }</textarea>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" class="l-button l-button-submit" onclick="funSubmit();" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>