<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>


<style type="text/css">
	body {
		width: 750px;
		height: 100%;
	}
	img {
		width: 100%;
	}
	.newUser {
		 width: 750px; 
		 height: 182px;
	}
	
	.brand_img {
		overflow: hidden;
		width: 187.5px;
		height: 94px;
		display: inline-block;
		float: left;
		border-right: 1px solid #eee;
		border-bottom: 1px solid #eee;
		box-sizing: border-box;
	}
	.float-clear {
		clear: both;
	}
</style>

<html>
<body>
	
	<div>
		<div>
			<img alt="图片1" src="${pageContext.request.contextPath}/images/previewHomePage/thead.jpg">
		</div>
		<div>
			<div class="newUser" >
				<img alt="新用户专区无图片" src="${pageContext.request.contextPath}/file_servelt/${pic_0.pic }"
					onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_1.jpg;this.onerror=null'" >
			</div>
			<div>
				<div style="width: 250px; height: 400px; display: inline-block; float: left;" >
					<img alt="秒杀专区无图片" src="${pageContext.request.contextPath}/file_servelt/${pic_1.pic }" 
						onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_2.jpg;this.onerror=null'" >
				</div>
				<div style="width: 500px; height: 200px; display: inline-block; float: left;">
					<img alt="爆款专区无图片" src="${pageContext.request.contextPath}/file_servelt/${pic_2.pic }"
						onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_3.jpg;this.onerror=null'" >
				</div>
				<div style="width: 250px; height: 200px; display: inline-block; float: left">
					<img alt="专区小广告1无图片" src="${pageContext.request.contextPath}/file_servelt/${pic_3.pic }"
						onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_4.jpg;this.onerror=null'" >
				</div>
				<div style="width: 250px; height: 200px; display: inline-block; float: left;">
					<img alt="专区小广告2无图片" src="${pageContext.request.contextPath}/file_servelt/${pic_4.pic }"
						onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_4.jpg;this.onerror=null'" >
				</div>
				<div class="float-clear" ></div>
			</div>
		</div>
		<div>
			<img alt="图片2" src="${pageContext.request.contextPath}/images/previewHomePage/tbody.jpg">
		</div>
		<div>
			<div class="brand_img" >
				<img alt="品牌推荐1无图片" src="${pageContext.request.contextPath}/file_servelt/${picBrand[0].pic }"
					onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_5.jpg;this.onerror=null'" >
			</div>
			<div class="brand_img" >
				<img alt="品牌推荐2无图片" src="${pageContext.request.contextPath}/file_servelt/${picBrand[1].pic }"
					onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_5.jpg;this.onerror=null'" >
			</div>
			<div class="brand_img" >
				<img alt="品牌推荐3无图片" src="${pageContext.request.contextPath}/file_servelt/${picBrand[2].pic }"
					onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_5.jpg;this.onerror=null'" >
			</div>
			<div class="brand_img" >
				<img alt="品牌推荐4无图片" src="${pageContext.request.contextPath}/file_servelt/${picBrand[3].pic }"
					onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_5.jpg;this.onerror=null'" >
			</div>
			<div class="brand_img" >
				<img alt="品牌推荐5无图片" src="${pageContext.request.contextPath}/file_servelt/${picBrand[4].pic }"
					onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_5.jpg;this.onerror=null'" >
			</div>
			<div class="brand_img" >
				<img alt="品牌推荐6无图片" src="${pageContext.request.contextPath}/file_servelt/${picBrand[5].pic }"
					onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_5.jpg;this.onerror=null'" >
			</div>
			<div class="brand_img" >
				<img alt="品牌推荐7无图片" src="${pageContext.request.contextPath}/file_servelt/${picBrand[6].pic }"
					onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_5.jpg;this.onerror=null'" >
			</div>
			<div class="brand_img" >
				<img alt="品牌推荐8无图片" src="${pageContext.request.contextPath}/file_servelt/${picBrand[7].pic }"
					onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_5.jpg;this.onerror=null'" >
			</div>
			<div class="float-clear" ></div>
		</div>
		<div>
			<img alt="图片3" src="${pageContext.request.contextPath}/images/previewHomePage/tfoot_1.jpg">
		</div>
		<div>
			<img alt="图片4" src="${pageContext.request.contextPath}/images/previewHomePage/tfoot_2.jpg">
		</div>
		<div>
			<img alt="图片5" src="${pageContext.request.contextPath}/images/previewHomePage/tfoot_3.jpg">
		</div>
	</div>
	
</body>
</html>
