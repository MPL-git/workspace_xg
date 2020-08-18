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
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">售后详情（<c:if test="${customerServiceOrder.serviceType eq 'A'}">退款单</c:if><c:if test="${customerServiceOrder.serviceType eq 'B'}">退货单</c:if><c:if test="${customerServiceOrder.serviceType eq 'C'}">换货单</c:if><c:if test="${customerServiceOrder.serviceType eq 'D'}">直赔单</c:if>）</span>
      </div>
     <div class="modal-body">
				<div class="panel panel-info">
    				<div class="bbbb lh_32"><span style="padding-left: 13px;">售后单编号：${customerServiceOrder.orderCode}</span><span class="pl_10">申请时间：<fmt:formatDate value="${customerServiceOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span><span class="pl_10">申请人联系电话:${customerServiceOrder.contactPhone}</span><span class="pl_10">售后状态：${proStatusDesc}(${serviceTypeDesc}${statusDesc})</span></div>
				    <ul class="list-group">
					        <li class="list-group-item" id="progress">
					        <c:if test="${customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C1'}">
					        	售后进度：客户申请换货 >><span style="color: #f55;">等待商家同意申请 </span>>><span style="color: #999;">客户寄件 </span> >><span style="color: #999;">商家同意换货</span>>><span style="color: #999;">商家寄件</span>>><span style="color: #999;">售后完成</span> 
					        </c:if>
					        <c:if test="${customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C2'}">
					        	售后进度：客户申请换货 >>商家同意申请>><span style="color: #f55;">等待客户寄件 </span>>><span style="color: #999;">商家同意换货</span>>><span style="color: #999;">商家寄件</span>>><span style="color: #999;">售后完成</span> 
					        </c:if>
					        <c:if test="${customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C4'}">
					        	 售后进度：客户申请换货 >>商家同意申请>>客户寄件 >><span style="color: #f55;">等待商家同意换货并寄件或换货改退款</span>>><span style="color: #999;">售后完成</span>
					        </c:if>
					        <c:if test="${customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C5'}">
					        	售后进度：客户申请换货 >>商家同意申请>>客户寄件 >>商家同意换货>><span style="color: #f55;">等待商家寄件</span>>><span style="color: #999;">售后完成</span>
					        </c:if>
					        <c:if test="${customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C7'}">
					        	售后进度：客户申请换货 >>商家同意申请>>客户寄件 >>商家同意换货>>商家寄件>><span style="color: #f55;">售后完成</span>
					        </c:if>
					        <c:if test="${customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C3'}">
					        	售后进度：客户申请换货 >>商家不同意申请>><span style="color: #f55;">售后已拒绝</span>
					        </c:if>
					        <c:if test="${customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C6'}">
					        	售后进度：客户申请换货 >>商家同意申请>>客户寄件>>商家不同意换货>><span style="color: #f55;">售后已拒绝</span>
					        </c:if>
					         
					        </li>
					        <li class="list-group-item">换货原因：${reasonDesc}</li>
					        <li class="list-group-item">换货说明：${customerServiceOrder.remarks}</li>
					        <c:if test="${customerServiceOrder.isAllowMchtModify == '1'}">
						        <li class="list-group-item">其他：允许商家换货改退款</li>
					        </c:if>
					        <c:if test="${not empty customerServicePics}">
						        <li class="list-group-item">
									图片凭证：
						        	<c:forEach items="${customerServicePics}" var="customerServicePic">
							        	<img class="w_h_60_60" src="${ctx}/file_servelt${customerServicePic.pic}" onclick="viewerPic(this.src,this)" name="pic">
							        </c:forEach>
						        </li>
					        </c:if>
					        <c:if test="${customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C2' && customerServiceOrder.status eq '0'}">
					        <li class="list-group-item">
					        	<button type="button" class="btn b_r_2 bc_3c9eff" id="memberExpress">为客户填写寄件快递单号</button>
					        </li>
					        </c:if>
				    </ul>
				    <c:if test="${customerServiceOrder.status == 0 && customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C1'}">
				    <div class="row">
						<div class="col-md-6" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_3c9eff w_h_130_36" id="agreeExchangeGoodsApply">同意换货申请</button><br>
				        </p>
						</div>
				        <div class="col-md-6" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_ff5555 w_h_130_36" id="disAgreeExchangeGoodsApply">不同意换货申请</button><br>
				        </p>
						</div>
				    </div>
				    </c:if>
				    <c:if test="${customerServiceOrder.status == 0 && customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C4' && interventionOrderCount == 0}">
				    <div class="row">
						<div class="col-md-2" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_3c9eff w_h_130_36" id="agreeExchangeGoods">同意换货并寄件</button><br>
				        </p>
						</div>
						<c:if test="${customerServiceOrder.isAllowMchtModify == '1'}">
						<div class="col-md-2" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_3c9eff w_h_130_36" id="changeToRefund">换货改退款</button><br>
				        </p>
						</div>
						</c:if>
				        <div class="col-md-2" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_ff5555 w_h_130_36" id="disAgreeExchangeGoods">不同意换货</button><br>
				        </p>
						</div>
				    </div>
				    </c:if>
				    <c:if test="${customerServiceOrder.status == 0 && customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C4' && interventionOrderCount > 0}">
				    <c:if test="${not empty winType}">
				    <div class="row">
						<div class="col-md-2" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_3c9eff w_h_130_36" id="agreeExchangeGoods">同意换货并寄件</button><br>
				        </p>
						</div>
						<c:if test="${customerServiceOrder.isAllowMchtModify == '1'}">
						<div class="col-md-2" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_3c9eff w_h_130_36" id="changeToRefund">换货改退款</button><br>
				        </p>
						</div>
						</c:if>
				        <div class="col-md-2" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_ff5555 w_h_130_36" id="disAgreeExchangeGoods">不同意换货</button><br>
				        </p>
						</div>
				    </div>
				    </c:if>
				    </c:if>
				    <c:if test="${customerServiceOrder.status == 0 && customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C5'}">
				    <div class="row">
						<div class="col-md-12" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_3c9eff w_h_130_36" id="showDeliveryDiv">立即发货</button><br>
				        </p>
						</div>
				    </div>
				    </c:if>
				    
				    <c:if test="${(customerServiceOrder.status == 0 || customerServiceOrder.status == 2) && customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C3'}">
				    <c:if test="${not empty winType && winType == 1}">
				    <div class="row">
						<div class="col-md-4" style="margin-left: 20px;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_3c9eff w_h_130_36" id="agreeExchangeGoodsApply">同意换货申请</button><br>
				        </p>
						</div>
				    </div>
				    </c:if>
				    </c:if>
				    
				    <c:if test="${(customerServiceOrder.status == 0 || customerServiceOrder.status == 2) && customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C6'}">
				    <c:if test="${not empty winType && winType == 1}">
				    <div class="row">
						<div class="col-md-2" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_3c9eff w_h_130_36" id="agreeExchangeGoods">同意换货并寄件</button><br>
				        </p>
						</div>
						<c:if test="${customerServiceOrder.isAllowMchtModify == '1'}">
						<div class="col-md-2" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_3c9eff w_h_130_36" id="changeToRefund">换货改退款</button><br>
				        </p>
						</div>
						</c:if>
						<div class="col-md-2" style="text-align: center;padding: 20px 0 10px;">
				        <p>
				        	<button type="button" style="margin-bottom: 7px; color: #fff;" class="btn b_r_2 bc_ff5555 w_h_130_36" id="disAgreeExchangeGoods">不同意换货</button><br>
				        </p>
						</div>
				    </div>
				    </c:if>
				    </c:if>
				    
				</div>
				<div class="row">
					<h4>换货商品信息</h4>
					<div class="col-md-12">
						<div class="panel panel-info">
	    				<div class="bc_eff9ff fs_12 ff_yahei color_333333 lh_32"><span style="padding-left: 10px;">原订单编号：${subOrderCustom.subOrderCode} <a href="javascript:;" onclick="viewSubOrder(${subOrderCustom.id},${subOrderCustom.status})">【查看】</a></span><span class="pl_10">发货时间：<fmt:formatDate value="${subOrderCustom.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span><span class="pl_10">活动ID:${orderDtl.activityId}</span></div>
					    <ul class="list-group">
						        <li class="list-group-item">
						        	<span>客户原收货人：${subOrderCustom.receiverName}</span><span class="pl_10">电话：${subOrderCustom.receiverPhone}</span><span class="pl_10">地址：${subOrderCustom.receiverAddress}</span><span class="pl_10"><a href="javascript:;" id="showWuliu"> 【查看物流动态】</a></span>
						        </li>
					    </ul>
						</div>
					</div>
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
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
							      	<div class="no-check">
							      		<div class="width-60"><img src="${ctx}/file_servelt${orderDtl.skuPic}" onclick="viewerPic(this.src,this)" name="skuPic"></div>
							      		<div class="width-226 c9"><p class="ellipsis" style="color: #333">${orderDtl.brandName}&nbsp;&nbsp;${orderDtl.productName}</p>${orderDtl.artNo}&nbsp;&nbsp;规格：${orderDtl.productPropDesc}<br>SKU码：${orderDtl.sku}</div>
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
    								<td colspan="17">
    									<div style="text-align: left;">
		    								<span>【售后模板信息】收货人：${productAfterTemplet.recipient}</span>
		    								<span>电话：${productAfterTemplet.phone}&nbsp;&nbsp;&nbsp;</span>
		    								<span>地址：${province}${city}${county}${productAfterTemplet.address}</span>
    									</div>
    								</td>
    							</tr>
							</tbody>
						</table>
					</div>	
				</div>


			 <c:if test="${customerServiceOrder.serviceType eq 'C' && customerServiceOrder.proStatus eq 'C4'}">
				 <div class="row">
				 <h4>客户寄件信息</h4>
				 <div class="col-md-12 datatable-container" >
					 <div class="bc_eff9ff fs_12 ff_yahei color_333333 lh_32" ><span style="padding-left: 10px;">【商品寄回地址】收货人：${customerServiceOrder.productReturnConsignee} </span><span class="pl_10">电话：${customerServiceOrder.productReturnContactPhone} </span><span class="pl_10">地址:${customerServiceOrder.productReturnAddress}</span></div>
					 <ul class="list-group">
						 <li class="list-group-item">
							 <span>【客户寄件信息】寄件物流：${customerServiceOrder.memberExpressCompany}</span><span class="pl_10">寄件单号：${customerServiceOrder.memberExpressNo}</span><span class="pl_10"> <a href="javascript:;" id="showReturnWuliu"> 【查看物流】</a></span>
						 </li>
					 </ul>
				 </div>
				 </div>
			 </c:if>
				<div class="row">
					<h4>售后记录</h4>
					<div class="col-md-12 datatable-container" style="overflow-y: auto;height: 310px;">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th class="w_h_200_36">操作者</th>
									<th class="w_h_740_36">内容</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${customerServiceLogCustoms}" var="customerServiceLogCustom">
								<tr>
							      <td style="text-align: left;padding: 10px;">
								      <span class="money" style="font-weight: bold;">
								      <c:if test="${customerServiceLogCustom.operatorType eq '1'}">
								      	客户
								      </c:if>
								      <c:if test="${customerServiceLogCustom.operatorType eq '2'}">
								      	商家
								      </c:if>
								      <c:if test="${customerServiceLogCustom.operatorType eq '3'}">
								      	系统
								      </c:if>
								      </span>
								      <div class="pt_10" style="padding-top: 3px;">
								      	<fmt:formatDate value="${customerServiceLogCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
								      </div>	
							      </td>
							      <td colspan="10" style="text-align: left;padding: 10px;">
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

