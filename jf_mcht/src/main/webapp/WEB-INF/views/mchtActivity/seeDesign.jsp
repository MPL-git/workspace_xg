<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>查看设计驳回详情</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
</head>

<style type="text/css">

	.panel-table tr td:first-child {
		width: 20%;
		text-align: center;
	}
	.text-tr {
		height: 300px;
	}
	.text-tr img {
		display: block;
		width: 100%;
	}
</style>

<body>
<!--查看品牌 -->
<div class="modal-dialog wg-xx" role="document" style="width:800px;">
<div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <span class="modal-title" id="exampleModalLabel">查看设计驳回详情</span>
    </div>
	<div class="modal-body">
		<div>
			<table class="panel-table">
				<tr>
	    			<td>入口图</td>
	    			<td>
	    				<img onclick="viewerPic(this.src)" style="width:300px;height:150px;" src="${ctx}/file_servelt${activity.entryPic }" />
	    			</td>
	    		</tr>
				<tr>
	    			<td>头部海报图</td>
	    			<td>
	    				<img onclick="viewerPic(this.src)" style="width:300px;height:150px;" src="${ctx}/file_servelt${activity.posterPic }" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>驳回理由</td>
	    			<td>${activity.designAuditRemarks }</td>
	    		</tr>
	    		<tr class="text-tr">
	    			<td>具体原因指导说明</td>
	    			<td>${activity.designRejectReason }</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2">
	    				<div>
	    					<button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close">关闭</button>
	    				</div>
	    			</td>
	    		</tr>
			</table>
		</div>
	</div>
	<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;">
	
	</ul>
</div>
</div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
  <script type="text/javascript">
	  function viewerPic(imgPath){
		  window.parent.viewerImage(imgPath);
	  }
  
  </script>
</body>
</html>
