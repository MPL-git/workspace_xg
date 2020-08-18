<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>售后单详情</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/css/subOrder.css" rel="stylesheet" type="text/css">
<style type="text/css">
.video_box {
    background: #fff;
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

.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}
.glyphicon-remove{
color:red;
margin-left: 3px;
cursor:pointer;
}
.docs-pictures{
padding: 0px;
}
.docs-pictures li{
position: relative;
margin: 3px;
}


.pic-btn-container{
	width:100%;
	position: absolute;
    top: 0px;
    background:rgba(0, 0, 0, 0.5);
    height:0px;
    z-index: 300;
    overflow: hidden;
    text-align: right;
    padding-right: 3px;
}



/*	测试物流条*/
ul li {
	list-style: none;
}

.package-status {
	padding: 18px 0 0 0
}

.package-status .status-list {
	margin: 0;
	padding: 0;
	margin-top: -5px;
	padding-left: 8px;
	list-style: none;
}

.package-status .status-list > li {
	border-left: 2px solid #8f8f8f;
	text-align: left;
}

.package-status .status-list > li:not(:first-child):before {
	/* 流程点的样式 */
	content: '';
	border: 3px solid #8f8f8f;
	background-color: #8f8f8f;
	display: inline-block;
	width: 12px;
	height: 12px;
	border-radius: 10px;
	margin-left: -7px;
	margin-right: 10px
}

.package-status .status-list > li:first-child:before{
	/* 流程点的样式 */
	content: '';
	border: 3px solid #3aa323;
	background-color: #3aa323;
	display: inline-block;
	width: 12px;
	height: 12px;
	border-radius: 10px;
	margin-left: -7px;
	margin-right: 10px
}

.package-status .status-box {
	overflow: hidden
}

.package-status .status-list > li {
	height: auto;
	width: 95%;
}

.package-status .status-list {
	margin-top: -8px
}

.package-status .status-box {
	position: relative
}

.package-status .status-box:before {
	content: " ";
	background-color: #f3f3f3;
	display: block;
	position: absolute;
	top: -8px;
	left: 20px;
	width: 10px;
	height: 4px
}

.package-status .status-list {
	margin-top: 0px;
}

.status-list > li:not(:first-child) {
	padding-top: 10px;
}

.status-content-before {
	text-align: left;
	margin-left: 25px;
	margin-top: -20px;
}

.status-content-latest {
	text-align: left;
	margin-left: 25px;
	color: #0278D8;
	margin-top: -20px;
}

.status-time-before {
	text-align: left;
	margin-left: 25px;
	font-size: 10px;
	margin-top: 5px;
}

.status-time-latest {
	text-align: left;
	margin-left: 25px;
	color: #0278D8;
	font-size: 10px;
	margin-top: 5px;
}

.status-line {
	border-bottom: 1px solid #ccc;
	margin-left: 25px;
	margin-top: 10px;
}

.list {
	padding: 0 20px;
	background-color: #F8F8F8;
	margin: 10px 0 0 25px;
	border: 1px solid #EBEBEB;
}

.list li {
	line-height: 30px;
	color: #616161;
}
</style>
</head>

