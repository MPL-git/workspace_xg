<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>单品活动报名</title>
<style type="text/css">
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
<div class="x_panel container-fluid gf-hd">
	<input type="hidden" id="mchtInfoStatus" value="${mchtInfoStatus}"/>
	<input type="hidden" id="newUserIsSpecial" value="${newUserIsSpecial}"/>
	<input type="hidden" id="hotIsSpecial" value="${hotIsSpecial}"/>
	<input type="hidden" id="killIsSpecial" value="${killIsSpecial}"/>
	<input type="hidden" id="newUserKillIsSpecial" value="${newUserKillIsSpecial}"/>
	<input type="hidden" id="integralIsSpecial" value="${integralIsSpecial}"/>
	<input type="hidden" id="brokenCodeIsSpecial" value="${brokenCodeIsSpecial}"/>
	<input type="hidden" id="cutFreeIsSpecial" value="${cutFreeIsSpecial}"/>
	<input type="hidden" id="invitationFreeIsSpecial" value="${invitationFreeIsSpecial}"/>
	<input type="hidden" id="assistanceIsSpecial" value="${assistanceIsSpecial}"/>
    <div class="row content-title">
        <div class="t-title">
            	单品活动报名
        </div>
    </div>
    <div class="clearfix"></div>
    <br>
    <div class="list-group-item" style="margin-bottom: 10px;">
	    	商家提报商品>>商城初审>>商城排期>>商城复审
    </div>
    <div type="1" class="panel panel-default home-content-panner company-info-container" style="height: auto;">
		<div class="row">
			<div class="col-md-12">
				<span class="company-name-text main-text">新用户专区</span>
			</div>

		</div>
		<div class="row" style="padding-top: 15px;font-size: 14px; line-height: 28px;">
			<div class="col-md-9">
				<p>
					<c:if test="${empty html1}">
					1、商品在首页《新用户专区》优先展示，具有更高的曝光及流量支持<br>
				    2、仅供注册用户购买，活动商品的价格必须优于其他网购商城<br>
				    3、商品要求： <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;应季商品、热销单品、高价比的商品、每SKU的价格统一价、整款库存大于300件
					</c:if>
					<c:if test="${not empty html1}">
						${html1}
					</c:if>
				</p>
				<p style="color: red;"><a href="javascript:;" class="btn btn-warning" onclick="getCount(1);" <c:if test="${not empty shopComment1 && shopComment1>mchtScore && (empty newUserIsSpecial || newUserIsSpecial==0)}">disabled="disabled"</c:if>>立即报名</a>
				<%-- 今天您还可以报名<span id="newCount"> ${newUserAllow}</span> 款 --%></p>
			</div>
					
		</div>
	</div>
    <div type="10" class="panel panel-default home-content-panner company-info-container" style="height: auto;">
		<div class="row">
			<div class="col-md-12">
				<span class="company-name-text main-text">助力减价</span>
			</div>

		</div>
		<div class="row" style="padding-top: 15px;font-size: 14px; line-height: 28px;">
			<div class="col-md-9">
				<p>
					<c:if test="${empty html10}">
					1、商品在首页《助力减价》优先展示，具有更高的曝光及流量支持<br>
				    2、仅供注册用户购买，活动商品的价格必须优于其他网购商城<br>
				    3、商品要求： <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;应季商品、热销单品、高价比的商品、每SKU的价格统一价、整款库存大于100件
				    </c:if>
				    <c:if test="${not empty html10}">
						${html10}
					</c:if>
				</p>
				<p style="color: red;"><a href="javascript:;" class="btn btn-warning" onclick="getCount(10);" <c:if test="${not empty shopComment10 && shopComment10>mchtScore && (empty assistanceIsSpecial || assistanceIsSpecial==0)}">disabled="disabled"</c:if>>立即报名</a>
				<%-- 今天您还可以报名<span id="assistanceCount"> ${assistanceAllow}</span> 款 --%></p>
			</div>
					
		</div>
	</div>
    <div type="2" class="panel panel-default home-content-panner company-info-container" style="height: auto;">
		<div class="row">
			<div class="col-md-12">
				<span class="company-name-text main-text">爆款专区</span>
			</div>

		</div>
		<div class="row" style="padding-top: 15px;font-size: 14px; line-height: 28px;">
			<div class="col-md-9">
				<p>
					<c:if test="${empty html2}">
					1、商品在首页《爆款专区》优先展示，具有更高的曝光及流量支持<br>
				    2、所有用户均可以购买，活动商品的价格必须低于其他网购商城价格的90%<br>
				    3、商品要求：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;应季商品、热销单品、高价比的商品、每SKU的价格统一价、整款库存大于100件
				   	</c:if>
				    <c:if test="${not empty html2}">
						${html2}
					</c:if>
				</p>
				<p style="color: red;"><a href="javascript:;" class="btn btn-warning" onclick="getCount(2);" <c:if test="${not empty shopComment2 && shopComment2>mchtScore && (empty hotIsSpecial || hotIsSpecial==0)}">disabled="disabled"</c:if>>立即报名</a>
				<%-- 今天您还可以报名<span id="hotCount"> ${hotAllow}</span> 款 --%></p>
			</div>
					
		</div>
	</div>
    <div type="3" class="panel panel-default home-content-panner company-info-container" style="height: auto;">
		<div class="row">
			<div class="col-md-12">
				<span class="company-name-text main-text">限时秒杀</span>
			</div>

		</div>
		<div class="row" style="padding-top: 15px;font-size: 14px; line-height: 28px;">
			<div class="col-md-9">
				<p>
					<c:if test="${empty html3}">
					1、商品在首页《限时秒杀》优先展示，具有更高的曝光及流量支持<br>
				    2、所有用户均可以购买，活动商品的价格必须低于其他网购商城价格的70%<br>
				    3、商品要求：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;应季商品、热销单品、高价比的商品、每SKU的价格统一价、整款库存大于50件
				    </c:if>
				    <c:if test="${not empty html3}">
				    	${html3}
				    </c:if>	
				</p>
				<p style="color: red;"><a href="javascript:;" class="btn btn-warning" onclick="getCount(3);" <c:if test="${not empty shopComment3 && shopComment3>mchtScore && (empty killIsSpecial || killIsSpecial==0)}">disabled="disabled"</c:if>>立即报名</a>
				<%-- 今天您还可以报名<span id="killCount"> ${killAllow}</span> 款 --%></p>
			</div>
					
		</div>
	</div>
    <div type="4" class="panel panel-default home-content-panner company-info-container" style="height: auto;">
		<div class="row">
			<div class="col-md-12">
				<span class="company-name-text main-text">新用户秒杀</span>
			</div>

		</div>
		<div class="row" style="padding-top: 15px;font-size: 14px; line-height: 28px;">
			<div class="col-md-9">
				<p>
					<c:if test="${empty html4}">
					1、商品在新用户专区《新用户秒杀》优先展示，具有更高的曝光及流量支持<br>
				    2、所有用户均可以购买，活动商品的价格必须低于其他网购商城价格的50%<br>
				    3、商品要求：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;应季商品、热销单品、高价比的商品、每SKU的价格统一价、整款库存大于50件
				    </c:if>
				    <c:if test="${not empty html4}">
				    	${html4}
				    </c:if>
				</p>
				<p style="color: red;"><a href="javascript:;" class="btn btn-warning" onclick="getCount(4);" <c:if test="${not empty shopComment4 && shopComment4>mchtScore && (empty newUserKillIsSpecial || newUserKillIsSpecial==0)}">disabled="disabled"</c:if>>立即报名</a>
				<%-- 今天您还可以报名<span id="newUserKillCount"> ${newUserKillAllow}</span> 款 --%></p>
			</div>
					
		</div>
	</div>
    <div type="5" class="panel panel-default home-content-panner company-info-container" style="height: auto;">
		<div class="row">
			<div class="col-md-12">
				<span class="company-name-text main-text">积分商城</span>
			</div>

		</div>
		<div class="row" style="padding-top: 15px;font-size: 14px; line-height: 28px;">
			<div class="col-md-9">
				<p style="width: 740px;">
					<c:if test="${empty html5}">
					1、商品在《积分商城》优先展示，具有更高的曝光及流量支持<br>
				    2、所有用户均可以购买，活动商品的价格必须低于其他网购商城价格的90%<br>
				    3、商品要求：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;应季商品、热销单品、高价比的商品、每SKU的价格统一价、整款库存大于500件，支持活动时间长，一般为1个月
				    </c:if>
				    <c:if test="${not empty html5}">
				    	${html5}
				    </c:if>
				</p>
				<p style="color: red;"><a href="javascript:;" class="btn btn-warning" onclick="getCount(5);" <c:if test="${not empty shopComment5 && shopComment5>mchtScore && (empty integralIsSpecial || integralIsSpecial==0)}">disabled="disabled"</c:if>>立即报名</a>
				<%-- 今天您还可以报名<span id="integralCount"> ${integralAllow}</span> 款 --%></p>
			</div>
					
		</div>
	</div>
    <div type="6" class="panel panel-default home-content-panner company-info-container" style="height: auto;">
		<div class="row">
			<div class="col-md-12">
				<span class="company-name-text main-text">断码清仓</span>
			</div>

		</div>
		<div class="row" style="padding-top: 15px;font-size: 14px; line-height: 28px;">
			<div class="col-md-9">
				<p style="width: 740px;">
					<c:if test="${empty html6}">
					1、商品在《断码清仓》优先展示，具有更高的曝光及流量支持<br>
				    2、所有用户均可以购买，活动商品的价格必须优于其他网购商城价格50%<br>
				    3、商品要求：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;高价比的商品、每SKU的价格统一价。
				    </c:if>
				    <c:if test="${not empty html6}">
				    	${html6}
				    </c:if>
				</p>
				<p style="color: red;"><a href="javascript:;" class="btn btn-warning" onclick="getCount(6);" <c:if test="${not empty shopComment6 && shopComment6>mchtScore && (empty brokenCodeIsSpecial || brokenCodeIsSpecial==0)}">disabled="disabled"</c:if>>立即报名</a>
				<%-- 今天您还可以报名<span id="brokenCodeCount"> ${brokenCodeAllow}</span> 款 --%></p>
			</div>
					
		</div>
	</div>
    <div type="7" class="panel panel-default home-content-panner company-info-container" style="height: auto;">
		<div class="row">
			<div class="col-md-12">
				<span class="company-name-text main-text">砍价免费拿</span>
			</div>

		</div>
		<div class="row" style="padding-top: 15px;font-size: 14px; line-height: 28px;">
			<div class="col-md-9">
				<p style="width: 740px;">
					<c:if test="${empty html7}">
					1、大量用户分享，获得关注度<br>

					2、商品详情页推荐商铺活动、商品<br>

					3、商品价格在0-500元之间
					</c:if>
					<c:if test="${not empty html7}">
				    	${html7}
				    </c:if>
				</p>
				<p style="color: red;"><a href="javascript:;" class="btn btn-warning" onclick="getCount(7);" <c:if test="${not empty shopComment7 && shopComment7>mchtScore && (empty cutFreeIsSpecial || cutFreeIsSpecial==0)}">disabled="disabled"</c:if>>立即报名</a>
				<%-- 今天您还可以报名<span id="cutFreeAllCount"> ${cutFreeAllow}</span> 款 --%></p>
			</div>
					
		</div>
	</div>
    <div type="8" class="panel panel-default home-content-panner company-info-container" style="height: auto;">
		<div class="row">
			<div class="col-md-12">
				<span class="company-name-text main-text">邀请享免单</span>
			</div>

		</div>
		<div class="row" style="padding-top: 15px;font-size: 14px; line-height: 28px;">
			<div class="col-md-9">
				<p style="width: 740px;">
					<c:if test="${empty html8}">
					1、大量用户分享，获得关注度<br>

					2、商品详情页推荐商铺活动、商品<br>

					3、商品价格在0-300元之间
					</c:if>
					<c:if test="${not empty html8}">
				    	${html8}
				    </c:if>
				</p>
				<p style="color: red;"><a href="javascript:;" class="btn btn-warning" onclick="getCount(8);" <c:if test="${not empty shopComment8 && shopComment8>mchtScore && (empty invitationFreeIsSpecial || invitationFreeIsSpecial==0)}">disabled="disabled"</c:if>>立即报名</a>
				<%-- 今天您还可以报名<span id="invitationFreeCount"> ${invitationFreeAllow}</span> 款 --%></p>
			</div>
					
		</div>
	</div>
    
    
