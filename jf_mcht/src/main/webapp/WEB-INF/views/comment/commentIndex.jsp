<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>订单管理</title>
<link href="${pageContext.request.contextPath}/static/css/subOrder.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
<link href="${pageContext.request.contextPath}/static/css/star.css" rel="stylesheet" type="text/css" />
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

.test-box .more {
	position: relative;
	color: blue;
	cursor: pointer;
}
.test-box .more:hover:after {
	z-index: 999;
	color: white;
	position: absolute;
	top: calc(100% + 10px);
	left: 50%;
	content: attr(data-more);
	padding: 2px 10px;
	background: rgba(0, 0, 0, .5);
	border-radius: 5px;
	word-break: keep-all;
	transform: translateX(-50%);
}
.test-box .more:before {
	z-index: 999;
	display: none;
	position: absolute;
	top: 100%;
	left: 0;
	right: 0;
	content: '';
	width: 0;
	margin: 0 auto;
	border-bottom: 10px solid rgba(0, 0, 0, .5);
	border-left: 5px solid transparent;
	border-right: 5px solid transparent;
}
.test-box .more:hover:before {
	display: block;
}

</style>
</head>

<body>
	<div class="x_panel container-fluid dd-gl">
		<div class="panel panel-default" style="margin-top: 15px;margin-left: -18px;">
    		<div class="panel-heading" style="background-color: white;font-size: 14px;font-weight: bold;height: 36px;"><span class="ff_yahei color_000000">店铺评分</span></div>
		    <div class="panel-body" style="height: 50px;">
		        <p>
		       		 商品描述：<a href="javascript:;" style="cursor: default;color: red;">${totalProductScore}</a>分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		       		 卖家服务：<a href="javascript:;" style="cursor: default;color: red;">${totalMchtScore}</a>分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		       		 物流服务：<a href="javascript:;" style="cursor: default;color: red;">${totalWuliuScore}</a>分
		        </p>
		    </div>
		</div>
		<div class="row content-title">
			<div class="t-title">
	            	商品评价
	        </div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">评价类型：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="hasPic" id="hasPic">
						   	<option value="">全部</option>
						   	<option value="1">有图</option>
						  </select>
					</div>
				
					<label for="productBrand" class="col-md-1 control-label search-lable">商品ID：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="productCode" name="productCode" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">品牌名称：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="brandId" id="brandId">
						   <option value="">--请选择--</option>
						   <c:forEach var="productBrand" items="${productBrandList}">
								<option value="${productBrand.id}">${productBrand.name}</option>
						   </c:forEach>
						  </select>
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">状态：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="isAllowModifyComment" id="isAllowModifyComment">
							   <option value="">--请选择--</option>
							   <option value="0">不允许修改评价</option>
							   <option value="1">待修改评价</option>
							   <option value="2">已修改评价</option>
						   </select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">评价时间：</label>
					<div class="col-md-5 search-condition">
						 <input class="form-control datePicker" type="text"  id="createDateBegin" name="createDateBegin" data-date-format="yyyy-mm-dd" value="${beginDate}" >
						 <i class="picker-split">-</i>
						 <input class="form-control datePicker" type="text"  id="createDateEnd" name="createDateEnd" data-date-format="yyyy-mm-dd" value="${endDate}" >
					</div>
					<label for="productBrand" class="col-md-1 control-label search-lable">下单时间：</label>
					<div class="col-md-5 search-condition">
						 <input class="form-control datePicker" type="text"  id="orderCreateDateBegin" name="orderCreateDateBegin" data-date-format="yyyy-mm-dd" >
						 <i class="picker-split">-</i>
						 <input class="form-control datePicker" type="text"  id="orderCreateDateEnd" name="orderCreateDateEnd" data-date-format="yyyy-mm-dd" >
					</div>
				</div>
				<div class="form-group">
					<label for="subOrderCode" class="col-md-1 control-label search-lable">订单编号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="subOrderCode" name="subOrderCode">
					</div>
					<label for="subOrderCode" class="col-md-1 control-label search-lable">
						<input type="checkbox" name="defaultComment" id="defaultComment" value="1" <c:if test="${defaultComment == 1}">checked="checked"</c:if>>不展示默认好评
					</label>
					<div class="col-md-3 text-center search-btn" style="padding-left: 10px;">
						<button type="button"  class="btn btn-info" id="btn-query">搜索</button>
						<button type="button"  class="btn btn-info" id="btn-reset">重置</button>
					</div>
				</div>
			</form>
		</div>