<body>
<!--查看售后单详情 -->
  <div class="modal-dialog sh-xq" role="document" style="width:1000px;">
  <input type="hidden" id="subOrderId" value="${subOrderCustom.id}">
  <input type="hidden" id="customerServiceOrderId" value="${customerServiceOrder.id}">
  <input type="hidden" id="proStatus" value="${customerServiceOrder.proStatus}">
  
  <input type="hidden" id="recipient" value="${productAfterTemplet.recipient}">
  <input type="hidden" id="phone" value="${productAfterTemplet.phone}">
  <input type="hidden" id="province" value="${province}">
  <input type="hidden" id="city" value="${city}">
  <input type="hidden" id="county" value="${county}">
  <input type="hidden" id="address" value="${productAfterTemplet.address}">
  <input type="hidden" id="productAfterTempletRemarks" value="${productAfterTemplet.remarks}">
  
  <input type="hidden" id="cloudRecipient" value="${cloudProductAfterTempletCustom.recipient}">
  <input type="hidden" id="cloudPhone" value="${cloudProductAfterTempletCustom.phone}">
  <input type="hidden" id="cloudProvince" value="${cloudProductAfterTempletCustom.provinceName}">
  <input type="hidden" id="cloudCity" value="${cloudProductAfterTempletCustom.cityName}">
  <input type="hidden" id="cloudCounty" value="${cloudProductAfterTempletCustom.countyName}">
  <input type="hidden" id="cloudAddress" value="${cloudProductAfterTempletCustom.address}">
  <input type="hidden" id="cloudAfterTempletRemarks" value="${cloudProductAfterTempletCustom.remarks}">
  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">售后详情（<c:if test="${customerServiceOrder.serviceType eq 'A'}">退款单</c:if><c:if test="${customerServiceOrder.serviceType eq 'B'}">退货单</c:if><c:if test="${customerServiceOrder.serviceType eq 'C'}">换货单</c:if><c:if test="${customerServiceOrder.serviceType eq 'D'}">直赔单</c:if>）</span>
      </div>
     <div class="modal-body">
				<div class="panel panel-info">
    				<div class="bbbb lh_32"><span style="padding-left: 13px;">售后单编号：${customerServiceOrder.orderCode}</span><span class="pl_10">申请时间：<fmt:formatDate value="${customerServiceOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span><span class="pl_10">申请人联系电话:${customerServiceOrder.contactPhone}</span><span class="pl_10">售后状态：${proStatusDesc}(${serviceTypeDesc}${statusDesc})</span></div>
				    <ul class="list-group">
					        <li class="list-group-item" style="background-color: #faebcc;" id="progress">
						        <c:if test="${customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B1'}">
						        	售后进度：客户申请退货 >><span style="color: red;font-size: medium;">等待商家同意申请 </span>>><span style="color: gray;">客户寄件</span> >><span style="color: gray;">商家同意退款</span>>><span style="color: gray;">平台退款</span>>><span style="color: gray;">售后完成</span>
						        </c:if>
						        <c:if test="${customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B2'}">
						        	售后进度：客户申请退货 >>商家同意申请>><span style="color: red;font-size: medium;">等待客户寄件</span> >><span style="color: gray;">商家同意退款</span>>><span style="color: gray;">平台退款</span>>><span style="color: gray;">售后完成</span>
						        </c:if>
						        <c:if test="${customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B4'}">
						        	售后进度：客户申请退货 >>商家同意申请>>客户寄件>><span style="color: red;font-size: medium;">等待商家同意退款</span>>><span style="color: gray;">平台退款</span>>><span style="color: gray;">售后完成</span> 
						        </c:if>
						        <c:if test="${customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B5'}">
						        	售后进度：客户申请退货 >>商家同意申请>>客户寄件>>商家同意退款>><span style="color: red;font-size: medium;">等待平台退款</span>>><span style="color: gray;">售后完成</span>
						        </c:if>
						        <c:if test="${customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B7'}">
						        	售后进度：客户申请退货 >>商家同意申请>>客户寄件>>商家同意退款>>平台退款>><span style="color: red;font-size: medium;">售后完成</span>
						        </c:if>
						        <c:if test="${customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B3'}">
						        	售后进度：客户申请退货 >>商家不同意申请>><span style="color: red;font-size: medium;">售后已拒绝</span>
						        </c:if>
						        <c:if test="${customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B6'}">
						        	售后进度：客户申请退货 >>商家同意申请>>客户寄件>>商家不同意退款>><span style="color: red;font-size: medium;">售后已拒绝</span>
						        </c:if>
					        </li>
					        <li class="list-group-item">退货原因：${reasonDesc}<c:if test="${not empty customerServiceOrder.depositAmount && customerServiceOrder.depositAmount gt 0}"><span style="color: red;">（预售订单，退款会将定金一起返还，退款原因如果是“商家责任”，需要额外赔偿定金等值的积分给用户，从商家保证金扣除）</span></c:if></li>
					        <li class="list-group-item">退货说明：${customerServiceOrder.remarks}</li>
							<c:if test="${not empty customerServicePics}">
							<li class="list-group-item">
								图片凭证：
						        <c:forEach items="${customerServicePics}" var="customerServicePic">
						        	<img class="w_h_60_60" src="${ctx}/file_servelt${customerServicePic.pic}" onclick="viewerPic(this.src,this)" name="pic">
						        </c:forEach>
					        </li>
							</c:if>
					        <li class="list-group-item">
					        	退款金额:
					        	<span class="ff_yahei fs_12" style="color: red;">${customerServiceOrder.amount}</span>
					        	元
					        </li>
					        <c:if test="${customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B2' && customerServiceOrder.status eq '0'}">
					        	<li class="list-group-item">
					        		<button type="button" class="btn b_r_2 bc_3c9eff" id="memberExpress">为客户填写寄件快递单号</button>
					        	</li>
					        </c:if>
				    </ul>
				    <c:if test="${customerServiceOrder.status == 0 && customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B1'}">
				    <div class="panel-body" style="display: inline-block;">
						<div style="display: inherit;">
				        <p>
				        	<button type="button" class="btn b_r_2 bc_3c9eff w_h_130_36" id="agreeReturnGoods">同意退货申请</button><br>
				       		<span class="preferential">同意客户退货申请之后，客户将寄回货品，注意查收。</span>
				        </p>
						</div>
						<span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>				    
				        <div style="float: right;">
				        <p>
				        	<button type="button" class="btn b_r_2 bc_ff5555 w_h_130_36" id="disAgreeReturnGoods">不同意退货申请</button><br>
				       		<span class="preferential">如已与客户沟通，并达成协商一致，商家可主动拒绝售后。</span>
				        </p>
						</div>
				    </div>
				    </c:if>
				    <c:if test="${customerServiceOrder.status == 0 && customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B4' && interventionOrderCount == 0}">
				    <div class="panel-body" style="display: inline-block;">
						<div style="display: inherit;">
				        <p>
				        	<button type="button" class="btn b_r_2 bc_3c9eff w_h_130_36" id="agreeRefund">同意退款</button><br>
				       		<span class="preferential">请确保已收到买家退货，并确认同意买家退货退款请求。</span>
				        </p>
						</div>
						<span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>				    
				        <div style="float: right;">
				        <p>
				        	<button type="button" class="btn b_r_2 bc_ff5555 w_h_130_36" id="disAgreeRefund">不同意退款</button><br>
				       		<span class="preferential">如已与客户沟通，并达成协商一致，商家可主动拒绝售后。</span>
				        </p>
						</div>
				    </div>
				    </c:if>
				    <c:if test="${customerServiceOrder.status == 0 && customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B4' && interventionOrderCount > 0}">
				   	<c:if test="${not empty winType}">
				    <div class="panel-body" style="display: inline-block;">
						<div style="display: inherit;">
				        <p>
				        	<button type="button" class="btn b_r_2 bc_3c9eff w_h_130_36" id="agreeRefund">同意退款</button><br>
				       		<span class="preferential">请确保已收到买家退货，并确认同意买家退货退款请求。</span>
				        </p>
						</div>
						<span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>				    
				        <div style="float: right;">
				        <p>
				        	<button type="button" class="btn b_r_2 bc_ff5555 w_h_130_36" id="disAgreeRefund">不同意退款</button><br>
				       		<span class="preferential">如已与客户沟通，并达成协商一致，商家可主动拒绝售后。</span>
				        </p>
						</div>
				    </div>
				    </c:if>
				    </c:if>
				    
				    <c:if test="${(customerServiceOrder.status == 0 || customerServiceOrder.status == 2) && customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B3'}">
				    <c:if test="${not empty winType && winType == 1}">
				    <div class="panel-body" style="display: inline-block;">
						<div style="display: inherit;">
				        <p>
				        	<button type="button" class="btn b_r_2 bc_3c9eff w_h_130_36" id="agreeReturnGoods">同意退货申请</button><br>
				       		<span class="preferential">同意客户退货申请之后，客户将寄回货品，注意查收。</span>
				        </p>
						</div>
				    </div>
				    </c:if>
				    </c:if>
				    
				    <c:if test="${(customerServiceOrder.status == 0 || customerServiceOrder.status == 2) && customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B6'}">
				    <c:if test="${not empty winType && winType == 1}">
				    <div class="panel-body" style="display: inline-block;">
						<div style="display: inherit;">
				        <p>
				        	<button type="button" class="btn b_r_2 bc_3c9eff w_h_130_36" id="agreeRefund">同意退款</button><br>
				       		<span class="preferential">请确保已收到买家退货，并确认同意买家退货退款请求。</span>
				        </p>
						</div>
						<span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>				    
				        <div style="float: right;">
				        <p>
				        	<button type="button" class="btn b_r_2 bc_ff5555 w_h_130_36" id="disAgreeRefund">不同意退款</button><br>
				       		<span class="preferential">如已与客户沟通，并达成协商一致，商家可主动拒绝售后。</span>
				        </p>
						</div>
				    </div>
				    </c:if>
				    </c:if>
				    
				    </div>
				    <div class="row">
						<h4>退货商品信息</h4>
						<div class="col-md-12">
						<div class="panel panel-info">
    						<div class="bc_eff9ff fs_12 ff_yahei color_333333 lh_32"><span style="padding-left: 10px;">原订单编号：${subOrderCustom.subOrderCode} <a href="javascript:;" onclick="viewSubOrder(${subOrderCustom.id},${subOrderCustom.status})">【查看】</a></span><span class="pl_10">发货时间：<fmt:formatDate value="${subOrderCustom.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span><span class="pl_10">活动ID:${orderDtl.activityId}</span></div>
							<ul class="list-group">
								<li class="list-group-item">
									<span>客户原收货人：${subOrderCustom.receiverName}</span><span class="pl_10">电话：${subOrderCustom.receiverPhone}</span><span class="pl_10"> 地址：${subOrderCustom.receiverAddress}</span><span class="pl_10"> <a href="javascript:;" id="showWuliu"> 【查看物流动态】</a></span>
								</li>
							</ul>
						</div>
						</div>
						<div class="col-md-12 datatable-container">
							<table id="datatable"
								class="table table-striped table-bordered dataTable no-footer"
								role="grid" aria-describedby="datatable_info">
								<thead style="background-color: #bce8f1;">
									<tr role="row" class="bbbb">
										<th colspan="7">商品</th>
										<th>单价</th>
										<th>数量</th>
										<th>商家优惠</th>
										<th>运费</th>
										<th>平台优惠</th>
										<th>积分优惠</th>
										<th>实付金额</th>
									</tr>
								</thead>
								<tbody>
									<tr>
								      <td colspan="7">
								      	<div class="row" style="width:300px;">
								      		<div class="col-md-3"><img class="w_h_60_60" src="${ctx}/file_servelt${orderDtl.skuPic}"></div>
								      		<div class="col-md-9 text-left">${orderDtl.brandName}<br>${orderDtl.productName}<br>${orderDtl.artNo}<br>规格:${orderDtl.productPropDesc}<br>SKU码:${orderDtl.sku}</div>
								      	</div>
								      </td>
								      <td>${orderDtl.salePrice}</td>
								      <td>${orderDtl.quantity}</td>
								      <td>${orderDtl.mchtPreferential}</td>
								      <td>${orderDtl.freight}</td>
								      <td>${orderDtl.platformPreferential}</td>
								      <td>${orderDtl.integralPreferential}</td>
								      <td>${orderDtl.payAmount}</td>
	    							</tr>
	    							<tr>
	    								<c:if test="${empty customerServiceOrder.cloudProductAfterTempletId || customerServiceOrder.cloudProductAfterTempletId == 0}">
	    								<td colspan="17">
		    								<span style="float: left;">【售后模板信息】收货人：${productAfterTemplet.recipient}</span>
		    								<span>电话：${productAfterTemplet.phone}&nbsp;&nbsp;&nbsp;</span>
		    								<span>地址：${province}${city}${county}${productAfterTemplet.address}</span>
	    								</td>
	    								</c:if>
	    								
	    								<c:if test="${not empty customerServiceOrder.cloudProductAfterTempletId && customerServiceOrder.cloudProductAfterTempletId != 0}">
	    								<td colspan="17">
		    								<span style="float: left;">【售后模板信息】收货人：${cloudProductAfterTempletCustom.recipient}</span>
		    								<span>电话：${cloudProductAfterTempletCustom.phone}&nbsp;&nbsp;&nbsp;</span>
		    								<span>地址：${cloudProductAfterTempletCustom.provinceName}${cloudProductAfterTempletCustom.cityName}${cloudProductAfterTempletCustom.countyName}${cloudProductAfterTempletCustom.address}</span>
	    								</td>
	    								</c:if>
	    							</tr>
								</tbody>
							</table>
						</div>	
					</div>

					<c:if test="${customerServiceOrder.serviceType eq 'B' && customerServiceOrder.proStatus eq 'B4'}">
					 <span class="ff_yahei fs_14 color_000000">客户寄件信息</span> <br>
					 <div class="panel panel-info">
						 <div class="bc_eff9ff fs_12 ff_yahei color_333333 lh_32"><span style="padding-left: 10px;">【商品寄回地址】收货人：${customerServiceOrder.productReturnConsignee} </span><span class="pl_10">电话：${customerServiceOrder.productReturnContactPhone} </span><span class="pl_10">地址:${customerServiceOrder.productReturnAddress}</span></div>
						 <ul class="list-group">
							 <li class="list-group-item">
								 <span>【客户寄件信息】寄件物流：${customerServiceOrder.memberExpressCompany}</span><span class="pl_10">寄件单号：${customerServiceOrder.memberExpressNo}</span><span class="pl_10"> <a href="javascript:;" id="showReturnWuliu"> 【查看物流】</a></span>
							 </li>
						 </ul>
					 </div>
					</c:if>

					<div class="row">
						<h4>售后记录</h4>
						<div class="col-md-12 datatable-container" style="overflow-y: auto;height: 310px;">
							<table id="datatable"
								class="table table-striped table-bordered dataTable no-footer"
								role="grid" aria-describedby="datatable_info">
								<thead style="background-color: #bce8f1;">
									<tr role="row" class="bbbb">
										<th class="w_h_200_36">操作者</th>
										<th class="w_h_740_36">内容</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${customerServiceLogCustoms}" var="customerServiceLogCustom">
									<tr>
								      <td style="text-align: left;">
									      <span class="money">
									      <c:if test="${customerServiceLogCustom.operatorType eq '1'}">
									      	客户
									      </c:if>
									      <c:if test="${customerServiceLogCustom.operatorType eq '2'}">
									      	商家
									      </c:if>
									      <c:if test="${customerServiceLogCustom.operatorType eq '3'}">
									      	系统
									      </c:if>
									      <c:if test="${customerServiceLogCustom.operatorType eq '4'}">
									      	供应商
									      </c:if>
									      </span>
									      <div class="pt_10">
									      	<fmt:formatDate value="${customerServiceLogCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
									      </div>	
								      </td>
								      <td colspan="10" style="text-align: left;">
								      	<div>${customerServiceLogCustom.content}</div>
								      	<c:if test="${not empty customerServiceLogCustom.serviceLogPics}">
								      	<div style="display: inline-block;">
									      	<c:forEach items="${customerServiceLogCustom.serviceLogPics}" var="serviceLogPic">
									      		<c:if test="${not empty serviceLogPic.pic}">
									      			<img class="w_h_60_60" src="${ctx}/file_servelt${serviceLogPic.pic}" onclick="viewerPic(this.src,this)" name="logPic">
									      		</c:if>
									      	</c:forEach>
								      	</div>
								      	</c:if>
								      </td>
	    							</tr>
	    						</c:forEach>	
								</tbody>
							</table>
						</div>
					</div>
			</div>		
    </div>
  </div>
  <!-- 	订单详情 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>

