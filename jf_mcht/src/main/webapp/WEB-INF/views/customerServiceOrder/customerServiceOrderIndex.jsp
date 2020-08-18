<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>订单管理</title>
<link href="${pageContext.request.contextPath}/static/css/subOrder.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.nav.q>li>a {
			width: 116px;
			height: 36px;
			line-height: 36px;
			margin: 0 0 -1px;
			padding: 0;
			border: 1px solid #ddd;
			text-align: center;
			border-radius: 0;
			border-left: none;
			border-bottom: none;
			color: #333;
		}

		.batchButton{
			height: 20px;
			margin: 0 10px 0 0;
			padding: 0 10px;
			border-radius: 1px;
			background: #3c9eff;
			color: #fff;
			font-size: 12px;
			box-sizing: content-box;
		}

	</style>
</head>
<body>
	<div class="x_panel container-fluid sh-gl">
		<div class="row content-title">
			<div class="t-title">
				售后管理
			</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<input type="hidden" name="searchOrderType" id="searchOrderType" value="${searchOrderType}">
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">售后编号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_orderCode" name="search_orderCode" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">订单编号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_subOrderCode" name="search_subOrderCode" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">联系电话：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_receiverPhone" name="search_receiverPhone" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">退货快递单号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_memberExpressNo" name="search_memberExpressNo" >
					</div>
				</div>
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">商品ID：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_productId" name="search_productId" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">品牌名称：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_brandId" id="search_brandId">
						   <option value="">--请选择--</option>
						   <c:forEach var="productBrand" items="${productBrandList}">
						   		<option value="${productBrand.id}">${productBrand.name}</option>
						   </c:forEach>
                          </select>
					</div>
					<!--  
					<label for="productBrand" class="col-md-1 control-label search-lable">客户寄件：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_" id="search_">
						   <option value="">--请选择--</option>
						   
                           </select>
					</div>
					-->
					<label for="productBrand" class="col-md-1 control-label search-lable">发货快递单号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_expressNo" name="search_expressNo" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">收货人：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_receiverName" name="search_receiverName" >
					</div>
				</div>
				
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">活动ID：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_activityId" name="search_activityId" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">售后类型：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_serviceType" id="search_serviceType">
						   <option value="">--请选择--</option>
						   <c:forEach var="customerServiceType" items="${customerServiceTypes}">
						   		<option value="${customerServiceType.statusValue}">${customerServiceType.statusDesc}</option>
						   </c:forEach>
                          </select>
					</div>
				
					<label for="productBrand" class="col-md-1 control-label search-lable">申请时间：</label>
					<div class="col-md-5 search-condition">
						 <input class="form-control datePicker" type="text"  id="createTimeBegin" name="createTimeBegin" data-date-format="yyyy-mm-dd">
						 <i class="picker-split">-</i>
						 <input class="form-control datePicker" type="text"  id="createTimeEnd" name="createTimeEnd" data-date-format="yyyy-mm-dd">
					</div>
					<div class="col-md-3 text-center search-btn">
						<button type="button"  class="btn btn-info" id="btn-query">搜索</button>
						<button type="button"  class="btn btn-info" id="btn-out">导出</button>
					</div>
				</div>
			</form>
		</div>
  <!-- Nav tabs -->
  <ul class="nav nav-tabs q" role="tablist">
    <li role="presentation" <c:if test="${searchOrderType =='0'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('0');" id="refundBeforeDelivery">发货前退款</a></li>
    <li role="presentation" <c:if test="${searchOrderType =='1'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('1');" id="returnOrExchangeApply">退换货申请</a></li>
    <li role="presentation" <c:if test="${searchOrderType =='7'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('7');" id="waitMemberSend">等待客户寄件</a></li>
    <li role="presentation" <c:if test="${searchOrderType =='2'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('2');" id="returnOrExchangeReceive">退货待收件</a></li>
    <li role="presentation" <c:if test="${searchOrderType =='3'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('3');" id="exchangeGoodsToSend">换货待收发件</a></li>
    <li role="presentation" <c:if test="${searchOrderType =='4'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('4');" id="mchtClose">售后完成</a></li>
	  <%--<c:if test="${mchtCode eq 'P193137' or mchtCode eq 'P193162' or mchtCode eq 'P193022'or mchtCode eq 'P193068'or mchtCode eq 'P193293'or mchtCode eq 'P193231'or mchtCode eq 'P192688' or mchtCode eq 'S170042'}">
	  <li role="presentation" <c:if test="${searchOrderType =='5'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('5');" id="directCompensation">商家直赔</a></li>
	  </c:if>--%>
    <li role="presentation" <c:if test="${searchOrderType =='6'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('6');" id="allOrder">全部售后</a></li>
  </ul>