<!--弹出同意换货申请Div-->
<div class="video_box" style="position:fixed; width:400px; height:168px; top:30%; left:40%; display: none;border-radius: 2px;" id="exchangeGoodsApplyDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="modal-header">
		        <h3 class="modal-title">
		        	同意客户售后换货申请确认
				</h3>
		    </div>
		    <div class="panel-body">
				  <div class="form-group">
				  	同意客户换货申请之后，客户将按如下地址寄回货品，注意查收。
				  </div>
				  <div class="form-group">
					  收货人 &nbsp;&nbsp; ：<input type="text" id="editRecipient" name="editRecipient" style="width:280px;" value="${productAfterTemplet.recipient}"/><br/>
					  联系电话：<input type="text" id="editPhone" name="editPhone" style="width:280px;" value="${productAfterTemplet.phone}"/><br/>
					  退货地址：<input type="text" id="editAddress" name="editAddress" style="width:280px;" value="${province}${city}${county}${productAfterTemplet.address}"/><br/>
					  注意事项：<input type="text" id="editRemarks" name="editRemarks" style="width:280px;" value="<c:if test="${empty productAfterTemplet.remarks}">无</c:if> <c:if test="${not empty productAfterTemplet.remarks}">${productAfterTemplet.remarks}</c:if>"/><br/>

					  <%--<p>收货人：${productAfterTemplet.recipient}</p>
				  		<p>联系电话：${productAfterTemplet.phone}</p>
				  		<p>退货地址：${province}${city}${county}${productAfterTemplet.address}</p>
				  		<p>注意事项：<c:if test="${empty productAfterTemplet.remarks}">无</c:if><c:if test="${not empty productAfterTemplet.remarks}">${productAfterTemplet.remarks}</c:if></p>
				  		--%>
				  		<p>在商品列表可以修改此退货地址</p>
				  </div>
				  <div class="form-group">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="confirmExchangeGoodsApply">确认</button>
				      <button type="button" class="btn btn-default" name="cancle" >取消</button>
				    </div>
				  </div>
		    </div>
		 </div>