<!--弹出退货申请Div-->
<div class="video_box" style="position:fixed; width:600px; height:155px; top:30%; left:40%; display: none;border-radius: 2px;" id="returnGoodsDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	同意客户退货申请
				</h3>
		    </div>
		    <div class="panel-body">
		    	<table class="panel-table">
		 			<tr>
		 				<td>类型</td>
		 				<td><div class="form-group"><input type="radio" name="need" value="1"/>需要客户退回商品 <input type="radio" name="need" value="0"/>直接退款，不需要客户退回商品 </div></td>
		 			</tr>
		 			<tr id="returnGoodsTr" style="display: none;">
		 				<td>退货地址</td>
		 				<td>
			 				<div class="form-group">
				 				<input type="radio" name="addressType" value="0" />商家的地址 
				 				<c:if test="${not empty cloudProductAfterTempletCustom}">
					 				<input type="radio" name="addressType" value="1" checked="checked"/>供应商的地址 
				 				</c:if>
			 				</div>
		 				</td>
		 			</tr>
		 			<tr id="templetTr" style="display: none;">
		 				<td colspan="5" style="text-align: left;">
		 					<div class="form-group" style="display: none;" name="needDiv">
				  				同意客户退货申请之后，将自动告知客户退货地址如下：（在商品列表可以修改此退货地址）
				  			</div>
		 					<div class="form-group" id="mchtTemplet" style="display: none;">
                                <form class="form-horizontal" id="dataJson">
                                    收货人&nbsp;&nbsp;：&nbsp;<input type="text" id="editRecipient" name="editRecipient" style="width:280px;" value="${productAfterTemplet.recipient}"/><br/>
                                    联系电话：<input type="text" id="editPhone" name="editPhone" style="width:280px;" value="${productAfterTemplet.phone}"/><br/>
                                    退货地址：<input type="text" id="editAddress" name="editAddress" style="width:280px;" value="${province}${city}${county}${productAfterTemplet.address}"/><br/>
                                    注意事项：<input type="text" id="editRemarks" name="editRemarks" style="width:280px;" value="<c:if test="${empty productAfterTemplet.remarks}">无</c:if> <c:if test="${not empty productAfterTemplet.remarks}">${productAfterTemplet.remarks}</c:if>"/><br/>
                                    <%--<p>收货人：${productAfterTemplet.recipient}</p>--%>
                                    <%--<p>联系电话：${productAfterTemplet.phone}</p>--%>
                                    <%--<p>退货地址：${province}${city}${county}${productAfterTemplet.address}</p>--%>
                                    <%--<p>注意事项：<c:if test="${empty productAfterTemplet.remarks}">无</c:if><c:if test="${not empty productAfterTemplet.remarks}">${productAfterTemplet.remarks}</c:if></p>--%>
                                </form>
                            </div>
		 					<div class="form-group" id="supplierTemplet" style="display: none;">
								收货人&nbsp;&nbsp;：&nbsp;<input type="text" id="yunRecipient" name="yunRecipient" style="width:280px;" value="${cloudProductAfterTempletCustom.recipient}"/><br/>
								联系电话：<input type="text" id="yunPhone" name="yunPhone" style="width:280px;" value="${cloudProductAfterTempletCustom.phone}"/><br/>
								退货地址：<input type="text" id="yunAddress" name="yunAddress" style="width:280px;" value="${cloudProductAfterTempletCustom.provinceName}${cloudProductAfterTempletCustom.cityName}${cloudProductAfterTempletCustom.countyName}${cloudProductAfterTempletCustom.address}"/><br/>
								注意事项：<input type="text" id="yunRemarks" name="yunRemarks" style="width:280px;" value="<c:if test="${empty cloudProductAfterTempletCustom.remarks}">无</c:if> <c:if test="${not empty cloudProductAfterTempletCustom.remarks}">${productAfterTemplet.remarks}</c:if>"/><br/>

								<%--<p>收货人：${cloudProductAfterTempletCustom.recipient}</p>
						  		<p>联系电话：${cloudProductAfterTempletCustom.phone}</p>
						  		<p>退货地址：${cloudProductAfterTempletCustom.provinceName}${cloudProductAfterTempletCustom.cityName}${cloudProductAfterTempletCustom.countyName}${cloudProductAfterTempletCustom.address}</p>
						  		<p>注意事项：<c:if test="${empty cloudProductAfterTempletCustom.remarks}">无</c:if><c:if test="${not empty cloudProductAfterTempletCustom.remarks}">${cloudProductAfterTempletCustom.remarks}</c:if></p>--%>
				  			</div>
				  			
				  			<div class="form-group" name="notNeedDiv" style="display: none;">
					    		您确认同意直接退款给客户：<span style="color: red;">${customerServiceOrder.amount}</span>元？
				  			</div>
							<div class="form-group" name="notNeedDiv" style="display: none;">
							   	 	<p style="color: gray;">同意之后，金额由平台操作退款给客户，请不要重复支付。</p>
							</div>
		 				</td>
		 			</tr>
	 			</table>
				  
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-md-7">
				      <span><button type="button" class="btn btn-default" id="confirmReturnGoods" style="font-size: large;">同意退货</button></span>
				      <span style="float: right;"><button type="button" class="btn btn-default" name="cancle" >取消</button></span>
				    </div>
				  </div>
		    </div>
		 </div>
