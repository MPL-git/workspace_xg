<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
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
.ant-btn {
	line-height: 1.499;
	position: relative;
	display: inline-block;
	font-weight: 400;
	white-space: nowrap;
	text-align: center;
	background-image: none;
	border: 1px solid transparent;
	-webkit-box-shadow: 0 2px 0 rgba(0,0,0,0.015);
	box-shadow: 0 2px 0 rgba(0,0,0,0.015);
	cursor: pointer;
	-webkit-transition: all .3s cubic-bezier(.645, .045, .355, 1);
	transition: all .3s cubic-bezier(.645, .045, .355, 1);
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	-ms-touch-action: manipulation;
	touch-action: manipulation;
	height: 32px;
	padding: 0 15px;
	font-size: 14px;
	border-radius: 4px;
	color: rgba(0,0,0,0.65);
	background-color: #fff;
	border-color: #d9d9d9;
}
.ant-btn-red {
	color: #fff;
	background-color: #FF5A44;
	border-color: #FF5A44;
	text-shadow: 0 -1px 0 rgba(0,0,0,0.12);
	-webkit-box-shadow: 0 2px 0 rgba(0,0,0,0.045);
	box-shadow: 0 2px 0 rgba(0,0,0,0.045);
}
</style>
<script type="text/javascript">
function formatMoney(s, n)
{
   n = n > 0 && n <= 20 ? n : 2;
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
   var l = s.split(".")[0].split("").reverse(),
   r = s.split(".")[1];
   t = "";
   for(i = 0; i < l.length; i ++ )
   {
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
   }
   return t.split("").reverse().join("") + "." + r;
}
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

