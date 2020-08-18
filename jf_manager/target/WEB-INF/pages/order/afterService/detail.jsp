<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}
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

function viewSubDetail(id) {
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

function viewWuliu(subOrderId) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()*0.35,
		title: "查看物流动态",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/afterService/viewWuliu.shtml?subOrderId=" + subOrderId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function updateCustomerServiceOrder(customerServiceOrderId) {
	$.ligerDialog.open({
 		height: '500',
		width: '600',
		title: "填写物流信息",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/complain/updateCustomerServiceOrderManager.shtml?customerServiceOrderId="+customerServiceOrderId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

//创建工单
function addWork(id) {
	 $.ligerDialog.open({
			height: 600,
			width: 950,
			title: "创建工单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/afterService/addcustomerServiceOrderWork.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
	  });
 }
</script>
<html>
<body>
	<div class="title-top">
		<table class="gridtable baseInfo">
          	<tr class="title-first">
          		<td>
          			<span>商家简称：${customerServiceOrderCustom.shortName}</span>
          			<span>公司名称：${customerServiceOrderCustom.companyName}</span>
          			<span>商家序号：${customerServiceOrderCustom.mchtCode}</span>
          			<c:if test="${woRks}">
          			<span><a href="javascript:addWork(${customerServiceOrderCustom.id});">【创建工单】</a></span>
          			</c:if>
          		</td>
          	</tr>
          	
          	<tr class="title-first">
          		<td>
          			<span>售后单编号：${customerServiceOrderCustom.orderCode}</span>
          			<span>申请时间：<fmt:formatDate value='${customerServiceOrderCustom.createDate}' pattern='yyyy-MM-dd HH:mm'/></span>
          			<span>申请人联系电话：${customerServiceOrderCustom.contactPhone}</span>
          			<span>售后状态：${customerServiceOrderCustom.proStatusDesc}（${customerServiceOrderCustom.serviceTypeDesc}${customerServiceOrderCustom.statusDesc}）
          		</td>
          	</tr>
          	
          	<tr class="title-second">
          		<td>
          			<span>售后进度：
          			<!-- 退款单售后进度 -->
          			<c:if test="${customerServiceOrderCustom.serviceType=='A'}">
          				<c:if test="${customerServiceOrderCustom.proStatus=='A1'}">
          					客户申请退款 >> <span class="color01">等待商家同意退款 >></span><span class="color02"> 平台退款 >> 售后完成</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='A2'}">
          					客户申请退款 >> 商家同意退款 >> <span class="color01">等待平台退款 >></span><span class="color02"> 售后完成</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='A3'}">
          					客户申请退款 >> 商家不同意退款 >> <span class="color01">售后已拒绝</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='A4'}">
          					客户申请退款 >> 商家同意退款 >> 平台退款 >> <span class="color01">售后完成</span>
          				</c:if>
          			</c:if>
          			
          			<!-- 退货单售后进度 -->
          			<c:if test="${customerServiceOrderCustom.serviceType=='B'}">
          				<c:if test="${customerServiceOrderCustom.proStatus=='B1'}">
          					客户申请退货 >> <span class="color01">等待商家同意申请 >></span><span class="color02"> 客户寄件 >> 商家同意退款 >> 平台退款 >> 售后完成</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='B2'}">
          					客户申请退货 >> 商家同意申请 >> <span class="color01">等待客户寄件 >></span><span class="color02"> 商家同意退款 >> 平台退款 >> 售后完成</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='B3'}">
          					客户申请退货 >> 商家不同意申请 >> <span class="color01">售后已拒绝</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='B4'}">
          					客户申请退货 >> 商家同意申请 >> 客户寄件 >> <span class="color01">等待商家同意退款 >></span><span class="color02"> 平台退款 >> 售后完成</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='B5'}">
          					客户申请退货 >> 商家同意申请 >> 客户寄件 >> 商家同意退款 >> <span class="color01">等待平台退款 >></span><span class="color02"> 售后完成</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='B6'}">
          					客户申请退货 >> 商家同意申请 >> 客户寄件 >> 商家不同意退款 >> <span class="color01">售后已拒绝</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='B7'}">
          					客户申请退货 >> 商家同意申请 >> 客户寄件 >> 商家同意退款 >> 平台退款 >> <span class="color01">售后完成</span>
          				</c:if>
          			</c:if>
          			
          			<!-- 换货单售后进度 -->
          			<c:if test="${customerServiceOrderCustom.serviceType=='C'}">
          				<c:if test="${customerServiceOrderCustom.proStatus=='C1'}">
          					客户申请换货 >> <span class="color01">等待商家同意申请 >></span><span class="color02"> 客户寄件 >> 商家同意换货>> 商家寄件 >> 售后完成</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='C2'}">
          					客户申请换货 >> 商家同意申请 >> <span class="color01">等待客户寄件 >></span><span class="color02"> 商家同意换货 >> 商家寄件 >> 售后完成</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='C3'}">
          					客户申请换货 >> 商家不同意申请 >> <span class="color01">售后已拒绝</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='C4'}">
          					客户申请换货 >> 商家同意申请 >> 客户寄件 >> <span class="color01">等待商家同意换货 >></span><span class="color02"> 商家寄件 >> 售后完成</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='C5'}">
          					客户申请换货 >> 商家同意申请 >> 客户寄件 >> 商家同意换货 >> <span class="color01">等待商家寄件 >></span><span class="color02"> 售后完成</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='C6'}">
          					客户申请换货 >> 商家同意申请 >> 客户寄件 >> 商家不同意换货 >> <span class="color01">售后已拒绝</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='C7'}">
          					客户申请换货 >> 商家同意申请 >> 客户寄件 >> 商家同意换货 >> 商家寄件 >> <span class="color01">售后完成</span>
          				</c:if>
          			</c:if>
          			
          			<!-- 直赔单售后进度 -->
          			<c:if test="${customerServiceOrderCustom.serviceType=='D'}">
          				<c:if test="${customerServiceOrderCustom.proStatus=='D1'}">
          					商家同意退款 >> <span class="color01">等待平台退款 >></span><span class="color02"> 售后完成</span>
          				</c:if>
          				<c:if test="${customerServiceOrderCustom.proStatus=='D2'}">
          					商家同意退款 >> 平台已退款 >> <span class="color01">售后完成</span>
          				</c:if>
          			</c:if>
          			</span>
          		</td>
          	</tr>
          	
          	<tr>
          		<td>
          			<span>售后原因：${customerServiceOrderCustom.reasonDesc}</span>
          		</td>
          	</tr>
          	
          	<tr>
          		<td>
          			<span>售后说明：${customerServiceOrderCustom.remarks}</span>
          		</td>
          	</tr>
          	<c:if test="${customerServicePics!=null && fn:length(customerServicePics) > 0}">
          	<tr>
          		<td>
          		<c:forEach var="customerServicePic" items="${customerServicePics}"> 
          			<img src="${pageContext.request.contextPath}/file_servelt${customerServicePic.pic}" width='80' height='80' onclick='viewerPic(this.src)'>
          		</c:forEach>
          		</td>
          	</tr>
          	</c:if>
          	<c:if test="${customerServiceOrderCustom.serviceType!='C'}">
	          	<tr class="title-third">
	          		<td>
	          			<span><c:if test="${customerServiceOrderCustom.serviceType!='D'}">退款</c:if><c:if test="${customerServiceOrderCustom.serviceType=='D'}">赔付</c:if>金额：<span class="amtSpan">${customerServiceOrderCustom.amount}</span>元</span>
	          		</td>
	          	</tr>
          	</c:if>
          	
          	<!-- 退货单售后进度 -->
  			<c:if test="${customerServiceOrderCustom.serviceType=='B'}">
  				<c:if test="${customerServiceOrderCustom.proStatus=='B2'}">
  					<tr>
		          		<td>
		          			<span><a href="javascript:;" onclick="updateCustomerServiceOrder(${customerServiceOrderCustom.id });">【填写物流信息】</a></span>
		          		</td>
		          	</tr>
  				</c:if>
  			</c:if>
  			
  			<!-- 换货单售后进度 -->
  			<c:if test="${customerServiceOrderCustom.serviceType=='C'}">
  				<c:if test="${customerServiceOrderCustom.proStatus=='C2'}">
  					<tr>
		          		<td>
		          			<span><a href="javascript:;" onclick="updateCustomerServiceOrder(${customerServiceOrderCustom.id });">【填写物流信息】</a></span>
		          		</td>
		          	</tr>
  				</c:if>
  			</c:if>
        </table>
	</div>
	
		<div class="title-top">
			<div class="table-title">
				<span class="marR10">
				<c:if test="${customerServiceOrderCustom.serviceType=='D'}">原订单信息</c:if>
				<c:if test="${customerServiceOrderCustom.serviceType!='D'}">${fn:substring(customerServiceOrderCustom.serviceTypeDesc, 0, 2)}商品信息</c:if>
				</span>
			</div>
		<table class="gridtable marT10 baseInfo">
          	<tr class="title-first">
          		<td>
          			<span>原订单编号：${customerServiceOrderCustom.subOrderCode}&nbsp;<a href="javascript:viewSubDetail(${customerServiceOrderCustom.subOrderId})" class="table-title-link">【查看】</a></span>
          			<span>发货时间：<fmt:formatDate value='${customerServiceOrderCustom.deliveryDate}' pattern='yyyy-MM-dd HH:mm'/></span>
          			<span>活动ID：${customerServiceOrderCustom.activityId}</span>
          		</td>
          	</tr>
          	<tr>
          		<td>
          			<span style="color: #999999;">客户原收货人：${customerServiceOrderCustom.receiverName}</span>
          			<span style="color: #999999;">电话：${customerServiceOrderCustom.receiverPhone}</span>
          			<span style="color: #999999;">地址：${customerServiceOrderCustom.receiverAddress}</span>
          			<span><a href="javascript:;" onclick="viewWuliu(${customerServiceOrderCustom.subOrderId});">【查看物流动态】</a></span>
          		</td>
          	</tr>
        </table>
	</div>
	
	<c:if test="${customerServiceOrderCustom.serviceType!='D'}">
 	<div class="title-top">
		<table class="gridtable">
        <tr class="title-first">
            <td>商品</td>
            <td>单价</td>
            <td>购买数量</td>
            <td>售后数量</td>
            <td>商家优惠</td>
            <td>运费</td>
            <td>平台优惠</td>
            <td>积分优惠</td>
            <td>实收金额</td>
		</tr>
        <tr>
           	<td>
           		<img style="float:left;margin:8px;" src="${pageContext.request.contextPath}/file_servelt${customerServiceOrderCustom.skuPic}" width="80" height="80" onclick='viewerPic(this.src)'>
           		<span style="display:block;text-align:left;margin-top:8px;">
           			<span style="display:block;margin-bottom:4px;">${customerServiceOrderCustom.productName}<br></span>
           			<span style="display:block;margin-bottom:4px;">货号：${customerServiceOrderCustom.artNo}<br></span>
           			<span style="display:block;color: #A1A1A1;margin-bottom:4px;">规格：${customerServiceOrderCustom.productPropDesc}<br></span>
           			<span style="display:block;color: #A1A1A1;margin-bottom:4px;">SKU码：${customerServiceOrderCustom.sku}<br></span>
           		</span>
           	</td>
           	<td>${customerServiceOrderCustom.salePrice}</td>
           	<td>${customerServiceOrderCustom.productQuantity}</td>
           	<td>${customerServiceOrderCustom.quantity}</td>
           	<td>${customerServiceOrderCustom.mchtPreferential}</td>
           	<td>${customerServiceOrderCustom.freight}</td>
           	<td>${customerServiceOrderCustom.platformPreferential}</td>
           	<td>${customerServiceOrderCustom.integralPreferential}</td>
           	<td>${customerServiceOrderCustom.payAmount}</td>
        </tr>
        </table>
	</div>
	</c:if>
	
	<div class="title-top">
		<div class="table-title">
			<span class="marR10">售后记录</span>
		</div>
		<table class="gridtable marT10 LogInfo">
          	<tr class="title-fourth">
          		<td>操作者</td>
          		<td colspan="5">内容</td>
          	</tr>
			<c:forEach var="customerServiceLogCustom" items="${customerServiceLogCustoms}"> 
          	<tr>
          		<td>${customerServiceLogCustom.operatorTypeDesc}<br><fmt:formatDate value='${customerServiceLogCustom.createDate}' pattern='yyyy-MM-dd HH:mm'/></td>
          		<td colspan="5">
          			${customerServiceLogCustom.content}
          			<c:if test="${customerServiceLogCustom.serviceLogPics!=null && fn:length(customerServiceLogCustom.serviceLogPics) > 0}">
          			<br>
          			<c:forEach var="serviceLogPic" items="${customerServiceLogCustom.serviceLogPics}">
          				<img src="${pageContext.request.contextPath}/file_servelt${serviceLogPic.pic}" width='80' height='80' onclick='viewerPic(this.src)'>
          			</c:forEach>
          			</c:if>
          		</td>
			</tr>
			</c:forEach>
        </table>
	</div>
		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