<!--   Tab panes -->
<div class="x_content container-fluid">
	<div class="row">
		<div class="col-md-12">
    		<c:if test="${searchOrderType eq '0'}">
			<div class="panel panel" id="progressBar" style="height: 66px;padding-top:10px;box-sizing: border-box;line-height: 10px;">
	    		<div class="panel-body">
	    			当前进度：客户已申请退款 >><span style="color: red;"> 等待商家同意退款  </span>（同意之后，金额由平台操作退款给客户，请商家不要重复支付）
	    		</div>
				<br>
				<span style="margin-right: 20px">
						<input type="checkbox" class="checkAll" onclick="checkAll(this,1);" value="ccc">全选
				</span>
				<button class="btn btn-info" id="batchRefund">批量同意退款</button>
			</div>
    		</c:if>
    		<c:if test="${searchOrderType eq '1'}">
    		<div class="panel panel" id="progressBar" style="height: 66px;padding-top:10px;box-sizing: border-box;line-height: 22px;">
	    		<div class="panel-body">
	    			当前进度：客户已申请退货和换货 >> <span style="color: red;">等待商家同意申请 </span>（同意之后，客户将寄回原货品，请商家注意查收;<span style="color: red">或同意后直接退款（仅对缺货或未发货的商品）</span>）<br>
				</div>
					<div>
						<span style="margin-right: 20px">
						<input type="checkbox" class="checkAll" onclick="checkAll(this,1);" value="ccc">全选
						</span>
						<button class="btn btn-info" id="batchReturn">批量同意退换货</button>
					</div>
	    	</div>
    		</c:if>
		</div>
    	<div class="col-md-12 datatable-container">
			<table id="dataTable" class="o table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info" style="table-layout: fixed;">
							<thead>
								<tr role="row">
									<th>商品</th>
									<th>售后数量</th>
									<th>售后类型</th>
									<th>剩余处理时间</th>
									<th>售后状态</th>
									<th>实付金额</th>
									<c:if test="${searchOrderType == '0' or searchOrderType == '1' or searchOrderType == '2'or searchOrderType == '3'or searchOrderType == '7'}">
									<th>操作</th>
									</c:if>
								</tr>
							</thead>
							<tbody id="tbody1">

							</tbody>
			 </table>
		</div>
	</div>
</div>
</div>


	<!--弹出批量退款Div-->
	<div class="video_box" style="position:fixed; width:400px; height:155px; top:30%; left:40%; display: none;border-radius: 2px;" id="batchRefundDiv">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					批量退款确认
				</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<p style="color: gray;">同意之后，金额由平台操作退款给客户，请不要重复支付。</p>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-md-7">
						<input type="hidden" id="batchRefundIds" value="" name="batchRefundIds">
						<span><button type="button" class="btn btn-default" onclick="confirmBatchRefund()"  style="font-size: large; ">同意</button></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close" style="font-size: large; ">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--弹出批量退还货Div-->
	<div class="video_box" style="position:fixed; width:400px; height:155px; top:30%; left:40%; display: none;border-radius: 2px;" id="batchReturnDiv">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					批量退换货确认
				</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<p style="color: gray;">同意之后，客户将寄回原货品,请商家注意查收(退回的地址为同意时商品设置的售后地址)。</p>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-md-7">
						<input type="hidden" id="batchReturnIds" value="" name="batchReturnIds">
						<span><button type="button" class="btn btn-default" onclick="confirmBatchReturn()" style="font-size: large;">同意</button></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close" style="font-size: large;">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>




		
