<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>官方活动管理</title>  
 <style>
         .change {
            background: white;
            color: #131313;
            border-top: 3px solid red;
        }
</style>
</head>
<body>
<div class="x_panel container-fluid gf-hd">
	<input type="hidden" id="mchtInfoStatus" value="${mchtInfoStatus}"/>
    <div class="row content-title">
        <div class="t-title">
            	营销活动
        </div>
    </div>
    <div class="clearfix"></div>
    <ul class="nav nav-tabs q" role="tablist" style="margin-top:10px;">
        <li id="tab1" <c:if test="${pageStatus eq 0}">class="change"</c:if>> <a href="#" role="tab" data-toggle="tab">活动报名</a></li>
        <li> <a href="#" role="tab" data-toggle="tab" onclick="enrolledProduct();">已报名商品</a></li>
    </ul>
    <table border="1" bordercolor=#ddd width="100%">
         <tr type="1">
         	<td>
            <div class="no-check" style="padding:15px;">
    		<div class='width-img' style="height:105px;"><img src='${pageContext.request.contextPath}/static/images/yhh.png' height='110' width='180'></div>
        	<div class="width-txt" style="padding-left:72px; width:58%">
        		<p style="font-size:17px;">有好货</p>
       			<p style="color:#949494;">有好货致力于为广大醒购用户提供物美价廉的精品好货。对于热销商品能够取得更大曝光，快速打造爆款商品，获得海量流量。</p>
       			<p style="font-size:14px;">关键字：精选资源位   好货推荐   全品类</p>
       			<p style="font-size:14px;margin-bottom:6px;">活动时间： 长期有效</p>
       		</div>
       		<button type="button" data_value="1" class="btn btn-info" id="btn-query" style="margin-left: 19%;margin-top: 8%;">立即报名</button>
    		</div>
    		</td>
          </tr>
          <tr type="2">
          	<td>
            <div class="no-check" style="padding:15px;">
    		<div class='width-img' style="height:105px;"><img src='${pageContext.request.contextPath}/static/images/ccsx.png' height='110' width='180'></div>
        	<div class="width-txt" style="padding-left:72px; width:58%">
        		<p style="font-size:17px;margin-bottom:6px;margin-top:3px;">潮鞋上新</p>
       			<p style="color:#949494;margin-bottom:6px;">超大精准流量，日销迅速翻倍的捷径</p>
       			<p style="font-size:14px;margin-bottom:6px;">关键字：新款扶持  潮鞋爆款  超大精准流</p>
       			<p style="font-size:14px;margin-bottom:6px;">活动时间： 长期有效</p>
       		</div>
       		<button type="button" data_value="2" class="btn btn-info" id="btn-query" style="margin-left: 19%;margin-top: 8%;">立即报名</button>
    		</div>
    		</td>
           </tr>
          <tr type="3">
          	<td>
            <div class="no-check" style="padding:15px;">
    		<div class='width-img' style="height:105px;"><img src='${pageContext.request.contextPath}/static/images/clnz.png' height='110' width='180'></div>
        	<div class="width-txt" style="padding-left:72px; width:58%">
        		<p style="font-size:17px;margin-bottom:6px;margin-top:3px;">潮流男装</p>
       			<p style="color:#949494;margin-bottom:6px;">超大精准流量，日销迅速翻倍的捷径</p>
       			<p style="font-size:14px;margin-bottom:6px;">关键字：快速打爆  超大精准流</p>
       			<p style="font-size:14px;margin-bottom:6px;">活动时间： 长期有效</p>
       		</div>
       		<button type="button" data_value="3" class="btn btn-info" id="btn-query" style="margin-left: 19%;margin-top: 8%;">立即报名</button>
    		</div>
    		</td>
           </tr>           
          <tr type="4">
          	<td>
            <div class="no-check" style="padding:15px;">
    		<div class='width-img' style="height:105px;"><img src='${pageContext.request.contextPath}/static/images/dmth.png' height='110' width='180'></div>
        	<div class="width-txt" style="padding-left:72px; width:58%">
        		<p style="font-size:17px;margin-bottom:6px;margin-top:3px;">断码特惠</p>
       			<p style="color:#949494;margin-bottom:6px;">超大流量，高清仓效率，高回报，快速清仓处理品牌商家断码货</p>
       			<p style="font-size:14px;margin-bottom:6px;">关键字：尾货清仓  大流量  转化高</p>
       			<p style="font-size:14px;margin-bottom:6px;">活动时间： 长期有效</p>
       		</div>
       		<button type="button" data_value="4" class="btn btn-info" id="btn-query" style="margin-left: 19%;margin-top: 8%;">立即报名</button>
    		</div>
    		</td>
           </tr>
          <tr type="5">
          	<td>
            <div class="no-check" style="padding:15px;">
    		<div class='width-img' style="height:105px;"><img src='${pageContext.request.contextPath}/static/images/ydxf.png' height='110' width='180'></div>
        	<div class="width-txt" style="padding-left:72px; width:58%">
        		<p style="font-size:17px;margin-bottom:6px;margin-top:3px;">运动鞋服</p>
       			<p style="color:#949494;margin-bottom:6px;">超大精准流量，日销迅速翻倍的捷径</p>
       			<p style="font-size:14px;margin-bottom:6px;">关键字：转化大  超大精准流</p>
       			<p style="font-size:14px;margin-bottom:6px;">活动时间： 长期有效</p>
       		</div>
       		<button type="button" data_value="5" class="btn btn-info" id="btn-query" style="margin-left: 19%;margin-top: 8%;">立即报名</button>
    		</div>
    		</td>
           </tr>
          <tr type="6">
          	<td>
            <div class="no-check" style="padding:15px;">
    		<div class='width-img' style="height:105px;"><img src='${pageContext.request.contextPath}/static/images/clmz.png' height='110' width='180'></div>
        	<div class="width-txt" style="padding-left:72px; width:58%">
        		<p style="font-size:17px;margin-bottom:6px;margin-top:3px;">潮流美妆</p>
       			<p style="color:#949494;margin-bottom:6px;">超大精准流量，日销迅速翻倍的捷径</p>
       			<p style="font-size:14px;margin-bottom:6px;">关键字：个护 超大精准流</p>
       			<p style="font-size:14px;margin-bottom:6px;">活动时间： 长期有效</p>
       		</div>
       		<button type="button" data_value="6" class="btn btn-info" id="btn-query" style="margin-left: 19%;margin-top: 8%;">立即报名</button>
    		</div>
    		</td>
           </tr>
          <tr type="7">
          	<td>
            <div class="no-check" style="padding:15px;">
    		<div class='width-img' style="height:105px;"><img src='${pageContext.request.contextPath}/static/images/spcs.png' height='110' width='180'></div>
        	<div class="width-txt" style="padding-left:72px; width:58%">
        		<p style="font-size:17px;margin-bottom:6px;margin-top:3px;">食品超市</p>
       			<p style="color:#949494;margin-bottom:6px;">超大精准流量，日销迅速翻倍的捷径</p>
       			<p style="font-size:14px;margin-bottom:6px;">关键字：转化大  超大精准流</p>
       			<p style="font-size:14px;margin-bottom:6px;">活动时间： 长期有效</p>
       		</div>
       		<button type="button" data_value="7" class="btn btn-info" id="btn-query" style="margin-left: 19%;margin-top: 8%;">立即报名</button>
    		</div>
    		</td>
           </tr> 
           <!--XG1530 每日好店  -->     
          <tr type="8">
          	<td>
            <div class="no-check" style="padding:15px;">
    		<div class='width-img' style="height:105px;"><img src='${pageContext.request.contextPath}/static/images/mrhd.png' height='110' width='180'></div>
        	<div class="width-txt" style="padding-left:72px; width:58%">
        		<p style="font-size:17px;margin-bottom:6px;margin-top:3px;">每日好店</p>
       			<p style="color:#949494;margin-bottom:6px;">超大精准流量，日销迅速翻倍的捷径</p>
       			<p style="font-size:14px;margin-bottom:6px;">关键字：转化大  超大精准流</p>
       			<p style="font-size:14px;margin-bottom:6px;">活动时间： 长期有效</p>
       		</div>
       		<button type="button" data_value="8" class="btn btn-info" id="btn-query" style="margin-left: 19%;margin-top: 8%;">立即报名</button>
    		</div>
    		</td>
           </tr> 

           <!--XG1530 大学生创业 -->   
          <tr type="9">
          	<td>
            <div class="no-check" style="padding:15px;">
    		<div class='width-img' style="height:105px;"><img src='${pageContext.request.contextPath}/static/images/dxscy.png' height='110' width='180'></div>
        	<div class="width-txt" style="padding-left:72px; width:58%">
        		<p style="font-size:17px;margin-bottom:6px;margin-top:3px;">大学生创业</p>
       			<p style="color:#949494;margin-bottom:6px;">超大精准流量，日销迅速翻倍的捷径</p>
       			<p style="font-size:14px;margin-bottom:6px;">关键字：转化大  超大精准流</p>
       			<p style="font-size:14px;margin-bottom:6px;">活动时间： 长期有效</p>
       		</div>
       		<button type="button" data_value="9" class="btn btn-info" id="btn-query" style="margin-left: 19%;margin-top: 8%;">立即报名</button>
    		</div>
    		</td>
           </tr>
		<!--XG1530 领券中心 -->
		<tr type="10">
			<td>
				<div class="no-check" style="padding:15px;">
					<div class='width-img' style="height:105px;"><img src='${pageContext.request.contextPath}/static/images/lqzx.png' height='110' width='180'></div>
					<div class="width-txt" style="padding-left:72px; width:58%">
						<p style="font-size:17px;margin-bottom:6px;margin-top:3px;">领券中心</p>
						<p style="color:#949494;margin-bottom:6px;">促销越大，销量越大</p>
						<p style="font-size:14px;margin-bottom:6px;">关键字：优惠  销量高</p>
						<p style="font-size:14px;margin-bottom:6px;">活动时间： 长期有效</p>
					</div>
					<button type="button" data_value="10" class="btn btn-info" id="btn-query" style="margin-left: 19%;margin-top: 8%;">立即报名</button>
				</div>
			</td>
		</tr>
		<tr type="11">
			<td>
				<div class="no-check" style="padding:15px;">
					<div class='width-img' style="height:105px;"><img src='${pageContext.request.contextPath}/static/images/jfzp.png' height='110' width='180'></div>
					<div class="width-txt" style="padding-left:72px; width:58%">
						<p style="font-size:17px;margin-bottom:6px;margin-top:3px;">积分转盘</p>
						<p style="color:#949494;margin-bottom:6px;">积分转盘让醒购用户可以通过积分消耗抽取商品，拥有可观的曝光度与关注度，适合商家快速引流和爆款打造。</p>
						<p style="font-size:14px;margin-bottom:6px;">关键字：全品类  爆款曝光</p>
						<p style="font-size:14px;margin-bottom:6px;">活动时间： 长期有效</p>
					</div>
					<button type="button" data_value="11" class="btn btn-info" id="btn-query" style="margin-left: 19%;margin-top: 8%;">立即报名</button>
				</div>
			</td>
		</tr>
	</table>
