<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"> -->
<title>醒购商城商家后台</title>
<link href="${ctx }/static/css/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap -->
<!-- Font Awesome -->
<link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/dataTables.bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/sweetalert.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/select2.min.css" rel="stylesheet">
<!--     <link href="${pageContext.request.contextPath}/static/css/select2-bootstrap.min.css" rel="stylesheet"> -->
<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath}/static/css/custom.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/jquery-ui.css">
 <link href="${pageContext.request.contextPath}/static/css/waitme.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/css/main-style.css" rel="stylesheet">
<!-- hqp修改部分 -->
<link href="${ctx }/static/css/main-style-hqp.css" rel="stylesheet">

<link rel="shortcut icon" href="${ctx }/static/images/favicon.png"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
</head>

<body id="bodycontainer">
	<div class="container-fluid header-container">
	<div class="header-main">
		<div class="header-main-left"><img src="${ctx}/static/images/LOGO_NEW.png"></div>
		<div class="header-main-right">
			<img style="width: 30px;height: 30px;margin-right:10px;" src="${ctx}/static/images/jsdk.png">
			<span style="color:#999999">欢迎您，</span>
			${mchtUser.userCode} 站内信（<a href="javascript:void(0);" onclick="getContent('${ctx}/platformMsg')"><span class="main-text">${msgCount}</span></a>）<a href="${ctx }/loginOut">[ 退出登录 ]</a>
            <a href="http://www.xgbuy.cc/information/informationManager" target="_blank">[ 醒购规则 ]</a>
		</div>
	</div>