function toConfirmStatus(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "确认结算单",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettleOrder/toConfirmStatus.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toPlatformInvoiceStatus(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "开票状态",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettleOrder/toPlatformInvoiceStatus.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toPay(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "结算单付款",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettleOrder/toPay.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewMchtDepositDtl(mchtCode) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "余额变化明细表",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtDeposit/mchtDepositDtlList.shtml?mchtCode=" + mchtCode,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
var listConfig={
	     url:"/mchtSettleOrder/historyPayData.shtml?id=${mchtSettleOrder.id}",
	     listGrid:{ columns: [
				            { display: '付款ID', name:'id'},
				            { display: '类型',name:'typeDesc'},
			                { display: '结算单ID', name:'mchtSettleOrderId'},
				            { display: '付款日期', render: function (rowdata, rowindex) {
				            	var payDate = new Date(rowdata.payDate);
			                	return payDate.format("yyyy-MM-dd");
			                }},
			                { display: '付款编号',name:'payCode'},
			                { display: '结算金额（元）',render: function (rowdata, rowindex) {
			                	if(rowdata.settleAmount){
									return formatMoney(rowdata.settleAmount,2);	                		
			                	}else{
			                		return "0.00";
			                	}
			                }},
			                { display: '实付款金额(元)',render: function (rowdata, rowindex) {
			                	if(rowdata.payAmount){
									return formatMoney(rowdata.payAmount,2);	                		
			                	}else{
			                		return "0.00";
			                	}
			                }},
			                { display: '转保证金抵缴（元）',render: function (rowdata, rowindex) {
			                	if(rowdata.depositAmount){
									return formatMoney(rowdata.depositAmount,2);	                		
			                	}else{
			                		return "0.00";
			                	}
			                }},
			                { display: '保证金记录',render: function (rowdata, rowindex) {
			                	return '<a href="javascript:;" onclick="viewMchtDepositDtl('+"'"+rowdata.mchtCode+"'"+')">查看</a>';
			                }},
			                { display: '付款人',name:'payStaffName'},
			                { display: '是否确认',name:'confirmStatusDesc'}
			                ],
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
	      },  							
	     container:{
	        toolBarName:"toptoolbar",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  };

function planPay(id) {
	$.ligerDialog.open({
		height: 220,
		width: 400,
		title: "续缴保证金",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettleOrder/planPay.shtml?id=" + id,
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
<body style="overflow-y: scroll;">
	<div class="title-top">
	<input type="hidden" id="mchtSettleOrderId" value="${mchtSettleOrder.id}">
		<table class="gridtable baseInfo">
          	<tr class="title-first">
          		<td>公司名称</td>
          		<td>${mchtInfo.companyName}</td>
          		<td>类型</td>
          		<td>
          			<c:if test="${mchtSettleOrder.mchtType eq '1'}">SPOP</c:if>
          			<c:if test="${mchtSettleOrder.mchtType eq '2'}">POP</c:if>
          		</td>
          	</tr>
          	<tr class="title-second">
          		<td>结算单ID</td>
          		<td>${mchtSettleOrder.id}</td>
          		<td>结算日期</td>
          		<td><fmt:formatDate value="${mchtSettleOrder.beginDate}" pattern="yyyy-MM-dd"/> 到<fmt:formatDate value="${mchtSettleOrder.endDate}" pattern="yyyy-MM-dd"/></td>
          	</tr>
        </table>
		<div class="table-title">
				<span class="marR10">
					结算数据：
				</span>
		</div>
		<table class="gridtable baseInfo">
          	<tr class="title-first">
          		<td>类型</td>
          		<td>商品数量</td>
          		<td>商品金额</td>
          		<td>商家优惠</td>
				<td>订单运费</td>
				<c:if test="${mchtSettleOrderCustom.mchtType eq '1' and mchtSettleOrderCustom.isManageSelf eq '1' or mchtInfo.mchtType eq '1' and mchtInfo.isManageSelf eq '1'}">
					<td>自营运费</td>
				</c:if>
          		<td>平台优惠</td>
          		<td>积分优惠</td>
          		<td>定金金额</td>
          		<td>订单金额</td>
          		<td>技术服务费</td>
          		<td>结算单金额</td>
          	</tr>
          	<tr class="title-second">
          		<td>完成订单</td>
          		<td>${mchtSettleOrderCustom.quantity}</td>
          		<td>${mchtSettleOrderCustom.amount}</td>
          		<td>${mchtSettleOrderCustom.mchtPreferential}</td>
          		<td>${mchtSettleOrderCustom.freight}</td>
				<c:if test="${mchtSettleOrderCustom.mchtType eq '1' and mchtSettleOrderCustom.isManageSelf eq '1' or mchtInfo.mchtType eq '1' and mchtInfo.isManageSelf eq '1'}">
				<td>${mchtSettleOrderCustom.manageSelfFreight}</td>
				</c:if>
          		<td>${mchtSettleOrderCustom.platformPreferential}</td>
          		<td>${mchtSettleOrderCustom.integralPreferential}</td>
          		<td>${mchtSettleOrderCustom.deposit}</td>
          		<td>${mchtSettleOrderCustom.payAmount}</td>
          		<td>${mchtSettleOrder.commissionAmount}</td>
          		<td>${mchtSettleOrderCustom.productSettleAmount}</td>
          	</tr>
          	<tr class="title-first">
          		<td>直赔单</td>
          		<td>0</td>
          		<td>0</td>
          		<td>0</td>
          		<td>0</td>
				<c:if test="${mchtSettleOrderCustom.mchtType eq '1' and mchtSettleOrderCustom.isManageSelf eq '1' or mchtInfo.mchtType eq '1' and mchtInfo.isManageSelf eq '1'}">
					<td>0</td>
				</c:if>
          		<td>0</td>
          		<td>0</td>
          		<td>0</td>
          		<td>0</td>
          		<td>0</td>
          		<td><c:if test="${mchtSettleOrder.refundAmount ne 0}">-</c:if>${mchtSettleOrder.refundAmount}</td>
          	</tr>
          	<tr class="title-second">
          		<td>合计</td>
          		<td>${mchtSettleOrderCustom.quantity}</td>
          		<td>${mchtSettleOrderCustom.amount}</td>
          		<td>${mchtSettleOrderCustom.mchtPreferential}</td>
          		<td>${mchtSettleOrderCustom.freight}</td>
				<c:if test="${mchtSettleOrderCustom.mchtType eq '1' and mchtSettleOrderCustom.isManageSelf eq '1' or mchtInfo.mchtType eq '1' and mchtInfo.isManageSelf eq '1'}">
					<td>${mchtSettleOrderCustom.manageSelfFreight}</td>
				</c:if>
          		<td>${mchtSettleOrderCustom.platformPreferential}</td>
          		<td>${mchtSettleOrderCustom.integralPreferential}</td>
          		<td>${mchtSettleOrder.earnestMoney}</td>
          		<td>${mchtSettleOrder.orderAmount}</td>
          		<td>${mchtSettleOrder.commissionAmount}</td>
          		<td>${mchtSettleOrder.settleAmount}</td>
          	</tr>
        </table>
	</div>
	
		<div class="title-top">
			<div class="table-title">
				<span class="marR10">
					其他信息：
				</span>
			</div>
			<table class="gridtable marT10 baseInfo">
	          	<tr class="title-first">
	          		<td>
	          			导出明细
	          		</td>
	          		<td colspan="2">
	          			<a href="${pageContext.request.contextPath}/mchtSettleOrder/exportOrderDtl.shtml?mchtSettleOrderId=${mchtSettleOrder.id}" target="_blank">下载订单明细</a>
	          		</td>
	          		<td colspan="1">
	          			<a href="${pageContext.request.contextPath}/mchtSettleOrder/exportCustomerServiceOrder.shtml?mchtSettleOrderId=${mchtSettleOrder.id}" target="_blank">下载直赔单明细</a>
	          		</td>
	          		<td colspan="1">
	          			<a href="${pageContext.request.contextPath}/mchtSettleOrder/exportSubDepositOrder.shtml?mchtSettleOrderId=${mchtSettleOrder.id}" target="_blank">下载预付定金明细</a>
	          		</td>
	          	</tr>
	          	<tr class="title-second">
	          		<td>
	          			商家开户行及账号 			
	          		</td>
	          		<td colspan="2"><c:if test="${not empty mchtBankAccountCustom}">${mchtBankAccountCustom.bankName}：${mchtBankAccountCustom.depositBank}</c:if></td>
	          		<td colspan="2"><c:if test="${not empty mchtBankAccountCustom}">${mchtBankAccountCustom.accName}&nbsp;&nbsp;${mchtBankAccountCustom.accNumber}&nbsp;&nbsp;【${mchtBankAccountCustom.statusDesc}】</c:if></td>
          		</tr>
          		<tr>
          			<td>开票类型</td>
          			<td colspan="4">
          			<c:if test="${not empty mchtCollectTypeDesc}">
          				${mchtCollectTypeDesc}（${BillingAmount}元）
          			</c:if>
          			</td>
          		</tr>
          		<tr>
          			<td>商家税票信息</td>
          			<td colspan="4">
          				纳税人识别号：${mchtTaxInvoiceInfo.taxNumber}<br>     
						开户行及账号：${mchtTaxInvoiceInfo.depositBank}&nbsp;&nbsp;&nbsp;&nbsp;${mchtTaxInvoiceInfo.accountNumber}<br>   
						地址、电话：${mchtTaxInvoiceInfo.address}&nbsp;&nbsp;&nbsp;&nbsp;${mchtTaxInvoiceInfo.tel}
					</td>
          		</tr>
          		<tr>
          			<td>商家地址</td>
          			<td colspan="4">
          				商家的收件地址：${province}${city}${county}${mchtInfo.contactAddress}   
          				 电话：${mchtContact.mobile} 
          				  收件人：${mchtContact.contactName}
          			</td>
          		</tr>
          		<tr>
          			<td>本结算单应结金额</td>
          			<td colspan="2">划到保证金金额：${mchtSettleOrder.depositAmount}元
						<input type="button" class="ant-btn ant-btn-red" value="续缴" onclick="planPay(${mchtSettleOrder.id})" <c:if test="${mchtSettleOrder.needPayAmount eq '0.00' or mchtSettleOrder.confirmStatus ne '3'}">style="display: none;"</c:if>>
<%--						<input type="button" class="ant-btn ant-btn-red" value="续缴" onclick="planPay(${mchtSettleOrder.id})" <c:if test="${mchtSettleOrder.depositAmount ne '0.00' and mchtSettleOrder.confirmStatus and '3' or payStatusDesc and '未排款'}">style="display: none;"</c:if>>--%>
					</td>
          			<td colspan="2">本期应结：${needSettleAmount}元</td>
          		</tr>
          		<tr>
          			<td>确认状态</td>
          			<td colspan="2">${confirmStatusDesc}&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${mchtSettleOrder.confirmStatus ne '3'}"><input type="button" value="确认" style="width: 60px;height: 30px;cursor: pointer;" onclick="toConfirmStatus(${mchtSettleOrder.id});"></c:if></td>
          			<td colspan="2">商家：<fmt:formatDate value="${mchtSettleOrder.mchtConfirmDate}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;平台：<fmt:formatDate value="${mchtSettleOrder.platformConfirmDate}" pattern="yyyy-MM-dd"/></td>
          		</tr>
          		<tr <c:if test="${mchtSettleOrder.confirmStatus ne '3'}">style="display: none;"</c:if>>
          			<td>开票状态</td>
          			<td colspan="2">${platformInvoiceStatusDesc}&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${mchtSettleOrder.platformInvoiceStatus ne '3' && mchtSettleOrder.confirmStatus eq '3'}"><input type="button" value="操作" style="width: 60px;height: 30px;cursor: pointer;" onclick="toPlatformInvoiceStatus(${mchtSettleOrder.id});"></c:if></td>
          			<td colspan="2">平台开票时间：<fmt:formatDate value="${mchtSettleOrder.platformInvoiceDate}" pattern="yyyy-MM-dd"/></td>
          		</tr>
					<tr <c:if test="${mchtSettleOrder.confirmStatus ne '3'}">style="display: none;"</c:if>>
          			<td>付款状态</td>
          			<td colspan="2">${payStatusDesc}（实付：${mchtSettleOrder.payAmount}元、还需支付：${mchtSettleOrder.needPayAmount}元    ${payStaffName}）&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${mchtSettleOrder.payStatus ne '3' && mchtSettleOrder.payStatus ne '4' && mchtSettleOrder.confirmStatus eq '3'}"><input type="button" value="付款" style="width: 60px;height: 30px;cursor: pointer;" onclick="toPay(${mchtSettleOrder.id});"></c:if><c:if test="${exceptionAmount ne mchtSettleOrder.settleAmount}"><span style="color: red;font-weight:bold;">【异常】</span></c:if></td>
          			<td colspan="2">付款日期：<fmt:formatDate value="${mchtSettleOrder.payDate}" pattern="yyyy-MM-dd"/></td>
          		</tr>
	        </table>
	</div>
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>
</html>
