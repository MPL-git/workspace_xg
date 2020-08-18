<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
.upload_image_list li a{display:none !important}


</style>
<script type="text/javascript">


</script>
<html>
<body>
	<!--招商评估商家  -->
	<c:if test="${confirmationStatus==1 || confirmationStatus==null}">
	<div id="content" style="text-align:center;margin-top:40px">
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/refuse.png"></a>
	
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/huixian.png"></a>
	
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/undetermined.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/huixian.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/undetermined.png"></a>
	<br>
	<div style="text-align:center;margin-left:0;">
	<span style="padding-right:40px;color:red">招商评估商家</span>
	<span style="padding-right:40px">主管确认是否合作</span>
	<span>入驻待提交</span>
	</div>
	</c:if>
	
	
	</div>
	<!--主管确认是否合作  -->
	<c:if test="${confirmationStatus==2 }">
	<div id="content" style="text-align:center;margin-top:40px">
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/pass.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/lvxian.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/refuse.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/huixian.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/undetermined.png"></a>
	<br>
	<div style="text-align:center;margin-left:0;">
	<span style="padding-right:40px">招商评估商家</span>
	<span style="padding-right:40px;color:red">主管确认是否合作</span>
	<span>入驻待提交</span>
	</div>
	</c:if>
	
	<c:if test="${confirmationStatus==3 }">
	<div id="content" style="text-align:center;margin-top:40px">
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/pass.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/lvxian.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/pass.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/lvxian.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/refuse.png"></a>
		<br>
	<div style="text-align:center;margin-left:0;">
	<span style="padding-right:40px">招商评估商家</span>
	<span style="padding-right:40px">主管确认是否合作</span>
	<span style="color:red">入驻待提交</span>
	</div>
	</c:if>
	
	<c:if test="${confirmationStatus==4 }">
	<div id="content" style="text-align:center;margin-top:40px">
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/pass.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/lvxian.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/pass.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/lvxian.png"></a>
	<a href="javascript:;" class="video_close"><img src="${pageContext.request.contextPath}/images/pass.png"></a>
		<br>
	<div style="text-align:center;margin-left:0;">
	<span style="padding-right:40px">招商评估商家</span>
	<span style="padding-right:40px">主管确认是否合作</span>
	<span >入驻待提交</span>
	</div>
	</c:if>
	

	
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
