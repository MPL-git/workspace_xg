<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/ligerTreeUtil.js" type="text/javascript"></script>
<script type="text/javascript"> 
	var $tree = null;
	<c:if test="${goodsMerchandise}">
	function loadTree() {
		var root = [{id: 1009, text: '商品审核'},
		            {id: 1001, text: '管理商品'},
		            {id: 1002, text: '广告管理'},
					{id: 12, text: '主题馆装修'},
					{id: 1003, text: '类目管理'},
					{id: 1101, text: '数据统计（在线商品）'},
					{id: 1102, text: '数据统计（类目）'},
					{id: 1103, text: '数据统计（小时数据）'},
					{id: 1104, text: '数据统计（日数据）'},
		           ];
		$tree.setData(root);
	}
	</c:if>
	
	<c:if test="${goodsStore}">
	function loadTree() {
		var root = [{id: 1070, text: '店铺审核'},
		            {id: 1000, text: '店铺管理'},
		            {id: 1004, text: '广告管理'},
					{id: 13, text: '主题馆装修'},
					{id: 1005, text: '类目管理'},
					{id: 1201, text: '数据统计（在线店铺）'},
					{id: 1202, text: '数据统计（类目）'},
					{id: 1203, text: '数据统计（小时数据）'},
					{id: 1204, text: '数据统计（日数据）'},
		           ];
		$tree.setData(root);
	}
	</c:if>
	
	
	<c:if test="${laXinRunGoodQuality}">
	function loadTree() {
		var root = [{id: 1006, text: '管理商品'},
		            {id: 1007, text: '广告管理'},
					{id: 1008, text: '类目管理'},
					{id: 1301, text: '数据统计（在线商品）'},
		           ];
		$tree.setData(root);
	}
	</c:if>
	
	//潮鞋上新
	<c:if test="${upTideShoes}">
	function loadTree() {
		var root = [{id: 1010, text: '商品审核'},
		            {id: 1011, text: '管理商品'},
		            {id: 1012, text: '广告管理'},
					{id: 14, text: '主题馆装修'},
					{id: 1401, text: '数据统计（在线商品）'},
		           ];
		$tree.setData(root);
	}
	</c:if>
	
	//潮流男装
	<c:if test="${fashionMenswear}">
	function loadTree() {
		var root = [{id: 1020, text: '商品审核'},
		            {id: 1021, text: '管理商品'},
		            {id: 1022, text: '广告管理'},
					{id: 15, text: '主题馆装修'},
					{id: 1501, text: '数据统计（在线商品）'},
		           ];
		$tree.setData(root);
	}
	</c:if>
	
	//断码特惠
	<c:if test="${breakingPreference}">
	function loadTree() {
		var root = [{id: 1030, text: '商品审核'},
		            {id: 1031, text: '管理商品'},
		            {id: 1032, text: '广告管理'},
					{id: 16, text: '主题馆装修'},
					{id: 1033, text: '类目管理'},
					{id: 1601, text: '数据统计（在线商品）'},
		           ];
		$tree.setData(root);
	}
	</c:if>
	
	//运动鞋服
	<c:if test="${sportswear}">
	function loadTree() {
		var root = [{id: 1040, text: '商品审核'},
		            {id: 1041, text: '管理商品'},
		            {id: 1042, text: '广告管理'},
					{id: 17, text: '主题馆装修'},
					{id: 1701, text: '数据统计（在线商品）'},
		           ];
		$tree.setData(root);
	}
	</c:if>
	
	//潮流美妆
	<c:if test="${fashionCosmetics}">
	function loadTree() {
		var root = [{id: 1050, text: '商品审核'},
		            {id: 1051, text: '管理商品'},
		            {id: 1052, text: '广告管理'},
					{id: 18, text: '主题馆装修'},
					{id: 1801, text: '数据统计（在线商品）'},
		           ];
		$tree.setData(root);
	}
	</c:if>
	
	//食品超市
	<c:if test="${foodSupermarket}">
	function loadTree() {
		var root = [{id: 1060, text: '商品审核'},
		            {id: 1061, text: '管理商品'},
		            {id: 1062, text: '广告管理'},
					{id: 19, text: '主题馆装修'},
					{id: 1901, text: '数据统计（在线商品）'},
		           ];
		$tree.setData(root);
	}
	</c:if>
	
	//大学生创业
	<c:if test="${studentEntrepreneurship}">
	function loadTree() {
		var root = [{id: 1080, text: '店铺审核'},
		            {id: 1081, text: '店铺管理'},
		            {id: 1082, text: '广告管理'},
					{id: 22, text: '主题馆装修'},
					{id: 2001, text: '数据统计（在线店铺）'},
		           ];
		$tree.setData(root);
	}
	</c:if>

	//领劵中心
	<c:if test="${bringStockCenter}">
	function loadTree() {
		var root = [{id: 1090, text: '时间设置'},
		            {id: 1091, text: '查看优惠券(平台劵)'},
		            {id: 1092, text: '查看优惠券(商品劵)'},
		            {id: 1093, text: '查看优惠券(店铺劵)'},
		            {id: 1094, text: '查看优惠券(特卖劵)'},
		            {id: 1095, text: '广告管理'},
		            {id: 24, text: '主题馆装修'},
		            {id: 1096, text: '类目管理'},
					{id: 1097, text: 'Tab管理'},
		           ];
		$tree.setData(root);
	}
	</c:if>

	//爆款推荐
	<c:if test="${recommendedModels}">
	function loadTree() {
		var root = [
			{id: 1071, text: '管理商品'},
			{id: 1102, text: '广告管理'},
			// {id: 25, text: '主题馆装修'},
			{id: 1106, text: '类目管理'}
		];
		$tree.setData(root);
	}
	</c:if>

	//积分抽奖
	<c:if test="${integralLottery}">
	function loadTree() {
		var root = [
			{id: 10000, text: '商品审核'},
			{id: 10001, text: '商品池'},
			{id: 10002, text: '商品劵'},
			{id: 10003, text: '抽奖管理'},
			{id: 10004, text: '抽奖记录'}
		];
		$tree.setData(root);
	}
	</c:if>

  	$(function() { 
		$("#layout1").ligerLayout({leftWidth: 200, minLeftWidth: 200});
		$tree = $("#menuTree").ligerTree({
			checkbox: false,
            slide: false,
            nodeWidth: 150,
            attribute: ['nodename', 'url'],
            isExpand: false,
            needCancel: false,  //不能取消选择
            onSelect: function(node) {
            	if(node.data.id != 0) {
            		if(node.data.id == 1001 || node.data.id == 1011 || node.data.id == 1021 || node.data.id == 1031 || node.data.id == 1041 || node.data.id == 1051 || node.data.id == 1061  ){
            			$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/sourceNiche.shtml?pagetype="+ node.data.id);
            		}else if (node.data.id == 1002 || node.data.id == 1004 || node.data.id == 1007 || node.data.id == 1012 || node.data.id == 1022 || node.data.id == 1032 || node.data.id == 1042 || node.data.id == 1052 || node.data.id == 1062 || node.data.id == 1082 || node.data.id == 1095 || node.data.id == 1102) {
            			$("#frm").attr("src", "${pageContext.request.contextPath}/appMng/adMng/list.shtml?mngPos=" + node.data.id);
					}else if (node.data.id == 12 || node.data.id == 13  || node.data.id == 14 || node.data.id == 15 || node.data.id == 16 || node.data.id == 17 || node.data.id == 18 || node.data.id == 19 || node.data.id == 22 || node.data.id == 24 || node.data.id == 25) {
						var pageType = node.data.id;
						var status='1';
						$("#frm").attr("src", "${pageContext.request.contextPath}/customAdPage/keyPageManager.shtml?pageType=" + pageType+"&status="+status);
					}else if (node.data.id == 1003 || node.data.id == 1005 || node.data.id == 1008 || node.data.id == 1033 || node.data.id == 1096 || node.data.id == 1106) {
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/categoryAdministration.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1000 || node.data.id == 1081) {//店铺管理
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/sourceNicheShop.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1006) {
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/laXinRunGoodQuality.shtml");
					}else if (node.data.id == 1009 || node.data.id == 1010  || node.data.id == 1020 || node.data.id == 1030 || node.data.id == 1040 || node.data.id == 1050 || node.data.id == 1060 ) {
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/commodityAudit.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1070 || node.data.id == 1080  ){//店铺审核
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/shopExamine.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1070 || node.data.id == 1080  ){//店铺审核
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/shopExamine.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1090  ){//时间设置
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/couponCenterTime.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1091 || node.data.id == 1092 || node.data.id == 1093 || node.data.id == 1094 ){
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/checkOutCoupons.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1097){
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/tabAdministration.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1101 || node.data.id == 1301 || node.data.id == 1401 || node.data.id == 1501 || node.data.id == 1601 || node.data.id == 1701 || node.data.id == 1801 || node.data.id == 1901){//商品(在线商品)
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/onlineProductDataStatistics.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1102  || node.data.id == 1202  ){//商品和店铺(类目)
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/categoryProductDataStatistics.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1103 || node.data.id == 1203 ){//商品和店铺(小时)
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/hourProductDataStatistics.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1104 ||  node.data.id == 1204  ){//商品和店铺(每日)
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/dayProductDataStatistics.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1201  || node.data.id == 2001){//店铺(在线店铺)
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/onlineShopDataStatistics.shtml?pagetype="+ node.data.id);
					}else if (node.data.id == 1071){
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/sourceNiche12.shtml?pagetype="+ node.data.id + "&auditStatus=1");
					}else if (node.data.id >= 10000 && node.data.id <= 10100){
						$("#frm").attr("src", "${pageContext.request.contextPath}/resourceLocationManagement/integralLotteryRoute.shtml?pagetype="+ node.data.id);
					}
            	}
            }
		}); 
		loadTree();  //加载数据 
		var height = $(".l-layout-center").height();
		$("#treeContent").css({height: height});
	}); 
    	
    	
</script>
  </head>
    <body style="padding:10px; margin:0;">
    <div class="l-loading" style="display: none;" id="pageloading"></div>
    <div id="layout1">
  	  <div position="left" title="栏目树">
  	    <div id="toptoolbar"></div>
  	   	<div id="treeContent" style="height: 100%; overflow: scroll;">
  	    <ul id="menuTree" style="margin-top:3px;"></ul>
  	    </div>
  	  </div>
  	  <div position="center">
  	  	<iframe id="frm" frameborder="0" name="home" id="home" src="about:blank" style="width: 100%; height: 100%;" scrolling="no"></iframe>
  	  </div>
  	</div> 
  </body>
</html>