</div>
<!--弹出同意换货Div-->
<!--  
<div class="video_box" style="position:fixed; width:400px; height:168px; top:30%; left:40%; display: none;border-radius: 2px;" id="exchangeGoodsDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="modal-header">
		        <h3 class="modal-title">
		        	同意换货确认
				</h3>
		    </div>
		    <div class="panel-body">
				  <div class="form-group">
				  	您确认同意客户换货?
				  </div>
				  <div class="form-group">
				   	 	<p style="color: #999;">同意之后，请尽快为客户寄出换新的商品。</p>
				  </div>
				  <div class="form-group">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="confirmExchangeGoods">确认</button>
				      <button type="button" class="btn btn-default" name="cancle" >取消</button>
				    </div>
				  </div>
		    </div>
		 </div>
</div>-->

<!--弹出立即发货Div-->
<div class="video_box" style="position:fixed; width:320px; height:155px; top:30%; left:40%; display: none;border-radius: 2px;" id="exchangeGoodsDeliveryDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="modal-header">
		        <h3 class="modal-title">
		        	立即发货
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" >
		    	  <input type="hidden" id="exchangeGoodsSubOrderId" value="${customerServiceOrder.subOrderId}">
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">&nbsp;&nbsp;快递名称<span style="color: red;">*</span>&nbsp;&nbsp;&nbsp;：</label>
				    <div class="col-md-7">
				    	<select class="form-control" id="exchangeGoodsExpressId">
							<option value="">--请选择--</option>
							<c:forEach var="express" items="${expressList}">
								<option value="${express.id}">${express.name}</option>
							</c:forEach>
				        </select>
				    </div>
				  </div>
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">&nbsp;&nbsp;快递单号<span style="color: red;">*</span>&nbsp;&nbsp;&nbsp;：</label>
				    <div class="col-md-7">
				      <input type="text" class="form-control" id="exchangeGoodsExpressNo" >
				    </div>
				  </div>
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">快递商户编号：</label>
				    <div class="col-md-7">
				      <input type="text" class="form-control" id="exchangeGoodsMerchantCode" >
				    </div>
				  </div>
 
				  <div class="form-group">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="exchangeGoodsDelivery">立即发货</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出不同意换货申请Div-->