<%-- 			<div class="col-md-4 text-left">
				<img src="${ctx }/static/images/wx_qr.png"><img
					src="${ctx }/static/images/zfb_qr.png">
			</div> --%>
	</div>

	<div class="container body-container">
		<div class="content-left">
			<div class="panel panel-default">
				<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
					<div class="menu_section">
						<ul class="nav side-menu">
							<c:if test="${empty mchtInfoStatus || mchtInfoStatus !=0 }">
							<li  onclick="getContent('${ctx}/home')"><a><i class="fa fa-home"></i><span style="padding-left:10px;">首页</span></a></li>
							</c:if>

							<c:forEach var="menu" items="${menus}">
								
							 <c:if test="${fun:length(menu.subMenus) > 0}"> 
							 <c:if test="${menu.menuName != '商城店铺'}">
							 	<c:if test="${menu.menuName == '品牌活动报名' || menu.menuName == '单品活动报名'}">
							 		<c:if test="${activityAuthStatus eq '1'}">
									 	<li><a><i class="${menu.menuIcon}"></i><span style="padding-left:10px;">${menu.menuName}</span><span class="fa fa-angle-down fa-8x"></span></a>
										 <ul class="nav child_menu">
											<c:forEach var="subMenu" items="${menu.subMenus}">
											<li onclick="getContent('${ctx}${subMenu.menuPath}')"><a>${subMenu.menuName }</a></li>
											</c:forEach>
										</ul>
										</li>
							 		</c:if>
							 	</c:if>
							 	<c:if test="${menu.menuName != '品牌活动报名' && menu.menuName != '单品活动报名'}">
									<c:if test="${menu.menuName == '商家管理'}">
							 		<li><a><i class="${menu.menuIcon}"></i><span style="padding-left:10px;">${menu.menuName}</span><span class="fa fa-angle-down fa-8x"></span></a>
										 <ul class="nav child_menu">
											<c:forEach var="subMenu" items="${menu.subMenus}">
												<c:if test="${subMenu.menuName == '关店申请'}">
													<c:if test="${allowMchtApplyClose eq '1'}">
														<li onclick="getContent('${ctx}${subMenu.menuPath}')"><a>${subMenu.menuName }</a></li>
													</c:if>
												</c:if>
												<c:if test="${subMenu.menuName != '关店申请'}">
													<li onclick="getContent('${ctx}${subMenu.menuPath}')"><a>${subMenu.menuName }</a></li>
												</c:if>
											</c:forEach>
										</ul>
									</li>
							 		</c:if>
							 		<c:if test="${menu.menuName != '商家管理'}">
							 			<c:if test="${menu.menuName == '商品管理'}">
								 		<li><a><i class="${menu.menuIcon}"></i><span style="padding-left:10px;">${menu.menuName}</span><span class="fa fa-angle-down fa-8x"></span></a>
										 <ul class="nav child_menu">
											<c:forEach var="subMenu" items="${menu.subMenus}">
												<c:if test="${subMenu.menuName == '供应商商品池'}">
													<c:if test="${not empty supplyChainStatus and supplyChainStatus eq 1}">
														<li onclick="getContent('${ctx}${subMenu.menuPath}')"><a>${subMenu.menuName }</a></li>
													</c:if>
												</c:if>
												<c:if test="${subMenu.menuName != '供应商商品池'}">
													<li onclick="getContent('${ctx}${subMenu.menuPath}')"><a>${subMenu.menuName }</a></li>
												</c:if>
											</c:forEach>
										</ul>
										</li>
										</c:if>
							 			<c:if test="${menu.menuName != '商品管理'}">
								 		<li><a><i class="${menu.menuIcon}"></i><span style="padding-left:10px;">${menu.menuName}</span><span class="fa fa-angle-down fa-8x"></span></a>
										 <ul class="nav child_menu">
											<c:forEach var="subMenu" items="${menu.subMenus}">
											<li onclick="getContent('${ctx}${subMenu.menuPath}')"><a>${subMenu.menuName }</a></li>
											</c:forEach>
										</ul>
										</li>
										</c:if>
							 		</c:if>
								</c:if>
							 </c:if>
							 <c:if test="${menu.menuName == '商城店铺'}">
							 	<c:if test="${shopStatus eq '1'}">
							 		<li><a><i class="${menu.menuIcon}"></i><span style="padding-left:10px;">${menu.menuName}</span><span class="fa fa-angle-down fa-8x"></span></a>
										 <ul class="nav child_menu">
											<c:forEach var="subMenu" items="${menu.subMenus}">
												<c:if test="${sessionScope.mchtInfo.isManageSelf == '1' or sessionScope.mchtInfo.mchtType != '1' or subMenu.menuName != '优惠管理'}">
													<li onclick="getContent('${ctx}${subMenu.menuPath}')"><a>${subMenu.menuName }</a></li>
												</c:if>
											</c:forEach>
										</ul>
									</li>
							 	</c:if>
							 </c:if>
							 </c:if>
							 <c:if test="${fun:length(menu.subMenus) <= 0}"> 
							 <li onclick="getContent('${ctx}${menu.menuPath}')" ><a><i class="${menu.menuIcon}"></i><span style="padding-left:10px;">${menu.menuName}</span></a>
							</li>
							</c:if>
							</c:forEach>
						</ul>
					</div>

				</div>
			</div>
			<div class="panel panel-default">
				<div class="row menu-slide-bottom ewm">
					<div class="col-md-12">
						<div>
							<img src="${pageContext.request.contextPath}/static/images/app.png">
							<p>IOS下载</p>
						</div>
						<div>
							<img src="${pageContext.request.contextPath}/static/images/app.png">
							<p>安卓下载</p>
						</div>
					</div>
				</div>
			</div>
		</div>
<!-- 		<iframe id="main-content-if" width=950 height=900 frameborder="no" border="0" marginwidth="0" marginheight="0"  allowtransparency="yes"> -->
		
<!-- 		</iframe> -->
		<div class="main-content" id="main-content">
		</div>
	<div class="container-fluid footer-container"></div>
