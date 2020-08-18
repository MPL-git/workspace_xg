<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>评论详情</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/css/star.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}
.glyphicon-remove{
color:red;
margin-left: 3px;
cursor:pointer;
}
.docs-pictures{
padding: 0px;
}
.docs-pictures li{
position: relative;
margin: 3px;
}

.pic-btn-container{
	width:100%;
	position: absolute;
    top: 0px;
    background:rgba(0, 0, 0, 0.5);
    height:0px;
    z-index: 300;
    overflow: hidden;
    text-align: right;
    padding-right: 3px;
}

.video_box {
    z-index: 2222;
    display: block;
}

.black_box {
    background: #000;
    opacity: 0.6;
    left: 0;
    top: 0;
    z-index: 1111;
    position: fixed;
    height: 100%;
    width: 100%;
}
.video_close {
    position: absolute;
    top: -14px;
    right: -12px;
}
</style>
</head>

<body>
<!--查看投诉详情 -->
  <div class="modal-dialog sh-xq ts-xq" role="document" style="width:420px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">评论详情</span>
      </div>
     <div class="modal-body" style="overflow: hidden;">
	    <div style="float: left;">商品描述&nbsp;&nbsp;&nbsp;&nbsp;</div> 			
		<div class="starBox">
			<ul class="star" >
				<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li>
				<li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li>
				<li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li>
				<li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li>
				<li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>
			</ul>
			<div class="current-rating" style="width: ${starWidth}px;"></div>
		</div>
	    <div style="float: left;clear: both;">
		    <c:if test="${empty comment.content}">
		    	此用户没有填写评价~
		    </c:if>
		    <c:if test="${not empty comment.content}">
		    	${comment.content}
		    </c:if>
	    </div>			
	    <div style="float: left;clear: both;">
	    	<c:forEach items="${commentPics}" var="commentPic">
				<img class="w_h_60_60" src="${ctx}/file_servelt${commentPic.pic}" onclick="viewerPic(this.src)">
			</c:forEach>
	    	<div class="preferential"><fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;${productPropDesc}</div>			
		    <c:if test="${not empty betweenDays}">
		    	<c:if test="${betweenDays==0}">
				    <div style="color: red;">当天追评</div>
		    	</c:if>
		    	<c:if test="${betweenDays>0}">
				    <div style="color: red;">${betweenDays}天后追评</div>
		    	</c:if>
		    </c:if>
	    </div>
	    <div style="float: left;clear: both;">
	    	${appendComment.content}
	    </div>
	    <div style="float: left;clear: both;">
	    	<c:forEach items="${appendCommentPics}" var="appendCommentPic">
				<img class="w_h_60_60" src="${ctx}/file_servelt${appendCommentPic.pic}" onclick="viewerPic(this.src)">
			</c:forEach>
	    </div>
	    <div style="float: left;clear: both;width: 100%">
	    	<hr style="border-top:2px dotted #ddd;" />
	    	<div>
		    	<span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;店铺评分
	    	</div>
	    	<div>
		    	<div style="float: left;">卖家服务&nbsp;&nbsp;&nbsp;&nbsp;</div> 			
				<div class="starBox">
					<ul class="star" >
						<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li>
						<li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li>
						<li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li>
						<li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li>
						<li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>
					</ul>
					<div class="current-rating" style="width: ${mchtScoreStarWidth}px;"></div>
				</div>
			</div>
			
			<div style="clear: both;">
		    	<div style="float: left;">物流服务&nbsp;&nbsp;&nbsp;&nbsp;</div> 			
				<div class="starBox">
					<ul class="star" >
						<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li>
						<li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li>
						<li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li>
						<li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li>
						<li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>
					</ul>
					<div class="current-rating" style="width: ${wuliuScoreStarWidth}px;"></div>
				</div>
			</div>
	    </div>
	</div>
    </div>
  </div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myWebUploader.js"></script>
<script type="text/javascript">
</script>
</body>
</html>
