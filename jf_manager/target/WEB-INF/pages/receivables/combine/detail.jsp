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
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
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
               <td class="title">母订单状态</td><td>${combineOrderCustom.statusDesc}</td>
               <td class="title">下单时间</td><td><fmt:formatDate value="${combineOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr> 
	        <tr>
               <td class="title">付款渠道</td><td>${combineOrderCustom.paymentName}</td> 
               <td class="title">支付交易号</td><td>${combineOrderCustom.paymentNo}</td>
               <td class="title">付款成功时间</td><td><fmt:formatDate value="${combineOrderCustom.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
               <td class="title">订单渠道</td><td>${combineOrderCustom.sourceClientDesc}</td>
               <td class="title">订单类型</td><td>${combineOrderCustom.orderTypeDesc}</td>
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
               <td class="title">主图</td> 
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
               <td class="title">平台优惠</td>
               <td class="title">积分优惠</td>
               <td class="title">实付金额</td>
               <td class="title">类型</td>
               <td class="title">供应商</td>
               <td class="title">佣金比例</td>
               <td class="title">结算金额</td>
               <td class="title">佣金金额</td>
               <td class="title">结算ID</td>
			</tr>
			<c:forEach var="orderDtl_P" items="${ orderDtlCustoms_P}"> 
	        <tr>
               	<td><img alt="" src="${pageContext.request.contextPath}/file_servelt${orderDtl_P.productPic}" onclick="viewerPic(this.src)" style="width:60px;height:60px;"></td>
               	<td>${orderDtl_P.subOrderCode}</td>
               	<td><fmt:formatDate value="${orderDtl_P.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
               	<td>${orderDtl_P.subStatusDesc}</td>
               	<td><c:choose> <c:when test="${empty orderDtl_P.serviceTypeDesc}">无</c:when> <c:otherwise>${orderDtl_P.serviceTypeDesc}<br>${orderDtl_P.serviceStatusDesc}</c:otherwise> </c:choose></td>
               	<td>${orderDtl_P.productId}</td>
               	<td>${orderDtl_P.productBrandName}</td>
               	<td>${orderDtl_P.productName}</td>
               	<td>${orderDtl_P.productArtNo}</td>
               	<td>${orderDtl_P.productPropDesc}</td>
               	<td>${orderDtl_P.tagPrice}</td>
               	<td>${orderDtl_P.salePrice}</td>
               	<td>${orderDtl_P.quantity}</td>
               	<td>${orderDtl_P.mchtPreferential}</td>
               	<td>${orderDtl_P.platformPreferential}</td>
               	<td>${orderDtl_P.integralPreferential}</td>
               	<td>${orderDtl_P.payAmount}</td>
               	<td>${orderDtl_P.isManageSelfDesc}POP</td>
               	<td>${orderDtl_P.mchtShopName}</td>
               	<td>${orderDtl_P.popCommissionRate}</td>
               	<td>${orderDtl_P.balanceMoney}</td>
               	<td>${orderDtl_P.commissionMoney}</td>
               	<td></td>
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
               <td class="title">主图</td> 
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
               <td class="title">平台优惠</td>
               <td class="title">积分优惠</td>
               <td class="title">实付金额</td>
               <td class="title">类型</td>
               <td class="title">供应商</td>
               <td class="title">应付货款</td>
               <td class="title">毛利</td>
               <td class="title">结算ID</td>
			</tr>
			
			<c:forEach var="orderDtl" items="${ orderDtlCustoms}"> 
	        <tr>
               	<td><img alt="" src="${pageContext.request.contextPath}/file_servelt${orderDtl.productPic}" onclick="viewerPic(this.src)" style="width:60px;height:60px;"></td>
               	<td>${orderDtl.subOrderCode}</td>
               	<td><fmt:formatDate value="${orderDtl.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
               	<td>${orderDtl.subStatusDesc}</td>
               	<td><c:choose> <c:when test="${empty orderDtl.serviceTypeDesc}">无</c:when> <c:otherwise>${orderDtl.serviceTypeDesc}<br>${orderDtl.serviceStatusDesc}</c:otherwise> </c:choose></td>
               	<td>${orderDtl.productId}</td>
               	<td>${orderDtl.productBrandName}</td>
               	<td>${orderDtl.productName}</td>
               	<td>${orderDtl.productArtNo}</td>
               	<td>${orderDtl.productPropDesc}</td>
               	<td>${orderDtl.tagPrice}</td>
               	<td>${orderDtl.salePrice}</td>
               	<td>${orderDtl.settlePrice}</td>
               	<td>${orderDtl.quantity}</td>
               	<td>${orderDtl.mchtPreferential}</td>
               	<td>${orderDtl.platformPreferential}</td>
               	<td>${orderDtl.integralPreferential}</td>
               	<td>${orderDtl.payAmount}</td>
               	<td>${orderDtl.isManageSelfDesc}SPOP</td>
               	<td>${orderDtl.mchtShopName}</td>
               	<td>${orderDtl.needPayMoeny}</td>
               	<td>${orderDtl.profitMoeny}</td>
               	<td></td>     	             	               	
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
               <td>${orderPreferentialInfo.preferentialTypeDesc}</td>
               <td>${orderPreferentialInfo.preferentialContent}</td>
               <td>${orderPreferentialInfo.totalAmout}</td>
               <td>${orderPreferentialInfo.contentProduct}</td>
			</tr>
			</c:forEach>
	        </table>	        	  
		</div>
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