<!-- 	售后详情 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>

<!-- 	订单详情 -->

<div class="modal fade" id="subOrderViewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>

	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script>
	function leftTimer(object,endTime,desc,title){
		 var nowDate = new Date();
		 var nowTime = nowDate.getTime();
		 if(endTime > nowTime){
			var leftTime = endTime - nowTime;//计算剩余的毫秒数 
			var days = parseInt(leftTime / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数 
			var hours = parseInt(leftTime / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
			var minutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟 
			days = checkTime(days); 
			hours = checkTime(hours); 
			minutes = checkTime(minutes);
			setTimeout(function() { 
				leftTimer(object,endTime,desc,title); 
			},1000)
			if(title){
			    $(object).html(title+'<span style="color: red;">'+days+'</span> 天 <span style="color: red;">'+hours+'</span> 时<span style="color: red;">'+minutes+'</span>分钟<br>'+desc);
			}else{
			    $(object).html('<span style="color: red;">'+days+'</span> 天 <span style="color: red;">'+hours+'</span> 时<span style="color: red;">'+minutes+'</span>分钟<br>'+desc);
			}
		 }else{
			$(object).html('<span style="color: red;">已超时,系统已自动处理</span>'); 
		 }
	}	
	function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
		 if(i<10){ 
		  	i = "0" + i;
		  } 
		 	return i; 
	}

		function checkAll(input,index){
			if($(input).prop("checked")){
				$("#tbody"+index).find("input[name='customerServiceOrderId']").each(function(){
					$(this).prop("checked",true);
				});
			}else{
				$("#tbody"+index).find("input[name='customerServiceOrderId']").each(function(){
					$(this).prop("checked",false);
				});
			}
		}

		$("#batchRefund").unbind('click').on('click',function(){
			if($("input[name='customerServiceOrderId']:checked").length==0){
				swal("请先选中退款单");
				return false;
			}
			var idsArray = [];
			$("input[name='customerServiceOrderId']:checked").each(function(){
				idsArray.push($(this).val());
			});
			var ids = idsArray.join(",");
			viewBatchRefund(ids);
		});

		function viewBatchRefund(ids){
			$("#batchRefundIds").val(ids);
			$("#batchRefundDiv").modal();
		}

		$(".video_close").on('click',function(){
			$("#batchRefundDiv").hide();
			$("#batchReturnDiv").hide();
			$("#returnGoodsBlackBox").hide();
		});

		$("button[name='cancle']").on('click',function(){
			$("#batchRefundDiv").hide();
			$("#batchReturnDiv").hide();
			$("#returnGoodsBlackBox").hide();
		});


	function confirmBatchRefund(){
		var proStatus = "A2";
		var content = "<span>同意退款，请耐心等待。</span>";
		$.ajax({
			url : "${ctx}/customerServiceOrder/saveBatchRefund",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"customerServiceOrderIds":$("#batchRefundIds").val(),"proStatus":proStatus,"content":content},
/*
			timeout : 60000,
*/
			success : function(data) {
				if (data.returnCode=="0000") {
					swal({
						title:"成功"+data.returnData.successCount+"单，失败"+data.returnData.errorCount+"单(退款单已取消或已同意)",
						type: "success",
						confirmButtonText: "确定"
					},function(){
						table.ajax.reload( null, false );
					});
					$("#batchRefundDiv").modal('hide');
				} else {
					swal({
						title: data.returnMsg,
						type: "error",
						confirmButtonText: "确定"
					},function(){
						$("#batchRefundDiv").on("hide.bs.modal",function() {
							table.ajax.reload( null, false );
						});
					});
				}
			},
			error : function() {
				swal({
					title: "提交失败！",
					type: "error",
					timer: 1500,
					confirmButtonText: "确定"
				},function(){
				});
			}
		});
	}

	//批量退换货
	$("#batchReturn").unbind('click').on('click',function(){
		if($("input[name='customerServiceOrderId']:checked").length==0){
			swal("请先选中退换货单");
			return false;
		}
		var idsArray = [];
		$("input[name='customerServiceOrderId']:checked").each(function(){
			idsArray.push($(this).val());
		});
		var ids = idsArray.join(",");
		viewBatchReturn(ids);
	});

	function viewBatchReturn(ids){
		$("#batchReturnIds").val(ids);
		$("#batchReturnDiv").modal();
	}


	function confirmBatchReturn(){
		$.ajax({
			url : "${ctx}/customerServiceOrder/savaBatchReturn",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"customerServiceOrderIds":$("#batchReturnIds").val()},
/*
			timeout : 60000,
*/
			success : function(data) {
				if (data.returnCode=="0000") {
					swal({
						title:"成功"+data.returnData.successCount+"单，失败"+data.returnData.errorCount+"单(售后单已取消或已同意)",
						type: "success",
						confirmButtonText: "确定"
					},function(){
						table.ajax.reload( null, false );
					});
					$("#batchReturnDiv").modal('hide');
				} else {
					swal({
						title: data.returnMsg,
						type: "error",
						confirmButtonText: "确定"
					},function(){
						$("#batchReturnDiv").on("hide.bs.modal",function() {
							table.ajax.reload( null, false );
						});
					});
				}
			},
			error : function() {
				swal({
					title: "提交失败！",
					type: "error",
					timer: 1500,
					confirmButtonText: "确定"
				},function(){
				});
			}
		});
	}









