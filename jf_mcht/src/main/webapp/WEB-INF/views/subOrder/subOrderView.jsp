<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<link href="${pageContext.request.contextPath}/static/css/subOrder.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/css/star.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
tr.td-border-top>td {
    border-top: 1px dotted #ddd !important;
}

.color-1{
color: #9D999D;
}
.color-2{
color: #FC0000;
}
.color-3{
color: #EFD104;
}
.color-4{
color: #00FC28;
}
.color-5{
color: #0351F7;
}
.color-6{
color: #DF00FB;
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
<style>  
        /*样式1*/  
        .a-upload {  
            padding: 4px 10px;  
            height: 20px;  
            line-height: 20px;  
            position: relative;  
            cursor: pointer;  
            color: #888;  
            background: #fafafa;  
            border: 1px solid #ddd;  
            border-radius: 4px;  
            overflow: hidden;  
            display: inline-block;  
            *display: inline;  
            *zoom: 1  
        }  
          
        .a-upload  input {  
            position: absolute;  
            font-size: 100px;  
            right: 0;  
            top: 0;  
            opacity: 0;  
            filter: alpha(opacity=0);  
            cursor: pointer  
        }  
          
        .a-upload:hover {  
            color: #444;  
            background: #eee;  
            border-color: #ccc;  
            text-decoration: none  
        }  
        /*样式2*/  
        .file {  
            position: relative;  
            display: inline-block;  
            background: #3c9eff;  
            border-radius: 4px;  
            padding: 0px 12px;  
            overflow: hidden;  
            color: #fff;  
            text-decoration: none;  
            text-indent: 0;  
            line-height: 20px;  
        }  
        .file input {  
            position: absolute;  
            font-size: 100px;  
            right: 0;  
            top: 0;  
            opacity: 0;  
        }  
        .file:hover {  
            background: #AADFFD;  
            border-color: #78C3F3;  
            color: #004974;  
            text-decoration: none;  
        }  
    </style> 
<title>订单详情</title>
</head>

<body>
<!--查看订单详情 -->
  <div class="modal-dialog dd-xq" role="document" style="width:1000px;">
  <input type="hidden" id="subOrderId" value="${subOrderCustom.id}">
  <input type="hidden" id="orderCreateDate" value="<fmt:formatDate value="${subOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
  <input type="hidden" id="payDateTime" value="<fmt:formatDate value="${payDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
  <input type="hidden" id="sendDateTime" value="<fmt:formatDate value="${sendDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
  <input type="hidden" id="currentStatusDateTime" value="<fmt:formatDate value="${currentOrderLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
  <input type="hidden" id="mchtSettleOrderId" value="${mchtSettleOrderId}">
  <input type="hidden" id="payAmount" value="${subOrderCustom.payAmount}">
  <input type="hidden" id="deliveryLastDate" value="<fmt:formatDate value="${subOrderCustom.deliveryLastDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
  <input type="hidden" id="hasLoadComment" value="0">  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">订单详情  <c:if test="${subOrderCustom.auditStatus eq '1' || subOrderCustom.auditStatus eq '2'}"><span style="color: red">(待审核)</span></c:if> <c:if test="${subOrderCustom.auditStatus eq '4'}"><span style="color: red">(不通过)</span></c:if></span>
      </div>
     <div class="modal-body">
				<div class="row">
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<tbody id="process">
								<c:if test="${subOrderCustom.status eq '0'}">
									<tr>
										<td class="complete w_h_188_36">1.客户下单</td>
										<td class="progress w_h_188_36">2.客户付款</td>
										<td class="w_h_188_36">3.商家发货</td>
										<td class="w_h_190_36">4.客户确认收货</td>
										<td class="w_h_190_36">5.订单生成结算</td>
									</tr>
									<tr>
										<td><fmt:formatDate value="${subOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td id="waitPay"></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</c:if>
								<c:if test="${subOrderCustom.status eq '1'}">
									<tr>
										<td class="complete w_h_188_36">1.客户下单</td>
										<td class="complete w_h_188_36">2.客户付款</td>
										<td class="progress w_h_188_36">3.商家发货</td>
										<td class="w_h_190_36">4.客户确认收货</td>
										<td class="w_h_190_36">5.订单生成结算</td>
									</tr>
									<tr>
										<td><fmt:formatDate value="${subOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${subOrderCustom.statusDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td id="waitDeliveryTime"></td>
										<td></td>
										<td></td>
									</tr>
								</c:if>
								<c:if test="${subOrderCustom.status eq '2'}">
									<tr>
										<td class="complete w_h_188_36">1.客户下单</td>
										<td class="complete w_h_188_36">2.客户付款</td>
										<td class="complete w_h_188_36">3.商家发货</td>
										<td class="progress w_h_190_36">4.客户确认收货</td>
										<td class="w_h_190_36">5.订单生成结算</td>
									</tr>
									<tr>
										<td><fmt:formatDate value="${subOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${payDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${subOrderCustom.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td id="waitReceive"></td>
										<td></td>
									</tr>
								</c:if>
								<c:if test="${subOrderCustom.status eq '3'}">
									<tr>
										<td class="complete w_h_188_36">1.客户下单cu</td>
										<td class="complete w_h_188_36">2.客户付款</td>
										<td class="complete w_h_188_36">3.商家发货</td>
										<td class="complete w_h_190_36">4.客户确认收货</td>
										<td class="complete w_h_190_36">5.订单生成结算</td>
									</tr>
									<tr>
										<td><fmt:formatDate value="${subOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${payDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${subOrderCustom.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${subOrderCustom.receiptDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td id="orderSettlement"></td>
									</tr>
								</c:if>
								<c:if test="${subOrderCustom.status eq '5'}">
									<tr>
										<td class="complete">1.客户下单</td>
										<td class="complete">2.客户付款</td>
										<td class="progress">3.订单关闭</td>
									</tr>
									<tr>
										<td><fmt:formatDate value="${subOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${payDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${subOrderCustom.statusDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									</tr>
								</c:if>
								<c:if test="${subOrderCustom.status eq '4'}">
									<tr>
										<td class="complete">1.客户下单</td>
										<td class="progress">2.订单关闭</td>
									</tr>
									<tr>
										<td><fmt:formatDate value="${subOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${subOrderCustom.statusDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									</tr>
								</c:if>
								<c:if test="${subOrderCustom.status eq '6'}">
									<tr>
										<td class="complete w_h_188_36">1.客户下单</td>
										<td class="complete w_h_188_36">2.客户付款</td>
										<td class="complete w_h_188_36">3.商家发货</td>
										<td class="progress w_h_190_36">4.客户确认收货</td>
										<td>5.订单生成结算</td>
									</tr>
									<tr>
										<td><fmt:formatDate value="${subOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${payDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${subOrderCustom.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${subOrderCustom.receiptDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td id="orderSettlement"></td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="panel panel-default h_110">
				    <div class="panel-body" style="height: 55px;line-height: 54px;padding: 0 10px;">
				       <span id="orderStatusDesc" class="ff_yahei color_000000">
				       		<c:if test="${status eq '0'}">
					        	订单状态:待客户付款 
				       		</c:if>
				       		<c:if test="${status eq '1'}">
					        	订单状态:待商家发货
					        	<c:if test="${!notDelivery}">
						        	<!-- <span class="pl_20"><button type="button" class="btn btn-danger" data-suborderid="${subOrderCustom.id}" id="showDeliveryDiv">立即发货</button></span> -->
					        	</c:if>
				       		</c:if>
				       		<c:if test="${status eq '2'}">
					        	订单状态:商家已发货 &nbsp;&nbsp;&nbsp;
					        	<c:if test="${hasDirectCompensation eq '0'}">
					        		<c:if test="${!hasCustomerServiceOrder}">
<%--										<c:if test="${mchtCode eq 'P193137' or mchtCode eq 'P193162' or mchtCode eq 'P193022'or mchtCode eq 'P193068'or mchtCode eq 'P193293'or mchtCode eq 'P193231'or mchtCode eq 'P192688' or mchtCode eq 'S170042'}">--%>
<%--										<a href="javascript:;" class="btn btn-danger" id="showDirectCompensationDiv" >创建直赔 </a>--%>
<%--										</c:if>--%>
										<a href="javascript:;" class="btn btn-danger" id="showDirectCompensationDiv" style="display: none;">创建直赔</a>
									</c:if>
					        	</c:if>
					        	<c:if test="${hasDirectCompensation eq '1'}">
					        		<c:if test="${empty hasRefund}">
						        		<a href="javascript:;" class="btn btn-info" onclick="viewServiceOrderBySubOrderId(${subOrderCustom.id});">直赔单进行中</a>
					        		</c:if>
					        		<c:if test="${hasRefund eq '1'}">
						        		<a href="javascript:;" class="btn btn-info" onclick="viewServiceOrderBySubOrderId(${subOrderCustom.id});">已直赔</a>
					        		</c:if>
					        	</c:if>
				       		</c:if>
				       		<c:if test="${status eq '3'}">
					        	订单状态:订单完成 &nbsp;&nbsp;&nbsp;
					        	<c:if test="${hasRefund eq '1'}">
						        	<a href="javascript:;" class="btn btn-info" onclick="viewServiceOrderBySubOrderId(${subOrderCustom.id});">已直赔</a>
					        	</c:if>
				       		</c:if>
				       		<c:if test="${status eq '4'}">
					        	订单状态:订单关闭 
				       		</c:if>
				       		<c:if test="${status eq '5'}">
					        	订单状态:已退款订单关闭 
				       		</c:if>
				       		<c:if test="${status eq '6'}">
					        	订单状态:已收货
				       		</c:if>
				       </span>
				    </div>
    				<div class="panel-footer" style="background-color: white;padding: 8px 15px;border: none;" id="tipDesc">
						<span>    				
	    				<c:if test="${status eq '0'}">
	    					请耐心等待客户在线支付订单金额。请不要与客户联系。如有此类投诉并被证实，将产生违规处罚。
	    				</c:if>
	    				<c:if test="${status eq '1'}">
	    					请商家及时发货，发货时务必检查好货品，确保货品完整无误，包装完好。如有超时发货，将产生违规处罚。
	    				</c:if>
	    				<c:if test="${status eq '2'}">
	    					如果客户提出售后要求，请积极与客户协商，做好售后服务。如有投诉并被证实，将产生违规处罚。
	    				</c:if>
	    				<c:if test="${status eq '3'}">
	    					如果客户提出售后要求，请积极与客户协商，做好售后服务。如有投诉并被证实，将产生违规处罚。
	    				</c:if>
	    				<c:if test="${status eq '4'}">
	    					<c:if test="${cancelType eq '1'}">客户取消</c:if>
	    					<c:if test="${cancelType eq '2'}">系统取消</c:if>
	    					,原因：${cancelReason}
	    				</c:if>
	    				<c:if test="${status eq '5'}">
	    					客户申请退款，商家已处理完毕，订单关闭。
	    				</c:if>
	    				<c:if test="${status eq '6'}">
	    					如果客户提出售后要求，请积极与客户协商，做好售后服务。如有投诉并被证实，将产生违规处罚。
	    				</c:if>
	    				</span>
    				</div>
				</div>
				<div class="panel panel-default">
    				<div class="panel-heading" style="background-color: #eff9ff;">订单编号：${subOrderCustom.subOrderCode} &nbsp;&nbsp;&nbsp; 下单时间：<fmt:formatDate value="${subOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp; 活动ID：${subOrderCustom.activityIds}</div>
				    <c:if test="${subOrderCustom.status ne '0'}">
				    <div class="panel-body w_h_940_36 preferential">
				        <p>
				       		 收货人：${subOrderCustom.receiverName}  电话：${subOrderCustom.receiverPhone} 地址：${subOrderCustom.receiverAddress}
				        </p>
				    </div>
				    </c:if>
				    <ul class="list-group preferential">
				    	<c:if test="${subOrderCustom.status ne '0' && subOrderCustom.status ne '1'}">
					        <li class="list-group-item">快递名称：${subOrderCustom.expressName} 快递单号：${subOrderCustom.expressNo}</li>
				    	</c:if>
					    <li class="list-group-item" style="background: #fff8f8;color: #333;">
					    	客户备注：<c:if test="${not empty remarks}">${remarks}</c:if><c:if test="${empty remarks}">无</c:if>
					    </li>
				    </ul>
				</div>
				<div class="row">
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row" class="active">
									<th>商品</th>
									<th>单价</th>
									<th>数量</th>
									<th>商家优惠</th>
									<th>运费</th>
									<th>平台优惠</th>
									<th>积分优惠</th>
									<th>实付金额</th>
									<th>售后状态</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${orderDtlCustoms}" var="orderDtlCustom">
								<tr>
							      <td>
							      	<div class="no-check">
							      		<div class="width-60">
							      			<img src="${ctx}/file_servelt${orderDtlCustom.skuPic}" onclick="viewerPic(this.src)">
							      		</div>
							      		<div class="width-226 c9">
								      		<p class="ellipsis" style="color: #333">
									      		${orderDtlCustom.brandName}&nbsp;&nbsp;
									      		${orderDtlCustom.productName}
								      		</p>
							      			${orderDtlCustom.artNo}&nbsp;&nbsp;规格：${orderDtlCustom.productPropDesc}
							      			<br>SKU码：${orderDtlCustom.sku}
							      			<c:if test="${orderDtlCustom.markedOutOfStock eq 1}"><br><p style="color: red;">已标记缺货</p></c:if>
							      			<c:if test="${not empty orderDtlCustom.dtlExpressId}"><br>快递名称：${orderDtlCustom.dtlExpressName}</c:if>
							      			<c:if test="${not empty orderDtlCustom.dtlExpressNo}">
							      				<br>快递单号：<a href="javascript:;" onclick="getWuliu(${orderDtlCustom.id});">${orderDtlCustom.dtlExpressNo}</a>
							      			</c:if>
							      		</div>
							      	</div>
							      </td>
							      <td>${orderDtlCustom.salePrice}<c:if test="${not empty orderDtlCustom.svipDiscount}"><br>SVIP价</c:if></td>
							      <td>${orderDtlCustom.quantity}</td>
							      <td>${orderDtlCustom.mchtPreferential}</td>
							      <td>
							      	<c:if test="${combineOrderStatus eq '0'}">
								      	<input style="width: 50px;" name="eachFreight" orderDtlId="${orderDtlCustom.id}" value="${orderDtlCustom.freight}" disabled="disabled"><br><a href="javascript:;" data-orderdtlid="${orderDtlCustom.id}" onclick="editFreight(this)">修改运费</a>
							      	</c:if>
							      	<c:if test="${combineOrderStatus ne '0'}">
								      	${orderDtlCustom.freight}
							      	</c:if>
							      </td>
							      <td>${orderDtlCustom.platformPreferential}</td>
							      <td>${orderDtlCustom.integralPreferential}</td>
							      <td>${orderDtlCustom.payAmount}</td>
							      <td>
							      <c:if test="${not empty orderDtlCustom.customerServiceStatusDesc}">
							      	${orderDtlCustom.customerServiceType}
							      	<br>
							      	${orderDtlCustom.customerServiceStatusDesc}
							      	<br>
							      	<c:if test="${orderDtlCustom.customerServiceStatus ne '3'}">
							      		<a href="javascript:;" onclick="viewServiceOrder(${orderDtlCustom.id});">查看</a>
							      	</c:if>
							      </c:if>
							      </td>
    							<tr>
							</c:forEach>
							<tr>
								<td class="w_h_340_36">合计</td>
								<td id="tofixed_tagPrice"></td>
								<td>${quantity}</td>
								<td id="tofixed_mchtPreferential">${mchtPreferential}</td>
								<td id="subOrderFreight">${subOrderCustom.freight}</td>
								<td id="tofixed_platformPreferential">${platformPreferential}</td>
								<td id="tofixed_integralPreferential">${integralPreferential}</td>
								<td id="tofixed_payAmount">${payAmount}</td>
								<td></td>
							</tr>
							<script>
								function toFixedTd(elem, str) {
									elem.innerHTML = Number(str).toFixed(2);
								}

								toFixedTd(tofixed_mchtPreferential, '${mchtPreferential}');
								toFixedTd(tofixed_platformPreferential, '${platformPreferential}');
								toFixedTd(tofixed_integralPreferential, '${integralPreferential}');
								toFixedTd(tofixed_payAmount, '${payAmount}');
							</script>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<h4>平台优惠</h4>
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row" class="active">
									<th>优惠归类</th>
									<th>优惠类型</th>
									<th>优惠内容</th>
									<th>优惠金额</th>
									<th>商品优惠金额</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${orderPreferentialInfoCustoms}" var="orderPreferentialInfoCustom">
								<tr>
							      <td class="w_h_100_36">${orderPreferentialInfoCustom.belongDesc}</td>
							      <td class="w_h_100_36">${orderPreferentialInfoCustom.preferentialTypeDesc}</td>
							      <td class="w_h_280_36">${orderPreferentialInfoCustom.preferentialContent}</td>
							      <td class="w_h_90_36">${orderPreferentialInfoCustom.totalPreferentialAmount}</td>
							      <td class="w_h_374_36 f_f_c_l">${orderPreferentialInfoCustom.productPreferentialDetail}</td>
    							<tr>
    						</c:forEach>	
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<h4 style="float: left;">附件管理&nbsp;&nbsp;</h4><a href="javascript:;" class="file">上传<input type="file" id="uploadFile" onchange="uploadFile(this)"><input type="hidden" id="filePath"></a>
				<!-- 	<a href="javascript:;" type="button" class="btn btn-info" style="width: 48px;line-height: 20px;margin-top: -21px;border-radius: 4px;padding: 4px;">下载</a > -->
					</div>
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row" class="active">
									<th>时间</th>
									<th>上传人</th>
									<th>上传附件</th>
								</tr>
							</thead>
							<tbody id="attachmentTbody">
							<c:forEach items="${subOrderAttachments}" var="subOrderAttachment">
								<tr>
							      <td class="w_h_100_36"><fmt:formatDate value="${subOrderAttachment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							      <td class="w_h_100_36">${userCode}</td>
							      <td class="w_h_280_36"><a href="javascript:;" name="downLoadSubOrderAttachment" data-suborderattachmentid="${subOrderAttachment.id}" data-filename="${subOrderAttachment.name}" data-filepath="${subOrderAttachment.attachmentPath}">${subOrderAttachment.name}</a></td>
    							<tr>
    						</c:forEach>	
							</tbody>
						</table>
					</div>
				</div>
				<c:if test="${subOrderCustom.status == '3'}">
				<div class="row">
					<h4 style="float: left;">客户评价</h4>
					<a href="javascript:;" id="openComment"><span class="glyphicon glyphicon-chevron-down" style="font-size: 15px;">展开</span></a>
					<a href="javascript:;" id="closeComment" style="display: none;"><span class="glyphicon glyphicon-chevron-up" style="font-size: 15px;">收起</span></a>
        			<div style="margin-left: 15px;display: none;border: 1px solid #8E8E8E;" id="commentArea">
						
					</div>
				</div>
				</c:if>
				<div class="row">
					<h4>商家备注日志</h4>
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row" class="active">
									<th>时间</th>
									<th>备注人</th>
									<th>备注内容</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${mchtRemarksLogCustoms}" var="mchtRemarksLogCustom">
								<tr>
							      <td class="w_h_100_36">
							      	<fmt:formatDate value="${mchtRemarksLogCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
							      </td>
							      <td class="w_h_100_36">
							      	<c:if test="${mchtRemarksLogCustom.operatorType == '1'}">
							      		${mchtRemarksLogCustom.mchtUserCode}
							      	</c:if>
							      	<c:if test="${mchtRemarksLogCustom.operatorType == '2'}">
							      		平台
							      	</c:if>
							      </td>
							      <td class="w_h_280_36"  style="word-wrap:break-word;word-break:break-all">
							      	<c:if test="${mchtRemarksLogCustom.remarksColor eq '1'}">
				    					<span class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span>
				    				</c:if>
							    	<c:if test="${mchtRemarksLogCustom.remarksColor eq '2'}">
							    		<span class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span>
							    	</c:if>
							    	<c:if test="${mchtRemarksLogCustom.remarksColor eq '3'}">
							    		<span class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span>
							    	</c:if>
							    	<c:if test="${mchtRemarksLogCustom.remarksColor eq '4'}">
							    		<span class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span>
							    	</c:if>
							    	<c:if test="${mchtRemarksLogCustom.remarksColor eq '5'}">
							    		<span class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span>
							    	</c:if>
							    	<c:if test="${mchtRemarksLogCustom.remarksColor eq '6'}">
							    		<span class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span>
							    	</c:if>
							      	${mchtRemarksLogCustom.remarks}
							      </td>
    							<tr>
    						</c:forEach>	
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="wlxx">
						<h4>物流信息</h4>
						<c:if test="${hasWuliu}">
							<c:set var="startIndex" value="${fn:length(wuliuInfos)-1 }"></c:set>  
		                    <c:forEach var="wuliuInfo" items="${wuliuInfos}" varStatus="status">
		                    	<div class="panel-body">
		                    		<p <c:if test="${status.index == 0}">style="color: green;"</c:if>>  
		                            	${wuliuInfos[startIndex - status.index].AcceptTime}
		                            	<br>
		                            	${wuliuInfos[startIndex - status.index].AcceptStation}
		                            </p>  
		                        </div>  
		                    </c:forEach>
						</c:if>
						<c:if test="${!hasWuliu}">
							<div class="panel-body">
		                    		<p>  
		                            	无
		                            </p>  
		                        </div>
						</c:if>
					</div>
				  <div class="form-group sjbz">
				    <label for="name" class="money">商家备注</label>
				    <div id="remarksColors">
				    		<a href="javascript:;" name="remarksColor" data-remarkscolor="1"><span class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span></a>
				    		<a href="javascript:;" name="remarksColor" data-remarkscolor="2"><span class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor" data-remarkscolor="3"><span class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor" data-remarkscolor="4"><span class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor" data-remarkscolor="5"><span class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor" data-remarkscolor="6"><span class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span></a>
					    	
				    </div>
				    <textarea class="form-control" rows="3" id="remarks" >${subOrderCustom.remarks}</textarea>
				    <span id="selectedRemark">
				    	旗帜设置为:
				    	<c:if test="${subOrderCustom.remarksColor eq '1'}">
				    		<span class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span>
				    	</c:if>
				    	<c:if test="${subOrderCustom.remarksColor eq '2'}">
				    		<span class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span>
				    	</c:if>
				    	<c:if test="${subOrderCustom.remarksColor eq '3'}">
				    		<span class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span>
				    	</c:if>
				    	<c:if test="${subOrderCustom.remarksColor eq '4'}">
				    		<span class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span>
				    	</c:if>
				    	<c:if test="${subOrderCustom.remarksColor eq '5'}">
				    		<span class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span>
				    	</c:if>
				    	<c:if test="${subOrderCustom.remarksColor eq '6'}">
				    		<span class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span>
				    	</c:if>
				    </span>
					<div style="float:right;" id="remarksBtnDiv">
					    <input type="hidden" id="remarksColor" value="${subOrderCustom.remarksColor}">
						<button type="button" class="btn btn-default" id="saveRemarks">提交备注</button>
					</div>
				  </div>
				</div>
				
    </div>
  </div>
  <!--弹出立即发货Div-->
<div class="video_box" style="position:fixed; width:320px; height:170px; top:35%; left:35%; display: none;" id="viewDeliveryDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="modal-header">
		        <h3 class="modal-title">
		        	立即发货
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" >
		    	  <input type="hidden" id="viewDeliverySubOrderId">
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">快递名称<span style="color: #f55;">*</span>：</label>
				    <div class="col-md-7">
				    	<select class="form-control" id="viewExpressId" name="viewExpressId">
							<option value="">--请选择--</option>
							<c:forEach var="express" items="${expressList}">
								<option value="${express.id}">${express.name}</option>
							</c:forEach>
				        </select>
				    </div>
				  </div>
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">快递单号<span style="color: #f55;">*</span>：</label>
				    <div class="col-md-7">
				      <input type="text" class="form-control" id="viewExpressNo" >
				    </div>
				  </div>
 
				  <div class="form-group" style="margin: 0;">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="viewDelivery">立即发货</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出修改运费Div-->
<div class="video_box" style="position:fixed; width:320px; height:170px; top:35%; left:35%; display: none;" id="editFreightDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="modal-header">
		        <h3 class="modal-title">
		        	修改运费
				</h3>
		    </div>
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" >
		    	  <input type="hidden" id="orderDtlId">	
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">运费<span style="color: #f55;">*</span>：</label>
				    <div class="col-md-7">
				      <input type="text" class="form-control" id="editFreight" data-type="money">
				    </div>
				  </div>
 
				  <div class="form-group" style="margin: 0;">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="cancle">取消</button>
				      <button type="button" class="btn btn-default" id="confirm">确定</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>

  <!--弹出直赔Div-->
<div class="video_box" style="position:fixed; width:375px; height:250px; top:35%; left:35%; display: none;" id="viewDirectCompensationDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="modal-header">
		        <h3 class="modal-title">
		        	创建直赔单
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" >
		    	  <input type="hidden" id="viewDeliverySubOrderId">
		    	  
		    	  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">直赔金额<span style="color: #f55;">*</span>：</label>
				    <div class="col-md-7" style="display: inline-flex">
				      <input type="text" class="form-control" id="amount" name="amount" data-type="money">元
				    </div>
				  </div>
		    	  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">直赔原因<span style="color: #f55;">*</span>：</label>
				    <div class="col-md-7">
				    	<select class="form-control" id="reason" name="reason">
							<option value="">--请选择--</option>
							<option value="D1">运费补偿</option>
							<option value="D2">收货后少件补偿</option>
							<option value="D3">商品质量问题补偿</option>
							<option value="D4">商品描述差异问题补偿</option>
							<option value="D5">活动返现类补偿</option>
							<option value="D6">维修退换货瑕疵类补偿</option>
							<option value="D7">因配送问题补偿</option>
							<option value="D8">其他类补偿</option>
				        </select>
				    </div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">原因说明<span style="color: red;">*</span>：</label>
				    <div class="col-md-7">
				      <textarea class="form-control" rows="3" id="customerServiceOrderRemarks" name="remarks"></textarea>
				    </div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">客户电话：</label>
				    <div class="col-md-7" >
				      <input type="text" class="form-control" id="contactPhone" name="contactPhone" data-type='number'>
				      <span>(选填)</span>
				    </div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">是否同意：</label>
				    <div class="col-md-7" style="line-height: 26px;">
				      <input type="checkbox" class="" id="isAgree" >同意直接赔付给客户。
				    </div>
				  </div>
 
				  <div class="form-group" style="margin: 0;display: none;">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="createDirectCompensation">创建直赔</button>
				      <br>
				      <span>此金额由平台堑付，请商家不要重复支付。</span>
				    </div>
				  </div>
			 </form>
		 </div>
</div>
<!--弹出物流信息Div-->
<div class="video_box" style="position:fixed; width:700px; height:250px; top:20%; left:15%; display: none;" id="wuliuInfoDiv">
	    <div class="modal-content">
	    <div class="modal-header">
	    	<span class="modal-title" >查看物流信息</span>
	    	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
	    </div>
		<div class="modal-body">
			<form id="dataFrom">
				<div class="wlxx" id="wuliuContent">
				</div>
			</form>
		</div>
		</div>
</div>

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>
<div class="black_box" style="display: none;" id="viewBlackBox"></div>
<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;"></ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
  <script type="text/javascript">
  function getWuliu(orderDtlId){
	  $.ajax({
	        method: 'POST',
	        url: '${ctx}/subOrder/getWuliuByOrderDtlId',
	        data: {"orderDtlId":orderDtlId},
	        dataType: 'json'
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	        	var wuliuInfos = result.returnData.wuliuInfos;
	        	var html = [];
	        	if(wuliuInfos){
		        	var index = wuliuInfos.length-1;
		        	for(var i=0;i<wuliuInfos.length;i++){
		        		html.push('<div class="panel-body">');
		        		if(i==0){
		        			html.push('<p style="color: green;">');
		        		}else{
		        			html.push('<p>');
		        		}
		        		html.push(wuliuInfos[index-i].AcceptTime);  
		        		html.push('<br>');  
		        		html.push(wuliuInfos[index-i].AcceptStation);
		        		html.push('</p>');  
		        		html.push('</div>');     
		        	}
	        	}else{
	        		html.push("暂无物流信息");
	        	}
	        	$("#wuliuContent").html(html.join(""));
	        	$("#wuliuInfoDiv").show();
	        	$("#viewBlackBox").show();
	        }else{
	        	swal(result.returnMsg);
	        }
	    });
  }
  
  function editFreight(_this){
	  $("#editFreightDiv").show();
	  $("#viewBlackBox").show();
	  $("#editFreight").val($(_this).prev().prev().val());
	  $("#orderDtlId").val($(_this).data("orderdtlid"));
  }
  
  function uploadFile(obj) {
		if (obj.files.length === 0) {
			return;
		}
		var oFile = obj.files[0];
		var rFilter = ["jpg","bmp","png","gif","JPG","BMP","PNG","GIF","mp3","wav","wma","ogg","ape","acc","MP3","WAV","WMA","OGG","APE","ACC","avi","mp4","mov","rm","wma","mkv","rmvb","AVI","MP4","MOV","RM","WMA","MKV","RMVB","doc","docx","xls","xlsx","ppt","pptx","pdf","rar","zip","DOC","DOCX","XLS","XLSX","PPT","PPTX","PDF","RAR","ZIP"];
	 	var suffix = oFile.name.substring(oFile.name.lastIndexOf(".")+1);
	 	var fileName = oFile.name;
		if($.inArray(suffix,rFilter)==-1){
			swal("文件格式不正确！");
			return;
		}
		if(oFile.size>10*1024*1024){
	        swal("文件大小不能超过10M");
	        return;
	    }
		var reader = new FileReader();  
	    reader.onload = function(e) { 
	    	var formData = new FormData();
	    	formData.append("file",oFile);
	    	formData.append("fileType",5);
	    	$.ajax({ 
	    		url : "${ctx}/common/fileUpload", 
	    		type : 'POST', 
	    		data : formData, 
	    		async: false,
	    		// 告诉jQuery不要去处理发送的数据
	    		processData : false, 
	    		// 告诉jQuery不要去设置Content-Type请求头
	    		contentType : false,
	    		beforeSend:function(){
	    			console.log("文件片上传中，请稍候");
	    		},
	    		success : function(data) {
	                if (data.returnCode=="0000") {
	                    var filePath = data.returnData;
	                    var subOrderId = $("#subOrderId").val();
                    	$.ajax({
            		        method: 'POST',
            		        url: '${ctx}/subOrder/saveSubOrderAttachment',
            		        data: {"id":subOrderId,"filePath":filePath,"fileName":fileName},
            		        dataType: 'json'
            		    }).done(function (result) {
            		        if (result.returnCode =='0000') {
            		           	swal("上传成功");
            		           	var subOrderAttachment = result.returnData;
            		           	var createDate = new Date(subOrderAttachment.createDate).format("yyyy-MM-dd hh:mm:ss");
            		           	var userCode = '${userCode}';
            		           	var html = [];
            		           	html.push('<tr>');
            		           	html.push('<td class="w_h_100_36">'+createDate+'</td>');
            		           	html.push('<td class="w_h_100_36">'+userCode+'</td>');
            		           	html.push('<td class="w_h_280_36"><a href="javascript:;" name="downLoadSubOrderAttachment" data-suborderattachmentid="'+subOrderAttachment.id+'" data-filename="'+subOrderAttachment.name+'" data-filepath="'+subOrderAttachment.attachmentPath+'">'+subOrderAttachment.name+'</a></td>');
            		           	html.push('<tr>');
            		           	$("#attachmentTbody").append(html.join(""));
            		           	
            		           	$("a[name='downLoadSubOrderAttachment']").on('click',function(){
            		    			var subOrderAttachmentId = $(this).data("suborderattachmentid");
            		    			var fileName = $(this).data("filename");
            		    			var filePath = $(this).data("filepath");
            		    			$.ajax({
            		    		        method: 'POST',
            		    		        url: '${ctx}/subOrder/checkFileExists',
            		    		        data: {"subOrderAttachmentId":subOrderAttachmentId},
            		    		        dataType: 'json'
            		    		    }).done(function (result) {
            		    		        if (result.returnCode =='0000') {
            		    		        	location.href="${ctx}/subOrder/downLoadSubOrderAttachment?fileName="+fileName+"&filePath="+filePath;
            		    		        }else{
            		    		        	swal(result.returnMsg);
            		    		        }
            		    		    });
            		    		});
            		        }else{
            		        	swal("上传失败，请稍后重试");
            		        }
            		    });
	                } else {
	                    swal({
	                        title: "文件上传失败！",
	                        type: "error",
	                        confirmButtonText: "确定"
	                    });
	                }
	    		}, 
	    		error : function(responseStr) { 
	    			swal("文件上传失败");
	    		} 
	    		});
	    }
	    reader.readAsDataURL(oFile); 
	}
  
  function settime(countdown) {
		if (countdown > 0) {
			countdown--; 
		} 
		setTimeout(function() { 
			settime(countdown); 
		},1000) 
	}
  	
  function viewServiceOrderBySubOrderId(subOrderId){
		$.ajax({
	        url: "${ctx}/customerServiceOrder/customerServiceOrderView?subOrderId="+subOrderId+"&serviceType=D", 
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
  
  function viewServiceOrder(orderDtlId){
		$.ajax({
	        url: "${ctx}/customerServiceOrder/customerServiceOrderView?orderDtlId="+orderDtlId, 
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
  
  	var viewerPictures;
	$(function(){
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		
		$("#cancle").on('click',function(){
			$("#editFreightDiv").hide();
			$("#viewBlackBox").hide();
		});
		
		$("#openComment").on('click',function(){
			$(this).hide();
			$("#closeComment").show();
			var hasLoadComment = $("#hasLoadComment").val();
			var commentCount = ${commentCount};
			if(hasLoadComment == "0" && commentCount>0){
				$("#hasLoadComment").val("1");//已加载评论
				var subOrderId = $("#subOrderId").val();
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/subOrder/getComments',
			        data: {"subOrderId":subOrderId},
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			        	var mchtScoreStarWidth = result.returnData.mchtScoreStarWidth;
			        	var wuliuScoreStarWidth = result.returnData.wuliuScoreStarWidth;
			        	var html = [];
			        	html.push('<div style="clear: both;margin-left: 5px;"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;店铺评分</div>');
			        	html.push('<div style="clear: both;margin-left: 5px;">');
			        	html.push('<div style="float: left;">卖家服务&nbsp;&nbsp;&nbsp;&nbsp;</div>');
				        html.push('<div class="starBox">');
				        html.push('<ul class="star">');
				        html.push('<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li><li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li><li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li><li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li><li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>');
				        html.push('</ul>');
				        html.push('<div class="current-rating" style="width:'+mchtScoreStarWidth+'px;" id="mchtScoreStarWidth"></div>');			        	
				        html.push('</div>');
				        html.push('</div>');
				        html.push('<div style="clear: both;margin-left: 5px;">');
				        html.push('<div style="float: left;">物流服务&nbsp;&nbsp;&nbsp;&nbsp;</div>');
				        html.push('<div class="starBox">');
				        html.push('<ul class="star" >');
				        html.push('<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li><li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li><li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li><li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li><li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>');
				        html.push('</ul>');
				        html.push('<div class="current-rating" style="width:'+wuliuScoreStarWidth+'px;" id="wuliuScoreStarWidth"></div>');
				        html.push('</div>');
				        html.push('</div>');
				        html.push('<table class="o table table-striped table-bordered dataTable no-footer" style="border: none;">');
				        html.push('<tbody id="commentCustomList">');
				        var commentCustomList = result.returnData.commentCustomList;
			        	for(var i=0;i<commentCustomList.length;i++){
			        		var commentCustom = commentCustomList[i];
			        		html.push('<tr class="td-border-top">');
			        		html.push('<td width="364" style="border: none;">');
			        		html.push('<table>');
			        		html.push('<tbody>');
			        		html.push('<tr>');
			        		html.push('<td width="364" class="br" style="border: none;">');
			        		html.push('<div class="no-check">');
			        		html.push('<div class="width-60">');
			        		html.push('<img src="${ctx}/file_servelt'+commentCustom.skuPic+'">');
			        		html.push('</div>');
			        		html.push('<div class="width-226"><p class="ellipsis" title="'+commentCustom.productName+'">'+commentCustom.productName+'</p><p><a href="javascript:;">规格：'+commentCustom.productPropDesc+'</a><br>SKU码:'+commentCustom.sku+'</p></div>');
			        		html.push('<div style="float: left;">商品描述</div>');
			        		html.push('<div class="starBox" style="margin-left: 18px;">');
			        		html.push('<ul class="star" >');
			        		html.push('<li><a href="javascript:void(0)"  class="one-star" style="cursor: default;">1</a></li><li><a href="javascript:void(0)"  class="two-stars" style="cursor: default;">2</a></li><li><a href="javascript:void(0)"  class="three-stars" style="cursor: default;">3</a></li><li><a href="javascript:void(0)"  class="four-stars" style="cursor: default;">4</a></li><li><a href="javascript:void(0)"  class="five-stars" style="cursor: default;">5</a></li>');
			        		html.push('</ul>');
			        		html.push('<div class="current-rating" style="width: '+commentCustom.productScoreStarWidth+'px;" id="mchtScoreStarWidth"></div>');
			        		html.push('</div>');
			        		html.push('</td>');
			        		html.push('</tr>');
			        		html.push('</tbody>');
			        		html.push('</table>');
			        		html.push('</td>');
			        		html.push('<td width="602" class="br" style="border: none;">');
			        		html.push('<div name="commentContent" style="text-align: left; padding-left: 5px;float:left;width:402px;">'+commentCustom.content+'</div>');
			        		html.push('<div style="width: 200px;float: left;">');
			        		for(var j=0;j<commentCustom.commentPics.length;j++){
				        		html.push('<div class="width-60" style="padding-left: 5px;"><img src="${ctx}/file_servelt'+commentCustom.commentPics[j].pic+'" onclick="viewerPic2(this.src)"></div>');
				        	}
			        		html.push('</div>');
			        		if(commentCustom.appendContent){
				        		html.push('<hr style="border-top:1px dotted #ddd;clear: both;" />');
			        		}
			        		if(commentCustom.appendContent && commentCustom.betweenDays == 0){
			        			html.push('<div style="color: red;float:left;margin-left: 5px;">当天追评</div>');
		        			}else{
			        			html.push('<div style="color: red;float:left;margin-left: 5px;">'+commentCustom.betweenDays+'天后追评</div>');
		        			}
			        		html.push('<br>');
			        		var appendContent = "";
			        		if(commentCustom.appendContent){
			        			appendContent = commentCustom.appendContent;
			        		}
			        		html.push('<div name="commentContent" style="text-align: left; padding-left:5px;width:402px;float:left;">'+appendContent+'</div>');
			        		html.push('<div style="width: 200px;margin-top: -30px;float: right">');
			        		if(commentCustom.appendCommentPics){
				        		for(var k=0;k<commentCustom.appendCommentPics.length;k++){
						        	html.push('<div class="width-60" style="padding-left: 5px;"><img src="${ctx}/file_servelt'+commentCustom.appendCommentPics[k].pic+'" onclick="viewerPic2(this.src)"></div>');
				        		}
			        		}
			        		html.push('</div>');
			        		html.push('</td>');
			        		html.push('</tr>');
			        	}
						html.push('</tbody>');	
						html.push('</table>');	
			        	$("#commentArea").append(html.join(""));
			        	$("#commentArea").show();
			        }else{
			        	if(result.returnMsg){
				        	swal(result.returnMsg);
			        	}else{
			        		swal("查看评论失败，请稍后重试");
			        	}
			        }
			    });
			}else{
				if(commentCount>0){
					$("#commentArea").show();
				}
			}
		});
		
		$("#closeComment").on('click',function(){
			$(this).hide();
			$("#openComment").show();
			$("#commentArea").hide();
		});
		
		$("a[name='downLoadSubOrderAttachment']").on('click',function(){
			var subOrderAttachmentId = $(this).data("suborderattachmentid");
			var fileName = $(this).data("filename");
			var filePath = $(this).data("filepath");
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/subOrder/checkFileExists',
		        data: {"subOrderAttachmentId":subOrderAttachmentId},
		        dataType: 'json'
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		        	location.href="${ctx}/subOrder/downLoadSubOrderAttachment?fileName="+fileName+"&filePath="+filePath;
		        }else{
		        	swal(result.returnMsg);
		        }
		    });
		});
		
		$("#showDeliveryDiv").on('click',function(){
			var subOrderId = $(this).data("suborderid");
			$("#viewDeliveryDiv").show();
			$("#viewBlackBox").show();
			$("#viewDeliverySubOrderId").val(subOrderId);
		});
		
		$("a[name='remarksColor']").on('click',function(){
			var remarksColor = $(this).data("remarkscolor");
			var remarksColorHtml = $(this).html();
			$("#selectedRemark").html("旗帜设置为:"+remarksColorHtml);
			$("#selectedRemark").show();
			$("#remarksColor").val(remarksColor);
		});
		var submitting;
	  	$("#saveRemarks").on('click',function(){
	  		if(submitting){
	  			return false;	
	  		}
	  		var subOrderId = $("#subOrderId").val();
	  		var remarks = $("#remarks").val();
	  		var remarksColor = $("#remarksColor").val();
	  		submitting = true;
	  		$.ajax({
                method: 'POST',
                url: '${ctx}/subOrder/remarksBatchSave',
                data: {"subOrderIds":subOrderId,"remarks":remarks,"remarksColor":remarksColor},
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                	swal("备注成功");
                	$("#remarks").val(remarks);
                	$("#viewModal").modal('hide');
		           	table.ajax.reload(null, false);
                }else{
                	swal("备注失败，请重试");
                }
                submitting = false;
            });
	  	});
		
		var status = ${status};
		var createDate = $("#orderCreateDate").val();
		var currentStatusDateTime = $("#currentStatusDateTime").val();
		var payDateTime = $("#payDateTime").val();
		var html = [];
		var countdown;
		var countdownDesc;
		if(status == "0"){//客户付款倒计时
			var nowDate = new Date();
			var nowTime = nowDate.getTime();
			var date = new Date(createDate.replace(/-/g,"/"));
			var endPayTime = date.getTime()+30*60*1000;
			if(endPayTime>nowTime){
				var diff = endPayTime - nowTime;//时间差的毫秒数  
				countdown = diff/1000;//秒数
				settime(countdown);
				var minute = Math.round(countdown/60);
        		countdownDesc="倒计时："+minute+"分";
			}else{
			    countdownDesc = "超时未支付";
			}
			$("#waitPay").text(countdownDesc);
		}else if(status == "1"){//商家发货倒计时
			var nowDate = new Date();
			var nowTime = nowDate.getTime();
			var date = new Date($("#deliveryLastDate").val().replace(/-/g,"/"));
			var endPayTime = date.getTime();
			var isOver = false;
			if(endPayTime>nowTime){
				var diff = endPayTime - nowTime;//时间差的毫秒数  
				countdown = diff/1000;//秒数
			}else{
				isOver = true;
				var diff = nowTime - endPayTime;//时间差的毫秒数  
				countdown = diff/1000;//秒数
			}
			settime(countdown);
			var hour = Math.round(countdown/3600);
    		if(!isOver){
    			countdownDesc="倒计时："+hour+"小时";
    		}else{
    			countdownDesc="超时："+hour+"小时";
    		}
			$("#waitDeliveryTime").text(countdownDesc);
		}else if(status == "2"){//客户确认收货倒计时
			var nowDate = new Date();
			var nowTime = nowDate.getTime();
			var date = new Date(currentStatusDateTime.replace(/-/g,"/"));
			var endPayTime = date.getTime()+15*24*3600*1000;
			if(endPayTime>nowTime){
				var diff = endPayTime - nowTime;//时间差的毫秒数  
				countdown = diff/1000;//秒数
				settime(countdown);
				var day = Math.round(countdown/(3600*24));
				countdownDesc="倒计时："+day+"天";
			}else{
				
			}
			$("#waitReceive").text(countdownDesc);
		}else if(status == "3"){//订单状态完成，订单结算处理
			var orderSettlement = "即将生成";
			if(${hasSettle}){
//				orderSettlement = "账单ID："+$("#mchtSettleOrderId").val();
				orderSettlement = "已生成结算单";
			}
			$("#orderSettlement").text(orderSettlement);
		}
		
		$("#viewDelivery").on('click',function(){
			var subOrderId = $("#viewDeliverySubOrderId").val();
			var viewExpressId = $("#viewExpressId").val();
			var viewExpressNo = $("#viewExpressNo").val();
			if($.trim(viewExpressId)==""){
				swal("快递名称必选");
				return false;
			}
			if($.trim(viewExpressNo)==""){
				swal("快递单号不能为空");
				return false;
			}
			$.ajax({
			        method: 'POST',
			        url: '${ctx}/subOrder/subOrderDelivery',
			        data: {"subOrderId":subOrderId,"expressId":viewExpressId,"expressNo":viewExpressNo},
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			        	$("#viewDeliveryDiv").hide();
			        	$("#viewBlackBox").hide();
			        	$("#viewDeliverySubOrderId").val("");
			           	swal("发货成功");
			           	//写进商家发货时间
			           	$("#orderStatusDesc").text("订单状态:商家已发货");
			           	$("#tipDesc").text("如果客户提出售后要求，请积极与客户协商，做好售后服务。如有投诉并被证实，将产生违规处罚。");
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

		$(".video_close").on('click',function(){
			$("#subOrderRemarksDiv").hide();
			$("#viewDeliveryDiv").hide();
			$("#viewDirectCompensationDiv").hide();
			$("#editFreightDiv").hide();
			$("#wuliuInfoDiv").hide();
			$("#viewBlackBox").hide();
		});
		
		$("#showDirectCompensationDiv").on('click',function(){
			$("#viewDirectCompensationDiv").show();
			$("#viewBlackBox").show();
		});
		
		$("#createDirectCompensation").on('click',function(){
			if(!$("#isAgree").prop("checked")){
				swal("必须同意直接赔付给客户才能创建直赔单");
				return false;
			}
			var subOrderId = $("#subOrderId").val();
			var amount = $.trim($("#amount").val());
			var reason = $.trim($("#reason").val());
			var remarks = $.trim($("#customerServiceOrderRemarks").val());
			var contactPhone = $("#contactPhone").val();
			if(amount == ""){
				swal("直赔金额不能为空");
				return false;
			}
			var payAmount = $("#payAmount").val();
			if(Number(amount) > Number(payAmount)){
				swal("直赔金额不能大于订单实付金额");
				return false;
			}
			if(reason == ""){
				swal("请选择直赔原因");
				return false;
			}
			if(remarks == ""){
				swal("原因说明不能为空");
				return false;
			}
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/subOrder/createDirectCompensation',
		        data: {"subOrderId":subOrderId,"amount":amount,"reason":reason,"remarks":remarks,"contactPhone":contactPhone},
		        dataType: 'json'
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		           	swal("创建成功");
		        	$("#viewDirectCompensationDiv").hide();
		        	$("#viewBlackBox").hide();
		        	$("#viewDeliverySubOrderId").val("");
		        	$("#showDirectCompensationDiv").hide();
		           	$("#showDirectCompensationDiv").text("直赔单进行中");
		           	$("#showDirectCompensationDiv").prop("class","btn btn-info");
		           	$("#showDirectCompensationDiv").prop("disabled",true);
		           	$("#viewModal").modal('hide');
		           	table.ajax.reload();
		        }else if(result.returnCode =='4004'){
		        	swal(result.returnMsg);
		        }
		    });
		});
		
		$("#confirm").on('click',function(){
			if(submitting){
	  			return false;	
	  		}
			var editFreight = $("#editFreight").val();
			if(editFreight){
				var subOrderId = $("#subOrderId").val();
				var orderDtlId = $("#orderDtlId").val();
				$("input[orderDtlId='"+orderDtlId+"']").val(editFreight);
				submitting = true;
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/subOrder/editFreight',
			        data: {"subOrderId":subOrderId,"orderDtlId":orderDtlId,"editFreight":editFreight},
			        cache : false,
					async : false,
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			        	submitting = false;
			        	$("#subOrderFreight").text(result.returnData.subOrderFreight);
			        	$("#editFreightDiv").hide();
						$("#viewBlackBox").hide();
			        }else if(result.returnCode =='4004'){
			        	submitting = false;
			        	if(result.returnMsg){
			        		swal(result.returnMsg);
			        	}else{
				        	swal("修改运费失败，请稍后重试");
			        	}
			        }
			    });
			}else{
				return false;
			}
		});
	});
	function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		var bigImgPath = imgPath.substring(0,imgPath.indexOf("_S"))+imgPath.substring(imgPath.lastIndexOf("."));
		$("#viewer-pictures").append('<li><img data-original="'+bigImgPath+'" src="'+bigImgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
		var width=window.parent.document.documentElement.clientWidth;
		var height=window.parent.document.documentElement.clientHeight;
		$(".viewer-container").height(height);
		$(".viewer-container").width(width-20);
		$(".viewer-container").css("top",-$('.modal-dialog').offset().top+297);
		$(".viewer-container").css("left",-$('.modal-dialog').offset().left+6);
	}
	function viewerPic2(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
		var width=window.parent.document.documentElement.clientWidth;
		var height=window.parent.document.documentElement.clientHeight;
		$(".viewer-container").height(height);
		$(".viewer-container").width(width-20);
		$(".viewer-container").css("top",-$('.modal-dialog').offset().top+297);
		$(".viewer-container").css("left",-$('.modal-dialog').offset().left+6);
	}
  </script>
</body>
</html>
