<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 <style type="text/css">
 	.tab-1{
		width: 100%;
		height: 57px;
		background: inherit;
	    background-color: inherit;
		background-color: rgba(242, 242, 242, 1);
		box-sizing: border-box;
		border-width: 1px;
		border-style: solid;
		border-color: rgba(204, 204, 204, 1);
		border-radius: 0px;
		-moz-box-shadow: none;
		-webkit-box-shadow: none;
		box-shadow: none;
		line-height: 16px;
 	}
 	.tab-1 td:last-child{
 		width: 70%;
 	}
 	.tab-1 td:nth-child(1), .tab-1 td:nth-child(2), .tab-1 td:nth-child(3){
 		width: 10%;
 		text-align: center; 
		background: inherit;
	    background-color: inherit;
		box-sizing: border-box;
		border-width: 1px;
		border-style: solid;
		border-color: rgba(204, 204, 204, 1);
		border-radius: 0px;
		-moz-box-shadow: none;
		-webkit-box-shadow: none;
		box-shadow: none;
		font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
		font-weight: 700;
		font-style: normal;
		font-size: 20px;
		color: #0099FF;
		line-height: 16px;
		cursor: pointer;
 	}
 	#dataForm{
 		background-color: rgba(242, 242, 242, 1);
 	}
 	#searchbtn{
		height: 23px;
		overflow: hidden;
		width: 60px;
		line-height: 23px;
		cursor: pointer;
		position: relative;
		text-align: center;
		border: solid 1px #A3C0E8;
		color: #333333;
		background: #E0EDFF repeat-x center;
 	}
 	.p-title{
 		background-color: rgba(242, 242, 242, 1);
 		padding: 20px 0px 0px 20px;
 	}
 	.span1{
 		font-weight: 700;
		font-style: normal;
		font-size: 16px;
		text-align: left;
 	}
 	.span2{
 		color: rgba(0, 153, 255, 1);
 	}
 	.dataShow{
 		margin: 10px 20px; 
 		background-color: rgba(0, 153, 255, 1); 
 		height: 170px;
 	}
 	.div-tab{
 		padding-top: 20px;
 	}
 	.tab-2{
 		width: 100%;
 	}
 	.tab-2 td{
 		width: 10%;
 		text-align: center; 
		background: inherit;
	    background-color: inherit;
		box-sizing: border-box;
		-moz-box-shadow: none;
		-webkit-box-shadow: none;
		box-shadow: none;
		font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
		font-weight: 400;
		font-style: normal;
		font-size: 16px;
		color: white;
		line-height: 32px;
 	}
 	.tab-2 tr:nth-child(2) td{
 		font-size: 28px;
 	}
 	.span-title1{
 		font-size: 28px;
 		color: rgba(0, 153, 255, 1); 
 	}
 	.span-title2{
 		font-size: 16px;
 		color: rgba(0, 153, 255, 1);
 	}
 	
 	.mainContant {
        margin: 10px 20px;
    }
    .wrap {
        padding: 10px 0;
        background: #fff;
        border-radius: 4px;
    }
    .tabs {
        margin: 0 40px;
    }
    ul {
        list-style: none;
    }
    li {
        box-sizing: border-box;
        float: left;
        height: 100%;
        text-align: center;
        font-weight: bold;
    }
    .tabWrap {
        height: 220px;
        cursor: pointer;
    }
    .tabWrap li {
        width: 33.3%;
    }
    .tabWrap li div {
        height: 190px;
        border: 1px solid #eee;
    }
    .con {
        height: 10px;
        display: inline-block;
        background-color: #fff;
        width: 100%;
    }
    .active {
        height: 10px;
        display: inline-block;
        background-color: #297de0;
        width: 100%;
    }
    .dataWrap {
        margin: 0 40px;
        background: #fff;
    }
    .dataWrap .data {
        width: 100%;
        height: 150px;
    }
    .data .item {
        border: 1px solid #eee;
        padding: 20px 10px;
        width: 220px;
    }
    .item p {
        text-align: left;
        text-align: left;
        height: 40px;
        font-size: 14px;
    }
    .data .item:hover,
    .data .item:active {
        border: 1px solid #297de0;
    }
    #sourceButton{
    	height: 23px;
		overflow: hidden;
		width: 60px;
		line-height: 23px;
		cursor: pointer;
		position: relative;
		text-align: center;
		border: solid 1px #A3C0E8;
		color: #333333;
		background: #E0EDFF repeat-x center;
		border-radius: 5px;
    }
 </style>
 <script type="text/javascript">
 	var mchtId = '${mchtInfoCustom.id }';
 	var statisticsBeginDate = '${statisticsBeginDate }';
 	var statisticsEndDate = '${statisticsEndDate }';
 	var comparisonBeginDate = '${comparisonBeginDate }';
 	var comparisonEndDate = '${comparisonEndDate }';
 	$(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135,
			cancelable : false
		});
		
		$("#searchbtn").click(function(){
			var statisticsBeginDate = $("#statisticsBeginDate").val();
			var statisticsEndDate = $("#statisticsEndDate").val();
			var comparisonBeginDate = $("#comparisonBeginDate").val();
			var comparisonEndDate = $("#comparisonEndDate").val();
			if(statisticsBeginDate == '' || statisticsEndDate == '' 
					|| comparisonBeginDate == '' || comparisonEndDate == '' ) {
				commUtil.alertError("日期不能为空！");				
			} else {
				$("#dataForm").submit();
			}
		});
		
		$('li').click(function() {
            var index = $(this).index();
            console.log($(this).children('span'))
            $(this).children("span").addClass('active');
            $(this).siblings().children("span").removeClass('active')
        })
		
        mchtVisitorPvFun();
        
	 });
 
 	//访问店铺
 	function mchtVisitorPvFun() {
 		$(".data .item:nth-child(1)").hide();
 		$(".data .item:nth-child(2)").hide();
 		$(".data .item:nth-child(3)").hide();
 		$(".data .item:nth-child(4)").hide();
 		$(".data .item:nth-child(5)").hide();
 		$(".data .item:nth-child(6)").hide();
 		$(".data .item:nth-child(7)").hide();
	 	$(".data .item:nth-child(1) p:nth-child(1)").eq(0).html("访客数(会员)");
	 	$(".data .item:nth-child(2) p:nth-child(1)").eq(0).html("访客数(非会员)");
	 	$(".data .item:nth-child(3) p:nth-child(1)").eq(0).html("浏览量(会员)");
	 	$(".data .item:nth-child(4) p:nth-child(1)").eq(0).html("浏览量(非会员)");
	 	$(".data .item:nth-child(5) p:nth-child(1)").eq(0).html("人均浏览量(会员)");
	 	$(".data .item:nth-child(6) p:nth-child(1)").eq(0).html("人均浏览量(非会员)");
	 	$(".data .item:nth-child(7) p:nth-child(1)").eq(0).html("关注店铺人数");
	 	$(".data .item:nth-child(1) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(2) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(3) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(4) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(5) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(6) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(7) p:nth-child(3) span").eq(1).attr("style", "");
 		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/mchtPvStatistical/getMchtVisitorPvMap.shtml",
			 data : {mchtId : mchtId, statisticsBeginDate : statisticsBeginDate, statisticsEndDate : statisticsEndDate, comparisonBeginDate : comparisonBeginDate, comparisonEndDate : comparisonEndDate},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.code != 200) {
					 commUtil.alertError(data.msg);
				 }else {
					 if(data.mchtVisitorMap != null) {
						 $(".data .item:nth-child(1) p:nth-child(2)").eq(0).html(data.mchtVisitorMap.total_visitor_count_a);
						 var total_visitor_count_rate = data.mchtVisitorMap.total_visitor_count_rate;
						 if(total_visitor_count_rate > 0 ) {
							 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).attr("style", "color:red;");
						 }else if(total_visitor_count_rate < 0) {
							 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).attr("style", "color:green;");
						 }
					 	 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).html(total_visitor_count_rate+"%");
						 $(".data .item:nth-child(2) p:nth-child(2)").eq(0).html(data.mchtVisitorMap.total_visitor_count_tourist_a);
						 var total_visitor_count_rate_tourist = data.mchtVisitorMap.total_visitor_count_rate_tourist;
						 if(total_visitor_count_rate_tourist > 0 ) {
							 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).attr("style", "color:red;");
						 }else if(total_visitor_count_rate_tourist < 0) {
							 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).attr("style", "color:green;");
						 }
					 	 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).html(total_visitor_count_rate_tourist+"%");
					 	 $(".data .item:nth-child(3) p:nth-child(2)").eq(0).html(data.mchtVisitorMap.total_pv_count_a);
					 	 var total_pv_count_rate = data.mchtVisitorMap.total_pv_count_rate;
					 	 if(total_pv_count_rate > 0 ) {
					 		 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).attr("style", "color:red;");
						 }else if(total_pv_count_rate < 0) {
							 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).attr("style", "color:green;");
						 }
					 	 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).html(total_pv_count_rate+"%");
						 $(".data .item:nth-child(4) p:nth-child(2)").eq(0).html(data.mchtVisitorMap.total_pv_count_tourist_a);
					 	 var total_pv_count_rate_tourist = data.mchtVisitorMap.total_pv_count_rate_tourist;
					 	 if(total_pv_count_rate_tourist > 0 ) {
					 		 $(".data .item:nth-child(4) p:nth-child(3) span").eq(1).attr("style", "color:red;");
						 }else if(total_pv_count_rate_tourist < 0) {
							 $(".data .item:nth-child(4) p:nth-child(3) span").eq(1).attr("style", "color:green;");
						 }
					 	 $(".data .item:nth-child(4) p:nth-child(3) span").eq(1).html(total_pv_count_rate_tourist+"%");
					 	 $(".data .item:nth-child(5) p:nth-child(2)").eq(0).html(data.mchtVisitorMap.total_pv_avg_a);
					 	 var total_pv_avg_rate = data.mchtVisitorMap.total_pv_avg_rate;
					 	 if(total_pv_avg_rate > 0 ) {
					 		 $(".data .item:nth-child(5) p:nth-child(3) span").eq(1).attr("style", "color:red;");
						 }else if(total_pv_avg_rate < 0) {
							 $(".data .item:nth-child(5) p:nth-child(3) span").eq(1).attr("style", "color:green;");
						 }
					 	 $(".data .item:nth-child(5) p:nth-child(3) span").eq(1).html(total_pv_avg_rate+"%");
						 $(".data .item:nth-child(6) p:nth-child(2)").eq(0).html(data.mchtVisitorMap.total_pv_avg_tourist_a);
					 	 var total_pv_avg_rate_tourist = data.mchtVisitorMap.total_pv_avg_rate_tourist;
					 	 if(total_pv_avg_rate_tourist > 0 ) {
					 		 $(".data .item:nth-child(6) p:nth-child(3) span").eq(1).attr("style", "color:red;");
						 }else if(total_pv_avg_rate_tourist < 0) {
							 $(".data .item:nth-child(6) p:nth-child(3) span").eq(1).attr("style", "color:green;");
						 }
					 	 $(".data .item:nth-child(6) p:nth-child(3) span").eq(1).html(total_pv_avg_rate_tourist+"%");
					 	 $(".data .item:nth-child(7) p:nth-child(2)").eq(0).html(data.mchtVisitorMap.member_remind_count_a);
						 var member_remind_count_rate = data.mchtVisitorMap.member_remind_count_rate;
						 if(member_remind_count_rate > 0 ) {
					 		 $(".data .item:nth-child(7) p:nth-child(3) span").eq(1).attr("style", "color:red;");
						 }else if(member_remind_count_rate < 0) {
							 $(".data .item:nth-child(7) p:nth-child(3) span").eq(1).attr("style", "color:green;");
						 }
				 		 $(".data .item:nth-child(7) p:nth-child(3) span").eq(1).html(member_remind_count_rate+"%");
					 }else {
						 $(".data .item:nth-child(1) p:nth-child(2)").eq(0).html("0");
					 	 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).html("0.00%");
					 	 $(".data .item:nth-child(2) p:nth-child(2)").eq(0).html("0");
					 	 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).html("0.00%");
					 	 $(".data .item:nth-child(3) p:nth-child(2)").eq(0).html("0");
					 	 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).html("0.00%");
						 $(".data .item:nth-child(4) p:nth-child(2)").eq(0).html("0");
				 		 $(".data .item:nth-child(4) p:nth-child(3) span").eq(1).html("0.00%");
					 	 $(".data .item:nth-child(5) p:nth-child(2)").eq(0).html("0");
				 		 $(".data .item:nth-child(5) p:nth-child(3) span").eq(1).html("0.00%");
					 	 $(".data .item:nth-child(6) p:nth-child(2)").eq(0).html("0");
				 		 $(".data .item:nth-child(6) p:nth-child(3) span").eq(1).html("0.00%");
					 	 $(".data .item:nth-child(7) p:nth-child(2)").eq(0).html("0");
				 		 $(".data .item:nth-child(7) p:nth-child(3) span").eq(1).html("0.00%");
					 }
					 $(".data .item:nth-child(1)").show();
				 	 $(".data .item:nth-child(2)").show();
				 	 $(".data .item:nth-child(3)").show();
				 	 $(".data .item:nth-child(4)").show();
					 $(".data .item:nth-child(5)").show();
					 $(".data .item:nth-child(6)").show();
					 $(".data .item:nth-child(7)").show();
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 } 
		 });
 	}
 	
 	//访问商品
 	function mchtPvProductVisitorFun() {
		$(".data .item:nth-child(1)").hide();
		$(".data .item:nth-child(2)").hide();
		$(".data .item:nth-child(3)").hide();
		$(".data .item:nth-child(4)").hide();
		$(".data .item:nth-child(5)").hide();
		$(".data .item:nth-child(6)").hide();
		$(".data .item:nth-child(7)").hide();
 		$(".data .item:nth-child(1) p:nth-child(1)").eq(0).html("商品访客数(会员)");
 		$(".data .item:nth-child(2) p:nth-child(1)").eq(0).html("商品访客数(非会员)");
	 	$(".data .item:nth-child(3) p:nth-child(1)").eq(0).html('浏览量(会员)<span style="margin-left: 10px;"><input type="button" id="sourceButton" value="来源" onclick="sourcePageClassifyFun(1);" /> </span>');
	 	$(".data .item:nth-child(4) p:nth-child(1)").eq(0).html('浏览量(非会员)<span style="margin-left: 10px;"><input type="button" id="sourceButton" value="来源" onclick="sourcePageClassifyFun(0);" /> </span>');
	 	$(".data .item:nth-child(5) p:nth-child(1)").eq(0).html("人均浏览量(会员)");
	 	$(".data .item:nth-child(6) p:nth-child(1)").eq(0).html("人均浏览量(非会员)");
	 	$(".data .item:nth-child(7) p:nth-child(1)").eq(0).html("收藏商品人数");
	 	$(".data .item:nth-child(1) p:nth-child(3) span").eq(1).attr("style", ""); 
	 	$(".data .item:nth-child(2) p:nth-child(3) span").eq(1).attr("style", "");
 	 	$(".data .item:nth-child(3) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(4) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(5) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(6) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(7) p:nth-child(3) span").eq(1).attr("style", "");
 		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/mchtPvStatistical/getMchtPvProductVisitorMap.shtml",
			 data : {mchtId : mchtId, statisticsBeginDate : statisticsBeginDate, statisticsEndDate : statisticsEndDate, comparisonBeginDate : comparisonBeginDate, comparisonEndDate : comparisonEndDate},
			 dataType : 'json',
			 success : function(data){
				 if(data.mchtPvProductVisitorMap != null) {
					 $(".data .item:nth-child(1) p:nth-child(2)").eq(0).html(data.mchtPvProductVisitorMap.total_visitor_count_a);
					 var total_visitor_count_rate = data.mchtPvProductVisitorMap.total_visitor_count_rate;
					 if(total_visitor_count_rate > 0 ) {
				 		 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).attr("style", "color:red;");
					 }else if(total_visitor_count_rate < 0) {
						 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).attr("style", "color:green;");
					 }
				 	 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).html(total_visitor_count_rate+"%");
					 $(".data .item:nth-child(2) p:nth-child(2)").eq(0).html(data.mchtPvProductVisitorMap.total_visitor_count_tourist_a);
					 var total_visitor_count_rate_tourist = data.mchtPvProductVisitorMap.total_visitor_count_rate_tourist;
					 if(total_visitor_count_rate_tourist > 0 ) {
				 		 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).attr("style", "color:red;");
					 }else if(total_visitor_count_rate < 0) {
						 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).attr("style", "color:green;");
					 }
				 	 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).html(total_visitor_count_rate_tourist+"%");
				 	 $(".data .item:nth-child(3) p:nth-child(2)").eq(0).html(data.mchtPvProductVisitorMap.total_pv_count_a);
				 	 var total_pv_count_rate = data.mchtPvProductVisitorMap.total_pv_count_rate;
				 	 if(total_pv_count_rate > 0 ) {
				 		 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).attr("style", "color:red;");
					 }else if(total_pv_count_rate < 0) {
						 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).attr("style", "color:green;");
					 }
				 	 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).html(total_pv_count_rate+"%");
				 	 $(".data .item:nth-child(4) p:nth-child(2)").eq(0).html(data.mchtPvProductVisitorMap.total_pv_count_tourist_a);
				 	 var total_pv_count_rate_tourist = data.mchtPvProductVisitorMap.total_pv_count_rate_tourist;
				 	 if(total_pv_count_rate_tourist > 0 ) {
				 		 $(".data .item:nth-child(4) p:nth-child(3) span").eq(1).attr("style", "color:red;");
					 }else if(total_pv_count_rate_tourist < 0) {
						 $(".data .item:nth-child(4) p:nth-child(3) span").eq(1).attr("style", "color:green;");
					 }
				 	 $(".data .item:nth-child(4) p:nth-child(3) span").eq(1).html(total_pv_count_rate_tourist+"%");
				 	 $(".data .item:nth-child(5) p:nth-child(2)").eq(0).html(data.mchtPvProductVisitorMap.total_pv_count_avg_a);
				 	 var total_pv_count_avg_rate = data.mchtPvProductVisitorMap.total_pv_count_avg_rate;
				 	 if(total_pv_count_avg_rate > 0 ) {
				 		 $(".data .item:nth-child(5) p:nth-child(3) span").eq(1).attr("style", "color:red;");
					 }else if(total_pv_count_avg_rate < 0) {
						 $(".data .item:nth-child(5) p:nth-child(3) span").eq(1).attr("style", "color:green;");
					 }
				 	 $(".data .item:nth-child(5) p:nth-child(3) span").eq(1).html(total_pv_count_avg_rate+"%");
					 $(".data .item:nth-child(6) p:nth-child(2)").eq(0).html(data.mchtPvProductVisitorMap.total_pv_count_avg_tourist_a);
				 	 var total_pv_count_avg_rate_tourist = data.mchtPvProductVisitorMap.total_pv_count_avg_rate_tourist;
				 	 if(total_pv_count_avg_rate_tourist > 0 ) {
				 		 $(".data .item:nth-child(6) p:nth-child(3) span").eq(1).attr("style", "color:red;");
					 }else if(total_pv_count_avg_rate_tourist < 0) {
						 $(".data .item:nth-child(6) p:nth-child(3) span").eq(1).attr("style", "color:green;");
					 }
				 	 $(".data .item:nth-child(6) p:nth-child(3) span").eq(1).html(total_pv_count_avg_rate_tourist+"%");
				 	 $(".data .item:nth-child(7) p:nth-child(2)").eq(0).html(data.mchtPvProductVisitorMap.member_remind_count_a);
					 var member_remind_count_rate = data.mchtPvProductVisitorMap.member_remind_count_rate;
					 if(member_remind_count_rate > 0 ) {
				 		 $(".data .item:nth-child(7) p:nth-child(3) span").eq(1).attr("style", "color:red;");
					 }else if(member_remind_count_rate < 0) {
						 $(".data .item:nth-child(7) p:nth-child(3) span").eq(1).attr("style", "color:green;");
					 }
			 		 $(".data .item:nth-child(7) p:nth-child(3) span").eq(1).html(member_remind_count_rate+"%");
				 }else {
					 $(".data .item:nth-child(1) p:nth-child(2)").eq(0).html("0");
					 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).html("0.00%");
					 $(".data .item:nth-child(2) p:nth-child(2)").eq(0).html("0");
					 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).html("0.00%");
					 $(".data .item:nth-child(3) p:nth-child(2)").eq(0).html("0");
					 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).html("0.00%");
					 $(".data .item:nth-child(4) p:nth-child(2)").eq(0).html("0");
					 $(".data .item:nth-child(4) p:nth-child(3) span").eq(1).html("0.00%");
					 $(".data .item:nth-child(5) p:nth-child(2)").eq(0).html("0");
					 $(".data .item:nth-child(5) p:nth-child(3) span").eq(1).html("0.00%");
					 $(".data .item:nth-child(6) p:nth-child(2)").eq(0).html("0");
					 $(".data .item:nth-child(6) p:nth-child(3) span").eq(1).html("0.00%");
					 $(".data .item:nth-child(7) p:nth-child(2)").eq(0).html("0");
					 $(".data .item:nth-child(7) p:nth-child(3) span").eq(1).html("0.00%");
				 }
				 $(".data .item:nth-child(1)").show();
				 $(".data .item:nth-child(2)").show();
				 $(".data .item:nth-child(3)").show();
				 $(".data .item:nth-child(4)").show();
				 $(".data .item:nth-child(5)").show();
				 $(".data .item:nth-child(6)").show();
				 $(".data .item:nth-child(7)").show();
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
 	}
 	
 	//转化
 	function mchtPvVisitorFun() {
		$(".data .item:nth-child(1)").hide();
		$(".data .item:nth-child(2)").hide();
		$(".data .item:nth-child(3)").hide();
		$(".data .item:nth-child(4)").hide();
		$(".data .item:nth-child(5)").hide();
		$(".data .item:nth-child(6)").hide();
		$(".data .item:nth-child(7)").hide();
 		$(".data .item:nth-child(1) p:nth-child(1)").eq(0).html("支付买家数");
	 	$(".data .item:nth-child(2) p:nth-child(1)").eq(0).html("提交订单买家数");
	 	$(".data .item:nth-child(3) p:nth-child(1)").eq(0).html("加购买家数");
	 	$(".data .item:nth-child(1) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(2) p:nth-child(3) span").eq(1).attr("style", "");
	 	$(".data .item:nth-child(3) p:nth-child(3) span").eq(1).attr("style", "");
 		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/mchtPvStatistical/getMchtPvVisitorMap.shtml",
			 data : {mchtId : mchtId, statisticsBeginDate : statisticsBeginDate, statisticsEndDate : statisticsEndDate, comparisonBeginDate : comparisonBeginDate, comparisonEndDate : comparisonEndDate},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.code != 200) {
					 commUtil.alertError(data.msg);
				 }else {
					 if(data.mchtPvVisitorMap != null) {
						 $(".data .item:nth-child(1) p:nth-child(2)").eq(0).html(data.mchtPvVisitorMap.pay_member_count_a);
						 var pay_member_count_rate = data.mchtPvVisitorMap.pay_member_count_rate;
						 if(pay_member_count_rate > 0 ) {
					 		 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).attr("style", "color:red;");
						 }else if(pay_member_count_rate < 0) {
							 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).attr("style", "color:green;");
						 }
					 	 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).html(pay_member_count_rate+"%");
					 	 $(".data .item:nth-child(2) p:nth-child(2)").eq(0).html(data.mchtPvVisitorMap.combine_order_count_a);
					 	 var combine_order_count_rate = data.mchtPvVisitorMap.combine_order_count_rate;
						 if(combine_order_count_rate > 0 ) {
					 		 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).attr("style", "color:red;");
						 }else if(combine_order_count_rate < 0) {
							 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).attr("style", "color:green;");
						 }
					 	 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).html(combine_order_count_rate+"%");
					 	 $(".data .item:nth-child(3) p:nth-child(2)").eq(0).html(data.mchtPvVisitorMap.shopping_cart_count_a);
					 	 var shopping_cart_count_rate = data.mchtPvVisitorMap.shopping_cart_count_rate;
					 	 if(shopping_cart_count_rate > 0 ) {
					 		 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).attr("style", "color:red;");
						 }else if(shopping_cart_count_rate < 0) {
							 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).attr("style", "color:green;");
						 }
					 	 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).html(shopping_cart_count_rate+"%");
					 }else {
						 $(".data .item:nth-child(1) p:nth-child(2)").eq(0).html("0");
					 	 $(".data .item:nth-child(1) p:nth-child(3) span").eq(1).html("0.00%");
					 	 $(".data .item:nth-child(2) p:nth-child(2)").eq(0).html("0");
					 	 $(".data .item:nth-child(2) p:nth-child(3) span").eq(1).html("0.00%");
					 	 $(".data .item:nth-child(3) p:nth-child(2)").eq(0).html("0");
					 	 $(".data .item:nth-child(3) p:nth-child(3) span").eq(1).html("0.00%");
					 }
					 $(".data .item:nth-child(1)").show();
				 	 $(".data .item:nth-child(2)").show();
				 	 $(".data .item:nth-child(3)").show();
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
 	}
 	
 	function sourcePageClassifyFun(flag) {
 		$.ligerDialog.open({
			height: $(window).height() - 200,
			width: $(window).width() - 600,
			title: "来源",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtPvStatistical/getMchtPvStatisticalSourceList.shtml?mchtId="
					+mchtId+"&statisticsBeginDate="+statisticsBeginDate+"&statisticsEndDate="+statisticsEndDate+"&flag="+flag,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
 	}


 </script>
</head>
<body style="padding: 0px; overflow: hidden;" >
	<p class="p-title">
		<span class="span-title1" >历史流量看板</span>
		<span class="span-title2" >（${mchtInfoCustom.shopName }-${mchtInfoCustom.mchtCode }）</span>
	</p>
	<form id="dataForm" action="${pageContext.request.contextPath}/mchtPvStatistical/mchtPvStatisticalShow.shtml" method="post" >
		<div class="search-pannel" >
			<input type="hidden" name="mchtId" value="${mchtInfoCustom.id }" />
			<div class="search-tr"  > 
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">统计日：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="statisticsBeginDate" name="statisticsBeginDate" value="${statisticsBeginDate }" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="statisticsEndDate" name="statisticsEndDate" value="${statisticsEndDate }" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">对比日：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="comparisonBeginDate" name="comparisonBeginDate" value="${comparisonBeginDate }" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="comparisonEndDate" name="comparisonEndDate" value="${comparisonEndDate }" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	<p class="p-title">
		<span class="span1" >
			<span class="span2" >|</span>
			流量总览
		</span>
	</p>
	<div class="mainContant">
        <div class="wrap">
            <hr>
            <div class="tabs">
                <ul class="tabWrap">
                    <li>
                        <span class="con active"></span>
                        <div onclick="mchtVisitorPvFun();">
                        	<div>
	                        	<p style="margin: 20px 30px 10px 30px;text-align: left;font-size: 16px;">访问店铺</p>
                        		<p style="margin: 0px 30px 0px 30px;text-align: left;">
                        			<span style="color: #aeaeae;">访客数</span>
                        			<span style="float: right;font-size: 16px;">
                        				${mchtVisitorMap.total_visitor_count_a }
                        			</span>
                        		</p>
                        		<p style="margin: 5px 30px 0px 30px;text-align: left;color: #aeaeae;">
                        			<span>较对比日同时段</span>
                       				<c:if test="${mchtVisitorMap.total_visitor_count_rate > 0 }">
                       					<span style="float: right;color: red;">
                       						${mchtVisitorMap.total_visitor_count_rate }%
                       					</span>
                       				</c:if>
                       				<c:if test="${mchtVisitorMap.total_visitor_count_rate < 0 }">
                       					<span style="float: right;color: green;">
                       						${mchtVisitorMap.total_visitor_count_rate }%
                       					</span>
                       				</c:if>
                       				<c:if test="${empty mchtVisitorMap.total_visitor_count_rate or mchtVisitorMap.total_visitor_count_rate == '0.00' }">
                       					<span style="float: right;">
                       						0.00%
                       					</span>
                       				</c:if>
                        		</p>
                        		<p>
                        			<img alt="1" src="${pageContext.request.contextPath}/images/mchtPv1.png">
                        		</p>
                        	</div>
                        </div>
                    </li>
                    <li>
                        <span class="con"></span>
                        <div onclick="mchtPvProductVisitorFun();">
	                        <div>
	                        	<p style="margin: 20px 30px 10px 30px;text-align: left;font-size: 16px;">访问商品</p>
	                       		<p style="margin: 0px 30px 0px 30px;text-align: left;">
	                       			<span style="color: #aeaeae;">商品访客数</span>
	                       			<span style="float: right;font-size: 16px;">
	                       				${mchtProductVisitorMap.total_visitor_count_a }
	                       			</span>
	                       		</p>
	                       		<p style="margin: 5px 30px 0px 30px;text-align: left;color: #aeaeae;">
	                       			<span>较对比日同时段</span>
	                       			<c:if test="${mchtProductVisitorMap.total_visitor_count_rate > 0 }">
	                  					<span style="float: right;color: red;">
	                  						${mchtProductVisitorMap.total_visitor_count_rate }%
	                  					</span>
	                  				</c:if>
	                  				<c:if test="${mchtProductVisitorMap.total_visitor_count_rate < 0 }">
	                  					<span style="float: right;color: green;">
	                  						${mchtProductVisitorMap.total_visitor_count_rate }%
	                  					</span>
	                  				</c:if>
	                  				<c:if test="${empty mchtProductVisitorMap.total_visitor_count_rate or mchtProductVisitorMap.total_visitor_count_rate == '0.00' }">
	                   					<span style="float: right;">
	                   						0.00%
	                   					</span>
	                   				</c:if>
	                       		</p>
	                       		<p>
                        			<img alt="1" src="${pageContext.request.contextPath}/images/mchtPv2.png">
                        		</p>
	                       	</div>
                        </div>
                    </li>
                    <li>
                        <span class="con"></span>
                        <div onclick="mchtPvVisitorFun();">
	                        <div>
	                        	<p style="margin: 20px 30px 10px 30px;text-align: left;font-size: 16px;">转化</p>
	                       		<p style="margin: 0px 30px 0px 30px;text-align: left;">
	                       			<span style="color: #aeaeae;">支付买家数</span>
	                       			<span style="float: right;font-size: 16px;">
	                       				${mchtVisitorMap.pay_member_count_a }
	                       			</span>
	                       		</p>
	                       		<p style="margin: 5px 30px 0px 30px;text-align: left;color: #aeaeae;">
	                       			<span>较对比日同时段</span>
	                       			<c:if test="${mchtVisitorMap.pay_member_count_rate > 0 }">
	                   					<span style="float: right;color: red;">
	                   						${mchtVisitorMap.total_visitor_count_rate }%
	                   					</span>
	                   				</c:if>
	                   				<c:if test="${mchtVisitorMap.pay_member_count_rate < 0 }">
	                   					<span style="float: right;color: green;">
	                   						${mchtVisitorMap.pay_member_count_rate }%
	                   					</span>
	                   				</c:if>
	                   				<c:if test="${empty mchtVisitorMap.pay_member_count_rate or mchtVisitorMap.pay_member_count_rate == '0.00' }">
	                   					<span style="float: right;">
	                   						0.00%
	                   					</span>
	                   				</c:if>
	                       		</p>
	                       		<p>
                        			<img alt="1" src="${pageContext.request.contextPath}/images/mchtPv3.png">
                        		</p>
	                       	</div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="dataWrap">
                <ul class="data">
                    <li class="item">
                        <p></p>
                        <p style="font-size: 16px;"></p>
                        <p style="color: #aeaeae;">
                        	<span style="margin-right: 10px;">较对比日同时段</span>
                        	<span></span>
                        </p>
                    </li>
                    <li class="item">
                        <p></p>
                        <p style="font-size: 16px;"></p>
                        <p style="color: #aeaeae;">
                        	<span style="margin-right: 10px;">较对比日同时段</span>
                        	<span></span>
                        </p>
                    </li>
                    <li class="item">
                        <p></p>
                        <p style="font-size: 16px;"></p>
                        <p style="color: #aeaeae;">
                        	<span style="margin-right: 10px;">较对比日同时段</span>
                        	<span></span>
                        </p>
                    </li>
                    <li class="item">
                        <p></p>
                        <p style="font-size: 16px;"></p>
                        <p style="color: #aeaeae;">
                        	<span style="margin-right: 10px;">较对比日同时段</span>
                        	<span></span>
                        </p>
                    </li>
					<li class="item">
                        <p></p>
                        <p style="font-size: 16px;"></p>
                        <p style="color: #aeaeae;">
                        	<span style="margin-right: 10px;">较对比日同时段</span>
                        	<span></span>
                        </p>
                    </li>
					<li class="item">
                        <p></p>
                        <p style="font-size: 16px;"></p>
                        <p style="color: #aeaeae;">
                        	<span style="margin-right: 10px;">较对比日同时段</span>
                        	<span></span>
                        </p>
                    </li>
					<li class="item">
                        <p></p>
                        <p style="font-size: 16px;"></p>
                        <p style="color: #aeaeae;">
                        	<span style="margin-right: 10px;">较对比日同时段</span>
                        	<span></span>
                        </p>
                    </li>
                </ul>
            </div>
        </div>

    </div>
	
</body>

</html>
