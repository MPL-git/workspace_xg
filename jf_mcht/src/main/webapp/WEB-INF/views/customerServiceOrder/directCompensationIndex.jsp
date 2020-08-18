<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>商家直赔</title>
<link href="${pageContext.request.contextPath}/static/css/subOrder.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="x_panel container-fluid sh-gl">
		<div class="row content-title">
			<div class="t-title">
				商家直赔管理
			</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<input type="hidden" name="searchOrderType" id="searchOrderType" value="${searchOrderType}">
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">收货人：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_receiverName" name="search_receiverName" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">订单编号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_subOrderCode" name="search_subOrderCode" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">联系电话：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_receiverPhone" name="search_receiverPhone" >
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
					
					<label for="productBrand" class="col-md-1 control-label search-lable">快递单号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_expressNo" name="search_expressNo" >
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
					</div>
				</div>
			</form>
		</div>
  <!-- Nav tabs -->
  <ul class="nav nav-tabs q" role="tablist">
    <li role="presentation" <c:if test="${searchOrderType =='0'}">class="active"</c:if>><a href="#" onclick="list('0');" role="tab" data-toggle="tab" id="refundBeforeDelivery">发货前退款</a></li>
    <li role="presentation" <c:if test="${searchOrderType =='1'}">class="active"</c:if>><a href="#" onclick="list('1');" role="tab" data-toggle="tab" id="returnOrExchangeApply">退换货申请</a></li>
    <li role="presentation" <c:if test="${searchOrderType =='2'}">class="active"</c:if>><a href="#" onclick="list('2');" role="tab" data-toggle="tab" id="returnOrExchangeReceive">退换货待收件</a></li>
    <li role="presentation" <c:if test="${searchOrderType =='3'}">class="active"</c:if>><a href="#" onclick="list('3');" role="tab" data-toggle="tab" id="exchangeGoodsToSend">换货单待发件</a></li>
    <li role="presentation" <c:if test="${searchOrderType =='4'}">class="active"</c:if>><a href="#" onclick="list('4');" role="tab" data-toggle="tab" id="mchtClose">售后完成</a></li>
    <li role="presentation" <c:if test="${searchOrderType =='5'}">class="active"</c:if>><a href="#" onclick="list('5');" role="tab" data-toggle="tab" id="directCompensation">商家直赔</a></li>
    <li role="presentation" <c:if test="${searchOrderType =='6'}">class="active"</c:if>><a href="#" onclick="list('6');" role="tab" data-toggle="tab" id="allOrder">全部售后</a></li>
  </ul>

<!--   Tab panes -->
  <div class="x_content container-fluid">
	<div class="row">
		<div class="col-md-12">
			<c:if test="${searchOrderType eq '0'}">
				<div class="panel panel" id="progressBar">
		    		<div class="panel-body">
		    			当前进度：客户已申请退款 >><span style="color: red;"> 等待商家同意退款  </span>（同意之后，金额由平台操作退款给客户，请商家不要重复支付）
		    		</div>
				</div>
	    	</c:if>
	    	<c:if test="${searchOrderType eq '1'}">
	    		<div class="panel panel" id="progressBar">
		    		<div class="panel-body">
		    			当前进度：客户已申请退货和换货 >> <span style="color: red;">等待商家同意申请 </span>（同意之后，客户将寄回原货品，请商家注意查收）
		    		</div>
		    	</div>	
	    	</c:if>
		</div>
    	<div class="col-md-12 datatable-container">
			<table id="dataTable" class="o table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>商品</th>
									<th>购买数量</th>
									<th>售后数量</th>
									<th>售后类型</th>
									<th>售后状态</th>
									<th>实付金额</th>
									<th>申请金额/换货</th>
								</tr>
							</thead>
							<tbody id="tbody1">
								
							</tbody>
			 </table>
		</div>
	</div>
</div>

</div>
		
