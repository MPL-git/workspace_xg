<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/swiper-4.3.5/dist/css/swiper.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/swiper-4.3.5/dist/js/swiper.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
	html {
		background-color: #f2f2f2;
		position: relative;
	}
	body {
		position: relative;
		text-align: center;
		margin-left: auto;
		margin-right: auto;
		width: 750px;
		height: 100%;
	}
	div {
		text-align: left;
	}
	img {
		width: 100%;
	}
	p {
		padding: 5px;
		margin-left: 30px;
		margin-right: 30px;
	}
	.div-white {
		background-color: white;
		padding-top: 15px;
		padding-bottom: 15px;
		margin-bottom: 15px;
	}
	.sale-price-span {
		color: red;
		font-size: 28px;
		margin-right: 50px;
	}
	.tag-price-span {
		color: gray;
		font-size: 20px;
		text-decoration: line-through;
	}
	.product-name-span {
		font-size: 20px;
	}
	.activity-p {
		font-size: 14px;
		color: red;
		padding-bottom: 10px;
	}
	.activity-span {
		border:1px solid red; 
		padding-left: 3px; 
		padding-right: 3px;
		margin-right: 10px; 
	}
	.title1 {
		font-size: 20px;
	}
	.font-size-14 {
		font-size: 14px;
	}
	.span-width {
		width: 50%; 
		display: block; 
		float: left;
	}
	.clearfix:after {
	    content: '';
	    height: 0;
	    display: block;
	    clear: both;
	}
	.swiper-wrapper {
		width: 100%;
		height: auto;
	}
	.swiper-slide {
      text-align: center;
      font-size: 18px;
      background: #fff;

      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      display: flex;
      -webkit-box-pack: center;
      -ms-flex-pack: center;
      -webkit-justify-content: center;
      justify-content: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
      align-items: center;
    }
</style>

<html>
<body>
	<div>
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<c:forEach items="${productPicList }" var="productPic" varStatus="productPicStatus" >
				 	<div class="swiper-slide">
					 	<img alt="图片${productPicStatus.index+1 }" src="${pageContext.request.contextPath}/file_servelt/${productPic.pic }"
							onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_5.jpg;this.onerror=null'" >
					</div>
				</c:forEach>
		    </div>
		    <!-- Add Pagination -->
		    <div class="swiper-pagination"></div>
		</div>
		<div class="div-white">
			<p>
				<span class="sale-price-span">¥&nbsp;${orderProductSnapshotCustom.salePrice }</span>
				<span class="tag-price-span">¥&nbsp;${orderProductSnapshotCustom.tagPrice }</span>
			</p>
			<p>
				<span class="product-name-span">${orderProductSnapshotCustom.productName }</span>
			</p>
			<p class="activity-p">
				<span class="activity-span">活动优惠</span>
				<span>${orderProductSnapshotCustom.activityDiscount }</span>
			</p>
		</div>
		
		<div class="div-white">
			<p class="title1">
				<span>商品规格图片</span>
			</p>
			<p>
				<img src="${pageContext.request.contextPath}/file_servelt/${skuPic}" style="width: 200px;height: 200px;" onclick="viewerPic(this.src)"
					onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_5.jpg;this.onerror=null'" >
			</p>
		</div>
		
		<div class="div-white">
			<p>
				<span class="title1">商品规格</span>
			</p>
			<p>
				<span class="font-size-14">${orderProductSnapshotCustom.productPropDesc }</span>
			</p>
			<p class="font-size-14 clearfix">
				<span class="span-width" style="color: red;">原价：${orderDtl.originalPrice }</span>
				<span style="color: red;">SVIP折扣：<fmt:formatNumber value="${orderDtl.svipDiscount }" pattern="#.####" ></fmt:formatNumber></span>
			</p>
			<p class="font-size-14 clearfix">
				<span class="span-width" style="color: red;">是否会员价购买：
					<c:if test="${orderDtl.isSvipBuy == '0' }">否</c:if>
					<c:if test="${orderDtl.isSvipBuy == '1' }">是</c:if>
				</span>
				<span style="color: red;">结算类型：
					<c:if test="${subOrder.mchtType == '1' }">SPOP</c:if>
					<c:if test="${subOrder.mchtType == '2' }">POP</c:if>
				</span>
			</p>
			<p class="font-size-14 clearfix">
				<span class="span-width" style="color: red;">结算金额：${orderDtl.settleAmount}</span>
				<span style="color: red;">技术服务费率：${orderDtl.popCommissionRate}</span>
			</p>
			<p class="font-size-14 clearfix">
				<span class="span-width" style="color: red;">栏目来源：${columnTypeDesc}</span>
			</p>
		</div>
		<div class="div-white">
			<p>
				<span class="title1">商品属性</span>
			</p>
			<p class="font-size-14 clearfix">
				<span class="span-width">品牌：${orderProductSnapshotCustom.brandName }</span>
				<span>商品货号：${orderProductSnapshotCustom.artNo }</span>
			</p>
			<p class="font-size-14 clearfix">
				<span class="span-width">适合性别：${orderProductSnapshotCustom.suitSexDesc }</span>
				<span>适合人群：${orderProductSnapshotCustom.suitGroupDesc }</span>
			</p>
			<p class="font-size-14">
				<span class="span-width">适合季节：${orderProductSnapshotCustom.seasonDesc }</span>
				<span style="color: red;">商品ID：${orderProductSnapshotCustom.productCode}</span>
			</p>
		</div>
		<div class="div-white">
			<p>
				<span class="title1">服务说明</span>
			</p>
			<p class="font-size-14">
				<span>${orderProductSnapshotCustom.serviceDesc }</span>
			</p>
		</div>
		<div class="div-white">
			<p class="title1">
				<span>运费</span>
			</p>
			<p class="font-size-14 clearfix">
				<span class="span-width"><span style="color:gray;margin-right: 5px;">送至</span>${freightDescList[0] }</span>
				<span><span style="color:gray;margin-right: 5px;">运费</span>${freightDescList[1] }</span>
			</p>
		</div>
		<div class="div-white">
			<p>
				<span class="title1">文字描述</span>
			</p>
			<div>
				<c:forEach items="${productDescList }" var="productDesc" varStatus="productDescStatus" >
					<p class="font-size-14">
						<span>${productDesc }</span>
					</p>
				</c:forEach>
			</div>
		</div>
		<div class="div-white">
			<p class="title1">
				<span>图片描述</span>
			</p>
			<c:forEach items="${productDescPicList }" var="productDescPic" varStatus="productDescPicStatus" >
				<p>
					<img alt="图片${productDescPicStatus.index+1 }" src="${pageContext.request.contextPath}/file_servelt/${productDescPic.pic }"
						onerror="this.src='${pageContext.request.contextPath}/images/previewHomePage/noImg_5.jpg;this.onerror=null'" >
				</p>
			</c:forEach>
		</div>
	</div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript">
	var swiper = new Swiper('.swiper-container', {
	    pagination: {
	      el: '.swiper-pagination',
	    },
	    loop : true,
	    speed:3000,
	    autoplay: {
	        delay: 3000,
	        disableOnInteraction: false,
        },
  	});
	
	var viewerPictures;
	$(function(){
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
	});

	function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
			$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
			viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
	}
	
</script>
</html>