</div>

<!--弹出退款Div-->
<div class="video_box" style="position:fixed; width:400px; height:155px; top:30%; left:40%; display: none;border-radius: 2px;" id="refundDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	同意退款确认
				</h3>
		    </div>
		    <div class="panel-body">
				  <div class="form-group">
					    	您确认同意直接退款给客户：<span style="color: red;">${customerServiceOrder.amount}</span>元？
				  </div>
				  <div class="form-group">
				   	 	<p style="color: gray;">同意之后，金额由平台操作退款给客户，请不要重复支付。</p>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-md-7">
				      <span><button type="button" class="btn btn-default" id="confirm" style="font-size: large;">确认</button></span>
				      <span style="float: right;"><button type="button" class="btn btn-default" name="cancle" >取消</button></span>
				    </div>
				  </div>
		    </div>
		 </div>
</div>

<!--弹出不同意退货申请Div-->
<div class="video_box" style="position:fixed; width:600px; height:290px; top:35%; left:34%; display: none;border-radius: 2px;" id="disAgreeReturnGoodsDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	不同意客户售后退货申请确认
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" >
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">拒绝原因<span style="color: red;">*</span>：</label>
				    <div class="col-md-7" style="padding-left: 40px;">
				    	<select class="form-control" id="reason" style="width: 200px;">
							<option value="">--请选择--</option>
							<c:forEach items="${returnGoodsApplySysParamCfgList}" var="returnGoodsApplySysParamCfg">
								<option value="${returnGoodsApplySysParamCfg.paramValue}">${returnGoodsApplySysParamCfg.paramValue}</option>
							</c:forEach>
				        </select>
				    </div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">请填写原因说明：</label>
				    <div class="col-md-7">
				      <textarea class="form-control" style="width: 420px;" rows="3" id="reasonDescription"></textarea>
				    </div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-14 control-label" style="padding-left:80px;">上传凭证&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    <span style="color: gray;margin-left: 30px;">每张图片大小不超过400K，最多3张，支持GIF、JPG、PNG格式</span>
				    <div id="disAgreeReturnGoods-pic-container" style="margin-left: -60px;">
					</div>
				  </div>
				  
				  <div class="form-group">
				    <div class="col-sm-offset-3 col-md-7" style="padding-left: 60px;">
				      <button type="button" class="btn btn-default" id="confirmDisAgree">确认不同意</button>
				      <button type="button" class="btn btn-default" name="cancle">取消</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>




