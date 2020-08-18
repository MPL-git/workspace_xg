<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.title-width{width: 20%}
.marL20{margin-left:20px;}
</style>
<script type="text/javascript">
	var viewerPictures;
	$(function(){
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		
	});
	
	//查看图片
	function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		imgPath=imgPath.replace('_S', '');
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
	}
</script>
<html>
<body>
	<div class="title-top">
		<table class="gridtable marT10">
			<c:forEach var="map" items="${listMap }">
				<tr>
	               <td class="title title-width" >
	               		<p style="margin-bottom: 10px;">
	               			<c:if test="${map.interventionProcess.operatorType == '1' }">客户</c:if>
	               			<c:if test="${map.interventionProcess.operatorType == '2' }">商家</c:if>
	               		</p>
	               		<p><fmt:formatDate value="${map.interventionProcess.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
	               </td>
	               <td>
	               		<p style="margin-bottom: 10px;">${map.interventionProcess.content }</p>
	               		<span>
	               			<c:forEach var="interventionProcessPic" items="${map.interventionProcessPicList }">
	               				<img src="${pageContext.request.contextPath}/file_servelt${interventionProcessPic.pic }" onclick="viewerPic(this.src);" style="width: 60px;height: 60px;" >
	               			</c:forEach>
	               		</span>
	               </td>
				</tr>
			</c:forEach>
        </table>
	</div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