var table;
$(document).ready(function () {
	
	$('.datePicker').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
    	    }
    );
	
	table = $('#dataTable').dataTable({
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
                url: '${ctx}/customerServiceOrder/getCustomerServiceOrderList',
                data: param,
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                    var returnData = {};
                    returnData.recordsTotal = result.returnData.Total;
                    returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.returnData.Rows;
                    callback(returnData);
                    if(result.returnData.refundBeforeDeliveryCount>0){
	                    $("#refundBeforeDelivery").text("发货前退款("+result.returnData.refundBeforeDeliveryCount+")");
                    }
                    if(result.returnData.returnOrExchangeApplyCount>0){
	                    $("#returnOrExchangeApply").text("退换货申请("+result.returnData.returnOrExchangeApplyCount+")");
                    }
                    if(result.returnData.returnOrExchangeReceiveCount>0){
	                    $("#returnOrExchangeReceive").text("退货待收件("+result.returnData.returnOrExchangeReceiveCount+")");
                    }
                    if(result.returnData.exchangeGoodsToSendCount>0){
	                    $("#exchangeGoodsToSend").text("换货待收发件("+result.returnData.exchangeGoodsToSendCount+")");
                    }
                    if(result.returnData.returnOrExchangeWaitMemberSendCount>0){
	                    $("#waitMemberSend").text("等待客户寄件("+result.returnData.returnOrExchangeWaitMemberSendCount+")");
                    }
                    
                    $("td[name='dealTimeA1']").each(function(){
                    	var updatedate = $(this).data("updatedate");
						var endTime = updatedate+2*24*3600*1000;
						var desc = "后自动同意退款";
                    	var status = $(this).data("status");
                    	if(status == "0"){
							leftTimer(this,endTime,desc);
                    	}
					});
                    
                    $("td[name='dealTimeB1']").each(function(){
						var updatedate = $(this).data("updatedate");
						var endTime = updatedate+2*24*3600*1000;
						var desc = "后自动同意退货";
						var status = $(this).data("status");
						if(status == "0"){
							leftTimer(this,endTime,desc);
                    	}
					});
                    
                    $("td[name='dealTimeC1']").each(function(){
						var updatedate = $(this).data("updatedate");
						var endTime = updatedate+2*24*3600*1000;
						var desc = "";
						var status = $(this).data("status");
						if(status == "0"){
							leftTimer(this,endTime,desc);
                    	}
					});
                    
                    $("td[name='dealTimeB4']").each(function(){
						var updatedate = $(this).data("updatedate");
						var endTime = updatedate+15*24*3600*1000;
						var title="剩余处理时间：";
						var desc = "若超期未处理将退款给买家。";
						var status = $(this).data("status");
						if(status == "0"){
							leftTimer(this,endTime,desc);
                    	}
					});
                    
                    $("td[name='dealTimeC4']").each(function(){
						var updatedate = $(this).data("updatedate");
						var endTime = updatedate+15*24*3600*1000;
						var title="剩余处理时间：";
						var desc = "若超期未处理将退款给买家。";
						var status = $(this).data("status");
						if(status == "0"){
							leftTimer(this,endTime,desc);
                    	}
					});
                    
                    $("td[name='dealTimeB2']").each(function(){
						var updatedate = $(this).data("updatedate");
						var endTime = updatedate+7*24*3600*1000;
						var title="客户寄件剩余时间<br>";
						var desc = "";
						var status = $(this).data("status");
						if(status == "0"){
							leftTimer(this,endTime,desc,title);
                    	}
					});
                    
                    $("td[name='dealTimeC2']").each(function(){
						var updatedate = $(this).data("updatedate");
						var endTime = updatedate+7*24*3600*1000;
						var title="客户寄件剩余时间<br>";
						var desc = "";
						var status = $(this).data("status");
						if(status == "0"){
							leftTimer(this,endTime,desc,title);
                    	}
					});
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
        	var customerServiceOrder = data;
        	var createDate = new Date(customerServiceOrder.createDate).format("yyyy-MM-dd hh:mm:ss");
           	var orderDtl = customerServiceOrder.orderDtl;
          	var rowtext=[];
			var blackMemberRemark="";
			//黑名单订单
			if(customerServiceOrder.memberStatus=='P'){
				blackMemberRemark = '&nbsp;&nbsp;<span style="color:red;">（用户异常）</span>';
			}
          	rowtext.push('<td colspan="8">');
          	var addressTypeDesc="";
          	if(customerServiceOrder.addressType == 1){
          		addressTypeDesc='&nbsp;&nbsp;<span style="color:red;">退往供应商</span>';
          	}
          	rowtext.push('<div class="order info1"><input type="checkbox" name="customerServiceOrderId" value="'+customerServiceOrder.id+'"><span>售后编号：'+customerServiceOrder.orderCode+'</span>&nbsp;&nbsp;<span>申请时间：'+createDate+'</span>&nbsp;&nbsp;<span>原订单编号：<a href="javascript:;" onclick="viewSubOrder('+customerServiceOrder.subOrderId+');">'+customerServiceOrder.subOrderCode+'</a></span>'+blackMemberRemark+''+addressTypeDesc+'</div>');
          	rowtext.push('<table class="order-info3">');
          	rowtext.push('<tr>');
          	rowtext.push('<td>');
           	rowtext.push('<table>');
           	rowtext.push('<tr>');
           	rowtext.push('<td width="" class="br">');
           	if(customerServiceOrder.serviceType == "D"){
           		rowtext.push("<div class='no-check'>");
	      		rowtext.push("<div class='width-60'>"+"<img src='${ctx}/file_servelt'>"+"</div>");
	      		rowtext.push("<div class='width-226'>商家直接替客户创建赔付，创建后由平台操作退款<br><span style='color:grey;'>请商家不要重复支付</span></div>");
	      		rowtext.push("<div>");
           	}else{
           		if(orderDtl){
           			var deliveryHtml = "";
           			if(orderDtl.deliveryStatus == "0"){
           				deliveryHtml = "<a style='color:red;'>未发货</a>";
           			}
           			var outOfStockHtml = "";
           			if(orderDtl.markedOutOfStock == "1"){
           				outOfStockHtml = "<a style='color:red;'>已标记为缺货</a>";
           			}
		      		rowtext.push("<div class='no-check'>");
		      		rowtext.push("<div class='width-60'>"+"<img src='${ctx}/file_servelt"+orderDtl.skuPic+"'>"+"</div>");
		      		rowtext.push("<div  class='width-226'><p class='ellipsis'>"+orderDtl.productName+"</p><p>规格:"+orderDtl.productPropDesc+"</p><p>SKU码:"+orderDtl.sku+"&nbsp;&nbsp;"+deliveryHtml+"&nbsp;&nbsp;"+outOfStockHtml+"</p></div>");
		      		
		      		rowtext.push("<div>");
           		}
           	}
           	rowtext.push('</td>');
           	rowtext.push('<td width="77" class="br">');
        	if(customerServiceOrder.quantity && customerServiceOrder.quantity>0){
	        	rowtext.push(customerServiceOrder.quantity);
        	}
           	rowtext.push('</td>');
           	rowtext.push('<td width="87" class="br">');
        	rowtext.push(customerServiceOrder.serviceTypeDesc);


        	if (customerServiceOrder.serviceType=="B") {
        		if (customerServiceOrder.orderDtl.deliveryStatus=="0" || customerServiceOrder.orderDtl.markedOutOfStock=="1"){
				rowtext.push("<br><span style='color: red'>(仅退款)</span> ")
        		}
			}
           	rowtext.push('<br><a href="javascript:;" onclick="viewCustomerServiceOrder('+customerServiceOrder.id+');">详情</a>');
           	rowtext.push('</td>');
           	
           	var dealTime;
          	if(customerServiceOrder.proStatus=="A1"){
          		dealTime = "A1";
          	}else if(customerServiceOrder.proStatus=="B1"){
          		dealTime = "B1";
          	}else if(customerServiceOrder.proStatus=="B2"){
          		dealTime = "B2";
          	}else if(customerServiceOrder.proStatus=="C1"){
          		dealTime = "C1";
          	}else if(customerServiceOrder.proStatus=="C2"){
          		dealTime = "C2";
          	}else if(customerServiceOrder.proStatus=="B4"){
          		dealTime = "B4";
          	}else if(customerServiceOrder.proStatus=="C4"){
          		dealTime = "C4";
          	}
          	rowtext.push('<td width="119" class="br" name="dealTime'+dealTime+'" data-status="'+customerServiceOrder.status+'" data-updatedate="'+customerServiceOrder.updateDate+'"></td>');
           	
           	rowtext.push('</tr>');
           	rowtext.push('</table>');
          	rowtext.push('</td>');
          	if(customerServiceOrder.serviceType ){
	          	rowtext.push('<td width="88" class="br">'+customerServiceOrder.statusDesc+'<br>'+customerServiceOrder.proStatusDesc+'</td>');
          	}/*else{
	          	rowtext.push('<td width="88" class="br">进行中<br>商家已同意退款</td>');
          	}*/
          	if(customerServiceOrder.serviceType != "D" && orderDtl){
          		rowtext.push('<td width="77" class="br">'+orderDtl.payAmount+'<br>');
          		if(orderDtl.mchtPreferential){
          			rowtext.push('商家优惠：'+orderDtl.mchtPreferential+'<br>');
          		}
          		if(orderDtl.platformPreferential){
          			rowtext.push('平台优惠：'+orderDtl.platformPreferential+'<br>');
          		}
          		if(orderDtl.integralPreferential){
          			rowtext.push('积分优惠：'+orderDtl.integralPreferential);
          		}
          		rowtext.push('</td>');
          	}else{
          		rowtext.push('<td width="77" class="br"></td>');
          	}

			<c:if test="${searchOrderType == '0' or searchOrderType == '1' or searchOrderType == '2'or searchOrderType == '3'or searchOrderType == '7'}">
			rowtext.push('<td width="87" class="br">');
			rowtext.push('<a href="javascript:;" onclick="viewCustomerServiceOrder('+customerServiceOrder.id+');">处理</a>');
			rowtext.push('</td>');
			</c:if>
          	
          	rowtext.push('</tr>');
          	rowtext.push('</table>');
          	
          	rowtext.push('</td>' );
            $(row).html(rowtext.join(""));
            },
        
        
        "columns": [
			<c:if test="${searchOrderType == '4' or searchOrderType == '6'}">
			{"data": "id","width":"476"},
			</c:if>
			<c:if test="${searchOrderType == '0' or searchOrderType == '1' or searchOrderType == '2'or searchOrderType == '3'or searchOrderType == '7'}">
			{"data": "id","width":"390"},
			</c:if>
			{"data": "id","width":"78"},
			{"data": "id","width":"88"},
			{"data": "id","width":"130"},
			{"data": "id","width":"88"},
			{"data": "id","width":"78"},
			<c:if test="${searchOrderType == '0' or searchOrderType == '1' or searchOrderType == '2'or searchOrderType == '3'or searchOrderType == '7'}">
			{"data": "id","width":"78"}
			</c:if>
        ]
    }).api();
});