<!--弹出不同意退款Div-->
<div class="video_box" style="position:fixed; width:600px; height:290px; top:35%; left:34%; display: none;border-radius: 2px;" id="disAgreeRefundDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	不同意退款确认
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" >
		    	  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">拒绝原因<span style="color: red;">*</span>：</label>
				    <div class="col-md-7" style="padding-left: 40px;">
				    	<select class="form-control" id="refundReason" style="width: 200px;">
							<option value="">--请选择--</option>
							<c:forEach items="${returnGoodsRefundSysParamCfgList}" var="returnGoodsRefundSysParamCfg">
								<option value="${returnGoodsRefundSysParamCfg.paramValue}">${returnGoodsRefundSysParamCfg.paramValue}</option>
							</c:forEach>
				        </select>
				    </div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">请填写原因说明：</label>
				    <div class="col-md-7">
				      <textarea class="form-control" style="width: 420px;" rows="3" id="refundReasonDescription"></textarea>
				    </div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-14 control-label" style="padding-left:80px;">上传凭证&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    <span style="color: gray;margin-left: 30px;">每张图片大小不超过400K，最多3张，支持GIF、JPG、PNG格式</span>
				    <div id="pic-container" style="margin-left: 105px;">
					</div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 60px;">
				    <div class="col-sm-offset-3 col-md-7">
				      <button type="button" class="btn btn-default" id="confirmDisAgreeRefund">确认不同意</button>
				      <button type="button" class="btn btn-default" name="cancle">取消</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出物流信息Div-->
<div class="video_box"
	 style="position:fixed; width:600px; height: auto; top:1%; left:32.5%;border-radius: 4px;display: none;"
	 id="wuliuDiv">
	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
	<div class="panel panel-default" style="margin-bottom:0px;">
		<div class="panel-heading">
			<h3 class="panel-title">
				物流动态
			</h3>
		</div>

		<div class="package-status" style="min-height:200px;overflow-y:auto;max-height:800px;">
			<c:if test="${hasWuliu}">
				&nbsp;&nbsp;&nbsp;&nbsp;<span class="pl_10"> 【收件地址】${subOrderCustom.receiverAddress}</span><br>
				&nbsp;&nbsp;&nbsp;&nbsp;<span class="pl_10"> 【物流信息】快递名称：${subOrderCustom.expressName}</span>
				<span class="pl_10">快递单号：${subOrderCustom.expressNo}</span>
				<div class="status-line" style="margin-right: 30px;margin-left: 36px"></div>
				<br>
				<c:set var="startIndex" value="${fn:length(wuliuInfos)-1 }"></c:set>
				<div class="status-box">
					<ul class="status-list">
						<c:forEach var="wuliuInfo" items="${wuliuInfos}" varStatus="status">
							<li >
								<br>
								<div class="status-content-before" <c:if test="${status.index == 0}"> style="color: green;"</c:if>>
										${wuliuInfos[startIndex - status.index].AcceptTime}
								</div>
								<div class="status-time-before" <c:if test="${status.index == 0}"> style="color: green;"</c:if>>
										${wuliuInfos[startIndex - status.index].AcceptStation}
								</div>

								<div class="status-line"></div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<c:if test="${!hasWuliu}">
				<div class="panel-body">
					<p>暂无物流信息</p>
				</div>
			</c:if>
		</div>
	</div>
</div>

<!--弹出div End-->

