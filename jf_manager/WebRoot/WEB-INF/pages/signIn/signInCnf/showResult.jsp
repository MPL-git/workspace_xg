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
	.div-row:first-child{
		margin-top: 50px;
	}
	.div-row:last-child{
		margin-top: 60px;
		text-align: center;
	}
 	.div-row{
 		margin: 20px 30px;
 	}
 	.span-color-red{
 		color: red;
 	}
</style>
<script type="text/javascript">

</script>

</head>
	<body style="margin: 10px;">
		<div>
			<div class="div-row">
				只签到不邀请好友累计签到满（
				<span class="span-color-red">基础值</span>
				）最低需（
				<span class="span-color-red">${minQdDay }</span>
				）天
			</div>			
			<div class="div-row">
				只签到不邀请好友累计签到满（
				<span class="span-color-red">基础值</span>
				）最高需（
				<span class="span-color-red">${maxQdDay }</span>
				）天
			</div>			
			<div class="div-row">
				每日邀请好友可获得额外红包（
				<span class="span-color-red">${minEveryDay }</span>
				）~（
				<span class="span-color-red">${maxEveryDay }</span>
				）元			
			</div>			
			<div class="div-row">
				签到并邀请好友累计签到满（
				<span class="span-color-red">基础值</span>
				）最低需（
				<span class="span-color-red">${minQdyDay }</span>
				）天
			</div>			
			<div class="div-row">
				签到并邀请好友累计签到满（
				<span class="span-color-red">基础值</span>
				）最高需（
				<span class="span-color-red">${maxQdyDay }</span>
				）天
			</div>			
			<div class="div-row">
				<input class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />			
			</div>			
		</div>
	</body>
</html>