<!--   Tab panes -->
<div class="x_content container-fluid">
	<div class="row">
		<div class="col-md-12 datatable-container">
						<table id="datatable" class="o table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>商品</th>
									<th>评价</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="tbody1">
								
							</tbody>
						</table>
					</div>
	</div>
</div>

</div>
		
<!-- 	订单详情 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>

<!-- 	订单备注 -->

<div class="modal fade" id="remarksModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>


<!--弹出div-->
<div class="video_box" style="position:fixed; width:600px; height:180px; top:50%; left:50%;margin: -90px 0 0 -300px; display: none;" id="subOrderRemarksDiv">
	<a href="javascript:;" id="closeVideo" class="video_close"><img src="${ctx}/static/images/close.png"></a>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					订单备注
					<span style="display: inline-block;float: right;" id="remarksColors">
							<a href="javascript:;" name="remarksColor" data-remarkscolor="1"><span class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span></a>
							<a href="javascript:;" name="remarksColor" data-remarkscolor="2"><span class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span></a>
							<a href="javascript:;" name="remarksColor" data-remarkscolor="3"><span class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span></a>
							<a href="javascript:;" name="remarksColor" data-remarkscolor="4"><span class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span></a>
							<a href="javascript:;" name="remarksColor" data-remarkscolor="5"><span class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span></a>
							<a href="javascript:;" name="remarksColor" data-remarkscolor="6"><span class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span></a>
					</span>
				</h3>
				
			</div>
			<div class="panel-body">
				<textarea class="form-control" rows="3" id="remarks" style="width: 100%;resize: vertical;margin-bottom: 10px;">${remarks}</textarea>
				<span id="selectedRemark">
				旗帜设置为:
				<c:if test="${remarksColor eq '1'}">
					<span class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span>
				</c:if>
				<c:if test="${remarksColor eq '2'}">
					<span class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span>
				</c:if>
				<c:if test="${remarksColor eq '3'}">
					<span class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span>
				</c:if>
				<c:if test="${remarksColor eq '4'}">
					<span class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span>
				</c:if>
				<c:if test="${remarksColor eq '5'}">
					<span class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span>
				</c:if>
				<c:if test="${remarksColor eq '6'}">
					<span class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span>
				</c:if>
			</span>
			<div style="float:right;" id="remarksBtnDiv">
				<input type="hidden" id="subOrderIds" value="${ids}">
				<input type="hidden" id="remarksColor" value="${remarksColor}">
				<button type="button" class="btn btn-default" id="batchSaveRemarks">提交备注</button>
			</div>
			</div>
		</div>
		
</div>
<!--弹出Div End-->

<!--弹出立即发货Div-->
<div class="video_box" style="position:fixed; width:320px; height:170px; top:35%; left:35%; display: none;" id="deliveryDiv">
	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					立即发货
				</h3>
			</div>
			
			<form class="form-horizontal" role="form" style="margin-top: 10px;" onsubmit="return false;">
				  <input type="hidden" id="deliverySubOrderId">
				  <div class="form-group" style="padding-left: 30px;">
					<label class="col-md-3 control-label">快递名称<span style="color: red;">*</span>：</label>
					<div class="col-md-7">
						<select class="form-control" id="expressId" name="expressId">
							<option value="">--请选择--</option>
							<c:forEach var="express" items="${expressList}">
								<option value="${express.id}">${express.name}</option>
							</c:forEach>
						</select>
					</div>
				  </div>
				  <div class="form-group" style="padding-left: 30px;">
					<label class="col-md-3 control-label">快递单号<span style="color: red;">*</span>：</label>
					<div class="col-md-7">
					  <input type="text" class="form-control" id="expressNo" name="expressNo">
					</div>
				  </div>
 
				  <div class="form-group">
					<div class="col-sm-offset-4 col-md-7">
					  <button type="button" class="btn btn-default" id="delivery">立即发货</button>
					</div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出div End-->