<!--弹出用户寄回物流信息Div-->
<div class="video_box" style="position:fixed; width:600px; height:auto; top:1%; left:32.5%;border-radius: 4px;display: none;" id="returnWuliuDiv">
	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
	<div class="panel panel-default" style="margin-bottom:0px;">
		<div class="panel-heading">
			<h3 class="panel-title">
				物流动态
			</h3>
		</div>
		<div class="package-status" style="min-height:200px;overflow-y:auto;max-height:800px;">
			<c:if test="${hasReturnWuliu}">
				<span class="pl_10"> 【寄回地址】${customerServiceOrder.productReturnAddress}</span><br>
				<span class="pl_10"> 【寄件信息】快递名称：${customerServiceOrder.memberExpressCompany}</span>
				<span class="pl_10">快递单号：${customerServiceOrder.memberExpressNo}</span>

				<div class="status-line" style="margin-right: 30px;margin-left: 36px"></div>
				<br>
				<c:set var="startIndex" value="${fn:length(returnWuliuInfos)-1 }"></c:set>
				<div class="status-box">
					<ul class="status-list">
						<c:forEach var="wuliuInfo" items="${returnWuliuInfos}" varStatus="status">
							<li >
								<br>
								<div class="status-content-before" <c:if test="${status.index == 0}"> style="color: green;"</c:if>>
										${returnWuliuInfos[startIndex - status.index].AcceptTime}
								</div>
								<div class="status-time-before" <c:if test="${status.index == 0}"> style="color: green;"</c:if>>
										${returnWuliuInfos[startIndex - status.index].AcceptStation}
								</div>

								<div class="status-line"></div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<c:if test="${!hasReturnWuliu}">
				<div class="panel-body">
					<p>暂无物流信息</p>
				</div>
			</c:if>
		</div>
	</div>
</div>

<!--弹出用户寄回物流div End-->






<!--填写物流信息Div-->
<div class="video_box" style="position:fixed; width:320px; height:155px; top:30%; left:40%; display: none;border-radius: 2px;" id="returnGoodsMemberSendDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="modal-header">
		        <h3 class="modal-title">
		        	填写物流信息
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" >
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">快递名称<span style="color: red;">*</span>：</label>
				    <div class="col-md-7">
				    	<select class="form-control" id="returnGoodsExpressName">
							<option value="">--请选择--</option>
							<c:forEach var="express" items="${expressList}">
								<option value="${express.name}">${express.name}</option>
							</c:forEach>
				        </select>
				    </div>
				  </div>
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">快递单号<span style="color: red;">*</span>：</label>
				    <div class="col-md-7">
				      <input type="text" class="form-control" id="returnGoodsExpressNo" >
				    </div>
				  </div>
 
				  <div class="form-group">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="updateMemberExpress">提交</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>
<div class="black_box" style="display: none;" id="returnGoodsBlackBox"></div>
<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;"></ul>
<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myWebUploader.js"></script>
<script type="text/javascript">