<div class="video_box" style="position:fixed; width:600px; height:290px; top:35%; left:34%; display: none;border-radius: 2px;" id="disAgreeExchangeGoodsApplyDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="modal-header">
		        <h3 class="modal-title">
		        	不同意换货申请确认
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" >
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">拒绝原因<span style="color: red;">*</span>：</label>
				    <div class="col-md-7" style="padding-left: 40px;">
				    	<select class="form-control" id="reason" style="width: 200px;">
							<option value="">--请选择--</option>
							<c:forEach items="${exchangeGoodsApplySysParamCfgList}" var="exchangeGoodsApplySysParamCfg">
								<option value="${exchangeGoodsApplySysParamCfg.paramValue}">${exchangeGoodsApplySysParamCfg.paramValue}</option>
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
				    <span style="color: #999;margin-left: 30px;">每张图片大小不超过400K，最多3张，支持GIF、JPG、PNG格式</span>
				    <div id="disAgreeExchangeGoods-pic-container" style="margin-left: -60px;">
							
					</div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 60px;">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="confirmDisAgree">确认不同意</button>
				      <button type="button" class="btn btn-default" name="cancle">取消</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出不同意换货Div-->
<div class="video_box" style="position:fixed; width:600px; height:290px; top:35%; left:34%; display: none;border-radius: 2px;" id="disAgreeExchangeGoodsDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="modal-header">
		        <h3 class="modal-title">
		        	不同意换货确认
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" >
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">拒绝原因<span style="color: red;">*</span>：</label>
				    <div class="col-md-7" style="padding-left: 40px;">
				    	<select class="form-control" id="exchangeGoodsReason" style="width: 200px;">
							<option value="">--请选择--</option>
							<c:forEach items="${exchangeGoodsSysParamCfgList}" var="exchangeGoodsSysParamCfg">
								<option value="${exchangeGoodsSysParamCfg.paramValue}">${exchangeGoodsSysParamCfg.paramValue}</option>
							</c:forEach>
				        </select>
				    </div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">请填写原因说明：</label>
				    <div class="col-md-7">
				      <textarea class="form-control" style="width: 420px;" rows="3" id="exchangeGoodsReasonDescription"></textarea>
				    </div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-14 control-label" style="padding-left:80px;">上传凭证&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    <span style="color: #999;margin-left: 30px;">每张图片大小不超过400K，最多3张，支持GIF、JPG、PNG格式</span>
				    <div id="pic-container" style="margin-left: 105px;">
							
					</div>
				  </div>
				  
				  <div class="form-group">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="exchangeGoodsConfirmDisAgree">确认不同意</button>
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
							<li>
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
<div class="video_box" style="position:fixed; width:320px; height:155px; top:30%; left:40%; display: none;border-radius: 2px;" id="exchangeGoodsMemberSendDiv">
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
				    	<select class="form-control" id="exchangeGoodsExpressName">
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
				      <input type="text" class="form-control" id="exchangeGoodsMemberExpressNo" >
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
					    	您确认同意直接退款给客户：<span style="color: red;">${orderDtl.payAmount}</span>元？
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
<div class="black_box" style="display: none;" id="exchangeGoodsBlackBox"></div>
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
		
		imageUploaderApply = $("#disAgreeExchangeGoods-pic-container").myWebUploader({
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
		
		$("#showDeliveryDiv").on('click',function(){
			$("#exchangeGoodsDeliveryDiv").show();
			$("#exchangeGoodsBlackBox").show();
		});
		
		$("#showWuliu").on('click',function(){
			$("#wuliuDiv").show();
			$("#exchangeGoodsBlackBox").show();
		});


		//用户寄件的物流弹出div
		$("#showReturnWuliu").on('click',function(){
			$("#returnWuliuDiv").show();
			$("#returnGoodsBlackBox").show();
		});
		
		$("#agreeExchangeGoodsApply").on('click',function(){
			//添加判断，已同意退款的，再次点击不显示弹出层
			var proStatus = $("#proStatus").val();
			if(proStatus == "C1" || proStatus == "C3"){
				$("#exchangeGoodsApplyDiv").show();
				$("#exchangeGoodsBlackBox").show();
			}else{
				return false;
			}
		});
		
		$(".video_close").on('click',function(){
			$("#exchangeGoodsApplyDiv").hide();
			$("#exchangeGoodsDiv").hide();
			$("#exchangeGoodsDeliveryDiv").hide();
			$("#disAgreeExchangeGoodsApplyDiv").hide();
			$("#disAgreeExchangeGoodsDiv").hide();
			$("#wuliuDiv").hide();
			$("#returnWuliuDiv").hide();
			$("#exchangeGoodsMemberSendDiv").hide();
			$("#exchangeGoodsBlackBox").hide();
			$("#refundDiv").hide();
		});
		
		$("button[name='cancle']").on('click',function(){
			$("#exchangeGoodsApplyDiv").hide();
			$("#exchangeGoodsDiv").hide();
			$("#exchangeGoodsDeliveryDiv").hide();
			$("#disAgreeExchangeGoodsApplyDiv").hide();
			$("#disAgreeExchangeGoodsDiv").hide();
			$("#exchangeGoodsBlackBox").hide();
			$("#refundDiv").hide();
		});
		var submiting;
		$("#confirmExchangeGoodsApply").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var proStatus = "C2";
			var recipient = $("#editRecipient").val();
			var phone = $("#editPhone").val();
			var province = $("#province").val();
			var city = $("#city").val();
			var county = $("#county").val();
			var address = $("#editAddress").val();
			var productAfterTempletRemarks = $("#editRemarks").val();
			var content = "<span>同意售后换货申请</span><br>请客户寄件到以下地址：<br>收货人："+recipient+"<br>联系电话："+phone+"<br>地址："+address+"<br>注意事项："+productAfterTempletRemarks;
			var isRefund = "0";
			if(!recipient || !phone || !address){
				swal("请填写收货信息");
				return false;
			}
			submiting = true;
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/customerServiceOrder/customerServiceOrderAgree',
		        data: {"editRecipient":recipient,"editPhone":phone,"editAddress":address,"editRemarks":productAfterTempletRemarks,"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"isRefund":isRefund},
		        dataType: 'json',
		        cache : false,
		        dataType: 'json'
		    }).done(function (result) {
		    	submiting = false;
		        if (result.returnCode =='0000') {
		           	swal(result.returnMsg);
		           	$("#proStatus").val(proStatus);
		        	$("#exchangeGoodsApplyDiv").hide();
		        	$("#exchangeGoodsBlackBox").hide();
					$("#agreeExchangeGoodsApply").parent().parent().parent().remove();
					$("#progress").html('售后进度：客户申请换货 >>商家同意申请>><span class="f55">等待客户寄件 </span>>><span style="color: #999;">商家同意换货</span> >><span style="color: #999;">商家寄件</span> >><span style="color: #999;"> 售后完成</span>');
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
		
		
		$("#agreeExchangeGoods").on('click',function(){
			//添加判断，已同意换货并寄件的，再次点击不显示弹出层
			var proStatus = $("#proStatus").val();
			if(proStatus == "C4" || proStatus == "C6"){
				$("#exchangeGoodsDeliveryDiv").show();
				$("#exchangeGoodsBlackBox").show();
			}else{
				return false;
			}
			
//			if(proStatus == "C4"){
//				$("#exchangeGoodsDiv").show();
//				$("#exchangeGoodsBlackBox").show();
//			}else if(proStatus == "C5"){//立即发货
//				$("#exchangeGoodsDeliveryDiv").show();
//				$("#exchangeGoodsBlackBox").show();
//			}else{
//				return false;
//			}
		});
		
		$("#confirmExchangeGoods").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var proStatus = "C5";
			var content = "<span>同意换货，请耐心等待</span>";
			var isRefund = "0";
			submiting = true;
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/customerServiceOrder/customerServiceOrderAgree',
		        data: {"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"isRefund":isRefund},
		        dataType: 'json',
		        cache : false,
		        dataType: 'json'
		    }).done(function (result) {
		    	submiting = false;
		        if (result.returnCode =='0000') {
		           	swal(result.returnMsg);
		           	$("#proStatus").val(proStatus);
		        	$("#exchangeGoodsDiv").hide();
		        	$("#exchangeGoodsBlackBox").hide();
		        	$("#agreeExchangeGoods").text("立即发货");
		        	$("#agreeExchangeGoods").next().next().text("您已同意客户换货，请尽快将客户想要的货品寄出。");
					$("#agreeExchangeGoods").parent().parent().siblings().remove();
					$("#progress").html('售后进度：客户申请换货 >>商家同意申请>>客户寄件>>商家同意换货>><span style="color: #f55;">等待商家寄件</span> >><span style="color: #999;"> 售后完成</span>');
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
		
		$("#exchangeGoodsDelivery").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var subOrderId = $("#exchangeGoodsSubOrderId").val();
			var exchangeGoodsExpressId = $("#exchangeGoodsExpressId").val();
			var exchangeGoodsExpressNo = $("#exchangeGoodsExpressNo").val();
			var exchangeGoodsMerchantCode = $("#exchangeGoodsMerchantCode").val()
			var proStatus = "C7";
			var orderDtlId = ${orderDtl.id};
			var content = "<span>已寄件，快递名称："+$("#exchangeGoodsExpressId").find("option:selected").text()+"，快递单号："+exchangeGoodsExpressNo+"。</span><br>换货操作完成，实际到货时间取决于物流快递。<br>请耐心等待。同时售后完成。";
			if($.trim(exchangeGoodsExpressId)==""){
				swal("快递名称必选");
				return false;
			}
			if($.trim(exchangeGoodsExpressNo)==""){
				swal("快递单号不能为空");
				return false;
			}
			if($.trim(exchangeGoodsExpressId)==20){
				if($.trim(exchangeGoodsMerchantCode)==null||$.trim(exchangeGoodsMerchantCode)==""){
					swal("京东物流必填快递商户编号");
					return false;
				}
			}
			submiting = true;
			$.ajax({
			        method: 'POST',
			        url: '${ctx}/customerServiceOrder/subOrderDelivery',
			      	data: {"customerServiceOrderId":customerServiceOrderId,"subOrderId":subOrderId,"orderDtlId":orderDtlId,"expressId":exchangeGoodsExpressId,"expressNo":exchangeGoodsExpressNo,"merchantCode":exchangeGoodsMerchantCode,"proStatus":proStatus,"content":content},
			      	dataType: 'json',
			        cache : false,
			        dataType: 'json'
			    }).done(function (result) {
			    	submiting = false;
			        if (result.returnCode =='0000') {
			        	$("#exchangeGoodsDeliveryDiv").hide();
			        	$("#exchangeGoodsBlackBox").hide();
			        	$("#exchangeGoodsSubOrderId").val("");
			           	swal("发货成功");
			           	$("#viewModal").modal('hide');
			           	table.ajax.reload(null, false);
			        }else{
			        	if(result.returnMsg){
			        		swal(result.returnMsg);
			        	}else{
				        	swal("发货失败，请稍后重试");
			        	}
			        }
			    });
		});
		
		$("#disAgreeExchangeGoodsApply").on('click',function(){
			//添加判断，已同意退款的，再次点击不显示弹出层
			var proStatus = $("#proStatus").val();
			if(proStatus == "C1"){
				$("#disAgreeExchangeGoodsApplyDiv").show();
				$("#exchangeGoodsBlackBox").show();
			}else{
				return false;
			}
		});
		
		$("#confirmDisAgree").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var proStatus = "C3";
			var reason = $.trim($("#reason").val());
			if(reason == ""){
				swal("拒绝原因必选，其他选填");
				return false;
			}
			var remarks = $("#reasonDescription").val();
			var content = "<span>不同意换货申请，同时关闭售后</span><br><span>拒绝原因："+reason+"<br>原因说明："+remarks+"</span>";
			submiting = true;
			imageUploaderApply.upload();
			imageUploaderApply.on('uploadFinished',function(){
				var imgPaths = $("#disAgreeExchangeGoods-pic-container").getImgPaths();
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/customerServiceOrder/customerServiceOrderDisAgree',
			        data: {"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"reason":reason,"remarks":remarks,"imgPaths":imgPaths},
			        dataType: 'json',
			        cache : false,
			        dataType: 'json'
			    }).done(function (result) {
			    	submiting = false;
			        if (result.returnCode =='0000') {
			        	$("#proStatus").val(proStatus);
			           	swal("操作成功");
			           	$("#disAgreeExchangeGoodsApply").text("已拒绝换货申请");
			           	$("#disAgreeExchangeGoodsApply").siblings().remove();
			           	$("#disAgreeExchangeGoodsApply").prop("readonly",true);
						$("#disAgreeExchangeGoodsApply").parent().parent().siblings().remove();
						$("#progress").html('售后进度：客户申请换货 >>商家不同意申请换货 >><span style="color: #f55;">售后已拒绝 </span>');
						$("#disAgreeExchangeGoodsApplyDiv").hide();
			        	$("#exchangeGoodsBlackBox").hide();
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
		
		
		$("#disAgreeExchangeGoods").on('click',function(){
			//
			var proStatus = $("#proStatus").val();
			if(proStatus == "C4" || proStatus == "C6"){
				$("#disAgreeExchangeGoodsDiv").show();
				$("#exchangeGoodsBlackBox").show();
			}else{
				return false;
			}
		});
		
		$("#exchangeGoodsConfirmDisAgree").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var proStatus = "C6";
			var reason = $.trim($("#exchangeGoodsReason").val());
			if(reason == ""){
				swal("拒绝原因必选，其他选填");
				return false;
			}
			var remarks = $("#exchangeGoodsReasonDescription").val();
			var content = "<span>不同意换货，同时关闭售后</span><br><span>拒绝原因："+reason+"<br>原因说明："+remarks+"</span>";
			submiting = true;
			imageUploader.upload();
			imageUploader.on('uploadFinished',function(){
				imgPaths = $("#pic-container").getImgPaths();
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/customerServiceOrder/customerServiceOrderDisAgree',
			        data: {"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"reason":reason,"remarks":remarks,"imgPaths":imgPaths},
			        dataType: 'json',
			        cache : false,
			        dataType: 'json'
			    }).done(function (result) {
			    	submiting = false;
			        if (result.returnCode =='0000') {
			        	$("#proStatus").val(proStatus);
			           	swal("操作成功");
			           	$("#disAgreeExchangeGoods").text("已拒绝换货");
			           	$("#disAgreeExchangeGoods").siblings().remove();
			           	$("#disAgreeExchangeGoods").prop("readonly",true);
						$("#disAgreeExchangeGoods").parent().parent().siblings().remove();
						$("#progress").html('售后进度：客户申请换货 >>商家同意申请换货 >>客户寄件>>商家不同意换货>><span style="color: #f55;">售后已拒绝 </span>');
						$("#disAgreeExchangeGoodsDiv").hide();
			        	$("#exchangeGoodsBlackBox").hide();
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
			$("#exchangeGoodsMemberSendDiv").show();
			$("#exchangeGoodsBlackBox").show();
		});
		
		$("#updateMemberExpress").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var expressName = $("#exchangeGoodsExpressName").val();
			var expressNo = $("#exchangeGoodsMemberExpressNo").val();
			var proStatus = "C4";
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
		        dataType: 'json',
		        cache : false,
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
			        	swal(result.returnMsg);
		        	}
		        }
		    });
		});
		
		$("#changeToRefund").on('click',function(){
			//添加判断，已同意退款的，再次点击不显示弹出层
			var proStatus = $("#proStatus").val();
			if(proStatus == "C4" || proStatus == "C6"){
				$("#refundDiv").show();
				$("#exchangeGoodsBlackBox").show();
			}else{
				return false;
			}
		});
		
		$("#confirm").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var proStatus = "B5";
			var content = "<span>换货改退款，请耐心等待。</span>";
			var isRefund = "1";
			submiting = true;
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/customerServiceOrder/customerServiceOrderAgree',
		        data: {"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"isRefund":isRefund},
		        dataType: 'json',
		        cache : false,
		        dataType: 'json'
		    }).done(function (result) {
		    	submiting = false;
		        if (result.returnCode =='0000') {
		        	$("#proStatus").val(proStatus);
		        	$("#exchangeGoodsBlackBox").hide();
		           	swal(result.returnMsg);
					$("#changeToRefund").parent().parent().parent().remove();
					$("#progress").html('售后进度：客户申请换货 >>商家同意申请换货 >>客户寄件>>换货改退款>><span style="color: red;font-size: medium;">等待平台退款 </span>');
					$("#refundDiv").hide();
					$("#exchangeGoodsBlackBox").hide();
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
