<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>首页</title>
<!-- Custom Theme Style -->
<link
	href="${pageContext.request.contextPath}/static/css/custom.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/jquery-ui.css">
<link href="${ctx }/static/css/main-style.css" rel="stylesheet">
<link href="${ctx }/static/css/main-style-hqp.css" rel="stylesheet">
 <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
</head>
<script type="text/javascript">
	function dateConversion(dates){
		var date = new Date(dates);
		return date.format("yyyy年MM月dd日");			
	}

	$(document).ready(function () {
		//品牌授权有效期
		<c:forEach items="${mchtProductBrand}" var="productBrand">
			var date = dateConversion('${productBrand.platformAuthExpDates}');
			var name = '${productBrand.productBrandName}';
			$('.company-info-container').after('<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">您的品牌【'+name+'】授权有效期于'+date+'到期，到期后品牌将暂停运营。请及时更新<a href="#" onclick="getContents(this);" link_url="${ctx}/mcht/mchtBrandIndex">去更新>>></a></div>');		
		</c:forEach>
		//其它经营许可证有效期
		<c:forEach items="${mchtOtherProductBrand}" var="otherProductBrand">
			var date = dateConversion('${otherProductBrand.otherExpDates}');
			var name = '${otherProductBrand.productBrandName}';
			$('.company-info-container').after('<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">您的品牌【'+name+'】其他经营许可证有效期于'+date+'到期，到期后品牌将暂停运营。请及时更新  <a href="#" onclick="getContents(this);" link_url="${ctx}/mcht/mchtBrandIndex">去更新>>></a></div>');		
		</c:forEach>
		//营业执照有效期
		if(${yearlyInspectionTag eq '0'}){
			var date = dateConversion('${mchtHomeInfo.yearlyInspectionDates}');
			$('.company-info-container').after('<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">您的营业执照有效期于'+date+'到期，到期后店铺将暂停运营。请及时更新  <a href="#" onclick="getContents(this);" link_url="${ctx}/mcht/mchtInfo">去更新>>></a></div>');		
		}
		//法人身份证有效期
		if(${corporationIdcardTag eq '0'}){
			var date = dateConversion('${mchtHomeInfo.corporationIdcardDates}');
			$('.company-info-container').after('<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">您的法人身份证有效期于'+date+'到期，到期后店铺将暂停运营。请及时更新  <a href="#" onclick="getContents(this);" link_url="${ctx}/mcht/mchtInfo">去更新>>></a></div>');		
		}
	});
   
   	var ctx="${ctx}";
   	var intervalId = null;
	function getContent(url){
		clearInterval(intervalId);
		if(url=="${ctx}"){
			return false;
		}
		$.ajax({
	        url: url, 
	        type: "GET", 
	        async: false, 
	        success: function(data){
	            $("#main-content").html(data);
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});
	}
	
	function viewContent(id) {
        var url = "${ctx}/info/content?id=" + id;
        getContent(url);
    }
	function getContents(_this){
		var url = $(_this).attr("link_url");
		debugger;
		$.ajax({
	        url: url, 
	        type: "GET", 
	        async: false, 
	        success: function(data){
	            $("#main-content").html(data);
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});
	}
</script>
<body>
<div>
<div class="content-middle">
			<div class="panel panel-default home-content-panner company-info-container" style="height: auto;">
				<div class="row">
					<div class="col-md-12">
						<span class="company-name-text main-text">${mchtHomeInfo.companyName}</span>
					</div>

				</div>
				<div class="row" style="padding-top: 15px;font-size: 14px; line-height: 28px;">
					<div class="col-md-6">
						<p class="ellipsis" style="margin: 0;" title="您的店铺名：${mchtHomeInfo.shopName}">店铺名：${mchtHomeInfo.shopName}</p>
					</div>
				</div>
				<div class="row" style="padding-top: 10px;font-size: 14px; line-height: 28px;">
					<div class="col-md-7">
						<p class="ellipsis" style="margin: 0;" title="您的店铺序号：${mchtHomeInfo.mchtCode}">店铺序号：${mchtHomeInfo.mchtCode}</p>
						<p style="margin: 0;" title="商品描述 ${productScore}分、卖家服务${mchtScore}分、物流服务${wuliuScore}分">商品描述${productScore}分、卖家服务${mchtScore}分、物流服务${wuliuScore}分</p>
					</div>
					<div class="col-md-5" style="text-align: right;">
						本月GMV：<span class="main-text"
							style="font-size: 20px;padding: 0 10px;">${mchtHomeInfo.totalAmount}</span>元
					</div>			
				</div>
			</div>
			
			<c:forEach items="${mchtBrandChgExamineReject}" var="mchtBrandChg">
				<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">您的品牌【${mchtBrandChg.productBrandName}】更新申请被驳回。请及时查看驳回原因并上传<a href="javascript:getContent('${ctx}/mcht/mchtBrandIndex')">去修改>>></a></div>
			</c:forEach>
					
			<c:forEach items="${mchtInfoChgExamineReject}" var="mchtInfoChg">
				<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">公司信息更新申请被驳回。请及时查看驳回原因并上传<a href="javascript:getContent('${ctx}/mcht/mchtInfo')">去更新>>></a></div>
			</c:forEach>
			
			<c:forEach items="${mchtLicenseChgExamineReject}" var="mchtLicenseChg">
				<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">经营许可证信息更新申请被驳回。请及时查看驳回原因并上传<a href="javascript:getContent('${ctx}/mchtUser/mchtUserIndex')">去更新>>></a></div>
			</c:forEach>
			
			<c:forEach items="${mchtBrandChgFileReject}" var="mchtBrandChg">
				<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">您的品牌【${mchtBrandChg.productBrandName}】资料归档被驳回。请及时查看驳回原因并重新寄回<a href="javascript:getContent('${ctx}/mcht/mchtBrandIndex')">去查看>>></a></div>
			</c:forEach>
			
			<c:forEach items="${mchtInfoChgFileReject}" var="mchtInfoChg">
				<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">公司信息资料归档被驳回。请及时查看驳回原因并上传  并重新寄回<a href="javascript:getContent('${ctx}/mcht/mchtInfo')">去查看>>></a></div>
			</c:forEach>
			
			<c:forEach items="${mchtLicenseChgFileReject}" var="mchtLicenseChg">
				<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">经营许可证归档被驳回。请及时查看驳回原因并上传  并重新寄回<a href="javascript:getContent('${ctx}/mchtUser/mchtUserIndex')">去查看>>></a></div>
			</c:forEach>
			
			<c:forEach items="${mchtContractFileReject}" var="mchtContract">
				<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">合同归档被驳回。请及时查看驳回原因并上传  并重新寄回 <a href="javascript:getContent('${ctx}/mcht/contract/renewIndex')">去查看>>></a></div>
			</c:forEach>
			
			<c:forEach items="${mchtBrandChgPassRemind}" var="mchtBrandChg">
				<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">您的品牌【${mchtBrandChg.productBrandName}】更新申请通过，请尽快将资质文件寄回平台 <a href="javascript:getContent('${ctx}/mcht/mchtBrandIndex')">去查看>>></a></div>
			</c:forEach>
			
			<c:forEach items="${mchtInfoChgPassRemind}" var="mchtInfoChg">
				<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">公司信息更新申请已通过，请尽快将资质文件寄回平台 <a href="javascript:getContent('${ctx}/mcht/mchtInfo')">去查看>>></a></div>
			</c:forEach>
			
			<c:forEach items="${mchtLicenseChgPassRemind}" var="mchtLicenseChg">
				<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">经营许可证更新申请已通过。请尽快将资质文件寄回平台<a href="javascript:getContent('${ctx}/mchtUser/mchtUserIndex')">去查看>>></a></div>
			</c:forEach>
			
			<c:if test="${mchtHomeInfo.isCompanyInfPerfect==0}">
			<div class="panel panel-default home-content-panner" style="background-color: #ffffcc;">您有1个公司信息的资质待完善，审核通过后可以申请特卖销售。  <a href="javascript:getContent('${ctx}/mcht/mchtInfo')">立即完善</a></div>
			</c:if>
			
			<style>
				.home-public-merge {
					width: 52px;
					height: 52px;
					margin: 0 auto;
					background: url(${ctx }/static/images/home-public-merge.png) no-repeat;
				}
			</style>
			
			<div class="panel panel-default home-content-panner" style="padding: 17px 10px 12px;">
				<div class="row text-center">
					<div class="col-md-2">
						<div class="home-public-merge" style="background-position: 0 0"></div>
						<!-- <img alt="" src="${ctx }/static/images/fbsp.png"> -->
					</div>
					<div class="col-md-2">
						<div class="home-public-merge" style="background-position: -108px 0"></div>
						<!-- <img alt="" src="${ctx }/static/images/sqtm.png"> -->
					</div>
					<div class="col-md-2">
						<div class="home-public-merge" style="background-position: -215px; 0"></div>
						<!-- <img alt="" src="${ctx }/static/images/shpq.png"> -->
					</div>
					<div class="col-md-2">
						<div class="home-public-merge" style="background-position: -323px 0"></div>
						<!-- <img alt="" src="${ctx }/static/images/tmks.png"> -->
					</div>
					<div class="col-md-2">
						<div class="home-public-merge" style="background-position: -431px 0"></div>
						<!-- <img alt="" src="${ctx }/static/images/hdjs.png"> -->
					</div>
					<div class="col-md-2">
						<div class="home-public-merge" style="background-position: -540px 0"></div>
						<!-- <img alt="" src="${ctx }/static/images/jsdk.png"> -->
					</div>

				</div>
				<div class="row text-center" style="padding-top: 7px; text-align: center;">
					<div class="col-md-2">发布商品</div>
					<div class="col-md-2">品牌特卖</div>
					<div class="col-md-2">官方会场</div>
					<div class="col-md-2">秒杀报名</div>
					<div class="col-md-2">订单管理</div>
					<div class="col-md-2">结算货款</div>

				</div>
			</div>
			<div class="panel panel-default home-content-panner container-fluid"
				style="padding-bottom: 0;">
				<div class="row">
					<div class="col-md-12"
						style="height: 44px;padding-top: 5px; padding-bottom: 5px;border-bottom: 1px solid #ddd; width:100%;">
						<img alt="" src="${ctx }/static/images/jydcl.png">
						<span style="font: 14px;">订单数据</span>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3"
						style="border-right: 1px solid #ddd;height: 144px;width:25%;">
						<div style="padding: 12px 2px;">超时未发货</div>
						<div style="padding: 12px;text-align: center;">
							<span class="main-text"
								style="font-size: 25px;padding-right: 10px;">${mchtHomeInfo.overtimeCount}</span>单
						</div>
					</div>
					<div class="col-md-3"
						style="border-right: 1px solid #ddd;height: 144px;width:25%;">
						<div style="padding: 12px 2px;">售后待处理</div>
						<div style="padding: 12px;text-align: center;">
							<span class="main-text"
								style="font-size: 25px;padding-right: 10px;">${mchtHomeInfo.serviceCount}</span>单
						</div>
					</div>
					<div class="col-md-3"
						style="border-right: 1px solid #ddd;height: 144px;width:25%;">
						<div style="padding: 12px 2px;">今日订单数</div>
						<div style="padding: 12px;text-align: center;">
							<span class="main-text"
								style="font-size: 25px;padding-right: 10px;">${mchtHomeInfo.todayCount}</span>单
						</div>
					</div>
					<div class="col-md-3"
						style="height: 144px;width:25%;">
						<div style="padding: 12px 2px;">未申诉违规单</div>
						<div style="padding: 12px;text-align: center;">
							<span class="main-text"
								style="font-size: 25px;padding-right: 10px;"><fmt:formatNumber type="number" value="${mchtHomeInfo.violateOrderCount }" maxFractionDigits="0"/></span>单
						</div>
					</div>

				</div>
			</div>
			
			
				<div class="panel panel-default home-content-panner"
				style="padding-bottom: 0;">
				<div class="row">
					<div class="col-md-12"
						style="height: 44px;padding-top: 5px; padding-bottom: 5px;border-bottom: 1px solid #ddd;width:100%;">
						<img alt="" src="${ctx }/static/images/tmdcl.png">
						<span style="font: 14px;">特卖待处理</span>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3"
						style="border-right: 1px solid #ddd;height: 144px;width:25%;">
						<div style="padding: 12px 2px;">待添加商品</div>
						<div style="padding: 12px;text-align: center;">
							<span class="main-text"
								style="font-size: 25px;padding-right: 10px;">${mchtHomeInfo.unaddCount}</span>场
						</div>
					</div>
					<div class="col-md-3"
						style="border-right: 1px solid #ddd;height: 144px;width:25%;">
						<div style="padding: 12px 2px;">待提报</div>
						<div style="padding: 12px;text-align: center;">
							<span class="main-text"
								style="font-size: 25px;padding-right: 10px;">${mchtHomeInfo.unsubmitCount}</span>场
						</div>
					</div>
					<div class="col-md-3"
						style="border-right: 1px solid #ddd;height: 144px;width:25%;">
						<div style="padding: 12px 2px;">审核中</div>
						<div style="padding: 12px;text-align: center;">
							<span class="main-text"
								style="font-size: 25px;padding-right: 10px;">${mchtHomeInfo.auditCount}</span>场
						</div>
					</div>
					<div class="col-md-3"
						style="height: 144px;width:25%;">
						<div style="padding: 12px 2px;">活动中</div>
						<div style="padding: 12px;text-align: center;">
							<span class="main-text"
								style="font-size: 25px;padding-right: 10px;">${mchtHomeInfo.activeCount}</span>场
						</div>
					</div>

				</div>
			</div>
			
			
			
			
			
			
			
		<style>
			.service-x {
				height: auto;
			}
			.service-x>div {
				padding: 10px 0;
			}
			.service-x span {
				margin-right: 3px;
			}
			.service-x img {
				vertical-align: bottom;
				margin-right: 3px;
			}
			.service-x i {
			    font-style: normal;
			}
		</style>
		</div>
		<div class="content-right">
			<div class="panel panel-default home-content-panner service-info-container service-x">
				<div><span>您的服务专员：</span><img src="${ctx }/static/images/qq.png"><c:if test="${empty mchtPlatformContactCustom.qq }">3004409253</c:if><c:if test="${not empty mchtPlatformContactCustom.qq }">${mchtPlatformContactCustom.qq }</c:if></div>
				<div><span>技术支持专员：</span><img src="${ctx }/static/images/qq.png">3004402177</div>
				<div><span>商家服务专线：</span><i class="main-text"><c:if test="${empty mchtPlatformContactCustom.mobile }">13720880491</c:if><c:if test="${not empty mchtPlatformContactCustom.mobile }">${mchtPlatformContactCustom.mobile }</c:if></i></div>
			</div>
			<div class="panel panel-default home-content-panner" style="border-top: 2px solid #2da8d5;">
					<div class="row news-title">
					<div class="col-md-12">
						<span style="font-weight: bold;">平台公告</span>
					</div>
					</div>
					<c:if test="${empty noticeInformationList}">
						<div class="row news-list">
							<div class="col-md-12 ">
								暂无数据
							</div>
							</div>
					</c:if>
					<c:forEach items="${noticeInformationList}" var="information" varStatus="status" >
						<c:if test="${status.index<=4}">
							<div class="row news-list">
							<div class="col-md-12 ">
									<a href="javascript:;" onclick="viewContent(${information.id});">${information.title}
									<c:if test="${empty information.isRead && empty information.tag}">
										<img src="${ctx}/static/images/notRead.png" width="30" style="margin-top: -20px;margin-left: -8px;width: 25px;height: 14px;"/>
									</c:if>
									</a>
							</div>
							</div>
						</c:if>
					</c:forEach>
			</div>
			<div class="panel panel-default home-content-panner" style="border-top: 2px solid #2da8d5;">
					<div class="row news-title">
					<div class="col-md-12">
						<span style="font-weight: bold;">帮助中心</span>
					</div>
					</div>
					<c:if test="${empty informations}">
						<div class="row news-list">
							<div class="col-md-12 ">
								暂无数据
							</div>
							</div>
					</c:if>
					<c:forEach items="${informations}" var="information" varStatus="status" >
						<c:if test="${status.index<=4}">
							<div class="row news-list">
							<div class="col-md-12 ">
								<a href="javascript:;" onclick="viewContent(${information.id});">${information.title}</a>

							</div>
							</div>
						</c:if>
					</c:forEach>
			</div>
			<div class="panel panel-default home-content-panner" style="border-top: 2px solid #2da8d5;">
					<div class="row news-title">
					<div class="col-md-12">
						<span style="font-weight: bold;">营运规则</span>
					</div>
					</div>
					<c:if test="${empty informationList}">
						<div class="row news-list">
							<div class="col-md-12 ">
								暂无数据
							</div>
							</div>
					</c:if>
					<c:forEach items="${informationList}" var="information" varStatus="status" >
						<c:if test="${status.index<=4}">
							<div class="row news-list">
							<div class="col-md-12 ">
								<a href="javascript:;" onclick="viewContent(${information.id});">${information.title}</a>
							</div>
							</div>
						</c:if>
					</c:forEach>
			</div>
		</div>
		</div>
</body>
</html>
