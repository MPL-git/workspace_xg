<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
 $(function(){
		$("#searchbtn2").bind('click',function(){
			var today = $("#today").val();
			var endDate = $("#endDate").val();
			if(endDate>today){
				commUtil.alertError("对不起，时间不能超过今天");
				return false;
			}else{
				$("#searchbtn").click();
			}
		});
	}); 
 function formatMoney(s, n)
 {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
    r = s.split(".")[1];
    t = "";
    for(i = 0; i < l.length; i ++ )
    {
       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
 }
function exportByMchtId(mchtId,mchtMonthlyCollectionsId,date_begin,date_end) {
	location.href="${pageContext.request.contextPath}/popDealOrderDtl/mchtMonthlyCollections/exportByMchtId.shtml?mchtId="+mchtId+"&mchtMonthlyCollectionsId="+mchtMonthlyCollectionsId+"&date_begin="+date_begin+"&date_end="+date_end;	
}
$(function(){
	$("#export").bind('click',function(){
		var mchtCode = $("#mchtCode").val();
		var name = $("#name").val();
		var year = $("#year").val();
		var month = $("#month").val();
		location.href="${pageContext.request.contextPath}/popDealOrderDtl/mchtMonthlyCollections/dayExport.shtml?mchtCode="+mchtCode+"&name="+name+"&year="+year+"&month="+month;
	});
});

function serviceOrderList(mchtId,dateBegin,dateEnd){
	if(!dateEnd){
		commUtil.alertError("当月截止日期不能为空");
		return false;
	}
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "直赔单列表",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/afterService/list.shtml?mchtId="+mchtId+"&date_begin="+dateBegin+"&date_end="+dateEnd+"&serviceType=D",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function popDealOrderDtlList(mchtCode,dateBegin,dateEnd,accept,refund){
	if(!dateEnd){
		commUtil.alertError("当月截止日期不能为空");
		return false;
	}
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "POP交易商品明细",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/popDealOrderDtl/list.shtml?mchtCode=" + mchtCode+"&date_begin="+dateBegin+"&date_end="+dateEnd+"&accept="+accept+"&refund="+refund,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function payToMchtLogList(mchtId,pay_date_begin,pay_date_end,mchtCode){
	if(!pay_date_end){
		commUtil.alertError("当月截止日期不能为空");
		return false;
	}
	 $.ligerDialog.open({
			height: $(window).height()-10,
			width: $(window).width()-10,
			title: "《向商家付款记录》",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/payToMchtLog/list.shtml?mchtId="+mchtId+"&pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end+"&type=1"+"&mchtCode="+mchtCode,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
}

function mchtInfo(mchtId){
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "商家基础资料",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId="+mchtId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 function getLastDay(year,month)     
 {     
  var new_year = year;  //取当前的年份     
  var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）     
  if(month>12)      //如果当前大于12月，则年份转到下一年     
  {     
  new_month -=12;    //月份减     
  new_year++;      //年份增     
  }     
  var new_date = new Date(new_year,new_month,1);        //取当年当月中的第一天     
  return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期     
 }   
 
 var listConfig={
     url:"/popDealOrderDtl/mchtMonthlyCollections/dayData.shtml",
     listGrid:{ columns: [
			            { display: '商家序号',name:'mchtCode'},
		                { display: '商家公司名称', render: function (rowdata, rowindex) {
		                	return '<a href="javascript:;" onclick="mchtInfo('+rowdata.mchtId+');">'+rowdata.mchtCompanyName+'</a>';
		                }},
		                { display: '店铺名称', name:'mchtShopName'},
			            { display: '期初结欠', render: function (rowdata, rowindex) {
			            	if(rowdata.beginUnpay){
								return formatMoney(rowdata.beginUnpay,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '数量',render: function (rowdata, rowindex) {
							return rowdata.productCount+"";	                		
		                }},
		                { display: '商品金额',render: function (rowdata, rowindex) {
		                	if(rowdata.productAmount){
			                	return formatMoney(rowdata.productAmount,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '商家优惠', render: function (rowdata, rowindex) {
		                	if(rowdata.mchtPreferential){
								return formatMoney(rowdata.mchtPreferential,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '平台优惠',render: function (rowdata, rowindex) {
		                	if(rowdata.platformPreferential){
			                	return formatMoney(rowdata.platformPreferential,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '积分优惠',render: function (rowdata, rowindex) {
		                	if(rowdata.integralPreferential){
			                	return formatMoney(rowdata.integralPreferential,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '订单客户实付',render: function (rowdata, rowindex) {
		                	if(rowdata.orderClientPayAmount){
			                	return formatMoney(rowdata.orderClientPayAmount,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: 'POP技术服务费',render: function (rowdata, rowindex) {
		                	if(rowdata.commissionAmount){
		                		return formatMoney(rowdata.commissionAmount,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: 'POP结算金额',render: function (rowdata, rowindex) {
		                	if(rowdata.settleAmount){
								return formatMoney(rowdata.settleAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '直赔单扣款',render: function (rowdata, rowindex) {
		                	if(rowdata.refundAmount){
		                		return formatMoney(rowdata.refundAmount,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '实付款',render: function (rowdata, rowindex) {
		                	if(rowdata.payAmount){
		                		return formatMoney(rowdata.payAmount,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '抵缴保证金',render: function (rowdata, rowindex) {
		                	if(rowdata.deductionDepositTotal){
		                		return formatMoney(rowdata.deductionDepositTotal,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '期末应付',render: function (rowdata, rowindex) {
		                	if(rowdata.discountedEndNeedPay){
		                		return formatMoney(rowdata.discountedEndNeedPay,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '查看',width:"8%",render: function (rowdata, rowindex) {
		                	var mchtId = rowdata.mchtId;
		                	var mchtCode = rowdata.mchtCode;
		                	var dateBegin = "";
		                	var dateEnd = $("#endDate").val();
		                	if(dateEnd){
		                		dateBegin = dateEnd.substring(0,7)+"-01";
		                	}
		                	return '<a href="javascript:;" onclick="popDealOrderDtlList('+"'"+mchtCode+"'"+','+"'"+dateBegin+"'"+','+"'"+dateEnd+"'"+',1,0);">收款</a>&nbsp;&nbsp;<a href="javascript:;" onclick="popDealOrderDtlList('+"'"+mchtCode+"'"+','+"'"+dateBegin+"'"+','+"'"+dateEnd+"'"+',0,1);">退款</a>&nbsp;&nbsp;<a href="javascript:;" onclick="serviceOrderList('+mchtId+','+"'"+dateBegin+"'"+','+"'"+dateEnd+"'"+');">直赔</a>&nbsp;&nbsp;<a href="javascript:;" onclick="payToMchtLogList('+mchtId+','+"'"+dateBegin+"'"+','+"'"+dateEnd+"'"+','+"'"+mchtCode+"'"+');">付款</a>';
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      },  							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" id="today" value="${nowDate}">
		<div class="search-pannel">
		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >商家名称：</div>
			<div class="search-td-condition" >
				<input type="text" id="name" name="name" >
			</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >主营类目：</div>
				<div class="search-td-condition" >
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" disabled="disabled">
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;">
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
			</div>
			<div class="search-td">
			<div class="search-td-label" style="float:left;">当月截止日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="endDate" name="endDate" value="${nowDate}"/>
				<script type="text/javascript">
					$(function() {
						$("#endDate").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			</div>
			 
			<div style="display: inline-flex;">
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="搜索" id="searchbtn2">
					<input type="hidden" id="searchbtn">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 65px;height: 30px;cursor: pointer;" value="导出汇总表" id="export">
				</div>
			</div>
			
		 </div>
		 	
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>