<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>

<!-- Bootstrap -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script>
	$(function(){	
		
	       $(".no-check").mouseover(function () {
		    $(this).css("background-color","rgb(249, 249, 249)");
	        }).mouseout(function () {
	            $(this).css("background-color","");
	        });
	       
		var showSingleActivity1 = '${showSingleActivity1}';
		if(showSingleActivity1 == "0"){
			$("tr[type='1']").hide();
		}
		var showSingleActivity2 = '${showSingleActivity2}';
		if(showSingleActivity2 == "0"){
			$("tr[type='2']").hide();
		}
		var showSingleActivity3 = '${showSingleActivity3}';
		if(showSingleActivity3 == "0"){
			$("tr[type='3']").hide();
		}
		var showSingleActivity4 = '${showSingleActivity4}';
		if(showSingleActivity4 == "0"){
			$("tr[type='4']").hide();
		}
		var showSingleActivity5 = '${showSingleActivity5}';
		if(showSingleActivity5 == "0"){
			$("tr[type='5']").hide();
		}
		var showSingleActivity6 = '${showSingleActivity6}';
		if(showSingleActivity6 == "0"){
			$("tr[type='6']").hide();
		}
		var showSingleActivity7 = '${showSingleActivity7}';
		if(showSingleActivity7 == "0"){
			$("tr[type='7']").hide();
		}
		var showSingleActivity8 = '${showSingleActivity8}';
		if(showSingleActivity8 == "0"){
			$("tr[type='8']").hide();
		}
		var showSingleActivity9 = '${showSingleActivity9}';
		if(showSingleActivity9 == "0"){
			$("tr[type='9']").hide();
		}
		var showSingleActivity10 = '${showSingleActivity10}';
		if(showSingleActivity10 == "0"){
			$("tr[type='10']").hide();
		}
		var showSingleActivity11 = '${showSingleActivity11}';
		if(showSingleActivity11 == "0"){
			$("tr[type='11']").hide();
		}
	});

	$("[id = btn-query]").click(function(){
		var type = $(this).attr("data_value");
		getContent("${ctx}/market/signUpImmediately?activityType="+type);
	});
	   
    function enrolledProduct() {
        getContent("${ctx}/market/enrolledProduct");
    }
</script>
</body>
</html>
