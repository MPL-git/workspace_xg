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

function toPlatformCollectStatus(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "收票状态",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettleOrder/toPlatformCollectStatus.shtml?id=" + id,
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
	     url:"/mchtSettleOrder/poolHistoryPayData.shtml?id=${mchtSettleOrder.id}",
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
</script>
<html>
<body style="overflow-y: scroll;">
	<div class="title-top">
	<input type="hidden" id="mchtSettleOrderId" value="${mchtSettleOrder.id}">
		<table class="gridtable baseInfo">
          	<tr class="title-first">
          		<td>公司名称</td>
          		<td colspan="2">${mchtInfo.companyName}</td>
          		<td colspan="2">
          			<c:if test="${mchtInfo.mchtType eq '1'}">SPOP</c:if>
          			<c:if test="${mchtInfo.mchtType eq '2'}">POP</c:if>
          		</td>
          	</tr>
          	<tr class="title-second">
          		<td>结算日期</td>
          		<td colspan="2"><fmt:formatDate value="${mchtSettleOrder.beginDate}" pattern="yyyy-MM-dd"/> 到<fmt:formatDate value="${mchtSettleOrder.endDate}" pattern="yyyy-MM-dd"/></td>
          		<td colspan="2">结算单ID：${mchtSettleOrder.id}</td>
          	</tr>
          	<tr class="title-first">
          		<td>完成订单</td>
          		<td colspan="2">商品货款金额：<c:if test="${not empty mchtSettleOrderCustom.payAmount}">${mchtSettleOrderCustom.payAmount}元</c:if></td>
          		<td colspan="2">商家优惠金额：<c:if test="${not empty mchtSettleOrderCustom.mchtPreferential}">${mchtSettleOrderCustom.mchtPreferential}元</c:if></td>
          	</tr>
          	<tr class="title-second">
          		<td>直赔单</td>
          		<td colspan="2">平台垫付直赔单金额：<c:if test="${mchtSettleOrder.refundAmount ne 0}">${mchtSettleOrder.refundAmount}元</c:if></td>
          		<td colspan="2">商家优惠、直赔单由SPOP商家承担</td>
          	</tr>
          	<tr class="title-first">
          		<td>本结算单</td>
          		<td colspan="4">结算单金额：${mchtSettleOrder.settleAmount}元</td>
          	</tr>
          	<tr class="title-second">
          		<td>导出明细</td>
          		<td colspan="2">
          			<a href="${pageContext.request.contextPath}/mchtSettleOrder/exportOrderDtl.shtml?mchtSettleOrderId=${mchtSettleOrder.id}" target="_blank">下载订单明细</a>
          		</td>
          		<td colspan="2">
          			<a href="${pageContext.request.contextPath}/mchtSettleOrder/exportCustomerServiceOrder.shtml?mchtSettleOrderId=${mchtSettleOrder.id}" target="_blank">下载直赔单明细</a>
          		</td>
          	</tr>
          	<tr class="title-first">
          		<td>商家开户行及账号</td>
          		<td colspan="2">${mchtBankAccount.depositBank}</td>
	          	<td colspan="2">${mchtBankAccount.accNumber}</td>
          	</tr>
          	<tr class="title-second">
          		<td>开票类型</td>
          		<td colspan="4">${PTKP_LX}</td>
          	</tr>
          	<tr class="title-first">
          		<td>平台税票信息</td>
          		<td colspan="4">
          			公司名称：${PTKP_MC}&nbsp;&nbsp; &nbsp;&nbsp;
					纳税人识别号：${PTKP_SBH}&nbsp;&nbsp; &nbsp;&nbsp;
					开户行及账号：${PTKP_KHH}&nbsp;&nbsp; &nbsp;&nbsp;${PTKP_ZH}   
					地址、电话：${PTKP_DZ}&nbsp;&nbsp; &nbsp;&nbsp;${PTKP_DH}
				</td>
          	</tr>
          	<tr class="title-second">
          		<td>开票类型及收件地址</td>
          		<td colspan="4">
          			开票类型：${PTKP_LX}
					平台的收件地址：${PTKP_SJDZ}&nbsp;&nbsp; &nbsp;&nbsp;电话：${PTKP_SJDH}&nbsp;&nbsp; &nbsp;&nbsp;收件人：${PTKP_SJR}
				</td>
          	</tr>
          	<tr class="title-first">
          		<td>本结算单应结金额</td>
          		<td colspan="2">
          			抵缴保证金金额：${mchtSettleOrder.depositAmount}元
				</td>
          		<td colspan="2">
          			本次需结算金额：${mchtSettleOrder.needPayAmount}元
				</td>
          	</tr>
          	<tr class="title-second">
          		<td>确认状态</td>
          		<td colspan="2">${confirmStatusDesc}&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${mchtSettleOrder.confirmStatus eq '2'}"><input type="button" value="确认" style="width: 60px;height: 30px;cursor: pointer;" onclick="toConfirmStatus(${mchtSettleOrder.id});"></c:if></td>
          		<td colspan="2">商家：<fmt:formatDate value="${mchtSettleOrder.mchtConfirmDate}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;平台：<fmt:formatDate value="${mchtSettleOrder.platformConfirmDate}" pattern="yyyy-MM-dd"/></td>
          	</tr>
          	<tr class="title-first">
          		<td>商家开票状态</td>
          		<td colspan="2">
          			${mchtInvoiceStatusDesc}
				</td>
          		<td colspan="2">
          			${expressName}&nbsp;&nbsp;&nbsp;&nbsp;${mchtSettleOrder.mchtInvoiceExpressNo}
				</td>
          	</tr>
          	<tr class="title-first">
          		<td>收票状态</td>
          		<td colspan="4">
          			${platformCollectStatusDesc}&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${mchtSettleOrder.confirmStatus eq '3' && mchtSettleOrder.platformCollectStatus eq '1'}"><input type="button" value="操作" style="width: 60px;height: 30px;cursor: pointer;" onclick="toPlatformCollectStatus(${mchtSettleOrder.id});"></c:if>
				</td>
          	</tr>
          	<tr class="title-first" <c:if test="${mchtSettleOrder.confirmStatus ne '3'}">style="display: none;"</c:if>>
          		<td>付款状态</td>
				<td colspan="2">${payStatusDesc}（实付：${mchtSettleOrder.payAmount}元、${payStaffName}）&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${mchtSettleOrder.payStatus ne '3' && mchtSettleOrder.payStatus ne '4' && mchtSettleOrder.confirmStatus eq '3'}"><input type="button" value="付款" style="width: 60px;height: 30px;cursor: pointer;" onclick="toPay(${mchtSettleOrder.id});"></c:if></td>
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