</div>
<!--弹出Div-->
<div class="video_box" style="position:fixed; width:320px; height:170px; top:35%; left:45%; display: none;" id="chooseDiv">
	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					单品报名
				</h3>
			</div>
			<form class="form-horizontal" role="form" >
				<input type="hidden" id="chooseType">
				  <div class="form-group" style="margin-top: 10px;">
					<div class="col-sm-offset-1 col-md-7" style="width: 260px">
					  <button type="button" class="btn btn-default" name="toAddBtn" isSpecial="0">普通商品</button>
					  <button type="button" class="btn btn-default" name="toAddBtn" isSpecial="1" style="float: right;">快速通道</button>
					</div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出div End-->
<div class="black_box" style="display: none;"></div>

<!-- 	查看信息框 -->
<div class="modal fade" style="overflow-y: auto;" id="toAddModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>
<!-- 	查看SKU -->
<div class="modal fade" id="skuModal" tabindex="-1" role="dialog" aria-labelledby="skuModal" data-backdrop="static"></div>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".video_close").on('click',function(){
		$("#chooseDiv").hide();
		$(".black_box").hide();
	});
	
	$("button[name='toAddBtn']").on('click',function(){
		$("#chooseDiv").hide();
		$(".black_box").hide();
		var type = $("#chooseType").val();
		var isSpecial = $(this).attr("isSpecial");
		toAdd(type,isSpecial);
	});
	
	var showSingleActivity1 = '${showSingleActivity1}';
	if(showSingleActivity1 == "0"){
		$("div[type='1']").hide();
	}
	var showSingleActivity2 = '${showSingleActivity2}';
	if(showSingleActivity2 == "0"){
		$("div[type='2']").hide();
	}
	var showSingleActivity3 = '${showSingleActivity3}';
	if(showSingleActivity3 == "0"){
		$("div[type='3']").hide();
	}
	var showSingleActivity4 = '${showSingleActivity4}';
	if(showSingleActivity4 == "0"){
		$("div[type='4']").hide();
	}
	var showSingleActivity5 = '${showSingleActivity5}';
	if(showSingleActivity5 == "0"){
		$("div[type='5']").hide();
	}
	var showSingleActivity6 = '${showSingleActivity6}';
	if(showSingleActivity6 == "0"){
		$("div[type='6']").hide();
	}
	var showSingleActivity7 = '${showSingleActivity7}';
	if(showSingleActivity7 == "0"){
		$("div[type='7']").hide();
	}
	var showSingleActivity8 = '${showSingleActivity8}';
	if(showSingleActivity8 == "0"){
		$("div[type='8']").hide();
	}
	var showSingleActivity10 = '${showSingleActivity10}';
	if(showSingleActivity10 == "0"){
		$("div[type='10']").hide();
	}
});

	function toAdd(type,isSpecial){
		var url = "${ctx}/singleProductActivity/toAdd?type=" + type+"&isSpecial="+isSpecial;
	    $.ajax({
	        url: url,
	        type: "GET",
	        success: function (data) {
	            $("#toAddModal").html(data);
	            $("#toAddModal").modal();
	        },
	        error: function () {
	            swal('页面不存在');
	        }
	    });
	}

    function getCount(type) {
    	var mchtInfoStatus = $("#mchtInfoStatus").val();
		if(mchtInfoStatus == "2"){
			swal("对不起，合作状态暂停，不能提报活动");
			return false;
		}
		if(type==2){
	      	var	hotIsSpecial = $("#hotIsSpecial").val();
	  		if(hotIsSpecial == "1"){
	  			$("#chooseDiv").show();
	  			$(".black_box").show();
	  			$("#chooseType").val(type);
	  		}else{
	  			<c:if test="${not empty shopComment2 && shopComment2>mchtScore}">
				return false;
				</c:if>
	      		toAdd(type,0);
	  		}
    	}else if(type==1 || type==4 || type==7 || type==8){
    		if(type == 1){
            	var	newUserIsSpecial = $("#newUserIsSpecial").val();
        		if(newUserIsSpecial == "1"){
        			$("#chooseDiv").show();
        			$(".black_box").show();
        			$("#chooseType").val(type);
        		}else{
        			<c:if test="${not empty shopComment1 && shopComment1>mchtScore}">
    				return false;
    				</c:if>
            		toAdd(type,0);
        		}
        	}else if(type == 4){
        		var	newUserKillIsSpecial = $("#newUserKillIsSpecial").val();
        		if(newUserKillIsSpecial == "1"){
        			$("#chooseDiv").show();
        			$(".black_box").show();
        			$("#chooseType").val(type);
        		}else{
        			<c:if test="${not empty shopComment4 && shopComment4>mchtScore}">
    				return false;
    				</c:if>
            		toAdd(type,0);
        		}
        	}else if(type == 7){
        		var	cutFreeIsSpecial = $("#cutFreeIsSpecial").val();
        		if(cutFreeIsSpecial == "1"){
        			$("#chooseDiv").show();
        			$(".black_box").show();
        			$("#chooseType").val(type);
        		}else{
        			<c:if test="${not empty shopComment7 && shopComment7>mchtScore}">
    				return false;
    				</c:if>
            		toAdd(type,0);
        		}
        	}else if(type == 8){
        		var	invitationFreeIsSpecial = $("#invitationFreeIsSpecial").val();
        		if(invitationFreeIsSpecial == "1"){
        			$("#chooseDiv").show();
        			$(".black_box").show();
        			$("#chooseType").val(type);
        		}else{
        			<c:if test="${not empty shopComment8 && shopComment8>mchtScore}">
    				return false;
    				</c:if>
            		toAdd(type,0);
        		}
        	}
    	}else if(type==3 || type==5){
    		if(type==3){
        		var	killIsSpecial = $("#killIsSpecial").val();
        		if(killIsSpecial == "1"){
        			$("#chooseDiv").show();
        			$(".black_box").show();
        			$("#chooseType").val(type);
        		}else{
        			<c:if test="${not empty shopComment3 && shopComment3>mchtScore}">
    				return false;
    				</c:if>
            		toAdd(type,0);
        		}
        	}else if(type==5){
        		var	integralIsSpecial = $("#integralIsSpecial").val();
        		if(integralIsSpecial == "1"){
        			$("#chooseDiv").show();
        			$(".black_box").show();
        			$("#chooseType").val(type);
        		}else{
        			<c:if test="${not empty shopComment5 && shopComment5>mchtScore}">
    				return false;
    				</c:if>
            		toAdd(type,0);
        		}
        	}
    	}else if(type==6 || type==10){
    		if(type==6){
        		var	brokenCodeIsSpecial = $("#brokenCodeIsSpecial").val();
        		if(brokenCodeIsSpecial == "1"){
        			$("#chooseDiv").show();
        			$(".black_box").show();
        			$("#chooseType").val(type);
        		}else{
        			<c:if test="${not empty shopComment6 && shopComment6>mchtScore}">
    				return false;
    				</c:if>
            		toAdd(type,0);
        		}
        	}else if(type==10){
        		var	assistanceIsSpecial = $("#assistanceIsSpecial").val();
        		if(assistanceIsSpecial == "1"){
        			$("#chooseDiv").show();
        			$(".black_box").show();
        			$("#chooseType").val(type);
        		}else{
        			<c:if test="${not empty shopComment10 && shopComment10>mchtScore}">
    				return false;
    				</c:if>
            		toAdd(type,0);
        		}
        	}
    	}
		
		
    	/* $.ajax({
            url: "${ctx}/singleProductActivity/getCount?type="+type,
            type: "GET",
            success: function (data) {
            	var defaultCount = data.returnData.defaultCount;
            	if(type==2){
            		if(!defaultCount && defaultCount!=0){
            			defaultCount = 5;
                	}
            		if(data.returnData.count>=defaultCount){
                    	swal('每天最多报名'+defaultCount+'款');
                    	return false;
                    }else{
                    	var	hotIsSpecial = $("#hotIsSpecial").val();
                		if(hotIsSpecial == "1"){
                			$("#chooseDiv").show();
                			$(".black_box").show();
                			$("#chooseType").val(type);
                		}else{
                    		toAdd(type,0);
                		}
                    }
            	}else if(type==1 || type==4 || type==7 || type==8){
            		if(!defaultCount){
            			defaultCount = 0;
                	}
            		if(data.returnData.count>=defaultCount){
                    	swal('每天最多报名'+defaultCount+'款');
                    	return false;
                    }else{
                    	if(type == 1){
	                    	var	newUserIsSpecial = $("#newUserIsSpecial").val();
                    		if(newUserIsSpecial == "1"){
                    			$("#chooseDiv").show();
                    			$(".black_box").show();
                    			$("#chooseType").val(type);
                    		}else{
	                    		toAdd(type,0);
                    		}
                    	}else if(type == 4){
                    		var	newUserKillIsSpecial = $("#newUserKillIsSpecial").val();
                    		if(newUserKillIsSpecial == "1"){
                    			$("#chooseDiv").show();
                    			$(".black_box").show();
                    			$("#chooseType").val(type);
                    		}else{
	                    		toAdd(type,0);
                    		}
                    	}else if(type == 7){
                    		var	cutFreeIsSpecial = $("#cutFreeIsSpecial").val();
                    		if(cutFreeIsSpecial == "1"){
                    			$("#chooseDiv").show();
                    			$(".black_box").show();
                    			$("#chooseType").val(type);
                    		}else{
	                    		toAdd(type,0);
                    		}
                    	}else if(type == 8){
                    		var	invitationFreeIsSpecial = $("#invitationFreeIsSpecial").val();
                    		if(invitationFreeIsSpecial == "1"){
                    			$("#chooseDiv").show();
                    			$(".black_box").show();
                    			$("#chooseType").val(type);
                    		}else{
	                    		toAdd(type,0);
                    		}
                    	}
                    }
            	}else if(type==3 || type==5){
            		if(!defaultCount && defaultCount!=0){
            			defaultCount = 3;
                	}
            		if(data.returnData.count>=defaultCount){
                    	swal('每天最多报名'+defaultCount+'款');
                    	return false;
                    }else{
                    	if(type==3){
                    		var	killIsSpecial = $("#killIsSpecial").val();
                    		if(killIsSpecial == "1"){
                    			$("#chooseDiv").show();
                    			$(".black_box").show();
                    			$("#chooseType").val(type);
                    		}else{
                        		toAdd(type,0);
                    		}
                    	}else if(type==5){
                    		var	integralIsSpecial = $("#integralIsSpecial").val();
                    		if(integralIsSpecial == "1"){
                    			$("#chooseDiv").show();
                    			$(".black_box").show();
                    			$("#chooseType").val(type);
                    		}else{
                        		toAdd(type,0);
                    		}
                    	}
                    }
            	}else if(type==6 || type==10){
            		if(!defaultCount && defaultCount!=0){
            			defaultCount = 100;
                	}
            		if(data.returnData.count>=defaultCount){
                    	swal('每天最多报名'+defaultCount+'款');
                    	return false;
                    }else{
                    	if(type==6){
                    		var	brokenCodeIsSpecial = $("#brokenCodeIsSpecial").val();
                    		if(brokenCodeIsSpecial == "1"){
                    			$("#chooseDiv").show();
                    			$(".black_box").show();
                    			$("#chooseType").val(type);
                    		}else{
                        		toAdd(type,0);
                    		}
                    	}else if(type==10){
                    		var	assistanceIsSpecial = $("#assistanceIsSpecial").val();
                    		if(assistanceIsSpecial == "1"){
                    			$("#chooseDiv").show();
                    			$(".black_box").show();
                    			$("#chooseType").val(type);
                    		}else{
                        		toAdd(type,0);
                    		}
                    	}
                    }
            	}
            },
            error: function () {
                swal('报名失败，请稍后重试');
            }
        }); */
    }  
</script>
</body>
</html>
