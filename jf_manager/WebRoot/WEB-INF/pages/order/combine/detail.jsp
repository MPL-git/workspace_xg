<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
	body {font-size: 12px;padding: 10px;}
	td input,td select{border:1px solid #AECAF0;}
	.table-title{font-size: 14px;color: #333333;font-weight: 600;}
	.title-top{padding-top:10px;padding-bottom:10px;}
	.marR10{margin-right:10px;}
	.marT10{margin-top:10px;}
	.marB05{margin-bottom:5px;}
</style>
<script type="text/javascript">
	var viewerPictures;
	$(function(){
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
				$("#viewer-pictures").hide();
			}});
	});

	function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		imgPath=imgPath.replace('_S', '');
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
				$("#viewer-pictures").hide();
			}});
		viewerPictures.show();
	}

	function viewDetail(id) {
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "子订单详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	function editCombineOrderInvoice(id) {
		var invoiceUrl = "${pageContext.request.contextPath}/order/editCombineOrderInvoiceView.shtml?combineOrderId=${combineOrderCustom.id}";
		if(id){
			invoiceUrl = invoiceUrl + "&id=" + id;
		}
		$.ligerDialog.open({
			height: 410,
			width: 600,
			title: "开票信息",
			name: "INSERT_WINDOW",
			url: invoiceUrl,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	function showRejectReason() {
		$.ligerDialog.open({
			target: $("#RejectReasonModel"),
			title: '驳回详情',
			width: 600,
			height: 180,
			isResize: true,
			modal: true
		});
	}


</script>
<html>
<body>
<div class="title-top">
	<div class="table-title">
		<span>母订单信息</span>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title">母订单号</td><td>${combineOrderCustom.combineOrderCode}</td>
			<td class="title">商品总金额</td><td>${combineOrderCustom.totalAmount}</td>
			<td class="title">订单实付金额</td><td>${combineOrderCustom.totalPayAmount}</td>
			<td class="title">母订单状态</td><td>${combineOrderCustom.statusDesc}<c:if test="${combineOrderCustom.isUserDel eq '1' }"><span style="color: red;">(已删除)</span></c:if></td>
			<td class="title">下单时间</td><td><fmt:formatDate value="${combineOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td class="title">付款渠道</td>
			<td>
				<c:if test="${combineOrderCustom.orderType eq '7'}">积分转盘</c:if>
				<c:if test="${empty combineOrderCustom.paymentName && combineOrderCustom.orderType ne '7'}">免费推广</c:if>
				<c:if test="${not empty combineOrderCustom.paymentName }">${combineOrderCustom.paymentName }</c:if>
			</td>
			<td class="title">支付交易号</td><td>${combineOrderCustom.paymentNo}</td>
			<td class="title">付款成功时间</td><td><fmt:formatDate value="${combineOrderCustom.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td class="title">订单渠道</td><td>${combineOrderCustom.sourceClientDesc}</td>
			<td class="title">订单类型</td>
			<td>
				${combineOrderCustom.orderTypeDesc}<c:if test="${combineOrderCustom.promotionType=='1'}"><span style="color: red;">(推广分润)</span></c:if>
				<c:if test="${not empty combineOrderCustom.combineOrderInvoiceId}">
					<div style="float: right;">
					<c:if test="${ combineOrderCustom.combineOrderInvoiceStatus eq '0'}">
						申请开票中,<a href="javascript:editCombineOrderInvoice(${combineOrderCustom.combineOrderInvoiceId});">【查看】</a>
					</c:if>
					<c:if test="${ combineOrderCustom.combineOrderInvoiceStatus eq '1'}">
						已开票
					</c:if>
					<c:if test="${ combineOrderCustom.combineOrderInvoiceStatus eq '2'}">
						已驳回:
						<a href="javascript:showRejectReason();">【驳回原因】</a>
						<a href="javascript:editCombineOrderInvoice(${combineOrderCustom.combineOrderInvoiceId});">【重新申请】</a>
					</c:if>
					</div>
				</c:if>
				<c:if test="${empty combineOrderCustom.combineOrderInvoiceId}">
					<div style="float: right">
						<button onclick="return editCombineOrderInvoice();">申请开票</button>
					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="title">财务收款状态</td><td>${combineOrderCustom.financialStatusDesc}</td>
			<td class="title">财务收款编号</td>
			<td>${combineOrderCustom.financialNo}</td>
			<td class="title">收款登记日期</td>
			<td>
				<c:if test="${not empty combineOrderCustom.collectionRegisterDate}">
					<fmt:formatDate value="${combineOrderCustom.collectionRegisterDate}" pattern="yyyy-MM-dd"/>
				</c:if>
				<c:if test="${empty combineOrderCustom.collectionRegisterDate}">
					<fmt:formatDate value="${combineOrderCustom.payDate}" pattern="yyyy-MM-dd"/>
				</c:if>
			</td>
			<td class="title">取消原因</td><td colspan="3">${combineOrderCustom.cancelReason }</td>
			<%-- <td class="title">财务操作人</td><td>${combineOrderCustom.financialStaffName}</td>
            <td class="title">财务操作最后时间</td><td><fmt:formatDate value="${combineOrderCustom.financialUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
		</tr>
		<tr>
			<td class="title">收货人</td><td>${combineOrderCustom.receiverName}</td>
			<td class="title">联系电话</td><td>${combineOrderCustom.receiverPhone}</td>
			<td class="title">收货地址</td><td colspan="5">${combineOrderCustom.receiverAddress}</td>
		</tr>
		<tr>
			<td class="title">用户ID</td><td>${combineOrderCustom.memberId}</td>
			<td class="title">用户昵称</td><td>${combineOrderCustom.memberNick}</td>
			<td class="title">客户备注</td><td colspan="5">${combineOrderCustom.remarks}</td>
		</tr>
	</table>
</div>

<div class="title-top">
	<div class="table-title">
		<span>POP子订单</span>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title">SKU图</td>
			<td class="title">子订单编号</td>
			<td class="title">发货时间</td>
			<td class="title">订单状态</td>
			<td class="title">售后状态</td>
			<td class="title">商品ID</td>
			<td class="title">品牌</td>
			<td class="title">名称</td>
			<td class="title">货号</td>
			<td class="title">规格</td>
			<td class="title">吊牌价</td>
			<td class="title">醒购价</td>
			<td class="title">数量</td>
			<td class="title">商家优惠</td>
			<td class="title">订单运费</td>
			<td class="title">平台优惠</td>
			<td class="title">积分优惠</td>
			<td class="title">实付金额</td>
		</tr>
		<c:forEach var="orderDtl_P" items="${ orderDtlCustoms_P}">
			<tr>
				<td><img src="${pageContext.request.contextPath}/file_servelt${orderDtl_P.sku_pic }" onclick="viewerPic(this.src)" style="width:60px;height:60px;"></td>
				<td><a href="javascript:viewDetail(${orderDtl_P.sub_order_id });">${orderDtl_P.sub_order_code }</a></td>
				<td><fmt:formatDate value="${orderDtl_P.delivery_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${orderDtl_P.sub_status_desc }</td>
				<td><c:choose> <c:when test="${empty orderDtl_P.service_type_desc }">无</c:when> <c:otherwise>${orderDtl_P.service_type_desc }<br>${orderDtl_P.service_status_desc }</c:otherwise> </c:choose></td>
				<td>${orderDtl_P.product_id }</td>
				<td>${orderDtl_P.brand_name }</td>
				<td>${orderDtl_P.product_name }</td>
				<td>${orderDtl_P.art_no }</td>
				<td>${orderDtl_P.product_prop_desc }</td>
				<td>${orderDtl_P.tag_price }</td>
				<td>${orderDtl_P.sale_price }</td>
				<td>${orderDtl_P.quantity }</td>
				<td>${orderDtl_P.mcht_preferential }</td>
				<td>${orderDtl_P.freight}</td>
				<td>${orderDtl_P.platform_preferential }</td>
				<td>${orderDtl_P.integral_preferential }</td>
				<td>${orderDtl_P.pay_amount }</td>
			</tr>
		</c:forEach>
	</table>
</div>

<div class="title-top">
	<div class="table-title">
		<span>SPOP子订单</span>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title">SKU图</td>
			<td class="title">子订单编号</td>
			<td class="title">发货时间</td>
			<td class="title">订单状态</td>
			<td class="title">售后状态</td>
			<td class="title">商品ID</td>
			<td class="title">品牌</td>
			<td class="title">名称</td>
			<td class="title">货号</td>
			<td class="title">规格</td>
			<td class="title">吊牌价</td>
			<td class="title">醒购价</td>
			<td class="title">结算单价</td>
			<td class="title">数量</td>
			<td class="title">商家优惠</td>
			<td class="title">订单运费</td>
			<td class="title">平台优惠</td>
			<td class="title">积分优惠</td>
			<td class="title">实付金额</td>
		</tr>

		<c:forEach var="orderDtl" items="${orderDtlCustoms}">
			<tr>
				<td><img src="${pageContext.request.contextPath}/file_servelt${orderDtl.sku_pic }" onclick="viewerPic(this.src)" style="width:60px;height:60px;"></td>
				<td><a href="javascript:viewDetail(${orderDtl.sub_order_id });">${orderDtl.sub_order_code }</a></td>
				<td><fmt:formatDate value="${orderDtl.delivery_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${orderDtl.sub_status_desc }</td>
				<td><c:choose> <c:when test="${empty orderDtl.service_type_desc }">无</c:when> <c:otherwise>${orderDtl.service_type_desc }<br>${orderDtl.service_status_desc }</c:otherwise> </c:choose></td>
				<td>${orderDtl.product_id }</td>
				<td>${orderDtl.brand_name }</td>
				<td>${orderDtl.product_name }</td>
				<td>${orderDtl.art_no }</td>
				<td>${orderDtl.product_prop_desc }</td>
				<td>${orderDtl.tag_price }</td>
				<td>${orderDtl.sale_price }</td>
				<td>${orderDtl.settle_price }</td>
				<td>${orderDtl.quantity }</td>
				<td>${orderDtl.mcht_preferential }</td>
				<td>${orderDtl.freight}</td>
				<td>${orderDtl.platform_preferential }</td>
				<td>${orderDtl.integral_preferential }</td>
				<td>${orderDtl.pay_amount }</td>
			</tr>
		</c:forEach>
	</table>
</div>

<div class="title-top">
	<div class="table-title">
		<span>订单优惠</span>
	</div>
	<table class="gridtable marT10">
		<tr>
			<td class="title">优惠归类</td>
			<td class="title">使用范围</td>
			<td class="title">优惠类型</td>
			<td class="title">优惠内容</td>
			<td class="title">优惠金额</td>
			<td class="title">商品优惠金额</td>
		</tr>
		<c:forEach var="orderPreferentialInfo" items="${orderPreferentialInfoCustoms}">
			<tr>

				<td>${orderPreferentialInfo.belongDesc}</td>
				<td>${orderPreferentialInfo.rangDesc}</td>
				<td>${orderPreferentialInfo.preferentialTypeDesc} <c:if test="${orderPreferentialInfo.preferentialType==1 }">(${orderPreferentialInfo.preferentialId })</c:if>  </td>
				<td>${orderPreferentialInfo.preferentialContent}</td>
				<td>${orderPreferentialInfo.totalAmout}</td>
				<td>${orderPreferentialInfo.contentProduct}</td>
			</tr>
		</c:forEach>
	</table>
</div>
<%--开票驳回详情--%>
<div id="RejectReasonModel" style="text-align:center;display: none">
	<table class="gridtable">
		<tr>
			<td colspan="1" class="title">驳回原因<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<textarea id="rejectReason" name="rejectReason" rows="5" cols="50" class="text" maxlength="100" readonly>${combineOrderCustom.combineOrderInvoiceRejectReason}</textarea>
			</td>
		</tr>
	</table>
</div>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