<div class="black_box" style="display: none;"></div>
<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;"></ul>
	<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/clipboard.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script type="text/javascript">
	function viewerPic(imgPath,_this){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		var id = $(_this).data("id");
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		$("img[name='pic"+id+"']").each(function(){
			var eachImgPath = $(this).prop("src");
			if(imgPath!=eachImgPath){
				$("#viewer-pictures").append('<li><img data-original="'+eachImgPath+'" src="'+eachImgPath+'" alt=""></li>');
			}
		});
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
		var width=window.parent.document.documentElement.clientWidth;
		var height=window.parent.document.documentElement.clientHeight;
		$(".viewer-container").height(height);
		$(".viewer-container").width(width-20);
	}
	
	function toReplyComment(id){
		$.ajax({
			url: "${ctx}/comment/toReplyComment?id="+id, 
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

    function createImpeach(id){
        $.ajax({
            url: "${ctx}/merchantReport/merchantReportAdd?commentId="+id+"&subOrderId="+$("#subOrderCode"+id).text(),
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
	
var table;
var viewerPictures;
$(document).ready(function () {
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$('.datePicker').datetimepicker(
			{
				minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
				language: 'zh-CN', //汉化
				autoclose:true //选择日期后自动关闭
    	    }
	);
	
	table =	$('#datatable').dataTable({
		"ajax": function (data, callback, settings) {
			var param = $("#searchForm").serializeArray();
			param.push({"name":"pagesize","value":data.length});
			if (data.start > 0) {
				param.push({"name":"page","value":data.start/data.length+1});
			} else {
				param.push({"name":"page","value":1});
			}
			$.ajax({
				method: 'POST',
				url: '${ctx}/comment/getCommentList',
				data: param,
				dataType: 'json'
			}).done(function (result) {
				if (result.returnCode =='0000') {
					var returnData = {};
					returnData.recordsTotal = result.returnData.Total;
					returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
					returnData.data = result.returnData.Rows;
					callback(returnData);
					
// 					$("div[name='commentContent']").ellipsis({
// 						maxLine: 2
// 					});
				}
			});
		},
		"language": {"url": '${ctx}/static/cn.json'},
		"autoWidth": false,   // 禁用自动调整列宽
		"order": [[ 1, 'asc' ]],          // 取消默认排序查询,否则复选框一列会出现小箭头
		"processing": true,  // 隐藏加载提示,自行处理
		"serverSide": true,   // 启用服务器端分页
		"searching": false,   // 禁用原生搜索
		"bSort": false,
		"bServerSide": true,
		
		"rowCallback": function( row, data, index ) {
			var receiverName = data.receiverName;
			if(!receiverName){
				receiverName="";
			}
			var orderCreateDate = new Date(data.orderCreateDate).format("yyyy-MM-dd hh:mm:ss");
			var createDate = new Date(data.createDate).format("yyyy-MM-dd hh:mm:ss");
			var productStarWidth = data.productScore*24;
			var mchtStarWidth = data.mchtScore*24;
			var wuliuStarWidth = data.wuliuScore*24;
			var rowtext=[];
			rowtext.push('<td colspan="7">');
			rowtext.push('<div class="mt-10"><div class="order info1"><span>订单编号：<tag id="subOrderCode'+data.id+'">'+data.subOrderCode+'</tag></span><span>收货人：'+receiverName+'</span><span>下单时间：'+orderCreateDate+'</span><span>评价时间：'+createDate+'</span></div></div>');
			rowtext.push('<table class="order-info3">');
				rowtext.push('<tr>');
					rowtext.push('<td>');
						rowtext.push('<table>');
						rowtext.push('<tr>');
						rowtext.push('<td width="364" class="br">');
						rowtext.push("<div class='no-check'>");
						rowtext.push("<div class='width-60'><img src='${ctx}/file_servelt"+data.skuPic+"'></div>");
						if(!data.sku){
							data.sku = "";
						}
						if(data.sku){
							rowtext.push("<div class='width-226'><p class='ellipsis' title='"+data.brandName+"&nbsp;&nbsp;"+data.productName+"&nbsp;&nbsp;"+data.artNo+"'>"+data.brandName+"&nbsp;&nbsp;"+data.productName+"&nbsp;&nbsp;"+data.artNo+"</p><p><a href='javascript:;'>规格："+data.productPropDesc+"</a><br>SKU码:"+data.sku+"</p></div>");
						}else{
							rowtext.push("<div class='width-226'><p class='ellipsis' title='"+data.brandName+"&nbsp;&nbsp;"+data.productName+"&nbsp;&nbsp;"+data.artNo+"'>"+data.brandName+"&nbsp;&nbsp;"+data.productName+"&nbsp;&nbsp;"+data.artNo+"</p><p><a href='javascript:;'>规格："+data.productPropDesc+"</a></p></div>");
						}
						rowtext.push('<div style="float: left;">商品描述&nbsp;&nbsp;&nbsp;&nbsp;</div>'); 			
						rowtext.push('<div class="starBox">');
						rowtext.push('<ul class="star" >');
						rowtext.push('<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>');
						rowtext.push('</ul>');
						rowtext.push('<div class="current-rating" style="width: '+productStarWidth+'px;"></div>');
						rowtext.push('</div>');
						
						rowtext.push('<div style="float: left;clear: both;">卖家服务&nbsp;&nbsp;&nbsp;&nbsp;</div>'); 			
						rowtext.push('<div class="starBox">');
						rowtext.push('<ul class="star" >');
						rowtext.push('<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>');
						rowtext.push('</ul>');
						rowtext.push('<div class="current-rating" style="width: '+mchtStarWidth+'px;"></div>');
						rowtext.push('</div>');
						
						rowtext.push('<div style="float: left;clear: both;">物流服务&nbsp;&nbsp;&nbsp;&nbsp;</div>'); 			
						rowtext.push('<div class="starBox">');
						rowtext.push('<ul class="star" >');
						rowtext.push('<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li>');
						rowtext.push('<li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>');
						rowtext.push('</ul>');
						rowtext.push('<div class="current-rating" style="width: '+wuliuStarWidth+'px;"></div>');
						rowtext.push('</div>');
						
						rowtext.push("</div>");
						rowtext.push('</td>');
						rowtext.push('</tr>');
						rowtext.push('</table>');
					rowtext.push('</td>');
					rowtext.push('<td class="br"><div name="commentContent" style="width:422px;text-align: left;padding-left: 5px;">'+data.content+'</div>');
					for(var i=0;i<data.commentPics.length;i++){
						var commentPic = data.commentPics[i];
						rowtext.push('<div class="width-60" style="padding-left: 5px;"><img src="${ctx}/file_servelt'+commentPic.pic+'" name="pic'+data.id+'" data-index="'+i+'" data-id="'+data.id+'" onclick="viewerPic(this.src,this)"></div>');
					}
					if(data.appendCommentId){
						var betweenDaysDesc="";
						if(data.betweenDays){
							betweenDaysDesc = '<span style="color:red">'+data.betweenDays+'天后追评</span>';
						}else{
							betweenDaysDesc = '<span style="color:red">当天追评</span>';
						}
						rowtext.push('<div style="float: left;clear: both;">'+betweenDaysDesc+'</div>');
						rowtext.push('<div style="float: left;clear: both;width:422px;text-align: left;">'+data.appendContent+'</div>');
						rowtext.push('<div style="float: left;clear: both;">');
						for(var i=0;i<data.appendCommentPics.length;i++){
							var appendCommentPic = data.appendCommentPics[i];
							rowtext.push('<div class="width-60" style="padding-left: 5px;float: left;"><img src="${ctx}/file_servelt'+appendCommentPic.pic+'" name="pic'+data.id+'" data-index="'+i+'" data-id="'+data.id+'" onclick="viewerPic(this.src,this)"></div>');
						}
						rowtext.push('</div>');
					}
					if(data.mchtReplyDate){
						var mchtReplyBetweenDaysDesc="";
						if(data.mchtReplyBetweenDays){
							mchtReplyBetweenDaysDesc = '<span style="color:red">'+data.mchtReplyBetweenDays+'天后回复</span>';
						}else{
							mchtReplyBetweenDaysDesc = '<span style="color:red">当天回复</span>';
						}
						rowtext.push('<div style="float: left;clear: both;">'+mchtReplyBetweenDaysDesc+'</div>');
						rowtext.push('<div style="float: left;clear: both;width:422px;word-break: break-all;text-align: left;">'+data.mchtReply+'</div>');
					}
					rowtext.push('</td>');
					var allowModifyCommentdesc;
					if(data.isAllowModifyComment == "0"){
						allowModifyCommentdesc = '<a href="javascript:;" onclick="allowModifyComment('+data.subOrderId+');">允许修改评价</a>';
					}else if(data.isAllowModifyComment == "1"){
						allowModifyCommentdesc = "待修改评价";
					}else if(data.isAllowModifyComment == "2"){
						allowModifyCommentdesc = "已修改评价";
					}
					var replyOperateDesc = "";
					if(data.mchtReplyDate && data.mchtReply){
						replyOperateDesc ='<a href="javascript:;" onclick="deleteReply('+data.id+');">删除回复</a>';
					}else{
						replyOperateDesc ='<a href="javascript:;" onclick="toReplyComment('+data.id+');">回复评价</a>';
					}
					var impeachStatus = "";
					if(data.impeachStatus=="" || data.impeachStatus==null){
                        impeachStatus ='<a href="javascript:;" onclick="createImpeach('+data.id+');">举报</a>';
					}else{
                        if(data.impeachStatus == "0"){
                            impeachStatus = "待处理";
                        }
                        if(data.impeachStatus == "1"||data.impeachStatus == "2"||data.impeachStatus == "4"||data.impeachStatus == "5"||data.impeachStatus == "6"){
                            impeachStatus = "处理中";
                        }
                        if(data.impeachStatus == "3"){
                            impeachStatus = "驳回";
                        }
                        if(data.impeachStatus == "7"){
                            impeachStatus = "通过";
                        }
                        if(data.impeachStatus == "8"){
                            impeachStatus = "超时关闭";
                        }
					}
					rowtext.push('<td width="153"><a href="javascript:;" onclick="viewSubOrder('+data.subOrderId+');">查看订单</a><br>'+allowModifyCommentdesc+'<br>'+replyOperateDesc+'<br>'+impeachStatus+'</td>');
				rowtext.push('</tr>');
			rowtext.push('</table>');
			rowtext.push('</td>' );
			$(row).html(rowtext.join(""));
		},
		"columns": [
			{"data": "id","width":"372"},
			{"data": "id","width":"423"},
			{"data": "id","width":"153"}
		]
	}).api();
	
});

function allowModifyComment(subOrderId){
	$.ajax({
        method: 'POST',
        url: '${ctx}/subOrder/allowModifyComment',
        data: {"subOrderId":subOrderId},
        dataType: 'json'
    }).done(function (result) {
        if (result.returnCode =='0000') {
           	swal("设置成功");
           	table.ajax.reload(null, false);
        }else{
        	swal("设置失败，请稍后重试");
        }
    });
}

function viewSubOrder(id){
		$.ajax({
			url: "${ctx}/subOrder/subOrderView?id="+id, 
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

function viewComment(id){
	$.ajax({
		url: "${ctx}/comment/commentView?id="+id, 
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

function showDeliveryDiv(id){
	$("#deliveryDiv").show();
	$(".black_box").show();
	$("#deliverySubOrderId").val(id);
}

function deleteReply(id){
	swal({
		  title: "确定要删除该回复吗?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonText: "确定",
		  cancelButtonText: "取消",
		  closeOnConfirm: false
		},
		function(){
			$.ajax({
				url : "${ctx}/comment/deleteReply",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {id:id},
				timeout : 30000,
				success : function(data) {
					if (data.returnCode=="0000") {
						swal.close();
						table.ajax.reload( null, false );
					} else {
						swal({
							  title: data.returnMsg,
							  type: "error",
							  confirmButtonText: "确定"
							});
					}
					
				},
				error : function() {
					swal({
						  title: "处理失败！",
						  type: "error",
						  timer: 1500,
						  confirmButtonText: "确定"
						});
				}
			});
		});
}

$("#delivery").on('click',function(){
	var subOrderId = $("#deliverySubOrderId").val();
	var expressId = $("#expressId").val();
	var expressNo = $("#expressNo").val();
	if($.trim(expressId)==""){
		swal("快递名称必选");
		return false;
	}
	if($.trim(expressNo)==""){
		swal("快递单号不能为空");
		return false;
	}
	$.ajax({
			method: 'POST',
			url: '${ctx}/subOrder/subOrderDelivery',
			data: {"subOrderId":subOrderId,"expressId":expressId,"expressNo":expressNo},
			dataType: 'json'
		}).done(function (result) {
			if (result.returnCode =='0000') {
				$("#deliveryDiv").hide();
				$(".black_box").hide();
				$("#deliverySubOrderId").val("");
				swal("发货成功");
				table.ajax.reload();
			}else{
				if(result.returnMsg){
		        	swal(result.returnMsg);
	        	}else{
		        	swal("发货失败，请稍后重试");
	        	}
			}
		});
});

$("#expressNo").keydown(function(e){
	if(e.keyCode==13){
		var subOrderId = $("#deliverySubOrderId").val();
		var expressId = $("#expressId").val();
		var expressNo = $("#expressNo").val();
		if($.trim(expressId)==""){
			swal("快递名称必选");
			return false;
		}
		if($.trim(expressNo)==""){
			swal("快递单号不能为空");
			return false;
		}
		$.ajax({
				method: 'POST',
				url: '${ctx}/subOrder/subOrderDelivery',
				data: {"subOrderId":subOrderId,"expressId":expressId,"expressNo":expressNo},
				dataType: 'json'
			}).done(function (result) {
				if (result.returnCode =='0000') {
					$("#deliveryDiv").hide();
					$(".black_box").hide();
					$("#deliverySubOrderId").val("");
					swal("发货成功");
					table.ajax.reload();
				}else{
					if(result.returnMsg){
			        	swal(result.returnMsg);
		        	}else{
			        	swal("发货失败，请稍后重试");
		        	}
				}
			});
	}
});

$(".video_close").on('click',function(){
	$("#subOrderRemarksDiv").hide();
	$("#deliveryDiv").hide();
	$("#editExpressDiv").hide();
	$(".black_box").hide();
});



$('#btn-query').on('click', function (event) {
	var createDateBegin = $("#createDateBegin").val();
	var createDateEnd = $("#createDateEnd").val();
	if(!createDateBegin || !createDateEnd){
		swal("请选择评论时间");
		return;
	}
	table.ajax.reload();
});

$("#btn-reset").on('click',function(){
	$(':input', '#searchForm')
	.not(':button, :submit, :reset, :hidden,:radio') // 去除不需要重置的input类型
	.val('')
	.removeAttr('checked')
	.removeAttr('selected');
	var beginDate = "${beginDate}";
	var endDate = "${endDate}";
	$("#createDateBegin").val(beginDate);
	$("#createDateEnd").val(endDate);
	$("#defaultComment").prop("checked",true);
	table.ajax.reload();
});
</script>
</body>
</html>
