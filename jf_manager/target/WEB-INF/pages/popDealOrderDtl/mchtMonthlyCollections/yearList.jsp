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
			var defaultYear = $("#defaultYear").val();
			var defaultMonth = $("#defaultMonth").val();
			var year = $("#year").val();
			var month = $("#month").val();
			var maxDate = new Date(defaultYear,defaultMonth);
			var date = new Date(year,month);
			if(date>maxDate){
				commUtil.alertError("对不起，时间不能超过上个月。");
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
		location.href="${pageContext.request.contextPath}/popDealOrderDtl/mchtMonthlyCollections/yearExport.shtml?mchtCode="+mchtCode+"&name="+name+"&year="+year+"&month="+month;
	});
});

function serviceOrderList(mchtId,dateBegin,dateEnd){
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
     url:"/popDealOrderDtl/mchtMonthlyCollections/yearData.shtml",
     listGrid:{ columns: [
			            { display: '商家序号',name:'mchtCode'},
		                { display: '商家公司名称',render: function (rowdata, rowindex) {
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
		                { display: '折让',render: function (rowdata, rowindex) {
		                	if(rowdata.discount){
		                		return formatMoney(rowdata.discount,2);
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
		                	var year = $("#year").val();
		                	var month = $("#month").val();
		                	var dateBegin = year+"-01"+"-01";
		                	var lastDay = getLastDay(year,month);
		                	var dateEnd = year+"-"+month+"-"+lastDay;
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
		<input type="hidden" id="defaultYear" value="${year}">
		<input type="hidden" id="defaultMonth" value="${month}">
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
			<div class="search-td-label" style="float:left;">年份：</div>
			<div class="l-panel-search-item" >
				<select id="year" name="year" style="width: 150px;">
					<option value="">请选择</option>
					<option value="2017" <c:if test="${year eq '2017'}">selected="selected"</c:if>>2017</option>
					<option value="2018" <c:if test="${year eq '2018'}">selected="selected"</c:if>>2018</option>
					<option value="2019" <c:if test="${year eq '2019'}">selected="selected"</c:if>>2019</option>
					<option value="2020" <c:if test="${year eq '2020'}">selected="selected"</c:if>>2020</option>
				</select>
		 	 </div>
			 </div>
			 
		 	<div class="search-td">
			<div class="search-td-label" style="float:left;">截止月份：</div>
			<div class="l-panel-search-item" >
				<select id="month" name="month" style="width: 150px;">
					<option value="">请选择</option>
					<option value="01" <c:if test="${month eq '01'}">selected="selected"</c:if>>01</option>
					<option value="02" <c:if test="${month eq '02'}">selected="selected"</c:if>>02</option>
					<option value="03" <c:if test="${month eq '03'}">selected="selected"</c:if>>03</option>
					<option value="04" <c:if test="${month eq '04'}">selected="selected"</c:if>>04</option>
					<option value="05" <c:if test="${month eq '05'}">selected="selected"</c:if>>05</option>
					<option value="06" <c:if test="${month eq '06'}">selected="selected"</c:if>>06</option>
					<option value="07" <c:if test="${month eq '07'}">selected="selected"</c:if>>07</option>
					<option value="08" <c:if test="${month eq '08'}">selected="selected"</c:if>>08</option>
					<option value="09" <c:if test="${month eq '09'}">selected="selected"</c:if>>09</option>
					<option value="10" <c:if test="${month eq '10'}">selected="selected"</c:if>>10</option>
					<option value="11" <c:if test="${month eq '11'}">selected="selected"</c:if>>11</option>
					<option value="12" <c:if test="${month eq '12'}">selected="selected"</c:if>>12</option>
				</select>
		 	 </div>
			 </div>
			 
			<!-- <div style="display: inline-flex;">
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="搜索" id="searchbtn2">
					<input type="hidden" id="searchbtn">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 65px;height: 30px;cursor: pointer;" value="导出汇总表" id="export">
				</div>
			</div> -->
		 </div>
		 <div class="search-tr"  >
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