<!-- 	售后详情 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
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
	                    $("#returnOrExchangeReceive").text("退换货待收件("+result.returnData.returnOrExchangeReceiveCount+")");
                    }
                    if(result.returnData.exchangeGoodsToSendCount>0){
	                    $("#exchangeGoodsToSend").text("换货单待发件("+result.returnData.exchangeGoodsToSendCount+")");
                    }
                    
                }
            });
        },
        "language": {"url": '${ctx}/static/cn.json'},
        "autoWidth": false,   // 禁用自动调整列宽
        "order": [[ 1, 'asc' ]],          // 取消默认排序查询,否则复选框一列会出现小箭头
        "displayLength": 25,
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
          	rowtext.push('<td colspan="7">');
          	rowtext.push('<div class="order info1"><span>售后编号：'+customerServiceOrder.orderCode+'</span><span>申请时间：'+createDate+'</span><span>原订单编号：<a href="javascript:;" onclick="viewSubOrder('+customerServiceOrder.subOrderId+');">'+customerServiceOrder.subOrderCode+'</a></span></div>');
          	rowtext.push('<table class="order-info3">');
          	rowtext.push('<tr>');
          	rowtext.push('<td>');
           	rowtext.push('<table>');
           	rowtext.push('<tr>');
           	rowtext.push('<td class="br">');
           	if(customerServiceOrder.serviceType == "D"){
           		rowtext.push("<div class='no-check'>");
	      		rowtext.push("<div class='width-60'>"+"<img src='${ctx}/static/images/directCompensation.png'>"+"</div>");
	      		rowtext.push("<div class='width-226'>商家直接替客户创建赔付，创建后由平台操作退款<br><span style='color:grey;'>请商家不要重复支付</span></div>");
	      		rowtext.push("<div>");
           	}else{
           		if(orderDtl){
		      		rowtext.push("<div class='no-check'>");
		      		rowtext.push("<div class='width-60'>"+"<img src='${ctx}/file_servelt"+orderDtl.skuPic+"'>"+"</div>");
		      		rowtext.push("<div  class='width-226'><p class='ellipsis'>"+orderDtl.productName+"</p><p>规格:"+orderDtl.productPropDesc+"</p><p>SKU码:"+orderDtl.sku+"</p></div>");
		      		rowtext.push("<div>");
           		}
           	}
           	rowtext.push('</td>');
           	rowtext.push('<td width="78" class="br">');
           	if(customerServiceOrder.serviceType != "D" && orderDtl && orderDtl.quantity && orderDtl.quantity>0){
	        	rowtext.push(orderDtl.quantity);
           	}
           	rowtext.push('</td>');
           	rowtext.push('<td width="78" class="br">');
        	if(customerServiceOrder.quantity && customerServiceOrder.quantity>0){
	        	rowtext.push(customerServiceOrder.quantity);
        	}
           	rowtext.push('</td>');
           	rowtext.push('<td width="88" class="br">');
        	rowtext.push('<span>'+customerServiceOrder.serviceTypeDesc+'</span>');
           	rowtext.push('<div><a href="javascript:;" onclick="viewCustomerServiceOrder('+customerServiceOrder.id+');">详情</a></div>');
           	rowtext.push('</td>');
           	rowtext.push('</tr>');
           	rowtext.push('</table>');
          	rowtext.push('</td>');
          	if(customerServiceOrder.serviceType != "D"){
	          	rowtext.push('<td width="88" class="br">'+customerServiceOrder.statusDesc+'<br>'+customerServiceOrder.proStatusDesc+'</td>');
          	}else{
	          	rowtext.push('<td width="88" class="br">进行中<div>商家已同意退款</div></td>');
          	}
          	if(customerServiceOrder.serviceType != "D" && orderDtl){
          		rowtext.push('<td width="164" class="br">'+orderDtl.payAmount+'<br>');
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
          		rowtext.push('<td width="164" class="br"></td>');
          	}
          	if(customerServiceOrder.amount){
	          	rowtext.push('<td width="98">'+customerServiceOrder.amount+'</td>');
          	}else{
          		rowtext.push('<td width="98"></td>');
          	}
          	rowtext.push('</tr>');
          	rowtext.push('</table>');
          	
          	rowtext.push('</td>' );
            $(row).html(rowtext.join(""));
            },
        
        
        "columns": [
            {"data": "id"},
			{"data": "id","width":"78"},
			{"data": "id","width":"78"},
			{"data": "id","width":"88"},
			{"data": "id","width":"88"},
			{"data": "id","width":"164"},
			{"data": "id","width":"98"}
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
            $("#viewModal").html(data);
            $("#viewModal").modal();
        },
	    error:function(){
	    	swal('页面不存在');
	    }
	});

}

function list(searchOrderType) {
    var url = "${ctx}/customerServiceOrder/directCompensationIndex?searchOrderType=" + searchOrderType;
    getContent(url);
}

$('#btn-query').on('click', function (event) {
    table.ajax.reload();
});
</script>
</body>
</html>