function viewerPic(imgPath,_this){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
	$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	var name = $(_this).attr("name");
	$("img[name='"+name+"']").each(function(){
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

function viewSubOrder(id,status){
		$.ajax({
	        url: "${ctx}/subOrder/subOrderView?id="+id+"&status="+status, 
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
  var imageUploaderApply;
  var imageUploader;
  var viewerPictures;
	$(function(){
		imageUploaderApply = $("#disAgreeReturnGoods-pic-container").myWebUploader({
			fileNumLimit:3,
	        fileSizeLimit:1024*400*3,
	        fileSingleSizeLimit:1024*400,
	        ulMarginLeft:"161px",
	        viewerTop:"250",
	        viewerLeft:"250"
	    });
		
		imageUploader = $("#pic-container").myWebUploader({
			fileNumLimit:3,
	        fileSizeLimit:1024*400*3,
	        fileSingleSizeLimit:1024*400,
	        ulMarginLeft:"-3px",
	        viewerTop:"250",
	        viewerLeft:"250"
	    });
		
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		
		$("input[name='addressType']").bind('click',function(){
			$("div[name='needDiv']").show();
			var addressType = $(this).val();
			if(addressType == 1){
				$("#supplierTemplet").show();
				$("#mchtTemplet").hide();
			}else{
				$("#supplierTemplet").hide();
				$("#mchtTemplet").show();
			}
		});
		
		$("input[name='need']").on('click',function(){
			if($(this).val()==0){
				$("#templetTr").show();
				$("#returnGoodsTr").hide();
				$("div[name='notNeedDiv']").show();
				$("div[name='needDiv']").hide();
				$("#supplierTemplet").hide();
				$("#mchtTemplet").hide();
			}else{
				$("#returnGoodsTr").show();
				$("#templetTr").show();
				var addressType = $("input[name='addressType']:checked").val();
				if(addressType == 1){
					$("#supplierTemplet").show();
				}else{
					$("#mchtTemplet").show();
				}
				$("div[name='notNeedDiv']").hide();
				$("div[name='needDiv']").show();
			}
		});
		
		$("#showWuliu").on('click',function(){
			$("#wuliuDiv").show();
			$("#returnGoodsBlackBox").show();
		});


		//用户寄件的物流弹出div
		$("#showReturnWuliu").on('click',function(){
			$("#returnWuliuDiv").show();
			$("#returnGoodsBlackBox").show();
		});



		
		$("#agreeRefund").on('click',function(){
			//添加判断，已同意退款的，再次点击不显示弹出层
			var proStatus = $("#proStatus").val();
			if(proStatus =="B4" || proStatus =="B6"){
				$("#refundDiv").show();
				$("#returnGoodsBlackBox").show();
			}else{
				return false;
			}
		});
		
		$(".video_close").on('click',function(){
			$("#refundDiv").hide();
			$("#returnGoodsDiv").hide();
			$("#disAgreeReturnGoodsDiv").hide();
			$("#disAgreeRefundDiv").hide();
			$("#wuliuDiv").hide();
			$("#returnWuliuDiv").hide();
			$("#returnGoodsBlackBox").hide();
			$("#returnGoodsMemberSendDiv").hide();
		});
		
		$("button[name='cancle']").on('click',function(){
			$("#refundDiv").hide();
			$("#returnGoodsDiv").hide();
			$("#disAgreeReturnGoodsDiv").hide();
			$("#disAgreeRefundDiv").hide();
			$("#returnGoodsBlackBox").hide();
		});
		var submiting;
		$("#confirm").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var proStatus = "B5";
			var content = "<span>同意退款，请耐心等待。</span>";
			var isRefund = "1";
			submiting = true;
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/customerServiceOrder/customerServiceOrderAgree',
		        data: {"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"isRefund":isRefund},
		        cache : false,
				async : false,
		        dataType: 'json'
		    }).done(function (result) {
		    	submiting = false;
		        if (result.returnCode =='0000') {
		        	$("#proStatus").val(proStatus);
		        	$("#returnGoodsBlackBox").hide();
		           	swal(result.returnMsg);
					$("#agreeRefund").parent().parent().parent().remove();
					$("#progress").html('售后进度：客户申请退货 >>商家同意申请 >>客户寄件>>商家同意退款>><span style="color: red;font-size: medium;">等待平台退款 </span> >><span style="color: gray;"> 售后完成</span>');
					$("#refundDiv").hide();
					$("#returnGoodsBlackBox").hide();
					$("#viewModal").modal('hide');
		           	table.ajax.reload(null, false);
		        }else{
		        	if(result.returnMsg){
		        		swal(result.returnMsg);
		        	}else{
			        	swal("操作失败，请稍后重试");
		        	}
		        }
		    });
		});
		
		
		$("#agreeReturnGoods").on('click',function(){
			//添加判断，已同意退款的，再次点击不显示弹出层
			var proStatus = $("#proStatus").val();
			if(proStatus =="B1" || proStatus =="B3"){
				<c:if test="${orderDtl.deliveryStatus == 0}">
					$("input[name='need']").last().attr("checked",true);
					$("input[name='need']").each(function(){
						$(this).attr("disabled","disabled");
					});
					$("#templetTr").show();
					$("div[name='notNeedDiv']").show();
				</c:if>
				$("#returnGoodsDiv").show();
				$("#returnGoodsBlackBox").show();
			}else{
				return false;
			}
		});
		
		
		$("#confirmReturnGoods").on('click',function(){

			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var need = $("input[name='need']:checked").val();
			var proStatus;
			submiting = true;
			var dataJson = $('#datajson').serializeArray();
            var editRecipient=$('#editRecipient').val();
            var editPhone=$('#editPhone').val();
            var editAddress=$('#editAddress').val();
            var editRemarks=$('#editRemarks').val();

			if(need == 0){
				proStatus = "B5";
				var content = "<span>同意售后退货申请，并且同意退款，请耐心等待。</span>";
				var isRefund = "1";
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/customerServiceOrder/customerServiceOrderAgree',
			        data: {"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"isRefund":isRefund},
			        cache : false,
					async : false,
			        dataType: 'json'
			    }).done(function (result) {
			    	submiting = false;
			        if (result.returnCode =='0000') {
			        	$("#proStatus").val(proStatus);
			        	$("#returnGoodsBlackBox").hide();
			           	swal(result.returnMsg);
						$("#agreeRefund").parent().parent().parent().remove();
						$("#progress").html('售后进度：客户申请退货 >>商家同意申请 >>客户寄件>>商家同意退款>><span style="color: red;font-size: medium;">等待平台退款 </span> >><span style="color: gray;"> 售后完成</span>');
						$("#refundDiv").hide();
						$("#returnGoodsBlackBox").hide();
						$("#viewModal").modal('hide');
			           	table.ajax.reload(null, false);
			        }else{
			        	if(result.returnMsg){
			        		swal(result.returnMsg);
			        	}else{
				        	swal("操作失败，请稍后重试");
			        	}
			        }
			    });
			}else if(need == 1){
				proStatus = "B2";
				var addressType = $("input[name='addressType']:checked").val();
				var recipient;
				var phone;
				var province;
				var city;
				var address;
				var productAfterTempletRemarks;
				var content;
				if(addressType == 0){
					recipient = $("#editRecipient").val();
					phone = $("#editPhone").val();
					province = $("#province").val();
					city = $("#city").val();
					county = $("#county").val();
					address = $("#editAddress").val();
					productAfterTempletRemarks = $("#editRemarks").val();
					content = "<span>同意售后退货申请</span><br>请客户寄件到以下地址：<br>收货人："+recipient+"<br>联系电话："+phone+"<br>地址："+address+"<br>注意事项："+productAfterTempletRemarks;
				}else{
					recipient = $("#yunRecipient").val();
					phone = $("#yunPhone").val();
					province = $("#cloudProvince").val();
					city = $("#cloudCity").val();
					county = $("#cloudCounty").val();
					address = $("#yunAddress").val();
					productAfterTempletRemarks = $("#yunRemarks").val();
					content = "<span>同意售后退货申请</span><br>请客户寄件到以下地址：<br>收货人："+recipient+"<br>联系电话："+phone+"<br>地址："+address+"<br>注意事项："+productAfterTempletRemarks;
				}
				var isRefund = "0";

				if(!addressType){
					submiting = false;
					swal("请选择退货地址");
					return false;
				}
				if(!recipient || !phone || !address){
					submiting = false;
					swal("请填写收货信息");
					return false;
				}
				if(phone.length > 15){
					submiting = false;
					swal("手机号格式错误");
					return false;
				}
				var cloudProductAfterTempletId="";
				var supplierAddress="";
				if(addressType == 1){
					<c:if test="${not empty cloudProductAfterTempletCustom}">
						cloudProductAfterTempletId = ${cloudProductAfterTempletCustom.id};
					</c:if>
					supplierAddress='${cloudProductAfterTempletCustom.provinceName}'+'${cloudProductAfterTempletCustom.cityName}'+'${cloudProductAfterTempletCustom.countyName}'+'${cloudProductAfterTempletCustom.address}';
				}



				$.ajax({
			        method: 'POST',
			        url: '${ctx}/customerServiceOrder/customerServiceOrderAgree',
			        data: {"editRecipient":recipient,"editPhone":phone,"editAddress":address,"editRemarks":productAfterTempletRemarks,"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"isRefund":isRefund,"addressType":addressType,"cloudProductAfterTempletId":cloudProductAfterTempletId,"supplierAddress":supplierAddress},
			        cache : false,
					async : false,
			        dataType: 'json'
			    }).done(function (result) {
			    	submiting = false;
			        if (result.returnCode =='0000') {
			           	swal(result.returnMsg);
			        	$("#proStatus").val(proStatus);
						$("#agreeReturnGoods").parent().parent().parent().remove();
						$("#progress").html('售后进度：客户申请退货 >>商家同意申请 >><span style="color: red;font-size: medium;">等待客户寄件 </span>>><span style="color: gray;">商家同意退款</span>>><span style="color: gray;">平台退款</span>>><span style="color: gray;"> 售后完成</span>');
						$("#returnGoodsDiv").hide();
						$("#returnGoodsBlackBox").hide();
						$("#viewModal").modal('hide');
			           	table.ajax.reload(null, false);
			        }else{
			        	if(result.returnMsg){
			        		swal(result.returnMsg);
			        	}else{
				        	swal("操作失败，请稍后重试");
			        	}
			        }
			    });
			}
		});
		
		$("#disAgreeReturnGoods").on('click',function(){
			//添加判断，已同意退款的，再次点击不显示弹出层
			var proStatus = $("#proStatus").val();
			if(proStatus == "B1"){
				$("#disAgreeReturnGoodsDiv").show();
				$("#returnGoodsBlackBox").show();
			}else{
				return false;
			}
		});
		
		$("#confirmDisAgree").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var proStatus = "B3";
			var reason = $.trim($("#reason").val());
			if(reason == ""){
				swal("拒绝原因必选，其他选填");
				return false;
			}
			var remarks = $("#reasonDescription").val();
			if(remarks.length >= 400){
				swal("拒绝原因说明需小于400字");
				return false;
			}
			var content = "<span>不同意退货申请，同时关闭售后</span><br><span>拒绝原因："+reason+"<br>原因说明："+remarks+"</span>";
			submiting = true;
			imageUploaderApply.upload();
			imageUploaderApply.on('uploadFinished',function(){
				var imgPaths = $("#disAgreeReturnGoods-pic-container").getImgPaths();	
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/customerServiceOrder/customerServiceOrderDisAgree',
			        data: {"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"reason":reason,"remarks":remarks,"imgPaths":imgPaths},
			        cache : false,
					async : false,
			        dataType: 'json'
			    }).done(function (result) {
			    	submiting = false;
			        if (result.returnCode =='0000') {
			        	$("#proStatus").val(proStatus);
			           	swal("操作成功");
			           	$("#disAgreeReturnGoods").text("已拒绝退货申请");
			           	$("#disAgreeReturnGoods").siblings().remove();
			           	$("#disAgreeReturnGoods").prop("readonly",true);
						$("#disAgreeReturnGoods").parent().parent().siblings().remove();
						$("#progress").html('售后进度：客户申请退货 >>商家不同意申请退货 >><span style="color: red;font-size: medium;">售后已拒绝 </span>');
						$("#disAgreeReturnGoodsDiv").hide();
			        	$("#returnGoodsBlackBox").hide();
			        	$("#viewModal").modal('hide');
			           	table.ajax.reload(null, false);
			        }else{
			        	if(result.returnMsg){
			        		swal(result.returnMsg);
			        	}else{
				        	swal("操作失败，请稍后重试");
			        	}
			        }
			    });
			});
		});
		
		
		$("#disAgreeRefund").on('click',function(){
			//添加判断，已同意退款的，再次点击不显示弹出层
			var proStatus = $("#proStatus").val();
			if(proStatus == "B4" || proStatus == "B6"){
				$("#disAgreeRefundDiv").show();
				$("#returnGoodsBlackBox").show();
			}else{
				return false;
			}
		});
		
		$("#confirmDisAgreeRefund").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var proStatus = "B6";
			var reason = $("#refundReason").val();
			var remarks = $("#refundReasonDescription").val();
			var content = "<span>不同意退款，同时关闭售后</span><br><span>拒绝原因："+reason+"<br>原因说明："+remarks+"</span>";
			if(reason == ""){
				swal("拒绝原因必选，其他选填");
				return false;
			}
			submiting = true;
			imageUploader.upload();
			imageUploader.on('uploadFinished',function(){
				var imgPaths = $("#pic-container").getImgPaths();
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/customerServiceOrder/customerServiceOrderDisAgree',
			        data: {"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"reason":reason,"remarks":remarks,"imgPaths":imgPaths},
			        cache : false,
					async : false,
			        dataType: 'json'
			    }).done(function (result) {
			    	submiting = false;
			        if (result.returnCode =='0000') {
			        	$("#proStatus").val(proStatus);
			           	swal("操作成功");
			           	$("#disAgreeRefund").text("已拒绝退款");
			           	$("#disAgreeRefund").siblings().remove();
			           	$("#disAgreeRefund").prop("readonly",true);
						$("#disAgreeRefund").parent().parent().siblings().remove();
						$("#progress").html('售后进度：客户申请退货 >>商家同意申请>>客户寄件>>商家不同意退款>><span style="color: red;font-size: medium;">售后已拒绝 </span>');
						$("#disAgreeRefundDiv").hide();
			        	$("#returnGoodsBlackBox").hide();
			        	$("#viewModal").modal('hide');
			           	table.ajax.reload(null, false);
			        }else{
			        	if(result.returnMsg){
			        		swal(result.returnMsg);
			        	}else{
				        	swal("操作失败，请稍后重试");
			        	}
			        }
			    });
			});
		});
		
		$("#memberExpress").on('click',function(){
			$("#returnGoodsMemberSendDiv").show();
			$("#returnGoodsBlackBox").show();
		});
		
		$("#updateMemberExpress").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var expressName = $("#returnGoodsExpressName").val();
			var expressNo = $("#returnGoodsExpressNo").val();
			var proStatus = "B4";
			var content="<span>已寄件</span><br><span>快递公司："+expressName+"</span><br><span>快递单号："+expressNo+"</span>";
			if(!expressName){
				swal("请选择快递名称");
				return false;
			}
			if(!expressNo){
				swal("快递单号不能为空");
				return false;
			}
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/customerServiceOrder/updateMemberExpress',
		        data: {"customerServiceOrderId":customerServiceOrderId,"expressName":expressName,"expressNo":expressNo,"proStatus":proStatus,"content":content},
		        cache : false,
				async : false,
		        dataType: 'json'
		    }).done(function (result) {
		    	submiting = false;
		        if (result.returnCode =='0000') {
		        	
					$("#viewModal").modal('hide');
		           	table.ajax.reload(null, false);
		        }else{
		        	if(result.returnMsg){
		        		swal(result.returnMsg);
		        	}else{
			        	swal("操作失败，请稍后重试");
		        	}
		        }
		    });
		});
	});
	
	
  </script>
  
  
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->

</body>
</html>