function viewCustomerServiceOrder(id){
		$.ajax({
	        url: "${ctx}/customerServiceOrder/customerServiceOrderView?id="+id, 
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

function viewSubOrder(id){
	$.ajax({
        url: "${ctx}/subOrder/subOrderView?id="+id, 
        type: "GET", 
        success: function(data){
            $("#subOrderViewModal").html(data);
            $("#subOrderViewModal").modal();
        },
	    error:function(){
	    	swal('页面不存在');
	    }
	});

}

function list(searchOrderType) {
    var url = "${ctx}/customerServiceOrder/customerServiceOrderIndex?searchOrderType=" + searchOrderType;
    getContent(url);
}

$('#btn-query').on('click', function (event) {
    table.ajax.reload();
});

$("#btn-out").on('click',function(){
	var createTimeBegin = $("#createTimeBegin").val();
	var createTimeEnd = $("#createTimeEnd").val();
	if(!createTimeBegin || !createTimeEnd){
		swal('申请时间不能为空');
		return false;
	}
	var searchOrderType = $("#searchOrderType").val();
	var search_receiverName = $("#search_receiverName").val();
	var search_subOrderCode = $("#search_subOrderCode").val();
	var search_receiverPhone = $("#search_receiverPhone").val();
	var search_productId = $("#search_productId").val();
	var search_brandId = $("#search_brandId").val();
	var search_expressNo = $("#search_expressNo").val();
	var search_memberExpressNo = $("#search_memberExpressNo").val();
	var search_activityId = $("#search_activityId").val();
	var search_serviceType = $("#search_serviceType").val();
	var createTimeBegin = $("#createTimeBegin").val();
	var createTimeEnd = $("#createTimeEnd").val();
	location.href="${ctx}/customerServiceOrder/exportCustomerServiceOrder?searchOrderType="+searchOrderType+"&search_brandId="+search_brandId+"&search_productId="+search_productId+"&search_activityId="+search_activityId+"&search_expressNo="+search_expressNo+"&createTimeBegin="+createTimeBegin+"&createTimeEnd="+createTimeEnd+"&search_receiverName="+search_receiverName+"&search_receiverPhone="+search_receiverPhone+"&search_subOrderCode="+search_subOrderCode+"&search_memberExpressNo="+search_memberExpressNo+"&search_serviceType="+search_serviceType;
});
</script>
</body>
</html>