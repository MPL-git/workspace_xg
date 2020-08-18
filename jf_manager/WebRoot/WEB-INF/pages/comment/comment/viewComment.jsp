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
	
	<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/star.css" rel="stylesheet" type="text/css" />
	
<style type="text/css">
	*{
		font: normal 12px/2em '微软雅黑';
		padding: 0;
		margin:	0
	}
	ul,li{
		list-style: none
	}
	a{
		color: #09f;
	}
</style>

</head>
	<body style="margin: 10px; ">
		<div>
			<div style="float: left;margin: 10px 10px 10px 0px;">商品描述</div> 			
			<div class="starBox" style="margin: 10px 0px 10px 0px;">
				<ul class="star" >
					<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li>
					<li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li>
					<li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li>
					<li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li>
					<li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>
				</ul>
				<div class="current-rating" style="width: ${commentCustom.productScore*24 }px;"></div>
			</div>
			<div style="float: left;clear: both;">
			    <c:if test="${empty commentCustom.content }">
			    	此用户没有填写评价~
			    </c:if>
			    <c:if test="${not empty commentCustom.content }">
			    	${commentCustom.content }
			    </c:if>
		    </div>	
			<div style="float: left;clear: both;">
		    	<c:forEach items="${commentPicList }" var="commentPic">
					<img style="margin: 5px 5px 0px 0px;" src="${pageContext.request.contextPath}/file_servelt${commentPic.pic }" width="60" height="60" >
				</c:forEach>
		    	<p style="color: gray;">
		    		<span>
		    			<fmt:formatDate value="${commentCustom.createDate }" pattern="yyyy-MM-dd"/>
		    		</span>
		    		<span style="margin-left: 5px;">${commentCustom.productPropDesc }</span>
		    	</p>			
			    <c:if test="${not empty betweenDays }">
			    	<c:if test="${betweenDays == 0}">
					    <div style="color: red;margin-top: 5px;">当天追评</div>
			    	</c:if>
			    	<c:if test="${betweenDays > 0}">
					    <div style="color: red;margin-top: 5px;">${betweenDays }天后追评</div>
			    	</c:if>
			    </c:if>
		    </div>
		    <div style="float: left;clear: both;">
		    	${appendComment.content }
		    </div>
		    <div style="float: left;clear: both;">
		    	<c:forEach items="${appendCommentPicList }" var="appendCommentPic">
					<img style="margin: 5px 5px 5px 0px;" src="${pageContext.request.contextPath}/file_servelt${appendCommentPic.pic }" width="60" height="60" >
				</c:forEach>
		    </div>
		    <div style="float: left;clear: both;width: 100%;">
		    	<hr style="border-top:2px dotted #ddd; margin: 10px 0px 10px 0px;" />
		    	<div>
			    	<span class="glyphicon glyphicon-home"></span>
			    	<span style="margin-left: 2px;">店铺评分</span>
		    	</div>
		    	<div>
			    	<div style="float: left;margin: 5px 10px 5px 0px;">卖家服务</div> 			
					<div class="starBox" style="margin: 5px 0px 5px 0px;">
						<ul class="star" >
							<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li>
							<li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li>
							<li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li>
							<li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li>
							<li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>
						</ul>
						<div class="current-rating" style="width: ${shopScore.mchtScore*24 }px;"></div>
					</div>
				</div>
				<div style="clear: both;">
			    	<div style="float: left;margin: 5px 10px 5px 0px;">物流服务</div> 			
					<div class="starBox" style="margin: 5px 0px 5px 0px;">
						<ul class="star" >
							<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li>
							<li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li>
							<li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li>
							<li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li>
							<li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>
						</ul>
						<div class="current-rating" style="width: ${shopScore.wuliuScore*24 }px;"></div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>