</div>
<!-- <div style="width: 100%;text-align: center;top:50%;position: absolute;"> -->
<!--  <div class="progress" style="width: 280px;display: inline-block;margin-bottom: 0px;margin-left:270px;"> -->
<!--   <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 45%"> -->
<!--     <span class="sr-only">45% Complete</span> -->
<!--   </div> -->
<!-- </div> -->
<!-- </div> -->

	<style>
		.page-footer {
			height: 260px;
			padding-top: 35px;
			background: #222;
		}

		.page-footer.flex {
			display: -webkit-flex;
			display: flex;
			box-sizing: border-box;
		}
		.page-footer.dc {
			-webkit-flex-direction: column;
			flex-direction: column;
		}
		.page-footer.jc {
			-webkit-justify-content: center;
			justify-content: center;
		}
		.page-footer.ac {
			-webkit-align-items: center;
			align-items: center;
		}
		
		.page-footer h4,
		.page-footer p {
			margin: 0;
		}
		.page-footer h4 {
			line-height: 120px;
			font-size: 36px;
			font-weight: bold;
			color: #ddd;
		}
		.page-footer p {
			line-height: 34px;
			font-size: 14px;
			color: #999;
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
	<div class="page-footer flex ac jc dc">
		<h4 style="line-height: 90px;">唤醒潮流  开心购物</h4>
		<p>公司名称：厦门聚买网络科技有限公司 <span>联系地址：厦门市思明区塔埔东路171号1104-A单元</span></p>
<%-- 		<p>
			<span style="margin-right: 10px;">福建省网络食品销售第三方平台提供者备案：<a style="color:#999;" target="_blank" href="${ctx}/info/content?id=245" >闽网食A35020010</a></span>
			<span>出版物网络交易平台服务经营备案证：<a style="color:#999;" target="_blank" href="${ctx}/info/content?id=223" >新出发闽备字第19001号</a></span>
		</p>
		
		<p>
			 <span style="margin-right: 10px;">增值电信业务经营许可证：<a style="color:#999;" target="_blank" href="${ctx}/info/content?id=179" >闽B2-20180244</a></span>
			<span>互联网药品信息服务资质证书编号:<a style="color:#999;" target="_blank" href="${ctx}/info/content?id=262" >(闽)-经营性-2019-0016</a></span>
		</p> --%>
		<p>
			<span style="margin-right: 10px;">福建省网络食品销售第三方平台提供者备案：<a style="color:#999;" href="http://www.xgbuy.cc/information/informationManager?typeId=3" target="_blank" >闽网食A35020010</a></span>
			<span>出版物网络交易平台服务经营备案证：<a style="color:#999;" href="http://www.xgbuy.cc/information/informationManager?typeId=4" target="_blank" >新出发闽备字第19001号</a></span>
		</p>
		
		<p>
			<span style="margin-right: 10px;">增值电信业务经营许可证：<a style="color:#999;" href="http://www.xgbuy.cc/information/informationManager?typeId=2"  target="_blank">闽B2-20180244</a></span>
			<span>互联网药品信息服务资质证书编号:<a style="color:#999;" href="http://www.xgbuy.cc/information/informationManager?typeId=5"  target="_blank">(闽)-经营性-2019-0016</a></span>
		</p>
		
		<p>
			<%-- <span style="margin-right: 10px;">增值电信业务经营许可证：<a style="color:#999;" target="_blank" href="${ctx}/info/content?id=179" >闽B2-20180244</a></span> --%>
			Copyright © 2016 - 2019 JuMai. All Rights Reseved 
			<span>闽ICP备17003368号-3</span> 
		</p>
	</div>

	<style>
		.pop-notice {
			display: none;
			position: fixed;
			top: 0;
			left: 0;
			z-index: 1111;
			width: 100%;
			height: 100%;
		}
		.pop-notice-con {
			position: absolute;
			top: 50%;
			left: 50%;
			width: 665px;
			margin: -330.5px 0 0 -332.5px;
			padding: 0 0 40px;
			background: #fff;
			border: 1px solid #ddd;
			border-radius: 2px;
			box-shadow: 0 0 10px #ccc;
			font-size: 14px;
		}
		.pop-notice-article {
			height: 360px;
			overflow: auto;
			padding: 0 27px;
			color: #333;
			line-height: 20px;
		}
		.pop-notice-article h2 {
			line-height: 34px;
			margin-bottom: 15px;
			font-size: 18px;
			font-weight: bold;
			color: #333;
			text-align: center;
		}
		.pop-notice-article p {
			position: relative;
			padding-left: 22px;
			line-height: 30px;
			word-spacing: 3px;
		}
		.pop-notice-article p:before {
			position: absolute;
			top: 0;
			left: 0;
			width: 22px;
			content: attr(data-order);
		}
		.pop-notice-header {
			position: relative;
			height: 30px;
		}
		.pop-notice-header a {
			position: absolute;
			top: 0;
			right: 0;
			width: 30px;
			height: 30px;
			background: url("${pageContext.request.contextPath}/static/images/pop-notice-close.png") no-repeat center center;
		}
		.pop-notice-footer a {
			display: block;
			width: 90px;
			height: 32px;
			line-height: 32px;
			margin: 0 auto;
			background: #3c9eff;
			color: #fff;
			text-align: center;
			cursor: pointer;
		}
		.pop-notice .indent {
			text-indent: 2em;
		}
		.pop-notice-footer button {
			display: block;
			width: 100px;
			height: 32px;
			line-height: 32px;
			margin: 0 auto;
			background: grey;
			color: #fff;
			text-align: center;
			cursor: pointer;
			border:none;
		}
	</style>
	<div class="pop-notice">
		<div class="pop-notice-con" style="width: 670px;">
			<div class="pop-notice-article" style="height: 500px;word-break: break-all;" id="xgNotice">
				<!-- 
				<h2>醒购商城公告</h2>
				<div>
					<p>尊敬的醒购商家： </p>
					<p data-order="" class="indent">临近双十一，平台了解各商家在双十一期间的发货压力。但是为了平台和商家的众多客户能</p>
					<p data-order="" class="indent">够及时收到快递，在此通知商家们具体的发货时限。</p>
					<p data-order="" class="indent">双十一11.10-11.12号期间的订单，应承诺在消费者下单之后的96小时内完成发货！</p>
					<p data-order="" class="indent">希望各个商家能解决双十一期间的发货压力，按平台要求在相应规定的时间内进行发货。</p>
					<p data-order="" class="indent">商家小能客服在线时间要求：</p>
					<p data-order="" class="indent">11月1日-11月8日   早8点半至晚24点</p>
					<p data-order="" class="indent">11月9日-11月12日  早8点至次日凌晨两点</p>
					<p style="text-align: right;">醒购商城</p>
					<p style="text-align: right;">2018年11月8日</p>
				</div>
				 -->
			</div>
			<div class="pop-notice-footer">
			<button id="isReadBtn" onclick="popNoticeClose()" disabled="disabled"></button>
			<input type="hidden" id="index" value="0">
		</div>
	</div>
	</div>
	
	<div class="video_box" style="position:fixed; width:430px; height:200px; top:35%; left:45%; display: none;" id="licenseMustDiv">
	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title" style="text-align: center;">
					提醒
				</h3>
			</div>
			<div>
				<p data-order="" class="indent">根据电商法相关规定，您经营的品牌需要相应的经营许可证（或生产许可证）。</p>
				<p data-order="" class="indent">您当前店铺未完善相应的资质。</p>
				<p style="text-align: center;"><a href="javascript:;" onclick="toMchtLicenseChg(${mchtId});">去完善>></a></p>
			</div>
		 </div>
	</div>
	
	<!--弹出div End-->
	<div class="black_box" style="display: none;"></div>
	
	<!-- 	查看信息框 -->
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>

	<!-- 	店铺总负责人信息完善框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  data-backdrop="static"> 
	<div class="modal-dialog">
		<div class="modal-content"  style="text-align:center;">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<font size="3px;">提醒</font>
				</h4>
			</div>
			<div class="modal-body">
				<c:if test="${contact.auditStatus eq '' || contact.id == null}">
				您的店铺负责人信息未完善，请尽快完善店铺负责人信息
				</c:if>
				<c:if test="${contact.auditStatus eq '2'}">
				您店铺总负责人信息审核被驳回，请根据驳回的原因修改并重新提交。
				</c:if>		
				<br>
				<br>
				<a href="javascript:showInfo('${ctx}/mchtContact/mchtContactEdit?contactType=1&id=${contact.id}&perfectType=1')"><font size="3px;">去完善>></font></a>
			</div>
		</div>
	</div>
	</div>
	
	<!-- 	合同续签到期提醒框 -->
	<div class="modal fade" id="unexpiredModal" tabindex="-1" role="dialog" aria-labelledby="unexpiredModalLabel" aria-hidden="true"  data-backdrop="static"> 
	<div class="modal-dialog">
		<div class="modal-content"  style="text-align:center;">
			<div class="modal-header">
			<c:if test="${unexpiredTAG eq 0}">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
			</c:if>	
				<h4 class="modal-title" id="myModalLabel">
					<font size="3px;">合同续签提醒</font>
				</h4>
			</div>
			<div class="modal-body">
				<c:if test="${unexpiredTAG eq 0}">
				您的合同将于${endDate}日到期，到期后将会影响店铺的运营，请您尽快的完成续签。
				</c:if>
				<c:if test="${unexpiredTAG eq 1}">
				您的合同已于${endDate}到期，请您确认是否续签，否则平台将根据相关的规则进行暂停
				</c:if>		
				<br>
				<br>
				<div>（备注：关闭窗口后，可在栏目【商家管理-合同续签】申请续签）</div>
				<br>
				<br>
				<button type="button" class="btn btn-info" id="renew" onclick="renew(${mchtContractId},2);">续签申请</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-info" id="notRenew" onclick="notRenew();">不再续签</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<c:if test="${unexpiredTAG eq 0}">
				<span><a href="javascript:void(0);" onclick="nextRemind();" >下次提醒</a></span>
				</c:if>
			</div>
		</div>
	</div>
	</div>
	
	<!--合同续签提交提醒框 -->
	<div class="modal fade" id="remind" tabindex="-1" role="dialog" aria-labelledby="remindLabel" aria-hidden="true"  data-backdrop="static"> 
	<div class="modal-dialog">
		<div class="modal-content"  style="text-align:center;">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<font size="3px;">提醒</font>
				</h4>
			</div>
			<div class="modal-body">
				您已提交申请，后续可在【商家管理-合同续签】栏目查看申请进度
				<br>
				<br>
				<button type="button" class="btn btn-info" onclick="remindClose();">关闭</button>
			</div>
		</div>
	</div>
	</div>
	
	<!--不续签弹框 -->
	<div class="modal fade" id="notRenewModal" tabindex="-1" role="dialog" aria-labelledby="notRenewModalLabel" aria-hidden="true"  data-backdrop="static"> 
	<div class="modal-dialog">
		<div class="modal-content"  style="text-align:center;">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<font size="3px;">提交原因</font>
				</h4>
			</div>
			<div class="modal-body">
				非常遗憾！不能再与贵司进行合作，请您能提交不续签原因，才能确认不续签。
				<br>
				<br>
				<div><textarea rows="5" id="mchtNotRenewRemarks" style="resize:none;outline:none;width:80%" placeholder="填写不续签原因" maxlength="64"></textarea></div>
				<br>
				<button type="button" class="btn btn-info" onclick="renew(${mchtContractId},1);">确认不续签</button>
			</div>
		</div>
	</div>
	</div>
	
	<!-- 	合同续签申请驳回框 -->
	<div class="modal fade" id="renewRejectModal" tabindex="-1" role="dialog" aria-labelledby="renewRejectModalLabel" aria-hidden="true"  data-backdrop="static"> 
	<div class="modal-dialog">
		<div class="modal-content"  style="text-align:center;">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<font size="3px;">合同续签申请驳回提醒</font>
				</h4>
			</div>
			<div class="modal-body">
				驳回原因：${zsNotRenewRemarks}
				<br>
				<br>
				（备注：关闭窗口后，可在栏目【商家管理-合同续签】查看驳原因
				<br>
				<br>
				<span><a href="javascript:void(0);" onclick="rejectNeverRemind();" >下次提醒</a></span>
			</div>
		</div>
	</div>
	</div>
	
	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-progressbar.min.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/custom.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery-ui.js"></script>

	<script src="${pageContext.request.contextPath}/static/js/moment.min.js"></script>
	
	<script src="${pageContext.request.contextPath}/static/js/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/select2.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/select2_zh-CN.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/waitme.js"></script>

   <script type="text/javascript">
   var ctx="${ctx}";
	$(function(){
		if(${not empty unexpiredTAG && unexpiredTAG ne 2 && sessionScope.neverRemind eq 0}){
			$("#unexpiredModal").modal('show');
		};
		
		<c:if test="${empty mchtInfoStatus || mchtInfoStatus !=0 }">
		getContent("${ctx}/home");
		</c:if>
	
		<c:if test="${not empty mchtInfoStatus && mchtInfoStatus ==0 }">
		getContent("${ctx}/mcht/contract");
		</c:if>
		var hasNotice = ${hasNotice};
		if(hasNotice){
			var contentArray = ${contentArray};
			$("#xgNotice").html("<h2>醒购商城公告</h2>"+contentArray[0]);
		}
		
		var licenseIsMust = "${licenseIsMust}";
		var hasBusinessLicensePic = "${hasBusinessLicensePic}";
		if(licenseIsMust == "1"){
			if(hasBusinessLicensePic == "0"){
				var showLicenseMust = "${showLicenseMust}";
				if(showLicenseMust == "0"){
					$("#licenseMustDiv").show();
					$(".black_box").show();
				}
			}
		}
		
		$(".video_close").on('click',function(){
			$("#licenseMustDiv").hide();
			$(".black_box").hide();
		});
	});
	
		//确认续签操作
		function renew(mchtContractId,status){
			var url = "${ctx}/mcht/contract/renew?mchtContractId="+mchtContractId+"&status="+status;
			var mchtNotRenewRemarks = $('#mchtNotRenewRemarks').val();
			//如果是不再续签，把不续签原因带上
			if(status == '1' && mchtNotRenewRemarks == ""){
				swal('不续签原因不可为空');
				return;
			}else if(status == '1' && mchtNotRenewRemarks != ""){
				url = "${ctx}/mcht/contract/renew?mchtContractId="+mchtContractId+"&status="+status+"&mchtNotRenewRemarks="+mchtNotRenewRemarks;	
			}	
			$.ajax({
		        url: url,
		        type: "GET", 
		        async: false, 
		        success: function(data){
		        	if(data.returnCode == '0000' && status == '2'){
		        		$("#unexpiredModal").hide();
		        		$('.modal-backdrop').remove();//去掉遮罩层
		        		$("#remind").modal();
		        	}else if(data.returnCode == '0000' && status == '1'){        		
		        		$("#notRenewModal").hide();
		        		$('.modal-backdrop').remove();//去掉遮罩层
		        	}
		        },
			    error:function(){
			    	swal('页面不存在');
			    }
			});
		};
		
		//关闭
		function remindClose(){
			$('#remind').hide();
			$('.modal-backdrop').remove();//去掉遮罩层
		}
		
		//不续签操作
		function notRenew(){
			$("#unexpiredModal").hide();
			$('.modal-backdrop').remove();//去掉遮罩层
			$("#notRenewModal").modal();
		}

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
// 		$("#main-content-if").attr("src",url);

	}

	//错误消息提示框
   function swalError(msg) {
	   swal({
		   title: msg,
		   type: "error",
		   confirmButtonText: "确定"
	   });
   }

	//成功消息提示框
   function swalSuccess(msg) {
	   swal({
		   title: msg,
		   type: "success",
		   confirmButtonText: "确定"
	   });
   }
	
	function show_waitMe(){
		$('#bodycontainer').waitMe({
			effect: 'roundBounce',
			text: '正在处理，请稍后......',
			bg: 'rgba(0,0,0,0.5)',
			color:'#ffffff',
			sizeW:'',
			sizeH:''
		});
		 $('.waitMe').css({   
			  height:  $(document).height()
			}); 
		 $('.waitMe_content').css({   
			  top:  $(document).scrollTop()+$(window).height()/2
			}); 
		 $('body').addClass('stop-scrolling');
	}
	function hide_waitMe(){
		$('body').removeClass('stop-scrolling');
		$('#bodycontainer').waitMe('hide');
	}

		function popNoticeClose() {
			var index = $("#index").val();
			var arrayIds = ${idArray};
			var tag = true;
            $.ajax({
                url: "${ctx}/info/insert/"+arrayIds[index],
                type: "GET",
                success: function(data){
                	if(data.returnCode == '4004'){
						swal('请求失败');
						tag = false;
					}
                },
                error:function(){
                    swal('页面不存在');
                }
            });
			if(!tag){
				return;
			}
			index++;
			$("#index").val(index);
			var contentArray = ${contentArray};
			if(contentArray[index]){
				time = 15;
				$("#xgNotice").html("<h2>醒购商城公告</h2>"+contentArray[index]);
				$('#isReadBtn').attr("disabled",true);
				$('#isReadBtn').css("background","grey");
				change();
			}else{
				$('.pop-notice').fadeOut();
				getContent('${ctx}/home');
				//当总店铺负责人信息处于未提交或店铺负责人没有数据
				if(${(contact.auditStatus eq "" || contact.id == null) && mchtInfoTotalStatus eq 2}){
					$("#myModal").modal();
				}
				//当总店铺负责人信息审核被驳回
				if(${contact.auditStatus eq "2"}){
					$("#myModal").modal();
				}
				//续签合同操作
				if(${not empty unexpiredTAG && unexpiredTAG ne 2 && sessionScope.neverRemind eq 0}){
					$("#unexpiredModal").modal('show');
				}
				//续签驳回原因提醒
				if(${unexpiredTAG eq 2 && sessionScope.rejectNeverRemind eq 0}){
					$("#renewRejectModal").modal();
				}
			}
		}
		
		$('#myModal a').click(function(){
			$('#myModal').hide();
			$('.modal-backdrop').remove();//去掉遮罩层
		});

		function showInfo(url){
			if(url == "")	return;
			$.ajax({
				url: url, 
				type: "GET",
				success: function(data){
					$("#viewModal").html(data);
					$("#viewModal").modal();
				},
				error:function(){
					swal('页面不存在');
				}
			});
		}

		var time = 15;
		function change(){
			if(time != -1){
				$('#isReadBtn').text("已读（"+time+"S）");
				time--;
				setTimeout(function(){
					change();
				},1000);
			}else{
				$('#isReadBtn').text("已读");
				$('#isReadBtn').attr("disabled",false);
				$('#isReadBtn').css("background","#3c9eff");
				return false;
			}
		}
			var hasNotice = ${hasNotice};
			if(hasNotice){
				$('.pop-notice').fadeIn();
				change();
			}else{
				<c:if test="${mchtInfoStatus ne 0}">
				//当总店铺负责人信息处于未提交或店铺负责人没有数据并且总审通过
				if(${(contact.auditStatus eq "" || contact.id == null) && mchtInfoTotalStatus eq 2}){
					$("#myModal").modal();
				}
				//当总店铺负责人信息审核被驳回
				if(${contact.auditStatus eq "2"}){
					$("#myModal").modal();
				}
				</c:if>
				//续签合同操作
				if(${not empty unexpiredTAG && unexpiredTAG ne 2 && sessionScope.neverRemind eq 0}){
					$("#unexpiredModal").modal('show');
				}
				//续签驳回原因提醒
				if(${unexpiredTAG eq 2 && sessionScope.rejectNeverRemind eq 0}){
					$("#renewRejectModal").modal('show');
				}
			}
				
		function toSeeViolate(id) {
			$('.pop-notice').fadeOut();
	        var url = "${ctx}/info/content?id=" + id;
	        getContent(url);
	    }
		
		function toMchtLicenseChg(mchtId){
			$("#licenseMustDiv").hide();
			$(".black_box").hide();
			$.ajax({
		        url: "${ctx}/mchtUser/toMchtLicenseChg?mchtId="+${mchtId}+"&source=1", 
		        type: "GET", 
		        success: function(data){
		            $("#viewModal").html(data);
		            $("#viewModal").modal();
		        },
			    error:function(){
			    	swal('页面不存在');
			    }
			});
		}
		
		//下次提醒
		function nextRemind(){
			if(${sessionScope.neverRemind eq 0}){
				$("#unexpiredModal").modal('hide');
				$.ajax({
			        url: "${ctx}/mcht/contract/neverRemind", 
			        type: "GET", 
			        success: function(data){
			        },
				    error:function(){
				    	swal('页面不存在');
				    }
				});
			}
		};
		
		//驳回不再提醒
		function rejectNeverRemind(){
			if(${sessionScope.rejectNeverRemind eq 0}){
				$("#renewRejectModal").modal('hide');
				$.ajax({
			        url: "${ctx}/mcht/contract/rejectNeverRemind", 
			        type: "GET", 
			        success: function(data){
			        },
				    error:function(){
				    	swal('页面不存在');
				    }
				});
			}
		};
	</script>
</body>
</html>